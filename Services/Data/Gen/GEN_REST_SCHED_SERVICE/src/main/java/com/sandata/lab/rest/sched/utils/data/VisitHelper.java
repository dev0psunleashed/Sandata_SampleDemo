package com.sandata.lab.rest.sched.utils.data;


import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.sandata.lab.common.utils.string.StringUtil;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.ScheduleEvent;
import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.data.model.jpub.model.VisitT;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.sched.impl.OracleDataService;
import com.sandata.lab.rest.sched.model.SchedUpdateStaff;
import com.sandata.lab.rest.sched.utils.templates.ProducerPojo;

import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

@SuppressWarnings("unchecked")

public class VisitHelper {

    Logger logger = LoggerFactory.getLogger("VisitHelperLogger");

    private Connection connection;
    private OracleDataService oracleDataService;
    private Visit visit;


    public VisitHelper(Connection oracleConnection, ScheduleEvent scheduleEvent) {
        this.connection = oracleConnection;

        prepareVisit(scheduleEvent.getBusinessEntityID(), scheduleEvent.getScheduleEventSK(), scheduleEvent.getPatientID(),
                scheduleEvent.getStaffID(), scheduleEvent.getChangeReasonMemo(), scheduleEvent.getRecordCreatedBy());
    }

    public VisitHelper(OracleDataService oracleConnection, SchedUpdateStaff schedUpdateStaff) {
        this.oracleDataService = oracleConnection;

        prepareVisit(schedUpdateStaff.getBusinessEntityID(), schedUpdateStaff.getScheduleEventSK(), null,
                schedUpdateStaff.getStaffID(), null, null);

    }

    private void prepareVisit(String bsnEntID, BigInteger schedEventSK, String patientID, String staffId, String changeReasonMemo, String updateOrCreatedBy) {

        this.visit = new Visit();

        this.visit.setBusinessEntityID(bsnEntID);
        this.visit.setScheduleEventSK(schedEventSK);
        this.visit.setPatientID(patientID);
        this.visit.setStaffID(staffId);
        this.visit.setChangeVersionID(BigInteger.ONE);
        this.visit.setChangeReasonMemo(changeReasonMemo);

        this.visit.setRecordCreatedBy(StringUtil.IsNullOrEmpty(updateOrCreatedBy) ? "GEN_REST_SCHED_SERVICE" : updateOrCreatedBy);
        this.visit.setRecordUpdatedBy(StringUtil.IsNullOrEmpty(updateOrCreatedBy) ? "GEN_REST_SCHED_SERVICE" : updateOrCreatedBy);

    }

    public static Response deleteAllVisitsForSchedule(OracleDataService oracleDataService, long scheduleSk, int[] weekdays, SandataLogger logger) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            Response response = new Response();

            ArrayDescriptor arrayDescriptor = ArrayDescriptor.createDescriptor(ConnectionType.COREDATA + ".NUMBER_ARRAY", connection);
            ARRAY weekDayArray = new ARRAY(arrayDescriptor, connection, weekdays);

            String callMethod = "{?=call PKG_SCHEDULE_UTIL.DELETE_ALL_VISITS_FOR_SCHED(?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);

            int index = 2;
            callableStatement.setLong(index++, scheduleSk);
            callableStatement.setArray(index++, weekDayArray);

            callableStatement.execute();
            long resultValue = callableStatement.getLong(1);

            if (resultValue < 0) {
                connection.rollback();
                response.setData("FAILED");
                return response;
            }

            connection.commit();
            response.setData("SUCCESS");
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
            throw new SandataRuntimeException(String.format("%s: %s", e.getClass().getName(), e.getMessage()));

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
     * This method will not throw any exception to stop main working flow
     * @param exchange
     * @param visitSK
     */
    public void triggerCheckVisitException(Exchange exchange, long visitSK){

        if(exchange == null || exchange.getContext() == null){
            logger.error(String.format("VisitHelper:triggerCheckVisitException - Cannot trigger to check exception for Visit with visitSK=%d. Exception: %s",
                    visitSK, "The input Exchange or Context cannot be null"));

            //Don't throw the exception to stop main flow
            return;
        }

        ProducerPojo producerPojo = new ProducerPojo(exchange);
        triggerCheckVisitException(producerPojo, visitSK);
    }

    /**
     * This method will not throw any exception to stop main working flow
     * @param producerTemplate
     * @param visitSK
     */
    public void triggerCheckVisitException(ProducerPojo producerTemplate, long visitSK){
        if(producerTemplate == null || visitSK <= 0){

            logger.error(String.format("VisitHelper:triggerCheckVisitException - Cannot trigger to check exception for Visit with visitSK=%d. Exception: %s",
                    visitSK, "The ProducerTemplate cannot be null"));
            return;
        }

        this.visit.setVisitSK(BigInteger.valueOf(visitSK));
        try {
            producerTemplate.sendVisit(this.visit);
        } catch (Exception ex) {
            logger.error(String.format("VisitHelper:triggerCheckVisitException - Error while sending Visit [Visit_SK = %d] to the queues of Visit Verification", visitSK));
        }

    }

    public long updateVisit(BigInteger scheduleSk) {
        Date now = new Date();
        this.visit.setRecordUpdateTimestamp(now);
        long retValue = updateOracleFunction(this.visit, scheduleSk);

        return retValue;
    }

    /**
     * Update Visit using either scheduleSK or scheduleEventSK
     *
     * @param schedUpdateStaff
     * @return
     */
    public long updateVisit(SchedUpdateStaff schedUpdateStaff) {

        //This Date() will be in UTC format
        this.visit.setRecordUpdateTimestamp(new Date());
        this.visit.setChangeReasonMemo("updateStaffIdForSchedule");

        long retValue = updateOracleFunction(this.visit, schedUpdateStaff);

        return retValue;
    }

