package com.sandata.lab.rest.sched.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.date.DateUtil;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.audit.AuditChanged;
import com.sandata.lab.data.model.dl.model.extended.ScheduleEventExt;
import com.sandata.lab.data.model.dl.model.extended.schedule.ScheduleEvntExt;
import com.sandata.lab.data.model.dl.model.extended.schedule.ScheduleExt;
import com.sandata.lab.data.model.dl.model.extended.schedule.SelectedDaysOfWeek;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.sched.api.OracleService;
import com.sandata.lab.rest.sched.model.AuthorizationAdapter;
import com.sandata.lab.rest.sched.model.SchedEventRelatedSK;
import com.sandata.lab.rest.sched.model.SchedUpdateStaff;
import com.sandata.lab.rest.sched.model.ScheduleEventCountAndDuration;
import com.sandata.lab.rest.sched.model.StaffExt;
import com.sandata.lab.rest.sched.utils.ScheduleUtils;

import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

@SuppressWarnings("unchecked")
public class OracleDataService implements OracleService {

    private static final SimpleDateFormat ORACLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat ORACLE_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private ConnectionPoolDataService connectionPoolDataService;

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

    private ScheduleEvent mapScheduleEvent(final ResultSet resultSet) throws SQLException {

        ScheduleEvntExt scheduleEvent = new ScheduleEvntExt();

        scheduleEvent.setScheduleEventID(resultSet.getString("SCHED_EVNT_ID"));
        scheduleEvent.setScheduleSK(BigInteger.valueOf(resultSet.getBigDecimal("SCHED_SK").longValue()));
        scheduleEvent.setScheduleEventSK(BigInteger.valueOf(resultSet.getBigDecimal("SCHED_EVNT_SK").longValue()));
        scheduleEvent.setBusinessEntityID(resultSet.getString("BE_ID"));
        scheduleEvent.setPayerID(resultSet.getString("PAYER_ID"));

        BigDecimal calSk = resultSet.getBigDecimal("BE_CALENDAR_LKUP_SK");
        if (calSk != null) {
            scheduleEvent.setBusinessEntityCalendarLookupSK(BigInteger.valueOf(calSk.longValue()));
        }

        BigDecimal authSk = resultSet.getBigDecimal("AUTH_SK");
        if (authSk != null) {
            scheduleEvent.setAuthorizationSK(BigInteger.valueOf(authSk.longValue()));
        }

        scheduleEvent.setPatientID(resultSet.getString("PT_ID"));

        BigDecimal rfrlSk = resultSet.getBigDecimal("RFRL_SK");
        if (rfrlSk != null) {
            scheduleEvent.setReferralSK(BigInteger.valueOf(rfrlSk.longValue()));
        }

        scheduleEvent.setStaffID(resultSet.getString("STAFF_ID"));

        Timestamp eventStartDate = resultSet.getTimestamp("SCHED_EVNT_START_DTIME");
        if(eventStartDate != null) {
            scheduleEvent.setScheduleEventStartDatetime(new java.util.Date(eventStartDate.getTime()));
        }

        Timestamp eventEndDate = resultSet.getTimestamp("SCHED_EVNT_END_DTIME");
        if(eventEndDate != null) {
            scheduleEvent.setScheduleEventEndDatetime(new java.util.Date(eventEndDate.getTime()));
        }

        Timestamp recCreateTmstp = resultSet.getTimestamp("REC_CREATE_TMSTP");
        if(recCreateTmstp != null) {
            scheduleEvent.setRecordCreateTimestamp(new java.util.Date(recCreateTmstp.getTime()));
        }

        Timestamp recUpdateTmstp = resultSet.getTimestamp("REC_UPDATE_TMSTP");
        if(recUpdateTmstp != null) {
            scheduleEvent.setRecordUpdateTimestamp(new java.util.Date(recUpdateTmstp.getTime()));
        }

        Timestamp recEffTmstp = resultSet.getTimestamp("REC_EFF_TMSTP");
        if(recEffTmstp != null) {
            scheduleEvent.setRecordEffectiveTimestamp(new java.util.Date(recEffTmstp.getTime()));
        }

        Timestamp recTermTmstp = resultSet.getTimestamp("REC_TERM_TMSTP");
        if(recTermTmstp != null) {
            scheduleEvent.setRecordTerminationTimestamp(new java.util.Date(recTermTmstp.getTime()));
        }

        //dmr--GEOR-1083
        scheduleEvent.setChangeReasonMemo(resultSet.getString("CHANGE_REASON_MEMO"));

        //dmr--GEOR-2672
        scheduleEvent.setChangeReasonCode(resultSet.getString("CHANGE_REASON_CODE"));

        scheduleEvent.setChangeVersionID(BigInteger.valueOf(resultSet.getBigDecimal("CHANGE_VERSION_ID").longValue()));

        scheduleEvent.setTimezoneName(resultSet.getString("TZ_NAME"));
        scheduleEvent.setScheduleEventBillRate(resultSet.getBigDecimal("SCHED_EVNT_BILL_RATE"));
        scheduleEvent.setMasterRateID(resultSet.getString("MASTER_RATE_ID"));
        scheduleEvent.setDayOfMonth(resultSet.getString("DAY_OF_MONTH"));
        scheduleEvent.setScheduleEventTotalHours(resultSet.getBigDecimal("SCHED_EVNT_TOTAL_HRS"));
        scheduleEvent.setScheduleEventStatus(resultSet.getString("SCHED_EVNT_STATUS"));
        scheduleEvent.setScheduleEventPayRate(resultSet.getBigDecimal("SCHED_EVNT_PAY_RATE"));
        scheduleEvent.setScheduleEventUnit(resultSet.getString("SCHED_EVNT_UNIT"));
        scheduleEvent.setScheduleEventRestriction(resultSet.getString("SCHED_EVNT_RESTRICTION"));
        scheduleEvent.setScheduleEventComment(resultSet.getString("SCHED_EVNT_COMMENT"));

        //dmr--GEOR-5791
        scheduleEvent.setScheduleEventManuallyOverrideIndicator(resultSet.getBoolean("SCHED_EVNT_MANUAL_OVERRIDE_IND"));

        scheduleEvent.setWorkHours(resultSet.getBigDecimal("WORK_HOURS"));
        scheduleEvent.setScheduleHours(resultSet.getBigDecimal("SCHEDULE_HOURS"));
        try {
            scheduleEvent.setAuthorizationQualifier(AuthorizationQualifier.fromValue(resultSet.getString("AUTH_QLFR")));
        } catch (Exception e) {
            // ignore
        }

        ScheduleUtils.populateScheduleEventOvernightIndicator(scheduleEvent);

        return scheduleEvent;
    }



    private FindScheduleResult mapSchedule(final ResultSet resultSet) throws SQLException {

        FindScheduleResult findScheduleResult = new FindScheduleResult();
        Schedule schedule = new Schedule();
        findScheduleResult.setSchedule(schedule);

        ScheduleEvent scheduleEvent = mapScheduleEvent(resultSet);
        schedule.getScheduleEvent().add(scheduleEvent);

        schedule.setScheduleID(resultSet.getString("SCHED_ID"));
        schedule.setScheduleSK(scheduleEvent.getScheduleSK());
        schedule.setPatientID(scheduleEvent.getPatientID());

        schedule.setStaffID(resultSet.getString("STAFF_ID"));
        schedule.setChangeVersionID(BigInteger.valueOf(resultSet.getBigDecimal("SCHED_CHANGE_VERSION_ID").longValue()));
        schedule.setBusinessEntityID(scheduleEvent.getBusinessEntityID());

        BigDecimal payerSk = resultSet.getBigDecimal("PAYER_SK");
        if (payerSk != null) {
            schedule.setPayerSK(BigInteger.valueOf(payerSk.longValue()));
        }

        schedule.setReferralSK(scheduleEvent.getReferralSK());

        schedule.setRecordCreateTimestamp(new java.util.Date());
        schedule.setRecordUpdateTimestamp(new java.util.Date());

        Timestamp startDate = resultSet.getTimestamp("SCHED_START_DATE");
        if(startDate != null) {
            schedule.setScheduleStartDate(new java.util.Date(startDate.getTime()));
        }

        Timestamp endDate = resultSet.getTimestamp("SCHED_END_DATE");
        if(endDate != null) {
            schedule.setScheduleEndDate(new java.util.Date(endDate.getTime()));
        }

        BigDecimal numOfOccurrences = resultSet.getBigDecimal("NUM_OF_OCCURENCES");
        if (numOfOccurrences != null) {
            schedule.setNumberOfOccurences(BigInteger.valueOf(numOfOccurrences.longValue()));
        }

        schedule.setSchedulePayRate(resultSet.getBigDecimal("SCHED_PAY_RATE"));
        schedule.setScheduleBillRate(resultSet.getBigDecimal("SCHED_BILL_RATE"));
        schedule.setScheduleUnit(resultSet.getString("SCHED_UNIT"));
        schedule.setScheduleFrequencyID(resultSet.getString("SCHED_FREQ_ID"));
        schedule.setScheduleRestrictions(resultSet.getString("SCHED_RESTRICTIONS"));
        schedule.setScheduleComments(resultSet.getString("SCHED_CMNT"));

        findScheduleResult.setPatientFirstName(resultSet.getString("PT_FIRST_NAME"));
        findScheduleResult.setPatientLastName(resultSet.getString("PT_LAST_NAME"));
        findScheduleResult.setStaffFirstName(resultSet.getString("STAFF_FIRST_NAME"));
        findScheduleResult.setStaffLastName(resultSet.getString("STAFF_LAST_NAME"));
        findScheduleResult.setServiceName(resultSet.getString("SVC_NAME"));

        //dmr GEOR-2359: 2/25/2016: Check to see if this schedule has an approved visit
        BigDecimal approvedVisitSk = resultSet.getBigDecimal("APPROVED_VISIT_SK");
        if (approvedVisitSk != null && approvedVisitSk.longValue() > 0) {

            long visitException = resultSet.getLong("VISIT_EXCEPTIONS");
            if (visitException > 0) {
                findScheduleResult.setScheduleEventStatus("Visit Completed with Exception");
            }
            else {
                findScheduleResult.setScheduleEventStatus("Visit Completed without Exception");
            }
        }

        BigDecimal cancelledVisitSk = resultSet.getBigDecimal("CANCELLED_VISIT_SK");
        if (cancelledVisitSk != null && cancelledVisitSk.longValue() > 0) {
            findScheduleResult.setScheduleEventStatus("Cancelled");
        }
        //dmr GEOR-2359

        //dmr GEOR-2364: 2/25/2016: Add Patient/Staff address info
        findScheduleResult.setPatientAddr1(resultSet.getString("PT_ADDR1"));
        findScheduleResult.setPatientAddr2(resultSet.getString("PT_ADDR2"));
        findScheduleResult.setPatientApartmentNumber(resultSet.getString("PT_APT_NUM"));
        findScheduleResult.setPatientCity(resultSet.getString("PT_CITY"));
        findScheduleResult.setPatientState(resultSet.getString("PT_STATE"));
        findScheduleResult.setPatientPostalCode(resultSet.getString("PT_PSTL_CODE"));
        findScheduleResult.setPatientZip(resultSet.getString("PT_ZIP4"));

        String phoneQualifier = resultSet.getString("PT_CONT_PHONE_QLFR");

        if(!StringUtil.IsNullOrEmpty(phoneQualifier)) {
            findScheduleResult.setPatientPhoneQualifier(PhoneQualifier.valueOf(phoneQualifier));
        }

        findScheduleResult.setPatientPhone(resultSet.getString("PT_PHONE"));
        findScheduleResult.setPatientPhoneExtension(resultSet.getString("PT_PHONE_EXT"));

        findScheduleResult.setStaffAddr1(resultSet.getString("STAFF_ADDR1"));
        findScheduleResult.setStaffAddr2(resultSet.getString("STAFF_ADDR2"));
        //dmr--MISSING--findScheduleResult.setStaffApartmentNumber(resultSet.getString(""));
        findScheduleResult.setStaffCity(resultSet.getString("STAFF_CITY"));
        findScheduleResult.setStaffState(resultSet.getString("STAFF_STATE"));
        findScheduleResult.setStaffPostalCode(resultSet.getString("STAFF_ZIP4"));
        //dmr--MISSING--findScheduleResult.setStaffZip(resultSet.getString(""));
        //dmr GEOR-2364



        return findScheduleResult;
    }

    public Response findSchedule(
                                 final String staffId,
                                 final String patientId,
                                 final String bsnEntId,
                                 final Long authSk,
                                 final int page,
                                 final int pageSize,
                                 final java.util.Date startDate,
                                 final java.util.Date endDate,
                                 final String orderByColumn,
                                 final String orderByDirection,
                                 final List<String> statuses) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        String orderByString = "T3.STAFF_LAST_NAME";
        if (orderByColumn.equals("fn")) {
            orderByString = "UPPER(T3.STAFF_FIRST_NAME)";
        }
        else if (orderByColumn.equals("svc")) {
            orderByString = "UPPER(T5.SVC_NAME)";
        } else if (orderByColumn.equals("evnt_start")) {
            orderByString = "T1.SCHED_EVNT_START_DTIME";
        } else if (orderByColumn.equals("evnt_end")) {
            orderByString = "T1.SCHED_EVNT_END_DTIME";
        }

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            StringBuilder filterItems = new StringBuilder();

