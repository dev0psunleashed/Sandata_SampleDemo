package com.sandata.lab.security.auth.services.oracle;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.extended.ApplicationUserExt;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.security.auth.api.OracleService;
import com.sandata.lab.security.auth.model.FindUserResult;
import com.sandata.lab.security.auth.model.jpub.TmpAppRoleTab;
import com.sandata.lab.security.auth.model.jpub.TmpAppRoleTyp;
import com.sandata.lab.security.auth.services.oracle.enums.Columns;
import com.sandata.lab.security.auth.services.oracle.enums.Tables;
import com.sandata.lab.security.auth.utils.cache.CacheUtil;
import com.sandata.lab.security.auth.utils.cache.Caches;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.Datum;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.*;

import static java.lang.String.format;

public class MetaDataService implements OracleService {

    private CacheUtil cacheUtil;

    public void setCacheUtil(CacheUtil cacheUtil) {
        this.cacheUtil = cacheUtil;
    }

    public String getTenantSKByBE(String businessEntityID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // Attempt to retrieve tenant_sk from cache before querying database
        String result = (String) cacheUtil.getValue(Caches.BE_TNT_ID_XWALK.getValue(), businessEntityID);

        if (!StringUtil.IsNullOrEmpty(result)) {
            return result;
        }

        try {

            connection = getOracleConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT %s FROM %s.%s WHERE %s = ? ",
                    Columns.META_TENANT_BE_XWALK_TENANT_SK.getValue(),
                    ConnectionType.METADATA,
                    Tables.META_TENANT_BE_XWALK.getValue(),
                    Columns.META_TENANT_BE_XWALK_BE_ID.getValue());

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, businessEntityID);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result = resultSet.getString(Columns.META_TENANT_BE_XWALK_TENANT_SK.getValue());
            }

            //Update cache with retrieved crosswalk value
            cacheUtil.putValue(Caches.BE_TNT_ID_XWALK.getValue(), businessEntityID, result);

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

    public ApplicationUserExt getApplicationUserByUserName(String userName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = getOracleConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT * FROM %s.%s T1 " +
                            " JOIN %s.%s T2 ON T1.%s = T2.%s " +
                            " WHERE UPPER(T1.%s) = ? ",
                    ConnectionType.METADATA,
                    Tables.META_APP_USER.getValue(),
                    ConnectionType.METADATA,
                    Tables.META_APP_USER_ROLE.getValue(),
                    Columns.META_APP_USER_SK.getValue(),
                    Columns.META_APP_USER_SK.getValue(),
                    "USER_NAME");

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, userName.toUpperCase());

            resultSet = preparedStatement.executeQuery();

            return createApplicationUserExtFromResultSet(resultSet);

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

    public ApplicationUserExt getApplicationUserBySk(BigDecimal userSk) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = getOracleConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT * FROM %s.%s T1 " +
                            " JOIN %s.%s T2 ON T1.%s = T2.%s " +
                            " WHERE UPPER(T1.%s) = ? ",
                    ConnectionType.METADATA,
                    Tables.META_APP_USER.getValue(),
                    ConnectionType.METADATA,
                    Tables.META_APP_USER_ROLE.getValue(),
                    Columns.META_APP_USER_SK.getValue(),
                    Columns.META_APP_USER_SK.getValue(),
                    "APP_USER_SK");

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setBigDecimal(1, userSk);

            resultSet = preparedStatement.executeQuery();

            return createApplicationUserExtFromResultSet(resultSet);

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

    public ApplicationUserExt getApplicationUserByUserName(String userName, BigDecimal tenantSk) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = getOracleConnection();
            connection.setAutoCommit(true);

            String sql = String.format(
                    "SELECT DISTINCT * FROM %s.%s T1 " +
                            "JOIN %s.%s T2 ON T1.%s = T2.%s  WHERE UPPER(T1.%s) = ? AND T2.%s = ? ",
                    ConnectionType.METADATA,
                    Tables.META_APP_USER.getValue(),
                    ConnectionType.METADATA,
                    Tables.META_APP_USER_ROLE.getValue(),
                    Columns.META_APP_USER_SK.getValue(),
                    Columns.META_APP_USER_SK.getValue(),
                    "USER_NAME",
                    Columns.META_TENANT_BE_XWALK_TENANT_SK.getValue());

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, userName.toUpperCase());
            preparedStatement.setBigDecimal(2, tenantSk);

            resultSet = preparedStatement.executeQuery();

            return createApplicationUserExtFromResultSet(resultSet);

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

    private ApplicationUserExt createApplicationUserExtFromResultSet(ResultSet resultSet) throws Exception {

        //UserName is a unique key in MetaData
        ApplicationUserExt applicationUser = null;


        Map<BigInteger, ApplicationUserRole> applicationUserRoleMap = new HashMap<>();

        while (resultSet.next()) {

            if (applicationUser == null) {
                applicationUser = new ApplicationUserExt();
            }
            applicationUser.setApplicationUserSK(resultSet.getBigDecimal("APP_USER_SK").toBigInteger());
            applicationUser.setRecordUpdateTimestamp(resultSet.getTimestamp("REC_UPDATE_TMSTP"));
            applicationUser.setRecordCreateTimestamp(resultSet.getTimestamp("REC_CREATE_TMSTP"));
            applicationUser.setUserName(resultSet.getString("USER_NAME"));
            applicationUser.setUserGloballyUniqueIdentifier(resultSet.getString("USER_GUID"));
            applicationUser.setUserLastName(resultSet.getString("USER_LAST_NAME"));
            applicationUser.setUserMiddleName(resultSet.getString("USER_MIDDLE_NAME"));
            applicationUser.setUserFirstName(resultSet.getString("USER_FIRST_NAME"));

            //There can be multiple roles for a user
            //Store each one in a map
            ApplicationUserRole applicationUserRole = new ApplicationUserRole();
            applicationUserRole.setApplicationTenantSK(resultSet.getBigDecimal("APP_TENANT_SK").toBigInteger());
            applicationUserRole.setRecordCreateTimestamp(resultSet.getTimestamp("REC_CREATE_TMSTP"));
            applicationUserRole.setRecordUpdateTimestamp(resultSet.getTimestamp("REC_UPDATE_TMSTP"));
            applicationUserRole.setApplicationRoleSK(resultSet.getBigDecimal("APP_ROLE_SK").toBigInteger());
            applicationUserRole.setApplicationUserSK(resultSet.getBigDecimal("APP_USER_SK").toBigInteger());
            applicationUserRole.setApplicationUserRoleSK(resultSet.getBigDecimal("APP_USER_ROLE_SK").toBigInteger());
            applicationUserRoleMap.put(applicationUserRole.getApplicationUserRoleSK(), applicationUserRole);
        }

        if (applicationUserRoleMap.size() > 0) {
            applicationUser.getApplicationUserRole().addAll(new ArrayList<ApplicationUserRole>(applicationUserRoleMap.values()));
        }

        return applicationUser;

    }

    public boolean applicationUserExists(String userName, BigDecimal tenantSk) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = getOracleConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT 'TRUE' AS USER_EXISTS FROM DUAL WHERE EXISTS ( " +
                            "SELECT * FROM %s.%s T1 " +
                            "JOIN %s.%s T2 ON T1.%s = T2.%s  WHERE UPPER(T1.%s) = ? AND T2.%s = ? )",
                    ConnectionType.METADATA,
                    Tables.META_APP_USER.getValue(),
                    ConnectionType.METADATA,
                    Tables.META_APP_USER_ROLE.getValue(),
                    Columns.META_APP_USER_SK.getValue(),
                    Columns.META_APP_USER_SK.getValue(),
                    "USER_NAME",
                    Columns.META_TENANT_BE_XWALK_TENANT_SK.getValue());

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, userName.toUpperCase());
            preparedStatement.setBigDecimal(2, tenantSk);

            resultSet = preparedStatement.executeQuery();

            //UserName is a unique key in MetaData

            boolean result = false;

            while (resultSet.next()) {

                result = resultSet.getString("USER_EXISTS").equals("TRUE") ? true : false;
            }

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

    public boolean applicationUserExists(String userName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = getOracleConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT 'TRUE' AS USER_EXISTS FROM DUAL WHERE EXISTS ( " +
                            "SELECT * FROM %s.%s T1 " +
                            "JOIN %s.%s T2 ON T1.%s = T2.%s  WHERE UPPER(T1.%s) = ? AND ROWNUM = 1)",
                    ConnectionType.METADATA,
                    Tables.META_APP_USER.getValue(),
                    ConnectionType.METADATA,
                    Tables.META_APP_USER_ROLE.getValue(),
                    Columns.META_APP_USER_SK.getValue(),
                    Columns.META_APP_USER_SK.getValue(),
                    "USER_NAME");

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, userName.toUpperCase());

            resultSet = preparedStatement.executeQuery();

            //UserName is a unique key in MetaData

            boolean result = false;

            while (resultSet.next()) {

                result = resultSet.getString("USER_EXISTS").equals("TRUE") ? true : false;
            }

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

    public long deleteUserUserAppRolesForTenant(BigDecimal appUserSk, BigDecimal tenantSk) {
        Connection connection = null;

        CallableStatement callableStatement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = getOracleConnection();
            connection.setAutoCommit(false);


            String packageName = "PKG_METADATA_SECURITY";
            String methodName = "delete_app_user";

            String callMethod = String.format("{?=call %s.%s.%s(?)}", ConnectionType.METADATA, packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);

            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
            callableStatement.setObject(2, appUserSk);

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


    public List<ApplicationSecureGroup> getApplicationSecureGroups() {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            connection = getOracleConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM "
                    + ConnectionType.METADATA
                    + ".APP_SECR_GRP";

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            List<ApplicationSecureGroup> applicationSecureGroupList =
                    (List<ApplicationSecureGroup>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.ApplicationSecureGroup");

            return applicationSecureGroupList;

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
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }


    public long insertUpdateRole(TmpAppRoleTyp tmpAppRoleTyp) {
        Connection connection = null;
        CallableStatement callableStatement = null;


        try {

            connection = getOracleConnection();
            connection.setAutoCommit(false);

            String packageName = "PKG_METADATA_SECURITY";
            String methodName = "save_app_role";

            String callMethod = String.format("{?=call %s.%s.%s(?)}", ConnectionType.METADATA, packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);

            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
            callableStatement.registerOutParameter(2, OracleTypes.STRUCT, TmpAppRoleTyp._SQL_NAME);
            callableStatement.setObject(2, tmpAppRoleTyp);


            callableStatement.execute();

            Datum datum = (Datum) callableStatement.getObject(2);

            TmpAppRoleTyp tmpAppRoleTyp1 = new TmpAppRoleTyp();
            tmpAppRoleTyp1 = (TmpAppRoleTyp) tmpAppRoleTyp.create(datum, OracleTypes.STRUCT);


            long result = tmpAppRoleTyp1.getAppRoleSk().longValue();


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

    public long deleteRole(long appRoleSk) {
        Connection connection = null;
        CallableStatement callableStatement = null;


        try {

            connection = getOracleConnection();
            connection.setAutoCommit(false);

            String packageName = "PKG_METADATA_SECURITY";
            String methodName = "delete_app_role";

            String callMethod = String.format("{?=call %s.%s.%s(?)}", ConnectionType.METADATA, packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);

            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
            callableStatement.setBigDecimal(2, new BigDecimal(appRoleSk));

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

    public TmpAppRoleTyp getRole(BigInteger appRoleSk) {
        Connection connection = null;
        CallableStatement callableStatement = null;


        try {

            connection = getOracleConnection();
            connection.setAutoCommit(true);

            String packageName = "PKG_METADATA_SECURITY";
            String methodName = "get_app_role";

            String callMethod = String.format("{?=call %s.%s.%s(?,?)}", ConnectionType.METADATA, packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);

            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
            callableStatement.setObject(2, appRoleSk);
            callableStatement.registerOutParameter(3, OracleTypes.STRUCT, TmpAppRoleTyp._SQL_NAME);

            callableStatement.execute();

            long returnValue = callableStatement.getLong(1);

            Datum result = (Datum) callableStatement.getObject(3);

            TmpAppRoleTyp tmpAppRoleTyp = new TmpAppRoleTyp();
            tmpAppRoleTyp = (TmpAppRoleTyp) tmpAppRoleTyp.create(result, OracleTypes.STRUCT);

            return tmpAppRoleTyp;


        } catch (Exception e) {

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

    public TmpAppRoleTab getRoles(String businessEntityID) throws Exception {
        Connection connection = null;
        CallableStatement callableStatement = null;

        String tenantSk = getTenantSKByBE(businessEntityID);

        if(StringUtil.IsNullOrEmpty(tenantSk)){
            throw new SandataRuntimeException(String.format("Business Entity ID: %s does not exist", businessEntityID));
        }

        try {

            connection = getOracleConnection();
            connection.setAutoCommit(true);

            String packageName = "PKG_METADATA_SECURITY";
            String methodName = "get_app_role_list";

            String callMethod = String.format("{?=call %s.%s.%s(?,?)}", ConnectionType.METADATA, packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);

            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
            callableStatement.setObject(2, new BigInteger(tenantSk));
            callableStatement.registerOutParameter(3, OracleTypes.ARRAY, TmpAppRoleTab._SQL_NAME);

            callableStatement.execute();

            long returnValue = callableStatement.getLong(1);

            Datum result = (Datum) callableStatement.getObject(3);

            TmpAppRoleTab tmpAppRoleTab = new TmpAppRoleTab();
            tmpAppRoleTab = (TmpAppRoleTab) tmpAppRoleTab.create(result, OracleTypes.ARRAY);

            return tmpAppRoleTab;


        } catch (Exception e) {

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

    public Response findUser(String bsnEnt, String lastName, String firstName, String userName, String roleName, int page,
                            int pageSize, String sortColumn, String sortDirection) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {

            connection = getOracleConnection();
            connection.setAutoCommit(true);



            StringBuilder selectStatement = new StringBuilder();
            selectStatement.append(
                    format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                                    "  SELECT * FROM ( " +
                                    "SELECT DISTINCT * FROM %s.APP_USER T1 " +
                                    "JOIN %s.APP_USER_ROLE T2 ON T1.APP_USER_SK = T2.APP_USER_SK " +
                                    "JOIN %s.APP_ROLE T3 ON T2.APP_ROLE_SK = T3.APP_ROLE_SK " +
                                    "JOIN %s.APP_TENANT_BE_XWALK T4 ON T3.APP_TENANT_SK = T4.APP_TENANT_SK " +
                                    "WHERE T4.BE_ID = ? ",
                            ConnectionType.METADATA,
                            ConnectionType.METADATA,
                            ConnectionType.METADATA,
                            ConnectionType.METADATA));


            StringBuilder whereClause = new StringBuilder();

            List<String> params = new ArrayList<>();
            params.add(bsnEnt);

            if (!StringUtil.IsNullOrEmpty(lastName)) {

                whereClause.append(" AND UPPER(T1." + Columns.META_USER_LAST_NAME.getValue() + ") LIKE ?");
                params.add(lastName.toUpperCase() + '%');
            }

            if (!StringUtil.IsNullOrEmpty(firstName)) {

                whereClause.append(" AND UPPER(T1." + Columns.META_USER_FIRST_NAME.getValue() + ") LIKE ?");
                params.add(firstName.toUpperCase() + '%');
            }

            if (!StringUtil.IsNullOrEmpty(userName)) {

                whereClause.append(" AND UPPER(T1." + Columns.META_USER_NAME.getValue() + ") LIKE ?");
                params.add(userName.toUpperCase() + '%');
            }

            if (!StringUtil.IsNullOrEmpty(roleName)) {

                whereClause.append(" AND UPPER(T3." + Columns.META_ROLE_NAME.getValue() + ") LIKE ?");
                params.add(roleName.toUpperCase() + '%');
            }

            selectStatement.append(whereClause);
            selectStatement.append(format(" ORDER BY %s %s " +
                    " ) " +
                    ") R1) " +
                    " WHERE ROW_NUMBER BETWEEN ? AND ?", sortColumn, sortDirection));


            preparedStatement = connection.prepareStatement(selectStatement.toString());

            int i = 0;
            for (String para : params) {

                preparedStatement.setString(i + 1, params.get(i).toUpperCase());
                i++;
            }

            int toRow = page * pageSize;
            int fromRow = toRow - (pageSize - 1);

            preparedStatement.setInt(i + 1, fromRow);

            i++;
            preparedStatement.setInt(i + 1, toRow);

            resultSet = preparedStatement.executeQuery();

            Map<BigInteger, FindUserResult> applicationUsers = new LinkedHashMap<>();

            List<ApplicationUserRole> applicationUserRoles = new ArrayList<>();

            List<ApplicationRole> applicationRoles = new ArrayList<>();

            int totalRows = 0;

            while (resultSet.next()) {


                if (totalRows == 0) {
                    totalRows = resultSet.getBigDecimal("TOTAL_ROWS").intValue();
                }

                FindUserResult findUserResult = new FindUserResult();

                findUserResult.setApplicationUserSK(resultSet.getBigDecimal("APP_USER_SK").toBigInteger());
                findUserResult.setRecordUpdateTimestamp(resultSet.getTimestamp("REC_UPDATE_TMSTP"));
                findUserResult.setRecordCreateTimestamp(resultSet.getTimestamp("REC_CREATE_TMSTP"));
                findUserResult.setUserName(resultSet.getString("USER_NAME"));
                findUserResult.setUserGloballyUniqueIdentifier(resultSet.getString("USER_GUID"));
                findUserResult.setUserLastName(resultSet.getString("USER_LAST_NAME"));
                findUserResult.setUserMiddleName(resultSet.getString("USER_MIDDLE_NAME"));
                findUserResult.setUserFirstName(resultSet.getString("USER_FIRST_NAME"));
                applicationUsers.put(findUserResult.getApplicationUserSK(), findUserResult);

                //There can be multiple roles for a user
                //Store each one in a map
                ApplicationUserRole applicationUserRole = new ApplicationUserRole();
                applicationUserRole.setApplicationTenantSK(resultSet.getBigDecimal("APP_TENANT_SK").toBigInteger());
                applicationUserRole.setRecordCreateTimestamp(resultSet.getTimestamp("REC_CREATE_TMSTP"));
                applicationUserRole.setRecordUpdateTimestamp(resultSet.getTimestamp("REC_UPDATE_TMSTP"));
                applicationUserRole.setApplicationRoleSK(resultSet.getBigDecimal("APP_ROLE_SK").toBigInteger());
                applicationUserRole.setApplicationUserSK(resultSet.getBigDecimal("APP_USER_SK").toBigInteger());
                applicationUserRole.setApplicationUserRoleSK(resultSet.getBigDecimal("APP_USER_ROLE_SK").toBigInteger());
                applicationUserRoles.add(applicationUserRole);

                ApplicationRole applicationRole = new ApplicationRole();
                applicationRole.setApplicationRoleSK(resultSet.getBigDecimal("APP_ROLE_SK").toBigInteger());
                applicationRole.setApplicationTenantSK(resultSet.getBigDecimal("APP_TENANT_SK").toBigInteger());
                applicationRole.setRoleName(resultSet.getString("ROLE_NAME"));
                applicationRoles.add(applicationRole);
            }

            for (ApplicationUserRole applicationUserRole : applicationUserRoles) {
                for (FindUserResult applicationUser : applicationUsers.values()) {
                    if (applicationUserRole.getApplicationUserSK().equals(applicationUser.getApplicationUserSK())) {
                        applicationUser.setApplicationUserRoleSk(applicationUserRole.getApplicationUserRoleSK());
                        applicationUser.setApplictionTenantSk(applicationUserRole.getApplicationTenantSK());
                        applicationUser.setApplicationRoleSk(applicationUserRole.getApplicationRoleSK());
                    }
                }

            }

            for (ApplicationRole appRole : applicationRoles) {
                for (FindUserResult applicationUser : applicationUsers.values()) {
                    if (StringUtil.IsNullOrEmpty(applicationUser.getRoleName()) && appRole.getApplicationRoleSK().equals(applicationUser.getApplicationRoleSk())) {
                        applicationUser.setRoleName(appRole.getRoleName());
                    }
                }
            }

            Response response = new Response();

            response.setTotalRows(totalRows);
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setData(new ArrayList<FindUserResult>(applicationUsers.values()));

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
            connectionPoolDataService.close(connection);
        }
    }

    protected ConnectionPoolDataService connectionPoolDataService;

    public Connection getOracleConnection() throws SandataRuntimeException {
        return connectionPoolDataService.getConnection(ConnectionType.METADATA);
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

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.NUMBER_ARRAY", connection);
            ARRAY primaryKeysArray = new ARRAY(des, connection, params);

            String callMethod = String.format("{?=call %s.%s.%s(?)}", ConnectionType.METADATA, packageName, methodName);
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

            String callMethod = String.format("{?=call %s.%s.%s(?)}", ConnectionType.METADATA, packageName, methodName);
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

            connection = getOracleConnection();
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s.%s(?)}", ConnectionType.METADATA, packageName, methodName);
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

            connection = getOracleConnection();
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s.%s(?)}", ConnectionType.METADATA, packageName, methodName);
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

            String callMethod = String.format("{?=call %s.%s.%s(?)}", ConnectionType.METADATA, packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
            callableStatement.setObject(2, jpubType);
            callableStatement.execute();
            long result = (Long) callableStatement.getObject(1);
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
     * Returns List of ApplicationModule.  Any rows in the table
     * that have a parent are put under their parent.
     *
     * @return List of defined application modules.
     */
    public List<ApplicationModule> getApplicationModules() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = getOracleConnection();
            connection.setAutoCommit(false);

            // Order by parent SK descending to ensure any parents are added to map first.
            String sql = "SELECT * FROM "
                    + ConnectionType.METADATA
                    + ".APP_MOD ORDER BY APP_MOD_PAR_SK DESC";

            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            List<ApplicationModule> applicationModuleList =
                    (List<ApplicationModule>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.ApplicationModule");

            if (applicationModuleList == null
                    || applicationModuleList.isEmpty()) {
                throw new SandataRuntimeException("Failed to query APP_MOD table!");
            }

            Map<BigInteger, ApplicationModule> applicationModuleMap = new LinkedHashMap<>();
            for (ApplicationModule applicationModule : applicationModuleList) {
                // If a parent, put into map.
                if (applicationModule.getApplicationModuleParentSK() == null) {
                    applicationModuleMap.put(applicationModule.getApplicationModuleSK(), applicationModule);
                } else {
                    // A child, find parent and add to list.
                    applicationModuleMap.get(applicationModule.getApplicationModuleParentSK())
                            .getApplicationModule().add(applicationModule);
                }
            }

            return new ArrayList<>(applicationModuleMap.values());

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
     * Update ApplicationUserSetting by USER_GUID and KEY
     *
     * @param userSetting
     * @return
     * @throws SandataRuntimeException
     */
    public long insertUpdateUserSetting(ApplicationUserSetting userSetting, boolean bShouldInsert) throws SandataRuntimeException {

        if (bShouldInsert) {
            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(userSetting);
            Object jpubType = new DataMapper().map(userSetting);
            return execute(oracleMetadata.packageName(), oracleMetadata.insertMethod(), jpubType);

        } else {
            return updateUserSetting(userSetting);

        }

    }

    /**
     * Update ApplicationUserSetting value by guid and key
     *
     * @param userSetting
     * @return
     * @throws SandataRuntimeException
     */
    public long updateUserSetting(ApplicationUserSetting userSetting) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = getOracleConnection();
            connection.setAutoCommit(false);

            String callMethod = "{?=call PKG_APP_UTIL.UPDATE_USR_SETTING_BY_GUID_KEY(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);

            int index = 1;
            callableStatement.registerOutParameter(index++, OracleTypes.BIGINT);
            callableStatement.setString(index++, userSetting.getValue());
            callableStatement.setString(index++, userSetting.getUserGloballyUniqueIdentifier());
            callableStatement.setString(index++, userSetting.getKey());

            callableStatement.execute();
            long result = callableStatement.getLong(1);

            if (result < 0) {
                throw new SandataRuntimeException("Error occurred while deleting from APP_USER_SETTING table");
            }

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
     * Delete ApplicationUserSetting by USER_GUID and KEY
     *
     * @param userSetting
     * @return
     * @throws SandataRuntimeException
     */
    public long deleteUserSetting(ApplicationUserSetting userSetting) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = getOracleConnection();
            connection.setAutoCommit(false);

            String callMethod = "{?=call PKG_APP_UTIL.DELETE_USR_SETTING_BY_GUID(?)}";
            callableStatement = connection.prepareCall(callMethod);

            int index = 1;
            callableStatement.registerOutParameter(index++, OracleTypes.BIGINT);
            callableStatement.setString(index++, userSetting.getUserGloballyUniqueIdentifier());

            callableStatement.execute();
            long result = callableStatement.getLong(1);

            if (result < 0) {
                throw new SandataRuntimeException("Error occurred while deleting from APP_USER_SETTING table");
            }

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
     * Find ApplicationUserSetting by USER_GUID
     *
     * @param userGUID
     * @return List<ApplicationUserSetting>
     * @throws SandataRuntimeException
     */
    public List<ApplicationUserSetting> getUserSettingByUserGUID(final String userGUID) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sql = String.format("SELECT * FROM %s.%s WHERE %s = ?",
                    ConnectionType.METADATA,
                    Tables.META_APP_USER_SETTING.getValue(),
                    Columns.META_USER_GUID.getValue());

            connection = getOracleConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userGUID);

            resultSet = preparedStatement.executeQuery();

            List<ApplicationUserSetting> userSettingList =
                    (List<ApplicationUserSetting>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.ApplicationUserSetting");

            return userSettingList;

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
     * Find ApplicationUserSettings by USER_GUID And Key
     *
     * @param userGUID
     * @return List<ApplicationUserSetting>
     * @throws SandataRuntimeException
     */
    public List<ApplicationUserKeyConfiguration> getUserSettingsByUserGUIDAndKey(final String userGUID, final String keyName) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sql = String.format("SELECT * FROM %s.%s " +
                            " WHERE UPPER(%s) = ? " +
                            " START WITH UPPER(KEY_NAME) = ? " +
                            " CONNECT BY PRIOR APP_USER_KEY_CONF_SK = APP_USER_KEY_CONF_PAR_SK",
                    ConnectionType.METADATA,
                    Tables.META_USER_KEY_CONF.getValue(),
                    Columns.META_USER_GUID.getValue());

            connection = getOracleConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userGUID.toUpperCase());
            preparedStatement.setString(2, keyName.toUpperCase());

            resultSet = preparedStatement.executeQuery();

            return
                    (List<ApplicationUserKeyConfiguration>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.ApplicationUserKeyConfiguration");

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
     * Find ApplicationUserSetting by USER_GUID and KEY
     *
     * @param userGUID
     * @param key
     * @return List<ApplicationUserSetting>
     * @throws SandataRuntimeException
     */
    public List<ApplicationUserSetting> getUserSettingByUserGUIDAndKey(final String userGUID, final String key) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sql = String.format("SELECT * FROM %s.%s WHERE %s = ? AND UPPER(%s) = ?",
                    ConnectionType.METADATA,
                    Tables.META_APP_USER_SETTING.getValue(),
                    Columns.META_USER_GUID.getValue(),
                    Columns.META_KEY.getValue());

            connection = getOracleConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userGUID);
            preparedStatement.setString(2, key.toUpperCase());

            resultSet = preparedStatement.executeQuery();

            List<ApplicationUserSetting> userSettingList =
                    (List<ApplicationUserSetting>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.ApplicationUserSetting");

            return userSettingList;

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


    public ApplicationSystemKeyConfiguration getAppSysConfigByKey(final String keyName) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sql = String.format("SELECT * FROM %s.%s " +
                            " WHERE UPPER(KEY_NAME) = ? ",
                    ConnectionType.METADATA,
                    Tables.META_SYS_KEY_CONF.getValue());

            connection = getOracleConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, keyName.toUpperCase());

            resultSet = preparedStatement.executeQuery();

            List<ApplicationSystemKeyConfiguration> applicationSystemKeyConfigurations =
                    (List<ApplicationSystemKeyConfiguration>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.ApplicationSystemKeyConfiguration");

            if (applicationSystemKeyConfigurations != null && applicationSystemKeyConfigurations.size() > 0) {

                return applicationSystemKeyConfigurations.get(0);
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
}
