package com.sandata.lab.payroll.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.logger.api.LoggerService;
import com.sandata.lab.payroll.api.OracleService;
import com.sandata.lab.payroll.model.PayrollGetStaffRateParams;
import com.sandata.lab.payroll.model.StaffPayrollRates;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;

@SuppressWarnings("unchecked")
public class OracleDataService implements OracleService {

    private ConnectionPoolDataService connectionPoolDataService;

    private LoggerService loggerService;

    public Connection getOracleConnection() throws SandataRuntimeException {
        return connectionPoolDataService.getConnection();
    }

    public ConnectionPoolDataService getConnectionPoolDataService() {
        return connectionPoolDataService;
    }

    public void setConnectionPoolDataService(ConnectionPoolDataService connectionPoolDataService) {
        this.connectionPoolDataService = connectionPoolDataService;
    }

    public LoggerService getLoggerService() {
        return loggerService;
    }

    public void setLoggerService(LoggerService loggerService) {
        this.loggerService = loggerService;
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
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
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
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
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
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setLong(2, sequenceKey);
            callableStatement.execute();
            int result = (Integer)callableStatement.getObject(1);

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
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
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
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setObject(2, jpubType);
            callableStatement.execute();
            int result = (Integer)callableStatement.getObject(1);

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
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    @Override
    public long execute(Connection connection, String packageName, String methodName, Object jpubType) throws SandataRuntimeException {

        CallableStatement callableStatement = null;

        try {

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setObject(2, jpubType);
            callableStatement.execute();
            int result = (Integer)callableStatement.getObject(1);
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
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    public int executeUpdate(String sql, Object... params) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (Object object : params) {
                preparedStatement.setObject(index++, object);
            }

            int resultValue = preparedStatement.executeUpdate();
            if (resultValue < 0) {

                throw new SandataRuntimeException(String.format("resultValue [%d] < 0", resultValue));
            }

            connection.commit();

            return resultValue;

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
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    /**
     * Executes Oracle function with list of params
     *
     * @param callMethodString
     * @param params
     * @return
     * @throws SandataRuntimeException
     */
    public int executeFunction(String callMethodString, Object... params) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            callableStatement = connection.prepareCall(callMethodString);

            int index = 1;

            callableStatement.registerOutParameter(index++, OracleTypes.INTEGER);
            for (Object object : params) {
                callableStatement.setObject(index++, object);
            }

            callableStatement.execute();
            int result = callableStatement.getInt(1);

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
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    public int executeCount(String sql, String countColumnName, Object... params) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (Object object : params) {
                preparedStatement.setObject(index++, object);
            }

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                BigDecimal countValue = resultSet.getBigDecimal(countColumnName);
                return countValue.intValue();
            }

            return 0;

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
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    public BigInteger getTimeSheetSummarySK(String staffId, String bsnEntId, java.util.Date weekEndDate) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT TIMESHEET_SUMMARY_SK FROM TIMESHEET_SUMMARY WHERE STAFF_ID = ? AND BE_ID = ? " +
                    " AND TIMESHEET_WEEK_END_DATE = TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, staffId);
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, dateFormat.format(weekEndDate));

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                BigDecimal timesheetSummarySK = resultSet.getBigDecimal("TIMESHEET_SUMMARY_SK");
                if (timesheetSummarySK != null) {
                    return BigInteger.valueOf(timesheetSummarySK.longValue());
                }
            }

