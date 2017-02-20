package com.sandata.lab.rest.patient.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.extended.admin.AdminPatientParentRelTypeResponse;
import com.sandata.lab.data.model.dl.model.extended.admin.AdminStaffRelTypeResponse;
import com.sandata.lab.data.model.jpub.model.PtAllergyT;
import com.sandata.lab.data.model.jpub.model.PtDmeAndSupplyT;
import com.sandata.lab.data.model.jpub.model.PtNutrRqmtT;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.patient.api.OracleService;
import com.sandata.lab.rest.patient.model.PatientContactDetail;
import com.sandata.lab.rest.patient.model.extended.FindPatientResultExt;
import com.sandata.lab.rest.patient.model.extended.PatientExt;
import com.sandata.lab.rest.patient.model.extended.PatientPayerExt;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
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

            String callMethod = format("{?=call %s.%s(?)}", packageName, methodName);
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

            throw new SandataRuntimeException(format("[%s][%s][%s]: %s: %s",
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

            String callMethod = format("{?=call %s.%s(?)}", packageName, methodName);
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

            throw new SandataRuntimeException(format("[%s][%s][%s]: %s: %s",
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

    public Response findPatients(
        final String patientFilterTyp,
        final List<String> params,
        final String orderByColumn,
        final int page,
        final int pageSize,
        final String orderByDirection,
        final String bsnEntId,
        Date schedFromDateTime,
        Date schedToDateTime,
        String coordinatorId,
        String payerId,
        String contractId,
        String nurseId,
        String medicaidId) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        String includeServiceFilter = "";
        String includeServiceColumn = "";
        if (schedFromDateTime != null && schedToDateTime != null) {
            includeServiceColumn = ",T9.SVC_NAME";
            includeServiceFilter = "INNER JOIN (SELECT BE_ID,PT_ID,STAFF_ID,AUTH_SK,SCHED_EVNT_START_DTIME " +
                "      FROM SCHED_EVNT " +
                "        WHERE SCHED_EVNT_START_DTIME BETWEEN " +
                "                TO_DATE(?, 'YYYY-MM-DD HH24:MI') AND " +
                "                TO_DATE(?, 'YYYY-MM-DD HH24:MI')) T7 " +
                "    ON T1.BE_ID = T7.BE_ID AND T1.PT_ID = T7.PT_ID " +
                "        AND AUTH_SK IS NOT NULL " +
                " " +
                "    INNER JOIN (SELECT BE_ID,AUTH_SK,SVC_NAME " +
                "      FROM AUTH_SVC) T8 " +
                "    ON T1.BE_ID = T8.BE_ID AND T7.AUTH_SK = T8.AUTH_SK " +
                " " +
                "    INNER JOIN (SELECT BE_ID,SVC_NAME " +
                "      FROM SVC) T9 " +
                "    ON T1.BE_ID = T9.BE_ID AND T8.SVC_NAME = T9.SVC_NAME";
        }

        // every patient only have maximum one Coordinator and one Nurse, 
        // so the couple of Coordinator and Nurse for a Patient is unique record
        String adminFilter = " " +
                "LEFT JOIN ( " +
                "SELECT K1.COOR_ADMIN_STAFF_SK, K1.COOR_ADMIN_STAFF_ID, K1.COOR_ADMIN_STAFF_FIRST_NAME, K1.COOR_ADMIN_STAFF_LAST_NAME,  " +
                "            K1.COOR_ADMIN_STAFF_ROLE_XREF_SK, K1.COOR_ADMIN_STAFF_ROLE_NAME,  " +
                "        K1.NURSE_ADMIN_STAFF_SK, K1.NURSE_ADMIN_STAFF_ID, K1.NURSE_ADMIN_STAFF_FIRST_NAME, K1.NURSE_ADMIN_STAFF_LAST_NAME,  " +
                "            K1.NURSE_ADMIN_STAFF_ROLE_XREF_SK, K1.NURSE_ADMIN_STAFF_ROLE_NAME, " +
                "        K2.PT_ID, K2.PT_FIRST_NAME, K2.PT_LAST_NAME, K2.BE_ID " +
                "FROM ( " +
                "    SELECT DISTINCT COOR_ADMIN_STAFF_SK, COOR_ADMIN_STAFF_ID, COOR_ADMIN_STAFF_FIRST_NAME, COOR_ADMIN_STAFF_LAST_NAME,  " +
                "              COOR_ADMIN_STAFF_ROLE_XREF_SK, COOR_ADMIN_STAFF_ROLE_NAME,  " +
                "           NURSE_ADMIN_STAFF_SK, NURSE_ADMIN_STAFF_ID, NURSE_ADMIN_STAFF_FIRST_NAME, NURSE_ADMIN_STAFF_LAST_NAME,  " +
                "              NURSE_ADMIN_STAFF_ROLE_XREF_SK, NURSE_ADMIN_STAFF_ROLE_NAME, " +
                "           COOR_STAFF.PT_ID, COOR_STAFF.BE_ID " +
                "    FROM ( " +
                "        SELECT DISTINCT J1.ADMIN_STAFF_SK AS COOR_ADMIN_STAFF_SK,  " +
                "            J1.ADMIN_STAFF_ID AS COOR_ADMIN_STAFF_ID,  " +
                "            J1.ADMIN_STAFF_FIRST_NAME AS COOR_ADMIN_STAFF_FIRST_NAME,  " +
                "            J1.ADMIN_STAFF_LAST_NAME AS COOR_ADMIN_STAFF_LAST_NAME, " +
                "            J2.ADMIN_STAFF_ROLE_XREF_SK AS COOR_ADMIN_STAFF_ROLE_XREF_SK,  " +
                "            J2.ADMIN_STAFF_ROLE_NAME AS COOR_ADMIN_STAFF_ROLE_NAME,  " +
                "            J3.PT_ID, J1.BE_ID " +
                "        FROM ADMIN_STAFF J1 " +
                "        INNER JOIN " +
                "           (SELECT BE_ID, ADMIN_STAFF_ID, ADMIN_STAFF_ROLE_XREF_SK, ADMIN_STAFF_ROLE_NAME, REC_TERM_TMSTP, CURR_REC_IND, " +
                "                ADMIN_STAFF_ROLE_EFF_DATE, ADMIN_STAFF_ROLE_TERM_DATE " +
                "            FROM ADMIN_STAFF_ROLE_XREF " +
                "            WHERE CURRENT_DATE BETWEEN ADMIN_STAFF_ROLE_EFF_DATE AND ADMIN_STAFF_ROLE_TERM_DATE " +
                "                AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                "            ) J2 ON J1.ADMIN_STAFF_ID = J2.ADMIN_STAFF_ID AND J1.BE_ID = J2.BE_ID " +
                "        INNER JOIN " +
                "           (SELECT BE_ID, ADMIN_STAFF_ID, PT_ID, REC_TERM_TMSTP, CURR_REC_IND, ADMIN_STAFF_PT_EFF_DATE, ADMIN_STAFF_PT_TERM_DATE " +
                "            FROM ADMIN_STAFF_PT " +
                "            WHERE CURRENT_DATE BETWEEN ADMIN_STAFF_PT_EFF_DATE AND ADMIN_STAFF_PT_TERM_DATE " +
                "                AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                "            ) J3 ON J1.BE_ID = J3.BE_ID AND J1.ADMIN_STAFF_ID = J3.ADMIN_STAFF_ID " +
                "        WHERE UPPER(J2.ADMIN_STAFF_ROLE_NAME) = 'COORDINATOR' " +
                "        ) COOR_STAFF " +
                "    FULL OUTER JOIN " +
                "        ( " +
                "        SELECT DISTINCT J1.ADMIN_STAFF_SK AS NURSE_ADMIN_STAFF_SK,  " +
                "            J1.ADMIN_STAFF_ID AS NURSE_ADMIN_STAFF_ID,  " +
                "            J1.ADMIN_STAFF_FIRST_NAME AS NURSE_ADMIN_STAFF_FIRST_NAME,  " +
                "            J1.ADMIN_STAFF_LAST_NAME AS NURSE_ADMIN_STAFF_LAST_NAME, " +
                "            J2.ADMIN_STAFF_ROLE_XREF_SK AS NURSE_ADMIN_STAFF_ROLE_XREF_SK,  " +
                "            J2.ADMIN_STAFF_ROLE_NAME AS NURSE_ADMIN_STAFF_ROLE_NAME,  " +
                "            J3.PT_ID, J1.BE_ID " +
                "        FROM ADMIN_STAFF J1 " +
                "        INNER JOIN " +
                "           (SELECT BE_ID, ADMIN_STAFF_ID, ADMIN_STAFF_ROLE_XREF_SK, ADMIN_STAFF_ROLE_NAME, REC_TERM_TMSTP, CURR_REC_IND, " +
                "                ADMIN_STAFF_ROLE_EFF_DATE, ADMIN_STAFF_ROLE_TERM_DATE " +
                "            FROM ADMIN_STAFF_ROLE_XREF " +
                "            WHERE CURRENT_DATE BETWEEN ADMIN_STAFF_ROLE_EFF_DATE AND ADMIN_STAFF_ROLE_TERM_DATE " +
                "                AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                "            ) J2 ON J1.ADMIN_STAFF_ID = J2.ADMIN_STAFF_ID AND J1.BE_ID = J2.BE_ID " +
                "        INNER JOIN " +
                "           (SELECT BE_ID, ADMIN_STAFF_ID, PT_ID, REC_TERM_TMSTP, CURR_REC_IND, ADMIN_STAFF_PT_EFF_DATE, ADMIN_STAFF_PT_TERM_DATE " +
                "            FROM ADMIN_STAFF_PT " +
                "            WHERE CURRENT_DATE BETWEEN ADMIN_STAFF_PT_EFF_DATE AND ADMIN_STAFF_PT_TERM_DATE " +
                "                AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                "            ) J3 ON J1.BE_ID = J3.BE_ID AND J1.ADMIN_STAFF_ID = J3.ADMIN_STAFF_ID " +
                "        WHERE UPPER(J2.ADMIN_STAFF_ROLE_NAME) = 'NURSE' " +
                "        ) NURSE_STAFF ON COOR_STAFF.PT_ID = NURSE_STAFF.PT_ID AND COOR_STAFF.BE_ID = NURSE_STAFF.BE_ID " +
                ") K1 " +
                "INNER JOIN " +
                "    (SELECT BE_ID, PT_ID, PT_FIRST_NAME, PT_LAST_NAME, REC_TERM_TMSTP, CURR_REC_IND " +
                "    FROM PT " +
                "    WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                "    ) K2 ON K1.BE_ID = K2.BE_ID AND K1.PT_ID = K2.PT_ID " +
                ") T10 " +
                "ON T1.BE_ID = T10.BE_ID AND T1.PT_ID = T10.PT_ID ";


        String adminCondition = "";
        if (!StringUtil.IsNullOrEmpty(coordinatorId) && !StringUtil.IsNullOrEmpty(nurseId)) {
            if (!patientFilterTyp.trim().isEmpty() && !patientFilterTyp.trim().endsWith(" AND")) {
                adminCondition += " AND ";
            }
            adminCondition += " (T10.COOR_ADMIN_STAFF_ID = ? OR T10.NURSE_ADMIN_STAFF_ID = ?) AND ";
        } else if (!StringUtil.IsNullOrEmpty(coordinatorId)) {
            if (!patientFilterTyp.trim().isEmpty() && !patientFilterTyp.trim().endsWith(" AND")) {
                adminCondition += " AND ";
            }
            adminCondition += " T10.COOR_ADMIN_STAFF_ID = ? AND ";
        } else if (!StringUtil.IsNullOrEmpty(nurseId)) {
            if (!patientFilterTyp.trim().isEmpty() && !patientFilterTyp.trim().endsWith(" AND")) {
                adminCondition += " AND ";
            }
            adminCondition += " T10.NURSE_ADMIN_STAFF_ID = ? AND ";
        } 

        String payerContractFilter = "";
        String payerContractCondition = "";
        if (!StringUtil.IsNullOrEmpty(payerId) || !StringUtil.IsNullOrEmpty(contractId)) {
            payerContractCondition = !patientFilterTyp.trim().isEmpty() && !patientFilterTyp.trim().endsWith(" AND") ? " AND " : "";
            payerContractFilter = " " +
                    "INNER JOIN " +
                    "   (SELECT BE_ID, PT_ID, PAYER_ID, CONTR_ID, REC_TERM_TMSTP, CURR_REC_IND " +
                    "   FROM PT_PAYER " +
                    "   WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) PT_PAYER " +
                    "ON T1.BE_ID = PT_PAYER.BE_ID AND T1.PT_ID = PT_PAYER.PT_ID ";

            if (!StringUtil.IsNullOrEmpty(payerId)) {
                payerContractFilter = payerContractFilter +
                        " " +
                        "INNER JOIN " +
                        "   (SELECT BE_ID, PAYER_ID, REC_TERM_TMSTP, CURR_REC_IND " +
                        "   FROM PAYER " +
                        "   WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) PAYER " +
                        "ON T1.BE_ID = PAYER.BE_ID AND PT_PAYER.PAYER_ID = PAYER.PAYER_ID ";
                payerContractCondition += " PAYER.PAYER_ID = ? AND ";
            }

            if (!StringUtil.IsNullOrEmpty(contractId)) {
                payerContractFilter = payerContractFilter +
                        " " +
                        "INNER JOIN " +
                        "   (SELECT BE_ID, PAYER_ID, CONTR_ID, REC_TERM_TMSTP, CURR_REC_IND " +
                        "   FROM CONTR " +
                        "   WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) CONTR " +
                        "ON T1.BE_ID = CONTR.BE_ID AND PT_PAYER.PAYER_ID = CONTR.PAYER_ID AND PT_PAYER.CONTR_ID = CONTR.CONTR_ID ";
                payerContractCondition += " CONTR.CONTR_ID = ? AND ";
            }
        }

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                    "  SELECT * FROM ( " +
                    "    (SELECT DISTINCT T1.PT_ID,T1.PT_SK,T1.BE_ID,T1.PT_FIRST_NAME,T1.PT_LAST_NAME,T1.PT_MIDDLE_NAME,T1.PT_DOB, T1.PT_STATUS_NAME, T1.PT_PRIO_LVL_CODE, T1.PT_MEDICAID_ID, " +
                    sqlGetAddressColumn("PT_ADDR1") + "," +
                    sqlGetAddressColumn("PT_ADDR2") + "," +
                    sqlGetAddressColumn("PT_APT_NUM") + "," +
                    sqlGetAddressColumn("PT_CITY") + "," +
                    sqlGetAddressColumn("PT_STATE") + "," +
                    sqlGetAddressColumn("PT_PSTL_CODE") + "," +
                    sqlGetAddressColumn("PT_ZIP4") + "," +
                    "      T3.BE_NAME,T3.BE_TYP,T3.BE_PRMY_ADDR1,T3.BE_PRMY_ADDR2,T3.BE_PRMY_CITY, " +
                    "      T3.BE_PRMY_STATE,T3.BE_PRMY_PSTL_CODE,T3.BE_PRMY_ZIP4,T3.BE_PRMY_PHONE_1, " +
                    "      (SELECT PT_PHONE FROM  " +
                    "          (" +
                    "              SELECT P1.PT_PHONE, P1.PT_CONT_PHONE_QLFR, NVL(P1.PT_PHONE_PRMY_IND, 0) AS PT_PHONE_PRMY_IND, " +
                    "                     P1.PT_ID, P1.BE_ID, P1.REC_CREATE_TMSTP " +
                    "              FROM PT_CONT_PHONE P1 " +
                    "              WHERE P1.PT_PHONE IS NOT NULL " +
                    "                  AND (TO_CHAR(P1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND P1.CURR_REC_IND = 1) " +
                    "              ORDER BY P1.PT_PHONE_PRMY_IND DESC, " +
                    "                       DECODE(UPPER(P1.PT_CONT_PHONE_QLFR), 'HOME', 1, 'MOBILE', 2, 'WORK', 3, 'FAX', 4, 5), " +
                    "                       REC_CREATE_TMSTP DESC " +
                    "          ) P2 WHERE (P2.PT_ID = T1.PT_ID AND P2.BE_ID = T1.BE_ID) AND ROWNUM = 1 " +
                    "      ) AS PT_PHONE, " +

                    "      T6.PT_EMAIL," +
                    // all nurses and coordinators columns
                    "      T10.COOR_ADMIN_STAFF_SK, T10.COOR_ADMIN_STAFF_ID, T10.COOR_ADMIN_STAFF_FIRST_NAME, T10.COOR_ADMIN_STAFF_LAST_NAME, " +
                    "      T10.COOR_ADMIN_STAFF_ROLE_XREF_SK, T10.COOR_ADMIN_STAFF_ROLE_NAME, " +
                    "      T10.NURSE_ADMIN_STAFF_SK, T10.NURSE_ADMIN_STAFF_ID, T10.NURSE_ADMIN_STAFF_FIRST_NAME, T10.NURSE_ADMIN_STAFF_LAST_NAME, " +
                    "      T10.NURSE_ADMIN_STAFF_ROLE_XREF_SK, T10.NURSE_ADMIN_STAFF_ROLE_NAME " +
                    "      %s" +
                    "      FROM PT T1 " +
                    " " +
                    "      LEFT JOIN (SELECT BE_ID,BE_NAME,BE_TYP,BE_PRMY_ADDR1,BE_PRMY_ADDR2,BE_PRMY_CITY, " +
                    "      BE_PRMY_STATE,BE_PRMY_PSTL_CODE,BE_PRMY_ZIP4,BE_PRMY_PHONE_1,REC_TERM_TMSTP,CURR_REC_IND " +
                    "        FROM BE) T3 " +
                    "      ON T1.BE_ID = T3.BE_ID AND " +
                    "        (TO_CHAR(T3.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T3.CURR_REC_IND = 1) " +
                    " " +
                    "      LEFT OUTER JOIN (SELECT PT_ID,BE_ID,PT_EMAIL_PRMY_IND,PT_EMAIL,REC_TERM_TMSTP,CURR_REC_IND " +
                    "        FROM PT_CONT_EMAIL WHERE " +
                    "        (PT_EMAIL_PRMY_IND IS NOT NULL AND PT_EMAIL_PRMY_IND = 1 " +
                    "          AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                    "          AND PT_EMAIL IS NOT NULL) T6 " +
                    "      ON T1.PT_ID = T6.PT_ID AND T1.BE_ID = T6.BE_ID " +
                    " " +
                    "      %s " +   // includeServiceFilter
                    " " +
                    "      %s " +   // adminFilter
                    " " +
                    "      %s " +   // payerContractFilter
                    " " +
                    "      WHERE " +
                    " " +
                    "      %s " +   // patientFilterTyp
                    " " +
                    "      %s " +   // adminCondition
                    " " +
                    "      %s " +   // payerContractCondition
                    " " +
                    "      (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1) AND " +
                    " " +
                    "      T1.BE_ID = ? " +
                    " " +
                    "      ORDER BY %s %s " +
                    " " +
                    "  ) " +
                    ")  ) R1) " +
                    " " +
                    "WHERE ROW_NUMBER BETWEEN %d AND %d ",

                includeServiceColumn,
                includeServiceFilter,
                adminFilter,
                payerContractFilter,
                patientFilterTyp,
                adminCondition,
                payerContractCondition,
                orderByColumn,
                orderByDirection,
                fromRow,
                toRow);
            
            preparedStatement = connection.prepareStatement(sql);

            int index = 1;

            boolean setService = false;
            if (schedFromDateTime != null && schedToDateTime != null) {
                setService = true;
                preparedStatement.setString(index++, ORACLE_DATE_TIME_FORMAT.format(schedFromDateTime));
                preparedStatement.setString(index++, ORACLE_DATE_TIME_FORMAT.format(schedToDateTime));
            }

            for (String param : params) {
                preparedStatement.setString(index++, param);
            }

            if (!StringUtil.IsNullOrEmpty(coordinatorId)) {
                preparedStatement.setString(index++, coordinatorId);
            }
            
            if (!StringUtil.IsNullOrEmpty(nurseId)) {
                preparedStatement.setString(index++, nurseId);
            }

            if (!StringUtil.IsNullOrEmpty(payerId)) {
                preparedStatement.setString(index++, payerId);
            }

            if (!StringUtil.IsNullOrEmpty(contractId)) {
                preparedStatement.setString(index++, contractId);
            }

            preparedStatement.setString(index, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<FindPatientResult> findPatientResults = new ArrayList<>();

            Response response = new Response();
            response.setData(findPatientResults);
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(orderByDirection);

            while (resultSet.next()) {

                FindPatientResultExt findPatientResult = new FindPatientResultExt();

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());
                }

                findPatientResult.setPatientID(resultSet.getString("PT_ID"));
                findPatientResult.setPatientSk(BigInteger.valueOf(resultSet.getBigDecimal("PT_SK").longValue()));
                findPatientResult.setPatientFirstName(resultSet.getString("PT_FIRST_NAME"));
                findPatientResult.setPatientLastName(resultSet.getString("PT_LAST_NAME"));
                findPatientResult.setPatientMiddleName(resultSet.getString("PT_MIDDLE_NAME"));

                Timestamp dob = resultSet.getTimestamp("PT_DOB");
                if (dob != null) {
                    findPatientResult.setPatientDateOfBirth(new Date(dob.getTime()));
                }

                findPatientResult.setPatientPhone(resultSet.getString("PT_PHONE"));
                findPatientResult.setPatientEmail(resultSet.getString("PT_EMAIL"));
                findPatientResult.setPatientAddressLine1(resultSet.getString("PT_ADDR1"));
                findPatientResult.setPatientAddressLine2(resultSet.getString("PT_ADDR2"));
                findPatientResult.setPatientAptNumber(resultSet.getString("PT_APT_NUM"));
                findPatientResult.setPatientCity(resultSet.getString("PT_CITY"));
                findPatientResult.setPatientState(resultSet.getString("PT_STATE"));
                findPatientResult.setPatientPostalCode(resultSet.getString("PT_PSTL_CODE"));
                findPatientResult.setPatientZip4(resultSet.getString("PT_ZIP4"));
                
                try {
                	findPatientResult.setStatus(PatientStatusName.fromValue(resultSet.getString("PT_STATUS_NAME"))); 
                } catch (Exception e) {
                	// Not a valid PatientStatusName
                }
                findPatientResult.setPriorityLevel(resultSet.getString("PT_PRIO_LVL_CODE"));

                //dmr 2015-11-30: Vitaliy removed BE_SK from Patient table
                //dmr--findPatientResult.setBusinessEntitySK(BigInteger.valueOf(resultSet.getBigDecimal("BE_SK").longValue()));

                findPatientResult.setBusinessEntityId(resultSet.getString("BE_ID"));

                findPatientResult.setBusinessEntityName(resultSet.getString("BE_NAME"));
                findPatientResult.setBusinessEntityType(resultSet.getString("BE_TYP"));
                findPatientResult.setBusinessEntityPrimaryAddressLine1(resultSet.getString("BE_PRMY_ADDR1"));
                findPatientResult.setBusinessEntityPrimaryAddressLine2(resultSet.getString("BE_PRMY_ADDR2"));
                findPatientResult.setBusinessEntityPrimaryCity(resultSet.getString("BE_PRMY_CITY"));
                findPatientResult.setBusinessEntityPrimaryState(resultSet.getString("BE_PRMY_STATE"));
                findPatientResult.setBusinessEntityPrimaryPostalCode(resultSet.getString("BE_PRMY_PSTL_CODE"));
                findPatientResult.setBusinessEntityPrimaryZip4(resultSet.getString("BE_PRMY_ZIP4"));
                findPatientResult.setBusinessEntityContactPhone(resultSet.getString("BE_PRMY_PHONE_1"));

                if (setService) {
                    findPatientResult.setService(resultSet.getString("SVC_NAME"));
                }

                String coordinatorStaffId = resultSet.getString("COOR_ADMIN_STAFF_ID");
                if(!StringUtil.IsNullOrEmpty(coordinatorStaffId)){
                    //SAN-3841: There is only coordinator for patient at once.
                    AdminPatientParentRelTypeResponse coordinator = new AdminPatientParentRelTypeResponse();
                    coordinator.setAdministrativeStaffID(coordinatorStaffId);
                    coordinator.setAdministrativeStaffFirstName(resultSet.getString("COOR_ADMIN_STAFF_FIRST_NAME"));
                    coordinator.setAdministrativeStaffLastName(resultSet.getString("COOR_ADMIN_STAFF_LAST_NAME"));
                    coordinator.setAdministrativeStaffSK(BigInteger.valueOf(resultSet.getBigDecimal("COOR_ADMIN_STAFF_SK").longValue()));
                    coordinator.setAdministrativeStaffRelationshipTypeName(resultSet.getString("COOR_ADMIN_STAFF_ROLE_NAME"));
                    findPatientResult.setCoordinator(coordinator);
                }
                
                String nurseStaffId = resultSet.getString("NURSE_ADMIN_STAFF_ID");
                if(!StringUtil.IsNullOrEmpty(nurseStaffId)){
                    //SAN-3841: There is only nurse for patient at once.
                    AdminPatientParentRelTypeResponse nurse = new AdminPatientParentRelTypeResponse();
                    nurse.setAdministrativeStaffID(nurseStaffId);
                    nurse.setAdministrativeStaffFirstName(resultSet.getString("NURSE_ADMIN_STAFF_FIRST_NAME"));
                    nurse.setAdministrativeStaffLastName(resultSet.getString("NURSE_ADMIN_STAFF_LAST_NAME"));
                    nurse.setAdministrativeStaffSK(BigInteger.valueOf(resultSet.getBigDecimal("NURSE_ADMIN_STAFF_SK").longValue()));
                    nurse.setAdministrativeStaffRelationshipTypeName(resultSet.getString("NURSE_ADMIN_STAFF_ROLE_NAME"));
                    findPatientResult.setNurse(nurse);
                }
                findPatientResult.setMedicaidId(resultSet.getString("PT_MEDICAID_ID"));

                findPatientResults.add(findPatientResult);
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
    
    private String sqlGetAddressColumn(String columnName) {
        return String.format(
                "(SELECT %s FROM " +
                "    (" +
                "        SELECT A1.* " +
                "        FROM PT_CONT_ADDR A1 " +
                "        WHERE (TO_CHAR(A1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND A1.CURR_REC_IND = 1) " +
                "            ORDER BY DECODE(UPPER(A1.ADDR_PRIO_NAME), 'PRIMARY', 1, 'SECONDARY', 2, 'TERTIARY', 3, 'OTHER', 4, 5)" +
                "        " +
                "    ) A2 WHERE (A2.PT_ID = T1.PT_ID AND A2.BE_ID = T1.BE_ID) AND ROWNUM = 1 " +
                ") AS %s"
                , columnName, columnName);
    }

    @Override
    public long execute(String packageName, String methodName, long sequenceKey) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String callMethod = format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
            callableStatement.setLong(2, sequenceKey);
            callableStatement.execute();
            long result =  callableStatement.getLong(1);

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

            throw new SandataRuntimeException(format("[%s][%s]: %s: %s",
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

            String callMethod = format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
            callableStatement.setObject(2, jpubType);
            callableStatement.execute();
            long result =  callableStatement.getLong(1);

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

            throw new SandataRuntimeException(format("[%s][%s]: %s: %s",
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

            String callMethod = format("{?=call %s.%s.%s(?)}", connectionType, packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
            callableStatement.setObject(2, jpubType);
            callableStatement.execute();
            long result =  callableStatement.getLong(1);
            return result;
        } catch (Exception e) {

            // NOTE: Rollback (if necessary) should be handled by caller since they passed in the connection...
            throw new SandataRuntimeException(format("[%s][%s]: %s: %s",
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

            String sql = format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                " " +
                "  SELECT * FROM ( " +
                " " +
                "      (SELECT DISTINCT PT_FIRST_NAME FROM PT " +
                "          WHERE PT_FIRST_NAME IS NOT NULL AND " +
                "          BE_ID = ? ORDER BY PT_FIRST_NAME ASC) " +
                "  ) " +
                " " +
                ") R1) " +
                " " +
                "WHERE ROW_NUMBER BETWEEN %d AND %d", fromRow, toRow);

            preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            Response response = new Response();
            response.setPage(page);
            response.setPageSize(pageSize);

            List<DistinctColumn> distinctColumns = null;
            while (resultSet.next()) {

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());
                }

                if (distinctColumns == null) {
                    distinctColumns = new ArrayList<>();
                    response.setData(distinctColumns);
                }

                DistinctColumn distinctColumn = new DistinctColumn();
                distinctColumn.setResult(resultSet.getString("PT_FIRST_NAME"));
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

            String sql = format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                " " +
                "  SELECT * FROM ( " +
                " " +
                "      (SELECT DISTINCT PT_LAST_NAME FROM PT " +
                "          WHERE PT_LAST_NAME IS NOT NULL AND " +
                "          BE_ID = ? ORDER BY PT_LAST_NAME ASC) " +
                "  ) " +
                " " +
                ") R1) " +
                " " +
                "WHERE ROW_NUMBER BETWEEN %d AND %d", fromRow, toRow);

            preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            Response response = new Response();
            response.setPage(page);
            response.setPageSize(pageSize);

            List<DistinctColumn> distinctColumns = null;
            while (resultSet.next()) {

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());
                }

                if (distinctColumns == null) {
                    distinctColumns = new ArrayList<>();
                    response.setData(distinctColumns);
                }

                DistinctColumn distinctColumn = new DistinctColumn();
                distinctColumn.setResult(resultSet.getString("PT_LAST_NAME"));
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
        }
    }

    /**
     * Checks if the @patientId is unique and validates the max length.
     *
     * @param patientId The id to validate.
     * @param bsnEntId  The Business Entity ID since the @patientId does not have to be unique across George.
     * @return Return TRUE if the @patientId is valid, otherwise return FALSE.
     */
    public boolean validatePatientId(String patientId, String bsnEntId, SandataLogger logger) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        boolean bResult = true;
        try {

            if (patientId == null) {
                logger.error("validatePatientId: ERROR: PatientID can not be NULL!");
                return false;
            }

            if (patientId.length() > 50) {
                logger.warn(format("validatePatientId: [RULE: PatientID Exceeded Max Length (VARCHAR 50): " +
                    "[PT_ID=%s]: [MAX_LENGTH=%d]", patientId, patientId.length()));
                return false;
            }

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement("SELECT PT_ID FROM PT WHERE PT_ID = ? AND BE_ID = ?");

            int index = 1;
            preparedStatement.setString(index++, patientId);
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                String pid = resultSet.getString("PT_ID");
                bResult = !(pid != null && pid.length() > 0); //GEOR-4685: Patient Validate endpoint returns incorrect result
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

    public List<PatientAllergy> getPatientAllergyForID(String patientId) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM PT_ALLERGY WHERE PT_ID = ? " +
                "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, patientId);

            resultSet = preparedStatement.executeQuery();

            List<PatientAllergy> resultList =
                (List<PatientAllergy>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.PatientAllergy");

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


    public List<PatientContactAddress> getPatientCntctAddrForID(String patientId, String bsnEntId, String sortOn, String direction) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT * FROM PT_CONT_ADDR WHERE PT_ID = ? AND BE_ID = ? " +
                "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1') " +
                " ORDER BY %s %s", sortOn, direction);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, patientId);
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<PatientContactAddress> resultList =
                (List<PatientContactAddress>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.PatientContactAddress");

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

    public List<PatientContactPhone> getPatientContactPhoneID(String patientId, String bsnEntId, String sortOn, String direction) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT * FROM PT_CONT_PHONE WHERE PT_ID = ? AND BE_ID = ? " +
                " AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1') " +
                " ORDER BY %s %s", sortOn, direction);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, patientId);
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<PatientContactPhone> resultList =
                (List<PatientContactPhone>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.PatientContactPhone");

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

    public List<PatientContactEmail> getPatientContactEmailID(String patientId, String bsnEntId) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM PT_CONT_EMAIL WHERE PT_ID = ? AND BE_ID = ? " +
                "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, patientId);
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<PatientContactEmail> resultList =
                (List<PatientContactEmail>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.PatientContactEmail");

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

    public PatientContactDetail getPatientContactDetails(String patientId, String bsnEntId, String sortOn, String direction) {

        PatientContactDetail result = new PatientContactDetail();

        result.setPatientContactAddressList(getPatientCntctAddrForID(patientId, bsnEntId, sortOn, direction));
        result.setPatientContactPhoneList(getPatientContactPhoneID(patientId, bsnEntId, sortOn, direction));
        result.setPatientContactEmailList(getPatientContactEmailID(patientId, bsnEntId));

        return result;
    }

    public Patient getPatientForID(String patientId, String bsnEntId) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM PT WHERE PT_ID = ? AND BE_ID = ? " +
                "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, patientId);
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<Patient> resultList =
                (List<Patient>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.Patient");

            connection.commit();

            if (resultList.size() > 0) {
                return resultList.get(0);
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

    public List<PatientDesigneeContactDetail> getPdContactDetlForID(String patientId, String bsnEntId, String sortOn, String direction) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT * FROM PD_CONT_DET WHERE PT_ID = ? AND BE_ID = ?" +
                " AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                " ORDER BY %s %s ,PD_CONT_DET_SK %s", sortOn, direction, direction);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, patientId);
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<PatientDesigneeContactDetail> resultList =
                (List<PatientDesigneeContactDetail>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.PatientDesigneeContactDetail");

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

    public Response findPatientsForStaffAgency(
        String staffId,
        String bsnEntId,
        int page,
        int pageSize,
        String sortOn,
        String direction) throws SandataRuntimeException {

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Response response = new Response();
        response.setPage(page);
        response.setPageSize(pageSize);
        response.setOrderByColumn(sortOn);
        response.setOrderByDirection(direction);

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                    " " +
                    "  SELECT * FROM ( " +
                    " " +
                    "      (SELECT * " +
                    "          FROM PT " +
                    "          WHERE BE_ID = ? " +
                    "          AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                    "          ORDER BY %s %s) " +
                    "  ) " +
                    " " +
                    ") R1) " +
                    " " +
                    "WHERE ROW_NUMBER BETWEEN %d AND %d",
                sortOn,
                direction,
                fromRow,
                toRow);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            //dmr--GEOR-2817: On 4/5/2016 The "Description" said the following:
            //dmr--             NOTE: For now he can just return all agency patients since there is no way for staff / patient association at this point.
            //dmr--preparedStatement.setString(index++, staffId);
            //dmr--
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());

                List<Patient> resultList =
                    (List<Patient>) new DataMapper().mapWithOffsetNext(resultSet, "com.sandata.lab.data.model.dl.model.Patient", 2);

                List<PatientExt> patientExtList = new ArrayList<>();
                for (Patient patient : resultList) {
                    PatientExt patientExt = new PatientExt(patient);
                    patientExtList.add(patientExt);
                }

                response.setData(patientExtList);

                return response;
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

    public List<PatientNote> getPatientNoteForID(String patientId, String bsnEntId) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM PT_NOTE WHERE PT_ID = ? AND BE_ID = ?" +
                " AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, patientId);
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<PatientNote> resultList =
                (List<PatientNote>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.PatientNote");

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
     * Update will first call delete to remove any existing records for the given BE_ID and PT_ID, then
     * insert the new values provided.
     *
     * @param bsnEntId
     * @param patientId
     * @param dmeSupplyIdList
     * @throws SandataRuntimeException
     */
    public void updatePatientDmeAndSupplyList(String bsnEntId, String patientId, List<String> dmeSupplyIdList) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            // Delete all dme and supply related for the given patientId (commit only if insert is successful, otherwise rollback)
            deletePatientDmeAndSupplyList(connection, bsnEntId, patientId);

            insertPatientDmeAndSupplyList(connection, bsnEntId, patientId, dmeSupplyIdList);

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

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    /**
     * Deletes ALL records in PT_DME_AND_SUPPLY table by BE_ID and PT_ID. Connection is managed externally
     * to rollback if any error is thrown.
     *
     * @param connection
     * @param bsnEntId
     * @param patientId
     * @throws SandataRuntimeException
     */
    private void deletePatientDmeAndSupplyList(Connection connection, String bsnEntId, String patientId)
        throws SandataRuntimeException {

        CallableStatement callableStatement = null;

        try {
            String callMethod = "{?=call PKG_PATIENT_UTIL.DELETE_PT_DME_SUPPLY_LST(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);

            int index = 1;
            callableStatement.registerOutParameter(index++, OracleTypes.INTEGER);
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, patientId);
            callableStatement.setNull(index++, Types.ARRAY, ConnectionType.COREDATA + ".DME_SUPPLY_ID_LIST");

            callableStatement.execute();
            int result = callableStatement.getInt(1);

            if (result < 0) {
                throw new SandataRuntimeException("Error occurred while updating PT_DME_AND_SUPPLY table");
            }

        } catch (Exception e) {
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

    /**
     * Deletes records in PT_DME_AND_SUPPLY table by BE_ID and PT_ID and DME_SUPPLY_ID
     *
     * @param bsnEntId
     * @param patientId
     * @param dmeSupplyIdList
     * @return
     * @throws SandataRuntimeException
     */
    public int deletePatientDmeAndSupplyList(String bsnEntId, String patientId, List<String> dmeSupplyIdList)
        throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.DME_SUPPLY_ID_LIST", connection);
            ARRAY allergyNameArray = new ARRAY(des, connection, dmeSupplyIdList.toArray());

            String callMethod = "{?=call PKG_PATIENT_UTIL.DELETE_PT_DME_SUPPLY_LST(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);

            int index = 1;
            callableStatement.registerOutParameter(index++, OracleTypes.INTEGER);
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, patientId);
            callableStatement.setArray(index++, allergyNameArray);

            callableStatement.execute();
            int result = callableStatement.getInt(1);

            if (result < 0) {
                throw new SandataRuntimeException("Error occurred while updating PT_DME_AND_SUPPLY table");
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
     * Insert records into PT_DME_AND_SUPPLY table for a given BE_ID and PT_ID. Connection is managed externally
     * to rollback if any error is thrown.
     *
     * @param connection
     * @param bsnEntId
     * @param patientId
     * @param dmeSupplyIdList
     * @throws SandataRuntimeException
     */
    public void insertPatientDmeAndSupplyList(Connection connection, String bsnEntId, String patientId,
                                              List<String> dmeSupplyIdList) throws SandataRuntimeException {


        try {
            for (String dmeSupplyId : dmeSupplyIdList) {
                PatientDurableMedicalEquipmentAndSupply patientDmeAndSupply = new PatientDurableMedicalEquipmentAndSupply();
                patientDmeAndSupply.setBusinessEntityID(bsnEntId);
                patientDmeAndSupply.setPatientID(patientId);
                patientDmeAndSupply.setDurableMedicalEquipmentSupplyID(dmeSupplyId);

                // TODO: setting correct values for below fields
                patientDmeAndSupply.setRecordCreateTimestamp(new Date());
                patientDmeAndSupply.setRecordUpdateTimestamp(new Date());
                patientDmeAndSupply.setRecordEffectiveTimestamp(new Date());
                //dmr--patientDmeAndSupply.setRecordTerminationTimestamp(new Date());
                patientDmeAndSupply.setChangeReasonMemo("MW: insertPatientDmeAndSupplyList");
                //dmr--patientDmeAndSupply.setCurrentRecordIndicator(true);
                patientDmeAndSupply.setChangeVersionID(new BigInteger("1"));

                OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(patientDmeAndSupply);
                PtDmeAndSupplyT jpubObj = (PtDmeAndSupplyT) new DataMapper().map(patientDmeAndSupply);

                execute(connection, ConnectionType.COREDATA, oracleMetadata.packageName(), oracleMetadata.insertMethod(), jpubObj);
            }
        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s",
                e.getClass().getName(), e.getMessage()), e);
        }
    }

    /**
     * Deletes records in PT_NUTR_RQMT table by BE_ID and PT_ID logically
     *
     * @param bsnEntId
     * @param patientId
     * @param nutrRqmtNames
     * @return
     * @throws SandataRuntimeException
     */
    public void updatePatientNutrRqmt(String bsnEntId, String patientId, List<String> nutrRqmtNames)
        throws SandataRuntimeException {

        Connection connection = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            //delete rows logically
            deletePatientNutrRqmt(connection, bsnEntId, patientId);

            //add new records
            insertPatientNutrRqmntList(connection, bsnEntId, patientId, nutrRqmtNames);

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
            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }


    private void deletePatientNutrRqmt(Connection connection, String bsnEntId, String patientId) {

        CallableStatement callableStatement = null;

        try {

            String callMethod = "{?=call PKG_PATIENT_UTIL.DELETE_PT_NUTR_RQMT_LST(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);

            int index = 1;
            callableStatement.registerOutParameter(index++, OracleTypes.INTEGER);
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, patientId);
            callableStatement.setNull(index++, Types.ARRAY, ConnectionType.COREDATA + ".NUTR_RQMT_NAME_LIST");

            callableStatement.execute();
            int result = callableStatement.getInt(1);

            if (result < 0) {
                throw new SandataRuntimeException("Error occurred while updating PT_NUTR_RQMT table");
            }

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


    /**
     * Add new rows into PATIENT_NUTR_RQMNT with given NUTR_RQMNT_NAME
     *
     * @param connection
     * @param bsnEntId
     * @param patientId
     * @param nutrRqmtNames
     * @throws SandataRuntimeException
     */
    public void insertPatientNutrRqmntList(Connection connection, String bsnEntId, String patientId,
                                           List<String> nutrRqmtNames) throws SandataRuntimeException {

        if (nutrRqmtNames == null) {
            return;
        }

        try {
            for (String nutrRqmntName : nutrRqmtNames) {
                PatientNutritionalRequirement patientNutrRqmnt = new PatientNutritionalRequirement();
                patientNutrRqmnt.setBusinessEntityID(bsnEntId);
                patientNutrRqmnt.setPatientID(patientId);

                patientNutrRqmnt.setRecordCreateTimestamp(new Date());
                patientNutrRqmnt.setRecordUpdateTimestamp(new Date());
                patientNutrRqmnt.setRecordEffectiveTimestamp(new Date());
                patientNutrRqmnt.setChangeReasonMemo("MW: insertPatientNutrRqmntList");
                patientNutrRqmnt.setChangeVersionID(new BigInteger("1"));

                patientNutrRqmnt.setNutritionalRequirementName(nutrRqmntName);

                OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(patientNutrRqmnt);
                PtNutrRqmtT jpubObj = (PtNutrRqmtT) new DataMapper().map(patientNutrRqmnt);

                execute(connection, ConnectionType.COREDATA, oracleMetadata.packageName(), oracleMetadata.insertMethod(), jpubObj);
            }
        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s",
                e.getClass().getName(), e.getMessage()), e);
        }
    }


    /**
     * accepts a DELETE request of a list of Nutr Req IDs and removes the items from PT_NUTR_RQMT table
     *
     * @param bsnEntId
     * @param patientId
     * @param nutrRqmtNames
     */
    public int deletePatientnutrRqmntList(String bsnEntId, String patientId, List<String> nutrRqmtNames) {
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.NUTR_RQMT_NAME_LIST", connection);
            ARRAY nutrRqmtNameArray = new ARRAY(des, connection, nutrRqmtNames.toArray());

            String callMethod = "{?=call PKG_PATIENT_UTIL.DELETE_PT_NUTR_RQMT_LST(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);

            int index = 1;
            callableStatement.registerOutParameter(index++, OracleTypes.INTEGER);
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, patientId);
            callableStatement.setArray(index++, nutrRqmtNameArray);

            callableStatement.execute();
            int result = callableStatement.getInt(1);

            if (result < 0) {
                throw new SandataRuntimeException("Error occurred while updating PT_NUTR_RQMT table");
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
     * Insert records into PT_ALLERGY table for a given BE_ID and PT_ID. Connection is managed externally
     * to rollback if any error is thrown.
     *
     * @param connection
     * @param bsnEntId
     * @param patientId
     * @param allergyNameList
     * @throws SandataRuntimeException
     */
    public void insertPatientAllergyList(Connection connection, String bsnEntId, String patientId, List<String> allergyNameList)
        throws SandataRuntimeException {

        if (allergyNameList == null) {
            return;
        }

        try {
            for (String allergyName : allergyNameList) {
                PatientAllergy patientAllergy = new PatientAllergy();
                patientAllergy.setBusinessEntityID(bsnEntId);
                patientAllergy.setPatientID(patientId);
                patientAllergy.setAllergyName(allergyName);
                patientAllergy.setRecordCreateTimestamp(new Date());
                patientAllergy.setRecordUpdateTimestamp(new Date());
                patientAllergy.setRecordEffectiveTimestamp(new Date());
                patientAllergy.setChangeReasonMemo("MW: insertPatientAllergyList");
                patientAllergy.setChangeVersionID(new BigInteger("1"));

                OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(patientAllergy);
                PtAllergyT jpubObj = (PtAllergyT) new DataMapper().map(patientAllergy);

                execute(connection, ConnectionType.COREDATA, oracleMetadata.packageName(), oracleMetadata.insertMethod(), jpubObj);
            }

        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s",
                e.getClass().getName(), e.getMessage()), e);
        }

    }

    /**
     * Marks records in PT_ALLERGY table as deleted by BE_ID and PT_ID and ALLERGY_NAME
     *
     * @param bsnEntId
     * @param patientId
     * @param allergyNameList
     * @return
     * @throws SandataRuntimeException
     */
    public long deletePatientAllergyList(String bsnEntId, String patientId, List<String> allergyNameList)
        throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.ALLERGY_NAME_LIST", connection);
            ARRAY allergyNameArray = new ARRAY(des, connection, allergyNameList.toArray());

            String callMethod = "{?=call PKG_PATIENT_UTIL.DELETE_PT_ALLERGY_LST(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);

            int index = 1;
            callableStatement.registerOutParameter(index++, OracleTypes.BIGINT);
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, patientId);
            callableStatement.setArray(index++, allergyNameArray);

            callableStatement.execute();
            long result = callableStatement.getLong(1);

            if (result < 0) {
                throw new SandataRuntimeException("Error occurred while updating PT_ALLERGY table");
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
     * Marks records in PT_ALLERGY table as deleted by BE_ID and PT_ID. Connection is managed externally
     * to rollback if any error is thrown.
     *
     * @param connection
     * @param bsnEntId
     * @param patientId
     * @throws SandataRuntimeException
     */
    private void deletePatientAllergyList(Connection connection, String bsnEntId, String patientId)
        throws SandataRuntimeException {

        CallableStatement callableStatement = null;

        try {
            String callMethod = "{?=call PKG_PATIENT_UTIL.DELETE_PT_ALLERGY_LST(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);

            int index = 1;
            callableStatement.registerOutParameter(index++, OracleTypes.BIGINT);
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, patientId);
            callableStatement.setNull(index++, Types.ARRAY, ConnectionType.COREDATA + ".ALLERGY_NAME_LIST");

            callableStatement.execute();
            long result = callableStatement.getLong(1);

            if (result < 0) {
                throw new SandataRuntimeException("Error occurred while updating PT_ALLERGY table");
            }

        } catch (Exception e) {
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

    /**
     * Update will first call delete to mark deleted any existing records for the given BSN_ENT_ID and PATIENT_ID, then
     * insert the new values provided.
     *
     * @param bsnEntId
     * @param patientId
     * @param allergyNameList
     * @throws SandataRuntimeException
     */
    public void updatePatientAllergyList(String bsnEntId, String patientId, List<String> allergyNameList) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            // Delete all dme and supply related for the given patientId (commit only if insert is successful, otherwise rollback)
            deletePatientAllergyList(connection, bsnEntId, patientId);

            insertPatientAllergyList(connection, bsnEntId, patientId, allergyNameList);

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

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    public List<PatientPayerExt> getPatientPayerEligibility(final String patientId, final String businessEntityId) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            List<PatientPayerExt> patientPayerExts = new ArrayList<>();

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM PT_PAYER WHERE PT_ID = ? AND BE_ID = ? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";

            preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            preparedStatement.setString(index++, patientId);
            preparedStatement.setString(index++, businessEntityId);

            resultSet = preparedStatement.executeQuery();

            List<PatientPayer> patientPayerList = (List<PatientPayer>) new DataMapper().map(resultSet,
                "com.sandata.lab.data.model.dl.model.PatientPayer");

            resultSet.close();
            preparedStatement.close();
            preparedStatement = null;

            String eligibilitySql = "SELECT * FROM ELIG WHERE PT_ID = ? AND BE_ID = ? AND PAYER_ID = ? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";
            for (PatientPayer patientPayer : patientPayerList) {
                PatientPayerExt patientPayerExt = new PatientPayerExt(patientPayer);

                preparedStatement = connection.prepareStatement(eligibilitySql);
                index = 1;
                preparedStatement.setString(index++, patientId);
                preparedStatement.setString(index++, businessEntityId);
                preparedStatement.setString(index++, patientPayer.getPayerID());

                resultSet = preparedStatement.executeQuery();
                List<Eligibility> eligibilities = (List<Eligibility>) new DataMapper().map(resultSet,
                    "com.sandata.lab.data.model.dl.model.Eligibility");

                if (eligibilities != null && !eligibilities.isEmpty()) {
                    patientPayerExt.setEligibility(eligibilities.get(0));
                }

                patientPayerExts.add(patientPayerExt);

                resultSet.close();
                preparedStatement.close();
                preparedStatement = null;
            }

            connection.commit();

            return patientPayerExts;

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

    public Response findPatientsForService(
        String patientFilterTyp,
        List<String> params,
        String orderByColumn,
        int page,
        int pageSize,
        String orderByDirection,
        String bsnEntId,
        String service,
        Date schedFromDateTime,
        Date schedToDateTime,
        String staffId,
        String coordinatorId,
        String payerId,
        String contractId,
        String nurseId,
        String medicaidId) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        String staffIdFilter = "";
        if (!StringUtil.IsNullOrEmpty(staffId)) {
            staffIdFilter = "AND T7.STAFF_ID = ?";
        }

        // every patient only have maximum one Coordinator and one Nurse, 
        // so the couple of Coordinator and Nurse for a Patient is unique record
        String adminFilter = " " +
                "LEFT JOIN ( " +
                "SELECT K1.COOR_ADMIN_STAFF_SK, K1.COOR_ADMIN_STAFF_ID, K1.COOR_ADMIN_STAFF_FIRST_NAME, K1.COOR_ADMIN_STAFF_LAST_NAME,  " +
                "            K1.COOR_ADMIN_STAFF_ROLE_XREF_SK, K1.COOR_ADMIN_STAFF_ROLE_NAME,  " +
                "        K1.NURSE_ADMIN_STAFF_SK, K1.NURSE_ADMIN_STAFF_ID, K1.NURSE_ADMIN_STAFF_FIRST_NAME, K1.NURSE_ADMIN_STAFF_LAST_NAME,  " +
                "            K1.NURSE_ADMIN_STAFF_ROLE_XREF_SK, K1.NURSE_ADMIN_STAFF_ROLE_NAME, " +
                "        K2.PT_ID, K2.PT_FIRST_NAME, K2.PT_LAST_NAME, K2.BE_ID " +
                "FROM ( " +
                "    SELECT DISTINCT COOR_ADMIN_STAFF_SK, COOR_ADMIN_STAFF_ID, COOR_ADMIN_STAFF_FIRST_NAME, COOR_ADMIN_STAFF_LAST_NAME,  " +
                "              COOR_ADMIN_STAFF_ROLE_XREF_SK, COOR_ADMIN_STAFF_ROLE_NAME,  " +
                "           NURSE_ADMIN_STAFF_SK, NURSE_ADMIN_STAFF_ID, NURSE_ADMIN_STAFF_FIRST_NAME, NURSE_ADMIN_STAFF_LAST_NAME,  " +
                "              NURSE_ADMIN_STAFF_ROLE_XREF_SK, NURSE_ADMIN_STAFF_ROLE_NAME, " +
                "           COOR_STAFF.PT_ID, COOR_STAFF.BE_ID " +
                "    FROM ( " +
                "        SELECT DISTINCT J1.ADMIN_STAFF_SK AS COOR_ADMIN_STAFF_SK,  " +
                "            J1.ADMIN_STAFF_ID AS COOR_ADMIN_STAFF_ID,  " +
                "            J1.ADMIN_STAFF_FIRST_NAME AS COOR_ADMIN_STAFF_FIRST_NAME,  " +
                "            J1.ADMIN_STAFF_LAST_NAME AS COOR_ADMIN_STAFF_LAST_NAME, " +
                "            J2.ADMIN_STAFF_ROLE_XREF_SK AS COOR_ADMIN_STAFF_ROLE_XREF_SK,  " +
                "            J2.ADMIN_STAFF_ROLE_NAME AS COOR_ADMIN_STAFF_ROLE_NAME,  " +
                "            J3.PT_ID, J1.BE_ID " +
                "        FROM ADMIN_STAFF J1 " +
                "        INNER JOIN " +
                "           (SELECT BE_ID, ADMIN_STAFF_ID, ADMIN_STAFF_ROLE_XREF_SK, ADMIN_STAFF_ROLE_NAME, REC_TERM_TMSTP, CURR_REC_IND, " +
                "                ADMIN_STAFF_ROLE_EFF_DATE, ADMIN_STAFF_ROLE_TERM_DATE " +
                "            FROM ADMIN_STAFF_ROLE_XREF " +
                "            WHERE CURRENT_DATE BETWEEN ADMIN_STAFF_ROLE_EFF_DATE AND ADMIN_STAFF_ROLE_TERM_DATE " +
                "                AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                "            ) J2 ON J1.ADMIN_STAFF_ID = J2.ADMIN_STAFF_ID AND J1.BE_ID = J2.BE_ID " +
                "        INNER JOIN " +
                "           (SELECT BE_ID, ADMIN_STAFF_ID, PT_ID, REC_TERM_TMSTP, CURR_REC_IND, ADMIN_STAFF_PT_EFF_DATE, ADMIN_STAFF_PT_TERM_DATE " +
                "            FROM ADMIN_STAFF_PT " +
                "            WHERE CURRENT_DATE BETWEEN ADMIN_STAFF_PT_EFF_DATE AND ADMIN_STAFF_PT_TERM_DATE " +
                "                AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                "            ) J3 ON J1.BE_ID = J3.BE_ID AND J1.ADMIN_STAFF_ID = J3.ADMIN_STAFF_ID " +
                "        WHERE UPPER(J2.ADMIN_STAFF_ROLE_NAME) = 'COORDINATOR' " +
                "        ) COOR_STAFF " +
                "    FULL OUTER JOIN " +
                "        ( " +
                "        SELECT DISTINCT J1.ADMIN_STAFF_SK AS NURSE_ADMIN_STAFF_SK,  " +
                "            J1.ADMIN_STAFF_ID AS NURSE_ADMIN_STAFF_ID,  " +
                "            J1.ADMIN_STAFF_FIRST_NAME AS NURSE_ADMIN_STAFF_FIRST_NAME,  " +
                "            J1.ADMIN_STAFF_LAST_NAME AS NURSE_ADMIN_STAFF_LAST_NAME, " +
                "            J2.ADMIN_STAFF_ROLE_XREF_SK AS NURSE_ADMIN_STAFF_ROLE_XREF_SK,  " +
                "            J2.ADMIN_STAFF_ROLE_NAME AS NURSE_ADMIN_STAFF_ROLE_NAME,  " +
                "            J3.PT_ID, J1.BE_ID " +
                "        FROM ADMIN_STAFF J1 " +
                "        INNER JOIN " +
                "           (SELECT BE_ID, ADMIN_STAFF_ID, ADMIN_STAFF_ROLE_XREF_SK, ADMIN_STAFF_ROLE_NAME, REC_TERM_TMSTP, CURR_REC_IND, " +
                "                ADMIN_STAFF_ROLE_EFF_DATE, ADMIN_STAFF_ROLE_TERM_DATE " +
                "            FROM ADMIN_STAFF_ROLE_XREF " +
                "            WHERE CURRENT_DATE BETWEEN ADMIN_STAFF_ROLE_EFF_DATE AND ADMIN_STAFF_ROLE_TERM_DATE " +
                "                AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                "            ) J2 ON J1.ADMIN_STAFF_ID = J2.ADMIN_STAFF_ID AND J1.BE_ID = J2.BE_ID " +
                "        INNER JOIN " +
                "           (SELECT BE_ID, ADMIN_STAFF_ID, PT_ID, REC_TERM_TMSTP, CURR_REC_IND, ADMIN_STAFF_PT_EFF_DATE, ADMIN_STAFF_PT_TERM_DATE " +
                "            FROM ADMIN_STAFF_PT " +
                "            WHERE CURRENT_DATE BETWEEN ADMIN_STAFF_PT_EFF_DATE AND ADMIN_STAFF_PT_TERM_DATE " +
                "                AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                "            ) J3 ON J1.BE_ID = J3.BE_ID AND J1.ADMIN_STAFF_ID = J3.ADMIN_STAFF_ID " +
                "        WHERE UPPER(J2.ADMIN_STAFF_ROLE_NAME) = 'NURSE' " +
                "        ) NURSE_STAFF ON COOR_STAFF.PT_ID = NURSE_STAFF.PT_ID AND COOR_STAFF.BE_ID = NURSE_STAFF.BE_ID " +
                ") K1 " +
                "INNER JOIN " +
                "    (SELECT BE_ID, PT_ID, PT_FIRST_NAME, PT_LAST_NAME, REC_TERM_TMSTP, CURR_REC_IND " +
                "    FROM PT " +
                "    WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                "    ) K2 ON K1.BE_ID = K2.BE_ID AND K1.PT_ID = K2.PT_ID " +
                ") T10 " +
                "ON T1.BE_ID = T10.BE_ID AND T1.PT_ID = T10.PT_ID ";


        String adminCondition = "";
        if (!StringUtil.IsNullOrEmpty(coordinatorId) && !StringUtil.IsNullOrEmpty(nurseId)) {
            if (!patientFilterTyp.trim().isEmpty() && !patientFilterTyp.trim().endsWith(" AND")) {
                adminCondition += " AND ";
            }
            adminCondition += " (T10.COOR_ADMIN_STAFF_ID = ? OR T10.NURSE_ADMIN_STAFF_ID = ?) AND ";
        } else if (!StringUtil.IsNullOrEmpty(coordinatorId)) {
            if (!patientFilterTyp.trim().isEmpty() && !patientFilterTyp.trim().endsWith(" AND")) {
                adminCondition += " AND ";
            }
            adminCondition += " T10.COOR_ADMIN_STAFF_ID = ? AND ";
        } else if (!StringUtil.IsNullOrEmpty(nurseId)) {
            if (!patientFilterTyp.trim().isEmpty() && !patientFilterTyp.trim().endsWith(" AND")) {
                adminCondition += " AND ";
            }
            adminCondition += " T10.NURSE_ADMIN_STAFF_ID = ? AND ";
        }

        String payerContractFilter = "";
        String payerContractCondition = "";
        if (!StringUtil.IsNullOrEmpty(payerId) || !StringUtil.IsNullOrEmpty(contractId)) {
            payerContractCondition = !patientFilterTyp.trim().isEmpty() && !patientFilterTyp.trim().endsWith(" AND") ? " AND " : "";
            payerContractFilter = " " +
                    "INNER JOIN " +
                    "   (SELECT BE_ID, PT_ID, PAYER_ID, CONTR_ID, REC_TERM_TMSTP, CURR_REC_IND " +
                    "   FROM PT_PAYER " +
                    "   WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) PT_PAYER " +
                    "ON T1.BE_ID = PT_PAYER.BE_ID AND T1.PT_ID = PT_PAYER.PT_ID ";

            if (!StringUtil.IsNullOrEmpty(payerId)) {
                payerContractFilter = payerContractFilter +
                        " " +
                        "INNER JOIN " +
                        "   (SELECT BE_ID, PAYER_ID, REC_TERM_TMSTP, CURR_REC_IND " +
                        "   FROM PAYER " +
                        "   WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) PAYER " +
                        "ON T1.BE_ID = PAYER.BE_ID AND PT_PAYER.PAYER_ID = PAYER.PAYER_ID ";
                payerContractCondition += " PAYER.PAYER_ID = ? AND ";
            }

            if (!StringUtil.IsNullOrEmpty(contractId)) {
                payerContractFilter = payerContractFilter +
                        " " +
                        "INNER JOIN " +
                        "   (SELECT BE_ID, PAYER_ID, CONTR_ID, REC_TERM_TMSTP, CURR_REC_IND " +
                        "   FROM CONTR " +
                        "   WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) CONTR " +
                        "ON T1.BE_ID = CONTR.BE_ID AND PT_PAYER.PAYER_ID = CONTR.PAYER_ID AND PT_PAYER.CONTR_ID = CONTR.CONTR_ID ";
                payerContractCondition += " CONTR.CONTR_ID = ? AND ";
            }
        }

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                    "  SELECT * FROM ( " +
                    "    (SELECT DISTINCT T9.SVC_NAME,T1.PT_ID,T1.PT_SK,T1.BE_ID,T1.PT_FIRST_NAME,T1.PT_LAST_NAME,T1.PT_MIDDLE_NAME,T1.PT_DOB, T1.PT_STATUS_NAME, T1.PT_PRIO_LVL_CODE, T1.PT_MEDICAID_ID, " +
                    sqlGetAddressColumn("PT_ADDR1") + "," +
                    sqlGetAddressColumn("PT_ADDR2") + "," +
                    sqlGetAddressColumn("PT_APT_NUM") + "," +
                    sqlGetAddressColumn("PT_CITY") + "," +
                    sqlGetAddressColumn("PT_STATE") + "," +
                    sqlGetAddressColumn("PT_PSTL_CODE") + "," +
                    sqlGetAddressColumn("PT_ZIP4") + "," +
                    "        T3.BE_NAME,T3.BE_TYP,T3.BE_PRMY_ADDR1,T3.BE_PRMY_ADDR2,T3.BE_PRMY_CITY, " +
                    "        T3.BE_PRMY_STATE,T3.BE_PRMY_PSTL_CODE,T3.BE_PRMY_ZIP4,T3.BE_PRMY_PHONE_1, " +
                    "      (SELECT PT_PHONE FROM  " +
                    "          (" +
                    "              SELECT P1.PT_PHONE, P1.PT_CONT_PHONE_QLFR, NVL(P1.PT_PHONE_PRMY_IND, 0) AS PT_PHONE_PRMY_IND, " +
                    "                     P1.PT_ID, P1.BE_ID, P1.REC_CREATE_TMSTP " +
                    "              FROM PT_CONT_PHONE P1 " +
                    "              WHERE P1.PT_PHONE IS NOT NULL " +
                    "                  AND (TO_CHAR(P1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND P1.CURR_REC_IND = 1) " +
                    "              ORDER BY P1.PT_PHONE_PRMY_IND DESC, " +
                    "                       DECODE(UPPER(P1.PT_CONT_PHONE_QLFR), 'HOME', 1, 'MOBILE', 2, 'WORK', 3, 'FAX', 4, 5), " +
                    "                       REC_CREATE_TMSTP DESC " +
                    "          ) P2 WHERE (P2.PT_ID = T1.PT_ID AND P2.BE_ID = T1.BE_ID) AND ROWNUM = 1 " +
                    "      ) AS PT_PHONE, " +
                    "        T6.PT_EMAIL, " +
                    // all nurses and coordinators columns
                    "      T10.COOR_ADMIN_STAFF_SK, T10.COOR_ADMIN_STAFF_ID, T10.COOR_ADMIN_STAFF_FIRST_NAME, T10.COOR_ADMIN_STAFF_LAST_NAME, " +
                    "      T10.COOR_ADMIN_STAFF_ROLE_XREF_SK, T10.COOR_ADMIN_STAFF_ROLE_NAME, " +
                    "      T10.NURSE_ADMIN_STAFF_SK, T10.NURSE_ADMIN_STAFF_ID, T10.NURSE_ADMIN_STAFF_FIRST_NAME, T10.NURSE_ADMIN_STAFF_LAST_NAME, " +
                    "      T10.NURSE_ADMIN_STAFF_ROLE_XREF_SK, T10.NURSE_ADMIN_STAFF_ROLE_NAME " +
                    "      FROM PT T1 " +
                    " " +
                    "    LEFT JOIN (SELECT BE_ID,BE_NAME,BE_TYP,BE_PRMY_ADDR1,BE_PRMY_ADDR2,BE_PRMY_CITY, " +
                    "                BE_PRMY_STATE,BE_PRMY_PSTL_CODE,BE_PRMY_ZIP4,BE_PRMY_PHONE_1,REC_TERM_TMSTP,CURR_REC_IND " +
                    "      FROM BE) T3 " +
                    "    ON T1.BE_ID = T3.BE_ID AND " +
                    "      (TO_CHAR(T3.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T3.CURR_REC_IND = 1) " +
                    " " +
                    "    LEFT OUTER JOIN (SELECT PT_ID,BE_ID,PT_EMAIL_PRMY_IND,PT_EMAIL,REC_TERM_TMSTP,CURR_REC_IND " +
                    "      FROM PT_CONT_EMAIL WHERE " +
                    "        (PT_EMAIL_PRMY_IND IS NOT NULL AND PT_EMAIL_PRMY_IND = 1 " +
                    "          AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                    "          AND PT_EMAIL IS NOT NULL) T6 " +
                    "    ON T1.PT_ID = T6.PT_ID AND T1.BE_ID = T6.BE_ID " +
                    " " +
                    "    INNER JOIN (SELECT BE_ID,PT_ID,STAFF_ID,AUTH_SK,SCHED_EVNT_START_DTIME " +
                    "      FROM SCHED_EVNT " +
                    "        WHERE SCHED_EVNT_START_DTIME BETWEEN " +
                    "                TO_DATE(?, 'YYYY-MM-DD HH24:MI') AND " +
                    "                TO_DATE(?, 'YYYY-MM-DD HH24:MI')) T7 " +
                    "    ON T1.BE_ID = T7.BE_ID AND T1.PT_ID = T7.PT_ID " +
                    "        AND AUTH_SK IS NOT NULL %s " +     // staffIdFilter
                    " " +
                    "    INNER JOIN (SELECT BE_ID,AUTH_SK,SVC_NAME " +
                    "      FROM AUTH_SVC) T8 " +
                    "    ON T1.BE_ID = T8.BE_ID AND T7.AUTH_SK = T8.AUTH_SK " +
                    " " +
                    "    INNER JOIN (SELECT BE_ID,SVC_NAME " +
                    "      FROM SVC) T9 " +
                    "    ON T1.BE_ID = T9.BE_ID AND T8.SVC_NAME = T9.SVC_NAME " +
                    " " +
                    "    %s " +   // coordinatorFilter
                    " " +
                    "    %s " +   // payerContractFilter
                    " " +
                    "    WHERE " +
                    " " +
                    "    %s " +   // patientFilterTyp
                    " " +
                    "    %s " +   // coordinatorCondition
                    " " +
                    "    %s " +   // payerContractCondition
                    " " +
                    "      (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1) " +
                    "      AND T1.BE_ID = ? AND T9.SVC_NAME = ? " +
                    " " +
                    "    ORDER BY %s %s " +
                    "  ) " +
                    ")  ) R1) " +
                    " " +
                    "WHERE ROW_NUMBER BETWEEN %d AND %d ",

                staffIdFilter, adminFilter, payerContractFilter, patientFilterTyp, adminCondition, payerContractCondition,
                    orderByColumn, orderByDirection, fromRow, toRow);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, ORACLE_DATE_TIME_FORMAT.format(schedFromDateTime));
            preparedStatement.setString(index++, ORACLE_DATE_TIME_FORMAT.format(schedToDateTime));

            if (!StringUtil.IsNullOrEmpty(staffId)) {
                preparedStatement.setString(index++, staffId);
            }

            for (String param : params) {
                preparedStatement.setString(index++, param);
            }

            if (!StringUtil.IsNullOrEmpty(coordinatorId)) {
                preparedStatement.setString(index++, coordinatorId);
            }
            
            if (!StringUtil.IsNullOrEmpty(nurseId)) {
                preparedStatement.setString(index++, nurseId);
            }

            if (!StringUtil.IsNullOrEmpty(payerId)) {
                preparedStatement.setString(index++, payerId);
            }

            if (!StringUtil.IsNullOrEmpty(contractId)) {
                preparedStatement.setString(index++, contractId);
            }

            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, service);

            resultSet = preparedStatement.executeQuery();

            List<FindPatientResultExt> findPatientResults = new ArrayList<>();

            Response response = new Response();
            response.setData(findPatientResults);
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(orderByDirection);

            while (resultSet.next()) {

                FindPatientResultExt findPatientResult = new FindPatientResultExt();

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());
                }

                findPatientResult.setPatientID(resultSet.getString("PT_ID"));
                findPatientResult.setPatientSk(BigInteger.valueOf(resultSet.getBigDecimal("PT_SK").longValue()));
                findPatientResult.setPatientFirstName(resultSet.getString("PT_FIRST_NAME"));
                findPatientResult.setPatientLastName(resultSet.getString("PT_LAST_NAME"));
                findPatientResult.setPatientMiddleName(resultSet.getString("PT_MIDDLE_NAME"));

                Timestamp dob = resultSet.getTimestamp("PT_DOB");
                if (dob != null) {
                    findPatientResult.setPatientDateOfBirth(new Date(dob.getTime()));
                }

                findPatientResult.setPatientPhone(resultSet.getString("PT_PHONE"));
                findPatientResult.setPatientEmail(resultSet.getString("PT_EMAIL"));
                findPatientResult.setPatientAddressLine1(resultSet.getString("PT_ADDR1"));
                findPatientResult.setPatientAddressLine2(resultSet.getString("PT_ADDR2"));
                findPatientResult.setPatientAptNumber(resultSet.getString("PT_APT_NUM"));
                findPatientResult.setPatientCity(resultSet.getString("PT_CITY"));
                findPatientResult.setPatientState(resultSet.getString("PT_STATE"));
                findPatientResult.setPatientPostalCode(resultSet.getString("PT_PSTL_CODE"));
                findPatientResult.setPatientZip4(resultSet.getString("PT_ZIP4"));
                
                try {
                	findPatientResult.setStatus(PatientStatusName.fromValue(resultSet.getString("PT_STATUS_NAME"))); 
                } catch (Exception e) {
                	// Not a valid PatientStatusName
                }
                findPatientResult.setPriorityLevel(resultSet.getString("PT_PRIO_LVL_CODE"));

                //dmr 2015-11-30: Vitaliy removed BE_SK from Patient table
                //dmr--findPatientResult.setBusinessEntitySK(BigInteger.valueOf(resultSet.getBigDecimal("BE_SK").longValue()));

                findPatientResult.setBusinessEntityId(resultSet.getString("BE_ID"));

                findPatientResult.setBusinessEntityName(resultSet.getString("BE_NAME"));
                findPatientResult.setBusinessEntityType(resultSet.getString("BE_TYP"));
                findPatientResult.setBusinessEntityPrimaryAddressLine1(resultSet.getString("BE_PRMY_ADDR1"));
                findPatientResult.setBusinessEntityPrimaryAddressLine2(resultSet.getString("BE_PRMY_ADDR2"));
                findPatientResult.setBusinessEntityPrimaryCity(resultSet.getString("BE_PRMY_CITY"));
                findPatientResult.setBusinessEntityPrimaryState(resultSet.getString("BE_PRMY_STATE"));
                findPatientResult.setBusinessEntityPrimaryPostalCode(resultSet.getString("BE_PRMY_PSTL_CODE"));
                findPatientResult.setBusinessEntityPrimaryZip4(resultSet.getString("BE_PRMY_ZIP4"));
                findPatientResult.setBusinessEntityContactPhone(resultSet.getString("BE_PRMY_PHONE_1"));

                findPatientResult.setService(resultSet.getString("SVC_NAME"));

                String coordinatorStaffId = resultSet.getString("COOR_ADMIN_STAFF_ID");
                if(!StringUtil.IsNullOrEmpty(coordinatorStaffId)){
                    //SAN-3841: There is only coordinator for patient at once.
                    AdminPatientParentRelTypeResponse coordinator = new AdminPatientParentRelTypeResponse();
                    coordinator.setAdministrativeStaffID(coordinatorStaffId);
                    coordinator.setAdministrativeStaffFirstName(resultSet.getString("COOR_ADMIN_STAFF_FIRST_NAME"));
                    coordinator.setAdministrativeStaffLastName(resultSet.getString("COOR_ADMIN_STAFF_LAST_NAME"));
                    coordinator.setAdministrativeStaffSK(BigInteger.valueOf(resultSet.getBigDecimal("COOR_ADMIN_STAFF_SK").longValue()));
                    coordinator.setAdministrativeStaffRelationshipTypeName(resultSet.getString("COOR_ADMIN_STAFF_ROLE_NAME"));
                    findPatientResult.setCoordinator(coordinator);
                }
                
                String nurseStaffId = resultSet.getString("NURSE_ADMIN_STAFF_ID");
                if(!StringUtil.IsNullOrEmpty(nurseStaffId)){
                    //SAN-3841: There is only nurse for patient at once.
                    AdminPatientParentRelTypeResponse nurse = new AdminPatientParentRelTypeResponse();
                    nurse.setAdministrativeStaffID(nurseStaffId);
                    nurse.setAdministrativeStaffFirstName(resultSet.getString("NURSE_ADMIN_STAFF_FIRST_NAME"));
                    nurse.setAdministrativeStaffLastName(resultSet.getString("NURSE_ADMIN_STAFF_LAST_NAME"));
                    nurse.setAdministrativeStaffSK(BigInteger.valueOf(resultSet.getBigDecimal("NURSE_ADMIN_STAFF_SK").longValue()));
                    nurse.setAdministrativeStaffRelationshipTypeName(resultSet.getString("NURSE_ADMIN_STAFF_ROLE_NAME"));
                    findPatientResult.setNurse(nurse);
                }
                findPatientResult.setMedicaidId(resultSet.getString("PT_MEDICAID_ID"));

                findPatientResults.add(findPatientResult);
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

    public Response getPatientCoordinators(String bsnEntId, String patientId) {
        if (!StringUtil.IsNullOrEmpty(patientId)) {
            return getPatientParentRelationship(bsnEntId, "Coordinator", patientId);
        }

        return getPatientRelationship(bsnEntId, "Coordinator");
    }

    private Response getPatientParentRelationship(String bsnEntId, String relType, String patientId)
        throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT DISTINCT T1.ADMIN_STAFF_SK,T1.ADMIN_STAFF_ID,T1.ADMIN_STAFF_FIRST_NAME,T1.ADMIN_STAFF_LAST_NAME, " +
                "          T2.ADMIN_STAFF_ROLE_XREF_SK,T2.ADMIN_STAFF_ROLE_NAME, " +
                "          T4.PT_ID,T4.PT_FIRST_NAME,T4.PT_LAST_NAME " +
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
                "INNER JOIN (SELECT BE_ID,ADMIN_STAFF_ID,PT_ID,REC_TERM_TMSTP,CURR_REC_IND, " +
                "              ADMIN_STAFF_PT_EFF_DATE,ADMIN_STAFF_PT_TERM_DATE " +
                "  FROM ADMIN_STAFF_PT " +
                "    WHERE TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                "      BETWEEN ADMIN_STAFF_PT_EFF_DATE AND ADMIN_STAFF_PT_TERM_DATE " +
                "        AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T3 " +
                "ON T1.BE_ID = T3.BE_ID AND T1.ADMIN_STAFF_ID = T3.ADMIN_STAFF_ID " +
                " " +
                "INNER JOIN (SELECT BE_ID,PT_ID,PT_FIRST_NAME,PT_LAST_NAME,REC_TERM_TMSTP,CURR_REC_IND " +
                "  FROM PT " +
                "    WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T4 " +
                "ON T3.BE_ID = T4.BE_ID AND T3.PT_ID = T4.PT_ID " +
                " " +
                "WHERE T1.BE_ID = ? AND T4.PT_ID = ? AND UPPER(T2.ADMIN_STAFF_ROLE_NAME) = ? " +
                "  AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1) " +
                "ORDER BY UPPER(T1.ADMIN_STAFF_LAST_NAME) ASC ";

            preparedStatement = connection.prepareStatement(sql);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String currentDate = dateFormat.format(new Date());

            int index = 1;
            preparedStatement.setString(index++, currentDate);
            preparedStatement.setString(index++, currentDate);
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, patientId);
            preparedStatement.setString(index++, relType.toUpperCase());

            resultSet = preparedStatement.executeQuery();

            List<AdminPatientParentRelTypeResponse> adminStaffParentRelTypeResponseList = new ArrayList<>();
            Response response = new Response();
            response.setData(adminStaffParentRelTypeResponseList);

            while (resultSet.next()) {
                AdminPatientParentRelTypeResponse adminStaffParentRelTypeResponse = new AdminPatientParentRelTypeResponse();

                adminStaffParentRelTypeResponse.setAdministrativeStaffSK(resultSet.getBigDecimal("ADMIN_STAFF_SK").toBigInteger());
                adminStaffParentRelTypeResponse.setAdministrativeStaffRelationshipSK(resultSet.getBigDecimal("ADMIN_STAFF_ROLE_XREF_SK").toBigInteger());
                adminStaffParentRelTypeResponse.setAdministrativeStaffID(resultSet.getString("ADMIN_STAFF_ID"));
                adminStaffParentRelTypeResponse.setAdministrativeStaffFirstName(resultSet.getString("ADMIN_STAFF_FIRST_NAME"));
                adminStaffParentRelTypeResponse.setAdministrativeStaffLastName(resultSet.getString("ADMIN_STAFF_LAST_NAME"));
                adminStaffParentRelTypeResponse.setAdministrativeStaffRelationshipTypeName(resultSet.getString("ADMIN_STAFF_ROLE_NAME"));
                adminStaffParentRelTypeResponse.setPatientId(resultSet.getString("PT_ID"));
                adminStaffParentRelTypeResponse.setPatientFirstName(resultSet.getString("PT_FIRST_NAME"));
                adminStaffParentRelTypeResponse.setPatientLastName(resultSet.getString("PT_LAST_NAME"));

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

    public Response getPatientRelationship(final String bsnEntId, final String relType) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT DISTINCT T1.ADMIN_STAFF_SK,T1.ADMIN_STAFF_ID,T1.ADMIN_STAFF_FIRST_NAME,T1.ADMIN_STAFF_LAST_NAME, " +
                "          T2.ADMIN_STAFF_ROLE_XREF_SK,T2.ADMIN_STAFF_ROLE_NAME " +
                "  FROM ADMIN_STAFF T1 " +
                "INNER JOIN (SELECT BE_ID, ADMIN_STAFF_ID,ADMIN_STAFF_ROLE_XREF_SK,ADMIN_STAFF_ROLE_NAME,REC_TERM_TMSTP,CURR_REC_IND, " +
                "              ADMIN_STAFF_ROLE_EFF_DATE,ADMIN_STAFF_ROLE_TERM_DATE " +
                "  FROM ADMIN_STAFF_ROLE_XREF " +
                "    WHERE TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                "      BETWEEN ADMIN_STAFF_ROLE_EFF_DATE AND ADMIN_STAFF_ROLE_TERM_DATE) T2 " +
                "ON T1.ADMIN_STAFF_ID = T2.ADMIN_STAFF_ID AND T1.BE_ID = T2.BE_ID " +
                "WHERE T1.BE_ID = ? AND UPPER(T2.ADMIN_STAFF_ROLE_NAME) = ? " +
                "  AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1) " +
                "  AND (TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T2.CURR_REC_IND = 1) " +
                "ORDER BY UPPER(T1.ADMIN_STAFF_LAST_NAME) ASC ";

            preparedStatement = connection.prepareStatement(sql);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String currentDate = dateFormat.format(new Date());

            int index = 1;
            preparedStatement.setString(index++, currentDate);
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

    public long endDateAuthForDischargedPatient(Connection connection,
                                               String bsnEntId,
                                               String patientID,
                                               String patientDischargeDateString) {

        CallableStatement callableStatement = null;

        try {

            String callMethod = "{?=call PKG_PATIENT_UTIL.END_DATE_AUTH_FOR_DISCHGED_PT(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);

            int index = 1;
            callableStatement.registerOutParameter(index++, OracleTypes.BIGINT);
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, patientID);
            callableStatement.setString(index++, patientDischargeDateString);

            callableStatement.execute();
            long result = callableStatement.getLong(1);

            if (result == -1) {
                throw new SandataRuntimeException("Error occurred while updating AUTH AND AUTH_SVC tables");

            } else if (result == -2) {
                throw new SQLException("Number of records updated did not match with number of records selected");
            }

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

    public long endDateOrdForDischargedPatient(Connection connection,
                                              String bsnEntId,
                                              String patientID,
                                              String patientDischargeDateString) {

        CallableStatement callableStatement = null;

        try {

            String callMethod = "{?=call PKG_PATIENT_UTIL.END_DATE_ORDER_FOR_DISCHGED_PT(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);

            int index = 1;
            callableStatement.registerOutParameter(index++, OracleTypes.BIGINT);
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, patientID);
            callableStatement.setString(index++, patientDischargeDateString);

            callableStatement.execute();
            long result = callableStatement.getLong(1);

            if (result == -1) {
                throw new SandataRuntimeException("Error occurred while updating ORD AND ORD_SVC tables");

            } else if (result == -2) {
                throw new SQLException("Number of records updated did not match with number of records selected");
            }

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


    public long endDatePocForDischargedPatient(Connection connection, String bsnEntId, String patientID, String patientDischargeDateString) {

        CallableStatement callableStatement = null;

        try {

            String callMethod = "{?=call PKG_PATIENT_UTIL.END_DATE_POC_FOR_DISCHGED_PT(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);

            int index = 1;
            callableStatement.registerOutParameter(index++, OracleTypes.BIGINT);
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, patientID);
            callableStatement.setString(index++, patientDischargeDateString);

            callableStatement.execute();
            long result = callableStatement.getLong(1);

            if (result == -1) {
                throw new SandataRuntimeException("Error occurred while updating ORD AND ORD_SVC tables");

            } else if (result == -2) {
                throw new SQLException("Number of records updated did not match with number of records selected");
            }

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

    public long cancelSchedEventsForDischargedPatient(Connection connection, String bsnEntId, String patientID, String patientDischargeDateString) {

        CallableStatement callableStatement = null;

        try {

            String callMethod = "{?=call PKG_PATIENT_UTIL.CANCEL_SCHED_EVNTS_DISCHGED_PT(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);

            int index = 1;
            callableStatement.registerOutParameter(index++, OracleTypes.BIGINT);
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, patientID);
            callableStatement.setString(index++, patientDischargeDateString);

            callableStatement.execute();
            long result = callableStatement.getLong(1);

            if (result < 0) {
                throw new SandataRuntimeException("Error occurred while updating SCHED_EVNT table");
            }

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
    
    public int terminateSchedEventsForDischargedPatient(Connection connection, String bsnEntId, String patientID, String patientDischargeDateString) {

        CallableStatement callableStatement = null;

        try {

            String callMethod = "{?=call PKG_PATIENT_UTIL.TERM_SCHED_EVNTS_DISCHGED_PT(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);

            int index = 1;
            callableStatement.registerOutParameter(index++, OracleTypes.INTEGER);
            callableStatement.setString(index++, bsnEntId);
            callableStatement.setString(index++, patientID);
            callableStatement.setString(index++, patientDischargeDateString);

            callableStatement.execute();
            int result = callableStatement.getInt(1);

            if (result < 0) {
                throw new SandataRuntimeException("Error occurred while updating SCHED_EVNT table");
            }

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

    public Map<String, BigDecimal> getPendingUnitsForAuthorization(BigInteger authorizationSK) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT COUNT(T2.SCHED_EVNT_SK) AS PENDING_COUNT,"
                + "       SUM(T2.SCHED_EVNT_TOTAL_HRS) AS PENDING_HOURS"
                + "  FROM AUTH T1"
                + "    INNER JOIN SCHED_EVNT T2 ON T2.BE_ID = T1.BE_ID"
                + "      AND T2.PT_ID = T1.PT_ID"
                + "      AND T2.AUTH_SK=T1.AUTH_SK"
                + "  WHERE T1.AUTH_SK=?"
                + "    AND UPPER(T2.SCHED_EVNT_STATUS) = 'PENDING'"
                + "    AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1)"
                + "    AND (TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T2.CURR_REC_IND = 1)";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, authorizationSK.longValue());

            resultSet = preparedStatement.executeQuery();

            Map<String, BigDecimal> pendingUnitsMap = new LinkedHashMap<>();

            if (resultSet.next()) {
                pendingUnitsMap.put("PENDING_COUNT", resultSet.getBigDecimal("PENDING_COUNT"));
                pendingUnitsMap.put("PENDING_HOURS", resultSet.getBigDecimal("PENDING_HOURS"));
            }

            return pendingUnitsMap;

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

    public Map<String, BigDecimal> getConfirmedUnitsForAuthorization(BigInteger authorizationSK) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT COUNT(T3.VISIT_SK) AS CONFIRMED_COUNT,"
                + "       SUM((T3.VISIT_ACT_END_TMSTP - T3.VISIT_ACT_START_TMSTP) * 24) AS CONFIRMED_HOURS"
                + "  FROM AUTH T1"
                + "    INNER JOIN SCHED_EVNT T2 ON T2.BE_ID = T1.BE_ID"
                + "      AND T2.PT_ID = T1.PT_ID"
                + "      AND T2.AUTH_SK=T1.AUTH_SK"
                + "    INNER JOIN VISIT T3 ON T3.BE_ID = T1.BE_ID"
                + "      AND T3.PT_ID = T1.PT_ID"
                + "      AND T3.SCHED_EVNT_SK = T2.SCHED_EVNT_SK"
                + "  WHERE T1.AUTH_SK=?"
                + "    AND UPPER(T2.SCHED_EVNT_STATUS) = 'CONFIRMED'"
                + "   AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1)"
                + "    AND (TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T2.CURR_REC_IND = 1)";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, authorizationSK.longValue());

            resultSet = preparedStatement.executeQuery();

            Map<String, BigDecimal> confirmedUnitsMap = new LinkedHashMap<>();

            if (resultSet.next()) {
                confirmedUnitsMap.put("CONFIRMED_COUNT", resultSet.getBigDecimal("CONFIRMED_COUNT"));
                confirmedUnitsMap.put("CONFIRMED_HOURS", resultSet.getBigDecimal("CONFIRMED_HOURS"));
            }

            return confirmedUnitsMap;

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
     * Get Patient Contact Address by City and State
     * 
     * @param bsnEntId
     * @param cityList
     * @param stateList
     * @param page
     * @param pageSize
     * @param orderByColumn
     * @param direction
     * @throws SandataRuntimeException
     */
    public Response getAddressByCityAndState(String bsnEntId, List<String> cityList, List<String> stateList,
            int page, int pageSize, String orderByColumn, String direction) throws SandataRuntimeException {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {
            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format(new StringBuilder()
                    .append("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( ")
                    .append("   SELECT * FROM ( ")
                    .append("       SELECT DISTINCT PT_ADDR1, PT_ADDR2, PT_APT_NUM ")
                    .append("       FROM COREDATA.PT_CONT_ADDR WHERE BE_ID = ? AND UPPER(PT_STATE) IN (%s) AND UPPER(PT_CITY) IN (%s) ")
                    .append("           AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) ORDER BY UPPER(%s) %s")
                    .append(")  ) R1) WHERE ROW_NUMBER BETWEEN %d AND %d ")
                    .toString(),
                    StringUtils.repeat("?", ",", stateList.size()),
                    StringUtils.repeat("?", ",", cityList.size()),
                    orderByColumn, direction, fromRow, toRow);
                        
            preparedStatement = connection.prepareStatement(sql);

            int index = 1;

            preparedStatement.setString(index++, bsnEntId);

            //stateList always has values as required
            for(String state : stateList){
                preparedStatement.setString(index++, state.toUpperCase());
            }

            //cityList always has values as required
            for(String city : cityList){
                preparedStatement.setString(index++, city.toUpperCase());
            }

            resultSet = preparedStatement.executeQuery();
            
            List<String> addresses = new ArrayList<String>();
            
            Response response = new Response();
            response.setData(addresses);
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction);
            
            StringBuilder addressBuilder = null;
            while (resultSet.next()) {
                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());
                }
                
                addressBuilder = new StringBuilder();
                
                if (resultSet.getString("PT_ADDR1") != null && resultSet.getString("PT_ADDR1").length() > 0) {
                    addressBuilder.append(resultSet.getString("PT_ADDR1")).append(", ");
                }
                
                if (resultSet.getString("PT_ADDR2") != null && resultSet.getString("PT_ADDR2").length() > 0) {
                    addressBuilder.append(resultSet.getString("PT_ADDR2")).append(", ");
                }
                
                if (resultSet.getString("PT_APT_NUM") != null && resultSet.getString("PT_APT_NUM").length() > 0) {
                    addressBuilder.append(resultSet.getString("PT_APT_NUM")).append(", ");
                }
                
                if (addressBuilder.length() > 0) {
                    addresses.add(addressBuilder.substring(0, addressBuilder.length() - 2));
                } else {
                    addresses.add(addressBuilder.toString());
                }
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
    
    /**
     * Get list of cities by State
     * 
     * @param bsnEntId
     * @param stateList
     * @param page
     * @param pageSize
     * @param orderByColumn
     * @param direction
     * @return
     * @throws SandataRuntimeException
     */
    public Response getCityByState(String bsnEntId, List<String> stateList,
            int page, int pageSize, String orderByColumn, String direction) throws SandataRuntimeException {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);
        
        try {
            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format(new StringBuilder()
                    .append("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( ")
                    .append("   SELECT * FROM ( ")
                    .append("       SELECT DISTINCT PT_CITY ")
                    .append("       FROM COREDATA.PT_CONT_ADDR WHERE BE_ID = ? AND UPPER(PT_STATE) IN (%s) ")
                    .append("           AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) ORDER BY UPPER(%s) %s")
                    .append(")  ) R1) WHERE ROW_NUMBER BETWEEN %d AND %d ")
                    .toString(),
                    StringUtils.repeat("?", ",", stateList.size()),
                    orderByColumn, direction, fromRow, toRow);
                        
            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);

            //stateList always has values as required
            for(String state : stateList){
                preparedStatement.setString(index++, state.toUpperCase());
            }

            resultSet = preparedStatement.executeQuery();
            
            List<String> cities = new ArrayList<String>();
            
            Response response = new Response();
            response.setData(cities);
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction);
            
            while (resultSet.next()) {
                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());
                }
                
                if (resultSet.getString("PT_CITY") != null && resultSet.getString("PT_CITY").length() > 0) {
                    cities.add(resultSet.getString("PT_CITY"));
                } 
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
    
    /**
     * get patient payers (both types) with Payer, Eligibility, Contract, LineOfBusiness, Insurance entities
     * 
     * this is the combination from cxf/data/service/payer/patient;patient_id=;bsn_ent_id=; and /cxf/data/service/patient/payer/eligibility;patient_id=;bsn_ent_id=; services
     * 
     * @param patientId
     * @param bsnEntId
     * @return
     */
    public Response getPatientPayerFull(String patientId, String bsnEntId) {
        Connection connection = null;
        try {
            List<Payer> payers = new ArrayList<>();
            
            connection = connectionPoolDataService.getConnection();
            String patientPayerSQL = "SELECT * FROM PT_PAYER WHERE PT_ID = ? AND BE_ID = ? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')";
            List<PatientPayer> patientPayerList = (List<PatientPayer>) getEntitiesForId(connection, patientPayerSQL, 
                    "com.sandata.lab.data.model.dl.model.PatientPayer", patientId, bsnEntId);
            
            for (PatientPayer patientPayer : patientPayerList) {
                PatientPayerExt patientPayerExt = new PatientPayerExt(patientPayer);
                List<Payer> payerListResult = (List<Payer>) getEntitiesForId(connection, 
                        "SELECT * FROM PAYER WHERE PAYER_ID=? AND BE_ID = ? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", 
                        "com.sandata.lab.data.model.dl.model.Payer", patientPayerExt.getPayerID(), bsnEntId);
                
                if (payerListResult != null && !payerListResult.isEmpty()) {
                    Payer payer = payerListResult.get(0);
                    payer.getPatientPayer().add(patientPayerExt);
                    payers.add(payerListResult.get(0));
                    
                    // get Eligibility
                    List<Eligibility> eligibilities = (List<Eligibility>) getEntitiesForId(connection, 
                            "SELECT * FROM ELIG WHERE PT_ID = ? AND BE_ID = ? AND PAYER_ID = ? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", 
                            "com.sandata.lab.data.model.dl.model.Eligibility", patientId, bsnEntId, patientPayerExt.getPayerID());
                    payer.getEligibility().addAll(eligibilities);
                    
                    // get Contract
                    List<Contract> contractList = (List<Contract>) getEntitiesForId(connection, 
                            "SELECT * FROM CONTR WHERE BE_ID = ? AND PAYER_ID = ? AND CONTR_ID = ? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)", 
                            "com.sandata.lab.data.model.dl.model.Contract", bsnEntId, patientPayerExt.getPayerID(), patientPayerExt.getContractID());
                    patientPayerExt.getContract().addAll(contractList);
                    
                    // get Line of Business
                    List<PayerLineOfBusinessList> payerLOBList = (List<PayerLineOfBusinessList>) getEntitiesForId(connection, 
                            "SELECT * FROM PAYER_LOB_LST WHERE BE_ID = ? AND PAYER_ID = ? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)", 
                            "com.sandata.lab.data.model.dl.model.PayerLineOfBusinessList", bsnEntId, patientPayerExt.getPayerID());
                    payer.getPayerLineOfBusinessList().addAll(payerLOBList);
                    
                    // get Insurance
                    List<PatientPayerInsurance> ppiList = (List<PatientPayerInsurance>) getEntitiesForId(connection, 
                            "SELECT * FROM PT_PAYER_INS WHERE PT_ID = ? AND BE_ID = ? AND PAYER_ID = ? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)", 
                            "com.sandata.lab.data.model.dl.model.PatientPayerInsurance", patientId, bsnEntId, patientPayerExt.getPayerID());
                    patientPayerExt.getPatientPayerInsurance().addAll(ppiList);
                    
                    // get patient payer limit
                    List<PatientPayerLimit> patientPayerLimits = (List<PatientPayerLimit>) getEntitiesForId(connection, 
                            "SELECT * FROM PT_PAYER_LMT WHERE PT_ID = ? AND PAYER_ID = ? AND BE_ID = ? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                            "com.sandata.lab.data.model.dl.model.PatientPayerLimit", patientId, patientPayerExt.getPayerID(), bsnEntId);
                    patientPayerExt.getPatientPayerLimit().addAll(patientPayerLimits);
                }
            }
            
            Response response = new Response();
            response.setTotalRows(payers.size());
            response.setData(payers);
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
            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }
}
