package com.sandata.lab.rest.staff.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.custom.AuditEmploymentStatusHistory;
import com.sandata.lab.data.model.dl.model.extended.admin.AdminStaffParentRelTypeResponse;
import com.sandata.lab.data.model.dl.model.extended.admin.AdminStaffRelTypeResponse;
import com.sandata.lab.data.model.dl.model.extended.staff.StaffComplianceExt;
import com.sandata.lab.data.model.dl.model.extended.staff.StaffExt;
import com.sandata.lab.data.model.dl.model.extended.staff.StaffTrainingClassEventEnrollmentExt;
import com.sandata.lab.data.model.jpub.model.AppAuditT;
import com.sandata.lab.data.model.jpub.model.StaffLangT;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.staff.api.OracleService;
import com.sandata.lab.rest.staff.model.FindExclPatientsResult;
import com.sandata.lab.rest.staff.model.FindExclStaffResult;
import com.sandata.lab.rest.staff.model.*;
import com.sandata.lab.rest.staff.model.ValidatedStaffResult.MessageLevel;
import com.sandata.lab.rest.staff.utils.data.DistinctColumnUtil;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

@SuppressWarnings("unchecked")
public class OracleDataService implements OracleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleDataService.class);

    private static final SimpleDateFormat ORACLE_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    protected ConnectionPoolDataService connectionPoolDataService;

    public Connection getOracleConnection() throws SandataRuntimeException {
        return connectionPoolDataService.getConnection();
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
    public Object executeGet(String packageName, String methodName, String className, Object... params) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
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

            // TODO: Make more generic...
            if (className.endsWith("DistinctColumn")) {
                return DistinctColumnUtil.toArray((List<DistinctColumn>) result);
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

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
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

    public Response findStaff(
            Connection connection,
            StringBuilder staffFilterTyp,
            String orderByColumn,
            int page,
            int pageSize,
            String orderByDirection,
            String bsnEntId,
            Date fromDateTime,
            Date toDateTime,
            String staffPosition,
            List<String> employmentStatusList,
            String complianceStatus,
            String staffCoordinatorId,
            List<String> params,
            String staffNurseId) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String currentDate = dateFormat.format(new Date());

            String filterByStaffPosition = "";
            if (!StringUtil.IsNullOrEmpty(staffPosition)) {
                filterByStaffPosition = "AND T1.STAFF_POSITION_NAME = ?";
            }

            String filterByEmplStatus = "";
            if (employmentStatusList != null && employmentStatusList.size() > 0) {
                filterByEmplStatus = String.format(" AND UPPER(T1.STAFF_EMPLT_STATUS_TYP_NAME) IN (%s) ", StringUtils.repeat("?", ",", employmentStatusList.size()));
            }

            String filterByComplianceStatus = "";
            String filterByComplianceStatusJoin = "";
            int bCompliantStatus = 0;
            if (!StringUtil.IsNullOrEmpty(complianceStatus)) {
                filterByComplianceStatusJoin = "INNER JOIN (SELECT BE_ID,STAFF_ID,REC_TERM_TMSTP,CURR_REC_IND,STAFF_COMP_EXPR_DATE,STAFF_COMP_RCVD_DATE,COMP_IND " +
                        "      FROM STAFF_COMP_SUMMARY " +
                        "        WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1') " +
                        "          AND  TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                        "              BETWEEN STAFF_COMP_RCVD_DATE AND STAFF_COMP_EXPR_DATE) T10 " +
                        "    ON T1.BE_ID = T10.BE_ID AND T1.STAFF_ID = T10.STAFF_ID";

                filterByComplianceStatus = "AND COMP_IND = ?";

                if (complianceStatus.equalsIgnoreCase("Compliant")) {
                    bCompliantStatus = 1;
                }
            }

            String filterByStaffCoordinator = "";
            if (!StringUtil.IsNullOrEmpty(staffCoordinatorId)) {
                filterByStaffCoordinator = "INNER JOIN (SELECT BE_ID,STAFF_ID,ADMIN_STAFF_ID,ADMIN_STAFF_STAFF_EFF_DATE,ADMIN_STAFF_STAFF_TERM_DATE " +
                        "        FROM ADMIN_STAFF_STAFF_XREF " +
                        "          WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)  " +
                        "            AND CURRENT_TIMESTAMP BETWEEN ADMIN_STAFF_STAFF_EFF_DATE AND ADMIN_STAFF_STAFF_TERM_DATE " +
                        "            AND ADMIN_STAFF_ID = ?) ASSX " +
                        "      ON T1.BE_ID = ASSX.BE_ID AND T1.STAFF_ID = ASSX.STAFF_ID ";
            }
            //san-3420.san-3255
            String filterByStaffNurse = "";
            if (!StringUtil.IsNullOrEmpty(staffNurseId)) {
                filterByStaffNurse = "INNER JOIN (SELECT BE_ID,STAFF_ID,ADMIN_STAFF_ID,ADMIN_STAFF_STAFF_EFF_DATE,ADMIN_STAFF_STAFF_TERM_DATE " +
                        "        FROM ADMIN_STAFF_STAFF_XREF " +
                        "          WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)  " +
                        "            AND CURRENT_TIMESTAMP BETWEEN ADMIN_STAFF_STAFF_EFF_DATE AND ADMIN_STAFF_STAFF_TERM_DATE " +
                        "            AND UPPER(ADMIN_STAFF_ROLE_NAME) = 'NURSE' " +
                        "            AND ADMIN_STAFF_ID = ?) NURSE " +
                        "      ON T1.BE_ID = NURSE.BE_ID AND T1.STAFF_ID = NURSE.STAFF_ID ";
            }


            String sql = String.format(
                            "SELECT R2.* FROM " +
                            "  (SELECT ROWNUM ROW_NUMBER, R1.* FROM " +
                            "    (SELECT DISTINCT COUNT(*) OVER() TOTAL_ROWS, " +
                            "            PKG_STAFF_UTIL.HIRE_DATE(T1.STAFF_ID, T2.BE_ID) AS ORIGINAL_STAFF_HIRE_DATE, T1.STAFF_POSITION_NAME,T1.STAFF_ID,T1.STAFF_SK,T1.STAFF_FIRST_NAME,T1.STAFF_LAST_NAME,T1.STAFF_DOB, T1.STAFF_EMPLT_STATUS_TYP_NAME, " +
                            "            T1.STAFF_HIRE_DATE,T3.BE_ID_NUM AS STAFF_LOCATION_XWALK,T1.STAFF_LAST_HIRE_DATE,T1Addr.STAFF_ADDR1,T1Addr.STAFF_ADDR2,T1Addr.STAFF_CITY,T1Addr.STAFF_STATE,T1Addr.STAFF_PSTL_CODE, " +
                            "            T2.BE_ID,T2.BE_SK,T2.BE_NAME,T2.BE_TYP,T2.BE_PRMY_ADDR1,T2.BE_PRMY_ADDR2,T2.BE_PRMY_CITY, " +
                            "            T2.BE_PRMY_STATE,T2.BE_PRMY_PSTL_CODE,T2.BE_PRMY_ZIP4,T2.BE_PRMY_PHONE_1, T1Phone.STAFF_PHONE, PKG_STAFF_UTIL.LAST_DATE_WORKED(T1.STAFF_ID, T1.BE_ID) AS LAST_DATE_WORKED  " +
                            " " +
                            "       FROM STAFF T1 " +
                            " " +
                            "      LEFT JOIN (SELECT BE_ID,STAFF_ID,STAFF_ADDR1,STAFF_ADDR2,STAFF_CITY,STAFF_STATE,STAFF_PSTL_CODE,REC_TERM_TMSTP,CURR_REC_IND " +
                            "          FROM STAFF_CONT_ADDR WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1') " +
                            "                AND UPPER(ADDR_PRIO_NAME) = 'PRIMARY')  T1Addr " +
                            "      ON T1.STAFF_ID = T1Addr.STAFF_ID AND T1.BE_ID = T1Addr.BE_ID " +
                            " " +
                            "      LEFT JOIN (SELECT BE_ID,STAFF_ID,STAFF_PHONE,REC_TERM_TMSTP,CURR_REC_IND " +
                            "          FROM STAFF_CONT_PHONE WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1') " +
                            "                AND UPPER(ADDR_PRIO_NAME) = 'PRIMARY' AND STAFF_PHONE_PRMY_IND = 1)  T1Phone " +
                            "      ON T1.STAFF_ID = T1Phone.STAFF_ID AND T1.BE_ID = T1Phone.BE_ID " +
                            " " +
                            "      INNER JOIN (SELECT BE_SK,BE_ID,BE_NAME,BE_TYP,BE_PRMY_ADDR1,BE_PRMY_ADDR2,BE_PRMY_CITY,BE_PRMY_STATE,BE_PRMY_PSTL_CODE, " +
                            "                    BE_PRMY_ZIP4,BE_PRMY_PHONE_1,REC_TERM_TMSTP,CURR_REC_IND FROM BE) T2 " +
                            "      ON T1.BE_ID = T2.BE_ID " +
                            " " +
                            "      LEFT JOIN (SELECT BE_ID, BE_ID_TYP, BE_ID_QLFR, BE_ID_NUM FROM BE_ID_XWALK WHERE BE_ID_TYP = 'LOCATION' AND BE_ID_QLFR = 'CODE') T3  " +
                            "      ON T1.STAFF_LOC = T3.BE_ID " +
                            " " +
                            "      %s   " +
                            " " +
                            "      %s   " +
                            " " +
                            "      %s          " +
                            " " +
                            "      WHERE T1.BE_ID = ? %s %s %s AND %s" +
                            " " +
                            "      (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = '1') AND " +
                            "      (TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T2.CURR_REC_IND = '1') " +
                            " " +
                            "      ORDER BY %s %s" +
                            " " +
                            "    ) R1" +
                            "    WHERE ROWNUM <= %d " +
                            "  ) R2 " +
                            "WHERE R2.ROW_NUMBER >= %d ",

                    filterByStaffCoordinator,
                    filterByStaffNurse,
                    filterByComplianceStatusJoin,
                    filterByComplianceStatus,
                    filterByStaffPosition,
                    filterByEmplStatus,
                    staffFilterTyp.toString(),
                    orderByColumn,
                    orderByDirection,
                    toRow,
                    fromRow
            );

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;

            if (!StringUtil.IsNullOrEmpty(staffCoordinatorId)) {
                preparedStatement.setString(index++, staffCoordinatorId);
            }
            //SAN-3420.SAN-3255
            if (!StringUtil.IsNullOrEmpty(staffNurseId)) {
                preparedStatement.setString(index++, staffNurseId);
            }


            if (!StringUtil.IsNullOrEmpty(complianceStatus)) {
                preparedStatement.setString(index++, currentDate);
                preparedStatement.setString(index++, bsnEntId);
                preparedStatement.setInt(index++, bCompliantStatus);

            } else {
                preparedStatement.setString(index++, bsnEntId);
            }

            if (!StringUtil.IsNullOrEmpty(staffPosition)) {
                preparedStatement.setString(index++, staffPosition.toUpperCase());
            }

            if (employmentStatusList != null && employmentStatusList.size() > 0) {
                for (String employmentStatus : employmentStatusList) {
                    preparedStatement.setString(index++, employmentStatus.toUpperCase());
                }
            }

            for (String param : params) {
                if (param != null) {
                    preparedStatement.setString(index++, param);
                }
            }

            resultSet = preparedStatement.executeQuery();

            Response response = mapResultSetToFindStaff(
                    connection,
                    resultSet,
                    fromDateTime,
                    toDateTime,
                    bsnEntId);

            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(orderByDirection);

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
        }
    }

    public Response findStaffForServiceSchedule(
            Connection connection,
            StringBuilder staffFilterTyp,
            String orderByColumn,
            int page,
            int pageSize,
            String orderByDirection,
            String bsnEntId,
            Date fromDateTime,
            Date toDateTime,
            String staffPosition,
            String patientId,
            List<String> employmentStatusList,
            String complianceStatus,
            String staffCoordinatorId,
            List<String> params,
            String staffNurseId) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String currentDate = dateFormat.format(new Date());

            String filterByComplianceStatusJoin = "";
            int bCompliantStatus = 0;
            if (!StringUtil.IsNullOrEmpty(complianceStatus)) {
                filterByComplianceStatusJoin = "INNER JOIN (SELECT BE_ID,STAFF_ID,REC_TERM_TMSTP,CURR_REC_IND,STAFF_COMP_EXPR_DATE,STAFF_COMP_RCVD_DATE,COMP_IND " +
                        "      FROM STAFF_COMP_SUMMARY " +
                        "        WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1') " +
                        "          AND  TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                        "              BETWEEN STAFF_COMP_RCVD_DATE AND STAFF_COMP_EXPR_DATE) T10 " +
                        "    ON T1.BE_ID = T10.BE_ID AND T1.STAFF_ID = T10.STAFF_ID";

                staffFilterTyp.append(" COMP_IND = ? AND ");

                if (complianceStatus.equalsIgnoreCase("Compliant")) {
                    bCompliantStatus = 1;
                }
            }

            if (!StringUtil.IsNullOrEmpty(patientId)) {
                staffFilterTyp.append(" PT_ID = ? AND ");
            }

            if (employmentStatusList != null && employmentStatusList.size() > 0) {

                //There is a list of status, so needs to build up a list of question marks
                staffFilterTyp.append(" UPPER(T1.STAFF_EMPLT_STATUS_TYP_NAME) IN ( ").append(StringUtils.repeat("?", ",", employmentStatusList.size())).append(" ) AND ");
            }

            String filterByStaffCoordinator = "";
            if (!StringUtil.IsNullOrEmpty(staffCoordinatorId)) {
                filterByStaffCoordinator = "INNER JOIN (SELECT BE_ID,STAFF_ID,ADMIN_STAFF_ID,ADMIN_STAFF_STAFF_EFF_DATE,ADMIN_STAFF_STAFF_TERM_DATE " +
                        "        FROM ADMIN_STAFF_STAFF_XREF " +
                        "          WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)  " +
                        "            AND CURRENT_TIMESTAMP BETWEEN ADMIN_STAFF_STAFF_EFF_DATE AND ADMIN_STAFF_STAFF_TERM_DATE " +
                        "            AND ADMIN_STAFF_ID = ?) ASSX " +
                        "      ON T1.BE_ID = ASSX.BE_ID AND T1.STAFF_ID = ASSX.STAFF_ID ";
            }
            //san-3255->san-3420
            String filterByStaffNurse = "";
            if (!StringUtil.IsNullOrEmpty(staffNurseId)) {
                filterByStaffNurse = "INNER JOIN (SELECT BE_ID,STAFF_ID,ADMIN_STAFF_ID,ADMIN_STAFF_STAFF_EFF_DATE,ADMIN_STAFF_STAFF_TERM_DATE " +
                        "        FROM ADMIN_STAFF_STAFF_XREF " +
                        "          WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)  " +
                        "            AND CURRENT_TIMESTAMP BETWEEN ADMIN_STAFF_STAFF_EFF_DATE AND ADMIN_STAFF_STAFF_TERM_DATE " +
                        "            AND UPPER(ADMIN_STAFF_ROLE_NAME) = 'NURSE' " +
                        "            AND ADMIN_STAFF_ID = ?) NURSE " +
                        "      ON T1.BE_ID = NURSE.BE_ID AND T1.STAFF_ID = NURSE.STAFF_ID ";
            }


            String sql = String.format("SELECT * FROM ( " +
                            "  SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* " +
                            "    FROM (SELECT DISTINCT PKG_STAFF_UTIL.HIRE_DATE(T1.STAFF_ID, T2.BE_ID) AS ORIGINAL_STAFF_HIRE_DATE, T1.STAFF_ID,T1.STAFF_SK,T1.STAFF_FIRST_NAME,T1.STAFF_LAST_NAME,T1.STAFF_DOB, T1.STAFF_EMPLT_STATUS_TYP_NAME, " +
                            "          T1.STAFF_HIRE_DATE,T3.BE_ID_NUM AS STAFF_LOCATION_XWALK,T1.STAFF_LAST_HIRE_DATE,T1Addr.STAFF_ADDR1,T1Addr.STAFF_ADDR2,T1Addr.STAFF_CITY,T1Addr.STAFF_STATE,T1Addr.STAFF_PSTL_CODE, " +
                            "          T2.BE_ID,T2.BE_SK,T2.BE_NAME,T2.BE_TYP,T2.BE_PRMY_ADDR1,T2.BE_PRMY_ADDR2,T2.BE_PRMY_CITY, " +
                            "          T2.BE_PRMY_STATE,T2.BE_PRMY_PSTL_CODE,T2.BE_PRMY_ZIP4,T2.BE_PRMY_PHONE_1, " +
                            " " +
                            "          T1.STAFF_POSITION_NAME, T1Phone.STAFF_PHONE, PKG_STAFF_UTIL.LAST_DATE_WORKED(T1.STAFF_ID, T1.BE_ID) AS LAST_DATE_WORKED " +
                            " " +
                            "          FROM STAFF T1 " +
                            " " +
                            "  LEFT JOIN (SELECT BE_ID,STAFF_ID,STAFF_ADDR1,STAFF_ADDR2,STAFF_CITY,STAFF_STATE,STAFF_PSTL_CODE,REC_TERM_TMSTP,CURR_REC_IND " +
                            "    FROM STAFF_CONT_ADDR WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1') " +
                            "      AND UPPER(ADDR_PRIO_NAME) = 'PRIMARY')  T1Addr " +
                            "  ON T1.STAFF_ID = T1Addr.STAFF_ID AND T1.BE_ID = T1Addr.BE_ID " +
                            " " +
                            "  LEFT JOIN (SELECT BE_ID,STAFF_ID,STAFF_PHONE,REC_TERM_TMSTP,CURR_REC_IND " +
                            "    FROM STAFF_CONT_PHONE WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1') " +
                            "      AND UPPER(ADDR_PRIO_NAME) = 'PRIMARY' AND STAFF_PHONE_PRMY_IND = 1)  T1Phone " +
                            "  ON T1.STAFF_ID = T1Phone.STAFF_ID AND T1.BE_ID = T1Phone.BE_ID " +
                            " " +
                            "  INNER JOIN (SELECT BE_SK,BE_ID,BE_NAME,BE_TYP,BE_PRMY_ADDR1,BE_PRMY_ADDR2,BE_PRMY_CITY,BE_PRMY_STATE,BE_PRMY_PSTL_CODE, " +
                            "    BE_PRMY_ZIP4,BE_PRMY_PHONE_1,REC_TERM_TMSTP,CURR_REC_IND FROM BE) T2 " +
                            "  ON T1.BE_ID = T2.BE_ID " +
                            " " +
                            "    INNER JOIN (SELECT BE_ID,PT_ID,STAFF_ID,AUTH_SK,SCHED_EVNT_START_DTIME " +
                            "      FROM SCHED_EVNT " +
                            "        WHERE SCHED_EVNT_START_DTIME BETWEEN " +
                            "                TO_DATE(?, 'YYYY-MM-DD HH24:MI') AND " +
                            "                TO_DATE(?, 'YYYY-MM-DD HH24:MI')) T7 " +
                            "    ON T1.BE_ID = T7.BE_ID AND T1.STAFF_ID = T7.STAFF_ID " +
                            "        AND AUTH_SK IS NOT NULL " +
                            " " +
                            "    INNER JOIN (SELECT BE_ID,AUTH_SK,SVC_NAME " +
                            "      FROM AUTH_SVC) T8 " +
                            "    ON T1.BE_ID = T8.BE_ID AND T7.AUTH_SK = T8.AUTH_SK " +
                            " " +
                            "    INNER JOIN (SELECT BE_ID,SVC_NAME " +
                            "      FROM SVC) T9 " +
                            "    ON T1.BE_ID = T9.BE_ID AND T8.SVC_NAME = T9.SVC_NAME " +
                            " " +
                            "    LEFT JOIN (SELECT BE_ID, BE_ID_TYP, BE_ID_QLFR, BE_ID_NUM FROM BE_ID_XWALK WHERE BE_ID_TYP = 'LOCATION' AND BE_ID_QLFR = 'CODE') T3  " +
                            "    ON T1.STAFF_LOC = T3.BE_ID " +
                            " " +
                            "    %s          " +
                            " " +
                            "    %s          " +
                            " " +
                            "    %s          " +
                            " " +
                            "WHERE T1.BE_ID = ? AND T9.SVC_NAME = ? AND T1.STAFF_POSITION_NAME = ? AND %s " +
                            " " +
                            "(TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = '1') AND " +
                            "(TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T2.CURR_REC_IND = '1') " +
                            " " +
                            "ORDER BY %s %s) R1) " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d ",

                    filterByStaffCoordinator,
                    filterByStaffNurse,
                    filterByComplianceStatusJoin,
                    staffFilterTyp.toString(),
                    orderByColumn,
                    orderByDirection,
                    fromRow,
                    toRow
            );

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;

            preparedStatement.setString(index++, ORACLE_DATE_TIME_FORMAT.format(fromDateTime));
            preparedStatement.setString(index++, ORACLE_DATE_TIME_FORMAT.format(toDateTime));

            if (!StringUtil.IsNullOrEmpty(staffCoordinatorId)) {
                preparedStatement.setString(index++, staffCoordinatorId);
            }

            //SAN-3420.SAN-3255
            if (!StringUtil.IsNullOrEmpty(staffNurseId)) {
                preparedStatement.setString(index++, staffNurseId);
            }

            if (!StringUtil.IsNullOrEmpty(complianceStatus)) {
                preparedStatement.setString(index++, currentDate);
            }

            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, staffPosition.toUpperCase());
            preparedStatement.setString(index++, staffPosition.toUpperCase());

            for (String param : params) {
                if (param != null) {
                    preparedStatement.setString(index++, param.toUpperCase() + "%");
                }
            }

            if (!StringUtil.IsNullOrEmpty(complianceStatus)) {
                preparedStatement.setInt(index++, bCompliantStatus);
            }

            if (!StringUtil.IsNullOrEmpty(patientId)) {
                preparedStatement.setString(index++, patientId);
            }

            if (employmentStatusList != null && employmentStatusList.size() > 0) {
                for (String employmentStatus : employmentStatusList) {
                    preparedStatement.setString(index++, employmentStatus.toUpperCase());
                }
            }

            resultSet = preparedStatement.executeQuery();

            Response response = mapResultSetToFindStaff(
                    connection,
                    resultSet,
                    fromDateTime,
                    toDateTime,
                    bsnEntId);

            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(orderByDirection);

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
        }
    }

    public List<ScheduleEvent> getStaffScheduleEvents(String staffId, String fromDate, String toDate) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM SCHED_EVNT " +
                "where STAFF_ID = ?  " +
                "  AND SCHED_EVNT_START_DTIME " +
                "  BETWEEN TO_DATE(?, 'YYYY-MM-DD HH24:MI') " +
                "  AND TO_DATE(?, 'YYYY-MM-DD HH24:MI') " +
                "  AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, staffId);
            preparedStatement.setString(index++, fromDate);
            preparedStatement.setString(index++, toDate);

            resultSet = preparedStatement.executeQuery();

            List<ScheduleEvent> resultList =
                (List<ScheduleEvent>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.ScheduleEvent");

            connection.commit();

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

    public List<ScheduleEvent> getStaffScheduleEvents(String staffId) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM SCHED_EVNT WHERE STAFF_ID = ?";

            preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            preparedStatement.setString(index, staffId);

            resultSet = preparedStatement.executeQuery();
            List<ScheduleEvent> scheduleEvents = (List<ScheduleEvent>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.ScheduleEvent");

            connection.commit();

            return scheduleEvents;

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

    public Response staffScheduleEvents(final String filterTyp, final List<String> params,
                                        final int page, final int pageSize)
        throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, r1.* " +
                "FROM " +
                "   (SELECT T1.* FROM STAFF T1 " +
                "     LEFT JOIN (SELECT SCHED_EVNT_SK,STAFF_ID,REC_TERM_TMSTP,CURR_REC_IND FROM SCHED_EVNT) T2 " +
                "     ON T1.STAFF_ID = T2.STAFF_ID " +
                "     WHERE %s " +
                "     (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1) AND " +
                "     (TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T2.CURR_REC_IND = 1) " +
                "     ORDER BY T1.STAFF_LAST_NAME ASC " +
                " ) r1) " +
                "WHERE ROW_NUMBER BETWEEN %d AND %d", filterTyp, fromRow, toRow);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (String param : params) {
                preparedStatement.setString(index++, param);
            }

            resultSet = preparedStatement.executeQuery();

            List<StaffSchedEvents> staffSchedEventsList = new ArrayList<>();

            Response response = new Response();
            response.setData(staffSchedEventsList);
            response.setPage(page);
            response.setPageSize(pageSize);

            Map<BigInteger, Staff> staffMap = new HashMap<>();
            int totalRows = 0;
            while (resultSet.next()) {

                if (totalRows == 0) {
                    totalRows = resultSet.getBigDecimal("TOTAL_ROWS").intValue();
                }

                Staff staff = (Staff) new DataMapper().mapResultSet(resultSet, "com.sandata.lab.data.model.dl.model.Staff", 2);

                if (!staffMap.containsKey(staff.getStaffSK())) {
                    staffMap.put(staff.getStaffSK(), staff);

                    StaffSchedEvents staffSchedEvents = new StaffSchedEvents();

                    staffSchedEvents.setStaff(staff);

                    // Get the schedule events for the staff...
                    List<ScheduleEvent> scheduleEvents = getStaffScheduleEvents(staff.getStaffID());
                    staffSchedEvents.setScheduleEvents(scheduleEvents);

                    staffSchedEventsList.add(staffSchedEvents);
                } else {
                    //TODO: Need to fix this...

                    // Since totalRows includes "dup" records for each sched_evnt because of the "join", subtract one from total...
                    totalRows--;
                }
            }

            response.setTotalRows(totalRows);

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

    @Override
    public long execute(String packageName, String methodName, long sequenceKey) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setLong(2, sequenceKey);
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

    public Response excludedStaff(final String orderByColumn,
                                  final String orderByDirection,
                                  final String patientId,
                                  final String bsnEntId) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* " +
                "  FROM " +
                "   (SELECT " +
                "      T1.SCHED_PTC_EXCL_SK, T1.PT_ID AS STAFF_EXCLUDED_PT_ID,T3.PT_SK AS PT_EXCLUDED_SK, " +
                "      T1.STAFF_ID,T1.SCHED_PTC_EXCL_NOTE, T1.BE_ID, " +
                "      T1.PT_ID,T2.STAFF_FIRST_NAME,T2.STAFF_LAST_NAME,T2.STAFF_MIDDLE_NAME,T1.SCHED_PTC_EXCL_QLFR,T1.SCHED_PERM_QLFR " +
                "    FROM SCHED_PTC_EXCL T1 " +
                " " +
                "      INNER JOIN (SELECT STAFF_SK,STAFF_ID,BE_ID,STAFF_FIRST_NAME,STAFF_LAST_NAME,STAFF_MIDDLE_NAME, " +
                "                   REC_TERM_TMSTP,CURR_REC_IND " +
                "                 FROM STAFF) T2 " +
                "        ON T1.STAFF_ID = T2.STAFF_ID AND T1.BE_ID = T2.BE_ID " +
                " " +
                "      INNER JOIN (SELECT PT_SK,PT_ID,BE_ID,REC_TERM_TMSTP,CURR_REC_IND " +
                "                 FROM PT) T3 " +
                "        ON T1.PT_ID = T3.PT_ID AND T1.BE_ID = T3.BE_ID " +
                " " +
                "    WHERE T1.PT_ID = ? AND T1.BE_ID = ? AND UPPER(SCHED_PTC_EXCL_QLFR) = 'STAFF' AND " +
                "          (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = '1') AND " +
                "          (TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T2.CURR_REC_IND = '1') AND " +
                "          (TO_CHAR(T3.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T3.CURR_REC_IND = '1') " +
                "    ORDER BY %s %s " +
                "   ) R1) ", orderByColumn, orderByDirection);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, patientId);
            preparedStatement.setString(index, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<FindExclStaffResult> findExclStaffResults = new ArrayList<>();

            Response response = new Response();
            response.setData(findExclStaffResults);
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(orderByDirection);

            while (resultSet.next()) {

                FindExclStaffResult findExclStaffResult = new FindExclStaffResult();

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());
                }

                findExclStaffResult.setStaffExcludedPatientID(resultSet.getString("STAFF_EXCLUDED_PT_ID"));
                findExclStaffResult.setScheduleParticipantExclusionSK(BigInteger.valueOf(resultSet.getBigDecimal("SCHED_PTC_EXCL_SK").longValue()));
                findExclStaffResult.setPatientExcludedSk(BigInteger.valueOf(resultSet.getBigDecimal("PT_EXCLUDED_SK").longValue()));
                findExclStaffResult.setStaffId(resultSet.getString("STAFF_ID"));
                findExclStaffResult.setScheduleParticipantExclusionNote(resultSet.getString("SCHED_PTC_EXCL_NOTE"));
                findExclStaffResult.setPatientId(resultSet.getString("PT_ID"));
                findExclStaffResult.setStaffFirstName(resultSet.getString("STAFF_FIRST_NAME"));
                findExclStaffResult.setStaffMiddleName(resultSet.getString("STAFF_MIDDLE_NAME"));
                findExclStaffResult.setStaffLastName(resultSet.getString("STAFF_LAST_NAME"));
                findExclStaffResult.setScheduleParticipantExclusionQualifier(resultSet.getString("SCHED_PTC_EXCL_QLFR"));
                findExclStaffResult.setSchedulePermissionQualifier(resultSet.getString("SCHED_PERM_QLFR"));
                findExclStaffResult.setBusinessEntityID(resultSet.getString("BE_ID"));
                findExclStaffResults.add(findExclStaffResult);
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

    public Response excludedPatients(
        final String orderByColumn,
        final String orderByDirection,
        final String staffId,
        final String bsnEntId)
        throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* " +
                    "  FROM " +
                    "   (SELECT DISTINCT " +
                    "      T1.SCHED_PTC_EXCL_SK,T1.PT_ID AS STAFF_EXCLUDED_PT_ID,T2.PT_SK, " +
                    "      T1.STAFF_ID,T1.SCHED_PTC_EXCL_NOTE, T1.BE_ID, " +
                    "      T2.PT_FIRST_NAME,T2.PT_LAST_NAME,T2.PT_MIDDLE_NAME,T1.PT_ID,T1.SCHED_PTC_EXCL_QLFR,T1.SCHED_PERM_QLFR " +
                    "    FROM SCHED_PTC_EXCL T1 " +
                    "      INNER JOIN (SELECT PT_SK,PT_ID,PT_FIRST_NAME,PT_LAST_NAME,PT_MIDDLE_NAME, " +
                    "                   BE_ID,REC_TERM_TMSTP,CURR_REC_IND " +
                    "                 FROM PT) T2 " +
                    "        ON T1.PT_ID = T2.PT_ID AND T1.BE_ID = T2.BE_ID " +
                    " " +
                    "    WHERE T1.STAFF_ID = ? AND T1.BE_ID = ? AND UPPER(SCHED_PTC_EXCL_QLFR) = 'PATIENT' AND " +
                    "          (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = '1') AND " +
                    "          (TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T2.CURR_REC_IND = '1') " +
                    " " +
                    "    ORDER BY %s %s " +
                    "   ) R1) ",

                orderByColumn,
                orderByDirection
            );

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, staffId);
            preparedStatement.setString(index, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<FindExclPatientsResult> findExclPatientsResults = new ArrayList<>();

            Response response = new Response();
            response.setData(findExclPatientsResults);
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(orderByDirection);

            while (resultSet.next()) {

                FindExclPatientsResult findExclPatientsResult = new FindExclPatientsResult();

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());
                }

                findExclPatientsResult.setStaffExcludedPatientID(resultSet.getString("STAFF_EXCLUDED_PT_ID"));
                findExclPatientsResult.setScheduleParticipantExclusionSK(BigInteger.valueOf(resultSet.getBigDecimal("SCHED_PTC_EXCL_SK").longValue()));
                findExclPatientsResult.setStaffId(resultSet.getString("STAFF_ID"));
                findExclPatientsResult.setScheduleParticipantExclusionNote(resultSet.getString("SCHED_PTC_EXCL_NOTE"));
                findExclPatientsResult.setPatientSK(BigInteger.valueOf(resultSet.getBigDecimal("PT_SK").longValue()));
                findExclPatientsResult.setPatientFirstName(resultSet.getString("PT_FIRST_NAME"));
                findExclPatientsResult.setPatientMiddleName(resultSet.getString("PT_MIDDLE_NAME"));
                findExclPatientsResult.setPatientLastName(resultSet.getString("PT_LAST_NAME"));
                findExclPatientsResult.setPatientId(resultSet.getString("PT_ID"));
                findExclPatientsResult.setScheduleParticipantExclusionQualifier(resultSet.getString("SCHED_PTC_EXCL_QLFR"));
                findExclPatientsResult.setSchedulePermissionQualifier(resultSet.getString("SCHED_PERM_QLFR"));
                findExclPatientsResult.setBusinessEntityID(resultSet.getString("BE_ID"));
                findExclPatientsResults.add(findExclPatientsResult);
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

    public long validatePatientExists(final String patientId, final String bsnEntId) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT PT_ID,PT_SK,BE_ID " +
                "  FROM PT T1 " +
                " WHERE PT_ID = ? AND BE_ID = ? AND " +
                "       (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, patientId);
            preparedStatement.setString(index++, bsnEntId);


            resultSet = preparedStatement.executeQuery();

            // Expecting one result
            if (resultSet.next()) {

                long sequenceKey = resultSet.getBigDecimal("PT_SK").longValue();
                return sequenceKey;
            }

            return 0;

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

    public long validateStaffExists(final String staffId, final String bsnEntId) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT STAFF_ID,STAFF_SK,BE_ID " +
                "  FROM STAFF T1 " +
                "WHERE STAFF_ID = ? AND BE_ID = ? AND " +
                "       (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1') ";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, staffId);
            preparedStatement.setString(index++, bsnEntId);


            resultSet = preparedStatement.executeQuery();

            // Expecting one result
            if (resultSet.next()) {

                long sequenceKey = resultSet.getBigDecimal("STAFF_SK").longValue();
                return sequenceKey;
            }

            return 0;

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

    public long isPatientExcluded(
        final String patientId,
        final String staffId,
        final String bsnEntId) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            long sequenceKey = validatePatientExists(patientId, bsnEntId);
            if (sequenceKey == 0) {
                // Patient Doesn't Exist, Return 0
                return 0;
            }

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT DISTINCT T1.PT_ID,T1.BE_ID " +
                "  FROM SCHED_PTC_EXCL T1 " +
                "LEFT JOIN (SELECT PT_ID,BE_ID,REC_TERM_TMSTP,CURR_REC_IND " +
                "  FROM PT) T2 " +
                "ON T1.PT_ID = T2.PT_ID AND T1.BE_ID = T2.BE_ID " +
                "WHERE T2.PT_ID = ? AND T1.STAFF_ID = ? AND T1.BE_ID = ? AND UPPER(SCHED_PTC_EXCL_QLFR) = 'PATIENT' AND " +
                "       (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = '1') AND " +
                "       (TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T2.CURR_REC_IND = '1') ";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, patientId);
            preparedStatement.setString(index++, staffId);
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            // Expecting one result
            if (resultSet.next()) {

                // This means that the patient is already excluded for the given staff
                return -1;
            }

            return sequenceKey;

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

    public long isStaffExcluded(
        final String patientId,
        final String staffId,
        final String bsnEntId) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            long sequenceKey = validateStaffExists(staffId, bsnEntId);
            if (sequenceKey == 0) {
                // Staff Doesn't Exist, Return 0
                return 0;
            }

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT DISTINCT T1.STAFF_ID,T1.BE_ID " +
                "  FROM SCHED_PTC_EXCL T1 " +
                "LEFT JOIN (SELECT STAFF_ID,BE_ID,REC_TERM_TMSTP,CURR_REC_IND " +
                "  FROM STAFF) T2 " +
                "ON T1.STAFF_ID = T2.STAFF_ID AND T1.BE_ID = T2.BE_ID " +
                "WHERE T1.PT_ID = ? AND T2.STAFF_ID = ? AND T1.BE_ID = ? AND UPPER(SCHED_PTC_EXCL_QLFR) = 'STAFF' AND " +
                "       (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = '1') AND " +
                "       (TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T2.CURR_REC_IND = '1') ";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, patientId);
            preparedStatement.setString(index++, staffId);
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            // Expecting one result
            if (resultSet.next()) {

                // This means that the patient is already excluded for the given staff
                return -1;
            }

            return sequenceKey;

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

    public Response distinctFirstNames(
        final String bsnEntId,
        final int page,
        final int pageSize) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (" +
                "" +
                "  SELECT * FROM (" +
                "" +
                "      (SELECT DISTINCT STAFF_FIRST_NAME FROM STAFF" +
                "          WHERE STAFF_FIRST_NAME IS NOT NULL AND" +
                "          BE_ID = ? ORDER BY STAFF_FIRST_NAME ASC)" +
                "  )" +
                "" +
                ") R1)" +
                "" +
                "WHERE ROW_NUMBER BETWEEN %d AND %d", fromRow, toRow);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<DistinctColumn> distinctColumns = new ArrayList<>();
            Response response = new Response();
            response.setData(distinctColumns);

            while (resultSet.next()) {

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());
                }

                DistinctColumn distinctColumn = new DistinctColumn();
                distinctColumn.setResult(resultSet.getString("STAFF_FIRST_NAME"));
                distinctColumns.add(distinctColumn);
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

    public Response getStaffRelationship(final String bsnEntId, final String relType) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT DISTINCT T1.ADMIN_STAFF_SK,T1.ADMIN_STAFF_ID,T1.ADMIN_STAFF_FIRST_NAME,T1.ADMIN_STAFF_LAST_NAME, " +
                    "          T2.ADMIN_STAFF_ROLE_XREF_SK,T2.ADMIN_STAFF_ROLE_NAME " +
                    "  FROM ADMIN_STAFF T1 " +
                    "INNER JOIN (SELECT BE_ID, ADMIN_STAFF_ID,ADMIN_STAFF_ROLE_NAME,REC_TERM_TMSTP,CURR_REC_IND, " +
                    "              ADMIN_STAFF_ROLE_EFF_DATE,ADMIN_STAFF_ROLE_TERM_DATE,ADMIN_STAFF_ROLE_XREF_SK " +
                    "  FROM ADMIN_STAFF_ROLE_XREF " +
                    "    WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1 " +
                    "      AND CAST(SYS_EXTRACT_UTC(CURRENT_TIMESTAMP) AS DATE) " +
                    "      BETWEEN ADMIN_STAFF_ROLE_EFF_DATE AND ADMIN_STAFF_ROLE_TERM_DATE) T2 " +
                    "ON T1.ADMIN_STAFF_ID = T2.ADMIN_STAFF_ID AND T1.BE_ID = T2.BE_ID " +
                    "WHERE T1.BE_ID = ? AND UPPER(T2.ADMIN_STAFF_ROLE_NAME) = ? " +
                    "  AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1) " +
                    "ORDER BY UPPER(T1.ADMIN_STAFF_LAST_NAME) ASC ";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, relType.toUpperCase());

            resultSet = preparedStatement.executeQuery();

            List<AdminStaffRelTypeResponse> adminStaffRelTypeResponseList = new ArrayList<>();
            Response response = new Response();
            response.setData(adminStaffRelTypeResponseList);

            while (resultSet.next()) {
                AdminStaffRelTypeResponse adminStaffRelTypeResponse = new AdminStaffRelTypeResponse();
                adminStaffRelTypeResponse.setAdministrativeStaffSK(resultSet.getBigDecimal("ADMIN_STAFF_SK").toBigInteger());
                adminStaffRelTypeResponse.setAdministrativeStaffRoleCrossReferenceSK(resultSet.getBigDecimal("ADMIN_STAFF_ROLE_XREF_SK").toBigInteger());
                adminStaffRelTypeResponse.setAdministrativeStaffID(resultSet.getString("ADMIN_STAFF_ID"));
                adminStaffRelTypeResponse.setAdministrativeStaffFirstName(resultSet.getString("ADMIN_STAFF_FIRST_NAME"));
                adminStaffRelTypeResponse.setAdministrativeStaffLastName(resultSet.getString("ADMIN_STAFF_LAST_NAME"));
                adminStaffRelTypeResponse.setAdministrativeStaffRoleName(resultSet.getString("ADMIN_STAFF_ROLE_NAME"));
                adminStaffRelTypeResponseList.add(adminStaffRelTypeResponse);
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

    private Response getStaffParentRelationship(String bsnEntId, String relType, String staffId)
        throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT DISTINCT T1.ADMIN_STAFF_SK,T1.ADMIN_STAFF_ID,T1.ADMIN_STAFF_FIRST_NAME,T1.ADMIN_STAFF_LAST_NAME, " +
                "          T2.ADMIN_STAFF_ROLE_XREF_SK,T2.ADMIN_STAFF_ROLE_NAME, " +
                "          T4.STAFF_ID,T4.STAFF_FIRST_NAME,T4.STAFF_LAST_NAME " +
                "  FROM ADMIN_STAFF T1 " +
                " " +
                "INNER JOIN (SELECT BE_ID, ADMIN_STAFF_ID,ADMIN_STAFF_ROLE_XREF_SK,ADMIN_STAFF_ROLE_NAME,REC_TERM_TMSTP,CURR_REC_IND, " +
                "              ADMIN_STAFF_ROLE_EFF_DATE,ADMIN_STAFF_ROLE_TERM_DATE " +
                "  FROM ADMIN_STAFF_ROLE_XREF " +
                "    WHERE TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                "      BETWEEN ADMIN_STAFF_ROLE_EFF_DATE AND ADMIN_STAFF_ROLE_TERM_DATE " +
                "        AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2 " +
                "ON T1.ADMIN_STAFF_ID = T2.ADMIN_STAFF_ID AND T1.BE_ID = T2.BE_ID " +
                " " +
                "INNER JOIN (SELECT BE_ID,ADMIN_STAFF_ID,STAFF_ID,REC_TERM_TMSTP,CURR_REC_IND, " +
                "              ADMIN_STAFF_STAFF_EFF_DATE,ADMIN_STAFF_STAFF_TERM_DATE " +
                "  FROM ADMIN_STAFF_STAFF_XREF " +
                "    WHERE TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                "      BETWEEN ADMIN_STAFF_STAFF_EFF_DATE AND ADMIN_STAFF_STAFF_TERM_DATE " +
                "        AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T3 " +
                "ON T1.BE_ID = T3.BE_ID AND T1.ADMIN_STAFF_ID = T3.ADMIN_STAFF_ID " +
                " " +
                "INNER JOIN (SELECT BE_ID,STAFF_ID,STAFF_FIRST_NAME,STAFF_LAST_NAME,REC_TERM_TMSTP,CURR_REC_IND,STAFF_TERM_DATE " +
                "  FROM STAFF " +
                "    WHERE STAFF_TERM_DATE IS NULL AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T4 " +
                "ON T3.BE_ID = T4.BE_ID AND T3.STAFF_ID = T4.STAFF_ID " +
                " " +
                "WHERE T1.BE_ID = ? AND T4.STAFF_ID = ? AND UPPER(T2.ADMIN_STAFF_ROLE_NAME) = ? " +
                "  AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1) " +
                "ORDER BY UPPER(T1.ADMIN_STAFF_LAST_NAME) ASC ";

            preparedStatement = connection.prepareStatement(sql);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String currentDate = dateFormat.format(new Date());

            int index = 1;
            preparedStatement.setString(index++, currentDate);
            preparedStatement.setString(index++, currentDate);
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, staffId);
            preparedStatement.setString(index++, relType.toUpperCase());

            resultSet = preparedStatement.executeQuery();

            List<AdminStaffParentRelTypeResponse> adminStaffParentRelTypeResponseList = new ArrayList<>();
            Response response = new Response();
            response.setData(adminStaffParentRelTypeResponseList);

            while (resultSet.next()) {
                AdminStaffParentRelTypeResponse adminStaffParentRelTypeResponse = new AdminStaffParentRelTypeResponse();

                adminStaffParentRelTypeResponse.setAdministrativeStaffSK(resultSet.getBigDecimal("ADMIN_STAFF_SK").toBigInteger());
                adminStaffParentRelTypeResponse.setAdministrativeStaffRelationshipSK(resultSet.getBigDecimal("ADMIN_STAFF_ROLE_XREF_SK").toBigInteger());
                adminStaffParentRelTypeResponse.setAdministrativeStaffID(resultSet.getString("ADMIN_STAFF_ID"));
                adminStaffParentRelTypeResponse.setAdministrativeStaffFirstName(resultSet.getString("ADMIN_STAFF_FIRST_NAME"));
                adminStaffParentRelTypeResponse.setAdministrativeStaffLastName(resultSet.getString("ADMIN_STAFF_LAST_NAME"));
                adminStaffParentRelTypeResponse.setAdministrativeStaffRelationshipTypeName(resultSet.getString("ADMIN_STAFF_ROLE_NAME"));
                adminStaffParentRelTypeResponse.setStaffId(resultSet.getString("STAFF_ID"));
                adminStaffParentRelTypeResponse.setStaffFirstName(resultSet.getString("STAFF_FIRST_NAME"));
                adminStaffParentRelTypeResponse.setStaffLastName(resultSet.getString("STAFF_LAST_NAME"));

                adminStaffParentRelTypeResponseList.add(adminStaffParentRelTypeResponse);
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

    public Response getStaffAdmins(final String bsnEntId, final String staffId, final String position) throws SandataRuntimeException {
        if (!StringUtil.IsNullOrEmpty(staffId)) {
            return getStaffParentRelationship(bsnEntId, position, staffId);
        }

        return getStaffRelationship(bsnEntId, position);
    }

    //ToDo - To be depracated
    public Response getStaffManagers(final String bsnEntId, final String staffId) throws SandataRuntimeException {
        if (!StringUtil.IsNullOrEmpty(staffId)) {
            return getStaffParentRelationship(bsnEntId, "Manager", staffId);
        }

        return getStaffRelationship(bsnEntId, "Manager");
    }

    //ToDo - To be depracated
    public Response getStaffCoordinators(final String bsnEntId, final String staffId) throws SandataRuntimeException {
        if (!StringUtil.IsNullOrEmpty(staffId)) {
            return getStaffParentRelationship(bsnEntId, "Coordinator", staffId);
        }

        return getStaffRelationship(bsnEntId, "Coordinator");
    }

    //ToDo - To be depracated
    public Response getStaffNurses(final String bsnEntId, final String staffId) throws SandataRuntimeException {
        if (!StringUtil.IsNullOrEmpty(staffId)) {
            return getStaffParentRelationship(bsnEntId, "Nurse", staffId);
        }

        return getStaffRelationship(bsnEntId, "Nurse");
    }

    public Response distinctLastNames(
        final String bsnEntId,
        final int page,
        final int pageSize) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (" +
                "" +
                "  SELECT * FROM (" +
                "" +
                "      (SELECT DISTINCT STAFF_LAST_NAME FROM STAFF" +
                "          WHERE STAFF_LAST_NAME IS NOT NULL AND" +
                "          BE_ID = ? ORDER BY STAFF_LAST_NAME ASC)" +
                "  )" +
                "" +
                ") R1)" +
                "" +
                "WHERE ROW_NUMBER BETWEEN %d AND %d", fromRow, toRow);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<DistinctColumn> distinctColumns = new ArrayList<>();
            Response response = new Response();
            response.setData(distinctColumns);

            while (resultSet.next()) {

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());
                }

                DistinctColumn distinctColumn = new DistinctColumn();
                distinctColumn.setResult(resultSet.getString("STAFF_LAST_NAME"));
                distinctColumns.add(distinctColumn);
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
    
    public Object getEntitiesForId(Connection connection, final String sql, final String className, final Object... params) throws SandataRuntimeException {

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
     * Checks if the @staffId is unique and validates the max length.
     *
     * @param staffId  The id to validate.
     * @param bsnEntId The Business Entity ID since the @staffId does not have to be unique across George.
     * @return Return TRUE if the @staffId is valid, otherwise return FALSE.
     */
    public boolean validateStaffId(String staffId, String bsnEntId, SandataLogger logger) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        boolean bResult = true;
        try {

            if (staffId == null) {
                logger.error("validatePatientId: ERROR: StaffID can not be NULL!");
                return false;
            }

            if (staffId.length() > 50) {
                logger.warn(String.format("validatePatientId: [RULE: StaffID Exceeded Max Length (VARCHAR 50): " +
                    "[STAFF_ID=%s]: [MAX_LENGTH=%d]", staffId, staffId.length()));
                return false;
            }

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement("SELECT STAFF_ID FROM STAFF WHERE STAFF_ID = ? AND BE_ID = ?");

            int index = 1;
            preparedStatement.setString(index++, staffId);
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                String sid = resultSet.getString("STAFF_ID");
                bResult = !(sid != null && sid.length() > 0);
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

    public Date getLastDateWorked(Connection connection, long staffSk, String staffID, String bsnEntId) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Date lastDateWorked = null;

        try {

            String sql = "SELECT PKG_STAFF_UTIL.LAST_DATE_WORKED(?,?) as LAST_DATE_WORKED FROM STAFF WHERE STAFF_SK = ?";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, staffID);
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setLong(index, staffSk);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                Timestamp lastDateWorkedTsmp = resultSet.getTimestamp("LAST_DATE_WORKED");
                if (lastDateWorkedTsmp != null) {
                    lastDateWorked = new Date(lastDateWorkedTsmp.getTime());
                }
            }

            return lastDateWorked;

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

    public Date getVerifiedStartDate(StaffExt staff) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT PKG_STAFF_UTIL.VERIFIED_START_DATE(?) AS VERIFIED_START_DATE " +
                            " FROM STAFF WHERE STAFF_SK = ? AND BE_ID = ?";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, staff.getStaffID());
            preparedStatement.setLong(index++, staff.getStaffSK().longValue());
            preparedStatement.setString(index++, staff.getBusinessEntityID());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                Timestamp verifiedStartDate = resultSet.getTimestamp("VERIFIED_START_DATE");
                if (verifiedStartDate != null) {
                    return new Date(verifiedStartDate.getTime());
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

    public List<Staff> getStaffHistoryWithSort(String staffId, String bsnEntId, String sortOn, String direction)
        throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT * FROM STAFF WHERE STAFF_ID = ? AND BE_ID = ? AND CURR_REC_IND = 1" +
                " ORDER BY %s %s", sortOn, direction);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, staffId);
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<Staff> resultList =
                (List<Staff>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.Staff");

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

    public com.sandata.lab.rest.staff.model.StaffHiringHistory getStaffHiringHistoryWithSort(
            String staffId, String bsnEntId, String sortOn, String direction)
            throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT " +
                    "  CAST(PKG_STAFF_UTIL.HIRE_DATE(?, ?) AS DATE) AS HIRE_DATE, " +
                    "  CAST(PKG_STAFF_UTIL.REHIRE_DATE(?, ?) AS DATE) AS REHIRE_DATE, " +
                    "  PKG_STAFF_UTIL.LAST_DATE_WORKED(?, ?) AS LAST_WORK_DATE " +
                    "FROM DUAL";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, staffId);
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, staffId);
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, staffId);
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            com.sandata.lab.rest.staff.model.StaffHiringHistory staffHiringHistory = null;
            if (resultSet.next()) {

                staffHiringHistory = new com.sandata.lab.rest.staff.model.StaffHiringHistory();

                Timestamp hireDate = resultSet.getTimestamp("HIRE_DATE");
                if (hireDate != null) {
                    staffHiringHistory.setHireDate(new Date(hireDate.getTime()));
                }

                Timestamp rehireDate = resultSet.getTimestamp("REHIRE_DATE");
                if (rehireDate != null) {
                    staffHiringHistory.setRehireDate(new Date(rehireDate.getTime()));
                }

                Timestamp lastWorkDate = resultSet.getTimestamp("LAST_WORK_DATE");
                if (lastWorkDate != null) {
                    staffHiringHistory.setLastDateWorked(new Date(lastWorkDate.getTime()));
                }

                resultSet.close();
                preparedStatement.close();

                index = 1;
                sql = String.format("SELECT DISTINCT " +
                        "          T1.STAFF_EMPLT_STATUS_TYP_NAME, " +
                        "          T1.CHANGE_REASON_CODE, " +
                        "          T1.CHANGE_REASON_MEMO, " +
                        "          T1.REC_EFF_TMSTP, " +
                        "          T1.REC_CREATE_TMSTP, " +
                        "          T1.REC_UPDATE_TMSTP, " +
                        "          T1.REC_CREATED_BY, " +
                        "          T1.REC_UPDATED_BY " +
                        "FROM STAFF T1 " +
                        "WHERE STAFF_ID = ?  " +
                        "  AND BE_ID = ?  " +
                        "  AND CURR_REC_IND = 1 " +
                        "ORDER BY %s %s",
                        sortOn, direction
                );

                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(index++, staffId);
                preparedStatement.setString(index++, bsnEntId);

                resultSet = preparedStatement.executeQuery();

                List<StaffEmploymentStatusChange> resultList = new ArrayList<>();
                while (resultSet.next()) {
                    StaffEmploymentStatusChange employmentStatusChange = new StaffEmploymentStatusChange();

                    employmentStatusChange.setEmploymentStatus(resultSet.getString("STAFF_EMPLT_STATUS_TYP_NAME"));
                    employmentStatusChange.setReasonCode(resultSet.getString("CHANGE_REASON_CODE"));
                    employmentStatusChange.setNotesForChange(resultSet.getString("CHANGE_REASON_MEMO"));

                    Timestamp effectiveTimestamp = resultSet.getTimestamp("REC_EFF_TMSTP");
                    if (effectiveTimestamp != null) {
                        employmentStatusChange.setEffectiveDate(new Date(effectiveTimestamp.getTime()));
                    }

                    Timestamp createdTimestamp = resultSet.getTimestamp("REC_CREATE_TMSTP");
                    if (createdTimestamp != null) {
                        employmentStatusChange.setCreatedDate(new Date(createdTimestamp.getTime()));
                    }

                    Timestamp updatedTimestamp = resultSet.getTimestamp("REC_UPDATE_TMSTP");
                    if (updatedTimestamp != null) {
                        employmentStatusChange.setUpdatedDate(new Date(updatedTimestamp.getTime()));
                    }

                    employmentStatusChange.setCreatedBy(resultSet.getString("REC_CREATED_BY"));
                    employmentStatusChange.setUpdatedBy(resultSet.getString("REC_UPDATED_BY"));

                    resultList.add(employmentStatusChange);
                }

                staffHiringHistory.setEmploymentStatusChange(resultList);

                return staffHiringHistory;
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

    public List<StaffEmploymentStatusHistory> getStaffEmploymentStatusHistoryWithSort(
            String staffId, String bsnEntId, String sortOn, String direction, SandataLogger logger)
            throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String orderBy = "STAFF_EMPLT_STATUS_CHANGE_DATE"; //Default

        if (!StringUtil.IsNullOrEmpty(sortOn)) {
            switch (sortOn) {
                case "EmploymentStatusChangeDate":
                    orderBy = "STAFF_EMPLT_STATUS_CHANGE_DATE";
                    break;
                case "Status":
                    orderBy = "UPPER(STAFF_EMPLT_STATUS_TYP_NAME)";
                    break;
                case "ReasonCode":
                    orderBy = "UPPER(CHANGE_REASON_CODE)";
                    break;
                case "Notes":
                    orderBy = "UPPER(CHANGE_REASON_MEMO)";
                    break;
                case "Modified":
                    orderBy = "REC_UPDATE_TMSTP";
                    break;
                case "ModifiedBy":
                    orderBy = "UPPER(REC_UPDATED_BY)";
                    break;
            }
        }

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT STAFF_SK FROM STAFF " +
                    //"  WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') <> '9999-12-31' " +
                    "    WHERE STAFF_ID = ? " +
                    "    AND BE_ID = ? " +
                    "ORDER BY %s %s", orderBy, direction);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, staffId);
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<StaffEmploymentStatusHistory> staffEmploymentStatusHistoryList = new ArrayList<>();

            while (resultSet.next()) {

                long staffSk = resultSet.getBigDecimal("STAFF_SK").toBigInteger().longValue();
                StaffEmploymentStatusHistory staffEmploymentStatusHistory = getMetadataStaffEmplHist(bsnEntId, staffSk, logger);

                if (staffEmploymentStatusHistory != null) {

                    staffEmploymentStatusHistory.setStaffId(staffId);
                    staffEmploymentStatusHistoryList.add(staffEmploymentStatusHistory);

                } else {
                    logger.error(String.format("%s: getStaffEmploymentStatusHistoryWithSort: [BE_ID=%s]: [STAFF_SK=%d]: " +
                                "staffEmploymentStatusHistory == null", getClass().getSimpleName(), bsnEntId, staffSk));
                }
            }

            return staffEmploymentStatusHistoryList;

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

    private StaffEmploymentStatusHistory getMetadataStaffEmplHist(String bsnEntId, long staffSk, SandataLogger logger) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String auditHostKey = String.format("%s|%d", bsnEntId, staffSk);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(true);

            String sql = "SELECT T1.DATA_SEC_CLAS_ID,T1.APP_DATA_STRUC_ELT_VAL,T2.USER_NAME " +
                            "FROM app_audit T1 " +
                            "LEFT JOIN (SELECT USER_NAME,USER_GUID " +
                            "  FROM APP_USER) T2 " +
                            "ON T1.USER_GUID = T2.USER_GUID " +
                            "WHERE audit_host = ?";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, auditHostKey);

            resultSet = preparedStatement.executeQuery();

            StaffEmploymentStatusHistory staffEmploymentStatusHistory = null;
            while (resultSet.next()) {
                if (staffEmploymentStatusHistory == null) {
                    staffEmploymentStatusHistory = new StaffEmploymentStatusHistory();
                    staffEmploymentStatusHistory.setStaffSk(BigInteger.valueOf(staffSk));
                    staffEmploymentStatusHistory.setBusinessEntityId(bsnEntId);
                }

                String property = resultSet.getString("DATA_SEC_CLAS_ID");
                String value = resultSet.getString("APP_DATA_STRUC_ELT_VAL");
                staffEmploymentStatusHistory.setModifiedBy(resultSet.getString("USER_NAME"));

                switch (property) {
                    case "EffectiveDate":
                        try {
                            staffEmploymentStatusHistory.setEmploymentStatusChangeDate(dateFormat.parse(value));
                        } catch (Exception e) {
                            logger.error(String.format("%s: getMetadataStaffEmplHist: EffectiveDate: Value=%s: ERROR: %s",
                                            getClass().getSimpleName(), value, e.getMessage()));
                        }
                        break;
                    case "Status":
                        staffEmploymentStatusHistory.setStatus(value);
                        break;
                    case "ReasonCode":
                        staffEmploymentStatusHistory.setReasonCode(value);
                        break;
                    case "Notes":
                        staffEmploymentStatusHistory.setNotes(value);
                        break;
                    case "Modified":
                        try {
                            staffEmploymentStatusHistory.setModified(dateFormat.parse(value));
                        } catch (Exception e) {
                            logger.error(String.format("%s: getMetadataStaffEmplHist: Modified: Value=%s: ERROR: %s",
                                    getClass().getSimpleName(), value, e.getMessage()));
                        }
                        break;
                }
            }

            return staffEmploymentStatusHistory;

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

        }  finally {

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

    public List<StaffComplianceExt> getStaffCompWithSort(String staffId, String bsnEntId, String sortOn, String direction,
                                                         int page, int pageSize) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        //Default value
        String orderByString = "SC.REC_CREATE_TMSTP";  //need to use prefix SC to prevent ambiguous column

        if (!StringUtil.IsNullOrEmpty(sortOn)) {
            switch (sortOn) {
                case "ComplianceCategoryName":
                    orderByString = "UPPER(CATEGORY_NAME)";
                    break;
                case "ComplianceName":
                    orderByString = "UPPER(COMP_NAME)";
                    break;
                case "RecordCreateTimestamp":
                    orderByString = "SC.REC_CREATE_TMSTP";
                    break;
                case "RecordUpdateTimestamp":
                    orderByString = "SC.REC_UPDATE_TMSTP";
                    break;
                case "RecordEffectiveTimestamp":
                    orderByString = "SC.REC_EFF_TMSTP";
                    break;
                case "RecordTerminationTimestamp":
                    orderByString = "SC.REC_TERM_TMSTP";
                    break;
                case "ComplianceCode":
                    orderByString = "UPPER(SC.COMP_CODE)";
                    break;
                case "ComplianceReadingValue":
                    orderByString = "UPPER(SC.COMP_RDNG_VAL)";
                    break;
                case "StaffComplianceReceivedDate":
                    orderByString = "SC.STAFF_COMP_RCVD_DATE";
                    break;
                case "StaffComplianceExpirationDate":
                    orderByString = "SC.STAFF_COMP_EXPR_DATE";
                    break;
                case "StaffComplianceNote":
                    orderByString = "UPPER(SC.STAFF_COMP_NOTE)";
                    break;
            }
        }

        List<StaffComplianceExt> staffComplianceExtList = new ArrayList<>();
        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format(
                "SELECT * FROM " +
                    "  (" +
                    "    SELECT ROWNUM ROW_NUM, COUNT(*) OVER() TOTAL_ROWS, r1.* FROM " +
                    "    (" +
                    "      SELECT BCCL.COMP_CTGY_NAME CATEGORY_NAME, BCL.COMP_NAME, SC.* " +
                    "      FROM STAFF_COMP SC " +
                    "          LEFT JOIN BE_COMP_LKUP BCL ON SC.BE_ID = BCL.BE_ID AND SC.COMP_CODE = BCL.COMP_CODE " +
                    "          LEFT JOIN BE_COMP_CTGY_LKUP BCCL ON BCL.BE_ID = BCCL.BE_ID AND BCL.COMP_CTGY_CODE = BCCL.COMP_CTGY_CODE " +
                    "      WHERE SC.STAFF_ID = ? AND SC.BE_ID = ? " +
                    "          AND (TO_CHAR(SC.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND SC.CURR_REC_IND = 1) " +
                    "          AND (BCL.REC_TERM_TMSTP IS NULL OR (TO_CHAR(BCL.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND BCL.CURR_REC_IND = 1)) " +
                    "          AND (BCCL.REC_TERM_TMSTP IS NULL OR (TO_CHAR(BCCL.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND BCCL.CURR_REC_IND = 1)) " +
                    "      ORDER BY %s %s " +
                    "    ) r1" +
                    "  )" +
                    "WHERE ROW_NUM BETWEEN %d AND %d"
                , orderByString, direction, fromRow, toRow);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, staffId);
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                StaffCompliance staffCompliance = (StaffCompliance) new DataMapper().mapWithOffsetEntityNext(
                    resultSet,
                    "com.sandata.lab.data.model.dl.model.StaffCompliance",
                    4);

                StaffComplianceExt staffComplianceExt = new StaffComplianceExt(staffCompliance);
                staffComplianceExt.setComplianceCategoryName(resultSet.getString("CATEGORY_NAME"));
                staffComplianceExt.setComplianceName(resultSet.getString("COMP_NAME"));

                //TODO GET VALUE FROM DB
                staffComplianceExt.setCompliant(true);

                staffComplianceExtList.add(staffComplianceExt);


            }

            return staffComplianceExtList;

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

    public List<StaffPatientHistory> getStaffPatientHist(String staffId, String patientId, String bsnEntId) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM STAFF_PT_HIST WHERE STAFF_ID = ? AND PT_ID = ? AND BE_ID = ?" +
                " AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, staffId);
            preparedStatement.setString(index++, patientId);
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<StaffPatientHistory> resultList =
                (List<StaffPatientHistory>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.StaffPatientHistory");

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

    public String getStaffPhone(String staffId, String bsnEntId) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM STAFF_CONT_PHONE WHERE STAFF_ID = ? AND BE_ID = ? AND STAFF_PHONE_PRMY_IND = 1" +
                " AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, staffId);
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<StaffContactPhone> resultList =
                (List<StaffContactPhone>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.StaffContactPhone");

            if (resultList.size() > 0) {
                return resultList.get(0).getStaffPhone(); // Should only have one Primary phone number
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
     * Returns completed in service training hours by Staff
     *
     * @param bsnEntId
     * @param staffId
     * @return
     */
    public BigDecimal getStaffTrngCompletedInServiceHours(String bsnEntId, String staffId) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "Select * from Staff T4" +
                " Left JOIN( " +
                " Select SUM(T1.STAFF_TRNG_TOTAL_HRS) AS TOTAL_HOURS, T2.BE_ID, T2.STAFF_ID FROM BE_STAFF_TRNG_LKUP T1 " +
                " LEFT JOIN STAFF_TRNG T2 ON T1.BE_ID = T2.BE_ID AND T1.STAFF_TRNG_CODE = T2.STAFF_TRNG_CODE AND " +
                " TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = '1' AND " +
                " TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T2.CURR_REC_IND = '1' AND" +
                " UPPER(T2.STAFF_TRNG_RESULT_CODE) = ? " +
                " GROUP BY T2.BE_ID, T2.STAFF_ID)T1 ON T4.STAFF_ID = T1.STAFF_ID AND T4.BE_ID = T1.BE_ID " +
                " WHERE T4.STAFF_ID = ? AND T4.BE_ID = ?";


            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, InServiceTrainingResult.COMPLETED.toString().toUpperCase());
            preparedStatement.setString(2, staffId);
            preparedStatement.setString(3, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            BigDecimal hours = null;

            while (resultSet.next()) {
                hours = resultSet.getBigDecimal("TOTAL_HOURS");
            }

            return hours;

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
     * Returns required in service training hours by staffPositionName
     *
     * @param bsnEntId
     * @param staffPositionName
     * @return
     */
    public BigDecimal getStaffTrngRequiredInServiceHours(String bsnEntId, ServiceName staffPositionName) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT SUM(STAFF_TRNG_CTGY_RQD_HRS) AS REQUIRED_HOURS"
                + " FROM STAFF_TRNG_CTGY_SVC T1"
                + " INNER JOIN BE_STAFF_TRNG_CTGY_LKUP T2"
                + " ON T2.BE_ID                                  =T1.BE_ID"
                + " AND T2.STAFF_TRNG_CTGY_CODE                  =T1.STAFF_TRNG_CTGY_CODE"
                + " AND (TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD')='9999-12-31'"
                + " AND T2.CURR_REC_IND                          =1)"
                + " WHERE T1.BE_ID                               =?"
                + " AND UPPER(T1.SVC_NAME)                              =?"
                + " AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD')='9999-12-31'"
                + " AND T1.CURR_REC_IND                          =1)";

            preparedStatement = connection.prepareStatement(sql);

            if (staffPositionName == null) {
                return null;
            }

            String staffPositionNameString = staffPositionName.value().toUpperCase();

            preparedStatement.setString(1, bsnEntId);
            preparedStatement.setString(2, staffPositionNameString);

            resultSet = preparedStatement.executeQuery();

            BigDecimal hours = null;

            while (resultSet.next()) {
                hours = resultSet.getBigDecimal("REQUIRED_HOURS");
            }

            return hours;

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
     * Returns compliant indicator for business entity ID and staff ID.
     *
     * @param bsnEntId
     * @param staffId
     * @return
     */
    public boolean getStaffCompliantIndicator(String bsnEntId, String staffId) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = " SELECT COMP_IND"
                + " FROM STAFF_COMP_SUMMARY"
                + " WHERE BE_ID                               =?"
                + " AND STAFF_ID                              =?"
                + " AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD')='9999-12-31'"
                + " AND CURR_REC_IND                          = 1)";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, bsnEntId);
            preparedStatement.setString(2, staffId);

            resultSet = preparedStatement.executeQuery();

            boolean sompliantIndicator = false;

            while (resultSet.next()) {
                sompliantIndicator = resultSet.getBoolean("COMP_IND");
            }

            return sompliantIndicator;

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
     * Returns Response containing List of StaffTrainingLocation for the specified
     * business entity ID, staff training code, page, page size,
     * sort on table, and sort direction.
     *
     * @param bsnEntId      Specified business entity ID.
     * @param staffTrngCode Specified staff training code.
     * @param page          Specified page number.
     * @param pageSize      Specified page size.
     * @param sortOn        Specified sort column.
     * @param direction     Specified sort direction.
     * @return Response with paging info and List of StaffTrainingLocation.
     */
    public Response getStaffTrngLocationForStaffTrngCode(String bsnEntId,
                                                         String staffTrngCode,
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

            // Build SQL query.
            String sql = String.format("SELECT * FROM"
                    + "  (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ("
                    + "    SELECT * FROM (SELECT * FROM ("
                    + "      SELECT * FROM STAFF_TRNG_LOC WHERE STAFF_TRNG_LOC.BE_ID=?"
                    + "        AND (SELECT COUNT(*) FROM STAFF_TRNG_CLS_EVNT"
                    + "              WHERE STAFF_TRNG_CLS_EVNT.BE_ID=STAFF_TRNG_LOC.BE_ID"
                    + "                  AND STAFF_TRNG_CLS_EVNT.STAFF_TRNG_LOC_NAME = STAFF_TRNG_LOC.STAFF_TRNG_LOC_NAME"
                    + "                  AND STAFF_TRNG_CLS_EVNT.STAFF_TRNG_CODE=?"
                    + "                  AND (TO_CHAR(STAFF_TRNG_CLS_EVNT.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND STAFF_TRNG_CLS_EVNT.CURR_REC_IND = '1')) > 0"
                    + "        AND (TO_CHAR(STAFF_TRNG_LOC.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND STAFF_TRNG_LOC.CURR_REC_IND = '1')"
                    + "ORDER BY STAFF_TRNG_LOC.%s %s))) R1) WHERE ROW_NUMBER BETWEEN %s AND %s",
                sortOn,
                direction,
                fromRow,
                toRow);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index, staffTrngCode);

            resultSet = preparedStatement.executeQuery();

            // Create Response and set details.
            Response response = new Response();
            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);
            response.setPage(page);
            response.setPageSize(pageSize);

            mapResultSetToStaffTrainingLocation(response, resultSet);

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
     * Maps specified ResultSet to List of StaffTrainingLocation and sets data of specified Response.
     *
     * @param response  Specified Response.
     * @param resultSet Specified ResultSet.
     * @throws SQLException
     */
    private void mapResultSetToStaffTrainingLocation(Response response, ResultSet resultSet) throws SQLException {
        List<StaffTrainingLocation> staffTrainingLocationList = new ArrayList<>();

        while (resultSet.next()) {
            if (response.getTotalRows() == 0) {
                response.setTotalRows(resultSet.getInt("TOTAL_ROWS"));
            }

            StaffTrainingLocation staffTrainingLocation = new StaffTrainingLocation();

            staffTrainingLocation.setStaffTrainingLocationSK(BigInteger.valueOf(resultSet.getBigDecimal("STAFF_TRNG_LOC_SK").longValue()));
            staffTrainingLocation.setRecordCreateTimestamp(resultSet.getTimestamp("REC_CREATE_TMSTP"));
            staffTrainingLocation.setRecordUpdateTimestamp(resultSet.getTimestamp("REC_UPDATE_TMSTP"));
            staffTrainingLocation.setRecordEffectiveTimestamp(resultSet.getTimestamp("REC_EFF_TMSTP"));
            staffTrainingLocation.setRecordTerminationTimestamp(resultSet.getTimestamp("REC_TERM_TMSTP"));
            staffTrainingLocation.setRecordCreatedBy(resultSet.getString("REC_CREATED_BY"));
            staffTrainingLocation.setRecordUpdatedBy(resultSet.getString("REC_UPDATED_BY"));
            staffTrainingLocation.setChangeReasonMemo(resultSet.getString("CHANGE_REASON_MEMO"));
            staffTrainingLocation.setCurrentRecordIndicator(resultSet.getBoolean("CURR_REC_IND"));
            staffTrainingLocation.setChangeVersionID(BigInteger.valueOf(resultSet.getBigDecimal("CHANGE_VERSION_ID").longValue()));
            staffTrainingLocation.setBusinessEntityID(resultSet.getString("BE_ID"));
            staffTrainingLocation.setStaffTrainingLocationName(resultSet.getString("STAFF_TRNG_LOC_NAME"));
            staffTrainingLocation.setStaffTrainingAddressLine1(resultSet.getString("STAFF_TRNG_ADDR1"));
            staffTrainingLocation.setStaffTrainingAddressLine2(resultSet.getString("STAFF_TRNG_ADDR2"));
            staffTrainingLocation.setStaffTrainingCity(resultSet.getString("STAFF_TRNG_CITY"));

            String stateCode = resultSet.getString("STAFF_TRNG_STATE");
            if (stateCode != null) {
                staffTrainingLocation.setStaffTrainingState(StateCode.fromValue(stateCode));
            }
            staffTrainingLocation.setStaffTrainingPostalCode(resultSet.getString("STAFF_TRNG_PSTL_CODE"));
            staffTrainingLocation.setStaffTrainingZip4(resultSet.getString("STAFF_TRNG_ZIP4"));

            staffTrainingLocationList.add(staffTrainingLocation);
        }

        response.setData(staffTrainingLocationList);
    }

    /**
     * Returns List of StaffTrainingClassEvent with the same business entity ID
     * and class name as the specified StaffTrainingLocation.
     *
     * @param staffTrainingLocation Specified StaffTrainingLocation.
     * @return List of StaffTrainingClassEvent.
     */
    public List<StaffTrainingClassEvent> getStaffTrngClassEvntForStaffTrngLocation(StaffTrainingLocation staffTrainingLocation) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM STAFF_TRNG_CLS_EVNT"
                + " WHERE BE_ID=?"
                + " AND STAFF_TRNG_LOC_NAME=?"
                + "(TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

            int index = 1;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(index++, staffTrainingLocation.getBusinessEntityID());
            preparedStatement.setString(index, staffTrainingLocation.getStaffTrainingLocationName());

            resultSet = preparedStatement.executeQuery();

            List<StaffTrainingClassEvent> result = (List<StaffTrainingClassEvent>) new DataMapper()
                .map(resultSet, "com.sandata.lab.data.model.dl.model.StaffTrainingClassEvent");

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
     * Returns Response containing List of StaffTrainingClassEvent
     * for the specified business entity ID, staff training code,
     * page, page size, sort on table, and sort direction.
     *
     * @param bsnEntId         Specified business entity ID.
     * @param staffTrngCode    Specified staff training code.
     * @param staffTrngLocName Specified staff training location name.
     * @param page             Specified page number.
     * @param pageSize         Specified page size.
     * @param sortOn           Specified sort column.
     * @param direction        Specified sort direction.
     * @return Response with paging info and List of StaffTrainingClassEvent.
     */
    public Response getStaffTrngClsEvntForStaffTrngLocName(String bsnEntId,
                                                           String staffTrngCode,
                                                           String staffTrngLocName,
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

            // Build SQL query.
            String sql = String.format("SELECT * FROM " +
                    "  (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (SELECT * FROM (SELECT * FROM (SELECT * FROM STAFF_TRNG_CLS_EVNT " +
                    "  WHERE BE_ID=? " +
                    "  AND STAFF_TRNG_CODE=? " +
                    "  AND STAFF_TRNG_LOC_NAME=? " +
                    "  AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1') ORDER BY %s %s))) R1) WHERE ROW_NUMBER BETWEEN %s AND %s",
                sortOn,
                direction,
                fromRow,
                toRow);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, staffTrngCode);
            preparedStatement.setString(index, staffTrngLocName);

            resultSet = preparedStatement.executeQuery();

            // Create Response and set details.
            Response response = new Response();
            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);
            response.setPage(page);
            response.setPageSize(pageSize);

            mapResultSetToStaffTrainingClassEvent(response, resultSet);

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
     * Returns a Response with data of List of StaffTrainingClassEvent
     * with row count and page details all extracted from the specified ResultSet.
     *
     * @param resultSet Specified ResultSet.
     * @throws SQLException
     */
    private void mapResultSetToStaffTrainingClassEvent(Response response, ResultSet resultSet) throws SQLException {
        List<StaffTrainingClassEvent> staffTrainingClassEventList = new ArrayList<>();

        while (resultSet.next()) {
            if (response.getTotalRows() == 0) {
                response.setTotalRows(resultSet.getInt("TOTAL_ROWS"));
            }

            StaffTrainingClassEvent staffTrainingClassEvent = new StaffTrainingClassEvent();

            staffTrainingClassEvent.setStaffTrainingClassEventSK(BigInteger.valueOf(resultSet.getBigDecimal("STAFF_TRNG_CLS_EVNT_SK").longValue()));
            staffTrainingClassEvent.setRecordCreateTimestamp(resultSet.getTimestamp("REC_CREATE_TMSTP"));
            staffTrainingClassEvent.setRecordUpdateTimestamp(resultSet.getTimestamp("REC_UPDATE_TMSTP"));
            staffTrainingClassEvent.setRecordEffectiveTimestamp(resultSet.getTimestamp("REC_EFF_TMSTP"));
            staffTrainingClassEvent.setRecordTerminationTimestamp(resultSet.getTimestamp("REC_TERM_TMSTP"));
            staffTrainingClassEvent.setRecordCreatedBy(resultSet.getString("REC_CREATED_BY"));
            staffTrainingClassEvent.setRecordUpdatedBy(resultSet.getString("REC_UPDATED_BY"));
            staffTrainingClassEvent.setChangeReasonMemo(resultSet.getString("CHANGE_REASON_MEMO"));
            staffTrainingClassEvent.setCurrentRecordIndicator(resultSet.getBoolean("CURR_REC_IND"));
            staffTrainingClassEvent.setChangeVersionID(BigInteger.valueOf(resultSet.getBigDecimal("CHANGE_VERSION_ID").longValue()));
            staffTrainingClassEvent.setBusinessEntityID(resultSet.getString("BE_ID"));
            staffTrainingClassEvent.setStaffTrainingLocationName(resultSet.getString("STAFF_TRNG_LOC_NAME"));
            staffTrainingClassEvent.setStaffTrainingCode(resultSet.getString("STAFF_TRNG_CODE"));
            staffTrainingClassEvent.setStaffTrainingQualifier(StaffTrainingQualifier.fromValue(resultSet.getString("STAFF_TRNG_QLFR")));
            staffTrainingClassEvent.setStaffTrainingStartDatetime(resultSet.getTimestamp("STAFF_TRNG_START_DTIME"));
            staffTrainingClassEvent.setStaffTrainingMaxAttendance(BigInteger.valueOf(resultSet.getBigDecimal("STAFF_TRNG_MAX_ATTND").longValue()));
            staffTrainingClassEvent.setStaffTrainingInstructorFirstName(resultSet.getString("STAFF_TRNG_INSTR_FIRST_NAME"));
            staffTrainingClassEvent.setStaffTrainingInstructorLastName(resultSet.getString("STAFF_TRNG_INSTR_LAST_NAME"));

            staffTrainingClassEventList.add(staffTrainingClassEvent);

        }

        response.setData(staffTrainingClassEventList);
    }

    /**
     * Returns List of StaffTrainingClassEventAttendance for specified
     * parameters.
     *
     * @param bsnEntId           Specified business entity ID.
     * @param staffTrngCode      Specified staff training code.
     * @param staffTrngLocName   Specified staff training location name.
     * @param staffTrngStartDate Specified staff training start date.
     * @return List of StaffTrainingClassEventAttendance.
     */
    public List<StaffTrainingClassEventAttendance> getStaffTrngClsEvntAttndForClsEvnt(String bsnEntId,
                                                                                      String staffTrngCode,
                                                                                      String staffTrngLocName,
                                                                                      Date staffTrngStartDate) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM STAFF_TRNG_CLS_EVNT_ATTND" +
                "  WHERE BE_ID=?" +
                "  AND STAFF_TRNG_CODE=?" +
                "  AND STAFF_TRNG_LOC_NAME=?" +
                "  AND STAFF_TRNG_START_DTIME=TO_DATE(?, 'MM/DD/YYYY')" +
                "  AND CURR_REC_IND=1" +
                "  AND REC_TERM_TMSTP=TO_DATE('12/31/9999 00:00:00', 'MM/DD/YYYY HH24:MI:SS')";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, staffTrngCode);
            preparedStatement.setString(index++, staffTrngLocName);
            preparedStatement.setString(index, new SimpleDateFormat("MM/dd/yyyy").format(staffTrngStartDate));

            resultSet = preparedStatement.executeQuery();

            List<StaffTrainingClassEventAttendance> result = (List<StaffTrainingClassEventAttendance>) new DataMapper().map(resultSet,
                "com.sandata.lab.data.model.dl.model.StaffTrainingClassEventAttendance");

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
     * Returns List of StaffTrainingClassEventEnrollment for specified
     * parameters.
     *
     * @param bsnEntId           Specified business entity ID.
     * @param staffTrngCode      Specified staff training code.
     * @param staffTrngLocName   Specified staff training location name.
     * @param staffTrngStartDate Specified staff training start date.
     * @return List of StaffTrainingClassEventEnrollment.
     */
    public List<StaffTrainingClassEventEnrollment> getStaffTrngClsEvntEnrolForClsEvnt(String bsnEntId,
                                                                                      String staffTrngCode,
                                                                                      String staffTrngLocName,
                                                                                      Date staffTrngStartDate) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM STAFF_TRNG_CLS_EVNT_ENROL" +
                "  WHERE BE_ID=?" +
                "  AND STAFF_TRNG_CODE=?" +
                "  AND STAFF_TRNG_LOC_NAME=?" +
                "  AND STAFF_TRNG_START_DTIME=TO_DATE(?, 'MM/DD/YYYY HH24:MI:SS')" +
                "  AND CURR_REC_IND=1" +
                "  AND REC_TERM_TMSTP=TO_DATE('12/31/9999 00:00:00', 'MM/DD/YYYY HH24:MI:SS')";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, staffTrngCode);
            preparedStatement.setString(index++, staffTrngLocName);
            preparedStatement.setString(index, new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(staffTrngStartDate));

            resultSet = preparedStatement.executeQuery();

            List<StaffTrainingClassEventEnrollment> result = (List<StaffTrainingClassEventEnrollment>) new DataMapper().map(resultSet,
                "com.sandata.lab.data.model.dl.model.StaffTrainingClassEventEnrollment");

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
     * Get list of compliance items by service for a business entity
     *
     * @param bsnEntId
     * @return
     */
    public Response getStaffTrainingClassesByLocation(String bsnEntId,
                                                      String locationName,
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
                    "   (SELECT * FROM (SELECT * FROM COREDATA.STAFF_TRNG_CLS_EVNT T1 " +
                    "  WHERE T1.BE_ID = ? AND UPPER(T1.STAFF_TRNG_LOC_NAME) = ? " +
                    "  AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = '1') ORDER BY T1.%s %s)) R1)" +
                    "  WHERE ROW_NUMBER BETWEEN %d AND %d",
                sortOn,
                direction,
                fromRow,
                toRow);

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, bsnEntId);
            preparedStatement.setString(2, locationName.toUpperCase());

            resultSet = preparedStatement.executeQuery();


            Response response = new Response();
