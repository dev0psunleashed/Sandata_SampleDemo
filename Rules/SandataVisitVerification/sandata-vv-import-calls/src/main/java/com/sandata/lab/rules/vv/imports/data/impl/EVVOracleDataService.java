package com.sandata.lab.rules.vv.imports.data.impl;

import com.sandata.lab.common.oracle.db.connection.SandataOracleConnection;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.rules.vv.imports.data.api.DBDataService;
import com.sandata.lab.rules.vv.imports.data.transformers.EVVCallTransformer;
import com.sandata.lab.rules.vv.imports.model.EVVCall;
import com.sandata.lab.rules.vv.log.utils.LoggingUtils;

import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 
 * @author thanh.le
 * 
 * This class provides 2 methods to interact to EVV database : one for get unprocessed calls and other one for mark calls as exported.
 *
 */
public class EVVOracleDataService implements DBDataService {

    private SandataOracleConnection sandataOracleConnection;
    
    private EVVCallTransformer evvCallTransformer;

    public void setSandataOracleConnection(SandataOracleConnection sandataOracleConnection) {
        this.sandataOracleConnection = sandataOracleConnection;
    }

    public void setEvvCallTransformer(EVVCallTransformer evvCallTransformer) {
        this.evvCallTransformer = evvCallTransformer;
    }

    /**
     * @param groupKey group key to get calls from EVV db
     * @param exportKey export key to get calls from EVV db
     * @param numOfCalls number of calls being pulled from db
     * 
     */
    public List<EVVCall> getUnprocessedCalls (String groupKey, String exportKey, int numOfCalls) throws SandataRuntimeException {
        
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet callResultSet = null;
        ResultSet callInfoResultSet = null;
        
        
        try {
            
            connection = sandataOracleConnection.getConnection();
            connection.setAutoCommit(false);
            
            String packageName = "stx.expcalls";
            String methodName = "GET_CALLS_FORQUEUE";
            
            String callMethod = String.format(
                    "{?=call %s.%s(p_groupkey => ?,p_exportkey => ?, p_max_queue_value => ?"
                            + ",p_calls_cursor => ?,p_calls_info_cursor => ?, p_calls_broadcst_cursor => ?, p_calls_sv_cursor => ?)}",
                    packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setString(2, groupKey);
            callableStatement.setString(3, exportKey);
            callableStatement.setInt(4, numOfCalls);
            callableStatement.registerOutParameter(5, OracleTypes.CURSOR);
            callableStatement.registerOutParameter(6, OracleTypes.CURSOR);
            callableStatement.registerOutParameter(7, OracleTypes.CURSOR);
            callableStatement.registerOutParameter(8, OracleTypes.CURSOR);
            
            callableStatement.execute();
            callResultSet = (ResultSet) callableStatement.getObject(5);
            callInfoResultSet = (ResultSet) callableStatement.getObject(6);
            
            connection.commit();
            
            return evvCallTransformer.transformCallResultSetToCall(callResultSet, callInfoResultSet);
            
        } catch (Exception e) {
            
            // Rollback
            if (connection != null) {
                
                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
            
            throw new SandataRuntimeException(e.getMessage(),e);
            
        } finally {
            
            // Close the ResultSet
            if (callResultSet != null) {
                try {
                    callResultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
            
            // Close the ResultSet
            if (callInfoResultSet != null) {
                try {
                    callInfoResultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
            
            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
            
            // Close the Connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
            
        }
    }

    /**
     * Tag Call as exported in EVV so that it is not imported into George more than once
     * @param sid Unique identifier for a call in EVV
     */
    public void markCallsAsExported (String sid) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet callResultSet = null;

        try {

            connection = sandataOracleConnection.getConnection();
            connection.setAutoCommit(false);

            String packageName = "stx.expcalls";
            String methodName = "RECEIVED_CALLS";

            String callMethod = String.format("{?=call %s.%s(p_sid => ?, p_callexpkey => ?, " +
                    "p_error_code => ?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);

            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setString(2, sid);
            callableStatement.setString(3, null);
            callableStatement.setInt(4, 0);

            callableStatement.execute();

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

            String msg = LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_IMPORT_KEYWORD
    				, this.getClass(), "markCallsAsExported", "ERROR in method markCallsAsExported ");
             throw new SandataRuntimeException(msg, e);

        } finally {

            // Close the ResultSet
            if (callResultSet != null) {
                try {
                    callResultSet.close();
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

}

