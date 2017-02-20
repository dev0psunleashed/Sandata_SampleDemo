/**
 * 
 */
package com.sandata.services.mobilehealth.processors;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.dl.model.MedicalExaminationItemCrosswalk;
import com.sandata.lab.data.model.dl.model.StaffMedicalHistory;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;

/**
 * @author huyvo
 *
 */
public class OracleDataService implements OracleService {
    private ConnectionPoolDataService connectionPoolDataService;
    
    /**
     * @return the connectionPoolDataService
     */
    public ConnectionPoolDataService getConnectionPoolDataService() {
        return connectionPoolDataService;
    }

    /**
     * @param connectionPoolDataService the connectionPoolDataService to set
     */
    public void setConnectionPoolDataService(ConnectionPoolDataService connectionPoolDataService) {
        this.connectionPoolDataService = connectionPoolDataService;
    }

    /**
     * default DataSource is CoreData
     * @return
     * @throws SandataRuntimeException
     */
    public Connection getOracleConnection() throws SandataRuntimeException {
        return connectionPoolDataService.getConnection();
    }
    
    public Connection getOracleConnection(ConnectionType connectionType) {
        return connectionPoolDataService.getConnection(connectionType);
    }

    @Override
    public Object executeGet(ConnectionType connectionType, String packageName, String methodName, String className, Object... params)
        throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            connection = getOracleConnection(connectionType);
            connection.setAutoCommit(false);

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.STRING_ARRAY", connection);
            ARRAY primaryKeysArray = new ARRAY(des, connection, params);

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
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

    @Override
    public Object executeGet(ConnectionType connectionType, String packageName, String methodName, String className, int sequenceKey) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            connection = getOracleConnection(connectionType);
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setInt(2, sequenceKey);
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

    @Override
    public Object executeGet(ConnectionType connectionType, String packageName, String methodName, String className, String entityId) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            connection = getOracleConnection(connectionType);
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
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

    @Override
    public int execute(ConnectionType connectionType, String packageName, String methodName, int sequenceKey) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = getOracleConnection(connectionType);
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setInt(2, sequenceKey);
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

