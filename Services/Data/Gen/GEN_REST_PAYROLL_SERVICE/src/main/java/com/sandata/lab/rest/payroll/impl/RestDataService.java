package com.sandata.lab.rest.payroll.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.data.money.MoneyUtil;
import com.sandata.lab.common.utils.date.DateUtil;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.common.utils.time.TimeUtil;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.extended.payroll.PayrollRateMatrixExchange;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.payroll.api.DataService;
import com.sandata.lab.rest.payroll.model.PayrollFindParams;
import com.sandata.lab.rest.payroll.model.PayrollFindStatus;
import com.sandata.lab.rest.payroll.model.PayrollRateMatrixRequest;
import com.sandata.lab.rest.payroll.utils.data.ServiceListUtil;
import com.sandata.lab.rest.payroll.utils.log.OracleDataLogger;
import org.apache.camel.Exchange;
import org.apache.cxf.message.MessageContentsList;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.sandata.lab.rest.payroll.utils.log.OracleDataLogger.CreateLogger;
import static java.lang.String.format;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */
@SuppressWarnings("unchecked")
public class RestDataService implements DataService {

    private OracleDataService oracleDataService;

    @Override
    public void get(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger();

        try
        {
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

                    if (methodParts[3].equals("PayrollInput")) {

                        exchange.getIn().setBody(getRelatedPayrollInputData((PayrollInput) result));
                    }
                    else if (methodParts[3].equals("PayrollOutput")) {

                        exchange.getIn().setBody(getRelatedPayrollOutputData((PayrollOutput) result));
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
                    params[index] = (String)mcl.get(index);
                }

                result = oracleDataService.executeGet(
                        packageName,
                        methodName,
                        className,
                        params
                );

                /*
                //dmr GEOR-1034: Extended VisitTaskList to logically determine if the task is accepted.
                if(methodParts[3].equals("VisitTaskList")) {

                    List<VisitTaskListExt> visitTaskListExtList = new ArrayList<>();

                    List<VisitTaskList> visitTaskLists = (List<VisitTaskList>)result;
                    for (VisitTaskList visitTaskList : visitTaskLists) {

                        VisitTaskListExt visitTaskListExt = new VisitTaskListExt(visitTaskList);
                        visitTaskListExtList.add(visitTaskListExt);
                    }

                    exchange.getIn().setBody(visitTaskListExtList);
                }
                // TODO: Temporary fix...
                else if(methodParts[3].equals("VisitEvent")) {

                    List<VisitEvent> visitEvents = (List<VisitEvent>)result;
                    for (VisitEvent visitEvent : visitEvents) {

                        visitEvent.setChangeVersionID(BigInteger.valueOf(1));
                    }

                    exchange.getIn().setBody(result);
                }
                else {
                    */
                    exchange.getIn().setBody(result);
                //}
            }

            logger.stop();
        }
        catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
    }

    private PayrollInput getRelatedPayrollInputData(final PayrollInput data) {

        /*List<VisitEvent> visitEvents = (List<VisitEvent>) oracleDataService.executeGet(
                "PKG_VISIT",
                "getVisitEvnt",
                "com.sandata.lab.data.model.dl.model.VisitEvent",
                visit.getVisitSK());

        for (VisitEvent item : visitEvents) {
            visit.getVisitEvent().add(item);
        } */

        return data;
    }

    private PayrollOutput getRelatedPayrollOutputData(final PayrollOutput data) {

        /*List<VisitEvent> visitEvents = (List<VisitEvent>) oracleDataService.executeGet(
                "PKG_VISIT",
                "getVisitEvnt",
                "com.sandata.lab.data.model.dl.model.VisitEvent",
                visit.getVisitSK());

        for (VisitEvent item : visitEvents) {
            visit.getVisitEvent().add(item);
        } */

        return data;
    }

    @Override
    public void delete(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger();

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

            logger.stop();
        }
        catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
    }

    @Override
    public void update(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger();

        Connection connection = null;

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
                throw new SandataRuntimeException("Update was not successful!");
            }

            logger.stop();
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

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
        finally {

            this.oracleDataService.closeOracleConnection(connection);
        }
    }

    @Override
    public void insert(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger();

        Connection connection = null;

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
                throw new SandataRuntimeException("Insert was not successful!");
            }

            logger.stop();
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

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
        finally {

            this.oracleDataService.closeOracleConnection(connection);
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
            throw e;
        }
    }

    private long executeRecursive(final Connection connection, final Object data, final boolean bShouldInsert, long returnVal)
            throws SandataRuntimeException {

        try {
            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(data);

            Object jpubType = new DataMapper().map(data);

            if (data instanceof PayrollInput || data instanceof PayrollInputDeductionDetail ||
                    data instanceof PayrollInputEarningDetail || data instanceof PayrollInputTaxDetail) {
                setSk(jpubType, returnVal, "setPrInputSk");
            }
            else if (data instanceof PayrollOutput) {
                setSk(jpubType, returnVal, "setPrInputSk");
            }

            long result = 0;

            if (bShouldInsert) {
                result = oracleDataService.execute(
                        connection,
                        ConnectionType.COREDATA,
                        oracleMetadata.packageName(),
                        oracleMetadata.insertMethod(),
                        jpubType
                );
            }
            else {

                if (data instanceof PayrollInput) {
                    returnVal = ((PayrollInput)data).getPayrollInputSK().longValue();
                } else if (data instanceof PayrollOutput) {
                    returnVal = ((PayrollOutput)data).getPayrollOutputSK().longValue();
                } else if (data instanceof PayrollInputDeductionDetail) {
                    returnVal = ((PayrollInputDeductionDetail)data).getPayrollInputDeductionDetailSK().longValue();
                } else if (data instanceof PayrollInputEarningDetail) {
                    returnVal = ((PayrollInputEarningDetail)data).getPayrollInputEarningDetailSK().longValue();
                }else if (data instanceof PayrollInputTaxDetail) {
                    returnVal = ((PayrollInputTaxDetail)data).getPayrollInputTaxDetailSK().longValue();
                }

                // UPDATE
                result = oracleDataService.execute(
                        connection,
                        ConnectionType.COREDATA,
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
                                    throw new SandataRuntimeException(format("INSERT: Failed: [%s]",
                                            object.getClass().getName()));
                                }
                                else {
                                    throw new SandataRuntimeException(format("UPDATE: Failed: [%s]",
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
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
    }

    public void findPayrollDetail(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange, getOracleDataService().getLoggerService());

        logger.start();

        try {

            logger.trace("findPayrollDetail: START");

            logger.debug(String.format("findPayrollDetail: [Exchange Headers=%s]",
                    exchange.getIn().getHeaders().toString())
            );

            DateFormat payrollDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

            PayrollFindParams payrollFindParams = new PayrollFindParams();

            String staffFirstName = (String)exchange.getIn().getHeader("staff_first_name");
            if (!StringUtil.IsNullOrEmpty(staffFirstName)) {
                payrollFindParams.setStaffFirstName(String.format("%%%s%%", staffFirstName.toUpperCase()));
            }

            String staffLastName = (String)exchange.getIn().getHeader("staff_last_name");
            if (!StringUtil.IsNullOrEmpty(staffLastName)) {
                payrollFindParams.setStaffLastName(String.format("%%%s%%", staffLastName.toUpperCase()));
            }

            payrollFindParams.setStaffID((String)exchange.getIn().getHeader("staff_id"));

            String payHoursString = (String)exchange.getIn().getHeader("pay_hours");
            String payHoursFilterString = (String)exchange.getIn().getHeader("pay_hours_filter");
            if (!StringUtil.IsNullOrEmpty(payHoursString)) {
                try {
                    BigDecimal payHours = new BigDecimal(payHoursString);

                    payrollFindParams.setPayHours(BigInteger.valueOf(TimeUtil.HoursToSeconds(payHours.floatValue())));

                } catch (Exception e) {
                    throw new SandataRuntimeException(
                            String.format("Pay Hours (pay_hours) [%s]: Is not in the expected format!",
                                    payHoursString));
                }
            } else if (!StringUtil.IsNullOrEmpty(payHoursFilterString)) {

                String payHoursFilter = null;
                long min = 0;
                long max = 0;
                switch (payHoursFilterString.toUpperCase()) {
                    case "0 TO 5 HOURS":
                        min = TimeUtil.HoursToSeconds(0f);
                        max = TimeUtil.HoursToSeconds(5f);
                        payHoursFilter = String.format("AND TIMESHEET_WEEK_TOTAL_HRS BETWEEN %d AND %d", min, max);
                        break;
                    case "6 TO 40 HOURS":
                        min = TimeUtil.HoursToSeconds(6f);
                        max = TimeUtil.HoursToSeconds(40f);
                        payHoursFilter = String.format("AND TIMESHEET_WEEK_TOTAL_HRS BETWEEN %d AND %d", min, max);
                        break;
                    case "40+ HOURS":
                        max = TimeUtil.HoursToSeconds(40f);
                        payHoursFilter = String.format("AND TIMESHEET_WEEK_TOTAL_HRS >= %d", max);
                        break;
                }

                payrollFindParams.setPayHoursFilter(payHoursFilter);
            }

            String services[] = ServiceListUtil.FormatToSqlArray((String)exchange.getIn().getHeader("service"));

            String fromDateTimeString = (String)exchange.getIn().getHeader("from_date_time");
            String toDateTimeString = (String)exchange.getIn().getHeader("to_date_time");

            if(StringUtil.IsNullOrEmpty(fromDateTimeString)){
                throw new SandataRuntimeException("From Date (from_date_time) is null or empty!");
            }

            if(StringUtil.IsNullOrEmpty(toDateTimeString)){
                throw new SandataRuntimeException("To Date (to_date_time) is null or empty!");
            }

            Date fromDateTime = DateUtil.DateFromStringFormat(fromDateTimeString, DateUtil.SANDATA_UTC_DATE_TIME_FORMAT);
            Date toDateTime = DateUtil.DateFromStringFormat(toDateTimeString, DateUtil.SANDATA_UTC_DATE_TIME_FORMAT);

            payrollFindParams.setPayrollFromDate(fromDateTime);
            payrollFindParams.setPayrollToDate(toDateTime);

            payrollFindParams.setCheckNumber((String)exchange.getIn().getHeader("check"));

            String checkAmountString = (String)exchange.getIn().getHeader("check_amount");
            if (!StringUtil.IsNullOrEmpty(checkAmountString)) {
                try {
                    payrollFindParams.setCheckAmount(MoneyUtil.toBigDecimal(checkAmountString));

                } catch (Exception e) {
                    throw new SandataRuntimeException(
                            String.format("Check Amount (check_amount) [%s]: Is not in the expected format!",
                                    checkAmountString));
                }
            }

            String checkDateString = (String)exchange.getIn().getHeader("check_date");
            if (!StringUtil.IsNullOrEmpty(checkDateString)) {
                Date checkDate = DateUtil.DateFromStringFormat(checkDateString, DateUtil.SANDATA_UTC_DATE_TIME_FORMAT);
                payrollFindParams.setCheckDate(checkDate);
            }

            String payRateString = (String)exchange.getIn().getHeader("pay_rate");
            if (!StringUtil.IsNullOrEmpty(payRateString)) {
                try {
                    payrollFindParams.setRateAmount(MoneyUtil.toBigDecimal(payRateString));

                } catch (Exception e) {
                    throw new SandataRuntimeException(
                            String.format("Pay Rate (pay_rate) [%s]: Is not in the expected format!",
                                    payRateString));
                }
            }

            payrollFindParams.setBusinessEntityID((String)exchange.getIn().getHeader("bsn_ent_id"));

            if (StringUtil.IsNullOrEmpty(payrollFindParams.getBusinessEntityID())) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            payrollFindParams.setPatientID((String)exchange.getIn().getHeader("patient_id"));

            String patientFirstName = (String)exchange.getIn().getHeader("patient_first_name");
            if (!StringUtil.IsNullOrEmpty(patientFirstName)) {
                payrollFindParams.setPatientFirstName(String.format("%%%s%%", patientFirstName.toUpperCase()));
            }

            String patientLastName = (String)exchange.getIn().getHeader("patient_last_name");
            if (!StringUtil.IsNullOrEmpty(patientLastName)) {
                payrollFindParams.setPatientLastName(String.format("%%%s%%", patientLastName.toUpperCase()));
            }

            String status = (String)exchange.getIn().getHeader("status");
            if (!StringUtil.IsNullOrEmpty(status)) {

                try {
                    PayrollFindStatus payrollFindStatus = PayrollFindStatus.fromValue(status.toUpperCase());
                    payrollFindParams.setStatus(payrollFindStatus);

                } catch (Exception e) {
                    throw new SandataRuntimeException(
                            String.format("Payroll Status (status) [%s]: Is not in the expected format!", status));
                }

            } else {
                throw new SandataRuntimeException("Payroll Status (status) is required!");
            }

            int page = (int)exchange.getIn().getHeader("page");
            int pageSize = (int)exchange.getIn().getHeader("page_size");

            BigInteger toRow = BigInteger.valueOf(page * pageSize);
            BigInteger fromRow = BigInteger.valueOf(toRow.intValue() - (pageSize - 1));

            payrollFindParams.setToRow(toRow);
            payrollFindParams.setFromRow(fromRow);

            String orderByColumn = "UPPER(STAFF_LAST_NAME)"; // DEFAULT

            String sortOn = (String)exchange.getIn().getHeader("sort_on");
            switch (sortOn) {
                case "staff_first_name": orderByColumn = "UPPER(STAFF_FIRST_NAME)"; break;
                case "staff_last_name": orderByColumn = "UPPER(STAFF_LAST_NAME)"; break;
                case "staff_id": orderByColumn = "UPPER(STAFF_ID)"; break;
                case "pay_hours": orderByColumn = "TIMESHEET_WEEK_TOTAL_HRS"; break;
                case "eft": orderByColumn = "UPPER(EFT_TXN_ID)"; break;
                case "check_number": orderByColumn = "CHECK_NUM"; break;
                case "check_date": orderByColumn = "CHECK_DATE"; break;
                case "check_amount": orderByColumn = "GROSS_PAY_AMT"; break;
            }

            payrollFindParams.setOrderByColumn(orderByColumn);
            payrollFindParams.setOrderByDirection((String)exchange.getIn().getHeader("direction"));

            String rateType = (String)exchange.getIn().getHeader("rate_type");

            payrollFindParams.setRateTypName(rateType);

            Response response = oracleDataService.findPayrollDetail(payrollFindParams, services, logger);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", page);
            exchange.getIn().setHeader("PAGE_SIZE", pageSize);
            exchange.getIn().setHeader("ORDER_BY_COLUMN", payrollFindParams.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", payrollFindParams.getOrderByDirection());

            exchange.getIn().setBody(response.getData());
        }
        catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.trace("findPayrollDetail: END");
            logger.stop();
        }
    }

    public void getPayrollDetail(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange, getOracleDataService().getLoggerService());

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            Long payrollSk = (Long)mcl.get(0);
            if (payrollSk == null) {
                throw new SandataRuntimeException("PayrollSK (payroll_sk) is required!");
            }

            String bsnEntId = (String)mcl.get(1);
            if (bsnEntId == null) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            Response response = oracleDataService.getPayrollDetail(payrollSk, bsnEntId);

            exchange.getIn().setBody(response != null ? response.getData() : null);
        }
        catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void updatePrRateMatrixExchange(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange, getOracleDataService().getLoggerService());

        logger.start();

        Connection connection = null;

        try {

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            exchange.getIn().setBody(oracleDataService.updatePrRateMatrixExchange(connection, exchange.getIn().getBody(PayrollRateMatrixExchange.class), logger));

            connection.commit();

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            logger.error(errMsg);
            throw new SandataRuntimeException(errMsg, e);

        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    public void insertPrRateMatrixExchange(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange, getOracleDataService().getLoggerService());

        logger.start();

        Connection connection = null;

        try {

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            exchange.getIn().setBody(oracleDataService.insertPrRateMatrixExchange(connection, exchange.getIn().getBody(PayrollRateMatrixExchange.class), logger));

            connection.commit();

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            logger.error(errMsg);
            throw new SandataRuntimeException(errMsg, e);

        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    public void getPrRateMatrixExchange(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange, oracleDataService.getLoggerService());

        logger.start();

        try {

            logger.trace("getPrRateMatrixExchange: START");

            logger.debug(String.format("getPrRateMatrixExchange: [Exchange Headers=%s]",
                                exchange.getIn().getHeaders().toString())
            );

            exchange.getIn().setBody(oracleDataService.getPrRateMatrixExchange(handlePayrollMatrixRequest(exchange, logger, false), logger));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            logger.error(errMsg);
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
            logger.trace("getPrRateMatrixExchange: END");
        }
    }

    public void deletePrRateMatrixExchange(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange, oracleDataService.getLoggerService());

        logger.start();

        try {

            exchange.getIn().setBody(oracleDataService.deletePrRateMatrixExchange(handlePayrollMatrixRequest(exchange, logger, true)));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            logger.error(errMsg);
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    private PayrollRateMatrixRequest handlePayrollMatrixRequest(Exchange exchange, SandataLogger logger, boolean isDeleteRequest) {

        logger.trace("handlePayrollMatrixRequest: START");

        logger.trace("handlePayrollMatrixRequest: isDeleteRequest = " + ((isDeleteRequest) ? "true" : "false"));

        PayrollRateMatrixRequest request = new PayrollRateMatrixRequest();

        MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

        request.setBusinessEntityId((String) mcl.get(0));
        if (StringUtil.IsNullOrEmpty(request.getBusinessEntityId())) {
            throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
        }

        logger.trace("handlePayrollMatrixRequest: [BE_ID=" + request.getBusinessEntityId() + "]");

        request.setPayerId((String) mcl.get(1));
        if (StringUtil.IsNullOrEmpty(request.getPayerId())) {
            throw new SandataRuntimeException("PayerID (payer_id) is required!");
        }

        logger.trace("handlePayrollMatrixRequest: [PAYER_ID=" + request.getPayerId() + "]");

        request.setContractId((String) mcl.get(2));
        if (StringUtil.IsNullOrEmpty(request.getContractId())) {
            throw new SandataRuntimeException("ContractID (contract_id) is required!");
        }

        logger.trace("handlePayrollMatrixRequest: [CONTR_ID=" + request.getContractId() + "]");

        String serviceNameString = (String) mcl.get(3);

        //dmr--GEOR-5311
        if (StringUtil.IsNullOrEmpty(serviceNameString) && isDeleteRequest) {
            throw new SandataRuntimeException("ServiceName (service_name) is required!");
        }

        logger.trace("handlePayrollMatrixRequest: [SVC_NAME=" + ((serviceNameString == null) ? "null" : serviceNameString) + "]");

        if (!StringUtil.IsNullOrEmpty(serviceNameString)) {
            // Verify ServiceName Exists
            ServiceName serviceName;
            try {
                serviceName = ServiceName.fromValue(serviceNameString.toUpperCase());
            } catch (IllegalArgumentException iae) {
                logger.error(iae.getMessage());
                throw new SandataRuntimeException(String.format("[ServiceName=%s]: Is not a valid service!", serviceNameString));
            }
            request.setServiceName(serviceName);
        }

        String rateTypeNameString = (String) mcl.get(4);

        //dmr--GEOR-5311
        if (StringUtil.IsNullOrEmpty(rateTypeNameString) && isDeleteRequest) {
            throw new SandataRuntimeException("RateType (rate_type) is required!");
        }

        logger.trace("handlePayrollMatrixRequest: [RATE_TYP_NAME=" + ((rateTypeNameString == null) ? "null" : rateTypeNameString) + "]");

        if (!StringUtil.IsNullOrEmpty(rateTypeNameString)) {
            request.setRateTypeName(rateTypeNameString.toUpperCase());
        }

        logger.trace("handlePayrollMatrixRequest: END");

        return request;
    }

    public OracleDataService getOracleDataService() {
        return oracleDataService;
    }

    public void setOracleDataService(OracleDataService oracleDataService) {
        this.oracleDataService = oracleDataService;
    }
}
