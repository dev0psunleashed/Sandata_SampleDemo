package com.sandata.lab.rest.billing.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.data.SandataRandUtil;
import com.sandata.lab.common.utils.date.DateUtil;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.extended.billing.preview.BillingPreviewSummary;
import com.sandata.lab.data.model.dl.model.extended.billing.preview.LineOfBusinessDetail;
import com.sandata.lab.data.model.dl.model.extended.billing.preview.PayerDetail;
import com.sandata.lab.data.model.dl.model.extended.billing.preview.SubmissionTypeDetail;
import com.sandata.lab.data.model.dl.model.find.InvoiceFindResult;
import com.sandata.lab.data.model.dl.model.find.InvoiceFindType;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.billing.utils.log.OracleDataLogger;

import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import org.apache.camel.Exchange;
import org.apache.cxf.message.MessageContentsList;

import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import static com.sandata.lab.common.utils.string.StringUtil.IsNullOrEmpty;
import static com.sandata.lab.rest.billing.utils.log.OracleDataLogger.CreateLogger;
import static java.lang.String.format;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */
@SuppressWarnings("unchecked")
public class InvoiceDataService extends RestDataService {

    private static Map<String, Object> lockedPreviewRequests = new HashMap<>();

    public void findBilling(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange, oracleDataService.getLoggerService());

        logger.start();

        try {

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            String status = (String) exchange.getIn().getHeader("status");

            String invoiceFindTypeString = (String) exchange.getIn().getHeader("invoice_find_type");
            if (invoiceFindTypeString == null && StringUtil.IsNullOrEmpty(status)) {
                throw new SandataRuntimeException("InvoiceFindType (invoice_find_type) is required!");
            }

            // Verify that the value sent for InvoiceFindType is valid
            InvoiceFindType invoiceFindType = null;
            if (StringUtil.IsNullOrEmpty(status)) {
                try {
                    invoiceFindType = InvoiceFindType.fromValue(invoiceFindTypeString);

                } catch (IllegalArgumentException iae) {
                    throw new SandataRuntimeException(format("InvoiceFindType (invoice_find_type) is not correct value: [%s]", invoiceFindTypeString));
                }
            }

            int page = (int) exchange.getIn().getHeader("page");
            int pageSize = (int) exchange.getIn().getHeader("page_size");

            String payerName = (String) exchange.getIn().getHeader("payer_name");
            String patientId = (String) exchange.getIn().getHeader("patient_id");
            String contract = (String) exchange.getIn().getHeader("contract");

            String fromDateTimeString = (String) exchange.getIn().getHeader("from_date_time");
            String toDateTimeString = (String) exchange.getIn().getHeader("to_date_time");

            // Validate the correct date format is being used. Throws SandataRuntimeException is date/time are not valid and/or null;
            SimpleDateFormat utcFormat = new SimpleDateFormat(DateUtil.SANDATA_UTC_DATE_TIME_FORMAT);
            Date fromDateTime = null;
            Date toDateTime = null;
            
            try {
                fromDateTime = utcFormat.parse(fromDateTimeString);
            } catch (Exception e) {
                throw new SandataRuntimeException(String.format("FromDateTime (from_date_time) must be in format: %s!", DateUtil.SANDATA_UTC_DATE_TIME_FORMAT));
            }
            
            try {
                toDateTime = utcFormat.parse(toDateTimeString);
            } catch (Exception e) {
                throw new SandataRuntimeException(String.format("ToDateTime (to_date_time) must be in format: %s!", DateUtil.SANDATA_UTC_DATE_TIME_FORMAT));
            }

            String invoiceNum = (String) exchange.getIn().getHeader("invoice_num");
            String patientFirstName = (String) exchange.getIn().getHeader("patient_first_name");
            String patientLastName = (String) exchange.getIn().getHeader("patient_last_name");
            String location = (String) exchange.getIn().getHeader("location");
            String lineOfBusiness = (String) exchange.getIn().getHeader("line_of_business");
            String submissionStatus = (String) exchange.getIn().getHeader("submission_status");
            String editErrorReason = (String) exchange.getIn().getHeader("edit_error_reason");
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            Response response = oracleDataService.findBilling(bsnEntId,
                    payerName,
                    patientId,
                    contract,
                    fromDateTime,
                    toDateTime,
                    invoiceNum,
                    patientFirstName,
                    patientLastName,
                    location,
                    lineOfBusiness,
                    submissionStatus,
                    status,
                    editErrorReason,
                    sortOn,
                    direction,
                    page,
                    pageSize,
                    invoiceFindType
            );

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());
            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getBillingDetailForPK(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange, oracleDataService.getLoggerService());

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            Long invoiceSk = (Long) mcl.get(0);
            if (invoiceSk == null || invoiceSk <= 0) {
                throw new SandataRuntimeException("InvoiceSK (billing_sk) is required!");
            }
            exchange.getIn().setBody(oracleDataService.getBillingDetailForPK(invoiceSk));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void deleteBilling (Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange, oracleDataService.getLoggerService());

