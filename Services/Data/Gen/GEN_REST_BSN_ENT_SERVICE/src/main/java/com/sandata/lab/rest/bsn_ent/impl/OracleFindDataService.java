package com.sandata.lab.rest.bsn_ent.impl;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.model.StateCode;
import com.sandata.lab.rest.bsn_ent.api.OracleService;
import org.apache.camel.PropertyInject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OracleFindDataService extends OracleDataService implements OracleService {

    @PropertyInject("{{be.xwalk.payroll.qualifier}}")
    private String beXWalkTypName = "Payroll";

    public HashMap<String, String> getBsnEntIDForPayCreatingOrg(String externalID, String vendor) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT * FROM COREDATA.BE_ID_XWALK T1" +
                    " JOIN COREDATA.BE T2 ON T1.BE_ID = T2.BE_ID " +
                    " JOIN COREDATA.BE_INTF T3 ON T2.BE_ID = T3.BE_ID" +
                    " JOIN COREDATA.INTF_LKUP T4 " +
                    " ON T3.INTF_ID = T4.INTF_ID AND UPPER(T1.BE_ID_CREATING_ORG) = UPPER(T4.INTF_OWNER_VENDOR_NAME)" +
                    " WHERE UPPER(T1.BE_ID_CREATING_ORG) = ? " +
                    " AND T1.BE_ID_NUM =? AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') ");

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, vendor.toUpperCase());
            preparedStatement.setString(2, externalID);

            resultSet = preparedStatement.executeQuery();

            HashMap<String, String> result = new HashMap<>();

            while (resultSet.next()) {

                result.put("bsnEntID", resultSet.getString("BE_ID"));
                result.put("INTFID", resultSet.getString("INTF_ID"));
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

    public HashMap<String, Map> getBsnEntIDsForPayroll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT * FROM BE_ID_XWALK T1" +
                    " JOIN BE T2 ON T1.BE_ID = T2.BE_ID AND (T2.CURR_REC_IND = 1 AND TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') " +
                    " JOIN COREDATA.BE_INTF T3 ON T2.BE_ID = T3.BE_ID AND (T3.CURR_REC_IND = 1 AND TO_CHAR(T3.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') " +
                    " JOIN COREDATA.INTF_LKUP T4 " +
                    " ON T3.INTF_ID = T4.INTF_ID AND UPPER(T1.BE_ID_CREATING_ORG) = UPPER(T4.INTF_OWNER_VENDOR_NAME) " +
                    " AND (T4.CURR_REC_IND = 1 AND TO_CHAR(T4.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') " +
                    " AND UPPER(T1.BE_ID_CREATING_ORG) = UPPER(T4.INTF_OWNER_VENDOR_NAME)" +
                    " WHERE UPPER(T1.BE_ID_TYP) = ? AND UPPER(T1.BE_ID_QLFR) = ? " +
                    " AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') ");

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, beXWalkTypName.toUpperCase());
            preparedStatement.setString(2, "COMPANY");


            resultSet = preparedStatement.executeQuery();

            String externalID = null;
            String bsnEntID = null;
            String vendorIntfName = null;


            while (resultSet.next()) {

                bsnEntID = resultSet.getString("BE_ID");
                externalID = resultSet.getString("BE_ID_NUM");
                vendorIntfName = resultSet.getString("INTF_OWNER_VENDOR_NAME");
            }

            resultSet.close();
            preparedStatement.close();
            this.connectionPoolDataService.close(connection);

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            sql = "SELECT DISTINCT * FROM BE_ID_XWALK T1 " +
                    "JOIN BE T3 on T1.BE_ID = T3.BE_ID AND T3.CURR_REC_IND = 1 AND TO_CHAR(T3.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' " +
                    "JOIN (Select * FROM BE_REL T2 " +
                    "WHERE T2.CURR_REC_IND = 1 AND TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' " +
                    "START WITH T2.BE_PAR_ID = ? " +
                    "CONNECT BY NOCYCLE PRIOR T2.BE_ID = T2.BE_PAR_ID) T3 ON T1.BE_ID = T3.BE_ID " +
                    "WHERE UPPER(T1.BE_ID_TYP) = ? AND UPPER(T1.BE_ID_QLFR) = ?  ";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, bsnEntID);
            preparedStatement.setString(2, beXWalkTypName.toUpperCase());
            preparedStatement.setString(3, "LOCATION");

            resultSet = preparedStatement.executeQuery();

            HashMap<String, Map> bsnMap = new HashMap<>();

            while (resultSet.next()) {

                String bsnEnt = resultSet.getString("BE_ID");

                HashMap<String, String> result = new HashMap<>();
                result.put("bsnEntID", bsnEnt);
                result.put("vendorParentBEID", externalID);
                result.put("vendorBEID", resultSet.getString("BE_ID_NUM"));
                result.put("prVendorName", vendorIntfName);
                result.put("bsnEntState", resultSet.getString("BE_PRMY_STATE"));

                result.put("timeZone", resultSet.getString("TZ_NAME"));

                bsnMap.put(bsnEnt, result);
            }

            return bsnMap;

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

    public HashMap<String, String> getBsnEntIDForPayrollByBusinessEntityID(String beID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT DISTINCT * FROM BE_ID_XWALK T1 " +
                    " JOIN BE T3 on T1.BE_ID = T3.BE_ID AND T3.CURR_REC_IND = 1 AND TO_CHAR(T3.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' " +
                    " AND T1.BE_ID = ? " +
                    " WHERE UPPER(T1.BE_ID_TYP) = ? AND UPPER(T1.BE_ID_QLFR) = ?  ";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, beID);
            preparedStatement.setString(2, beXWalkTypName.toUpperCase());
            preparedStatement.setString(3, "LOCATION");

            resultSet = preparedStatement.executeQuery();

            HashMap<String, String> bsnMap = new HashMap<>();

            while (resultSet.next()) {

                String bsnEnt = resultSet.getString("BE_ID");


                bsnMap.put("bsnEntID", bsnEnt);
                bsnMap.put("vendorBEID", resultSet.getString("BE_ID_NUM"));
                bsnMap.put("bsnEntState", resultSet.getString("BE_PRMY_STATE"));

                bsnMap.put("timeZone", resultSet.getString("TZ_NAME"));
            }

            resultSet.close();
            preparedStatement.close();
            this.connectionPoolDataService.close(connection);

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            sql = "SELECT DISTINCT * " +
                    "FROM BE_ID_XWALK T1 " +
                    "JOIN " +
                    "  (SELECT *" +
                    "  FROM BE_REL J1" +
                    "  WHERE J1.CURR_REC_IND                        = 1" +
                    "  AND TO_CHAR(J1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'" +
                    "    START WITH J1.BE_ID                        = ? " +
                    "    CONNECT BY NOCYCLE PRIOR J1.BE_PAR_ID      = J1.BE_ID" +
                    "  ) T2" +
                    " ON T1.BE_ID = T2.BE_ID" +
                    " JOIN COREDATA.BE_INTF T3" +
                    " ON T2.BE_ID                                  = T3.BE_ID" +
                    " AND (T3.CURR_REC_IND                         = 1" +
                    " AND TO_CHAR(T3.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31')" +
                    " JOIN COREDATA.INTF_LKUP T4" +
                    " ON T3.INTF_ID                                = T4.INTF_ID" +
                    " AND UPPER(T1.BE_ID_CREATING_ORG)             = UPPER(T4.INTF_OWNER_VENDOR_NAME)" +
                    " AND (T4.CURR_REC_IND                         = 1" +
                    " AND TO_CHAR(T4.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31')" +
                    " WHERE UPPER(T1.BE_ID_TYP)                    = ? AND UPPER(T1.BE_ID_QLFR) = ?  ";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, beID);
            preparedStatement.setString(2, beXWalkTypName.toUpperCase());
            preparedStatement.setString(3, "COMPANY");


            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String bsnEnt = resultSet.getString("BE_ID");

                bsnMap.put("vendorParentBEID", resultSet.getString("BE_ID_NUM"));
                bsnMap.put("prVendorName", resultSet.getString("INTF_OWNER_VENDOR_NAME"));
            }

            return bsnMap;

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

    public HashMap<String, Map> getBsnEntIDsForPayrollByVendor(String vendorName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT * FROM BE_ID_XWALK T1" +
                    " JOIN BE T2 ON T1.BE_ID = T2.BE_ID AND (T2.CURR_REC_IND = 1 AND TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') " +
                    " " +
                    " JOIN COREDATA.BE_INTF T3 ON T2.BE_ID = T3.BE_ID AND (T3.CURR_REC_IND = 1 AND TO_CHAR(T3.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') " +
                    " " +
                    " JOIN COREDATA.INTF_LKUP T4 " +
                    " ON T3.INTF_ID = T4.INTF_ID AND UPPER(T1.BE_ID_CREATING_ORG) = UPPER(T4.INTF_OWNER_VENDOR_NAME) " +
                    " AND (T4.CURR_REC_IND = 1 AND TO_CHAR(T4.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') " +
                    " AND UPPER(T1.BE_ID_CREATING_ORG) = UPPER(T4.INTF_OWNER_VENDOR_NAME)" +
                    " " +
                    " WHERE UPPER(T1.BE_ID_TYP) = ? AND UPPER(T1.BE_ID_CREATING_ORG) = ? " +
                    " AND UPPER(T1.BE_ID_QLFR) = ? " +
                    " AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') ");

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, beXWalkTypName.toUpperCase());
            preparedStatement.setString(2, vendorName.toUpperCase());
            preparedStatement.setString(3, "COMPANY");

            resultSet = preparedStatement.executeQuery();

            String externalID = null;
            String bsnEntID = null;
            String vendorIntfName = null;


            while (resultSet.next()) {

                bsnEntID = resultSet.getString("BE_ID");
                externalID = resultSet.getString("BE_ID_NUM");
                vendorIntfName = resultSet.getString("INTF_OWNER_VENDOR_NAME");
            }

            resultSet.close();
            preparedStatement.close();
            this.connectionPoolDataService.close(connection);

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            sql = " SELECT DISTINCT * FROM BE_ID_XWALK T1 " +
                    " JOIN BE T3 on T1.BE_ID = T3.BE_ID AND T3.CURR_REC_IND = 1 AND TO_CHAR(T3.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' " +
                    " JOIN (Select * FROM BE_REL T2 " +
                    " WHERE T2.CURR_REC_IND = 1 AND TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' " +
                    " START WITH T2.BE_ID = ? " +
                    " CONNECT BY NOCYCLE PRIOR T2.BE_ID = T2.BE_PAR_ID) T3 ON T1.BE_ID = T3.BE_ID " +
                    " WHERE UPPER(T1.BE_ID_TYP) = ? AND UPPER(T1.BE_ID_QLFR) = ? " +
                    " AND  TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' ";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, bsnEntID);
            preparedStatement.setString(2, beXWalkTypName.toUpperCase());
            preparedStatement.setString(3, "LOCATION");

            resultSet = preparedStatement.executeQuery();

            HashMap<String, Map> bsnMap = new HashMap<>();

            while (resultSet.next()) {

                String bsnEnt = resultSet.getString("BE_ID");

                HashMap<String, String> result = new HashMap<>();
                result.put("bsnEntID", bsnEnt);
                result.put("vendorParentBEID", externalID);
                result.put("vendorBEID", resultSet.getString("BE_ID_NUM"));
                result.put("prVendorName", vendorIntfName);
                result.put("bsnEntState", resultSet.getString("BE_PRMY_STATE"));

                result.put("timeZone", resultSet.getString("TZ_NAME"));

                bsnMap.put(bsnEnt, result);
            }

            return bsnMap;

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

    public ArrayList<String> getLocationsBusinessEntityIDsForBusinessEntity(String businessEntityID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("" +
                    "   SELECT * FROM COREDATA.BE_REL " +
                    "   WHERE BE_PAR_ID = ? AND UPPER(BE_REL_TYP) = ? " +
                    "   AND CURR_REC_IND = 1" +
                    "   (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') ");

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, businessEntityID);
            preparedStatement.setString(2, "LOCATION");

            resultSet = preparedStatement.executeQuery();

            ArrayList<String> arrayList = new ArrayList<>();

            while (resultSet.next()) {

                arrayList.add(resultSet.getString("BE_ID"));
            }

            if (arrayList.size() > 0) {
                return arrayList;
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
/*
    public HashMap<String, Map> getBsnEntIDsForPayrollByVendor(String vendorName)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT * FROM BE_ID_XWALK T1" +
                    " JOIN BE T2 ON T1.BE_ID = T2.BE_ID " +
                    " JOIN COREDATA.BE_INTF T3 ON T2.BE_ID = T3.BE_ID" +
                    " JOIN COREDATA.INTF_LKUP T4 " +
                    " ON T3.INTF_ID = T4.INTF_ID AND UPPER(T1.BE_ID_CREATING_ORG) = UPPER(T4.INTF_OWNER_VENDOR_NAME)" +
                    " WHERE UPPER(T1.BE_ID_QLFR) = ? AND UPPER(T1.BE_ID_CREATING_ORG) = ?" +
                    " AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') ");

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, beXWalkTypName.toUpperCase());
            preparedStatement.setString(2, vendorName.toUpperCase());

            resultSet = preparedStatement.executeQuery();



            HashMap<String, Map> bsnMap = new HashMap<>();

            while(resultSet.next()){

                String bsnEnt = resultSet.getString("BE_ID");

                HashMap<String ,String> result = new HashMap<>();
                result.put("bsnEntID", bsnEnt);
                result.put("vendorBEID", resultSet.getString("BE_ID_NUM"));
                result.put("prVendorName", resultSet.getString("INTF_OWNER_VENDOR_NAME"));
                result.put("timeZone", resultSet.getString("TZ_NAME"));

                bsnMap.put(bsnEnt, result);
            }

            return bsnMap;

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
                    e.getClass().getName(), e.getMessage()));

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
    }*/

}
