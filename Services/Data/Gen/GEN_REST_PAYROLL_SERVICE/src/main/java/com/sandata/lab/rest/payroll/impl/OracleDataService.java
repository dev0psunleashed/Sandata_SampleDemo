package com.sandata.lab.rest.payroll.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.common.utils.time.TimeUtil;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.extended.VisitTaskListExt;
import com.sandata.lab.data.model.dl.model.extended.payroll.PayrollRateMatrixExchange;
import com.sandata.lab.data.model.jpub.model.PrRateMatrixT;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.data.model.staff.StaffVisit;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.logger.api.LoggerService;
import com.sandata.lab.rest.payroll.api.OracleService;
import com.sandata.lab.rest.payroll.model.*;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

@SuppressWarnings("unchecked")
public class OracleDataService implements OracleService {

    protected ConnectionPoolDataService connectionPoolDataService;

    private LoggerService loggerService;

    public LoggerService getLoggerService() {
        return loggerService;
    }

    public void setLoggerService(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    public Connection getOracleConnection() throws SandataRuntimeException {
        return connectionPoolDataService.getConnection();
    }

    public ConnectionPoolDataService getConnectionPoolDataService() {
        return connectionPoolDataService;
    }

    public void setConnectionPoolDataService(ConnectionPoolDataService connectionPoolDataService) {
        this.connectionPoolDataService = connectionPoolDataService;
    }
    public void closeOracleConnection(Connection connection) throws SandataRuntimeException {
        this.connectionPoolDataService.close(connection);
    }

    @Override
    public Object executeGet(String packageName, String methodName, String className, Object... params)
            throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.STRING_ARRAY", connection);
            ARRAY primaryKeysArray = new ARRAY(des, connection, params);

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setArray(2, primaryKeysArray);
            callableStatement.execute();

            resultSet = (ResultSet)callableStatement.getObject(1);

            Object result = new DataMapper().map(resultSet, className);

            connection.commit();

            return result;

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

            throw new SandataRuntimeException(String.format("[%s][%s][%s]: %s: %s",
                    packageName, methodName, className,
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
            this.connectionPoolDataService.close(connection);
        }
    }

    @Override
    public Object executeGet(String packageName, String methodName, String className, long sequenceKey) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setLong(2, sequenceKey);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(1);

            Object result = new DataMapper().map(resultSet, className);

            connection.commit();

            return result;

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

            throw new SandataRuntimeException(String.format("[%s][%s][%s]: %s: %s",
                    packageName, methodName, className,
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
            this.connectionPoolDataService.close(connection);
        }
    }

    @Override
    public long execute(String packageName, String methodName, long sequenceKey) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
            callableStatement.setLong(2, sequenceKey);
            callableStatement.execute();
            long result = callableStatement.getLong(1);

            connection.commit();

            return result;

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

            throw new SandataRuntimeException(String.format("[%s][%s]: %s: %s",
                    packageName, methodName,
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
            this.connectionPoolDataService.close(connection);
        }
    }

    @Override
    public long execute(String packageName, String methodName, Object jpubType) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
            callableStatement.setObject(2, jpubType);
            callableStatement.execute();
            long result = callableStatement.getLong(1);

            connection.commit();

            return result;

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

            throw new SandataRuntimeException(String.format("[%s][%s]: %s: %s",
                    packageName, methodName,
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
            this.connectionPoolDataService.close(connection);
        }
    }

    @Override
    public long execute(Connection connection, ConnectionType connectionType, String packageName, String methodName, Object jpubType) throws SandataRuntimeException {

        CallableStatement callableStatement = null;

        try {

            String callMethod = String.format("{?=call %s.%s.%s(?)}", connectionType, packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
            callableStatement.setObject(2, jpubType);
            callableStatement.execute();
            long result = callableStatement.getLong(1);
            return result;
        }
        catch (Exception e) {

            // NOTE: Rollback (if necessary) should be handled by caller since they passed in the connection...
            throw new SandataRuntimeException(String.format("[%s][%s]: %s: %s",
                    packageName, methodName,
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
        }
    }

    public FindPayrollResult mapFindPayrollDetail(final ResultSet resultSet) throws SQLException {

        FindPayrollResult findPayrollResult = new FindPayrollResult();

        findPayrollResult.setPayrollSk(resultSet.getBigDecimal("TIMESHEET_SUMMARY_SK").toBigInteger());
        findPayrollResult.setStaffId(resultSet.getString("STAFF_ID"));
        findPayrollResult.setStaffFirstName(resultSet.getString("STAFF_FIRST_NAME"));
        findPayrollResult.setStaffLastName(resultSet.getString("STAFF_LAST_NAME"));

        // Hours are stored in seconds in the DB
        // Calculated on getPayHours
        BigDecimal totalHours = resultSet.getBigDecimal("TIMESHEET_WEEK_TOTAL_HRS");
        if (totalHours != null) {
            findPayrollResult.setPayHours(TimeUtil.SecondsToHours(totalHours.longValue()));
        }

        Timestamp timesheetWeekEndDate = resultSet.getTimestamp("TIMESHEET_WEEK_END_DATE");
        if (timesheetWeekEndDate != null) {
            findPayrollResult.setPayEndDate(new Date(timesheetWeekEndDate.getTime()));
        }

        findPayrollResult.setElectronicFinancialTransactionID(resultSet.getString("EFT_TXN_ID"));

        findPayrollResult.setCheckNumber(resultSet.getString("CHECK_NUM"));

        Timestamp checkDate = resultSet.getTimestamp("CHECK_DATE");
        if (checkDate != null) {
            findPayrollResult.setCheckDate(new Date(checkDate.getTime()));
        }

        BigDecimal checkAmount = resultSet.getBigDecimal("GROSS_PAY_AMT");
        if (checkAmount != null) {
            findPayrollResult.setCheckAmount(checkAmount.setScale(2, RoundingMode.CEILING));
        }

        List<FindPayrollDetail> services = new ArrayList<>();
        findPayrollResult.setServices(services);

        Object timesheetDetCursor = resultSet.getObject("TIMESHEET_DET_CURSOR");
        ResultSet tdcResultSet = (ResultSet) timesheetDetCursor;
        while (tdcResultSet.next()) {

            FindPayrollDetail findPayrollDetail = new FindPayrollDetail();

            BigDecimal visitSK = tdcResultSet.getBigDecimal("VISIT_SK");
            if (visitSK != null) {
                findPayrollDetail.setVisitSk(visitSK.toBigInteger());
            }

            findPayrollDetail.setPatientId(tdcResultSet.getString("PT_ID"));
            findPayrollDetail.setPatientFirstName(tdcResultSet.getString("PT_FIRST_NAME"));
            findPayrollDetail.setPatientLastName(tdcResultSet.getString("PT_LAST_NAME"));

            findPayrollDetail.setServiceName(tdcResultSet.getString("SVC_NAME"));

            Timestamp serviceDate = tdcResultSet.getTimestamp("TIMESHEET_DATE");
            if (serviceDate != null) {
                findPayrollDetail.setServiceDate(new Date(serviceDate.getTime()));
            }

            BigDecimal payrollRateAmount = tdcResultSet.getBigDecimal("PR_RATE_AMT");
            if (payrollRateAmount != null) {
                findPayrollDetail.setRate(payrollRateAmount.setScale(2, RoundingMode.CEILING));
            }

            BigDecimal payrollHoursInSeconds = tdcResultSet.getBigDecimal("PR_HRS");
            if (payrollHoursInSeconds != null) {
                findPayrollDetail.setHours(TimeUtil.SecondsToHours(payrollHoursInSeconds.longValue()));
            }

            BigDecimal payrollAmount = tdcResultSet.getBigDecimal("PR_AMT");
            if (payrollAmount != null) {
                findPayrollDetail.setPayrollAmount(payrollAmount.setScale(2, RoundingMode.CEILING));
            }

            findPayrollDetail.setRateTypeName(tdcResultSet.getString("RATE_TYP_NAME"));

            findPayrollDetail.initTotalPay();

            services.add(findPayrollDetail);
        }

        tdcResultSet.close();

        findPayrollResult.init();

        return findPayrollResult;
    }

    public Response findPayrollDetail(PayrollFindParams payrollFindParams, String services[], SandataLogger logger) {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        //logger.debug(String.format("%s: findPayrollDetail: [PayrollFindParams=%s]", getClass().getName(), payrollFindParams.toString()));

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            List<FindPayrollResult> findPayrollResults = new ArrayList<>();

            Response response = new Response();
            response.setData(findPayrollResults);

            if (services == null) {
                services = new String[0];
            }

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.STRING_ARRAY", connection);
            ARRAY servicesArray = new ARRAY(des, connection, services);

            Object jpubType = new DataMapper().map(payrollFindParams);
            String callMethod = "{?=call COREDATA.PKG_PR_UTIL.FIND_PAYROLL(?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setObject(2, jpubType);
            callableStatement.setArray(3, servicesArray);
            callableStatement.execute();

            resultSet = (ResultSet)callableStatement.getObject(1);
            while (resultSet.next()) {

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());
                }

                FindPayrollResult findPayrollResult = mapFindPayrollDetail(resultSet);
                findPayrollResult.setPayrollStatus(payrollFindParams.getStatus().value());
                findPayrollResults.add(findPayrollResult);
            }

            return response;

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
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    private List<VisitTaskList> getVisitTaskList(final Connection connection, final long visitSk) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT * FROM VISIT_TASK_LST WHERE VISIT_SK = ?";

            preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            preparedStatement.setLong(index++, visitSk);

            resultSet = preparedStatement.executeQuery();

            return  (List<VisitTaskList>)new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.VisitTaskList");
        }
        catch (Exception e) {

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    private List<VisitService> getServiceVisits(final Connection connection, final long visitSk) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT * FROM VISIT_SVC WHERE VISIT_SK = ?";

            preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            preparedStatement.setLong(index++, visitSk);

            resultSet = preparedStatement.executeQuery();

            return  (List<VisitService>)new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.VisitService");
        }
        catch (Exception e) {

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    private List<VisitException> getVisitExceptions(final Connection connection, final long visitSk) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT * FROM VISIT_EXCP WHERE VISIT_SK = ?";

            preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            preparedStatement.setLong(index++, visitSk);

            resultSet = preparedStatement.executeQuery();

            return  (List<VisitException>)new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.VisitException");
        }
        catch (Exception e) {

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

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    private Visit getVisits(final Connection connection, final long visitSk) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT * FROM VISIT WHERE VISIT_SK = ?";

            preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            preparedStatement.setLong(index++, visitSk);

            resultSet = preparedStatement.executeQuery();

            List<Visit> visits = (List<Visit>)new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.Visit");

            if (visits.size() > 0) {

                Visit visit = visits.get(0);
                List<VisitException> visitExceptions = getVisitExceptions(connection, visitSk);

                if (visitExceptions != null && visitExceptions.size() > 0) {
                    visit.getVisitException().addAll(visitExceptions);
                }

                List<VisitService> serviceVisits = getServiceVisits(connection, visitSk);

                if (serviceVisits != null && serviceVisits.size() > 0) {
                    visit.getVisitService().addAll(serviceVisits);
                }

                List<VisitTaskList> visitTaskLists = getVisitTaskList(connection, visitSk);

                if (visitTaskLists != null && visitTaskLists.size() > 0) {

                    List<VisitTaskListExt> visitTaskListExtList = new ArrayList<>(visitTaskLists.size());
                    for (VisitTaskList visitTaskList : visitTaskLists) {

                        VisitTaskListExt visitTaskListExt = new VisitTaskListExt(visitTaskList);
                        visitTaskListExtList.add(visitTaskListExt);
                    }

                    visit.getVisitTaskList().addAll(visitTaskListExtList);
                }

                return visit;
            }

            return null;
        }
        catch (Exception e) {

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()) , e);

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

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }

    }

    private List<PayrollInputTaxDetail> getPayrollTaxes(Connection connection, long payrollSk) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    
        try {
            String sql = "SELECT * FROM PR_INPUT_TAX_DET WHERE PR_INPUT_SK = ?";
    
            preparedStatement = connection.prepareStatement(sql);
    
            int index = 1;
            preparedStatement.setLong(index++, payrollSk);
    
            resultSet = preparedStatement.executeQuery();
    
            List<PayrollInputTaxDetail> resultList =
                    (List<PayrollInputTaxDetail>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.PayrollInputTaxDetail");
    
            return resultList;
    
        } catch (Exception e) {

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);
    
        } finally {
    
            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
    
            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }
    
    private List<StaffVisit> getStaffVisits(Connection connection, final long payrollSk) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT * FROM (SELECT r1.* " +
                    "   FROM " +
                    "     (SELECT T1.PR_INPUT_SK AS PAYROLL_SK,T3.VISIT_SK,T1.GROSS_PAY_AMT AS TOTAL_PAY, " +
                    "              T5.PT_FIRST_NAME,T5.PT_LAST_NAME,T5.PT_ID,T2.PR_RATE AS RATE,T4.VISIT_PAY_HRS AS HOURS, " +
                    " " +
                    "              T4.VISIT_OT_ABS_HRS AS TOTAL_ADDITIONAL_PAY, " +
                    " " +
                    "              ROUND((T4.VISIT_BILL_HRS*T2.PR_RATE),2) AS TOTAL_VISIT_PAY FROM PR_INPUT T1 " +
                    " " +
                    "       LEFT JOIN (SELECT BE_ID,STAFF_ID,WEEK_END_DATE,TIMESHEET_SUMMARY_SK,PR_RATE, " +
                    "                    REC_TERM_TMSTP,CURR_REC_IND FROM PR_OUTPUT) T2 " +
                    "         ON T1.BE_ID = T2.BE_ID AND T1.STAFF_ID = T2.STAFF_ID AND T1.WEEK_END_DATE = T2.WEEK_END_DATE " +
                    "       LEFT JOIN (SELECT TIMESHEET_SUMMARY_SK,VISIT_SK FROM TIMESHEET_DET) T3 " +
                    "         ON T2.TIMESHEET_SUMMARY_SK = T3.TIMESHEET_SUMMARY_SK " +
                    "       LEFT JOIN (SELECT VISIT_SK,PT_ID,VISIT_PAY_HRS,VISIT_OT_ABS_HRS,VISIT_BILL_HRS FROM VISIT) T4 " +
                    "         ON T3.VISIT_SK = T4.VISIT_SK " +
                    "       LEFT JOIN (SELECT PT_ID,PT_FIRST_NAME,PT_LAST_NAME FROM PT) T5 " +
                    "         ON T4.PT_ID = T5.PT_ID " +
                    " " +
                    "      WHERE T1.PR_INPUT_SK = ? " +
                    ") r1) ";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setLong(index++, payrollSk);

            resultSet = preparedStatement.executeQuery();

            List<StaffVisit> staffVisits = new ArrayList<>();
            while (resultSet.next()) {

                BigDecimal visitSk = resultSet.getBigDecimal("VISIT_SK");

                if (visitSk != null) {

                    StaffVisit staffVisit = new StaffVisit();

                    BigDecimal totalPay = resultSet.getBigDecimal("TOTAL_PAY");
                    if (totalPay != null) {
                        staffVisit.setTotalPay(totalPay.longValue());
                    }

                    staffVisit.setPatientFirstName(resultSet.getString("PT_FIRST_NAME"));
                    staffVisit.setPatientLastName(resultSet.getString("PT_LAST_NAME"));
                    staffVisit.setPatientId(resultSet.getString("PT_ID"));
                    staffVisit.setRate(resultSet.getString("RATE"));

                    BigDecimal hours = resultSet.getBigDecimal("HOURS");
                    if (hours != null) {
                        staffVisit.setHours(hours.longValue());
                    }

                    BigDecimal totalAdditionalPay = resultSet.getBigDecimal("TOTAL_ADDITIONAL_PAY");
                    if (totalAdditionalPay != null) {
                        staffVisit.setTotalAdditionalPay(totalAdditionalPay.longValue());
                    }

                    BigDecimal totalVisitPay = resultSet.getBigDecimal("TOTAL_VISIT_PAY");
                    if (totalVisitPay != null) {
                        staffVisit.setTotalVisitPay(totalVisitPay.longValue());
                    }

                    // TODO: BA's researching this...
                    /*
                    BigDecimal staffAdditionalPay = resultSet.getBigDecimal("");
                    if (staffAdditionalPay != null) {
                        staffVisit.setStaffAdditionalPay(staffAdditionalPay.longValue());
                    }*/

                    staffVisit.setVisit(getVisits(connection, visitSk.longValue()));
                    staffVisits.add(staffVisit);
                }
            }

            return staffVisits;

        } catch (Exception e) {

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

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    public Response getPayrollDetail(final long payrollSk, final String businessEntityId) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String callMethod = "{?=call COREDATA.PKG_PR_UTIL.GET_PAYROLL(?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setLong(2, payrollSk);
            callableStatement.execute();

            resultSet = (ResultSet)callableStatement.getObject(1);

            if(resultSet.next()) {

                PayrollDetail payrollDetail = new PayrollDetail();

                Response response = new Response();
                response.setData(payrollDetail);

                payrollDetail.setPayrollSk(payrollSk);
                payrollDetail.setPayrollId(resultSet.getString("TIMESHEET_SUMMARY_ID"));

                Timestamp payEndDateTmstp = resultSet.getTimestamp("TIMESHEET_WEEK_END_DATE");
                if (payEndDateTmstp != null) {
                    payrollDetail.setPayEndDate(new Date(payEndDateTmstp.getTime()));
                }

                payrollDetail.setStaffFirstName(resultSet.getString("STAFF_FIRST_NAME"));
                payrollDetail.setStaffLastName(resultSet.getString("STAFF_LAST_NAME"));
                payrollDetail.setStaffId(resultSet.getString("STAFF_ID"));

                BigDecimal payHours = resultSet.getBigDecimal("TIMESHEET_WEEK_TOTAL_HRS");
                if (payHours != null) {
                    payrollDetail.setPayHours(TimeUtil.SecondsToHours(payHours.longValue()));
                }

                payrollDetail.setCheck(resultSet.getString("CHECK_NUM"));

                payrollDetail.setCheckAmount(resultSet.getBigDecimal("GROSS_PAY_AMT"));

                Timestamp checkDate = resultSet.getTimestamp("CHECK_DATE");
                if (checkDate != null) {
                    payrollDetail.setCheckDate(new Date(checkDate.getTime()));
                    payrollDetail.setStatus("PAID");
                }

                //dmr GEOR-2814: Voucher # -> PR_INPUT.EFT_TXN_ID
                payrollDetail.setVoucherId(resultSet.getString("EFT_TXN_ID"));

                // Get Staff Visits
                List<com.sandata.lab.rest.payroll.model.StaffVisit> staffVisitList = new ArrayList<>();
                payrollDetail.setStaffVisits(staffVisitList);
                Object timesheetDetCursor = resultSet.getObject("TIMESHEET_DET_CURSOR");
                if (timesheetDetCursor != null && timesheetDetCursor instanceof ResultSet) {

                    ResultSet tdcResultSet = (ResultSet) timesheetDetCursor;
                    while (tdcResultSet.next()) {

                        com.sandata.lab.rest.payroll.model.StaffVisit staffVisit = new com.sandata.lab.rest.payroll.model.StaffVisit();
                        staffVisit.setPatientFirstName(tdcResultSet.getString("PT_FIRST_NAME"));
                        staffVisit.setPatientLastName(tdcResultSet.getString("PT_LAST_NAME"));
                        staffVisit.setPatientId(tdcResultSet.getString("PT_ID"));
                        BigDecimal payrollHoursInSeconds = tdcResultSet.getBigDecimal("PR_HRS");
                        if (payrollHoursInSeconds != null) {
                            staffVisit.setHours(TimeUtil.SecondsToHours(payrollHoursInSeconds.longValue()));
                        }

                        BigDecimal payrollRateAmount = tdcResultSet.getBigDecimal("PR_RATE_AMT");
                        if (payrollRateAmount != null) {
                            staffVisit.setRate(payrollRateAmount.setScale(2, RoundingMode.CEILING));
                        }

                        BigDecimal visitSk = tdcResultSet.getBigDecimal("VISIT_SK");
                        if (visitSk != null) {
                            staffVisit.setVisit(getVisits(connection, visitSk.longValue()));
                        }

                        staffVisit.setPayrollCode(tdcResultSet.getString("PR_CODE"));

                        Timestamp timesheetDate = tdcResultSet.getTimestamp("TIMESHEET_DATE");
                        if (timesheetDate != null) {
                            staffVisit.setTimesheetDate(new Date(timesheetDate.getTime()));
                        }

                        BigDecimal payrollAmount = tdcResultSet.getBigDecimal("PR_AMT");
                        if (payrollAmount != null) {
                            staffVisit.setPayrollAmount(payrollAmount.setScale(2, RoundingMode.CEILING));
                        }

                        staffVisit.setService(tdcResultSet.getString("SVC_NAME"));

                        staffVisit.setRateTypeName(tdcResultSet.getString("RATE_TYP_NAME"));

                        staffVisit.initTotalPay();

                        staffVisitList.add(staffVisit);
                    }

                    tdcResultSet.close();
                }

                //dmr GEOR-2814: Federal Tax / State Tax / City Tax / Other Tax > PR_INPUT_TAX_DETL
                //payrollDetail.setTaxes(getPayrollTaxes(connection, payrollSk));

                return response;
            }

            return null;

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

            // Close the statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    public Object getEntitiesForId(final String sql, final String className, final Object... params) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (Object object : params) {
                preparedStatement.setObject(index++, object);
            }

            resultSet = preparedStatement.executeQuery();

            Object result = new DataMapper().map(resultSet, className);

            connection.commit();

            return result;

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

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    public long[] updatePrRateMatrixExchange(
            Connection connection,
            PayrollRateMatrixExchange payrollRateMatrixExchange,
            SandataLogger logger) throws SandataRuntimeException {

        validatePayrollRateMatrixExchange(payrollRateMatrixExchange);

        long[] result = new long[3];

        try {
            logger.info(String.format("%s: updatePrRateMatrixExchange: [BE_ID:%s]: [PAYER_ID=%s]: [CONTR_ID=%s]: %s",
                    getClass().getName(),
                    payrollRateMatrixExchange.getBusinessEntityID(),
                    payrollRateMatrixExchange.getPayerID(),
                    payrollRateMatrixExchange.getContractID(),
                    payrollRateMatrixExchange.toString()));

            PayrollRateMatrixRequest request = new PayrollRateMatrixRequest();
            request.setBusinessEntityId(payrollRateMatrixExchange.getBusinessEntityID());
            request.setContractId(payrollRateMatrixExchange.getContractID());
            request.setPayerId(payrollRateMatrixExchange.getPayerID());
            request.setServiceName(payrollRateMatrixExchange.getServiceName());
            request.setRateTypeName(payrollRateMatrixExchange.getRateTypeName());

            List<PayrollRateMatrix> payrollRateMatrixList = getPayrollRateMatrix(request);
            if (payrollRateMatrixList.size() != 3) {
                logger.warn(String.format("updatePrRateMatrixExchange: PayrollRateMatrixExchange does not exist!: [%s]", payrollRateMatrixExchange.toString()));
                return new long[0];
            }

            int index = 0;
            for (PayrollRateMatrix payrollRateMatrix : payrollRateMatrixList) {
                payrollRateMatrix.setBusinessEntityID(payrollRateMatrixExchange.getBusinessEntityID());
                payrollRateMatrix.setBusinessEntityLocationID(payrollRateMatrixExchange.getBusinessEntityLocationID());
                payrollRateMatrix.setPayerID(payrollRateMatrixExchange.getPayerID());
                payrollRateMatrix.setContractID(payrollRateMatrixExchange.getContractID());
                payrollRateMatrix.setPayrollRateMatrixEffectiveDate(payrollRateMatrixExchange.getEffectiveDate());
                payrollRateMatrix.setServiceName(payrollRateMatrixExchange.getServiceName());
                payrollRateMatrix.setRateTypeName(payrollRateMatrixExchange.getRateTypeName());
                payrollRateMatrix.setPayrollRateMatrixNote(payrollRateMatrixExchange.getPayrollRateMatrixNote());
                payrollRateMatrix.setPayrollCode(payrollRateMatrixExchange.getPayrollCode());

                if (payrollRateMatrix.getRateSubTypeName() == RateSubTypeName.REGULAR) {
                    payrollRateMatrix.setRateAmount(payrollRateMatrixExchange.getRegularRateAmount());
                    payrollRateMatrix.setServiceUnitName(payrollRateMatrixExchange.getRegularServiceUnitName());

                } else if (payrollRateMatrix.getRateSubTypeName() == RateSubTypeName.WEEKEND) {
                    payrollRateMatrix.setRateAmount(payrollRateMatrixExchange.getWeekendRateAmount());
                    payrollRateMatrix.setServiceUnitName(payrollRateMatrixExchange.getWeekendServiceUnitName());

                } else if (payrollRateMatrix.getRateSubTypeName() == RateSubTypeName.HOLIDAY) {
                    payrollRateMatrix.setRateAmount(payrollRateMatrixExchange.getHolidayRateAmount());
                    payrollRateMatrix.setServiceUnitName(payrollRateMatrixExchange.getHolidayServiceUnitName());
                }

                result[index++] = updatePrRateMatrix(connection, payrollRateMatrix);
            }
        }
        catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);
        }

        return result;
    }

    public long[] insertPrRateMatrixExchange(
            Connection connection,
            PayrollRateMatrixExchange payrollRateMatrixExchange,
            SandataLogger logger) throws SandataRuntimeException {

        validatePayrollRateMatrixExchange(payrollRateMatrixExchange);

        int count = getPrRateMatrixExchangeCount(
                        connection,
                        payrollRateMatrixExchange.getBusinessEntityID(),
                        payrollRateMatrixExchange.getPayerID(),
                        payrollRateMatrixExchange.getContractID(),
                        payrollRateMatrixExchange.getServiceName(),
                        payrollRateMatrixExchange.getRateTypeName()
                );

        if (count == 3) {
            throw new SandataRuntimeException(String.format("insertPrRateMatrixExchange: PayrollRateMatrixExchange already exists!: [%s]", payrollRateMatrixExchange.toString()));
        }

        if (count < 0 || count > 3) {
            throw new SandataRuntimeException(String.format("insertPrRateMatrixExchange: PayrollRateMatrixExchange: ILLEGAL PayrollRateMatrix [Count=%d]: [%s]", count, payrollRateMatrixExchange.toString()));
        }

        long[] result = new long[3];

        try {
            logger.info(String.format("%s: insertPrRateMatrixExchange: [BE_ID:%s]: [PAYER_ID=%s]: [CONTR_ID=%s]: %s",
                    getClass().getName(),
                    payrollRateMatrixExchange.getBusinessEntityID(),
                    payrollRateMatrixExchange.getPayerID(),
                    payrollRateMatrixExchange.getContractID(),
                    payrollRateMatrixExchange.toString()));

            // REGULAR
            PayrollRateMatrix regularPrRateMatrix = createPrRateMatrix(payrollRateMatrixExchange,
                    "Payroll: OracleDataService: insertPrRateMatrixExchange: REGULAR");
            regularPrRateMatrix.setRateSubTypeName(RateSubTypeName.REGULAR);
            regularPrRateMatrix.setRateAmount(payrollRateMatrixExchange.getRegularRateAmount());
            regularPrRateMatrix.setServiceUnitName(payrollRateMatrixExchange.getRegularServiceUnitName());
            regularPrRateMatrix.setPayrollCode(payrollRateMatrixExchange.getPayrollCode());
            result[0] = insertPrRateMatrix(connection, regularPrRateMatrix);

            // WEEKEND
            PayrollRateMatrix weekendPrRateMatrix = createPrRateMatrix(payrollRateMatrixExchange,
                    "Payroll: OracleDataService: insertPrRateMatrixExchange: WEEKEND");
            weekendPrRateMatrix.setRateSubTypeName(RateSubTypeName.WEEKEND);
            weekendPrRateMatrix.setRateAmount(payrollRateMatrixExchange.getWeekendRateAmount());
            weekendPrRateMatrix.setServiceUnitName(payrollRateMatrixExchange.getWeekendServiceUnitName());
            weekendPrRateMatrix.setPayrollCode(payrollRateMatrixExchange.getPayrollCode());
            result[1] = insertPrRateMatrix(connection, weekendPrRateMatrix);

            // HOLIDAY
            PayrollRateMatrix holidayPrRateMatrix = createPrRateMatrix(payrollRateMatrixExchange,
                    "Payroll: OracleDataService: insertPrRateMatrixExchange: HOLIDAY");
            holidayPrRateMatrix.setRateSubTypeName(RateSubTypeName.HOLIDAY);
            holidayPrRateMatrix.setRateAmount(payrollRateMatrixExchange.getHolidayRateAmount());
            holidayPrRateMatrix.setServiceUnitName(payrollRateMatrixExchange.getHolidayServiceUnitName());
            holidayPrRateMatrix.setPayrollCode(payrollRateMatrixExchange.getPayrollCode());
            result[2] = insertPrRateMatrix(connection, holidayPrRateMatrix);
        }
        catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);
        }

        return result;
    }

    private long insertPrRateMatrix(Connection connection, PayrollRateMatrix payrollRateMatrix) throws SandataRuntimeException {

        OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(payrollRateMatrix);
        PrRateMatrixT jpubObj = (PrRateMatrixT)new DataMapper().map(payrollRateMatrix);
        // Execute Insert
        return execute(connection, ConnectionType.COREDATA, oracleMetadata.packageName(), oracleMetadata.insertMethod(), jpubObj);
    }

    private long updatePrRateMatrix(Connection connection, PayrollRateMatrix payrollRateMatrix) throws SandataRuntimeException {

        OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(payrollRateMatrix);
        PrRateMatrixT jpubObj = (PrRateMatrixT)new DataMapper().map(payrollRateMatrix);
        // Execute Update
        long result = execute(connection, ConnectionType.COREDATA, oracleMetadata.packageName(), oracleMetadata.updateMethod(), jpubObj);
        if (result < 0) {
            throw new SandataRuntimeException("updatePrRateMatrix: ERROR: result < 0");
        }

        return payrollRateMatrix.getPayrollRateMatrixSK().longValue();
    }

    private PayrollRateMatrix createPrRateMatrix(
                PayrollRateMatrixExchange payrollRateMatrixExchange,
                String changReasonMemo) throws ParseException {

        Date termDate = new SimpleDateFormat("yyyy-MM-dd").parse("9999-12-31");

        PayrollRateMatrix prRateMatrix = new PayrollRateMatrix();

        prRateMatrix.setRecordCreateTimestamp(new Date());
        prRateMatrix.setRecordUpdateTimestamp(new Date());
        prRateMatrix.setRecordEffectiveTimestamp(new Date());
        prRateMatrix.setRecordTerminationTimestamp(termDate);
        prRateMatrix.setCurrentRecordIndicator(true);
        prRateMatrix.setChangeVersionID(BigInteger.ZERO);

        prRateMatrix.setRecordCreatedBy("Middleware Service");
        prRateMatrix.setChangeReasonMemo(changReasonMemo);

        prRateMatrix.setBusinessEntityID(payrollRateMatrixExchange.getBusinessEntityID());
        prRateMatrix.setPayerID(payrollRateMatrixExchange.getPayerID());
        prRateMatrix.setContractID(payrollRateMatrixExchange.getContractID());
        prRateMatrix.setPayrollRateMatrixEffectiveDate(payrollRateMatrixExchange.getEffectiveDate());
        prRateMatrix.setServiceName(payrollRateMatrixExchange.getServiceName());
        prRateMatrix.setRateTypeName(payrollRateMatrixExchange.getRateTypeName());
        prRateMatrix.setBusinessEntityLocationID(payrollRateMatrixExchange.getBusinessEntityLocationID());
        prRateMatrix.setRateQualifierCode(RateQualifierCode.PAY);
        prRateMatrix.setPayrollRateMatrixNote(payrollRateMatrixExchange.getPayrollRateMatrixNote());
        prRateMatrix.setPayrollRateMatrixTerminationDate(termDate);

        return prRateMatrix;
    }

    private void validatePayrollRateMatrixExchange(PayrollRateMatrixExchange payrollRateMatrixExchange) throws SandataRuntimeException {

        if (StringUtil.IsNullOrEmpty(payrollRateMatrixExchange.getBusinessEntityID())) {
            throw new SandataRuntimeException("validatePayrollRateMatrixExchange: payrollRateMatrixExchange.getBusinessEntityID() is NULL or EMPTY!");
        }

        if (StringUtil.IsNullOrEmpty(payrollRateMatrixExchange.getBusinessEntityLocationID())) {
            throw new SandataRuntimeException("validatePayrollRateMatrixExchange: payrollRateMatrixExchange.getBusinessEntityLocationID() is NULL or EMPTY!");
        }

        if (StringUtil.IsNullOrEmpty(payrollRateMatrixExchange.getPayerID())) {
            throw new SandataRuntimeException("validatePayrollRateMatrixExchange: payrollRateMatrixExchange.getPayerID() is NULL or EMPTY!");
        }

        if (StringUtil.IsNullOrEmpty(payrollRateMatrixExchange.getContractID())) {
            throw new SandataRuntimeException("validatePayrollRateMatrixExchange: payrollRateMatrixExchange.getContractID() is NULL or EMPTY!");
        }

        if (payrollRateMatrixExchange.getEffectiveDate() == null) {
            throw new SandataRuntimeException("validatePayrollRateMatrixExchange: payrollRateMatrixExchange.getEffectiveDate() is NULL!");
        }

        if (payrollRateMatrixExchange.getServiceName() == null) {
            throw new SandataRuntimeException("validatePayrollRateMatrixExchange: payrollRateMatrixExchange.getServiceName() is NULL!");
        }

        if (payrollRateMatrixExchange.getRateTypeName() == null) {
            throw new SandataRuntimeException("validatePayrollRateMatrixExchange: payrollRateMatrixExchange.getRateTypeName() is NULL!");
        }

        if (payrollRateMatrixExchange.getPayrollCode() == null) {
            throw new SandataRuntimeException("validatePayrollRateMatrixExchange: payrollRateMatrixExchange.getPayrollCode() is NULL!");
        }

        // REGULAR
        if (payrollRateMatrixExchange.getRegularRateAmount() == null
                || payrollRateMatrixExchange.getRegularRateAmount().doubleValue() <= 0) {

            throw new SandataRuntimeException("validatePayrollRateMatrixExchange: payrollRateMatrixExchange.getRegularRateAmount() is NULL or <= 0");
        }

        if (payrollRateMatrixExchange.getRegularServiceUnitName() == null) {
            throw new SandataRuntimeException("validatePayrollRateMatrixExchange: payrollRateMatrixExchange.getRegularServiceUnitName() is NULL!");
        }

        // WEEKEND
        if (payrollRateMatrixExchange.getWeekendRateAmount() == null
                || payrollRateMatrixExchange.getWeekendRateAmount().doubleValue() <= 0) {

            throw new SandataRuntimeException("validatePayrollRateMatrixExchange: payrollRateMatrixExchange.getWeekendRateAmount() is NULL or <= 0");
        }

        if (payrollRateMatrixExchange.getWeekendServiceUnitName() == null) {
            throw new SandataRuntimeException("validatePayrollRateMatrixExchange: payrollRateMatrixExchange.getWeekendServiceUnitName() is NULL!");
        }

        // HOLIDAY
        if (payrollRateMatrixExchange.getHolidayRateAmount() == null
                || payrollRateMatrixExchange.getHolidayRateAmount().doubleValue() <= 0) {

            throw new SandataRuntimeException("validatePayrollRateMatrixExchange: payrollRateMatrixExchange.getHolidayRateAmount() is NULL or <= 0");
        }

        if (payrollRateMatrixExchange.getHolidayServiceUnitName() == null) {
            throw new SandataRuntimeException("validatePayrollRateMatrixExchange: payrollRateMatrixExchange.getHolidayServiceUnitName() is NULL!");
        }
    }

    public List<PayrollRateMatrixExchange> getPrRateMatrixExchange(
            PayrollRateMatrixRequest request, SandataLogger logger) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        logger.trace(String.format("%s: getPrRateMatrixExchange: START",
                        getClass().getName())
        );

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String serviceNameFilter = "";
            if (request.getServiceName() != null) {
                serviceNameFilter = "AND SVC_NAME = ?";
            }

            String rateTypeNameFilter = "";
            if (request.getRateTypeName() != null) {
                rateTypeNameFilter = "AND RATE_TYP_NAME = ?";
            }

            String sql = String.format("SELECT * FROM PR_RATE_MATRIX " +
                    "WHERE BE_ID = ? " +
                    "  AND PAYER_ID = ? " +
                    "  AND CONTR_ID = ? " +
                    "  %s " +
                    "  %s " +
                    "  AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",

                    serviceNameFilter,
                    rateTypeNameFilter
            );

            logger.debug(String.format("%s: getPrRateMatrixExchange: [SQL=%s]",
                    getClass().getName(),
                    sql)
            );

            logger.debug(String.format("%s: getPrRateMatrixExchange: [Request Params=%s]",
                    getClass().getName(),
                    request.toString())
            );

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, request.getBusinessEntityId());
            preparedStatement.setString(index++, request.getPayerId());
            preparedStatement.setString(index++, request.getContractId());

            if (request.getServiceName() != null) {
                preparedStatement.setString(index++, request.getServiceName().value());
            }

            if (!StringUtil.IsNullOrEmpty(request.getRateTypeName())) {
                preparedStatement.setString(index++, request.getRateTypeName().toUpperCase());
            }

            resultSet = preparedStatement.executeQuery();

            List<PayrollRateMatrix> resultList =
                    (List<PayrollRateMatrix>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.PayrollRateMatrix");

            // There are no records that match the query
            if (resultList.size() == 0) {
                logger.info(String.format("getPrRateMatrixExchange: No data found! [SQL=%s]", sql));
                return null;
            }

            //dmr-GEOR-5311
            /*if (resultList.size() != 3) {
                throw new SandataRuntimeException(String.format("getPrRateMatrixExchange: List<PayrollRateMatrix> Size=%d: Expected Size=3", resultList.size()));
            }*/

            Map<String, PayrollRateMatrixExchange> payrollRateMatrixExchangeMap = new HashMap<>();
            for (PayrollRateMatrix payrollRateMatrix : resultList) {

                String key = String.format("%s-%s", payrollRateMatrix.getRateTypeName(), payrollRateMatrix.getServiceName());
                PayrollRateMatrixExchange payrollRateMatrixExchange = payrollRateMatrixExchangeMap.get(key);
                if (payrollRateMatrixExchange == null) {
                    payrollRateMatrixExchange = new PayrollRateMatrixExchange();
                    payrollRateMatrixExchangeMap.put(key, payrollRateMatrixExchange);
                }

                switch(payrollRateMatrix.getRateSubTypeName()) {
                    case REGULAR:
                        payrollRateMatrixExchange.setRegularPayrollRateMatrixSK(payrollRateMatrix.getPayrollRateMatrixSK());
                        payrollRateMatrixExchange.setRegularChangeVersionID(payrollRateMatrix.getChangeVersionID());
                        payrollRateMatrixExchange.setRegularRateAmount(payrollRateMatrix.getRateAmount());
                        payrollRateMatrixExchange.setRegularServiceUnitName(payrollRateMatrix.getServiceUnitName());
                        payrollRateMatrixExchange.setPayrollCode(payrollRateMatrix.getPayrollCode());
                        break;
                    case WEEKEND:
                        payrollRateMatrixExchange.setWeekendPayrollRateMatrixSK(payrollRateMatrix.getPayrollRateMatrixSK());
                        payrollRateMatrixExchange.setWeekendChangeVersionID(payrollRateMatrix.getChangeVersionID());
                        payrollRateMatrixExchange.setWeekendRateAmount(payrollRateMatrix.getRateAmount());
                        payrollRateMatrixExchange.setWeekendServiceUnitName(payrollRateMatrix.getServiceUnitName());
                        payrollRateMatrixExchange.setPayrollCode(payrollRateMatrix.getPayrollCode());
                        break;
                    case HOLIDAY:
                        payrollRateMatrixExchange.setHolidayPayrollRateMatrixSK(payrollRateMatrix.getPayrollRateMatrixSK());
                        payrollRateMatrixExchange.setHolidayChangeVersionID(payrollRateMatrix.getChangeVersionID());
                        payrollRateMatrixExchange.setHolidayRateAmount(payrollRateMatrix.getRateAmount());
                        payrollRateMatrixExchange.setHolidayServiceUnitName(payrollRateMatrix.getServiceUnitName());
                        payrollRateMatrixExchange.setPayrollCode(payrollRateMatrix.getPayrollCode());
                        break;
                }

                // Init is run once for common properties
                if (StringUtil.IsNullOrEmpty(payrollRateMatrixExchange.getBusinessEntityID())) {
                    payrollRateMatrixExchange.setBusinessEntityID(payrollRateMatrix.getBusinessEntityID());
                    payrollRateMatrixExchange.setBusinessEntityLocationID(payrollRateMatrix.getBusinessEntityLocationID());
                    payrollRateMatrixExchange.setPayrollRateMatrixNote(payrollRateMatrix.getPayrollRateMatrixNote());
                    payrollRateMatrixExchange.setPayerID(payrollRateMatrix.getPayerID());
                    payrollRateMatrixExchange.setContractID(payrollRateMatrix.getContractID());
                    payrollRateMatrixExchange.setEffectiveDate(payrollRateMatrix.getPayrollRateMatrixEffectiveDate());
                    payrollRateMatrixExchange.setServiceName(payrollRateMatrix.getServiceName());
                    payrollRateMatrixExchange.setRateTypeName(payrollRateMatrix.getRateTypeName());
                }
            }

            return new ArrayList<>(payrollRateMatrixExchangeMap.values());

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
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
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the connection
            this.connectionPoolDataService.close(connection);

            logger.trace(String.format("%s: getPrRateMatrixExchange: END",
                    getClass().getName())
            );
        }
    }

    private List<PayrollRateMatrix> getPayrollRateMatrix(PayrollRateMatrixRequest request) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM PR_RATE_MATRIX " +
                    "WHERE BE_ID = ? " +
                    "  AND PAYER_ID = ? " +
                    "  AND CONTR_ID = ? " +
                    "  AND SVC_NAME = ? " +
                    "  AND RATE_TYP_NAME = ? " +
                    "  AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, request.getBusinessEntityId());
            preparedStatement.setString(index++, request.getPayerId());
            preparedStatement.setString(index++, request.getContractId());
            preparedStatement.setString(index++, request.getServiceName().value());
            preparedStatement.setString(index++, request.getRateTypeName().toUpperCase());

            resultSet = preparedStatement.executeQuery();

            List<PayrollRateMatrix> resultList =
                    (List<PayrollRateMatrix>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.PayrollRateMatrix");


            return resultList;

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
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
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    public long deletePrRateMatrixExchange(PayrollRateMatrixRequest request) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        
        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String callMethod = "{?=call PKG_PR_UTIL.DELETE_PR_RATE_MATRIX(?,?,?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);

            int index = 2;
            callableStatement.setString(index++, request.getBusinessEntityId());
            callableStatement.setString(index++, request.getPayerId());
            callableStatement.setString(index++, request.getContractId());
            callableStatement.setString(index++, request.getServiceName().value());
            callableStatement.setString(index++, request.getRateTypeName().toUpperCase());

            callableStatement.execute();
            long result = callableStatement.getLong(1);

            connection.commit();

            return result;

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    private int getPrRateMatrixExchangeCount(
            Connection connection, String bsnEntId, String payerId, String contractId, ServiceName serviceName,
            String rateTypeName) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT COUNT(*) AS TOTAL FROM PR_RATE_MATRIX " +
                    "WHERE BE_ID = ? " +
                    "  AND PAYER_ID = ? " +
                    "  AND CONTR_ID = ? " +
                    "  AND SVC_NAME = ? " +
                    "  AND RATE_TYP_NAME = ? " +
                    "  AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, payerId);
            preparedStatement.setString(index++, contractId);
            preparedStatement.setString(index++, serviceName.value());
            preparedStatement.setString(index++, rateTypeName.toUpperCase());

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("TOTAL");
            }

         return 0;

        } catch (Exception e) {

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }
}
