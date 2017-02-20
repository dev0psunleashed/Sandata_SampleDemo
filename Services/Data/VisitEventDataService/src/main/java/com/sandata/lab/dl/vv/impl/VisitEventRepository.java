package com.sandata.lab.dl.vv.impl;


import com.google.gson.Gson;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.constants.filter.Filter;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.extended.ScheduleEventExt;
import com.sandata.lab.data.model.dl.model.extended.VisitTaskListExt;
import com.sandata.lab.data.model.request.visit.PatientStaffRequest;
import com.sandata.lab.data.model.request.visit.ScheduleEventRequest;
import com.sandata.lab.data.model.response.visit.PatientStaffResponse;
import com.sandata.lab.data.model.response.visit.ScheduleEventResponse;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.dl.vv.api.VisitEventDataService;
import com.sandata.lab.dl.vv.app.AppContext;
import com.sandata.lab.dl.vv.utils.data.DataMapper;
import com.sandata.lab.dl.vv.utils.log.VisitEventRepositoryLogger;
import com.sandata.lab.logger.api.LoggerService;
import com.sandata.lab.rules.call.model.CallPreferences;
import com.sandata.lab.rules.call.model.State;
import com.sandata.lab.rules.call.model.VisitAndSchedule;
import com.sandata.lab.rules.call.model.VisitFact;

import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.cxf.message.MessageContentsList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import static java.lang.String.format;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */
@SuppressWarnings({"unchecked", "duplicates"})

public class VisitEventRepository implements VisitEventDataService {

    private LoggerService loggerService;
    private Logger visitEventLogger = LoggerFactory.getLogger("visitEventLogger");
    private Logger visitEventSqlExceptionLogger = LoggerFactory.getLogger("visitEventSqlExceptionLogger");
    private Logger visitEventExceptionLogger = LoggerFactory.getLogger("visitEventExceptionLogger");

    public Logger getVisitEventLogger(){
        if(visitEventLogger != null)
            return visitEventLogger;
        else {
            visitEventLogger = LoggerFactory.getLogger(VISIT_EVENT_LOGGER);
            return visitEventLogger;
        }
    }
    public Logger getVisitEventSqlExceptionLogger() {
        if(visitEventSqlExceptionLogger != null) {
            return visitEventSqlExceptionLogger;
        }
        else {
            visitEventSqlExceptionLogger = LoggerFactory.getLogger(VISIT_EVENT_SQL_ECEPTION_LOGGER);
            return visitEventSqlExceptionLogger;
        }
    }
    public Logger getVisitEventExceptionLogger() {
        if(visitEventExceptionLogger != null) {
            return visitEventExceptionLogger;
        }
        else {
            visitEventExceptionLogger = LoggerFactory.getLogger(VISIT_EVENT_ECEPTION_LOGGER);
            return visitEventExceptionLogger;
        }
    }
    public void setVisitEventLogger(Logger logger) { visitEventLogger = logger;}
    public void setVisitEventSqlExceptionLogger(Logger logger) {
        visitEventSqlExceptionLogger = logger;
    }
    private ConnectionPoolDataService connectionPoolDataService;
    private static final String VISIT_EVENT_LOGGER = "visitEventLogger";
    private static final String VISIT_EVENT_SQL_ECEPTION_LOGGER = "visitEventSqlExceptionLogger";
    private static final String VISIT_EVENT_ECEPTION_LOGGER = "visitEventExceptionLogger";
    public Connection getOracleConnection() throws SandataRuntimeException {
        return connectionPoolDataService.getConnection();
    }
    public ConnectionPoolDataService getConnectionPoolDataService() {
        return connectionPoolDataService;
    }

    public void setConnectionPoolDataService(ConnectionPoolDataService connectionPoolDataService) {
        this.connectionPoolDataService = connectionPoolDataService;
    }

    public LoggerService getLoggerService() {
        return loggerService;
    }

