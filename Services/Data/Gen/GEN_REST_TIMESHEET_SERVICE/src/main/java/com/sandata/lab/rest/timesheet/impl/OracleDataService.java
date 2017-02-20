package com.sandata.lab.rest.timesheet.impl;

import com.sandata.lab.common.utils.date.DateUtil;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.dl.model.Invoice;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.logger.api.LoggerService;
import com.sandata.lab.rest.timesheet.api.OracleService;
import com.sandata.lab.rest.timesheet.utils.data.DataMapper;
import com.sandata.lab.rest.timesheet.utils.log.OracleDataLogger;
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
    public void closeOracleConnection(Connection connection) throws SandataRuntimeException {
        this.connectionPoolDataService.close(connection);
    }

    public LoggerService getLoggerService() {
        return loggerService;
    }

    public void setLoggerService(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    public Response getInvoiceForNumber(final String invoiceNumber) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        SandataLogger logger = OracleDataLogger.CreateLogger();

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM INVOICE WHERE INVOICE_NUM = ? AND " +
                            " (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

            Response response = new Response();

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index, invoiceNumber);

            resultSet = preparedStatement.executeQuery();

            List<Invoice> invoices = (List<Invoice>)
                                new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.Invoice");

            response.setData(invoices);

            connection.commit();

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

            logger.stop();
        }
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
    public long execute(Connection connection, String packageName, String methodName, Object jpubType) throws SandataRuntimeException {

        CallableStatement callableStatement = null;

        try {

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
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

    public BigInteger getTimeSheetSummarySK(String staffId, String bsnEntId, java.util.Date weekEndDate, SandataLogger logger) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            logger.debug(String.format("%s: getTimeSheetSummarySK: START", getClass().getName()));

            if (DateUtil.IsNull(weekEndDate)) {

                String errMsg = String.format("%s: EXCEPTION: weekEndDate == null");
                logger.error(errMsg);
                throw new SandataRuntimeException(errMsg);
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            logger.trace(String.format("%s: getTimeSheetSummarySK: [STAFF_ID: %s]: [BE_ID=%s]: [WEEK_END_DATE=%s]",
                        getClass().getName(), staffId, bsnEntId, dateFormat.format(weekEndDate)));


            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT TIMESHEET_SUMMARY_SK FROM TIMESHEET_SUMMARY WHERE STAFF_ID = ? AND BE_ID = ? " +
                    " AND TIMESHEET_WEEK_END_DATE = TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')";

            logger.trace(String.format("%s: getTimeSheetSummarySK: [SQL: %s]", getClass().getName(), sql));

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

            String errMsg = String.format("%s: getTimeSheetSummarySK: EXCEPTION: %s", e.getClass().getName(), e.getMessage());
            logger.error(errMsg);
            throw new SandataRuntimeException(errMsg, e);

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

            logger.debug(String.format("%s: getTimeSheetSummarySK: END", getClass().getName()));
        }
    }

    public BigInteger getTimeSheetDetailSK(long visitSk, String payrollCode, java.util.Date timesheetDate, SandataLogger logger) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            logger.debug(String.format("%s: getTimeSheetDetailSK: START", getClass().getName()));

            if (DateUtil.IsNull(timesheetDate)) {
                String errMsg = String.format("%s: getTimeSheetDetailSK: [VISIT_SK=%d]: [PAYROLL_CODE=%s]: ERROR: timesheetDate == null",
                                        getClass().getName(), visitSk, payrollCode);
                throw new SandataRuntimeException(errMsg);
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            logger.trace(String.format("%s: getTimeSheetDetailSK: [VISIT_SK: %d]: [PAYROLL_CODE=%s]: [TIMESHEET_DATE=%s]",
                    getClass().getName(), visitSk, payrollCode, dateFormat.format(timesheetDate)));

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT TIMESHEET_DET_SK FROM TIMESHEET_DET WHERE VISIT_SK = ? AND PR_CODE = ?" +
                             " AND TIMESHEET_DATE = TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')";

            logger.trace(String.format("%s: getTimeSheetDetailSK: [SQL: %s]", getClass().getName(), sql));

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setLong(index++, visitSk);
            preparedStatement.setString(index++, payrollCode);
            preparedStatement.setString(index++, dateFormat.format(timesheetDate));

            resultSet = preparedStatement.executeQuery();

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                BigDecimal timesheetSummarySK = resultSet.getBigDecimal("TIMESHEET_DET_SK");
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

            String errMsg = String.format("%s: getTimeSheetDetailSK: EXCEPTION: %s", e.getClass().getName(), e.getMessage());
            logger.error(errMsg);
            throw new SandataRuntimeException(errMsg, e);

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

            logger.debug(String.format("%s: getTimeSheetDetailSK: END", getClass().getName()));
        }
    }
}
