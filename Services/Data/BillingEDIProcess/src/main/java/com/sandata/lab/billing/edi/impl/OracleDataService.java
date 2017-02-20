package com.sandata.lab.billing.edi.impl;

import com.sandata.lab.billing.edi.api.OracleService;
import com.sandata.lab.billing.edi.utils.log.LoggingUtils;
import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;

import oracle.jdbc.OracleTypes;

import java.sql.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Data service class to access Oracle database
 */
public class OracleDataService implements OracleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleDataService.class);
    private ConnectionPoolDataService connectionPoolDataService;

    /**
     * Gets value of the ConnectionPoolDataService from this object
     * 
     * @return an instance of {@link ConnectionPoolDataService}; null if not set
     */
    public ConnectionPoolDataService getConnectionPoolDataService() {
        return connectionPoolDataService;
    }

    /**
     * Sets value to the ConnectionPoolDataService of this object
     * 
     * @param connectionPoolDataService
     *            an instance of {@link ConnectionPoolDataService} to set
     */
    public void setConnectionPoolDataService(ConnectionPoolDataService connectionPoolDataService) {
        this.connectionPoolDataService = connectionPoolDataService;
    }

    /**
    * Executes database call for specified {@code connectionType},
    * {@code packageName}, {@code methodName} and {@code jpubType}
    * 
    * @param connectionType
    *            {@link ConnectionType} to use to get database connection
    * @param packageName
    *            Oracle package name to execute
    * @param methodName
    *            Oracle method name to execute
    * @param jpubType
    *            the jpubType
    * 
    * @throws SandateRuntimeException
    *             (runtime) if exception happens when executing a database call
    * 
    * @return SK of jpubType object
    */
   @Override
   public int execute(ConnectionType connectionType, String packageName, String methodName, Object jpubType) {
       Connection connection = null;
       CallableStatement callableStatement = null;

       try {

           connection = connectionPoolDataService.getConnection(connectionType);
           connection.setAutoCommit(false);

           String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
           callableStatement = connection.prepareCall(callMethod);
           callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
           callableStatement.setObject(2, jpubType);
           callableStatement.execute();
           int result = (Integer) callableStatement.getObject(1);

           connection.commit();

           return result;

       } catch (Exception e) {

           safeRollback(connection);

           String errorMessage = LoggingUtils.getErrorMessageInfo(this, "execute",
                   new StringBuilder("An exception happens when executing a database call")
                       .append(", database:").append(connectionType)
                       .append(", packageName:").append(packageName)
                       .append(", methodName:").append(methodName)
                       .append(", error: ").append(e.getMessage())
                       .toString());
           LOGGER.error(errorMessage, e);
           throw new SandataRuntimeException(errorMessage, e);

       } finally {

           safeClose(callableStatement);
           safeClose(connection);

       }
   }

   /**
    * Gets objects type of {@code className} by specified {@code entityId}
    * 
    * @param connectionType
    *            {@link ConnectionType} to use to get database connection
    * @param packageName
    *            Oracle package name to execute get
    * @param methodName
    *            Oracle method name to execute get
    * @param className
    *            the class name to map database records to objects
    * @param entityId
    *            the entity id to get objects from database
    * @throws SandateRuntimeException
    *             (runtime) if exception happens when executing a database call
    *
    * @return a list of objects type of {@code className}
    */
   @Override
   public Object executeGet(ConnectionType connectionType, String packageName, String methodName, String className, String entityId) {

       Connection connection = null;
       CallableStatement callableStatement = null;
       ResultSet resultSet = null;

       try {

           connection = connectionPoolDataService.getConnection(connectionType);
           connection.setAutoCommit(false);

           String callMethod = String.format("{?=call %s.%s.%s(?)}", connectionType, packageName, methodName);
           callableStatement = connection.prepareCall(callMethod);
           callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
           callableStatement.setString(2, entityId);
           callableStatement.execute();
           resultSet = (ResultSet) callableStatement.getObject(1);

           Object result = new DataMapper().map(resultSet, className);

           connection.commit();

           return result;

       } catch (Exception e) {

           safeRollback(connection);

           String errorMessage = LoggingUtils.getErrorMessageInfo(this, "executeGet",
                   new StringBuilder("An exception happens when executing a database call")
                       .append(", database:").append(connectionType)
                       .append(", packageName:").append(packageName)
                       .append(", methodName:").append(methodName)
                       .append(", className:").append(className)
                       .append(", entityId:").append(entityId)
                       .append(", error: ").append(e.getMessage())
                       .toString());
           LOGGER.error(errorMessage, e);
           throw new SandataRuntimeException(errorMessage, e);

       } finally {

           safeClose(resultSet);
           safeClose(callableStatement);
           safeClose(connection);

       }
   }

   private void safeClose(ResultSet resultSet) {
       if (resultSet != null) {
           try {
               resultSet.close();
           } catch (SQLException sqle) {
               LOGGER.warn(LoggingUtils.getLogMessageInfo(this, "Error happened when closing result set: {}"), sqle.getMessage(), sqle);
           }
       }
   }

   private void safeClose(Statement statement) {
       if (statement != null) {
           try {
               statement.close();
           } catch (SQLException sqle) {
               LOGGER.warn(LoggingUtils.getLogMessageInfo(this, "Error happened when closing statement: {}"), sqle.getMessage(), sqle);
           }
       }
   }

   private void safeClose(Connection connection) {
       if (connection != null) {
           try {
               connection.close();
           } catch (SQLException sqle) {
               LOGGER.warn(LoggingUtils.getLogMessageInfo(this, "Error happened when closing connection: {}"), sqle.getMessage(), sqle);
           }
       }
   }
   
   private void safeRollback(Connection connection) {
       if (connection != null) {
           try {
               connection.rollback();
           } catch (SQLException sqle) {
               LOGGER.warn(LoggingUtils.getLogMessageInfo(this, "Error happened when rollback connection: {}"), sqle.getMessage(), sqle);
           }
       }
   }
}
