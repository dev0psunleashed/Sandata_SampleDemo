package com.sandata.one.aggregator.audit.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.data.model.dl.model.custom.AuditEmploymentStatusHistory;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.one.aggregator.audit.api.OracleService;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class OracleDataService implements OracleService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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

    public Object executeGet(String packageName, String methodName, String className, long sequenceKey) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.COREDATA);
            connection.setAutoCommit(true);

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setLong(2, sequenceKey);
            callableStatement.execute();
            resultSet = (ResultSet) callableStatement.getObject(1);

            Object result = new DataMapper().map(resultSet, className);

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

            throw new SandataRuntimeException(String.format("[%s][%s][%s]: %s: %s",
                packageName, methodName, className,
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

    private String getBsnEntId(long visitSk) {
        List<Visit> visits = (ArrayList<Visit>) executeGet(
                "PKG_VISIT",
                "getVisit",
                "com.sandata.lab.data.model.dl.model.Visit",
                visitSk);

        if (visits != null & visits.size() > 0) {
            return visits.get(0).getBusinessEntityID();
        }

        return null;
    }

    public String forcedLogout(String userGuid) throws SandataRuntimeException {
    
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
    
        try {
    
            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(false);

            // TODO: Rename AuditEmploymentStatusHistory to something generic
            AuditEmploymentStatusHistory auditEmploymentStatusHistory = new AuditEmploymentStatusHistory();
            auditEmploymentStatusHistory.setUserGuid(userGuid);
            Object jpubType = new DataMapper().map(auditEmploymentStatusHistory);

            String callMethod = String.format("{?=call %s.PKG_AUDIT.INSERT_AUDIT_RECORD(?)", ConnectionType.METADATA);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setObject(2, jpubType);
            callableStatement.execute();

            int resultVal = callableStatement.getInt(1);

            connection.commit();
    
            return (resultVal > 0) ? "SUCCESS" : "FAILED";
    
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
            if (callableStatement != null) {
                try {
                    callableStatement.close();
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