            throw new SandataRuntimeException(String.format("[%s][%s]: %s: %s",
                packageName, methodName,
                e.getClass().getName(), e.getMessage()));

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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    @Override
    public int execute(ConnectionType connectionType, String packageName, String methodName, Object jpubType) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = getOracleConnection(connectionType);
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
                e.getClass().getName(), e.getMessage()));

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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    @Override
    public int execute(Connection connection, String packageName, String methodName, Object jpubType) throws SandataRuntimeException {

        CallableStatement callableStatement = null;

        try {

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setObject(2, jpubType);
            callableStatement.execute();
            int result = (Integer) callableStatement.getObject(1);
            return result;
        } catch (Exception e) {

            // NOTE: Rollback (if necessary) should be handled by caller since they passed in the connection...
            throw new SandataRuntimeException(String.format("[%s][%s]: %s: %s",
                packageName, methodName,
                e.getClass().getName(), e.getMessage()));

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
    
    public Object getEntitiesForId(ConnectionType connectionType, final String sql, final String className, final Object... params) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = getOracleConnection(connectionType);
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
    
    /**
     * get the staff id in George associate with SSN (called STAFF_TIN in GEORGE)
     * @param ssn
     * @return
     */
    public String getStaffIdFromSSN(String ssn, String beId) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = getOracleConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT STAFF_ID FROM STAFF WHERE STAFF_TIN = ? AND BE_ID = ?";
            
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ssn);
            preparedStatement.setString(2, beId);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("STAFF_ID");
            }

            connection.commit();

            return null;
        } catch (Exception e) {
            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    // Ignore
                }
            }
            throw new SandataRuntimeException(String.format("%s: %s", e.getClass().getName(), e.getMessage()), e);
        } finally {
            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    // Ignore
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    // Ignore
                }
            }

            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    // Ignore
                }
            }
        }
    }
    
    /**
     * mapping BusinessEntity ID from BE_ID_XWALK table
     * @param vendorNumber
     * @return
     */
    public String getBeIdFromVendorNumber(String vendorNumber, String vendorType, String vendorName) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String beId = null;

        try {

            connection = getOracleConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT BE_ID FROM BE_ID_XWALK WHERE BE_ID_NUM = ? AND BE_ID_TYP = ? AND BE_ID_QLFR = ?";
            
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, vendorNumber);
            preparedStatement.setString(2, vendorType);
            preparedStatement.setString(3, vendorName);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                beId = resultSet.getString("BE_ID");
            }

            connection.commit();

            return beId;
        } catch (Exception e) {
            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    // Ignore
                }
            }
            throw new SandataRuntimeException(String.format("%s: %s", e.getClass().getName(), e.getMessage()), e);
        } finally {
            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    // Ignore
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    // Ignore
                }
            }

            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    // Ignore
                }
            }
        }
    }
    
    /**
     * mapping BusinessEntity ID from BE_ID_XWALK table
     * @param vendorNumber
     * @return
     */
    public String getVendorNumberFromBeId(String BeId, String vendorType, String vendorName) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String beId = null;

        try {

            connection = getOracleConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT BE_ID_NUM FROM BE_ID_XWALK WHERE BE_ID = ? AND BE_ID_TYP = ? AND BE_ID_QLFR = ?";
            
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, BeId);
            preparedStatement.setString(2, vendorType);
            preparedStatement.setString(3, vendorName);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                beId =  resultSet.getString("BE_ID_NUM");
            }

            connection.commit();

            return beId;
        } catch (Exception e) {
            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    // Ignore
                }
            }
            throw new SandataRuntimeException(String.format("%s: %s", e.getClass().getName(), e.getMessage()), e);
        } finally {
            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    // Ignore
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    // Ignore
                }
            }

            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    // Ignore
                }
            }
        }
    }
    
    /**
     * get Valid StaffMedicalHistory from STAFF_MEDCL_HIST table corresponding to BusinessEntity ID and Staff ID
     * @param staff
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<StaffMedicalHistory> getValidStaffMedicalHistory(String beId, String staffId) {
        List<StaffMedicalHistory> staffMedicalHistoryResults = null;
        
        StringBuilder sqlGetStaffMedclHis = new StringBuilder();
        sqlGetStaffMedclHis.append("SELECT * FROM STAFF_MEDCL_HIST ");
        sqlGetStaffMedclHis.append("WHERE BE_ID = ? AND STAFF_ID = ? ");
        sqlGetStaffMedclHis.append(    "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)");
        
        staffMedicalHistoryResults = (List<StaffMedicalHistory>) getEntitiesForId(
                ConnectionType.COREDATA,
                sqlGetStaffMedclHis.toString(), 
                "com.sandata.lab.data.model.dl.model.StaffMedicalHistory",
                beId,
                staffId);
        
        return staffMedicalHistoryResults;
    }
    
    /**
     * Return list of MedicalExamItemCosswalk by Business Entity ID
     * @param beId
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<MedicalExaminationItemCrosswalk> getMedicalExamItemCrossWalks(String beId) {
        StringBuilder sqlGetMedclExamXwalk = new StringBuilder();
        sqlGetMedclExamXwalk.append("SELECT * FROM MEDCL_EXAM_ITEM_XWALK ");
        sqlGetMedclExamXwalk.append("WHERE BE_ID = ? ");
        sqlGetMedclExamXwalk.append(    "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)");
        
        return (List<MedicalExaminationItemCrosswalk>) getEntitiesForId(
                ConnectionType.COREDATA,
                sqlGetMedclExamXwalk.toString(), 
                "com.sandata.lab.data.model.dl.model.MedicalExaminationItemCrosswalk",
                beId);
    }
}
