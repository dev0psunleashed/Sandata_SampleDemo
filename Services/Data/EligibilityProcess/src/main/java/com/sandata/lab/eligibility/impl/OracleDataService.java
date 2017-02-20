package com.sandata.lab.eligibility.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.dl.model.ApplicationTenantKeyConfiguration;
import com.sandata.lab.data.model.dl.model.Eligibility;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.eligibility.api.OracleService;
import com.sandata.lab.eligibility.utils.log.LoggingUtils;

import oracle.jdbc.OracleTypes;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service class to access Oracle database based on {@link ConnectionPoolDataService} to get database {@link Connection}
 */
public class OracleDataService implements OracleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleDataService.class);
    private ConnectionPoolDataService connectionPoolDataService;

    public void setConnectionPoolDataService(ConnectionPoolDataService connectionPoolDataService) {
        this.connectionPoolDataService = connectionPoolDataService;
    }

    public Connection getOracleConnection() throws SandataRuntimeException {
        return connectionPoolDataService.getConnection();
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

    /**
     * Gets Eligibility Inquiry record in Map&lt;String, Object&gt; for
     * specified {@code patientPayerSk}
     * 
     * @param patientPayerSk
     *            the patient payer SK to get eligibility inquiry
     * @return an eligibility inquiry record in Map&lt;String, Object&gt;; null
     *         if not found.
     * @throws SandateRuntimeException
     *             (runtime) if exception happens when executing the SQL
     */
    public Map<String, Object> getEligibilityInquiryByPatientPayerSk(Integer patientPayerSk) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Map<String, Object> map = null;
        String sql = getSqlToBuildEligibilityInquiry();

        try {
            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            preparedStatement.setInt(index, patientPayerSk);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                map = toMap(resultSet);
            }

            connection.commit();

            return map;
        } catch (Exception e) {

            safeRollback(connection);

            String errorMessage = LoggingUtils.getErrorMessageInfo(this, "getEligibilityInquiryByPatientPayerSk",
                    String.format("An error happened when executing the SQL = %s, patientPayerSk=%s, error=%s", sql,
                            patientPayerSk, e.getMessage()));
            LOGGER.error(errorMessage, e);
            throw new SandataRuntimeException(errorMessage, e);

        } finally {

            safeClose(resultSet);
            safeClose(preparedStatement);
            safeClose(connection);

        }
    }

    private String getSqlToBuildEligibilityInquiry() {
        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("SELECT DISTINCT ");
        sqlBuilder.append("  PT.PT_FIRST_NAME, PT.PT_MIDDLE_NAME, PT.PT_LAST_NAME, PT.PT_GENDER_TYP_NAME, PT.PT_DOB, PT.PT_TIN, PT.PT_ID, PT_MEDICAID_ID");
        sqlBuilder.append("  , PP.PAYER_ID, PP.PT_PAYER_PMT_RESP_VAL");
        sqlBuilder.append("  , PPI.PT_INS_START_DATE, PPI.PT_INS_END_DATE");
        sqlBuilder.append("  , BE.BE_NPI, BE.BE_ID");
        sqlBuilder.append("  , PCA.PT_ADDR1, PCA.PT_ADDR2, PCA.PT_CITY, PCA.PT_STATE, PCA.PT_PSTL_CODE");
        sqlBuilder.append("  , PCPH.PT_PHONE AS PT_PHONE_HOME, PCPW.PT_PHONE AS PT_PHONE_WORK, PCPC.PT_PHONE AS PT_PHONE_CELL");
        sqlBuilder.append(" ");
        sqlBuilder.append("FROM ");
        sqlBuilder.append("  COREDATA.PT JOIN COREDATA.PT_PAYER PP ON PT.PT_ID = PP.PT_ID AND PT.BE_ID = PP.BE_ID");
        sqlBuilder.append("  JOIN COREDATA.PT_PAYER_INS PPI ON PT.PT_ID = PPI.PT_ID AND PT.BE_ID = PPI.BE_ID AND PP.PAYER_ID = PPI.PAYER_ID");
        sqlBuilder.append("  JOIN COREDATA.BE ON PT.BE_ID = BE.BE_ID");
        sqlBuilder.append("  LEFT JOIN (");
        sqlBuilder.append("    SELECT PCA1.*, ROW_NUMBER() OVER(PARTITION BY PT_ID ORDER BY REC_UPDATE_TMSTP DESC) AS ROW_NUM");
        sqlBuilder.append("    FROM COREDATA.PT_CONT_ADDR PCA1");
        sqlBuilder.append("    WHERE (TO_CHAR(PCA1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND PCA1.CURR_REC_IND = 1)");
        sqlBuilder.append("  ) PCA ON PT.PT_ID = PCA.PT_ID AND PT.BE_ID = PCA.BE_ID AND PCA.ROW_NUM = 1");
        // home phone
        sqlBuilder.append("  LEFT JOIN (");
        sqlBuilder.append("    SELECT PCPH1.*, ROW_NUMBER() OVER(PARTITION BY PT_ID ORDER BY REC_UPDATE_TMSTP DESC) AS ROW_NUM");
        sqlBuilder.append("    FROM COREDATA.PT_CONT_PHONE PCPH1");
        sqlBuilder.append("    WHERE (TO_CHAR(PCPH1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND PCPH1.CURR_REC_IND = 1 AND PCPH1.PT_CONT_PHONE_QLFR = 'HOME')");
        sqlBuilder.append("  ) PCPH ON PT.PT_ID = PCPH.PT_ID AND PT.BE_ID = PCPH.BE_ID AND PCPH.ROW_NUM = 1");
        // work phone
        sqlBuilder.append("  LEFT JOIN (");
        sqlBuilder.append("    SELECT PCPW1.*, ROW_NUMBER() OVER(PARTITION BY PT_ID ORDER BY REC_UPDATE_TMSTP DESC) AS ROW_NUM");
        sqlBuilder.append("    FROM COREDATA.PT_CONT_PHONE PCPW1");
        sqlBuilder.append("    WHERE (TO_CHAR(PCPW1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND PCPW1.CURR_REC_IND = 1 AND PCPW1.PT_CONT_PHONE_QLFR = 'WORK')");
        sqlBuilder.append("  ) PCPW ON PT.PT_ID = PCPW.PT_ID AND PT.BE_ID = PCPW.BE_ID AND PCPW.ROW_NUM = 1");
        // cell phone
        sqlBuilder.append("  LEFT JOIN (");
        sqlBuilder.append("    SELECT PCPC1.*, ROW_NUMBER() OVER(PARTITION BY PT_ID ORDER BY REC_UPDATE_TMSTP DESC) AS ROW_NUM");
        sqlBuilder.append("    FROM COREDATA.PT_CONT_PHONE PCPC1");
        sqlBuilder.append("    WHERE (TO_CHAR(PCPC1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND PCPC1.CURR_REC_IND = 1 AND PCPC1.PT_CONT_PHONE_QLFR = 'MOBILE')");
        sqlBuilder.append("  ) PCPC ON PT.PT_ID = PCPC.PT_ID AND PT.BE_ID = PCPC.BE_ID AND PCPC.ROW_NUM = 1");
        sqlBuilder.append(" ");
        sqlBuilder.append("WHERE ");
        sqlBuilder.append("  (TO_CHAR(PT.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND PT.CURR_REC_IND = 1)");
        sqlBuilder.append("  AND (TO_CHAR(PP.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND PP.CURR_REC_IND = 1)");
        sqlBuilder.append("  AND (TO_CHAR(PPI.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND PPI.CURR_REC_IND = 1)");
        sqlBuilder.append("  AND (TO_CHAR(BE.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND BE.CURR_REC_IND = 1)");
        sqlBuilder.append("  AND (PP.PT_PAYER_SK = ?)");

        return sqlBuilder.toString();
    }

    private Map<String, Object> toMap(ResultSet resultSet) throws SQLException {
        Map<String, Object> map = new HashMap<String, Object>();
        ResultSetMetaData meta = resultSet.getMetaData();
        for (int i = 1; i <= meta.getColumnCount(); i++) {
            String key = meta.getColumnName(i);
            Object value = resultSet.getObject(key);
            map.put(key, value);
        }

        return map;
    }

    /**
     * Returns ApplicationTenantKeyConfiguration for the specified Application
     * Tenant SK and Key Name
     *
     * @param applicationTenantSk
     *            Specified ApplicationTenant SK.
     * @return an instance of {@link ApplicationTenantKeyConfiguration} or null
     *         if not found
     * @throws SandateRuntimeException
     *             (runtime) if exception happens when executing the SQL
     */
    public ApplicationTenantKeyConfiguration getAppTenantKeyConfigurationByAppTenantSkAndKeyName(long applicationTenantSk, String keyName) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(true);

            String sql = new StringBuilder()
                    .append("SELECT * FROM ")
                    .append(ConnectionType.METADATA).append(".APP_TENANT_KEY_CONF ")
                    .append("WHERE APP_TENANT_SK = ? AND KEY_NAME = ?")
                    .toString();

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setLong(index++, applicationTenantSk);
            preparedStatement.setString(index++, keyName);

            resultSet = preparedStatement.executeQuery();

            List<ApplicationTenantKeyConfiguration> list = (List<ApplicationTenantKeyConfiguration>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.ApplicationTenantKeyConfiguration");
            if (!list.isEmpty()) {
                return list.get(0);
            }

            return null;

        } catch (Exception e) {

            safeRollback(connection);

            String errorMessage = LoggingUtils.getErrorMessageInfo(this, "getAppTenantKeyConfigurationByAppTenantSkAndKeyName",
                    String.format(
                            "Error happened when getting ApplicationTenantKeyConfiguration for ApplicationTenant SK: %s, keyName: %s, error: %s",
                            applicationTenantSk, keyName, e.getMessage()));

            LOGGER.error(errorMessage, e);
            throw new SandataRuntimeException(errorMessage, e.getCause());

        } finally {

            safeClose(resultSet);
            safeClose(preparedStatement);
            safeClose(connection);

        }

    }

    /**
     * Gets objects type of {@code className} by specified {@code sql} and
     * {@code params}
     * 
     * @param connectionType
     *            {@link ConnectionType} to use to get database connection to
     *            execute the specified {@code sql}
     * @param sql
     *            SQL statement to execute to get records
     * @param className
     *            the class name to map database records to objects
     * @param params
     *            parameters to pass to parameterized SQL
     *
     * @return a list of objects type of {@code className}
     * 
     * @throws SandateRuntimeException
     *             (runtime) if exception happens when executing the SQL
     */
    public Object getEntitiesById(final ConnectionType connectionType, final String sql, final String className, final Object... params) throws SandataRuntimeException {

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

            safeRollback(connection);

            String errorMessage = LoggingUtils.getErrorMessageInfo(this, "getEntitiesById",
                    String.format(
                            "Error happened when getting entities by id, connectionType: %s, sql: %s, className: %s, params: %s, error: %s",
                            connectionType, sql, className, params, e.getMessage()));
            
            LOGGER.error(errorMessage, e);
            throw new SandataRuntimeException(errorMessage, e.getCause());

        } finally {

            safeClose(resultSet);
            safeClose(preparedStatement);
            safeClose(connection);

        }
    }

    /**
     * Updates eligibility status to database
     * 
     * @param eligibility
     *            an instance of {@link Eligibility} with status to update to
     *            database
     * @throws IllegalArgumentException
     *             (runtime) if {@code eligibility} is null
     * @throws SandateRuntimeException
     *             (runtime) if exception happens when updating eligibility
     *             status
     */
    public void updateEligibilityStatus(Eligibility eligibility) {

        Validate.isTrue(eligibility != null, "eligibility must not be null");

        String methodName = "updateEligibilityStatus";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = new StringBuilder()
                .append("UPDATE ").append(ConnectionType.COREDATA).append(".ELIG SET ELIG_STATUS_NAME = ? WHERE ELIG_SK = ?")
                .toString();

        try {

            LOGGER.debug(
                    LoggingUtils.getErrorMessageInfo(this, methodName, "Updating ELIG_STATUS_NAME={} for ELIG_SK={}"),
                    eligibility.getEligibilityStatusName(), eligibility.getEligibilitySK());

            connection = connectionPoolDataService.getConnection(ConnectionType.COREDATA);
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, eligibility.getEligibilityStatusName().toString());
            preparedStatement.setObject(2, eligibility.getEligibilitySK(), OracleTypes.NUMBER);
            preparedStatement.executeUpdate();

            connection.commit();

            LOGGER.debug(
                    LoggingUtils.getErrorMessageInfo(this, methodName, "Updated ELIG_STATUS_NAME={} for ELIG_SK={}"),
                    eligibility.getEligibilityStatusName(), eligibility.getEligibilitySK());

        } catch (Exception e) {

            safeRollback(connection);

            String errorMessage = LoggingUtils.getErrorMessageInfo(this, methodName, String.format(
                    "Error happened when updating Eligibility status, sql: %s, ELIG_STATUS_NAME: %s, ELIG_SK: %s, error: %s",
                    sql, eligibility.getEligibilityStatusName(), eligibility.getEligibilitySK(), e.getMessage()));

            LOGGER.error(errorMessage, e);

            throw new SandataRuntimeException(errorMessage, e.getCause());

        } finally {

            safeClose(preparedStatement);
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
