package com.sandata.lab.rest.staff.impl;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.response.Response;
import org.apache.camel.PropertyInject;

import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OracleFindDataService extends OracleDataService {

    @PropertyInject("{{payroll.batch.size}}")
    private int batchSize = 200;

    public String getStaffIDByTIN(String tin, String businessEntityID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT STAFF_ID FROM STAFF WHERE UPPER(STAFF_TIN) = ? " +
                    " AND BE_ID =? AND CURR_REC_IND = 1 AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') ");

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, tin.toUpperCase());
            preparedStatement.setString(2, businessEntityID);

            resultSet = preparedStatement.executeQuery();

            String result = null;

            while (resultSet.next()) {
                result = resultSet.getString("STAFF_ID");
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

    public boolean staffExists(String businessEntityId, String firstName, String lastName, String staffID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT 'TRUE' AS STAFF_EXISTS FROM DUAL WHERE EXISTS (SELECT * FROM COREDATA.STAFF WHERE UPPER(STAFF_FIRST_NAME) = ? " +
                    " AND UPPER(STAFF_LAST_NAME) = ? " +
                    " AND UPPER(STAFF_ID) = ?" +
                    " AND BE_ID =? AND CURR_REC_IND = 1 AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') )");

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, firstName.toUpperCase());
            preparedStatement.setString(2, lastName.toUpperCase());
            preparedStatement.setString(3, staffID.toUpperCase());
            preparedStatement.setString(4, businessEntityId);

            resultSet = preparedStatement.executeQuery();

            boolean result = false;

            while (resultSet.next()) {
                result = resultSet.getString("STAFF_EXISTS").equals("TRUE") ? true : false;
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

    public List<Staff> getStaffByBusinessEntityID(String businessEntityID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT COUNT(*) AS TOTAL FROM COREDATA.STAFF " +
                    " WHERE BE_ID = ? AND UPPER(STAFF_EMPLT_STATUS_TYP_NAME) = ? AND CURR_REC_IND = 1 AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') " +
                    "ORDER BY REC_CREATE_TMSTP DESC";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, businessEntityID);
            preparedStatement.setString(2, EmploymentStatusTypeName.ACTIVE.value());

            resultSet = preparedStatement.executeQuery();

            int numRows = 0;

            while (resultSet.next()) {

                numRows = resultSet.getInt("TOTAL");

            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

            List<Staff> staffList = new ArrayList<>();

            int page = 1;
            int pageSize = batchSize;

            while (staffList.size() < numRows) {

                connection = connectionPoolDataService.getConnection();
                connection.setAutoCommit(true);

                int toRow = page * pageSize;
                int fromRow = toRow - (pageSize - 1);

                sql = "SELECT * from (SELECT ROWNUM ROW_NUM,r1.* FROM " +
                        "(SELECT * FROM COREDATA.STAFF T1" +
                        " LEFT OUTER JOIN (SELECT * FROM (SELECT * FROM COREDATA.STAFF_CONT_ADDR WHERE " +
                        " UPPER(ADDR_PRIO_NAME) = ?) WHERE ROWNUM = ?) T2" +
                        " ON T1.BE_ID = T2.BE_ID AND T1.STAFF_ID = T2.STAFF_ID " +
                        " WHERE " +
                        " T1.BE_ID = ? AND UPPER(T1.STAFF_EMPLT_STATUS_TYP_NAME) = ? AND T1.CURR_REC_IND = 1 AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31')" +
                        " ORDER BY T1.REC_CREATE_TMSTP DESC) r1) " +
                        " WHERE ROW_NUM BETWEEN ? AND ? ";

                preparedStatement = connection.prepareStatement(sql);

                int index = 1;
                preparedStatement.setString(index++, "PRIMARY");
                preparedStatement.setInt(index++, 1);
                preparedStatement.setString(index++, businessEntityID);
                preparedStatement.setString(index++, EmploymentStatusTypeName.ACTIVE.value());
                preparedStatement.setInt(index++, fromRow);
                preparedStatement.setInt(index++, toRow);

                resultSet = preparedStatement.executeQuery();


                //Stop loop since there are not more results.
                if (!resultSet.isBeforeFirst()) {

                    break;
                }

                page++;


                while (resultSet.next()) {

                    Staff staff = new Staff();
                    staff.setStaffID(resultSet.getString("STAFF_ID"));
                    staff.setStaffFirstName(resultSet.getString("STAFF_FIRST_NAME"));
                    staff.setStaffLastName(resultSet.getString("STAFF_LAST_NAME"));
                    staff.setStaffMiddleName(resultSet.getString("STAFF_MIDDLE_NAME"));
                    staff.setStaffTaxpayerIdentificationNumber(resultSet.getString("STAFF_TIN"));

                    try {

                        String maritalStat = resultSet.getString("STAFF_MRTL_STATUS_NAME");

                        if (!StringUtil.IsNullOrEmpty(maritalStat)) {
                            staff.setStaffMaritalStatusName(MaritalStatusName.fromValue(maritalStat));
                        }

                    } catch (Exception e) {

                        e.printStackTrace();
                    }

                    Timestamp staffTerminationDate = resultSet.getTimestamp("STAFF_TERM_DATE");
                    if (staffTerminationDate != null) {
                        staff.setStaffTerminationDate(new Date(staffTerminationDate.getTime()));
                    }

                    Timestamp staffHireDate = resultSet.getTimestamp("STAFF_HIRE_DATE");
                    if (staffHireDate != null) {
                        staff.setStaffHireDate(new Date(staffHireDate.getTime()));
                    }

                    Timestamp staffLastHireDate = resultSet.getTimestamp("STAFF_LAST_HIRE_DATE");
                    if (staffLastHireDate != null) {
                        staff.setStaffLastHireDate(new Date(staffLastHireDate.getTime()));
                    }

                    String positionName = resultSet.getString("STAFF_POSITION_NAME");

                    try {

                        if (!StringUtil.IsNullOrEmpty(positionName)) {
                            staff.setStaffPositionName(ServiceName.fromValue(positionName));
                        }
                    } catch (Exception e) {

                        e.printStackTrace();
                    }

                    staff.setStaffDateOfBirth(resultSet.getTimestamp("STAFF_DOB"));


                    StaffContactAddress staffContactAddress = new StaffContactAddress();
                    staffContactAddress.setStaffAddressLine1(resultSet.getString("STAFF_ADDR1"));
                    staffContactAddress.setStaffAddressLine2(resultSet.getString("STAFF_ADDR2"));
                    staffContactAddress.setStaffCity(resultSet.getString("STAFF_CITY"));

                    String state = resultSet.getString("STAFF_STATE");

                    if (!StringUtil.IsNullOrEmpty(state)) {
                        staffContactAddress.setStaffState(StateCode.fromValue(state));
                    }

                    staffContactAddress.setStaffPostalCode(resultSet.getString("STAFF_PSTL_CODE"));
                    staffContactAddress.setStaffZip4(resultSet.getString("STAFF_ZIP4"));

                    List<StaffContactAddress> staffContactAddresses = new ArrayList<>();
                    staffContactAddresses.add(staffContactAddress);
                    staff.getStaffContactAddress().addAll(staffContactAddresses);

                    staffList.add(staff);

                }

                connection.close();
                resultSet.close();
                preparedStatement.close();

                if (staffList.size() == numRows) {
                    break;
                }
            }

            return staffList;

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

    public Response findStaffClassEnrollment(
            String staffId,
            String bsnEntId,
            Date fromDateTime,
            Date toDateTime,
            int page,
            int pageSize,
            String direction,
            String orderBy) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            // Calculate row range.
            int toRow = page * pageSize;
            int fromRow = toRow - (pageSize - 1);


            StringBuilder filter = new StringBuilder();

            filter.append(" WHERE T1.STAFF_ID = ? AND T1.BE_ID = ? AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = '1') ");

            if (fromDateTime != null) {

                filter.append(" AND T1.STAFF_TRNG_START_DTIME >= " +
                        " TO_DATE(?, 'YYYY-MM-DD') ");
            }

            if (toDateTime != null) {

                filter.append(" AND " +
                        " T1.STAFF_TRNG_START_DTIME <= TO_DATE(?, 'YYYY-MM-DD')");
            }


            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, r1.* FROM( " +
                    " SELECT T3.STAFF_TRNG_RESULT_CODE, T2.STAFF_TRNG_NAME, T2.STAFF_TRNG_DESC, T1.*,T1.STAFF_TRNG_START_DTIME + (T2.STAFF_TRNG_TOTAL_HRS/24)  AS STAFF_TRNG_END_DTIME FROM " +
                    " COREDATA.STAFF_TRNG_CLS_EVNT_ENROL T1 " +
                    " LEFT JOIN COREDATA.BE_STAFF_TRNG_LKUP T2 " +
                    " ON T1.STAFF_TRNG_CODE = T2.STAFF_TRNG_CODE AND T1.BE_ID = T2.BE_ID AND (TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T2.CURR_REC_IND = '1')" +
                    " LEFT JOIN COREDATA.STAFF_TRNG T3 " +
                    " ON T1.BE_ID = T3.BE_ID AND T1.STAFF_ID = T3.STAFF_ID AND T1.STAFF_TRNG_LOC_NAME = T3.STAFF_TRNG_LOC_NAME " +
                    " AND TO_CHAR(T1.STAFF_TRNG_START_DTIME, 'YYYY-MM-DD') = TO_CHAR(T3.STAFF_TRNG_START_DTIME, 'YYYY-MM-DD') " +
                    " AND (TO_CHAR(T3.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T3.CURR_REC_IND = '1')" +
                    " %s " +
                    " ORDER BY T1.%s %s) r1) WHERE ROW_NUMBER BETWEEN %d AND %d", filter, orderBy, direction, fromRow, toRow);

            preparedStatement = connection.prepareStatement(sql);


            preparedStatement.setString(1, staffId);
            preparedStatement.setString(2, bsnEntId);

            int i = 3;

            if (fromDateTime != null) {
                preparedStatement.setString(i, new SimpleDateFormat("yyyy-MM-dd").format(fromDateTime));
                i++;
            }

            if (toDateTime != null) {
                preparedStatement.setString(i, new SimpleDateFormat("yyyy-MM-dd").format(toDateTime));
            }

            resultSet = preparedStatement.executeQuery();

            List<FindClassResult> classResults = new ArrayList<>();

            int total = 0;

            while (resultSet.next()) {

                total = resultSet.getInt("TOTAL_ROWS");

                FindClassResult findClassResult = new FindClassResult();

                findClassResult.setStaffTrainingCode(resultSet.getString("STAFF_TRNG_CODE"));
                findClassResult.setStaffTrainingName(resultSet.getString("STAFF_TRNG_NAME"));
                findClassResult.setStaffTrainingLocationName(resultSet.getString("STAFF_TRNG_LOC_NAME"));
                findClassResult.setStaffTrainingStartDateTime(resultSet.getTimestamp("STAFF_TRNG_START_DTIME"));
                findClassResult.setStaffTrainingEndDateTime(resultSet.getTimestamp("STAFF_TRNG_END_DTIME"));

                BigDecimal dropInd = resultSet.getBigDecimal("STAFF_TRNG_DROP_IND");

                if (dropInd != null && dropInd.intValue() == 1) {

                    findClassResult.setStaffTrainingDropIndicator(true);
                    findClassResult.setClassStatus(InServiceTrainingResult.DROPPED.toString());
                }
                else {
                    findClassResult.setStaffTrainingDropIndicator(false);
                }

                String staffTrainingResultCode = resultSet.getString("STAFF_TRNG_RESULT_CODE");

                if (InServiceTrainingResult.COMPLETED.toString().equalsIgnoreCase(staffTrainingResultCode)) {
                    findClassResult.setStaffTrainingCompletedIndicator(true);
                    findClassResult.setStaffTrainingNoShowIndicator(false);

                } else if (InServiceTrainingResult.NO_SHOW.toString().equalsIgnoreCase(staffTrainingResultCode)) {
                    findClassResult.setStaffTrainingNoShowIndicator(true);
                    findClassResult.setStaffTrainingCompletedIndicator(false);
                }

                if(!StringUtil.IsNullOrEmpty(staffTrainingResultCode)){

                    findClassResult.setClassStatus(InServiceTrainingResult.fromValue(staffTrainingResultCode).toString());
                }

                classResults.add(findClassResult);
            }

            Response response = new Response();

            response.setTotalRows(total);
            response.setData(classResults);

            connection.commit();

            return response;

        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s", e.getClass().getName(), e.getMessage()), e);
        } finally {
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
