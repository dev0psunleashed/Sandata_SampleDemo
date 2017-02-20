package com.sandata.one.aggregator.visit.review.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.extended.VisitExceptionExt;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.one.aggregator.visit.review.api.OracleService;
import com.sandata.one.aggregator.visit.review.model.*;
import oracle.jdbc.OracleTypes;
import org.apache.camel.PropertyInject;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@SuppressWarnings("unchecked")
public class OracleDataService implements OracleService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private ConnectionPoolDataService connectionPoolDataService;

    @PropertyInject("{{manually.added.task.source.qualifier}}")
    private String manuallyAddedTaskSourceQualifier;

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

    public Response reviewVisit(ReviewVisitRequest reviewVisitRequest) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String orderByString = "UPPER(PT_LAST_NAME)"; // Default
        switch (reviewVisitRequest.getSortOn()) {
            case "PatientFirstName":
                orderByString = "UPPER(PT_FIRST_NAME)";
                break;
            case "StaffLastName":
                orderByString = "UPPER(STAFF_LAST_NAME)";
                break;
            case "StaffFirstName":
                orderByString = "UPPER(STAFF_FIRST_NAME)";
                break;
            case "VisitSK":
                orderByString = "VISIT.VISIT_SK";
                break;
        }

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            StringBuilder filter = new StringBuilder();
            List<Object> params = new ArrayList<>();

            if (!StringUtils.isEmpty(reviewVisitRequest.getPayerId())) {
                String[] payerIdList = reviewVisitRequest.getPayerId().split(",");
                String payerIdListParams = "?";

                if (payerIdList.length > 1) {
                    for(int i = 1; i < payerIdList.length; i++) {
                        payerIdListParams += ",?";
                    }
                }

                filter.append(String.format(" AND Payer.PAYER_ID IN (%s) ", payerIdListParams));
                params.add(Arrays.asList(payerIdList));
            }

            if (!StringUtils.isEmpty(reviewVisitRequest.getContractId())) {
                filter.append(" AND Contract.CONTR_ID = ? ");
                params.add(reviewVisitRequest.getContractId());
            }

            if (!StringUtils.isEmpty(reviewVisitRequest.getServiceName())) {
                filter.append(" AND PatientPayer.SVC_NAME = ? ");
                params.add(reviewVisitRequest.getServiceName().toUpperCase());
            }

            if (!StringUtils.isEmpty(reviewVisitRequest.getBusinessEntityId())) {
                filter.append(" AND Agency.BE_ID  = ? ");
                params.add(reviewVisitRequest.getBusinessEntityId());
            }

            if (!StringUtils.isEmpty(reviewVisitRequest.getPatientId())) {
                filter.append(" AND Visit.PT_ID = ? ");
                params.add(reviewVisitRequest.getPatientId());
            }

            if (!StringUtils.isEmpty(reviewVisitRequest.getMedicaidId())) {
                filter.append(" AND UPPER(PT_MEDICAID_ID) = ? ");
                params.add(reviewVisitRequest.getMedicaidId().toUpperCase());
            }

            if (!StringUtils.isEmpty(reviewVisitRequest.getPatientFirstName())) {
                filter.append(" AND UPPER(Patient.PT_FIRST_NAME) LIKE ? ");
                params.add("%" + reviewVisitRequest.getPatientFirstName().toUpperCase() + "%");
            }

            if (!StringUtils.isEmpty(reviewVisitRequest.getPatientLastName())) {
                filter.append(" AND UPPER(Patient.PT_LAST_NAME) LIKE ? ");
                params.add("%" + reviewVisitRequest.getPatientLastName().toUpperCase() + "%");
            }

            if (!StringUtils.isEmpty(reviewVisitRequest.getPatientName())) {

                // The name can be first last or last first
                String[] name = reviewVisitRequest.getPatientName().split(" ");
                // Its only one name
                if (name.length == 1) {
                    filter.append(" AND (UPPER(Patient.PT_FIRST_NAME) LIKE ? OR UPPER(Patient.PT_LAST_NAME) LIKE ?)");
                    params.add("%" + name[0].toUpperCase(Locale.ENGLISH) + "%");
                    params.add("%" + name[0].toUpperCase(Locale.ENGLISH) + "%");
                } else {
                    // First or Last
                    filter.append(" AND (UPPER(Patient.PT_FIRST_NAME) LIKE ? AND UPPER(Patient.PT_LAST_NAME) LIKE ?)");
                    params.add("%" + name[0].toUpperCase(Locale.ENGLISH) + "%");
                    params.add("%" + name[1].toUpperCase(Locale.ENGLISH) + "%");

                    // Last or First
                    filter.append(" OR (UPPER(Patient.PT_LAST_NAME) LIKE ? AND UPPER(Patient.PT_FIRST_NAME) LIKE ?)");
                    params.add("%" + name[0].toUpperCase(Locale.ENGLISH) + "%");
                    params.add("%" + name[1].toUpperCase(Locale.ENGLISH) + "%");
                }
            }

            if (!StringUtils.isEmpty(reviewVisitRequest.getStaffId())) {
                filter.append(" AND Visit.STAFF_ID = ? ");
                params.add(reviewVisitRequest.getStaffId());
            }

            if (!StringUtils.isEmpty(reviewVisitRequest.getStaffSsn())) {
                filter.append(" AND (STAFF_TIN_QLFR = 'SSN' AND STAFF_TIN = ?) ");
                params.add(reviewVisitRequest.getStaffSsn());
            }

            if (!StringUtils.isEmpty(reviewVisitRequest.getStaffFirstName())) {
                filter.append(" AND UPPER(Staff.STAFF_FIRST_NAME) LIKE ? ");
                params.add("%" + reviewVisitRequest.getStaffFirstName().toUpperCase() + "%");
            }

            if (!StringUtils.isEmpty(reviewVisitRequest.getStaffLastName())) {
                filter.append(" AND UPPER(Staff.STAFF_LAST_NAME) LIKE ? ");
                params.add("%" + reviewVisitRequest.getStaffLastName().toUpperCase() + "%");
            }

            if (!StringUtils.isEmpty(reviewVisitRequest.getStaffName())) {

                // The name can be first last or last first
                String[] name = reviewVisitRequest.getStaffName().split(" ");
                // Its only one name
                if (name.length == 1) {
                    filter.append(" AND (UPPER(Staff.STAFF_FIRST_NAME) LIKE ? OR UPPER(Staff.STAFF_LAST_NAME) LIKE ?)");
                    params.add("%" + name[0].toUpperCase(Locale.ENGLISH) + "%");
                    params.add("%" + name[0].toUpperCase(Locale.ENGLISH) + "%");
                } else {
                    // First or Last
                    filter.append(" AND (UPPER(Staff.STAFF_FIRST_NAME) LIKE ? AND UPPER(Staff.STAFF_LAST_NAME) LIKE ?)");
                    params.add("%" + name[0].toUpperCase(Locale.ENGLISH) + "%");
                    params.add("%" + name[1].toUpperCase(Locale.ENGLISH) + "%");

                    // Last or First
                    filter.append(" OR (UPPER(Staff.STAFF_LAST_NAME) LIKE ? AND UPPER(Staff.STAFF_FIRST_NAME) LIKE ?)");
                    params.add("%" + name[0].toUpperCase(Locale.ENGLISH) + "%");
                    params.add("%" + name[1].toUpperCase(Locale.ENGLISH) + "%");
                }
            }

            if (!StringUtils.isEmpty(reviewVisitRequest.getVisitStatus())
                    && !reviewVisitRequest.getVisitStatus().equals("all")) {
                filter.append(" AND UPPER(VISIT_STATUS_NAME) = ? ");
                params.add(reviewVisitRequest.getVisitStatus().toUpperCase());
            }

            if (!StringUtils.isEmpty(reviewVisitRequest.getCoordinatorId())) {
                filter.append(" AND AdminStaffCoordinator.ADMIN_STAFF_ID = ? ");
                params.add(reviewVisitRequest.getCoordinatorId());
            }
            
            if (StringUtils.isNotBlank(reviewVisitRequest.getCoordinatorName())) {
                String[] name = reviewVisitRequest.getCoordinatorName().trim().split(" ");
                
                if (name.length == 1) {
                    // One name
                    filter.append(" AND (UPPER(AdminStaffCoordinator.ADMIN_STAFF_FIRST_NAME) LIKE ? OR UPPER(AdminStaffCoordinator.ADMIN_STAFF_LAST_NAME) LIKE ?)");
                    params.add("%" + name[0].toUpperCase(Locale.ENGLISH) + "%");
                    params.add("%" + name[0].toUpperCase(Locale.ENGLISH) + "%");
                } else {
                    // First name - Last name
                    filter.append(" AND (UPPER(AdminStaffCoordinator.ADMIN_STAFF_FIRST_NAME) LIKE ? AND UPPER(AdminStaffCoordinator.ADMIN_STAFF_LAST_NAME) LIKE ?)");
                    params.add("%" + name[0].toUpperCase(Locale.ENGLISH) + "%");
                    params.add("%" + name[1].toUpperCase(Locale.ENGLISH) + "%");

                    // Last name - First name
                    filter.append(" OR (UPPER(AdminStaffCoordinator.ADMIN_STAFF_LAST_NAME) LIKE ? AND UPPER(AdminStaffCoordinator.ADMIN_STAFF_FIRST_NAME) LIKE ?)");
                    params.add("%" + name[0].toUpperCase(Locale.ENGLISH) + "%");
                    params.add("%" + name[1].toUpperCase(Locale.ENGLISH) + "%");
                }
            }

            if (!StringUtils.isEmpty(reviewVisitRequest.getCallType())
                    && !reviewVisitRequest.getCallType().equalsIgnoreCase("all")) {
                filter.append(" AND UPPER(VisitEvent.VISIT_EVNT_TYP_CODE) = ? ");
                params.add(reviewVisitRequest.getCallType().toUpperCase());
            }

            if (reviewVisitRequest.getExceptionsList() != null && reviewVisitRequest.getExceptionsList().size() > 0) {
                if (reviewVisitRequest.getExceptionsList().size() == 1 && reviewVisitRequest.getExceptionsList().get(0).intValue() == -1) {
                    // visits without exception
                    filter.append(" AND VisitException.EXCP_ID IS NULL ");
                } else {
                    filter.append(" AND VisitException.EXCP_ID IN ( ");
                    for (int index = 0; index < reviewVisitRequest.getExceptionsList().size(); index++) {
                        filter.append(index == 0 ? "?" : ",?");
                    }
                    filter.append(" ) ");
                    params.addAll(reviewVisitRequest.getExceptionsList());
                }
            }

            String betweenExpression = " BETWEEN TO_DATE('" + dateFormat.format(reviewVisitRequest.getFromDate()) + "','YYYY-MM-DD HH24:MI:SS') " +
                                          "  AND TO_DATE('" + dateFormat.format(reviewVisitRequest.getToDate()) + "','YYYY-MM-DD HH24:MI:SS') ";

            int toRow = reviewVisitRequest.getPage() * reviewVisitRequest.getPageSize();
            int fromRow = toRow - (reviewVisitRequest.getPageSize() - 1);

            String sql = String.format("SELECT * FROM(SELECT ROWNUM ROW_NUMBER, R1.* FROM ( " +
                            " " +
                            "      (SELECT COUNT(VISIT.VISIT_SK) OVER() TOTAL_ROWS, " +
                            "            SCHED_EVNT_START_DTIME,SCHED_EVNT_END_DTIME, " +
                            "            24 * (SCHED_EVNT_END_DTIME-SCHED_EVNT_START_DTIME) ScheduledHours, " +
                            "            (SELECT COUNT(*) FROM VISIT_TASK_LST WHERE VISIT_SK=VISIT.VISIT_SK) TaskCount, " +
                            "            (SELECT COUNT(*) FROM VISIT_EXCP " +
                            "              WHERE VISIT_SK=VISIT.VISIT_SK AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) VisitExceptionCount, " +
                            "            AdminStaffManager.ADMIN_STAFF_ID AS ManagerID, " +
                            "            AdminStaffManager.ADMIN_STAFF_FIRST_NAME AS ManagerFirstName, " +
                            "            AdminStaffManager.ADMIN_STAFF_LAST_NAME AS ManagerLastName, " +
                            "            AdminStaffCoordinator.ADMIN_STAFF_ID AS CoordinatorID, " +
                            "            AdminStaffCoordinator.ADMIN_STAFF_FIRST_NAME AS CoordinatorFirstName, " +
                            "            AdminStaffCoordinator.ADMIN_STAFF_LAST_NAME AS CoordinatorLastName, " +
                            "            PatientPayer.PAYER_ID,PAYER_NAME, " +
                            "            PatientPayer.CONTR_ID,CONTR_DESC, " +
                            "            PatientPayer.SVC_NAME, " +
                            "            PT_FIRST_NAME,PT_LAST_NAME,PT_MIDDLE_NAME,PT_MEDICAID_ID,PT_PHONE,PT_ADDR_TYP_NAME,Patient.TZ_NAME, " +
                            "            STAFF_FIRST_NAME,STAFF_LAST_NAME,STAFF_MIDDLE_NAME,STAFF_TIN_QLFR,STAFF_TIN,STAFF_PHONE, " +
                            "            BE_NAME, " +
                            "            VISIT.* " +
                            "          FROM VISIT " +
                            " " +
                            "            LEFT JOIN (SELECT BE_ID,PT_ID,PT_FIRST_NAME,PT_LAST_NAME,PT_MIDDLE_NAME,PT_MEDICAID_ID,TZ_NAME " +
                            "              FROM PT " +
                            "                WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) Patient " +
                            "            ON VISIT.BE_ID = Patient.BE_ID " +
                            "              AND VISIT.PT_ID = Patient.PT_ID " +
                            " " +
                            "            LEFT JOIN (SELECT BE_ID,PT_ID,PT_PHONE,PT_PHONE_ANI_ENABLED_IND " +
                            "              FROM PT_CONT_PHONE " +
                            "                WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1 " +
                            "                  AND PT_PHONE_PRMY_IND = 1) PatientPhone " +
                            "            ON VISIT.BE_ID = PatientPhone.BE_ID " +
                            "              AND VISIT.PT_ID = PatientPhone.PT_ID " +
                            " " +
                            "            LEFT JOIN (SELECT BE_ID,PT_ID,PT_ADDR_TYP_NAME " +
                            "              FROM PT_CONT_ADDR " +
                            "                WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1 " +
                            "                  AND ADDR_PRIO_NAME = 'PRIMARY') PatientAddress " +
                            "            ON VISIT.BE_ID = PatientAddress.BE_ID " +
                            "              AND VISIT.PT_ID = PatientAddress.PT_ID " +
                            " " +
                            "            LEFT JOIN (SELECT BE_ID,PT_ID,PAYER_ID,CONTR_ID,SVC_NAME " +
                            "              FROM PT_PAYER " +
                            "                WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1 " +
                            "                  AND PAYER_RANK_NAME = 'PRIMARY') PatientPayer " +
                            "            ON VISIT.BE_ID = PatientPayer.BE_ID " +
                            "              AND VISIT.PT_ID = PatientPayer.PT_ID " +
                            " " +
                            "            LEFT JOIN (SELECT BE_ID,STAFF_ID,STAFF_FIRST_NAME,STAFF_LAST_NAME,STAFF_MIDDLE_NAME,STAFF_TIN_QLFR,STAFF_TIN " +
                            "              FROM STAFF " +
                            "                WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) Staff " +
                            "            ON VISIT.BE_ID = Staff.BE_ID " +
                            "              AND VISIT.STAFF_ID = Staff.STAFF_ID " +
                            " " +
                            "            LEFT JOIN (SELECT BE_ID,STAFF_ID,STAFF_PHONE " +
                            "              FROM STAFF_CONT_PHONE " +
                            "                WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1 " +
                            "                  AND STAFF_PHONE_PRMY_IND = 1) StaffPhone " +
                            "            ON VISIT.BE_ID = StaffPhone.BE_ID " +
                            "              AND VISIT.STAFF_ID = StaffPhone.STAFF_ID " +
                            " " +
                            "            LEFT JOIN (SELECT XREF.BE_ID,XREF.STAFF_ID,XREF.ADMIN_STAFF_ID, " +
                            "                        COORDINATOR.ADMIN_STAFF_FIRST_NAME,COORDINATOR.ADMIN_STAFF_LAST_NAME " +
                            "              FROM ADMIN_STAFF_STAFF_XREF XREF " +
                            "                INNER JOIN (SELECT BE_ID,ADMIN_STAFF_ID,ADMIN_STAFF_FIRST_NAME,ADMIN_STAFF_LAST_NAME " +
                            "                  FROM ADMIN_STAFF " +
                            "                    WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) COORDINATOR " +
                            "                ON XREF.BE_ID = COORDINATOR.BE_ID AND XREF.ADMIN_STAFF_ID = COORDINATOR.ADMIN_STAFF_ID " +
                            "                WHERE XREF.ADMIN_STAFF_ROLE_NAME = 'COORDINATOR' " +
                            "                    AND TO_CHAR(XREF.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND XREF.CURR_REC_IND = 1) AdminStaffCoordinator " +
                            "            ON VISIT.BE_ID = AdminStaffCoordinator.BE_ID " +
                            "              AND VISIT.STAFF_ID = AdminStaffCoordinator.STAFF_ID " +
                            " " +
                            "            LEFT JOIN (SELECT XREF.BE_ID,XREF.STAFF_ID,XREF.ADMIN_STAFF_ID, " +
                            "                        MANAGER.ADMIN_STAFF_FIRST_NAME,MANAGER.ADMIN_STAFF_LAST_NAME " +
                            "              FROM ADMIN_STAFF_STAFF_XREF XREF " +
                            "                INNER JOIN (SELECT BE_ID,ADMIN_STAFF_ID,ADMIN_STAFF_FIRST_NAME,ADMIN_STAFF_LAST_NAME " +
                            "                  FROM ADMIN_STAFF " +
                            "                    WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) MANAGER " +
                            "                ON XREF.BE_ID = MANAGER.BE_ID AND XREF.ADMIN_STAFF_ID = MANAGER.ADMIN_STAFF_ID " +
                            "                WHERE XREF.ADMIN_STAFF_ROLE_NAME = 'MANAGER' " +
                            "                    AND TO_CHAR(XREF.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND XREF.CURR_REC_IND = 1) AdminStaffManager " +
                            "            ON VISIT.BE_ID = AdminStaffManager.BE_ID " +
                            "              AND VISIT.STAFF_ID = AdminStaffManager.STAFF_ID " +
                            " " +
                            "            LEFT JOIN (SELECT BE_ID,PAYER_ID,PAYER_NAME " +
                            "              FROM PAYER " +
                            "                WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) Payer " +
                            "            ON VISIT.BE_ID = Payer.BE_ID " +
                            "              AND PatientPayer.PAYER_ID = Payer.PAYER_ID " +
                            " " +
                            "            LEFT JOIN (SELECT BE_ID,PAYER_ID,CONTR_ID,CONTR_DESC " +
                            "              FROM CONTR " +
                            "                WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) Contract " +
                            "            ON VISIT.BE_ID = Contract.BE_ID " +
                            "              AND PatientPayer.PAYER_ID = Contract.PAYER_ID " +
                            "              AND PatientPayer.CONTR_ID = Contract.CONTR_ID " +
                            " " +
                            "            INNER JOIN (SELECT BE_ID,BE_NAME " +
                            "              FROM BE " +
                            "                WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) Agency " +
                            "            ON VISIT.BE_ID = Agency.BE_ID " +
                            " " +
                            "            LEFT JOIN (SELECT BE_ID,PT_ID,SCHED_EVNT_SK,SCHED_EVNT_START_DTIME,SCHED_EVNT_END_DTIME,SCHED_EVNT_TOTAL_HRS " +
                            "              FROM SCHED_EVNT " +
                            "                WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) ScheduleEvent " +
                            "            ON VISIT.BE_ID = ScheduleEvent.BE_ID " +
                            "                AND VISIT.PT_ID = ScheduleEvent.PT_ID " +
                            "                AND VISIT.SCHED_EVNT_SK = ScheduleEvent.SCHED_EVNT_SK " +
                            " " +
                            "            LEFT JOIN (SELECT VISIT_SK, PT_ID, VISIT_EVNT_TYP_CODE " +
                            "              FROM VISIT_EVNT " +
                            "                WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) VisitEvent " +
                            "            ON VISIT.VISIT_SK = VisitEvent.VISIT_SK " +
                            "                AND VISIT.PT_ID = VisitEvent.PT_ID " +
                            " " +
                            "            LEFT JOIN (SELECT VISIT_SK, EXCP_ID " +
                            "              FROM VISIT_EXCP " +
                            "                WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) VisitException " +
                            "            ON VISIT.VISIT_SK = VisitException.VISIT_SK " +
                            " " +
                            "            WHERE VISIT_ACT_START_TMSTP %s " +
                            " " +
                            "              %s " +
                            " " +
                            "          ORDER BY %s %s " +
                            "    ) " +
                            ") R1) " +
                            " " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d ",

                    betweenExpression,
                    filter.toString(),
                    orderByString,
                    reviewVisitRequest.getDirection(),
                    fromRow,
                    toRow
            );

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (Object param : params) {
                if (param instanceof List) {
                    for (Object listParam : (List)param) {
                        preparedStatement.setObject(index++, listParam);
                    }
                } else {
                    preparedStatement.setObject(index++, param);
                }
            }
            resultSet = preparedStatement.executeQuery();

            List<ReviewVisitResult> findVisitResultList = new ArrayList<>();

            Response response = new Response();
            response.setPage(reviewVisitRequest.getPage());
            response.setPageSize(reviewVisitRequest.getPageSize());
            response.setOrderByColumn(orderByString);
            response.setOrderByDirection(reviewVisitRequest.getDirection().toUpperCase());
            response.setData(findVisitResultList);

            while (resultSet.next()) {

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getInt("TOTAL_ROWS"));
                }

                findVisitResultList.add(mapVisits(connection, resultSet));
            }

            return response;

        }  catch (Exception e) {

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
            this.connectionPoolDataService.close(connection);
        }
    }

    private ReviewVisitResult mapVisits(Connection connection, ResultSet resultSet) throws SQLException {

        ReviewVisitResult reviewVisitResult = new ReviewVisitResult();

        Visit visit = (Visit)new DataMapper().mapWithOffsetEntityNext(resultSet, "com.sandata.lab.data.model.dl.model.Visit", 32);

        visit.getVisitException().addAll(getVisitExceptions(connection, visit.getVisitSK()));
        visit.getVisitTaskList().addAll(getTasks(connection, visit.getVisitSK(), visit.getBusinessEntityID()));
        visit.getVisitNote().addAll(getVisitNote(connection, visit.getVisitSK(), "REC_CREATE_TMSTP", "DESC"));

        reviewVisitResult.setVisit(visit);

        reviewVisitResult.setBusinessEntityName(resultSet.getString("BE_NAME"));
        reviewVisitResult.setPayerId(resultSet.getString("PAYER_ID"));
        reviewVisitResult.setPayerName(resultSet.getString("PAYER_NAME"));
        reviewVisitResult.setContractId(resultSet.getString("CONTR_ID"));
        reviewVisitResult.setContractDesc(resultSet.getString("CONTR_DESC"));
        reviewVisitResult.setServiceName(resultSet.getString("SVC_NAME"));
        reviewVisitResult.setMedicaidID(resultSet.getString("PT_MEDICAID_ID"));
        reviewVisitResult.setPatientFirstName(resultSet.getString("PT_FIRST_NAME"));
        reviewVisitResult.setPatientLastName(resultSet.getString("PT_LAST_NAME"));
        reviewVisitResult.setPatientMiddleName(resultSet.getString("PT_MIDDLE_NAME"));
        reviewVisitResult.setPrimaryPatientPhoneNumber(resultSet.getString("PT_PHONE"));
        reviewVisitResult.setVisitLocation(resultSet.getString("PT_ADDR_TYP_NAME"));
        reviewVisitResult.setVisitTimeZone(resultSet.getString("TZ_NAME"));

        String tinQlfr = resultSet.getString("STAFF_TIN_QLFR");
        if (!StringUtils.isEmpty(tinQlfr) && tinQlfr.equals("SSN")) {
            reviewVisitResult.setStaffSsn(resultSet.getString("STAFF_TIN"));
        }

        reviewVisitResult.setStaffFirstName(resultSet.getString("STAFF_FIRST_NAME"));
        reviewVisitResult.setStaffLastName(resultSet.getString("STAFF_LAST_NAME"));
        reviewVisitResult.setStaffMiddleName(resultSet.getString("STAFF_MIDDLE_NAME"));
        reviewVisitResult.setStaffContactPhoneNumber(resultSet.getString("STAFF_PHONE"));

        reviewVisitResult.setVisitStatus(resultSet.getString("VISIT_STATUS_NAME"));

        //TODO: Call In/Out Type should be calculated.
        //TODO: NEED util plsql function
        reviewVisitResult.setCallInType("TEL");
        reviewVisitResult.setCallOutType("TEL");

        reviewVisitResult.setCoordinatorId(resultSet.getString("CoordinatorID"));
        reviewVisitResult.setCoordinatorFirstName(resultSet.getString("CoordinatorFirstName"));
        reviewVisitResult.setCoordinatorLastName(resultSet.getString("CoordinatorLastName"));

        reviewVisitResult.setManagerId(resultSet.getString("ManagerID"));
        reviewVisitResult.setManagerFirstName(resultSet.getString("ManagerFirstName"));
        reviewVisitResult.setManagerLastName(resultSet.getString("ManagerLastName"));

        Timestamp scheduledStartDate = resultSet.getTimestamp("SCHED_EVNT_START_DTIME");
        if (scheduledStartDate != null) {
            reviewVisitResult.setScheduledStartDate(new Date(scheduledStartDate.getTime()));
        }

        Timestamp scheduledEndDate = resultSet.getTimestamp("SCHED_EVNT_END_DTIME");
        if (scheduledEndDate != null) {
            reviewVisitResult.setScheduledEndDate(new Date(scheduledEndDate.getTime()));
        }

        BigDecimal scheduledHours = resultSet.getBigDecimal("ScheduledHours");
        if (scheduledHours != null) {
            reviewVisitResult.setScheduledHours(scheduledHours.setScale(2, RoundingMode.CEILING));
        }

        reviewVisitResult.setTaskCount(resultSet.getInt("TaskCount"));
        reviewVisitResult.setVisitExceptionCount(resultSet.getInt("VisitExceptionCount"));

        return reviewVisitResult;
    }

    private List<VisitException> getVisitExceptions(Connection connection, BigInteger visitSK) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT * FROM VISIT_EXCP WHERE VISIT_SK = ?" +
                    " AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setInt(index++, visitSK.intValue());

            resultSet = preparedStatement.executeQuery();

            List<VisitException> resultList =
                    (List<VisitException>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.VisitException");

            return resultList;

        } catch (Exception e) {

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
        }
    }

    private List<VisitTaskList> getTasks(Connection connection, BigInteger visitSK, String bsnEntId) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT * FROM VISIT_TASK_LST WHERE VISIT_SK = ? AND BE_ID = ?";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setInt(index++, visitSK.intValue());
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<VisitTaskList> resultList =
                    (List<VisitTaskList>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.VisitTaskList");

            return resultList;

        } catch (Exception e) {

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
        }
    }

    private List<VisitNote> getVisitNote(Connection connection, final BigInteger visitSk, String sortOn, String direction) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = String.format("SELECT * FROM VISIT_NOTE WHERE VISIT_SK = ? " +
                    " AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                    " ORDER BY %s %s", sortOn, direction);

            preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            preparedStatement.setLong(index, visitSk.longValue());

            resultSet = preparedStatement.executeQuery();

            List<VisitNote> visitNoteList = (List<VisitNote>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.VisitNote");

            return visitNoteList;

        } catch (Exception e) {

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
        }
    }

    public Object executeGet(String packageName, String methodName, String className, long sequenceKey) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.COREDATA);
            connection.setAutoCommit(true);

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setLong(2, sequenceKey);
            callableStatement.execute();
            resultSet = (ResultSet) callableStatement.getObject(1);

            Object result = new DataMapper().map(resultSet, className);

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

    private String getBsnEntId(long visitSk) {
        List<Visit> visits = (ArrayList<Visit>) executeGet(
                "PKG_VISIT",
                "getVisit",
                "com.sandata.lab.data.model.dl.model.Visit",
                visitSk);

        if (visits != null & visits.size() > 0) {
            return visits.get(0).getBusinessEntityID();
        }

        return null;
    }

    public Response getVisitHistory(long visitSk, int page, int pageSize, String sortOn, String direction) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // default column for ordering
        String orderByColumn = "T1.REC_CREATE_TMSTP";

        switch (sortOn) {
            case "ChangedOn":
                orderByColumn = "T1.REC_CREATE_TMSTP";
                break;

            case "ChangedBy":
                orderByColumn = "UPPER(T2.USER_NAME)";
                break;

            case "ReasonCode":
                orderByColumn = "UPPER(T_Reason_Code.APP_DATA_STRUC_ELT_VAL)";
                break;
        }

        String auditHost = getBsnEntId(visitSk) + "|" + String.valueOf(visitSk);

        
        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(true);

            int toRow = page * pageSize;
            int fromRow = toRow - (pageSize - 1);

            String sql = String.format( 
                    " SELECT * FROM(SELECT ROWNUM ROW_NUMBER, R1.* FROM ( " +
                    "   ( " +
                    " " +
                    "   SELECT DISTINCT COUNT(T1.REC_CREATE_TMSTP) OVER() TOTAL_ROWS, " +
                    "       T1.REC_CREATE_TMSTP AS CHANGED_ON, " +
                    "       T2.USER_NAME AS CHANGED_BY, " +
                    "       T_Reason_Code.APP_DATA_STRUC_ELT_VAL AS REASON_CODE, " +
                    "       T_Notes.APP_DATA_STRUC_ELT_VAL AS NOTES, " +
                    "       T1.AUDIT_HOST, " +
                    "       T1.USER_GUID " +
                    " " +
                    "   FROM APP_AUDIT T1 " +
                    " " +
                    "   LEFT JOIN (SELECT USER_GUID, USER_NAME FROM APP_USER) T2 " +
                    "       ON T1.USER_GUID = T2.USER_GUID " +
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
                    " " +
                    "   ORDER BY %s %s " +
                    "   ) " +
                    " ) R1) " +
                    " " +
                    " WHERE ROW_NUMBER BETWEEN %d AND %d ",
               orderByColumn,
               direction,
               fromRow,
               toRow);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, auditHost);

            resultSet = preparedStatement.executeQuery();

            List<VisitChangedLog> visitChangedLogs = new ArrayList<VisitChangedLog>();

            Response response = new Response();
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction);
            
            response.setData(visitChangedLogs);

            while (resultSet.next()) {
                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getInt("TOTAL_ROWS"));
                }

               visitChangedLogs.add(mapVisitChangedLog(resultSet));
            }

            // TODO: refactor to just use one DB call to get all needed data
            // instead of making each DB call for each changed date
            for (VisitChangedLog visitChangedLog : visitChangedLogs) {
                visitChangedLog.getChangedFields().addAll(getFieldChangedLogs(connection, auditHost, visitChangedLog));
            }

            return response;

        }  catch (Exception e) {

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
            this.connectionPoolDataService.close(connection);
        }
    }

    private VisitChangedLog mapVisitChangedLog(final ResultSet resultSet) throws SQLException {
        VisitChangedLog visitChangedLog = new VisitChangedLog();

        visitChangedLog.setChangedOn(resultSet.getTimestamp("CHANGED_ON"));
        visitChangedLog.setChangedBy(resultSet.getString("CHANGED_BY"));
        visitChangedLog.setReasonCode(resultSet.getString("REASON_CODE"));
        visitChangedLog.setNotes(resultSet.getString("NOTES"));

        visitChangedLog.setUserGuid(resultSet.getString("USER_GUID"));

        return visitChangedLog;
    }

    private List<FieldChangedLog> getFieldChangedLogs(Connection connection, String auditHost, final VisitChangedLog visitChangedLog) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            StringBuilder whereClause = new StringBuilder();
            List<Object> params = new ArrayList<Object>();

            whereClause.append(" AUDIT_HOST = ? ");
            params.add(auditHost);

            whereClause.append(" AND USER_GUID = ? ");
            params.add(visitChangedLog.getUserGuid());

            whereClause.append(" AND REC_CREATE_TMSTP = ? ");
            params.add(visitChangedLog.getChangedOn());

            // exclude Notes
            whereClause.append(" AND DATA_SEC_CLAS_ID <> 'Notes'");

            // exclude Reason Code
            whereClause.append(" AND DATA_SEC_CLAS_ID <>  'ReasonCode'");

            String sql = String.format("SELECT * " +
                        " " +
                        "   FROM APP_AUDIT " +
                        " " +
                        "   WHERE " +
                        "       %s " +
                        " " +
                        "   ORDER BY DATA_SEC_CLAS_ID ",
                    whereClause.toString());

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (Object param : params) {
                preparedStatement.setObject(index++, param);
            }

            resultSet = preparedStatement.executeQuery();

            List<FieldChangedLog> fieldChangedLogs = new ArrayList<FieldChangedLog>();

            while (resultSet.next()) {
                fieldChangedLogs.add(mapFieldChangedLog(resultSet));
            }

            return fieldChangedLogs;

        }  catch (Exception e) {

            // Rollback: Connection was passed in. Caller to take care of rolling back if needed

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

            // Connection was passed in. Caller to take care of closing the connection
        }
    }

    private FieldChangedLog mapFieldChangedLog(final ResultSet resultSet) throws SQLException {
        FieldChangedLog fieldChangedLog = new FieldChangedLog();

        fieldChangedLog.setChangedField(resultSet.getString("DATA_SEC_CLAS_ID"));

        // there should be 02 records for the same field, one contains "ChangedFrom", one contains "ChangedTo"
        setChangedFromOrTo(resultSet, fieldChangedLog);

        if (resultSet.next()) {
            setChangedFromOrTo(resultSet, fieldChangedLog);
        }

        return fieldChangedLog;
    }

    private boolean isChangedFrom(final ResultSet resultSet) throws SQLException {
        return resultSet.getBoolean("AUDIT_DATA_STRUC_READ_IND");
    }

    private boolean isChangedTo(final ResultSet resultSet) throws SQLException {
        return resultSet.getBoolean("AUDIT_DATA_STRUC_WRITE_IND");
    }

    private void setChangedFromOrTo(final ResultSet resultSet, final FieldChangedLog fieldChangedLog) throws SQLException {
        if (isChangedFrom(resultSet)) {
            fieldChangedLog.setChangedFrom(resultSet.getString("APP_DATA_STRUC_ELT_VAL"));
        }

        if (isChangedTo(resultSet)) {
            fieldChangedLog.setChangedTo(resultSet.getString("APP_DATA_STRUC_ELT_VAL"));
        }
    }

    public ReviewVisitPatientResult getVisitPatientDetails(long visitSk) throws SandataRuntimeException {
    
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    
        try {
    
            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);
    
            String sql = "SELECT Visit.VISIT_SK, " +
                    "        Patient.PT_ID,PT_MEDICAID_ID,PT_FIRST_NAME,PT_LAST_NAME,PT_MIDDLE_NAME,PT_DOB,PT_DISCHARGE_DATE, " +
                    "        PT_PHONE,PT_ADDR1,PT_ADDR2,PT_APT_NUM,PT_CITY,PT_STATE,PT_PSTL_CODE,PT_ZIP4,PT_COUNTY,PT_PRIO_LVL_CODE, Patient.TZ_NAME," +
                    "        ThirdPartyPatientID.PT_ID_NUM AS THIRD_PARTY_PT_ID, " +
                    "        AdminStaffManager.ADMIN_STAFF_ID AS ManagerID, " +
                    "        AdminStaffManager.ADMIN_STAFF_FIRST_NAME AS ManagerFirstName, " +
                    "        AdminStaffManager.ADMIN_STAFF_LAST_NAME AS ManagerLastName, " +
                    "        AdminStaffCoordinator.ADMIN_STAFF_ID AS CoordinatorID, " +
                    "        AdminStaffCoordinator.ADMIN_STAFF_FIRST_NAME AS CoordinatorFirstName, " +
                    "        AdminStaffCoordinator.ADMIN_STAFF_LAST_NAME AS CoordinatorLastName " +
                    " " +
                    "  FROM PT Patient " +
                    " " +
                    "INNER JOIN (SELECT VISIT_SK,PT_ID,BE_ID,STAFF_ID " +
                    "    FROM VISIT) Visit " +
                    "ON Patient.PT_ID = Visit.PT_ID AND Patient.BE_ID = Visit.BE_ID " +
                    " " +
                    "LEFT JOIN (SELECT BE_ID,PT_ID,PT_ID_NUM " +
                    "    FROM PT_ID_XWALK WHERE ROWNUM <=1) ThirdPartyPatientID " +
                    "ON ThirdPartyPatientID.PT_ID = Visit.PT_ID AND ThirdPartyPatientID.BE_ID = Visit.BE_ID " +
                    " " +
                    "LEFT JOIN (SELECT BE_ID,PT_ID,PT_PHONE,PT_PHONE_ANI_ENABLED_IND " +
                    "  FROM PT_CONT_PHONE " +
                    "    WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1 " +
                    "      AND PT_PHONE_PRMY_IND = 1) PatientPhone " +
                    "ON Visit.BE_ID = PatientPhone.BE_ID " +
                    "  AND Visit.PT_ID = PatientPhone.PT_ID " +
                    " " +
                    "LEFT JOIN (SELECT BE_ID,PT_ID,PT_ADDR_TYP_NAME, " +
                    "                  PT_ADDR1,PT_ADDR2,PT_APT_NUM,PT_CITY,PT_STATE,PT_PSTL_CODE,PT_ZIP4,PT_COUNTY " +
                    "  FROM PT_CONT_ADDR " +
                    "    WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1 " +
                    "      AND ADDR_PRIO_NAME = 'PRIMARY') PatientAddress " +
                    "ON Visit.BE_ID = PatientAddress.BE_ID " +
                    "  AND Visit.PT_ID = PatientAddress.PT_ID " +
                    " " +
                    "LEFT JOIN (SELECT XREF.BE_ID,XREF.STAFF_ID,XREF.ADMIN_STAFF_ID, " +
                    "            COORDINATOR.ADMIN_STAFF_FIRST_NAME,COORDINATOR.ADMIN_STAFF_LAST_NAME " +
                    "  FROM ADMIN_STAFF_STAFF_XREF XREF " +
                    "    INNER JOIN (SELECT BE_ID,ADMIN_STAFF_ID,ADMIN_STAFF_FIRST_NAME,ADMIN_STAFF_LAST_NAME " +
                    "      FROM ADMIN_STAFF " +
                    "        WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) COORDINATOR " +
                    "    ON XREF.BE_ID = COORDINATOR.BE_ID AND XREF.ADMIN_STAFF_ID = COORDINATOR.ADMIN_STAFF_ID " +
                    "    WHERE XREF.ADMIN_STAFF_ROLE_NAME = 'COORDINATOR' " +
                    "        AND TO_CHAR(XREF.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND XREF.CURR_REC_IND = 1) AdminStaffCoordinator " +
                    "ON Visit.BE_ID = AdminStaffCoordinator.BE_ID " +
                    "  AND Visit.STAFF_ID = AdminStaffCoordinator.STAFF_ID " +
                    " " +
                    "LEFT JOIN (SELECT XREF.BE_ID,XREF.STAFF_ID,XREF.ADMIN_STAFF_ID, " +
                    "          MANAGER.ADMIN_STAFF_FIRST_NAME,MANAGER.ADMIN_STAFF_LAST_NAME " +
                    "FROM ADMIN_STAFF_STAFF_XREF XREF " +
                    "  INNER JOIN (SELECT BE_ID,ADMIN_STAFF_ID,ADMIN_STAFF_FIRST_NAME,ADMIN_STAFF_LAST_NAME " +
                    "    FROM ADMIN_STAFF " +
                    "      WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) MANAGER " +
                    "  ON XREF.BE_ID = MANAGER.BE_ID AND XREF.ADMIN_STAFF_ID = MANAGER.ADMIN_STAFF_ID " +
                    "  WHERE XREF.ADMIN_STAFF_ROLE_NAME = 'MANAGER' " +
                    "      AND TO_CHAR(XREF.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND XREF.CURR_REC_IND = 1) AdminStaffManager " +
                    "ON Visit.BE_ID = AdminStaffManager.BE_ID " +
                    "AND Visit.STAFF_ID = AdminStaffManager.STAFF_ID " +
                    " " +
                    "WHERE Visit.VISIT_SK = ? ";
    
            preparedStatement = connection.prepareStatement(sql);
    
            int index = 1;
            preparedStatement.setLong(index++, visitSk);
    
            resultSet = preparedStatement.executeQuery();

            return mapReviewVisitPatientResult(resultSet);
    
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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    private ReviewVisitPatientResult mapReviewVisitPatientResult(ResultSet resultSet) throws SandataRuntimeException {

        try {
            if (resultSet.next()) {
                ReviewVisitPatientResult reviewVisitPatientResult = new ReviewVisitPatientResult();
                reviewVisitPatientResult.setMedicaidID(resultSet.getString("PT_MEDICAID_ID"));
                reviewVisitPatientResult.setPatientFirstName(resultSet.getString("PT_FIRST_NAME"));
                reviewVisitPatientResult.setPatientLastName(resultSet.getString("PT_LAST_NAME"));
                reviewVisitPatientResult.setPatientMiddleName(resultSet.getString("PT_MIDDLE_NAME"));

                Timestamp patientDob = resultSet.getTimestamp("PT_DOB");
                if (patientDob != null) {
                    reviewVisitPatientResult.setPatientDob(new Date(patientDob.getTime()));
                }

                Timestamp patientDischargeDate = resultSet.getTimestamp("PT_DISCHARGE_DATE");
                if (patientDischargeDate != null) {
                    reviewVisitPatientResult.setDischargeDate(new Date(patientDischargeDate.getTime()));
                }

                reviewVisitPatientResult.setPrimaryPatientPhoneNumber(resultSet.getString("PT_PHONE"));
                reviewVisitPatientResult.setPatientAddress1(resultSet.getString("PT_ADDR1"));
                reviewVisitPatientResult.setPatientAddress2(resultSet.getString("PT_ADDR2"));
                reviewVisitPatientResult.setPatientApartment(resultSet.getString("PT_APT_NUM"));
                reviewVisitPatientResult.setPatientCity(resultSet.getString("PT_CITY"));
                reviewVisitPatientResult.setPatientState(resultSet.getString("PT_STATE"));
                reviewVisitPatientResult.setPatientPostalCode(resultSet.getString("PT_PSTL_CODE"));
                reviewVisitPatientResult.setPatientZip4(resultSet.getString("PT_ZIP4"));
                reviewVisitPatientResult.setPatientCounty(resultSet.getString("PT_COUNTY"));
                reviewVisitPatientResult.setThirdPartyPatientID(resultSet.getString("THIRD_PARTY_PT_ID"));
                reviewVisitPatientResult.setManagerId(resultSet.getString("ManagerID"));
                reviewVisitPatientResult.setManagerFirstName(resultSet.getString("ManagerFirstName"));
                reviewVisitPatientResult.setManagerLastName(resultSet.getString("ManagerLastName"));
                reviewVisitPatientResult.setCoordinatorId(resultSet.getString("CoordinatorID"));
                reviewVisitPatientResult.setCoordinatorFirstName(resultSet.getString("CoordinatorFirstName"));
                reviewVisitPatientResult.setCoordinatorLastName(resultSet.getString("CoordinatorLastName"));
                reviewVisitPatientResult.setPatientPriorityLevelCode(resultSet.getString("PT_PRIO_LVL_CODE"));

                reviewVisitPatientResult.setTimezoneName(resultSet.getString("TZ_NAME"));

                return reviewVisitPatientResult;
            }

            return null;

        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s: Exception: %s",
                    getClass().getSimpleName(), e.getClass().getSimpleName()), e);
        }
    }


    /**
     *
     * @param visitSk
     * @return
     * @throws SandataRuntimeException
     */
    public Response getVisitExcpForReview(long visitSk, Integer page, Integer pageSize, String sortOn, String sortDirection) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String orderByString = "UPPER(EXCP_NAME)"; // Default
            switch (sortOn) {
                case "excpdes": //sort on excp descritpion
                    orderByString = "UPPER(EXCP_DESC)";
                    break;
            }

            int toRow = page * pageSize;
            int fromRow = toRow - (pageSize - 1);

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql  = "SELECT * "
                            + " FROM "
                            +" (SELECT ROWNUM ROW_NUMBER, "
                                    +" R1.* "
                                            +" FROM "
                                                    +" (SELECT COUNT(T1.VISIT_EXCP_SK) OVER() TOTAL_ROWS, "
                                                            +" T1.* , "
                                                            +" EXCP_NAME, EXCP_DESC, "
                                                            +" EXCP_NON_FIXABLE_IND , "
                                                            + " EXCP_ACK_IND "
                                                            +" FROM VISIT_EXCP T1 "
                                                            +" INNER JOIN "
                                                                    +" ( SELECT * FROM EXCP_LKUP "
                                                                    +" ) T2 "
                                                            +" ON T1.EXCP_ID                                = T2.EXCP_ID "
                                                            +" WHERE T1.VISIT_SK                            = %s "
                                    +" AND TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' "
                                    +" AND T1.CURR_REC_IND                          = 1 "
                            +" ) R1 )"
                            +" WHERE ROW_NUMBER BETWEEN %s AND %s "
                            +" ORDER BY %s %s ";


            sql = String.format(sql,visitSk,fromRow, toRow, orderByString, sortDirection);
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();
            Response response = new Response();
            List<VisitExceptionExt> result = mapVisitExceptionInfo(resultSet);
            response.setData(result);

            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(orderByString);
            response.setOrderByDirection(sortDirection);

            return response;

        }  catch (Exception e) {

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
            this.connectionPoolDataService.close(connection);
        }
    }


    private List<VisitExceptionExt> mapVisitExceptionInfo(ResultSet resultSet) throws SQLException {

        VisitExceptionExt visitExceptionExt = null;
        List<VisitExceptionExt> result = new ArrayList<>();
        while (resultSet.next()) {
            visitExceptionExt = new VisitExceptionExt();
            //fill data
            BigDecimal visitExceptionSK = resultSet.getBigDecimal("VISIT_EXCP_SK");
            if (visitExceptionSK != null) {
                visitExceptionExt.setVisitExceptionSK(visitExceptionSK.toBigInteger());
            }

            Timestamp recCreateTime = resultSet.getTimestamp("REC_CREATE_TMSTP");
            if(recCreateTime != null) {
                visitExceptionExt.setRecordCreateTimestamp(new java.util.Date(recCreateTime.getTime()));
            }

            Timestamp recUpdateTime = resultSet.getTimestamp("REC_UPDATE_TMSTP");
            if(recUpdateTime != null) {
                visitExceptionExt.setRecordUpdateTimestamp(new java.util.Date(recUpdateTime.getTime()));
            }

            Timestamp recEffTime = resultSet.getTimestamp("REC_EFF_TMSTP");
            if(recEffTime != null) {
                visitExceptionExt.setRecordEffectiveTimestamp(new java.util.Date(recUpdateTime.getTime()));
            }

            Timestamp recTerTime = resultSet.getTimestamp("REC_TERM_TMSTP");
            if(recTerTime != null) {
                visitExceptionExt.setRecordTerminationTimestamp(new java.util.Date(recTerTime.getTime()));
            }

            visitExceptionExt.setRecordCreatedBy(resultSet.getString("REC_CREATED_BY"));
            visitExceptionExt.setRecordUpdatedBy(resultSet.getString("REC_UPDATED_BY"));
            visitExceptionExt.setChangeReasonMemo(resultSet.getString("CHANGE_REASON_MEMO"));

            BigDecimal changeVersionID = resultSet.getBigDecimal("CHANGE_VERSION_ID");
            if (changeVersionID != null) {
                visitExceptionExt.setChangeVersionID(changeVersionID.toBigInteger());
            }

            BigDecimal visitSkResult = resultSet.getBigDecimal("VISIT_SK");
            if (visitSkResult != null) {
                visitExceptionExt.setVisitSK(visitSkResult.toBigInteger());
            }

            BigDecimal excpId = resultSet.getBigDecimal("EXCP_ID");
            if (excpId != null) {
                visitExceptionExt.setExceptionID(excpId.toBigInteger());
            }
            visitExceptionExt.setExceptionClearedManaullyIndicator(resultSet.getBoolean("EXCP_CLRD_MANAULLY_IND"));

            visitExceptionExt.setExceptionLookupName(resultSet.getString("EXCP_NAME"));
            visitExceptionExt.setExceptionLookupDescription(resultSet.getString("EXCP_DESC"));
            visitExceptionExt.setExceptionNonFixableIndicator(resultSet.getBoolean("EXCP_NON_FIXABLE_IND"));
            visitExceptionExt.setExceptionAcknowledgableIndicator(resultSet.getBoolean("EXCP_ACK_IND"));

            result.add(visitExceptionExt);
        }

        return result;
    }

    public Response getVisitNotes(long visitSk) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT * " +
                    "  FROM VISIT_NOTE " +
                    " " +
                    "  WHERE " +
                    "    TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1 " +
                    "    AND VISIT_SK = ? " +
                    " " +
                    " ORDER BY REC_CREATE_TMSTP DESC";
    
            preparedStatement = connection.prepareStatement(sql);
    
            int index = 1;
            preparedStatement.setLong(index++, visitSk);
    
            resultSet = preparedStatement.executeQuery();

            Response response = new Response();
            response.setData(mapReviewVisitNoteResult(resultSet));

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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    private List<ReviewVisitNoteResult> mapReviewVisitNoteResult(ResultSet resultSet) throws SandataRuntimeException {

        List<ReviewVisitNoteResult> results = new ArrayList<ReviewVisitNoteResult>();

        try {
            while (resultSet.next()) {

                ReviewVisitNoteResult reviewVisitNoteResult = new ReviewVisitNoteResult();
                reviewVisitNoteResult.setNoteType(resultSet.getString("VISIT_NOTE_TYP_NAME"));
                reviewVisitNoteResult.setNote(resultSet.getString("VISIT_NOTE"));

                Timestamp dateCreated = resultSet.getTimestamp("REC_CREATE_TMSTP");
                if (dateCreated != null) {
                    reviewVisitNoteResult.setDateCreated(new java.util.Date(dateCreated.getTime()));
                }

                reviewVisitNoteResult.setCreatedBy(resultSet.getString("REC_CREATED_BY"));

                results.add(reviewVisitNoteResult);
            }

            return results;

        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s: Exception: %s",
                    getClass().getSimpleName(), e.getClass().getSimpleName()), e);
        }
    }

    public Response getVisitTasks(long visitSk, int page, int pageSize, String sortOn, String direction) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // default column for ordering
        String orderByColumn = "UPPER(TASK_ID)";

        switch (sortOn) {
            case "TaskID":
                orderByColumn = "UPPER(TASK_ID)";
                break;

            case "TaskDescription":
                orderByColumn = "UPPER(TASK_DESC)";
                break;

            default:
                break;
        }

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format(
                    "SELECT * FROM(SELECT ROWNUM ROW_NUMBER, R1.* FROM ( " +
                    " " +
                    "      (SELECT COUNT(VISIT_TASK_LST_SK) OVER() TOTAL_ROWS, " +
                    "         VISIT_TASK_LST_SK, VISIT_SK, TASK_ID, TASK_DESC, NVL(CRITICAL_TASK_IND, 0) AS CRITICAL_TASK_IND, TASK_RESULTS_RDNG_VAL, TASK_SOURCE_QLFR " +
                    "      FROM VISIT_TASK_LST VTL " +
                    " " +
                    "      LEFT JOIN (SELECT TASK_SK, BE_ID, TASK_ID, BE_TASK_ID, TASK_DESC, TASK_SOURCE_QLFR FROM TASK " +
                    "            WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) Task " +
                    "        ON VTL.BE_ID = Task.BE_ID AND VTL.BE_TASK_ID = Task.BE_TASK_ID " +
                    " " +
                    "      WHERE VISIT_SK = ? " +
                    " " +
                    "     ORDER BY %s %s" +
                    "    ) " +
                    ") R1) " +
                    " " +
                    "WHERE ROW_NUMBER BETWEEN %d AND %d ",
                orderByColumn,
                direction,
                fromRow,
                toRow);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setLong(index++, visitSk);

            resultSet = preparedStatement.executeQuery();

            List<ReviewVisitTaskResult> visitTasks = new ArrayList<ReviewVisitTaskResult>();

            Response response = new Response();
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction);
            response.setData(visitTasks);

            while (resultSet.next()) {

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getInt("TOTAL_ROWS"));
                }

                visitTasks.add(mapReviewVisitTaskResult(resultSet));
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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    private ReviewVisitTaskResult mapReviewVisitTaskResult(ResultSet resultSet) throws SandataRuntimeException {

        try {

            ReviewVisitTaskResult reviewVisitTaskResult = new ReviewVisitTaskResult();
            reviewVisitTaskResult.setTaskID(resultSet.getString("TASK_ID"));
            reviewVisitTaskResult.setTaskDescription(resultSet.getString("TASK_DESC"));
            reviewVisitTaskResult.setCritial(resultSet.getBoolean("CRITICAL_TASK_IND"));
            reviewVisitTaskResult.setReading(resultSet.getString("TASK_RESULTS_RDNG_VAL"));
            reviewVisitTaskResult.setManuallyAdded(manuallyAddedTaskSourceQualifier.equalsIgnoreCase(resultSet.getString("TASK_SOURCE_QLFR")));

            return reviewVisitTaskResult;

        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s: Exception: %s",
                    getClass().getSimpleName(), e.getClass().getSimpleName()), e);
        }
    }

    /**
     *
     * @param visitSk
     * @return Staff info
     */
    public List<ReviewVisitStaffResult> getStaffDetail(long visitSk) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT T1.STAFF_ID , "
                                    + " T1.STAFF_FIRST_NAME,T1.STAFF_LAST_NAME, T1.STAFF_MIDDLE_NAME, T1.STAFF_VV_ID AS STAFF_TELEPHONY_ID, "
                                    + " STAFF_PREF_PHONE, "
                                    + " T1.STAFF_POSITION_NAME , "
                                    + " ADDRESS_NAME , "
                                    + " STAFF_ADDR1, "
                                    + " STAFF_ADDR2, "
                                    + " STAFF_CITY, "
                                    + " STAFF_STATE, "
                                    + " STAFF_PSTL_CODE, "
                                    + " STAFF_ZIP4, "
                                    + " STAFF_COUNTY, "
                                    +" T1.STAFF_HIRE_DATE , "
                                    +" T1.STAFF_TERM_DATE , "
                                    +" T5.SCHED_EVNT_SK, "
                                    +" T5.SCHED_EVNT_START_DTIME "
                            +" FROM STAFF T1 "
                            +" INNER JOIN "
                            +" (SELECT VISIT_SK , SCHED_EVNT_SK, STAFF_ID, VISIT_ACT_START_TMSTP "
                            +" FROM VISIT "
                                +" WHERE VISIT_SK = %s) T2 ON T1.STAFF_ID = T2.STAFF_ID "
                            +" LEFT JOIN "
                            +" (SELECT STAFF_ID ,STAFF_PHONE AS STAFF_PREF_PHONE, STAFF_PHONE_PRMY_IND "
                            +" FROM STAFF_CONT_PHONE "
                            +" WHERE STAFF_PHONE_PRMY_IND                = 1 "
                            +" AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' "
                            +" AND CURR_REC_IND                          = 1) T3 "
                            +" ON T2.STAFF_ID = T3.STAFF_ID "
                            +" LEFT JOIN "
                            +" (SELECT STAFF_ID, "
                                    +" STAFF_ADDR_TYP_NAME AS ADDRESS_NAME, "
                                    +" STAFF_ADDR1, "
                                    +" STAFF_ADDR2, "
                                    +" STAFF_CITY, "
                                    +" STAFF_STATE, "
                                    +" STAFF_PSTL_CODE, "
                                    +" STAFF_ZIP4, "
                                    +" STAFF_COUNTY "
                            +" FROM STAFF_CONT_ADDR "
                            +" WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' "
                            +" AND CURR_REC_IND                            = 1 "
                            +" AND UPPER(ADDR_PRIO_NAME)                   ='PRIMARY') T4 "
                            +" ON T3.STAFF_ID = T4.STAFF_ID "
                            +" LEFT JOIN "
                            +" (SELECT SCHED_EVNT_SK, SCHED_EVNT_START_DTIME FROM SCHED_EVNT) T5 "
                            +" ON T2.SCHED_EVNT_SK = T5.SCHED_EVNT_SK "
                            +" WHERE TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'"
                            +" AND T1.CURR_REC_IND                            = 1";

            sql = String.format(sql,visitSk);
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();
            Response response = new Response();
            List<ReviewVisitStaffResult> result =  mapReviewVisitStaffResult(resultSet);

            return result;

        }  catch (Exception e) {

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
            this.connectionPoolDataService.close(connection);
        }
    }

    private List<ReviewVisitStaffResult> mapReviewVisitStaffResult(ResultSet resultSet) throws   SQLException{
        List<ReviewVisitStaffResult> result = new ArrayList<>();
        ReviewVisitStaffResult visitStaffResult = null;
        while (resultSet.next()) {
            visitStaffResult = new ReviewVisitStaffResult();
            //fill data
            visitStaffResult.setStaffID(resultSet.getString("STAFF_ID"));
            visitStaffResult.setStaffFirstName(resultSet.getString("STAFF_FIRST_NAME"));
            visitStaffResult.setStaffLastName(resultSet.getString("STAFF_LAST_NAME"));
            visitStaffResult.setStaffMiddleName(resultSet.getString("STAFF_MIDDLE_NAME"));

            visitStaffResult.setThirdpartyID(resultSet.getString("STAFF_ID"));
            visitStaffResult.setTelephonyID(resultSet.getString("STAFF_TELEPHONY_ID"));
            visitStaffResult.setPreferredPhone(resultSet.getString("STAFF_PREF_PHONE"));
            visitStaffResult.setStaffPosition(resultSet.getString("STAFF_POSITION_NAME"));
            visitStaffResult.setAddressName(resultSet.getString("ADDRESS_NAME"));

            visitStaffResult.setAddress1(resultSet.getString("STAFF_ADDR1"));
            visitStaffResult.setAddress2(resultSet.getString("STAFF_ADDR2"));
            visitStaffResult.setCity(resultSet.getString("STAFF_CITY"));
            visitStaffResult.setState(resultSet.getString("STAFF_STATE"));

            visitStaffResult.setPostalCode(resultSet.getString("STAFF_PSTL_CODE"));
            visitStaffResult.setZip4(resultSet.getString("STAFF_ZIP4"));
            visitStaffResult.setCounty(resultSet.getString("STAFF_COUNTY"));

            Timestamp hiredDate = resultSet.getTimestamp("STAFF_HIRE_DATE");
            if(hiredDate != null) {
                visitStaffResult.setHireDate(new java.util.Date(hiredDate.getTime()));
            }

            Timestamp termDate = resultSet.getTimestamp("STAFF_TERM_DATE");
            if(termDate != null) {
                visitStaffResult.setTermDate(new java.util.Date(termDate.getTime()));
            }

            Timestamp schedEvntDate = resultSet.getTimestamp("SCHED_EVNT_START_DTIME");
            if(schedEvntDate != null) {
                visitStaffResult.setSchedEvntDate(new java.util.Date(schedEvntDate.getTime()));
            }


            BigDecimal schedEvntSk = resultSet.getBigDecimal("SCHED_EVNT_SK");
            if (schedEvntSk != null) {
                visitStaffResult.setSchedEvntSk(schedEvntSk.longValue());
            }

            //thirdparty indecator
            visitStaffResult.setThirdPartyStaffIDIndicator(true);

            result.add(visitStaffResult);
        }

        return result;
    }


    /**
     *
     * @param visitSk Visit SK
     * @param page offset record in database from ... to
     * @param pageSize total row per page
     * @param sortOn orer by column
     * @param sortDirection sort direction
     * @return List of visit event info
     */
    public Response getCallLogs(long visitSk, Integer page, Integer pageSize, String sortOn, String sortDirection) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String orderByString = "VISIT_EVNT_DTIME"; // Default
            switch (sortOn) {
                case "svcnm": //service name
                    orderByString = "UPPER(SVC_NAME)";
                    break;
            }

            int toRow = page * pageSize;
            int fromRow = toRow - (pageSize - 1);

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql  = " SELECT * FROM "
                          +"  (SELECT ROWNUM ROW_NUMBER,R1.* "
                          +"          FROM (SELECT COUNT(T1.VISIT_EVNT_SK) OVER() TOTAL_ROWS,"
                          +"                  T2.BE_ID, "
                          +"                  T4.BE_NAME, "
                          +"                  T1.VISIT_EVNT_DTIME, VISIT_EVNT_SK, "
                          //+"                  T1.VISIT_EVNT_DTIME, 'YYYY-MM-DD')  AS CALL_DATE , "
                          +"                  TO_CHAR (T1.VISIT_EVNT_DTIME, 'HH24:MI:SS') AS CALL_TIME, "
                          +"                  CALL_IN_IND, CALL_OUT_IND, VISIT_EVNT_TYP_CODE AS CALL_TYPE, "
                          +"                  SVC_NAME, COORD_LATITUDE, COORD_LONGITIDE, ANI, PT_ID "
                          +"                  FROM VISIT_EVNT T1 "
                          +"          INNER JOIN "
                          +"               (SELECT VISIT_SK,BE_ID, SCHED_EVNT_SK FROM VISIT) T2 "
                          +"                  ON T1.VISIT_SK = T2.VISIT_SK "
                          +"          LEFT JOIN VISIT_SVC T3 "
                          +"                   ON T1.VISIT_SK = T3.VISIT_SK "
                          +"           LEFT JOIN (SELECT BE_ID,BE_NAME,BE_TYP FROM BE WHERE BE_TYP IS NULL ) T4 "//BE ACTIVE with be_type is null
                          +"                  ON T2.BE_ID = T4.BE_ID "
                          +"          WHERE T1.VISIT_SK = %s "
                          +"                  AND TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' "
                          +"                  AND T1.CURR_REC_IND = 1) R1) "
                          +"  WHERE ROW_NUMBER BETWEEN %d AND %d "
                          +"  ORDER BY %s %s ";
                          //+"  ORDER BY UPPER(VISIT_EVNT_DTIME) DESC ";


            sql = String.format(sql,visitSk,fromRow, toRow, orderByString, sortDirection);
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();
            Response response = new Response();
            List<ReviewVisitEventResult> result = mapVisitEventInfo(resultSet);
            response.setData(result);

            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(orderByString);
            response.setOrderByDirection(sortDirection);

            return response;

        }  catch (Exception e) {

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
            this.connectionPoolDataService.close(connection);
        }

    }

    private List<ReviewVisitEventResult> mapVisitEventInfo(ResultSet resultSet) throws SQLException{
        ReviewVisitEventResult visitEventResult = null;
        List<ReviewVisitEventResult> result = new ArrayList<>();
        while (resultSet.next()) {

            visitEventResult = new ReviewVisitEventResult();
            Date callDate = resultSet.getDate("VISIT_EVNT_DTIME");
            if(callDate != null) {
                visitEventResult.setCallDate(callDate);
            }

            visitEventResult.setCallTime(resultSet.getString("CALL_TIME"));
            String callType = resultSet.getString("CALL_TYPE");
            //VisitEventTypeCode callType = VisitEventTypeCode.fromValue(resultSet.getString("CALL_TYPE"));
            visitEventResult.setCallType(callType);
            //set call source data
            if (callType != null && (callType.equals(VisitEventTypeCode.FVV.value())
                    || callType.equals(VisitEventTypeCode.MVV.value())
                    || callType.equals(VisitEventTypeCode.TEL.value())
                    || callType.equals(VisitEventTypeCode.UI.value()))) {
                visitEventResult.setCallSource("SANDATA EVV");
            } else {
                visitEventResult.setCallSource(resultSet.getString("BE_NAME"));
            }

            //call assignment / indicator
            visitEventResult.setCallInIndicator(resultSet.getBoolean("CALL_IN_IND"));
            visitEventResult.setCallOutIndicator(resultSet.getBoolean("CALL_OUT_IND"));

            //service name
            visitEventResult.setServiceName(resultSet.getString("SVC_NAME"));

            visitEventResult.setAni(resultSet.getString("ANI"));
            visitEventResult.setPatientID(resultSet.getString("PT_ID"));

            visitEventResult.setLatitude(resultSet.getString("COORD_LATITUDE"));
            visitEventResult.setLongitude(resultSet.getString("COORD_LONGITIDE"));

            BigDecimal visitEvntSk = resultSet.getBigDecimal("VISIT_EVNT_SK");
            if (visitEvntSk != null) {
                visitEventResult.setVisitEventSK(visitEvntSk.longValue());
            }

            result.add(visitEventResult);
        }

        return result;
    }

    public Response getVisitDetail(long visitSk) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql =
                    "   SELECT " +
                    "           Patient.TZ_NAME," +
                    "           Agency.BE_ID_NUM, Agency.BE_ID_CREATING_ORG, " +
                    "           PatientPayer.PAYER_ID, Payer.PAYER_NAME, " +
                    "           PatientPayer.CONTR_ID, Contract.CONTR_DESC, " +
                    "           VisitService.SVC_NAME, " +
                    "           Svc.BILLING_CODE, " +
                    "           VisitService.RATE_TYP_NAME, " +
                    "           ROUND(((VISIT.VISIT_ACT_END_TMSTP - VISIT.VISIT_ACT_START_TMSTP)*24),2) CALL_HRS," +
                    "           PatientDigitalSignature.DOC_ID AS PT_DIG_SIGNATURE_DOC_ID, " +
                    "           PatientAudioSignature.DOC_ID AS PT_AUD_SIGNATURE_DOC_ID, " +
                    "           Vendor.VENDOR_NAME, " +
                    "           CASE " +
                    "              WHEN UPPER(Vendor.VENDOR_NAME) = 'SANDATA.ONE' THEN TO_CHAR(VISIT.VISIT_SK) " +
                    "              ELSE VISIT.VISIT_OTHER_ID " +
                    "           END AS THIRD_PARTY_VISIT_ID, " +
                    "           VISIT.* " +
                    " " +
                    "   FROM VISIT " +
                    " " +
                    "            LEFT JOIN (SELECT BE_ID, PT_ID, TZ_NAME " +
                    "              FROM PT " +
                    "                WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) Patient " +
                    "            ON VISIT.BE_ID = Patient.BE_ID " +
                    "              AND VISIT.PT_ID = Patient.PT_ID " +
                    " " +
                    "            LEFT JOIN (SELECT BE_ID, PT_ID, PAYER_ID, CONTR_ID, SVC_NAME " +
                    "              FROM PT_PAYER " +
                    "                WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1 " +
                    "                  AND PAYER_RANK_NAME = 'PRIMARY') PatientPayer " +
                    "            ON VISIT.BE_ID = PatientPayer.BE_ID " +
                    "              AND VISIT.PT_ID = PatientPayer.PT_ID " +
                    " " +
                    "            LEFT JOIN (SELECT BE_ID, PAYER_ID, PAYER_NAME " +
                    "              FROM PAYER " +
                    "                WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) Payer " +
                    "            ON VISIT.BE_ID = Payer.BE_ID " +
                    "              AND PatientPayer.PAYER_ID = Payer.PAYER_ID " +
                    " " +
                    "            LEFT JOIN (SELECT BE_ID, PAYER_ID, CONTR_ID, CONTR_DESC " +
                    "              FROM CONTR " +
                    "                WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) Contract " +
                    "            ON VISIT.BE_ID = Contract.BE_ID " +
                    "              AND PatientPayer.PAYER_ID = Contract.PAYER_ID " +
                    "              AND PatientPayer.CONTR_ID = Contract.CONTR_ID " +
                    " " +
                    "            LEFT JOIN (SELECT BE_ID, BE_ID_QLFR, BE_ID_NUM, BE_ID_CREATING_ORG " +
                    "              FROM BE_ID_XWALK " +
                    "                WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') Agency " +
                    "            ON VISIT.BE_ID = Agency.BE_ID " +
                    "              AND UPPER(Agency.BE_ID_QLFR) = 'MEDICAID ID' " +
                    " " +
                    "            LEFT JOIN (SELECT BE_ID, BE_ID_TYP, BE_ID_QLFR, BE_ID_NUM " +
                    "              FROM BE_ID_XWALK " +
                    "                WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') VendorXwalk " +
                    "            ON VISIT.BE_ID = VendorXwalk.BE_ID " +
                    "              AND UPPER(VendorXwalk.BE_ID_QLFR) = 'VENDOR ID' " +
                    "              AND UPPER(VendorXwalk.BE_ID_TYP) = 'VENDOR' " +
                    " " +
                    "            LEFT JOIN (SELECT VENDOR_ID, VENDOR_NAME " +
                    "              FROM VENDOR_LKUP " +
                    "                WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) Vendor " +
                    "            ON Vendor.VENDOR_ID = VendorXwalk.BE_ID_NUM" +
                    " " +
                    "            LEFT JOIN (SELECT VISIT_SK, BE_ID, SVC_NAME, RATE_TYP_NAME " +
                    "              FROM VISIT_SVC " +
                    "                WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) VisitService " +
                    "            ON VISIT.VISIT_SK = VisitService.VISIT_SK " +
                    "              AND VISIT.BE_ID = VisitService.BE_ID " +
                    " " +
                    "            LEFT JOIN (SELECT VISIT_SK, BE_ID, DOC_ID, DOC_CLAS_NAME " +
                    "              FROM VISIT_DOC_XWALK " +
                    "                WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) PatientDigitalSignature " +
                    "            ON VISIT.VISIT_SK = PatientDigitalSignature.VISIT_SK " +
                    "              AND VISIT.BE_ID = PatientDigitalSignature.BE_ID " +
                    "              AND UPPER(PatientDigitalSignature.DOC_CLAS_NAME) = 'PATIENT DIGITAL SIGNATURE' " +
                    " " +
                    "            LEFT JOIN (SELECT VISIT_SK, BE_ID, DOC_ID, DOC_CLAS_NAME " +
                    "              FROM VISIT_DOC_XWALK " +
                    "                WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) PatientAudioSignature " +
                    "            ON VISIT.VISIT_SK = PatientAudioSignature.VISIT_SK " +
                    "              AND VISIT.BE_ID = PatientAudioSignature.BE_ID " +
                    "              AND UPPER(PatientAudioSignature.DOC_CLAS_NAME) = 'PATIENT AUDIO SIGNATURE' " +
                    " " +
                    "            LEFT JOIN (SELECT BILLING_CODE, SVC_NAME, BE_ID, PAYER_ID " +
                    "              FROM SVC " +
                    "                WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) Svc " +
                    "            ON VisitService.SVC_NAME = Svc.SVC_NAME " +
                    "              AND VisitService.BE_ID = Svc.BE_ID " +
                    "              AND PatientPayer.PAYER_ID = Svc.PAYER_ID" +
                    " " +
                    "   WHERE VISIT.VISIT_SK = ? ";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setLong(index++, visitSk);

            resultSet = preparedStatement.executeQuery();

            Response response = new Response();
            response.setData(mapVisitDetail(resultSet));

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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    private ReviewVisitDetail mapVisitDetail(ResultSet resultSet) throws SandataRuntimeException {

        try {
            if (resultSet.next()) {
                ReviewVisitDetail reviewVisitDetail = new ReviewVisitDetail();

                Visit visit = (Visit) new DataMapper().mapWithOffsetEntityNext(resultSet, "com.sandata.lab.data.model.dl.model.Visit", 15);

                reviewVisitDetail.setVisit(visit);

                reviewVisitDetail.setVisitTimeZone(resultSet.getString("TZ_NAME"));
                reviewVisitDetail.setVisitStatus(resultSet.getString("VISIT_STATUS_NAME"));

                reviewVisitDetail.setAgencyId(resultSet.getString("BE_ID_NUM"));
                reviewVisitDetail.setAgencyName(resultSet.getString("BE_ID_CREATING_ORG"));

                reviewVisitDetail.setPayerId(resultSet.getString("PAYER_ID"));
                reviewVisitDetail.setPayerName(resultSet.getString("PAYER_NAME"));

                reviewVisitDetail.setContractId(resultSet.getString("CONTR_ID"));
                reviewVisitDetail.setContractDesc(resultSet.getString("CONTR_DESC"));

                reviewVisitDetail.setServiceName(resultSet.getString("SVC_NAME"));
                reviewVisitDetail.setBillCode(resultSet.getString("BILLING_CODE"));
                // GroupVisit always false for now
                reviewVisitDetail.setGroupVisitIndicator(false);
                reviewVisitDetail.setRateType(resultSet.getString("RATE_TYP_NAME"));

                reviewVisitDetail.setCallHours(resultSet.getString("CALL_HRS"));

                // Patient Verified Time and Patient Verified Service always NULL for now
                reviewVisitDetail.setPatientVerifiedTimeIndicator(null);
                reviewVisitDetail.setPatientVerifiedServiceIndicator(null);

                reviewVisitDetail.setPatientDigitalSignatureDocId(resultSet.getString("PT_DIG_SIGNATURE_DOC_ID"));
                reviewVisitDetail.setPatientAudioSignatureDocId(resultSet.getString("PT_AUD_SIGNATURE_DOC_ID"));

                reviewVisitDetail.setVisitSource(resultSet.getString("VENDOR_NAME"));
                reviewVisitDetail.setThirdPartyVisitId(resultSet.getString("THIRD_PARTY_VISIT_ID"));

                return reviewVisitDetail;
            }

            return null;

        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("%s: %s: Exception: %s",
                    getClass().getSimpleName(), e.getClass().getSimpleName()), e);
        }
    }
}
