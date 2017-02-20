package com.sandata.lab.rest.timesheet.impl;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.TimesheetDetail;
import com.sandata.lab.data.model.dl.model.TimesheetSummary;
import com.sandata.lab.data.model.payroll.TimeSheetDetailRequest;
import com.sandata.lab.data.model.payroll.TimeSheetSummaryRequest;
import com.sandata.lab.rest.timesheet.api.DataService;
import com.sandata.lab.rest.timesheet.utils.data.DataMapper;
import com.sandata.lab.rest.timesheet.utils.log.OracleDataLogger;
import org.apache.camel.Exchange;
import org.apache.cxf.message.MessageContentsList;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */
@SuppressWarnings("unchecked")
public class RestDataService implements DataService {

    private OracleDataService oracleDataService;

    @Override
    public void get(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

        logger.start();

        try {

            String operationName = (String)exchange.getIn().getHeader("operationName");

            String[] methodParts = operationName.split("_");

            String packageName = methodParts[0] + "_" + methodParts[1];
            String methodName = methodParts[2];
            String className = "com.sandata.lab.data.model.dl.model." + methodParts[3];

            Object result;
            Object body = exchange.getIn().getBody();

            if (exchange.getIn().getHeader("sequence_key") != null)
            {
                long sequenceKey = (long) exchange.getIn().getHeader("sequence_key");

                result = oracleDataService.executeGet(
                        packageName,
                        methodName,
                        className,
                        sequenceKey
                );

                ArrayList result2 = (ArrayList<Object>)result;

                if (result2 != null && result2.size() > 0) {
                    result = result2.get(0);

                    if (methodParts[3].equals("TimesheetDetail")) {

                        exchange.getIn().setBody(getRelatedTimesheetDetailData((TimesheetDetail) result));
                    }
                    else if (methodParts[3].equals("TimesheetSummary")) {

                        exchange.getIn().setBody(getRelatedTimesheetSummaryData((TimesheetSummary) result));
                    }
                    else {
                        exchange.getIn().setBody(result);
                    }
                }
                else {
                    exchange.getIn().setBody(null);
                }
            }
            else
            {
                MessageContentsList mcl = (MessageContentsList)body;

                String[] params = new String[mcl.size()];

                for (int index = 0; index < mcl.size(); index++) {
                    params[index] = (String) mcl.get(index);
                }

                result = oracleDataService.executeGet(
                        packageName,
                        methodName,
                        className,
                        params
                );

                exchange.getIn().setBody(result);
            }
        }
        catch (Exception e) {

            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    private TimesheetDetail getRelatedTimesheetDetailData(final TimesheetDetail timesheetDetail) {

        /*List<VisitEvent> visitEvents = (List<VisitEvent>) oracleDataService.executeGet(
                "PKG_VISIT",
                "getVisitEvnt",
                "com.sandata.lab.data.model.dl.model.VisitEvent",
                visit.getVisitSK());

        for (VisitEvent item : visitEvents) {
            visit.getVisitEvent().add(item);
        } */

        return timesheetDetail;
    }

    private TimesheetSummary getRelatedTimesheetSummaryData(final TimesheetSummary timesheetSummary) {

        /*List<VisitEvent> visitEvents = (List<VisitEvent>) oracleDataService.executeGet(
                "PKG_VISIT",
                "getVisitEvnt",
                "com.sandata.lab.data.model.dl.model.VisitEvent",
                visit.getVisitSK());

        for (VisitEvent item : visitEvents) {
            visit.getVisitEvent().add(item);
        } */

        return timesheetSummary;
    }

    @Override
    public void delete(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

        logger.start();

        try
        {
            String operationName = (String)exchange.getIn().getHeader("operationName");

            String[] methodParts = operationName.split("_");

            String packageName = methodParts[0] + "_" + methodParts[1];
            String methodName = methodParts[2];

            long sequenceKey = (long)exchange.getIn().getHeader("sequence_key");

            long result = oracleDataService.execute(
                    packageName,
                    methodName,
                    sequenceKey
            );

            exchange.getIn().setBody(result);
        }
        catch (Exception e) {

            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    @Override
    public void update(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

        Connection connection = null;

        logger.start();

        try
        {
            Object data = exchange.getIn().getBody();

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);
            long returnVal = executeRecursive(connection, data, false /* UPDATE */, -999);
            if (returnVal > 0) {

                connection.commit();
                exchange.getIn().setBody(returnVal);
            }
            else {
                throw new SandataRuntimeException(exchange, "Update was not successful!");
            }
        }
        catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        }
        finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    @Override
    public void insert(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

        Connection connection = null;

        logger.start();

        try
        {
            Object data = exchange.getIn().getBody();

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);
            long returnVal = executeRecursive(connection, data, true, -999);
            if (returnVal > 0) {

                connection.commit();
                exchange.getIn().setBody(returnVal);
            }
            else {
                throw new SandataRuntimeException(exchange, "Insert was not successful!");
            }
        }
        catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        }
        finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    private void setSk(final Object jpubType, long sequenceKey, String setSkMethodName) throws Exception {

        if (sequenceKey <= 0) {
            return;
        }

        try {

            Method method = jpubType.getClass().getDeclaredMethod(setSkMethodName, BigDecimal.class);
            method.invoke(jpubType, BigDecimal.valueOf(sequenceKey));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private long executeRecursive(final Connection connection, final Object data, final boolean bShouldInsert, long returnVal)
            throws SandataRuntimeException {

        try {
            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(data);

            Object jpubType = new DataMapper().map(data);

            if (data instanceof TimesheetDetail) {
                setSk(jpubType, returnVal, "setTimesheetDetlSk");
            }
            else if (data instanceof TimesheetSummary) {
                setSk(jpubType, returnVal, "setTimesheetSummarySk");
            }

            long result = 0;

            if (bShouldInsert) {
                result = oracleDataService.execute(
                        connection,
                        oracleMetadata.packageName(),
                        oracleMetadata.insertMethod(),
                        jpubType
                );
            }
            else {

                if (data instanceof TimesheetDetail) {
                    returnVal = ((TimesheetDetail)data).getTimesheetDetailSK().longValue();
                }
                else if (data instanceof TimesheetSummary) {
                    returnVal = ((TimesheetSummary)data).getTimesheetSummarySK().longValue();
                }

                // UPDATE
                result = oracleDataService.execute(
                        connection,
                        oracleMetadata.packageName(),
                        oracleMetadata.updateMethod(),
                        jpubType
                );
            }

            if (result > 0) {

                if (returnVal == -999) {
                    returnVal = result;
                }

                // Check if there are any lists that need to be inserted
                for (Field field : data.getClass().getDeclaredFields()) {

                    field.setAccessible(true);

                    Object property = field.get(data);
                    if (property != null && property instanceof List) {

                        List list = (List)property;
                        for (Object object : list) {

                            // Try to insert the object!

                            // WARNING: RECURSIVE!!!!
                            long insertResponse = executeRecursive(connection, object, bShouldInsert, returnVal);
                            if (insertResponse == -1) {
                                if (bShouldInsert) {
                                    throw new SandataRuntimeException(String.format("INSERT: Failed: [%s]",
                                            object.getClass().getName()));
                                }
                                else {
                                    throw new SandataRuntimeException(String.format("UPDATE: Failed: [%s]",
                                            object.getClass().getName()));
                                }
                            }
                        }
                    }
                }

                // SUCCESS
                return returnVal;

            } // if (result > 0)

            // FAILED
            return -1;
        }
        catch (Exception e) {
            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
    }

    public void timeSheetDetailProcess(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange, oracleDataService.getLoggerService());

        logger.start();

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            logger.trace(exchange.getFromRouteId(), null, "RestDataService: timeSheetDetailProcess: START");

            Object request = exchange.getIn().getBody();

            if (request == null) {
                String errMsg = String.format("%s: EXCEPTION: timeSheetDetailProcess: request == null", getClass().getName());
                logger.error(errMsg);
                throw new SandataRuntimeException(errMsg);
            }

            if (!(request instanceof TimeSheetDetailRequest)) {
                String errMsg = String.format("%s: EXCEPTION: timeSheetDetailProcess: The request is not a TimeSheetDetailRequest entity!", getClass().getName());
                logger.error(errMsg);
                throw new SandataRuntimeException(errMsg);
            }

            TimeSheetDetailRequest timeSheetDetailRequest = (TimeSheetDetailRequest)request;
            TimeSheetSummaryRequest timeSheetSummaryRequest = timeSheetDetailRequest.getTimeSheetSummaryRequest();

            if (timeSheetSummaryRequest == null) {
                String errMsg = String.format("%s: EXCEPTION: timeSheetDetailProcess: timeSheetSummaryRequest == null", getClass().getName());
                logger.error(errMsg);
                throw new SandataRuntimeException(errMsg);
            }

            BigInteger timesheetSummarySk = oracleDataService.getTimeSheetSummarySK(
                            timeSheetSummaryRequest.getStaffId(),
                            timeSheetSummaryRequest.getBusinessEntityId(),
                            timeSheetSummaryRequest.getPayrollWeekEndDate(),
                            logger);

            // Timesheet Summary does not exist!
            if (timesheetSummarySk == null) {

                logger.info(String.format("%s: timeSheetDetailProcess: Creating TimesheetSummary: [STAFF_ID=%s]: " +
                                "[BE_ID=%s]: [WEEK_END_DATE=%s]",
                                    getClass().getName(),
                                    timeSheetSummaryRequest.getStaffId(),
                                    timeSheetSummaryRequest.getBusinessEntityId(),
                                    dateFormat.format(timeSheetSummaryRequest.getPayrollWeekEndDate())));

                TimesheetSummary timesheetSummary = new TimesheetSummary();

                // TODO: Ask DB team why this is still part of TimesheetSummary
                timesheetSummary.setBusinessEntityCalendarLookupSK(timeSheetDetailRequest.getCalendarLkupSk());

                timesheetSummary.setRecordCreateTimestamp(new Date());
                timesheetSummary.setRecordUpdateTimestamp(new Date());
                timesheetSummary.setChangeVersionID(BigInteger.ZERO);

                timesheetSummary.setBusinessEntityID(timeSheetSummaryRequest.getBusinessEntityId());
                timesheetSummary.setBusinessEntityLineOfBusinessID(timeSheetSummaryRequest.getBusinessEntityId());
                timesheetSummary.setStaffID(timeSheetSummaryRequest.getStaffId());
                timesheetSummary.setTimesheetWeekEndDate(timeSheetSummaryRequest.getPayrollWeekEndDate());

                exchange.getIn().setBody(timesheetSummary);
                insert(exchange);

                timesheetSummarySk = BigInteger.valueOf((int)exchange.getIn().getBody());

                logger.info(String.format("%s: timeSheetDetailProcess: Created TimeSheetSummary Record: [TIMESHEET_SUMMARY_SK=%d]",
                                getClass().getName(), timesheetSummarySk.intValue()));
            }

            // If the TimesheetDetail doesn't already exist, create it
            BigInteger timeSheetDetailSk = oracleDataService.getTimeSheetDetailSK(
                                timeSheetDetailRequest.getVisitSk().longValue(),
                                timeSheetDetailRequest.getPayrollCode(),
                                timeSheetDetailRequest.getTimesheetDate(),
                                logger);

            if (timeSheetDetailSk == null) {

                logger.info(String.format("%s: timeSheetDetailProcess: Creating TimesheetDetail: [STAFF_ID=%s]: " +
                                "[PT_ID: %s]: [VISIT_SK: %d]: [PR_CODE: %s]: [BE_ID=%s]: [TIMESHEET_DATE=%s]",
                        getClass().getName(),
                        timeSheetSummaryRequest.getStaffId(),
                        timeSheetDetailRequest.getPatientId(),
                        timeSheetDetailRequest.getVisitSk().longValue(),
                        timeSheetDetailRequest.getPayrollCode(),
                        timeSheetSummaryRequest.getBusinessEntityId(),
                        dateFormat.format(timeSheetDetailRequest.getTimesheetDate())));

                TimesheetDetail timesheetDetail = new TimesheetDetail();

                timesheetDetail.setTimesheetSummarySK(timesheetSummarySk);

                timesheetDetail.setRecordCreateTimestamp(new Date());
                timesheetDetail.setRecordUpdateTimestamp(new Date());
                timesheetDetail.setChangeVersionID(BigInteger.ZERO);

                timesheetDetail.setBusinessEntityID(timeSheetSummaryRequest.getBusinessEntityId());
                timesheetDetail.setPatientID(timeSheetDetailRequest.getPatientId());
                timesheetDetail.setVisitSK(timeSheetDetailRequest.getVisitSk());
                timesheetDetail.setBusinessEntityCalendarLookupSK(timeSheetDetailRequest.getCalendarLkupSk());
                timesheetDetail.setPayrollCode(timeSheetDetailRequest.getPayrollCode());
                timesheetDetail.setTimesheetDate(timeSheetDetailRequest.getTimesheetDate());
                timesheetDetail.setPayrollRateAmount(timeSheetDetailRequest.getPayrollRateAmount());
                timesheetDetail.setPayrollHours(BigDecimal.valueOf(timeSheetDetailRequest.getPayrollHours().intValue()));
                timesheetDetail.setPayrollAmount(timeSheetDetailRequest.getPayrollAmount());

                exchange.getIn().setBody(timesheetDetail);

                insert(exchange);

                int insertReturnValue = (int) exchange.getIn().getBody();

                logger.info(String.format("%s: timeSheetDetailProcess: Created TimeSheetDetail Record: [TIMESHEET_DET_SK=%d]",
                        getClass().getName(), insertReturnValue));
            }
            else {

                // Timesheet Detail Record Already Exists!
                String timeSheetDateString = dateFormat.format(timeSheetDetailRequest.getTimesheetDate());

                logger.warn(String.format("%s: timeSheetDetailProcess: TimeSheetDetail Already Exists! " +
                        "[TIMESHEET_DETL_SK=%d]: [VISIT_SK=%d]: [PR_CODE:%s]: [TIMESHEET_DATE:%s]",
                        getClass().getName(),
                        timeSheetDetailSk.longValue(),
                        timeSheetDetailRequest.getVisitSk().longValue(),
                        timeSheetDetailRequest.getPayrollCode(),
                        timeSheetDateString));
            }

        } catch (Exception e) {
            e.printStackTrace();

            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.trace(exchange.getFromRouteId(), null, "RestDataService: timeSheetDetailProcess: END");
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