    public long createVisit() {

        Date now = new Date();
        this.visit.setRecordCreateTimestamp(now);
        this.visit.setRecordUpdateTimestamp(now);

        long retValue = createOracleFunction(this.visit);

        return retValue;
    }

    private long deleteVisit(java.util.Date termDate) throws SandataRuntimeException {
        throw new SandataRuntimeException("Not Implemented!!");
    }

    private long createOracleFunction(Visit visit) {

        long returnVal = -999;
        Connection connection = this.connection;
        CallableStatement callableStatement = null;
        try {

            Object jpubType;
            OracleMetadata oracleMetadata;
            oracleMetadata = DataMapper.getOracleMetadata(visit);
            jpubType = new DataMapper().map(visit);
            setSk(jpubType, returnVal, "setVisitSk");
            logger.info("=====================================================");
            logger.info("==Creating Empty Visit from schedule event creation==");
            logger.info("=====================================================");
            if (jpubType instanceof VisitT) {
                VisitT visitTyp = (VisitT) jpubType;
                long result = 0;
                String callMethod = String.format("{?=call %s.%s(?)}", oracleMetadata.packageName(), oracleMetadata.insertMethod());
                callableStatement = connection.prepareCall(callMethod);
                callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
                callableStatement.setObject(2, jpubType);
                callableStatement.execute();
                result = callableStatement.getLong(1);
                logger.info(String.format("Visit creation with SK = (%d)", result));

                if (result > 0)
                    returnVal = result;
            } else {
                logger.error("Expected JPUBTYPE of VISITTYP WAS NOT RETURNED!!");
            }
            return returnVal;
        } catch (Exception e) {
            String errMsg = String.format("%s: %s", getClass().getName(), e.getMessage());
            //throw new SandataRuntimeException(errMsg);
            logger.error(String.format("ERROR CREATING EMPTY VISIT FOR SCHEDULE:: (%s)", errMsg));

        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            logger.info("finsished");
        }
        return returnVal;
    }

    private void setSk(final Object jpubType, long sequenceKey, String setSkMethodName) throws Exception {
        if (sequenceKey <= 0) {
            return;
        }

        try {

            Method method = jpubType.getClass().getDeclaredMethod(setSkMethodName, BigDecimal.class);
            method.invoke(jpubType, BigDecimal.valueOf(sequenceKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private long updateOracleFunction(Visit visit, BigInteger scheduleSk) {

        long result = 0;
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = this.connection;

            VisitT visitT = new VisitT();
            visitT.setRecUpdateTmstp(new java.sql.Timestamp(visit.getRecordUpdateTimestamp().getTime()));
            visitT.setRecUpdatedBy(visit.getRecordUpdatedBy());
            visitT.setChangeReasonMemo(visit.getChangeReasonMemo());

            //find out what change
            //this was an empty visit if its changing form schedule there are only certain things that can change.
            //right now we can only change staffId
            if (visit.getStaffID() != null) {
                visitT.setStaffId(visit.getStaffID());
            }

            String callMethod = "{?=call PKG_SCHEDULE_UTIL.UPDATE_VISIT_BY_SCHED_SK(?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);

            int index = 2;
            callableStatement.setObject(index++, visitT);
            callableStatement.setLong(index++, scheduleSk.longValue());

            callableStatement.execute();
            result = callableStatement.getLong(1);

        } catch (Exception e) {

            logger.error("VisitEventRepository::updateVisit() Exception :: " + e.getLocalizedMessage());

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()));
        } finally {

            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sex) {
                    sex.printStackTrace();
                }
            }
        }

        return result;
    }

    /**
     * Update Visit using either scheduleSK or scheduleEventSK
     *
     * @param visit
     * @param schedUpdateStaff
     * @return
     */
    private long updateOracleFunction(Visit visit, SchedUpdateStaff schedUpdateStaff) {

        long result = 0;
        Connection connection = null;

        CallableStatement callableStatement = null;

        String callMethod = "";
        BigInteger updateSk;
        // When scheduleEventSK is not null, just update for a single ScheduleEvent
        if (schedUpdateStaff.getScheduleEventSK() != null && schedUpdateStaff.getScheduleEventSK().intValue() > 0) {
            callMethod = "{?=call PKG_SCHEDULE_UTIL.UPDATE_VISIT_BY_SCHED_EVNT_SK(?,?)}";
            updateSk = schedUpdateStaff.getScheduleEventSK();

        } else { // When scheduleEventSK is null and scheduleSK is not null, update all ScheduleEvents belong to the Schedule
            callMethod = "{?=call PKG_SCHEDULE_UTIL.UPDATE_VISIT_BY_SCHED_SK(?,?)}";
            updateSk = schedUpdateStaff.getScheduleSK();
        }

        try {

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            VisitT visitT = new VisitT();
            visitT.setRecUpdateTmstp(new java.sql.Timestamp(visit.getRecordUpdateTimestamp().getTime()));
            visitT.setRecUpdatedBy(visit.getRecordUpdatedBy());
            visitT.setChangeReasonMemo(visit.getChangeReasonMemo());

            //find out what change
            //this was an empty visit if its changing form schedule there are only certain things that can change.
            //right now we can only change staffId
            if (visit.getStaffID() != null) {
                visitT.setStaffId(visit.getStaffID());
            }

            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);

            int index = 2;
            callableStatement.setObject(index++, visitT);
            callableStatement.setLong(index++, updateSk.longValue());

            callableStatement.execute();
            result = callableStatement.getLong(1);

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

            logger.error("VisitEventRepository::updateVisit() Exception :: " + e.getLocalizedMessage());
            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()));
        } finally {

            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sex) {
                    sex.printStackTrace();
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

        return result;
    }
}
