package com.sandata.lab.rest.auth.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.dl.model.Authorization;
import com.sandata.lab.data.model.dl.model.AuthorizationLimitTypeName;
import com.sandata.lab.data.model.dl.model.AuthorizationServiceUnitName;
import com.sandata.lab.data.model.dl.model.PlanOfCare;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.auth.api.OracleService;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class OracleDataService implements OracleService {

    private ConnectionPoolDataService connectionPoolDataService;

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

            String callMethod = String.format("{?=call %s.%s.%s(?)}", ConnectionType.COREDATA, packageName, methodName);
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
    public Object executeGet(String packageName, String methodName, String className, long sequenceKey) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s.%s(?)}", ConnectionType.COREDATA, packageName, methodName);
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
    public long execute(String packageName, String methodName, long sequenceKey) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s.%s(?)}", ConnectionType.COREDATA, packageName, methodName);
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
    public long execute(String packageName, String methodName, Object jpubType) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s.%s(?)}", ConnectionType.COREDATA, packageName, methodName);
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
    public long execute(Connection connection, String packageName, String methodName, Object jpubType) throws SandataRuntimeException {

        CallableStatement callableStatement = null;

        try {

            String callMethod = String.format("{?=call %s.%s.%s(?)}", ConnectionType.COREDATA, packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
            callableStatement.setObject(2, jpubType);
            callableStatement.execute();
            long result = callableStatement.getLong(1);
            return result;
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

    public Object getEntitiesForId(final String sql, final String className, final Object... params) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            if ("com.sandata.lab.data.model.dl.model.ApplicationUserKeyConfiguration".equals(className)) {
                connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            } else {
                connection = connectionPoolDataService.getConnection();
            }
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
     * Returns a Response with data containing a List of active or historic
     * Authorizations and Orders specified by the historicOrActiveClause
     * for the specified patient ID, business entity ID, page, page size,
     * order by column, and direction.
     *
     * @param patientId          Specified patient ID.
     * @param bsnEntId           Specified business entity ID.
     * @param page               Specified page number.
     * @param pageSize           Specified page size.
     * @param orderByColumn      Specified column to order by.
     * @param orderByDirection   Specified direction to sort.
     * @param historicClauseAuth Specified where clause for active or historic.
     * @return Response object.
     * @throws SandataRuntimeException
     */
    public Response getHistoricOrActiveAuthAndOrderList(String patientId,
                                                        String bsnEntId,
                                                        int page,
                                                        int pageSize,
                                                        String orderByColumn,
                                                        String orderByDirection,
                                                        String historicClauseAuth,
                                                        String historicClauseOrder) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            int toRow = page * pageSize; // 9
            int fromRow = toRow - (pageSize - 1); // 8

            String sql = String.format("SELECT * FROM\n" +
                    "(SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( SELECT * FROM(SELECT * FROM (SELECT *\n" +
                    "FROM "
                    + ConnectionType.COREDATA
                    + ".AUTH WHERE PT_ID = ? AND BE_ID = ? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1') AND %s\n" +
                    "UNION\n" +
                    "SELECT ORD_SK AS AUTH_SK,\n" +
                    "       ORD_PAR_SK AS AUTH_PAR_SK,\n" +
                    "       NULL AS AUTH_ID,\n" +
                    "       REC_CREATE_TMSTP,\n" +
                    "       REC_UPDATE_TMSTP,\n" +
                    "       REC_EFF_TMSTP,\n" +
                    "       REC_TERM_TMSTP,\n" +
                    "       REC_CREATED_BY,\n" +
                    "       REC_UPDATED_BY,\n" +
                    "       CHANGE_REASON_MEMO,\n" +
                    "       CURR_REC_IND,\n" +
                    "       CHANGE_VERSION_ID,\n" +
                    "       BE_ID,\n" +
                    "       PT_ID,\n" +
                    "       PAYER_ID,\n" +
                    "       NULL AS ORD_SK,\n" +
                    "       ORD_ISSUED_DATE AS AUTH_ISSUED_DATE,\n" +
                    "       ORD_START_TMSTP AS AUTH_START_TMSTP,\n" +
                    "       ORD_END_TMSTP AS AUTH_END_TMSTP,\n" +
                    "       ORD_COMMENT AS AUTH_COMMENT,\n" +
                    "       ORD_LMT_TYP_NAME AS AUTH_LMT_TYP_NAME,\n" +
                    "       ORD_SVC_UNIT_NAME AS AUTH_SVC_UNIT_NAME,\n" +
                    "       ORD_LMT AS AUTH_LMT,\n" +
                    "       ORD_LMT_TOTAL AS AUTH_LMT_TOTAL,\n" +
                    "       ORD_LMT_DAY_1 AS AUTH_LMT_DAY_1,\n" +
                    "       ORD_LMT_START_TIME_DAY_1 AS AUTH_LMT_START_TIME_DAY_1,\n" +
                    "       ORD_LMT_END_TIME_DAY_1 AS AUTH_LMT_END_TIME_DAY_1,\n" +
                    "       ORD_LMT_DAY_2 AS AUTH_LMT_DAY_2,\n" +
                    "       ORD_LMT_START_TIME_DAY_2 AS AUTH_LMT_START_TIME_DAY_2,\n" +
                    "       ORD_LMT_END_TIME_DAY_2 AS AUTH_LMT_END_TIME_DAY_2,\n" +
                    "       ORD_LMT_DAY_3 AS AUTH_LMT_DAY_3,\n" +
                    "       ORD_LMT_START_TIME_DAY_3 AS AUTH_LMT_START_TIME_DAY_3,\n" +
                    "       ORD_LMT_END_TIME_DAY_3 AS AUTH_LMT_END_TIME_DAY_3,\n" +
                    "       ORD_LMT_DAY_4 AS AUTH_LMT_DAY_4,\n" +
                    "       ORD_LMT_START_TIME_DAY_4 AS AUTH_LMT_START_TIME_DAY_4,\n" +
                    "       ORD_LMT_END_TIME_DAY_4 AS AUTH_LMT_END_TIME_DAY_4,\n" +
                    "       ORD_LMT_DAY_5 AS AUTH_LMT_DAY_5,\n" +
                    "       ORD_LMT_START_TIME_DAY_5 AS AUTH_LMT_START_TIME_DAY_5,\n" +
                    "       ORD_LMT_END_TIME_DAY_5 AS AUTH_LMT_END_TIME_DAY_5,\n" +
                    "       ORD_LMT_DAY_6 AS AUTH_LMT_DAY_6,\n" +
                    "       ORD_LMT_START_TIME_DAY_6 AS AUTH_LMT_START_TIME_DAY_6,\n" +
                    "       ORD_LMT_END_TIME_DAY_6 AS AUTH_LMT_END_TIME_DAY_6,\n" +
                    "       ORD_LMT_DAY_7 AS AUTH_LMT_DAY_7,\n" +
                    "       ORD_LMT_START_TIME_DAY_7 AS AUTH_LMT_START_TIME_DAY_7,\n" +
                    "       ORD_LMT_END_TIME_DAY_7 AS AUTH_LMT_END_TIME_DAY_7,\n" +
                    "       CONTR_ID \n" +
                    "FROM " +
                    ConnectionType.COREDATA +
                    ".ORD WHERE PT_ID = ? AND BE_ID = ? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1') AND %s AND (SELECT COUNT(*) FROM AUTH WHERE AUTH.ORD_SK = ORD.ORD_SK) = 0)) ORDER BY %s %s, AUTH_SK ASC) R1)  WHERE ROW_NUMBER BETWEEN %s AND %s",
                historicClauseAuth,
                historicClauseOrder,
                orderByColumn,
                orderByDirection,
                fromRow,
                toRow);


            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, patientId);
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, patientId);
            preparedStatement.setString(index, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            Response response = new Response();
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(orderByDirection);
            response.setPage(page);
            response.setPageSize(pageSize);

            List<Authorization> authorizationList = mapResultSetToAuthorizations(resultSet, response);

            response.setData(authorizationList);

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
     * Returns List of AuthorizationService for the specified authorization SK.
     *
     * @param sk Specified authorization SK.
     * @return List of AuthorizationService.
     */
    public List getAuthOrOdSvcForSk(Long sk, String table, String column, String sortAscColumn, String className) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT * FROM %s.%s WHERE %s=? ORDER BY %s ASC",
                ConnectionType.COREDATA,
                table,
                column,
                sortAscColumn);

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, sk);

            resultSet = preparedStatement.executeQuery();

            List resultList =
                (List) new DataMapper().map(resultSet, String.format("com.sandata.lab.data.model.dl.model.%s", className));

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
     * Returns Plan of Cares for the specified parameters from an Authorization.
     *
     * @param bsnEntId        Specified business entity ID.
     * @param patientId       Specified patient ID.
     * @param authorizationId Specified authorization ID.
     * @param startTimestamp  Specified start timestamp.
     * @param endTimestamp    Specified end timestamp.
     * @return PlanOfCare List.
     */
    public List<PlanOfCare> getPlanOfCareForAuthorization(String bsnEntId,
                                                          String patientId,
                                                          String authorizationId,
                                                          Date startTimestamp,
                                                          Date endTimestamp) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            List<String> parameterList = new ArrayList<>();
            StringBuilder sqlStringBuilder = new StringBuilder();
            sqlStringBuilder.append("SELECT * FROM "
                + ConnectionType.COREDATA
                + ".POC"
                + " WHERE BE_ID=?"
                + " AND PT_ID=?");

            parameterList.add(bsnEntId);
            parameterList.add(patientId);

            // If this is an order append AUTH_ID.
            if (authorizationId != null
                && !authorizationId.isEmpty()) {
                sqlStringBuilder.append(" AND AUTH_ID=?");
                parameterList.add(authorizationId);
            }

            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            sqlStringBuilder.append(" AND POC_START_DATE = TO_DATE(?, 'MM/DD/YYYY HH24:MI:SS')");
            parameterList.add(dateFormat.format(startTimestamp));

            if (endTimestamp == null) {
                sqlStringBuilder.append(" AND POC_END_DATE IS NULL");
            } else {
                sqlStringBuilder.append(" AND POC_END_DATE = TO_DATE(?, 'MM/DD/YYYY HH24:MI:SS')");
                parameterList.add(dateFormat.format(endTimestamp));
            }

            preparedStatement = connection.prepareStatement(sqlStringBuilder.toString());

            int index = 1;
            for (String parameter : parameterList) {
                preparedStatement.setString(index++, parameter);
            }

            resultSet = preparedStatement.executeQuery();

            return (List<PlanOfCare>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.PlanOfCare");

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
     * Returns a List of Authorization mapped from the specified ResultSet
     * and sets the total number of rows in the specified Response.
     *
     * @param resultSet Specified ResultSet.
     * @param response  Specified Response.
     * @return List of Authorization.
     * @throws SQLException
     */
    private List<Authorization> mapResultSetToAuthorizations(ResultSet resultSet, Response response) throws SQLException {

        List<Authorization> authorizationList = new ArrayList<>();

        while (resultSet.next()) {

            if (response.getTotalRows() == 0) {
                response.setTotalRows(resultSet.getInt("TOTAL_ROWS"));
            }

            Authorization authorization = new Authorization();


            authorization.setAuthorizationSK(BigInteger.valueOf(resultSet.getBigDecimal("AUTH_SK").longValue()));

            BigDecimal authorizationParentSk = resultSet.getBigDecimal("AUTH_PAR_SK");
            if (authorizationParentSk != null) {
                authorization.setAuthorizationParentSK(BigInteger.valueOf(authorizationParentSk.longValue()));
            }

            authorization.setAuthorizationID(resultSet.getString("AUTH_ID"));
            authorization.setRecordCreateTimestamp(resultSet.getTimestamp("REC_CREATE_TMSTP"));
            authorization.setRecordUpdateTimestamp(resultSet.getTimestamp("REC_UPDATE_TMSTP"));
            authorization.setRecordEffectiveTimestamp(resultSet.getTimestamp("REC_EFF_TMSTP"));
            authorization.setRecordTerminationTimestamp(resultSet.getTimestamp("REC_TERM_TMSTP"));
            authorization.setRecordCreatedBy(resultSet.getString("REC_CREATED_BY"));
            authorization.setRecordUpdatedBy(resultSet.getString("REC_UPDATED_BY"));
            authorization.setChangeReasonMemo(resultSet.getString("CHANGE_REASON_MEMO"));
            authorization.setCurrentRecordIndicator(resultSet.getBoolean("CURR_REC_IND"));
            authorization.setChangeVersionID(BigInteger.valueOf(resultSet.getBigDecimal("CHANGE_VERSION_ID").longValue()));
            authorization.setBusinessEntityID(resultSet.getString("BE_ID"));
            authorization.setPatientID(resultSet.getString("PT_ID"));
            authorization.setPayerID(resultSet.getString("PAYER_ID"));
            BigDecimal orderSk = resultSet.getBigDecimal("ORD_SK");
            if (orderSk != null) {
                authorization.setOrderSK(BigInteger.valueOf(orderSk.longValue()));
            }

            authorization.setAuthorizationIssuedDate(resultSet.getTimestamp("AUTH_ISSUED_DATE"));
            authorization.setAuthorizationStartTimestamp(resultSet.getTimestamp("AUTH_START_TMSTP"));
            authorization.setAuthorizationEndTimestamp(resultSet.getTimestamp("AUTH_END_TMSTP"));
            authorization.setAuthorizationComment(resultSet.getString("AUTH_COMMENT"));

            String authServiceUnitString = resultSet.getString("AUTH_SVC_UNIT_NAME");
            if (authServiceUnitString != null
                && !authServiceUnitString.isEmpty()) {
                authorization.setAuthorizationServiceUnitName(AuthorizationServiceUnitName.fromValue(authServiceUnitString));
            }

            String authLimitTypeString = resultSet.getString("AUTH_LMT_TYP_NAME");
            if (authLimitTypeString != null
                && !authLimitTypeString.isEmpty()) {
                authorization.setAuthorizationLimitTypeName(AuthorizationLimitTypeName.fromValue(authLimitTypeString));
            }

            authorization.setAuthorizationLimit(resultSet.getBigDecimal("AUTH_LMT"));
            authorization.setAuthorizationLimitTotal(resultSet.getBigDecimal("AUTH_LMT_TOTAL"));
            authorization.setAuthorizationLimitDay1(resultSet.getBigDecimal("AUTH_LMT_DAY_1"));
            authorization.setAuthorizationLimitStartTimeDay1(resultSet.getTimestamp("AUTH_LMT_START_TIME_DAY_1"));
            authorization.setAuthorizationLimitEndTimeDay1(resultSet.getTimestamp("AUTH_LMT_END_TIME_DAY_1"));
            authorization.setAuthorizationLimitDay2(resultSet.getBigDecimal("AUTH_LMT_DAY_2"));
            authorization.setAuthorizationLimitStartTimeDay2(resultSet.getTimestamp("AUTH_LMT_START_TIME_DAY_2"));
            authorization.setAuthorizationLimitEndTimeDay2(resultSet.getTimestamp("AUTH_LMT_END_TIME_DAY_2"));
            authorization.setAuthorizationLimitDay3(resultSet.getBigDecimal("AUTH_LMT_DAY_3"));
            authorization.setAuthorizationLimitStartTimeDay3(resultSet.getTimestamp("AUTH_LMT_START_TIME_DAY_3"));
            authorization.setAuthorizationLimitEndTimeDay3(resultSet.getTimestamp("AUTH_LMT_END_TIME_DAY_3"));
            authorization.setAuthorizationLimitDay4(resultSet.getBigDecimal("AUTH_LMT_DAY_4"));
            authorization.setAuthorizationLimitStartTimeDay4(resultSet.getTimestamp("AUTH_LMT_START_TIME_DAY_4"));
            authorization.setAuthorizationLimitEndTimeDay4(resultSet.getTimestamp("AUTH_LMT_END_TIME_DAY_4"));
            authorization.setAuthorizationLimitDay5(resultSet.getBigDecimal("AUTH_LMT_DAY_5"));
            authorization.setAuthorizationLimitStartTimeDay5(resultSet.getTimestamp("AUTH_LMT_START_TIME_DAY_5"));
            authorization.setAuthorizationLimitEndTimeDay5(resultSet.getTimestamp("AUTH_LMT_END_TIME_DAY_5"));
            authorization.setAuthorizationLimitDay6(resultSet.getBigDecimal("AUTH_LMT_DAY_6"));
            authorization.setAuthorizationLimitStartTimeDay6(resultSet.getTimestamp("AUTH_LMT_START_TIME_DAY_6"));
            authorization.setAuthorizationLimitEndTimeDay6(resultSet.getTimestamp("AUTH_LMT_END_TIME_DAY_6"));
            authorization.setAuthorizationLimitDay7(resultSet.getBigDecimal("AUTH_LMT_DAY_7"));
            authorization.setAuthorizationLimitStartTimeDay7(resultSet.getTimestamp("AUTH_LMT_START_TIME_DAY_7"));
            authorization.setAuthorizationLimitEndTimeDay7(resultSet.getTimestamp("AUTH_LMT_END_TIME_DAY_7"));

            authorizationList.add(authorization);
        }

        return authorizationList;
    }

    public int cancelSchedEventsForEndDatedAuth(Connection connection,
                                                String bsnEntId,
                                                String patientID,
                                                Date authEndTimestamp) {
        CallableStatement callableStatement = null;

        try {

            String authEndTimestampString = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(authEndTimestamp);

            String callMethod = "{?=call PKG_AUTH_UTIL.CANCEL_SCHED_EVENTS_FOR_AUTH(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);

            int index = 2;
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, patientID);
            callableStatement.setString(index++, authEndTimestampString);

            callableStatement.execute();

            return (Integer)callableStatement.getObject(1);

        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s",
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
        }
    }


    /**
     * For get the APP_USER_KEY_CONF from METADATA
     */
    @Override
    public Object executeGet(ConnectionType connectionType, String packageName,
                             String methodName, String className, long sequenceKey) throws SandataRuntimeException {
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


    /**
     * @param connectionType
     * @param packageName
     * @param methodName
     * @param sequenceKey
     * @return
     * @throws SandataRuntimeException
     */
    @Override
    public long execute(ConnectionType connectionType, String packageName,
                       String methodName, long sequenceKey) throws SandataRuntimeException {
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

    public Object executeGet(Connection connection, String packageName, String methodName, String className, Object... params)
            throws SandataRuntimeException {

            CallableStatement callableStatement = null;
            ResultSet resultSet = null;

            try {
                ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.STRING_ARRAY", connection);
                ARRAY primaryKeysArray = new ARRAY(des, connection, params);

                String callMethod = String.format("{?=call %s.%s.%s(?)}", ConnectionType.COREDATA, packageName, methodName);
                callableStatement = connection.prepareCall(callMethod);
                callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
                callableStatement.setArray(2, primaryKeysArray);
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
    
    public int updateAuthOrOrderRelevantTables(Connection connection, long oldSK, long newSK, String qualifier) 
            throws SandataRuntimeException {

        CallableStatement callableStatement = null;

        try {

            String callMethod = String.format("{?=call PKG_HIST.updateAuthSkForRelatedTables(?,?,?)}");
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setBigDecimal(2, BigDecimal.valueOf(oldSK));
            callableStatement.setBigDecimal(3, BigDecimal.valueOf(newSK));
            callableStatement.setString(4, qualifier);
            callableStatement.execute();

            int result = callableStatement.getInt(1);

            return result;
        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("[%s][%s]: %s: %s", 
                    "OracleDataService", 
                    "updateAuthOrOrderRelevantTables",
                    e.getClass().getName(), 
                    e.getMessage()), e);

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
    
    public int updateOrderRelevantTablesForTransformedAuthorization(Connection connection, long oldSK, long newSK) 
            throws SandataRuntimeException {

        CallableStatement callableStatement = null;

        try {

            String callMethod = String.format("{?=call PKG_HIST.changeQlfrFromOrderToAuth(?,?)}");
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setBigDecimal(2, BigDecimal.valueOf(oldSK));
            callableStatement.setBigDecimal(3, BigDecimal.valueOf(newSK));
            callableStatement.execute();

            int result = callableStatement.getInt(1);

            return result;
        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("[%s][%s]: %s: %s", 
                    "OracleDataService", 
                    "updateAuthOrOrderRelevantTables",
                    e.getClass().getName(), 
                    e.getMessage()), e);

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

}
