package com.sandata.lab.payroll.impl;

import com.sandata.lab.common.utils.date.DateUtil;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.model.BusinessEntity;
import com.sandata.lab.data.model.dl.model.PayrollCodeQualifier;
import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.data.model.payroll.TimeSheetDetailRequest;
import com.sandata.lab.data.model.payroll.TimeSheetSummaryRequest;
import com.sandata.lab.payroll.model.StaffPayrollRates;
import com.sandata.lab.payroll.utils.PayrollProcessUtil;
import com.sandata.lab.payroll.utils.processor.StaffRateProcessor;
import org.apache.camel.Exchange;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.sandata.lab.payroll.utils.log.OracleDataLogger.CreateLogger;
import static java.lang.String.format;

/**
 * Process visits and determine if they can be moved to payroll.
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

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(true);

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
            String businessEntId = visit.getBusinessEntityID();

            Object agency = oracleDataService.getEntitiesForId(
                    "SELECT * FROM BE WHERE BE_ID = ?",
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

            BusinessEntity businessEntity = businessEntityList.get(0);

            if (businessEntity.getBusinessEntityPayrollWeekEndDay() == null) {
                String errMsg = String.format("%s: handler: ERROR: [BE_ID=%s] businessEntity.getBusinessEntityPayrollWeekEndDay() == null: Agency is not configured correctly!",
                        getClass().getName(), businessEntId);
                logger.error(exchange.getFromRouteId(), (long)visitSk, errMsg);
                throw new SandataRuntimeException(errMsg);
            }

            Date payrollWeekEndDate = PayrollProcessUtil.PayrollWeekEndDate(businessEntity.getBusinessEntityPayrollWeekEndDay().toString());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            logger.trace(String.format("%s: handler: payrollWeekEndDate: %s", getClass().getName(), dateFormat.format(payrollWeekEndDate)));

            // Validate StaffID is not null/empty
            if (StringUtil.IsNullOrEmpty(visit.getStaffID())) {

                logger.warn(exchange.getFromRouteId(), (long)visitSk, String.format("%s: handler: [VISIT_SK=%d]: Visit Does Not Have StaffID!", getClass().getName(), visitSk));

                // If the staff was removed from the Visit, then we must remove the timesheet detail record for that Visit if it exists
                deleteTimesheetDetail(businessEntId, visit, payrollWeekEndDate, logger);

            } else if (visit.isVisitCancelledIndicator() != null && visit.isVisitCancelledIndicator()) {

                logger.warn(exchange.getFromRouteId(), (long)visitSk, String.format("%s: handler: [VISIT_SK=%d]: Cancelled!", getClass().getName(), visitSk));

                // If Timesheet exists, remove Visit
                deleteTimesheetDetail(businessEntId, visit, payrollWeekEndDate, logger);

            } else if (visit.isVisitDoNotPayIndicator() != null && visit.isVisitDoNotPayIndicator()) {

                logger.warn(exchange.getFromRouteId(), (long)visitSk, String.format("%s: handler: [VISIT_SK=%d]: Do NOT Pay!", getClass().getName(), visitSk));

                // If Timesheet exists, remove Visit
                deleteTimesheetDetail(businessEntId, visit, payrollWeekEndDate, logger);

            } else if (visit.isVisitApprovedIndicator() != null && visit.isVisitApprovedIndicator()) {

                logger.info(exchange.getFromRouteId(), (long)visitSk, String.format("%s: handler: [VISIT_SK=%d]: Approved!", getClass().getName(), visitSk));

                TimeSheetSummaryRequest timeSheetSummaryRequest = new TimeSheetSummaryRequest();
                timeSheetSummaryRequest.setBusinessEntityId(businessEntId);
                timeSheetSummaryRequest.setStaffId(visit.getStaffID());
                timeSheetSummaryRequest.setPayrollWeekEndDate(payrollWeekEndDate);

                TimeSheetDetailRequest timeSheetDetailRequest = new TimeSheetDetailRequest();
                timeSheetDetailRequest.setTimeSheetSummaryRequest(timeSheetSummaryRequest);

                timeSheetDetailRequest.setCalendarLkupSk(BigInteger.ONE); // TODO: This needs to be determined based on the Visit Date
                timeSheetDetailRequest.setVisitSk(BigInteger.valueOf(visitSk));
                timeSheetDetailRequest.setPatientId(visit.getPatientID());
                timeSheetDetailRequest.setPayrollCode(PayrollCodeQualifier.EARNING.toString());
                timeSheetDetailRequest.setTimesheetDate(getTimesheetDate(visit, logger));

                // Get the staff payroll rates for the given context
                StaffPayrollRates staffPayrollRates = oracleDataService.getStaffPayrollRates(
                        connection,
                        businessEntId,
                        businessEntId,        // TODO: Need Location
                        businessEntId,        // TODO: Need LOB
                        visit.getPatientID(),
                        visit.getStaffID(),
                        visit.getVisitSK().longValue(),
                        timeSheetDetailRequest.getTimesheetDate(),
                        logger
                );

                BigDecimal rate = new StaffRateProcessor().getStaffRateAmount(staffPayrollRates, logger);
                BigDecimal hours = visitTotalHours(visit, exchange, logger);

                timeSheetDetailRequest.setPayrollRateAmount(rate);

                //dmr-7/20/2016: Note that the "Hours" domain in the model is always stored as Seconds!
                //https://mnt-ers-ts01.sandata.com/object/view.spg?key=149710
                timeSheetDetailRequest.setPayrollHours(BigInteger.valueOf(visitTotalSeconds(visit, exchange, logger)));

                timeSheetDetailRequest.setPayrollAmount(rate.multiply(hours));

                // If TimeSheetSummary doesn't exist, create it, add TimeSheetDetail if not already there
                exchange.getIn().setBody(timeSheetDetailRequest);

                logger.info(String.format("%s: setBody(timeSheetDetailRequest): Complete!", getClass().getName()));

                logger.trace(String.format("%s: setBody(timeSheetDetailRequest): %s", getClass().getName(), timeSheetDetailRequest.toString()));

            } else {

                logger.warn(exchange.getFromRouteId(), (long)visitSk, String.format("%s: handler: [VISIT_SK=%d]: Not Approved!", getClass().getName(), visitSk));

                // Visit was "Unapproved", check Timesheet, if it exists, remove it
                deleteTimesheetDetail(businessEntId, visit, payrollWeekEndDate, logger);
            }

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

            String errMsg = format("%s: EXCEPTION: %s", getClass().getName(), e.getMessage());
            logger.error(exchange.getFromRouteId(), null, errMsg);
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {

            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            logger.trace(exchange.getFromRouteId(), null, "ProcessVisit: handler: END");
            logger.stop();
        }
    }

    private void deleteTimesheetDetail(String businessEntId, Visit visit, Date payrollWeekEndDate, SandataLogger logger) {

        logger.debug(String.format("%s: deleteTimesheetDetail: >>> IN", getClass().getName()));

        int rowsDeleted = oracleDataService.executeFunction("{?=call PKG_PR_UTIL.DELETE_TIMESHEET_DET(?,?)}",
                businessEntId, visit.getVisitSK().longValue());

        logger.trace(String.format("%s: [SQL: DELETE TIMESHEET_DET WHERE BE_ID = %s AND VISIT_SK = %d][ROWS_DELETED=%d]",
                            getClass().getName(), visit.getBusinessEntityID(), visit.getVisitSK().longValue(), rowsDeleted));

        logger.trace(null, visit.getVisitSK().longValue(), String.format("%s: deleteTimesheetDetail: [ROWS_DELETED=%d]", getClass().getName(), rowsDeleted));

        // Can't delete the Timesheet Summary if the StaffID was nulled out because the StaffID is a critical key
        if (! StringUtil.IsNullOrEmpty(visit.getStaffID())) {

            logger.debug(String.format("deleteTimesheetDetail: [VISIT_SK=%d][STAFF_ID=%s]", visit.getVisitSK().longValue(), visit.getStaffID()));

            BigInteger timesheetSummarySk = oracleDataService.getTimeSheetSummarySK(visit.getStaffID(), visit.getBusinessEntityID(), payrollWeekEndDate);

            if (timesheetSummarySk != null) {

                logger.debug(String.format("deleteTimesheetDetail: [TIMESHEET_SUMMARY_SK=%d]", timesheetSummarySk.longValue()));

                int timesheetSummaryDetailsCount = oracleDataService.executeCount("SELECT COUNT(*) AS TIMESHEET_DETL_COUNT " +
                                "FROM TIMESHEET_DET WHERE TIMESHEET_SUMMARY_SK = ?",
                        "TIMESHEET_DETL_COUNT", timesheetSummarySk.longValue());

                logger.trace(String.format("SQL: SELECT COUNT(*) AS TIMESHEET_DETL_COUNT FROM TIMESHEET_DET WHERE TIMESHEET_SUMMARY_SK = %d",
                        timesheetSummarySk.longValue()));

                // If there are no TimesheetDetail records, then delete the TimesheetSummary record
                if (timesheetSummaryDetailsCount == 0) {

                    logger.debug("deleteTimesheetDetail: timesheetSummaryDetailsCount == 0");

                    deleteTimesheetSummary(timesheetSummarySk, logger);
                }

                logger.trace(null, visit.getVisitSK().longValue(), String.format("%s: timesheetSummaryDetailsCount: %d",
                        getClass().getName(), timesheetSummaryDetailsCount));

            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                logger.warn(null, visit.getVisitSK().longValue(), String.format("deleteTimesheetDetail: WARN: Could not check TimesheetSummary because " +
                        "timesheetSummarySk == null: [STAFF_ID=%s]: [BE_ID=%s]: [TIMESHEET_WEEK_END_DATE=%s]",
                        visit.getStaffID(), visit.getBusinessEntityID(), dateFormat.format(payrollWeekEndDate)));
            }

        } else {

            logger.warn(null, visit.getVisitSK().longValue(), "deleteTimesheetDetail: WARN: Could not check TimesheetSummary because StaffID was NULL or Empty!");
        }

        logger.debug(String.format("%s: deleteTimesheetDetail: <<< OUT", getClass().getName()));
    }

    private void deleteTimesheetSummary(BigInteger timesheetSummarySK, SandataLogger logger) {

        logger.debug(String.format("%s: deleteTimesheetSummary: >>> IN", getClass().getName()));

        int rowsDeleted = oracleDataService.executeFunction("{?=call PKG_PR_UTIL.DELETE_TIMESHEET_SUMMARY(?)}",
                timesheetSummarySK.longValue());

        logger.trace(String.format("%s: deleteTimesheetSummary: [ROWS_DELETED=%d]", getClass().getName(), rowsDeleted));

        logger.debug(String.format("%s: deleteTimesheetSummary: <<< OUT", getClass().getName()));
    }

    private Date getTimesheetDate(Visit visit, SandataLogger logger) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (visit.getVisitAdjustedStartTimestamp() != null) {
            Date adjustedDate = visit.getVisitAdjustedStartTimestamp();
            logger.trace(String.format("%s: getTimesheetDate: [VISIT_ADJ_START_DATE=%s]",
                    getClass().getName(), dateFormat.format(adjustedDate)));
            return adjustedDate;
        }

        Date actualDate = visit.getVisitActualStartTimestamp();
        if (actualDate == null) {
            logger.error(String.format("%s: getTimesheetDate: adjusted/actual start dates == null", getClass().getName()));

        } else {
            logger.trace(String.format("%s: getTimesheetDate: [VISIT_ACT_START_DATE=%s]",
                    getClass().getName(), dateFormat.format(actualDate)));
        }

        return actualDate;
    }

    private BigDecimal visitTotalHours(Visit visit, Exchange exchange, SandataLogger logger) throws SandataRuntimeException {

        try {
            // If the user has set the adjusted time, then use that to calculate the hours
            if (useVisitAdjustedTime(visit, exchange, logger)) {

                float hours = DateUtil.Hours(visit.getVisitAdjustedStartTimestamp(), visit.getVisitAdjustedEndTimestamp());
                if (hours == 0f) {
                    throw new SandataRuntimeException(String.format("%s: visitTotalHours: EXCEPTION: [VISIT_SK=%d]: Adjusted Time Hours == 0",
                                    getClass().getName(), visit.getVisitSK().longValue()));
                }

                return BigDecimal.valueOf(hours);
            }
            else {
                validateVisitActualTime(visit, exchange, logger);

                float hours = DateUtil.Hours(visit.getVisitActualStartTimestamp(), visit.getVisitActualEndTimestamp());
                if (hours == 0f) {
                    throw new SandataRuntimeException(String.format("%s: visitTotalHours: EXCEPTION: [VISIT_SK=%d]: Actual Time Hours == 0",
                                    getClass().getName(), visit.getVisitSK().longValue()));
                }

                return BigDecimal.valueOf(hours);
            }
        }
        catch (Exception e) {
            String errMsg = format("%s: visitTotalHours: EXCEPTION: %s", getClass().getName(), e.getMessage());
            logger.error(exchange.getFromRouteId(), null, errMsg);
            throw new SandataRuntimeException(exchange, errMsg, e);
        }
    }

    private long visitTotalSeconds(Visit visit, Exchange exchange, SandataLogger logger) throws SandataRuntimeException {

        try {
            // If the user has set the adjusted time, then use that to calculate the hours
            if (useVisitAdjustedTime(visit, exchange, logger)) {

                long seconds = DateUtil.Seconds(visit.getVisitAdjustedStartTimestamp(), visit.getVisitAdjustedEndTimestamp());
                if (seconds == 0) {
                    throw new SandataRuntimeException(String.format("%s: visitTotalSeconds: EXCEPTION: [VISIT_SK=%d]: Adjusted Time Hours == 0 (Seconds)",
                            getClass().getName(), visit.getVisitSK().longValue()));
                }

                return seconds;
            }
            else {
                validateVisitActualTime(visit, exchange, logger);

                long seconds = DateUtil.Seconds(visit.getVisitActualStartTimestamp(), visit.getVisitActualEndTimestamp());
                if (seconds == 0) {
                    throw new SandataRuntimeException(String.format("%s: visitTotalSeconds: EXCEPTION: [VISIT_SK=%d]: Actual Time Hours == 0 (Seconds)",
                            getClass().getName(), visit.getVisitSK().longValue()));
                }

                return seconds;
            }
        }
        catch (Exception e) {
            String errMsg = format("%s: visitTotalSeconds: EXCEPTION: %s", getClass().getName(), e.getMessage());
            logger.error(exchange.getFromRouteId(), null, errMsg);
            throw new SandataRuntimeException(exchange, errMsg, e);
        }
    }

    // If the Visit has adjusted time then use them to calculate the hours
    private boolean useVisitAdjustedTime(Visit visit, Exchange exchange, SandataLogger logger) throws SandataRuntimeException {

        if (DateUtil.IsNull(visit.getVisitAdjustedStartTimestamp())) {
            logger.trace(exchange.getFromRouteId(), visit.getVisitSK().longValue(),
                    String.format("%s: useVisitAdjustedTime: [VISIT_SK=%d]: visit.getVisitAdjustedStartTimestamp() == null",
                            getClass().getName(), visit.getVisitSK().longValue()));

            return false;
        }

        if (DateUtil.IsNull(visit.getVisitAdjustedEndTimestamp())) {
            logger.trace(exchange.getFromRouteId(), visit.getVisitSK().longValue(),
                    String.format("%s: useVisitAdjustedTime: [VISIT_SK=%d]: visit.getVisitAdjustedEndTimestamp() == null",
                            getClass().getName(), visit.getVisitSK().longValue()));

            return false;
        }

        // should not happen!
        if (visit.getVisitAdjustedEndTimestamp().before(visit.getVisitAdjustedStartTimestamp())) {
            throw new SandataRuntimeException(String.format("%s: useVisitAdjustedTime: EXCEPTION: [VISIT_SK=%d]" +
                    "visit.getVisitAdjustedEndTimestamp().before(visit.getVisitAdjustedStartTimestamp()", getClass().getName(), visit.getVisitSK().longValue()));
        }

        return true;
    }

    // If the Visit adjusted time is not provided then validate that the actual time is provided, otherwise we can not calculate the hours!
    private void validateVisitActualTime(Visit visit, Exchange exchange, SandataLogger logger) throws SandataRuntimeException {

        if (DateUtil.IsNull(visit.getVisitActualStartTimestamp())) {
            throw new SandataRuntimeException(
                    String.format("%s: validateVisitActualTime: [VISIT_SK=%d]: visit.getVisitActualStartTimestamp() == null",
                            getClass().getName(), visit.getVisitSK().longValue()));
        }

        if (DateUtil.IsNull(visit.getVisitActualEndTimestamp())) {
            throw new SandataRuntimeException(
                    String.format("%s: validateVisitActualTime: [VISIT_SK=%d]: visit.getVisitActualEndTimestamp() == null",
                            getClass().getName(), visit.getVisitSK().longValue()));
        }

        // should not happen!
        if (visit.getVisitActualEndTimestamp().before(visit.getVisitActualStartTimestamp())) {
            throw new SandataRuntimeException(String.format("%s: validateVisitActualTime: EXCEPTION: [VISIT_SK=%d]" +
                    "visit.getVisitActualEndTimestamp().before(visit.getVisitActualStartTimestamp()", getClass().getName(), visit.getVisitSK().longValue()));
        }
    }

    public OracleDataService getOracleDataService() {
        return oracleDataService;
    }

    public void setOracleDataService(OracleDataService oracleDataService) {
        this.oracleDataService = oracleDataService;
    }
}