/*
            List<StaffTrainingClassEvent> staffTrainingClassEvents =
                    (List<StaffTrainingClassEvent>) new DataMapper().mapWithOffset(resultSet, "com.sandata.lab.data.model.dl.model.StaffTrainingClassEvent", 2);
*/
            mapResultSetToStaffTrainingClassEvent(response, resultSet);

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

    /**
     * Returns Response containing List of FindCompSchedStaffResult
     * for the specified parameters.
     *
     * @param bsnEntId                 Specified Business Entity ID.
     * @param staffTrngCode            Specified Staff Training code.
     * @param staffTrngLocName         Specified Staff Training lcoation name.
     * @param staffTrngStartTime Specified time of class.
     * @param employmentStatusTypeName Specified EmploymentStatusTypeName (optional).
     * @param compliant                Specified compliance indicator (optional).
     * @param staffId                  Specified Staff ID (optional).
     * @param firstName                Specified Staff first name (optional).
     * @param lastName                 Specified Staff last name (optional).
     * @param page                     Specified page number.
     * @param pageSize                 Specified page size.
     * @param sortOn                   Specified column from Staff table to sort on.
     * @param direction                Specified direction of sort.
     * @return Response with paging info and List of FindCompSchedStaffResult.
     */
    public Response findStaffForTrainingCategory(String bsnEntId,
                                                 String staffTrngCode,
                                                 String staffTrngLocName,
                                                 Date staffTrngStartTime,
                                                 EmploymentStatusTypeName employmentStatusTypeName,
                                                 Boolean compliant,
                                                 String staffId,
                                                 String firstName,
                                                 String lastName,
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

            List parameterList = new ArrayList();
            StringBuilder sqlStringBuilder = new StringBuilder();

            sqlStringBuilder.append("SELECT * FROM"
                + " (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (SELECT * FROM"
                + "   (SELECT T5.STAFF_SK,"
                + "      T5.STAFF_ID,"
                + "      T5.STAFF_FIRST_NAME, "
                + "      T5.STAFF_MIDDLE_NAME,"
                + "      T5.STAFF_LAST_NAME,"
                + "      T5.STAFF_EMPLT_STATUS_TYP_NAME,"
                + "      T5.STAFF_POSITION_NAME"
                + " FROM STAFF_TRNG_CLS_EVNT T1"
                + "      INNER JOIN BE_STAFF_TRNG_LKUP T2 ON T2.STAFF_TRNG_CODE = T1.STAFF_TRNG_CODE"
                + "        AND T2.BE_ID = T1.BE_ID"
                + "      INNER JOIN BE_STAFF_TRNG_CTGY_LST T3  ON T3.STAFF_TRNG_CODE = T2.STAFF_TRNG_CODE"
                + "        AND T3.BE_ID = T2.BE_ID"
                + "      INNER JOIN STAFF_TRNG_CTGY_SVC T4  ON T4.STAFF_TRNG_CTGY_CODE = T3.STAFF_TRNG_CTGY_CODE"
                + "        AND T4.BE_ID = T3.BE_ID"
                + "      INNER JOIN STAFF T5 ON T5.STAFF_POSITION_NAME = T4.SVC_NAME"
                + "        AND T5.BE_ID = T4.BE_ID"
                + "      LEFT JOIN STAFF_COMP_SUMMARY T6 ON T6.BE_ID = T5.BE_ID"
                + "        AND T6.STAFF_ID = T5.STAFF_ID"
                + "    WHERE T1.BE_ID=?"
                + "      AND T1.STAFF_TRNG_CODE=?"
                + "      AND T1.STAFF_TRNG_LOC_NAME=?"
                + "      AND T1.STAFF_TRNG_START_DTIME=TO_DATE(?, 'YYYY-MM-DD HH24:MI')"
                + "      AND (SELECT COUNT(*) FROM STAFF_TRNG_CLS_EVNT_ENROL T7"
                + "              INNER JOIN BE_STAFF_TRNG_LKUP T8 ON T8.BE_ID = T7.BE_ID"
                + "                AND T8.STAFF_TRNG_CODE = T7.STAFF_TRNG_CODE"
                + "           WHERE T7.BE_ID=T1.BE_ID"
                + "             AND T7.STAFF_ID = T5.STAFF_ID"
                + "             AND T7.STAFF_TRNG_DROP_IND = 0"
                + "             AND (T7.STAFF_TRNG_START_DTIME BETWEEN T1.STAFF_TRNG_START_DTIME AND T1.STAFF_TRNG_START_DTIME + T2.STAFF_TRNG_TOTAL_HRS/24"
                + "                OR T7.STAFF_TRNG_START_DTIME + T8.STAFF_TRNG_TOTAL_HRS/24 BETWEEN T1.STAFF_TRNG_START_DTIME AND T1.STAFF_TRNG_START_DTIME + T2.STAFF_TRNG_TOTAL_HRS/24)"
                + "                AND (TO_CHAR(T7.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T7.CURR_REC_IND = '1')"
                + "                AND (TO_CHAR(T8.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T8.CURR_REC_IND = '1')) = 0");

            parameterList.add(bsnEntId);
            parameterList.add(staffTrngCode);
            parameterList.add(staffTrngLocName);
            parameterList.add(ORACLE_DATE_TIME_FORMAT.format(staffTrngStartTime));

            // Build optional parameters.
            if (employmentStatusTypeName != null) {
                sqlStringBuilder.append(" AND UPPER(T5.STAFF_EMPLT_STATUS_TYP_NAME)=?");
                parameterList.add(employmentStatusTypeName.value().toUpperCase());
            }

            if (compliant != null) {
                sqlStringBuilder.append(" AND T6.COMP_IND=?");
                sqlStringBuilder.append(" AND (TO_CHAR(T6.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T6.CURR_REC_IND = '1')");
                parameterList.add(compliant);
            }

            if (staffId != null
                && !staffId.isEmpty()) {
                sqlStringBuilder.append(" AND T5.STAFF_ID=?");
                parameterList.add(staffId);
            }

            if (firstName != null
                && !firstName.isEmpty()) {
                sqlStringBuilder.append(" AND UPPER(T5.STAFF_FIRST_NAME) LIKE UPPER(?)");
                parameterList.add(String.format("%s%%", firstName));
            }

            if (lastName != null
                && !lastName.isEmpty()) {
                sqlStringBuilder.append(" AND UPPER(T5.STAFF_LAST_NAME) LIKE UPPER(?)");
                parameterList.add(String.format("%s%%", lastName));
            }

            String sortByColumn = "STAFF_LAST_NAME";
            if (!StringUtil.IsNullOrEmpty(sortOn)) {
                switch (sortOn.toUpperCase()) {
                    case "STAFFFIRSTNAME":
                        sortByColumn = "STAFF_FIRST_NAME";
                        break;
                    case "STAFFMIDDLENAME":
                        sortByColumn = "STAFF_MIDDLE_NAME";
                        break;
                    case "STAFFID":
                        sortByColumn = "STAFF_ID";
                        break;
                    case "STAFFPOSITIONNAME":
                        sortByColumn = "STAFF_POSITION_NAME";
                        break;
                    case "STAFFEMPLOYMENTSTATUSTYPENAME":
                        sortByColumn = "STAFF_EMPLT_STATUS_TYP_NAME";
                        break;
                    case "STAFFLASTNAME":
                        sortByColumn = "STAFF_LAST_NAME";
                        break;
                }
            }
            if(StringUtil.IsNullOrEmpty(direction)){
                direction = "ASC";
            }

            // Support sorting and paging.
            int toRow = page * pageSize;
            int fromRow = toRow - (pageSize - 1);

            sqlStringBuilder.append(String.format(" AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = '1')"
                    + " AND (TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T2.CURR_REC_IND = '1')"
                    + " AND (TO_CHAR(T3.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T3.CURR_REC_IND = '1')"
                    + " AND (TO_CHAR(T4.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T4.CURR_REC_IND = '1')"
                    + " AND (TO_CHAR(T5.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T5.CURR_REC_IND = '1')"
                    + " ORDER BY T5.%s %s)) R1)"
                    + " WHERE ROW_NUMBER BETWEEN %s AND %s",
                sortByColumn,
                direction,
                fromRow,
                toRow));

            preparedStatement = connection.prepareStatement(sqlStringBuilder.toString());

            // Set parameters.
            int index = 1;
            for (Object parameter : parameterList) {
                if (parameter instanceof String) {
                    preparedStatement.setString(index++, (String) parameter);
                } else if (parameter instanceof Boolean) {
                    preparedStatement.setBoolean(index++, (Boolean) parameter);
                }
            }

            resultSet = preparedStatement.executeQuery();

            // Create Response and set details.
            Response response = new Response();
            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);
            response.setPage(page);
            response.setPageSize(pageSize);

            mapResultSetToFindCompSchedStaffResult(response, resultSet);

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
     * Maps specified ResultSet to List of FindCompSchedStaffResult and sets data of specified Response.
     *
     * @param response  Specified Response.
     * @param resultSet Specified ResultSet.
     * @throws SQLException
     */
    private void mapResultSetToFindCompSchedStaffResult(Response response, ResultSet resultSet) throws SQLException {
        List<FindCompSchedStaffResult> findCompSchedStaffResultList = new ArrayList<>();

        while (resultSet.next()) {
            if (response.getTotalRows() == 0) {
                response.setTotalRows(resultSet.getInt("TOTAL_ROWS"));
            }

            FindCompSchedStaffResult findCompSchedStaffResult = new FindCompSchedStaffResult();

            findCompSchedStaffResult.setStaffSK(BigInteger.valueOf(resultSet.getBigDecimal("STAFF_SK").longValue()));
            findCompSchedStaffResult.setStaffID(resultSet.getString("STAFF_ID"));
            findCompSchedStaffResult.setStaffFirstName(resultSet.getString("STAFF_FIRST_NAME"));
            findCompSchedStaffResult.setStaffMiddleName(resultSet.getString("STAFF_MIDDLE_NAME"));
            findCompSchedStaffResult.setStaffLastName(resultSet.getString("STAFF_LAST_NAME"));
            String staffEmpltStatusTypName = resultSet.getString("STAFF_EMPLT_STATUS_TYP_NAME");
            findCompSchedStaffResult.setStaffEmploymentStatusTypeName(staffEmpltStatusTypName != null ? EmploymentStatusTypeName.fromValue(staffEmpltStatusTypName) : null);
            String staffPositionName = resultSet.getString("STAFF_POSITION_NAME");
            findCompSchedStaffResult.setStaffPositionName(staffPositionName != null ? ServiceName.fromValue(staffPositionName) : null);

            findCompSchedStaffResultList.add(findCompSchedStaffResult);
        }

        response.setData(findCompSchedStaffResultList);
    }

    public void insertStaffLanguageLst(Connection connection, String staffId, String bsnEntId, List<String> languages)
        throws SandataRuntimeException {

        try {

            Date termDate = new SimpleDateFormat("yyyy-MM-dd").parse("9999-12-31");

            for (String language : languages) {

                // Creates and initializes the object
                StaffLanguage staffLanguage = new StaffLanguage();

                staffLanguage.setRecordCreateTimestamp(new Date());
                staffLanguage.setRecordUpdateTimestamp(new Date());
                staffLanguage.setRecordEffectiveTimestamp(new Date());
                staffLanguage.setRecordTerminationTimestamp(termDate);
                staffLanguage.setCurrentRecordIndicator(true);
                staffLanguage.setChangeVersionID(BigInteger.ZERO);

                staffLanguage.setStaffID(staffId);
                staffLanguage.setBusinessEntityID(bsnEntId);
                staffLanguage.setLanguageCode(language);

                staffLanguage.setRecordCreatedBy("Middleware Service");
                staffLanguage.setChangeReasonMemo("Staff: OracleDataService: insertStaffLanguageLst");

                OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(staffLanguage);
                StaffLangT jpubObj = (StaffLangT) new DataMapper().map(staffLanguage);

                execute(connection, ConnectionType.COREDATA, oracleMetadata.packageName(), oracleMetadata.insertMethod(), jpubObj);
            }
        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s",
                e.getClass().getName(), e.getMessage()), e);
        }
    }

    public void deleteStaffLanguageItemRequest(Connection connection, String staffId, String bsnEntId) throws SandataRuntimeException {

        CallableStatement callableStatement = null;

        try {
            String callMethod = "{?=call PKG_STAFF_UTIL.DELETE_STAFF_LANGUAGE_LIST(?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);

            int index = 2;
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, staffId);

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
     * Deletes StaffTrainingClassEvent entities and for StaffTrainingLocation
     * entity with the specified sequence key.
     *
     * @param sequenceKey Specified sequence key for StaffTrainingLocation.
     * @return Number of StaffTrainingClassEvent entities updated.
     */
    public int deleteStaffTrngClsEvntForLoc(long sequenceKey) {
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String callMethod = "{?=call PKG_STAFF_UTIL.DELETE_STAFF_TRNG_CLS_EVNT(?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setLong(2, sequenceKey);

            callableStatement.execute();
            int result = (Integer)callableStatement.getObject(1);

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

    public Response getStaffTrngClsEvntEnrolForClsEvnt(String bsnEntId,
                                                       String staffTrngLocName,
                                                       String staffTrngCode,
                                                       String staffTrngStartDateTime,
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

            // Build SQL query.
            String sql = String.format("SELECT * FROM"
                    + "  (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (SELECT * FROM (SELECT * FROM ("
                    + "    SELECT DISTINCT STAFF_TRNG_CLS_EVNT_ENROL.STAFF_TRNG_CLS_EVNT_ENROL_SK,"
                    + "     STAFF_TRNG_CLS_EVNT_ENROL.REC_CREATE_TMSTP,"
                    + "     STAFF_TRNG_CLS_EVNT_ENROL.REC_UPDATE_TMSTP,"
                    + "     STAFF_TRNG_CLS_EVNT_ENROL.REC_EFF_TMSTP,"
                    + "     STAFF_TRNG_CLS_EVNT_ENROL.REC_TERM_TMSTP,"
                    + "     STAFF_TRNG_CLS_EVNT_ENROL.REC_CREATED_BY,"
                    + "     STAFF_TRNG_CLS_EVNT_ENROL.REC_UPDATED_BY,"
                    + "     STAFF_TRNG_CLS_EVNT_ENROL.CHANGE_REASON_MEMO,"
                    + "     STAFF_TRNG_CLS_EVNT_ENROL.CURR_REC_IND,"
                    + "     STAFF_TRNG_CLS_EVNT_ENROL.CHANGE_VERSION_ID,"
                    + "     STAFF_TRNG_CLS_EVNT_ENROL.BE_ID,"
                    + "     STAFF_TRNG_CLS_EVNT_ENROL.STAFF_ID,"
                    + "     STAFF_TRNG_CLS_EVNT_ENROL.STAFF_TRNG_LOC_NAME,"
                    + "     STAFF_TRNG_CLS_EVNT_ENROL.STAFF_TRNG_CODE,"
                    + "     STAFF_TRNG_CLS_EVNT_ENROL.STAFF_TRNG_START_DTIME,"
                    + "     STAFF_TRNG_CLS_EVNT_ENROL.STAFF_TRNG_QLFR,"
                    + "     STAFF_TRNG_CLS_EVNT_ENROL.STAFF_TRNG_DROP_IND,"
                    + "     STAFF.STAFF_FIRST_NAME,"
                    + "     STAFF.STAFF_LAST_NAME,"
                    + "     STAFF.STAFF_MIDDLE_NAME,"
                    + "     STAFF_TRNG.STAFF_TRNG_ENROLLED_DATE,"
                    + "     STAFF_TRNG.STAFF_TRNG_RESULT_CODE"
                    + "  FROM STAFF_TRNG_CLS_EVNT_ENROL"
                    + "    INNER JOIN STAFF ON STAFF.BE_ID = STAFF_TRNG_CLS_EVNT_ENROL.BE_ID"
                    + "      AND STAFF.STAFF_ID = STAFF_TRNG_CLS_EVNT_ENROL.STAFF_ID"
                    + "    INNER JOIN STAFF_TRNG ON STAFF_TRNG.BE_ID = STAFF_TRNG_CLS_EVNT_ENROL.BE_ID"
                    + "      AND STAFF_TRNG.STAFF_ID = STAFF.STAFF_ID"
                    + "      AND STAFF_TRNG.STAFF_TRNG_START_DTIME = STAFF_TRNG_CLS_EVNT_ENROL.STAFF_TRNG_START_DTIME"
                    + "      AND STAFF_TRNG.STAFF_TRNG_LOC_NAME = STAFF_TRNG_CLS_EVNT_ENROL.STAFF_TRNG_LOC_NAME"
                    + "      AND STAFF_TRNG.STAFF_TRNG_CODE = STAFF_TRNG_CLS_EVNT_ENROL.STAFF_TRNG_CODE"
                    + "  WHERE STAFF_TRNG_CLS_EVNT_ENROL.BE_ID=?"
                    + "    AND STAFF_TRNG_CLS_EVNT_ENROL.STAFF_TRNG_LOC_NAME=?"
                    + "    AND STAFF_TRNG_CLS_EVNT_ENROL.STAFF_TRNG_CODE=?"
                    + "    AND STAFF_TRNG_CLS_EVNT_ENROL.STAFF_TRNG_START_DTIME=TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')"
                    + "    AND (TO_CHAR(STAFF_TRNG_CLS_EVNT_ENROL.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND STAFF_TRNG_CLS_EVNT_ENROL.CURR_REC_IND = '1')"
                    + "    AND (TO_CHAR(STAFF.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND STAFF.CURR_REC_IND = '1')"
                    + "    AND (TO_CHAR(STAFF_TRNG.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND STAFF_TRNG.CURR_REC_IND = '1') ORDER BY %s %s))) R1) WHERE ROW_NUMBER BETWEEN %s AND %s",
                sortOn,
                direction,
                fromRow,
                toRow);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, staffTrngLocName);
            preparedStatement.setString(index++, staffTrngCode);
            preparedStatement.setString(index, staffTrngStartDateTime);

            resultSet = preparedStatement.executeQuery();

            // Create Response and set details.
            Response response = new Response();
            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);
            response.setPage(page);
            response.setPageSize(pageSize);

            mapResultSetToStaffTrainingStaffTrainingClassEventEnrollment(response, resultSet);

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

    private void mapResultSetToStaffTrainingStaffTrainingClassEventEnrollment(Response response, ResultSet resultSet) throws SQLException {
        List<StaffTrainingClassEventEnrollmentExt> staffTrainingClassEventEnrollmentExtList = new ArrayList<>();

        while (resultSet.next()) {
            if (response.getTotalRows() == 0) {
                response.setTotalRows(resultSet.getInt("TOTAL_ROWS"));
            }

            StaffTrainingClassEventEnrollmentExt staffTrainingClassEventEnrollmentExt = new StaffTrainingClassEventEnrollmentExt();

            staffTrainingClassEventEnrollmentExt.setStaffTrainingClassEventEnrollmentSK(BigInteger.valueOf(resultSet.getBigDecimal("STAFF_TRNG_CLS_EVNT_ENROL_SK").longValue()));
            staffTrainingClassEventEnrollmentExt.setRecordCreateTimestamp(resultSet.getTimestamp("REC_CREATE_TMSTP"));
            staffTrainingClassEventEnrollmentExt.setRecordUpdateTimestamp(resultSet.getTimestamp("REC_UPDATE_TMSTP"));
            staffTrainingClassEventEnrollmentExt.setRecordEffectiveTimestamp(resultSet.getTimestamp("REC_EFF_TMSTP"));
            staffTrainingClassEventEnrollmentExt.setRecordTerminationTimestamp(resultSet.getTimestamp("REC_TERM_TMSTP"));
            staffTrainingClassEventEnrollmentExt.setRecordCreatedBy(resultSet.getString("REC_CREATED_BY"));
            staffTrainingClassEventEnrollmentExt.setRecordUpdatedBy(resultSet.getString("REC_UPDATED_BY"));
            staffTrainingClassEventEnrollmentExt.setChangeReasonMemo(resultSet.getString("CHANGE_REASON_MEMO"));
            staffTrainingClassEventEnrollmentExt.setCurrentRecordIndicator(resultSet.getBoolean("CURR_REC_IND"));
            staffTrainingClassEventEnrollmentExt.setChangeVersionID(BigInteger.valueOf(resultSet.getBigDecimal("CHANGE_VERSION_ID").longValue()));
            staffTrainingClassEventEnrollmentExt.setBusinessEntityID(resultSet.getString("BE_ID"));
            staffTrainingClassEventEnrollmentExt.setStaffID(resultSet.getString("STAFF_ID"));
            staffTrainingClassEventEnrollmentExt.setStaffTrainingLocationName(resultSet.getString("STAFF_TRNG_LOC_NAME"));
            staffTrainingClassEventEnrollmentExt.setStaffTrainingCode(resultSet.getString("STAFF_TRNG_CODE"));
            staffTrainingClassEventEnrollmentExt.setStaffTrainingStartDatetime(resultSet.getTimestamp("STAFF_TRNG_START_DTIME"));
            staffTrainingClassEventEnrollmentExt.setStaffTrainingQualifier(StaffTrainingQualifier.fromValue(resultSet.getString("STAFF_TRNG_QLFR")));
            staffTrainingClassEventEnrollmentExt.setStaffTrainingDropIndicator(resultSet.getBoolean("STAFF_TRNG_DROP_IND"));
            staffTrainingClassEventEnrollmentExt.setStaffFirstName(resultSet.getString("STAFF_FIRST_NAME"));
            staffTrainingClassEventEnrollmentExt.setStaffLastName(resultSet.getString("STAFF_LAST_NAME"));
            staffTrainingClassEventEnrollmentExt.setStaffMiddleName(resultSet.getString("STAFF_MIDDLE_NAME"));
            staffTrainingClassEventEnrollmentExt.setStaffTrainingEnrollmentDate(resultSet.getTimestamp("STAFF_TRNG_ENROLLED_DATE"));
            String staffTrainingResultCode = resultSet.getString("STAFF_TRNG_RESULT_CODE");
            if (staffTrainingResultCode.equalsIgnoreCase(InServiceTrainingResult.COMPLETED.toString())) {
                staffTrainingClassEventEnrollmentExt.setStaffTrainingCompletedIndicator(true);
            } else if (staffTrainingResultCode.equalsIgnoreCase(InServiceTrainingResult.NO_SHOW.toString())) {
                staffTrainingClassEventEnrollmentExt.setStaffTrainingNoShowIndicator(true);
            }

            staffTrainingClassEventEnrollmentExtList.add(staffTrainingClassEventEnrollmentExt);
        }

        response.setData(staffTrainingClassEventEnrollmentExtList);
    }

    public void deleteStaffTrngForClsEvntEnrol(long sequenceKey) {
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String callMethod = "{?=call PKG_STAFF_UTIL.DEL_STAFF_TRNG_CLS_EVNT_ENROL(?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setLong(2, sequenceKey);
            
            callableStatement.execute();
            
            // commit the transaction
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

    public StaffTraining getStaffTrngForClsEvntEnrol(Connection connection,
                                                     String bsnEntId,
                                                     String staffID,
                                                     Date staffTrngStartDateTime,
                                                     String staffTrngLocName,
                                                     String staffTrngCode) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM STAFF_TRNG"
                + "  WHERE BE_ID=?"
                + "    AND STAFF_ID=?"
                + "    AND STAFF_TRNG_START_DTIME=TO_DATE(?, 'MM/DD/YYYY HH24:MI:SS')"
                + "    AND STAFF_TRNG_LOC_NAME=?"
                + "    AND STAFF_TRNG_CODE=?"
                + "    AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, staffID);
            preparedStatement.setString(index++, new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(staffTrngStartDateTime));
            preparedStatement.setString(index++, staffTrngLocName);
            preparedStatement.setString(index, staffTrngCode);

            resultSet = preparedStatement.executeQuery();

            List<StaffTraining> staffTrainingList = (List<StaffTraining>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.StaffTraining");

            if (staffTrainingList != null
                && !staffTrainingList.isEmpty()) {
                return staffTrainingList.get(0);
            }

            throw new SandataRuntimeException(String.format("Failed to find StaffTraining entity for Staff with ID %s," +
                    "class start date time %s," +
                    "class location name %s," +
                    "and class code %s!",
                staffID,
                new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(staffTrngStartDateTime),
                staffTrngLocName,
                staffTrngCode));

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
        }
    }

    public int deleteStaffTrngClsEvntAttndForEnrol(Connection connection,
                                                   int sequenceKey) {

        CallableStatement callableStatement = null;

        try {
            String callMethod = "{?=call PKG_STAFF_UTIL.DEL_STAFF_TRNG_CLS_EVNT_ATTND(?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setInt(2, sequenceKey);

            callableStatement.execute();
            int result = (Integer)callableStatement.getObject(1);

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
        }
    }

    private Response mapResultSetToFindStaff(
        Connection connection,
        ResultSet resultSet,
        Date fromDateTime,
        Date toDateTime,
        String bsnEntId) throws SQLException {

        List<FindStaffResultExt> findStaffResults = new ArrayList<>();

        Response response = new Response();
        response.setData(findStaffResults);

        while (resultSet.next()) {

            FindStaffResultExt findStaffResult = new FindStaffResultExt();

            if (response.getTotalRows() == 0) {
                response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());
            }

            Timestamp originalStaffHireDate = resultSet.getTimestamp("ORIGINAL_STAFF_HIRE_DATE");
            if (originalStaffHireDate != null) {
                findStaffResult.setOriginalStaffHireDate(new Date(originalStaffHireDate.getTime()));
            }

            findStaffResult.setStaffID(resultSet.getString("STAFF_ID"));
            findStaffResult.setStaffSK(BigInteger.valueOf(resultSet.getBigDecimal("STAFF_SK").longValue()));
            findStaffResult.setStaffFirstName(resultSet.getString("STAFF_FIRST_NAME"));
            findStaffResult.setStaffLastName(resultSet.getString("STAFF_LAST_NAME"));

            //STAFF_PHONE is primary phone
            findStaffResult.setStaffPreferredPhone(resultSet.getString("STAFF_PHONE"));

            //LAST_DATE_WORKED is the result from PKG_STAFF_UTIL.LAST_DATE_WORKED()
            Timestamp lastDateWorked = resultSet.getTimestamp("LAST_DATE_WORKED");
            if (lastDateWorked != null) {
                findStaffResult.setLastDateWorked(new java.util.Date(lastDateWorked.getTime()));
            }

            Timestamp dob = resultSet.getTimestamp("STAFF_DOB");
            if (dob != null) {
                findStaffResult.setStaffDateOfBirth(new java.util.Date(dob.getTime()));
            }

            //dmr--SAN-3829: DROP COLUMN staff_hire_date_qlfr; ADD staff_last_hire_date DATE;
            Timestamp staffLastHireDate = resultSet.getTimestamp("STAFF_LAST_HIRE_DATE");
            if (staffLastHireDate != null) {
                findStaffResult.setStaffLastHireDate(new java.util.Date(staffLastHireDate.getTime()));
            }
            //

            Timestamp hireDate = resultSet.getTimestamp("STAFF_HIRE_DATE");
            if (hireDate != null) {
                findStaffResult.setStaffHireDate(new java.util.Date(hireDate.getTime()));
            }

            findStaffResult.setStaffLocation(resultSet.getString("STAFF_LOCATION_XWALK"));

            findStaffResult.setStaffAddressLine1(resultSet.getString("STAFF_ADDR1"));
            findStaffResult.setStaffAddressLine2(resultSet.getString("STAFF_ADDR2"));
            findStaffResult.setStaffCity(resultSet.getString("STAFF_CITY"));
            findStaffResult.setStaffState(resultSet.getString("STAFF_STATE"));

            findStaffResult.setStaffPostalCode(resultSet.getString("STAFF_PSTL_CODE"));

            findStaffResult.setBusinessEntitySK(BigInteger.valueOf(resultSet.getBigDecimal("BE_SK").longValue()));
            findStaffResult.setBusinessEntityName(resultSet.getString("BE_NAME"));
            findStaffResult.setBusinessEntityType(resultSet.getString("BE_TYP"));
            findStaffResult.setBusinessEntityPrimaryAddressLine1(resultSet.getString("BE_PRMY_ADDR1"));
            findStaffResult.setBusinessEntityPrimaryAddressLine2(resultSet.getString("BE_PRMY_ADDR2"));
            findStaffResult.setBusinessEntityPrimaryCity(resultSet.getString("BE_PRMY_CITY"));
            findStaffResult.setBusinessEntityPrimaryState(resultSet.getString("BE_PRMY_STATE"));
            findStaffResult.setBusinessEntityPrimaryPostalCode(resultSet.getString("BE_PRMY_PSTL_CODE"));
            findStaffResult.setBusinessEntityPrimaryZip4(resultSet.getString("BE_PRMY_ZIP4"));
            findStaffResult.setBusinessEntityContactPhone(resultSet.getString("BE_PRMY_PHONE_1"));

            findStaffResult.setStaffPositionName(resultSet.getString("STAFF_POSITION_NAME"));

            String statusTypName = resultSet.getString("STAFF_EMPLT_STATUS_TYP_NAME");
            if (statusTypName != null) {
                try {
                    findStaffResult.setEmploymentStatusTypeName(EmploymentStatusTypeName.fromValue(statusTypName));
                } catch (Exception e) {
                    // not a valid Status Type Name
                }
            }

            findStaffResults.add(findStaffResult);

            if (fromDateTime != null && toDateTime != null) {

                findStaffResult.setScheduleEvents(getStaffScheduleEvents(
                    findStaffResult.getStaffID(),
                    ORACLE_DATE_TIME_FORMAT.format(fromDateTime),
                    ORACLE_DATE_TIME_FORMAT.format(toDateTime)));
            }
        }

        return response;
    }

    public boolean doesScheduleEventConflictExist(String bsnEntId,
                                                  String staffID,
                                                  String staffTrngLocName,
                                                  String staffTrngCode,
                                                  Date staffTrngStartDateTime) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT COUNT(*) FROM SCHED_EVNT"
                + "  WHERE SCHED_EVNT.BE_ID=?"
                + "    AND SCHED_EVNT.STAFF_ID=?"
                + "    AND SCHED_EVNT.SCHED_EVNT_STATUS='PENDING'"
                + "    AND (SCHED_EVNT.SCHED_EVNT_START_DTIME BETWEEN TO_DATE(?, 'MM/DD/YYYY HH24:MI:SS') AND (SELECT STAFF_TRNG_CLS_EVNT.STAFF_TRNG_START_DTIME + BE_STAFF_TRNG_LKUP.STAFF_TRNG_TOTAL_HRS/24 FROM STAFF_TRNG_CLS_EVNT"
                + "        INNER JOIN BE_STAFF_TRNG_LKUP ON BE_STAFF_TRNG_LKUP.BE_ID = STAFF_TRNG_CLS_EVNT.BE_ID"
                + "          AND BE_STAFF_TRNG_LKUP.STAFF_TRNG_CODE = STAFF_TRNG_CLS_EVNT.STAFF_TRNG_CODE"
                + "        WHERE STAFF_TRNG_CLS_EVNT.BE_ID=SCHED_EVNT.BE_ID"
                + "         AND STAFF_TRNG_CLS_EVNT.STAFF_TRNG_CODE=?"
                + "         AND STAFF_TRNG_CLS_EVNT.STAFF_TRNG_LOC_NAME=?"
                + "         AND STAFF_TRNG_CLS_EVNT.STAFF_TRNG_START_DTIME=TO_DATE(?, 'MM/DD/YYYY HH24:MI:SS')"
                + "         AND (TO_CHAR(STAFF_TRNG_CLS_EVNT.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND STAFF_TRNG_CLS_EVNT.CURR_REC_IND = '1')"
                + "         AND (TO_CHAR(BE_STAFF_TRNG_LKUP.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND BE_STAFF_TRNG_LKUP.CURR_REC_IND = '1'))"
                + "      OR SCHED_EVNT.SCHED_EVNT_END_DTIME BETWEEN TO_DATE(?, 'MM/DD/YYYY HH24:MI:SS') AND  (SELECT STAFF_TRNG_CLS_EVNT.STAFF_TRNG_START_DTIME + BE_STAFF_TRNG_LKUP.STAFF_TRNG_TOTAL_HRS/24 FROM STAFF_TRNG_CLS_EVNT"
                + "        INNER JOIN BE_STAFF_TRNG_LKUP ON BE_STAFF_TRNG_LKUP.BE_ID = STAFF_TRNG_CLS_EVNT.BE_ID"
                + "          AND BE_STAFF_TRNG_LKUP.STAFF_TRNG_CODE = STAFF_TRNG_CLS_EVNT.STAFF_TRNG_CODE"
                + "         WHERE STAFF_TRNG_CLS_EVNT.BE_ID=SCHED_EVNT.BE_ID"
                + "          AND STAFF_TRNG_CLS_EVNT.STAFF_TRNG_CODE=?"
                + "          AND STAFF_TRNG_CLS_EVNT.STAFF_TRNG_LOC_NAME=?"
                + "          AND STAFF_TRNG_CLS_EVNT.STAFF_TRNG_START_DTIME=TO_DATE(?, 'MM/DD/YYYY HH24:MI:SS')"
                + "          AND (TO_CHAR(STAFF_TRNG_CLS_EVNT.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND STAFF_TRNG_CLS_EVNT.CURR_REC_IND = '1')"
                + "          AND (TO_CHAR(BE_STAFF_TRNG_LKUP.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND BE_STAFF_TRNG_LKUP.CURR_REC_IND = '1')))"
                + "    AND (TO_CHAR(SCHED_EVNT.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND SCHED_EVNT.CURR_REC_IND = '1')";

            preparedStatement = connection.prepareStatement(sql);

            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            String staffTrngStartDateTimeString = dateFormat.format(staffTrngStartDateTime);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, staffID);
            preparedStatement.setString(index++, staffTrngStartDateTimeString);
            preparedStatement.setString(index++, staffTrngCode);
            preparedStatement.setString(index++, staffTrngLocName);
            preparedStatement.setString(index++, staffTrngStartDateTimeString);
            preparedStatement.setString(index++, staffTrngStartDateTimeString);
            preparedStatement.setString(index++, staffTrngCode);
            preparedStatement.setString(index++, staffTrngLocName);
            preparedStatement.setString(index, staffTrngStartDateTimeString);

            resultSet = preparedStatement.executeQuery();

            int count = -1;

            if (resultSet.next()) {
                count = resultSet.getInt("COUNT(*)");
            }

            connection.commit();

            if (count == -1) {
                throw new SandataRuntimeException("Failed to find count of schedule events!");
            }

            return count > 0;

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

            // Close the prepared statement
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


    public int cancelSchedEvntForStaffTrngClsEvntEnrol(Connection connection,
                                                       String bsnEntId,
                                                       String staffID,
                                                       String staffTrngCode,
                                                       String staffTrngLocName,
                                                       Date staffTrngStartDateTime) {

        CallableStatement callableStatement = null;

        try {

            String callMethod = "{?=call PKG_STAFF_UTIL.CANCEL_SCHED_EVNT_CLS_EVNT_ENR(?,?,?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);

            int index = 2;
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, staffID);
            callableStatement.setString(index++, staffTrngCode);
            callableStatement.setString(index++, staffTrngLocName);
            callableStatement.setTimestamp(index++, new java.sql.Timestamp(staffTrngStartDateTime.getTime()));

            callableStatement.execute();
            int result = (Integer)callableStatement.getObject(1);

            // commit the transaction
            connection.commit();

            return result;

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
     * Gets all active Administrative Staffs for a specified Business Entity ID
     *
     * @param bsnEntId
     * @param page
     * @param pageSize
     * @param orderByColumn
     * @param direction
     * @return a list of Administrative Staffs
     */
    public Response getAdminStaffs(String bsnEntId, int page, int pageSize, String orderByColumn, String direction) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {
            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format(new StringBuilder()
                .append("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (")
                .append("   SELECT * FROM (")
                .append("       SELECT * ")
                .append("       FROM COREDATA.ADMIN_STAFF WHERE BE_ID = ?")
                .append("           AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) ORDER BY UPPER(%s) %s")
                .append(")  ) R1) WHERE ROW_NUMBER BETWEEN %d AND %d")
                .toString(), orderByColumn, direction, fromRow, toRow);

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<Object> adminStaffs = new ArrayList<Object>();

            Response response = new Response();
            response.setData(adminStaffs);
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction);

            DataMapper dataMapper = new DataMapper();

            while (resultSet.next()) {
                if (resultSet.isFirst() && (response.getTotalRows() == 0)) {
                    response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());
                }

                adminStaffs.add(dataMapper.mapResultSet(resultSet,
                    "com.sandata.lab.data.model.dl.model.AdministrativeStaff", 2));
            }

            return response;

        } catch (Exception e) {
            safeRollback(connection);
            throw new SandataRuntimeException(
                String.format("%s: getAdminStaffs: %s", this.getClass().getName(), e.getMessage()), e);

        } finally {
            safeClose(resultSet);
            safeClose(preparedStatement);
            safeClose(connection);
        }
    }

    /**
     * Returns a list of categories with category hours, and number of enrolled hours by staffId
     *
     * @param bsnEntId
     * @param staffId
     * @return
     */
    public Response getTrainingCategoryTotalHours(String bsnEntId, String staffId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = 
                    "SELECT T4.STAFF_TRNG_CTGY_CODE, T4.STAFF_TRNG_CTGY_NAME, NVL(SUM(T2.STAFF_TRNG_TOTAL_HRS), 0) AS CATEGORY_TOTAL_HOURS, NVL(T5.CATEGORY_ENROLLED_TOTAL_HOURS, 0) AS CATEGORY_ENROLLED_TOTAL_HOURS " +
                    "FROM STAFF_TRNG T1 " +
                    "INNER JOIN  " +
                    "  (SELECT *  " +
                    "  FROM BE_STAFF_TRNG_LKUP  " +
                    "  WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2  " +
                    "ON T1.BE_ID = T2.BE_ID AND T1.STAFF_TRNG_CODE = T2.STAFF_TRNG_CODE " +
                    "INNER JOIN  " +
                    "  (SELECT * " +
                    "  FROM BE_STAFF_TRNG_CTGY_LST " +
                    "  WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T3 " +
                    "ON T1.BE_ID = T3.BE_ID AND T1.STAFF_TRNG_CODE = T3.STAFF_TRNG_CODE " +
                    "INNER JOIN  " +
                    "  (SELECT * " +
                    "  FROM BE_STAFF_TRNG_CTGY_LKUP " +
                    "  WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T4 " +
                    "ON T1.BE_ID = T4.BE_ID AND T3.STAFF_TRNG_CTGY_CODE = T4.STAFF_TRNG_CTGY_CODE " +
                    " " +
                    "LEFT JOIN ( " +
                    "  SELECT J1.BE_ID, J1.STAFF_ID, J5.STAFF_TRNG_CTGY_CODE, J5.STAFF_TRNG_CTGY_NAME, SUM(J3.STAFF_TRNG_TOTAL_HRS) AS CATEGORY_ENROLLED_TOTAL_HOURS " +
                    "  FROM STAFF_TRNG_CLS_EVNT_ENROL J1 " +
                    "  INNER JOIN  " +
                    "    (SELECT * " +
                    "    FROM STAFF_TRNG " +
                    "    WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) J2 " +
                    "  ON J1.BE_ID = J2.BE_ID  " +
                    "    AND J1.STAFF_ID = J2.STAFF_ID  " +
                    "    AND J1.STAFF_TRNG_CODE = J2.STAFF_TRNG_CODE " +
                    "    AND J1.STAFF_TRNG_LOC_NAME = J2.STAFF_TRNG_LOC_NAME " +
                    "    AND J1.STAFF_TRNG_START_DTIME = J2.STAFF_TRNG_START_DTIME " +
                    "  INNER JOIN  " +
                    "    (SELECT *  " +
                    "    FROM BE_STAFF_TRNG_LKUP  " +
                    "    WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) J3  " +
                    "  ON J1.BE_ID = J3.BE_ID AND J1.STAFF_TRNG_CODE = J3.STAFF_TRNG_CODE " +
                    "  INNER JOIN  " +
                    "    (SELECT * " +
                    "    FROM BE_STAFF_TRNG_CTGY_LST " +
                    "    WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) J4 " +
                    "  ON J1.BE_ID = J4.BE_ID AND J1.STAFF_TRNG_CODE = J4.STAFF_TRNG_CODE " +
                    "  INNER JOIN  " +
                    "    (SELECT * " +
                    "    FROM BE_STAFF_TRNG_CTGY_LKUP " +
                    "    WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) J5 " +
                    "  ON J1.BE_ID = J5.BE_ID AND J4.STAFF_TRNG_CTGY_CODE = J5.STAFF_TRNG_CTGY_CODE " +
                    "  WHERE (TO_CHAR(J1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND J1.CURR_REC_IND = 1) " +
                    "  GROUP BY J1.BE_ID, J1.STAFF_ID, J5.STAFF_TRNG_CTGY_CODE, J5.STAFF_TRNG_CTGY_NAME " +
                    ") T5 " +
                    "ON T1.BE_ID = T5.BE_ID AND T1.STAFF_ID = T5.STAFF_ID AND T4.STAFF_TRNG_CTGY_CODE = T5.STAFF_TRNG_CTGY_CODE " +
                    "WHERE  " +
                    "  T1.BE_ID = ? " +
                    "  AND T1.STAFF_ID = ? " +
                    "  AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1) " +
                    "GROUP BY T4.STAFF_TRNG_CTGY_CODE, T4.STAFF_TRNG_CTGY_NAME, T5.CATEGORY_ENROLLED_TOTAL_HOURS";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, bsnEntId);
            preparedStatement.setString(2, staffId);

            resultSet = preparedStatement.executeQuery();

            List<TrainingCategoryTotalHoursResult> resultList = new ArrayList<>();

            while (resultSet.next()) {
                TrainingCategoryTotalHoursResult result = new TrainingCategoryTotalHoursResult();
                result.setStaffTrainingCategoryCode(resultSet.getString("STAFF_TRNG_CTGY_CODE"));
                result.setStaffTrainingCategoryName(resultSet.getString("STAFF_TRNG_CTGY_NAME"));
                result.setCategoryTotalHours(resultSet.getBigDecimal("CATEGORY_TOTAL_HOURS"));
                result.setCategoryEnrolledTotalHours(resultSet.getBigDecimal("CATEGORY_ENROLLED_TOTAL_HOURS"));

                resultList.add(result);
            }
            Response response = new Response();
            response.setData(resultList);
            response.setTotalRows(resultList.size());

            return response;

        } catch (Exception e) {
            safeRollback(connection);
            throw new SandataRuntimeException(
                    String.format("%s: getAdminStaffs: %s", this.getClass().getName(), e.getMessage()), e);

        } finally {
            safeClose(resultSet);
            safeClose(preparedStatement);
            safeClose(connection);
        }
    }
    
    /**
     * get Specific Available Days for specific Staff of specific BE
     * 
     * @param staffId
     * @param bsnEntId
     * @param filterConditions
     * @param params
     * @param sortOn
     * @param direction
     * @param page
     * @param pageSize
     * @return
     */
    public Response getStaffSpecificAvail(String staffId, String bsnEntId, String filterConditions, List<Object> params,
            String sortOn, String direction, int page, int pageSize) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            int toRow = page * pageSize;
            int fromRow = toRow - (pageSize - 1);
            
            direction = "DESC".equalsIgnoreCase(direction) ? "DESC" : "ASC";
            
            switch (sortOn.toLowerCase(Locale.US)) {
                case "start_hour":
                    sortOn = "AVAIL_START_HOUR";
                    break;
                case "end_hour":
                    sortOn = "AVAIL_END_HOUR";
                    break;
                case "created":
                    sortOn = "REC_CREATE_TMSTP";
                    break;
                case "avail":
                    sortOn = "STAFF_IS_AVAILABLE_IND";
                    break;
                default:
                    sortOn = "AVAIL_START_HOUR";
            }
            
            
            connection = connectionPoolDataService.getConnection();

            String sql = String.format( 
                    "SELECT * FROM (" +
                    "    SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (" +
                    
                    "        SELECT T1.* FROM STAFF_AVAIL T1 " + 
                    "        WHERE " + 
                    "            %s " + 
                    "            STAFF_ID = ? AND BE_ID = ? AND AVAIL_DAY IS NULL " + 
                    "        ORDER BY %s %s " +
                    
                    "    ) R1" +
                    ") WHERE ROW_NUMBER BETWEEN %d AND %d"
                    , filterConditions, sortOn, direction, fromRow, toRow);
            
            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (Object param : params) {
                preparedStatement.setObject(index++, param);
            }
            preparedStatement.setString(index++, staffId);
            preparedStatement.setString(index++, bsnEntId);
            

            resultSet = preparedStatement.executeQuery();
            
            Response response = new Response();
            List<StaffAvailability> result = new ArrayList<>();
            while (resultSet.next()) {
                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getInt("TOTAL_ROWS"));
                }
                
                StaffAvailability staffAvail = (StaffAvailability) new DataMapper()
                        .mapResultSet(resultSet, "com.sandata.lab.data.model.dl.model.StaffAvailability", 2);
                result.add(staffAvail);
            }
            
            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setData(result);
            
            return response;

        } catch (Exception e) {
            safeRollback(connection);
            throw new SandataRuntimeException(
                    String.format("%s: getAdminStaffs: %s", this.getClass().getName(), e.getMessage()), e);

        } finally {
            safeClose(resultSet);
            safeClose(preparedStatement);
            safeClose(connection);
        }
    }
    
    public List<ValidatedStaffResult> validateExistStaffInformation(String bsnEntId, Long editingStaffSK, String ssn, String tin,
            String email, String lastName, String firstName, String phone) {
        Connection connection = null;
        
        try {
            
            connection = connectionPoolDataService.getConnection();
            List<ValidatedStaffResult> validateResultList = new ArrayList<>();

            String sqlGetStaffInformation =
                    "SELECT T1.STAFF_SK, T1.STAFF_ID, T1.STAFF_FIRST_NAME, T1.STAFF_LAST_NAME, T1.STAFF_EMAIL, " +
                            "    (EXTRACT(YEAR FROM CURRENT_DATE) - EXTRACT(YEAR FROM T1.STAFF_DOB)) AS AGE, " +
                            "    CASE WHEN (UPPER(T1.STAFF_TIN_QLFR) = 'SSN') THEN T1.STAFF_TIN ELSE NULL END AS STAFF_SSN, " +
                            "    CASE WHEN (UPPER(T1.STAFF_TIN_QLFR) = 'ITIN') THEN T1.STAFF_TIN ELSE NULL END AS STAFF_TIN, " +
                            "    T2.STAFF_ADDR1, T2.STAFF_ADDR2, T2.STAFF_APT_NUM, T2.STAFF_CITY, T2.STAFF_STATE, T2.STAFF_PSTL_CODE,T3.STAFF_PHONE " +
                            "FROM STAFF T1 " +
                            "    LEFT JOIN STAFF_CONT_ADDR T2 ON T1.BE_ID = T2.BE_ID AND T1.STAFF_ID = T2.STAFF_ID " +
                            "        AND UPPER(T2.ADDR_PRIO_NAME) = 'PRIMARY' AND (TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T2.CURR_REC_IND = 1) " +
                            "    LEFT JOIN STAFF_CONT_PHONE T3 ON T1.BE_ID = T3.BE_ID AND T1.STAFF_ID = T3.STAFF_ID " +
                            "        AND UPPER(T3.ADDR_PRIO_NAME) = 'PRIMARY' AND (TO_CHAR(T3.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T3.CURR_REC_IND = 1) " +
                            "WHERE T1.BE_ID = ? AND T1.STAFF_SK <> ? " + // just check other staff
                            "    AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1) ";
            
            if (!StringUtil.IsNullOrEmpty(ssn)) {
                // check SSN #
                List<StaffPromptInformation> staffPromptInformationList = getStaffPromptInformationList(connection, 
                        sqlGetStaffInformation + " AND UPPER(T1.STAFF_TIN_QLFR) = 'SSN' AND T1.STAFF_TIN = ? ",
                        bsnEntId,
                        editingStaffSK != null ? editingStaffSK.longValue() : 0,
                        ssn);
                
                if(!staffPromptInformationList.isEmpty()) {
                    ValidatedStaffResult validateResult = new ValidatedStaffResult();
                    validateResult.setMessageLevel(MessageLevel.PREVENT);
                    validateResult.setErrorMessage("This social security number already exists");
                    validateResult.getExistedStaffs().addAll(staffPromptInformationList);
                    validateResultList.add(validateResult);
                }
            }
            
            if (!StringUtil.IsNullOrEmpty(tin)) {
                // check TIN #
                List<StaffPromptInformation> staffPromptInformationList = getStaffPromptInformationList(connection, 
                        sqlGetStaffInformation + " AND UPPER(T1.STAFF_TIN_QLFR) = 'ITIN' AND T1.STAFF_TIN = ? ",
                        bsnEntId,
                        editingStaffSK != null ? editingStaffSK.longValue() : 0,
                        tin);
                
                if(!staffPromptInformationList.isEmpty()) {
                    ValidatedStaffResult validateResult = new ValidatedStaffResult();
                    validateResult.setMessageLevel(MessageLevel.PREVENT);
                    validateResult.setErrorMessage("This taxpayer indentification number already exists");
                    validateResult.getExistedStaffs().addAll(staffPromptInformationList);
                    validateResultList.add(validateResult);
                }
            }
            
            if (!StringUtil.IsNullOrEmpty(email)) {
                // check Email
                List<StaffPromptInformation> staffPromptInformationList = getStaffPromptInformationList(connection, 
                        sqlGetStaffInformation + " AND UPPER(T1.STAFF_EMAIL) = ? ",
                        bsnEntId,
                        editingStaffSK != null ? editingStaffSK.longValue() : 0,
                        email.toUpperCase(Locale.US));
                
                if(!staffPromptInformationList.isEmpty()) {
                    ValidatedStaffResult validateResult = new ValidatedStaffResult();
                    validateResult.setMessageLevel(MessageLevel.PREVENT);
                    validateResult.setErrorMessage("This email already exists");
                    validateResult.getExistedStaffs().addAll(staffPromptInformationList);
                    validateResultList.add(validateResult);
                }
            }
            
            if (!StringUtil.IsNullOrEmpty(lastName) && !StringUtil.IsNullOrEmpty(firstName)) {
                // check last name and first name
                List<StaffPromptInformation> staffPromptInformationList = getStaffPromptInformationList(connection, 
                        sqlGetStaffInformation + " AND UPPER(T1.STAFF_FIRST_NAME) = ? AND UPPER(T1.STAFF_LAST_NAME) = ? ",
                        bsnEntId,
                        editingStaffSK != null ? editingStaffSK.longValue() : 0,
                        firstName.toUpperCase(Locale.US),
                        lastName.toUpperCase(Locale.US));
                
                if(!staffPromptInformationList.isEmpty()) {
                    ValidatedStaffResult validateResult = new ValidatedStaffResult();
                    validateResult.setMessageLevel(MessageLevel.WARN);
                    validateResult.setErrorMessage("Last name and first name already exists");
                    validateResult.getExistedStaffs().addAll(staffPromptInformationList);
                    validateResultList.add(validateResult);
                }
            }
            
            if (!StringUtil.IsNullOrEmpty(phone)) {
                // check phone
                List<StaffPromptInformation> staffPromptInformationList = getStaffPromptInformationList(connection, 
                        sqlGetStaffInformation + " AND T3.STAFF_PHONE = ? ",
                        bsnEntId,
                        editingStaffSK != null ? editingStaffSK.longValue() : 0,
                        phone);
                
                if(!staffPromptInformationList.isEmpty()) {
                    ValidatedStaffResult validateResult = new ValidatedStaffResult();
                    validateResult.setMessageLevel(MessageLevel.WARN);
                    validateResult.setErrorMessage("Phone number exists");
                    validateResult.getExistedStaffs().addAll(staffPromptInformationList);
                    validateResultList.add(validateResult);
                }
            }
            
            return validateResultList;
        } catch (Exception e) {
            throw new SandataRuntimeException(
                    String.format("%s: validateExistStaffInformation: %s", this.getClass().getName(), e.getMessage()), e);

        } finally {
            safeClose(connection);
        }
    }
    
    private List<StaffPromptInformation> getStaffPromptInformationList(Connection connection, String sql, Object... params) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            preparedStatement = connection.prepareStatement(sql);
            
            int index = 1;
            for (Object param : params) {
                preparedStatement.setObject(index++, param);
            }
            
            resultSet = preparedStatement.executeQuery();
            List<StaffPromptInformation> staffPromptInformationList = new ArrayList<>();
            while (resultSet.next()) {
                staffPromptInformationList.add(mapResultSetToStaffPromptInformation(resultSet));
            }
            
            return staffPromptInformationList;
        } catch (Exception e) {
            throw new SandataRuntimeException(
                    String.format("%s: getStaffPromptInformationList: %s", this.getClass().getName(), e.getMessage()), e);

        } finally {
            safeClose(resultSet);
            safeClose(preparedStatement);
        } 
    }
    
    private StaffPromptInformation mapResultSetToStaffPromptInformation(ResultSet resultSet) throws SQLException {
        StaffPromptInformation staffPromptInformation = new StaffPromptInformation();
        staffPromptInformation.setStaffSK(BigInteger.valueOf(resultSet.getBigDecimal("STAFF_SK").longValue()));
        staffPromptInformation.setStaffID(resultSet.getString("STAFF_ID"));
        staffPromptInformation.setStaffFirstName(resultSet.getString("STAFF_FIRST_NAME"));
        staffPromptInformation.setStaffLastName(resultSet.getString("STAFF_LAST_NAME"));
        staffPromptInformation.setAge(resultSet.getInt("AGE"));
        staffPromptInformation.setSsn(resultSet.getString("STAFF_SSN"));
        staffPromptInformation.setTin(resultSet.getString("STAFF_TIN"));
        staffPromptInformation.setStaffAddressLine1(resultSet.getString("STAFF_ADDR1"));
        staffPromptInformation.setStaffAddressLine2(resultSet.getString("STAFF_ADDR2"));
        staffPromptInformation.setStaffApartmentNumber(resultSet.getString("STAFF_APT_NUM"));
        staffPromptInformation.setStaffCity(resultSet.getString("STAFF_CITY"));
        
        String staffState = resultSet.getString("STAFF_STATE");
        if (staffState != null) {
            try {
                staffPromptInformation.setStaffState(StateCode.fromValue(staffState));
            } catch (Exception e) {
                // Ignore
            }
        }
        staffPromptInformation.setStaffPostalCode(resultSet.getString("STAFF_PSTL_CODE"));
        
        staffPromptInformation.setStaffPhone(resultSet.getString("STAFF_PHONE"));
        staffPromptInformation.setStaffEmail(resultSet.getString("STAFF_EMAIL"));
        
        return staffPromptInformation;
    }

    private void safeClose(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException sqle) {
                LOGGER.warn("Error happened when closing result set: {}", sqle.getMessage(), sqle);
            }
        }
    }

    private void safeClose(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException sqle) {
                LOGGER.warn("Error happened when closing statement: {}", sqle.getMessage(), sqle);
            }
        }
    }

    private void safeClose(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException sqle) {
                LOGGER.warn("Error happened when closing connection: {}", sqle.getMessage(), sqle);
            }
        }
    }

    private void safeRollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException sqle) {
                LOGGER.warn("Error happened when rollback connection: {}", sqle.getMessage(), sqle);
            }
        }
    }

    public int insertAuditEmploymentStatusHistory(long staffSk, String userGuid, Staff staff) throws SandataRuntimeException {

        Connection connection = null;

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(false);

            return insertAuditEmploymentStatusHistory(connection, staffSk, userGuid, staff);

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
                    e.getClass().getName(), e.getMessage()));

        } finally {

            // Close the Connection
            this.connectionPoolDataService.close(connection);
        }
    }

    private int insertAuditEmploymentStatusHistory(Connection connection, long staffSk, String userGuid, Staff staff) throws SandataRuntimeException {

        CallableStatement callableStatement = null;

        try {

            AuditEmploymentStatusHistory auditEmploymentStatusHistory = new AuditEmploymentStatusHistory();
            auditEmploymentStatusHistory.setAuditHost(String.format("%s|%d", staff.getBusinessEntityID(), staffSk));
            auditEmploymentStatusHistory.setUserGuid(userGuid);
            //dmr--SAN-2058: The audit for Employment Status cares about the EmploymentStatusChangeDate which is the effective date
            auditEmploymentStatusHistory.setEffectiveDate(staff.getStaffEmploymentStatusChangeDate());

            EmploymentStatusTypeName employmentStatusTypeName = staff.getStaffEmploymentStatusTypeName();
            if (employmentStatusTypeName != null) {
                auditEmploymentStatusHistory.setStatus(staff.getStaffEmploymentStatusTypeName().value());
            }
            auditEmploymentStatusHistory.setReasonCode(staff.getChangeReasonCode());
            auditEmploymentStatusHistory.setNotes(staff.getChangeReasonMemo());
            auditEmploymentStatusHistory.setModified(staff.getRecordUpdateTimestamp());

            Object jpubType = new DataMapper().map(auditEmploymentStatusHistory);

            String callMethod = String.format("{?=call %s.PKG_AUDIT.INSERT_EMPL_STATUS_HIST(?)", ConnectionType.METADATA);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setObject(2, jpubType);
            callableStatement.execute();

            int resultVal = callableStatement.getInt(1);

            connection.commit();

            return resultVal;

        } catch (Exception e) {

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()));

        } finally {

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    public int updateAuditEmploymentStatusHistory(long staffSk, String userGuid, EmploymentStatusTypeName currentStatus, Staff staff) throws SandataRuntimeException {

        Connection connection = null;

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(false);

            if (currentStatus == null && staff.getStaffEmploymentStatusTypeName() == null) {
                return -1;
            }

            if (currentStatus != null
                    && staff.getStaffEmploymentStatusTypeName()  != null
                    && currentStatus == staff.getStaffEmploymentStatusTypeName() ) {
              return -1;
            }

            return insertAuditEmploymentStatusHistory(connection, staffSk, userGuid, staff);

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
                    e.getClass().getName(), e.getMessage()));

        } finally {

            // Close the Connection
            this.connectionPoolDataService.close(connection);
        }
    }

    public Response getStaffProfileChangesHistory(String bsnEntId, String staffId, String sortOn, String direction) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // default column for ordering
        String orderByColumn = "T1.REC_CREATE_TMSTP";
        String returnOrderByColumn = "ChangedOn";
        if("ChangedOn".equalsIgnoreCase(sortOn)) {
            orderByColumn = "T1.REC_CREATE_TMSTP";
            returnOrderByColumn = "ChangedOn";
        } else if ("ChangedBy".equalsIgnoreCase(sortOn)) {
            orderByColumn = "UPPER(T3.USER_NAME)";
            returnOrderByColumn = "ChangedBy";
        } else if ("ReasonCode".equalsIgnoreCase(sortOn)) {
            orderByColumn = "UPPER(T_Reason_Code.APP_DATA_STRUC_ELT_VAL)";
            returnOrderByColumn = "ReasonCode";
        }

        String auditHost = bsnEntId + "|" + staffId + "|" + "Staff";

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(true);

            String sql =  String.format(
                            "SELECT T1.REC_CREATE_TMSTP AS CHANGED_ON, " +
                            "       T3.USER_NAME AS CHANGED_BY, " +
                            "       T_Reason_Code.APP_DATA_STRUC_ELT_VAL AS REASON_CODE, " +
                            "       T_Notes.APP_DATA_STRUC_ELT_VAL AS NOTES, " +
                            "       T1.AUDIT_HOST, " +
                            "       T1.USER_GUID, " +
                            "       T1.DATA_SEC_CLAS_ID AS CHANGED_FIELD, " +
                            "       T1.APP_DATA_STRUC_ELT_VAL AS CHANGED_FROM, " +
                            "       T2.APP_DATA_STRUC_ELT_VAL AS CHANGED_TO " +
                            " " +
                            "   FROM APP_AUDIT T1 " +
                            " " +
                            "   JOIN APP_AUDIT T2 " +
                            "       ON T1.AUDIT_HOST = T2.AUDIT_HOST " +
                            "           AND T1.USER_GUID = T2.USER_GUID " +
                            "           AND T1.DATA_SEC_CLAS_ID = T2.DATA_SEC_CLAS_ID " +
                            "           AND T1.REC_CREATE_TMSTP = T2.REC_CREATE_TMSTP " +
                            " " +
                            "   LEFT JOIN (SELECT USER_GUID, USER_NAME FROM APP_USER) T3 " +
                            "       ON T1.USER_GUID = T3.USER_GUID " +
                            " " +
                            "   JOIN APP_AUDIT T_Reason_Code " +
                            "       ON T1.AUDIT_HOST = T_Reason_Code.AUDIT_HOST " +
                            "           AND T1.USER_GUID = T_Reason_Code.USER_GUID " +
                            "           AND T1.REC_CREATE_TMSTP = T_Reason_Code.REC_CREATE_TMSTP " +
                            "           AND T_Reason_Code.DATA_SEC_CLAS_ID = 'ReasonCode' " +
                            " " +
                            "   JOIN APP_AUDIT T_Notes " +
                            "         ON T1.AUDIT_HOST = T_Notes.AUDIT_HOST " +
                            "           AND T1.USER_GUID = T_Reason_Code.USER_GUID " +
                            "           AND T1.REC_CREATE_TMSTP = T_Notes.REC_CREATE_TMSTP " +
                            "           AND T_Notes.DATA_SEC_CLAS_ID = 'Notes' " +
                            " " +
                            "   WHERE " +
                            "       T1.AUDIT_HOST = ? " +
                            "           AND T1.AUDIT_DATA_STRUC_READ_IND = 1 " +
                            "           AND T2.AUDIT_DATA_STRUC_WRITE_IND = 1 " +
                            " " +
                            "   ORDER BY %s %s ",
                    orderByColumn,
                    direction);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, auditHost);

            resultSet = preparedStatement.executeQuery();

            Map<Date, StaffProfileChangedLog> map = new LinkedHashMap<>();
            while (resultSet.next()) {
                FieldChangedLog fieldChangedLog = new FieldChangedLog();
                fieldChangedLog.setChangedField(resultSet.getString("CHANGED_FIELD"));
                fieldChangedLog.setChangedFrom(resultSet.getString("CHANGED_FROM"));
                fieldChangedLog.setChangedTo(resultSet.getString("CHANGED_TO"));

                Date changedOn = resultSet.getTimestamp("CHANGED_ON");
                StaffProfileChangedLog staffProfileChangedLog = map.get(changedOn);
                if (staffProfileChangedLog == null) {
                    staffProfileChangedLog = new StaffProfileChangedLog();
                    staffProfileChangedLog.setChangedOn(resultSet.getTimestamp("CHANGED_ON"));
                    staffProfileChangedLog.setChangedBy(resultSet.getString("CHANGED_BY"));
                    staffProfileChangedLog.setReasonCode(resultSet.getString("REASON_CODE"));
                    staffProfileChangedLog.setNotes(resultSet.getString("NOTES"));
                    staffProfileChangedLog.setUserGuid(resultSet.getString("USER_GUID"));
                    map.put(staffProfileChangedLog.getChangedOn(), staffProfileChangedLog);
                }
                staffProfileChangedLog.getChangedFields().add(fieldChangedLog);
            }

            Response response = new Response();
            response.setData(map.values());
            response.setOrderByColumn(returnOrderByColumn);
            response.setOrderByDirection(direction);
            response.setTotalRows(map.size());

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

    public int insertStaffProfileChangeLog(StaffProfileChangedLog staffProfileChangedLog,
                                            String bsnEntId, String staffId) {
    	List<FieldChangedLog> changedFields = staffProfileChangedLog.getChangedFields();
    	int result = -1;
    	if (!changedFields.isEmpty()) {
    		List<AppAuditT> listAppAuditT = new ArrayList<>();
    		Date now = new Date();
    		String auditHost = String.format("%s|%s|%s", bsnEntId, staffId, "Staff");
    		for (FieldChangedLog changedField : changedFields) {
    			ApplicationAudit applicationAuditChangedFrom= new ApplicationAudit();
    			applicationAuditChangedFrom.setRecordCreateTimestamp(now);
    			applicationAuditChangedFrom.setRecordUpdateTimestamp(now);
    			applicationAuditChangedFrom.setUserGloballyUniqueIdentifier(staffProfileChangedLog.getUserGuid());
    			applicationAuditChangedFrom.setAuditHost(auditHost);
    			applicationAuditChangedFrom.setDataSecurityClassificationID(changedField.getChangedField());
    			applicationAuditChangedFrom.setApplicationDataStructureElementValue(changedField.getChangedFrom());
    			applicationAuditChangedFrom.setAuditDataStructureReadIndicator(true);
    			AppAuditT auditTChangedFrom = (AppAuditT) new DataMapper().map(applicationAuditChangedFrom);
    			listAppAuditT.add(auditTChangedFrom);
    			
    			ApplicationAudit applicationAuditChangedTo = new ApplicationAudit();
    			applicationAuditChangedTo.setRecordCreateTimestamp(now);
    			applicationAuditChangedTo.setRecordUpdateTimestamp(now);
    			applicationAuditChangedTo.setUserGloballyUniqueIdentifier(staffProfileChangedLog.getUserGuid());
    			applicationAuditChangedTo.setAuditHost(auditHost);
    			applicationAuditChangedTo.setDataSecurityClassificationID(changedField.getChangedField());
    			applicationAuditChangedTo.setApplicationDataStructureElementValue(changedField.getChangedTo());
    			applicationAuditChangedTo.setAuditDataStructureWriteIndicator(true);
    			AppAuditT auditTChangedTo = (AppAuditT) new DataMapper().map(applicationAuditChangedTo);
    			listAppAuditT.add(auditTChangedTo);
    		}

            ApplicationAudit applicationAuditReasonCode= new ApplicationAudit();
            applicationAuditReasonCode.setRecordCreateTimestamp(now);
            applicationAuditReasonCode.setRecordUpdateTimestamp(now);
            applicationAuditReasonCode.setUserGloballyUniqueIdentifier(staffProfileChangedLog.getUserGuid());
            applicationAuditReasonCode.setAuditHost(auditHost);
            applicationAuditReasonCode.setDataSecurityClassificationID("ReasonCode");
            applicationAuditReasonCode.setApplicationDataStructureElementValue(staffProfileChangedLog.getReasonCode());
            AppAuditT auditTReasonCode = (AppAuditT) new DataMapper().map(applicationAuditReasonCode);
            listAppAuditT.add(auditTReasonCode);

            ApplicationAudit applicationAuditNotes= new ApplicationAudit();
            applicationAuditNotes.setRecordCreateTimestamp(now);
            applicationAuditNotes.setRecordUpdateTimestamp(now);
            applicationAuditNotes.setUserGloballyUniqueIdentifier(staffProfileChangedLog.getUserGuid());
            applicationAuditNotes.setAuditHost(auditHost);
            applicationAuditNotes.setDataSecurityClassificationID("Notes");
            applicationAuditNotes.setApplicationDataStructureElementValue(staffProfileChangedLog.getNotes());
            AppAuditT auditTNotes = (AppAuditT) new DataMapper().map(applicationAuditNotes);
            listAppAuditT.add(auditTNotes);

            result = insertListAppAuditT(listAppAuditT);
        }
        return result;
    }

    private int insertListAppAuditT(List<AppAuditT> listAppAuditT) {
        Connection connection;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(true);

            ArrayDescriptor des = ArrayDescriptor.createDescriptor(ConnectionType.METADATA + ".APP_AUDIT_LST_T", connection);
            ARRAY array = new ARRAY(des, connection, listAppAuditT.toArray());

            String callMethod = String.format("{?=call %s.PKG_AUDIT.INSERT_STAFF_APP_AUDIT(?)", ConnectionType.METADATA);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setArray(2, array);

            callableStatement.execute();

            int resultVal = callableStatement.getInt(1);

            connection.commit();

            return resultVal;

        } catch (Exception e) {

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()));

        } finally {

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }
}
