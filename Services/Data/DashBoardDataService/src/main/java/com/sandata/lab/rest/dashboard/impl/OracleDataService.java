package com.sandata.lab.rest.dashboard.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.dashboard.api.OracleService;
import com.sandata.lab.rest.dashboard.model.*;
import com.sandata.lab.rest.dashboard.model.enums.Columns;
import com.sandata.lab.rest.dashboard.model.enums.Tables;
import com.sandata.lab.rest.dashboard.utils.DataMapper;

public class OracleDataService implements OracleService {

    private ConnectionPoolDataService connectionPoolDataService;

    public Connection getOracleConnection() throws SandataRuntimeException {
        return connectionPoolDataService.getConnection(ConnectionType.COREDATAMART);
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

    private String getLocationsQuery(String fromDate, String toDate, String sumColumn, String tableName, int currentLvl, String dateColumn) {

        String businessEntityIDColumnName = getBusinessEntityIDColumnName(currentLvl);

        boolean sum = false;

        if (isSumQuery(tableName)) {
            sum = true;
        }

        StringBuilder stringBuilder = new StringBuilder();

        String operation = null;

        if (sum) {
            operation = "SUM ";
        } else {
            operation = "COUNT ";
        }


        String sumQuery = null;

     /*   if(currentLvl < 5){

            sumQuery = String.format(
                    " (SELECT %s( CASE WHEN %s IS NOT NULL THEN %s END)" +
                       " FROM %s J1 " +
                       " WHERE J1.%s BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(?, 'YYYY-MM-DD') + 1 " +
                       " START WITH J1.%s = T1.%s " +
                       " CONNECT BY J1.%s = PRIOR J1.%s) TOTAL", operation, sumColumn, sumColumn, tableName, dateColumn,
                            businessEntityIDColumnName, businessEntityIDColumnName, businessEntityIDColumnName, getBusinessEntityIDColumnName(currentLvl + 1));
        }else*/
        {

            sumQuery = String.format(
                    " %s ( CASE WHEN %s IS NOT NULL THEN %s  END)" +
                            " AS TOTAL", operation, sumColumn, sumColumn);

        }

        stringBuilder.append(String.format(
                "SELECT DISTINCT" +
                        "  T1.BE_ID," +
                        "  T1.BE_NAME," +
                        "  T1.BE_LVL_2_ID," +
                        "  T1.BE_LVL_2_NAME," +
                        "  T1.BE_LVL," +
                        "  T1.BE_LVL_3_ID," +
                        "  T1.BE_LVL_3_NAME," +
                        "  T1.BE_LVL_4_ID," +
                        "  T1.BE_LVL_4_NAME," +
                        "  T1.BE_LVL_5_ID," +
                        "  T1.BE_LVL_5_NAME," +
                        "%s" +
                        " from %s T1 ", sumQuery, tableName));


        stringBuilder.append(String.format("" +
                        " WHERE T1.%s = ? and T1.BE_LVL >= ? " +
                        " AND T1.%s BETWEEN  TO_DATE(?, 'YYYY-MM-DD HH24:MI') and TO_DATE(?, 'YYYY-MM-DD HH24:MI')" +
                        " GROUP BY" +
                        "  T1.BE_ID," +
                        "  T1.BE_NAME," +
                        "  T1.BE_LVL_2_ID," +
                        "  T1.BE_LVL_2_NAME," +
                        "  T1.BE_LVL," +
                        "  T1.BE_LVL_3_ID," +
                        "  T1.BE_LVL_3_NAME," +
                        "  T1.BE_LVL_4_ID," +
                        "  T1.BE_LVL_4_NAME," +
                        "  T1.BE_LVL_5_ID," +
                        "  T1.BE_LVL_5_NAME "
                , businessEntityIDColumnName, dateColumn));

        return stringBuilder.toString();
    }

    private int getBusinessEntityTotal(String businessEntityID, int currentLvl, String fromDate, String toDate,
                                       String tableName, String sumColumn, String dateColumn) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = getOracleConnection();
            connection.setAutoCommit(true);

            String businessEntityIDColumnName = "BE_ID";

            if (currentLvl > 1) {
                businessEntityIDColumnName = "BE_LVL_" + (currentLvl) + "_ID";
            }

            StringBuilder sql = new StringBuilder();

            boolean sum = false;
            if (isSumQuery(tableName)) {
                sum = true;
            }
            if (sum) {
                sql.append(String.format("SELECT SUM(%s) AS \"Total\" ", sumColumn));
            } else {
                sql.append(String.format("SELECT COUNT(*) AS \"Total\" ", sumColumn));
            }


            sql.append(String.format("FROM %s T1 ", tableName));
            sql.append(String.format("WHERE T1.%s = ? and  T1.BE_LVL >= ? " +
                    " AND T1.%s BETWEEN  TO_DATE('%s', 'YYYY-MM-DD HH24:MI') and TO_DATE('%s', 'YYYY-MM-DD HH24:MI') " +
                    " GROUP BY T1.%s", businessEntityIDColumnName, dateColumn, fromDate, toDate, businessEntityIDColumnName));

            preparedStatement = connection.prepareStatement(sql.toString());

            preparedStatement.setString(1, businessEntityID);
            preparedStatement.setInt(2, currentLvl);

            resultSet = preparedStatement.executeQuery();

            int result = 0;

            while (resultSet.next()) {
                result = resultSet.getInt("Total");
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

    public LocationResponse getDashboardLocationData(String businessEntityID, int currentLvl, String fromDate, String toDate, String tableName, String sumColumn, String dateColumn) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = getOracleConnection();

            String sql = getLocationsQuery(fromDate, toDate, sumColumn, tableName, currentLvl, dateColumn);

            preparedStatement = connection.prepareStatement(sql);

            int i = 1;

            /*
            //Subquery varies based on level
            if(currentLvl < 5) {
                preparedStatement.setString(i++, fromDate);
                preparedStatement.setString(i++, toDate);
            }
*/
            preparedStatement.setString(i++, businessEntityID);
            preparedStatement.setInt(i++, currentLvl);
            preparedStatement.setString(i++, fromDate);
            preparedStatement.setString(i++, toDate);

            resultSet = preparedStatement.executeQuery();

            List<Location> locationList = (List<Location>) new DataMapper().map(resultSet, tableName);


            LocationResponse locationResponse = new LocationResponse();
            locationResponse.setBusinessEntityId(businessEntityID);
            locationResponse.setBusinessEntityLevel(currentLvl);
            locationResponse.setLocations(locationList);

            int total = getBusinessEntityTotal(businessEntityID, currentLvl, fromDate, toDate, tableName, sumColumn, dateColumn);
            locationResponse.setValue(total);

            return locationResponse;

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

    public Object getDashboardDetailsData(String businessEntityID, int currentLvl, String fromDate, String toDate, String tableName, String sumColumn, String dateColumn, Class response) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = getOracleConnection();
            connection.setAutoCommit(true);

            String businessEntityIDColumnName = getBusinessEntityIDColumnName(currentLvl);

            String sql = String.format("Select * from %s where %s = ? and" +
                    " BE_lvl = ? and %s BETWEEN  TO_DATE('%s', 'YYYY-MM-DD') " +
                    "and TO_DATE('%s', 'YYYY-MM-DD')", tableName, businessEntityIDColumnName, dateColumn, fromDate, toDate);

            preparedStatement = connection.prepareStatement(sql);


            preparedStatement.setString(1, businessEntityID);
            preparedStatement.setInt(2, currentLvl);

            resultSet = preparedStatement.executeQuery();

            Object locationList = (Object) new DataMapper().map(resultSet, tableName, sumColumn, response, isSumQuery(tableName));


            return locationList;

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
     * @param bsnEntLvl
     * @param businessEntityId
     * @param fromDate
     * @param toDate
     * @param operationName
     * @param pending
     * @return
     */
    public LocationResponse getTotalOrPendingValues(int bsnEntLvl, final String businessEntityId, final String fromDate, final String toDate,
                                                    final String operationName, final boolean pending) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sqlDetails = null;
        String valColName = getValColName(operationName);
        String bsnEntColIdName = getBsnEntIdColName(bsnEntLvl);
        String bsnEntColNameName = bsnEntColIdName.replace("ID", "NAME");
        Tables table = getPRTable(operationName, pending);
        Columns dateColumn = getPRDateColumn(table);

        try {
            // Getting sum of "valColName"
            int total = getBusinessEntityTotal(businessEntityId, bsnEntLvl, fromDate, toDate, table.getValue(), valColName, dateColumn.getValue());

            // Getting Location details            
            connection = getOracleConnection();
            connection.setAutoCommit(true);

            sqlDetails = getLocationsQuery(fromDate, toDate, valColName, table.getValue(), bsnEntLvl, dateColumn.getValue());

            preparedStatement = connection.prepareStatement(sqlDetails);

            // int locationLevel = bsnEntLvl + 1;

            preparedStatement.setInt(1, bsnEntLvl);
            preparedStatement.setString(2, businessEntityId);
            preparedStatement.setInt(3, bsnEntLvl);
            preparedStatement.setInt(4, bsnEntLvl);

            resultSet = preparedStatement.executeQuery();

            List<Location> locationList = (List<Location>) new DataMapper().map(resultSet, table.getValue());

            LocationResponse locationResponse = new LocationResponse();
            locationResponse.setBusinessEntityId(businessEntityId);
            locationResponse.setBusinessEntityLevel(bsnEntLvl);
            locationResponse.setLocations(locationList);
            locationResponse.setValue(total);

            return locationResponse;

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
            safeCloseDataResources(resultSet, preparedStatement, connection);
        }

    }

    /**
     * @param businessEntityID
     * @param currentLvl
     * @param fromDate
     * @param toDate
     * @return
     */
    public List<Object> getPatientStaffDetail(String businessEntityID, int currentLvl, String fromDate, String toDate, String tableName, String sumColumn, String dateColumn, Class response) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String businessEntityIDColumnName = getBusinessEntityIDColumnName(currentLvl);

            StringBuilder sql = new StringBuilder();

            boolean isSum = false;

            String operation = "COUNT";

            if (isSumQuery(tableName)) {
                operation = "SUM";
                isSum = true;
            }

            String selectQuery = "";

            if (PatientDetailsResponse.class == response) {
                selectQuery = "T1.PT_FIRST_NAME, T1.PT_LAST_NAME,T1.PT_ID";
            } else if (StaffDetailsResponse.class == response) {
                selectQuery = "T1.STAFF_FIRST_NAME, T1.STAFF_LAST_NAME,T1.STAFF_ID";
            }


            /*
            if (tableName.equals(MV_SCHED.getValue()) || tableName.equals((MV_VISIT_VERIF.getValue()))) {
                selectQuery += ",T1." + Columns.VISIT_SK;
            }*/

            sql.append(String.format("SELECT %s, %s(%s) AS \"TOTAL\" " +
                    "from %s T1 ", selectQuery, operation, sumColumn, tableName));
            sql.append(String.format("where T1.%s = ? and   T1.BE_lvl = ? " +
                            "and T1.%s BETWEEN  TO_DATE('%s', 'YYYY-MM-DD HH24:MI') and TO_DATE('%s', 'YYYY-MM-DD HH24:MI') " +
                            "GROUP BY %s",
                    businessEntityIDColumnName, dateColumn, fromDate, toDate, selectQuery));
            ;


            connection = getOracleConnection();
            connection.setAutoCommit(true);

            preparedStatement = connection.prepareStatement(sql.toString());

            preparedStatement.setString(1, businessEntityID);
            preparedStatement.setInt(2, currentLvl);

            //execute select query

            resultSet = preparedStatement.executeQuery();

            List<Object> locationList = (ArrayList) new DataMapper().map(resultSet, tableName, sumColumn, response, isSum);

            return locationList;

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
     * @param businessEntityID
     * @param currentLvl
     * @param fromDate
     * @param toDate
     * @return
     */
    public List<StaffDetailsResponse> getVisiExceptionBreakdown(String businessEntityID, int currentLvl, String fromDate, String toDate) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String businessEntityIDColumnName = getBusinessEntityIDColumnName(currentLvl);

            StringBuilder sql = new StringBuilder();

            sql.append(String.format("SELECT T1.STAFF_FIRST_NAME, T1.STAFF_LAST_NAME,T1.STAFF_ID, COUNT(%s) AS \"TOTAL\" " +
                    "from %s T1 ", Columns.VISIT_EXCEPTION_SK.getValue(), Tables.MV_VISIT_EXCP.getValue()));
            sql.append(String.format("where T1.%s = ? and   T1.BE_lvl = ? " +
                            "and T1.%s BETWEEN  TO_DATE('%s', 'YYYY-MM-DD HH24:MI') and TO_DATE('%s', 'YYYY-MM-DD HH24:MI') " +
                            "GROUP BY T1.STAFF_FIRST_NAME, T1.STAFF_LAST_NAME,T1.STAFF_ID",
                    businessEntityIDColumnName, Columns.VISIT_START_DATE.getValue(), fromDate, toDate));
            ;


            connection = getOracleConnection();
            connection.setAutoCommit(true);

            preparedStatement = connection.prepareStatement(sql.toString());

            preparedStatement.setString(1, businessEntityID);
            preparedStatement.setInt(2, currentLvl);

            //execute select query

            resultSet = preparedStatement.executeQuery();

            List<StaffDetailsResponse> locationList = (ArrayList) new DataMapper().map(resultSet, Tables.MV_VISIT_EXCP.getValue(), Columns.VISIT_EXCEPTION_SK.getValue(), StaffDetailsResponse.class, true);

            return locationList;

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
     * @param businessEntityID
     * @param currentLvl
     * @param fromDate
     * @param toDate
     * @return
     */
    public List<VisitException> getStaffVisitExceptions(String businessEntityID, int currentLvl, String staffID, String fromDate, String toDate) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String businessEntityIDColumnName = getBusinessEntityIDColumnName(currentLvl);

            StringBuilder sql = new StringBuilder();

            sql.append(String.format("Select EXCP_NAME,VISIT_SK, VISIT_EXCP_SK, VISIT_START_DATE from COREDATAMART.MV_VISIT_EXCP WHERE STAFF_ID = ? " +
                            "AND %s = ?  AND BE_LVL = ? " +
                            "AND %s BETWEEN  TO_DATE('%s', 'YYYY-MM-DD HH24:MI') and TO_DATE('%s', 'YYYY-MM-DD HH24:MI') ",
                    businessEntityIDColumnName, Columns.VISIT_START_DATE.getValue(), fromDate, toDate));
            ;


            connection = getOracleConnection();
            connection.setAutoCommit(true);

            preparedStatement = connection.prepareStatement(sql.toString());

            preparedStatement.setString(1, staffID);
            preparedStatement.setString(2, businessEntityID);
            preparedStatement.setInt(3, currentLvl);


            //execute select query

            resultSet = preparedStatement.executeQuery();

            List<VisitException> visitExceptionList = new ArrayList<>();

            while (resultSet.next()) {
                VisitException visitException = new VisitException();
                visitException.setVisitDate(resultSet.getTimestamp("VISIT_START_DATE"));
                visitException.setVisitExcpName(resultSet.getString("EXCP_NAME"));
                visitException.setVisitSK(resultSet.getString("VISIT_SK"));
                visitException.setVisitExceptionSK(resultSet.getString("VISIT_EXCP_SK"));

                visitExceptionList.add(visitException);
            }

            return visitExceptionList;

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


    private String getValColName(String operationName) {
        if (operationName.contains("TotalPayrollHours") || operationName.contains("TotalOvertimeHours")) {
            return "PR_HRS";
        } else if (operationName.contains("totalPayrollDollars")) {
            return "PR_AMT";
        }
        return null;
    }


    private String getBsnEntIdColName(int bsnEntLvl) {

        switch (bsnEntLvl) {
            case 2:
                return "BE_LVL_2_ID";
            case 3:
                return "BE_LVL_3_ID";
            case 4:
                return "BE_LVL_4_ID";
            case 5:
                return "BE_LVL_5_ID";
            default:
                return "BE_ID";
        }
    }

    private Tables getPRTable(String operationName, boolean pending) {
        if (operationName.contains("TotalPayrollHours") || operationName.contains("TotalPayrollDollars")) {
            if (pending) {
                return Tables.MV_PR_PENDING;
            } else {
                return Tables.MV_PR;
            }

        } else if (operationName.contains("TotalOvertimeHours")) {
            return Tables.MV_PR_OT;
        }

        return null;
    }

    private Columns getPRDateColumn(Tables table) {
        switch (table) {
            case MV_PR:
                return Columns.MV_PR_PR_EXPORT_DATE;

            case MV_PR_OT:
                return Columns.MV_PR_PR_EXPORT_DATE;

            case MV_PR_PENDING:
                return Columns.MV_PR_PR_EXPORT_DATE;

            default:
                return Columns.MV_PR_PR_EXPORT_DATE;
        }
    }

    /**
     * Safely close database resources such as ResultSet, PreparedStatement, Connection
     *
     * @param resultSet
     * @param preparedStatement
     * @param connection
     */
    private void safeCloseDataResources(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
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

    private boolean isSumQuery(String tableName) {
        if (tableName.equals(Tables.MV_AUTH_EXPIRE.getValue()) || tableName.equals(Tables.MV_VISIT_EXCP.getValue())
                || tableName.equals(Tables.MV_OPEN_ORDER.getValue()) || tableName.equals(Tables.MV_COMP.getValue())
                || tableName.equals(Tables.MV_NONELIGIB.getValue())) {
            //using count function
            return false;
        } else {
            return true;
        }

    }

    private String getBusinessEntityIDColumnName(int level) {
        String businessEntityIDColumnName = "BE_ID";

        if (level > 1) {
            businessEntityIDColumnName = "BE_LVL_" + level + "_ID";
        }

        return businessEntityIDColumnName;
    }

    /**
     * get the list of staff/coordinator with total count of patients
     *
     * @param businessEntityID
     * @param currentLvl
     * @param fromDate
     * @param toDate
     * @return
     */
    public List<CoordinatorsResponse> getListCoordinators(String businessEntityID, int currentLvl, String fromDate,
                                                          String toDate) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String businessEntityIDColumnName = getBusinessEntityIDColumnName(currentLvl);

            StringBuilder sql = new StringBuilder();

            sql.append(String.format("SELECT T1.COORDINATOR_ID,T1.COORDINATOR_FIRST_NAME, T1.COORDINATOR_LAST_NAME, COUNT(%s) AS \"TOTAL\" " +
                    "from %s T1 ", Columns.PT_ID.getValue(), Tables.MV_OPEN_ORDER.getValue()));
            sql.append(String.format("where T1.%s = ? and   T1.BE_lvl = ? " +
                            "and T1.%s BETWEEN  TO_DATE('%s', 'YYYY-MM-DD HH24:MI') and TO_DATE('%s', 'YYYY-MM-DD HH24:MI') " +
                            "GROUP BY T1.COORDINATOR_ID,T1.COORDINATOR_FIRST_NAME, T1.COORDINATOR_LAST_NAME",
                    businessEntityIDColumnName, Columns.OPEN_ORD_DATE.getValue(), fromDate, toDate));

            connection = getOracleConnection();

            preparedStatement = connection.prepareStatement(sql.toString());

            preparedStatement.setString(1, businessEntityID);
            preparedStatement.setInt(2, currentLvl);

            //execute select query

            resultSet = preparedStatement.executeQuery();

            List<CoordinatorsResponse> locationList = (ArrayList) new DataMapper().map(resultSet, Tables.MV_OPEN_ORDER.getValue(), Columns.PT_ID.getValue(), CoordinatorsResponse.class, true);

            return locationList;

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
     * get list of patients by staff/coordinator id
     *
     * @param businessEntityID
     * @param currentLvl
     * @param fromDate
     * @param toDate
     * @return
     */
    public List<Patient> getListPatients(String staffId, String businessEntityID, int currentLvl, String fromDate, String toDate) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String businessEntityIDColumnName = getBusinessEntityIDColumnName(currentLvl);

            StringBuilder sql = new StringBuilder();

            sql.append(String.format("SELECT  T1.PT_ID,T1.PT_FIRST_NAME, T1.PT_LAST_NAME,T1.OPEN_ORD_DATE FROM %s T1 ", Tables.MV_OPEN_ORDER.getValue()));
            sql.append(String.format("where T1.%s = ? and   T1.BE_lvl = ? " +
                            "and T1.%s BETWEEN  TO_DATE('%s', 'YYYY-MM-DD HH24:MI') and TO_DATE('%s', 'YYYY-MM-DD HH24:MI')"

                    ,businessEntityIDColumnName, Columns.OPEN_ORD_DATE.getValue(), fromDate, toDate));

            if(!StringUtil.IsNullOrEmpty(staffId)){

                sql.append(" and T1.COORDINATOR_ID = ? ");

            }else {

                sql.append(" and T1.COORDINATOR_ID IS NULL ");
            }

            connection = getOracleConnection();
            connection.setAutoCommit(true);

            preparedStatement = connection.prepareStatement(sql.toString());

            preparedStatement.setString(1, businessEntityID);
            preparedStatement.setInt(2, currentLvl);

            if(!StringUtil.IsNullOrEmpty(staffId)) {
                preparedStatement.setString(3, staffId);
            }

            //execute select query
            resultSet = preparedStatement.executeQuery();
            List<Patient> patientList = (ArrayList) new DataMapper().map(resultSet, Tables.MV_OPEN_ORDER.getValue(), Patient.class);

            return patientList;

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