            if (staffId != null && staffId.length() > 0) {
                filterItems.append("T1.STAFF_ID = ? AND ");
            }

            if (patientId != null && patientId.length() > 0) {
                filterItems.append("T1.PT_ID = ? AND ");
            }

            if (bsnEntId != null && bsnEntId.length() > 0) {
                filterItems.append("T1.BE_ID = ? AND ");
            }

            if (authSk != null && authSk > 0) {
                filterItems.append("T1.AUTH_SK = ? AND ");
            }

            boolean setScheduleDates = false;
            if (startDate != null && endDate != null) {
                setScheduleDates = true;
                filterItems.append("T1.SCHED_EVNT_START_DTIME >= " +
                                "TO_DATE(?, 'YYYY-MM-DD HH24:MI') AND " +
                                "T1.SCHED_EVNT_START_DTIME < TO_DATE(?, 'YYYY-MM-DD HH24:MI') AND ");
            }
            
            if (statuses != null && !statuses.isEmpty()) {
                StringBuilder statusParam = new StringBuilder("?");
                for (int i = 1; i < statuses.size(); i++) {
                    statusParam.append(",?");
                }
                
                filterItems.append(String.format("UPPER(T1.SCHED_EVNT_STATUS) IN (%s) AND ", statusParam.toString()));
            }

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, r1.*  FROM " +
                            "  (SELECT DISTINCT T6.PT_FIRST_NAME,T6.PT_LAST_NAME,T3.STAFF_FIRST_NAME,T3.STAFF_LAST_NAME,T5.SVC_NAME,T1.SCHED_EVNT_SK,T1.SCHED_EVNT_ID,T1.BE_ID,T1.SCHED_SK,T1.PAYER_ID,T1.BE_CALENDAR_LKUP_SK,T1.AUTH_SK,T1.AUTH_QLFR, " +
                            "    T1.PT_ID,T1.RFRL_SK,T1.STAFF_ID,T3.STAFF_SK,T1.TZ_NAME,T1.SCHED_EVNT_BILL_RATE, " +
                            "    T1.MASTER_RATE_ID,T1.DAY_OF_MONTH,T1.SCHED_EVNT_START_DTIME,T1.SCHED_EVNT_END_DTIME, " +
                            "    T1.SCHED_EVNT_TOTAL_HRS,T1.SCHED_EVNT_STATUS,T1.SCHED_EVNT_PAY_RATE,T1.SCHED_EVNT_UNIT, " +
                            "    T1.SCHED_EVNT_RESTRICTION,T1.SCHED_EVNT_COMMENT,T1.REC_CREATE_TMSTP,T1.REC_UPDATE_TMSTP,T1.REC_EFF_TMSTP,T1.REC_TERM_TMSTP,T1.CHANGE_REASON_MEMO,T1.CHANGE_REASON_CODE, " +
                            "    T2.SCHED_ID,T2.SCHED_START_DATE,T2.SCHED_END_DATE,T2.NUM_OF_OCCURENCES, " +
                            "    T2.SCHED_PAY_RATE,T2.SCHED_BILL_RATE,T2.SCHED_UNIT,T2.SCHED_FREQ_ID,T2.SCHED_RESTRICTIONS,T2.SCHED_CMNT,T7.PAYER_SK, " +
                            "    T3Addr.STAFF_ADDR1,T3Addr.STAFF_ADDR2,T3Addr.STAFF_CITY,T3Addr.STAFF_STATE,T3Addr.STAFF_ZIP4, " +
                            "    T10.PT_ADDR1,T10.PT_ADDR2,T10.PT_APT_NUM,T10.PT_CITY,T10.PT_STATE,T10.PT_PSTL_CODE,T10.PT_ZIP4, " +
                            "    T11.PT_CONT_PHONE_QLFR, T11.PT_PHONE, T11.PT_PHONE_EXT, " +
                            " " +
                            "   (CASE WHEN T8.VISIT_APRVD_IND = 1 " +
                            "           THEN T8.VISIT_SK END) AS APPROVED_VISIT_SK," +
                            "  " +
                            "   (CASE WHEN T8.VISIT_CANCELLED_IND = 1 " +
                            "           THEN T8.VISIT_SK END) AS CANCELLED_VISIT_SK, " +
                            " " +
                            "    (CASE WHEN T8.VISIT_APRVD_IND = 1 AND T8.VISIT_PAY_HRS IS NOT NULL " +
                            "           THEN T8.VISIT_PAY_HRS" +
                            "          WHEN T8.VISIT_ADJ_END_TMSTP IS NOT NULL AND T8.VISIT_ADJ_START_TMSTP IS NOT NULL " +
                            "           THEN (T8.VISIT_ADJ_END_TMSTP - T8.VISIT_ADJ_START_TMSTP) * 24 " +
                            "          WHEN T8.VISIT_ACT_END_TMSTP IS NOT NULL AND T8.VISIT_ACT_START_TMSTP IS NOT NULL" +
                            "           THEN (T8.VISIT_ACT_END_TMSTP - T8.VISIT_ACT_START_TMSTP) * 24 " +
                            "         ELSE 0 END) AS WORK_HOURS, " +
                            "" +
                            "    (SELECT COUNT(*) FROM VISIT_EXCP WHERE VISIT_SK = T8.VISIT_SK AND T8.VISIT_APRVD_IND = 1) AS VISIT_EXCEPTIONS, " +
                            "    T1.CHANGE_VERSION_ID, " +
                            "    T2.CHANGE_VERSION_ID AS SCHED_CHANGE_VERSION_ID, " +
                            "    T1.SCHED_EVNT_MANUAL_OVERRIDE_IND, " +
                            "    ( " +
                            "        (T1.SCHED_EVNT_END_DTIME - T1.SCHED_EVNT_START_DTIME) * 24 " +
                            "    ) AS SCHEDULE_HOURS " +
                            
                            "    FROM SCHED_EVNT T1 " +
                            " " +
                            "    INNER JOIN (SELECT SCHED_ID,SCHED_SK,SCHED_START_DATE,SCHED_END_DATE, " +
                            "        NUM_OF_OCCURENCES,SCHED_PAY_RATE,SCHED_BILL_RATE,SCHED_UNIT,SCHED_FREQ_ID,SCHED_RESTRICTIONS,SCHED_CMNT,CHANGE_VERSION_ID " +
                            "      FROM SCHED) T2 " +
                            "    ON T1.SCHED_SK = T2.SCHED_SK " +
                            " " +
                            "    LEFT OUTER JOIN (SELECT BE_ID,STAFF_SK,STAFF_ID,STAFF_FIRST_NAME,STAFF_LAST_NAME,REC_TERM_TMSTP,CURR_REC_IND " +
                            "      FROM STAFF WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T3 " +
                            "    ON T1.STAFF_ID = T3.STAFF_ID AND T1.BE_ID = T3.BE_ID " +
                            " " +
                            "    LEFT JOIN (SELECT BE_ID,STAFF_ID,STAFF_ADDR1,STAFF_ADDR2,STAFF_CITY,STAFF_STATE,STAFF_ZIP4,ADDR_PRIO_NAME,REC_TERM_TMSTP,CURR_REC_IND " +
                            "      FROM STAFF_CONT_ADDR WHERE UPPER(ADDR_PRIO_NAME) = 'PRIMARY' AND " +
                            "        (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T3Addr " +
                            "    ON T1.STAFF_ID = T3Addr.STAFF_ID AND T1.BE_ID = T3Addr.BE_ID " +
                            " " +
                            "    INNER JOIN (SELECT SCHED_EVNT_SK,SVC_SK,REC_TERM_TMSTP,CURR_REC_IND " +
                            "      FROM SCHED_EVNT_SVC WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T4 " +
                            "    ON T1.SCHED_EVNT_SK = T4.SCHED_EVNT_SK " +
                            " " +
                            "    INNER JOIN (SELECT SVC_SK,SVC_NAME " +
                            "      FROM SVC WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T5 " +
                            "    ON T4.SVC_SK = T5.SVC_SK " +
                            " " +
                            "    INNER JOIN (SELECT BE_ID,PT_ID,PT_FIRST_NAME,PT_LAST_NAME " +
                            "      FROM PT WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T6 " +
                            "    ON T1.PT_ID = T6.PT_ID AND T1.BE_ID = T6.BE_ID " +
                            " " +
                            "    LEFT JOIN (SELECT BE_ID,PAYER_SK,PAYER_ID " +
                            "      FROM PAYER WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T7 " +
                            "    ON T1.PAYER_ID = T7.PAYER_ID AND T1.BE_ID = T7.BE_ID " +
                            " " +
                            "    LEFT OUTER JOIN VISIT T8 " +
                            "    ON T1.SCHED_EVNT_SK = T8.SCHED_EVNT_SK " +
                            " " +

