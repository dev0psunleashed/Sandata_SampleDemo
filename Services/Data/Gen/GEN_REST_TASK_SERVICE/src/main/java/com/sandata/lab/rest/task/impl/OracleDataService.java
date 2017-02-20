package com.sandata.lab.rest.task.impl;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.dl.model.Task;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.rest.task.api.OracleService;
import com.sandata.lab.rest.task.model.TaskExt;
import com.sandata.lab.rest.task.utils.data.DataMapper;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OracleDataService implements OracleService {

    private ConnectionPoolDataService connectionPoolDataService;

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
            long result = (long)callableStatement.getLong(1);

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

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (Object object : params) {
                preparedStatement.setObject(index++, object);
            }

            resultSet = preparedStatement.executeQuery();

            Object result = new DataMapper().map(resultSet, className);

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

    /**
     * Gets tasks for a specified Business Entity ID with sorting options
     * @param bsnEntId
     * @param orderByColumn
     * @param direction
     * @return a list of Tasks
     */
    public Response getTasks(String bsnEntId, String orderByColumn, String direction) {
        try {

            String sql = String.format(
                    new StringBuilder()
                    .append("SELECT tsk.TASK_SK,tsk.REC_CREATE_TMSTP,tsk.REC_UPDATE_TMSTP,tsk.REC_EFF_TMSTP,tsk.REC_TERM_TMSTP ")
                    .append("    ,tsk.REC_CREATED_BY,tsk.REC_UPDATED_BY,tsk.CHANGE_REASON_MEMO,tsk.CURR_REC_IND,tsk.CHANGE_VERSION_ID ")
                    .append("    ,tsk.BE_ID,tsk.TASK_ID,tsk.BE_TASK_ID ")
                    .append("    ,CASE ")
                    .append("        WHEN (tsk.BE_TASK_NAME IS NULL AND tsk.TASK_DESC IS NULL) THEN tskup.TASK_NAME ")
                    .append("        ELSE tsk.BE_TASK_NAME ")
                    .append("    END AS BE_TASK_NAME ")
                    .append("    ,CASE ")
                    .append("        WHEN (tsk.BE_TASK_NAME IS NULL AND tsk.TASK_DESC IS NULL) THEN tskup.TASK_DESC ")
                    .append("        ELSE tsk.TASK_DESC ")
                    .append("    END AS TASK_DESC ")
                    .append("    ,tsk.TASK_RDNG_IND,tsk.TASK_RDNG_VALID_RULE,tsk.CRITICAL_TASK_IND,tsk.TASK_EFF_DATE,tsk.TASK_TERM_DATE ")
                    .append("    ,tsk.TASK_SOURCE_QLFR ")
                    .append("FROM TASK tsk ")
                    .append("    JOIN TASK_LKUP tskup ON tsk.TASK_ID = tskup.TASK_ID ")
                    .append("WHERE tsk.BE_ID = ? ")
                    .append("    AND (TO_CHAR(tsk.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND tsk.CURR_REC_IND = '1') ")
                    .append("    AND (TO_CHAR(tskup.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND tskup.CURR_REC_IND = '1') ")
                    .append("ORDER BY UPPER(%s) %s")
                    .toString(), orderByColumn, direction);

            List<Task> tasks = (List<Task>)getEntitiesForId(sql, "com.sandata.lab.data.model.dl.model.Task", bsnEntId);

            List<TaskExt> taskExtList = new ArrayList<>();
            for (Task task : tasks) {
                TaskExt taskExt = new TaskExt(task);
                //TODO: Need to get the ServiceSK
                taskExtList.add(taskExt);
            }

            Response response = new Response();
            response.setData(taskExtList);
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction);

            return response;

        } catch (Exception e) {
            throw new SandataRuntimeException(
                    String.format("%s: getTasks: %s", this.getClass().getName(), e.getMessage()), e);

        }
    }

    public Response getTasksForService(String bsnEntId, long serviceSk, String orderByColumn, String direction) {
        try {

            String sql = String.format("SELECT tsk.TASK_SK,tsk.REC_CREATE_TMSTP,tsk.REC_UPDATE_TMSTP,tsk.REC_EFF_TMSTP,tsk.REC_TERM_TMSTP  " +
                            "    ,tsk.REC_CREATED_BY,tsk.REC_UPDATED_BY,tsk.CHANGE_REASON_MEMO,tsk.CURR_REC_IND,tsk.CHANGE_VERSION_ID  " +
                            "    ,tsk.BE_ID,tsk.TASK_ID,tsk.BE_TASK_ID  " +
                            "    ,CASE  " +
                            "        WHEN (tsk.BE_TASK_NAME IS NULL AND tsk.TASK_DESC IS NULL) THEN tskup.TASK_NAME  " +
                            "        ELSE tsk.BE_TASK_NAME  " +
                            "    END AS BE_TASK_NAME  " +
                            "    ,CASE  " +
                            "        WHEN (tsk.BE_TASK_NAME IS NULL AND tsk.TASK_DESC IS NULL) THEN tskup.TASK_DESC  " +
                            "        ELSE tsk.TASK_DESC  " +
                            "    END AS TASK_DESC  " +
                            "    ,tsk.TASK_RDNG_IND,tsk.TASK_RDNG_VALID_RULE,tsk.CRITICAL_TASK_IND,tsk.TASK_EFF_DATE,tsk.TASK_TERM_DATE  " +
                            "    ,tsk.TASK_SOURCE_QLFR " +
                            "FROM TASK tsk  " +
                            "    JOIN TASK_LKUP tskup ON tsk.TASK_ID = tskup.TASK_ID  " +
                            /*"    INNER JOIN SVC_TASK svct ON tsk.BE_ID = svct.BE_ID AND tsk.BE_TASK_ID = svct.BE_TASK_ID " +*/
                            "WHERE svct.SVC_SK = ? " +
                            "    AND (TO_CHAR(tsk.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND tsk.CURR_REC_IND = '1')  " +
                            "    AND (TO_CHAR(tskup.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND tskup.CURR_REC_IND = '1')  " +
                            "ORDER BY UPPER(%s) %s",
                            orderByColumn, direction);

            List<Task> tasks = (List<Task>)getEntitiesForId(sql, "com.sandata.lab.data.model.dl.model.Task", bsnEntId, serviceSk);

            Response response = new Response();
            response.setData(tasks);
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction);

            return response;

        } catch (Exception e) {
            throw new SandataRuntimeException(
                    String.format("%s: getTasks: %s", this.getClass().getName(), e.getMessage()), e);

        }
    }

    public Task getTaskForSK(long taskSk) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM TASK WHERE TASK_SK = ?";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setLong(index++, taskSk);

            resultSet = preparedStatement.executeQuery();

            List<Task> resultList =
                    (List<Task>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.Task");

            if (resultList.size() > 0) {
                return resultList.get(0);
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
}