            return null;

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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    public StaffPayrollRates getStaffPayrollRates(
            Connection connection,
            String bsnEntId,
            String bsnLocId,
            String bsnLobId,
            String patientId,
            String staffId,
            long visitSk,
            java.util.Date visitDate,
            SandataLogger logger) throws SandataRuntimeException {


        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        logger.trace(String.format("%s: getStaffPayrollRates: START",
                getClass().getName())
        );

        StaffPayrollRates staffPayrollRates = new StaffPayrollRates(bsnEntId, bsnLocId, bsnLobId, staffId);

        logger.debug(String.format("%s: getStaffPayrollRates: [StaffPayrollRates=%s]", getClass().getName(), staffPayrollRates.toString()));

        try {

            PayrollGetStaffRateParams payrollGetStaffRateParams = new PayrollGetStaffRateParams();
            payrollGetStaffRateParams.setBusinessEntityID(bsnEntId);
            payrollGetStaffRateParams.setBusinessEntityLineOfBusinessID(bsnLobId);
            payrollGetStaffRateParams.setBusinessEntityLocationID(bsnLocId);
            payrollGetStaffRateParams.setPatientID(patientId);
            payrollGetStaffRateParams.setStaffID(staffId);
            payrollGetStaffRateParams.setVisitSK(BigDecimal.valueOf(visitSk));
            payrollGetStaffRateParams.setVisitDate(visitDate);

            logger.debug(String.format("%s: getStaffPayrollRates: [PayrollGetStaffRateParams=%s]", getClass().getName(), payrollGetStaffRateParams.toString()));

            Object payrollGetStaffRateParamsObj = new DataMapper().map(payrollGetStaffRateParams);

            String callMethod = "{?=call COREDATA.PKG_PR_UTIL.GET_STAFF_RATES(?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setObject(2, payrollGetStaffRateParamsObj);
            callableStatement.execute();

            resultSet = (ResultSet)callableStatement.getObject(1);

            logger.trace(String.format("%s: getStaffPayrollRates: After resultSet...", getClass().getName()));

            if (resultSet.next()) {

                logger.trace(String.format("%s: getStaffPayrollRates: resultSet.next()... ", getClass().getName()));

                Object beRateCursor = resultSet.getObject("BE_RATE_CURSOR");
                ResultSet beRateCursorResultSet = (ResultSet) beRateCursor;
                List<BusinessEntityRate> businessEntityRateList =
                        (List<BusinessEntityRate>) new DataMapper().map(beRateCursorResultSet, "com.sandata.lab.data.model.dl.model.BusinessEntityRate");

                if (businessEntityRateList.size() > 0) {
                    staffPayrollRates.setBusinessEntityRate(businessEntityRateList.get(0));
                }

                beRateCursorResultSet.close();

                Object staffSupplRateCursor = resultSet.getObject("STAFF_SUPPL_RATE_CURSOR");
                ResultSet staffSupplRateCursorResultSet = (ResultSet) staffSupplRateCursor;
                List<StaffSupplementalRate> staffSupplementalRateList =
                        (List<StaffSupplementalRate>) new DataMapper().map(staffSupplRateCursorResultSet, "com.sandata.lab.data.model.dl.model.StaffSupplementalRate");

                if (staffSupplementalRateList.size() > 0) {
                    staffPayrollRates.setStaffSupplementalRate(staffSupplementalRateList.get(0));
                }

                staffSupplRateCursorResultSet.close();

                Object staffAssocRateCursor = resultSet.getObject("STAFF_ASSOC_RATE_CURSOR");
                ResultSet staffAssocRateCursorResultSet = (ResultSet) staffAssocRateCursor;
                List<StaffAssociatedRate> staffAssociatedRateList =
                        (List<StaffAssociatedRate>) new DataMapper().map(staffAssocRateCursorResultSet, "com.sandata.lab.data.model.dl.model.StaffAssociatedRate");

                if (staffAssociatedRateList.size() > 0) {
                    staffPayrollRates.setStaffAssociatedRate(staffAssociatedRateList.get(0));
                }

                staffAssocRateCursorResultSet.close();

                Object payrollRateMatrixCursor = resultSet.getObject("PR_RATE_MATRIX_CURSOR");
                ResultSet payrollRateMatrixCursorResultSet = (ResultSet) payrollRateMatrixCursor;
                List<PayrollRateMatrix> payrollRateMatrixList =
                        (List<PayrollRateMatrix>) new DataMapper().map(payrollRateMatrixCursorResultSet, "com.sandata.lab.data.model.dl.model.PayrollRateMatrix");

                if (payrollRateMatrixList.size() > 0) {
                    staffPayrollRates.setPayrollRateMatrix(payrollRateMatrixList.get(0));
                }

                payrollRateMatrixCursorResultSet.close();

                List<StaffRate> staffRateList =
                        (List<StaffRate>) new DataMapper().mapWithOffsetNext(resultSet, "com.sandata.lab.data.model.dl.model.StaffRate", 4);

                if (staffRateList.size() > 0) {
                    staffPayrollRates.setStaffRate(staffRateList.get(0));
                }

                return staffPayrollRates;
            }
            else {
                logger.trace(String.format("%s: getStaffPayrollRates: resultSet: No data!", getClass().getName()));
            }

            return null;

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
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            logger.trace(String.format("%s: getStaffPayrollRates: END",
                    getClass().getName())
            );
        }
    }
}