                            " " +
                            "    LEFT OUTER JOIN (SELECT BE_ID,PT_ID,PT_ADDR_TYP_NAME,PT_ADDR1,PT_ADDR2,PT_APT_NUM, " +
                            "        PT_CITY,PT_STATE,PT_PSTL_CODE,PT_ZIP4,REC_TERM_TMSTP,CURR_REC_IND " +
                            "      FROM PT_CONT_ADDR WHERE UPPER(ADDR_PRIO_NAME) = 'PRIMARY' AND " +
                            "        (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T10 " +
                            "    ON T6.PT_ID = T10.PT_ID AND T6.BE_ID = T10.BE_ID " +
                            " " +
                            "    LEFT OUTER JOIN (SELECT BE_ID,PT_ID,ADDR_PRIO_NAME,PT_CONT_PHONE_QLFR,PT_PHONE,PT_PHONE_EXT, " +
                            "        PT_PHONE_ANI_ENABLED_IND,PT_PHONE_PRMY_IND,PT_PHONETEXT_MSG_IND " +
                            "      FROM PT_CONT_PHONE WHERE PT_PHONE_PRMY_IND = 1 AND " +
                            "        (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T11 " +
                            "    ON T6.PT_ID = T11.PT_ID AND T6.BE_ID = T11.BE_ID " +
                            " " +
                            "    WHERE %s " +
                            "    (" +
                            "      SCHED_EVNT_START_DTIME <= CURRENT_DATE" +
                            "      OR (SCHED_EVNT_START_DTIME > CURRENT_DATE AND SCHED_EVNT_STATUS != 'CANCELLED')" +
                            "    ) AND " +
                            "    (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1) " +
                            "    ORDER BY %s %s " +
                            " " +
                            ") r1) " +
                            " " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d ",
                    filterItems.toString(),
                    orderByString,
                    orderByDirection,
                    fromRow,
                    toRow);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;

            if (staffId != null && staffId.length() > 0) {
                preparedStatement.setString(index++, staffId);
            }

            if (patientId != null && patientId.length() > 0) {
                preparedStatement.setString(index++, patientId);
            }

            if (bsnEntId != null && bsnEntId.length() > 0) {
                preparedStatement.setString(index++, bsnEntId);
            }

            if (authSk != null && authSk > 0) {
                preparedStatement.setLong(index++, authSk);
            }

            if (setScheduleDates) {
                preparedStatement.setString(index++, ORACLE_DATE_TIME_FORMAT.format(startDate));
                preparedStatement.setString(index++, ORACLE_DATE_TIME_FORMAT.format(endDate));
            }
            
            if (statuses != null && !statuses.isEmpty()) {
                for (String status : statuses) {
                    preparedStatement.setString(index++, status.toUpperCase(Locale.US));
                }
            }

            resultSet = preparedStatement.executeQuery();

            List<FindScheduleResult> findSchedules = new ArrayList<>();

            Response response = new Response();
            response.setData(findSchedules);
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(orderByString);
            response.setOrderByDirection(orderByDirection);

            while (resultSet.next()) {

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());
                }

                findSchedules.add(mapSchedule(resultSet));
            }
            
            resultSet.close();
            preparedStatement.close();
            
            calculateScheduleUnitHours(connection, findSchedules);

            return response;

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
            this.connectionPoolDataService.close(connection);
        }
    }
    
    private void calculateScheduleUnitHours(Connection connection, List<FindScheduleResult> findSchedules) {
        Map<String, AuthorizationAdapter> scheduleAuthorizationMap = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        for (FindScheduleResult findSchedule : findSchedules) {
            ScheduleEvntExt schedEvnt = (ScheduleEvntExt) findSchedule.getSchedule().getScheduleEvent().get(0);
            BigDecimal workHours = schedEvnt.getWorkHours() != null ? schedEvnt.getWorkHours() : BigDecimal.ZERO;
            BigDecimal scheduleHours = schedEvnt.getScheduleHours() != null ? schedEvnt.getScheduleHours() : BigDecimal.ZERO;
            BigDecimal calculatedWorkHours = BigDecimal.ZERO;
            BigDecimal calculatedScheduledHours = BigDecimal.ZERO;
            
            if (schedEvnt.getAuthorizationSK() != null 
                    && !"CANCELLED".equalsIgnoreCase(schedEvnt.getScheduleEventStatus())
                    && schedEvnt.getAuthorizationQualifier() != null) {
                
                long authorizationSK = schedEvnt.getAuthorizationSK().longValue();
                AuthorizationAdapter auth = null;
                String authMapKey = String.format("%s-%s", authorizationSK, schedEvnt.getAuthorizationQualifier());
                
                
                if (scheduleAuthorizationMap.get(authMapKey) == null) {
                    auth = getAuthorizationAdapter(connection, authorizationSK, schedEvnt.getAuthorizationQualifier());
                    
                    // if the authorization unit is 'UNIT' and it's not logical deleted, get the Unit Equivalance from Contract
                    if (AuthorizationServiceUnitName.UNIT == auth.getAuthorizationServiceUnitName()
                            && auth != null && auth.isCurrentRecordIndicator()
                            && dateFormat.format(auth.getRecordTerminationTimestamp()).equals("9999-12-31")) {
                    
                        boolean isOrder = AuthorizationQualifier.ORDER.equals(schedEvnt.getAuthorizationQualifier());
                        Contract contract = getContractForAuthService(connection, schedEvnt.getBusinessEntityID(), 
                                authorizationSK,
                                isOrder);
                        if (contract == null) {
                            throw new SandataRuntimeException(
                                    String.format("The Authorization SK=%d, isOrder=%s has Authorizaiton Service Unit Name = UNIT but no contract found!", 
                                            authorizationSK, isOrder)
                            );
                        }
                        auth.getContract().add(contract);
                        auth.setEquivalence(getContractServiceUnitEquivalence(connection, contract));
                    }
                    
                    scheduleAuthorizationMap.put(authMapKey, auth);
                } else {
                    auth = scheduleAuthorizationMap.get(authMapKey);
                }
                
                // only calculate for Authorization which is not logical deleted
                if (auth != null && auth.isCurrentRecordIndicator()
                        && dateFormat.format(auth.getRecordTerminationTimestamp()).equals("9999-12-31")) {
                    AuthorizationServiceUnitName authSvcUnitName = auth.getAuthorizationServiceUnitName();

                    schedEvnt.setAuthServiceUnitName(authSvcUnitName);

                    switch(authSvcUnitName) {
                        case UNIT:
                            if (auth.getEquivalence() != null) {
                                calculatedWorkHours = workHours.multiply(BigDecimal.valueOf(60))
                                        .divide(BigDecimal.valueOf(auth.getEquivalence().longValue()), 2, RoundingMode.HALF_UP);
                                calculatedScheduledHours = scheduleHours.multiply(BigDecimal.valueOf(60))
                                        .divide(BigDecimal.valueOf(auth.getEquivalence().longValue()), 2, RoundingMode.HALF_UP);
                            }
                            break;
                        case VISIT:
                            calculatedWorkHours = (workHours.compareTo(BigDecimal.ZERO) > 0) ? BigDecimal.ONE : BigDecimal.ZERO;
                            calculatedScheduledHours = (scheduleHours.compareTo(BigDecimal.ZERO) > 0) ? BigDecimal.ONE : BigDecimal.ZERO;
                            break;
                        // 1 LIVE-IN = 24 Hours
                        case LIVE_IN:
                            calculatedWorkHours = workHours.divide(BigDecimal.valueOf(24), 2, RoundingMode.HALF_UP);
                            calculatedScheduledHours = scheduleHours.divide(BigDecimal.valueOf(24), 2, RoundingMode.HALF_UP);
                            break;
                        case HOUR:
                            calculatedWorkHours = workHours.setScale(2, RoundingMode.HALF_UP);
                            calculatedScheduledHours = scheduleHours.setScale(2, RoundingMode.HALF_UP);
                            break;
                    }
                }
            }


            schedEvnt.setWorkHours(calculatedWorkHours);
            schedEvnt.setScheduleHours(calculatedScheduledHours);
            ScheduleUtils.populateScheduleEventOvernightIndicator(schedEvnt);
        }
    }

    public Response deleteSchedule(final long scheduleSk, final int... weekdays) {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            Response response = new Response();

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            ArrayDescriptor arrayDescriptor = ArrayDescriptor.createDescriptor(ConnectionType.COREDATA + ".WEEK_DAY_LIST", connection);
            ARRAY weekDayArray = new ARRAY(arrayDescriptor, connection, weekdays);

            String callMethod = "{?=call PKG_SCHEDULE_UTIL.DELETE_SCHEDULES(?,?)}";
            callableStatement = connection.prepareCall(callMethod);

            int index = 1;
            callableStatement.registerOutParameter(index++, OracleTypes.BIGINT);
            callableStatement.setLong(index++, scheduleSk);
            callableStatement.setArray(index++, weekDayArray);

            callableStatement.execute();
            long result = callableStatement.getLong(1);

            if (result < 0) {
                connection.rollback();
                response.setData("FAILED");
                return response;
            }

            // commit the transaction
            connection.commit();

            response.setData("SUCCESS");

            return response;

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

            // Close the statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    /**
     * 
     * @param filterTyp
     * @param bsnEntID
     * @param params
     * @param orderByColumn
     * @param page
     * @param pageSize
     * @param noConflictedWithScheduleSK
     * @param noConflictedWithEventSK
     * @param eventDateFrom
     * @param eventDateTo
     * @param tobeUpdatedEvent                  The Event that will be updated with given start/end date that we will check conflicted schedule. 
     * @param tobeUpdatedScheduleSK             The Schedule that will be updated 
     * @param canConflictedWithServiceSK        If the given service is different from overlapped/conflicted schedule'service, we accept it        
     * @return
     * @throws SandataRuntimeException
     */
    public Response findStaff(String filterTyp, final String bsnEntID, final List<Object> params,
                              final String orderByColumn, final int page, final int pageSize,
                             /* final long noConflictedWithScheduleSK, final long noConflictedWithEventSK,
                              final String eventDateFrom, final String eventDateTo)*/

                              final long noConflictedWithScheduleSK, final long noConflictedWithEventSK,
                              final String eventDateFrom, final String eventDateTo, 
                              final ScheduleEvent tobeUpdatedEvent, final BigInteger tobeUpdatedScheduleSK)
            throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            connection = connectionPoolDataService.getConnection();

            String conflictQuery = "";
            List<Object> conflictParams = new ArrayList<>();
            if ((noConflictedWithScheduleSK > 0 || noConflictedWithEventSK > 0) 
                    // SAN-3313: User is able to overlap schedules with the same staff and service
                    || tobeUpdatedEvent != null) {
                conflictParams.add(bsnEntID);
                
                String getCheckConflictEventTable = "";
                StringBuilder getEventAdditionalCond = new StringBuilder();

                String tobeUpdatedService = "";
                
                // for validation only
                if (tobeUpdatedEvent != null) {
                    getCheckConflictEventTable = String.format("SELECT %d AS SCHED_EVNT_SK, " +
                            "TO_DATE('%s', 'YYYY-MM-DD HH24:MI') AS SCHED_EVNT_START_DTIME, " +
                            "TO_DATE('%s', 'YYYY-MM-DD HH24:MI') AS SCHED_EVNT_END_DTIME, ? AS TZ_NAME, ? AS BE_ID, ? AS PT_ID, ? AS STAFF_ID " +
                            "FROM DUAL",
                            tobeUpdatedEvent.getScheduleEventSK() != null ? tobeUpdatedEvent.getScheduleEventSK().longValue() : 0,
                            ORACLE_DATE_TIME_FORMAT.format(tobeUpdatedEvent.getScheduleEventStartDatetime()),
                            ORACLE_DATE_TIME_FORMAT.format(tobeUpdatedEvent.getScheduleEventEndDatetime()));
                    conflictParams.add(tobeUpdatedEvent.getTimezoneName());
                    conflictParams.add(bsnEntID);
                    conflictParams.add(tobeUpdatedEvent.getPatientID());
                    conflictParams.add(tobeUpdatedEvent.getStaffID());
                    
                    getEventAdditionalCond.append((tobeUpdatedEvent.getScheduleEventSK() != null && tobeUpdatedEvent.getScheduleEventSK().longValue() > 0) 
                            ? String.format(" AND SE.SCHED_EVNT_SK <> %d ",tobeUpdatedEvent.getScheduleEventSK().longValue()) : "");
                    getEventAdditionalCond.append((tobeUpdatedScheduleSK != null && tobeUpdatedScheduleSK.longValue() > 0) 
                            ? String.format(" AND SE.SCHED_SK <> %d ", tobeUpdatedScheduleSK.longValue()) : "");
                    tobeUpdatedService = String.valueOf(tobeUpdatedEvent.getScheduleEventService().get(0).getServiceSK());
                    
                // for find staffs only    
                } else {
                    StringBuilder conditionGetEvents = new StringBuilder();
                    
                    // permanent
                    if (noConflictedWithScheduleSK > 0) {
                        conditionGetEvents.append(" K1.SCHED_SK = ? AND ");
                        conflictParams.add(noConflictedWithScheduleSK);
                        // temporary
                        if (!StringUtil.IsNullOrEmpty(eventDateFrom) && !StringUtil.IsNullOrEmpty(eventDateTo)) {
                            conditionGetEvents.append("(K1.SCHED_EVNT_START_DTIME >= TO_DATE(?, 'YYYY-MM-DD HH24:MI') AND ")
                                .append(" K1.SCHED_EVNT_END_DTIME <= TO_DATE(?, 'YYYY-MM-DD HH24:MI')) AND ");
                            conflictParams.add(eventDateFrom);
                            conflictParams.add(eventDateTo);
                        }
                    // event only
                    } else if (noConflictedWithEventSK > 0) {
                        conditionGetEvents.append(" K1.SCHED_EVNT_SK = ? AND ");
                        conflictParams.add(noConflictedWithEventSK);
                    }
                    
                    getCheckConflictEventTable = String.format(" SELECT K1.*, K2.SVC_SK FROM SCHED_EVNT K1 " +
                            "LEFT JOIN SCHED_EVNT_SVC K2 ON K2.SCHED_EVNT_SK = K1.SCHED_EVNT_SK AND (TO_CHAR(K2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND K2.CURR_REC_IND = 1) " +
                            "WHERE %s (TO_CHAR(K1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND K1.CURR_REC_IND = 1) ",
                            conditionGetEvents.toString());
                    tobeUpdatedService = " J2.SVC_SK ";
                }
                
                conflictQuery = String.format("LEFT OUTER JOIN " +
                        "    ( " +
                        "    SELECT J1.STAFF_ID, J1.BE_ID, COUNT(J2.SCHED_EVNT_SK) AS EVENT_CONFLICTED_COUNT " +
                        "    FROM  " +
                        "        (SELECT DISTINCT STAFF_ID, BE_ID FROM STAFF WHERE BE_ID = ? AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) J1 " +
                        "    LEFT OUTER JOIN    " +
                        "        (%s) J2  ON J1.BE_ID = J2.BE_ID " +
                                
                        "    WHERE NOT  " +
                        "    ( " +
                                 // logic of checking schedule event overlaped
                                 // SAN-3269: Ability to assign the same staff to two consecutive visits for the same patient => remove completely the threshold between ScheduleEvents
                        "        NOT EXISTS ( " +
                        "            SELECT 1  " +
                        "            FROM SCHED_EVNT SE " +
                        // each created Event must have an service
                        "                LEFT JOIN SCHED_EVNT_SVC SES ON SES.SCHED_EVNT_SK = SE.SCHED_EVNT_SK AND (TO_CHAR(SES.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND SES.CURR_REC_IND = 1) " +
                        "            WHERE SE.BE_ID = J1.BE_ID AND SE.STAFF_ID = J1.STAFF_ID AND UPPER(SE.SCHED_EVNT_STATUS) <> 'CANCELLED' " +
                        "                AND ( " +
                        //  The admin should NOT be allowed to make conflicting/overlapping schedules for the same staff, with the same patient that have the SAME service
                        "                    SE.PT_ID = J2.PT_ID " +
                        "                    AND SES.SVC_SK IS NOT NULL AND SES.SVC_SK = %s " + // same staff, same patient, same services
                        "                ) " +
                        "                %s " +
                        "                AND (TO_CHAR(SE.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND SE.CURR_REC_IND = 1) " +
                        "                AND ( " +
                        "                    SE.SCHED_EVNT_START_DTIME < J2.SCHED_EVNT_END_DTIME AND SE.SCHED_EVNT_END_DTIME > J2.SCHED_EVNT_START_DTIME " +
                        "                ) " +
                        "        ) " +
                                 // logic of checking unavailability overlaped goes here
                        "        AND NOT EXISTS ( " +
                        "            SELECT 1 " +
                        "            FROM STAFF_AVAIL SA " +
                        "            WHERE SA.BE_ID = J1.BE_ID AND SA.STAFF_ID = J1.STAFF_ID AND AVAIL_DAY IS NULL AND SA.STAFF_IS_AVAILABLE_IND = 0 " +
                                         // not any specific availability/unavailability start/end hour between schedule event
                        "                AND ( " +
                        "                    (J2.SCHED_EVNT_START_DTIME < SA.AVAIL_END_HOUR AND J2.SCHED_EVNT_END_DTIME > SA.AVAIL_START_HOUR) " +
                        "                ) " +
                        "        ) " +
                                 // logic of checking availability goes here
                        "        AND ( " +
                                     // there is no availability on that Schedule Event day, we assume that staff is available all day
                        "            NOT EXISTS ( " +
                        "                SELECT 1 " +
                        "                FROM STAFF_AVAIL SA " +
                        "                WHERE SA.BE_ID = J1.BE_ID AND SA.STAFF_ID = J1.STAFF_ID AND SA.STAFF_IS_AVAILABLE_IND = 1 " +
                        "                    AND ( " +
                        "                        (SA.AVAIL_DAY IS NOT NULL  " +
                        "                        AND UPPER(TRIM(SA.AVAIL_DAY)) = UPPER(TRIM(TO_CHAR(CAST(from_tz(CAST(J2.SCHED_EVNT_START_DTIME AS TIMESTAMP), 'UTC') AT TIME ZONE J2.TZ_NAME AS TIMESTAMP), 'DAY'))) " +
                        "                        ) " +
                        "                      OR " +
                        "                        ( " +
                        "                        SA.AVAIL_DAY IS NULL  " +
                        "                        AND TRUNC(CAST(from_tz(CAST(SA.AVAIL_START_HOUR AS TIMESTAMP), 'UTC') AT TIME ZONE J2.TZ_NAME AS TIMESTAMP))  " +
                        "                              = TRUNC(CAST(from_tz(CAST(J2.SCHED_EVNT_START_DTIME AS TIMESTAMP), 'UTC') AT TIME ZONE J2.TZ_NAME AS TIMESTAMP)) " +
                        "                        ) " +
                        "                    ) " +
                        "            ) " +
                        "            OR " +
                                     // there is specific availability on that day and it cover the schedule event, specific availability win out general availability
                        "            EXISTS ( " +
                        "                SELECT 1 " +
                        "                FROM STAFF_AVAIL SA " +
                        "                WHERE SA.BE_ID = J1.BE_ID AND SA.STAFF_ID = J1.STAFF_ID AND SA.STAFF_IS_AVAILABLE_IND = 1 AND SA.AVAIL_DAY IS NULL " +
                        "                    AND TRUNC(CAST(from_tz(CAST(SA.AVAIL_START_HOUR AS TIMESTAMP), 'UTC') AT TIME ZONE J2.TZ_NAME AS TIMESTAMP))  " +
                        "                          = TRUNC(CAST(from_tz(CAST(J2.SCHED_EVNT_START_DTIME AS TIMESTAMP), 'UTC') AT TIME ZONE J2.TZ_NAME AS TIMESTAMP)) " +
                        "                    AND (J2.SCHED_EVNT_START_DTIME BETWEEN SA.AVAIL_START_HOUR AND SA.AVAIL_END_HOUR) " +
                        "                    AND (J2.SCHED_EVNT_END_DTIME BETWEEN SA.AVAIL_START_HOUR AND SA.AVAIL_END_HOUR) " +
                        "            ) " +
                        "            OR " +
                                     // there is no specific availability/unavailability, and there is general availability
                        "            ( " +
                        "                NOT EXISTS ( " +
                        "                    SELECT 1 " +
                        "                    FROM STAFF_AVAIL SA " +
                        "                    WHERE SA.BE_ID = J1.BE_ID AND SA.STAFF_ID = J1.STAFF_ID AND SA.AVAIL_DAY IS NULL  " +
                        "                            AND TRUNC(CAST(from_tz(CAST(SA.AVAIL_START_HOUR AS TIMESTAMP), 'UTC') AT TIME ZONE J2.TZ_NAME AS TIMESTAMP))  " +
                        "                                  = TRUNC(CAST(from_tz(CAST(J2.SCHED_EVNT_START_DTIME AS TIMESTAMP), 'UTC') AT TIME ZONE J2.TZ_NAME AS TIMESTAMP))  " +
                        "                ) " +
                        "                AND EXISTS ( " +
                        "                    SELECT 1 " +
                        "                    FROM STAFF_AVAIL SA " +
                        "                    WHERE SA.BE_ID = J1.BE_ID AND SA.STAFF_ID = J1.STAFF_ID AND SA.STAFF_IS_AVAILABLE_IND = 1 AND SA.AVAIL_DAY IS NOT NULL  " +
                        "                        AND UPPER(TRIM(SA.AVAIL_DAY)) = UPPER(TRIM(TO_CHAR(CAST(from_tz(CAST(J2.SCHED_EVNT_START_DTIME AS TIMESTAMP), 'UTC') AT TIME ZONE J2.TZ_NAME AS TIMESTAMP), 'DAY'))) " +
                        "                    AND ( " +
                        "                        (SA.AVAIL_START_HOUR IS NULL AND SA.AVAIL_END_HOUR IS NULL) " +
                        "                        OR " +
                        "                        ( " +
                        "                            (TO_CHAR(CAST(from_tz(CAST(J2.SCHED_EVNT_START_DTIME AS TIMESTAMP), 'UTC') AT TIME ZONE J2.TZ_NAME AS TIMESTAMP), 'HH24:MI:SS')  " +
                        "                                BETWEEN TO_CHAR(CAST(from_tz(CAST(SA.AVAIL_START_HOUR AS TIMESTAMP), 'UTC') AT TIME ZONE J2.TZ_NAME AS TIMESTAMP), 'HH24:MI:SS') " +
                        "                                    AND TO_CHAR(CAST(from_tz(CAST(SA.AVAIL_END_HOUR AS TIMESTAMP), 'UTC') AT TIME ZONE J2.TZ_NAME AS TIMESTAMP), 'HH24:MI:SS')) " +
                        "                            AND " +
                        "                            (TO_CHAR(CAST(from_tz(CAST(J2.SCHED_EVNT_END_DTIME AS TIMESTAMP), 'UTC') AT TIME ZONE J2.TZ_NAME AS TIMESTAMP), 'HH24:MI:SS')  " +
                        "                                BETWEEN TO_CHAR(CAST(from_tz(CAST(SA.AVAIL_START_HOUR AS TIMESTAMP), 'UTC') AT TIME ZONE J2.TZ_NAME AS TIMESTAMP), 'HH24:MI:SS') " +
                        "                                    AND TO_CHAR(CAST(from_tz(CAST(SA.AVAIL_END_HOUR AS TIMESTAMP), 'UTC') AT TIME ZONE J2.TZ_NAME AS TIMESTAMP), 'HH24:MI:SS')) " +
                        "                        ) " +
                        "                    ) " +
                        "                ) " +
                        "            ) " +
                        "        ) " +
                        
                        // SAN-3403: MW - Terminated to Active status validation and support
                        // the history/current status must cover the schedules in either the past or future
                        "        AND NOT EXISTS ( " +
                        "            SELECT 1 FROM STAFF HIST_STAFF " +
                        "            WHERE HIST_STAFF.STAFF_ID = J1.STAFF_ID AND HIST_STAFF.BE_ID = J1.BE_ID " +
                        "                AND HIST_STAFF.CURR_REC_IND = 1 " + // not to check terminate date because we are looking for history records
                        "                AND HIST_STAFF.STAFF_EMPLT_STATUS_TYP_NAME IS NOT NULL AND UPPER(HIST_STAFF.STAFF_EMPLT_STATUS_TYP_NAME) <> 'ACTIVE' " +
                        "                AND (HIST_STAFF.REC_CREATE_TMSTP < J2.SCHED_EVNT_END_DTIME AND HIST_STAFF.REC_TERM_TMSTP > J2.SCHED_EVNT_START_DTIME) " +
                        "        ) " +
                        // the history/current status must cover the schedule which are finded staff for
                        "        AND EXISTS ( " +
                        "            SELECT 1 FROM STAFF HIST_STAFF " +
                        "            WHERE HIST_STAFF.STAFF_ID = J1.STAFF_ID AND HIST_STAFF.BE_ID = J1.BE_ID " +
                        "                AND HIST_STAFF.CURR_REC_IND = 1 " + // not to check terminate date because we are looking for history and current active records
                        "                AND HIST_STAFF.STAFF_EMPLT_STATUS_TYP_NAME IS NOT NULL AND UPPER(HIST_STAFF.STAFF_EMPLT_STATUS_TYP_NAME) = 'ACTIVE' " +
                        "                AND (HIST_STAFF.REC_CREATE_TMSTP <= J2.SCHED_EVNT_START_DTIME) " + // must have a start Active staff earlier than event's start time
                        "        ) " +
                        
                        "      ) " +
                        "      GROUP BY J1.STAFF_ID, J1.BE_ID " +
                        "      ) T8 ON T8.BE_ID                 = T1.BE_ID " +
                        "    AND T8.STAFF_ID                    = T1.STAFF_ID "
                        , getCheckConflictEventTable, tobeUpdatedService, getEventAdditionalCond);
                
                filterTyp = filterTyp +
                         "   (T8.EVENT_CONFLICTED_COUNT = 0 OR T8.EVENT_CONFLICTED_COUNT IS NULL) AND ";
            }

            String sql = String.format("SELECT * FROM ( " +
                            "  SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, r1.* FROM " +
                            "  (SELECT * FROM " +
                            "  (SELECT DISTINCT T1.STAFF_SK," +
                            "   T1.STAFF_ID," +
                            "   T1.STAFF_FIRST_NAME," +
                            "   T1.STAFF_LAST_NAME," +
                            "   T6.LANG_NAME AS STAFF_PRMY_LANG," +
                            "   T1.STAFF_GENDER_TYP_NAME, " +
                            "   T3.STAFF_ADDR1," +
                            "   T3.STAFF_ADDR2,T3.STAFF_CITY,T3.STAFF_STATE," +
                            "   T3.STAFF_ZIP4,T1.STAFF_POSITION_NAME, " +
                            "   T1.STAFF_EMPLT_STATUS_TYP_NAME, " +
                            // we get the most recent staff status for preventing duplicated records with different status
                            "   ROW_NUMBER() OVER(PARTITION BY T1.STAFF_ID ORDER BY T1.REC_UPDATE_TMSTP DESC, T1.REC_TERM_TMSTP DESC) AS SAME_STAFF_ID_ORDER " +
                            "  FROM STAFF T1 " +
                            " " +
                            "  LEFT JOIN (SELECT STAFF_ID,STAFF_ADDR1,STAFF_ADDR2,STAFF_CITY,STAFF_STATE,STAFF_ZIP4,ADDR_PRIO_NAME,REC_TERM_TMSTP,CURR_REC_IND " +
                            "    FROM STAFF_CONT_ADDR WHERE UPPER(ADDR_PRIO_NAME) = 'PRIMARY' AND " +
                            "      (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T3 " +
                            "  ON T1.STAFF_ID = T3.STAFF_ID " +
                            " " +
                            "  LEFT JOIN (SELECT BE_ID,STAFF_ID,LANG_CODE,STAFF_LANG_PRMY_IND " +
                            "    FROM STAFF_LANG WHERE STAFF_LANG_PRMY_IND = 1 AND " +
                            "      (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T5 " +
                            "  ON T1.STAFF_ID = T5.STAFF_ID AND T1.BE_ID = T5.BE_ID " +
                            " " +
                            "  LEFT JOIN (SELECT LANG_CODE,LANG_NAME " +
                            "    FROM LANG_LKUP " +
                            "      WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T6 " +
                            "  ON T5.LANG_CODE = T6.LANG_CODE " +
                            " " +
                            "   %s " +
                            "" +
                            "  WHERE T1.BE_ID = ? AND %s (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1) " +
                            " " +
                            "  ORDER BY DECODE(UPPER(STAFF_PRMY_LANG),?,1) ASC " +
                            ") WHERE SAME_STAFF_ID_ORDER = 1" +
                            ") r1) " +
                            " " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d ",
                     conflictQuery, filterTyp, fromRow, toRow);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;

            for (Object param : conflictParams) {
                preparedStatement.setObject(index++, param);
            }

            preparedStatement.setString(index++, bsnEntID);

            for (Object param : params) {
                preparedStatement.setObject(index++, param);
            }

            preparedStatement.setString(index++, orderByColumn);

            resultSet = preparedStatement.executeQuery();

            List<FindStaffResult> findStaffResults = new ArrayList<>();

            Response response = new Response();
            response.setData(findStaffResults);
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(orderByColumn);

            while (resultSet.next()) {

                FindStaffResult findStaffResult = new FindStaffResult();

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());
                }

                findStaffResult.setStaffSK(BigInteger.valueOf(resultSet.getBigDecimal("STAFF_SK").longValue()));
                findStaffResult.setStaffID(resultSet.getString("STAFF_ID"));
                findStaffResult.setStaffFirstName(resultSet.getString("STAFF_FIRST_NAME"));
                findStaffResult.setStaffLastName(resultSet.getString("STAFF_LAST_NAME"));

                findStaffResult.setStaffPreferredPhone(getStaffPhone(findStaffResult.getStaffID(), bsnEntID));

                findStaffResult.setStaffAddressLine1(resultSet.getString("STAFF_ADDR1"));
                findStaffResult.setStaffAddressLine2(resultSet.getString("STAFF_ADDR2"));
                findStaffResult.setStaffCity(resultSet.getString("STAFF_CITY"));
                findStaffResult.setStaffState(resultSet.getString("STAFF_STATE"));
                findStaffResult.setStaffPostalCode(resultSet.getString("STAFF_ZIP4"));
                findStaffResult.setStaffPrimaryLanguage(resultSet.getString("STAFF_PRMY_LANG"));
                findStaffResult.setStaffServiceName(resultSet.getString("STAFF_POSITION_NAME"));
                findStaffResult.setEmploymentStatusTypeName(EmploymentStatusTypeName.fromValue(resultSet.getString("STAFF_EMPLT_STATUS_TYP_NAME")));

                findStaffResults.add(findStaffResult);
            }

            return response;

        } catch (Exception e) {

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
            this.connectionPoolDataService.close(connection);
        }
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

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setArray(2, primaryKeysArray);
            callableStatement.execute();

            resultSet = (ResultSet)callableStatement.getObject(1);

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

            throw new SandataRuntimeException(String.format("[%s][%s][%s]: %s: %s",
                    packageName, methodName, className,
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

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                }
                catch (SQLException sqle) {
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
            resultSet = (ResultSet)callableStatement.getObject(1);

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

            throw new SandataRuntimeException(String.format("[%s][%s][%s]: %s: %s",
                    packageName, methodName, className,
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

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Connection
            this.connectionPoolDataService.close(connection);
        }
    }
    
    /**
     * 
     * @param connection handled by caller for rolling back and closing connection
     * @param packageName
     * @param methodName
     * @param className
     * @param sequenceKey
     * @return
     * @throws SandataRuntimeException
     */
    public Object executeGet(Connection connection, String packageName, String methodName, String className, long sequenceKey) throws SandataRuntimeException {

        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setLong(2, sequenceKey);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(1);

            Object result = new DataMapper().map(resultSet, className);

            return result;

        } catch (Exception e) {
            throw new SandataRuntimeException(String.format("[%s][%s][%s]: %s: %s",
                    packageName, methodName, className,
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

    @Override
    public long execute(String packageName, String methodName, long sequenceKey) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
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
                }
                catch (SQLException sqle) {
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
                }
                catch (SQLException sqle) {
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
                }
                catch (SQLException sqle) {
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
                }
                catch (SQLException sqle) {
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

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
            callableStatement.setObject(2, jpubType);
            callableStatement.execute();
            long result = callableStatement.getLong(1);
            return result;
        }
        catch (Exception e) {

            // NOTE: Rollback (if necessary) should be handled by caller since they passed in the connection...
            throw new SandataRuntimeException(String.format("[%s][%s]: %s: %s",
                    packageName, methodName,
                    e.getClass().getName(), e.getMessage()), e);

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
    
    public long executeDelete(Connection connection, Object data) throws SandataRuntimeException {

        CallableStatement callableStatement = null;
        OracleMetadata metadata = null;
        try {
            metadata = DataMapper.getOracleMetadata(data);
            BigInteger sk = DataMapper.GetSK(data);
            if (sk == null) {
                throw new SandataRuntimeException("Cannot delete an Object with non sequence key");
            }

            String callMethod = String.format("{?=call %s.%s(?)}", metadata.packageName(), metadata.deleteMethod());
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
            callableStatement.setObject(2, sk.intValue());
            callableStatement.execute();
            long result = callableStatement.getLong(1);
            return result;
        }
        catch (Exception e) {

            // NOTE: Rollback (if necessary) should be handled by caller since they passed in the connection...
            throw new SandataRuntimeException(String.format("[%s][%s]: %s: %s",
                    metadata != null ? metadata.packageName() : null, metadata != null ? metadata.deleteMethod() : null,
                    e.getClass().getName(), e.getMessage()), e);

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
        }
    }

    public Patient getPatient(final String patientId, final String bsnEntId) {

        List<Patient> patients = (List<Patient>)getEntitiesForId(
                "SELECT * FROM PT WHERE PT_ID = ? AND BE_ID = ? AND " +
                        "(TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                "com.sandata.lab.data.model.dl.model.Patient",
                patientId,
                bsnEntId
        );

        if (patients.size() > 0) {
            return patients.get(0);
        }

        return null;
    }

    public Staff getStaff(final String staffId, final String bsnEntId) {

        List<Staff> staffList = (List<Staff>)getEntitiesForId(
                "SELECT * FROM STAFF WHERE STAFF_ID = ? AND BE_ID = ? AND " +
                        "(TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                "com.sandata.lab.data.model.dl.model.Staff",
                staffId,
                bsnEntId
        );

        if (staffList.size() > 0) {
            return staffList.get(0);
        }

        return null;
    }

    /**
     * Get StaffExt object and its associated StaffContactPhone list
     * 
     * @param staffId
     * @param bsnEntId
     * @return
     */
    public StaffExt getStaffExt(final String staffId, final String bsnEntId) {

        Staff staff = getStaff(staffId, bsnEntId);
        
        if (staff != null) {
            List<StaffContactPhone> staffContPhoneList = (List<StaffContactPhone>)getEntitiesForId(
                    "SELECT * FROM STAFF_CONT_PHONE WHERE STAFF_ID = ? AND BE_ID = ? AND " +
                            "(TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                    "com.sandata.lab.data.model.dl.model.StaffContactPhone",
                    staffId,
                    bsnEntId);
            
            StaffExt staffExt = new StaffExt(staff);
            staffExt.getStaffContactPhone().addAll(staffContPhoneList);
            
            return staffExt;
        }

        return null;
    }
    
    /**
     * found nowhere using this method, so just comment it out
     */
    /*public long updateScheduleEvent(ScheduleEvent scheduleEvent, SandataLogger logger) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        logger.info(String.format("updateScheduleEvent: [STAFF_ID=%s]: [SCHED_SK=%d]: [BE_ID=%s]",
                            scheduleEvent.getStaffID(),
                            scheduleEvent.getScheduleSK().longValue(),
                            scheduleEvent.getBusinessEntityID())
        );

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String sql = "UPDATE SCHED_EVNT SET STAFF_ID = ? WHERE SCHED_SK = ? AND BE_ID = ?" +
                    " AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, scheduleEvent.getStaffID());
            preparedStatement.setLong(index++, scheduleEvent.getScheduleSK().longValue());
            preparedStatement.setString(index, scheduleEvent.getBusinessEntityID());

            int result = preparedStatement.executeUpdate();
            logger.info(String.format("updateScheduleEvent: UPDATE SCHED_EVNT: [RESULT=%d]", result));

            // Close Statement, reset index
            preparedStatement.close();

            index = 1;

            sql = "UPDATE SCHED SET STAFF_ID = ? WHERE SCHED_SK = ? AND BE_ID = ?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(index++, scheduleEvent.getStaffID());
            preparedStatement.setLong(index++, scheduleEvent.getScheduleSK().longValue());
            preparedStatement.setString(index, scheduleEvent.getBusinessEntityID());

            result = preparedStatement.executeUpdate();
            logger.info(String.format("updateScheduleEvent: UPDATE SCHED: [RESULT=%d]", result));

            connection.commit();

            return scheduleEvent.getScheduleSK().longValue();

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
    }*/

    public long updateStaffIdForSchedule(SchedUpdateStaff schedUpdateStaff, SandataLogger logger) throws SandataRuntimeException {

        if (schedUpdateStaff.getScheduleEventSK() != null && schedUpdateStaff.getScheduleEventSK().longValue() > 0) {
            return updateStaffIdForScheduleEventSK(schedUpdateStaff, logger);
        }

        return updateStaffIdForScheduleSK(schedUpdateStaff, logger);
    }

    private long updateStaffIdForScheduleEventSK(SchedUpdateStaff schedUpdateStaff, SandataLogger logger) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        logger.info(String.format("updateStaffIdForScheduleEventSK: [STAFF_ID=%s]: [SCHED_EVNT_SK=%d]: [BE_ID=%s]",
                schedUpdateStaff.getStaffID(),
                schedUpdateStaff.getScheduleEventSK().longValue(),
                schedUpdateStaff.getBusinessEntityID())
        );

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String callMethod = "{?=call PKG_SCHEDULE_UTIL.UPDATE_STAFF_ID_FOR_SCHED_EVNT(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);

            int index = 2;
            callableStatement.setString(index++, schedUpdateStaff.getStaffID());
            callableStatement.setLong(index++, schedUpdateStaff.getScheduleEventSK().longValue());
            callableStatement.setString(index++, schedUpdateStaff.getBusinessEntityID());

            callableStatement.execute();
            long result = callableStatement.getLong(1);

            connection.commit();

            logger.info(String.format("updateStaffIdForScheduleEventSK: [RESULT=%d]", result));

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

    private long updateStaffIdForScheduleSK(SchedUpdateStaff schedUpdateStaff, SandataLogger logger) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        logger.info(String.format("updateStaffIdForScheduleSK: [STAFF_ID=%s]: [SCHED_SK=%d]: [BE_ID=%s]",
                schedUpdateStaff.getStaffID(),
                schedUpdateStaff.getScheduleSK().longValue(),
                schedUpdateStaff.getBusinessEntityID())
        );

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String callMethod = "{?=call PKG_SCHEDULE_UTIL.UPDATE_STAFF_ID_FOR_SCHED_SK(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);

            int index = 2;
            callableStatement.setString(index++, schedUpdateStaff.getStaffID());
            callableStatement.setLong(index++, schedUpdateStaff.getScheduleSK().longValue());
            callableStatement.setString(index++, schedUpdateStaff.getBusinessEntityID());

            callableStatement.execute();
            long result = callableStatement.getLong(1);

            connection.commit();

            logger.info(String.format("updateStaffIdForScheduleSK: [RESULT=%d]", result));

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

    public Object getSchdPtcExcl(String sql, String bsnEntId) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Object result = null;
        ResultSet resultSet = null;
        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);


            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();
            //ScheduleParticipantExclusion
            result = new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.ScheduleParticipantExclusion");

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
            this.connectionPoolDataService.close(connection);
        }
    }

    private String getStaffPhone(String staffId, String bsnEntId) throws SandataRuntimeException {

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

    public Response getSchedEvntHistory(String bsnEntId,
                                        String patientId,
                                        String staffId,
                                        Date schedFromDateTime,
                                        Date schedToDateTime,
                                        int page,
                                        int pageSize,
                                        String sortOn,
                                        String direction)
                        throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // Calculate row range.
        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String orderByColumn = "STAFF_ID"; // Default
            switch (sortOn) {
                case "pid":
                    orderByColumn = "PT_ID";
                    break;
            }

            List<String> params = new ArrayList<>();

            StringBuilder whereClause = new StringBuilder();
            if (!StringUtil.IsNullOrEmpty(patientId)) {
                whereClause.append("PT_ID = ? ");
                params.add(patientId);
            }

            if (params.size() == 1) {
                whereClause.append("AND STAFF_ID = ? ");
                params.add(staffId);
            } else {
                whereClause.append("STAFF_ID = ? ");
                params.add(staffId);
            }

            if (schedFromDateTime != null && schedToDateTime != null) {

                whereClause.append("AND SCHED_EVNT_START_DTIME BETWEEN " +
                        "              TO_DATE(?, 'YYYY-MM-DD HH24:MI') AND " +
                        "              TO_DATE(?, 'YYYY-MM-DD HH24:MI') ");

                params.add(ORACLE_DATE_TIME_FORMAT.format(schedFromDateTime));
                params.add(ORACLE_DATE_TIME_FORMAT.format(schedToDateTime));
            }

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                            " " +
                            "  SELECT * FROM ( " +
                            " " +
                            "      (SELECT * FROM SCHED_EVNT " +
                            "          WHERE BE_ID = ? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) AND %s ORDER BY %s %s) " +
                            "  ) " +
                            " " +
                            ") R1) " +
                            " " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d ",
                whereClause.toString(), orderByColumn, direction, fromRow, toRow
            );

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);

            for (String param : params) {
                preparedStatement.setString(index++, param);
            }

            Response response = new Response();
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());

                List<ScheduleEvent> resultList = (List<ScheduleEvent>) new DataMapper().mapWithOffsetNext(resultSet,
                                        "com.sandata.lab.data.model.dl.model.ScheduleEvent", 2);

                response.setData(resultList);
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
     * Get Authorization by AuthSK
     *
     * @param authSK
     * @return
     */
    public Authorization getAuthorization(long authSK) {
    	List<Authorization> authorization = (List<Authorization>)getEntitiesForId(
                "SELECT * FROM AUTH WHERE AUTH_SK = ? AND " +
                        "(TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                "com.sandata.lab.data.model.dl.model.Authorization",
                authSK
        );

        if (authorization.size() > 0) {
            return authorization.get(0);
        }

        return null;
    }

    /**
     * Get Order by OrderSK
     *
     * @param orderSK
     * @return
     */
    public Order getOrder(long orderSK) {
        List<Order> order = (List<Order>)getEntitiesForId(
                "SELECT * FROM ORD WHERE ORD_SK = ? AND " +
                        "(TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                "com.sandata.lab.data.model.dl.model.Order",
                orderSK
        );

        if (order.size() > 0) {
            return order.get(0);
        }

        return null;
    }
    
    /**
     * Get AuthorizationAdapter
     *
     * @param authorizationSK
     * @param authorizationQualifier
     * @return
     */
    public AuthorizationAdapter getAuthorizationAdapter(long authorizationSK, AuthorizationQualifier authorizationQualifier) {
        AuthorizationAdapter auth = null;
        if (authorizationQualifier == AuthorizationQualifier.ORDER) {
            Order order = getOrder(authorizationSK);
            if (order == null) {
                throw new SandataRuntimeException(String.format("Order (OrderSK=%s) not found", authorizationSK));
            }
            auth = new AuthorizationAdapter(order);

        } else if (authorizationQualifier == AuthorizationQualifier.AUTHORIZATION) {
            Authorization authorization = getAuthorization(authorizationSK);
            if (authorization == null) {
                throw new SandataRuntimeException(String.format("Authorization (AuthorizationSK=%s) not found", authorizationSK));
            }
            auth = new AuthorizationAdapter(authorization);
        }

        return auth;
    }
    
    /**
     * Get AuthorizationAdapter even the Auth is Cancelled!
     *
     * @param authorizationSK
     * @param authorizationQualifier
     * @return
     */
    public AuthorizationAdapter getAuthorizationAdapter(Connection connection, long authorizationSK, AuthorizationQualifier authorizationQualifier) {
        AuthorizationAdapter auth = null;
        if (authorizationQualifier == AuthorizationQualifier.ORDER) {
            String sql = "SELECT * FROM ORD WHERE ORD_SK = ?";
            List<Order> listOrders = (List<Order>) 
                    getEntitiesForId(connection, sql, "com.sandata.lab.data.model.dl.model.Order", authorizationSK);
            
            // this is historical table, this should be returned record
            if (listOrders.size() == 0) {
                throw new SandataRuntimeException(String.format("Order (OrderSK=%s) not found", authorizationSK));
            }
            auth = new AuthorizationAdapter(listOrders.get(0));

        } else if (authorizationQualifier == AuthorizationQualifier.AUTHORIZATION) {
            String sql = "SELECT * FROM AUTH WHERE AUTH_SK = ?";
            List<Authorization> listAuths = (List<Authorization>) 
                    getEntitiesForId(connection, sql, "com.sandata.lab.data.model.dl.model.Authorization", authorizationSK);
            
         // this is historical table, this should be returned record
            if (listAuths.size() == 0) {
                throw new SandataRuntimeException(String.format("Authorization (AuthorizationSK=%s) not found", authorizationSK));
            }
            auth = new AuthorizationAdapter(listAuths.get(0));
        }

        return auth;
    }

    /**
     * Get Schedule by ScheduleSK
     *
     * @param scheduleSK
     * @return
     */
    public Schedule getSchedule(long scheduleSK) {
        List<Schedule> schedule = (List<Schedule>)getEntitiesForId(
                "SELECT * FROM SCHED WHERE SCHED_SK = ?",
                "com.sandata.lab.data.model.dl.model.Schedule",
                scheduleSK
        );

        if (schedule.size() > 0) {
            return schedule.get(0);
        }

        return null;
    }

    /**
     * Get ScheduleEvent by ScheduleEventSK
     *
     * @param scheduleEventSK
     * @return
     */
    public ScheduleEvent getScheduleEvent(long scheduleEventSK) {
        List<ScheduleEvent> scheduleEvent = (List<ScheduleEvent>)getEntitiesForId(
                "SELECT * FROM SCHED_EVNT WHERE SCHED_EVNT_SK = ?",
                "com.sandata.lab.data.model.dl.model.ScheduleEvent",
                scheduleEventSK
        );

        if (scheduleEvent.size() > 0) {
            return scheduleEvent.get(0);
        }

        return null;
    }

    /**
     * Get Payer by BusinessEntityID and PayerID
     *
     * @param bsnEntId
     * @param payerId
     */
    public Payer getPayer(String bsnEntId, String payerId) {
        List<Payer> payer = (List<Payer>)getEntitiesForId(
                "SELECT * FROM PAYER WHERE BE_ID = ? AND PAYER_ID = ?",
                "com.sandata.lab.data.model.dl.model.Payer",
                bsnEntId, payerId
        );

        if (payer.size() > 0) {
            return payer.get(0);
        }

        return null;
    }

    public PlanOfCare getPlanOfCareWithTaskList(long planOfCareSK) {
    	List<PlanOfCare> planOfCares = (List<PlanOfCare>) getEntitiesForId(
                "SELECT * FROM POC WHERE POC_SK = ? AND " +
                        "(TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                "com.sandata.lab.data.model.dl.model.PlanOfCare",
                planOfCareSK
        );
    	
    	if (planOfCares.size() > 0) {
    		PlanOfCare poc = planOfCares.get(0);
    		List<PlanOfCareTaskList> pocTaskList = poc.getPlanOfCareTaskList();
    		pocTaskList.addAll((List<PlanOfCareTaskList>) getEntitiesForId(
                    "SELECT * FROM POC_TASK_LST WHERE POC_SK = ? ",
                    "com.sandata.lab.data.model.dl.model.PlanOfCareTaskList",
                    planOfCareSK
            ));
    		
    		return poc;
    	}
    	
    	return null;
    }

    public Response getSchedEvntHistoryDetail(
            Long scheduleEvenSK,
            int page,
            int pageSize,
            String sortOn,
            String direction) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        String orderByColumn = "CHANGED_ON";
        switch (sortOn.toLowerCase()) {
            case "first_name": orderByColumn = "USER_FIRST_NAME"; break;
            case "last_name": orderByColumn = "USER_LAST_NAME"; break;
            case "data_point": orderByColumn = "DATA_POINT"; break;
            case "changed_from": orderByColumn = "CHANGED_FROM"; break;
            case "changed_to": orderByColumn = "CHANGED_TO"; break;
        }

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT " +
                    "    T1.BE_ID, " +
                    "    T2.SCHED_EVNT_ACTIVITY_SK, " +
                    "    T3.SCHED_EVNT_AUTH_SK, " +
                    "    T4.SCHED_EVNT_SVC_SK " +
                    "FROM SCHED_EVNT T1 " +
                    " " +
                    "LEFT JOIN (SELECT SCHED_EVNT_SK,SCHED_EVNT_ACTIVITY_SK " +
                    "  FROM SCHED_EVNT_ACTIVITY) T2 " +
                    "ON T1.SCHED_EVNT_SK = T2.SCHED_EVNT_SK " +
                    " " +
                    "LEFT JOIN (SELECT SCHED_EVNT_SK,SCHED_EVNT_AUTH_SK " +
                    "  FROM SCHED_EVNT_AUTH) T3 " +
                    "ON T1.SCHED_EVNT_SK = T2.SCHED_EVNT_SK " +
                    " " +
                    "LEFT JOIN (SELECT SCHED_EVNT_SK,SCHED_EVNT_SVC_SK " +
                    "  FROM SCHED_EVNT_SVC " +
                    "    WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T4 " +
                    "ON T1.SCHED_EVNT_SK = T2.SCHED_EVNT_SK " +
                    " " +
                    "WHERE T1.SCHED_EVNT_SK = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setLong(index++, scheduleEvenSK);

            resultSet = preparedStatement.executeQuery();

            String bsnEntId = null;
            SchedEventRelatedSK schedEventRelatedSK = null;
            while (resultSet.next()) {

                if (bsnEntId == null) {
                    bsnEntId = resultSet.getString("BE_ID");
                    schedEventRelatedSK = new SchedEventRelatedSK(bsnEntId, scheduleEvenSK);
                }

                schedEventRelatedSK.addScheduleEventAuth(resultSet.getBigDecimal("SCHED_EVNT_AUTH_SK"));
                schedEventRelatedSK.addScheduleEventActivity(resultSet.getBigDecimal("SCHED_EVNT_ACTIVITY_SK"));
                schedEventRelatedSK.addScheduleEventService(resultSet.getBigDecimal("SCHED_EVNT_SVC_SK"));
            }

            // Clean Up
            resultSet.close();
            preparedStatement.close();
            this.connectionPoolDataService.close(connection);

            Response response = new Response();

            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(true);

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("METADATA.STRING_ARRAY", connection);
            ARRAY hostKeysArray = new ARRAY(des, connection, schedEventRelatedSK.getParams());

            String callMethod = "{?=call METADATA.PKG_AUDIT.GET_CHANGES_PAGING(?,?,?,?,?)}";

            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setArray(2, hostKeysArray);
            callableStatement.setObject(3, orderByColumn);
            callableStatement.setObject(4, direction);
            callableStatement.setObject(5, fromRow);
            callableStatement.setObject(6, toRow);
            callableStatement.execute();

            resultSet = (ResultSet) callableStatement.getObject(1);

            List<AuditChanged> auditChangedList = new ArrayList<>();
            response.setData(auditChangedList);

            while (resultSet.next()) {

                AuditChanged auditChanged = new AuditChanged();

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());
                }

                String userFirstName = resultSet.getString("USER_FIRST_NAME");
                if (StringUtil.IsNullOrEmpty(userFirstName)) {
                    auditChanged.setUserFirstName("George"); // Default if NULL
                }
                else {
                    auditChanged.setUserFirstName(userFirstName);
                }

                String userLastName = resultSet.getString("USER_LAST_NAME");
                if (StringUtil.IsNullOrEmpty(userLastName)) {
                    auditChanged.setUserLastName("Sandata"); // Default if NULL
                }
                else {
                    auditChanged.setUserLastName(userLastName);
                }

                auditChanged.setDataPoint(resultSet.getString("DATA_POINT"));
                auditChanged.setChangedFrom(resultSet.getString("CHANGED_FROM"));
                auditChanged.setChangedTo(resultSet.getString("CHANGED_TO"));

                Timestamp changedOn = resultSet.getTimestamp("CHANGED_ON");
                if (changedOn != null) {
                    auditChanged.setChangedOn(new java.util.Date(changedOn.getTime()));
                }

                auditChangedList.add(auditChanged);
            }

            response.setOrderByColumn(orderByColumn);

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
     * Get Contract for an Authorization or Order
     *
     * @param authorizationSK
     * @return
     * @throws SandataRuntimeException
     */
    public Contract getContractForAuthService(String bsnEntId, Long authorizationSK, boolean isOrder) throws SandataRuntimeException {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);
            String sql;
            if (isOrder) {
                sql =
                    "SELECT DISTINCT T5.*  " +
                    "FROM ORD T1 " +
                    "INNER JOIN ORD_SVC T2 " +
                    "ON T1.BE_ID = T2.BE_ID AND T1.ORD_SK = T2.ORD_SK " +
                    "INNER JOIN SVC T3 " +
                    "ON T1.BE_ID = T3.BE_ID AND T2.SVC_NAME = T3.SVC_NAME " +
                    "INNER JOIN CONTR_SVC_LST T4 " +
                    "ON T1.BE_ID = T4.BE_ID AND T1.PAYER_ID = T4.PAYER_ID AND T3.SVC_NAME = T4.SVC_NAME " +
                    "INNER JOIN CONTR T5 " +
                    "ON T1.BE_ID = T5.BE_ID AND T1.PAYER_ID = T5.PAYER_ID AND T4.CONTR_ID = T5.CONTR_ID " +
                    "WHERE  " +
                    "  T1.BE_ID = ? " +
                    "  AND T1.ORD_SK = ? " +
                    "  AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1) " +
                    "  AND (TO_CHAR(T3.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T3.CURR_REC_IND = 1) " +
                    "  AND (TO_CHAR(T4.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T4.CURR_REC_IND = 1) " +
                    "  AND (TO_CHAR(T5.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T5.CURR_REC_IND = 1)";
            } else {
                sql =
                    "SELECT DISTINCT T5.*  " +
                    "FROM AUTH T1 " +
                    "INNER JOIN AUTH_SVC T2 " +
                    "ON T1.BE_ID = T2.BE_ID AND T1.AUTH_SK = T2.AUTH_SK " +
                    "INNER JOIN SVC T3 " +
                    "ON T1.BE_ID = T3.BE_ID AND T2.SVC_NAME = T3.SVC_NAME " +
                    "INNER JOIN CONTR_SVC_LST T4 " +
                    "ON T1.BE_ID = T4.BE_ID AND T1.PAYER_ID = T4.PAYER_ID AND T3.SVC_NAME = T4.SVC_NAME " +
                    "INNER JOIN CONTR T5 " +
                    "ON T1.BE_ID = T5.BE_ID AND T1.PAYER_ID = T5.PAYER_ID AND T4.CONTR_ID = T5.CONTR_ID " +
                    "WHERE  " +
                    "  T1.BE_ID = ? " +
                    "  AND T1.AUTH_SK = ? " +
                    "  AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1) " +
                    "  AND (TO_CHAR(T3.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T3.CURR_REC_IND = 1) " +
                    "  AND (TO_CHAR(T4.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T4.CURR_REC_IND = 1) " +
                    "  AND (TO_CHAR(T5.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T5.CURR_REC_IND = 1)";
            }

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setLong(index++, authorizationSK);

            resultSet = preparedStatement.executeQuery();

            List<Contract> resultList = (List<Contract>)new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.Contract");
            if (resultList == null || resultList.size() == 0) {
                return null;
            }

            return resultList.get(0);

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
    
    /**
     * Get Contract for an Authorization or Order
     *
     * @param authorizationSK
     * @return
     * @throws SandataRuntimeException
     */
    public Contract getContractForAuthService(Connection connection, String bsnEntId, Long authorizationSK, boolean isOrder) throws SandataRuntimeException {
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            String sql;
            if (isOrder) {
                sql =
                    "SELECT DISTINCT T5.*  " +
                    "FROM ORD T1 " +
                    "INNER JOIN ORD_SVC T2 " +
                    "ON T1.BE_ID = T2.BE_ID AND T1.ORD_SK = T2.ORD_SK " +
                    "INNER JOIN SVC T3 " +
                    "ON T1.BE_ID = T3.BE_ID AND T2.SVC_NAME = T3.SVC_NAME " +
                    "INNER JOIN CONTR_SVC_LST T4 " +
                    "ON T1.BE_ID = T4.BE_ID AND T1.PAYER_ID = T4.PAYER_ID AND T3.SVC_NAME = T4.SVC_NAME " +
                    "INNER JOIN CONTR T5 " +
                    "ON T1.BE_ID = T5.BE_ID AND T1.PAYER_ID = T5.PAYER_ID AND T4.CONTR_ID = T5.CONTR_ID " +
                    "WHERE  " +
                    "  T1.BE_ID = ? " +
                    "  AND T1.ORD_SK = ? " +
                    "  AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1) " +
                    "  AND (TO_CHAR(T3.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T3.CURR_REC_IND = 1) " +
                    "  AND (TO_CHAR(T4.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T4.CURR_REC_IND = 1) " +
                    "  AND (TO_CHAR(T5.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T5.CURR_REC_IND = 1)";
            } else {
                sql =
                    "SELECT DISTINCT T5.*  " +
                    "FROM AUTH T1 " +
                    "INNER JOIN AUTH_SVC T2 " +
                    "ON T1.BE_ID = T2.BE_ID AND T1.AUTH_SK = T2.AUTH_SK " +
                    "INNER JOIN SVC T3 " +
                    "ON T1.BE_ID = T3.BE_ID AND T2.SVC_NAME = T3.SVC_NAME " +
                    "INNER JOIN CONTR_SVC_LST T4 " +
                    "ON T1.BE_ID = T4.BE_ID AND T1.PAYER_ID = T4.PAYER_ID AND T3.SVC_NAME = T4.SVC_NAME " +
                    "INNER JOIN CONTR T5 " +
                    "ON T1.BE_ID = T5.BE_ID AND T1.PAYER_ID = T5.PAYER_ID AND T4.CONTR_ID = T5.CONTR_ID " +
                    "WHERE  " +
                    "  T1.BE_ID = ? " +
                    "  AND T1.AUTH_SK = ? " +
                    "  AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1) " +
                    "  AND (TO_CHAR(T3.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T3.CURR_REC_IND = 1) " +
                    "  AND (TO_CHAR(T4.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T4.CURR_REC_IND = 1) " +
                    "  AND (TO_CHAR(T5.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T5.CURR_REC_IND = 1)";
            }

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setLong(index++, authorizationSK);

            resultSet = preparedStatement.executeQuery();

            List<Contract> resultList = (List<Contract>)new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.Contract");
            if (resultList == null || resultList.size() == 0) {
                return null;
            }

            return resultList.get(0);

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
     * Find a List that contains ScheduleEvent count and duration in each day that exceeds limit
     *
     * @param auth
     * @param contract
     * @param selectedDaysOfWeek
     * @param schedEventDateList
     * @param isVisitValidation
     * @return
     */
    public List<ScheduleEventCountAndDuration> validateDailyScheduleEvent(final Authorization auth,
                                                                          final Object excludedObject,
                                                                          final Contract contract,
                                                                          final SelectedDaysOfWeek selectedDaysOfWeek,
                                                                          final List<Date> schedEventDateList,
                                                                          final boolean isVisitValidation) {
        List<ScheduleEventCountAndDuration> result = new ArrayList<>();
        if (schedEventDateList == null || schedEventDateList.size() == 0) {
            return result;
        }

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            BigDecimal authLimit = auth.getAuthorizationLimit();
            BigDecimal authLimitDay1 = auth.getAuthorizationLimitDay1() == null ? authLimit : auth.getAuthorizationLimitDay1();
            BigDecimal authLimitDay2 = auth.getAuthorizationLimitDay2() == null ? authLimit : auth.getAuthorizationLimitDay2();
            BigDecimal authLimitDay3 = auth.getAuthorizationLimitDay3() == null ? authLimit : auth.getAuthorizationLimitDay3();
            BigDecimal authLimitDay4 = auth.getAuthorizationLimitDay4() == null ? authLimit : auth.getAuthorizationLimitDay4();
            BigDecimal authLimitDay5 = auth.getAuthorizationLimitDay5() == null ? authLimit : auth.getAuthorizationLimitDay5();
            BigDecimal authLimitDay6 = auth.getAuthorizationLimitDay6() == null ? authLimit : auth.getAuthorizationLimitDay6();
            BigDecimal authLimitDay7 = auth.getAuthorizationLimitDay7() == null ? authLimit : auth.getAuthorizationLimitDay7();

            if (!isVisitValidation) { // HOUR / UNIT / LIVE-IN
                BigDecimal millisecondsPerServiceUnit = new BigDecimal("3600000"); // 1 hour
                AuthorizationServiceUnitName svcUnitName = auth.getAuthorizationServiceUnitName();
                switch (svcUnitName) {
                    case HOUR:
                        // use default 1 hour
                        break;
                    case UNIT:
                        // ContractServiceUnitEquivalence is in minute
                        millisecondsPerServiceUnit = new BigDecimal(getContractServiceUnitEquivalence(contract)).multiply(new BigDecimal("60000"));
                        break;
                    case LIVE_IN:
                        millisecondsPerServiceUnit = millisecondsPerServiceUnit.multiply(new BigDecimal("24"));
                        break;
                }

                // Convert auth limit to milli seconds
                authLimitDay1 = authLimitDay1.multiply(millisecondsPerServiceUnit);
                authLimitDay2 = authLimitDay2.multiply(millisecondsPerServiceUnit);
                authLimitDay3 = authLimitDay3.multiply(millisecondsPerServiceUnit);
                authLimitDay4 = authLimitDay4.multiply(millisecondsPerServiceUnit);
                authLimitDay5 = authLimitDay5.multiply(millisecondsPerServiceUnit);
                authLimitDay6 = authLimitDay6.multiply(millisecondsPerServiceUnit);
                authLimitDay7 = authLimitDay7.multiply(millisecondsPerServiceUnit);
            }

            // Build excluded condition query
            String excludedSchedEventCondition = "";
            if (excludedObject instanceof ScheduleExt &&
                    (((ScheduleExt)excludedObject).getScheduleSK() != null && ((ScheduleExt)excludedObject).getScheduleSK().longValue() > 0)) {

                excludedSchedEventCondition = " AND SCHED_EVNT_SK NOT IN (" +
                        "SELECT SCHED_EVNT_SK " +
                        "FROM SCHED_EVNT " +
                        "WHERE SCHED_SK = ? " +
                        "   AND BE_ID = ? " +
                        "   AND PT_ID = ? " +
                        "   AND AUTH_SK = ? " +
                        "   AND SCHED_EVNT_STATUS != 'CANCELLED' " +
                        "   AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                        ") ";

            } else if (excludedObject instanceof ScheduleEvent) {
                excludedSchedEventCondition = " AND SCHED_EVNT_SK != ? ";
            }

            StringBuilder sqlBuilder = new StringBuilder("");
            String havingCondition = "";
            for (int i = 0; i < schedEventDateList.size(); i++) {
                Date date = schedEventDateList.get(i);
                if (date == null) {
                    continue;
                }

                if (isVisitValidation) {
                    havingCondition = "COUNT(*) + 1 ";
                } else {
                    // Get duration for the day
                    long totalDurationinRange = ScheduleUtils.getDurationForDate(date, selectedDaysOfWeek);
                    havingCondition = "NVL(SUM((SCHED_EVNT_END_DTIME - SCHED_EVNT_START_DTIME)*24*60*60*1000), 0) + " + totalDurationinRange;
                }

                sqlBuilder.append(String.format(
                        "SELECT  " +
                        "  TO_DATE('%s 00:00:00', 'YYYY-MM-DD HH24:MI:SS') AS SCHED_EVNT_START_DATE, " +
                        "  TO_DATE('%s 23:59:59', 'YYYY-MM-DD HH24:MI:SS') AS SCHED_EVNT_END_DATE,  " +
                        "  COUNT(*) AS SCHED_EVNT_COUNT,  " +
                        "  NVL(SUM((SCHED_EVNT_END_DTIME - SCHED_EVNT_START_DTIME)*24*60*60*1000), 0) AS SCHED_EVNT_DURATION " +
                        "FROM SCHED_EVNT " +
                        "WHERE " +
                        "  BE_ID = ? " +
                        "  AND PT_ID = ? " +
                        "  AND AUTH_SK = ? " +
                        "  AND (SCHED_EVNT_START_DTIME BETWEEN TO_DATE('%s 00:00:00', 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE('%s 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) " +
                        "  AND SCHED_EVNT_STATUS != 'CANCELLED' " +
                        "  AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                        excludedSchedEventCondition +
                        "HAVING " + havingCondition + " > %d ",
                        ORACLE_DATE_FORMAT.format(date),
                        ORACLE_DATE_FORMAT.format(date),
                        ORACLE_DATE_FORMAT.format(date),
                        ORACLE_DATE_FORMAT.format(date),
                        ScheduleUtils.getAuthLimitForDate(date, authLimitDay1, authLimitDay2, authLimitDay3, authLimitDay4, authLimitDay5, authLimitDay6, authLimitDay7).longValue()));

                if (i < schedEventDateList.size() - 1) {
                    sqlBuilder.append(" UNION ");
                }
            }

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlBuilder.toString());

            int index = 1;
            for (int i = 0; i < schedEventDateList.size(); i++) {
                preparedStatement.setString(index++, auth.getBusinessEntityID());
                preparedStatement.setString(index++, auth.getPatientID());
                preparedStatement.setLong(index++, auth.getAuthorizationSK().longValue());

                if (excludedObject instanceof ScheduleExt
                        && (((ScheduleExt)excludedObject).getScheduleSK() != null && ((ScheduleExt)excludedObject).getScheduleSK().longValue() > 0)) {

                    preparedStatement.setLong(index++, ((ScheduleExt)excludedObject).getScheduleSK().longValue());
                    preparedStatement.setString(index++, ((ScheduleExt)excludedObject).getBusinessEntityID());
                    preparedStatement.setString(index++, ((ScheduleExt)excludedObject).getPatientID());
                    preparedStatement.setLong(index++, auth.getAuthorizationSK().longValue());

                } else if (excludedObject instanceof ScheduleEvent) {
                    preparedStatement.setLong(index++, ((ScheduleEvent)excludedObject).getScheduleEventSK().longValue());
                }
            }

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ScheduleEventCountAndDuration schedEvntCountDuration = new ScheduleEventCountAndDuration();
                schedEvntCountDuration.setScheduleEventStartDate(resultSet.getDate("SCHED_EVNT_START_DATE"));
                schedEvntCountDuration.setScheduleEventEndDate(resultSet.getDate("SCHED_EVNT_END_DATE"));
                schedEvntCountDuration.setScheduleEventCount(resultSet.getInt("SCHED_EVNT_COUNT"));
                schedEvntCountDuration.setTotalDuration(resultSet.getLong("SCHED_EVNT_DURATION"));

                result.add(schedEvntCountDuration);
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
     *
     * @param auth
     * @param contract
     * @param scheduleExt
     * @param schedEventDateRanges
     * @param isVisitValidation
     * @param isLimitTotalValidation
     * @return
     */
    public List<ScheduleEventCountAndDuration> validateScheduleEventInRanges(final Authorization auth,
                                                                             final Contract contract,
                                                                             final ScheduleExt scheduleExt,
                                                                             final List<Date[]> schedEventDateRanges,
                                                                             final boolean isVisitValidation,
                                                                             final boolean isLimitTotalValidation) {
        List<ScheduleEventCountAndDuration> result = new ArrayList<>();
        if (schedEventDateRanges == null || schedEventDateRanges.size() == 0) {
            return result;
        }

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            BigDecimal authLimit;
            if (isLimitTotalValidation) {
                authLimit = auth.getAuthorizationLimitTotal();
            } else {
                authLimit = auth.getAuthorizationLimit();
            }

            // No validation needed if no auth limit specified
            if (authLimit == null) {
                return result;
            }

            if (!isVisitValidation) { // HOUR / UNIT / LIVE-IN
                BigDecimal millisecondsPerServiceUnit = new BigDecimal("3600000"); // 1 hour
                AuthorizationServiceUnitName svcUnitName = auth.getAuthorizationServiceUnitName();
                switch (svcUnitName) {
                    case HOUR:
                        // use default 1 hour
                        break;
                    case UNIT:
                        // ContractServiceUnitEquivalence is in minute
                        millisecondsPerServiceUnit = new BigDecimal(getContractServiceUnitEquivalence(contract)).multiply(new BigDecimal("60000"));
                        break;
                    case LIVE_IN:
                        millisecondsPerServiceUnit = millisecondsPerServiceUnit.multiply(new BigDecimal("24"));
                        break;
                }

                // Convert auth limit to milli seconds
                authLimit = authLimit.multiply(millisecondsPerServiceUnit);
            }

            // Build excluded condition query
            String excludedSchedEventCondition = "";
            if (scheduleExt.getScheduleSK() != null) {
                excludedSchedEventCondition = " AND SCHED_EVNT_SK NOT IN (" +
                        "SELECT SCHED_EVNT_SK " +
                        "FROM SCHED_EVNT " +
                        "WHERE SCHED_SK = ? " +
                        "   AND BE_ID = ? " +
                        "   AND PT_ID = ? " +
                        "   AND AUTH_SK = ? " +
                        "   AND SCHED_EVNT_STATUS != 'CANCELLED' " +
                        "   AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                        ") ";
            }

            String havingCondition = "";
            String sql = "";
            StringBuilder sqlBuilder = new StringBuilder();
            // dateArray[0] is startDate, dateArray[1] is endDate
            for (int i = 0; i < schedEventDateRanges.size(); i++) {
                Date[] date = schedEventDateRanges.get(i);
                if (date == null || date.length != 2) {
                    continue;
                }

                List<Integer> selectedDayIndexInWeek = ScheduleUtils.getScheduleSelectedDayOfWeek(scheduleExt.getSelectedDaysOfWeek());
                List<Date> selectedDateInRage = ScheduleUtils.getScheduleDatesInRange(scheduleExt, date[0], date[1], selectedDayIndexInWeek);
                if (isVisitValidation) {
                    // Get all schedule events for selected days between date range date[0] and date[1]
                    havingCondition = "COUNT(*) + " + selectedDateInRage.size();
                } else {
                    // Sum all duration for selected days between date range date[0] and date[1]
                    long totalDurationinRange = ScheduleUtils.getTotalDurationForDateList(selectedDateInRage, scheduleExt.getSelectedDaysOfWeek());
                    havingCondition = "NVL(SUM((SCHED_EVNT_END_DTIME - SCHED_EVNT_START_DTIME)*24*60*60*1000), 0) + " + totalDurationinRange;
                }

                sql =
                    "SELECT  " +
                    "  TO_DATE('%s 00:00:00', 'YYYY-MM-DD HH24:MI:SS') AS SCHED_EVNT_START_DATE, " +
                    "  TO_DATE('%s 23:59:59', 'YYYY-MM-DD HH24:MI:SS') AS SCHED_EVNT_END_DATE,  " +
                    "  COUNT(*) AS SCHED_EVNT_COUNT,  " +
                    "  NVL(SUM((SCHED_EVNT_END_DTIME - SCHED_EVNT_START_DTIME)*24*60*60*1000), 0) AS SCHED_EVNT_DURATION " +
                    "FROM SCHED_EVNT " +
                    "WHERE " +
                    "  BE_ID = ? " +
                    "  AND PT_ID = ? " +
                    "  AND AUTH_SK = ? " +
                    "  AND (SCHED_EVNT_START_DTIME BETWEEN TO_DATE('%s 00:00:00', 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE('%s 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) " +
                    "  AND SCHED_EVNT_STATUS != 'CANCELLED' " +
                    "  AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                    excludedSchedEventCondition +
                    "HAVING " + havingCondition + " > ? ";

                // SAN-31: Auth limit total validates against entire Auth duration
                if (isLimitTotalValidation) {
                    sqlBuilder.append(String.format(sql,
                            ORACLE_DATE_FORMAT.format(auth.getAuthorizationStartTimestamp()),
                            ORACLE_DATE_FORMAT.format(auth.getAuthorizationEndTimestamp()),
                            ORACLE_DATE_FORMAT.format(auth.getAuthorizationStartTimestamp()),
                            ORACLE_DATE_FORMAT.format(auth.getAuthorizationEndTimestamp())));

                } else {
                    sqlBuilder.append(String.format(sql,
                            ORACLE_DATE_FORMAT.format(date[0]),
                            ORACLE_DATE_FORMAT.format(date[1]),
                            ORACLE_DATE_FORMAT.format(date[0]),
                            ORACLE_DATE_FORMAT.format(date[1])));
                }

                if (i < schedEventDateRanges.size() - 1) {
                    sqlBuilder.append(" UNION ");
                }
            }

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlBuilder.toString());

            int index = 1;
            for (int i = 0; i < schedEventDateRanges.size(); i++) {
                preparedStatement.setString(index++, auth.getBusinessEntityID());
                preparedStatement.setString(index++, auth.getPatientID());
                preparedStatement.setLong(index++, auth.getAuthorizationSK().longValue());

                if (scheduleExt.getScheduleSK() != null) {
                    preparedStatement.setLong(index++, scheduleExt.getScheduleSK().longValue());
                    preparedStatement.setString(index++, scheduleExt.getBusinessEntityID());
                    preparedStatement.setString(index++, scheduleExt.getPatientID());
                    preparedStatement.setLong(index++, auth.getAuthorizationSK().longValue());
                }

                preparedStatement.setLong(index++, authLimit.longValue());
            }

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ScheduleEventCountAndDuration schedEvntCountDuration = new ScheduleEventCountAndDuration();
                schedEvntCountDuration.setScheduleEventStartDate(resultSet.getDate("SCHED_EVNT_START_DATE"));
                schedEvntCountDuration.setScheduleEventEndDate(resultSet.getDate("SCHED_EVNT_END_DATE"));
                schedEvntCountDuration.setScheduleEventCount(resultSet.getInt("SCHED_EVNT_COUNT"));
                schedEvntCountDuration.setTotalDuration(resultSet.getLong("SCHED_EVNT_DURATION"));

                result.add(schedEvntCountDuration);
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
            if (callableStatement != null) {
                try {
                    callableStatement.close();
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


    /**
     * Validate a ScheduleEvent for update in a date range
     *
     * @param auth
     * @param contract
     * @param scheduleEvent
     * @param validationDateRange
     * @param isVisitValidation
     * @param isLimitTotalValidation
     * @return
     */
    public List<ScheduleEventCountAndDuration> validateScheduleEventForUpdate(final Authorization auth,
                                                                              final Contract contract,
                                                                              final ScheduleEvntExt scheduleEvntExt,
                                                                              final Date[] validationDateRange,
                                                                              final boolean isVisitValidation,
                                                                              final boolean isLimitTotalValidation) {
        List<ScheduleEventCountAndDuration> result = new ArrayList<>();
        if (validationDateRange == null || validationDateRange.length != 2) {
            return result;
        }

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            BigDecimal authLimit;
            if (isLimitTotalValidation) {
                authLimit = auth.getAuthorizationLimitTotal();
            } else {
                authLimit = auth.getAuthorizationLimit();
            }

            // No validation needed if no auth limit specified
            if (authLimit == null) {
                return result;
            }

            if (!isVisitValidation) { // HOUR / UNIT / LIVE-IN
                BigDecimal millisecondsPerServiceUnit = new BigDecimal("3600000"); // 1 hour
                AuthorizationServiceUnitName svcUnitName = auth.getAuthorizationServiceUnitName();
                switch (svcUnitName) {
                    case HOUR:
                        // use default 1 hour
                        break;
                    case UNIT:
                        // ContractServiceUnitEquivalence is in minute
                        millisecondsPerServiceUnit = new BigDecimal(getContractServiceUnitEquivalence(contract)).multiply(new BigDecimal("60000"));
                        break;
                    case LIVE_IN:
                        millisecondsPerServiceUnit = millisecondsPerServiceUnit.multiply(new BigDecimal("24"));
                        break;
                }

                // Convert auth limit to milli seconds
                authLimit = authLimit.multiply(millisecondsPerServiceUnit);
            }

            String havingCondition = "";
            if (isVisitValidation) {
                havingCondition = "COUNT(*) + 1";
            } else {
                long scheduleEventDuration = ScheduleUtils.getDurationForScheduleEvntExt(scheduleEvntExt);
                havingCondition = "NVL(SUM((SCHED_EVNT_END_DTIME - SCHED_EVNT_START_DTIME)*24*60*60*1000), 0) + " + scheduleEventDuration;
            }

            String sql =
                    "SELECT  " +
                    "  TO_DATE('%s 00:00:00', 'YYYY-MM-DD HH24:MI:SS') AS SCHED_EVNT_START_DATE, " +
                    "  TO_DATE('%s 23:59:59', 'YYYY-MM-DD HH24:MI:SS') AS SCHED_EVNT_END_DATE,  " +
                    "  COUNT(*) AS SCHED_EVNT_COUNT,  " +
                    "  NVL(SUM((SCHED_EVNT_END_DTIME - SCHED_EVNT_START_DTIME)*24*60*60*1000), 0) AS SCHED_EVNT_DURATION " +
                    "FROM SCHED_EVNT " +
                    "WHERE " +
                    "  BE_ID = ? " +
                    "  AND PT_ID = ? " +
                    "  AND AUTH_SK = ? " +
                    "  AND (SCHED_EVNT_START_DTIME BETWEEN TO_DATE('%s 00:00:00', 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE('%s 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) " +
                    "  AND SCHED_EVNT_STATUS != 'CANCELLED' " +
                    "  AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                    "  AND SCHED_EVNT_SK != ? " +    // exclude old ScheduleEvent
                    "HAVING " + havingCondition + " > ? ";

            // SAN-31: Auth limit total validates against entire Auth duration
            if (isLimitTotalValidation) {
                sql = String.format(sql,
                        ORACLE_DATE_FORMAT.format(auth.getAuthorizationStartTimestamp()),
                        ORACLE_DATE_FORMAT.format(auth.getAuthorizationEndTimestamp()),
                        ORACLE_DATE_FORMAT.format(auth.getAuthorizationStartTimestamp()),
                        ORACLE_DATE_FORMAT.format(auth.getAuthorizationEndTimestamp()));
            } else {
                sql = String.format(sql,
                        ORACLE_DATE_FORMAT.format(validationDateRange[0]),
                        ORACLE_DATE_FORMAT.format(validationDateRange[1]),
                        ORACLE_DATE_FORMAT.format(validationDateRange[0]),
                        ORACLE_DATE_FORMAT.format(validationDateRange[1]));
            }

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, auth.getBusinessEntityID());
            preparedStatement.setString(index++, auth.getPatientID());
            preparedStatement.setLong(index++, auth.getAuthorizationSK().longValue());
            preparedStatement.setLong(index++, scheduleEvntExt.getScheduleEventSK().longValue());
            preparedStatement.setLong(index++, authLimit.longValue());


            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ScheduleEventCountAndDuration schedEvntCountDuration = new ScheduleEventCountAndDuration();
                schedEvntCountDuration.setScheduleEventStartDate(resultSet.getDate("SCHED_EVNT_START_DATE"));
                schedEvntCountDuration.setScheduleEventEndDate(resultSet.getDate("SCHED_EVNT_END_DATE"));
                schedEvntCountDuration.setScheduleEventCount(resultSet.getInt("SCHED_EVNT_COUNT"));
                schedEvntCountDuration.setTotalDuration(resultSet.getLong("SCHED_EVNT_DURATION"));

                result.add(schedEvntCountDuration);
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
     * Get Service Unit Equivalence from Contract or Payer
     *
     * @param contract
     * @return
     * @throws SandataRuntimeException
     */
    private BigInteger getContractServiceUnitEquivalence(Contract contract) throws SandataRuntimeException {
        if (contract.getContractServiceUnitEquivalence() == null) {
            Payer payer = getPayer(contract.getBusinessEntityID(), contract.getPayerID());
            if (payer == null) {
                throw new SandataRuntimeException("Payer not found");
            }

            if (payer.getPayerServiceUnitEquivalence() == null) {
                throw new SandataRuntimeException("No Serivce Unit Equivalence defined for Contract and Payer");
            }

            return payer.getPayerServiceUnitEquivalence();
        }

        return contract.getContractServiceUnitEquivalence();
    }

    /**
     * Get Service Unit Equivalence from Contract or Payer
     *
     * @param contract
     * @return
     * @throws SandataRuntimeException
     */
    private BigInteger getContractServiceUnitEquivalence(Connection connection, Contract contract) throws SandataRuntimeException {
        if (contract.getContractServiceUnitEquivalence() == null) {
            List<Payer> listPayer = (List<Payer>) getEntitiesForId(connection,
                    "SELECT * FROM PAYER WHERE BE_ID = ? AND PAYER_ID = ?", Payer.class.getName(),
                    contract.getBusinessEntityID(), contract.getPayerID());
            
            if (listPayer.size() == 0) {
                throw new SandataRuntimeException("Payer not found");
            }

            if (listPayer.get(0).getPayerServiceUnitEquivalence() == null) {
                throw new SandataRuntimeException("No Serivce Unit Equivalence defined for Contract and Payer");
            }

            return listPayer.get(0).getPayerServiceUnitEquivalence();
        }

        return contract.getContractServiceUnitEquivalence();
    }
}