        logger.start();

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = getOracleDataService().getOracleConnection();
            connection.setAutoCommit(false);

            List<Long> sequenceKeys = (List<Long>) exchange.getIn().getBody();
            if (sequenceKeys == null || sequenceKeys.size() == 0) {
                throw new SandataRuntimeException(format("%s: deleteInvoices: sequenceKeys is NULL or EMPTY!", getClass().getName()));
            }

            StringBuilder keys = new StringBuilder();
            for (int i = 0; i < sequenceKeys.size() - 1; i++) {
                keys.append("?,");
            }

            keys.append("?");

            Object[] params = new Object[sequenceKeys.size()];
            for(int i = 0; i < sequenceKeys.size(); i++) {
                params[i] = sequenceKeys.get(i);
            }

            List<Billing> billingList = (List<Billing>)oracleDataService.getEntitiesForId(
                                            connection,
                                            String.format("SELECT * FROM BILLING WHERE BILLING_SK IN (%s)", keys.toString()),
                                            "com.sandata.lab.data.model.dl.model.Billing",
                                            params
            );

            List<BillingHistory> billingHistoryList = new ArrayList<>();
            for (Billing billing : billingList) {

                BillingHistory billingHistory = new BillingHistory();
                billingHistory.setRecordCreateTimestampHistory(new java.util.Date());
                billingHistory.setActionCode(ActionCode.D);
                DataMapper.Copy(billing, billingHistory);
                billingHistoryList.add(billingHistory);
            }

            for (BillingHistory billingHistory : billingHistoryList) {

                if (billingHistory.getBillingSK() != null) {
                    // If the record has already been deleted, than do not insert another one
                    if (!oracleDataService.recordExists(connection,
                            "SELECT BILLING_SK FROM BILLING_HIST WHERE ACTION_CODE = 'D' AND BILLING_SK = ?", billingHistory.getBillingSK().longValue())) {
                        long resultVal = oracleDataService.execute(connection, "PKG_BILLING", "insertBillingHist", new DataMapper().map(billingHistory));
                        logger.info(String.format("%s: insertInvHist: [BILLING_HIST_SK=%d]", getClass().getName(), resultVal));
                    }
                }
            }

            for (Billing billing : billingList) {
                List<BillingDetail> billingDetailList = (List<BillingDetail>) oracleDataService.getEntitiesForId(
                        connection,
                        "SELECT * FROM BILLING_DET WHERE BE_ID = ? AND PT_ID = ? AND CONTR_ID = ? AND PAYER_ID = ?",
                        "com.sandata.lab.data.model.dl.model.BillingDetail",
                        billing.getBusinessEntityID(),
                        billing.getPatientID(),
                        billing.getContractID(),
                        billing.getPayerID()
                );

                List<BillingDetailHistory> billingDetailHistoryList = new ArrayList<>();
                for (BillingDetail billingDetail : billingDetailList) {

                    BillingDetailHistory billingDetailHistory = new BillingDetailHistory();
                    billingDetailHistory.setRecordCreateTimestampHistory(new java.util.Date());
                    billingDetailHistory.setRateAmount(billingDetail.getBillingDetailRateAmount());
                    billingDetailHistory.setActionCode(ActionCode.D);
                    DataMapper.Copy(billingDetail, billingDetailHistory);
                    billingDetailHistoryList.add(billingDetailHistory);
                }

                for (BillingDetailHistory billingDetailHistory : billingDetailHistoryList) {

                    if (billingDetailHistory.getBillingDetailSK() != null) {
                        // If the record has already been deleted, than do not insert another one
                        if (!oracleDataService.recordExists(connection,
                                "SELECT BILLING_DET_SK FROM BILLING_DET_HIST WHERE ACTION_CODE = 'D' AND BILLING_DET_SK = ?",
                                    billingDetailHistory.getBillingDetailSK().longValue())) {
                            long resultVal = oracleDataService.execute(connection, "PKG_BILLING", "insertBillingDetHist", new DataMapper().map(billingDetailHistory));
                            logger.info(String.format("%s: insertInvDetHist: [BILLING_DET_HIST_SK=%d]", getClass().getName(), resultVal));
                        }
                    }
                }
            }

