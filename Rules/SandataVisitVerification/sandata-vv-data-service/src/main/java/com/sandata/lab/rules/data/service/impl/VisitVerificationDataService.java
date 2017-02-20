package com.sandata.lab.rules.data.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.sandata.lab.rules.vv.fact.*;
import oracle.jdbc.internal.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import org.apache.camel.Exchange;
import org.apache.camel.PropertyInject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.agency.AgencyVisitVerificationSettings;
import com.sandata.lab.data.model.agency.PatientVisitVerificationIdentifier;
import com.sandata.lab.data.model.agency.StaffVisitVerificationIdentifier;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.AuthorizationQualifier;
import com.sandata.lab.data.model.dl.model.ExceptionLookup;
import com.sandata.lab.data.model.dl.model.Patient;
import com.sandata.lab.data.model.dl.model.PatientContactAddress;
import com.sandata.lab.data.model.dl.model.ScheduleEvent;
import com.sandata.lab.data.model.dl.model.Staff;
import com.sandata.lab.data.model.dl.model.Task;
import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.data.model.dl.model.VisitAuthorization;
import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.data.model.dl.model.VisitEventTypeCode;
import com.sandata.lab.data.model.dl.model.VisitException;
import com.sandata.lab.data.model.dl.model.VisitTaskList;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rules.data.service.utils.CommonUtils;
import com.sandata.lab.rules.vv.log.utils.LoggingUtils;
import com.sandata.lab.rules.vv.model.CallPreferences;

/**
 * <p>Database services for visit verification bundles.</p>
 *
 * @author jasonscott
 */
public class VisitVerificationDataService {

    private Logger visitVerificationLogger = LoggerFactory.getLogger(VisitVerificationDataService.class);

    @PropertyInject("{{schedule.start.threshold.visit.exceptions}}")
    private long scheduleStartThresholdForVisitExceptions = 60000L;

    protected ConnectionPoolDataService connectionPoolDataService;

    public Connection getOracleConnection() throws SandataRuntimeException {
        return connectionPoolDataService.getConnection();
    }

    public Connection getOracleConnection(ConnectionType connectionType) throws SandataRuntimeException {
        return connectionPoolDataService.getConnection(connectionType);
    }
    
    public ConnectionPoolDataService getConnectionPoolDataService() {
        return connectionPoolDataService;
    }

    public void setConnectionPoolDataService(ConnectionPoolDataService connectionPoolDataService) {
        this.connectionPoolDataService = connectionPoolDataService;
    }

    public void getBsnEntIdDnisMapping(Exchange exchange) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Map<String, String> bsnEntDnisMap = new LinkedHashMap<>();

        try {
            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(true);

            String sql = "SELECT T1.BE_ID, T2.TENANT_KEY_CONF_VAL FROM APP_TENANT_BE_XWALK T1" +
                "  INNER JOIN APP_TENANT_KEY_CONF T2" +
                "    ON T1.APP_TENANT_SK=T2.APP_TENANT_SK" +
                " WHERE T2.KEY_NAME='MDW_DNIS_LIST_FOR_AGENCY_ID'";

            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String bsnEntId = resultSet.getString("BE_ID");
                String jsonDnisList = resultSet.getString("TENANT_KEY_CONF_VAL");
                ArrayList<String> dnisList = (ArrayList<String>) GSONProvider.FromJson(jsonDnisList, ArrayList.class);
                for (String dnis : dnisList) {
                    //TODO: what happen if same DNIS for different BE_ID (in case of bad data/wrong config)
                    bsnEntDnisMap.put(dnis, bsnEntId);
                }
            }

