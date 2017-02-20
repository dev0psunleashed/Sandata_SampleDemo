package com.sandata.lab.security.auth.services.oracle;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.dl.model.AdministrativeStaff;
import com.sandata.lab.data.model.dl.model.AdministrativeStaffRoleCrossReference;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.security.auth.api.OracleService;
import com.sandata.lab.security.auth.utils.cache.CacheUtil;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import java.sql.*;
import java.util.List;

public class CoreDataService implements OracleService {

    private CacheUtil cacheUtil;

    public void setCacheUtil(CacheUtil cacheUtil) {
        this.cacheUtil = cacheUtil;
    }

    protected ConnectionPoolDataService connectionPoolDataService;

    public Connection getOracleConnection() throws SandataRuntimeException {
        return connectionPoolDataService.getConnection(ConnectionType.COREDATA);
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

            connection = getOracleConnection();
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

            connection = getOracleConnection();
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

    public AdministrativeStaff getAdminStaffByAdminStaffID(final String businessEntityID, final String adminStaffID) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sql = String.format("SELECT * FROM %s.%s T1 " +
                            " WHERE UPPER(T1.ADMIN_STAFF_ID) = ? " +
                            " AND T1.BE_ID = ? AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1)",
                    ConnectionType.COREDATA,
                    "ADMIN_STAFF");

            connection = getOracleConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, adminStaffID.toUpperCase());
            preparedStatement.setString(2, businessEntityID);

            resultSet = preparedStatement.executeQuery();

            List<AdministrativeStaff> administrativeStaffs =
                    (List<AdministrativeStaff>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.AdministrativeStaff");

            if (administrativeStaffs != null && administrativeStaffs.size() > 0) {

                return administrativeStaffs.get(0);
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

    public List<AdministrativeStaffRoleCrossReference> getAdminStaffRoleByAdminStaffID(final String businessEntityID, final String adminStaffID) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sql = String.format("SELECT * FROM %s.%s T1 " +
                            " WHERE UPPER(T1.ADMIN_STAFF_ID) = ? " +
                            " AND T1.BE_ID = ? AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1)",
                    ConnectionType.COREDATA,
                    "ADMIN_STAFF_ROLE_XREF");

            connection = getOracleConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, adminStaffID.toUpperCase());
            preparedStatement.setString(2, businessEntityID);

            resultSet = preparedStatement.executeQuery();

            List<AdministrativeStaffRoleCrossReference> administrativeStaffRoleCrossReferences =
                    (List<AdministrativeStaffRoleCrossReference>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.AdministrativeStaffRoleCrossReference");

            return administrativeStaffRoleCrossReferences;

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

    public long deleteAdminStaffAdminStaffID(final String businessEntityID, final String adminStaffID) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = getOracleConnection();
            connection.setAutoCommit(false);

            String callMethod = "{?=call PKG_ADMIN_UTIL.DEL_ADMIN_STAFF_BY_AD_STAFF_ID(?,?)}";
            callableStatement = connection.prepareCall(callMethod);

            int index = 1;
            callableStatement.registerOutParameter(index++, OracleTypes.BIGINT);
            callableStatement.setString(index++, businessEntityID);
            callableStatement.setString(index++, adminStaffID.toUpperCase());

            callableStatement.execute();
            long result = callableStatement.getLong(1);

            if (result < 0) {
                throw new SandataRuntimeException("Error occurred while deleting from ADMIN_STAFF table");
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

    public long deleteAdminStaffRelAdminStaffID(final String businessEntityID, final String adminStaffID) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = getOracleConnection();
            connection.setAutoCommit(false);

            String callMethod = "{?=call PKG_ADMIN_UTIL.DEL_ADMIN_STAFF_REL_BY_AD_S_ID(?,?)}";
            callableStatement = connection.prepareCall(callMethod);

            int index = 1;
            callableStatement.registerOutParameter(index++, OracleTypes.BIGINT);
            callableStatement.setString(index++, businessEntityID);
            callableStatement.setString(index++, adminStaffID.toUpperCase());

            callableStatement.execute();
            long result = callableStatement.getLong(1);

            if (result < 0) {
                throw new SandataRuntimeException("Error occurred while deleting from ADMIN_STAFF_REL table");
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

    public long deleteAdminStaffRoleAdminStaffID(final String businessEntityID, final String adminStaffID) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = getOracleConnection();
            connection.setAutoCommit(false);

            String callMethod = "{?=call PKG_ADMIN_UTIL.DEL_ADMIN_STAFF_ROLE_BY_ID(?,?)}";
            callableStatement = connection.prepareCall(callMethod);

            int index = 1;
            callableStatement.registerOutParameter(index++, OracleTypes.BIGINT);
            callableStatement.setString(index++, businessEntityID);
            callableStatement.setString(index++, adminStaffID.toUpperCase());

            callableStatement.execute();
            long result = callableStatement.getLong(1);

            if (result < 0) {
                throw new SandataRuntimeException("Error occurred while deleting from ADMIN_STAFF_REL table");
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
}
