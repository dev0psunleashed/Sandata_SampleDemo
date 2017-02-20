package com.sandata.lab.billing.impl;

import com.sandata.lab.billing.model.BillingResponse;
import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.dl.model.Billing;
import com.sandata.lab.data.model.dl.model.BillingDetail;
import com.sandata.lab.data.model.dl.model.BusinessEntity;
import com.sandata.lab.data.model.dl.model.Visit;
import org.apache.camel.Exchange;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.sandata.lab.billing.utils.log.OracleDataLogger.CreateLogger;
import static java.lang.String.format;

/**
 * Process visits and determine if they can be moved to billing.
 * <p/>
 *
 * @author David Rutgos
 */
@SuppressWarnings("unchecked")
public class ProcessVisit {

    private OracleDataService oracleDataService;

    public void handler(Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange, oracleDataService.getLoggerService());

        logger.start();

        Connection connection = null;

        try {

            connection = oracleDataService.getConnectionPoolDataService().getConnection();
            connection.setAutoCommit(false);

            logger.trace(exchange.getFromRouteId(), null, "ProcessVisit: handler: START");

            Integer visitSk = (Integer)exchange.getIn().getBody();

            if (visitSk == null || visitSk <= 0) {
                String errMsg = String.format("%s: handler: ERROR: VISIT_SK == null", getClass().getName());
                logger.error(exchange.getFromRouteId(), null, errMsg);
                throw new SandataRuntimeException(errMsg);
            }

            logger.trace(exchange.getFromRouteId(), (long)visitSk, "ProcessVisit: handler: Processing...");

            Object result = oracleDataService.getEntitiesForId(
                    "SELECT * FROM VISIT WHERE VISIT_SK = ?",
                    "com.sandata.lab.data.model.dl.model.Visit",
                    visitSk
            );

            logger.trace(String.format("%s: handler: SQL: SELECT * FROM VISIT WHERE VISIT_SK = %d", getClass().getName(), visitSk));

            if (result == null) {
                String errMsg = String.format("%s: handler: ERROR: [VISIT_SK=%d]: result == null", getClass().getName(), visitSk);
                logger.error(exchange.getFromRouteId(), (long)visitSk, errMsg);
                throw new SandataRuntimeException(errMsg);
            }

            List<Visit> visitList = (List<Visit>)result;

            Visit visit = visitList.get(0);

            if (visit.isVisitApprovedIndicator() == null) {
                logger.info(String.format("%s: handler: [VISIT_SK=%d]: visit.isVisitApprovedIndicator() == null", getClass().getName(), visitSk));
                return;
            }

            if (!visit.isVisitApprovedIndicator()) {
                logger.info(String.format("%s: handler: [VISIT_SK=%d]: visit.isVisitApprovedIndicator() == false", getClass().getName(), visitSk));
                return;
            }

            logger.info(String.format("%s: handler: [VISIT_SK=%d]: visit.isVisitApprovedIndicator() == true", getClass().getName(), visitSk));

            String businessEntId = visit.getBusinessEntityID();

            Object agency = oracleDataService.getEntitiesForId(
                    "SELECT * FROM BE WHERE BE_ID = ? AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1",
                    "com.sandata.lab.data.model.dl.model.BusinessEntity",
                    businessEntId
            );

            logger.trace(String.format("%s: handler: SQL: SELECT * FROM BE WHERE BE_ID = %s", getClass().getName(), businessEntId));

            if (agency == null) {
                String errMsg = String.format("%s: handler: ERROR: [BE_ID=%s] agency == null", getClass().getName(), businessEntId);
                logger.error(exchange.getFromRouteId(), (long)visitSk, errMsg);
                throw new SandataRuntimeException(errMsg);
            }

            List<BusinessEntity> businessEntityList = (List<BusinessEntity>)agency;

            if (businessEntityList.size() != 1) {
                String errMsg = String.format("%s: handler: ERROR: [BE_ID=%s] businessEntityList.size()[%d] != 1",
                        getClass().getName(), businessEntId, businessEntityList.size());
                logger.error(exchange.getFromRouteId(), (long)visitSk, errMsg);
                throw new SandataRuntimeException(errMsg);
            }

            Long schedEventSk = null;
            if (visit.getScheduleEventSK() == null) {
                String errMsg = String.format("%s: handler: ERROR: [BE_ID=%s] schedEventSk == null",
                        getClass().getName(), businessEntId);
                logger.warn(exchange.getFromRouteId(), (long)visitSk, errMsg);

            } else {
                schedEventSk = visit.getScheduleEventSK().longValue();
                logger.trace(String.format("%s: handler: [SCHED_EVNT_SK=%d]", getClass().getName(), schedEventSk));
            }

            BillingResponse billingResponse = oracleDataService.getBillingResponse(connection, visitSk, logger);

            if (billingResponse.getBilling() == null) {
                logger.error("ProcessVisit: billingResponse.getBilling() == null");
                return;
            }

            if (billingResponse.getBilling().getBillingSK() == null) {
                logger.warn("ProcessVisit: billingResponse.getBilling().getBillingSK() == null");
            }

            Billing billingRecord = null;
            if (billingResponse.getBilling().getBillingSK() != null) {

                billingRecord = oracleDataService.getBillingRecord(connection, billingResponse.getBilling().getBillingSK().longValue(), logger);
            }

            if (billingRecord == null) {

                logger.trace("insertBilling: START");

                long billingSk = oracleDataService.execute(connection, "PKG_BILLING", "insertBilling", new DataMapper().map(billingResponse.getBilling()));

                logger.trace(String.format("insertBilling: END: [BILLING_SK=%d]", billingSk));

                logger.trace("insertBillingDetail: START");

                long billingDetailSk = oracleDataService.execute(connection, "PKG_BILLING", "insertBillingDet", new DataMapper().map(billingResponse.getBillingDetail()));

                logger.trace(String.format("insertBillingDetail: END: [BILLING_DET_SK=%d]", billingDetailSk));

            }
            else {
                logger.trace(String.format("ProcessVisit: updateBilling: START... BillingSK=[%d]", billingRecord.getBillingSK().longValue()));

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                Billing billing = billingResponse.getBilling();
                BillingDetail billingDetail = billingResponse.getBillingDetail();

                Date billingDetailDate = billingDetail.getBillingDetailDate();
                if (billingDetailDate == null) {
                    logger.error("ProcessVisit: updateBilling: billingDetailDate == null");
                }
                else {
                    String billingDetailDateString = simpleDateFormat.format(billingDetailDate);

                    int count = oracleDataService.executeCount(
                            "SELECT COUNT(*) TOTAL FROM BILLING_DET " +
                                    "  WHERE BE_ID = ? AND PT_ID = ? " +
                                    "      AND PAYER_ID = ? AND CONTR_ID = ? " +
                                    "      AND SVC_NAME = ? AND BILLING_DET_DATE = TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')",
                            "TOTAL",
                            billingDetail.getBusinessEntityID(),
                            billingDetail.getPatientID(),
                            billingDetail.getPayerID(),
                            billingDetail.getContractID(),
                            billingDetail.getServiceName().value(),
                            billingDetailDateString
                    );

                    if (count > 0) {
                        logger.warn(String.format("ProcessVisit: BillingDetail already exists!: [BE_ID=%s][PT_ID=%s]" +
                                "[PAYER_ID=%s][CONTR_ID=%s][SVC_NAME=%s][BILLING_DET_DATE=%s]",
                                billingDetail.getBusinessEntityID(),
                                billingDetail.getPatientID(),
                                billingDetail.getPayerID(),
                                billingDetail.getContractID(),
                                billingDetail.getServiceName().value(),
                                billingDetailDateString));
                    } else {

                        logger.info(String.format("ProcessVisit: INSERT: BillingDetail: [BE_ID=%s][PT_ID=%s]" +
                                        "[PAYER_ID=%s][CONTR_ID=%s][SVC_NAME=%s][BILLING_DET_DATE=%s]",
                                billingDetail.getBusinessEntityID(),
                                billingDetail.getPatientID(),
                                billingDetail.getPayerID(),
                                billingDetail.getContractID(),
                                billingDetail.getServiceName().value(),
                                billingDetailDateString));

                        // Adjust Start Date
                        if (billingRecord.getBillingStartDate().after(billing.getBillingStartDate())) {
                            billingRecord.setBillingStartDate(billing.getBillingStartDate());
                        }

                        // Adjust End Date
                        if (billingRecord.getBillingEndDate().before(billing.getBillingEndDate())) {
                            billingRecord.setBillingEndDate(billing.getBillingEndDate());
                        }

                        // Recalculate BillingTotalUnits
                        if (billingRecord.getBillingTotalUnit() != null
                                && billing.getBillingTotalUnit() != null) {

                            BigDecimal billingTotalUnits = BigDecimal.valueOf(billingRecord.getBillingTotalUnit().longValue() + billing.getBillingTotalUnit().longValue());
                            billingRecord.setBillingTotalUnit(billingTotalUnits);

                            logger.info(String.format("ProcessVisit: updateBilling: [BillingTotalUnit=%d]", billingTotalUnits.longValue()));

                        } else {
                            logger.warn("ProcessVisit: updateBilling: billingRecord.getBillingTotalUnit() != null && billing.getBillingTotalUnit() != null");
                        }

                        // Recalculate BillingTotalAmount
                        float hours = Math.round(billingRecord.getBillingTotalUnit().longValue() / 3600f);

                        logger.trace(String.format("ProcessVisit: updateBilling: [HOURS=%.2f]", hours));

                        billingRecord.setBillingTotalAmount(BigDecimal.valueOf(hours * billingDetail.getBillingDetailRateAmount().doubleValue()));

                        logger.trace(String.format("ProcessVisit: updateBilling: [BillingTotalAmount=%.2f]", billingRecord.getBillingTotalAmount()));

                        // Update Billing
                        logger.trace("ProcessVisit: updateBilling: *** BEFORE ***");
                        billingRecord.setRecordUpdatedBy("MW: Billing Process");
                        billingRecord.setRecordUpdateTimestamp(new Date());
                        long billingSk = oracleDataService.execute(connection, "PKG_BILLING", "updateBilling", new DataMapper().map(billingRecord));
                        logger.trace(String.format("ProcessVisit: updateBilling: *** AFTER ***: UPDATED: [BILLING_SK=%d]", billingSk));

                        logger.trace("ProcessVisit: insertBillingDetail: START");

                        long billingDetailSk = oracleDataService.execute(connection, "PKG_BILLING", "insertBillingDet", new DataMapper().map(billingDetail));

                        logger.trace(String.format("ProcessVisit: insertBillingDetail: END: [BILLING_DET_SK=%d]", billingDetailSk));

                    }
                }

                logger.trace("ProcessVisit: updateBilling: END");
            }

            connection.commit();

        } catch (Exception e) {

            e.printStackTrace();

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: EXCEPTION: %s", getClass().getName(), e.getMessage());
            logger.error(exchange.getFromRouteId(), null, errMsg);
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {

            // Close the connection
            this.oracleDataService.closeOracleConnection(connection);

            logger.trace(exchange.getFromRouteId(), null, "ProcessVisit: handler: END");
            logger.stop();
        }
    }

    public OracleDataService getOracleDataService() {
        return oracleDataService;
    }

    public void setOracleDataService(OracleDataService oracleDataService) {
        this.oracleDataService = oracleDataService;
    }
}
