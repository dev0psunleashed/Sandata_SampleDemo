package com.sandata.lab.logger.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.ApplicationLog;
import com.sandata.lab.data.model.dl.model.ApplicationSession;
import com.sandata.lab.data.model.jpub.model.AppLogT;
import com.sandata.lab.data.model.jpub.model.AppSessT;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.logger.api.OracleService;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.List;

@SuppressWarnings("unchecked")
public class OracleDataService implements OracleService {

    private ConnectionPoolDataService connectionPoolDataService;

    public ConnectionPoolDataService getConnectionPoolDataService() {
        return connectionPoolDataService;
    }

    public void setConnectionPoolDataService(ConnectionPoolDataService connectionPoolDataService) {
        this.connectionPoolDataService = connectionPoolDataService;
    }

    @Override
    public Object executeGet(ConnectionType connectionType, String packageName, String methodName, String className, Object... params)
            throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(connectionType);
            connection.setAutoCommit(false);

            ArrayDescriptor des = ArrayDescriptor.createDescriptor(connectionType + ".STRING_ARRAY", connection);
            ARRAY primaryKeysArray = new ARRAY(des, connection, params);

            String callMethod = String.format("{?=call %s.%s.%s(?)}", connectionType, packageName, methodName);
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
                    e.getClass().getName(), e.getMessage()));

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
    public Object executeGet(ConnectionType connectionType, String packageName, String methodName, String className, int sequenceKey) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(connectionType);
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s.%s(?)}", connectionType, packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setInt(2, sequenceKey);
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
                    e.getClass().getName(), e.getMessage()));

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
    public int execute(ConnectionType connectionType, String packageName, String methodName, int sequenceKey) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection(connectionType);
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s.%s(?)}", connectionType, packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setInt(2, sequenceKey);
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
                    e.getClass().getName(), e.getMessage()));

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
    public int execute(ConnectionType connectionType, String packageName, String methodName, Object jpubType) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection(connectionType);
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s.%s(?)}", connectionType, packageName, methodName);
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
                    e.getClass().getName(), e.getMessage()));

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
    public int execute(Connection connection, ConnectionType connectionType, String packageName, String methodName, Object jpubType) throws SandataRuntimeException {

        CallableStatement callableStatement = null;

        try {

            String callMethod = String.format("{?=call %s.%s.%s(?)}", connectionType, packageName, methodName);
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
                    e.getClass().getName(), e.getMessage()));

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

    public Object getEntitiesForId(ConnectionType connectionType, String sql, String className, final Object... params) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(connectionType);
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
                    e.getClass().getName(), e.getMessage()));

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

    public int executeUpdate(ConnectionType connectionType, String sql, Object... params) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = connectionPoolDataService.getConnection(connectionType);
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
                    e.getClass().getName(), e.getMessage()));

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

    public int executeCount(ConnectionType connectionType, String sql, String countColumnName, Object... params) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(connectionType);
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
                    e.getClass().getName(), e.getMessage()));

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

    public List<ApplicationLog> getAppLogPK(
                    Long logThread,
                    String logLevel,
                    String logProcess,
                    String fromDate,
                    String fromTime,
                    String toDate,
                    String toTime) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(true);

            String fromDateTimeString = "";
            String toDateTimeString = "";
            StringBuilder filter = new StringBuilder();
            boolean bDateFilter = false;
            if (!StringUtil.IsNullOrEmpty(fromDate)
                    && !StringUtil.IsNullOrEmpty(fromTime)
                    && !StringUtil.IsNullOrEmpty(toDate)
                    && !StringUtil.IsNullOrEmpty(toTime)) {

                bDateFilter = true;

                fromDateTimeString = String.format("%s %s", fromDate, fromTime);
                toDateTimeString = String.format("%s %s", toDate, toTime);

                filter.append("REC_UPDATE_TMSTP BETWEEN " +
                                "    TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') AND " +
                                "    TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') ");
            }

            if (!StringUtil.IsNullOrEmpty(logProcess)) {
                if (!StringUtil.IsNullOrEmpty(filter.toString())) {
                    filter.append(" AND ");
                }

                filter.append("UPPER(LOG_PROCESS) LIKE ?");
            }

            if (!StringUtil.IsNullOrEmpty(logLevel)) {
                if (!StringUtil.IsNullOrEmpty(filter.toString())) {
                    filter.append(" AND ");
                }

                filter.append("UPPER(LOG_LVL) = ?");
            }

            if (logThread != null) {
                if (!StringUtil.IsNullOrEmpty(filter.toString())) {
                    filter.append(" AND ");
                }

                filter.append("LOG_THREAD = ?");
            }

            String sql = "SELECT * FROM APP_LOG " +
                            "WHERE " + filter.toString();

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            if (bDateFilter) {
                preparedStatement.setString(index++, fromDateTimeString);
                preparedStatement.setString(index++, toDateTimeString);
            }

            if (!StringUtil.IsNullOrEmpty(logProcess)) {
                preparedStatement.setString(index++, "%" + logProcess.toUpperCase() + "%");
            }

            if (!StringUtil.IsNullOrEmpty(logLevel)) {
                preparedStatement.setString(index++, logLevel.toUpperCase());
            }

            if (logThread != null) {
                preparedStatement.setLong(index++, logThread);
            }

            resultSet = preparedStatement.executeQuery();

            List<ApplicationLog> resultList =
                    (List<ApplicationLog>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.ApplicationLog");

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
                    e.getClass().getName(), e.getMessage()));

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

    public List<ApplicationSession> getAppSessPK(
            String userGuid,
            String fromDate,
            String fromTime,
            String toDate,
            String toTime) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(true);

            String fromDateTimeString = "";
            String toDateTimeString = "";
            StringBuilder filter = new StringBuilder();
            boolean bDateFilter = false;
            if (!StringUtil.IsNullOrEmpty(fromDate)
                    && !StringUtil.IsNullOrEmpty(fromTime)
                    && !StringUtil.IsNullOrEmpty(toDate)
                    && !StringUtil.IsNullOrEmpty(toTime)) {

                bDateFilter = true;

                fromDateTimeString = String.format("%s %s", fromDate, fromTime);
                toDateTimeString = String.format("%s %s", toDate, toTime);

                filter.append("REC_UPDATE_TMSTP BETWEEN " +
                        "    TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') AND " +
                        "    TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') ");
            }

            if (!StringUtil.IsNullOrEmpty(userGuid)) {
                if (!StringUtil.IsNullOrEmpty(filter.toString())) {
                    filter.append(" AND ");
                }

                filter.append("UPPER(USER_GUID) = ?");
            }

            String sql = "SELECT * FROM APP_SESS " +
                    "WHERE " + filter.toString();

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            if (bDateFilter) {
                preparedStatement.setString(index++, fromDateTimeString);
                preparedStatement.setString(index++, toDateTimeString);
            }

            if (!StringUtil.IsNullOrEmpty(userGuid)) {
                preparedStatement.setString(index++, userGuid.toUpperCase());
            }

            resultSet = preparedStatement.executeQuery();

            List<ApplicationSession> resultList =
                    (List<ApplicationSession>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.ApplicationSession");

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
                    e.getClass().getName(), e.getMessage()));

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


    public Response getAppLogHistory(
                    String userGuid,
                    Long logThread,
                    String logLevel,
                    String logProcess,
                    String fromDate,
                    String fromTime,
                    String toDate,
                    String toTime,
                    int page,
                    int pageSize,
                    String sortOn,
                    String direction)
                        throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // Calculate row range.
        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(true);

            String orderByColumn = "T1.REC_UPDATE_TMSTP"; // Default
            switch (sortOn) {
                case "create":
                    orderByColumn = "T1.REC_CREATE_TMSTP";
                    break;
                case "host":
                    orderByColumn = "T1.LOG_HOST";
                    break;
                case "process":
                    orderByColumn = "T1.LOG_PROCESS";
                    break;
                case "pid":
                    orderByColumn = "T1.LOG_PID";
                    break;
                case "thread":
                    orderByColumn = "T1.LOG_THREAD";
                    break;
                case "level":
                    orderByColumn = "T1.LOG_LVL";
                    break;
                case "guid":
                    orderByColumn = "T1.T2.USER_GUID";
                    break;
            }

            String fromDateTimeString = "";
            String toDateTimeString = "";
            StringBuilder appSessionDateFilter = new StringBuilder();
            StringBuilder filter = new StringBuilder();
            boolean bDateFilter = false;
            if (!StringUtil.IsNullOrEmpty(fromDate)
                    && !StringUtil.IsNullOrEmpty(fromTime)
                    && !StringUtil.IsNullOrEmpty(toDate)
                    && !StringUtil.IsNullOrEmpty(toTime)) {

                bDateFilter = true;

                fromDateTimeString = String.format("%s %s", fromDate, fromTime);
                toDateTimeString = String.format("%s %s", toDate, toTime);

                appSessionDateFilter.append("WHERE REC_UPDATE_TMSTP BETWEEN " +
                        "    TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') AND " +
                        "    TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') ");

                filter.append("T1.REC_UPDATE_TMSTP BETWEEN " +
                        "    TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') AND " +
                        "    TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') ");
            }

            if (!StringUtil.IsNullOrEmpty(userGuid)) {
                if (!StringUtil.IsNullOrEmpty(filter.toString())) {
                    filter.append(" AND ");
                }

                filter.append("UPPER(USER_GUID) = ?");
            }

            if (logThread != null) {
                if (!StringUtil.IsNullOrEmpty(filter.toString())) {
                    filter.append(" AND ");
                }

                filter.append("LOG_THREAD = ?");
            }

            if (!StringUtil.IsNullOrEmpty(logLevel)) {
                if (!StringUtil.IsNullOrEmpty(filter.toString())) {
                    filter.append(" AND ");
                }

                filter.append("UPPER(LOG_LVL) = ?");
            }

            if (!StringUtil.IsNullOrEmpty(logProcess)) {
                if (!StringUtil.IsNullOrEmpty(filter.toString())) {
                    filter.append(" AND ");
                }

                filter.append("UPPER(LOG_PROCESS) LIKE ?");
            }

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                            " " +
                            "  SELECT * FROM ( " +
                            " " +
                            "      (SELECT T2.USER_GUID,T1.* FROM APP_LOG T1 " +
                            " " +
                            "          LEFT JOIN (SELECT USER_GUID,APP_SESS_SK,REC_UPDATE_TMSTP " +
                            "            FROM APP_SESS) T2 " +
                            "          ON T1.APP_SESS_SK = T2.APP_SESS_SK " +
                            " " +
                            "        WHERE %s" +
                            " " +
                            "        ORDER BY %s %s) " +
                            "  ) " +
                            " " +
                            ") R1) " +
                            " " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d ",
                    filter.toString(),
                    orderByColumn,
                    direction,
                    fromRow,
                    toRow
            );

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            if (bDateFilter) {
                preparedStatement.setString(index++, fromDateTimeString);
                preparedStatement.setString(index++, toDateTimeString);
            }

            if (!StringUtil.IsNullOrEmpty(userGuid)) {
                preparedStatement.setString(index++, userGuid.toUpperCase());
            }

            if (logThread != null) {
                preparedStatement.setLong(index++, logThread);
            }

            if (!StringUtil.IsNullOrEmpty(logLevel)) {
                preparedStatement.setString(index++, logLevel.toUpperCase());
            }

            if (!StringUtil.IsNullOrEmpty(logProcess)) {
                preparedStatement.setString(index++, "%" + logProcess.toUpperCase() + "%");
            }

            Response response = new Response();
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());

                List<ApplicationLog> resultList = (List<ApplicationLog>) new DataMapper().mapWithOffsetNext(resultSet, "com.sandata.lab.data.model.dl.model.ApplicationLog", 3);

                response.setData(resultList);
            }

            return response;

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
                e.getClass().getName(), e.getMessage()));

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

    public int addLogForUserGUID(Connection connection, String userGuid, ApplicationLog applicationLog) throws SandataRuntimeException {

        Long appSessionSK = getAppSessSK(connection, userGuid);
        if (appSessionSK == null) {
            appSessionSK = (long)insertAppSession(connection, userGuid);
        }

        applicationLog.setApplicationSessionSK(BigInteger.valueOf(appSessionSK));
        return insertAppLog(connection, applicationLog);
    }

    public int addLog(Connection connection, ApplicationLog applicationLog) throws SandataRuntimeException {
        return insertAppLog(connection, applicationLog);
    }

    private int insertAppLog(Connection connection, ApplicationLog applicationLog) throws SandataRuntimeException {

        //dmr LOG_MSG is limited to 4K Bytes (2016-09-15)
        String logMsg = applicationLog.getLogMessage();
        if (logMsg != null && logMsg.length() > 4000) {
            applicationLog.setLogMessage(String.format("%s ...[TRIMMED]", logMsg.substring(0, 3980)));
        }

        OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(applicationLog);
        AppLogT jpubObj = (AppLogT) new DataMapper().map(applicationLog);
        return execute(connection, ConnectionType.METADATA, oracleMetadata.packageName(), oracleMetadata.insertMethod(), jpubObj);
    }

    private int insertAppSession(Connection connection, String userGuid) throws SandataRuntimeException {

        ApplicationSession appSession = new ApplicationSession();
        appSession.setRecordCreateTimestamp(new java.util.Date());
        appSession.setRecordUpdateTimestamp(new java.util.Date());
        appSession.setUserGloballyUniqueIdentifier(userGuid);

        OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(appSession);
        AppSessT jpubObj = (AppSessT) new DataMapper().map(appSession);
        return execute(connection, ConnectionType.METADATA, oracleMetadata.packageName(), oracleMetadata.insertMethod(), jpubObj);
    }

    private Long getAppSessSK(Connection connection, String userGuid) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT APP_SESS_SK FROM APP_SESS WHERE USER_GUID = ?";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, userGuid);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getBigDecimal("APP_SESS_SK").longValue();
            }

            return null;

        } catch (Exception e) {

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()));

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