            exchange.getIn().setBody(sequenceKeys);

            connection.commit();

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Connection
            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    public void getBillingPreviewLock(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange, oracleDataService.getLoggerService());

        logger.start();

        try {

            logger.trace(exchange.getFromRouteId(), null, "getBillingPreviewLock: handler: START");

            List<Long> sequenceKeys = (List<Long>) exchange.getIn().getBody();

            BillingPreviewSummary billingPreviewSummary = getBillingPreviewSummary(sequenceKeys);

            logger.info(String.format("getBillingPreviewLock: *** LOCK *** : [TRANSACTION_ID=%s]", billingPreviewSummary.getTransactionId()));

            addLock(billingPreviewSummary.getTransactionId(), sequenceKeys);

            exchange.getIn().setBody(billingPreviewSummary);

        } catch (Exception e) {

            throw new SandataRuntimeException(format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            logger.trace(exchange.getFromRouteId(), null, "getBillingPreviewLock: handler: END");
            logger.stop();
        }
    }

    public void submitBillingPreviewLock(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange, oracleDataService.getLoggerService());

        logger.start();

        Connection connection = null;

        try {
            logger.trace(exchange.getFromRouteId(), null, "submitBillingPreviewLock: handler: START");

            connection = oracleDataService.getConnectionPoolDataService().getConnection();
            connection.setAutoCommit(false);

            String transactionId = (String) exchange.getIn().getHeader("transaction_id");
            if (StringUtil.IsNullOrEmpty(transactionId))  {
                throw new SandataRuntimeException("TransactionID (transaction_id) is required!");
            }

            logger.info(String.format("getBillingPreviewLock: *** SUBMIT *** : [TRANSACTION_ID=%s]", transactionId));

            List<Long> list = (List<Long>)getLock(transactionId);

            if (list != null) {

                for (Long billingSk : list) {

                    List<Billing> billingList = (List<Billing>)oracleDataService.getEntitiesForId(
                            connection,
                            "SELECT * FROM BILLING WHERE BILLING_SK = ?",
                            "com.sandata.lab.data.model.dl.model.Billing",
                            billingSk
                    );

                    if (billingList != null && billingList.size() > 0) {

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        Billing billing = billingList.get(0);

                        String invNumber = String.valueOf(SandataRandUtil.RandomLong());

                        billing.setRecordUpdateTimestamp(new java.util.Date());
                        billing.setRecordUpdatedBy("MW: Billing REST Service");
                        billing.setBillingStatusName("SUBMITTED");

                        logger.trace("updateBilling: START");

                        long billUpdateResult = oracleDataService.execute(connection, "PKG_BILLING", "updateBilling", new DataMapper().map(billing));

                        logger.trace(String.format("updateBilling: END: [UPDATE_RESULT=%d]", billUpdateResult));

                        BillingInvoiceCrosswalk billingInvXwalk = new BillingInvoiceCrosswalk();
                        billingInvXwalk.setRecordCreateTimestamp(new java.util.Date());
                        billingInvXwalk.setRecordUpdateTimestamp(new java.util.Date());
                        billingInvXwalk.setRecordCreatedBy("MW: Billing REST Service");
                        billingInvXwalk.setRecordUpdatedBy("N/A");
                        billingInvXwalk.setRecordEffectiveTimestamp(new java.util.Date());
                        billingInvXwalk.setRecordTerminationTimestamp(simpleDateFormat.parse("9999-12-31 00:00:00"));
                        billingInvXwalk.setCurrentRecordIndicator(true);
                        billingInvXwalk.setBusinessEntityID(billing.getBusinessEntityID());
                        billingInvXwalk.setInvoiceNumber(invNumber);
                        billingInvXwalk.setPayerID(billing.getPayerID());
                        billingInvXwalk.setContractID(billing.getContractID());
                        billingInvXwalk.setPatientID(billing.getPatientID());

                        logger.trace("insertBillingInvXwalk: START");

                        long billInvXwalkSK = oracleDataService.execute(connection, "PKG_BILLING", "insertBillingInvXwalk", new DataMapper().map(billingInvXwalk));

                        logger.trace(String.format("insertBillingInvXwalk: END: [BILLING_INV_XWALK_SK=%d]", billInvXwalkSK));

                        logger.trace(String.format("Copy: [BILLING_SK=%d]: START", billing.getBillingSK().longValue()));

                        Invoice invoice = new Invoice();
                        DataMapper.Copy(billing, invoice);

                        invoice.setRecordCreateTimestamp(new java.util.Date());
                        invoice.setRecordUpdateTimestamp(new java.util.Date());
                        invoice.setRecordEffectiveTimestamp(new java.util.Date());
                        invoice.setRecordTerminationTimestamp(simpleDateFormat.parse("9999-12-31 00:00:00"));
                        invoice.setCurrentRecordIndicator(true);
                        invoice.setRecordCreatedBy("MW: Billing REST Service");
                        invoice.setRecordUpdatedBy("N/A");
                        //dmr--Use the CODE value from INV_STATUS_LKUP
                        //dmr--invoice.setInvoiceStatusCode("PENDING");
                        invoice.setInvoiceStatusCode("1");
                        //dmr--Use the CODE value from INV_SUBM_TYP_LKUP
                        //dmr--invoice.setInvoiceSubmissionTypeCode("FIRST_TIME_SUBMISSION");
                        invoice.setInvoiceSubmissionTypeCode("1");
                        invoice.setInvoiceDate(billing.getBillingDate());
                        invoice.setInvoiceNumber(invNumber);
                        invoice.setInvoiceStartDate(billing.getBillingStartDate());
                        invoice.setInvoiceEndDate(billing.getBillingEndDate());
                        invoice.setInvoiceTotalAmount(billing.getBillingTotalAmount());
                        invoice.setInvoiceBillTypeCode(billing.getBillingBillTypeCode());
                        invoice.setInvoiceManuallyOverrideIndicator(billing.isBillingManuallyOverrideIndicator());
                        invoice.setRecordEffectiveTimestamp(new java.util.Date());
                        invoice.setRecordTerminationTimestamp(simpleDateFormat.parse("9999-12-31 00:00:00"));

                        logger.trace(String.format("Copy: [BILLING_SK=%d]: END", billing.getBillingSK().longValue()));

                        logger.trace("insertInvoice: START");

                        long invSk = oracleDataService.execute(connection, "PKG_INVOICE", "insertInv", new DataMapper().map(invoice));

                        logger.trace(String.format("insertInvoice: END: [INV_SK=%d]", invSk));

                        logger.trace("getBillingDetails: START");

                        List<BillingDetail> billingDetailList = (List<BillingDetail>)oracleDataService.getEntitiesForId(
                                connection,
                                "SELECT * FROM BILLING_DET " +
                                        "  WHERE BE_ID = ? AND PT_ID = ?" +
                                        "      AND PAYER_ID = ? AND CONTR_ID = ?",
                                "com.sandata.lab.data.model.dl.model.BillingDetail",
                                billing.getBusinessEntityID(),
                                billing.getPatientID(),
                                billing.getPayerID(),
                                billing.getContractID()
                        );

                        logger.trace(String.format("getBillingDetails: END: [billingDetailList.size()=%d]", billingDetailList.size()));

                        for (BillingDetail billingDetail : billingDetailList) {

                            logger.trace("insertInvDet: START");

                            InvoiceDetail invoiceDetail = new InvoiceDetail();
                            DataMapper.Copy(billingDetail, invoiceDetail);
                            invoiceDetail.setRecordCreateTimestamp(new java.util.Date());
                            invoiceDetail.setRecordUpdateTimestamp(new java.util.Date());
                            invoiceDetail.setRecordCreatedBy("MW: Billing REST Service");
                            invoiceDetail.setRecordUpdatedBy("N/A");
                            invoiceDetail.setInvoiceDetailStatusCode("PENDING");
                            invoiceDetail.setInvoiceDetailSubmissionStatusName("FIRST_TIME_SUBMISSION");
                            invoiceDetail.setInvoiceNumber(invNumber);
                            invoiceDetail.setInvoiceDetailServiceDate(billingDetail.getBillingDetailDate());
                            invoiceDetail.setInvoiceDetailTotalAmount(billingDetail.getBillingDetailTotalAmount());
                            invoiceDetail.setInvoiceDetailTotalUnit(billingDetail.getBillingDetailTotalUnit());
                            invoiceDetail.setRecordEffectiveTimestamp(new java.util.Date());
                            invoiceDetail.setRecordTerminationTimestamp(simpleDateFormat.parse("9999-12-31 00:00:00"));
                            invoiceDetail.setCurrentRecordIndicator(true);
                            invoiceDetail.setInvoiceDetailRateAmount(billingDetail.getBillingDetailRateAmount());

                            long invDetlSk = oracleDataService.execute(connection, "PKG_INVOICE", "insertInvDet", new DataMapper().map(invoiceDetail));

                            logger.trace(String.format("insertInvDet: END: [INV_DET_SK=%d]", invDetlSk));
                        }
                    }
                }

            } else {
                logger.warn(String.format("getBillingPreviewLock: *** SUBMIT *** : UNKNOWN: [TRANSACTION_ID=%s]", transactionId));
            }

            removeLock(transactionId);

            exchange.getIn().setBody(true);

            connection.commit();

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", e.getClass().getName(), e.getMessage());
            logger.error(errMsg);
            throw new SandataRuntimeException(errMsg, e);

        } finally {

            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            logger.trace(exchange.getFromRouteId(), null, "submitBillingPreviewLock: handler: END");
            logger.stop();
        }
    }

    public void cancelBillingPreviewLock(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange, oracleDataService.getLoggerService());

        logger.start();

        try {

            String transactionId = (String) exchange.getIn().getHeader("transaction_id");
            if (StringUtil.IsNullOrEmpty(transactionId))  {
                throw new SandataRuntimeException("TransactionID (transaction_id) is required!");
            }

            logger.info(String.format("getBillingPreviewLock: *** CANCEL *** : [TRANSACTION_ID=%s]", transactionId));

            removeLock(transactionId);

            exchange.getIn().setBody(true);

        } catch (Exception e) {

            throw new SandataRuntimeException(format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            logger.stop();
        }
    }

    private BillingPreviewSummary getBillingPreviewSummary(List<Long> invoiceSkList) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        BillingPreviewSummary billingPreviewSummary = new BillingPreviewSummary();
        billingPreviewSummary.setTransactionId(UUID.randomUUID().toString());

        try {

            Object[] params = new Object[invoiceSkList.size()];
            int index = 0;
            for (Long invoiceSk : invoiceSkList) {
                params[index++] = invoiceSk;
            }

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(true);

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.NUMBER_ARRAY", connection);
            ARRAY primaryKeysArray = new ARRAY(des, connection, params);

            String callMethod = String.format("{?=call %s.%s.%s(?)}",
                    ConnectionType.COREDATA, "PKG_BILLING_UTIL", "BILLABLE_REVIEW_SUMMARY");
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setArray(2, primaryKeysArray);
            callableStatement.execute();

            resultSet = (ResultSet)callableStatement.getObject(1);

            while(resultSet.next()) {

                billingPreviewSummary.setSubmissionDate(new Date(resultSet.getTimestamp("SUBMISSION_DATE").getTime()));
                billingPreviewSummary.setTotalInvoicesBilled(resultSet.getBigDecimal("TOTAL_INVOICES_BILLED").toBigInteger());

                if (billingPreviewSummary.getTotalInvoicesBilled().intValue() == 0) {
                    throw new SandataRuntimeException(String.format("%s: getBillingPreviewSummary: " +
                            "billingPreviewSummary.getTotalInvoicesBilled() == 0", getClass().getName()));
                }

                billingPreviewSummary.setTotalAmountBilled(resultSet.getBigDecimal("TOTAL_AMOUNT_BILLED"));
                billingPreviewSummary.setBilledHours(resultSet.getBigDecimal("BILLED_HOURS").toBigInteger());

                List<LineOfBusinessDetail> lineOfBusinessDetailList = new ArrayList<>();
                ResultSet lobCursorResultSet = (ResultSet) resultSet.getObject("LOB_CURSOR_RESULT");
                if (lobCursorResultSet != null) {
                    while (lobCursorResultSet.next()) {

                        LineOfBusinessDetail lineOfBusinessDetail = new LineOfBusinessDetail();
                        lineOfBusinessDetail.setLineOfBusiness(lobCursorResultSet.getString("LINE_OF_BUSINESS"));

                        BigDecimal totalInvBilled = lobCursorResultSet.getBigDecimal("TOTAL_INVOICES_BILLED");
                        if (totalInvBilled != null) {
                            lineOfBusinessDetail.setTotalInvoicesBilled(totalInvBilled.toBigInteger());
                        }

                        lineOfBusinessDetail.setTotalAmountBilled(lobCursorResultSet.getBigDecimal("TOTAL_AMOUNT_BILLED"));

                        BigDecimal billedHours = lobCursorResultSet.getBigDecimal("BILLED_HOURS");
                        if (billedHours != null) {
                            lineOfBusinessDetail.setBilledHours(billedHours.toBigInteger());
                        }

                        lineOfBusinessDetailList.add(lineOfBusinessDetail);
                    }

                    lobCursorResultSet.close();

                    billingPreviewSummary.setLineOfBusinessDetail(lineOfBusinessDetailList);
                }

                List<PayerDetail> payerDetailList = new ArrayList<>();
                ResultSet payerCursorResultSet = (ResultSet) resultSet.getObject("PAYER_CURSOR_RESULT");
                if (payerCursorResultSet != null) {
                    while (payerCursorResultSet.next()) {

                        PayerDetail payerDetail = new PayerDetail();

                        payerDetail.setPayerName(payerCursorResultSet.getString("PAYER"));

                        BigDecimal totalInvBilled = payerCursorResultSet.getBigDecimal("TOTAL_INVOICES_BILLED");
                        if (totalInvBilled != null) {
                            payerDetail.setTotalInvoicesBilled(totalInvBilled.toBigInteger());
                        }

                        payerDetail.setTotalAmountBilled(payerCursorResultSet.getBigDecimal("TOTAL_AMOUNT_BILLED"));

                        BigDecimal billedHours = payerCursorResultSet.getBigDecimal("BILLED_HOURS");
                        if (billedHours != null) {
                            payerDetail.setBilledHours(billedHours.toBigInteger());
                        }

                        payerDetailList.add(payerDetail);
                    }

                    payerCursorResultSet.close();

                    billingPreviewSummary.setPayerDetail(payerDetailList);
                }

                List<SubmissionTypeDetail> submissionTypeDetailList = new ArrayList<>();
                ResultSet submTypeCursorResultSet = (ResultSet) resultSet.getObject("SUBM_TYP_CURSOR_RESULT");
                if (submTypeCursorResultSet != null) {
                    while (submTypeCursorResultSet.next()) {

                        SubmissionTypeDetail submissionTypeDetail = new SubmissionTypeDetail();

                        String submTypeName = submTypeCursorResultSet.getString("SUBMISSION_TYPE");
                        submissionTypeDetail.setInvoiceSubmissionTypeName(InvoiceSubmissionTypeName.fromValue(submTypeName));

                        submissionTypeDetail.setPayerDetail(getBPSPayerDetail(invoiceSkList, submTypeName));

                        //System.out.println("\tTOTAL_INVOICES_BILLED : " + submTypeCursorResultSet.getBigDecimal("TOTAL_INVOICES_BILLED"));
                        //System.out.println("\tTOTAL_AMOUNT_BILLED : " + submTypeCursorResultSet.getBigDecimal("TOTAL_AMOUNT_BILLED"));
                        //System.out.println("\tBILLED_HOURS : " + submTypeCursorResultSet.getBigDecimal("BILLED_HOURS"));

                        submissionTypeDetailList.add(submissionTypeDetail);
                    }

                    submTypeCursorResultSet.close();

                    billingPreviewSummary.setSubmissionTypeDetail(submissionTypeDetailList);
                }

                List<InvoiceFindResult> invoiceFindResultList = new ArrayList<>();
                ResultSet invDetCursorResultSet = (ResultSet) resultSet.getObject("INV_DET_CURSOR_RESULT");
                if (invDetCursorResultSet != null) {
                    while (invDetCursorResultSet.next()) {

                        InvoiceFindResult invoiceFindResult = new InvoiceFindResult();

                        //TODO:invoiceFindResult.setLocation(invDetCursorResultSet.getString("BE_LOC_ID"));
                        invoiceFindResult.setBillingSk(invDetCursorResultSet.getBigDecimal("BILLING_SK").toBigInteger());
                        invoiceFindResult.setLineOfBusiness(invDetCursorResultSet.getString("BE_LOB_ID"));
                        invoiceFindResult.setPayerName(invDetCursorResultSet.getString("PAYER_NAME"));
                        invoiceFindResult.setContractDescription(invDetCursorResultSet.getString("CONTR_DESC"));

                        Timestamp invStartDateTmsp = invDetCursorResultSet.getTimestamp("BILLING_START_DATE");
                        if (invStartDateTmsp != null) {
                            invoiceFindResult.setInvoiceFromDate(new java.util.Date(invStartDateTmsp.getTime()));
                        }

                        Timestamp invEndDateTmsp = invDetCursorResultSet.getTimestamp("BILLING_END_DATE");
                        if (invEndDateTmsp != null) {
                            invoiceFindResult.setInvoiceToDate(new java.util.Date(invEndDateTmsp.getTime()));
                        }

                        invoiceFindResult.setInvoiceNumber(invDetCursorResultSet.getString("INV_NUM"));

                        Timestamp invDateTmsp = invDetCursorResultSet.getTimestamp("BILLING_DATE");
                        if (invDateTmsp != null) {
                            invoiceFindResult.setInvoiceDate(new java.util.Date(invDateTmsp.getTime()));
                        }

                        invoiceFindResult.setPatientFirstName(invDetCursorResultSet.getString("PT_FIRST_NAME"));
                        invoiceFindResult.setPatientLastName(invDetCursorResultSet.getString("PT_LAST_NAME"));
                        invoiceFindResult.setPatientInsuranceIdNumber(invDetCursorResultSet.getString("PT_INS_ID_NUM"));
                        invoiceFindResult.setLocation(invDetCursorResultSet.getString("BE_NAME"));

                        BigDecimal invTotalAmount = invDetCursorResultSet.getBigDecimal("BILLING_TOTAL_AMT");
                        if (invTotalAmount != null) {
                            invoiceFindResult.setBilledAmount(invTotalAmount);
                        }

                        invoiceFindResult.setSubmissionStatus(invDetCursorResultSet.getString("BILLING_SUBM_TYP_NAME"));

                        invoiceFindResultList.add(invoiceFindResult);
                    }

                    invDetCursorResultSet.close();

                    billingPreviewSummary.setInvoices(invoiceFindResultList);
                }
            }

            return billingPreviewSummary;

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Connection
            this.oracleDataService.closeOracleConnection(connection);
        }
    }

    private List<PayerDetail> getBPSPayerDetail(List<Long> invoiceSkList, String invoiceSubmissionTypeName) {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            Object[] params = new Object[invoiceSkList.size()];
            int index = 0;
            for (Long invoiceSk : invoiceSkList) {
                params[index++] = invoiceSk;
            }

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(true);

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.NUMBER_ARRAY", connection);
            ARRAY primaryKeysArray = new ARRAY(des, connection, params);

            String callMethod = String.format("{?=call %s.%s.%s(?,?)}",
                    ConnectionType.COREDATA, "PKG_BILLING_UTIL", "GET_BRS_PAYER_FOR_SUBM_TYP");
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setArray(2, primaryKeysArray);
            callableStatement.setObject(3, invoiceSubmissionTypeName);
            callableStatement.execute();

            resultSet = (ResultSet)callableStatement.getObject(1);

            List<PayerDetail> payerDetailList = new ArrayList<>();

            while(resultSet.next()) {

                PayerDetail payerDetail = new PayerDetail();
                payerDetail.setPayerName(resultSet.getString("PAYER_NAME"));
                payerDetail.setPayerId(resultSet.getString("PAYER_ID"));

                BigDecimal totalInvoicesBilled = resultSet.getBigDecimal("TOTAL_INVOICES_BILLED");
                if (totalInvoicesBilled != null) {
                    payerDetail.setTotalInvoicesBilled(totalInvoicesBilled.toBigInteger());
                }

                payerDetail.setTotalAmountBilled(resultSet.getBigDecimal("TOTAL_AMOUNT_BILLED"));

                BigDecimal billedHours = resultSet.getBigDecimal("BILLED_HOURS");
                if (billedHours != null) {
                    payerDetail.setBilledHours(billedHours.toBigInteger());
                }

                payerDetailList.add(payerDetail);
            }

            return payerDetailList;

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Connection
            this.oracleDataService.closeOracleConnection(connection);
        }
    }

    private synchronized void addLock(String key, List invoiceSkList) {

        lockedPreviewRequests.put(key, invoiceSkList);
    }

    private synchronized Object getLock(String key) {

        return lockedPreviewRequests.get(key);
    }

    private synchronized void removeLock(String key) {

        lockedPreviewRequests.remove(key);
    }
}
