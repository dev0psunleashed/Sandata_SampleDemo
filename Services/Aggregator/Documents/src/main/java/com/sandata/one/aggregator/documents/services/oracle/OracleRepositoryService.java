/*
 * Copyright (c) 2015. Sandata Technologies, LLC
 * 26 Harbor Park Drive, Port Washington, NY 11050, 800-544-7263
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Sandata Technologies, LLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with
 * Sandata.
 */

package com.sandata.one.aggregator.documents.services.oracle;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.one.aggregator.documents.impl.data.requests.OracleRequest;
import com.sandata.one.aggregator.documents.utils.log.DocumentDataLogger;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * <p/>
 *
 * @author Ralph Sylvain
 */

public abstract class OracleRepositoryService {

    protected ConnectionPoolDataService connectionPoolDataService;

    public Connection getOracleConnection() throws SandataRuntimeException {
        return connectionPoolDataService.getConnection();
    }

    public ConnectionPoolDataService getConnectionPoolDataService() {
        return connectionPoolDataService;
    }

    public void setConnectionPoolDataService(ConnectionPoolDataService connectionPoolDataService) {
        this.connectionPoolDataService = connectionPoolDataService;
    }

    protected ResultSetTransformer resultSetTransformer;

    public void setResultSetTransformer(ResultSetTransformer resultSetTransformer) {
        this.resultSetTransformer = resultSetTransformer;
    }

    public void closeOracleConnection(Connection connection) throws SandataRuntimeException {
        this.connectionPoolDataService.close(connection);
    }

    public int execute(OracleRequest oracleRequest) {
        SandataLogger sandataLogger = DocumentDataLogger.CreateLogger();

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            sandataLogger.logger().info("Executing insert...");

            connection = getOracleConnection();
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s(?)}", oracleRequest.getPackageName(), oracleRequest.getMethodName());
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setObject(2, oracleRequest.getData());
            callableStatement.execute();
            int result = (Integer) callableStatement.getObject(1);

            connection.commit();

            sandataLogger.logger().info("Executed insert.");

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

            throw new SandataRuntimeException(String.format("[%s][%s]: %s: %s",
                    oracleRequest.getPackageName(), oracleRequest.getMethodName(),
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Connection
            this.connectionPoolDataService.close(connection);


            sandataLogger.stop();
        }
    }

    public int executeRequests(List<OracleRequest> oracleRequests) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        int result = 0;

        try {

            connection = getOracleConnection();
            connection.setAutoCommit(false);

            for (OracleRequest oracleRequest : oracleRequests) {


                String callMethod = String.format("{?=call %s.%s(?)}", oracleRequest.getPackageName(), oracleRequest.getMethodName());
                callableStatement = connection.prepareCall(callMethod);
                callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
                callableStatement.setObject(2, oracleRequest.getData());
                callableStatement.execute();
                result = (Integer) callableStatement.getObject(1);
            }

            connection.commit();

            return result;
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

            throw new SandataRuntimeException(e.getMessage(), e);

        } finally {

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Connection
            this.connectionPoolDataService.close(connection);

        }
    }

    /**
     * Found nowhere using this method. So just comment it
     */
    /*public int executeDelete(OracleRequest oracleRequest) {
        SandataLogger sandataLogger = DocumentDataLogger.CreateLogger();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = getOracleConnection();
            connection.setAutoCommit(true);


            Date now = new java.util.Date();

            String sql = String.format("UPDATE %s SET CURR_REC_IND = 0, REC_TERM_TMSTP = ?" +
                    "WHERE %s = ? and CURR_REC_IND = 1", oracleRequest.getTableName(), oracleRequest.getColumnName());

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDate(1, new java.sql.Date(now.getTime()));
            preparedStatement.setObject(2, oracleRequest.getData());


            connection.commit();


            int resultVal = preparedStatement.executeUpdate();

            sandataLogger.logger().info("Executed delete.");

            return resultVal;

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("[%s][%s]: %s: %s",
                    oracleRequest.getPackageName(), oracleRequest.getMethodName(),
                    e.getClass().getName(), e.getMessage()));

        } finally {

            // Close the Statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Connection
            this.connectionPoolDataService.close(connection);


            sandataLogger.stop();
        }
    }*/


    public int executeDeletes(List<OracleRequest> oracleRequests) {
        SandataLogger sandataLogger = DocumentDataLogger.CreateLogger();

        Connection connection = null;
        CallableStatement callableStatement = null;

        int resultVal = 0;

        try {

            connection = getOracleConnection();
            connection.setAutoCommit(false);

            for (OracleRequest oracleRequest : oracleRequests) {
                String callMethod = "{?=call PKG_DOCXWALK_UTIL.DELETE_DOC_XWALK(?,?,?)}";
                callableStatement = connection.prepareCall(callMethod);
                callableStatement.registerOutParameter(1, OracleTypes.INTEGER);

                int index = 2;
                callableStatement.setString(index++, oracleRequest.getTableName());
                callableStatement.setString(index++, oracleRequest.getColumnName());
                callableStatement.setObject(index++, oracleRequest.getData());

                callableStatement.execute();
                int result = (Integer)callableStatement.getObject(1);

                if (result > 0) {
                    resultVal = result;
                }
            }

            connection.commit();

            sandataLogger.logger().info("Executed delete.");

            return resultVal;

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

            throw new SandataRuntimeException(e.getMessage(), e);

        } finally {

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Connection
            this.connectionPoolDataService.close(connection);

        }
    }
}