            exchange.getIn().setBody(bsnEntDnisMap);

        } catch (Exception e) {
            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = String.format("%s: %s",e.getClass().getName(), e.getMessage());
            visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
               		, this.getClass(), "getBsnEntIdDnisMapping", errMsg));
            throw new SandataRuntimeException(errMsg,e);

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

    /**
     * Get scheduleEvent by BE_ID, start and end schedule date time
     *
     * @param exchange
     */
    public void getScheduleEventEntities(Exchange exchange) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //List<ScheduleEvent> scheduleEventList = new ArrayList<>();

        String bsnEntId = (String) exchange.getIn().getHeader("bsnEntId");
        String lowerLimitDateString = (String) exchange.getIn().getHeader("lowerLimitDateString");
        String upperLimitDateString = (String) exchange.getIn().getHeader("upperLimitDateString");

        //default value
        String staffVisitVerificationId = StaffVisitVerificationIdentifier.STAFF_ID.toString();
        String patientVisitVerificationId = PatientVisitVerificationIdentifier.PATIENT_ID.toString();

        try {
            AgencyVisitVerificationSettings agencyVisitVerificationSettings = getBusinessEntityVisitVerificationSettings(bsnEntId);

            if (agencyVisitVerificationSettings == null) {
                throw new SandataRuntimeException(String.format("Failed to lookup visit verification settings for business entity ID %s!", bsnEntId));
            }

            PatientVisitVerificationIdentifier patientVisitVerificationIdentifier = agencyVisitVerificationSettings.getPatientVisitVerificationIdentifier();
            StaffVisitVerificationIdentifier staffVisitVerificationIdentifier = agencyVisitVerificationSettings.getStaffVisitVerificationIdentifier();

            if (staffVisitVerificationIdentifier != null) {
                staffVisitVerificationId = staffVisitVerificationIdentifier.toString();
            }

            if (patientVisitVerificationIdentifier != null) {
                patientVisitVerificationId = patientVisitVerificationIdentifier.toString();
            }

            connection = connectionPoolDataService.getConnection(ConnectionType.COREDATA);
            connection.setAutoCommit(true);

            //StringBuilder sqlStringBuilder = new StringBuilder();
            StringBuilder conditions = new StringBuilder();
            List<String> parameterList = new ArrayList<>();

            if (bsnEntId == null) {
                throw new SandataRuntimeException("Parameter bsnEntId required!");
            }

            //add 3 times of BE_ID for
            parameterList.add(bsnEntId);


            if (lowerLimitDateString == null
                || lowerLimitDateString.isEmpty()) {
                throw new SandataRuntimeException("Parameter lowerLimitDateString required!");
            }

            if (upperLimitDateString == null
                || upperLimitDateString.isEmpty()) {
                throw new SandataRuntimeException("Parameter lowerLimitDateString required!");
            }

            String sql = "SELECT T4.PT_PHONE AS ANI,T2.STAFF_ID,T2.STAFF_VV_ID,T3.PT_ID,T3.PT_VV_ID," +
                " T1.SCHED_EVNT_SK,T1.SCHED_SK,T1.TZ_NAME,T1.BE_ID,T1.SCHED_EVNT_START_DTIME,T1.SCHED_EVNT_END_DTIME" +
                " FROM " +
                "  (SELECT SCHED_EVNT_SK,SCHED_SK,TZ_NAME,BE_ID,PT_ID,STAFF_ID,SCHED_EVNT_START_DTIME,SCHED_EVNT_END_DTIME," +
                "      SCHED_EVNT_ID,REC_TERM_TMSTP,CURR_REC_IND" +
                "                FROM SCHED_EVNT " +
                "                  WHERE BE_ID = ? AND UPPER(SCHED_EVNT_STATUS) <> 'CANCELLED'" +
                //TODO: should check SCHED_EVNT_STATUS ? AND UPPER(SCHED_EVNT_STATUS) <> 'CANCELLED'
                "                  %s                    " +

                "              ) T1 " +

                " LEFT JOIN " +
                "(SELECT STAFF_ID,STAFF_ID_PLACE_HOLDER AS STAFF_VV_ID,BE_ID,REC_TERM_TMSTP,CURR_REC_IND" +
                "                FROM STAFF " +
                "                  WHERE BE_ID = ?" +
                "                    AND ((TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') AND CURR_REC_IND = 1)) T2 " +
                "              ON T1.STAFF_ID=T2.STAFF_ID AND T1.BE_ID=T2.BE_ID" +

                " LEFT JOIN " +
                "  (SELECT PT_SK,PT_ID,PT_ID_PLACE_HOLDER AS PT_VV_ID,BE_ID,REC_TERM_TMSTP,CURR_REC_IND" +
                "                FROM PT" +
                "                  WHERE BE_ID = ?" +
                "                      AND PT_ID IS NOT NULL" +
                "                      AND ((TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') AND CURR_REC_IND = 1)) T3" +
                "              ON T1.PT_ID = T3.PT_ID" +
                " LEFT JOIN " +
                "  (SELECT PT_ID,PT_PHONE,REC_TERM_TMSTP,CURR_REC_IND" +
                "                FROM PT_CONT_PHONE" +
                "                  WHERE PT_PHONE_ANI_ENABLED_IND = 1" +
                "                      AND ((TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') AND CURR_REC_IND = 1)) T4" +
                "              ON T3.PT_ID = T4.PT_ID" +
                " ORDER BY SCHED_EVNT_START_DTIME,SCHED_EVNT_END_DTIME";

            sql = sql.replace("STAFF_ID_PLACE_HOLDER", staffVisitVerificationId).replace("PT_ID_PLACE_HOLDER", patientVisitVerificationId);

            conditions.append(" AND (( (SCHED_EVNT_START_DTIME >= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS'))");
            conditions.append(" AND (SCHED_EVNT_START_DTIME <= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')))");
            parameterList.add(lowerLimitDateString);
            parameterList.add(upperLimitDateString);

            conditions.append(" OR ((SCHED_EVNT_END_DTIME >= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS'))");
            conditions.append(" AND (SCHED_EVNT_END_DTIME <= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS'))) )");
            parameterList.add(lowerLimitDateString);
            parameterList.add(upperLimitDateString);

            //TODO:
           /* conditions.append(" AND TO_CHAR(SCHED_EVNT_START_DTIME, 'YYYY-MM-DD') >= ? ");
            conditions.append(" AND TO_CHAR(SCHED_EVNT_END_DTIME, 'YYYY-MM-DD') <= ? ");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String lowerDate = sdf.format(sdf.parse(lowerLimitDateString));
            parameterList.add(lowerDate);
            String udpaterDate = sdf.format(sdf.parse(upperLimitDateString));
            parameterList.add(udpaterDate);*/

            //for BE_ID
            parameterList.add(bsnEntId);
            parameterList.add(bsnEntId);

            conditions.append(" AND (CURR_REC_IND=1 AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD')='9999-12-31')");

            sql = String.format(sql, conditions.toString());

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (String parameter : parameterList) {
                preparedStatement.setString(index++, parameter);
            }

            //List<ScheduleEvent> scheduleEventList = (List<ScheduleEvent>) new DataMapper().map(resultSet, ScheduleEvent.class.getCanonicalName());
            resultSet = preparedStatement.executeQuery();

            List<ScheduleEventFact> scheduleEventList = new ArrayList<ScheduleEventFact>();
            while (resultSet.next()) {
                scheduleEventList.add(mapSchedule(resultSet));
            }

            exchange.getIn().setBody(scheduleEventList);

        } catch (Exception e) {
            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = String.format("%s: %s",e.getClass().getName(), e.getMessage());
            visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
               		, this.getClass(), "getScheduleEventEntities", errMsg));
            throw new SandataRuntimeException(errMsg,e);

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

    private ScheduleEventFact mapSchedule(ResultSet resultSet) throws SQLException {
        ScheduleEventFact scheduleEvent = new ScheduleEventFact();

        scheduleEvent.setPatientVisitVerificationId(resultSet.getString("PT_VV_ID"));
        scheduleEvent.setStaffVisitVerificationId(resultSet.getString("STAFF_VV_ID"));
        scheduleEvent.setAni(resultSet.getString("ANI"));
        scheduleEvent.setStaffID(resultSet.getString("STAFF_ID"));
        scheduleEvent.setPatientID(resultSet.getString("PT_ID"));
        scheduleEvent.setBusinessEntityID(resultSet.getString("BE_ID"));
        scheduleEvent.setTimezoneName(resultSet.getString("TZ_NAME"));
        BigDecimal schedEvntSk = resultSet.getBigDecimal("SCHED_EVNT_SK");
        if (schedEvntSk != null) {
            scheduleEvent.setScheduleEventSK(BigInteger.valueOf(schedEvntSk.longValue()));
        }
        BigDecimal schedSk = resultSet.getBigDecimal("SCHED_SK");
        if (schedSk != null) {
            scheduleEvent.setScheduleSK(BigInteger.valueOf(schedSk.longValue()));
        }

        Timestamp schedStartDtime = resultSet.getTimestamp("SCHED_EVNT_START_DTIME");
        if (schedStartDtime != null) {
            scheduleEvent.setScheduleEventStartDatetime(new java.util.Date(schedStartDtime.getTime()));
        }

        Timestamp schedEndDtime = resultSet.getTimestamp("SCHED_EVNT_END_DTIME");
        if (schedEndDtime != null) {
            scheduleEvent.setScheduleEventEndDatetime(new java.util.Date(schedEndDtime.getTime()));
        }
        return scheduleEvent;
    }


    public void getBusinessEntityCallPreferences(Exchange exchange) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        CallPreferences callPreferences = null;
        String bsnEntId = (String) exchange.getIn().getHeader("bsnEntId");

        try {
            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(true);

            if (bsnEntId == null) {
                throw new SandataRuntimeException("Parameter bsnEntId required!");
            }

            String sql = "SELECT T1.BE_ID, T2.TENANT_KEY_CONF_VAL FROM APP_TENANT_BE_XWALK T1" +
                "  INNER JOIN APP_TENANT_KEY_CONF T2" +
                "    ON T1.APP_TENANT_SK=T2.APP_TENANT_SK" +
                " WHERE T1.BE_ID=?" +
                "  AND T2.KEY_NAME='MDW_AGENCY_CALL_PREFERENCES_FOR_CALL_MATCHING'";


            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String json = resultSet.getString("TENANT_KEY_CONF_VAL");
                if (json != null) {
                    callPreferences = (CallPreferences) GSONProvider.FromJson(resultSet.getString("TENANT_KEY_CONF_VAL"), CallPreferences.class);
                }
            }

            exchange.getIn().setBody(callPreferences);

        } catch (Exception e) {
            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = String.format("%s: %s",e.getClass().getName(), e.getMessage());
            visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
               		, this.getClass(), "getBusinessEntityCallPreferences", errMsg));
            throw new SandataRuntimeException(errMsg,e);

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

    private AgencyVisitVerificationSettings getBusinessEntityVisitVerificationSettings(String bsnEntId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        AgencyVisitVerificationSettings agencyVisitVerificationSettings = null;

        try {
            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(true);

            if (bsnEntId == null) {
                throw new SandataRuntimeException("Parameter bsnEntId required!");
            }

            String sql = "SELECT T1.BE_ID, T2.TENANT_KEY_CONF_VAL FROM APP_TENANT_BE_XWALK T1" +
                "  INNER JOIN APP_TENANT_KEY_CONF T2" +
                "    ON T1.APP_TENANT_SK=T2.APP_TENANT_SK" +
                " WHERE T1.BE_ID=?" +
                "  AND T2.KEY_NAME='AGNCY_MGMT_PAT_STAFF_VIS_VER'";


            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String json = resultSet.getString("TENANT_KEY_CONF_VAL");
                if (json != null) {
                    agencyVisitVerificationSettings = (AgencyVisitVerificationSettings) GSONProvider.FromJson(resultSet.getString("TENANT_KEY_CONF_VAL"), AgencyVisitVerificationSettings.class);
                    agencyVisitVerificationSettings.setBusinessEntityId(bsnEntId);
                }
            }

            return agencyVisitVerificationSettings;

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = String.format("%s: %s",e.getClass().getName(), e.getMessage());
            visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
               		, this.getClass(), "getBusinessEntityVisitVerificationSettings", errMsg));
            throw new SandataRuntimeException(errMsg,e);

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


    /**
     * This methdd used to get all the staffs by BE_ID
     *
     * @param exchange with BE_ID in the header
     */
    public void getStaffEntities(Exchange exchange) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String bsnEntId = (String) exchange.getIn().getHeader("bsnEntId");

        String staffIds = (String) exchange.getIn().getHeader("staffIds");

        String staffVisitVerificationId = StaffVisitVerificationIdentifier.STAFF_ID.toString();

        try {
            connection = connectionPoolDataService.getConnection(ConnectionType.COREDATA);
            connection.setAutoCommit(true);

            if (bsnEntId == null) {
                throw new SandataRuntimeException("Parameter bsnEntId required!");
            }

            if (staffIds == null || staffIds.isEmpty()) {

                String errorMsg = "There's no staff ids found from visit events";
                visitVerificationLogger.error(errorMsg);
                throw new SandataRuntimeException(errorMsg);
            }


            AgencyVisitVerificationSettings agencyVisitVerificationSettings = getBusinessEntityVisitVerificationSettings(bsnEntId);

            if (agencyVisitVerificationSettings == null) {
                throw new SandataRuntimeException(String.format("Failed to lookup visit verification settings for business entity ID %s!", bsnEntId));
            }

            //PatientVisitVerificationIdentifier patientVisitVerificationIdentifier = agencyVisitVerificationSettings.getPatientVisitVerificationIdentifier();
            StaffVisitVerificationIdentifier staffVisitVerificationIdentifier = agencyVisitVerificationSettings.getStaffVisitVerificationIdentifier();

            if (staffVisitVerificationIdentifier != null) {
                staffVisitVerificationId = staffVisitVerificationIdentifier.toString();
            }


            String sql = "SELECT STAFF_ID , STAFF_ID_PLACE_HOLDER AS STAFF_VV_ID FROM STAFF WHERE BE_ID=?" +
                " AND (CURR_REC_IND=1 AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD')='9999-12-31') %s";

            sql = sql.replace("STAFF_ID_PLACE_HOLDER",staffVisitVerificationId);
            sql = String.format(sql, staffIds);

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            //List<Staff> staffList = (List<Staff>) new DataMapper().map(resultSet, Staff.class.getCanonicalName());
            List<StaffFact> staffFactList = new ArrayList<StaffFact>();
            StaffFact staffFact = null;
            while (resultSet.next()) {
                staffFact = new StaffFact();
                staffFact.setStaffId(resultSet.getString("STAFF_ID"));
                staffFact.setStaffVisitVerificationId("STAFF_VV_ID");
                staffFactList.add(staffFact);
            }

            exchange.getIn().setBody(staffFactList);

        } catch (Exception e) {
            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = String.format("%s: %s",e.getClass().getName(), e.getMessage());
            visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
               		, this.getClass(), "getStaffEntities", errMsg));
            throw new SandataRuntimeException(errMsg,e);

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


    /**
     * This method used to get all the patient by PT_IDs
     *
     * @param exchange with BE_ID in the header
     */
    public void getPatientEntities(Exchange exchange) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<StaffFact> patientFactList = new ArrayList<StaffFact>();

        String bsnEntId = (String) exchange.getIn().getHeader("bsnEntId");

        String patientIds = (String) exchange.getIn().getHeader("patientIds");

        String aniList = (String) exchange.getIn().getHeader("aniList");

        String patientVisitVerificationId = PatientVisitVerificationIdentifier.PATIENT_ID.toString();

        try {

            AgencyVisitVerificationSettings agencyVisitVerificationSettings = getBusinessEntityVisitVerificationSettings(bsnEntId);

            if (agencyVisitVerificationSettings == null) {
                throw new SandataRuntimeException(String.format("Failed to lookup visit verification settings for business entity ID %s!", bsnEntId));
            }

            PatientVisitVerificationIdentifier patientVisitVerificationIdentifier = agencyVisitVerificationSettings.getPatientVisitVerificationIdentifier();

            if (patientVisitVerificationIdentifier != null) {
                patientVisitVerificationId = patientVisitVerificationIdentifier.toString();
            }


            if (bsnEntId == null) {
                throw new SandataRuntimeException("Parameter bsnEntId required!");
            }
            List<String> parameterList = new ArrayList<String>();
            parameterList.add(bsnEntId);
            parameterList.add(bsnEntId);

            if ((patientIds == null || patientIds.isEmpty()) && (aniList == null || aniList.isEmpty())) {
                String errorMsg = "Un-expected case , call info seems corrupted , missing both ANI and Staff ID";
                visitVerificationLogger.error(errorMsg);
                throw new SandataRuntimeException(errorMsg);
            }


            connection = connectionPoolDataService.getConnection(ConnectionType.COREDATA);
            connection.setAutoCommit(false);

            StringBuilder whereConditions = new StringBuilder();
            if (aniList != null && !aniList.isEmpty()) {

                whereConditions.append("WHERE PT_PHONE IN ( ");
                whereConditions.append(aniList);
                whereConditions.append(" )");
            }

            if (patientIds != null && !patientIds.isEmpty()) {
                if (!whereConditions.toString().isEmpty()) {
                    whereConditions.append(" OR ");
                } else {
                    whereConditions.append(" WHERE ");
                }
                whereConditions.append(" P1.PT_ID IN ( ");
                whereConditions.append(patientIds);
                whereConditions.append(" )");
            }

            String sql = "SELECT P1.PT_ID, P1.BE_ID, P1.PT_VV_ID,P2.PT_PHONE AS ANI FROM " +
                "  (SELECT PT_ID,BE_ID, PT_ID_PLACE_HOLDER AS PT_VV_ID FROM PT WHERE " +
                "    BE_ID=? " +
                "    AND (CURR_REC_IND=1 " +
                "    AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD')='9999-12-31')" +
                "  ) P1 " +
                "LEFT JOIN (" +
                "        SELECT PT_PHONE,PT_ID,BE_ID FROM PT_CONT_PHONE WHERE BE_ID=? AND PT_PHONE_ANI_ENABLED_IND=1  " +
                "        AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)" +
                "    ) P2" +
                "    ON P1.PT_ID = P2.PT_ID AND P1.BE_ID = P2.BE_ID " +
                " %s";

            sql = sql.replace("PT_ID_PLACE_HOLDER",patientVisitVerificationId);
            sql = String.format(sql, whereConditions.toString());

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (String parameter : parameterList) {
                preparedStatement.setString(index++, parameter);
            }

            resultSet = preparedStatement.executeQuery();

            List<PatientFact> patientFacts = new ArrayList<>();
            PatientFact patientFact = null;

            while (resultSet.next()) {
                patientFact = new PatientFact();
                patientFact.setPatientId(resultSet.getString("PT_ID"));
                patientFact.setPatientVisitVerificationId(resultSet.getString("PT_VV_ID"));
                patientFact.setAni(resultSet.getString("ANI"));
                patientFact.setBusinessEntId(resultSet.getString("BE_ID"));

                patientFacts.add(patientFact);
            }

            connection.commit();
            exchange.getIn().setBody(patientFacts);

        } catch (Exception e) {
            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = String.format("%s: %s",e.getClass().getName(), e.getMessage());
            visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
               		, this.getClass(), "getPatientEntities", errMsg));
            throw new SandataRuntimeException(errMsg,e);

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


    public void insertPlannedVisitEvent(Exchange exchange) {

        Connection connection = null;

        //TODO: need to confirm this case for planned visit
        //int secondCallStillValidThresholdMinutes = callInfo.getCallPreferences().getSecondCallStillValidThresholdMinutes();//24 //hours
        try {
            ConnectionType connectionType = ConnectionType.COREDATA;
            connection = connectionPoolDataService.getConnection(connectionType);
            connection.setAutoCommit(false);

            // Find Visit entity for schedEventSk.
            VisitEventFact visitEventFact = (VisitEventFact) exchange.getIn().getBody();
            if (visitEventFact == null) {
            	String errMsg = "VisitEventFact required!";
            	visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
                   		, this.getClass(), "insertPlannedVisitEvent", errMsg));
                throw new SandataRuntimeException(errMsg);
            }

            String bsnEntId = visitEventFact.getBusinessEntityId();
            if (bsnEntId == null) {
            	String errMsg = "Business entity ID required!";
            	visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
                   		, this.getClass(), "insertPlannedVisitEvent", errMsg));
                throw new SandataRuntimeException(errMsg);
            }

            ScheduleEventFact scheduleEventFact = visitEventFact.getScheduleEventFact();
            if (scheduleEventFact == null) {
            	String errMsg = "ScheduleEventFact required!";
            	visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
                   		, this.getClass(), "insertPlannedVisitEvent", errMsg));
                throw new SandataRuntimeException(errMsg);
            }

            BigInteger schedEvntSk = scheduleEventFact.getScheduleEventSK();
            if (schedEvntSk == null) {
            	String errMsg = "schedEvntSk required!";
            	visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
                   		, this.getClass(), "insertPlannedVisitEvent", errMsg));
                throw new SandataRuntimeException(errMsg);
            }

            List<Visit> visitList = getVisitBySchedEvntSk(connection, bsnEntId, schedEvntSk.longValue());
            if (visitList == null || visitList.size() != 1) {
            	String errMsg = String.format("No Visit entity found for business entity ID %s and schedule event SK %s!", bsnEntId, schedEvntSk);
            	visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
                   		, this.getClass(), "insertPlannedVisitEvent", errMsg));
            	throw new SandataRuntimeException(errMsg);
            }

            //visit from database
            Visit visit = visitList.get(0);

            //visit from call server
            VisitEvent visitEvent = visitEventFact.getVisit();

            OracleMetadata visitOracleMetaData = DataMapper.getOracleMetadata(visit);
            OracleMetadata visitEventOracleMetadata = DataMapper.getOracleMetadata(visitEvent);

            //set visit_sk to visit event
            visitEvent.setVisitSK(visit.getVisitSK());

            //checking if any existing visit event by Visit_SK
            List<VisitEvent> existingVisitEvents = getVisitEventListForVisitSk(connection, visit.getVisitSK().intValue());

            // no existing visit event in db
            if (existingVisitEvents == null
                || existingVisitEvents.isEmpty()) {
                // Revert to indicator set on new VisitEvent.
                if (visitEvent.isCallInIndicator()
                    && !visitEvent.isCallOutIndicator()) {
                    visit.setVisitActualStartTimestamp(visitEvent.getVisitEventDatetime());
                } else if (!visitEvent.isCallInIndicator()
                    && visitEvent.isCallOutIndicator()) {
                    visit.setVisitActualEndTimestamp(visitEvent.getVisitEventDatetime());
                }

            } else if (existingVisitEvents.size() == 1) {
                // Compare time of new and existing
                VisitEvent existingVisitEvent = existingVisitEvents.get(0);

                recalculateVisitTimesForVisitEvents(visit, existingVisitEvent, visitEvent);

            } else if (existingVisitEvents.size() > 1) {

                //more than 1 existing visit event and new visit event , mean that has extra call
                visitEventFact.setVisitHasExtraCall(true);
                // recalculate times.
                existingVisitEvents.add(visitEvent);
                VisitEvent[] visitEvents = new VisitEvent[existingVisitEvents.size()];
                visitEvents = existingVisitEvents.toArray(visitEvents);
                recalculateVisitTimesForVisitEvents(visit, visitEvents);
                existingVisitEvents = new LinkedList<>(Arrays.asList(visitEvents));
                existingVisitEvents.remove(visitEvent);
            }

            //additional info for visit event
            setAdditionalVisitEventInfo(visitEvent);

            // Update Visit.
            Integer result = execute(connection,
                connectionType,
                visitOracleMetaData.packageName(),
                visitOracleMetaData.updateMethod(),
                new DataMapper().map(visit));

            if (result < 0) {
            	String errMsg = String.format("Failed to update Visit entity with SK %s!", visit.getVisitSK());
            	visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
                   		, this.getClass(), "insertPlannedVisitEvent", errMsg));
                throw new SandataRuntimeException(errMsg);
            }

            // Update existing VisitEvents.
            for (VisitEvent existingVisitEvent : existingVisitEvents) {

                result = execute(connection,
                    connectionType,
                    visitEventOracleMetadata.packageName(),
                    visitEventOracleMetadata.updateMethod(),
                    new DataMapper().map(existingVisitEvent));

                if (result < 0) {
                	String errMsg = String.format("Failed to update existing VisitEvent entity with SK %s!", existingVisitEvent.getVisitSK());
                    throw new SandataRuntimeException(errMsg);
                }

            }

            // Insert new VisitEvent.
            result = execute(connection,
                connectionType,
                visitEventOracleMetadata.packageName(),
                visitEventOracleMetadata.insertMethod(),
                new DataMapper().map(visitEvent));

            if (result < 0) {
                String errMsg = String.format("Insert of VisitEvent failed with result %s!", result);
                throw new SandataRuntimeException(errMsg);
            }

            //TODO: need to prevent duplicated task for visit
            //visit task List handle
            if (visitEventFact.getTasks() != null) {
                visit.getVisitTaskList().addAll(visitEventFact.getTasks());
                setVisitTaskListInfo(visit);
                result = insertVisitTaskList(connection, connectionType, visit.getVisitTaskList());
                if (result < 0) {
                    String errMsg = String.format("Failed to insert visit task list for visit SK %s!", visit.getVisitSK());
                    throw new SandataRuntimeException(errMsg);
                }
            }

            connection.commit();
            //send visit event fact for exception handle
            visitEventFact.setParentVisit(visit);
            visitEventFact.setVisitSK(visit.getVisitSK());
            visitEventFact.setVisit(visitEvent);
            exchange.getIn().setBody(visitEventFact);
            exchange.getIn().setHeader("bsnEntId",bsnEntId);

        } catch (Exception e) {
            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = String.format("%s: %s",e.getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg,e);

        } finally {

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

    public void recalculateVisitTimesForVisitEvents(Visit visit, VisitEvent... visitEvents) {

        if (visitEvents.length < 2) {
            throw new SandataRuntimeException("Minimum number of VisitEvent required is 2!");
        }

        List<VisitEvent> visitEventList = Arrays.asList(visitEvents);
        Collections.sort(visitEventList, new Comparator<VisitEvent>() {
            @Override
            public int compare(VisitEvent o1, VisitEvent o2) {
                return o1.getVisitEventDatetime().compareTo(o2.getVisitEventDatetime());
            }
        });

        // Use first and last of list to set indicators and visit start/end times.
        VisitEvent startVisitEvent = visitEventList.get(0);
        startVisitEvent.setCallInIndicator(true);
        startVisitEvent.setCallOutIndicator(false);
        visit.setVisitActualStartTimestamp(startVisitEvent.getVisitEventDatetime());

        VisitEvent endVisitEvent = visitEventList.get(visitEventList.size() - 1);
        endVisitEvent.setCallInIndicator(false);
        endVisitEvent.setCallOutIndicator(true);
        visit.setVisitActualEndTimestamp(endVisitEvent.getVisitEventDatetime());

        // Iterate any VisitEvents in between first and last and set the IN and OUT indicators to false.
        if (visitEventList.size() > 2) {
            for (int i = 1; i < visitEventList.size() - 1; i++) {
                VisitEvent visitEvent = visitEventList.get(i);
                visitEvent.setCallInIndicator(false);
                visitEvent.setCallOutIndicator(false);
            }
        }
    }


    public void insertUnplannedVisitEvent(final Exchange exchange) {

        Connection connection = null;

        try {

            ConnectionType connectionType = ConnectionType.COREDATA;
            connection = connectionPoolDataService.getConnection(connectionType);
            connection.setAutoCommit(false);

            // Find Visit entity for schedEventSk.
            VisitEventFact callInfo = (VisitEventFact) exchange.getIn().getBody();
            if (callInfo == null) {
            	
                visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
                  		, this.getClass(), "insertUnplannedVisitEvent", "VisitEventFact required!") );
                throw new SandataRuntimeException("VisitEventFact required!");
            }

            String bsnEntId = callInfo.getBusinessEntityId();
            if (bsnEntId == null) {
            	visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
                   		, this.getClass(), "insertUnplannedVisitEvent", "Business entity ID required!") );
                throw new SandataRuntimeException("Business entity ID required!");
            }

            //check if exiting visit and visit events in db
            List<VisitFact> visitFacts = getExistingUnplannedVisit(callInfo);

            if (visitFacts == null || visitFacts.isEmpty()) {
                //no existing visit , so create new visit , visit event and visit task list
                Visit insertedVisit = createNewVisitInfo(connection, connectionType, callInfo);
                //return visit sk , or should be whole visit with full data , for exception handling
                callInfo.setParentVisit(insertedVisit);
                callInfo.setVisitSK(insertedVisit.getVisitSK());
                exchange.getIn().setBody(callInfo);
                exchange.getIn().setHeader("bsnEntId",bsnEntId);

            } else {
                // in case of there are existing visit event
                if (!isDuplicatedVisitEvnt(callInfo, visitFacts)) {

                    //this flag for if visit is approved or not (completed or not)
                    boolean isApprovedVisit = false;
                    //remove duplicated visit event
                    VisitEvent newVisitEvnt = callInfo.getVisit();
                    VisitEvent existingVisitEvent = new VisitEvent();
                    OracleMetadata visitEvntOracleMetaData = DataMapper.getOracleMetadata(existingVisitEvent);

                    List<VisitEvent> existingVisitEvntLst = new ArrayList<>();
                    Visit existingVisit = new Visit();

                    //existing visit
                    VisitFact visitFact = visitFacts.get(0);
                    newVisitEvnt.setVisitSK(visitFact.getVisitSk());

                    OracleMetadata visitOracleMetaData = DataMapper.getOracleMetadata(new Visit());
                    List<Visit> existingVisitListBySk = (List) executeGet(visitOracleMetaData.packageName()
                        , visitOracleMetaData.getMethod()
                        , existingVisit.getClass().getCanonicalName()
                        , visitFact.getVisitSk().intValue());
                    existingVisit = existingVisitListBySk.get(0);

                    //add new visit event to list, for recalculate in/out, dtime.
                    existingVisitEvntLst.add(newVisitEvnt);

                    int returnVisitSk = -99999;
                    //if one visit event
                    if (visitFacts.size() == 1) {
                        //get existing visit event - only one record

                        List<VisitEvent> existingVisitEvntListBySk = new ArrayList<>();

                        if (visitFact.getVisitEvntSk() != null) {
                            existingVisitEvntListBySk = (List) executeGet(visitEvntOracleMetaData.packageName()
                                    , visitEvntOracleMetaData.getMethod()
                                    , existingVisitEvent.getClass().getCanonicalName()
                                    , visitFact.getVisitEvntSk().intValue());
                        }

                        if (existingVisitEvntListBySk != null && existingVisitEvntListBySk.size() > 0) {
                            existingVisitEvntLst.add(existingVisitEvntListBySk.get(0));
                        }

                        //re update call time, call in , call out
                        VisitEvent[] visitEvents = new VisitEvent[existingVisitEvntLst.size()];
                        visitEvents = existingVisitEvntLst.toArray(visitEvents);
                        recalculateVisitTimesForVisitEvents(existingVisit, visitEvents);
                        existingVisitEvntLst = new LinkedList<>(Arrays.asList(visitEvents));

                    } else if (visitFacts.size() > 1) {

                        //more than 1 existing visit event and new visit event , mean that has extra call
                        callInfo.setVisitHasExtraCall(true);

                        //get existing from db by in VisitEventSK
                        List<Integer> visitSkList = new ArrayList<>();
                        for (VisitFact visit : visitFacts) {
                            if (visit.getVisitEvntSk() != null) {
                                visitSkList.add(visit.getVisitEvntSk().intValue());
                            }
                        }
                        String visitEvntSkList = CommonUtils.convertNumListToStringParams(visitSkList);
                        existingVisitEvntLst.addAll(getVisitEventBySks(connection, connectionType, visitEvntSkList));

                        VisitEvent[] visitEvents = new VisitEvent[existingVisitEvntLst.size()];
                        visitEvents = existingVisitEvntLst.toArray(visitEvents);
                        //re calculate in/out, dtime ...
                        recalculateVisitTimesForVisitEvents(existingVisit, visitEvents);
                        existingVisitEvntLst = new LinkedList<>(Arrays.asList(visitEvents));

                    }

                    //---------- update visit info ------------

                    //if (!isApprovedVisit) {
                    // Update Visit.
                    Integer result = execute(connection,
                        connectionType,
                        visitOracleMetaData.packageName(),
                        visitOracleMetaData.updateMethod(),
                        new DataMapper().map(existingVisit));

                    if (result < 0) {
                    	String errMsg = String.format("Failed to update Visit entity with SK %s!", existingVisit.getVisitSK());
                    	visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
                           		, this.getClass(), "insertUnplannedVisitEvent", errMsg));
                        throw new SandataRuntimeException(errMsg);
                    }

                    // update existing visit event info
                    existingVisitEvntLst.remove(newVisitEvnt); //this is new visit event, not update, but insert new
                    //update info for existing visit event after recalculating
                    for (VisitEvent visitEvent : existingVisitEvntLst) {
                        result = execute(connection,
                            connectionType,
                            visitEvntOracleMetaData.packageName(),
                            visitEvntOracleMetaData.updateMethod(),
                            new DataMapper().map(visitEvent));

                        if (result < 0) {
                        	String errMsg = String.format("Failed to update existing VisitEvent entity with SK %s!", existingVisitEvent.getVisitSK());
                        	visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
                               		, this.getClass(), "insertUnplannedVisitEvent", errMsg));
                            throw new SandataRuntimeException(errMsg);
                        }
                    }

                    //add new visit event
                    setAdditionalVisitEventInfo(newVisitEvnt);
                    result = execute(connection,
                        connectionType,
                        visitEvntOracleMetaData.packageName(),
                        visitEvntOracleMetaData.insertMethod(),
                        new DataMapper().map(newVisitEvnt));

                    if (result < 0) {
                    	String errMsg = String.format("Insert of VisitEvent failed with result %s!", result);
                    	visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
                           		, this.getClass(), "insertUnplannedVisitEvent", errMsg));
                        throw new SandataRuntimeException(errMsg);
                    }

                    //add new visit task list info
                    if (callInfo.getTasks() != null) {
                        existingVisit.getVisitTaskList().addAll(callInfo.getTasks());
                        setVisitTaskListInfo(existingVisit);
                        result = insertVisitTaskList(connection, connectionType, existingVisit.getVisitTaskList());
                        if (result < 0) {
                            String errMsg = String.format("Failed to insert visit task list for visit SK %s!", existingVisitEvent.getVisitSK());
                            visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
                                    , this.getClass(), "insertUnplannedVisitEvent", errMsg));
                            throw new SandataRuntimeException(errMsg);
                        }

                    }
                    connection.commit();

                    //send visit event fact for exception handle
                    callInfo.setParentVisit(existingVisit);
                    callInfo.setVisitSK(existingVisit.getVisitSK());
                    callInfo.setVisit(newVisitEvnt);
                    exchange.getIn().setBody(callInfo);
                    exchange.getIn().setHeader("bsnEntId",bsnEntId);
                    
                    visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
                       		, this.getClass(), "insertUnplannedVisitEvent", "Completed to insert visit info to database !"));
                    //}
                } else {
                	visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
                       		, this.getClass(), "insertUnplannedVisitEvent", "Unexpected case , this call is existing in database"));
                }
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

            String errMsg = String.format("%s: %s : %s",e.getClass().getName(), e.getMessage(),e.getStackTrace());
        	visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
               		, this.getClass(), "insertUnplannedVisitEvent", errMsg));
            throw new SandataRuntimeException(errMsg,e);

        } finally {

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

    private List<VisitEvent> getVisitEventBySks(Connection connection, ConnectionType connectionType, String visitSkList) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT * FROM VISIT_EVNT  %s";
            StringBuilder whereConditions = new StringBuilder();
            if (visitSkList != null && !visitSkList.isEmpty()) {

                whereConditions.append(" WHERE VISIT_EVNT_SK IN ( ");
                whereConditions.append(visitSkList);
                whereConditions.append(" )");
            }

            sql = String.format(sql, whereConditions.toString());

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            List<VisitEvent> scheduleEventList = (List<VisitEvent>) new DataMapper().map(resultSet, VisitEvent.class.getCanonicalName());

            return scheduleEventList;

        } catch (Exception e) {
            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = String.format("%s: %s",
                    e.getClass().getName(), e.getMessage());
            visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
            		, this.getClass(), "getVisitEventBySks", errMsg));
            
            throw new SandataRuntimeException(errMsg,e);

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


    private Visit createNewVisitInfo(Connection connection, ConnectionType connectionType, VisitEventFact callInfo) throws ParseException, SQLException {

        Visit visit = new Visit();
        if (callInfo.getStaffFact() != null && StringUtils.isNotEmpty(callInfo.getStaffFact().getStaffId())) {
            visit.setStaffID(callInfo.getStaffFact().getStaffId());
        } else {
            visit.setStaffID(callInfo.getStaffID());
        }

        if (callInfo.getPatientFact() != null && StringUtils.isNotEmpty(callInfo.getPatientFact().getPatientId())) {
            visit.setPatientID(callInfo.getPatientFact().getPatientId());
        } else {
            visit.setPatientID(callInfo.getPatientID());
        }

        visit.setVisitActualStartTimestamp(callInfo.getVisitEventDatetime());
        visit.setBusinessEntityID(callInfo.getBusinessEntityId());
        //additional info for visit
        setAdditionalVisit(visit);

        //visit event
        VisitEvent visitEvent = callInfo.getVisit();
        visitEvent.setCallInIndicator(true);
        visitEvent.setCallOutIndicator(false);
        setAdditionalVisitEventInfo(visitEvent);

        visit.getVisitEvent().add(visitEvent);

        //visit task list
        if (callInfo.getTasks() != null) {
            visit.getVisitTaskList().addAll(callInfo.getTasks());
            setVisitTaskListInfo(visit);
        }

        //recursive execute
        boolean isInsert = true;
        connection.setAutoCommit(false);
        long returnVal = executeRecursive(connection, connectionType, visit, isInsert, -999);
        if (returnVal > 0) {
            connection.commit();
        }

        visit.setVisitSK(BigInteger.valueOf(returnVal));
        return visit;

    }


    private int insertVisitTaskList(Connection connection, ConnectionType connectionType, List<VisitTaskList> visitTaskList) {
        OracleMetadata visitTskLstMetaData = DataMapper.getOracleMetadata(new VisitTaskList());

        int result = 0;
        for (VisitTaskList vsTask : visitTaskList) {
            result = execute(connection,
                connectionType,
                visitTskLstMetaData.packageName(),
                visitTskLstMetaData.insertMethod(),
                new DataMapper().map(vsTask));
        }

        return result;
    }


    private void setVisitTaskListInfo(Visit visit) {
        List<VisitTaskList> taskList = visit.getVisitTaskList();
        for (Iterator<VisitTaskList> iterator = taskList.iterator(); iterator.hasNext(); ) {
            VisitTaskList visitTaskList = iterator.next();
            if (visitTaskList.getBusinessEntityTaskID() == null) {
                Task task = getTask(visit.getBusinessEntityID(), visitTaskList.getVisitTaskListID());
                if (task != null) {
                    Date date = new Date();
                    visitTaskList.setVisitTaskListID(task.getTaskID());
                    visitTaskList.setVisitSK(visit.getVisitSK());
                    visitTaskList.setBusinessEntityTaskID(task.getBusinessEntityTaskID());
                    visitTaskList.setCriticalTaskIndicator(task.isCriticalTaskIndicator());
                    //visitTaskList.setTaskResultsReadingType(task.getTaskReadingType());
                    visitTaskList.setRecordCreateTimestamp(date);
                    visitTaskList.setRecordUpdateTimestamp(date);
                    visitTaskList.setChangeVersionID(BigInteger.ONE);
                    visitTaskList.setBusinessEntityID(visit.getBusinessEntityID());
                } else {
                    // logger.error(String.format("TASK ID (%s) returned null from getTask()!", visitTaskList.getVisitTaskListID()));
                    iterator.remove();
                }
            }

        }
    }


    private Task getTask(final String bsnEntID, final String taskId) {

        // initialize cache
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = getOracleConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM TASK WHERE BE_ID=? AND BE_TASK_ID=?" +
                    " AND ( TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'" +
                    "  AND CURR_REC_IND = 1)";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;

            preparedStatement.setString(index++, bsnEntID);
            preparedStatement.setString(index++, taskId);

            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                String errMsg = "NOT IN DATABASE";
                //logger.warn(String.format("[BE_ID: %s][TASK_ID: %s]: Not Found!", bsnEntID, taskId));
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
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMessg = String.format("%s: %s", e.getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMessg, e);

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


    protected Object executeGet(String packageName, String methodName, String className, long sequenceKey) throws SandataRuntimeException {

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

            String errMsg = String.format("[%s][%s][%s]: %s: %s",
                    packageName, methodName, className,
                    e.getClass().getName(), e.getMessage());
            visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
               		, this.getClass(), "executeGet", errMsg) );
            
            throw new SandataRuntimeException(errMsg ,e);

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
        }
    }

    public Object executeGet(ConnectionType connectionType,
                             Connection connection,
                             String packageName,
                             String methodName,
                             String className,
                             Object... params)
        throws SandataRuntimeException {

        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.STRING_ARRAY", connection);
            ARRAY primaryKeysArray = new ARRAY(des, connection, params);

            String callMethod = String.format("{?=call %s.%s.%s(?)}", connectionType, packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            callableStatement.setArray(2, primaryKeysArray);
            callableStatement.execute();

            resultSet = (ResultSet) callableStatement.getObject(1);

            Object result = new DataMapper().map(resultSet, className);

            connection.commit();

            return result;

        } catch (Exception e) {

        	 String errMsg = String.format("[%s][%s][%s]: %s: %s",
                     packageName, methodName, className,
                     e.getClass().getName(), e.getMessage());
             visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
                		, this.getClass(), "executeGet", errMsg) );
             
             throw new SandataRuntimeException(errMsg ,e);

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
        }
    }

    private boolean isDuplicatedVisitEvnt(VisitEventFact callInfo, List<VisitFact> visitFacts) {
        for (VisitFact visitFact : visitFacts) {
            if (visitFact.getVisitEvntDtime() != null && callInfo.getCallInTime() != null && callInfo.getCallInTime().compareTo(visitFact.getVisitEvntDtime()) == 0
                && visitFact.getAni() != null && callInfo.getAni().equals(visitFact.getAni())
                && visitFact.getDnis() != null && callInfo.getDnis().equals(visitFact.getDnis())) {
                return true;
            }
        }

        return false;
    }

    private List<VisitFact> getExistingUnplannedVisit(VisitEventFact callInfo) {
       /* String s = GSONProvider.ToJson(callInfo);
        visitVerificationLogger.info("Visit eventfact request to get all the unplanned existing visit event : {}", s);
        */
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<VisitFact> visitFacts = new ArrayList<VisitFact>();

        try {

            if (callInfo == null) {
            	visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
                    		, this.getClass(), "getExistingUnplannedVisit", "Parameter visitSk required!"));
                throw new SandataRuntimeException("Parameter visitSk required!");
            }

            List<String> parameterList = new ArrayList<String>();

            // get from call preferences
            int secondCallStillValidThresholdMinutes = callInfo.getCallPreferences().getSecondCallStillValidThresholdMinutes();//24 //hours

            int offSetFuture = secondCallStillValidThresholdMinutes;
            int offSetPast = -secondCallStillValidThresholdMinutes;
            String formatPattern = "yyyy-MM-dd HH:mm:ss";
            String fromDate = CommonUtils.convertDateToString(formatPattern, CommonUtils.createDateWithOffset(callInfo.getCallInTime(), offSetPast));
            String toDate = CommonUtils.convertDateToString(formatPattern, CommonUtils.createDateWithOffset(callInfo.getCallInTime(), offSetFuture));

            parameterList.add(fromDate);
            parameterList.add(toDate);
            parameterList.add(callInfo.getBusinessEntityId());

            if (callInfo.getStaffFact() != null && StringUtils.isNotEmpty(callInfo.getStaffFact().getStaffId())) {
                parameterList.add(callInfo.getStaffFact().getStaffId());
            } else {
                parameterList.add(callInfo.getStaffID());
            }
            StringBuilder conditions = new StringBuilder(" AND ");
            if (callInfo.getPatientID() != null) {
                conditions.append(" PT_ID = '").append(callInfo.getPatientID()).append("'");
            } else if (callInfo.getAni() != null) {
                conditions.append(" ANI = '").append(callInfo.getAni()).append("'");
            }

            connection = connectionPoolDataService.getConnection(ConnectionType.COREDATA);
            connection.setAutoCommit(true);

            //get unplanned visit event from database that get matched by ANI/PT_ID,STAFF_ID,BE_ID , call time within second threshold
            String sql = "SELECT V1.VISIT_SK,BE_ID,VISIT_EVNT_SK,STAFF_ID,PT_ID,ANI,DNIS,VISIT_EVNT_DTIME " +
                " FROM    (SELECT * FROM VISIT " +
                " WHERE SCHED_EVNT_SK IS NULL " +
                " AND VISIT_ACT_START_TMSTP IS NOT NULL " +
                " AND VISIT_ACT_START_TMSTP BETWEEN TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                " AND TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                " AND BE_ID = ? " +
                " AND STAFF_ID = ? AND ( VISIT_APRVD_IND = 0 OR VISIT_APRVD_IND IS NULL )) V1 " +
                " INNER JOIN (SELECT VISIT_EVNT_SK,VISIT_SK,ANI,DNIS,VISIT_EVNT_DTIME " +
                " FROM VISIT_EVNT " +
                " WHERE ((TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') AND CURR_REC_IND = 1) %s ) V2 " +
                " ON V1.VISIT_SK = V2.VISIT_SK";

            sql = String.format(sql, conditions.toString());

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (String parameter : parameterList) {
                preparedStatement.setString(index++, parameter);
            }

            resultSet = preparedStatement.executeQuery();
            VisitFact visitFact = null;

            while (resultSet.next()) {
                visitFact = new VisitFact();
                visitFact.setBusinessEntityId(resultSet.getString("BE_ID"));
                visitFact.setAni(resultSet.getString("ANI"));
                visitFact.setDnis(resultSet.getString("DNIS"));
                visitFact.setStaffId(resultSet.getString("STAFF_ID"));
                visitFact.setPatientId(resultSet.getString("PT_ID"));
                //visitFact.setPatientId(resultSet.getString("PT_ID"));

                BigDecimal visitSk = resultSet.getBigDecimal("VISIT_SK");
                if (visitSk != null) {
                    visitFact.setVisitSk(BigInteger.valueOf(visitSk.longValue()));
                }

                BigDecimal visitEvntSk = resultSet.getBigDecimal("VISIT_EVNT_SK");
                if (visitEvntSk != null) {
                    visitFact.setVisitEvntSk(BigInteger.valueOf(visitEvntSk.longValue()));
                }

                Timestamp visitEvntDtime = resultSet.getTimestamp("VISIT_EVNT_DTIME");
                if (visitEvntDtime != null) {
                    visitFact.setVisitEvntDtime(new java.util.Date(visitEvntDtime.getTime()));
                }

                visitFacts.add(visitFact);
            }


            return visitFacts;

        } catch (Exception e) {
            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = String.format("%s: %s", e.getClass().getName(), e.getMessage());
            visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
            		, this.getClass(), "getExistingUnplannedVisit", errMsg));
            throw new SandataRuntimeException(errMsg,e);

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

    private void setAdditionalVisitEventInfo(VisitEvent visitEvent) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date termDate = simpleDateFormat.parse("9999-12-31");
        String createUpdatedBy = "ArcherVV";

        visitEvent.setRecordTerminationTimestamp(termDate);
        visitEvent.setRecordEffectiveTimestamp(new Date());
        visitEvent.setRecordCreateTimestamp(new Date());
        visitEvent.setRecordUpdateTimestamp(new Date());
        visitEvent.setChangeVersionID(BigInteger.ONE);
        //dmr--RemovedFromModel--visitEvent.setVisitEventTypeID("CALLMATCHING");
        visitEvent.setVisitEventTypeCode(VisitEventTypeCode.TEL);
        //visitEvent.setChangeReasonMemo(reasonForChange);
        visitEvent.setRecordCreatedBy(createUpdatedBy);
        visitEvent.setRecordUpdatedBy(createUpdatedBy);
    }


    private void setAdditionalVisit(Visit visit) throws ParseException {

        visit.setRecordCreateTimestamp(new Date());
        visit.setRecordUpdateTimestamp(new Date());
        visit.setChangeVersionID(BigInteger.ONE);

        String reasonForChange = "Created: ArcherVV";
        String createUpdatedBy = "ArcherVV";
        visit.setChangeReasonMemo(reasonForChange);
        visit.setRecordCreatedBy(createUpdatedBy);
        visit.setRecordUpdatedBy(createUpdatedBy);
    }

    public List<Visit> getVisitBySchedEvntSk(Connection connection, String bsnEntId, Long schedEvntSk) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Visit> visitList = null;

        try {

            if (bsnEntId == null) {
                throw new SandataRuntimeException("Parameter bsnEntId required!");
            }

            if (schedEvntSk == null) {
                throw new SandataRuntimeException("Parameter schedEvntSk required!");
            }

            String sql = "SELECT * FROM VISIT" +
                " WHERE BE_ID=?" +
                "   AND SCHED_EVNT_SK=?";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setLong(index, schedEvntSk);

            resultSet = preparedStatement.executeQuery();

            visitList = (List<Visit>) new DataMapper().map(resultSet, Visit.class.getCanonicalName());

            return visitList;

        } catch (Exception e) {
            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = String.format("%s: %s", e.getClass().getName(), e.getMessage());
            visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
               		, this.getClass(), "getVisitBySchedEvntSk", errMsg));
            throw new SandataRuntimeException(errMsg,e);

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

    public List<VisitEvent> getVisitEventListForVisitSk(Connection connection, Integer visitSk) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<VisitEvent> visitEventList = null;

        try {

            if (visitSk == null) {
                throw new SandataRuntimeException("Parameter visitSk required!");
            }

            String sql = "SELECT * FROM VISIT_EVNT" +
                "  WHERE VISIT_SK=%s" +
                "    AND (CURR_REC_IND=1 AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD')='9999-12-31')";

            sql = String.format(sql,visitSk);
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            visitEventList = (List<VisitEvent>) new DataMapper().map(resultSet, VisitEvent.class.getCanonicalName());
            return visitEventList;

        } catch (Exception e) {
            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = String.format("%s: %s",e.getClass().getName(), e.getMessage());
            visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
               		, this.getClass(), "getVisitEventListForVisitSk", errMsg));
            throw new SandataRuntimeException(errMsg,e);

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

    private int execute(Connection connection,
                        ConnectionType connectionType,
                        String packageName,
                        String methodName,
                        Object jpubType) throws SandataRuntimeException {

        CallableStatement callableStatement = null;

        try {

            String callMethod = String.format("{?=call %s.%s.%s(?)}", connectionType, packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setObject(2, jpubType);
            callableStatement.execute();
            return (int) (Integer) callableStatement.getObject(1);
        } catch (Exception e) {

            // NOTE: Rollback (if necessary) should be handled by caller since they passed in the connection...
        	String errMsg = String.format("[%s][%s]: %s: %s",
                    packageName, methodName,
                    e.getClass().getName(), e.getMessage());
        	visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
               		, this.getClass(), "execute", errMsg));
            throw new SandataRuntimeException(errMsg, e);

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


    private long executeRecursive(final Connection connection, final ConnectionType connectionType, final Object data, final boolean bShouldInsert, long returnVal) throws SandataRuntimeException {

        boolean cantUpdate = false;

        Object jpubType;
        String shouldInsertStr = "false";
        OracleMetadata oracleMetadata;

        try {

            oracleMetadata = DataMapper.getOracleMetadata(data);
            jpubType = new DataMapper().map(data);

            setSk(jpubType, returnVal, "setVisitSk");

            long result = 0;

            if (bShouldInsert || cantUpdate) {
                result = execute(connection, connectionType, oracleMetadata.packageName(),
                    oracleMetadata.insertMethod(), jpubType);
            } else {

                if (data instanceof Visit) {
                    returnVal = ((Visit) data).getVisitSK().longValue();

                    // update specific columns only TMD really should never be updating creation
                    // timestamp anyway
                    result = updateVisit(connection, (Visit) data);
                } else
                    // UPDATE
                    result = execute(connection, ConnectionType.COREDATA, oracleMetadata.packageName(),
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
                            long insertResponse = executeRecursive(connection, connectionType, object, true,
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
        	visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
               		, this.getClass(), "executeRecursive", errMsg));
            throw new SandataRuntimeException(errMsg, e);

        }
    }

    private void setSk(final Object jpubType, Long sequenceKey, String setSkMethodName) throws Exception {

        if (sequenceKey <= 0) {
            return;
        }
        try {

            Method method = jpubType.getClass().getDeclaredMethod(setSkMethodName, BigDecimal.class);
            method.invoke(jpubType, BigDecimal.valueOf(sequenceKey));
        } catch (Exception e) {

            String errMsg = String.format("%s: %s", e.getClass().getName(), e.getMessage());
            visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
               		, this.getClass(), "setSk", errMsg));
            throw new SandataRuntimeException(errMsg, e);
        }
    }


    private int updateVisit(Connection connection, Visit visit) {

        int result = 0;
        PreparedStatement updateVisitStmnt = null;
        //F#^$ing empty visits
        String sqlDateTimes = "";
        if (visit.getVisitActualStartTimestamp() != null)
            sqlDateTimes += ",VISIT_ACT_START_TMSTP=TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')";
        if (visit.getVisitActualEndTimestamp() != null)
            sqlDateTimes += ",VISIT_ACT_END_TMSTP=TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')";
        String staffId = "";
        if (visit.getStaffID() != null) {
            staffId = ",STAFF_ID=?";
        }
        String updateVisitSql = String.format("UPDATE VISIT SET REC_UPDATE_TMSTP=TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS'),REC_UPDATED_BY=?,CHANGE_REASON_MEMO=?%s%s WHERE VISIT_SK=?", sqlDateTimes, staffId);

        try {

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            connection.setAutoCommit(false);
            updateVisitStmnt = connection.prepareStatement(updateVisitSql);
            int index = 1;
            updateVisitStmnt.setString(index++, dateFormat.format(visit.getRecordUpdateTimestamp()));
            updateVisitStmnt.setString(index++, visit.getRecordUpdatedBy());
            updateVisitStmnt.setString(index++, visit.getChangeReasonMemo());
            if (visit.getVisitActualStartTimestamp() != null)
                updateVisitStmnt.setString(index++, dateFormat.format(visit.getVisitActualStartTimestamp()));
            if (visit.getVisitActualEndTimestamp() != null)
                updateVisitStmnt.setString(index++, dateFormat.format(visit.getVisitActualEndTimestamp()));
            if (visit.getStaffID() != null) {
                updateVisitStmnt.setString(index++, visit.getStaffID());
            }
            updateVisitStmnt.setLong(index++, visit.getVisitSK().longValue());
            result = updateVisitStmnt.executeUpdate();
            connection.commit();
        } catch (SQLException sex) {

            String errMessage = "VisitArcher::updateVisit() Exception :: " + sex.getLocalizedMessage();
            visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
               		, this.getClass(), "updateVisit", errMessage));
            throw new SandataRuntimeException(errMessage, sex);
        } finally {

            if (updateVisitStmnt != null) {
                try {
                    updateVisitStmnt.close();

                } catch (SQLException sex) {
                    sex.printStackTrace();
                }
            }
        }

        return result;
    }

    /**
     * Query system defined visit verification exceptions.
     *
     * @param exchange Specified Exchange.
     */
    public void getVisitVerificationExceptionList(Exchange exchange) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPoolDataService.getConnection(ConnectionType.COREDATA);
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM EXCP_LKUP" +
                "  WHERE APP_MOD_NAME='VISIT VERIFICATION'" +
                "    AND (CURR_REC_IND=1 AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD')='9999-12-31')";

            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            List<ExceptionLookup> exceptionLookupList = (List<ExceptionLookup>) new DataMapper().map(resultSet, ExceptionLookup.class.getCanonicalName());

            connection.commit();

            exchange.getIn().setBody(exceptionLookupList);

        } catch (Exception e) {
            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = String.format("%s: %s", e.getClass().getName(), e.getMessage());
            visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
               		, this.getClass(), "getVisitVerificationExceptionList", errMsg));
            throw new SandataRuntimeException(errMsg,e);

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

    public void getBusinessEntityIdList(Exchange exchange) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPoolDataService.getConnection(ConnectionType.COREDATA);
            connection.setAutoCommit(false);

            String sql = "SELECT BE_ID FROM BE" +
                "  WHERE (CURR_REC_IND=1 AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD')='9999-12-31')";

            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            List<String> businessEntityIdList = new ArrayList<>();

            while (resultSet.next()) {
                businessEntityIdList.add(resultSet.getString("BE_ID"));
            }

            connection.commit();

            exchange.getIn().setBody(businessEntityIdList);

        } catch (Exception e) {
            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = String.format("%s: %s", e.getClass().getName(), e.getMessage());
            visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
               		, this.getClass(), "getBusinessEntityIdList", errMsg));
            throw new SandataRuntimeException(errMsg,e);

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

    public void getBusinessEntityVisitVerificationSettings(Exchange exchange) {

        try {

            String bsnEntId = (String) exchange.getIn().getHeader("bsnEntId");
            if (bsnEntId == null || bsnEntId.isEmpty()) {
            	
            	visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
                   		, this.getClass(), "getBusinessEntityVisitVerificationSettings", "Parameter bsnEntId required!"));
                throw new SandataRuntimeException("Parameter bsnEntId required!");
            }

            exchange.getIn().setBody(getBusinessEntityVisitVerificationSettings(bsnEntId));

        } catch (Exception e) {
            String errMsg = String.format("%s: %s", e.getClass().getName(), e.getMessage());
            visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
               		, this.getClass(), "getBusinessEntityVisitVerificationSettings", errMsg));
            throw new SandataRuntimeException(errMsg,e);
        }
    }


    public void createVisitAuth(final Exchange exchange) {
        Connection connection = null;

        try {
            VisitEventFact visitEventFact = exchange.getIn().getBody(VisitEventFact.class);
            if (visitEventFact != null) {
                PatientFact patientFact = visitEventFact.getPatientFact();
                Visit parentVisit = visitEventFact.getParentVisit();
                if (patientFact != null && parentVisit != null) {

                    BigInteger visitSk = parentVisit.getVisitSK();

                    Date now = new Date();
                    VisitAuthorization data = new VisitAuthorization();
                    data.setVisitSK(visitSk);
                    data.setChangeVersionID(BigInteger.ONE);
                    data.setRecordCreateTimestamp(now);
                    data.setRecordUpdateTimestamp(now);

                    data = getAndSetAuthInfo(data, visitEventFact.getBusinessEntityId(), patientFact.getPatientId(), visitEventFact.getVisitEventDatetime());
                    BigInteger authorizationSk = data.getAuthorizationSK();
                    if (authorizationSk == null) {
                        String message = "Cannot get existing AuthorizationSK! Stop creating VisitAuthRequest ";
                        visitVerificationLogger.info(message);
                        throw new SandataRuntimeException(message);
                    }

                    AuthorizationQualifier authQualifier = data.getAuthorizationQualifier();
                    long returnVal = getExisitngVisitAuth(visitSk.longValue(), authorizationSk.longValue(), authQualifier.name());
                    if (returnVal > 0) {
                        //VISIT_AUTH already existed
                        visitVerificationLogger.info("The existing VISIT_AUTH with VisitSK = {} , AUTH_SK = {} , QLFier = {}", visitSk, authorizationSk, authQualifier.name());
                        exchange.getIn().setBody(returnVal);

                    } else {

                        //insert new VISIT_AUTH

                        ConnectionType connectionType = ConnectionType.COREDATA;
                        connection = connectionPoolDataService.getConnection(connectionType);
                        connection.setAutoCommit(false);

                        returnVal = executeRecursive(connection, connectionType, data, true, -999);
                        if (returnVal > 0) {

                            connection.commit();
                            exchange.getIn().setBody(returnVal);
                            visitVerificationLogger.info("Add new VISIT_AUTH successfully ");
                        } else {
                            String message = "Insert was not successful!";
                            visitVerificationLogger.info(message);
                            throw new SandataRuntimeException(message);
                        }

                    }
                }
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

            String errMsg = String.format("%s: %s", e.getClass().getName(), e.getMessage());
            visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
               		, this.getClass(), "createVisitAuth", errMsg));
            throw new SandataRuntimeException(errMsg,e);

        } finally {

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

    private VisitAuthorization getAndSetAuthInfo(VisitAuthorization data, String bsnEntityId, String patientId, Date visitDateTime) throws SandataRuntimeException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        AuthorizationQualifier authQualifier = AuthorizationQualifier.AUTHORIZATION;
        data.setAuthorizationQualifier(authQualifier);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

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
            if (resultSet.next()) {
                BigInteger authSk = resultSet.getBigDecimal("AUTH_SK").toBigInteger();
                data.setAuthorizationSK(authSk);
                BigDecimal bigDecimalOrdSk = resultSet.getBigDecimal("ORD_SK");
                if (bigDecimalOrdSk != null) {
                    data.setAuthorizationQualifier(AuthorizationQualifier.ORDER);
                }
            }
            connection.commit();
            return data;
        } catch (Exception e) {
            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                    visitVerificationLogger.info(sqle.getMessage());
                }
            }

            String errMsg = String.format("%s: %s", e.getClass().getName(), e.getMessage());
            visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
               		, this.getClass(), "getAndSetAuthInfo", errMsg));
            throw new SandataRuntimeException(errMsg,e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                    visitVerificationLogger.info(sqle.getMessage());
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                    visitVerificationLogger.info(sqle.getMessage());
                }
            }

            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                    visitVerificationLogger.info(sqle.getMessage());
                }
            }
        }


    }

    private int getExisitngVisitAuth(long visitSK, long authSK, String authQlfr) {

        String sql = "SELECT VISIT_AUTH_SK FROM VISIT_AUTH WHERE VISIT_SK = ? AND AUTH_SK = ? AND AUTH_QLFR = ? ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = -999;

        try {
            connection = getOracleConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            preparedStatement.setLong(index++, visitSK);
            preparedStatement.setLong(index++, authSK);
            preparedStatement.setString(index++, authQlfr);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt("VISIT_AUTH_SK");
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
                    visitVerificationLogger.info(sqle.getMessage());
                }
            }

            String errMsg = String.format("%s: %s", e.getClass().getName(), e.getMessage());
            visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
               		, this.getClass(), "getExisitngVisitAuth", errMsg));
            throw new SandataRuntimeException(errMsg,e);

        } finally {
            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                    visitVerificationLogger.info(sqle.getMessage());
                }
            }
            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                    visitVerificationLogger.info(sqle.getMessage());
                }
            }
            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                    visitVerificationLogger.info(sqle.getMessage());
                }
            }
        }
    }

    public void getBusinessEntityVisitsForExceptions(Exchange exchange) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String bsnEntId = (String) exchange.getIn().getHeader("bsnEntId");

            String scheduledStartDateLowerLimitString = (String) exchange.getIn().getHeader("scheduledStartDateLowerLimitString");
            String scheduledStartDateUpperLimitString = (String) exchange.getIn().getHeader("scheduledStartDateUpperLimitString");
            long currentTimeMillis;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (scheduledStartDateLowerLimitString == null
                || scheduledStartDateLowerLimitString.isEmpty()) {
                currentTimeMillis = System.currentTimeMillis();
                scheduledStartDateLowerLimitString = dateFormat.format(new Date(currentTimeMillis - scheduleStartThresholdForVisitExceptions));
            } else {
                currentTimeMillis = dateFormat.parse(scheduledStartDateLowerLimitString).getTime() + scheduleStartThresholdForVisitExceptions;
            }

            if (scheduledStartDateUpperLimitString == null
                || scheduledStartDateUpperLimitString.isEmpty()) {
                scheduledStartDateUpperLimitString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(currentTimeMillis));
            }

            ConnectionType connectionType = ConnectionType.COREDATA;
            connection = connectionPoolDataService.getConnection(connectionType);
            connection.setAutoCommit(false);

            String sql = String.format("  SELECT * FROM VISIT T1" +
                    "  INNER JOIN SCHED_EVNT T2 ON T2.BE_ID=T1.BE_ID" +
                    "    AND T2.SCHED_EVNT_SK=T1.SCHED_EVNT_SK" +
                    "  WHERE T1.BE_ID=?" +
                    "    AND T2.SCHED_EVNT_START_DTIME > SYS_EXTRACT_UTC(TIMESTAMP '%s')" +
                    "    AND T2.SCHED_EVNT_START_DTIME <= SYS_EXTRACT_UTC(TIMESTAMP '%s')" +
                    "    AND (T1.VISIT_CANCELLED_IND=0 OR T1.VISIT_CANCELLED_IND IS NULL)" +
                    "    AND (T1.VISIT_APRVD_IND=0 OR T1.VISIT_APRVD_IND IS NULL)" +
                    "    AND (T2.CURR_REC_IND=1 AND TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD')='9999-12-31')" +
                    "  ORDER BY T2.SCHED_EVNT_START_DTIME ASC",
                scheduledStartDateLowerLimitString,
                scheduledStartDateUpperLimitString);

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<VisitForExceptionsFact> visitForExceptionsFactList = getVisitForExceptionFactsFromResultSet(resultSet);

            //get visit event and visit exception 
            getRelatedVisitForExceptionFactData(connectionType,
                connection,
                visitForExceptionsFactList);

            connection.commit();

            exchange.getIn().setBody(visitForExceptionsFactList);

        } catch (Exception e) {
            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = String.format("%s: %s", e.getClass().getName(), e.getMessage());
            visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
               		, this.getClass(), "getBusinessEntityVisitsForExceptions", errMsg));
            throw new SandataRuntimeException(errMsg,e);

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

    private void getRelatedVisitForExceptionFactData(ConnectionType connectionType,
                                                     Connection connection,
                                                     List<VisitForExceptionsFact> visitForExceptionsFactList) {

        for (VisitForExceptionsFact visitForExceptionsFact : visitForExceptionsFactList) {
            visitForExceptionsFact.getVisitEvent().addAll((List<VisitEvent>) executeGet(connectionType,
                connection,
                "PKG_VISIT",
                "getVisitEvnt",
                VisitEvent.class.getCanonicalName(),
                visitForExceptionsFact.getVisitSK()));

            visitForExceptionsFact.getVisitException().addAll((List<VisitException>) executeGet(connectionType,
                connection,
                "PKG_VISIT",
                "getVisitExcp",
                VisitException.class.getCanonicalName(),
                visitForExceptionsFact.getVisitSK()));
        }

    }

    private List<VisitForExceptionsFact> getVisitForExceptionFactsFromResultSet(ResultSet resultSet) {
        List<VisitForExceptionsFact> visitForExceptionsFactList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                VisitForExceptionsFact visitForExceptionsFact = new VisitForExceptionsFact();
                visitForExceptionsFact.setVisitSK(BigInteger.valueOf(resultSet.getBigDecimal("VISIT_SK").longValue()));
                visitForExceptionsFact.setRecordCreateTimestamp(resultSet.getDate("REC_CREATE_TMSTP"));
                visitForExceptionsFact.setRecordUpdateTimestamp(resultSet.getDate("REC_UPDATE_TMSTP"));
                visitForExceptionsFact.setRecordCreatedBy(resultSet.getString("REC_CREATED_BY"));
                visitForExceptionsFact.setRecordUpdatedBy(resultSet.getString("REC_UPDATED_BY"));
                visitForExceptionsFact.setChangeReasonCode(resultSet.getString("CHANGE_REASON_CODE"));
                visitForExceptionsFact.setChangeReasonMemo(resultSet.getString("CHANGE_REASON_MEMO"));
                visitForExceptionsFact.setChangeVersionID(BigInteger.valueOf(resultSet.getBigDecimal("CHANGE_VERSION_ID").longValue()));
                visitForExceptionsFact.setBusinessEntityID(resultSet.getString("BE_ID"));
                visitForExceptionsFact.setPatientID(resultSet.getString("PT_ID"));
                visitForExceptionsFact.setStaffID(resultSet.getString("STAFF_ID"));
                BigDecimal bigDecimal = resultSet.getBigDecimal("SCHED_EVNT_SK");
                visitForExceptionsFact.setScheduleEventSK(bigDecimal != null ? BigInteger.valueOf(bigDecimal.longValue()) : null);
                visitForExceptionsFact.setVisitActualStartTimestamp(resultSet.getDate("VISIT_ACT_START_TMSTP"));
                visitForExceptionsFact.setVisitActualEndTimestamp(resultSet.getDate("VISIT_ACT_END_TMSTP"));
                visitForExceptionsFact.setVisitAdjustedStartTimestamp(resultSet.getDate("VISIT_ADJ_START_TMSTP"));
                visitForExceptionsFact.setVisitAdjustedEndTimestamp(resultSet.getDate("VISIT_ADJ_END_TMSTP"));
                visitForExceptionsFact.setVisitCancelledIndicator(resultSet.getBoolean("VISIT_CANCELLED_IND"));
                visitForExceptionsFact.setVisitCancellationReason(resultSet.getString("VISIT_CANCELLATION_REASON"));
                visitForExceptionsFact.setVisitApprovedIndicator(resultSet.getBoolean("VISIT_APRVD_IND"));
                visitForExceptionsFact.setVisitVerifiedByScheduleIndicator(resultSet.getBoolean("VISIT_VFYD_BY_SCHED_IND"));
                visitForExceptionsFact.setVisitPayByScheduleIndicator(resultSet.getBoolean("VISIT_PAY_BY_SCHED_IND"));
                visitForExceptionsFact.setVisitDoNotBillIndicator(resultSet.getBoolean("VISIT_DO_NOT_BILL_IND"));
                visitForExceptionsFact.setVisitDoNotPayIndicator(resultSet.getBoolean("VISIT_DO_NOT_PAY_IND"));
                visitForExceptionsFact.setVisitPayHours(resultSet.getBigDecimal("VISIT_PAY_HRS"));
                visitForExceptionsFact.setVisitBillHours(resultSet.getBigDecimal("VISIT_BILL_HRS"));
                visitForExceptionsFact.setVisitOvertimeAbsenceHours(resultSet.getBigDecimal("VISIT_OT_ABS_HRS"));
                visitForExceptionsFact.setUserName(resultSet.getString("USER_NAME"));
                visitForExceptionsFact.setUserGloballyUniqueIdentifier(resultSet.getString("USER_GUID"));
                visitForExceptionsFact.setResolutionCode(resultSet.getString("RESOLUTION_CODE"));
                visitForExceptionsFact.setMemo(resultSet.getString("MEMO"));

                ScheduleEvent scheduleEvent = new ScheduleEvent();
                scheduleEvent.setScheduleEventSK(BigInteger.valueOf(resultSet.getBigDecimal("SCHED_EVNT_SK").longValue()));
                scheduleEvent.setScheduleEventID(resultSet.getString("SCHED_EVNT_ID"));
                scheduleEvent.setRecordCreateTimestamp(resultSet.getDate("REC_CREATE_TMSTP"));
                scheduleEvent.setRecordUpdateTimestamp(resultSet.getDate("REC_UPDATE_TMSTP"));
                scheduleEvent.setRecordEffectiveTimestamp(resultSet.getDate("REC_EFF_TMSTP"));
                scheduleEvent.setRecordTerminationTimestamp(resultSet.getDate("REC_TERM_TMSTP"));
                scheduleEvent.setRecordCreatedBy(resultSet.getString("REC_CREATED_BY"));
                scheduleEvent.setRecordUpdatedBy(resultSet.getString("REC_UPDATED_BY"));
                //this for calculate missing change reason code 
                scheduleEvent.setChangeReasonCode(resultSet.getString("CHANGE_REASON_CODE"));
                scheduleEvent.setChangeReasonMemo(resultSet.getString("CHANGE_REASON_MEMO"));
                scheduleEvent.setCurrentRecordIndicator(resultSet.getBoolean("CURR_REC_IND"));
                scheduleEvent.setChangeVersionID(BigInteger.valueOf(resultSet.getBigDecimal("CHANGE_VERSION_ID").longValue()));
                scheduleEvent.setBusinessEntityID(resultSet.getString("BE_ID"));
                scheduleEvent.setPatientID(resultSet.getString("PT_ID"));
                bigDecimal = resultSet.getBigDecimal("AUTH_SK");
                scheduleEvent.setAuthorizationSK(bigDecimal != null ? BigInteger.valueOf(bigDecimal.longValue()) : null);
                String authorizationQualifierString = resultSet.getString("AUTH_QLFR");
                if (authorizationQualifierString != null
                    && !authorizationQualifierString.isEmpty()) {
                    scheduleEvent.setAuthorizationQualifier(AuthorizationQualifier.fromValue(authorizationQualifierString));
                }
                scheduleEvent.setStaffID(resultSet.getString("STAFF_ID"));
                scheduleEvent.setPayerID(resultSet.getString("PAYER_ID"));
                bigDecimal = resultSet.getBigDecimal("RFRL_SK");
                scheduleEvent.setReferralSK(bigDecimal != null ? BigInteger.valueOf(bigDecimal.longValue()) : null);
                bigDecimal = resultSet.getBigDecimal("BE_CALENDAR_LKUP_SK");
                scheduleEvent.setBusinessEntityCalendarLookupSK(bigDecimal != null ? BigInteger.valueOf(bigDecimal.longValue()) : null);
                bigDecimal = resultSet.getBigDecimal("SCHED_SK");
                scheduleEvent.setScheduleSK(bigDecimal != null ? BigInteger.valueOf(bigDecimal.longValue()) : null);
                bigDecimal = resultSet.getBigDecimal("POC_SK");
                scheduleEvent.setPlanOfCareSK(bigDecimal != null ? BigInteger.valueOf(bigDecimal.longValue()) : null);
                scheduleEvent.setTimezoneName(resultSet.getString("TZ_NAME"));
                scheduleEvent.setScheduleEventBillRate(resultSet.getBigDecimal("SCHED_EVNT_BILL_RATE"));
                scheduleEvent.setMasterRateID(resultSet.getString("MASTER_RATE_ID"));
                scheduleEvent.setDayOfMonth(resultSet.getString("DAY_OF_MONTH"));
                scheduleEvent.setScheduleEventStartDatetime(resultSet.getDate("SCHED_EVNT_START_DTIME"));
                scheduleEvent.setScheduleEventEndDatetime(resultSet.getDate("SCHED_EVNT_END_DTIME"));
                scheduleEvent.setScheduleEventTotalHours(resultSet.getBigDecimal("SCHED_EVNT_TOTAL_HRS"));
                scheduleEvent.setScheduleEventStatus(resultSet.getString("SCHED_EVNT_STATUS"));
                scheduleEvent.setScheduleEventPayRate(resultSet.getBigDecimal("SCHED_EVNT_PAY_RATE"));
                scheduleEvent.setScheduleEventUnit(resultSet.getString("SCHED_EVNT_UNIT"));
                scheduleEvent.setScheduleEventRestriction(resultSet.getString("SCHED_EVNT_RESTRICTION"));
                scheduleEvent.setScheduleEventComment(resultSet.getString("SCHED_EVNT_COMMENT"));
                scheduleEvent.setScheduleEventManuallyOverrideIndicator(resultSet.getBoolean("SCHED_EVNT_MANUAL_OVERRIDE_IND"));

                visitForExceptionsFact.setScheduleEvent(scheduleEvent);

                visitForExceptionsFactList.add(visitForExceptionsFact);
            }
        } catch (SQLException e) {
        	String errMsg = String.format("%s: %s", e.getClass().getName(), e.getMessage());
            visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
               		, this.getClass(), "getVisitForExceptionFactsFromResultSet", errMsg));
            throw new SandataRuntimeException(errMsg,e);
            
        }

        return visitForExceptionsFactList;
    }

    /**
     * Logically delete old VisitExceptions for a specific VISIT_SK and create new VisitExceptions
     *
     * @param exchange
     */
    public void saveVisitExceptions(final Exchange exchange) {

        Connection connection = null;

        try {
            connection = getOracleConnection();
            connection.setAutoCommit(false);

            if (null == exchange.getIn().getHeader("existingVisitSk")) {
                visitVerificationLogger.error(LoggingUtils.getErrorLogMessageForProcessor(
                        LoggingUtils.SUB_APP_VV_DATA_SERVICE, this.getClass(), "saveVisitExceptions", "'existingVisitSk' cannot be null"));
                return;
            }

            BigInteger existingVisitSk = new BigInteger(exchange.getIn().getHeader("existingVisitSk").toString());
            // Logically delete old VisitExceptions for the Visit
            clearExceptionsForVisit(connection, existingVisitSk);

            // Insert new VisitException
            List<VisitException> newVisitExceptions = ( List<VisitException> ) exchange.getIn().getBody();
            if (null != newVisitExceptions) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date termDate = simpleDateFormat.parse("9999-12-31");
                for (VisitException newVisitException : newVisitExceptions) {
                    Date stamp = new Date();

                    newVisitException.setRecordCreateTimestamp(stamp);
                    newVisitException.setRecordUpdateTimestamp(stamp);
                    newVisitException.setRecordEffectiveTimestamp(stamp);
                    newVisitException.setCurrentRecordIndicator(true);
                    newVisitException.setRecordTerminationTimestamp(termDate);
                    newVisitException.setChangeVersionID(BigInteger.ONE);
                    newVisitException.setChangeReasonMemo("Reason");
                    newVisitException.setRecordCreatedBy("VV_EXCEPTION_RULES");
                    newVisitException.setRecordUpdatedBy("VV_EXCEPTION_RULES");

                    long returnVal = executeRecursive(connection, ConnectionType.COREDATA, newVisitException, true, -999);

                    if (returnVal > 0) {
                        newVisitException.setVisitExceptionSK(BigInteger.valueOf(returnVal));

                    } else {
                        String errMsg = "Insert VISIT EXCEPTION was not successful!";
                        visitVerificationLogger.error(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
                                , this.getClass(), "saveVisitExceptions", errMsg));
                        throw new SandataRuntimeException(errMsg);
                    }
                }
            }

            connection.commit();

        } catch (Exception e) {
            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                    visitVerificationLogger.error(sqle.getMessage(), e.getStackTrace());
                }
            }

            String errMsg = String.format("%s: %s",e.getClass().getName(), e.getMessage());
            visitVerificationLogger.error(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
                    , this.getClass(), "saveVisitExceptions", errMsg));
            throw new SandataRuntimeException(errMsg,e);

        } finally {
            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                    visitVerificationLogger.error(sqle.getMessage(), sqle.getStackTrace());
                }
            }
        }
    }


    /**
     * Clear Exception for a Visit
     *
     * @param connection
     * @param visitSK
     * @return
     */
    private int clearExceptionsForVisit(Connection connection, BigInteger visitSK) {
        CallableStatement callableStatement = null;
        int result = 0;
        try {
            ArrayDescriptor arrayDescriptor = ArrayDescriptor.createDescriptor(ConnectionType.COREDATA + ".NUMBER_ARRAY", connection);
            ARRAY oracleArray = new ARRAY(arrayDescriptor, connection, new Object[]{});

            String callMethod = "{?=call PKG_VISIT_UTIL.CLEAR_EXCEPTIONS_BY_VISIT_SK(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            int index = 2;
            callableStatement.setBigDecimal(index++, BigDecimal.valueOf(visitSK.longValue()));
            callableStatement.setInt(index++, 0); // 0 means deleting VisitException with EXCP_ID < 100
            callableStatement.setArray(index++, oracleArray);

            callableStatement.execute();
            result = callableStatement.getInt(1);

            return result;

        }  catch (Exception e) {
            // Don't rollback connection in this method, caller should catch Exception and rollback

            String errMsg = String.format("%s: %s", e.getClass().getName(), e.getMessage());
            visitVerificationLogger.error(errMsg, e.getStackTrace());
            throw new SandataRuntimeException(errMsg ,e);

        } finally {
            // Close the statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();

                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    visitVerificationLogger.error(sqle.getMessage(), sqle.getStackTrace());
                }
            }
        }
    }

    public void clearExceptionsByVisitSk(final Exchange exchange) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        String sql;

        boolean isFromScheduledRoute = exchange.getIn().getHeader("IsFromScheduledRoute",Boolean.class);
        String excpIdListHandledByCronRoute = exchange.getIn().getHeader("ExcepIdListHandledByCronRoute",String.class);
        
        StringBuilder excpIdStr = new StringBuilder("AND  EXCP_ID ");//IN (");
        if (isFromScheduledRoute) {
        	excpIdStr.append(" IN ( ");
        } else {
        	excpIdStr.append(" NOT IN ( ");
        }
        excpIdStr.append(excpIdListHandledByCronRoute).append(" )");
        
        BigInteger existingEVisitSk = (BigInteger) exchange.getIn().getHeader("existingVisitSk");
        
        try {
            connection = getOracleConnection();
            connection.setAutoCommit(false);
            //EXCP_ID < 31 : only for visit excp of VISIT VERIFICATION
            sql = String.format("UPDATE VISIT_EXCP SET REC_TERM_TMSTP = TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS'),REC_UPDATE_TMSTP = TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS'), " +
                    "REC_UPDATED_BY='ARCHER_VV_EXCEPTION_RULES'  WHERE VISIT_SK = ? AND EXCP_ID < 31 %s",excpIdStr);

            preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            Date termDate = new Date();
            String termDateString = sdf.format(termDate);
            preparedStatement.setString(index++, termDateString);
            preparedStatement.setString(index++, termDateString);
            preparedStatement.setBigDecimal(index++, BigDecimal.valueOf(existingEVisitSk.longValue()));
            result = preparedStatement.executeUpdate();
            connection.commit();

            if (result < 0) {
                throw new SandataRuntimeException(String.format("Failed to clearExceptionsByVisitSk Visit entity with SK %s!"));
            }

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                    visitVerificationLogger.info(sqle.getMessage());
                }
            }

            String errMsg = String.format("%s: %s",e.getClass().getName(), e.getMessage());
            visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
               		, this.getClass(), "clearExceptionsByVisitSk", errMsg));
            throw new SandataRuntimeException(errMsg,e);

        } finally {


            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();

                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                    visitVerificationLogger.info(sqle.getMessage());
                }
            }

            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                    visitVerificationLogger.info(sqle.getMessage());
                }
            }

        }
    }


    /**
     * Get schedule task list by schedule sk
     *
     * @param exchange
     */
    public void getScheduleTaskList(final Exchange exchange) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String bsnEntId = (String) exchange.getIn().getHeader("bsnEntId");
        String scheduleSk = (String) exchange.getIn().getHeader("getTaskBySchedSk");

        if (bsnEntId == null) {
            throw new SandataRuntimeException("Parameter bsnEntId required!");
        }

        if (scheduleSk == null) {
            throw new SandataRuntimeException("Unexpected error , missing schedule event fact");
        }
        List<String> parameterList = new ArrayList<>();

        try {
            connection = connectionPoolDataService.getConnection(ConnectionType.COREDATA);
            connection.setAutoCommit(true);

            String sql = "SELECT TASK.BE_ID,SCHED.SCHED_SK," +
                "  TASK_ID," +
                "  TASK.BE_TASK_ID," +
                "  TASK.CRITICAL_TASK_IND" +
                " FROM" +
                "  (SELECT * FROM SCHED_TASK_LST WHERE BE_ID =? " +
                "  ) SCHED_TASK" +
                " INNER JOIN" +
                "  (SELECT TASK_ID,BE_ID,BE_TASK_ID,CRITICAL_TASK_IND" +
                "  FROM TASK" +
                "  WHERE BE_ID=?" +
                "  AND ( TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'" +
                "  AND CURR_REC_IND= 1)" +
                "  ) TASK" +
                " ON SCHED_TASK.BE_TASK_ID = TASK.BE_TASK_ID " +
                "INNER JOIN" +
                "  ( SELECT SCHED_SK FROM SCHED WHERE BE_ID = ? AND SCHED_SK = %s " +
                "  ) SCHED " +
                "ON SCHED_TASK.SCHED_SK = SCHED.SCHED_SK";

            sql = String.format(sql, scheduleSk);
            preparedStatement = connection.prepareStatement(sql);

            parameterList.add(bsnEntId);
            parameterList.add(bsnEntId);
            parameterList.add(bsnEntId);

            int index = 1;
            for (String parameter : parameterList) {
                preparedStatement.setString(index++, parameter);
            }

            resultSet = preparedStatement.executeQuery();

            List<ScheduleTaskFact> scheduleTaskFacts = new ArrayList<ScheduleTaskFact>();
            ScheduleTaskFact scheduleTaskFact = null;
            while (resultSet.next()) {
                scheduleTaskFact = new ScheduleTaskFact();
                scheduleTaskFact.setBusinessEntityId(resultSet.getString("BE_ID"));
                scheduleTaskFact.setBeTaskId(resultSet.getString("TASK_ID"));
                scheduleTaskFact.setBeTaskId(resultSet.getString("BE_TASK_ID"));
                scheduleTaskFact.setCriticalTaskInd(resultSet.getBoolean("CRITICAL_TASK_IND"));

                BigDecimal schedSk = resultSet.getBigDecimal("SCHED_SK");
                if (schedSk != null) {
                    scheduleTaskFact.setScheduleSk(BigInteger.valueOf(schedSk.longValue()));
                }
                scheduleTaskFacts.add(scheduleTaskFact);
            }

            exchange.getIn().setBody(scheduleTaskFact);

        } catch (Exception e) {
            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = String.format("%s: %s",e.getClass().getName(), e.getMessage());
            visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
               		, this.getClass(), "getScheduleTaskList", errMsg));
            throw new SandataRuntimeException(errMsg,e);

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


    public void getPatientCoord(final Exchange exchange) {

        PatientContactAddress patientContactAddress = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String bsnEntId = (String) exchange.getIn().getHeader("bsnEntId");
        String patientId = (String) exchange.getIn().getHeader("patientIdForGPS");

        if (bsnEntId == null) {
            throw new SandataRuntimeException("Parameter bsnEntId required!");
        }

        if (patientId == null) {
            throw new SandataRuntimeException("Unexpected error , missing schedule event fact");
        }
        List<String> parameterList = new ArrayList<>();

        try {
            connection = connectionPoolDataService.getConnection(ConnectionType.COREDATA);
            connection.setAutoCommit(true);

            String sql = "SELECT *" +
                " FROM" +
                "  PT_CONT_ADDR" +
                " WHERE BE_ID = ? AND PT_ID = ? " +
                "  AND ( TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'" +
                "  AND CURR_REC_IND = 1)";

            preparedStatement = connection.prepareStatement(sql);

            parameterList.add(bsnEntId);
            parameterList.add(patientId);

            int index = 1;
            for (String parameter : parameterList) {
                preparedStatement.setString(index++, parameter);
            }

            resultSet = preparedStatement.executeQuery();
            List<PatientContactAddress> patientContactAddresses = (List<PatientContactAddress>) new DataMapper().map(resultSet
                , PatientContactAddress.class.getCanonicalName());

            if (patientContactAddresses != null && !patientContactAddresses.isEmpty()) {
                exchange.getIn().setBody(patientContactAddresses.get(0));
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

            String errMsg = String.format("%s: %s",e.getClass().getName(), e.getMessage());
            visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
               		, this.getClass(), "getPatientCoord", errMsg));
            throw new SandataRuntimeException(errMsg,e);

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

    /**
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void getScheduleEventBySk (final Exchange exchange) throws SandataRuntimeException {

        ScheduleEventFact scheduleEventFact = new ScheduleEventFact();
        VisitEventFact visitEventFact = exchange.getIn().getBody(VisitEventFact.class);
        if (visitEventFact != null && visitEventFact.getScheduleEventFact() != null
                && visitEventFact.getScheduleEventFact().getScheduleEventSK() != null) {

            String sql = "SELECT * FROM SCHED_EVNT WHERE SCHED_EVNT_SK = %s " +
                    " AND ( TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'" +
                    " AND CURR_REC_IND = 1)";
            sql = String.format(sql,visitEventFact.getScheduleEventFact().getScheduleEventSK());
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            int result = -999;

            try {
                connection = getOracleConnection();
                connection.setAutoCommit(true);
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                List<ScheduleEvent> scheduleEvents = (List<ScheduleEvent>) new DataMapper().map(resultSet, ScheduleEvent.class.getCanonicalName());

                if (scheduleEvents != null && !scheduleEvents.isEmpty()) {
                    ScheduleEvent scheduleEvent = scheduleEvents.get(0);
                    //set data to fact object
                    scheduleEventFact.setScheduleSK(scheduleEvent.getScheduleSK());
                    scheduleEventFact.setScheduleEventSK(scheduleEvent.getScheduleEventSK());
                    scheduleEventFact.setScheduleEventStartDatetime(scheduleEvent.getScheduleEventStartDatetime());
                    scheduleEventFact.setScheduleEventEndDatetime(scheduleEvent.getScheduleEventEndDatetime());
                }

            exchange.getIn().setBody(scheduleEventFact);

            } catch (Exception e) {

                // Rollback
                if (connection != null) {

                    try {
                        connection.rollback();
                    } catch (SQLException sqle) {
                        sqle.printStackTrace();
                        visitVerificationLogger.info(sqle.getMessage());
                    }
                }

                String errMsg = String.format("%s: %s",e.getClass().getName(), e.getMessage());
                visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
                   		, this.getClass(), "getScheduleEventBySk", errMsg));
                throw new SandataRuntimeException(errMsg,e);

            } finally {
                // Close the ResultSet
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (SQLException sqle) {
                        sqle.printStackTrace();
                        visitVerificationLogger.info(sqle.getMessage());
                    }
                }
                // Close the statement
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException sqle) {
                        sqle.printStackTrace();
                        visitVerificationLogger.info(sqle.getMessage());
                    }
                }
                // Close the connection
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException sqle) {
                        sqle.printStackTrace();
                        visitVerificationLogger.info(sqle.getMessage());
                    }
                }
            }
        }

    }

    
    
    
    
    
    public void getStaffByID (final Exchange exchange) {
        StaffFact staffFact = new StaffFact();

        VisitEventFact visitEventFact = exchange.getIn().getBody(VisitEventFact.class);

        if (visitEventFact.getStaffID() != null) {

            String sql = "SELECT * FROM STAFF WHERE STAFF_ID = ? " +
                    " AND ( TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'" +
                    " AND CURR_REC_IND = 1)";
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            int result = -999;

            try {
                connection = getOracleConnection();
                connection.setAutoCommit(true);
                preparedStatement = connection.prepareStatement(sql);
                int index = 1;
                preparedStatement.setString(index++, visitEventFact.getStaffID());
                resultSet = preparedStatement.executeQuery();
                List<Staff> staffList = (List<Staff>) new DataMapper().map(resultSet, Staff.class.getCanonicalName());
                if (staffList != null && !staffList.isEmpty()) {
                    staffFact.setStaffId(staffList.get(0).getStaffID());
                }

            } catch (Exception e) {

                // Rollback
                if (connection != null) {

                    try {
                        connection.rollback();
                    } catch (SQLException sqle) {
                        sqle.printStackTrace();
                        visitVerificationLogger.info(sqle.getMessage());
                    }
                }

                String errMsg = String.format("%s: %s",e.getClass().getName(), e.getMessage());
                visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
                   		, this.getClass(), "getStaffByID", errMsg));
                throw new SandataRuntimeException(errMsg,e);

            } finally {
                // Close the ResultSet
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (SQLException sqle) {
                        sqle.printStackTrace();
                        visitVerificationLogger.info(sqle.getMessage());
                    }
                }
                // Close the statement
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException sqle) {
                        sqle.printStackTrace();
                        visitVerificationLogger.info(sqle.getMessage());
                    }
                }
                // Close the connection
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException sqle) {
                        sqle.printStackTrace();
                        visitVerificationLogger.info(sqle.getMessage());
                    }
                }
            }
        }

        exchange.getIn().setBody(staffFact);
    }


    public void getPatientByID (final Exchange exchange) {
        PatientFact patientFact = new PatientFact();

        VisitEventFact visitEventFact = exchange.getIn().getBody(VisitEventFact.class);

        if (visitEventFact.getPatientID() != null) {

            String sql = "SELECT * FROM PT WHERE PT_ID = ? " +
                    " AND ( TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'" +
                    " AND CURR_REC_IND = 1)";
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            int result = -999;

            try {
                connection = getOracleConnection();
                connection.setAutoCommit(true);
                preparedStatement = connection.prepareStatement(sql);
                int index = 1;
                preparedStatement.setString(index++, visitEventFact.getPatientID());
                resultSet = preparedStatement.executeQuery();
                List<Patient> patientList = (List<Patient>) new DataMapper().map(resultSet, Patient.class.getCanonicalName());
                if (patientList != null && !patientList.isEmpty()) {
                    patientFact.setPatientId(patientList.get(0).getPatientID());
                }

            } catch (Exception e) {

                // Rollback
                if (connection != null) {

                    try {
                        connection.rollback();
                    } catch (SQLException sqle) {
                        sqle.printStackTrace();
                        visitVerificationLogger.info(sqle.getMessage());
                    }
                }

                String errMsg = String.format("%s: %s",e.getClass().getName(), e.getMessage());
                visitVerificationLogger.info(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_VV_DATA_SERVICE
                   		, this.getClass(), "getPatientByID", errMsg));
                throw new SandataRuntimeException(errMsg,e);


            } finally {
                // Close the ResultSet
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (SQLException sqle) {
                        sqle.printStackTrace();
                        visitVerificationLogger.info(sqle.getMessage());
                    }
                }
                // Close the statement
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException sqle) {
                        sqle.printStackTrace();
                        visitVerificationLogger.info(sqle.getMessage());
                    }
                }
                // Close the connection
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException sqle) {
                        sqle.printStackTrace();
                        visitVerificationLogger.info(sqle.getMessage());
                    }
                }
            }
        }

        exchange.getIn().setBody(patientFact);
    }

    /**
     * Find VisitFact instance in an enrich Exchange message (which contains list of objects)
     *
     * @param exchange
     */
    protected VisitFact findVisitFactInEnrichedExchange(final Exchange exchange) {

        for (Object object : exchange.getIn().getBody(List.class)) {
            if (object instanceof VisitFact) {
                return (VisitFact) object;
            }
        }

        return null;
    }
}
