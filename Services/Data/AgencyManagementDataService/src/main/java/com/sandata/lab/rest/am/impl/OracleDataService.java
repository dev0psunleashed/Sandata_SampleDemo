package com.sandata.lab.rest.am.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.extended.compliance.BusinessEntityComplianceLookupExt;
import com.sandata.lab.data.model.jpub.model.*;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.am.api.OracleService;
import com.sandata.lab.rest.am.model.BEStaffTrainingClassesByServiceFindResult;
import com.sandata.lab.rest.am.model.PayerListItemRequest;
import com.sandata.lab.rest.am.model.ReportKeyValue;
import com.sandata.lab.rest.am.model.ReportName;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import org.apache.camel.Exchange;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import static java.lang.String.format;

@SuppressWarnings("unchecked")
public class OracleDataService implements OracleService {

    protected ConnectionPoolDataService connectionPoolDataService;

    public Connection getOracleConnection() throws SandataRuntimeException {
        return connectionPoolDataService.getConnection();
    }

    public Connection getOracleConnection(ConnectionType connectionType) throws SandataRuntimeException {
        return connectionPoolDataService.getConnection(connectionType);
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
    public Object executeGet(ConnectionType connectionType, String packageName, String methodName, String className, Object... params)
            throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(connectionType);
            connection.setAutoCommit(false);

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.STRING_ARRAY", connection);
            ARRAY primaryKeysArray = new ARRAY(des, connection, params);

            String callMethod = String.format("{?=call %s.%s.%s(?)}", connectionType, packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setArray(2, primaryKeysArray);
            callableStatement.execute();

            resultSet = (ResultSet) callableStatement.getObject(1);

            Object result = new DataMapper().map(resultSet, className);

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

    @Override
    public Object executeGet(ConnectionType connectionType, String packageName, String methodName, String className, long sequenceKey) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(connectionType);
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s.%s(?)}", connectionType, packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setLong(2, sequenceKey);
            callableStatement.execute();
            resultSet = (ResultSet) callableStatement.getObject(1);

            Object result = new DataMapper().map(resultSet, className);

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

    @Override
    public Object executeGet(ConnectionType connectionType, String packageName, String methodName, String className, String entityId) throws SandataRuntimeException {

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

    @Override
    public long execute(ConnectionType connectionType, String packageName, String methodName, long sequenceKey) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection(connectionType);
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s.%s(?)}", connectionType, packageName, methodName);
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
                } catch (SQLException sqle) {
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
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Connection
            this.connectionPoolDataService.close(connection);
        }
    }

    @Override
    public long execute(Connection connection, ConnectionType connectionType, String packageName, String methodName, long sequenceKey) throws SandataRuntimeException {
        CallableStatement callableStatement = null;

        try {

            String callMethod = String.format("{?=call %s.%s.%s(?)}", connectionType, packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
            callableStatement.setLong(2, sequenceKey);
            callableStatement.execute();
            long result = callableStatement.getLong(1);

            connection.commit();

            return result;

        } catch (Exception e) {

            throw new SandataRuntimeException(String.format("[%s][%s]: %s: %s",
                    packageName, methodName,
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
        }
    }

    @Override
    public long execute(ConnectionType connectionType, String packageName, String methodName, Object jpubType) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection(connectionType);
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s.%s(?)}", connectionType, packageName, methodName);
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
                } catch (SQLException sqle) {
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
                } catch (SQLException sqle) {
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
            return callableStatement.getLong(1);
        } catch (Exception e) {

            // NOTE: Rollback (if necessary) should be handled by caller since they passed in the connection...
            throw new SandataRuntimeException(String.format("[%s][%s]: %s: %s",
                    packageName, methodName,
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
        }
    }

    private long insertBeLobLkup(Connection connection, String bsnEntId, Object jpubType) throws SandataRuntimeException {

        CallableStatement callableStatement = null;

        try {

            String callMethod = "{?=call COREDATA.PKG_BE_LOB.insertBeLobLkup(?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.NUMBER);
            callableStatement.setString(2, bsnEntId);
            callableStatement.setObject(3, jpubType);
            callableStatement.execute();
            return callableStatement.getBigDecimal(1).longValue();

        } catch (Exception e) {

            // NOTE: Rollback (if necessary) should be handled by caller since they passed in the connection...
            throw new SandataRuntimeException(String.format("%s: insertBeLobLkup: %s", e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    public Object getEntitiesForId(final ConnectionType connectionType, final String sql, final String className, final Object... params) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(connectionType);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (Object object : params) {
                preparedStatement.setObject(index++, object);
            }

            resultSet = preparedStatement.executeQuery();

            Object result = new DataMapper().map(resultSet, className);

            return result;

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

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    /**
     * Returns List of ApplicationTenantKeyConfiguration for the specified ApplicationTenant SK.
     *
     * @param applicationTenantSk Specified ApplicationTenant SK.
     * @return List of ApplicationTenantKeyConfiguration
     * @throws SandataRuntimeException
     */
    public List<ApplicationTenantKeyConfiguration> getAppTenantKeyConfigurationForAppTenantSk(Long applicationTenantSk) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM "
                    + ConnectionType.METADATA
                    + ".APP_TENANT_KEY_CONF WHERE APP_TENANT_SK = ?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, applicationTenantSk);

            resultSet = preparedStatement.executeQuery();

            List<ApplicationTenantKeyConfiguration> applicationTenantKeyConfigurationList
                    = (List<ApplicationTenantKeyConfiguration>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.ApplicationTenantKeyConfiguration");

            return applicationTenantKeyConfigurationList;

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


    /**
     * Returns ApplicationTenantKeyConfiguration for the specified ApplicationTenant SK and Key name.
     *
     * @param applicationTenantSk Specified ApplicationTenant SK.
     * @param keyName             Specified ApplicationTenant SK.
     * @return ApplicationTenantKeyConfiguration
     * @throws SandataRuntimeException
     */
    public ApplicationTenantKeyConfiguration getAppTenantKeyConfigurationForAppTenantSkAndKey(Long applicationTenantSk, String keyName) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM "
                    + ConnectionType.METADATA
                    + ".APP_TENANT_KEY_CONF WHERE APP_TENANT_SK = ? AND KEY_NAME = ?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, applicationTenantSk);
            preparedStatement.setString(2, keyName);

            resultSet = preparedStatement.executeQuery();

            List<ApplicationTenantKeyConfiguration> applicationTenantKeyConfiguration
                    = (List<ApplicationTenantKeyConfiguration>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.ApplicationTenantKeyConfiguration");

            if (applicationTenantKeyConfiguration != null && applicationTenantKeyConfiguration.size() > 0) {
                return applicationTenantKeyConfiguration.get(0);
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
            this.connectionPoolDataService.close(connection);
        }

    }

    /**
     * Returns ApplicationSystemKeyConfiguration for the specified Key name.
     *

     * @param keyName             Specified Key Name.
     * @return ApplicationTenantKeyConfiguration
     * @throws SandataRuntimeException
     */
    public List<ApplicationSystemKeyConfiguration> getAppSystemKeyConfigurationForAppKey(String keyName) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM "
                    + ConnectionType.METADATA
                    + ".APP_SYS_KEY_CONF  " +
                    " WHERE LEVEL > 1 " +
                    " START WITH UPPER(KEY_NAME) = ? " +
                    " CONNECT BY PRIOR APP_SYS_KEY_CONF_SK = APP_SYS_KEY_CONF_PAR_SK ";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, keyName.toUpperCase());

            resultSet = preparedStatement.executeQuery();

            List<ApplicationSystemKeyConfiguration> applicationSystemKeyConfiguration
                    = (List<ApplicationSystemKeyConfiguration>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.ApplicationSystemKeyConfiguration");

            if (applicationSystemKeyConfiguration != null && applicationSystemKeyConfiguration.size() > 0) {
                return applicationSystemKeyConfiguration;
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
            this.connectionPoolDataService.close(connection);
        }

    }


    /**
     * Returns The latest staff/patient id
     *
     * @param sql Specified the sql query to get the latest staff/patientID.
     * @return ResultSet
     * @throws SandataRuntimeException
     */
    public String getLatestStaffOrPatientID(String sql, String tableName) throws SandataRuntimeException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.COREDATA);
            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if ("PT".equals(tableName)) {
                    return resultSet.getString("PT_ID");
                } else if ("STAFF".equals(tableName)) {
                    return resultSet.getString("STAFF_ID");
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
            this.connectionPoolDataService.close(connection);
        }

    }


    /**
     * Add list of payer service list into database.
     *
     * @param connection
     * @param payerId
     * @param bsnEntId
     * @param serviceNameList List of ServiceName strings.
     */
    public void insertPayerServiceLstList(Connection connection, String payerId, String bsnEntId, List<String> serviceNameList)
            throws SandataRuntimeException {

        try {

            Date termDate = new SimpleDateFormat("yyyy-MM-dd").parse("9999-12-31");

            for (String serviceName : serviceNameList) {

                // Creates and initializes the object
                PayerServiceList payerServiceList = new PayerServiceList();

                payerServiceList.setRecordCreateTimestamp(new Date());
                payerServiceList.setRecordUpdateTimestamp(new Date());
                payerServiceList.setRecordEffectiveTimestamp(new Date());
                payerServiceList.setRecordTerminationTimestamp(termDate);
                payerServiceList.setCurrentRecordIndicator(true);
                payerServiceList.setChangeVersionID(BigInteger.ZERO);

                payerServiceList.setPayerID(payerId);
                payerServiceList.setBusinessEntityID(bsnEntId);
                payerServiceList.setServiceName(ServiceName.fromValue(serviceName));

                payerServiceList.setRecordCreatedBy("Middleware Service");
                payerServiceList.setChangeReasonMemo("AM: OracleDataService: insertPayerServiceLstList");

                OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(payerServiceList);
                PayerSvcLstT jpubObj = (PayerSvcLstT) new DataMapper().map(payerServiceList);

                execute(connection, ConnectionType.COREDATA, oracleMetadata.packageName(), oracleMetadata.insertMethod(), jpubObj);
            }
        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);
        }
    }

    public void deletePayerServiceList(Connection connection, String payerId, String bsnEntId) throws SandataRuntimeException {

        CallableStatement callableStatement = null;

        try {
            String callMethod = "{?=call PKG_AM_UTIL.DELETE_PAYER_SVC_LST(?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);

            int index = 2;
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, payerId);

            callableStatement.execute();

        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s", e.getClass().getName(), e.getMessage()), e);
        } finally {
            // Close the statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    public void deletePayerService(Connection connection, String payerId, String bsnEntId, String serviceName) throws SandataRuntimeException {

        CallableStatement callableStatement = null;

        try {
            String callMethod = "{?=call PKG_AM_UTIL.DELETE_PAYER_SVC(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);

            int index = 2;
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, payerId);
            callableStatement.setString(index++, serviceName);

            callableStatement.execute();

        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s", e.getClass().getName(), e.getMessage()), e);
        } finally {
            // Close the statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    /**
     * Checks if the @patientId is unique and validates the max length.
     *
     * @param patientOrStaffId The id to validate.
     * @param bsnEntId         The Business Entity ID since the @patientId does not have to be unique across George.
     * @return Return TRUE if the @patientId is valid, otherwise return FALSE.
     */
    public boolean isExistingPatientOrStaffId(String patientOrStaffId, String bsnEntId, String sql, String id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, patientOrStaffId);
            preparedStatement.setString(2, bsnEntId);

            boolean bResult = false;

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                String pid = resultSet.getString(id);
                bResult = (pid != null && pid.length() > 0);
            }
            connection.commit();

            return bResult;

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(format("%s: %s",
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

    /**
     * Insert records into PAYER_BILLING_CODE_LKUP table for a given BE_ID and PAYER_ID.
     * Connection is managed externally to rollback if any error is thrown.
     *
     * @param connection
     * @param bsnEntId
     * @param payerId
     * @param billingCodeList
     * @throws SandataRuntimeException
     */
    public void insertPayerBillingCodeLkupIdList(Connection connection, String bsnEntId, String payerId,
                                                 List<String> billingCodeList) throws SandataRuntimeException {

        try {
            for (String billingCode : billingCodeList) {
                PayerBillingCodeLookup payerBillingCodeLookup = new PayerBillingCodeLookup();

                payerBillingCodeLookup.setRecordCreateTimestamp(new Date());
                payerBillingCodeLookup.setRecordUpdateTimestamp(new Date());
                payerBillingCodeLookup.setRecordEffectiveTimestamp(new Date());
                payerBillingCodeLookup.setChangeReasonMemo("MW: insertPayerBillingCodeLkupIdList");
                payerBillingCodeLookup.setChangeVersionID(new BigInteger("1"));
                payerBillingCodeLookup.setBusinessEntityID(bsnEntId);
                payerBillingCodeLookup.setBillingCode(billingCode);
                payerBillingCodeLookup.setPayerID(payerId);

                OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(payerBillingCodeLookup);
                PayerBillingCodeLkupT jpubObj = (PayerBillingCodeLkupT) new DataMapper().map(payerBillingCodeLookup);

                execute(connection, ConnectionType.COREDATA, oracleMetadata.packageName(), oracleMetadata.insertMethod(), jpubObj);
            }


        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);
        }
    }

    /**
     * Update will first call delete to remove any existing records for the given BE_ID and PAYER_ID, then
     * insert the new values provided.
     *
     * @param bsnEntId
     * @param payerId
     * @param billingCodeList
     * @throws SandataRuntimeException
     */
    public void updatePayerBillingCodeLstIdList(String bsnEntId, String payerId, List<String> billingCodeList)
            throws SandataRuntimeException {

        Connection connection = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            // which items were de-seleteced will be remove from inactive contract
            deletePayerBillingCodeContractFromInactiveContract(connection, bsnEntId, payerId, billingCodeList);

            // delete rows logically
            deletePayerBillingCodeLkupByBsnEntIdAndPayerId(connection, bsnEntId, payerId);

            // add new records
            insertPayerBillingCodeLkupIdList(connection, bsnEntId, payerId, billingCodeList);

            connection.commit();

        } catch (Exception e) {
            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("%s: %s", e.getClass().getName(), e.getMessage()), e);

        } finally {
            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }
    
    /**
     * remove Payer Billing Code Contract from Inactive contract if User un-selected the items
     * @param connection
     * @param bsnEntId
     * @param payerId
     * @param billingCodeList
     * @return
     */
    private int deletePayerBillingCodeContractFromInactiveContract(Connection connection, String bsnEntId, String payerId, List<String> billingCodeList) {
        CallableStatement callableStatement = null;
        
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.STRING_ARRAY", connection);
            ARRAY billingCodeArray = new ARRAY(des, connection, billingCodeList.toArray());
            
            String callMethod = "{?=call PKG_AM_UTIL.DEL_PAYER_BILLING_CODE_CONTR(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);

            int index = 2;
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, payerId);
            callableStatement.setArray(index++, billingCodeArray);

            return callableStatement.executeUpdate();

        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s", e.getClass().getName(), e.getMessage()), e);
        } finally {
            // Close the statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    /**
     * Deletes ALL records in PAYER_BILLING_CODE_LKUP table by BE_ID and PAYER_ID. Connection is managed externally
     * to rollback if any error is thrown.
     *
     * @param connection
     * @param bsnEntId
     * @param payerId
     * @throws SandataRuntimeException
     */
    private void deletePayerBillingCodeLkupByBsnEntIdAndPayerId(Connection connection, String bsnEntId, String payerId)
            throws SandataRuntimeException {

        CallableStatement callableStatement = null;

        try {
            String callMethod = "{?=call PKG_AM_UTIL.DELETE_PAYER_BILLING_CODE_LKUP(?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);

            int index = 2;
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, payerId);

            callableStatement.execute();

        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s", e.getClass().getName(), e.getMessage()), e);
        } finally {
            // Close the statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    /**
     * Deletes records in PAYER_BILLING_CODE_LKUP table by BE_ID and PAYER_ID and BILLING_CODE
     *
     * @param bsnEntId
     * @param payerId
     * @param billingCodeList
     * @return
     * @throws SandataRuntimeException
     */
    public int deletePayerBillingCodeLkupIdList(String bsnEntId, String payerId, List<String> billingCodeList)
            throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            ArrayDescriptor arrayDes = ArrayDescriptor.createDescriptor(ConnectionType.COREDATA + ".BILLING_CODE_LST", connection);
            ARRAY billingCodeArray = new ARRAY(arrayDes, connection, billingCodeList.toArray());

            String callMethod = "{?=call PKG_AM_UTIL.DEL_PAYER_BILL_CODE_LST_ID_LST(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);

            int index = 2;
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, payerId);
            callableStatement.setArray(index++, billingCodeArray);

            callableStatement.execute();
            int result = (Integer) callableStatement.getObject(1);

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

            throw new SandataRuntimeException(String.format("%s: %s", e.getClass().getName(), e.getMessage()), e);

        } finally {
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


    /**
     * Insert records into PAYER_LOB_LST table for a given BE_ID and PAYER_ID.
     * Connection is managed externally to rollback if any error is thrown.
     *
     * @param connection
     * @param bsnEntId
     * @param payerId
     * @param lobList
     * @throws SandataRuntimeException
     */
    public void insertPayerLobLstList(Connection connection, String bsnEntId, String payerId,
                                      List<String> lobList) throws SandataRuntimeException {

        try {
            for (String lob : lobList) {
                PayerLineOfBusinessList payerLobList = new PayerLineOfBusinessList();

                payerLobList.setRecordCreateTimestamp(new Date());
                payerLobList.setRecordUpdateTimestamp(new Date());
                payerLobList.setRecordEffectiveTimestamp(new Date());
                payerLobList.setChangeReasonMemo("MW: insertPayerLobLstList");
                payerLobList.setChangeVersionID(new BigInteger("1"));
                payerLobList.setBusinessEntityID(bsnEntId);
                payerLobList.setBusinessEntityLineOfBusiness(lob);
                payerLobList.setPayerID(payerId);

                OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(payerLobList);
                PayerLobLstT jpubObj = (PayerLobLstT) new DataMapper().map(payerLobList);

                execute(connection, ConnectionType.COREDATA, oracleMetadata.packageName(), oracleMetadata.insertMethod(), jpubObj);
            }


        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);
        }
    }

    /**
     * Update will first call delete to remove any existing records for the given BE_ID and PAYER_ID, then
     * insert the new values provided.
     *
     * @param bsnEntId
     * @param payerId
     * @param lobList
     * @throws SandataRuntimeException
     */
    public void updatePayerLobLstList(String bsnEntId, String payerId, List<String> lobList)
            throws SandataRuntimeException {

        Connection connection = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);
            
            // remove inactive payer lob contract if User un-selected LOB from Payer
            deleteInactivePayerLobContract(connection, bsnEntId, payerId, lobList);

            // delete rows logically
            deletePayerLobLstListByBsnEntIdAndPayerId(connection, bsnEntId, payerId);

            // add new records
            insertPayerLobLstList(connection, bsnEntId, payerId, lobList);

            connection.commit();

        } catch (Exception e) {
            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("%s: %s", e.getClass().getName(), e.getMessage()), e);

        } finally {
            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }
    
    /**
     * remove Payer Rate Type Contract from Inactive contract if User un-selected the items
     * @param connection
     * @param bsnEntId
     * @param payerId
     * @param lobList
     * @return
     */
    private int deleteInactivePayerLobContract(Connection connection, String bsnEntId, String payerId, List<String> lobList) {
        CallableStatement callableStatement = null;
        
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.STRING_ARRAY", connection);
            ARRAY lobArray = new ARRAY(des, connection, lobList.toArray());
            
            String callMethod = "{?=call PKG_AM_UTIL.DEL_PAYER_LOB_CONTR(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);

            int index = 2;
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, payerId);
            callableStatement.setArray(index++, lobArray);

            return callableStatement.executeUpdate();

        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s", e.getClass().getName(), e.getMessage()), e);
        } finally {
            // Close the statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    /**
     * Deletes ALL records in PAYER_LOB_LST table by BE_ID and PAYER_ID. Connection is managed externally
     * to rollback if any error is thrown.
     *
     * @param connection
     * @param bsnEntId
     * @param payerId
     * @throws SandataRuntimeException
     */
    private void deletePayerLobLstListByBsnEntIdAndPayerId(Connection connection, String bsnEntId, String payerId)
            throws SandataRuntimeException {

        CallableStatement callableStatement = null;

        try {
            String callMethod = "{?=call PKG_AM_UTIL.DELETE_PAYER_LOB_LST(?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);

            int index = 2;
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, payerId);

            callableStatement.execute();

        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s", e.getClass().getName(), e.getMessage()), e);
        } finally {
            // Close the statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    /**
     * Deletes records in PAYER_LOB_LST table by BE_ID and PAYER_ID and BE_LOB
     *
     * @param bsnEntId
     * @param payerId
     * @param lobList
     * @return
     * @throws SandataRuntimeException
     */
    public int deletePayerLobLstList(String bsnEntId, String payerId, List<String> lobList)
            throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            ArrayDescriptor arrayDes = ArrayDescriptor.createDescriptor(ConnectionType.COREDATA + ".BE_LOB_LST", connection);
            ARRAY lobArray = new ARRAY(arrayDes, connection, lobList.toArray());

            String callMethod = "{?=call PKG_AM_UTIL.DELETE_PAYER_LOB_LST(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);

            int index = 2;
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, payerId);
            callableStatement.setArray(index++, lobArray);

            callableStatement.execute();
            int result = (Integer) callableStatement.getObject(1);

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

            throw new SandataRuntimeException(String.format("%s: %s", e.getClass().getName(), e.getMessage()), e);

        } finally {
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


    /**
     * Insert records into PAYER_MDFR_LKUP table for a given BE_ID and PAYER_ID.
     * Connection is managed externally to rollback if any error is thrown.
     *
     * @param connection
     * @param bsnEntId
     * @param payerId
     * @param modifierCodeList
     * @throws SandataRuntimeException
     */
    public void insertPayerMdfrLstList(Connection connection, String bsnEntId, String payerId,
                                       List<String> modifierCodeList) throws SandataRuntimeException {

        try {
            for (String modifierCode : modifierCodeList) {
                PayerModifierLookup payerMdfrList = new PayerModifierLookup();

                payerMdfrList.setRecordCreateTimestamp(new Date());
                payerMdfrList.setRecordUpdateTimestamp(new Date());
                payerMdfrList.setRecordEffectiveTimestamp(new Date());
                payerMdfrList.setChangeReasonMemo("MW: insertPayerMdfrLstList");
                payerMdfrList.setChangeVersionID(new BigInteger("1"));
                payerMdfrList.setBusinessEntityID(bsnEntId);
                payerMdfrList.setPayerID(payerId);
                payerMdfrList.setModifierCode(modifierCode);

                OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(payerMdfrList);
                PayerMdfrLkupT jpubObj = (PayerMdfrLkupT) new DataMapper().map(payerMdfrList);

                execute(connection, ConnectionType.COREDATA, oracleMetadata.packageName(), oracleMetadata.insertMethod(), jpubObj);
            }


        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);
        }
    }

    /**
     * Update will first call delete to remove any existing records for the given BE_ID and PAYER_ID, then
     * insert the new values provided.
     *
     * @param bsnEntId
     * @param payerId
     * @param modifierCodeList
     * @throws SandataRuntimeException
     */
    public void updatePayerMdfrLstList(String bsnEntId, String payerId, List<String> modifierCodeList)
            throws SandataRuntimeException {

        Connection connection = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);
            
            // which items were de-seleteced will be remove from inactive contract
            deletePayerModifierContractFromInactiveContract(connection, bsnEntId, payerId, modifierCodeList);

            // delete rows logically
            deletePayerMdfrLstListByBsnEntIdAndPayerId(connection, bsnEntId, payerId);

            // add new records
            insertPayerMdfrLstList(connection, bsnEntId, payerId, modifierCodeList);

            connection.commit();

        } catch (Exception e) {
            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("%s: %s", e.getClass().getName(), e.getMessage()), e);

        } finally {
            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }
    
    /**
     * remove Payer Modifier Contract from Inactive contract if User un-selected the items
     * @param connection
     * @param bsnEntId
     * @param payerId
     * @param modifierCodeList
     * @return
     */
    private int deletePayerModifierContractFromInactiveContract(Connection connection, String bsnEntId, String payerId, List<String> modifierCodeList) {
        CallableStatement callableStatement = null;
        
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.STRING_ARRAY", connection);
            ARRAY modifierCodeArray = new ARRAY(des, connection, modifierCodeList.toArray());
            
            String callMethod = "{?=call PKG_AM_UTIL.DEL_PAYER_MDFR_CONTR(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);

            int index = 2;
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, payerId);
            callableStatement.setArray(index++, modifierCodeArray);

            return callableStatement.executeUpdate();

        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s", e.getClass().getName(), e.getMessage()), e);
        } finally {
            // Close the statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    /**
     * Deletes ALL records in PAYER_MDFR_LKUP table by BE_ID and PAYER_ID. Connection is managed externally
     * to rollback if any error is thrown.
     *
     * @param connection
     * @param bsnEntId
     * @param payerId
     * @throws SandataRuntimeException
     */
    private void deletePayerMdfrLstListByBsnEntIdAndPayerId(Connection connection, String bsnEntId, String payerId)
            throws SandataRuntimeException {

        CallableStatement callableStatement = null;

        try {
            String callMethod = "{?=call PKG_AM_UTIL.DELETE_PAYER_MDFR_LKUP(?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);

            int index = 2;
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, payerId);

            callableStatement.execute();

        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s", e.getClass().getName(), e.getMessage()), e);
        } finally {
            // Close the statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    /**
     * Deletes records in PAYER_MDFR_LKUP table by BE_ID and PAYER_ID and BE_LOB
     *
     * @param bsnEntId
     * @param payerId
     * @param modifierCodeList
     * @return
     * @throws SandataRuntimeException
     */
    public int deletePayerMdfrLstList(String bsnEntId, String payerId, List<String> modifierCodeList)
            throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            ArrayDescriptor arrayDes = ArrayDescriptor.createDescriptor(ConnectionType.COREDATA + ".MDFR_CODE_LST_T", connection);
            ARRAY modifierCodeArray = new ARRAY(arrayDes, connection, modifierCodeList.toArray());

            String callMethod = "{?=call PKG_AM_UTIL.DELETE_PAYER_MDFR_LKUP(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);

            int index = 2;
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, payerId);
            callableStatement.setArray(index++, modifierCodeArray);

            callableStatement.execute();
            int result = (Integer) callableStatement.getObject(1);

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

            throw new SandataRuntimeException(String.format("%s: %s", e.getClass().getName(), e.getMessage()), e);

        } finally {
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


    /**
     * Insert records into PAYER_RATE_TYP_LST table for a given BE_ID and PAYER_ID.
     * Connection is managed externally to rollback if any error is thrown.
     *
     * @param connection
     * @param bsnEntId
     * @param payerId
     * @param rateTypeNameList
     * @throws SandataRuntimeException
     */
    public void insertPayerRateTypLstList(Connection connection, String bsnEntId, String payerId,
                                          List<String> rateTypeNameList) throws SandataRuntimeException {

        try {
            Date termDate = new SimpleDateFormat("yyyy-MM-dd").parse("9999-12-31");

            for (String rateTypName : rateTypeNameList) {
                PayerRateTypeList payerRateTypList = new PayerRateTypeList();

                payerRateTypList.setRecordCreateTimestamp(new Date());
                payerRateTypList.setRecordUpdateTimestamp(new Date());
                payerRateTypList.setRecordEffectiveTimestamp(new Date());
                payerRateTypList.setRecordTerminationTimestamp(termDate);
                payerRateTypList.setCurrentRecordIndicator(true);
                payerRateTypList.setChangeVersionID(BigInteger.ZERO);

                payerRateTypList.setBusinessEntityID(bsnEntId);
                payerRateTypList.setPayerID(payerId);
                payerRateTypList.setRateTypeName(rateTypName);
                payerRateTypList.setRateQualifierCode(RateQualifierCode.PAY);

                payerRateTypList.setRecordCreatedBy("Middleware Service");
                payerRateTypList.setChangeReasonMemo("AM: OracleDataService: insertPayerRateTypLstList");

                OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(payerRateTypList);
                PayerRateTypLstT jpubObj = (PayerRateTypLstT) new DataMapper().map(payerRateTypList);

                execute(connection, ConnectionType.COREDATA, oracleMetadata.packageName(), oracleMetadata.insertMethod(), jpubObj);
            }


        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);
        }
    }

    /**
     * Update will first call delete to remove any existing records for the given BE_ID and PAYER_ID, then
     * insert the new values provided.
     *
     * @param bsnEntId
     * @param payerId
     * @param rateTypeNameList
     * @throws SandataRuntimeException
     */
    public void updatePayerRateTypLstList(String bsnEntId, String payerId, List<String> rateTypeNameList)
            throws SandataRuntimeException {

        Connection connection = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);
            
            // which items were de-seleteced will be remove from inactive contract
            deletePayerRateTypeContractFromInactiveContract(connection, bsnEntId, payerId, rateTypeNameList);

            // delete rows logically
            deletePayerRateTypLstListByBsnEntIdAndPayerId(connection, bsnEntId, payerId);

            // add new records
            insertPayerRateTypLstList(connection, bsnEntId, payerId, rateTypeNameList);

            connection.commit();

        } catch (Exception e) {
            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("%s: %s", e.getClass().getName(), e.getMessage()), e);

        } finally {
            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }
    
    /**
     * remove Payer Rate Type Contract from Inactive contract if User un-selected the items
     * @param connection
     * @param bsnEntId
     * @param payerId
     * @param rateTypeNameList
     * @return
     */
    private int deletePayerRateTypeContractFromInactiveContract(Connection connection, String bsnEntId, String payerId, List<String> rateTypeNameList) {
        CallableStatement callableStatement = null;
        
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.STRING_ARRAY", connection);
            ARRAY rateTypeNameArray = new ARRAY(des, connection, rateTypeNameList.toArray());
            
            String callMethod = "{?=call PKG_AM_UTIL.DEL_PAYER_RATE_TYP_CONTR(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);

            int index = 2;
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, payerId);
            callableStatement.setArray(index++, rateTypeNameArray);

            return callableStatement.executeUpdate();

        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s", e.getClass().getName(), e.getMessage()), e);
        } finally {
            // Close the statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    /**
     * Deletes ALL records in PAYER_RATE_TYP_LST table by BE_ID and PAYER_ID. Connection is managed externally
     * to rollback if any error is thrown.
     *
     * @param connection
     * @param bsnEntId
     * @param payerId
     * @throws SandataRuntimeException
     */
    private void deletePayerRateTypLstListByBsnEntIdAndPayerId(Connection connection, String bsnEntId, String payerId)
            throws SandataRuntimeException {

        CallableStatement callableStatement = null;

        try {
            String callMethod = "{?=call PKG_AM_UTIL.DELETE_PAYER_RATE_TYP_LST(?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);

            int index = 2;
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, payerId);

            callableStatement.execute();

        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s", e.getClass().getName(), e.getMessage()), e);
        } finally {
            // Close the statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    /**
     * Deletes records in PAYER_RATE_TYP_LST table by BE_ID and PAYER_ID and RATE_TYP_NAME
     *
     * @param payerListItemRequest
     * @return
     * @throws SandataRuntimeException
     */
    public int deletePayerRateTypLstList(Connection connection, PayerListItemRequest payerListItemRequest)
            throws SandataRuntimeException {

        CallableStatement callableStatement = null;

        try {

            ArrayDescriptor arrayDes = ArrayDescriptor.createDescriptor(ConnectionType.COREDATA + ".RATE_TYP_NAME_ARRAY", connection);
            ARRAY rateTypeNameArray = new ARRAY(arrayDes, connection, payerListItemRequest.getListItems().toArray());

            String callMethod = "{?=call PKG_AM_UTIL.DELETE_PAYER_RATE_TYP_LST(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);

            int index = 2;
            callableStatement.setString(index++, payerListItemRequest.getBusinessEntityId());
            callableStatement.setString(index++, payerListItemRequest.getPayerId());
            callableStatement.setArray(index++, rateTypeNameArray);

            callableStatement.execute();
            return (Integer) callableStatement.getObject(1);

        } catch (Exception e) {

            throw new SandataRuntimeException(String.format("%s: %s", e.getClass().getName(), e.getMessage()), e);

        } finally {
            // Close the statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }


    /**
     * Deletes records in STAFF_TRNG_CTGY_SVC table by STAFF_TRNG_CTGY_SVC_SK
     *
     * @param exchange (List) of SK need to be deleted
     * @return
     * @throws SandataRuntimeException
     */
    public int deleteStaffTrngCtgySvcList(Connection connection, final Exchange exchange)
            throws SandataRuntimeException {

        List<BigInteger> requestStaffTrngCtgySvcList = (List<BigInteger>) exchange.getIn().getBody();
        if (requestStaffTrngCtgySvcList == null || requestStaffTrngCtgySvcList.isEmpty()) {
            throw new SandataRuntimeException("The request StaffTrngCtgySvcList is empty");
        }

        CallableStatement callableStatement = null;

        try {

            ArrayDescriptor arrayDes = ArrayDescriptor.createDescriptor(ConnectionType.COREDATA + ".SK_ARRAY", connection);
            ARRAY staffTrngCtgySvcSkArray = new ARRAY(arrayDes, connection, requestStaffTrngCtgySvcList.toArray());

            String callMethod = "{?=call PKG_AM_UTIL.DELETE_STAFF_TRNG_CTGY_SVC_LST(?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);

            callableStatement.setArray(2, staffTrngCtgySvcSkArray);

            callableStatement.execute();
            return (Integer) callableStatement.getObject(1);

        } catch (Exception e) {

            throw new SandataRuntimeException(String.format("%s: %s", e.getClass().getName(), e.getMessage()), e);

        } finally {
            // Close the statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }


    /**
     * Returns Response containing List of BusinessEntityStaffTraining for the specified
     * business entity ID, optional staff training category code, page, page size,
     * sort on table, and sort direction.
     *
     * @param bsnEntId  Specified business entity ID.
     * @param page      Specified page number.
     * @param pageSize  Specified page size.
     * @param sortOn    Specified sort column.
     * @param direction Specified sort direction.
     * @return Response with paging info and List of BusinessEntityStaffTraining.
     */
    public Response getBeStaffTrngWithPaginationSortAndOption(String bsnEntId,
                                                              String staffTrngName,
                                                              Integer page,
                                                              Integer pageSize,
                                                              String sortOn,
                                                              String direction) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            // Calculate row range.
            int toRow = page * pageSize;
            int fromRow = toRow - (pageSize - 1);

            // Parameters
            List<String> parameterList = new ArrayList<>();

            // Build raw SQL query.
            StringBuilder sqlStringBuilder = new StringBuilder();
            sqlStringBuilder.append("SELECT * FROM " +
                    "  (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (SELECT * FROM (SELECT * FROM (SELECT * " +
                    "    FROM BE_STAFF_TRNG_LKUP WHERE ");

            // Add filter for staff training name.
            if (staffTrngName != null
                    && !staffTrngName.isEmpty()) {
                sqlStringBuilder.append("UPPER(BE_STAFF_TRNG_LKUP.STAFF_TRNG_NAME) LIKE UPPER(?) AND ");
                parameterList.add(String.format("%%%s%%", staffTrngName));
            }

            sqlStringBuilder.append(String.format("BE_STAFF_TRNG_LKUP.BE_ID=? " +
                            "AND (TO_CHAR(BE_STAFF_TRNG_LKUP.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND BE_STAFF_TRNG_LKUP.CURR_REC_IND = '1') ORDER BY BE_STAFF_TRNG_LKUP.%s %s))) R1) WHERE ROW_NUMBER BETWEEN %s AND %s",
                    sortOn,
                    direction,
                    fromRow,
                    toRow));

            parameterList.add(bsnEntId);

            preparedStatement = connection.prepareStatement(sqlStringBuilder.toString());

            int index = 1;
            for (String parameter : parameterList) {
                preparedStatement.setString(index++, parameter);
            }

            resultSet = preparedStatement.executeQuery();

            Response response = new Response();
            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);
            response.setPage(page);
            response.setPageSize(pageSize);

            mapResultSetToBusinessEntityStaffTraining(response, resultSet);

            connection.commit();

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

            throw new SandataRuntimeException(format("%s: %s",
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

    /**
     * Returns a Response with data of List of BusinessEntityStaffTrainingLookup
     * with row count and page details all extracted from the specified ResultSet.
     *
     * @param resultSet Specified ResultSet.
     * @throws SQLException
     */
    private void mapResultSetToBusinessEntityStaffTraining(Response response, ResultSet resultSet) throws SQLException {
        List<BusinessEntityStaffTrainingLookup> businessEntityStaffTrainingList = new ArrayList<>();

        while (resultSet.next()) {

            if (response.getTotalRows() == 0) {
                response.setTotalRows(resultSet.getInt("TOTAL_ROWS"));
            }


            BusinessEntityStaffTrainingLookup businessEntityStaffTrainingLookup = new BusinessEntityStaffTrainingLookup();
            businessEntityStaffTrainingLookup.setBusinessEntityStaffTrainingLookupSK(BigInteger.valueOf(resultSet.getBigDecimal("BE_STAFF_TRNG_LKUP_SK").longValue()));
            businessEntityStaffTrainingLookup.setRecordCreateTimestamp(resultSet.getTimestamp("REC_CREATE_TMSTP"));
            businessEntityStaffTrainingLookup.setRecordUpdateTimestamp(resultSet.getTimestamp("REC_UPDATE_TMSTP"));
            businessEntityStaffTrainingLookup.setRecordEffectiveTimestamp(resultSet.getTimestamp("REC_EFF_TMSTP"));
            businessEntityStaffTrainingLookup.setRecordTerminationTimestamp(resultSet.getTimestamp("REC_TERM_TMSTP"));
            businessEntityStaffTrainingLookup.setRecordCreatedBy(resultSet.getString("REC_CREATED_BY"));
            businessEntityStaffTrainingLookup.setRecordUpdatedBy(resultSet.getString("REC_UPDATED_BY"));
            businessEntityStaffTrainingLookup.setChangeReasonMemo(resultSet.getString("CHANGE_REASON_MEMO"));
            businessEntityStaffTrainingLookup.setCurrentRecordIndicator(resultSet.getBoolean("CURR_REC_IND"));
            businessEntityStaffTrainingLookup.setChangeVersionID(BigInteger.valueOf(resultSet.getBigDecimal("CHANGE_VERSION_ID").longValue()));
            businessEntityStaffTrainingLookup.setBusinessEntityID(resultSet.getString("BE_ID"));
            businessEntityStaffTrainingLookup.setStaffTrainingCode(resultSet.getString("STAFF_TRNG_CODE"));
            businessEntityStaffTrainingLookup.setStaffTrainingName(resultSet.getString("STAFF_TRNG_NAME"));
            businessEntityStaffTrainingLookup.setStaffTrainingDescription(resultSet.getString("STAFF_TRNG_DESC"));
            businessEntityStaffTrainingLookup.setStaffTrainingQualifier(StaffTrainingQualifier.fromValue(resultSet.getString("STAFF_TRNG_QLFR")));
            businessEntityStaffTrainingLookup.setStaffTrainingMandatoryIndicator(resultSet.getBoolean("STAFF_TRNG_MAND_IND"));
            businessEntityStaffTrainingLookup.setStaffTrainingTotalHours(resultSet.getBigDecimal("STAFF_TRNG_TOTAL_HRS"));
            businessEntityStaffTrainingLookup.setStaffTrainingNotes(resultSet.getString("STAFF_TRNG_NOTES"));

            businessEntityStaffTrainingList.add(businessEntityStaffTrainingLookup);
        }

        response.setData(businessEntityStaffTrainingList);
    }

    /**
     * Removes link between any BusinessEntityComplianceLookup entities and a
     * BusinessEntityComplianceCategoryLookup with the specified sequence key.
     *
     * @param sequenceKey Specified sequence key for BusinessEntityComplianceCategoryLookup.
     * @return Number of BusinessEntityComplianceLookup entities updated.
     */
    public int removeCategoryToComplianceLink(long sequenceKey) {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String callMethod = "{?=call PKG_AM_UTIL.REMOVE_CTGY_TO_COMP_LNK(?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);

            callableStatement.setLong(2, sequenceKey);

            callableStatement.execute();

            connection.commit();

            return (Integer) callableStatement.getObject(1);

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

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

    /**
     * Removes link between any BusinessEntityStaffTrainingLookup entities and a
     * BusinessEntityStaffTrainingCategoryLookup with the specified sequence key.
     *
     * @param sequenceKey Specified sequence key for BusinessEntityStaffTrainingLookup.
     * @return Number of BusinessEntityStaffTrainingCategoryLookup entities deleted.
     */
    public int removeStaffTrainingToCategoryLink(long sequenceKey) {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String callMethod = "{?=call PKG_AM_UTIL.REMOVE_STAFF_TRNG_TO_CTGY_LNK(?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setLong(2, sequenceKey);

            callableStatement.execute();
            int result = (Integer) callableStatement.getObject(1);

            // commit the transaction
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

            throw new SandataRuntimeException(format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {
            // Close the prepared statement
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


    public int removedBeCompLkupFullfill(long sequenceKey) {

        Connection connection = null;
        Statement statement = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            ConnectionType connectionType = ConnectionType.COREDATA;
            // this BE_COMP_LKUP is deleted as REC_TERM_TMSTP and CURR_REC_IND are updated
            String sqlGetDeletedCompLkup = "SELECT * FROM BE_COMP_LKUP WHERE BE_COMP_LKUP_SK = ? ";
            List<BusinessEntityComplianceLookup> compLkupList = (List<BusinessEntityComplianceLookup>) getEntitiesForId(
                    connectionType,
                    sqlGetDeletedCompLkup,
                    "com.sandata.lab.data.model.dl.model.BusinessEntityComplianceLookup",
                    sequenceKey);
            if (compLkupList == null || compLkupList.size() == 0) {
                throw new SandataRuntimeException(String.format("BE_COMP_LKUP_SK = %s does not exist", sequenceKey));
            }

            BusinessEntityComplianceLookup deletedBECompLkup = compLkupList.get(0);

            // this should be the comp_code of compliance which is deleted
            String sqlGetBECompRel = "SELECT * FROM BE_COMP_REL WHERE COMP_PAR_CODE = ?";
            List<BusinessEntityComplianceRelationship> listBECompRel = (List<BusinessEntityComplianceRelationship>) getEntitiesForId(
                    connectionType,
                    connection,
                    sqlGetBECompRel,
                    "com.sandata.lab.data.model.dl.model.BusinessEntityComplianceRelationship",
                    deletedBECompLkup.getComplianceCode());
            int numRowsUpdated = 0;
            // here we update the to be fullfill column to empty
            if (listBECompRel.size() > 0) {
                List<BigInteger> beCompRelSKList = new ArrayList<BigInteger>();
                for (BusinessEntityComplianceRelationship businessEntityComplianceRelationship : listBECompRel) {
                    beCompRelSKList.add(businessEntityComplianceRelationship.getBusinessEntityComplianceRelationshipSK());
                }

                ArrayDescriptor arrayDes = ArrayDescriptor.createDescriptor(ConnectionType.COREDATA + ".BE_COMP_REL_SK_LST_T", connection);
                ARRAY beCompRelSKArray = new ARRAY(arrayDes, connection, beCompRelSKList.toArray());

                String callMethod = "{?=call PKG_AM_UTIL.REMOVE_BE_COMP_LKUP_FULLFILL(?)}";
                callableStatement = connection.prepareCall(callMethod);
                callableStatement.registerOutParameter(1, OracleTypes.INTEGER);

                callableStatement.setArray(2, beCompRelSKArray);

                callableStatement.execute();
                numRowsUpdated = (Integer) callableStatement.getObject(1);
            }


            // commit the transaction
            connection.commit();

            return numRowsUpdated;

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the statement
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the callableStatement
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

    /**
     * Get list of training classes by service for a business entity
     *
     * @param bsnEntId
     * @param serviceName
     * @return
     */
    public Response getBEStaffTrngClassesByService(String bsnEntId,
                                                   ServiceName serviceName,
                                                   int page,
                                                   int pageSize,
                                                   String sortOn,
                                                   String direction) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();

            // Calculate row range.
            int toRow = page * pageSize;
            int fromRow = toRow - (pageSize - 1);

            String sortColumn = "T3.REC_EFF_TMSTP";
            if (sortOn.equals("STAFF_TRNG_NAME")) {
                sortColumn = "UPPER(T3.STAFF_TRNG_NAME)";
            }

            // Build SQL query.
            String sql = String.format("SELECT * FROM" +
                            "  (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (SELECT * FROM (SELECT * FROM COREDATA.STAFF_TRNG_CTGY_SVC T1 " +
                            "  JOIN COREDATA.BE_STAFF_TRNG_CTGY_LST T2 ON T1.BE_ID = T2.BE_ID AND T1.STAFF_TRNG_CTGY_CODE = T2.STAFF_TRNG_CTGY_CODE " +
                            "  JOIN COREDATA.BE_STAFF_TRNG_LKUP T3 ON T2.BE_ID = T3.BE_ID AND T2.STAFF_TRNG_CODE = T3.STAFF_TRNG_CODE  " +
                            "  WHERE UPPER(T1.SVC_NAME) = ? AND T3.BE_ID = ?" +
                            "  AND (TO_CHAR(T3.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T3.CURR_REC_IND = '1') ORDER BY %s %s)) R1)" +
                            "  WHERE ROW_NUMBER BETWEEN %d AND %d",
                    sortColumn,
                    direction,
                    fromRow,
                    toRow);

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, serviceName.value().toUpperCase());
            preparedStatement.setString(2, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            //  List<BusinessEntityStaffTraining> businessEntityStaffTrainings =
            //          (List<BusinessEntityStaffTraining>) new DataMapper().map(resultSet,"com.sandata.lab.data.model.dl.model.BusinessEntityStaffTraining");

            Response response = new Response();
            mapResultSetToBusinessEntityStaffTraining(response, resultSet);

            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);


            return response;

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

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    /**
     * Get list of training classes by category for a business entity
     *
     * @param bsnEntId
     * @param category
     * @param page
     * @param pageSize
     * @param sortOn
     * @param direction
     * @return
     */
    public Response getBEStaffTrngClassesByCategory(String bsnEntId,
                                                    String category,
                                                    String staffTrngName,
                                                    int page,
                                                    int pageSize,
                                                    String sortOn,
                                                    String direction) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            // Calculate row range.
            int toRow = page * pageSize;
            int fromRow = toRow - (pageSize - 1);

            String sortColumn;
            if (sortOn.equals("name")) {
                sortColumn = "UPPER(T2.STAFF_TRNG_NAME)";
            } else if (sortOn.equals("ctgy_name")) {
                sortColumn = "T3.STAFF_TRNG_CTGY_NAME";
            } else if (sortOn.equals("total_hours")) {
                sortColumn = "T2.STAFF_TRNG_TOTAL_HRS";
            } else {
                sortColumn = "T2.REC_EFF_TMSTP";
            }

            StringBuilder filterItems = new StringBuilder();
            List<String> params = new ArrayList<>();

            filterItems.append("    WHERE T2.BE_ID = ?");
            params.add(bsnEntId);
            if (category != null
                    && !category.isEmpty()) {
                filterItems.append("      AND UPPER(T1.STAFF_TRNG_CTGY_CODE) LIKE ?");
                params.add(String.format("%s%%", category.toUpperCase()));
            }

            if (staffTrngName != null
                    && !staffTrngName.isEmpty()) {
                filterItems.append("    AND UPPER(T2.STAFF_TRNG_NAME) LIKE ?");
                params.add(String.format("%s%%", staffTrngName.toUpperCase()));
            }

            String sql = String.format("SELECT * FROM"
                            + "  (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (SELECT * FROM"
                            + "    (SELECT (SELECT LISTAGG(SVC_NAME, ',') WITHIN GROUP ("
                            + "        ORDER BY SVC_NAME)"
                            + "        FROM STAFF_TRNG_CTGY_SVC"
                            + "        WHERE BE_ID=T1.BE_ID"
                            + "        AND STAFF_TRNG_CTGY_CODE=T1.STAFF_TRNG_CTGY_CODE"
                            + "        AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD')='9999-12-31'"
                            + "        AND CURR_REC_IND=1"
                            + "        ) AS SVC_NAME_LST,"
                            + "            T1.STAFF_TRNG_CTGY_CODE,"
                            + "            T2.BE_STAFF_TRNG_LKUP_SK,"
                            + "            T2.BE_ID,"
                            + "            T2.STAFF_TRNG_CODE,"
                            + "            T2.STAFF_TRNG_NAME,"
                            + "            T2.STAFF_TRNG_DESC,"
                            + "            T2.STAFF_TRNG_QLFR,"
                            + "            T2.STAFF_TRNG_MAND_IND,"
                            + "            T2.STAFF_TRNG_TOTAL_HRS,"
                            + "            T2.STAFF_TRNG_NOTES,"
                            + "            T3.STAFF_TRNG_CTGY_NAME"
                            + "    FROM COREDATA.BE_STAFF_TRNG_CTGY_LST T1"
                            + "    INNER JOIN (SELECT * FROM COREDATA.BE_STAFF_TRNG_LKUP"
                            + "                WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1') T2"
                            + "        ON T1.BE_ID = T2.BE_ID AND T1.STAFF_TRNG_CODE = T2.STAFF_TRNG_CODE"
                            + "    INNER JOIN (SELECT * FROM COREDATA.BE_STAFF_TRNG_CTGY_LKUP"
                            + "                WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1') T3"
                            + "        ON T1.BE_ID = T3.BE_ID AND T1.STAFF_TRNG_CTGY_CODE = T3.STAFF_TRNG_CTGY_CODE"
                            + "%s"
                            + "      AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = '1')             "
                            + "    ORDER BY %s %s)) R1) WHERE ROW_NUMBER BETWEEN %s AND %s",
                    filterItems.toString(),
                    sortColumn,
                    direction,
                    fromRow,
                    toRow);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (String param : params) {
                preparedStatement.setString(index++, param);
            }

            resultSet = preparedStatement.executeQuery();

            List<BEStaffTrainingClassesByServiceFindResult> resultList = new ArrayList<>();

            Response response = new Response();
            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);
            response.setData(resultList);

            while (resultSet.next()) {
                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getInt("TOTAL_ROWS"));
                }

                resultList.add(mapBeStaffTrainingClassesByServiceFindResult(resultSet));
            }

            connection.commit();

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

    /**
     * Maps ResultSet record to BEStaffTrainingClassesByServiceFindResult object
     *
     * @param rs
     * @return
     */
    private BEStaffTrainingClassesByServiceFindResult mapBeStaffTrainingClassesByServiceFindResult(ResultSet rs) throws SQLException {

        BEStaffTrainingClassesByServiceFindResult result = new BEStaffTrainingClassesByServiceFindResult();
        result.setBusinessEntityStaffTrainingLookupSK(BigInteger.valueOf(rs.getBigDecimal("BE_STAFF_TRNG_LKUP_SK").longValue()));
        result.setBusinessEntityID(rs.getString("BE_ID"));
        result.setStaffTrainingCode(rs.getString("STAFF_TRNG_CODE"));
        result.setStaffTrainingName(rs.getString("STAFF_TRNG_NAME"));
        result.setStaffTrainingDescription(rs.getString("STAFF_TRNG_DESC"));
        result.setStaffTrainingQualifier(rs.getString("STAFF_TRNG_QLFR"));
        result.setStaffTrainingMandatoryIndicator(rs.getBoolean("STAFF_TRNG_MAND_IND"));
        result.setStaffTrainingTotalHours(rs.getBigDecimal("STAFF_TRNG_TOTAL_HRS"));
        result.setStaffTrainingNotes(rs.getString("STAFF_TRNG_NOTES"));
        String serviceNameListString = rs.getString("SVC_NAME_LST");
        if (serviceNameListString != null
                && !serviceNameListString.isEmpty()) {
            List<String> serviceNameList = Arrays.asList(serviceNameListString.split(","));
            result.setServiceNameList(serviceNameList);
        }

        result.setStaffTrainingCategoryCode(rs.getString("STAFF_TRNG_CTGY_CODE"));
        result.setStaffTrainingCategoryName(rs.getString("STAFF_TRNG_CTGY_NAME"));

        return result;

    }

    /**
     * Get list of compliance items by service for a business entity
     *
     * @param bsnEntId
     * @param serviceName
     * @return
     */
    public Response getBECompByService(String bsnEntId,
                                       ServiceName serviceName,
                                       int page,
                                       int pageSize,
                                       String sortOn,
                                       String direction) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            // Calculate row range.
            int toRow = page * pageSize;
            int fromRow = toRow - (pageSize - 1);

            // Build SQL query.
            String sql = String.format("SELECT * FROM" +
                            "  (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM " +
                            "   (SELECT * FROM (SELECT * FROM COREDATA.BE_COMP_SVC_LST T1 " +
                            "  JOIN BE_COMP_LKUP T2 " +
                            "ON T1.BE_ID = T2.BE_ID AND T1.COMP_CODE = T2.COMP_CODE" +
                            " AND (TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T2.CURR_REC_IND = '1') " +
                            "  WHERE UPPER(T1.SVC_NAME) = ? AND T1.BE_ID = ?" +
                            "  AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = '1') ORDER BY T2.%s %s)) R1)" +
                            "  WHERE ROW_NUMBER BETWEEN %d AND %d",
                    sortOn,
                    direction,
                    fromRow,
                    toRow);

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, serviceName.value().toUpperCase());
            preparedStatement.setString(2, bsnEntId);

            resultSet = preparedStatement.executeQuery();


            Response response = new Response();

            mapResultSetToBusinessEntityComplianceLookup(response, resultSet);

            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);

            connection.commit();

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

    /**
     * Get list of compliance items by service for a business entity
     *
     * @param bsnEntId
     * @return
     */
    public Response getStaffTrainingLocationByBusinessEntity(String bsnEntId,
                                                             int page,
                                                             int pageSize,
                                                             String sortOn,
                                                             String direction) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            // Calculate row range.
            int toRow = page * pageSize;
            int fromRow = toRow - (pageSize - 1);

            // Build SQL query.
            String sql = String.format("SELECT * FROM" +
                            "  (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM " +
                            "   (SELECT * FROM (SELECT * FROM COREDATA.STAFF_TRNG_LOC T1 " +
                            "  WHERE T1.BE_ID = ?" +
                            "  AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = '1') ORDER BY T1.%s %s)) R1)" +
                            "  WHERE ROW_NUMBER BETWEEN %d AND %d",
                    sortOn,
                    direction,
                    fromRow,
                    toRow);

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, bsnEntId);

            resultSet = preparedStatement.executeQuery();


            Response response = new Response();

            List<StaffTrainingLocation> staffTrainingLocations =
                    (List<StaffTrainingLocation>) new DataMapper().mapWithOffset(resultSet, "com.sandata.lab.data.model.dl.model.StaffTrainingLocation", 2);


            response.setData(staffTrainingLocations);
            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);

            connection.commit();

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

    private void mapResultSetToBusinessEntityComplianceLookup(Response response, ResultSet resultSet) throws SQLException {

        List<BusinessEntityComplianceLookupExt> businessEntityComplianceLookupExtList = new ArrayList<>();

        while (resultSet.next()) {

            if (response.getTotalRows() == 0) {
                response.setTotalRows(resultSet.getInt("TOTAL_ROWS"));
            }

            BusinessEntityComplianceLookupExt businessEntityComplianceLookupExt = new BusinessEntityComplianceLookupExt();
            businessEntityComplianceLookupExt.setBusinessEntityComplianceLookupSK(BigInteger.valueOf(resultSet.getBigDecimal("BE_COMP_LKUP_SK").longValue()));
            businessEntityComplianceLookupExt.setRecordCreateTimestamp(resultSet.getTimestamp("REC_CREATE_TMSTP"));
            businessEntityComplianceLookupExt.setRecordUpdateTimestamp(resultSet.getTimestamp("REC_UPDATE_TMSTP"));
            businessEntityComplianceLookupExt.setRecordEffectiveTimestamp(resultSet.getTimestamp("REC_EFF_TMSTP"));
            businessEntityComplianceLookupExt.setRecordTerminationTimestamp(resultSet.getTimestamp("REC_TERM_TMSTP"));
            businessEntityComplianceLookupExt.setRecordCreatedBy(resultSet.getString("REC_CREATED_BY"));
            businessEntityComplianceLookupExt.setRecordUpdatedBy(resultSet.getString("REC_UPDATED_BY"));
            businessEntityComplianceLookupExt.setChangeReasonMemo(resultSet.getString("CHANGE_REASON_MEMO"));
            businessEntityComplianceLookupExt.setCurrentRecordIndicator(resultSet.getBoolean("CURR_REC_IND"));
            businessEntityComplianceLookupExt.setChangeVersionID(BigInteger.valueOf(resultSet.getBigDecimal("CHANGE_VERSION_ID").longValue()));
            businessEntityComplianceLookupExt.setBusinessEntityID(resultSet.getString("BE_ID"));
            businessEntityComplianceLookupExt.setComplianceCategoryCode(resultSet.getString("COMP_CTGY_CODE"));
            businessEntityComplianceLookupExt.setComplianceEffectiveDate(resultSet.getTimestamp("COMP_EFF_DATE"));
            businessEntityComplianceLookupExt.setComplianceTerminationDate(resultSet.getTimestamp("COMP_TERM_DATE"));
            businessEntityComplianceLookupExt.setComplianceCode(resultSet.getString("COMP_CODE"));
            businessEntityComplianceLookupExt.setComplianceName(resultSet.getString("COMP_NAME"));
            businessEntityComplianceLookupExt.setComplianceDescription(resultSet.getString("COMP_DESC"));
            businessEntityComplianceLookupExt.setComplianceRecurringIndicator(resultSet.getBoolean("COMP_RECUR_IND"));
            businessEntityComplianceLookupExt.setComplianceRequiredByDate(resultSet.getTimestamp("COMP_RQD_BY_DATE"));

            BigDecimal compRecurFreq = resultSet.getBigDecimal("COMP_RECUR_FREQ");

            if (compRecurFreq != null) {
                businessEntityComplianceLookupExt.setComplianceRecurringFrequency(BigInteger.valueOf(compRecurFreq.intValue()));
            }

            businessEntityComplianceLookupExt.setComplianceRecurringFrequencyUnitOfMeasure(resultSet.getString("COMP_RECUR_FREQ_UOM"));

            String fromDateQualifier = resultSet.getString("COMP_RQD_FROM_DATE_QLFR");

            if (!StringUtil.IsNullOrEmpty(fromDateQualifier)) {

                businessEntityComplianceLookupExt.setComplianceRequiredFromDateQualifier(StaffItemRequiredFromQualifier.fromValue(fromDateQualifier));
            }

            businessEntityComplianceLookupExt.setNonComplianceAlertIndicator(resultSet.getBoolean("NON_COMP_ALERT_IND"));

            BigDecimal nonCompAlertThreshold = resultSet.getBigDecimal("NON_COMP_ALERT_THRESHOLD");

            if (nonCompAlertThreshold != null) {

                businessEntityComplianceLookupExt.setNonComplianceAlertThreshold(BigInteger.valueOf(nonCompAlertThreshold.intValue()));
            }

            businessEntityComplianceLookupExt.setComplianceNote(resultSet.getString("COMP_NOTE"));

            String compAddtlQualifier = resultSet.getString("COMP_ADDL_INFO_QLFR");

            if (!StringUtil.IsNullOrEmpty(compAddtlQualifier)) {
                businessEntityComplianceLookupExt.setComplianceAdditionalInformationQualifier(ComplianceAdditionalInformationQualifier.fromValue(compAddtlQualifier));
            }

            businessEntityComplianceLookupExt.setComplianceAdditionalInformationRequiredIndicator(resultSet.getBoolean("COMP_ADDL_INFO_RQD_IND"));
            businessEntityComplianceLookupExt.setComplianceAdditionalInformationName(resultSet.getString("COMP_ADDL_INFO_NAME"));

            businessEntityComplianceLookupExtList.add(businessEntityComplianceLookupExt);
        }

        response.setData(businessEntityComplianceLookupExtList);
    }

    private void mapResultSetToBusinessEntityComplianceLookupExt(Response response, ResultSet resultSet) throws SQLException {

        List<BusinessEntityComplianceLookupExt> businessEntityComplianceLookupExtList = new ArrayList<>();

        while (resultSet.next()) {

            if (response.getTotalRows() == 0) {
                response.setTotalRows(resultSet.getInt("TOTAL_ROWS"));
            }

            BusinessEntityComplianceLookupExt businessEntityComplianceLookupExt = new BusinessEntityComplianceLookupExt();
            businessEntityComplianceLookupExt.setBusinessEntityComplianceLookupSK(BigInteger.valueOf(resultSet.getBigDecimal("BE_COMP_LKUP_SK").longValue()));
            businessEntityComplianceLookupExt.setRecordCreateTimestamp(resultSet.getTimestamp("REC_CREATE_TMSTP"));
            businessEntityComplianceLookupExt.setRecordUpdateTimestamp(resultSet.getTimestamp("REC_UPDATE_TMSTP"));
            businessEntityComplianceLookupExt.setRecordEffectiveTimestamp(resultSet.getTimestamp("REC_EFF_TMSTP"));
            businessEntityComplianceLookupExt.setRecordTerminationTimestamp(resultSet.getTimestamp("REC_TERM_TMSTP"));
            businessEntityComplianceLookupExt.setRecordCreatedBy(resultSet.getString("REC_CREATED_BY"));
            businessEntityComplianceLookupExt.setRecordUpdatedBy(resultSet.getString("REC_UPDATED_BY"));
            businessEntityComplianceLookupExt.setChangeReasonMemo(resultSet.getString("CHANGE_REASON_MEMO"));
            businessEntityComplianceLookupExt.setCurrentRecordIndicator(resultSet.getBoolean("CURR_REC_IND"));
            businessEntityComplianceLookupExt.setChangeVersionID(BigInteger.valueOf(resultSet.getBigDecimal("CHANGE_VERSION_ID").longValue()));
            businessEntityComplianceLookupExt.setBusinessEntityID(resultSet.getString("BE_ID"));
            businessEntityComplianceLookupExt.setComplianceCategoryCode(resultSet.getString("COMP_CTGY_CODE"));
            businessEntityComplianceLookupExt.setComplianceEffectiveDate(resultSet.getTimestamp("COMP_EFF_DATE"));
            businessEntityComplianceLookupExt.setComplianceTerminationDate(resultSet.getTimestamp("COMP_TERM_DATE"));
            businessEntityComplianceLookupExt.setComplianceCode(resultSet.getString("COMP_CODE"));
            businessEntityComplianceLookupExt.setComplianceName(resultSet.getString("COMP_NAME"));
            businessEntityComplianceLookupExt.setComplianceDescription(resultSet.getString("COMP_DESC"));
            businessEntityComplianceLookupExt.setComplianceRecurringIndicator(resultSet.getBoolean("COMP_RECUR_IND"));
            businessEntityComplianceLookupExt.setComplianceRequiredByDate(resultSet.getTimestamp("COMP_RQD_BY_DATE"));

            BigDecimal compRecurFreq = resultSet.getBigDecimal("COMP_RECUR_FREQ");

            if (compRecurFreq != null) {
                businessEntityComplianceLookupExt.setComplianceRecurringFrequency(BigInteger.valueOf(compRecurFreq.intValue()));
            }

            businessEntityComplianceLookupExt.setComplianceRecurringFrequencyUnitOfMeasure(resultSet.getString("COMP_RECUR_FREQ_UOM"));

            String fromDateQualifier = resultSet.getString("COMP_RQD_FROM_DATE_QLFR");

            if (!StringUtil.IsNullOrEmpty(fromDateQualifier)) {

                businessEntityComplianceLookupExt.setComplianceRequiredFromDateQualifier(StaffItemRequiredFromQualifier.fromValue(fromDateQualifier));
            }

            businessEntityComplianceLookupExt.setNonComplianceAlertIndicator(resultSet.getBoolean("NON_COMP_ALERT_IND"));

            BigDecimal nonCompAlertThreshold = resultSet.getBigDecimal("NON_COMP_ALERT_THRESHOLD");

            if (nonCompAlertThreshold != null) {

                businessEntityComplianceLookupExt.setNonComplianceAlertThreshold(BigInteger.valueOf(nonCompAlertThreshold.intValue()));
            }

            businessEntityComplianceLookupExt.setComplianceNote(resultSet.getString("COMP_NOTE"));

            String compAddtlQualifier = resultSet.getString("COMP_ADDL_INFO_QLFR");

            if (!StringUtil.IsNullOrEmpty(compAddtlQualifier)) {
                businessEntityComplianceLookupExt.setComplianceAdditionalInformationQualifier(ComplianceAdditionalInformationQualifier.fromValue(compAddtlQualifier));
            }

            businessEntityComplianceLookupExt.setComplianceAdditionalInformationRequiredIndicator(resultSet.getBoolean("COMP_ADDL_INFO_RQD_IND"));
            businessEntityComplianceLookupExt.setComplianceAdditionalInformationName(resultSet.getString("COMP_ADDL_INFO_NAME"));

            businessEntityComplianceLookupExt.setComplianceCategoryName(resultSet.getString("COMP_CTGY_NAME"));

            businessEntityComplianceLookupExtList.add(businessEntityComplianceLookupExt);
        }

        response.setData(businessEntityComplianceLookupExtList);
    }

    public Response getStaffPositionsByBeId(String bsnEntId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT DISTINCT SVC_NAME FROM SVC WHERE BE_ID = ? AND SVC_NAME IS NOT NULL "
                    + "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<String> staffPositions = new ArrayList<>();
            while (resultSet.next()) {
                staffPositions.add(resultSet.getString("SVC_NAME"));
            }

            Response response = new Response();
            response.setData(staffPositions);
            response.setTotalRows(staffPositions.size());

            connection.commit();

            return response;
        } catch (Exception e) {
            System.out.println(e);
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

    public Response getAdminStaffPK(String bsnEntId, int page, int pageSize, String sortOn, String direction)
            throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // Calculate row range.
        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String orderByColumn = "ADMIN_STAFF_LAST_NAME"; // Default
            switch (sortOn) {
                case "fn":
                    orderByColumn = "ADMIN_STAFF_FIRST_NAME";
                    break;
                case "id":
                    orderByColumn = "ADMIN_STAFF_ID";
                    break;
            }

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                            " " +
                            "  SELECT * FROM ( " +
                            " " +
                            "      (SELECT * FROM ADMIN_STAFF " +
                            "          WHERE ADMIN_STAFF_FIRST_NAME IS NOT NULL " +
                            "            AND ADMIN_STAFF_LAST_NAME IS NOT NULL " +
                            "            AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                            "            AND BE_ID = ? " +
                            "        ORDER BY %s %s) " +
                            "  ) " +
                            " " +
                            ") R1) " +
                            " " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d",
                    orderByColumn, direction, fromRow, toRow
            );

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);

            Response response = new Response();
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());

                List<AdministrativeStaff> resultList = (List<AdministrativeStaff>) new DataMapper().mapWithOffsetNext(resultSet, "com.sandata.lab.data.model.dl.model.AdministrativeStaff", 2);

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

    public Response getAdminStaffPtPK(String patientId, String bsnEntId, int page, int pageSize, String sortOn, String direction)
            throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // Calculate row range.
        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            String patientIdFilter = "";
            if (!StringUtil.IsNullOrEmpty(patientId)) {
                patientIdFilter = "AND PT_ID = ?";
            }

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String orderByColumn = "ADMIN_STAFF_ID"; // Default
            switch (sortOn) {
                case "pid":
                    orderByColumn = "PT_ID";
                    break;
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String currentDate = dateFormat.format(new Date());

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                            " " +
                            "  SELECT * FROM ( " +
                            " " +
                            "      (SELECT * FROM ADMIN_STAFF_PT " +
                            "          WHERE TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                            "              BETWEEN ADMIN_STAFF_PT_EFF_DATE AND ADMIN_STAFF_PT_TERM_DATE " +
                            "            AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                            "            AND BE_ID = ? %s" +
                            "        ORDER BY %s %s) " +
                            "  ) " +
                            " " +
                            ") R1) " +
                            " " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d ",
                    patientIdFilter, orderByColumn, direction, fromRow, toRow
            );

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, currentDate);
            preparedStatement.setString(index++, bsnEntId);

            if (!StringUtil.IsNullOrEmpty(patientId)) {
                preparedStatement.setString(index++, patientId);
            }

            Response response = new Response();
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());

                List<AdministrativeStaffPatient> resultList = (List<AdministrativeStaffPatient>) new DataMapper().mapWithOffsetNext(resultSet, "com.sandata.lab.data.model.dl.model.AdministrativeStaffPatient", 2);

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

    public Response getAdminStaffStaffXwalkPK(String staffId, String bsnEntId, int page, int pageSize, String sortOn, String direction)
            throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // Calculate row range.
        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            String staffIdFilter = "";
            if (!StringUtil.IsNullOrEmpty(staffId)) {
                staffIdFilter = "AND STAFF_ID = ?";
            }

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String orderByColumn = "ADMIN_STAFF_ID"; // Default
            switch (sortOn) {
                case "sid":
                    orderByColumn = "STAFF_ID";
                    break;
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String currentDate = dateFormat.format(new Date());

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                            "  SELECT * FROM ( " +
                            " " +
                            "      (SELECT * FROM ADMIN_STAFF_STAFF_XWALK " +
                            "          WHERE TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                            "              BETWEEN ADMIN_STAFF_STAFF_EFF_DATE AND ADMIN_STAFF_STAFF_TERM_DATE " +
                            "            AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                            "            AND BE_ID = ? %s " +
                            "        ORDER BY %s %s) " +
                            "  ) " +
                            ") R1) " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d ",
                    staffIdFilter, orderByColumn, direction, fromRow, toRow
            );

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, currentDate);
            preparedStatement.setString(index++, bsnEntId);

            if (!StringUtil.IsNullOrEmpty(staffId)) {
                preparedStatement.setString(index++, staffId);
            }

            Response response = new Response();
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());

                List<AdministrativeStaffStaffCrosswalk> resultList = (List<AdministrativeStaffStaffCrosswalk>) new DataMapper().mapWithOffsetNext(resultSet, "com.sandata.lab.data.model.dl.model.AdministrativeStaffStaffCrosswalk", 2);

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

    public List<String> getReportTypes() throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM METADATA.RPRT_TYP_LKUP ORDER BY RPRT_TYP_NAME ASC";

            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            List<String> resultList = new ArrayList<>();

            while (resultSet.next()) {
                resultList.add(resultSet.getString("RPRT_TYP_NAME"));
            }

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

    public List<ReportName> getReportNames(String reportType) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(true);

            String sql = "SELECT RPRT_ID,RPRT_NAME,RPRT_DESC FROM METADATA.RPRT_LKUP " +
                    "WHERE UPPER(RPRT_TYP_NAME) = ? ORDER BY RPRT_TYP_NAME ASC";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, reportType.toUpperCase());

            resultSet = preparedStatement.executeQuery();

            List<ReportName> resultList = new ArrayList<>();
            while (resultSet.next()) {

                ReportName reportName = new ReportName();

                reportName.setReportId(resultSet.getString("RPRT_ID"));
                reportName.setReportName(resultSet.getString("RPRT_NAME"));
                reportName.setReportDescription(resultSet.getString("RPRT_DESC"));

                resultList.add(reportName);
            }

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

    public List<ReportKeyValue> getReportParams(String reportId) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM METADATA.RPRT_ATTR WHERE RPRT_ID = ?";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, reportId);

            resultSet = preparedStatement.executeQuery();

            List<ReportKeyValue> resultList = new ArrayList<>();
            while (resultSet.next()) {

                ReportKeyValue keyValue = new ReportKeyValue();
                keyValue.setKey(resultSet.getString("RPRT_ATTR_KEY_NAME"));
                keyValue.setValue(resultSet.getString("RPRT_ATTR_KEY_VAL"));
                resultList.add(keyValue);
            }

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

    public List<BusinessEntityStaffTrainingRelationshipDetail> getBeStaffTrngRelDetlForParentSk(Long beStaffTrngRelSK) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM BE_STAFF_TRNG_REL_DET WHERE BE_STAFF_TRNG_REL_SK=?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, beStaffTrngRelSK);

            resultSet = preparedStatement.executeQuery();

            return (List<BusinessEntityStaffTrainingRelationshipDetail>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.BusinessEntityStaffTrainingRelationshipDetail");

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

    public List<BusinessEntityStaffTrainingRelationship> getBeStaffTrngRelForStaffTrngCode(String bsnEntId, String staffTrngCode) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM BE_STAFF_TRNG_REL"
                    + " WHERE BE_ID=?"
                    + "  AND STAFF_TRNG_CODE=?"
                    + "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index, staffTrngCode);

            resultSet = preparedStatement.executeQuery();

            return (List<BusinessEntityStaffTrainingRelationship>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.BusinessEntityStaffTrainingRelationship");

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

    public long insertBeLobLkup(Connection connection, String bsnEntId, BusinessEntityLineOfBusinessLookup beLobLkup) throws SandataRuntimeException {

        try {

            beLobLkup.setRecordCreateTimestamp(new Date());
            beLobLkup.setRecordUpdateTimestamp(new Date());
            beLobLkup.setChangeVersionID(BigInteger.ZERO);

            // SAN-3205: this should be null to let Oracle function create completely new BE_ID instead of accepting any BE_ID from input request!
            if (beLobLkup.getBusinessEntityID() != null) {
                throw new SandataRuntimeException("The BusinessEntityID must be set to null then the system will auto generate a unique BE_ID!");
            }

            return insertBeLobLkup(connection, bsnEntId, new DataMapper().map(beLobLkup));

        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: insertBeLobLkup: %s",
                    e.getClass().getName(), e.getMessage()), e);
        }
    }

    public long updateBeLobLkup(Connection connection, String bsnEntId, BusinessEntityLineOfBusinessLookup beLobLkup) throws SandataRuntimeException {

        try {

            beLobLkup.setRecordUpdateTimestamp(new Date());

            return insertBeLobLkup(connection, bsnEntId, new DataMapper().map(beLobLkup));

        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: insertBeLobLkup: %s",
                    e.getClass().getName(), e.getMessage()), e);
        }
    }

    public long deleteBeLobLkup(long sequenceKey) throws SandataRuntimeException {
        return execute(ConnectionType.COREDATA, "PKG_BE_LOB", "deleteBeLobLkup", sequenceKey);
    }

    public Response getAllBEComplianceFilteredByCategory(String bsnEntId,
                                                         String categoryName,
                                                         int page,
                                                         int pageSize,
                                                         String sortTable,
                                                         String sortOn,
                                                         String direction) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            // Calculate row range.
            int toRow = page * pageSize;
            int fromRow = toRow - (pageSize - 1);

            String categoryCondition = "";
            if (StringUtils.isNotEmpty(categoryName)) {
                //should get all compliance
                categoryCondition = " AND UPPER(T2.COMP_CTGY_NAME) = ? AND (TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T2.CURR_REC_IND = '1')";
            }

            String sortByColumnString = "UPPER(T1.COMP_NAME)";
            if (sortOn.equals("COMP_EFF_DATE")) {
                sortByColumnString = new StringBuilder()
                        .append(sortTable)
                        .append(".")
                        .append(sortOn)
                        .toString();
            } else {
                sortByColumnString = new StringBuilder()
                        .append("UPPER(")
                        .append(sortTable)
                        .append(".")
                        .append(sortOn)
                        .append(")")
                        .toString();
            }

            String sql = String.format("SELECT * FROM"
                            + "  (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (SELECT * FROM ("
                            + "    SELECT T1.BE_COMP_LKUP_SK,"
                            + "         T1.REC_CREATE_TMSTP,"
                            + "         T1.REC_UPDATE_TMSTP,"
                            + "         T1.REC_EFF_TMSTP,"
                            + "         T1.REC_TERM_TMSTP,"
                            + "         T1.REC_CREATED_BY,"
                            + "         T1.REC_UPDATED_BY,"
                            + "         T1.CHANGE_REASON_MEMO,"
                            + "         T1.CURR_REC_IND,"
                            + "         T1.CHANGE_VERSION_ID,"
                            + "         T1.BE_ID,"
                            + "         T1.COMP_CTGY_CODE,"
                            + "         T1.COMP_EFF_DATE,"
                            + "         T1.COMP_TERM_DATE,"
                            + "         T1.COMP_CODE,"
                            + "         T1.COMP_NAME,"
                            + "         T1.COMP_DESC,"
                            + "         T1.COMP_RECUR_IND,"
                            + "         T1.COMP_RQD_BY_DATE,"
                            + "         T1.COMP_RECUR_FREQ,"
                            + "         T1.COMP_RECUR_FREQ_UOM,"
                            + "         T1.COMP_RQD_FROM_DATE_QLFR,"
                            + "         T1.NON_COMP_ALERT_IND,"
                            + "         T1.NON_COMP_ALERT_THRESHOLD,"
                            + "         T1.COMP_NOTE,"
                            + "         T1.COMP_ADDL_INFO_QLFR,"
                            + "         T1.COMP_ADDL_INFO_RQD_IND,"
                            + "         T1.COMP_ADDL_INFO_NAME,"
                            + "         T2.COMP_CTGY_NAME"
                            + "    FROM COREDATA.BE_COMP_LKUP T1"
                            + "      LEFT JOIN BE_COMP_CTGY_LKUP T2 ON T1.BE_ID = T2.BE_ID"
                            + "        AND T1.COMP_CTGY_CODE = T2.COMP_CTGY_CODE"
                            + " WHERE T1.BE_ID = ?"
                            + "%s"
                            + " AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = '1') "
                            + "       ORDER BY %s %s)) R1)"
                            + "  WHERE ROW_NUMBER BETWEEN %d AND %d",
                    categoryCondition,
                    sortByColumnString,
                    direction,
                    fromRow,
                    toRow);

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, bsnEntId);

            if (StringUtils.isNotEmpty(categoryName)) {
                preparedStatement.setString(2, categoryName.toUpperCase());
            }

            resultSet = preparedStatement.executeQuery();


            Response response = new Response();

            mapResultSetToBusinessEntityComplianceLookupExt(response, resultSet);
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);

            connection.commit();

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

    public BusinessEntityComplianceLookup getBEComplianceLookup(BigInteger complianceSk) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);


            String sql = "SELECT * FROM COREDATA.BE_COMP_LKUP T1 " +
                    "       WHERE (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = '1') AND " +
                    "   T1.BE_COMP_LKUP_SK = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBigDecimal(1, new BigDecimal(complianceSk));

            resultSet = preparedStatement.executeQuery();


            Response response = new Response();

            List<BusinessEntityComplianceLookup> businessEntityComplianceLookups =
                    (List<BusinessEntityComplianceLookup>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.BusinessEntityComplianceLookup");

            //  mapResultSetToBusinessEntityComplianceLookupExt(response, resultSet);

            if (businessEntityComplianceLookups.size() > 0) {

                return businessEntityComplianceLookups.get(0);
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
            this.connectionPoolDataService.close(connection);
        }
    }

    public List<BusinessEntityComplianceServiceList> getBEComplianceServiceListByCompCode(String businessEntityID, String compCode) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);


            String sql = "SELECT * FROM COREDATA.BE_COMP_SVC_LST T1 " +
                    "       WHERE (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = '1') AND " +
                    "   T1.COMP_CODE = ? AND T1.BE_ID = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, compCode);
            preparedStatement.setString(2, businessEntityID);

            resultSet = preparedStatement.executeQuery();


            Response response = new Response();

            List<BusinessEntityComplianceServiceList> businessEntityComplianceServiceLists =
                    (List<BusinessEntityComplianceServiceList>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.BusinessEntityComplianceServiceList");


            return businessEntityComplianceServiceLists;

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

    public List<BusinessEntityComplianceRelationship> getBEComplianceRelationshipByCompCode(String businessEntityID, String compCode) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);


            String sql = "SELECT * FROM COREDATA.BE_COMP_REL T1 " +
                    " LEFT JOIN COREDATA.BE_COMP_REL_DET T2 ON T1.BE_ID = T2.BE_ID AND T1.BE_COMP_REL_SK = T2.BE_COMP_REL_SK " +
                    "       WHERE (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = '1') AND " +
                    "   T1.COMP_CODE = ? AND T1.BE_ID = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, compCode);
            preparedStatement.setString(2, businessEntityID);

            resultSet = preparedStatement.executeQuery();

            List<BusinessEntityComplianceRelationship> businessEntityComplianceRelationships
                    = (List<BusinessEntityComplianceRelationship>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.BusinessEntityComplianceRelationship");


            return businessEntityComplianceRelationships;

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

    public List<BusinessEntityComplianceRelationshipDetail> getBeCompRelDetlForParentSk(BigInteger relSk, String businessEntityID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM BE_COMP_REL_DET WHERE BE_COMP_REL_SK = ? AND BE_ID = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBigDecimal(1, new BigDecimal(relSk));
            preparedStatement.setString(2, businessEntityID);

            resultSet = preparedStatement.executeQuery();

            return (List<BusinessEntityComplianceRelationshipDetail>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.BusinessEntityComplianceRelationshipDetail");

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


    public List<BusinessEntityComplianceAdditionalInformationLookup> getBEComplianceAddInfoByCompCode(String businessEntityID, String compCode) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);


            String sql = "SELECT * FROM COREDATA.BE_COMP_ADDL_INFO_LKUP T1 " +
                    "       WHERE (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = '1') AND " +
                    "   T1.COMP_CODE = ? AND T1.BE_ID = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, compCode);
            preparedStatement.setString(2, businessEntityID);

            resultSet = preparedStatement.executeQuery();


            Response response = new Response();

            List<BusinessEntityComplianceAdditionalInformationLookup> businessEntityComplianceAdditionalInformationLookups =
                    (List<BusinessEntityComplianceAdditionalInformationLookup>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.BusinessEntityComplianceAdditionalInformationLookup");

            connection.commit();

            return businessEntityComplianceAdditionalInformationLookups;

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

    public List<MedicalExaminationItemComplianceCrosswalk> getBECompMedXwalkByCompCode(String businessEntityID, String compCode) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);


            String sql = "SELECT * FROM COREDATA.MEDCL_EXAM_ITEM_COMP_XWALK T1 " +
                    "       WHERE (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = '1') AND " +
                    "   T1.COMP_CODE = ? AND T1.BE_ID = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, compCode);
            preparedStatement.setString(2, businessEntityID);

            resultSet = preparedStatement.executeQuery();


            Response response = new Response();

            List<MedicalExaminationItemComplianceCrosswalk> medicalExaminationItemComplianceCrosswalks =
                    (List<MedicalExaminationItemComplianceCrosswalk>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.MedicalExaminationItemComplianceCrosswalk");

            connection.commit();

            return medicalExaminationItemComplianceCrosswalks;

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

    public List<StaffCompliance> getStaffComplianceByCompCode(String businessEntityID, String compCode) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);


            String sql = "SELECT * FROM COREDATA.STAFF_COMP T1 " +
                    "       WHERE (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = '1') AND " +
                    "   T1.COMP_CODE = ? AND T1.BE_ID = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, compCode);
            preparedStatement.setString(2, businessEntityID);

            resultSet = preparedStatement.executeQuery();


            Response response = new Response();

            List<StaffCompliance> staffCompliances =
                    (List<StaffCompliance>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.StaffCompliance");

            connection.commit();

            return staffCompliances;

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

    public List<StaffTrainingCategoryService> getBeStaffTrngCtgySvcForCtgyCode(String bsnEntId, String staffTrngCtgyCode) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM "
                    + ConnectionType.COREDATA
                    + ".STAFF_TRNG_CTGY_SVC"
                    + " WHERE BE_ID=?"
                    + "  AND STAFF_TRNG_CTGY_CODE=?"
                    + "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index, staffTrngCtgyCode);

            resultSet = preparedStatement.executeQuery();

            return (List<StaffTrainingCategoryService>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.StaffTrainingCategoryService");

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

    public Response findStaffTrngCtgySvc(String bsnEntId, int page, int pageSize, String orderColumn, String sortDirection) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            StringBuilder sql = new StringBuilder("SELECT * FROM ");
            sql.append("(SELECT ROWNUM ROW_NUMBER , TEST.*  , COUNT(*) OVER() TOTAL_ROWS FROM ");
            sql.append("(SELECT * FROM STAFF_TRNG_CTGY_SVC) TEST) ");
            sql.append("WHERE BE_ID =%s AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)");
            sql.append("AND ROW_NUMBER BETWEEN %s AND %s ");
            sql.append("ORDER BY %s %s ");


            String sqlQuery = String.format(sql.toString(), bsnEntId, page, pageSize, orderColumn, sortDirection);
            preparedStatement = connection.prepareStatement(sqlQuery);

            Response response = new Response();
            response.setOrderByColumn(orderColumn);
            response.setOrderByDirection(sortDirection);

            resultSet = preparedStatement.executeQuery();

            List<StaffTrainingCategoryService> resultList = new ArrayList<StaffTrainingCategoryService>();
            response.setData(resultList);

            while (resultSet.next()) {

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getInt("TOTAL_ROWS"));
                }

                resultList.add(mapStaffTrngCtgySvc(resultSet));

            }

            return response;


        } catch (SQLException e) {
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

    private StaffTrainingCategoryService mapStaffTrngCtgySvc(ResultSet resultSet) throws SQLException {
        StaffTrainingCategoryService result = new StaffTrainingCategoryService();
        result.setStaffTrainingCategoryServiceSK(BigInteger.valueOf(resultSet.getBigDecimal("STAFF_TRNG_CTGY_SVC_SK").longValue()));

        Timestamp tmstp = resultSet.getTimestamp("REC_CREATE_TMSTP");
        if (tmstp != null) {
            result.setRecordCreateTimestamp(new java.util.Date(tmstp.getTime()));
        }

        tmstp = resultSet.getTimestamp("REC_UPDATE_TMSTP");
        if (tmstp != null) {
            result.setRecordUpdateTimestamp(new java.util.Date(tmstp.getTime()));
        }

        tmstp = resultSet.getTimestamp("REC_EFF_TMSTP");
        if (tmstp != null) {
            result.setRecordEffectiveTimestamp(new java.util.Date(tmstp.getTime()));
        }

        result.setRecordCreatedBy(resultSet.getString("REC_CREATED_BY"));
        result.setRecordUpdatedBy(resultSet.getString("REC_UPDATED_BY"));
        result.setChangeReasonMemo(resultSet.getString("CHANGE_REASON_MEMO"));

        result.setChangeVersionID(BigInteger.valueOf(resultSet.getBigDecimal("CHANGE_VERSION_ID").longValue()));

        result.setBusinessEntityID(resultSet.getString("BE_ID"));
        result.setStaffTrainingCategoryCode(resultSet.getString("STAFF_TRNG_CTGY_CODE"));
        result.setServiceName(ServiceName.fromValue(resultSet.getString("SVC_NAME")));


        return result;
    }


    public Response getBeCompLkupWithPaginationSortAndOption(String bsnEntId,
                                                             String compName,
                                                             Integer page,
                                                             Integer pageSize,
                                                             String sortOn,
                                                             String direction) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();

            // Calculate row range.
            int toRow = page * pageSize;
            int fromRow = toRow - (pageSize - 1);

            // Parameters
            List<String> parameterList = new ArrayList<>();

            // Build raw SQL query.
            StringBuilder sqlStringBuilder = new StringBuilder();
            sqlStringBuilder.append("SELECT * FROM " +
                    "  (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (SELECT * FROM (SELECT * FROM (SELECT * " +
                    "    FROM BE_COMP_LKUP WHERE ");

            // Add filter for compliance name.
            if (compName != null
                    && !compName.isEmpty()) {
                sqlStringBuilder.append("BE_COMP_LKUP.COMP_NAME LIKE ? AND ");
                parameterList.add(String.format("%%%s%%", compName));
            }

            sqlStringBuilder.append(String.format("BE_COMP_LKUP.BE_ID=? " +
                            "AND (TO_CHAR(BE_COMP_LKUP.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND BE_COMP_LKUP.CURR_REC_IND = '1') ORDER BY BE_COMP_LKUP.%s %s))) R1) WHERE ROW_NUMBER BETWEEN %s AND %s",
                    sortOn,
                    direction,
                    fromRow,
                    toRow));

            parameterList.add(bsnEntId);

            preparedStatement = connection.prepareStatement(sqlStringBuilder.toString());

            int index = 1;
            for (String parameter : parameterList) {
                preparedStatement.setString(index++, parameter);
            }

            resultSet = preparedStatement.executeQuery();

            Response response = new Response();
            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);
            response.setPage(page);
            response.setPageSize(pageSize);

            response.setData(new DataMapper().mapWithOffset(resultSet, "com.sandata.lab.data.model.dl.model.BusinessEntityComplianceLookup", 2));

            return response;

        } catch (Exception e) {

            throw new SandataRuntimeException(format("%s: %s",
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

    public List<ScheduleEvent> getScheduleEventByDateRangeForStaff(String bsnEntId, String staffId, Date dateFrom, Date dateTo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT SE.* FROM SCHED_EVNT SE "
                    + "WHERE SE.BE_ID = ? AND SE.STAFF_ID = ? "
                    + "AND ("
                    + "    SE.SCHED_EVNT_START_DTIME <= ? "
                    + "    AND SE.SCHED_EVNT_END_DTIME >= ? "
                    + "    AND UPPER(SE.SCHED_EVNT_STATUS) <> 'CANCELLED' "
                    + "    AND (TO_CHAR(SE.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND SE.CURR_REC_IND = '1')"
                    + ") ";
            connection = getOracleConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bsnEntId);
            preparedStatement.setString(2, staffId);
            preparedStatement.setTimestamp(3, new java.sql.Timestamp(dateTo.getTime()));
            preparedStatement.setTimestamp(4, new java.sql.Timestamp(dateFrom.getTime()));

            resultSet = preparedStatement.executeQuery();

            return (List<ScheduleEvent>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.ScheduleEvent");
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

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    public List<ScheduleEvent> getScheduleEventByDateRangeForPatient(String bsnEntId, String patientId, Date dateFrom, Date dateTo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT SE.* FROM SCHED_EVNT SE "
                    + "WHERE SE.BE_ID = ? AND SE.PT_ID = ? "
                    + "AND ("
                    + "    SE.SCHED_EVNT_START_DTIME <= ? "
                    + "    AND SE.SCHED_EVNT_END_DTIME >= ? "
                    + "    AND (TO_CHAR(SE.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND SE.CURR_REC_IND = '1')"
                    + ") ";
            connection = getOracleConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bsnEntId);
            preparedStatement.setString(2, patientId);
            preparedStatement.setDate(3, new java.sql.Date(dateTo.getTime()));
            preparedStatement.setDate(4, new java.sql.Date(dateFrom.getTime()));

            resultSet = preparedStatement.executeQuery();

            return (List<ScheduleEvent>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.ScheduleEvent");
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

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    public String getBeStaffCompNameForCode(String bsnEntId,
                                            String compCode) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT COMP_NAME FROM BE_COMP_LKUP"
                    + "  WHERE BE_ID=?"
                    + "    AND COMP_CODE=?"
                    + "    AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

            connection = getOracleConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bsnEntId);
            preparedStatement.setString(2, compCode);

            resultSet = preparedStatement.executeQuery();

            String compName = null;

            if (resultSet.next()) {
                compName = resultSet.getString("COMP_NAME");
            }

            return compName;
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

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    public String getBeStaffTrngLkupNameForCode(String bsnEntId,
                                                String staffTrngCode) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT STAFF_TRNG_NAME FROM BE_STAFF_TRNG_LKUP"
                    + "  WHERE BE_ID=?"
                    + "    AND STAFF_TRNG_CODE=?"
                    + "    AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

            connection = getOracleConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bsnEntId);
            preparedStatement.setString(2, staffTrngCode);

            resultSet = preparedStatement.executeQuery();

            String staffTrngName = null;

            if (resultSet.next()) {
                staffTrngName = resultSet.getString("STAFF_TRNG_NAME");
            }

            return staffTrngName;
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

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    /**
     * Returns Response containing List of <code>simpleClassName</code> for the specified
     * business entity ID, optional sort on table and sort direction.
     *
     * @param bsnEntId  Specified business entity ID.
     * @param sortOn    Specified sort column.
     * @param direction Specified sort direction.
     * @return Response with  List of <code>simpleClassName</code>.
     */
    public Response getBeObjectsWithSortOption(String tableName,
                                               String bsnEntId,
                                               String sortOn,
                                               String direction,
                                               String simpleClassName) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();

            if ("name".equalsIgnoreCase(sortOn)) {
                sortOn = getColumnObjectNameByClassName(simpleClassName);
            }

            String sql = String.format("SELECT * FROM %s B1 WHERE B1.BE_ID = ? "
                            + "AND (TO_CHAR(B1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND B1.CURR_REC_IND = 1) "
                            + "ORDER BY UPPER(B1.%s) %s"
                    , tableName
                    , sortOn
                    , direction);

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bsnEntId);
            resultSet = preparedStatement.executeQuery();

            Response response = new Response();
            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);
            List data = (List) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model." + simpleClassName);

            injectAdditionalData(data, simpleClassName);

            response.setData(data);
            response.setTotalRows(data.size());
            return response;

        } catch (Exception e) {

            throw new SandataRuntimeException(format("%s: %s",
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

    private void injectAdditionalData(List listData, String simpleClassName) {
        // add StaffTrainingCategoryService
        if ("BusinessEntityStaffTrainingCategoryLookup".equalsIgnoreCase(simpleClassName)) {
            for (Object object : listData) {
                BusinessEntityStaffTrainingCategoryLookup beStaffTrngCatLkup = (BusinessEntityStaffTrainingCategoryLookup) object;
                beStaffTrngCatLkup.getStaffTrainingCategoryService()
                        .addAll(getBeStaffTrngCtgySvcForCtgyCode(beStaffTrngCatLkup.getBusinessEntityID(),
                                beStaffTrngCatLkup.getStaffTrainingCategoryCode()));
            }
        }
    }

    private String getColumnObjectNameByClassName(String simpleClassName) {
        if (simpleClassName == null) {
            throw new SandataRuntimeException("Cannot get column name for Simple Class Name NULL!");
        }

        switch (simpleClassName) {
            case "BusinessEntityReligionList":
                return "RELIGION_NAME";
            case "BusinessEntitySafetyMeasureLookup":
                return "SFTY_MEASURE_NAME";
            case "BusinessEntityLanguageList":
                return "LANG_NAME";
            case "BusinessEntityRaceEthnicityList":
                return "RACE_ETHNICITY_NAME";
            case "BusinessEntityRateTypeLookup":
                return "RATE_TYP_NAME";
            case "BusinessEntityMedicationDosageFrequencyLookup":
                return "MED_DOSAGE_FREQ_NAME";
            case "BusinessEntitySkillLookup":
                return "SKILL_NAME";
            case "BusinessEntityEmploymentClassLookup":
                return "EMPLT_CLS_NAME";
            case "BusinessEntityEmploymentStatusTypeLookup":
                return "EMPLT_STATUS_TYP_NAME";
            case "BusinessEntityStaffWorkingPreferenceLookup":
                return "STAFF_WRK_PREF_NAME";
            case "BusinessEntityReferralTypeLookup":
                return "RFRL_TYP_NAME";
            case "BusinessEntityPatientPriorityLevelLookup":
                return "PT_PRIO_LVL_NAME";
            case "BusinessEntityNutritionalRequirementLookup":
                return "NUTR_RQMT_NAME";
            case "BusinessEntityStaffTrainingCategoryLookup":
                return "STAFF_TRNG_CTGY_NAME";
            case "BusinessEntityComplianceCategoryLookup":
                return "COMP_CTGY_NAME";
            case "BusinessEntityPatientRequirementLookup":
                return "PT_RQMT_NAME";
            default:
                throw new SandataRuntimeException("Not supported column name for Simple Class Name = " + simpleClassName);
        }
    }

    public ApplicationTenantKeyConfiguration getAppTenantKeyConfForName(
            ConnectionType connectionType,
            String keyName,
            BigInteger appTenantSk,
            SandataLogger logger) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(connectionType);
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM APP_TENANT_KEY_CONF " +
                    "WHERE APP_TENANT_SK = ? AND KEY_NAME = ?";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setLong(index++, appTenantSk.longValue());
            preparedStatement.setString(index++, keyName);

            resultSet = preparedStatement.executeQuery();

            List<ApplicationTenantKeyConfiguration> resultList =
                    (List<ApplicationTenantKeyConfiguration>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.ApplicationTenantKeyConfiguration");

            if (resultList.size() == 0) {
                return null;
            }

            if (resultList.size() > 1) {
                logger.warn(String.format("%s: getAppTenantKeyConfForName: WARN: ApplicationTenantKeyConfiguration for [KEY_NAME=%s] result is greater than 1! [SIZE=%d]",
                        getClass().getName(),
                        keyName,
                        resultList.size()));
            }

            return resultList.get(0);

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

    public long insertAppTenantKeyConfForBEID(Connection connection, ConnectionType connectionType, ApplicationTenantKeyConfiguration applicationTenantKeyConfiguration) {

        Object jpubType = new DataMapper().map(applicationTenantKeyConfiguration);
        OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(applicationTenantKeyConfiguration);
        return (long) execute(connection, connectionType, oracleMetadata.packageName(), oracleMetadata.insertMethod(), jpubType);
    }

    public long updateAppTenantKeyConfForBEID(Connection connection, ConnectionType connectionType, ApplicationTenantKeyConfiguration applicationTenantKeyConfiguration) {

        Object jpubType = new DataMapper().map(applicationTenantKeyConfiguration);
        OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(applicationTenantKeyConfiguration);
        return (long) execute(connection, connectionType, oracleMetadata.packageName(), oracleMetadata.updateMethod(), jpubType);
    }

    public long deleteAppTenantKeyConfForBEID(Connection connection, ConnectionType connectionType, long appTenantKeyConfSK) {

        return (long) execute(connection, connectionType, "PKG_APP", "deleteAppTenantKeyConf", appTenantKeyConfSK);
    }

    public List<BusinessEntityStaffNoteTypeLookup> getBeStaffNoteTypLkupByBEID(String bsnEntId, String sortOn, String direction)
            throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String orderBy = "STAFF_NOTE_TYP_NAME"; //default
            switch (sortOn.toLowerCase()) {
                case "desc":
                    orderBy = "STAFF_NOTE_TYP_DESC";
                    break;
                case "created":
                    orderBy = "REC_CREATE_TMSTP";
                    break;
            }

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM BE_STAFF_NOTE_TYP_LKUP WHERE BE_ID = ? " +
                    " AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)" +
                    "ORDER BY " + orderBy + " " + direction;

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<BusinessEntityStaffNoteTypeLookup> resultList =
                    (List<BusinessEntityStaffNoteTypeLookup>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.BusinessEntityStaffNoteTypeLookup");

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

    public List<BusinessEntityPatientNoteTypeLookup> getBePtNoteTypLkupByBEID(String bsnEntId, String sortOn, String direction)
            throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String orderBy = "PT_NOTE_TYP_NAME"; //default
            switch (sortOn.toLowerCase()) {
                case "desc":
                    orderBy = "PT_NOTE_TYP_DESC";
                    break;
                case "created":
                    orderBy = "REC_CREATE_TMSTP";
                    break;
            }

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM BE_PT_NOTE_TYP_LKUP WHERE BE_ID = ? " +
                    " AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)" +
                    "ORDER BY " + orderBy + " " + direction;

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<BusinessEntityPatientNoteTypeLookup> resultList =
                    (List<BusinessEntityPatientNoteTypeLookup>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.BusinessEntityPatientNoteTypeLookup");

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

    /**
     * Get BusinessEntityChangeReasonLookup by bsn_ent_id and module and status
     *
     * @param bsnEntId
     * @param module
     * @param status
     * @return
     * @throws SandataRuntimeException
     */
    public List<BusinessEntityChangeReasonLookup> getBeChangeReasonLkupByModuleAndStatus(
            String bsnEntId, String module, String status) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            StringBuilder sqlBuilder = new StringBuilder();

            // If no module and status are provided, return all Reason Codes (level 2)
            if (StringUtil.IsNullOrEmpty(module) && StringUtil.IsNullOrEmpty(status)) {
                sqlBuilder
                        .append("SELECT * FROM ( " +
                                "    SELECT BE_CHANGE_REASON_LKUP.*, LEVEL AS REASON_CODE_LEVEL  " +
                                "    FROM BE_CHANGE_REASON_LKUP  " +
                                "    START WITH BE_CHANGE_REASON_LKUP_PAR_SK IS NULL  " +
                                "    CONNECT BY PRIOR BE_CHANGE_REASON_LKUP_SK = BE_CHANGE_REASON_LKUP_PAR_SK)  " +
                                "WHERE REASON_CODE_LEVEL = 2 AND BE_ID = ? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                                "ORDER BY BE_CHANGE_REASON_LKUP_SK ASC");

            } else if (!StringUtil.IsNullOrEmpty(module) && !StringUtil.IsNullOrEmpty(status)) {
                sqlBuilder
                        .append("SELECT * FROM BE_CHANGE_REASON_LKUP T1 " +
                                "INNER JOIN " +
                                "  (SELECT BE_CHANGE_REASON_LKUP_SK  " +
                                "  FROM BE_CHANGE_REASON_LKUP  " +
                                "  INNER JOIN  " +
                                "    (SELECT *  " +
                                "    FROM APP_MOD_LKUP  " +
                                "    WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) APP_MOD_LKUP " +
                                "  ON BE_CHANGE_REASON_LKUP.APP_MOD_NAME = APP_MOD_LKUP.APP_MOD_NAME " +
                                "  WHERE BE_ID = ? AND UPPER(APP_MOD_LKUP.APP_MOD_NAME) = ? AND UPPER(CHANGE_REASON_NAME) = ?) T2 " +
                                "ON T1.BE_CHANGE_REASON_LKUP_PAR_SK = T2.BE_CHANGE_REASON_LKUP_SK " +
                                "WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1 " +
                                "ORDER BY T1.BE_CHANGE_REASON_LKUP_SK ASC");

            }

            preparedStatement = connection.prepareStatement(sqlBuilder.toString());

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);
            if (!StringUtil.IsNullOrEmpty(module) && !StringUtil.IsNullOrEmpty(status)) {
                preparedStatement.setString(index++, module.toUpperCase());
                preparedStatement.setString(index++, status.toUpperCase());
            }

            resultSet = preparedStatement.executeQuery();

            List<BusinessEntityChangeReasonLookup> resultList =
                    (List<BusinessEntityChangeReasonLookup>) new DataMapper().map(
                            resultSet, "com.sandata.lab.data.model.dl.model.BusinessEntityChangeReasonLookup");

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

    /**
     * Get all Modules from COREDATA.BE_CHANGE_REASON_LKUP table
     *
     * @return
     * @throws SandataRuntimeException
     */
    public List<ApplicationModuleLookup> getApplicationModuleLookup() throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = new StringBuilder()
                    .append("SELECT * FROM COREDATA.APP_MOD_LKUP ")
                    .append("WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1 ")
                    .append("ORDER BY APP_MOD_LKUP_SK ASC")
                    .toString();

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            List<ApplicationModuleLookup> resultList =
                    (List<ApplicationModuleLookup>) new DataMapper().map(
                            resultSet, "com.sandata.lab.data.model.dl.model.ApplicationModuleLookup");

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

    /**
     * Get all Statuses by Module from COREDATA.BE_CHANGE_REASON_LKUP table
     *
     * @param bsnEntId
     * @param module
     * @return
     * @throws SandataRuntimeException
     */
    public List<BusinessEntityChangeReasonLookup> getBeChangeReasonLkupStatusByModule(String bsnEntId, String module) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            StringBuilder sqlBuilder = new StringBuilder()
                    .append("SELECT * FROM COREDATA.BE_CHANGE_REASON_LKUP T1 " +
                            "INNER JOIN  " +
                            "    (SELECT APP_MOD_NAME  " +
                            "    FROM APP_MOD_LKUP  " +
                            "    WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) T2 " +
                            "ON T1.APP_MOD_NAME = T2.APP_MOD_NAME " +
                            "WHERE BE_ID = ?  " +
                            "  AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)  " +
                            "  AND BE_CHANGE_REASON_LKUP_PAR_SK IS NULL " +
                            "  AND UPPER(T1.APP_MOD_NAME) = ? " +
                            "ORDER BY BE_CHANGE_REASON_LKUP_SK ASC");

            preparedStatement = connection.prepareStatement(sqlBuilder.toString());

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, module.toUpperCase());

            resultSet = preparedStatement.executeQuery();

            List<BusinessEntityChangeReasonLookup> resultList =
                    (List<BusinessEntityChangeReasonLookup>) new DataMapper().map(
                            resultSet, "com.sandata.lab.data.model.dl.model.BusinessEntityChangeReasonLookup");

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

    public Response getAdminStaffStaffXref(String staffId, String bsnEntId,
                                           int page, int pageSize, String sortOn, String direction) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // Calculate row range.
        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            String staffIdFilter = "";
            if (!StringUtil.IsNullOrEmpty(staffId)) {
                staffIdFilter = "AND STAFF_ID = ?";
            }

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String orderByColumn = "ADMIN_STAFF_ID"; // Default
            switch (sortOn) {
                case "sid":
                    orderByColumn = "STAFF_ID";
                    break;
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDate = dateFormat.format(new Date());
            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                            "  SELECT * FROM ( " +
                            " " +
                            "      (SELECT * FROM ADMIN_STAFF_STAFF_XREF " +
                            "          WHERE TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                            "              BETWEEN ADMIN_STAFF_STAFF_EFF_DATE AND ADMIN_STAFF_STAFF_TERM_DATE " +
                            "            AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                            "            AND BE_ID = ? %s " +
                            "        ORDER BY %s %s) " +
                            "  ) " +
                            ") R1) " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d ",
                    staffIdFilter, orderByColumn, direction, fromRow, toRow
            );

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, currentDate);
            preparedStatement.setString(index++, bsnEntId);

            if (!StringUtil.IsNullOrEmpty(staffId)) {
                preparedStatement.setString(index++, staffId);
            }

            Response response = new Response();
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());

                List<AdministrativeStaffStaffCrossReference> resultList = (List<AdministrativeStaffStaffCrossReference>) new DataMapper().mapWithOffsetNext(resultSet, "com.sandata.lab.data.model.dl.model.AdministrativeStaffStaffCrossReference", 2);

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

    public List<BusinessEntityLineOfBusinessLookup> getBeLobLkupPK(String bsnEntId) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String callMethod = "{?=call COREDATA.PKG_BE_LOB.getBeLobLkup(?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setString(2, bsnEntId);
            callableStatement.execute();

            resultSet = (ResultSet) callableStatement.getObject(1);

            List<BusinessEntityLineOfBusinessLookup> resultList =
                    (List<BusinessEntityLineOfBusinessLookup>) new DataMapper().mapWithOffset(resultSet, "com.sandata.lab.data.model.dl.model.BusinessEntityLineOfBusinessLookup", 3);

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

    /**
     * not to manage connection that other process will handle this connection
     *
     * @param connectionType
     * @param connection
     * @param packageName
     * @param methodName
     * @param className
     * @param sequenceKey
     * @return
     * @throws SandataRuntimeException
     */
    public Object executeGet(ConnectionType connectionType, Connection connection, String packageName, String methodName, String className, long sequenceKey)
            throws SandataRuntimeException {

        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            String callMethod = String.format("{?=call %s.%s.%s(?)}", connectionType, packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setLong(2, sequenceKey);
            callableStatement.execute();
            resultSet = (ResultSet) callableStatement.getObject(1);

            Object result = new DataMapper().map(resultSet, className);

            return result;

        } catch (Exception e) {

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
        }
    }

    public void deleteBusinessEntityStaffTrainingCategoryList(Connection connection, String staffTrainingCode, String bsnEntId) throws SandataRuntimeException {

        CallableStatement callableStatement = null;

        try {
            String callMethod = "{?=call PKG_AM_UTIL.DELETE_BE_STAFF_TRNG_CTGY_LST(?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);

            int index = 2;
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, staffTrainingCode);

            callableStatement.execute();


        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s", e.getClass().getName(), e.getMessage()), e);
        } finally {
            // Close the statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    public Object getEntitiesForId(final ConnectionType connectionType, Connection connection, final String sql, final String className, final Object... params)
            throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (Object object : params) {
                preparedStatement.setObject(index++, object);
            }

            resultSet = preparedStatement.executeQuery();

            Object result = new DataMapper().map(resultSet, className);

            return result;

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

    /**
     * @param compCode Used to check if it is Requisite item for other items
     * @param bsnEntId
     * @return
     */
    public boolean isComplianceUsedAsRequisiteForOthers(String compCode, String bsnEntId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean isUsedByOthers = false;

        try {
            connection = getOracleConnection();
            String sql = "SELECT COUNT(*) " +
                    "FROM BE_COMP_LKUP T1 " +
                    "    LEFT JOIN BE_COMP_REL T2 ON T1.BE_ID = T2.BE_ID AND T1.COMP_CODE = T2.COMP_PAR_CODE " +
                    "    LEFT JOIN STAFF_COMP T3 ON T1.BE_ID = T3.BE_ID AND T1.COMP_CODE = T3.COMP_CODE " +
                    "WHERE T1.COMP_CODE = ? AND T1.BE_ID = ? " +
                    "    AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1) " +
                    "    AND ( " +

                    //is it the requisite for other item?
                    "        ( " +
                    "            (TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T2.CURR_REC_IND = 1) " +
                    "            AND T2.COMP_CODE IS NOT NULL AND T2.COMP_PAR_CODE IS NOT NULL " +
                    "            AND T2.COMP_CODE <> T2.COMP_PAR_CODE " +
                    "        ) " +
                    "        OR " +
                    //is it assigned to staff?
                    "        ( " +
                    "            (TO_CHAR(T3.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T3.CURR_REC_IND = 1) " +
                    "            AND T3.COMP_CODE IS NOT NULL AND T3.STAFF_ID IS NOT NULL" +
                    "        ) " +
                    "    ) ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, compCode);
            preparedStatement.setString(2, bsnEntId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int resultCount = resultSet.getInt(1);

                isUsedByOthers = resultCount > 0;
            }

            return isUsedByOthers;

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

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    /**
     * The Compliance Name is unique if cannot find the same name in existing compliance items,
     * not to check Compliance which has SK=<code>exceptComplianceSK</code>
     *
     * @param compName
     * @param bsnEntId
     * @param exceptComplianceSK
     * @return
     */
    public boolean isComplianceNameUnique(Connection connection, String compName, String bsnEntId, BigInteger exceptComplianceSK) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean isUnique = true;

        try {
            String filterExceptComplianceSK = "";
            if (exceptComplianceSK != null && exceptComplianceSK.longValue() > 0) {
                filterExceptComplianceSK = "AND T1.BE_COMP_LKUP_SK <> " + exceptComplianceSK.longValue();
            }

            String sql = String.format("SELECT COUNT(*) FROM BE_COMP_LKUP T1 WHERE UPPER(T1.COMP_NAME) = ? AND T1.BE_ID = ? %s "
                            + "AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1)",
                    filterExceptComplianceSK);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, compName.trim().toUpperCase(Locale.ENGLISH));
            preparedStatement.setString(2, bsnEntId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int resultCount = resultSet.getInt(1);

                isUnique = resultCount == 0;
            }

            return isUnique;

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

    public Response findBeHcpLkup(String keyword, String bsnEntId, int page, int pageSize, String sortOn, String direction) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // Calculate row range.
        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        String orderByColumn = "UPPER(HCP_LAST_NAME)"; // Default
        switch (sortOn) {
            case "first_name":
                orderByColumn = "UPPER(HCP_FIRST_NAME)";
                break;
        }

        try {
            boolean bFilterByFirstAndLastName = false;
            String firstValue;
            String secondValue;
            String sql;

            // The keyword is either FirstName (space) LastName OR FirstName OR LastName
            String[] keywordParts = keyword.split(" ");
            if (keywordParts.length >= 2) {
                // If there is more than one space, we will ignore the other values
                bFilterByFirstAndLastName = true;
                firstValue = keywordParts[0];
                secondValue = keywordParts[1];
            } else {
                firstValue = keywordParts[0];
                secondValue = keywordParts[0];
            }

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            int index = 1;
            if (bFilterByFirstAndLastName) {
                sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                        " " +
                        "  SELECT * FROM ( " +
                        " " +
                        "      (SELECT * FROM BE_HCP_LKUP " +
                        "        WHERE ((UPPER(HCP_FIRST_NAME) LIKE ? AND UPPER(HCP_FIRST_NAME) LIKE ?) " +
                        "          OR (UPPER(HCP_FIRST_NAME) LIKE ? AND UPPER(HCP_FIRST_NAME) LIKE ?)) " +
                        "        AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                        "        AND BE_ID = ? " +
                        "        ORDER BY %s %s " +
                        "    ) " +
                        "  ) " +
                        " " +
                        ") R1) " +
                        " " +
                        "WHERE ROW_NUMBER BETWEEN %d AND %d", orderByColumn, direction, fromRow, toRow);

            } else {
                sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                        " " +
                        "  SELECT * FROM ( " +
                        " " +
                        "      (SELECT * FROM BE_HCP_LKUP " +
                        "        WHERE ((UPPER(HCP_FIRST_NAME) LIKE ? OR UPPER(HCP_FIRST_NAME) LIKE ?) " +
                        "          OR (UPPER(HCP_FIRST_NAME) LIKE ? OR UPPER(HCP_FIRST_NAME) LIKE ?)) " +
                        "        AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                        "        AND BE_ID = ? " +
                        "        ORDER BY %s %s " +
                        "    ) " +
                        "  ) " +
                        " " +
                        ") R1) " +
                        " " +
                        "WHERE ROW_NUMBER BETWEEN %d AND %d", orderByColumn, direction, fromRow, toRow);
            }

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(index++, "%" + firstValue.toUpperCase() + "%");
            preparedStatement.setString(index++, "%" + secondValue.toUpperCase() + "%");
            preparedStatement.setString(index++, "%" + secondValue.toUpperCase() + "%");
            preparedStatement.setString(index++, "%" + firstValue.toUpperCase() + "%");
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();
            Response response = new Response();
            if (resultSet.next()) {

                response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());

                List<BusinessEntityHealthcareProfessionalLookup> resultList =
                        (List<BusinessEntityHealthcareProfessionalLookup>) new DataMapper().mapWithOffsetNext(resultSet, "com.sandata.lab.data.model.dl.model.BusinessEntityHealthcareProfessionalLookup", 2);

                response.setData(resultList);
            }

            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction.toUpperCase());
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

    public List<PayerBillingCodeLookup> getPayerBillingCodeByBEAndPayerID(String businessEntityID, String payerID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.COREDATA);
            connection.setAutoCommit(true);

            StringBuilder stringBuilder = new StringBuilder("SELECT * FROM COREDATA.PAYER_BILLING_CODE_LKUP T1 " +
                    " WHERE TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1 AND T1.BE_ID = ? ");

            if (!StringUtil.IsNullOrEmpty(payerID)) {

                stringBuilder.append(" AND UPPER(T1.PAYER_ID) = ?");
            }


            preparedStatement = connection.prepareStatement(stringBuilder.toString());

            int i = 1;
            preparedStatement.setString(i++, businessEntityID);

            if (!StringUtil.IsNullOrEmpty(payerID)) {

                preparedStatement.setString(i++, payerID.toUpperCase());
            }

            resultSet = preparedStatement.executeQuery();

            List<PayerBillingCodeLookup> resultList =
                    (List<PayerBillingCodeLookup>) new DataMapper()
                            .map(resultSet, "com.sandata.lab.data.model.dl.model.PayerBillingCodeLookup");

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

    public List<ContractTask> getContractTasksByContractID(String contractID, String serviceName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.COREDATA);
            connection.setAutoCommit(true);

            StringBuilder stringBuilder = new StringBuilder("SELECT * FROM COREDATA.CONTR_TASK T1 " +
                    " WHERE TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1 AND CONTR_ID = ? ");

            if (!StringUtil.IsNullOrEmpty(serviceName)) {

                stringBuilder.append(" AND UPPER(T1.SVC_NAME) = ?");
            }


            preparedStatement = connection.prepareStatement(stringBuilder.toString());

            int i = 1;
            preparedStatement.setString(i++, contractID);

            if (!StringUtil.IsNullOrEmpty(serviceName)) {

                preparedStatement.setString(i++, serviceName.toUpperCase());
            }

            resultSet = preparedStatement.executeQuery();

            List<ContractTask> resultList =
                    (List<ContractTask>) new DataMapper()
                            .map(resultSet, "com.sandata.lab.data.model.dl.model.ContractTask");

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

    /**
     * @param bsnEntId
     * @param excpId
     * @return true if bsnEntId & excpId are existing in the table BE_EXCP_LST, otherwise false
     */
    public boolean isBeExcpExisting(String bsnEntId, BigInteger excpId) {
        String sql = "SELECT * FROM BE_EXCP_LST "
                + "WHERE BE_ID = ? AND EXCP_ID = ? "
                + "      AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

        Object[] params = new Object[]{bsnEntId, excpId};

        return hasRecords(ConnectionType.COREDATA, sql, params);
    }

    /**
     * @param bsnEntId
     * @param excpId
     * @param beExcpLstSk
     * @return true if bsnEntId & excpId with beExcpLstSk <> provided SK are existing in the table BE_EXCP_LST, otherwise false
     */
    public boolean isBeExcpExistingForUpdate(String bsnEntId, BigInteger excpId, BigInteger beExcpLstSk) {
        String sql = "SELECT * FROM BE_EXCP_LST "
                + "WHERE BE_ID = ? AND EXCP_ID = ? "
                + "      AND BE_EXCP_LST_SK <> ? "
                + "      AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

        Object[] params = new Object[]{bsnEntId, excpId, beExcpLstSk};

        return hasRecords(ConnectionType.COREDATA, sql, params);
    }

    /**
     * @param payerId
     * @param contractId
     * @param excpId
     * @return true if the record with payerId & contractId & excpId are existing in the table CONTR_EXCP_LST, otherwise false
     */
    public boolean isContrExcpExisting(String payerId, String contractId, BigInteger excpId) {
        String sql = "SELECT * FROM CONTR_EXCP_LST "
                + "WHERE PAYER_ID = ? AND CONTR_ID = ? AND EXCP_ID = ? "
                + "      AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

        Object[] params = new Object[]{payerId, contractId, excpId};

        return hasRecords(ConnectionType.COREDATA, sql, params);
    }

    /**
     * @param payerId
     * @param contractId
     * @param excpId
     * @param contrExcpLstSk
     * @return true if the record with payerId & contractId & excpId with contrExcpLstSk <> provided SK are existing in the table CONTR_EXCP_LST, otherwise false
     */
    public boolean isContrExcpExistingForUpdate(String payerId, String contractId, BigInteger excpId, BigInteger contrExcpLstSk) {
        String sql = "SELECT * FROM CONTR_EXCP_LST "
                + "WHERE PAYER_ID = ? AND CONTR_ID = ? AND EXCP_ID = ? "
                + "      AND CONTR_EXCP_LST_SK <> ? "
                + "      AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

        Object[] params = new Object[]{payerId, contractId, excpId, contrExcpLstSk};

        return hasRecords(ConnectionType.COREDATA, sql, params);
    }

    private boolean hasRecords(ConnectionType connectionType, String sql, Object... params) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(connectionType);
            connection.setAutoCommit(true);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (Object param : params) {
                preparedStatement.setObject(index++, param);
            }

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("%s: %s: SQL=%s",
                    e.getClass().getName(), e.getMessage(), sql), e);

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

    /**
     * @param beExcpId
     * @param beExcpSetting
     * @param bsnEntId
     * @return APP_TENANT_KEY_CONF_SK for new MW_API_BE_EXCP_LIST_EXCEPTION_SETTING inserted or number of records updated
     * @throws SandataRuntimeException
     */
    public long insertOrUpdateBeExcpLstSetting(String beExcpId, String beExcpSetting, String bsnEntId) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(false);

            String callMethod = "{?=call PKG_APP_UTIL.UPDATE_BE_EXCP_LIST_SETTING(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);

            int index = 2;
            callableStatement.setString(index++, beExcpId);
            callableStatement.setString(index++, beExcpSetting);
            callableStatement.setString(index++, bsnEntId);

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

            throw new SandataRuntimeException(String.format("[PKG_APP_UTIL.UPDATE_BE_EXCP_LIST_SETTING]: beExcpId=%s, beExcpSetting=%s, bsnEntId=%s)",
                    beExcpId, beExcpSetting, bsnEntId,
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
        }
    }

    /**
     * @param beExcpId
     * @param bsnEntId
     * @return Additional settings for beExcpId
     * @throws SandataRuntimeException
     */
    public String getBeExcpLstSetting(String beExcpId, String bsnEntId) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(true);

            String callMethod = "{?=call PKG_APP_UTIL.GET_BE_EXCP_LIST_SETTING(?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.VARCHAR);

            int index = 2;
            callableStatement.setString(index++, beExcpId);
            callableStatement.setString(index++, bsnEntId);

            callableStatement.execute();
            return callableStatement.getString(1);

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("[PKG_APP_UTIL.GET_BE_EXCP_LIST_SETTING]: beExcpId=%s, bsnEntId=%s)",
                    beExcpId, bsnEntId,
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
        }
    }

    /**
     * @param beExcpId
     * @param bsnEntId
     * @return number of records deleted
     * @throws SandataRuntimeException
     */
    public long deleteBeExcpLstSetting(String beExcpId, String bsnEntId) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(false);

            String callMethod = "{?=call PKG_APP_UTIL.DELETE_BE_EXCP_LIST_SETTING(?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);

            int index = 2;
            callableStatement.setString(index++, beExcpId);
            callableStatement.setString(index++, bsnEntId);

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

            throw new SandataRuntimeException(String.format("[PKG_APP_UTIL.DELETE_BE_EXCP_LIST_SETTING]: beExcpId=%s, bsnEntId=%s)",
                    beExcpId, bsnEntId,
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
        }
    }

    /**
     * @param bsnEntId
     * @param qualifier
     * @param ruleId
     * @return true if Setting record of BE Rounding Rule with bsnEntId, qualifier & ruleId is existing, otherwise false.
     */
    public boolean isBeVvRndingRuleSettingExisting(String bsnEntId, String qualifier, String ruleId) {
        String sql =
                "SELECT T3.APP_TENANT_KEY_CONF_SK "
                        + "FROM "
                        + "  APP_TENANT_BE_XWALK XWALK JOIN APP_TENANT_KEY_CONF T1 ON XWALK.APP_TENANT_SK = T1.APP_TENANT_SK AND T1.APP_TENANT_KEY_CONF_PAR_SK IS NULL "
                        + "  JOIN APP_TENANT_KEY_CONF T2 ON T1.APP_TENANT_KEY_CONF_SK = T2.APP_TENANT_KEY_CONF_PAR_SK AND T1.APP_TENANT_SK = T2.APP_TENANT_SK "
                        + "  JOIN APP_TENANT_KEY_CONF T3 ON T2.APP_TENANT_KEY_CONF_SK = T3.APP_TENANT_KEY_CONF_PAR_SK AND T2.APP_TENANT_SK = T3.APP_TENANT_SK "
                        + "WHERE "
                        + "  XWALK.BE_ID = ? "
                        + "  AND T1.KEY_NAME = 'MW_API_BE_VV_RNDING_RULE' "
                        + "  AND T2.KEY_NAME = 'MW_API_BE_VV_RNDING_RULE_QLFR' AND T2.TENANT_KEY_CONF_VAL = ? "
                        + "  AND T3.KEY_NAME = 'MW_API_BE_VV_RNDING_RULE_ID' AND T3.TENANT_KEY_CONF_VAL = ?";

        Object[] params = new Object[]{bsnEntId, qualifier, ruleId};

        return hasRecords(ConnectionType.METADATA, sql, params);
    }

    /**
     * @param bsnEntId
     * @param payerId
     * @param contractId
     * @param qualifier
     * @param ruleId
     * @return true if Setting record of CONTR Rounding Rule with bsnEntId, payerId, contractId, qualifier & ruleId is existing, otherwise false.
     */
    public boolean isContrVvRndingRuleSettingExisting(String bsnEntId, String payerId, String contractId, String qualifier, String ruleId) {
        String sql =
                "SELECT T5.APP_TENANT_KEY_CONF_SK "
                        + "FROM "
                        + "  APP_TENANT_BE_XWALK XWALK JOIN APP_TENANT_KEY_CONF T1 ON XWALK.APP_TENANT_SK = T1.APP_TENANT_SK AND T1.APP_TENANT_KEY_CONF_PAR_SK IS NULL "
                        + "  JOIN APP_TENANT_KEY_CONF T2 ON T1.APP_TENANT_KEY_CONF_SK = T2.APP_TENANT_KEY_CONF_PAR_SK AND T1.APP_TENANT_SK = T2.APP_TENANT_SK "
                        + "  JOIN APP_TENANT_KEY_CONF T3 ON T2.APP_TENANT_KEY_CONF_SK = T3.APP_TENANT_KEY_CONF_PAR_SK AND T2.APP_TENANT_SK = T3.APP_TENANT_SK "
                        + "  JOIN APP_TENANT_KEY_CONF T4 ON T3.APP_TENANT_KEY_CONF_SK = T4.APP_TENANT_KEY_CONF_PAR_SK AND T3.APP_TENANT_SK = T4.APP_TENANT_SK "
                        + "  JOIN APP_TENANT_KEY_CONF T5 ON T4.APP_TENANT_KEY_CONF_SK = T5.APP_TENANT_KEY_CONF_PAR_SK AND T4.APP_TENANT_SK = T5.APP_TENANT_SK "
                        + "WHERE "
                        + "  XWALK.BE_ID = ? "
                        + "  AND T1.KEY_NAME = 'MW_API_CONTR_VV_RNDING_RULE' "
                        + "  AND T2.KEY_NAME = 'MW_API_CONTR_VV_RNDING_RULE_PAYER' AND T2.TENANT_KEY_CONF_VAL = ? "
                        + "  AND T3.KEY_NAME = 'MW_API_CONTR_VV_RNDING_RULE_CONTRACT' AND T3.TENANT_KEY_CONF_VAL = ? "
                        + "  AND T4.KEY_NAME = 'MW_API_CONTR_VV_RNDING_RULE_QLFR' AND T4.TENANT_KEY_CONF_VAL = ? "
                        + "  AND T5.KEY_NAME = 'MW_API_CONTR_VV_RNDING_RULE_ID' AND T5.TENANT_KEY_CONF_VAL = ?";

        Object[] params = new Object[]{bsnEntId, payerId, contractId, qualifier, ruleId};

        return hasRecords(ConnectionType.METADATA, sql, params);
    }

    public boolean getBeStaffTrngCtgryNameCanAdd(String bsnEntId, String staffTrngCtgryName) {
        String sql = "SELECT * " +
                "FROM " +
                "  BE_STAFF_TRNG_CTGY_LKUP WHERE BE_STAFF_TRNG_CTGY_LKUP.BE_ID = ? " +
                "  AND BE_STAFF_TRNG_CTGY_LKUP.STAFF_TRNG_CTGY_NAME = ? " +
                "  AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";
        Object[] params = new Object[]{bsnEntId, staffTrngCtgryName};
        return !hasRecords(ConnectionType.COREDATA, sql, params);
    }
    
    /**
     * remove Payer Billing Service Contract from Inactive contract if User un-selected the items
     * @param connection
     * @param bsnEntId
     * @param payerId
     * @param serviceNameList
     * @return
     */
    public int deletePayerServiceContractFromInactiveContract(Connection connection, String bsnEntId, String payerId, List<String> serviceNameList) {
        CallableStatement callableStatement = null;
        
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.STRING_ARRAY", connection);
            ARRAY serviceNameArray = new ARRAY(des, connection, serviceNameList.toArray());
            
            String callMethod = "{?=call PKG_AM_UTIL.DEL_PAYER_SERVICE_CONTR(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);

            int index = 2;
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, payerId);
            callableStatement.setArray(index++, serviceNameArray);

            return callableStatement.executeUpdate();

        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s", e.getClass().getName(), e.getMessage()), e);
        } finally {
            // Close the statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }
}