    public void setLoggerService(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    private String getBusinessEntityIDForDNIS(final String dnis) {

        return createAgencyIdRequest(dnis);
    }
    @SuppressWarnings("Duplicates")
    private List<ScheduleEventExt> getScheduleEventsExt(final ScheduleEventRequest scheduleEventRequest, final String staffIdField,final String bsnEntityId ) throws SandataRuntimeException {

        UUID uuid = scheduleEventRequest.getRequestId();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //The following is the fieldname used to perform visit verification!
        getVisitEventLogger().info("Started logging to new VISIT_EVENT_LOGGER appender!  In getScheduleEventsExt");
        getVisitEventSqlExceptionLogger().info("Started logging to new VISIT_EVENT_SQL_EXCEPTION_LOGGER appender!  In getScheduleEventsExt");
        getVisitEventExceptionLogger().info("Started logging to new VISIT_EVENT_EXCEPTION_LOGGER appender!  In getScheduleEventsExt");
        String santraxId = scheduleEventRequest.getVv_id();
        String debugSQL = "";
        String debugBeId = "";
        String debugStaffId1 = "";
        String debugStaffId2 = "";
        String debugPtId = "";
        boolean debugHasPatient = false;
        String debugDateFrom = "";
        String debugDateTo = "";
        try {

            StringBuilder filter = new StringBuilder("");
            StringBuilder filterUnion = new StringBuilder("");
            String patientIfPresent = "";
            if(scheduleEventRequest.getPatientId() != null && scheduleEventRequest.getPatientId().length() > 0) {
                patientIfPresent = "AND PT_ID = ?";//scheduleEventRequest.getPatientId();
 }
            if ( ( staffIdField.equals("STAFF_ID") && scheduleEventRequest.getStaffId() != null && scheduleEventRequest.getStaffId().length() != 0 ) ||
                    ( scheduleEventRequest.getVv_id() != null && !scheduleEventRequest.getVv_id().isEmpty() ) )  {
                filter.append(String.format("AND %s = ?", staffIdField));
                filterUnion.append("AND STAFF_ID = ?");

            }
            connection = getOracleConnection();
            connection.setAutoCommit(false);

            String sql = String.format("SELECT T6.PT_PHONE AS ANI,S1.STAFF_ID,S1.STAFF_VV_ID,T5.PT_ID,T2.VISIT_SK," +
                    " S2.SCHED_EVNT_SK,S1.BE_ID,S2.SCHED_EVNT_START_DTIME,S2.SCHED_EVNT_END_DTIME," +
                    " T2.VISIT_ACT_START_TMSTP,T2.VISIT_ACT_END_TMSTP,T2.VISIT_ADJ_START_TMSTP,T2.VISIT_ADJ_END_TMSTP,S2.SCHED_EVNT_ID" +
                    " FROM (SELECT STAFF_ID, STAFF_VV_ID,BE_ID,REC_TERM_TMSTP,CURR_REC_IND" +
                    "                FROM STAFF " +
                    "                  WHERE BE_ID = ?" +
                    "                    %s" +
                    "                    AND ((TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') AND CURR_REC_IND = 1)) S1 " +
                    " LEFT JOIN " +
                    "  (SELECT SCHED_EVNT_SK,BE_ID,PT_ID,STAFF_ID,SCHED_EVNT_START_DTIME,SCHED_EVNT_END_DTIME," +
                    "      SCHED_EVNT_ID,REC_TERM_TMSTP,CURR_REC_IND" +
                    "                FROM SCHED_EVNT " +
                    "                  WHERE BE_ID = ?" +
                    "                  %s                    " +
                    "                       AND SCHED_EVNT_START_DTIME IS NOT NULL" +
                    "                       AND SCHED_EVNT_END_DTIME IS NOT NULL" +
                    "                       AND SCHED_EVNT_START_DTIME BETWEEN" +
                    "                  TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')" +
                    "                  AND TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')" +
                    "              AND ((TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') AND CURR_REC_IND = 1)) S2 " +
                    "              ON S1.STAFF_ID=S2.STAFF_ID AND S1.BE_ID=S2.BE_ID" +
                    " LEFT JOIN " +
                    "  (SELECT PT_SK,PT_ID,BE_ID,REC_TERM_TMSTP,CURR_REC_IND" +
                    "                FROM PT" +
                    "                  WHERE BE_ID = ?" +
                    "                      AND PT_ID IS NOT NULL" +
                    "                      AND ((TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') AND CURR_REC_IND = 1)) T5" +
                    "              ON S2.PT_ID = T5.PT_ID" +
                    " LEFT JOIN " +
                    "  (SELECT PT_ID,PT_PHONE,REC_TERM_TMSTP,CURR_REC_IND" +
                    "                FROM PT_CONT_PHONE" +
                    "                  WHERE PT_PHONE_ANI_ENABLED_IND = 1" +
                    "                      AND ((TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') AND CURR_REC_IND = 1)) T6" +
                    "              ON T5.PT_ID = T6.PT_ID" +
                    " LEFT JOIN (SELECT SCHED_EVNT_SK,VISIT_SK,VISIT_ACT_START_TMSTP,VISIT_ACT_END_TMSTP," +
                    "                                VISIT_ADJ_START_TMSTP,VISIT_ADJ_END_TMSTP" +
                    "                FROM VISIT" +
                    "                  ) T2" +
                    "              ON S2.SCHED_EVNT_SK = T2.SCHED_EVNT_SK" +
                    "            " +
                    " UNION " +
                    "  SELECT DISTINCT V3.ANI,V2.STAFF_ID,NULL AS STAFF_VV_ID, V3.PT_ID,V2.VISIT_SK, V2.SCHED_EVNT_SK," +
                    "         V2.BE_ID,V2.VISIT_ACT_START_TMSTP AS SCHED_EVNT_START_DTIME," +
                    "         NULL AS SCHED_EVNT_END_DTIME," +
                    "         V2.VISIT_ACT_START_TMSTP,V2.VISIT_ACT_END_TMSTP, V2.VISIT_ADJ_START_TMSTP,V2.VISIT_ADJ_END_TMSTP," +
                    "         NULL AS SCHED_EVNT_ID" +
                    " FROM (SELECT VISIT_SK,BE_ID, SCHED_EVNT_SK,STAFF_ID,PT_ID,VISIT_ACT_START_TMSTP,VISIT_ACT_END_TMSTP,VISIT_ADJ_START_TMSTP," +
                    "                          VISIT_ADJ_END_TMSTP" +
                    "                          FROM VISIT" +
                    "                              WHERE SCHED_EVNT_SK IS NULL" +
                    "                              AND VISIT_ACT_START_TMSTP IS NOT NULL" +
                    "                              AND VISIT_ACT_START_TMSTP BETWEEN TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')" +
                    "                              AND TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')" +
                    "                              AND BE_ID = ?" +
                    "                              %s " +
                    "                              ) V2" +
                    "                    LEFT JOIN (SELECT ANI,VISIT_EVNT_SK,VISIT_SK,STAFF_ID,PT_ID,VISIT_EVNT_DTIME,REC_TERM_TMSTP,CURR_REC_IND" +
                    "                      FROM VISIT_EVNT" +
                    "                        WHERE ((TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') AND CURR_REC_IND = 1)) V3" +
                    "                    ON V2.VISIT_SK = V3.VISIT_SK" +
                    "                    ORDER BY SCHED_EVNT_START_DTIME,SCHED_EVNT_END_DTIME", filter, patientIfPresent, filterUnion);


            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

          /*
            logger.info(String.format("Sql statement = %s ", sql));
            int tom = 1;
            logger.info(String.format("calling Prepared Statement tom = %s , value = %s ", Integer.toString(tom), Long.toString(bsnEntitySk.longValue())));
            tom++;
            logger.info(String.format("calling Prepared Statement tom = %s , value = %s ", Integer.toString(tom), dateFormat.format(scheduleEventRequest.getFromDate())));
            tom++;
            logger.info(String.format("calling Prepared Statement tom = %s , value = %s ", Integer.toString(tom), dateFormat.format(scheduleEventRequest.getToDate())));
            tom++;
            logger.info(String.format("calling Prepared Statement tom = %s , value = %s ", Integer.toString(tom), bsnEntityId));
            tom++;
            logger.info(String.format("calling Prepared Statement tom = %s , value = %s ", Integer.toString(tom), bsnEntityId));
            */
            preparedStatement = connection.prepareStatement(sql);
            debugSQL = sql;
            debugSQL = debugSQL.replace("?", "%s");

            StringBuffer preparedSql = new StringBuffer(uuid.toString());
            preparedSql.append(", ");
            preparedSql.append(sql);
            preparedSql.append("; ");
            preparedSql.append(bsnEntityId);
            preparedSql.append(", ");
            debugBeId = bsnEntityId;
            int index = 1;
//            logger.info(String.format("calling Prepared Statement index = %s , value = %s ", Integer.toString(index), Long.toString(bsnEntitySk.longValue())));
            preparedStatement.setString(index++, bsnEntityId);
//            logger.info(String.format("calling Prepared Statement index = %s , value = %s ", Integer.toString(index), dateFormat.format(scheduleEventRequest.getFromDate())));
            if(staffIdField.equals("STAFF_VV_ID")) {
                if (scheduleEventRequest.getVv_id() != null && scheduleEventRequest.getVv_id().length() != 0) {

                    preparedStatement.setString(index++, santraxId);
                    preparedSql.append(scheduleEventRequest.getVv_id());
                    preparedSql.append(", ");
                    debugStaffId1 = santraxId;
                }
            }
            else
            {
                if (scheduleEventRequest.getStaffId() != null && scheduleEventRequest.getStaffId().length() != 0) {
                    preparedStatement.setString(index++, scheduleEventRequest.getStaffId());
                    preparedSql.append(scheduleEventRequest.getStaffId());
                    preparedSql.append(", ");
                    debugStaffId1 = scheduleEventRequest.getStaffId();
                }
            }

            preparedStatement.setString(index++, bsnEntityId);
            preparedSql.append(bsnEntityId);
            preparedSql.append(", ");

            if (scheduleEventRequest.getPatientId() != null && scheduleEventRequest.getPatientId().length() > 0) {
                preparedStatement.setString(index++, scheduleEventRequest.getPatientId());
                preparedSql.append(scheduleEventRequest.getPatientId());
                preparedSql.append(", ");
                debugPtId = scheduleEventRequest.getPatientId();
                debugHasPatient = true;
            }

            preparedStatement.setString(index++, dateFormat.format(scheduleEventRequest.getFromDate()));
            preparedSql.append(dateFormat.format(scheduleEventRequest.getFromDate()));
            preparedSql.append(", ");
            debugDateFrom = dateFormat.format(scheduleEventRequest.getFromDate());

//            logger.info(String.format("calling Prepared Statement index = %s , value = %s ", Integer.toString(index), dateFormat.format(scheduleEventRequest.getToDate())));
            preparedStatement.setString(index++, dateFormat.format(scheduleEventRequest.getToDate()));
            preparedSql.append(dateFormat.format(scheduleEventRequest.getToDate()));
            preparedSql.append(", ");
            debugDateTo = dateFormat.format(scheduleEventRequest.getToDate());

//            logger.info(String.format("calling Prepared Statement index = %s , value = %s ", Integer.toString(index), bsnEntityId));

//            logger.info(String.format("calling Prepared Statement index = %s , value = %s ", Integer.toString(index), bsnEntityId));
            preparedStatement.setString(index++, bsnEntityId);
            preparedSql.append(bsnEntityId);
            preparedSql.append(", ");
            preparedStatement.setString(index++, dateFormat.format(scheduleEventRequest.getFromDate()));
            preparedSql.append(dateFormat.format(scheduleEventRequest.getFromDate()));
            preparedSql.append(", ");
            preparedStatement.setString(index++, dateFormat.format(scheduleEventRequest.getToDate()));
            preparedSql.append(dateFormat.format(scheduleEventRequest.getToDate()));
            preparedSql.append(", ");
            preparedStatement.setString(index++, bsnEntityId);
            preparedSql.append(bsnEntityId);
            preparedSql.append(", ");
            if (scheduleEventRequest.getStaffId() != null && scheduleEventRequest.getStaffId().length() != 0) {
                preparedStatement.setString(index++, scheduleEventRequest.getStaffId());
                preparedSql.append(scheduleEventRequest.getStaffId());
                preparedSql.append(", ");
                debugStaffId2 = scheduleEventRequest.getStaffId();
            }

            getVisitEventLogger().info(preparedSql.toString());
            /*if (staffId != null && staffId.length() != 0) {
                preparedStatement.setString(index, staffId);
            }*/
            resultSet = preparedStatement.executeQuery();

            List<ScheduleEventExt> scheduleEventsExt = new ArrayList<>();
            HashMap<BigDecimal, ScheduleEventExt> scheduleEventMapExt = new HashMap<>();
            int missingSk = -1;
            if(!resultSet.isBeforeFirst()) {
                getVisitEventLogger().info("No records returned from query!");
            }
            while (resultSet.next()) {

                mapScheduleEventsExt(scheduleEventMapExt, resultSet, missingSk, uuid, staffIdField, santraxId);
                missingSk--;
            }
            if(scheduleEventMapExt.size() > 1) {
                ScheduleEventExt scheduleEventExt = scheduleEventMapExt.remove(BigDecimal.ONE);
                if(scheduleEventExt != null) {
                    String staff_id = scheduleEventExt.getStaffID();
                    String vv_id = scheduleEventExt.getVv_id();
                    getVisitEventLogger().info(String.format("removed 1 record which was staff only, staffId = %s, vv_id = %s", staff_id, vv_id));
                }
            }
            for (Map.Entry entry : scheduleEventMapExt.entrySet()) {

                ScheduleEventExt scheduleEventExt = (ScheduleEventExt)entry.getValue();
                scheduleEventsExt.add(scheduleEventExt);
                StringBuffer retStr = new StringBuffer(uuid.toString());
                retStr.append("::ScheduleEventSK::");
                if(scheduleEventExt.getStaffID() != null) {
                    retStr.append(String.format("staffId = (%s)",scheduleEventExt.getStaffID()));
                }
                if(scheduleEventExt.getPatientID() != null) {
                    retStr.append(String.format("patientId = (%s)",scheduleEventExt.getPatientID()));
                }
                else {
                    retStr.append("patientId = (null)");
                }
                if(scheduleEventExt.getAni() != null) {
                    retStr.append(String.format("ANI = (%s)",scheduleEventExt.getAni()));
                }
                if(scheduleEventExt.getVisit() != null && scheduleEventExt.getVisit().size() > 0 && scheduleEventExt.getVisit().get(0) != null && scheduleEventExt.getVisit().get(0).getVisitSK() != null) {
                    retStr.append(String.format("visitSk = (%s)",scheduleEventExt.getVisit().get(0).getVisitSK().toString()));
                }

                if(scheduleEventExt.getScheduleEventSK() != null ) {
                    retStr.append(String.format("scheduleSk = (%s)",scheduleEventExt.getScheduleEventSK().toString()));
                }

                getVisitEventLogger().info(retStr.toString());



            }

            connection.commit();

            return scheduleEventsExt;

        } catch (Exception e) {

            try {
                if(debugHasPatient) {
                    getVisitEventExceptionLogger().info(String.format(debugSQL, debugBeId, debugStaffId1, debugBeId, debugPtId, debugDateFrom, debugDateTo,
                            debugBeId, debugDateFrom, debugDateTo, debugBeId, debugStaffId2));
                }
                else
                {
                    getVisitEventExceptionLogger().info(String.format(debugSQL, debugBeId, debugStaffId1, debugBeId, debugDateFrom, debugDateTo,
                            debugBeId, debugDateFrom, debugDateTo, debugBeId, debugStaffId2));
                }
            }
            catch(Exception exceptionDebug) {
                getVisitEventSqlExceptionLogger().info(exceptionDebug.getMessage());
            }
            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }
            getVisitEventExceptionLogger().info(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()));
            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()),e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }
        }
    }


    private void mapScheduleEventsExt(final HashMap<BigDecimal, ScheduleEventExt> scheduleEventMap, final ResultSet resultSet, int missingSk, UUID uuid, String staffIdField, String santraxId)
            throws SQLException {
        ScheduleEventExt scheduleEventExt = null;
        BigDecimal visitSk = resultSet.getBigDecimal("VISIT_SK");

        if (visitSk == null) {
            /***   creating a scheduleeventext object with a null schedulesk indicates valid staff with no visits or schedules.***/
            String businessEntityId_STAFFONLY = resultSet.getString("BE_ID");
            String staffId_STAFFONLY = resultSet.getString("STAFF_ID");
            if(staffId_STAFFONLY == null || staffId_STAFFONLY.isEmpty()) {
                //bad staff setup error so return
                return;
            }
            else {
                BigDecimal key_STAFFONLY = BigDecimal.ONE;
                scheduleEventExt = scheduleEventMap.get(key_STAFFONLY);
                if(scheduleEventExt == null) {
                    scheduleEventExt = new ScheduleEventExt();
                }
                if(staffIdField.equals("STAFF_VV_ID")) {
                    scheduleEventExt.setVv_id(santraxId);
                }

                scheduleEventExt.setScheduleEventSK(null);
                scheduleEventExt.setStaffID(staffId_STAFFONLY);
                scheduleEventExt.setBusinessEntityID(businessEntityId_STAFFONLY);
                scheduleEventMap.put(key_STAFFONLY, scheduleEventExt);
                return;
            }
        }
        String ani = resultSet.getString("ANI");
        BigDecimal schedEventSk = resultSet.getBigDecimal("SCHED_EVNT_SK");
        String businessEntityId = resultSet.getString("BE_ID");
        String patientId = resultSet.getString("PT_ID");
        String staffId = resultSet.getString("STAFF_ID");
        Timestamp schedEventStart = resultSet.getTimestamp("SCHED_EVNT_START_DTIME");
        Timestamp schedEventEnd = resultSet.getTimestamp("SCHED_EVNT_END_DTIME");
        Timestamp visitActualStartDate = resultSet.getTimestamp("VISIT_ACT_START_TMSTP");
        Timestamp visitActualEndDate = resultSet.getTimestamp("VISIT_ACT_END_TMSTP");
        Timestamp visitAdjustedStartDate = resultSet.getTimestamp("VISIT_ADJ_START_TMSTP");
        Timestamp visitAdjustedEndDate = resultSet.getTimestamp("VISIT_ADJ_END_TMSTP");

        BigDecimal key = getAniNum(ani);
        key = key.add(BigDecimal.valueOf(visitSk.longValue()));

        scheduleEventExt = scheduleEventMap.get(key);

        if (scheduleEventExt == null) {
            scheduleEventExt = new ScheduleEventExt();
            scheduleEventExt.setScheduleEventID(resultSet.getString("SCHED_EVNT_ID"));
        }

        if(patientId != null)
            scheduleEventExt.setPatientID(patientId);
        if(ani != null)
            scheduleEventExt.setAni(ani);

        if(staffId != null)
            scheduleEventExt.setStaffID(staffId);

        if(staffIdField.equals("STAFF_VV_ID")) {
            scheduleEventExt.setVv_id(santraxId);
        }

        if(schedEventSk != null)
            scheduleEventExt.setScheduleEventSK(BigInteger.valueOf(schedEventSk.longValue()));
        else if(scheduleEventExt.getScheduleEventSK() == null)
            scheduleEventExt.setScheduleEventSK(BigInteger.valueOf(visitSk.negate().longValue()));

        scheduleEventExt.setBusinessEntityID(businessEntityId);


        if (schedEventStart != null) {
            scheduleEventExt.setScheduleEventStartDatetime(new Date(schedEventStart.getTime()));
        }

        if (schedEventEnd != null) {
            scheduleEventExt.setScheduleEventEndDatetime(new Date(schedEventEnd.getTime()));
        }
        else if (schedEventStart != null && scheduleEventExt.getScheduleEventEndDatetime() == null) {
            //This can only happen if it was an unplanned visit log it
            Date twoHours =   new Date(schedEventStart.getTime() + (2 * 60 * 60000));

            String msg = String.format("UUID:(%s) ::No Scheduled End Time::Temporary ScheduleEventSK (%d) was (null).", uuid,
                    scheduleEventExt.getScheduleEventSK());
            getVisitEventLogger().info(msg);
            LoggerFactory.getLogger("VisitEventRepository::UnplannedVisit::").warn(msg);
            if(visitActualEndDate != null) {
                //this wont help us match but going 2 hours from the sstart might
                if(twoHours.after(visitActualEndDate)) {
                    scheduleEventExt.setScheduleEventEndDatetime(new Date(twoHours.getTime()));//schedEventStart.getTime() + (120 * 60000)));
                }
                else
                {
                    scheduleEventExt.setScheduleEventEndDatetime(new Date(visitActualEndDate.getTime()));
                }
            }
            else
            {
                scheduleEventExt.setScheduleEventEndDatetime(new Date(schedEventStart.getTime() + (120 * 60000)));//POOMA
            }

        }

        //need this to create a new visit from a schedule
        //We only add if we have a visit unplanned or scheduled
        Visit visit = null;

        if ( visitSk != null ) {
            if(scheduleEventExt.getVisit().size() > 0) {
                visit = scheduleEventExt.getVisit().get(0);
            }
            else {
                visit = new Visit();
            }
            if(schedEventSk != null && visit.getScheduleEventSK() != null && BigInteger.valueOf(schedEventSk.longValue()) != visit.getScheduleEventSK()) {
                LoggerFactory.getLogger("VisitRepository").error("Visit had mismatched SCHED_EVNT_SK ");
            }
            else if(schedEventSk != null) {
                visit.setScheduleEventSK(BigInteger.valueOf(schedEventSk.longValue()));
            }
            visit.setVisitSK(BigInteger.valueOf(visitSk.longValue()));
            if(patientId != null && patientId.length() > 0) {
                visit.setPatientID(patientId);
            }
            if(staffId != null && staffId.length() > 0) {
                visit.setStaffID(staffId);
            }
            visit.setBusinessEntityID(businessEntityId);
            if (visitActualStartDate != null) {
                visit.setVisitActualStartTimestamp(new Date(visitActualStartDate.getTime()));
            }
            if (visitActualEndDate != null) {
                visit.setVisitActualEndTimestamp(new Date(visitActualEndDate.getTime()));
            }

            if (visitAdjustedStartDate != null) {
                visit.setVisitAdjustedStartTimestamp(new Date(visitAdjustedStartDate.getTime()));
            }

            if (visitAdjustedEndDate != null) {
                visit.setVisitAdjustedEndTimestamp(new Date(visitAdjustedEndDate.getTime()));
            }
            scheduleEventExt.getVisit().clear();
            scheduleEventExt.getVisit().add(visit);
            scheduleEventMap.put(key, scheduleEventExt);
        }


    }

    private BigDecimal getAniNum(String ani) {
        //goal is to return same number given same string
        if(ani == null)
            return BigDecimal.ZERO;

        char[] aniArray = ani.toCharArray();
        BigDecimal returnValue = BigDecimal.ZERO;
        for(int i = 0;i < ani.length();i++) {
            returnValue = returnValue.add(BigDecimal.valueOf( (int) aniArray[i]));
        }
        return returnValue;
    }
    
    private String getPatientID(final String ani, String bsnEntityId) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        
        getVisitEventSqlExceptionLogger().info("Started logging to new VISIT_EVENT_SQL_EXCEPTION_LOGGER appender!  In getPatientID");
        getVisitEventExceptionLogger().info("Started logging to new VISIT_EVENT_EXCEPTION_LOGGER appender!  In getPatientID");

        try {
            connection = getOracleConnection();
            connection.setAutoCommit(false);
            String sql = "SELECT P1.PT_ID FROM PT P1 JOIN PT_CONT_PHONE P2 ON P1.PT_ID = P2.PT_ID AND P1.BE_ID = P2.BE_ID " +
                    "WHERE P1.BE_ID = ? AND (TO_CHAR(P1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND P1.CURR_REC_IND = 1) " +
                    "AND P2.PT_PHONE = ? AND P2.PT_PHONE_ANI_ENABLED_IND=1 AND (TO_CHAR(P2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND P2.CURR_REC_IND = 1) ";

            preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            preparedStatement.setString(index++, bsnEntityId);
            preparedStatement.setString(index, ani);

            resultSet = preparedStatement.executeQuery();
            String retResult = null;
            Object result = null;

            if(!resultSet.isBeforeFirst()) {
                getVisitEventLogger().info(String.format("No Patient Found with an ani enabled phone at telephone number=%s", ani));
            }
            int checkSize = 0;
            String exceptionMsg = null;
            if(resultSet.next()) {
                result = resultSet.getObject(1);
                if (result != null && result instanceof String) {
                    retResult = result.toString();
                    exceptionMsg = String.format("Returning PT_ID=%s for ani=%s", retResult, ani);
                    checkSize = exceptionMsg.length();
                    while(resultSet.next()) {
                        Object tmp = resultSet.getObject(1);
                        if(tmp != null && tmp instanceof String) {
                            exceptionMsg += String.format(", found additional PT_ID=%s", (String)tmp);
                        }
                    }
                }
            }
            if(exceptionMsg != null && exceptionMsg.length() > checkSize) {
                getVisitEventExceptionLogger().info(String.format("EXCEPTION ALERT ALERT=%s", exceptionMsg));
            }
            connection.commit();
            return retResult;
        }  catch (Exception e) {
            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }
            

            String errMsg = String.format("%s: %s",
                    e.getClass().getName(), e.getMessage());
            getVisitEventExceptionLogger().info(errMsg);
            
            throw new SandataRuntimeException(errMsg);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }
        }
    }
    private List<Patient> getPatientRequest(final PatientStaffRequest patientStaffRequest, String bsnEntityId) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        getVisitEventSqlExceptionLogger().info("Started logging to new VISIT_EVENT_SQL_EXCEPTION_LOGGER appender!  In getPatientRequest");
        getVisitEventExceptionLogger().info("Started logging to new VISIT_EVENT_EXCEPTION_LOGGER appender!  In getPatientRequest");

        try {

            StringBuilder filter = new StringBuilder("PATIENT_ID");
            if (patientStaffRequest.getFilter() == Filter.OPTIONS.EQUALS) {
                filter.append(" = ?");
            }
            else {
                filter.append(" LIKE ?");
            }

            connection = getOracleConnection();
            connection.setAutoCommit(false);

            String sql = String.format("SELECT * FROM PT WHERE BE_ID = ? AND " +
                    "(TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) AND %s", filter.toString());

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;

            preparedStatement.setString(index++, bsnEntityId);

            if (patientStaffRequest.getFilter() == Filter.OPTIONS.EQUALS) {
                preparedStatement.setString(index, patientStaffRequest.getPatientId());
            }
            else if (patientStaffRequest.getFilter() == Filter.OPTIONS.CONTAINS) {
                preparedStatement.setString(index, "%" + patientStaffRequest.getPatientId() + "%");
            }
            else if (patientStaffRequest.getFilter() == Filter.OPTIONS.STARTS_WITH) {
                preparedStatement.setString(index, patientStaffRequest.getPatientId() + "%");
            }
            else if (patientStaffRequest.getFilter() == Filter.OPTIONS.ENDS_WITH) {
                preparedStatement.setString(index, "%" + patientStaffRequest.getPatientId());
            }

            resultSet = preparedStatement.executeQuery();
            Object result = new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.Patient");

            connection.commit();

            return (List<Patient>)result;

        }  catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            String errMsg = String.format("%s: %s",
                    e.getClass().getName(), e.getMessage());
            getVisitEventExceptionLogger().info(errMsg);
            
            throw new SandataRuntimeException(errMsg);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }
        }
    }
    private String getStaffIdBySantraxId(final String santraxId, String bsnEntityId ) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String result = null;
        getVisitEventLogger().info("Started logging to new VISIT_EVENT_LOGGER appender!  In getStaffIdBySantraxId");
        getVisitEventSqlExceptionLogger().info("Started logging to new VISIT_EVENT_SQL_EXCEPTION_LOGGER appender!  In getStaffIdBySantraxId");
        getVisitEventExceptionLogger().info("Started logging to new VISIT_EVENT_EXCEPTION_LOGGER appender!  In getStaffIdBySantraxId");
        try {
            connection = getOracleConnection();
            connection.setAutoCommit(false);
            String sql = "SELECT STAFF_ID FROM STAFF WHERE BE_ID = ? AND STAFF_VV_ID = ? AND " +
                    "(TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";
            preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            preparedStatement.setString(index++, bsnEntityId);
            preparedStatement.setString(index++, santraxId);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.isBeforeFirst()) {
                getVisitEventExceptionLogger().info(String.format("WARNING::STAFF_ID NOT FOUND FOR SANTRAX_ID = %s and BsnEntityId = %s", santraxId, bsnEntityId));
            }
            if(resultSet.next()) {
                result = resultSet.getString("STAFF_ID");
            }
            connection.commit();



        }  catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            String errMsg = String.format("%s: %s",
                    e.getClass().getName(), e.getMessage());
            getVisitEventExceptionLogger().info(errMsg);

            throw new SandataRuntimeException(errMsg);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }
            return result;
        }
    }
    private List<Staff> getStaffRequest(final PatientStaffRequest patientStaffRequest, final String bsnEntityId) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        getVisitEventLogger().info("Started logging to new VISIT_EVENT_LOGGER appender!  In getStaffRequest");
        getVisitEventSqlExceptionLogger().info("Started logging to new VISIT_EVENT_SQL_EXCEPTION_LOGGER appender!  In getStaffRequest");
        getVisitEventExceptionLogger().info("Started logging to new VISIT_EVENT_EXCEPTION_LOGGER appender!  In getStaffRequest");
        
        
        try {
            StringBuilder filter = new StringBuilder("STAFF_ID");
            if (patientStaffRequest.getFilter() == Filter.OPTIONS.EQUALS) {
                filter.append(" = ?");
            }
            else {
                filter.append(" LIKE ?");
            }
            List<String> listStaffId = new ArrayList();
            String filterString = filter.toString();
            StringBuilder newFilter = new StringBuilder(filterString);
            int i = 0;
            if(patientStaffRequest.getStaffIdFilterList().size() > 0) {
                for (String staff: patientStaffRequest.getStaffIdFilterList()) {
                    listStaffId.add(staff);
                    if(i > 0) {
                        newFilter.append(" OR ");
                        filter.append(filterString);
                    }
                    i++;
                }
            }
            else {
                listStaffId.add(patientStaffRequest.getStaffId());
            }

            connection = getOracleConnection();
            connection.setAutoCommit(false);

            String sql = String.format("SELECT * FROM STAFF WHERE BE_ID = ? AND " +
                    "(TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) AND ( %s )", newFilter.toString());

            getVisitEventLogger().info(newFilter.toString());
            preparedStatement = connection.prepareStatement(sql);

            int index = 1;

            preparedStatement.setString(index++, bsnEntityId);
            for (String staffId : listStaffId) {
                if (patientStaffRequest.getFilter() == Filter.OPTIONS.EQUALS) {
                    preparedStatement.setString(index++, staffId);
                } else if (patientStaffRequest.getFilter() == Filter.OPTIONS.CONTAINS) {
                    preparedStatement.setString(index++, "%" + staffId + "%");
                } else if (patientStaffRequest.getFilter() == Filter.OPTIONS.STARTS_WITH) {
                    preparedStatement.setString(index++, staffId + "%");
                } else if (patientStaffRequest.getFilter() == Filter.OPTIONS.ENDS_WITH) {
                    preparedStatement.setString(index++, "%" + staffId);
                }
            }

            resultSet = preparedStatement.executeQuery();
            Object result = new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.Staff");

            connection.commit();

            return (List<Staff>)result;

        }  catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            String errMsg = String.format("%s: %s",
                    e.getClass().getName(), e.getMessage());
            getVisitEventExceptionLogger().info(errMsg);
            
            throw new SandataRuntimeException(errMsg);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }
        }
    }

    @Override
    public void getScheduleEvents(Exchange exchange) throws SandataRuntimeException {

        getScheduleEventsRequest(exchange);
    }

    @Override
    public void getScheduleEventsRequest(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = VisitEventRepositoryLogger.CreateLogger(exchange, loggerService);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        logger.start();

        Message in = exchange.getIn();
        Object request = in.getBody();
        if (request == null) {
            throw new SandataRuntimeException("getScheduleEventsRequest: ScheduleEventRequest is NULL!");
        }
        if( request instanceof MessageContentsList) {
            MessageContentsList mcl = in.getBody(MessageContentsList.class);
            String fromDate = (String) mcl.get(0);
            // Convert to Date to check format
            if (fromDate != null && fromDate.length() != 0) {
                try {
                    Date date = format.parse(fromDate);
                } catch (ParseException pe) {
                    throw new SandataRuntimeException(
                            String.format("From Date: [%s]: Is NOT a valid date and/or the format is incorrect!",
                                    fromDate));
                }
            } else {
                throw new SandataRuntimeException("From Date is required!");
            }
            String toDate = (String) mcl.get(1);
            if (toDate != null && toDate.length() != 0) {
                try {
                    Date date = format.parse(toDate);
                } catch (ParseException pe) {
                    throw new SandataRuntimeException(
                            String.format("To Date: [%s]: Is NOT a valid date and/or the format is incorrect!",
                                    toDate));
                }
            } else {
                throw new SandataRuntimeException("To Date is required!");
            }
            String dnis = (String) mcl.get(2);
            if (dnis == null || dnis.length() == 0) {
                throw new SandataRuntimeException("DNIS is NULL or Empty!");
            }
            String staffId = (String) mcl.get(3);

            try {
                ScheduleEventRequest scheduleEventRequest = new ScheduleEventRequest();
                scheduleEventRequest.setStaffId(staffId);
                scheduleEventRequest.setFromDate(format.parse(fromDate));
                scheduleEventRequest.setToDate(format.parse(toDate));
                scheduleEventRequest.setDnis(dnis);
                String staffIdField = "STAFF_ID";
                String bsnEntityId = getBusinessEntityIDForDNIS(scheduleEventRequest.getDnis());
                if (bsnEntityId == null) {
                    getVisitEventExceptionLogger().info(String.format("getScheduleEventsExt::DNIS: [%s]: Is not configured and/or could not be found!",
                            scheduleEventRequest.getDnis()));
                    throw new SandataRuntimeException(String.format("DNIS: [%s]: Is not configured and/or could not be found!",
                            scheduleEventRequest.getDnis()));

                }
                //Added for visit verification so stx_id could be used in place of staff_id
                staffIdField = createCallPreferencesStaffFieldForVVRequest(bsnEntityId);
                exchange.getIn().setBody(getScheduleEventsExt(scheduleEventRequest, staffIdField, bsnEntityId));

            } catch (Exception e) {
                String errMsg = String.format("%s: %s", e.getClass().getName(), e.getMessage());

                logger.error(errMsg);
                throw new SandataRuntimeException(errMsg);

            } finally {
                logger.stop();
            }
        }
        else if (request instanceof ScheduleEventRequest) {
            ScheduleEventRequest scheduleEventRequest = (ScheduleEventRequest) request;

            ScheduleEventResponse scheduleEventResponse = new ScheduleEventResponse();
            scheduleEventResponse.setScheduleEventRequest(scheduleEventRequest);
            String staffIdField = "STAFF_ID";
            String bsnEntityId = getBusinessEntityIDForDNIS(scheduleEventRequest.getDnis());
            if (bsnEntityId == null) {
                getVisitEventExceptionLogger().info(String.format("getScheduleEventsExt::DNIS: [%s]: Is not configured and/or could not be found!",
                        scheduleEventRequest.getDnis()));
                throw new SandataRuntimeException(String.format("DNIS: [%s]: Is not configured and/or could not be found!",
                        scheduleEventRequest.getDnis()));

            }
            //Added for visit verification so stx_id could be used in place of staff_id
            staffIdField = createCallPreferencesStaffFieldForVVRequest(bsnEntityId);
            if(staffIdField.equals("STAFF_VV_ID")) {
                String santraxId = scheduleEventRequest.getStaffId();
                scheduleEventRequest.setVv_id(santraxId);
                String staffId = getStaffIdBySantraxId(santraxId, bsnEntityId);
                if(staffId == null || staffId.isEmpty()) {
                    getVisitEventExceptionLogger().info(String.format("StaffId returned from getStaffIdBySantraxId(%s, %s) was null or Empty", santraxId, bsnEntityId));
                }
                scheduleEventRequest.setStaffId(getStaffIdBySantraxId(santraxId, bsnEntityId));
            }
            try {
                List<ScheduleEventExt> scheduleEventsExt = getScheduleEventsExt(scheduleEventRequest, staffIdField, bsnEntityId);
                scheduleEventResponse.setScheduleEventsExt(scheduleEventsExt);

            } catch (SandataRuntimeException sre) {
                String errMsg = String.format("EXCEPTION: %s", sre.getMessage());
                scheduleEventResponse.setErrorMessage(errMsg);
            }
            exchange.getIn().setBody(scheduleEventResponse);

            logger.stop();
        }
        else {
            throw new SandataRuntimeException("getScheduleEventsRequest: request is NOT an instance of ScheduleEventRequest!");
        }
    }

    @Override
    public void getPatientStaffRequest(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = VisitEventRepositoryLogger.CreateLogger(exchange, loggerService);

        logger.start();

        Object request = exchange.getIn().getBody();
        if (request == null) {
            throw new SandataRuntimeException("getPatientStaffRequest: PatientStaffRequest is NULL!");
        }

        if (!(request instanceof PatientStaffRequest)) {
            throw new SandataRuntimeException("getPatientStaffRequest: request is NOT an instance of PatientStaffRequest!");
        }

        PatientStaffRequest patientStaffRequest = (PatientStaffRequest)request;

        PatientStaffResponse patientStaffResponse = new PatientStaffResponse();
        patientStaffResponse.setPatientStaffRequest(patientStaffRequest);

        try {
            String bsnEntId = getBusinessEntityIDForDNIS(patientStaffRequest.getDnis());
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(String.format("DNIS: [%s]: Is not configured and/or could not be found!",
                        patientStaffRequest.getDnis()));
            }

            List<Patient> patients = getPatientRequest(patientStaffRequest, bsnEntId);
            List<Staff> staffList = getStaffRequest(patientStaffRequest, bsnEntId);

            patientStaffResponse.setPatients(patients);
            patientStaffResponse.setStaffList(staffList);
        }
        catch (SandataRuntimeException sre) {
            String errMsg = String.format("EXCEPTION: %s", sre.getMessage());
            patientStaffResponse.setErrorMessage(errMsg);
        }

        exchange.getIn().setBody(patientStaffResponse);

        logger.stop();
    }

    @Override
    public void createVisitEventRequest(Exchange exchange) throws SandataRuntimeException {

        //UUID uuid = UUID.randomUUID();
        SandataLogger logger = VisitEventRepositoryLogger.CreateLogger(exchange, loggerService);
        getVisitEventSqlExceptionLogger().info("Started logging to new VISIT_EVENT_SQL_EXCEPTION_LOGGER appender!  In createVisitEventRequest");
        getVisitEventExceptionLogger().info("Started logging to new VISIT_EVENT_EXCEPTION_LOGGER appender!  In createVisitEventRequest");

        logger.start();
        UUID uuid = UUID.fromString(logger.getLoggerId());
        Connection connection = null;

        String createUpdatedBy = "VisitEventRepository";

        Object request = exchange.getIn().getBody();
        if (request == null) {
            throw new SandataRuntimeException("createVisitEventRequest: VisitEvent is NULL!");
        }

        if (!(request instanceof VisitEvent)) {
            throw new SandataRuntimeException("createVisitEventRequest: request is NOT an instance of VisitEvent!");
        }

        try {
logger.info("***************** CREATEVISITEVENTREQUEST::CREATING VISITEVENT WITHOUT A VISIT ************************");
            connection = getOracleConnection();
            connection.setAutoCommit(false);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date termDate = simpleDateFormat.parse("9999-12-31");

            VisitEvent visitEvent = (VisitEvent) request;
            // Insert VisitEvent
            if (visitEvent.getVisitEventSK() == null) {

                String reasonForChange = "Created: Call Matching Logic";

                visitEvent.setRecordTerminationTimestamp(termDate);
                visitEvent.setCurrentRecordIndicator(true);
                visitEvent.setRecordEffectiveTimestamp(new Date());
                visitEvent.setRecordCreateTimestamp(new Date());
                visitEvent.setRecordUpdateTimestamp(new Date());
                visitEvent.setChangeVersionID(BigInteger.ONE);
                //dmr--RemovedFromModel--visitEvent.setVisitEventTypeID("CALLMATCHING");
                visitEvent.setVisitEventTypeCode(VisitEventTypeCode.TEL);
                visitEvent.setChangeReasonMemo(reasonForChange);
                visitEvent.setRecordCreatedBy(createUpdatedBy);

                int returnVal = executeRecursive(connection, visitEvent, true, -999);
                if (returnVal > 0) {

                    connection.commit();
                    visitEvent.setVisitEventSK(BigInteger.valueOf(returnVal));
                    exchange.getIn().setBody(visitEvent);
                }
                else {
                    throw new SandataRuntimeException("Insert was not successful!");
                }
            }

            connection.commit();

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            getVisitEventExceptionLogger().info(errMsg);
            throw new SandataRuntimeException(errMsg,e);
        }
        finally {

            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            logger.stop();
        }
    }
    @Override
    public void clearScheduledVisitisExceptionsArray(Exchange exchange) throws  SandataRuntimeException {
        Message in = exchange.getIn();
        //Object value = in.getBody();
        int result = 0;
        ArrayList list = in.getBody(ArrayList.class);
        int size = list.size();
        for(int i = 0; i < size; i++) {
            BigInteger visitSk = (BigInteger) list.get(i);
            result = clearExceptionsByVisitSk(visitSk, true);
        }
        in.setHeader("clearResult", result);
    }
    @Override
    public void clearExceptionsArray(Exchange exchange) throws  SandataRuntimeException {
        Message in = exchange.getIn();
        //Object value = in.getBody();
        int result = 0;
        ArrayList list = in.getBody(ArrayList.class);
        int size = list.size();
        for(int i = 0; i < size; i++) {
            BigInteger visitSk = (BigInteger) list.get(i);
            result = clearExceptionsByVisitSk(visitSk);
        }
        in.setHeader("clearResult", result);
    }
    @Override
    public void clearExceptions(Exchange exchange) throws  SandataRuntimeException {
        Message in = exchange.getIn();
        //Object value = in.getBody();
        Object value = in.getHeader("VISIT_SK");

        int visitSK = Integer.valueOf(value.toString());
        int result = clearExceptionsByVisitSk(BigInteger.valueOf(visitSK));

        in.setHeader("clearResult", result);
    }
    @Override
    public void createVisitExceptionRequest(Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = VisitEventRepositoryLogger.CreateLogger(exchange, loggerService);
        logger.start();
        
        getVisitEventSqlExceptionLogger().info("Started logging to new VISIT_EVENT_SQL_EXCEPTION_LOGGER appender!  In createVisitExceptionRequest");
        getVisitEventExceptionLogger().info("Started logging to new VISIT_EVENT_EXCEPTION_LOGGER appender!  In createVisitExceptionRequest");
        
        Connection connection = null;
        String createUpdatedBy = "VisitEventRepository";
        Object request = exchange.getIn().getBody();
        if (request == null) {
            throw new SandataRuntimeException("createVisitRequest: Visit is NULL!");
        }
        if (!(request instanceof VisitException)) {
            throw new SandataRuntimeException("createVisitExceptionRequest: request is NOT an instance of VisitException!");
        }
        VisitException visitException = (VisitException)request;
        if(visitException.getVisitSK() == null || visitException.getExceptionID() == null) {
            throw new SandataRuntimeException("createVisitExceptionRequest: request is missing VisitSK and/or ExceptionID values!");
        }
        if(visitException.getVisitExceptionSK() != null) {
            throw new SandataRuntimeException("createVisitExceptionRequest: request has SK value (DUPLICATE)!");
        }


        try {
            connection = getOracleConnection();
            connection.setAutoCommit(false);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date termDate = simpleDateFormat.parse("9999-12-31");

            Date stamp = new Date();
            visitException.setRecordCreateTimestamp(stamp);
            visitException.setRecordUpdateTimestamp(stamp);
            visitException.setRecordEffectiveTimestamp(stamp);
            visitException.setCurrentRecordIndicator(true);
            visitException.setRecordTerminationTimestamp(termDate);
            visitException.setChangeVersionID(BigInteger.ONE);
            visitException.setRecordCreatedBy("VISIT_EXCEPTION_RULES");

            int returnVal = executeRecursive(connection, visitException, true, -999);
            if (returnVal > 0) {

                connection.commit();
                visitException.setVisitExceptionSK(BigInteger.valueOf(returnVal));
                exchange.getIn().setBody(visitException);
            }
            else {
                throw new SandataRuntimeException("Insert was not successful!");
            }
            connection.commit();

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            getVisitEventExceptionLogger().info(errMsg);
            throw new SandataRuntimeException(errMsg,e);
        }
        finally {

            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            logger.stop();
        }
    }
    @Override
    public void createExcLookupRequest(Exchange exchange) throws SandataRuntimeException {
        Message in = exchange.getIn();
        List<ExceptionLookup> list = getExcpLkupTableRequest();
        exchange.getIn().setBody(list);
    }
    private List<ExceptionLookup> getExcpLkupTableRequest() throws SandataRuntimeException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        getVisitEventSqlExceptionLogger().info("Started logging to new VISIT_EVENT_SQL_EXCEPTION_LOGGER appender!  In getExcpLkupTableRequest");
        getVisitEventExceptionLogger().info("Started logging to new VISIT_EVENT_EXCEPTION_LOGGER appender!  In getExcpLkupTableRequest");
        
        try {
            connection = getOracleConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM EXCP_LKUP WHERE EXCP_ID < 30";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            Object result = new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.ExceptionLookup");
            connection.commit();

            return (List<ExceptionLookup>)result;

        }  catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            String errMessg = String.format("%s: %s", e.getClass().getName(), e.getMessage());
            getVisitEventExceptionLogger().info(errMessg);
            throw new SandataRuntimeException(errMessg,e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }
        }
    }
    @Override
    public void createVisitRequest(Exchange exchange) throws SandataRuntimeException {
        int line = 1148;
        String lineStr = "==UUID(%s)===>>>> passed line (%d)";
        SandataLogger logger = VisitEventRepositoryLogger.CreateLogger(exchange, loggerService);
        line = 1371;
        logger.start();
        String uuidStr = logger.getLoggerId();
        getVisitEventLogger().info(String.format("UUID: (%S) ::Started logging to new VISIT_EVENT_LOGGER appender!  In createVisitRequest", uuidStr));
        getVisitEventSqlExceptionLogger().info(String.format("UUID: (%S) ::Started logging to new VISIT_EVENT_SQL_EXCEPTION_LOGGER appender!  In createVisitRequest", uuidStr));
        getVisitEventExceptionLogger().info(String.format("UUID: (%S) ::Started logging to new VISIT_EVENT_EXCEPTION_LOGGER appender!  In createVisitRequest", uuidStr));
        
       Connection connection = null;
        String createUpdatedBy = "VisitEventRepository";
        logger.info(String.format(lineStr, uuidStr, line));
        getVisitEventLogger().info(String.format(lineStr, uuidStr, line));
        Object request = exchange.getIn().getBody();
        if (request == null) {

            throw new SandataRuntimeException("createVisitRequest: Visit is NULL!");
        }
        if (!(request instanceof Visit)) {
            throw new SandataRuntimeException("createVisitRequest: request is NOT an instance of Visit!");
        }


        line = 1463;
        logger.info(String.format(lineStr, uuidStr, line));
        getVisitEventLogger().info(String.format(lineStr, uuidStr, line));
        try {
            connection = getOracleConnection();
            connection.setAutoCommit(false);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date termDate = simpleDateFormat.parse("9999-12-31");
            String dnis = null;
            String bsnEntId = null;
            Visit visit = (Visit) request;
            bsnEntId=visit.getBusinessEntityID();
            getVisitEventLogger().info(String.format("VisitEventRepository.createVisitRequest.bsnEntId passed in exchange.message,body.visit was %s", bsnEntId));
            if(bsnEntId == null) {

             if(visit.getVisitEvent() != null && visit.getVisitEvent().size() > 0) {
                dnis = visit.getVisitEvent().get(0).getDialedNumberIdentificationService();
                bsnEntId = getBusinessEntityIDForDNIS(dnis);
                if (bsnEntId == null || bsnEntId.length() == 0) {
                    throw new SandataRuntimeException(String.format("DNIS: [%s]: Is not configured and/or could not be found!",
                            dnis));
                }
            }
            else {
                String staff = visit.getStaffID();
                BigInteger scheduleSK = visit.getScheduleEventSK();
                String scheduleReference = "NULL";
                if(scheduleSK != null)
                    scheduleReference = scheduleSK.toString();
                throw new SandataRuntimeException(String.format("VISIT WITHOUT VISITEVENTS ARE NOT ALLOWED!!! trying to get staff (%s) trying to get a schedule reference (%s)",staff, scheduleReference));
            }}
            String patientID = visit.getPatientID();
            // Insert Visit
            if (duplicateVisitEvent(connection, (VisitEvent)visit.getVisitEvent().get(0), uuidStr)) {
                VisitEvent ve = (VisitEvent)visit.getVisitEvent().get(0);
                logger.error(String.format("DUPLICATE VISIT EVENT BSN ENT ID (%s), dnis (%s), ani (%s), staffId (%s)",bsnEntId, dnis, ve.getAutomaticNumberIdentification(), ve.getStaffID())  );
                visit.setVisitSK(BigInteger.ZERO);//make sure handle duplicates (where visitSk == 0),
                // but still need to stop call imports from sending this duplicate by processing callEvent.
                exchange.getIn().setBody(visit);
            }
            else if (visit.getVisitSK() == null) {
                //first time we need staff, patient
                getVisitEventLogger().info("Insert new visit event");
                line = 1506;
                getVisitEventLogger().info(String.format(lineStr, uuidStr, line));
                logger.info(String.format(lineStr, uuidStr, line));
                String reasonForChange = "Created: Call Matching Logic";
                if ((patientID == null || patientID.length() == 0) && (visit.getVisitEvent().get(0).getAutomaticNumberIdentification() != null && visit.getVisitEvent().get(0).getAutomaticNumberIdentification().length() > 0)) {
                    patientID = getPatientID(visit.getVisitEvent().get(0).getAutomaticNumberIdentification(), bsnEntId);
                    line = 1441;
                    getVisitEventLogger().info(String.format(lineStr, uuidStr, line));
                    logger.info(String.format(lineStr, uuidStr, line));
                    if (patientID != null) {
                        visit.setPatientID(patientID);
                        logger.info(String.format("UUID=\"(%s)\", PatientID=\"(%s)\"", uuidStr, patientID));
                    }
                }
                line = 1520;
                logger.info(String.format(lineStr, uuidStr, line));
                getVisitEventLogger().info(String.format(lineStr, uuidStr, line));
                visit.setBusinessEntityID(bsnEntId);
                //dmr--RemovedFromModel--visit.setRecordTerminationTimestamp(termDate);
                //dmr--RemovedFromModel--visit.setCurrentRecordIndicator(true);
                //dmr--RemovedFromModel--visit.setRecordEffectiveTimestamp(new Date());
                visit.setRecordCreateTimestamp(new Date());
                visit.setRecordUpdateTimestamp(new Date());
                visit.setChangeVersionID(BigInteger.ONE);

                visit.setChangeReasonMemo(reasonForChange);
                visit.setRecordCreatedBy(createUpdatedBy);
                line = 1533;
                getVisitEventLogger().info(String.format(lineStr, uuidStr, line));
                logger.info(String.format(lineStr, uuidStr, line));
                for (VisitEvent visitEvent : visit.getVisitEvent()) {
                    if (visit.getVisitActualStartTimestamp() != null && visit.getVisitActualStartTimestamp().equals(visitEvent.getVisitEventDatetime()))
                        visitEvent.setCallInIndicator(true);
                    else if (visit.getVisitActualEndTimestamp() != null && visit.getVisitActualEndTimestamp().equals(visitEvent.getVisitEventDatetime()))
                        visitEvent.setCallOutIndicator(true);
                    visitEvent.setRecordTerminationTimestamp(termDate);
                    visitEvent.setRecordEffectiveTimestamp(new Date());
                    visitEvent.setRecordCreateTimestamp(new Date());
                    visitEvent.setRecordUpdateTimestamp(new Date());
                    visitEvent.setChangeVersionID(BigInteger.ONE);
                    //dmr--RemovedFromModel--visitEvent.setVisitEventTypeID("CALLMATCHING");
                    visitEvent.setVisitEventTypeCode(VisitEventTypeCode.TEL);
                    visitEvent.setChangeReasonMemo(reasonForChange);
                    visitEvent.setRecordCreatedBy(createUpdatedBy);
                }
                line = 1551;
                logger.info(String.format(lineStr, uuidStr, line));

                List<VisitTaskList> taskList = visit.getVisitTaskList();
                for (Iterator<VisitTaskList> iterator = taskList.iterator();iterator.hasNext();) {
                    VisitTaskList visitTaskList = iterator.next();
                    if(visitTaskList.getBusinessEntityTaskID() == null) {
                        Task task = getTask(bsnEntId, visitTaskList.getVisitTaskListID(), logger);
                        if (task != null) {
                            Date date = new Date();
                            visitTaskList.setVisitTaskListID(task.getTaskID());
                            visitTaskList.setBusinessEntityTaskID(task.getBusinessEntityTaskID());
                            visitTaskList.setCriticalTaskIndicator(task.isCriticalTaskIndicator());
                            //visitTaskList.setTaskResultsReadingType(task.getTaskReadingType());
                            visitTaskList.setRecordCreateTimestamp(date);
                            visitTaskList.setRecordUpdateTimestamp(date);
                            visitTaskList.setChangeVersionID(BigInteger.ONE);
                            visitTaskList.setBusinessEntityID(bsnEntId);
                        } else {
                            logger.error(String.format("TASK ID (%s) returned null from getTask()!", visitTaskList.getVisitTaskListID()));
                            getVisitEventLogger().info(String.format("UUID (%s): TASK ID (%s)  returned null from getTask()! Removing so we can continue.", uuidStr, visitTaskList.getVisitTaskListID()));
                            iterator.remove();
                        }
                    }

                }
                //visit.getVisitTaskList().clear();
                //visit.getVisitTaskList().addAll(taskList);
                line = 1579;
                getVisitEventLogger().info(String.format(lineStr, uuidStr, line));
                logger.info(String.format(lineStr, uuidStr, line));
                int returnVal = executeRecursive(connection, visit, true, -999);
                if (returnVal > 0) {

                    connection.commit();
                    visit.setVisitSK(BigInteger.valueOf(returnVal));
                    if(!isValidStaffId(visit.getBusinessEntityID(), visit.getStaffID()) ) {

                        visit.setStaffID(null);
                        getVisitEventLogger().info("nulled invalid staff for visit exceptions");
                    }
                    exchange.getIn().setBody(visit);
                    line = 1593;
                    getVisitEventLogger().info(String.format("New visit was inserted VISIT_SK=%d , %s",returnVal, String.format(lineStr, uuidStr, line)));
                } else {
                    getVisitEventExceptionLogger().info(String.format("ALERT_IN_CREATE_VISIT_REQUEST Insert Failed in recursive call!  Use uuid to check reason for failure. UUID=\"(%s)\" line=(%d)", uuidStr, line));
                    logger.error(String.format("ALERT_IN_CREATE_VISIT_REQUEST Insert Failed in recursive call!  Use uuid to check reason for failure. UUID=\"(%s)\" line=(%d)", uuidStr, line));
                    throw new SandataRuntimeException(String.format("Insert was not successful!  After line (%d)", line));

                }
            }
            // Update Visit
            else {
                String reasonForChange = "Updated: Call Matching Logic";



                line = 1608;
                logger.info(String.format(lineStr, uuidStr, line));


                visit.setRecordUpdatedBy(createUpdatedBy);
                visit.setRecordUpdateTimestamp(new Date());
                visit.setChangeReasonMemo(reasonForChange);
                boolean setCallInIndicator = false;
                boolean setCallOutIndicator = false;

                if( (patientID == null || patientID.length() == 0) && (visit.getVisitEvent().get(0).getAutomaticNumberIdentification() != null && visit.getVisitEvent().get(0).getAutomaticNumberIdentification().length() > 0 ))
                {
                    patientID = getPatientID(visit.getVisitEvent().get(0).getAutomaticNumberIdentification(),bsnEntId);

                    line = 1622;
                    logger.info(String.format(lineStr, uuidStr, line));
                    getVisitEventLogger().info(String.format(lineStr, uuidStr, line));
                    if(patientID != null) {
                        visit.setPatientID(patientID);
                        logger.info(String.format("UUID (%s), Setting patientID %s", uuidStr, patientID));
                    }
                }
                // Update all VisitEvents
                //tmd only insert new ones
                List<VisitEvent> list  = visit.getVisitEvent();
                for(Iterator<VisitEvent> iterator = list.iterator();iterator.hasNext();) {
                    VisitEvent visitEvent  = iterator.next();

                    if(visitEvent.getRecordCreateTimestamp() == null && visitEvent.getDialedNumberIdentificationService() != null) {

                        if(visit.getVisitActualStartTimestamp() != null && visit.getVisitActualStartTimestamp().equals(visitEvent.getVisitEventDatetime())) {
                            setCallInIndicator = true;
                            visitEvent.setCallInIndicator(true);
                        }
                        else if(visit.getVisitActualEndTimestamp() != null && visit.getVisitActualEndTimestamp().equals(visitEvent.getVisitEventDatetime()))
                        {
                            setCallOutIndicator = true;
                            visitEvent.setCallOutIndicator(true);
                        }
                        visitEvent.setRecordTerminationTimestamp(termDate);
                        visitEvent.setRecordEffectiveTimestamp(new Date());
                        visitEvent.setRecordCreateTimestamp(new Date());
                        visitEvent.setRecordUpdateTimestamp(new Date());
                        visitEvent.setChangeVersionID(BigInteger.ONE);
                        //dmr--RemovedFromModel--visitEvent.setVisitEventTypeID("CALLMATCHING");
                        visitEvent.setVisitEventTypeCode(VisitEventTypeCode.TEL);
                        visitEvent.setChangeReasonMemo(reasonForChange);
                        visitEvent.setRecordCreatedBy(createUpdatedBy);
                        line = 1656;
                        logger.info(String.format(lineStr, uuidStr, line));
                        getVisitEventLogger().info(String.format(lineStr, uuidStr, line));

                    }
                    else
                    {
                        iterator.remove();
                    }
                    line = 1665;
                    getVisitEventLogger().info(String.format(lineStr, uuidStr, line));
                    if(setCallInIndicator || setCallOutIndicator) {
                        updateVisitEvent(visit.getVisitSK(), setCallInIndicator, setCallOutIndicator);
                        if(visit.getVisitActualStartTimestamp() != null)
                            updateVisitEventCallInInd(visit.getVisitSK(), visit.getVisitActualStartTimestamp());
                        else
                            logger.error("visit.getVisitActualStartTimestamp() was null and should not have been for visitSK " + visit.getVisitSK().toString());
                    }

                }
                line = 1676;
                logger.info(String.format(lineStr, uuidStr, line));
                getVisitEventLogger().info(String.format(lineStr, uuidStr, line));
                List<VisitTaskList> list2 = visit.getVisitTaskList();
                for (Iterator<VisitTaskList> iterator = list2.iterator();iterator.hasNext();) {
                    VisitTaskList visitTaskList = iterator.next();
                    if(visitTaskList.getBusinessEntityTaskID() == null) {
                        Task task = getTask(bsnEntId, visitTaskList.getVisitTaskListID(), logger);
                        if (task != null) {
                            visitTaskList.setVisitTaskListID(task.getTaskID());
                            visitTaskList.setBusinessEntityTaskID(task.getBusinessEntityTaskID());
                            visitTaskList.setCriticalTaskIndicator(task.isCriticalTaskIndicator());
                            //visitTaskList.setTaskResultsReadingType(task.getTaskReadingType());
                            visitTaskList.setRecordCreateTimestamp(new Date());
                            visitTaskList.setRecordUpdateTimestamp(new Date());
                            visitTaskList.setChangeVersionID(BigInteger.ONE);
                            visitTaskList.setBusinessEntityID(bsnEntId);
                        } else {
                            logger.error(String.format("TASK ID (%s) not found!", visitTaskList.getVisitTaskListID()));
                            getVisitEventLogger().info(String.format("UUID (%s): TASK ID (%s) not found! Removing so we can continue.", uuidStr, visitTaskList.getVisitTaskListID()));
                            iterator.remove();
                        }
                    }

                }
                //visit.getVisitTaskList().clear();
                //visit.getVisitTaskList().addAll(list2);
                line = 1703;
                getVisitEventLogger().info(String.format(lineStr, uuidStr, line));
                logger.info(String.format(lineStr, uuidStr, line));
                int returnVal = executeRecursive(connection, visit, false, -999);
                if (returnVal > 0) {
                    line = 1708;
                    logger.info(String.format(lineStr, uuidStr, line));
                    getVisitEventLogger().info(String.format(lineStr, uuidStr, line));
                    connection.commit();
                    if(visit.getVisitSK() == null) {
                        visit.setVisitSK(BigInteger.valueOf((long)returnVal));
                    }
                    else
                    {
                        int oldVisitSk = visit.getVisitSK().intValue();
                        if(oldVisitSk == returnVal) {
                            getVisitEventLogger().info(String.format("after Update db call return matching visitSK UUID=\"%s\", VISIT_SK=%d ", uuidStr, returnVal));
                        }
                        else {
                            getVisitEventExceptionLogger().info(String.format("ALERT_IN_CREATE_VISIT_REQUEST after Update db call return mismatched visitSK UUID=\"%s\", VISIT_SK=%d , old VISIT_SK=%d", uuidStr, returnVal, oldVisitSk));
                        }
                    }

                    if(!isValidStaffId(visit.getBusinessEntityID(), visit.getStaffID()) ) {

                        visit.setStaffID(null);
                        getVisitEventLogger().info("nulled invalid staff for visit exceptions");
                    }
                    exchange.getIn().setBody(visit);

                }
                else {

                    getVisitEventExceptionLogger().info(String.format("ALERT_IN_CREATE_VISIT_REQUEST Update Failed in recursive call!  Use uuid to check reason for failure. UUID=\"(%s)\" line=(%d)", uuidStr, line));
                    logger.error(String.format("ALERT_IN_CREATE_VISIT_REQUEST Update Failed in recursive call!  Use uuid to check reason for failure. UUID=\"(%s)\" line=(%d)", uuidStr, line));

                    throw new SandataRuntimeException(String.format("Update was not successful! Made it past line (%d)", line));
                }
            }

            connection.commit();

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(String.format("UUID (%s): last line:(%d): SQLException(%s)",uuidStr, line, sqle.getMessage()));
                }
            }

            String errMsg = String.format("ALERT_CREATE_VISIT_REQUEST_EXCEPTION: class=%s : message=%s", getClass().getName(), e.getMessage() + String.format(lineStr, uuidStr, line));
            getVisitEventExceptionLogger().info(errMsg);
            throw new SandataRuntimeException(errMsg,e);
        }
        finally {

            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(String.format("UUID (%s): last line:(%d): SQLException(%s)",uuidStr, line, sqle.getMessage()));
                }
            }

            logger.stop();
        }
    }
    private boolean isValidStaffId(String bsnEntityId, String staffID) {
        boolean isValid = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getOracleConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM STAFF WHERE BE_ID = ? AND  STAFF_ID = ? AND " +
                    "(TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

            getVisitEventLogger().info(String.format("SELECT * FROM STAFF WHERE BE_ID = ? AND  STAFF_ID = ? AND " +
                    "(TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)", bsnEntityId, staffID));
            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntityId);
            preparedStatement.setString(index++, staffID);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.isBeforeFirst()) {
                isValid = true;
            }
        }  catch (Exception e) {

            String errMsg = String.format("%s: %s",
                    e.getClass().getName(), e.getMessage());
            getVisitEventExceptionLogger().info(errMsg);

            throw new SandataRuntimeException(errMsg);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }
            return isValid;
        }

    }

    private boolean duplicateVisitEvent(Connection connection, VisitEvent visitEvent, String uuidStr) throws SQLException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        String sql = "SELECT VISIT_EVNT_SK, REC_CREATE_TMSTP, VISIT_SK FROM VISIT_EVNT WHERE DNIS = ? AND ANI = ? AND VISIT_EVNT_DTIME = TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean result = false;
        
        getVisitEventSqlExceptionLogger().info("Started logging to new VISIT_EVENT_SQL_EXCEPTION_LOGGER appender!  In duplicateVisitEvent");
        getVisitEventExceptionLogger().info("Started logging to new VISIT_EVENT_EXCEPTION_LOGGER appender!  In duplicateVisitEvent");
        
        try {
            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            preparedStatement.setString(index++, visitEvent.getDialedNumberIdentificationService());
            preparedStatement.setString(index++, visitEvent.getAutomaticNumberIdentification());
            preparedStatement.setString(index++, dateFormat.format(visitEvent.getVisitEventDatetime()));
            resultSet = preparedStatement.executeQuery();
            connection.commit();
            result =  resultSet.next();

            if(result) {
                getVisitEventLogger().info(String.format("DUPLICATEVISITEVENTCHECK found a duplicate visit event!  UUID=\"%s\", VISIT_EVNT_SK=%f, VISIT_SK=%f, REC_CREATE_TMSTP=\"%s\" ," +
                        " DNIS=%s , ANI=%s , visitEventDateTime=%s ",
                        uuidStr, resultSet.getBigDecimal("VISIT_EVNT_SK"), resultSet.getBigDecimal("VISIT_SK"), dateFormat.format(resultSet.getDate("REC_CREATE_TMSTP")),
                        visitEvent.getDialedNumberIdentificationService(), visitEvent.getAutomaticNumberIdentification(), dateFormat.format(visitEvent.getVisitEventDatetime())));
            }
        }  catch (Exception e) {

            String errMsg = String.format("DUPLICATEVISITEVENTCHECK EXCEPTION in duplicate check: UUID=\"%s\": %s: exceptionmessage=\"%s\" ", uuidStr, e.getClass().getName(), e.getMessage());
            getVisitEventExceptionLogger().info(errMsg);
            throw new SandataRuntimeException(errMsg,e);

        } finally {


            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(String.format("DUPLICATEVISITEVENTCHECK SQL EXCEPTION UUID=\"%s\" exception=\"%s\"", uuidStr, sqle.getMessage()));
                }

            }

            connection.setAutoCommit(false);
           return result;
        }

    }

    private int updateVisitEventCallInInd(BigInteger visitSK, Date visitActualStartTimestamp) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        int result = 0;
        
        getVisitEventSqlExceptionLogger().info("Started logging to new VISIT_EVENT_SQL_EXCEPTION_LOGGER appender!  In updateVisitEventCallInInd");
        getVisitEventExceptionLogger().info("Started logging to new VISIT_EVENT_EXCEPTION_LOGGER appender!  In updateVisitEventCallInInd");

        try {
            connection = getOracleConnection();
            connection.setAutoCommit(false);
            callableStatement = connection.prepareCall("{?=call PKG_VISIT_UTIL.UPDATE_VISIT_EVNT_CALL_IN_IND(?,?)}");
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            
            int index = 2;
            callableStatement.setBigDecimal(index++, BigDecimal.valueOf(visitSK.longValue()));
            callableStatement.setString(index++, dateFormat.format(visitActualStartTimestamp));
            callableStatement.execute();
            result = (Integer) callableStatement.getObject(1);
            
            connection.commit();
            return result;

        }  catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            String errMsg = String.format("%s: %s", e.getClass().getName(), e.getMessage());
            getVisitEventExceptionLogger().info(errMsg);
            throw new SandataRuntimeException(errMsg,e);

        } finally {
            // Close the statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }
        }
    }

    private int updateVisitEvent(BigInteger visitSK, boolean isInCallIndicator, boolean isOutCallIndicator) {

        int isInCallInd = 1;
        if(isOutCallIndicator)
            isInCallInd = 0;
        Connection connection = null;
        CallableStatement callableStatement = null;
        int result = 0;
        
        getVisitEventSqlExceptionLogger().info("Started logging to new VISIT_EVENT_SQL_EXCEPTION_LOGGER appender!  In updateVisitEvent");
        getVisitEventExceptionLogger().info("Started logging to new VISIT_EVENT_EXCEPTION_LOGGER appender!  In updateVisitEvent");

        try {
            connection = getOracleConnection();
            connection.setAutoCommit(false);
            
            String callMethod = "{?=call PKG_VISIT_UTIL.UPDATE_VISIT_EVNT_IND(?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            
            int index = 2;
            callableStatement.setBigDecimal(index++, BigDecimal.valueOf(visitSK.longValue()));
            callableStatement.setInt(index++, isInCallInd);
            
            callableStatement.execute();
            result = (Integer) callableStatement.getObject(1);
            
            connection.commit();
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

            String errMsg = String.format("%s: %s", e.getClass().getName(), e.getMessage());
            getVisitEventExceptionLogger().info(errMsg);
            throw new SandataRuntimeException(errMsg,e);

        } finally {


            // Close the statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }
        }
    }

    private void setSk(final Object jpubType, int sequenceKey, String setSkMethodName) throws Exception {

        getVisitEventExceptionLogger().info("Started logging to new VISIT_EVENT_EXCEPTION_LOGGER appender!  In setSk");
        
        if (sequenceKey <= 0) {
            return;
        }

        try {

            Method method = jpubType.getClass().getDeclaredMethod(setSkMethodName, BigDecimal.class);
            method.invoke(jpubType, BigDecimal.valueOf(sequenceKey));
        }
        catch (Exception e) {
            
            String errMsg = String.format("%s: %s", e.getClass().getName(), e.getMessage());
            getVisitEventExceptionLogger().info(errMsg);
            throw new SandataRuntimeException(errMsg,e);
        }
    }

    private int executeRecursive(final Connection connection, final Object data, final boolean bShouldInsert, int returnVal) throws SandataRuntimeException {

        boolean cantUpdate = false;
        SandataLogger logger = VisitEventRepositoryLogger.CreateLogger();
        
        getVisitEventExceptionLogger().info("Started logging to new VISIT_EVENT_EXCEPTION_LOGGER appender!  In executeRecursive");
        
        Object jpubType;
        String shouldInsertStr = "false";
        OracleMetadata oracleMetadata;
        if (bShouldInsert) {
            shouldInsertStr = "true";
        }
        String dataStr = "unknown";
        
        if (data instanceof Visit) {
            dataStr = "Visit";
        } else if (data instanceof VisitEvent) {
            dataStr = "VisitEvent";
        } else if (data instanceof VisitTaskListExt) {
            dataStr = "VisitTaskListExt";
        } else if (data instanceof VisitTaskList) {
            if( ((VisitTaskList)data).getRecordCreateTimestamp() == null) {
                getVisitEventExceptionLogger().info("Type od data was VisitTaskLst and rec_create_tmstp was null");
                ((VisitTaskList) data).setRecordCreateTimestamp(new Date());
            }
            else {
                getVisitEventExceptionLogger().info("Type od data was VisitTaskLst and rec_create_tmstp was NOT null");
            }
            dataStr = "VisitTaskList";
        }
        
        logger.info(String.format("executeRecursive(Connection, %s, %s, %s)", dataStr,
                shouldInsertStr, Integer.toString(returnVal)));
        try {
            
            oracleMetadata = DataMapper.getOracleMetadata(data);
            jpubType = new DataMapper().map(data);                                 
            
            setSk(jpubType, returnVal, "setVisitSk");
            
            int result = 0;
            
            if (bShouldInsert || cantUpdate) {
                result = execute(connection, oracleMetadata.packageName(),
                        oracleMetadata.insertMethod(), jpubType);
            } else {
                
                if (data instanceof Visit) {
                    returnVal = ((Visit) data).getVisitSK().intValue();
                    
                    // update specific columns only TMD really should never be updating creation
                    // timestamp anyway
                    result = updateVisit(connection, (Visit) data);
                } else
                    // UPDATE
                    result = execute(connection, oracleMetadata.packageName(),
                            oracleMetadata.updateMethod(), jpubType);
            }
            
            if (result > 0) {
                
                if (returnVal == -999) {
                    returnVal = result;
                }
                
                // Check if there are any lists that need to be inserted
                for (Field field : data.getClass().getDeclaredFields()) {
                    
                    field.setAccessible(true);
                    
                    Object property = field.get(data);
                    if (property != null && property instanceof List) {
                        
                        List list = (List) property;
                        for (Object object : list) {
                            
                            // Try to insert the object!
                            
                            // WARNING: RECURSIVE!!!!
                            // TMD Currently all the lists are insert only no updates.
                            int insertResponse = executeRecursive(connection, object, true,
                                    returnVal);
                            if (insertResponse == -1) {
                                if (bShouldInsert) {
                                    throw new SandataRuntimeException(String.format(
                                            "INSERT: Failed: [%s]", object.getClass().getName()));
                                } else {
                                    throw new SandataRuntimeException(String.format(
                                            "UPDATE: Failed: [%s]", object.getClass().getName()));
                                }
                            }
                        }
                    }
                }
                
                // SUCCESS
                return returnVal;
                
            } // if (result > 0)
            
            // FAILED
            return -1;
        } catch (Exception e) {
            
            
            String errMsg = String.format("%s: %s", e.getClass().getName(), e.getMessage());
            getVisitEventExceptionLogger().info(errMsg);
            throw new SandataRuntimeException(errMsg,e);
            
        } finally {
            logger.stop();
        }
    }

    private int updateVisit(Connection connection, Visit visit) {

        getVisitEventSqlExceptionLogger().info("Started logging to new VISIT_EVENT_SQL_EXCEPTION_LOGGER appender!  In updateVisit");
        
        int result = 0;
        CallableStatement callableStatement = null;

        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            String callMethod = "{?=call PKG_VISIT_UTIL.UPDATE_VISIT(?,?,?,?,?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            
            int index = 2;
            callableStatement.setBigDecimal(index++, BigDecimal.valueOf(visit.getVisitSK().longValue()));
            callableStatement.setString(index++, dateFormat.format(visit.getRecordUpdateTimestamp()));
            callableStatement.setString(index++, visit.getRecordUpdatedBy());
            callableStatement.setString(index++, visit.getChangeReasonMemo());
            callableStatement.setString(index++, visit.getStaffID() != null ? visit.getStaffID() : null);
            callableStatement.setString(index++, visit.getVisitActualStartTimestamp() != null 
                    ? dateFormat.format(visit.getVisitActualStartTimestamp()) : null);
            callableStatement.setString(index++, visit.getVisitActualEndTimestamp() != null 
                    ? dateFormat.format(visit.getVisitActualEndTimestamp()) : null);
            
            callableStatement.execute();
            result = (Integer) callableStatement.getObject(1);
            
            connection.commit();
        }
        catch(SQLException sex) {

            String errMessage = "VisitEventRepository::updateVisit() Exception :: " + sex.getLocalizedMessage() ;
            getVisitEventSqlExceptionLogger().info(errMessage);
            throw new SandataRuntimeException(errMessage , sex);
        }
        finally {
            
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sex) {
                    sex.printStackTrace();
                    String errMsg = String.format("%s: %s", sex.getClass().getName(), sex.getMessage());
                    getVisitEventSqlExceptionLogger().info(errMsg);
                }
            }
        }

        return result;
    }

    private int execute(Connection connection, String packageName, String methodName, Object jpubType) throws SandataRuntimeException {

        CallableStatement callableStatement = null;
        getVisitEventSqlExceptionLogger().info("Started logging to new VISIT_EVENT_SQL_EXCEPTION_LOGGER appender!  In execute");
        getVisitEventExceptionLogger().info("Started logging to new VISIT_EVENT_EXCEPTION_LOGGER appender!  In execute");

        try {

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setObject(2, jpubType);
            callableStatement.execute();
            int result = (Integer)callableStatement.getObject(1);
            return result;
        }
        catch (Exception e) {

            // NOTE: Rollback (if necessary) should be handled by caller since they passed in the connection...
            String errMessg = String.format("[%s][%s]: %s: %s", packageName, methodName, e.getClass().getName(), e.getMessage());
            getVisitEventExceptionLogger().info(errMessg);
            throw new SandataRuntimeException(errMessg ,e);

        } finally {

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }
        }
    }

    private Task getTask(final String bsnEntID, final String taskId, SandataLogger logger) {

        // initialize cache
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        getVisitEventSqlExceptionLogger().info("Started logging to new VISIT_EVENT_SQL_EXCEPTION_LOGGER appender!  In getTask");
        getVisitEventExceptionLogger().info("Started logging to new VISIT_EVENT_EXCEPTION_LOGGER appender!  In getTask");

        try {

            connection = getOracleConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM TASK WHERE BE_ID=? AND BE_TASK_ID=?";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;

            preparedStatement.setString(index++, bsnEntID);
            preparedStatement.setString(index++, taskId);

            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                String errMsg =                   "NOT IN DATABASE";
                logger.warn(String.format("[BE_ID: %s][TASK_ID: %s]: Not Found!", bsnEntID, taskId));
                Task t = new Task();
                t.setBusinessEntityTaskID(bsnEntID);
                t.setBusinessEntityTaskID(taskId);
                t.setBusinessEntityTaskName(errMsg);
                t.setChangeReasonMemo(errMsg);
                BigInteger errInt = BigInteger.ZERO.subtract(BigInteger.ONE);
                t.setChangeVersionID(errInt);
                t.setTaskID("0000");
                return t;
            }

            Task task = new Task();
            task.setTaskSK(BigInteger.valueOf(resultSet.getBigDecimal("TASK_SK").longValue()));
            task.setBusinessEntityID(bsnEntID);
            task.setTaskID(taskId);
            task.setBusinessEntityTaskID(resultSet.getString("BE_TASK_ID"));
            //task.setTaskName(resultSet.getString("TASK_NAME"));
            task.setTaskDescription(resultSet.getString("TASK_DESC"));
            task.setCriticalTaskIndicator(resultSet.getBoolean("CRITICAL_TASK_IND"));
            //task.setTaskReadingType(resultSet.getString("TASK_READING_TYPE"));
            task.setRecordCreateTimestamp(resultSet.getDate("REC_CREATE_TMSTP"));
            task.setRecordUpdateTimestamp(resultSet.getDate("REC_UPDATE_TMSTP"));
            task.setChangeVersionID(BigInteger.valueOf(resultSet.getBigDecimal("CHANGE_VERSION_ID").longValue()));

            connection.commit();

            return task;

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            String errMessg = String.format("%s: %s", e.getClass().getName(), e.getMessage());
            getVisitEventExceptionLogger().info(errMessg);
            throw new SandataRuntimeException(errMessg ,e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }
        }
    }

    public Object getEntitiesForId(final String sql, final String className, final Object... params) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        getVisitEventSqlExceptionLogger().info("Started logging to new VISIT_EVENT_SQL_EXCEPTION_LOGGER appender!  In getEntitiesForId");
        getVisitEventExceptionLogger().info("Started logging to new VISIT_EVENT_EXCEPTION_LOGGER appender!  In getEntitiesForId");

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
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }
            
            String errMessg = String.format("%s: %s",e.getClass().getName(), e.getMessage());
            getVisitEventExceptionLogger().info(errMessg);
            throw new SandataRuntimeException(errMessg ,e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }
        }
    }
    public void createVisitAuthRequest(Exchange exchange) throws SandataRuntimeException {
        Message in  = exchange.getIn();
        Logger logger = LoggerFactory.getLogger(VISIT_EVENT_LOGGER);
        getVisitEventSqlExceptionLogger().info("Started logging to new VISIT_EVENT_SQL_EXCEPTION_LOGGER appender!  In createVisitAuthRequest");
        getVisitEventExceptionLogger().info("Started logging to new VISIT_EVENT_EXCEPTION_LOGGER appender!  In createVisitAuthRequest");
        
        
        if(in.getBody() instanceof HashMap) {
            HashMap<String, String> simpleVisitAuthRequest = in.getBody(HashMap.class);
            String patientId = simpleVisitAuthRequest.get("patientId");
            String strVisitSK = simpleVisitAuthRequest.get("visitSK");
            String strVisitDateTime = simpleVisitAuthRequest.get("visitDateTime");
            String bsnEntityId = simpleVisitAuthRequest.get("bsnEntityId");
            BigInteger visitSK = new BigInteger(strVisitSK);
            Date visitDateTime = new Date(Long.parseLong(strVisitDateTime));
            Date now = new Date();
            VisitAuthorization data = new VisitAuthorization();
            data.setVisitSK(visitSK);
            data.setChangeVersionID(BigInteger.ONE);
            data.setRecordCreateTimestamp(now);
            data.setRecordUpdateTimestamp(now);
            data = setAuthorizationSkAndOrdSk(data, bsnEntityId, patientId, visitDateTime);
            BigInteger authorizationSk = data.getAuthorizationSK();
            if(authorizationSk == null ) { 
                String message = "Cannot get AuthorizationSK! Stop creating VisitAuthRequest ";
                getVisitEventExceptionLogger().info(message);
                throw new SandataRuntimeException(message);
            }
            
            AuthorizationQualifier authQualifier = data.getAuthorizationQualifier();
            int returnVal =  getExisitngVisit_Auth(strVisitSK, authorizationSk.toString(), authQualifier.name() );
            if(returnVal > 0) {
                exchange.getIn().setBody(returnVal);
            }
            else {
                Connection connection = null;
                try {
                    connection = getOracleConnection();
                    connection.setAutoCommit(false);
                    returnVal = executeRecursive(connection, data, true, -999);
                    if (returnVal > 0) {

                        connection.commit();
                        exchange.getIn().setBody(returnVal);
                    } else {
                        String message = "Insert was not successful!";
                        logger.info(message);
                        throw new SandataRuntimeException(message);
                    }

                } catch (Exception e) {

                    if (connection != null) {

                        try {
                            connection.rollback();
                        } catch (SQLException sqle) {
                            sqle.printStackTrace();
                            getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                        }
                    }

                    String errMsg = format("%s: %s -  %s - %s", getClass().getName(), e.getMessage(),e.getStackTrace(),e.getCause());
                    getVisitEventExceptionLogger().info(errMsg);
                    throw new SandataRuntimeException(errMsg,e);
                    
                } finally {

                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (SQLException sqle) {
                            sqle.printStackTrace();
                            getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                        }
                    }
                }
            }
        }


    }

    private int getExisitngVisit_Auth(String visitSK, String authSK, String authQlfr) {
        String sql = "SELECT VISIT_AUTH_SK FROM VISIT_AUTH WHERE VISIT_SK = ? AND AUTH_SK = ? AND AUTH_QLFR = ? ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = -999;
        
        getVisitEventSqlExceptionLogger().info("Started logging to new VISIT_EVENT_SQL_EXCEPTION_LOGGER appender!  In getExisitngVisit_Auth");
        getVisitEventExceptionLogger().info("Started logging to new VISIT_EVENT_EXCEPTION_LOGGER appender!  In getExisitngVisit_Auth");
        
        try {
            connection = getOracleConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            preparedStatement.setLong(index++, Long.parseLong(visitSK));
            preparedStatement.setLong(index++, Long.parseLong(authSK));
            preparedStatement.setString(index++, authQlfr);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                result = resultSet.getInt("VISIT_AUTH_SK");
            }
            connection.commit();
            return result;
        }  catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            String errMsg = String.format("%s: %s", e.getClass().getName(), e.getMessage());
            getVisitEventExceptionLogger().info(errMsg);
            throw new SandataRuntimeException(errMsg,e);
            
        } finally {
            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }
            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }
            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }
        }
    }

    private VisitAuthorization setAuthorizationSkAndOrdSk(VisitAuthorization data, String bsnEntityId, String patientId, Date visitDateTime) throws SandataRuntimeException{

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        AuthorizationQualifier authQualifier = AuthorizationQualifier.AUTHORIZATION;
        data.setAuthorizationQualifier(authQualifier);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        getVisitEventSqlExceptionLogger().info("Started logging to new VISIT_EVENT_SQL_EXCEPTION_LOGGER appender!  In setAuthorizationSkAndOrdSk");
        getVisitEventExceptionLogger().info("Started logging to new VISIT_EVENT_EXCEPTION_LOGGER appender!  In setAuthorizationSkAndOrdSk");

        try {
            connection = getOracleConnection();
            connection.setAutoCommit(false);
            String sql = "SELECT AUTH_SK, ORD_SK FROM AUTH " +
                    "WHERE BE_ID = ? AND PT_ID = ? AND AUTH_START_TMSTP <= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') AND  AUTH_END_TMSTP > TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                    " AND ( TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) ";


            preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            String dateString = simpleDateFormat.format(visitDateTime);
            preparedStatement.setString(index++, bsnEntityId);
            preparedStatement.setString(index++, patientId);
            preparedStatement.setString(index++, dateString);
            preparedStatement.setString(index++, dateString);

            resultSet = preparedStatement.executeQuery();
            String retResult = null;
            Object result = null;
            if(resultSet.next()) {
                BigInteger authSk = resultSet.getBigDecimal("AUTH_SK").toBigInteger();
                data.setAuthorizationSK(authSk);
                BigDecimal bigDecimalOrdSk = resultSet.getBigDecimal("ORD_SK");
                if( bigDecimalOrdSk != null) {
                    data.setAuthorizationQualifier(AuthorizationQualifier.ORDER);
                }
            }
            connection.commit();
            return data;
        }  catch (Exception e) {
            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            String errMsg = String.format("%s: %s",
                    e.getClass().getName(), e.getMessage());
            
            getVisitEventExceptionLogger().info(errMsg);
            throw new SandataRuntimeException(errMsg,e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }
        }


    }
    public static final String CALL_MATCHING_KEY = "CallMatchingKey";
    public static final String CamelCacheKey = "CamelCacheKey";
    public void createCallPreferencesRequest(Exchange exchange)  throws SandataRuntimeException {
        Message in = exchange.getIn();
        String beId = (String)in.getBody(String.class);//("CamelCacheKey");
        CallPrefKV result = createCallPreferencesRequest(beId);
        in.setHeader(CamelCacheKey, beId);
        in.setHeader(CALL_MATCHING_KEY, beId );
        Gson gson = new Gson();
        String jsonString = gson.toJson(result.getKeyValue());
        in.setBody(jsonString);//be_id
        exchange.setIn(in);
    }
    private CallPrefKV createCallPreferencesRequest(String beId)  throws SandataRuntimeException {
        CallPrefKV callPreference = null;
        getVisitEventSqlExceptionLogger().info("Started logging to new VISIT_EVENT_SQL_EXCEPTION_LOGGER appender!  In createCallPreferencesStaffFieldForVVRequest");
        getVisitEventExceptionLogger().info("Started logging to new VISIT_EVENT_EXCEPTION_LOGGER appender!  In createCallPreferencesStaffFieldForVVRequest");
        String key = "MDW_AGENCY_CALL_PREFERENCES_FOR_CALL_MATCHING";
        String errMsg = String.format("ALERT NO AGENCY SETUP IN DATABASE MISSING KEY_NAME = %s IN Table = METADATA.APP_TENANT_KEY_CONF", key);
        List<String> jsonStrings = getKeyConfigurationValByKey(key, beId, false, errMsg);
        if(jsonStrings != null && jsonStrings.size() > 1) {
            throw new SandataRuntimeException(String.format("ERROR ALERT ---------------THERE CAN BE ONLY ONE!-----------CHECK METADATA.APP_TENANT_KEY_CONF, KEY_NAME = %s, BE_ID = %s " +
                    "----", key, beId));
        }
        Iterator<String> jsonIteratorString = jsonStrings.iterator();

        while(jsonIteratorString.hasNext()) {
            String jsonString = jsonIteratorString.next();
            Gson gson = new Gson();
            callPreference = gson.fromJson(jsonString, CallPrefKV.class );
        }
        if(callPreference == null) {
            getVisitEventExceptionLogger().info(String.format("ALERT MISSING CallPreferences TENANT_KEY_CONF_VAL = null THERE IS NO AGENCY WITH BE_ID = %s SETUP WITH CALL PREFERENCES KEY", beId));
        }
        else {
            getVisitEventExceptionLogger().info(String.format("CALLPREFERENCES FOUND FOR THIS BE_ID = %s ", beId));
        }
        return callPreference;
    }
    public String createCallPreferencesStaffFieldForVVRequest(String beId) throws SandataRuntimeException {

        String vvField = null;
        CallPrefKV callPreferenceByBe = createCallPreferencesRequest(beId);
        CallPreferences callPref = callPreferenceByBe.getKeyValue();
        vvField = callPref.getVv_id();
        if(vvField == null) {
            getVisitEventExceptionLogger().info(String.format("ALERT MISSING CallPreferences TENANT_KEY_CONF_VAL = null THERE IS NO AGENCY WITH BE_ID = %s SETUP WITH CALL PREFERENCES KEY", beId));
        }
        else {
            getVisitEventExceptionLogger().info(String.format("CALLPREFERENCES VV_ID = %s FOR THIS BE_ID = %s ",vvField, beId));
        }
        return vvField;
    }
    public void createAgencyIdRequest(Exchange exchange) throws SandataRuntimeException {
        Message in = exchange.getIn();
        String dnis = (String)in.getBody(String.class);//("CamelCacheKey");
        String result = createAgencyIdRequest(dnis);
        in.setHeader(CamelCacheKey, dnis);
        in.setHeader(CALL_MATCHING_KEY, dnis );
        in.setBody(result);//be_id
        exchange.setIn(in);
   }
    public String createAgencyIdRequest(String dnis) throws SandataRuntimeException {
        String agencyId = null;
        getVisitEventSqlExceptionLogger().info("Started logging to new VISIT_EVENT_SQL_EXCEPTION_LOGGER appender!  In createAgencyIdRequest");
        getVisitEventExceptionLogger().info("Started logging to new VISIT_EVENT_EXCEPTION_LOGGER appender!  In createAgencyIdRequest");
        String key = "MDW_DNIS_LIST_FOR_AGENCY_ID";
        String errMsg = String.format("ALERT NO AGENCY SETUP IN DATABASE MISSING KEY_NAME = %s IN Table = METADATA.APP_TENANT_KEY_CONF", key);
        List<String> jsonStrings = getKeyConfigurationValByKey(key, null, true, errMsg);
        Iterator<String> jsonIteratorString = jsonStrings.iterator();
        while(jsonIteratorString.hasNext() && agencyId == null) {
            String jsonString = jsonIteratorString.next();
            getVisitEventExceptionLogger().info(jsonString);
            Gson gson = new Gson();
            DnisList dnisListByBe = gson.fromJson(jsonString, DnisList.class );
            List<String> dnisList = dnisListByBe.getKeyValue();

            for (String dnisItem : dnisList) {
                if (dnisItem.equals(dnis)) {
                    agencyId = dnisListByBe.getBusineEntityId();
                    break;
                }
            }
        }
        if(agencyId == null) {
            getVisitEventExceptionLogger().info(String.format("ALERT CHECKING KEY = %s :: MISSING DNIS TENANT_KEY_CONF_VAL=null THERE IS NO AGENCY SETUP FOR THIS DNIS=%s ",
                    key, dnis));
        }
        else {
            getVisitEventExceptionLogger().info(String.format("TENANT_KEY_CONF_VAL=%s FOR THIS DNIS=%s ",agencyId, dnis));
        }
        return agencyId;
    }

    private class CallPrefKV implements Serializable {
        private static final long serialVersionUID = 1L;
        private String busineEntityId;
        private CallPreferences keyValue;
        public CallPrefKV(String be_id, CallPreferences callPreferences) {
            this.busineEntityId = be_id;
            this.keyValue = callPreferences;
        }
        public String getBusineEntityId() {
            return busineEntityId;
        }

        public void setBusineEntityId(String busineEntityId) {
            this.busineEntityId = busineEntityId;
        }

        public CallPreferences getKeyValue() {
            return keyValue;
        }

        public void setKeyValue(CallPreferences keyValue) {
            this.keyValue = keyValue;
        }
    }
    private class DnisList implements Serializable {
        private static final long serialVersionUID = 1L;
        private String busineEntityId;
        private List<String> keyValue;

        public DnisList(String be_id, ArrayList keyValue) {
            this.busineEntityId = be_id;
            this.keyValue = keyValue;
        }
        public String getBusineEntityId() {
            return busineEntityId;
        }

        public void setBusineEntityId(String busineEntityId) {
            this.busineEntityId = busineEntityId;
        }

        public List<String> getKeyValue() {
            return keyValue;
        }

        public void setKeyValue(List<String> keyValue) {
            this.keyValue = keyValue;
        }
    }
   private List<String> getKeyConfigurationValByKey(String key, String be_id, boolean unknownBeId, String errMsgOnKeyMissing) {
       List<String> values = new ArrayList<>();
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       int index = 1;
       try {
           connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
           connection.setAutoCommit(false);
           String sql = String.format("SELECT T1.BE_ID, T2.TENANT_KEY_CONF_VAL FROM METADATA.APP_TENANT_BE_XWALK T1" +
                   " JOIN METADATA.APP_TENANT_KEY_CONF T2 ON T1.APP_TENANT_SK = T2.APP_TENANT_SK " +
                   " WHERE UPPER(T2.KEY_NAME) = '%s'", key);
           if(unknownBeId) {
               preparedStatement = connection.prepareStatement(sql);
           }
           else
           {
               sql = String.format("SELECT T1.BE_ID, T2.TENANT_KEY_CONF_VAL FROM METADATA.APP_TENANT_BE_XWALK T1" +
                       " JOIN METADATA.APP_TENANT_KEY_CONF T2 ON T1.APP_TENANT_SK = T2.APP_TENANT_SK " +
                       " WHERE UPPER(T2.KEY_NAME) = '%s' AND T1.BE_ID = ? ", key);
               preparedStatement = connection.prepareStatement(sql);
               preparedStatement.setString(index++, be_id);
           }
           resultSet = preparedStatement.executeQuery();

           if(!resultSet.isBeforeFirst()) {
               getVisitEventExceptionLogger().info(errMsgOnKeyMissing);
           }
           else {
           while(resultSet.next()) {
               String jsonString = "{\"busineEntityId\":\"" + resultSet.getString("BE_ID") + "\", \"keyValue\": " + resultSet.getString("TENANT_KEY_CONF_VAL") + " }";
               values.add(jsonString);
           }}

       } catch (Exception e) {

           // Rollback
           if (connection != null) {

               try {
                   connection.rollback();
               } catch (SQLException sqle) {
                   sqle.printStackTrace();
                   getVisitEventSqlExceptionLogger().info(sqle.getMessage());
               }
           }

           String errMsg = String.format("%s: %s",
                   e.getClass().getName(), e.getMessage());

           getVisitEventExceptionLogger().info(errMsg);

       } finally {

           // Close the ResultSet
           if (resultSet != null) {
               try {
                   resultSet.close();
               } catch (SQLException sqle) {
                   sqle.printStackTrace();
                   getVisitEventSqlExceptionLogger().info(sqle.getMessage());
               }

           }

           // Close the statement
           if (preparedStatement != null) {
               try {
                   preparedStatement.close();
               } catch (SQLException sqle) {
                   sqle.printStackTrace();
                   getVisitEventSqlExceptionLogger().info(sqle.getMessage());
               }
           }

           // Close the connection
           if (connection != null) {
               try {
                   connection.close();
               } catch (SQLException sqle) {
                   sqle.printStackTrace();
                   getVisitEventSqlExceptionLogger().info(sqle.getMessage());
               }
           }
           return values;
       }

    }

    public void doVisitExceptionsCheck(Exchange exchange) {
        Message in = exchange.getIn();
        Date from;
        Date to;
        List<VisitFact> visits = new ArrayList();
        if (in.getBody() instanceof ArrayList) {
            ArrayList a = in.getBody(ArrayList.class);
            if(a != null && a.size()  == 2) {
                if(a.get(0) instanceof Date) {
                    from = (Date)a.get(0);
                    if(a.get(1) instanceof Date) {
                        to = (Date)a.get(1);
                        List<VisitFact> list = visitExceptions(from, to);
                        if(list.size() > 0) {
                            visits.addAll(list);
                            String msg = String.format("doVisitsExceptionChecker returning %d visits to recheck.", visits.size());
                            getVisitEventExceptionLogger().info(msg);
                        }
                    }
                }
            }
            /*if(visits.size() > 0) {
                visits = getTasksForExceptionCheck(visits);
            }*/
            in.setBody(visits);
        }
    }

    private List<VisitFact> getTasksForExceptionCheck(List<VisitFact> visitFacts) {
        for(VisitFact visitFact : visitFacts) {
            visitFact.addTaskList(getTasksForVisit(visitFact.getVisit().getVisitSK()));
        }
        return visitFacts;
    }

    private List<VisitTaskList> getTasksForVisit(BigInteger visitSK) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

        List<VisitTaskList> tasks = new ArrayList();
        int index = 1;
        try {
            connection = getOracleConnection();
            connection.setAutoCommit(false);
            String sql = "SELECT VISIT_SK, BE_ID, BE_TASK_ID, CRITICAL_TASK_IND FROM VISIT_TASK_LST WHERE VISIT_SK = ? ";
            preparedStatement = connection.prepareStatement(sql);
            //DECIDE GOOD VALUE FOR DAYS BACK FOR DEMO PURPOSES WE WILL USE BEGINNING OF MONTH
            preparedStatement.setInt(index++, visitSK.intValue());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                VisitTaskList visitTaskList = new VisitTaskList();
                visitTaskList.setBusinessEntityID(resultSet.getString("BE_ID"));
                visitTaskList.setVisitSK(BigInteger.valueOf(resultSet.getLong("VISIT_SK")));
                visitTaskList.setBusinessEntityTaskID(resultSet.getString("BE_TASK_ID"));
                int criticalTaskIndicator = resultSet.getInt("CRITICAL_TASK_IND");
                if(criticalTaskIndicator > 0) {
                    visitTaskList.setCriticalTaskIndicator(true);
                }
                else
                {
                    visitTaskList.setCriticalTaskIndicator(false);
                }
                tasks.add(visitTaskList);
            }

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try { connection.rollback();} catch (SQLException sqle) {sqle.printStackTrace();getVisitEventSqlExceptionLogger().info(sqle.getMessage());}
                String errMsg = String.format("%s: %s",e.getClass().getName(), e.getMessage());
                getVisitEventExceptionLogger().info(errMsg);}
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }

            }
            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }
            return tasks;
        }
    }

    public List<VisitFact> visitExceptions(Date from, Date to) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

        List<VisitFact> visits = new ArrayList();
        final int fetchSize = 100;
        int index = 1;
        int count = 0;
        int fetch = 0;
        boolean threwException = false;
        int maxRows = 0;
        String sql;
        List <BigInteger> clearVisits = new ArrayList<>();
        try {
            connection = getOracleConnection();
            connection.setAutoCommit(false);

            sql = "SELECT V1.VISIT_SK, V1.STAFF_ID, V1.PT_ID, V1.REC_UPDATE_TMSTP, V1.BE_ID,V1.SCHED_EVNT_SK,V1.VISIT_ACT_START_TMSTP, V1.VISIT_ACT_END_TMSTP, " +
                    "V1.VISIT_ADJ_START_TMSTP, V1.VISIT_ADJ_END_TMSTP, V2.BE_TASK_ID, V2.CRITICAL_TASK_IND " +
            "FROM ( SELECT VISIT_SK, STAFF_ID, PT_ID, REC_UPDATE_TMSTP,BE_ID, SCHED_EVNT_SK, VISIT_ACT_START_TMSTP, " +
                    "VISIT_ACT_END_TMSTP, VISIT_ADJ_START_TMSTP, VISIT_ADJ_END_TMSTP FROM VISIT ) V1 " +
                    "LEFT JOIN ( SELECT VISIT_SK, BE_ID, BE_TASK_ID, MAX(CRITICAL_TASK_IND) AS CRITICAL_TASK_IND " +
                    "FROM VISIT_TASK_LST GROUP BY VISIT_SK, BE_ID, BE_TASK_ID) V2 ON V1.VISIT_SK = V2.VISIT_SK " +
                    "WHERE REC_UPDATE_TMSTP BETWEEN TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') ";
            preparedStatement = connection.prepareStatement(sql);
            //DECIDE GOOD VALUE FOR DAYS BACK FOR DEMO PURPOSES WE WILL USE BEGINNING OF MONTH

            preparedStatement.setString(index++, sdf.format(from));
            preparedStatement.setString(index++, sdf.format(to));
            preparedStatement.setFetchSize(fetchSize);
            maxRows = preparedStatement.getMaxRows();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count++;
                Visit visit = new Visit();
                BigInteger visitSk = BigInteger.valueOf(resultSet.getLong("VISIT_SK"));
                visit.setVisitSK(visitSk);
                clearVisits.add(visitSk);
                String staffId, ptId;
                staffId = resultSet.getString("STAFF_ID");
                ptId = resultSet.getString("PT_ID");
                visit.setStaffID(staffId);
                visit.setPatientID(ptId);
                BigInteger schedEvntSk = null;
                Long value = resultSet.getLong("SCHED_EVNT_SK");
                if(value != null) {
                    schedEvntSk = BigInteger.valueOf(value);
                }
                visit.setScheduleEventSK(schedEvntSk);
                String bsnEntId = resultSet.getString("BE_ID");
                visit.setBusinessEntityID(bsnEntId);
                visit.setVisitActualStartTimestamp(resultSet.getTimestamp("VISIT_ACT_START_TMSTP"));
                visit.setVisitActualEndTimestamp(resultSet.getTimestamp("VISIT_ACT_END_TMSTP"));
                visit.setVisitAdjustedStartTimestamp(resultSet.getTimestamp("VISIT_ADJ_START_TMSTP"));
                visit.setVisitAdjustedEndTimestamp(resultSet.getTimestamp("VISIT_ADJ_END_TMSTP"));
                VisitFact visitFact = new VisitFact(visit);
                visitFact.setState((State.LOADED));
                VisitTaskList visitTaskList = null;
                String beTaskId = resultSet.getString("BE_TASK_ID");
                if(beTaskId != null) {
                    visitTaskList = new VisitTaskList();
                    visitTaskList.setBusinessEntityID(bsnEntId);
                    visitTaskList.setBusinessEntityTaskID(beTaskId);
                    Object o = resultSet.getInt("CRITICAL_TASK_IND");
                    int criticalTaskInd = 0;
                    boolean critTskInd = false;
                    if (o != null) {
                        criticalTaskInd = Integer.valueOf(o.toString());
                        if (criticalTaskInd > 0) {
                            critTskInd = true;
                        }
                    }
                    visitTaskList.setCriticalTaskIndicator(critTskInd);
                    visitFact.getTaskList().add(visitTaskList);
                }
                visits.add(visitFact);
                if(count >= fetchSize) {
                    fetch++;
                    count = 0;
                    AppContext.getContext().createProducerTemplate().sendBody("activemq:queue:CEP_CLEAR_VISIT_EXCEPTIONS_ARRAY_REQUEST", clearVisits);
                    Thread.sleep(500);
                    AppContext.getContext().createProducerTemplate().sendBody("activemq:queue:VisitExceptionsCheckResponseArrayTemplate", visits);
                    visits.clear();
                    clearVisits.clear();
                }
            }
        } catch (Exception e) {
            threwException = true;
            String infoString = String.format("ERROR::EXCEPTION CAUGHT :: vistExceptions(%s, %s) while loop executed %d fetches before throwing " +
                            "exception.  Exception message was %s, maxRows = %d ",
            from.toString(), to.toString(), fetch, e.getLocalizedMessage(), maxRows);
            getVisitEventExceptionLogger().info(infoString);
            // Rollback
            if (connection != null) {

                try { connection.rollback();} catch (SQLException sqle) {sqle.printStackTrace();getVisitEventSqlExceptionLogger().info(sqle.getMessage());}
                String errMsg = String.format("%s: %s",e.getClass().getName(), e.getMessage());
                getVisitEventExceptionLogger().info(errMsg);}
        } finally {
            if (resultSet != null) {
                try {resultSet.close();} catch (SQLException sqle) {sqle.printStackTrace();getVisitEventSqlExceptionLogger().info(sqle.getMessage());}

            }
            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }
            if(!threwException) {
                String infoString = String.format("SUCCESS::vistExceptions(%s, %s) completed:: while loop executed %d fetches plus %d records:  Total = %d records",
                        from.toString(), to.toString(), fetch, count, (fetch * fetchSize) + count);
                getVisitEventExceptionLogger().info(infoString);

            }
            return visits;
        }
    }
    public void doScheduledVisitExceptionsCheck(Exchange exchange) {
        //only return scheduledvisitexceptions NOSHOW, LATE INCALL EARLY OUTCALL
        Message in = exchange.getIn();
        Date from;
        Date to;
        List<VisitAndSchedule> visitAndSchedules = new ArrayList();
        if (in.getBody() instanceof ArrayList) {
            ArrayList a = in.getBody(ArrayList.class);
            if(a != null && a.size()  == 2) {
                if(a.get(0) instanceof Date) {
                    from = (Date)a.get(0);
                    if(a.get(1) instanceof Date) {
                        to = (Date)a.get(1);
                        visitAndSchedules.addAll(scheduleAndVisitExceptions(from, to));
                        String msg = String.format("doScheduledVisitsExceptionChecker returning %d scheduled visits to recheck.", visitAndSchedules.size());
                        getVisitEventExceptionLogger().info(msg);
                        in.setBody(visitAndSchedules);
                    }
                }
            }
        }
    }

   public List<VisitAndSchedule> scheduleAndVisitExceptions(Date from, Date to) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        boolean threwException = false;
        List<VisitAndSchedule> visitsAndSchedules = new ArrayList();
        List<BigInteger> clearVisits = new ArrayList();
        int count = 0;
        int fetch = 0;
       int fetchSize = 100;
        int index = 1;
        int maxRows = 0;
        try {
            connection = getOracleConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT V.VISIT_SK, V.REC_UPDATE_TMSTP,V.BE_ID,V.SCHED_EVNT_SK,V.VISIT_ACT_START_TMSTP, V.VISIT_ACT_END_TMSTP, V.VISIT_ADJ_START_TMSTP, V.VISIT_ADJ_END_TMSTP, " +
                    "S.SCHED_EVNT_START_DTIME AS START_TIME, S.SCHED_EVNT_END_DTIME AS END_TIME, S.SCHED_EVNT_MANUAL_OVERRIDE_IND AS OVERRIDE FROM VISIT V JOIN SCHED_EVNT S " +
                    "ON V.SCHED_EVNT_SK = S.SCHED_EVNT_SK " +
                    "WHERE V.SCHED_EVNT_SK IS NOT NULL AND V.REC_UPDATE_TMSTP BETWEEN TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')";
            preparedStatement = connection.prepareStatement(sql);
            //DECIDE GOOD VALUE FOR DAYS BACK FOR DEMO PURPOSES WE WILL USE BEGINNING OF MONTH
            preparedStatement.setString(index++, sdf.format(from));
            preparedStatement.setString(index++, sdf.format(to));

/*            String sql = "SELECT V.VISIT_SK, V.REC_UPDATE_TMSTP,V.BE_ID,V.SCHED_EVNT_SK,V.VISIT_ACT_START_TMSTP, V.VISIT_ACT_END_TMSTP, V.VISIT_ADJ_START_TMSTP, V.VISIT_ADJ_END_TMSTP, " +
                    "S.SCHED_EVNT_START_DTIME AS START_TIME, S.SCHED_EVNT_END_DTIME AS END_TIME, S.SCHED_EVNT_MANUAL_OVERRIDE_IND AS OVERRIDE FROM VISIT V JOIN SCHED_EVNT S " +
                    "ON V.SCHED_EVNT_SK = S.SCHED_EVNT_SK " +
                    "WHERE V.SCHED_EVNT_SK IS NOT NULL AND V.VISIT_SK = 754481";
            preparedStatement = connection.prepareStatement(sql);
*/
            preparedStatement.setFetchSize(fetchSize);
            maxRows = preparedStatement.getMaxRows();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                try {
                    count++;
                    Visit visit = new Visit();
                    BigInteger visitSk = BigInteger.valueOf(resultSet.getLong("VISIT_SK"));
                    visit.setVisitSK(visitSk);
                    BigInteger schedEvntSk = BigInteger.valueOf(resultSet.getLong("SCHED_EVNT_SK"));
                    visit.setScheduleEventSK(schedEvntSk);
                    String bsnEntId = resultSet.getString("BE_ID");
                    visit.setBusinessEntityID(bsnEntId);
                    visit.setVisitActualStartTimestamp(resultSet.getTimestamp("VISIT_ACT_START_TMSTP"));
                    visit.setVisitActualEndTimestamp(resultSet.getTimestamp("VISIT_ACT_END_TMSTP"));
                    visit.setVisitAdjustedStartTimestamp(resultSet.getTimestamp("VISIT_ADJ_START_TMSTP"));
                    visit.setVisitAdjustedEndTimestamp(resultSet.getTimestamp("VISIT_ADJ_END_TMSTP"));
                    VisitFact visitFact = new VisitFact(visit);
                    visitFact.setState((State.AGG_WAITING_FOR_RESPONSE));

                    Date startTime = resultSet.getTimestamp("START_TIME");
                    Date endTime = resultSet.getTimestamp("END_TIME");
                    if (startTime == null || endTime == null || visit.getVisitAdjustedEndTimestamp() != null || visit.getVisitAdjustedStartTimestamp() != null) {
                        String msg = String.format("Schedules cant have null start or end times or vist was adjusted so clear and do not update exceptions. sched_evnt_sK = %d", schedEvntSk.intValue());
                        getVisitEventExceptionLogger().info(msg);

                    } else {
                        com.sandata.lab.rules.call.model.Schedule schedule = new com.sandata.lab.rules.call.model.Schedule(startTime, endTime);
                        ScheduleEventExt schedExt = new ScheduleEventExt();
                        schedExt.setBusinessEntityID(bsnEntId);
                        schedExt.setScheduleEventStartDatetime(startTime);
                        schedExt.setScheduleEventEndDatetime(endTime);
                        schedule.getScheduleEventExt().setScheduleEventSK(schedEvntSk);
                        schedule.getVisit().add(visit);
                        schedule.setState(State.LOADED);
                        VisitAndSchedule visitAndSchedule = new VisitAndSchedule();
                        visitAndSchedule.setVisit(visitFact);
                        visitAndSchedule.setScheduleEventSk(schedEvntSk.toString());
                        VisitAndSchedule scheduleAndVisit = new VisitAndSchedule();
                        scheduleAndVisit.setSchedule(schedule);
                        scheduleAndVisit.setScheduleEventSk(schedEvntSk.toString());
                        //clearExceptionsByVisitSk(visitSk, true);
                        visitsAndSchedules.add(visitAndSchedule);
                        visitsAndSchedules.add(scheduleAndVisit);
                    }
                    clearVisits.add(visitSk);
                    if(count >= fetchSize) {
                        count = 0;
                        fetch++;
                        AppContext.getContext().createProducerTemplate().sendBody("activemq:queue:CEP_CLEAR_SCHEDULED_VISIT_EXCEPTIONS_ARRAY_REQUEST", clearVisits);
                        Thread.sleep(500);
                        AppContext.getContext().createProducerTemplate().sendBody("activemq:queue:ScheduledVisitExceptionsCheckResponseArrayTemplate", visitsAndSchedules);
                        visitsAndSchedules.clear();
                        clearVisits.clear();
                    }
                }
                catch(Exception ex) {
                    if( resultSet.isClosed() ) {
                        throw new Exception(ex);
                    }
                    getVisitEventExceptionLogger().info(ex.getLocalizedMessage());
                }

            }



        } catch (Exception e) {
            threwException = true;
            String infoString = String.format("ERROR::EXCEPTION CAUGHT :: scheduleAndVisitExceptions(%s, %s) while loop executed %d fetches before throwing " +
                            "exception.  Exception message was %s, maxRows = %d ",
                    from.toString(), to.toString(), fetch, e.getLocalizedMessage(), maxRows);
            getVisitEventExceptionLogger().info(infoString);

            // Rollback
            if (connection != null) {

            try { connection.rollback();} catch (SQLException sqle) {sqle.printStackTrace();getVisitEventSqlExceptionLogger().info(sqle.getMessage());}
            String errMsg = String.format("%s: %s",e.getClass().getName(), e.getMessage());
            getVisitEventExceptionLogger().info(errMsg);}
        } finally {
            if (resultSet != null) {
                try {resultSet.close();} catch (SQLException sqle) {sqle.printStackTrace();getVisitEventSqlExceptionLogger().info(sqle.getMessage());}

            }
            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }
            for(BigInteger visitSk : clearVisits) {
                clearExceptionsByVisitSk(visitSk, true);
            }
            if(!threwException) {
                String infoString = String.format("SUCCESS::scheduleAndVisitExceptions(%s, %s) completed:: while loop executed %d fetches plus %d records for total = %d records",
                        from.toString(), to.toString(), fetch, count, (fetch * fetchSize) + count);
                getVisitEventExceptionLogger().info(infoString);

            }
            return visitsAndSchedules;
        }
    }
    private int clearExceptionsByVisitSk(BigInteger visitSk) {
        return clearExceptionsByVisitSk(visitSk, false);
    }
    private int clearExceptionsByVisitSk(BigInteger visitSK, boolean scheduledVisitsOnly) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        int result = 0;
        try {
            connection = getOracleConnection();
            connection.setAutoCommit(false);
            
            ArrayDescriptor arrayDescriptor = ArrayDescriptor.createDescriptor(ConnectionType.COREDATA + ".NUMBER_ARRAY", connection);
            int[] exceptionIds = new int[6];
            exceptionIds[0] = State.VISIT_NO_SHOW.getVisitException().intValue();
            exceptionIds[1] = State.ACTUAL_HOURS_EXCEED_SCHEDULED_HOURS.getVisitException().intValue();
            exceptionIds[2] = State.UNMATCHED_PAYROLL_AND_SCHEDULED_HOURS.getVisitException().intValue();
            exceptionIds[3] = State.VISIT_LATE_IN_CALL.getVisitException().intValue();
            exceptionIds[4] = State.VISIT_EARLY_OUT_CALL.getVisitException().intValue();
            exceptionIds[5] = State.VISIT_SHORT.getVisitException().intValue();
            ARRAY execeptionIdsArray = new ARRAY(arrayDescriptor, connection, exceptionIds);
            
            String callMethod = "{?=call PKG_VISIT_UTIL.CLEAR_EXCEPTIONS_BY_VISIT_SK(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            int index = 2;
            callableStatement.setBigDecimal(index++, BigDecimal.valueOf(visitSK.longValue()));
            callableStatement.setInt(index++, scheduledVisitsOnly ? 1 : 0);
            callableStatement.setArray(index++, execeptionIdsArray);
            
            callableStatement.execute();
            result = callableStatement.getInt(1);
            connection.commit();

            return result;
        }  catch (Exception e) {
            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            String errMsg = String.format("%s: %s", e.getClass().getName(), e.getMessage());
            getVisitEventExceptionLogger().info(errMsg);
            throw new SandataRuntimeException(errMsg ,e);

        } finally {


            // Close the statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();

                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }

            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    getVisitEventSqlExceptionLogger().info(sqle.getMessage());
                }
            }
        }
    }

    private Date firstDayCurrentMonth() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

}
