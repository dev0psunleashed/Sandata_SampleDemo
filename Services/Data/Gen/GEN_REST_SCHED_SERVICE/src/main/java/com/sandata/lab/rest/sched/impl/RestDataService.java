package com.sandata.lab.rest.sched.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.date.DateUtil;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.common.utils.rest.RestUtil;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.constants.audit.Audit;
import com.sandata.lab.data.model.data.Compare;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.extended.ScheduleEventExt;
import com.sandata.lab.data.model.dl.model.extended.schedule.ScheduleEvntExt;
import com.sandata.lab.data.model.dl.model.extended.schedule.ScheduleExt;
import com.sandata.lab.data.model.dl.model.extended.schedule.SelectedDaysOfWeek;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.rest.sched.api.DataService;
import com.sandata.lab.rest.sched.model.*;
import com.sandata.lab.rest.sched.utils.ScheduleUtils;
import com.sandata.lab.rest.sched.utils.SelectedDaysOfWeekBuilder;
import com.sandata.lab.rest.sched.utils.data.VisitExceptionHelper;
import com.sandata.lab.rest.sched.utils.data.VisitHelper;
import com.sandata.lab.rest.sched.utils.log.OracleDataLogger;
import com.sandata.lab.rest.sched.utils.templates.ProducerPojo;

import org.apache.camel.Exchange;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.cxf.message.MessageContentsList;
import org.joda.time.LocalTime;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.sandata.lab.rest.sched.utils.log.OracleDataLogger.CreateLogger;
import static java.lang.String.format;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */
@SuppressWarnings("unchecked")
public class RestDataService implements DataService {

    private OracleDataService oracleDataService;
    private ProducerPojo producerPojo;

    public void updateStaffIdForSchedule(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            SchedUpdateStaff schedUpdateStaff = (SchedUpdateStaff) exchange.getIn().getBody();

            BigInteger scheduleSK = schedUpdateStaff.getScheduleSK();
            BigInteger scheduleEventSK = schedUpdateStaff.getScheduleEventSK();
            if ((scheduleSK == null || scheduleSK.longValue() <= 0)
                    && (scheduleEventSK == null || scheduleEventSK.longValue() <= 0)) {
                throw new SandataRuntimeException("ScheduleSK OR ScheduleEventSK is required! Both can not be null!");
            }

            String staffID = schedUpdateStaff.getStaffID();
            if (staffID == null || staffID.length() == 0) {
                throw new SandataRuntimeException("StaffID is required!");
            }

            String bsnEntId = schedUpdateStaff.getBusinessEntityID();
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID is required!");
            }

            long resultVal = oracleDataService.updateStaffIdForSchedule(schedUpdateStaff, logger);
            exchange.getIn().setBody(resultVal);

            VisitHelper visitHelper = new VisitHelper(oracleDataService, schedUpdateStaff);
            long visitSK = visitHelper.updateVisit(schedUpdateStaff);

            VisitExceptionHelper.triggerCheckVisitException(exchange, visitSK);

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    @Override
    public void saveSchedEvents(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);
        logger.start();

        Connection connection = null;
        try {
            List<ScheduleEvent> scheduleEvents = (List) exchange.getIn().getBody();

            List<SaveScheduleEventsResult> results = new ArrayList<>();

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            for (int index = 0; index < scheduleEvents.size(); index++) {

                SaveScheduleEventsResult scheduleEventsResult = new SaveScheduleEventsResult();
                scheduleEventsResult.setIndex(index);
                scheduleEventsResult.setScheduleEventSk(-1);
                results.add(scheduleEventsResult);

                try {
                    ScheduleEvent scheduleEvent = scheduleEvents.get(index);

                    ScheduleUtils.populateScheduleEventTotalHours(scheduleEvent);

                    long returnVal = executeRecursive(connection, scheduleEvent, true, -999, null, exchange, logger);

                    scheduleEventsResult.setScheduleEventSk(returnVal);
                    if (scheduleEvent.getScheduleEventSK() == null || scheduleEvent.getScheduleEventSK().intValue() != returnVal) {
                        scheduleEvent.setScheduleEventSK(BigInteger.valueOf(returnVal));
                    }

                    VisitHelper visitHelper = new VisitHelper(connection, scheduleEvent);
                    visitHelper.createVisit();

                    connection.commit();

                } catch (Exception ex) {

                    String errorMessage = format("[EXCEPTION=%s]: [MESSAGE=%s]: [Index=%d]",
                            getClass().getName(), ex.getMessage(), index);
                    scheduleEventsResult.setErrorMessage(errorMessage);
                }
            }

            exchange.getIn().setBody(results);
        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    @Override
    public void deleteSchedule(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList params = (MessageContentsList) exchange.getIn().getBody();

            String scheduleSk = (String) params.get(0);
            if (scheduleSk == null || scheduleSk.length() == 0) {

                throw new SandataRuntimeException("ScheduleSK is required!");
            }

            long scheduleSkInt = Long.parseLong(scheduleSk);

            String weekDays = (String) params.get(1);
            if (weekDays == null || weekDays.length() == 0) {

                throw new SandataRuntimeException("WeekDays is required!");
            }

            String[] weekDaysStrings = weekDays.split(",");
            if (weekDaysStrings.length == 0) {
                throw new SandataRuntimeException("WeekDays.length == 0");
            }

            // If the week days aren't numbers, this will throw an exception
            int[] weekDaysInts = new int[weekDaysStrings.length];
            int index = 0;
            for (String weekDay : weekDaysStrings) {
                int weekDayInt = Integer.parseInt(weekDay);

                // Validate weekDayInt is between 1 and 7
                if (weekDayInt < 1 || weekDayInt > 7) {
                    throw new SandataRuntimeException(format("[%d] WeekDay is not in range! [1 .. 7]", weekDayInt));
                }

                weekDaysInts[index++] = weekDayInt;
            }

            Response response = oracleDataService.deleteSchedule(scheduleSkInt, weekDaysInts);
            if (response != null && response.getData() != null && response.getData().toString().length() > 0 && response.getData().toString().equals("SUCCESS")) {
                Response response2 = VisitHelper.deleteAllVisitsForSchedule(oracleDataService, scheduleSkInt, weekDaysInts, logger);
                if (response2 != null && response2.getData() != null && response2.getData().toString().length() > 0 && response2.getData().toString().equals("FAILED")) {
                    logger.error(format("COULD NOT PERFORM LOGICAL DELETE OF VISITS ON LOGICAL DELETE OF SCHEDULE (%s) ", scheduleSk));
                }
            }
            exchange.getIn().setBody(response.getData());
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    @Override
    public void findSchedule(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList params = (MessageContentsList) exchange.getIn().getBody();

            String staffId = (String) params.get(0);

            String patientId = (String) params.get(1);
            if (patientId == null || patientId.length() == 0) {

                // Patient is only required if Staff is not provided
                if (staffId == null || staffId.length() == 0) {
                    throw new SandataRuntimeException("StaffID (staff_id) OR PatientID (patient_id) is required. At least one must be provided!");
                }
            }

            String bsnEntId = (String) params.get(2);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            Long authSk = (Long) params.get(3);

            String startDateTimeUTCStr = (String) params.get(4);
            String endDateSTimeUTCtr = (String) params.get(5);

            // Convert to Date to check format
            DateFormat format = new SimpleDateFormat(DateUtil.SANDATA_UTC_DATE_TIME_FORMAT, Locale.ENGLISH);
            Date startDate = null;
            Date endDate = null;
            if (!StringUtil.IsNullOrEmpty(startDateTimeUTCStr)) {
                try {
                    startDate = format.parse(startDateTimeUTCStr);
                } catch (ParseException pe) {
                    throw new SandataRuntimeException(
                            format("Start Date: [%s]: Is NOT a valid date and/or the format is incorrect, format: [%s]!",
                                    startDateTimeUTCStr, DateUtil.SANDATA_UTC_DATE_TIME_FORMAT));
                }

                // Make sure End Date is provided also!
                if (StringUtil.IsNullOrEmpty(endDateSTimeUTCtr)) {
                    throw new SandataRuntimeException(
                            format("End Date is required because Start Date [%s] has been provided!", startDateTimeUTCStr));
                }
            } else {
                startDate = null;
                endDate = null;
            }

            if (!StringUtil.IsNullOrEmpty(endDateSTimeUTCtr)) {
                try {
                    //dmr 10-14-2015: Increment the endDate by one day
                    Date date = format.parse(endDateSTimeUTCtr);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    calendar.add(Calendar.DATE, 1); // add one day
                    endDate = calendar.getTime();
                } catch (ParseException pe) {
                    throw new SandataRuntimeException(
                            format("Start Date: [%s]: Is NOT a valid date and/or the format is incorrect, format: [%s]!",
                                    endDateSTimeUTCtr, DateUtil.SANDATA_UTC_DATE_TIME_FORMAT));
                }
            }

            int page = (Integer) params.get(6);
            int pageSize = (Integer) params.get(7);

            String orderByColumn = (String) params.get(8);
            String orderByDirection = (String) params.get(9);
            
            List<String> statuses = (List<String>) params.get(10);
            List<String> validStatuses = new ArrayList<>();
            if (statuses != null && !statuses.isEmpty()) {
                for (String status : statuses) {
                    if (status != null && !status.isEmpty()) {
                        validStatuses.add(status);
                    }
                }
            }

            Response response = oracleDataService.findSchedule(
                    staffId,
                    patientId,
                    bsnEntId,
                    authSk,
                    page,
                    pageSize,
                    startDate,
                    endDate,
                    orderByColumn,
                    orderByDirection,
                    validStatuses
            );

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    @Override
    public void findStaff(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            StringBuilder filterTyp = new StringBuilder();

            List<Object> params = new ArrayList<>();

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();


            String bsnEntId = (String) mcl.get(5);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            String language = (String) mcl.get(0);


            String service = ""; //SAN-1643: HOTFIX to ignore the check for service for Archer.
            if (service.length() > 0) {
                params.add(service.toUpperCase());
                filterTyp.append("(UPPER(T1.STAFF_POSITION_NAME) = ?) AND ");
            }

            String keywordString = (String) mcl.get(2);
            if (keywordString.length() > 0) {

                String[] keywords = keywordString.split(" ");
                if (keywords.length >= 2) {

                    // Example: Tom Hanks
                    params.add(keywords[0].toUpperCase() + "%");
                    params.add(keywords[1].toUpperCase() + "%");

                    // Flip params around
                    // Example: Hanks Tom
                    params.add(keywords[1].toUpperCase() + "%");
                    params.add(keywords[0].toUpperCase() + "%");

                    // NOTE:    If there is a third word or more, it will be ignored!
                    //          Keyword is expected to be a name, first/last.
                    //          Check first/last in any order. For example: Tom Hanks or Hanks Tom
                    filterTyp.append("((UPPER(T1.STAFF_FIRST_NAME) LIKE ? AND UPPER(T1.STAFF_LAST_NAME) LIKE ?) OR " +
                            "(UPPER(T1.STAFF_FIRST_NAME) LIKE ? AND UPPER(T1.STAFF_LAST_NAME) LIKE ?)) AND ");
                } else {

                    params.add(keywords[0].toUpperCase() + "%");
                    params.add(keywords[0].toUpperCase() + "%");

                    filterTyp.append("(UPPER(T1.STAFF_FIRST_NAME) LIKE ? " +
                            "OR UPPER(T1.STAFF_LAST_NAME) LIKE ?) AND ");
                }

            }

            // The status of Schedule Find Staff must be Active of the time range, the time range may be in the past
            // so currently we don't use this for filtering
            String employmentStatusTypeName = (String) mcl.get(6);

            long noConflictedWithScheduleSK = (long) mcl.get(7);
            long noConflictedWithEventSK = (long) mcl.get(8);
            String eventDateFromUTCString = (String) mcl.get(9);
            String eventDateToUTCString = (String) mcl.get(10);
            
            String eventDateFromOracleFormat = null;
            String eventDateToOracleFormat = null;
            
            if (!StringUtil.IsNullOrEmpty(eventDateFromUTCString) && !StringUtil.IsNullOrEmpty(eventDateToUTCString)) {
                eventDateFromOracleFormat = DateUtil.convertStringUTCtoStringDateTime(eventDateFromUTCString, "event_date_from");
                eventDateToOracleFormat = DateUtil.convertStringUTCtoStringDateTime(eventDateToUTCString, "event_date_to");
            } else if (!StringUtil.IsNullOrEmpty(eventDateFromUTCString) && StringUtil.IsNullOrEmpty(eventDateToUTCString)) {
                throw new SandataRuntimeException("Event From Date (event_date_from) is provided so Event To Date (event_date_to) is required!");
            } else if (StringUtil.IsNullOrEmpty(eventDateFromUTCString) && !StringUtil.IsNullOrEmpty(eventDateToUTCString)) {
                throw new SandataRuntimeException("Event To Date (event_date_to) is provided so Event From Date (event_date_from) is required!");
            }

            int page = (Integer) mcl.get(3);
            int pageSize = (Integer) mcl.get(4);

            String orderByColumn = language.toUpperCase();

            Response response = oracleDataService.findStaff(
                    filterTyp.toString(),
                    bsnEntId,
                    params,
                    orderByColumn,
                    page,
                    pageSize,
                    noConflictedWithScheduleSK,
                    noConflictedWithEventSK,
                    eventDateFromOracleFormat,
                    eventDateToOracleFormat,
                    null, // always null in find staff endpoint
                    null // always null in find staff endpoint
            );

            // Find StaffLanguage list
            //dmr--I don't believe we need all the languages that the staff can speak but only their primary language which is handled in findStaff
            /*List<FindStaffResult> findStaffResultList = (List<FindStaffResult>)response.getData();
            for (FindStaffResult result : findStaffResultList) {
            	result.setStaffLanguages((List<StaffLanguage>)oracleDataService.getEntitiesForId(
            			"SELECT * FROM STAFF_LANG WHERE BE_ID = ? AND STAFF_ID = ? "
            					+ "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)", 
            			"com.sandata.lab.data.model.dl.model.StaffLanguage", 
            			bsnEntId, result.getStaffID()));
            }*/

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    @Override
    public void get(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String operationName = (String) exchange.getIn().getHeader("operationName");

            String[] methodParts = operationName.split("_");

            String packageName = methodParts[0] + "_" + methodParts[1];
            String methodName = methodParts[2];
            String className = "com.sandata.lab.data.model.dl.model." + methodParts[3];

            Object result;
            Object body = exchange.getIn().getBody();

            if (exchange.getIn().getHeader("sequence_key") != null) {

                long sequenceKey = (long) exchange.getIn().getHeader("sequence_key");

                result = oracleDataService.executeGet(
                        packageName,
                        methodName,
                        className,
                        sequenceKey
                );

                ArrayList result2 = (ArrayList<Object>) result;

                if (result2 != null && result2.size() > 0) {
                    result = result2.get(0);

                    if (className.equals("com.sandata.lab.data.model.dl.model.ScheduleEvent")) {

                        exchange.getIn().setBody(processScheduleEvents((List<ScheduleEvent>) result2, logger));
                    } else {
                        exchange.getIn().setBody(result);
                    }
                } else {
                    exchange.getIn().setBody(null);
                }
            } else {
                MessageContentsList mcl = (MessageContentsList) body;

                if (className.equals("com.sandata.lab.data.model.dl.model.Schedule")) {

                    String patientId = (String) mcl.get(0);
                    if (patientId == null || patientId.length() == 0) {
                        throw new SandataRuntimeException("PatientID (patient_id) is required!");
                    }

                    String staffId = (String) mcl.get(1);

                    String bsnEntId = (String) mcl.get(2);
                    if (bsnEntId == null || bsnEntId.length() == 0) {
                        throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
                    }

                    if (staffId != null) {
                        List<Schedule> scheduleList = (List<Schedule>) oracleDataService.getEntitiesForId(
                                "SELECT * FROM SCHED WHERE PT_ID = ? AND BE_ID = ? AND STAFF_ID = ?",
                                "com.sandata.lab.data.model.dl.model.Schedule",
                                patientId,
                                bsnEntId,
                                staffId
                        );

                        exchange.getIn().setBody(scheduleList);
                    } else {
                        List<Schedule> scheduleList = (List<Schedule>) oracleDataService.getEntitiesForId(
                                "SELECT * FROM SCHED WHERE PT_ID = ? AND BE_ID = ?",
                                "com.sandata.lab.data.model.dl.model.Schedule",
                                patientId,
                                bsnEntId
                        );

                        exchange.getIn().setBody(scheduleList);
                    }
                } else if (className.equals("com.sandata.lab.data.model.dl.model.ScheduleEvent")) {

                    Long schedSk = (Long) mcl.get(0);
                    if (schedSk == null || schedSk <= 0) {
                        throw new SandataRuntimeException("ScheduleSK (sched_sk) is required!");
                    }

                    String bsnEntId = (String) mcl.get(1);
                    if (bsnEntId == null || bsnEntId.length() == 0) {
                        throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
                    }

                    String patientId = (String) mcl.get(2);
                    String staffId = (String) mcl.get(3);

                    if (!StringUtil.IsNullOrEmpty(patientId) && !StringUtil.IsNullOrEmpty(staffId)) {
                        List<ScheduleEvent> scheduleEventList = (List<ScheduleEvent>) oracleDataService.getEntitiesForId(
                                "SELECT * FROM SCHED_EVNT WHERE SCHED_SK = ? AND PT_ID = ? AND STAFF_ID = ? " +
                                        "AND BE_ID = ?  AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' " +
                                        "AND CURR_REC_IND = 1)",
                                "com.sandata.lab.data.model.dl.model.ScheduleEvent",
                                schedSk,
                                patientId,
                                staffId,
                                bsnEntId
                        );

                        exchange.getIn().setBody(processScheduleEvents(scheduleEventList, logger));

                    } else if (!StringUtil.IsNullOrEmpty(patientId) && StringUtil.IsNullOrEmpty(staffId)) {
                        List<ScheduleEvent> scheduleEventList = (List<ScheduleEvent>) oracleDataService.getEntitiesForId(
                                "SELECT * FROM SCHED_EVNT WHERE SCHED_SK = ? AND PT_ID = ? " +
                                        "AND BE_ID = ?  AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' " +
                                        "AND CURR_REC_IND = 1)",
                                "com.sandata.lab.data.model.dl.model.ScheduleEvent",
                                schedSk,
                                patientId,
                                bsnEntId
                        );

                        exchange.getIn().setBody(processScheduleEvents(scheduleEventList, logger));
                    } else if (!StringUtil.IsNullOrEmpty(staffId) && StringUtil.IsNullOrEmpty(patientId)) {
                        List<ScheduleEvent> scheduleEventList = (List<ScheduleEvent>) oracleDataService.getEntitiesForId(
                                "SELECT * FROM SCHED_EVNT WHERE SCHED_SK = ? AND STAFF_ID = ? " +
                                        "AND BE_ID = ?  AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' " +
                                        "AND CURR_REC_IND = 1)",
                                "com.sandata.lab.data.model.dl.model.ScheduleEvent",
                                schedSk,
                                staffId,
                                bsnEntId
                        );

                        exchange.getIn().setBody(processScheduleEvents(scheduleEventList, logger));
                    } else {
                        List<ScheduleEvent> scheduleEventList = (List<ScheduleEvent>) oracleDataService.getEntitiesForId(
                                "SELECT * FROM SCHED_EVNT WHERE SCHED_SK = ? " +
                                        "AND BE_ID = ?  AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' " +
                                        "AND CURR_REC_IND = 1)",
                                "com.sandata.lab.data.model.dl.model.ScheduleEvent",
                                schedSk,
                                bsnEntId
                        );

                        exchange.getIn().setBody(processScheduleEvents(scheduleEventList, logger));
                    }
                } else {
                    String[] params = new String[mcl.size()];

                    for (int index = 0; index < mcl.size(); index++) {
                        params[index] = (String) mcl.get(index);
                    }

                    result = oracleDataService.executeGet(
                            packageName,
                            methodName,
                            className,
                            params
                    );

                    exchange.getIn().setBody(result);
                }
            }
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    private List<ScheduleEventResponse> processScheduleEvents(
            final List<ScheduleEvent> scheduleEventList, SandataLogger logger) {

        if (scheduleEventList == null) {

            logger.warn("processScheduleEvents: scheduleEventList == null");
            return null;
        }

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        logger.info("*** START ***: processScheduleEvents ...");

        List<ScheduleEventResponse> result = new ArrayList<>();

        for (ScheduleEvent scheduleEvent : scheduleEventList) {

            ScheduleEventResponse response = new ScheduleEventResponse();

            ScheduleEvntExt scheduleEvntExt = new ScheduleEvntExt();
            BeanUtils.copyProperties(scheduleEvent, scheduleEvntExt);

            ScheduleUtils.populateScheduleEventOvernightIndicator(scheduleEvntExt);
            ScheduleUtils.populateScheduleEventTotalHours(scheduleEvntExt);

            response.setScheduleEvent(scheduleEvntExt);
            response.setPatient(oracleDataService.getPatient(scheduleEvent.getPatientID(), scheduleEvent.getBusinessEntityID()));
            response.setStaff(oracleDataService.getStaffExt(scheduleEvent.getStaffID(), scheduleEvent.getBusinessEntityID()));

            if (scheduleEvent.getAuthorizationSK() != null) {
                response.setAuthorization(oracleDataService.getAuthorization(
                        scheduleEvent.getAuthorizationSK().longValue()));
            }

            if (scheduleEvent.getPlanOfCareSK() != null) {
                response.setPlanOfCare(oracleDataService.getPlanOfCareWithTaskList(
                        scheduleEvent.getPlanOfCareSK().longValue()));
            }

            result.add(response);
        }

        stopWatch.stop();
        logger.info(format("*** END ***: processScheduleEvents: [RAN_IN: %s]", stopWatch.toString()));

        return result;
    }

    @Override
    public void delete(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            String operationName = (String) exchange.getIn().getHeader("operationName");

            String[] methodParts = operationName.split("_");

            String packageName = methodParts[0] + "_" + methodParts[1];
            String methodName = methodParts[2];

            long sequenceKey = (long) exchange.getIn().getHeader("sequence_key");

            long result = oracleDataService.execute(
                    packageName,
                    methodName,
                    sequenceKey
            );

            exchange.getIn().setBody(result);
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    @Override
    public void update(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        Connection connection = null;

        logger.start();

        try {
            Object data = exchange.getIn().getBody();

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            if (data instanceof ScheduleEvent) {
                ScheduleEvent scheduleEventToUpdate = (ScheduleEvent) data;
                
                ScheduleUtils.populateScheduleEventTotalHours((ScheduleEvent) data);
                
                List<ScheduleEvent> oldScheduleEventList = (List<ScheduleEvent>) oracleDataService.getEntitiesForId(connection, 
                        "SELECT * FROM SCHED_EVNT WHERE SCHED_EVNT_SK = ?", 
                        "com.sandata.lab.data.model.dl.model.ScheduleEvent", 
                        scheduleEventToUpdate.getScheduleEventSK());
                if (oldScheduleEventList == null || oldScheduleEventList.isEmpty()) {
                    throw new SandataRuntimeException("The Schedule Event to be updated does not exists in database!");
                }
                ScheduleEvent oldScheduleEvent = oldScheduleEventList.get(0);
                
                // delete old one with origin data like staff_id
                logicalDeleteScheduleEventWithRelevantTables(connection, oldScheduleEvent, exchange, logger);
                
                // create new one
                scheduleEventToUpdate = createScheduleEventFromOldEvent(connection, oldScheduleEvent, scheduleEventToUpdate);
                long returnVal = executeRecursiveForScheduleAndEvent(connection, data, true /* INSERT */, -999, scheduleEventToUpdate.getBusinessEntityID(), exchange, logger);
                
                if (returnVal > 0) {
                    exchange.getIn().setBody(returnVal);
                    
                    List<Visit> visitList = (List<Visit>) oracleDataService.getEntitiesForId(connection, 
                            "SELECT * FROM VISIT WHERE SCHED_EVNT_SK = ?", 
                            "com.sandata.lab.data.model.dl.model.Visit", 
                            returnVal);
                    if (visitList != null && !visitList.isEmpty()) {
                        VisitExceptionHelper.triggerCheckVisitException(exchange, visitList.get(0).getVisitSK().intValue());
                    }
                    
                    connection.commit();
                    
                    return;
                } else {
                    throw new SandataRuntimeException("Update Schedule Event was not successful!");
                }
            }

            // Normal update
            long returnVal = executeRecursive(connection, data, false /* UPDATE */, -999, null, exchange, logger);

            if (returnVal > 0) {
                exchange.getIn().setBody(returnVal);
                connection.commit();
            } else {
                throw new SandataRuntimeException("Update was not successful!");
            }
        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    @Override
    public void insert(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        Connection connection = null;

        logger.start();

        try {
            Object data = exchange.getIn().getBody();

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            if (data instanceof ScheduleEvent) {
                ScheduleUtils.populateScheduleEventTotalHours((ScheduleEvent) data);
            }

            long returnVal = executeRecursive(connection, data, true, -999, null, exchange, logger);
            if (returnVal > 0) {
                exchange.getIn().setBody(returnVal);

                if (data instanceof ScheduleEvent) {
                    ScheduleEvent scheduleEvent = (ScheduleEvent) data;
                    scheduleEvent.setScheduleEventSK(BigInteger.valueOf(returnVal));

                    VisitHelper visitHelper = new VisitHelper(connection, scheduleEvent);
                    visitHelper.createVisit();
                }

                connection.commit();
            } else {
                throw new SandataRuntimeException("Insert was not successful!");
            }
        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
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

    private long executeRecursive(final Connection connection, final Object data, final boolean bShouldInsert, long returnVal,
                                 String bsnEntId, Exchange exchange, SandataLogger logger)
            throws SandataRuntimeException {

        try {
            // GEOR-6612
            String timezoneFieldErrMsg = RestUtil.validateRequiredTimezoneName(data);
            if (timezoneFieldErrMsg.length() > 0) {
                throw new SandataRuntimeException(timezoneFieldErrMsg);
            }
            
            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(data);

            Object jpubType = new DataMapper().map(data);

            setSk(jpubType, returnVal, "setSchedSk");

            long result = 0;

            if (bShouldInsert) {

                result = oracleDataService.execute(
                        connection,
                        oracleMetadata.packageName(),
                        oracleMetadata.insertMethod(),
                        jpubType
                );
                if (data instanceof ScheduleEvent) {
                    ScheduleEvent scheduleEvent = (ScheduleEvent) data;
                    scheduleEvent.setScheduleEventSK(BigInteger.valueOf(result));
                }
            } else {

                BigInteger skValue = DataMapper.GetSK(data);

                if (skValue == null) {
                    throw new SandataRuntimeException(String.format("%s: executeRecursive: skValue == NULL: [Type: %s]",
                            getClass().getName(), data.getClass().getName()));
                }

                // Since this is a recursive call, the first data element will have the bsnEntId, the children may not but they are related by the same parent SK
                if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                    // Init once
                    bsnEntId = DataMapper.GetBusinessEntityID(data);
                }

                returnVal = skValue.intValue();

                Compare compare = null;
                // bsnEntId is require, so only process if it exists!
                if (!StringUtil.IsNullOrEmpty(bsnEntId)) {
                    //TODO: Need to consider different approach since this may slow the service down
                    //dmr--For audit reasons, we need to get the current record before doing an update to it.
                    List currentStateList = (List) oracleDataService.executeGet(
                            oracleMetadata.packageName(),
                            oracleMetadata.getMethod(),
                            data.getClass().getName(),
                            skValue.intValue()
                    );

                    if (currentStateList.size() < 1) {
                        // Since this is an update, this should not happen!
                        throw new SandataRuntimeException(String.format("%s: executeRecursive: currentStateList is EMPTY!", getClass().getName()));
                    }

                    compare = new Compare();
                    compare.setOriginal(currentStateList.get(0));
                    compare.setUpdated(data);

                } else {
                    logger.warn(String.format("%s: executeRecursive: bsnEntId is NULL or EMPTY!", data.getClass().getName()));
                }

                // UPDATE
                result = oracleDataService.execute(
                        connection,
                        oracleMetadata.packageName(),
                        oracleMetadata.updateMethod(),
                        jpubType
                );

                if (result > 0 && compare != null) {

                    exchange.getIn().setHeader("AuditBsnEntID", bsnEntId);
                    exchange.getIn().setHeader("AuditSkValue", skValue.longValue());

                    String queueName = Messaging.Names.COMPONENT_TYPE_QUEUE + Audit.EVENT.AUDIT_CHANGED_EVENT.toString();

                    logger.trace(String.format("%s: executeRecursive: [Result: %d]: [Queue: %s]: Sending audit message ...",
                            getClass().getName(), result, queueName));

                    if(producerPojo == null) {
                        producerPojo = new ProducerPojo(exchange);
                    }
                    producerPojo.queueData(compare, queueName, exchange.getIn().getHeaders(), logger);
                }
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
                            long insertResponse = executeRecursive(connection, object, bShouldInsert, returnVal, bsnEntId, exchange, logger);
                            if (insertResponse == -1) {
                                if (bShouldInsert) {
                                    throw new SandataRuntimeException(format("INSERT: Failed: [%s]",
                                            object.getClass().getName()));
                                } else {
                                    throw new SandataRuntimeException(format("UPDATE: Failed: [%s]",
                                            object.getClass().getName()));
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
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
    }

    public OracleDataService getOracleDataService() {
        return oracleDataService;
    }

    public void setOracleDataService(OracleDataService oracleDataService) {
        this.oracleDataService = oracleDataService;
    }

    public void getSchdlPtcExcl(String operationName, Exchange exchange) {

        boolean callGet = true;
        if (exchange.getIn().getHeader("sequence_key") != null) {
            get(exchange);
        } else {
            Object body = exchange.getIn().getBody();
            MessageContentsList mcl = (MessageContentsList) body;
            String[] params = new String[mcl.size()];
            for (int index = 0; index < mcl.size(); index++) {
                params[index] = (String) mcl.get(index);
                if (params[index] == null || params[index].isEmpty()) {
                    callGet = false;
                }
            }
            if (callGet) {
                get(exchange);
            } else {
                Object result;
                String patientId = params[0];
                String staffId = params[1];
                String bsnEntId = params[2];
                if (patientId == null || patientId.length() == 0) {

                    // Patient is only required if Staff is not provided
                    if (staffId == null || staffId.length() == 0) {
                        throw new SandataRuntimeException("StaffID (staff_id) OR PatientID (patient_id) is required. At least one must be provided!");
                    }
                }

                if (bsnEntId == null || bsnEntId.length() == 0) {
                    throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
                }

                String whereClause = "WHERE BE_ID = ? AND ";
                if (patientId != null && !patientId.isEmpty()) {
                    whereClause += String.format("PT_ID = '%s' ", patientId);
                } else {
                    whereClause += String.format("STAFF_ID = '%s' ", staffId);
                }

                String sql = String.format("SELECT * FROM SCHED_PTC_EXCL %s AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", whereClause);
                result = oracleDataService.getSchdPtcExcl(sql, bsnEntId);

                exchange.getIn().setBody(result);

            }

        }

    }

    public void getSchedEvntHistory(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            String patientId = (String) mcl.get(1);
            String staffId = (String) mcl.get(2);

            if (StringUtil.IsNullOrEmpty(patientId) && StringUtil.IsNullOrEmpty(staffId)) {
                throw new SandataRuntimeException("Please provide at least a Patient (patient_id) or StaffID (staff_id)!");
            }

            String schedFromDateTimeString = (String) mcl.get(3);
            String schedToDateTimeString = (String) mcl.get(4);

            SimpleDateFormat utcFormat = new SimpleDateFormat(DateUtil.SANDATA_UTC_DATE_TIME_FORMAT);
            Date fromDateTime = null;
            Date toDateTime = null;
            if (!StringUtil.IsNullOrEmpty(schedFromDateTimeString)) {
                
                try {
                    fromDateTime = utcFormat.parse(schedFromDateTimeString);
                } catch (Exception e) {
                    throw new SandataRuntimeException(String.format("Since the \"Schedule From Date Time\" (sched_from_date_time) [%s] was provided, "
                            + "it must be provided in format %s!",
                            schedFromDateTimeString,
                            DateUtil.SANDATA_UTC_DATE_TIME_FORMAT));
                }
            }

            if (!StringUtil.IsNullOrEmpty(schedToDateTimeString)) {
                
                try {
                    toDateTime = utcFormat.parse(schedToDateTimeString);
                } catch (Exception e) {
                    throw new SandataRuntimeException(String.format("Since the \"Schedule To Date Time\" (sched_to_date_time) [%s] was provided, "
                            + "it must be provided in format %s!",
                            schedToDateTimeString,
                            DateUtil.SANDATA_UTC_DATE_TIME_FORMAT));
                }
            }

            int page = (Integer) exchange.getIn().getHeader("page");
            int pageSize = (Integer) exchange.getIn().getHeader("page_size");
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            Response response = oracleDataService.getSchedEvntHistory(
                    bsnEntId,
                    patientId,
                    staffId,
                    fromDateTime,
                    toDateTime,
                    page,
                    pageSize,
                    sortOn,
                    direction
            );

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getSchedEvntHistoryDetail(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

        logger.start();

        try {

            Long scheduleEvenSK = (Long) exchange.getIn().getHeader("schedule_event_sk");
            if (scheduleEvenSK == null || scheduleEvenSK <= 0) {
                throw new SandataRuntimeException("ScheduleEventSK (schedule_event_sk) is required!");
            }

            int page = (int) exchange.getIn().getHeader("page");
            int pageSize = (int) exchange.getIn().getHeader("page_size");
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            Response response = oracleDataService.getSchedEvntHistoryDetail(scheduleEvenSK, page, pageSize, sortOn, direction);
            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", page);
            exchange.getIn().setHeader("PAGE_SIZE", pageSize);
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", direction.toUpperCase());

            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void updateScheduleWithDateRange(final Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);

        Connection connection = null;

        logger.start();

        try {
            Schedule schedule = (Schedule) exchange.getIn().getBody();

            if (schedule == null) {
                throw new SandataRuntimeException("Schedule to update (body) is required!");
            }

            SimpleDateFormat dateUTCFormat = new SimpleDateFormat(DateUtil.SANDATA_UTC_DATE_TIME_FORMAT);
            
            String dateFromString = (String) exchange.getIn().getHeader("date_from");
            if (dateFromString == null) {
                throw new SandataRuntimeException("date from (date_from) is required!");
            }
            Date dateFrom;
            try {
                dateFrom = dateUTCFormat.parse(dateFromString);
            } catch (Exception e) {
                throw new SandataRuntimeException(String.format("date from (date_from) must be valid format: %s!", DateUtil.SANDATA_UTC_DATE_TIME_FORMAT));
            }


            String dateToString = (String) exchange.getIn().getHeader("date_to");
            if (dateToString == null) {
                throw new SandataRuntimeException("date to (date_to) is required!");
            }
            Date dateTo;
            try {
                dateTo = dateUTCFormat.parse(dateToString);
            } catch (Exception e) {
                throw new SandataRuntimeException(String.format("date to (date_to) must be valid format: %s!", DateUtil.SANDATA_UTC_DATE_TIME_FORMAT));
            }

            // check for event in date range
            List<ScheduleEvent> scheduleEventsInRange = new ArrayList<>();
            for (ScheduleEvent event : schedule.getScheduleEvent()) {
                boolean startTimeInRange = (event.getScheduleEventStartDatetime() != null)
                        && (event.getScheduleEventStartDatetime().compareTo(dateTo) <= 0)
                        && (event.getScheduleEventStartDatetime().compareTo(dateFrom) >= 0);

                boolean endTimeInRange = (event.getScheduleEventEndDatetime() != null)
                        && (event.getScheduleEventEndDatetime().compareTo(dateTo) <= 0)
                        && (event.getScheduleEventEndDatetime().compareTo(dateFrom) >= 0);

                if (startTimeInRange && endTimeInRange) {
                    scheduleEventsInRange.add(event);
                }
            }

            // update list ScheduleEvents in range
            schedule.getScheduleEvent().clear();
            schedule.getScheduleEvent().addAll(scheduleEventsInRange);

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);
            long returnVal = executeRecursive(connection, schedule, false /* UPDATE */, -999, null, exchange, logger);
            if (returnVal > 0) {

                connection.commit();
                exchange.getIn().setBody(returnVal);
            } else {
                throw new SandataRuntimeException("Update was not successful!");
            }
        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    /**
     * create Schedule with relavent data: 
     * [ScheduleAuthorization, ScheduleService, ScheduleRepeatDayOfWeek, ScheduleEvent, ScheduleEventAuthorization, ScheduleEventService] 
     * @param exchange
     */
    public void createScheduleWithFullProcess(final Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        Connection connection = null;

        logger.start();

        try {
            ScheduleExt scheduleExt = (ScheduleExt) exchange.getIn().getBody();

            if (scheduleExt == null) {
                throw new SandataRuntimeException("Schedule to update (body) is required!");
            }

            if (scheduleExt.getScheduleStartDate() == null) {
                throw new SandataRuntimeException("Schedule must have StartDate");
            }

            if (scheduleExt.getScheduleEndDate() == null && scheduleExt.getWeekOfMonthNumber() == 0) {
                throw new SandataRuntimeException("Schedule must have EndDate, Or WeekOfMonthNumber");
            }
            //Need to check to allow only 1 condition: getWeekOfMonthNumber or Start & Date.
            else if (scheduleExt.getScheduleEndDate() != null && scheduleExt.getWeekOfMonthNumber() > 0) {
                throw new SandataRuntimeException("Schedule cannot have both StartDate & EndDate and StartDate & WeekOfMonthNumber");
            }

            // If repeat number of weeks is specified, set schedule end date with value calculated
            // by schedule start date and number of repeated weeks
            // NOTE: the schedule end date and Week # won't both exist
            if (scheduleExt.getWeekOfMonthNumber() > 0) {
                scheduleExt.setScheduleEndDate(
                        ScheduleUtils.getScheduleEndDateByRepeatWeek(
                                scheduleExt.getScheduleStartDate(), scheduleExt.getWeekOfMonthNumber()));
            }

            if (scheduleExt.getScheduleStartDate().after(scheduleExt.getScheduleEndDate())) {
                throw new SandataRuntimeException("Schedule StartDate must less than or equal to EndDate");
            }

            if (StringUtil.IsNullOrEmpty(scheduleExt.getScheduleID())) {

                scheduleExt.setScheduleID(generateUniqueId());

            } else {
                List<Schedule> listActiveScheduleByID = (List<Schedule>) oracleDataService.getEntitiesForId(
                        String.format("SELECT * FROM %s WHERE STAFF_ID = ? AND BE_ID = ? ", "SCHED"),
                        Schedule.class.getName(),
                        scheduleExt.getScheduleID(),
                        scheduleExt.getBusinessEntityID());

                if (listActiveScheduleByID != null && listActiveScheduleByID.size() > 0) {
                    throw new SandataRuntimeException(exchange, String.format("The schedule id [%s] is already being used", listActiveScheduleByID.get(0).getStaffID()));
                }
            }



            //At this point, there always have ScheduleStartDate and ScheduleEndDate to process further

            //scheduleExt.getScheduleStartDate() is in UTC timezone, needs to convert it to the target input Timezone to get actual date part.
            Date scheduleStartDateInTargetTimeZone = ScheduleUtils.getDateOnlyInTargetTimeZone(scheduleExt.getScheduleStartDate(), scheduleExt.getTimezoneName());
            //Attention: We do not do any change to ScheduleStartDate. Keep the original time from outside, will return the same value

            //scheduleExt.getScheduleEndDate() is in UTC timezone, needs to convert it to the target input Timezone to get actual date part.
            Date scheduleEndDateInTargetTimeZone = ScheduleUtils.getDateOnlyInTargetTimeZone(scheduleExt.getScheduleEndDate(), scheduleExt.getTimezoneName());
            //Attention: We do not do any change to ScheduleEndDate. Keep the original time from outside, will return the same value

            logger.info(String.format("Schedule full creation: patientId [%s] - staffId [%s] - schedSK [%s] - timezone [%s]:" +
                            " - sched date from [%s] - sched date to [%s] " +
                            " - scheduleStartDateInTargetTimeZone [%s] - scheduleEndDateInTargetTimeZone [%s] : [%s]",
                    scheduleExt.getPatientID(),
                    scheduleExt.getStaffID(),
                    scheduleExt.getScheduleSK(),
                    scheduleExt.getTimezoneName(),
                    DateUtil.StringFromFormat(scheduleExt.getScheduleStartDate(), DateUtil.SANDATA_UTC_DATE_TIME_FORMAT),
                    DateUtil.StringFromFormat(scheduleExt.getScheduleEndDate(), DateUtil.SANDATA_UTC_DATE_TIME_FORMAT),
                    DateUtil.StringFromFormat(scheduleStartDateInTargetTimeZone, DateUtil.SANDATA_UTC_DATE_TIME_FORMAT),
                    DateUtil.StringFromFormat(scheduleEndDateInTargetTimeZone, DateUtil.SANDATA_UTC_DATE_TIME_FORMAT),
                    scheduleExt.toString())
            );

            List<Date> eventDateList = null;
            Map<Integer, DayInWeek> selectedDaysInWeek = null;
            if (scheduleExt.getSelectedDaysOfWeek() != null) {
                selectedDaysInWeek = ScheduleUtils.getScheduleSelectedDayOfWeekInMap(scheduleExt);

                eventDateList = ScheduleUtils.getAllScheduleDatesInRange(
                        scheduleStartDateInTargetTimeZone,
                        scheduleEndDateInTargetTimeZone,
                        new ArrayList<>(selectedDaysInWeek.keySet()));
            } else {
                eventDateList = new ArrayList<>();
                selectedDaysInWeek = new HashMap<>();
            }

            Schedule insertSchedule = new Schedule();
            BeanUtils.copyProperties(scheduleExt, insertSchedule);

            // create schedule service
            ScheduleService scheduleService = createOrUpdateScheduleService(scheduleExt, null);
            insertSchedule.getScheduleService().clear();
            insertSchedule.getScheduleService().add(scheduleService);

            // create schedule authorization
            ScheduleAuthorization scheduleAuth = createOrUpdateScheduleAuthorization(scheduleExt, null);
            insertSchedule.getScheduleAuthorization().clear();
            insertSchedule.getScheduleAuthorization().add(scheduleAuth);

            // create Schedule Repeat day of week
            List<ScheduleRepeatDayOfWeek> schedRepeatDayOfWeek = createScheduleRepeatDayOfWeekList(scheduleExt, eventDateList, selectedDaysInWeek,
                    scheduleStartDateInTargetTimeZone, ScheduleUtils.toTheEndOfTheDate(scheduleEndDateInTargetTimeZone));
            insertSchedule.getScheduleRepeatDayOfWeek().clear();
            insertSchedule.getScheduleRepeatDayOfWeek().addAll(schedRepeatDayOfWeek);

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            long scheduleSK = executeRecursiveForScheduleAndEvent(connection, insertSchedule, true /* INSERT */, -999, scheduleExt.getBusinessEntityID(), exchange, logger);
            if (scheduleSK < 0) {
                throw new SandataRuntimeException("Insert Schedule was not successful!");
            }
            scheduleExt.setScheduleSK(BigInteger.valueOf(scheduleSK));

            // not to insert schedule event first due to recursive will set scheduleSK to schedule_event_service and schedule_event_authorization
            List<ScheduleEvent> scheduleEventsList = createScheduleEventList(scheduleExt, eventDateList, selectedDaysInWeek,
                    scheduleStartDateInTargetTimeZone, ScheduleUtils.toTheEndOfTheDate(scheduleEndDateInTargetTimeZone));
            insertSchedule.getScheduleEvent().clear();
            insertSchedule.getScheduleEvent().addAll(scheduleEventsList);

            for (ScheduleEvent schedEvent : scheduleEventsList) {
                long schedEventSK = executeRecursiveForScheduleAndEvent(connection, schedEvent, true /* INSERT */, -999, schedEvent.getBusinessEntityID(), exchange, logger);

                if (schedEventSK < 0) {
                    throw new SandataRuntimeException("Insert Schedule Event was not successful!");
                }
            }

            logger.info(String.format("Schedule full creation: patientId [%s] - staffId [%s] - schedSK [%s] - timezone [%s]:" +
                            " - scheduleEventsList.size() = [%d] ",
                    scheduleExt.getPatientID(),
                    scheduleExt.getStaffID(),
                    scheduleExt.getScheduleSK(),
                    scheduleExt.getTimezoneName(),
                    scheduleEventsList.size())
            );

            connection.commit();
            exchange.getIn().setBody(scheduleSK);
        } catch (Exception e) {

            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());

            logger.error("Schedule full creation - ERROR: " + errMsg);

            throw new SandataRuntimeException(errMsg, e);
        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    /**
     * Only updates schedule events that are in the future (and scheds in progress? GEOR-6251- awaiting response from Tram)
     * Deletes visits that are associated with the existing sched events, then create new visits with the new sched_event sks
     * @param exchange
     */
    public void updateScheduleWithFullProcess(final Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        Connection connection = null;

        logger.start();

        try {
            ScheduleExt scheduleExt = (ScheduleExt) exchange.getIn().getBody();

            if (scheduleExt == null) {
                throw new SandataRuntimeException("Schedule to update (body) is required!");
            }

            if (scheduleExt.getScheduleStartDate() == null) {
                throw new SandataRuntimeException("Schedule must have StartDate");
            }

            if (scheduleExt.getScheduleEndDate() == null && scheduleExt.getWeekOfMonthNumber() == 0) {
                throw new SandataRuntimeException("Schedule must have EndDate, Or WeekOfMonthNumber");
            }

            //Need to check to allow only 1 condition: getWeekOfMonthNumber or Start & Date.
            else if(scheduleExt.getScheduleEndDate() != null && scheduleExt.getWeekOfMonthNumber() > 0){
                throw new SandataRuntimeException("Schedule cannot have both StartDate & EndDate and StartDate & WeekOfMonthNumber");
            }
            
            if (scheduleExt.getScheduleStartDate().after(scheduleExt.getScheduleEndDate())) {
                //throw new SandataRuntimeException("Schedule StartDate must less than or equal to EndDate");
                exchange.getIn().setBody(new ScheduleValidationResponse(false, "Schedule start date must before end date"));
                return;
            }

            // If repeat number of weeks is specified, set schedule end date with value calculated
            // by schedule start date and number of repeated weeks
            // NOTE: the schedule end date and Week # won't both exist
            if (scheduleExt.getWeekOfMonthNumber() > 0) {
                scheduleExt.setScheduleEndDate(
                        ScheduleUtils.getScheduleEndDateByRepeatWeek(
                                scheduleExt.getScheduleStartDate(), scheduleExt.getWeekOfMonthNumber()));
            }

            //At this point, there always have ScheduleStartDate and ScheduleEndDate to process further
            //And we will keep the original ScheduleStartDate() & ScheduleEndDate() from outside, will return the same values.

            String dateFromStringInUTC = (String) exchange.getIn().getHeader("date_from");
            String dateToStringInUTC = (String) exchange.getIn().getHeader("date_to");

            Boolean manuallyOverrideIndicator = (Boolean)exchange.getIn().getHeader("manually_override_indicator");

            Date dateFromInTargetTimeZone = null;
            Date dateToInTargetTimeZone = null;
            boolean isPermanentUpdate = false;

            if (!StringUtil.IsNullOrEmpty(dateFromStringInUTC) && !StringUtil.IsNullOrEmpty(dateToStringInUTC)) {
                Date dateFromInUTC = DateUtil.DateFromStringFormat(dateFromStringInUTC, DateUtil.SANDATA_UTC_DATE_TIME_FORMAT);
                Date dateToInUTC =  DateUtil.DateFromStringFormat(dateToStringInUTC, DateUtil.SANDATA_UTC_DATE_TIME_FORMAT);

                if(dateToInUTC.before(dateFromInUTC)){
                    throw new SandataRuntimeException(String.format("The date to (date_to=%s) cannot be before date from [date_from=%s]",
                            DateUtil.StringFromFormat(scheduleExt.getScheduleEndDate(), DateUtil.SANDATA_UTC_DATE_TIME_FORMAT),
                            DateUtil.StringFromFormat(scheduleExt.getScheduleStartDate(), DateUtil.SANDATA_UTC_DATE_TIME_FORMAT)));
                }

                if(dateFromInUTC.after(scheduleExt.getScheduleEndDate())){
                    throw new SandataRuntimeException(String.format("The date from (date_from=%s) cannot be after schedule end date [%s]",
                            dateFromStringInUTC, DateUtil.StringFromFormat(scheduleExt.getScheduleEndDate(), DateUtil.SANDATA_UTC_DATE_TIME_FORMAT)));
                }

                if(dateToInUTC.before(scheduleExt.getScheduleStartDate())){
                    throw new SandataRuntimeException(String.format("The date to [date_to=%s] cannot be before schedule start date [%s]",
                            dateToStringInUTC, DateUtil.StringFromFormat(scheduleExt.getScheduleStartDate(), DateUtil.SANDATA_UTC_DATE_TIME_FORMAT)));
                }

                if(dateFromInUTC.before(scheduleExt.getScheduleStartDate())){
                    dateFromInUTC = scheduleExt.getScheduleStartDate();
                }

                if(dateToInUTC.after(scheduleExt.getScheduleEndDate())){
                    dateToInUTC = scheduleExt.getScheduleEndDate();
                }

                dateFromInTargetTimeZone = ScheduleUtils.getDateOnlyInTargetTimeZone(dateFromInUTC, scheduleExt.getTimezoneName());
                dateToInTargetTimeZone = ScheduleUtils.getDateOnlyInTargetTimeZone(dateToInUTC, scheduleExt.getTimezoneName());

            } else if (StringUtil.IsNullOrEmpty(dateFromStringInUTC) && StringUtil.IsNullOrEmpty(dateToStringInUTC)) {
                //scheduleExt.getScheduleStartDate() is in UTC timezone, needs to convert it to the target input Timezone to get actual date part.
                dateFromInTargetTimeZone = ScheduleUtils.getDateOnlyInTargetTimeZone(scheduleExt.getScheduleStartDate(), scheduleExt.getTimezoneName());
                //scheduleExt.getScheduleEndDate() is in UTC timezone, needs to convert it to the target input Timezone to get actual date part.
                dateToInTargetTimeZone = ScheduleUtils.getDateOnlyInTargetTimeZone(scheduleExt.getScheduleEndDate(), scheduleExt.getTimezoneName());
                
                isPermanentUpdate = true;
            } else {
                if (StringUtil.IsNullOrEmpty(dateFromStringInUTC)) {
                    throw new SandataRuntimeException("date from (date_from) is required!");
                }

                if (StringUtil.IsNullOrEmpty(dateToStringInUTC)) {
                    throw new SandataRuntimeException("date to (date_to) is required!");
                }
            }

            //TODO: For Archer, allow to update any schedule including in the past. Review the rule after Archer
            // Cannot edit for the past schedule event, the editing must be from now to the future
            /*
            Date now = new Date();
            if (dateFromInTargetTimeZone.before(now)) {
                // new Date() is in UTC as the Fuse server is set to UTC
                dateFromInTargetTimeZone = ScheduleUtils.getDateOnlyInTargetTimeZone(now, scheduleExt.getTimezoneName());
            }
            */

            //date to should be the end of the date, so will update all schedules up to the end date.
            Calendar dateToCal = Calendar.getInstance();
            dateToCal.setTime(dateToInTargetTimeZone);
            dateToCal.set(Calendar.HOUR, 23);
            dateToCal.set(Calendar.MINUTE, 59);
            dateToCal.set(Calendar.SECOND, 59);
            dateToCal.set(Calendar.MILLISECOND, 999);
            dateToInTargetTimeZone = dateToCal.getTime();

            logger.info(String.format("Schedule full update: patientId [%s] - staffId [%s] - schedSK [%s]: filtered  dateFrom [%s] - filtered dateTo [%s]; sched date from [%s] - sched date to [%s]" +
                            "- calculated dateFromInTargetTimeZone [%s] - calculated dateToInTargetTimeZone [%s] : [%s]",
                    scheduleExt.getPatientID(),
                    scheduleExt.getStaffID(),
                    scheduleExt.getScheduleSK(),
                    dateFromStringInUTC, dateToStringInUTC,
                    DateUtil.StringFromFormat(scheduleExt.getScheduleStartDate(), DateUtil.SANDATA_UTC_DATE_TIME_FORMAT),
                    DateUtil.StringFromFormat(scheduleExt.getScheduleEndDate(), DateUtil.SANDATA_UTC_DATE_TIME_FORMAT),
                    DateUtil.StringFromFormat(dateFromInTargetTimeZone, DateUtil.SANDATA_UTC_DATE_TIME_FORMAT),
                    DateUtil.StringFromFormat(dateToInTargetTimeZone, DateUtil.SANDATA_UTC_DATE_TIME_FORMAT),
                    scheduleExt.toString())
            );

            // only create event date in date range
            List<Date> eventDateList = null;
            Map<Integer, DayInWeek> selectedDaysInWeek = null;
            if (scheduleExt.getSelectedDaysOfWeek() != null) {
                selectedDaysInWeek = ScheduleUtils.getScheduleSelectedDayOfWeekInMap(scheduleExt);

                // only get Date in DateRange
                eventDateList = ScheduleUtils.getAllScheduleDatesInRange(
                        dateFromInTargetTimeZone,
                        dateToInTargetTimeZone,
                        new ArrayList<>(selectedDaysInWeek.keySet()));
            } else {
                eventDateList = new ArrayList<>();
                selectedDaysInWeek = new HashMap<>();
            }

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            // get all relevant data for updating
            Schedule updateSchedule = new Schedule();
            BeanUtils.copyProperties(scheduleExt, updateSchedule);

            String sqlGetSchedService = "SELECT * FROM SCHED_SVC WHERE SCHED_SK = ?";
            List<ScheduleService> schedServiceList = (List<ScheduleService>) oracleDataService.getEntitiesForId(
                    connection,
                    sqlGetSchedService,
                    "com.sandata.lab.data.model.dl.model.ScheduleService",
                    scheduleExt.getScheduleSK());
            for (ScheduleService schedService : schedServiceList) {
                createOrUpdateScheduleService(scheduleExt, schedService);
            }
            updateSchedule.getScheduleService().addAll(schedServiceList);

            String sqlGetSchedAuth = "SELECT * FROM SCHED_AUTH WHERE SCHED_SK = ?";
            List<ScheduleAuthorization> schedAuthList = (List<ScheduleAuthorization>) oracleDataService.getEntitiesForId(
                    connection,
                    sqlGetSchedAuth,
                    "com.sandata.lab.data.model.dl.model.ScheduleAuthorization",
                    scheduleExt.getScheduleSK());
            for (ScheduleAuthorization schedAuth : schedAuthList) {
                createOrUpdateScheduleAuthorization(scheduleExt, schedAuth);
            }
            updateSchedule.getScheduleAuthorization().addAll(schedAuthList);

            long updateScheduleResult = executeRecursiveForScheduleAndEvent(connection, updateSchedule, false /* UPDATE */, -999, updateSchedule.getBusinessEntityID(), exchange, logger);
            if (updateScheduleResult < 0) {
                throw new SandataRuntimeException("Update Schedule was not successful!");
            }


            String sqlGetSchedRepeatDayOfWeek = "SELECT * FROM SCHED_RPT_DAY_OF_WEEK WHERE SCHED_SK = ?";
            List<ScheduleRepeatDayOfWeek> schedRepeatDayOfWeekList = (List<ScheduleRepeatDayOfWeek>) oracleDataService.getEntitiesForId(
                    connection,
                    sqlGetSchedRepeatDayOfWeek,
                    "com.sandata.lab.data.model.dl.model.ScheduleRepeatDayOfWeek",
                    scheduleExt.getScheduleSK());

            String sqlGetSchedEvnt = "SELECT * FROM SCHED_EVNT WHERE SCHED_SK = ? "
                    + " AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";
            List<ScheduleEvent> schedEventList = (List<ScheduleEvent>) oracleDataService.getEntitiesForId(
                    connection,
                    sqlGetSchedEvnt,
                    "com.sandata.lab.data.model.dl.model.ScheduleEvent",
                    scheduleExt.getScheduleSK());

            List<ScheduleEvent> scheduleEventsInRange = new ArrayList<>();
            List<ScheduleRepeatDayOfWeek> scheduleRepeatDaysOfWeekInRange = new ArrayList<>();

            String targetTimeZone = scheduleExt.getTimezoneName();
            for (ScheduleEvent event : schedEventList) {
                //schedEventList is loaded from database, this is an UTC datetime comparision
                boolean isOvernight = !ScheduleUtils.isTheSameDate(
                        event.getScheduleEventStartDatetime(), event.getScheduleEventEndDatetime(), targetTimeZone);
                //SAN-1052: if this is Permanent update, add all events to deleted list
                if (isPermanentUpdate || isValidBetweenDateRange(
                        ScheduleUtils.convertToTargetTimeZone(event.getScheduleEventStartDatetime(), targetTimeZone),
                        ScheduleUtils.convertToTargetTimeZone(event.getScheduleEventEndDatetime(), targetTimeZone),
                        dateFromInTargetTimeZone,
                        isOvernight? ScheduleUtils.addDays(dateToInTargetTimeZone, 1) : dateToInTargetTimeZone )) {

                    scheduleEventsInRange.add(event);
                }
            }

            for (ScheduleRepeatDayOfWeek repeatDayOfWeek : schedRepeatDayOfWeekList) {
                //schedRepeatDayOfWeekList is loaded from database, this is an UTC datetime

                boolean isOvernight = !ScheduleUtils.isTheSameDate(
                        repeatDayOfWeek.getScheduleStartTime(), repeatDayOfWeek.getScheduleEndTime(), targetTimeZone);

                //SAN-1052: if this is Permanent update, add all repeat day of week to deleted list
                if (isPermanentUpdate || isValidBetweenDateRange(
                        ScheduleUtils.convertToTargetTimeZone(repeatDayOfWeek.getScheduleStartTime(), targetTimeZone),
                        ScheduleUtils.convertToTargetTimeZone(repeatDayOfWeek.getScheduleEndTime(), targetTimeZone),
                        dateFromInTargetTimeZone,
                        isOvernight ? ScheduleUtils.addDays(dateToInTargetTimeZone, 1) : dateToInTargetTimeZone)) {
                    scheduleRepeatDaysOfWeekInRange.add(repeatDayOfWeek);
                }
            }

            logger.info(String.format("Schedule full update: patientId [%s] - staffId [%s] - schedSK [%s] - Number of events for scheduleEventsInRange to be replaced [%d] ",
                    scheduleExt.getPatientID(), scheduleExt.getStaffID(),
                    scheduleExt.getScheduleSK(), scheduleEventsInRange.size()));
            // if date_from and date_to are provided only delete Schedule events in date range, else delete all and create new ones
            for (ScheduleEvent schedEventInRange : scheduleEventsInRange) {
                // SAN-3166: Schedules duplicating in Santrax causing by incorrect updating schedule fields in George 
                logicalDeleteScheduleEventWithRelevantTables(connection, schedEventInRange, exchange, logger);
            }

            logger.info(String.format("Schedule full update: patientId [%s] - staffId [%s] - schedSK [%s] - Number of events for scheduleRepeatDaysOfWeekInRange to be deleted [%d] ",
                    scheduleExt.getPatientID(), scheduleExt.getStaffID(),
                    scheduleExt.getScheduleSK(), scheduleRepeatDaysOfWeekInRange.size()));

            for (ScheduleRepeatDayOfWeek repeatDayOfWeek : scheduleRepeatDaysOfWeekInRange) {
                long deleteResult = oracleDataService.executeDelete(connection, repeatDayOfWeek);
                if (deleteResult < 0) {
                    throw new SandataRuntimeException("Delete ScheduleRepeatDayOfWeek was not successful!");
                }
            }
            // end delete old ones

            // create new ones in range
            // create Schedule Repeat day of week
            List<ScheduleRepeatDayOfWeek> schedRepeatDayOfWeekNewList = createScheduleRepeatDayOfWeekList(scheduleExt, eventDateList, selectedDaysInWeek,
                    dateFromInTargetTimeZone, ScheduleUtils.toTheEndOfTheDate(dateToInTargetTimeZone));

            logger.info(String.format("Schedule full update: patientId [%s] - staffId [%s] - schedSK [%s] - Number of events for schedRepeatDayOfWeekNewList to be created [%d] ",
                    scheduleExt.getPatientID(), scheduleExt.getStaffID(),
                    scheduleExt.getScheduleSK(), schedRepeatDayOfWeekNewList.size()));

            for (ScheduleRepeatDayOfWeek repeatDayOfWeekNew : schedRepeatDayOfWeekNewList) {
                // it's not late for the current time - new Date() is in UTC as Fuse is in UTC
                //TODO: For Archer, allow to update any schedule including in the past. Review the rule after Archer
                //if (!repeatDayOfWeekNew.getScheduleStartTime().before(new Date())) {
                    OracleMetadata metaData = DataMapper.getOracleMetadata(repeatDayOfWeekNew);
                    Object jpubType = new DataMapper().map(repeatDayOfWeekNew);

                    long insertResult = oracleDataService.execute(connection, metaData.packageName(), metaData.insertMethod(), jpubType);
                    if (insertResult < 0) {
                        throw new SandataRuntimeException("Insert ScheduleRepeatDayOfWeek was not successful!");
                    }
                //}
            }

            List<ScheduleEvent> schedEventNewList = updateScheduleEventList(scheduleExt, eventDateList, selectedDaysInWeek,
                    dateFromInTargetTimeZone, ScheduleUtils.toTheEndOfTheDate(dateToInTargetTimeZone), manuallyOverrideIndicator);

            logger.info(String.format("Schedule full update: patientId [%s] - staffId [%s] - schedSK [%s] - Number of events for schedEventNewList to be created [%d] ",
                    scheduleExt.getPatientID(), scheduleExt.getStaffID(),
                    scheduleExt.getScheduleSK(), schedEventNewList.size()));

            for (ScheduleEvent schedEventNew : schedEventNewList) {
                // it's not late for the current time, the new Date() is in UTC as Fuse server is in UTC
                //TODO: For Archer, allow to update any schedule including in the past. Review the rule after Archer
                //if (!schedEventNew.getScheduleEventStartDatetime().before(new Date())) {
                    long insertEventResult = executeRecursiveForScheduleAndEvent(connection, schedEventNew, true /* INSERT */, -999, schedEventNew.getBusinessEntityID(), exchange, logger);
    
                    if (insertEventResult < 0) {
                        throw new SandataRuntimeException("Insert Schedule Event was not successful!");
                    }
               //}
            }

            connection.commit();
            exchange.getIn().setBody(updateScheduleResult);
        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());

            logger.error("Schedule full update - ERROR: " + errMsg);

            throw new SandataRuntimeException(errMsg, e);
        } finally {

            safeCloseConnection(connection);

            logger.stop();
        }
    }
    
    private boolean isValidBetweenDateRange(Date objectStartDate, Date objectEndDate, Date dateRangeFrom, Date dateRangeTo) {
        boolean startTimeInRange = (objectStartDate != null)
                && (objectStartDate.compareTo(dateRangeTo) <= 0)
                && (objectStartDate.compareTo(dateRangeFrom) >= 0);

        boolean endTimeInRange = (objectEndDate != null)
                && (objectEndDate.compareTo( dateRangeTo) <= 0)
                && (objectEndDate.compareTo(dateRangeFrom) >= 0);
        
        return startTimeInRange && endTimeInRange;
    }

    /**
     * Validate schedule against Authorization or Order
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void validateSchedule(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            ScheduleExt scheduleExt = exchange.getIn().getBody(ScheduleExt.class);
            if (scheduleExt == null) {
                throw new SandataRuntimeException("Schedule object cannot be null.");
            }

            Schedule oldSchedule = null;
            // If scheduleSK is provided (i.e. validation upon Permanent/Temporary update)
            if (scheduleExt.getScheduleSK() != null && scheduleExt.getScheduleSK().longValue() > 0) {
                oldSchedule = oracleDataService.getSchedule(scheduleExt.getScheduleSK().longValue());
                if (oldSchedule == null) {
                    throw new SandataRuntimeException(String.format("Schedule (ScheduleSK=%s) not found", scheduleExt.getScheduleSK()));
                }
            }

            // Validate Schedule Start Date and End Date
            if (scheduleExt.getScheduleStartDate().after(scheduleExt.getScheduleEndDate())) {
                throw new SandataRuntimeException("Schedule Start Date cannot be after Schedule End Date.");
            }

            if (scheduleExt.getAuthorizationSK() == null || scheduleExt.getAuthorizationSK().intValue() == 0) {
                throw new SandataRuntimeException("AuthorizationSK cannot be null.");
            }

            AuthorizationAdapter auth;
            if (StringUtil.IsNullOrEmpty(scheduleExt.getAuthorizationID())) {
                // When AuthorizationID is null, UI is sending OrderSK value in ScheduleExt.AuthorizationSK property
                auth = oracleDataService.getAuthorizationAdapter(scheduleExt.getAuthorizationSK().longValue(), AuthorizationQualifier.ORDER);
            } else {
                auth = oracleDataService.getAuthorizationAdapter(scheduleExt.getAuthorizationSK().longValue(), AuthorizationQualifier.AUTHORIZATION);
            }

            if (auth == null) {
                throw new SandataRuntimeException("Authorization/Order not found");
            }
            
            // start Validation
            
            Date originalSchedStartDate = scheduleExt.getScheduleStartDate();
            Date originalSchedEndDate = scheduleExt.getScheduleEndDate();
            
            // SAN-1096: If date_from and date_to params are provided, this is Temporary update
            if (exchange.getIn().getHeader("date_from") != null && exchange.getIn().getHeader("date_to") != null) {
                if (oldSchedule == null) {
                    throw new SandataRuntimeException("Schedule not found when temporarily updating schedule");
                }

                SimpleDateFormat utcFormat = new SimpleDateFormat(DateUtil.SANDATA_UTC_DATE_TIME_FORMAT);
                Date temporaryDateFrom = utcFormat.parse(exchange.getIn().getHeader("date_from", String.class));
                Date temporaryDateTo = utcFormat.parse(exchange.getIn().getHeader("date_to", String.class));

                if (temporaryDateFrom.before(oldSchedule.getScheduleStartDate()) || temporaryDateTo.after(oldSchedule.getScheduleEndDate())) {
                    throw new SandataRuntimeException("Selected date range for temporary update cannot exceed Schedule date range");
                }

                scheduleExt.setScheduleStartDate(temporaryDateFrom);
                scheduleExt.setScheduleEndDate(temporaryDateTo);
            }

            if ("CANCELLED".equalsIgnoreCase(scheduleExt.getEventStatus())) {
                exchange.getIn().setBody(new ScheduleValidationResponse(true, "The schedules are being cancelled"));
                return;
            }
            
            // If repeat number of weeks is specified, overwrite schedule end date with value calculated
            // by schedule start date and number of repeated weeks
            if (scheduleExt.getWeekOfMonthNumber() > 0) {
                scheduleExt.setScheduleEndDate(
                        ScheduleUtils.getScheduleEndDateByRepeatWeek(
                                scheduleExt.getScheduleStartDate(), scheduleExt.getWeekOfMonthNumber()));
            }
            
            // SAN-3313: check if staff has other event overlapped time
            if (!StringUtil.IsNullOrEmpty(scheduleExt.getStaffID())) {
                Date scheduleStartDateInTargetTimeZone = ScheduleUtils.getDateOnlyInTargetTimeZone(scheduleExt.getScheduleStartDate(), scheduleExt.getTimezoneName());
                Date scheduleEndDateInTargetTimeZone = ScheduleUtils.getDateOnlyInTargetTimeZone(scheduleExt.getScheduleEndDate(), scheduleExt.getTimezoneName());
                // only create event date in date range
                List<Date> eventDateList = null;
                Map<Integer, DayInWeek> selectedDaysInWeek = null;
                if (scheduleExt.getSelectedDaysOfWeek() != null) {
                    selectedDaysInWeek = ScheduleUtils.getScheduleSelectedDayOfWeekInMap(scheduleExt);

                    // only get Date in DateRange
                    eventDateList = ScheduleUtils.getAllScheduleDatesInRange(
                            scheduleStartDateInTargetTimeZone,
                            scheduleEndDateInTargetTimeZone,
                            new ArrayList<>(selectedDaysInWeek.keySet()));
                } else {
                    eventDateList = new ArrayList<>();
                    selectedDaysInWeek = new HashMap<>();
                }
                
                List<ScheduleEvent> scheduleEventsList = createScheduleEventList(scheduleExt, 
                        eventDateList, 
                        selectedDaysInWeek, 
                        scheduleStartDateInTargetTimeZone, 
                        ScheduleUtils.toTheEndOfTheDate(scheduleEndDateInTargetTimeZone));
                for (ScheduleEvent scheduleEvent : scheduleEventsList) {
                    String validatedMsg = validateStaffOverlapedSchedule(scheduleEvent, scheduleExt.getScheduleSK());
                    if (!validatedMsg.isEmpty()) {
                        exchange.getIn().setBody(new ScheduleValidationResponse(false, validatedMsg));
                        return;
                    }
                }
            }

            // No validation needed if Auth.AuthorizationLimitTypeName = NONE
            if (AuthorizationLimitTypeName.NONE == auth.getAuthorizationLimitTypeName()) {
                exchange.getIn().setBody(new ScheduleValidationResponse(true, ""));
                return;

            }

            // Validate against Authorization Start Date and End Date
            if (originalSchedStartDate.before(auth.getAuthorizationStartTimestamp())
                    || originalSchedStartDate.after(auth.getAuthorizationEndTimestamp())
                    || originalSchedEndDate.after(auth.getAuthorizationEndTimestamp())) {
                exchange.getIn().setBody(new ScheduleValidationResponse(false, "Schedule Start Date and End Date do not match with Authorization"));
                return;
            }

            Contract contract = oracleDataService.getContractForAuthService(auth.getBusinessEntityID(), auth.getAuthorizationSK().longValue(), auth.isOrder());
            if (contract == null) {
                throw new SandataRuntimeException("Contract associated with Authorization Service not found");
            }

            String validationMessage = "";

            logger.info(String.format("ValidateSchedule: patientId [%s] - staffId [%s] - schedSK [%s] : [%s]",
                    scheduleExt.getPatientID(),
                    scheduleExt.getStaffID(),
                    scheduleExt.getScheduleSK(),
                    scheduleExt.toString())
            );

            // Convert Schedule Start date and End date to target timezone before duration validation
            scheduleExt.setScheduleStartDate(ScheduleUtils.convertToTargetTimeZone(scheduleExt.getScheduleStartDate(), scheduleExt.getTimezoneName()));
            scheduleExt.setScheduleEndDate(ScheduleUtils.convertToTargetTimeZone(scheduleExt.getScheduleEndDate(), scheduleExt.getTimezoneName()));

            if (AuthorizationLimitTypeName.DAY == auth.getAuthorizationLimitTypeName()) {
                List<Date> selectedDates = ScheduleUtils.getselectedDateForRange(scheduleExt, scheduleExt.getScheduleEndDate());
                List<ScheduleEventCountAndDuration> exceedLimitDays;

                if (AuthorizationServiceUnitName.VISIT == auth.getAuthorizationServiceUnitName()) {
                    exceedLimitDays = oracleDataService.validateDailyScheduleEvent(
                            auth, scheduleExt, contract, scheduleExt.getSelectedDaysOfWeek(), selectedDates, true);
                    validationMessage = processValidationResult(exceedLimitDays, AuthorizationLimitTypeName.DAY, true, false);

                } else {
                    validationMessage = ScheduleUtils.validateDailyTimeRanges(auth, scheduleExt);

                    if (StringUtil.IsNullOrEmpty(validationMessage)) {
                        exceedLimitDays = oracleDataService.validateDailyScheduleEvent(
                                auth, scheduleExt, contract, scheduleExt.getSelectedDaysOfWeek(), selectedDates, false);
                        validationMessage = processValidationResult(exceedLimitDays, AuthorizationLimitTypeName.DAY, false, false);
                    }
                }

            } else if (AuthorizationLimitTypeName.WEEK == auth.getAuthorizationLimitTypeName()) {
                // Get WeekendStartDay from Contract or Payer
                WeekendStartDay weekendStartDay = getContractWeekendStartDay(contract);

                // Get all weeks between ScheduleExt.scheduleStartDate and ScheduleExt.scheduleEndDate
                List<Date[]> weeks = ScheduleUtils.getWeeksInDateRange(scheduleExt.getScheduleStartDate(), scheduleExt.getScheduleEndDate(), weekendStartDay);

                List<ScheduleEventCountAndDuration> exceedLimitDays;
                if (AuthorizationServiceUnitName.VISIT == auth.getAuthorizationServiceUnitName()) {
                    exceedLimitDays = oracleDataService.validateScheduleEventInRanges(auth, contract, scheduleExt, weeks, true, false);
                    validationMessage = processValidationResult(exceedLimitDays, AuthorizationLimitTypeName.WEEK, true, false);

                } else {
                    exceedLimitDays = oracleDataService.validateScheduleEventInRanges(auth, contract, scheduleExt, weeks, false, false);
                    validationMessage = processValidationResult(exceedLimitDays, AuthorizationLimitTypeName.WEEK, false, false);
                }

            } else if (AuthorizationLimitTypeName.MONTH == auth.getAuthorizationLimitTypeName()) {
                // Get all months between ScheduleExt.scheduleStartDate and ScheduleExt.scheduleEndDate
                List<Date[]> months = ScheduleUtils.getMonthsInDateRange(scheduleExt.getScheduleStartDate(), scheduleExt.getScheduleEndDate());
                List<ScheduleEventCountAndDuration> exceedLimitDays;

                if (AuthorizationServiceUnitName.VISIT == auth.getAuthorizationServiceUnitName()) {
                    exceedLimitDays = oracleDataService.validateScheduleEventInRanges(auth, contract, scheduleExt, months, true, false);
                    validationMessage = processValidationResult(exceedLimitDays, AuthorizationLimitTypeName.MONTH, true, false);

                } else {
                    exceedLimitDays = oracleDataService.validateScheduleEventInRanges(auth, contract, scheduleExt, months, false, false);
                    validationMessage = processValidationResult(exceedLimitDays, AuthorizationLimitTypeName.MONTH, false, false);
                }

            } else if (AuthorizationLimitTypeName.YEAR == auth.getAuthorizationLimitTypeName()) {
                // Get all years between ScheduleExt.scheduleStartDate and ScheduleExt.scheduleEndDate
                List<Date[]> years = ScheduleUtils.getYearsInDateRange(scheduleExt.getScheduleStartDate(), scheduleExt.getScheduleEndDate());
                List<ScheduleEventCountAndDuration> exceedLimitDays;

                if (AuthorizationServiceUnitName.VISIT == auth.getAuthorizationServiceUnitName()) {
                    exceedLimitDays = oracleDataService.validateScheduleEventInRanges(auth, contract, scheduleExt, years, true, false);
                    validationMessage = processValidationResult(exceedLimitDays, AuthorizationLimitTypeName.YEAR, true, false);

                } else {
                    exceedLimitDays = oracleDataService.validateScheduleEventInRanges(auth, contract, scheduleExt, years, false, false);
                    validationMessage = processValidationResult(exceedLimitDays, AuthorizationLimitTypeName.YEAR, false, false);
                }

            }

            // Validate if sum of all duration from Schedule Start Date and Schedule End Date exceeds Authorization Limit Total
            if (StringUtil.IsNullOrEmpty(validationMessage)) {
                Date[] scheduleExtRange = new Date[]{scheduleExt.getScheduleStartDate(), scheduleExt.getScheduleEndDate()};
                List<Date[]> scheduleExtRangeList = new ArrayList<>();
                scheduleExtRangeList.add(scheduleExtRange);
                List<ScheduleEventCountAndDuration> exceedLimitDays;

                if (AuthorizationServiceUnitName.VISIT == auth.getAuthorizationServiceUnitName()) {
                    exceedLimitDays = oracleDataService.validateScheduleEventInRanges(
                            auth, contract, scheduleExt, scheduleExtRangeList, true, true);
                    validationMessage = processValidationResult(exceedLimitDays, AuthorizationLimitTypeName.YEAR, true, true);

                } else {
                    exceedLimitDays = oracleDataService.validateScheduleEventInRanges(
                            auth, contract, scheduleExt, scheduleExtRangeList, false, true);
                    validationMessage = processValidationResult(exceedLimitDays, AuthorizationLimitTypeName.YEAR, false, true);
                }
            }

            // Set validation result to exchange body response
            if (StringUtil.IsNullOrEmpty(validationMessage)) {
                exchange.getIn().setBody(new ScheduleValidationResponse(true, ""));
            } else {
                exchange.getIn().setBody(new ScheduleValidationResponse(false, validationMessage));
            }
        } catch (Exception e){
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            logger.error("ValidateSchedule:- ERROR: " + errMsg);
            throw new SandataRuntimeException(errMsg, e);
        } finally {
            logger.stop();
        }
    }

    /**
     *  Build error message from validation result
     *
     * @param exceedLimitDays
     * @param authorizationLimitTypeName
     * @param isVisitValidation
     * @return
     */
    private String processValidationResult(List<ScheduleEventCountAndDuration> exceedLimitDays,
                                           AuthorizationLimitTypeName authorizationLimitTypeName,
                                           boolean isVisitValidation,
                                           boolean isLimitTotalValidation) {
        DateFormat format = new SimpleDateFormat("EEEE yyyy-MM-dd", Locale.ENGLISH);

        String errorMessage = "";
        int errorCount = 0;

        for (ScheduleEventCountAndDuration scheduleEventCountAndDuration : exceedLimitDays) {
            switch (authorizationLimitTypeName) {
                case DAY:
                    if (isVisitValidation) {
                        errorMessage = "Scheduled visits on " +
                                format.format(scheduleEventCountAndDuration.getScheduleEventStartDate()) +
                                (isLimitTotalValidation ? " cannot exceed total authorized limit" : " cannot exceed authorized limit");
                    } else {
                        errorMessage = "Scheduled hours on " + format.format(scheduleEventCountAndDuration.getScheduleEventStartDate()) +
                                (isLimitTotalValidation ? " cannot exceed total authorized limit" : " cannot exceed authorized limit");
                    }
                    break;

                default:    // WEEK/MONTH/YEAR or LIMIT TOTAL
                    if (isVisitValidation) {
                        errorMessage = "Scheduled visits between " +
                                ScheduleUtils.DATE_ONLY_FORMAT.format(scheduleEventCountAndDuration.getScheduleEventStartDate()) +
                                " and " +
                                ScheduleUtils.DATE_ONLY_FORMAT.format(scheduleEventCountAndDuration.getScheduleEventEndDate()) +
                                (isLimitTotalValidation ? " cannot exceed total authorized limit" : " cannot exceed authorized limit");
                    } else {
                        errorMessage = "Total scheduled hours between " +
                                ScheduleUtils.DATE_ONLY_FORMAT.format(scheduleEventCountAndDuration.getScheduleEventStartDate()) +
                                " and " +
                                ScheduleUtils.DATE_ONLY_FORMAT.format(scheduleEventCountAndDuration.getScheduleEventEndDate()) +
                                (isLimitTotalValidation ? " cannot exceed total authorized limit" : " cannot exceed authorized limit");
                    }
            }

            errorCount++;

            if (isVisitValidation && errorCount > 1) {
                return errorMessage = "Scheduled visits cannot exceed authorized limit";
            } else if (errorCount > 1) {
                return errorMessage = "Scheduled hours cannot exceed authorized limit";
            }
        }

        return errorMessage;
    }

    /**
     * Validate ScheduleEvent against Authorization or Order
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void validateScheduleEvent(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            ScheduleEvntExt scheduleEvntExt = exchange.getIn().getBody(ScheduleEvntExt.class);
            if (scheduleEvntExt == null) {
                throw new SandataRuntimeException("ScheduleEvent object cannot be null.");
            }

            if (scheduleEvntExt.getScheduleEventSK() == null || scheduleEvntExt.getScheduleEventSK().longValue() == 0) {
                throw new SandataRuntimeException("ScheduleEventSK cannot be null.");
            }

            if (scheduleEvntExt.getScheduleSK() == null || scheduleEvntExt.getScheduleSK().longValue() == 0) {
                throw new SandataRuntimeException("ScheduleSK cannot be null.");
            }

            if (scheduleEvntExt.getAuthorizationSK() == null || scheduleEvntExt.getAuthorizationSK().longValue() == 0) {
                throw new SandataRuntimeException("AuthorizationSK cannot be null.");
            }

            if (scheduleEvntExt.getScheduleEventEndDatetime().before(scheduleEvntExt.getScheduleEventStartDatetime())) {
                throw new SandataRuntimeException("ScheduleEvent Start Date Time cannot be after ScheduleEvent End Date Time.");
            }
            
            if ("CANCELLED".equalsIgnoreCase(scheduleEvntExt.getScheduleEventStatus())) {
                exchange.getIn().setBody(new ScheduleValidationResponse(true, "The schedule is being cancelled"));
                return;
            }
            
            // SAN-3313: Check event is overlapped with other events
            if (!StringUtil.IsNullOrEmpty(scheduleEvntExt.getStaffID())) {
                String validatedMsg = validateStaffOverlapedSchedule(scheduleEvntExt, null);
                if (!validatedMsg.isEmpty()) {
                    exchange.getIn().setBody(new ScheduleValidationResponse(false, validatedMsg));
                    return;
                }
            }

            // Get Schedule
            Schedule schedule = oracleDataService.getSchedule(scheduleEvntExt.getScheduleSK().longValue());
            if (schedule == null) {
                throw new SandataRuntimeException(String.format("Schedule (ScheduleSK=%s) not found", scheduleEvntExt.getScheduleSK()));
            }
            ScheduleExt scheduleExt = new ScheduleExt(schedule);

            // Get old ScheduleEvent
            ScheduleEvent oldScheduleEvent = oracleDataService.getScheduleEvent(scheduleEvntExt.getScheduleEventSK().longValue());
            if (oldScheduleEvent == null) {
                throw new SandataRuntimeException(String.format("ScheduleEvent (ScheduleEventSK=%s) not found", scheduleEvntExt.getScheduleEventSK()));
            }

            // Get Authorization or Order
            AuthorizationAdapter auth = oracleDataService.getAuthorizationAdapter(scheduleEvntExt.getAuthorizationSK().longValue(), scheduleEvntExt.getAuthorizationQualifier());
            if (auth == null) {
                throw new SandataRuntimeException("Authorization/Order not found");
            }

            // SAN-1052: Validate if new ScheduleEvent start/end date still within range of Authorization
            if (scheduleEvntExt.getScheduleEventStartDatetime().before(auth.getAuthorizationStartTimestamp())
                    || scheduleEvntExt.getScheduleEventStartDatetime().after(auth.getAuthorizationEndTimestamp())
                    || scheduleEvntExt.getScheduleEventEndDatetime().after(auth.getAuthorizationEndTimestamp())) {
                exchange.getIn().setBody(new ScheduleValidationResponse(false, "Schedule Event Start Date and End Date do not match with Authorization"));
                return;
            }

            // No limit validation needed if Auth.AuthorizationLimitTypeName = NONE
            if (AuthorizationLimitTypeName.NONE == auth.getAuthorizationLimitTypeName()) {
                exchange.getIn().setBody(new ScheduleValidationResponse(true, ""));
                return;

            }

            // Get Contract
            Contract contract = oracleDataService.getContractForAuthService(auth.getBusinessEntityID(), auth.getAuthorizationSK().longValue(), auth.isOrder());
            if (contract == null) {
                throw new SandataRuntimeException("Contract associated with Authorization Service not found");
            }

            // Create SelectedDaysOfWeek instance with selected start/end dates from schedEvent
            Date schedEvntStartDtInTargetTz = ScheduleUtils.convertToTargetTimeZone(scheduleEvntExt.getScheduleEventStartDatetime(), scheduleEvntExt.getTimezoneName());
            Date schedEvntEndDtInTargetTz = ScheduleUtils.convertToTargetTimeZone(scheduleEvntExt.getScheduleEventEndDatetime(), scheduleEvntExt.getTimezoneName());
            SelectedDaysOfWeek selectedDaysOfWeek = new SelectedDaysOfWeekBuilder(new SelectedDaysOfWeek())
                    .build(schedEvntStartDtInTargetTz, schedEvntEndDtInTargetTz, scheduleEvntExt.isScheduleEventOvernight())
                    .getSelectedDaysOfWeek();
            scheduleExt.setSelectedDaysOfWeek(selectedDaysOfWeek);

            logger.info(String.format("ValidateScheduleEvent: patientId [%s] - staffId [%s] - schedSK [%s] - schedevent [%s] : [%s]",
                    scheduleExt.getPatientID(),
                    scheduleExt.getStaffID(),
                    scheduleExt.getScheduleSK(),
                    scheduleEvntExt.getScheduleEventSK(),
                    scheduleEvntExt.toString())
            );

            String validationMessage = "";
            if (AuthorizationLimitTypeName.DAY == auth.getAuthorizationLimitTypeName()) {
                List<Date> selectedDate = new ArrayList<>();
                selectedDate.add(scheduleEvntExt.getScheduleEventStartDatetime());
                List<ScheduleEventCountAndDuration> exceedLimitDays;

                if (AuthorizationServiceUnitName.VISIT == auth.getAuthorizationServiceUnitName()) {
                    exceedLimitDays = oracleDataService.validateDailyScheduleEvent(
                            auth, oldScheduleEvent, contract, scheduleExt.getSelectedDaysOfWeek(), selectedDate, true);
                    validationMessage = processValidationResult(exceedLimitDays, AuthorizationLimitTypeName.DAY, true, false);

                } else {
                    validationMessage = ScheduleUtils.validateDailyTimeRanges(auth, scheduleExt);
                    if (StringUtil.IsNullOrEmpty(validationMessage)) {
                        exceedLimitDays = oracleDataService.validateDailyScheduleEvent(
                                auth, oldScheduleEvent, contract, scheduleExt.getSelectedDaysOfWeek(), selectedDate, false);
                        validationMessage = processValidationResult(exceedLimitDays, AuthorizationLimitTypeName.DAY, false, false);
                    }
                }

            } else if (AuthorizationLimitTypeName.WEEK == auth.getAuthorizationLimitTypeName()) {
                // Get WeekendStartDay from Contract or Payer
                WeekendStartDay weekendStartDay = getContractWeekendStartDay(contract);
                Date[] startAndEndDatesOfWeek = ScheduleUtils.getWeekForDate(scheduleEvntExt.getScheduleEventStartDatetime(), weekendStartDay);

                List<ScheduleEventCountAndDuration> exceedLimitDays;
                if (AuthorizationServiceUnitName.VISIT == auth.getAuthorizationServiceUnitName()) {
                    exceedLimitDays = oracleDataService.validateScheduleEventForUpdate(
                            auth, contract, scheduleEvntExt, startAndEndDatesOfWeek, true, false);
                    validationMessage = processValidationResult(exceedLimitDays, AuthorizationLimitTypeName.WEEK, true, false);

                } else {
                    exceedLimitDays = oracleDataService.validateScheduleEventForUpdate(
                            auth, contract, scheduleEvntExt, startAndEndDatesOfWeek, false, false);
                    validationMessage = processValidationResult(exceedLimitDays, AuthorizationLimitTypeName.WEEK, false, false);
                }

            } else if (AuthorizationLimitTypeName.MONTH == auth.getAuthorizationLimitTypeName()) {
                Date[] startAndEndDatesOfMonth = ScheduleUtils.getMonthForDate(scheduleEvntExt.getScheduleEventStartDatetime());

                List<ScheduleEventCountAndDuration> exceedLimitDays;
                if (AuthorizationServiceUnitName.VISIT == auth.getAuthorizationServiceUnitName()) {
                    exceedLimitDays = oracleDataService.validateScheduleEventForUpdate(
                            auth, contract, scheduleEvntExt, startAndEndDatesOfMonth, true, false);
                    validationMessage = processValidationResult(exceedLimitDays, AuthorizationLimitTypeName.MONTH, true, false);

                } else {
                    exceedLimitDays = oracleDataService.validateScheduleEventForUpdate(
                            auth, contract, scheduleEvntExt, startAndEndDatesOfMonth, false, false);
                    validationMessage = processValidationResult(exceedLimitDays, AuthorizationLimitTypeName.MONTH, false, false);
                }

            } else if (AuthorizationLimitTypeName.YEAR == auth.getAuthorizationLimitTypeName()) {
                Date[] startAndEndDatesOfYear = ScheduleUtils.getYearForDate(scheduleEvntExt.getScheduleEventStartDatetime());

                List<ScheduleEventCountAndDuration> exceedLimitDays;
                if (AuthorizationServiceUnitName.VISIT == auth.getAuthorizationServiceUnitName()) {
                    exceedLimitDays = oracleDataService.validateScheduleEventForUpdate(
                            auth, contract, scheduleEvntExt, startAndEndDatesOfYear, true, false);
                    validationMessage = processValidationResult(exceedLimitDays, AuthorizationLimitTypeName.YEAR, true, false);

                } else {
                    exceedLimitDays = oracleDataService.validateScheduleEventForUpdate(
                            auth, contract, scheduleEvntExt, startAndEndDatesOfYear, false, false);
                    validationMessage = processValidationResult(exceedLimitDays, AuthorizationLimitTypeName.YEAR, false, false);
                }
            }

            // Validate if sum of all duration from Schedule Start Date and Schedule End Date exceeds Authorization Limit Total
            if (StringUtil.IsNullOrEmpty(validationMessage)) {
                Date[] scheduleExtRange = new Date[]{scheduleExt.getScheduleStartDate(), scheduleExt.getScheduleEndDate()};

                List<ScheduleEventCountAndDuration> exceedLimitDays;
                if (AuthorizationServiceUnitName.VISIT == auth.getAuthorizationServiceUnitName()) {
                    exceedLimitDays = oracleDataService.validateScheduleEventForUpdate(
                            auth, contract, scheduleEvntExt, scheduleExtRange, true, true);
                    validationMessage = processValidationResult(exceedLimitDays, AuthorizationLimitTypeName.YEAR, true, true);

                } else {
                    exceedLimitDays = oracleDataService.validateScheduleEventForUpdate(
                            auth, contract, scheduleEvntExt, scheduleExtRange, false, true);
                    validationMessage = processValidationResult(exceedLimitDays, AuthorizationLimitTypeName.YEAR, false, true);
                }
            }

            // Set validation result to exchange body response
            if (StringUtil.IsNullOrEmpty(validationMessage)) {
                exchange.getIn().setBody(new ScheduleValidationResponse(true, ""));
            } else {
                exchange.getIn().setBody(new ScheduleValidationResponse(false, validationMessage));
            }
        } catch (Exception e){
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            logger.error("ValidateScheduleEvent - ERROR: " + errMsg);
            throw new SandataRuntimeException(errMsg, e);
        } finally {
            logger.stop();
        }
    }

    /**
     * Get WeekendStartDay from Contract or Payer
     *
     * @param contract
     * @return
     * @throws SandataRuntimeException
     */
    private WeekendStartDay getContractWeekendStartDay(Contract contract) throws SandataRuntimeException {
        WeekendStartDay weekendStartDay = WeekendStartDay.FRIDAY; // default WeekendStartDay is Friday

        if (contract == null) {
            return weekendStartDay;
        }

        if (contract.getContractWeekendStartDay() != null) {
            weekendStartDay = contract.getContractWeekendStartDay();
        } else {
            // When Contract.ContractWeekendStartDay is null, try to get weekendStartDay from Payer
            Payer payer = oracleDataService.getPayer(contract.getBusinessEntityID(), contract.getPayerID());
            if (payer == null) {
                throw new SandataRuntimeException("Payer not found");
            }

            if (payer.getPayerWeekendStartDay() != null) {
                weekendStartDay = payer.getPayerWeekendStartDay();
            }
        }

        return weekendStartDay;
    }

    private ScheduleService createOrUpdateScheduleService(ScheduleExt schedExt, ScheduleService scheduleService) {
        if (scheduleService == null) {
            scheduleService = new ScheduleService();
            scheduleService.setRecordCreateTimestamp(new Date());
            scheduleService.setChangeVersionID(BigInteger.valueOf(1));
        }
        scheduleService.setScheduleSK(schedExt.getScheduleSK());
        scheduleService.setServiceSK(schedExt.getServiceSK());
        scheduleService.setRecordUpdateTimestamp(new Date());

        return scheduleService;
    }

    private ScheduleAuthorization createOrUpdateScheduleAuthorization(ScheduleExt scheduleExt, ScheduleAuthorization scheduleAuthorization) {
        if (scheduleAuthorization == null) {
            scheduleAuthorization = new ScheduleAuthorization();

            if(StringUtil.IsNullOrEmpty(scheduleExt.getAuthorizationID())){
                scheduleAuthorization.setAuthorizationQualifier(AuthorizationQualifier.ORDER);
            }
            else{
                scheduleAuthorization.setAuthorizationQualifier(AuthorizationQualifier.AUTHORIZATION);
            }

            scheduleAuthorization.setRecordCreateTimestamp(new Date());
            scheduleAuthorization.setChangeVersionID(BigInteger.valueOf(1));
        }


        scheduleAuthorization.setScheduleSK(scheduleExt.getScheduleSK());
        scheduleAuthorization.setAuthorizationSK(scheduleExt.getAuthorizationSK());
        scheduleAuthorization.setRecordUpdateTimestamp(new Date());

        return scheduleAuthorization;
    }


    private List<ScheduleEvent> createScheduleEventList(ScheduleExt scheduleExt,
                                                        List<Date> dateList, Map<Integer, DayInWeek> selectedDaysInWeek,
                                                        Date dateRangeFrom, Date dateRangeTo) {
        return prepareScheduleEventList(scheduleExt,
                dateList,
                selectedDaysInWeek,
                false /*false: This will be an INSERT*/,
                dateRangeFrom,
                dateRangeTo,
                null /*When creating a new schedule event, the manuallyOverrideIndicator value is not being used*/);

    }

    private List<ScheduleEvent> updateScheduleEventList(ScheduleExt scheduleExt,
                                                        List<Date> dateList, Map<Integer, DayInWeek> selectedDaysInWeek,
                                                        Date dateRangeFrom, Date dateRangeTo, Boolean manuallyOverrideIndicator) {
        return prepareScheduleEventList(scheduleExt,
                dateList,
                selectedDaysInWeek,
                true /*true: This will be an UPDATE*/,
                dateRangeFrom,
                dateRangeTo,
                manuallyOverrideIndicator /*When creating a new schedule event, the manuallyOverrideIndicator value is not being used*/
        );

    }
    /**
     * only create ScheduleEvent within <code>dateRangeFrom</code> and <code>dateRangeTo</code> due to UTC convertion may be out of range
     * @param scheduleExt
     * @param dateList
     * @param selectedDaysInWeek
     * @param isUpdate
     * @param dateRangeFrom
     * @param dateRangeTo
     * @return
     */
    private List<ScheduleEvent> prepareScheduleEventList(ScheduleExt scheduleExt,
                                                         List<Date> dateList, Map<Integer, DayInWeek> selectedDaysInWeek, boolean isUpdate,
                                                         Date dateRangeFrom, Date dateRangeTo, Boolean manuallyOverrideIndicator) {


        List<ScheduleEvent> schedEventList = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        String targetTimeZone = scheduleExt.getTimezoneName();

        for (Date date : dateList) {
            cal.setTime(date);
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            DayInWeek dayInWeek = selectedDaysInWeek.get(dayOfWeek);

            ScheduleEvent schedEvent = createOrUpdateScheduleEventForStartAndEndTime(scheduleExt,
                    date,
                    dayInWeek,
                    null);

            if (isUpdate && manuallyOverrideIndicator != null) {
                schedEvent.setScheduleEventManuallyOverrideIndicator(manuallyOverrideIndicator);
            }
            
            if (isValidBetweenDateRange(
                    ScheduleUtils.convertToTargetTimeZone(schedEvent.getScheduleEventStartDatetime(),targetTimeZone),
                    ScheduleUtils.convertToTargetTimeZone(schedEvent.getScheduleEventEndDatetime(),targetTimeZone),
                    dateRangeFrom, dayInWeek.isOvernight() ? ScheduleUtils.addDays(dateRangeTo, 1) : dateRangeTo)) {
                schedEvent.getScheduleEventService().add(createOrUpdateScheduleEventService(schedEvent, scheduleExt, null));
                schedEvent.getScheduleEventAuthorization().add(createOrUpdateScheduleEventAuthorization(schedEvent, scheduleExt, null));
                
                // create new Visit with each Schedule Event
                schedEvent.getVisit().add(createOrUpdateVisit(schedEvent, null));
                
                schedEventList.add(schedEvent);
            }
            
        }

        return schedEventList;
    }

    private ScheduleEvent createOrUpdateScheduleEventForStartAndEndTime(ScheduleExt scheduleExt, Date scheduleEventDate,
                                                                        DayInWeek dayInWeek, ScheduleEvent scheduleEvent) {
        LocalTime startTime = dayInWeek.getStartTime();
        LocalTime endTime = dayInWeek.getEndTime();

        if (scheduleEvent == null) {

            Date terminationDate = null;
            try {
                terminationDate = new SimpleDateFormat("yyyy-MM-dd").parse("9999-12-31");
            } catch (ParseException pe) {
                pe.printStackTrace();
            }

            scheduleEvent = new ScheduleEvent();
            scheduleEvent.setScheduleEventID(generateUniqueId());

            if(StringUtil.IsNullOrEmpty(scheduleExt.getAuthorizationID())){
                scheduleEvent.setAuthorizationQualifier(AuthorizationQualifier.ORDER);
            }
            else{
                scheduleEvent.setAuthorizationQualifier(AuthorizationQualifier.AUTHORIZATION);
            }

            scheduleEvent.setBusinessEntityCalendarLookupSK(null);
            scheduleEvent.setMasterRateID("1");
            scheduleEvent.setScheduleEventTotalHours(null);
            scheduleEvent.setChangeReasonCode(null);
            scheduleEvent.setRecordCreateTimestamp(new Date());
            scheduleEvent.setRecordCreatedBy("MW");
            scheduleEvent.setRecordEffectiveTimestamp(new Date());
            scheduleEvent.setRecordTerminationTimestamp(terminationDate);
            scheduleEvent.setCurrentRecordIndicator(true);
            scheduleEvent.setRecordUpdatedBy("MW");
            scheduleEvent.setChangeVersionID(BigInteger.valueOf(1));
            scheduleEvent.setChangeReasonMemo("Reason");
        }

        scheduleEvent.setBusinessEntityID(scheduleExt.getBusinessEntityID());
        scheduleEvent.setPayerID(scheduleExt.getPayerId());
        scheduleEvent.setScheduleSK(scheduleExt.getScheduleSK());
        scheduleEvent.setAuthorizationSK(scheduleExt.getAuthorizationSK());
        scheduleEvent.setPlanOfCareSK(scheduleExt.getPlanOfCareSK());
        scheduleEvent.setPatientID(scheduleExt.getPatientID());
        scheduleEvent.setReferralSK(scheduleExt.getReferralSK());
        scheduleEvent.setStaffID(scheduleExt.getStaffID());
        scheduleEvent.setTimezoneName(scheduleExt.getTimezoneName());
        scheduleEvent.setScheduleEventBillRate(scheduleExt.getScheduleBillRate());
        scheduleEvent.setScheduleEventStatus(scheduleExt.getEventStatus() != null ? scheduleExt.getEventStatus() : "PENDING");
        scheduleEvent.setScheduleEventPayRate(scheduleExt.getSchedulePayRate());
        scheduleEvent.setScheduleEventUnit(scheduleExt.getScheduleUnit());
        scheduleEvent.setScheduleEventRestriction(scheduleExt.getScheduleRestrictions());
        scheduleEvent.setScheduleEventComment(scheduleExt.getScheduleComments());
        scheduleEvent.setRecordUpdateTimestamp(new Date());


        String timezoneName = scheduleEvent.getTimezoneName();
        if (startTime != null && scheduleEventDate != null) {
            scheduleEvent.setScheduleEventStartDatetime(
                    ScheduleUtils.convertToUTC(scheduleEventDate, startTime.getHourOfDay(), startTime.getMinuteOfHour(), timezoneName)
                    );
        }

        if (endTime != null && scheduleEventDate != null) {
            scheduleEvent.setScheduleEventEndDatetime(
                    ScheduleUtils.convertToUTC(
                            dayInWeek.isOvernight() ? ScheduleUtils.addDays(scheduleEventDate, 1) : scheduleEventDate, endTime.getHourOfDay(), endTime.getMinuteOfHour(), timezoneName)
                    );
        }

        //The ScheduleEventTotalHours is calculated in SECOND
        ScheduleUtils.populateScheduleEventTotalHours(scheduleEvent);

        return scheduleEvent;
    }


    private ScheduleEventService createOrUpdateScheduleEventService(ScheduleEvent schedEvent, ScheduleExt schedExt,
                                                                    ScheduleEventService scheduleEventService) {
        if (scheduleEventService == null) {
            Date terminationDate = null;
            try {
                terminationDate = new SimpleDateFormat("yyyy-MM-dd").parse("9999-12-31");
            } catch (ParseException pe) {
                pe.printStackTrace();
            }

            scheduleEventService = new ScheduleEventService();
            scheduleEventService.setRecordCreateTimestamp(new Date());
            scheduleEventService.setRecordEffectiveTimestamp(new Date());
            scheduleEventService.setRecordTerminationTimestamp(terminationDate);
            scheduleEventService.setRecordUpdatedBy("MW");
            scheduleEventService.setRecordCreatedBy("MW");
            scheduleEventService.setChangeReasonMemo("Reason");
            scheduleEventService.setCurrentRecordIndicator(true);
            scheduleEventService.setChangeVersionID(BigInteger.valueOf(1));
        }

        scheduleEventService.setScheduleEventSK(schedEvent.getScheduleEventSK());
        scheduleEventService.setServiceSK(schedExt.getServiceSK());
        scheduleEventService.setRecordUpdateTimestamp(new Date());

        return scheduleEventService;
    }

    private ScheduleEventAuthorization createOrUpdateScheduleEventAuthorization(ScheduleEvent schedEvent, ScheduleExt scheduleExt,
                                                                                ScheduleEventAuthorization scheduleEventAuthorization) {
        if (scheduleEventAuthorization == null) {
            scheduleEventAuthorization = new ScheduleEventAuthorization();

            if(StringUtil.IsNullOrEmpty(scheduleExt.getAuthorizationID())
                    && (schedEvent.getAuthorizationSK() != null && schedEvent.getAuthorizationSK().intValue() > 0)){
                scheduleEventAuthorization.setAuthorizationQualifier(AuthorizationQualifier.ORDER);
            }
            else{
                scheduleEventAuthorization.setAuthorizationQualifier(AuthorizationQualifier.AUTHORIZATION);
            }

            scheduleEventAuthorization.setRecordCreateTimestamp(new Date());
            scheduleEventAuthorization.setChangeVersionID(BigInteger.valueOf(1));
        }

        scheduleEventAuthorization.setScheduleEventSK(schedEvent.getScheduleEventSK());
        scheduleEventAuthorization.setAuthorizationSK(schedEvent.getAuthorizationSK());
        scheduleEventAuthorization.setRecordUpdateTimestamp(new Date());

        return scheduleEventAuthorization;
    }
    
    private Visit createOrUpdateVisit(ScheduleEvent schedEvent, Visit visit) {
        if (visit == null) {
            visit = new Visit();
            visit.setChangeVersionID(BigInteger.ONE);
            visit.setRecordCreatedBy("GEN_REST_SCHED_SERVICE");
            visit.setRecordUpdatedBy("GEN_REST_SCHED_SERVICE");
            visit.setRecordCreateTimestamp(new Date());
        }
        
        visit.setBusinessEntityID(schedEvent.getBusinessEntityID());
        visit.setScheduleEventSK(schedEvent.getScheduleEventSK());
        visit.setPatientID(schedEvent.getPatientID());
        visit.setStaffID(schedEvent.getStaffID());
        visit.setChangeReasonMemo(schedEvent.getChangeReasonMemo());
        visit.setRecordUpdateTimestamp(new Date());
        
        return visit;
    }

    /**
     * only create ScheduleRepeatDayOfWeek within <code>dateRangeFrom</code> and <code>dateRangeTo</code> due to UTC convertion may be out of range
     * @param scheduleExt
     * @param dateList
     * @param selectedDaysInWeek
     * @param dateRangeFrom
     * @param dateRangeTo
     * @return
     */
    private List<ScheduleRepeatDayOfWeek> createScheduleRepeatDayOfWeekList(ScheduleExt scheduleExt,
            List<Date> dateList, Map<Integer, DayInWeek> selectedDaysInWeek,
            Date dateRangeFrom, Date dateRangeTo) {
        List<ScheduleRepeatDayOfWeek> schedRepeatDayOfWeekList = new ArrayList<>();

        String targetTimeZone = scheduleExt.getTimezoneName();

        Calendar cal = Calendar.getInstance();
        for (Date date : dateList) {
            cal.setTime(date);
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            DayInWeek dayInWeek = selectedDaysInWeek.get(dayOfWeek);
            
            ScheduleRepeatDayOfWeek newRepeatDayOfWeek = createOrUpdateScheduleRepeatDayOfWeekForStartAndEndTime(scheduleExt,
                    date,
                    dayInWeek,
                    dayOfWeek,
                    null);

            if (isValidBetweenDateRange(
                    ScheduleUtils.convertToTargetTimeZone(newRepeatDayOfWeek.getScheduleStartTime(),targetTimeZone),
                    ScheduleUtils.convertToTargetTimeZone(newRepeatDayOfWeek.getScheduleEndTime(),targetTimeZone),
                    dateRangeFrom,
                    dayInWeek.isOvernight() ? ScheduleUtils.addDays(dateRangeTo, 1) : dateRangeTo )){

                schedRepeatDayOfWeekList.add(newRepeatDayOfWeek);
            }
        }

        return schedRepeatDayOfWeekList;
    }

    private ScheduleRepeatDayOfWeek createOrUpdateScheduleRepeatDayOfWeekForStartAndEndTime(ScheduleExt scheduleExt, Date repeatDayOfWeekDate,
                                                                                            DayInWeek dayInWeek, int dayOfWeek, ScheduleRepeatDayOfWeek scheduleRepeatDayOfWeek) {
        LocalTime startTime = dayInWeek.getStartTime();
        LocalTime endTime = dayInWeek.getEndTime();

        if (scheduleRepeatDayOfWeek == null) {
            scheduleRepeatDayOfWeek = new ScheduleRepeatDayOfWeek();
            scheduleRepeatDayOfWeek.setDayOfWeekNumber(BigInteger.valueOf(dayOfWeek));
            scheduleRepeatDayOfWeek.setRecordCreateTimestamp(new Date());
            scheduleRepeatDayOfWeek.setChangeVersionID(BigInteger.valueOf(1));
        }

        scheduleRepeatDayOfWeek.setScheduleSK(scheduleExt.getScheduleSK());
        
        scheduleRepeatDayOfWeek.setRecordUpdateTimestamp(new Date());
        
        
        String timezoneName = scheduleExt.getTimezoneName();
        if (startTime != null && repeatDayOfWeekDate != null) {
            scheduleRepeatDayOfWeek.setScheduleStartTime(
                    ScheduleUtils.convertToUTC(repeatDayOfWeekDate, startTime.getHourOfDay(), startTime.getMinuteOfHour(), timezoneName)
                    );
        }

        if (endTime != null && repeatDayOfWeekDate != null) {
            scheduleRepeatDayOfWeek.setScheduleEndTime(
                    ScheduleUtils.convertToUTC(
                            dayInWeek.isOvernight() ? ScheduleUtils.addDays(repeatDayOfWeekDate, 1) : repeatDayOfWeekDate, endTime.getHourOfDay(), endTime.getMinuteOfHour(), timezoneName)
                    );
        }

        return scheduleRepeatDayOfWeek;
    }

    private long executeRecursiveForScheduleAndEvent(final Connection connection, final Object data, final boolean bShouldInsert, long returnVal,
            String bsnEntId, Exchange exchange, SandataLogger logger)
            throws SandataRuntimeException {

        try {
            // GEOR-6612
            String timezoneFieldErrMsg = RestUtil.validateRequiredTimezoneName(data);
            if (timezoneFieldErrMsg.length() > 0) {
                throw new SandataRuntimeException(timezoneFieldErrMsg);
            }
            
            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(data);

            Object jpubType = new DataMapper().map(data);

            if (!(data instanceof ScheduleEventAuthorization) && !(data instanceof ScheduleEventService) && !(data instanceof ScheduleEventActivity)
                    && !(data instanceof Visit)) {
                setSk(jpubType, returnVal, "setSchedSk");
            }

            if (!(data instanceof ScheduleRepeatDayOfMonth) && !(data instanceof ScheduleRepeatDayOfWeek) && !(data instanceof ScheduleRepeatMonth)
                    && !(data instanceof ScheduleRepeatWeek) && !(data instanceof  ScheduleService) && !(data instanceof ScheduleTaskList)
                    && !(data instanceof ScheduleActivity) && !(data instanceof ScheduleAuthorization)) {
                setSk(jpubType, returnVal, "setSchedEvntSk");
            }

            long result = 0;
            
            BigInteger skValue = DataMapper.GetSK(data);
            Object originalObject = null;
            if (StringUtil.IsNullOrEmpty(bsnEntId) && !(data instanceof ScheduleEventService)) {
                // Init once
                bsnEntId = DataMapper.GetBusinessEntityID(data);
            }

            if (bShouldInsert) {
                result = oracleDataService.execute(
                        connection,
                        oracleMetadata.packageName(),
                        oracleMetadata.insertMethod(),
                        jpubType
                );
            } else {

                if (skValue == null) {
                    throw new SandataRuntimeException(String.format("%s: executeRecursive: skValue == NULL: [Type: %s]",
                            getClass().getName(), data.getClass().getName()));
                }
                returnVal = skValue.longValue();
                
                // bsnEntId is require, so only process if it exists!
                if (!StringUtil.IsNullOrEmpty(bsnEntId)) {
                    //TODO: Need to consider different approach since this may slow the service down
                    //dmr--For audit reasons, we need to get the current record before doing an update to it.
                    List currentStateList = (List) oracleDataService.executeGet(
                            connection,
                            oracleMetadata.packageName(),
                            oracleMetadata.getMethod(),
                            data.getClass().getName(),
                            skValue.longValue()
                    );

                    if (currentStateList.size() < 1) {
                        // Since this is an update, this should not happen!
                        throw new SandataRuntimeException(String.format("%s: executeRecursive: currentStateList is EMPTY!", getClass().getName()));
                    }

                    originalObject = currentStateList.get(0);

                } else {
                    logger.warn(String.format("%s: executeRecursive: bsnEntId is NULL or EMPTY!", data.getClass().getName()));
                }

                // UPDATE
                result = oracleDataService.execute(
                        connection,
                        oracleMetadata.packageName(),
                        oracleMetadata.updateMethod(),
                        jpubType
                );
            }
            
            
            // Audit for data insert/update
            if (!StringUtil.IsNullOrEmpty(bsnEntId) && result > 0) {
                Compare compare = null;
                compare = new Compare();
                compare.setOriginal(originalObject);
                compare.setUpdated(data);
                
                exchange.getIn().setHeader("AuditBsnEntID", bsnEntId);
                exchange.getIn().setHeader("AuditSkValue", skValue != null ? skValue.longValue() : result);

                String queueName = Messaging.Names.COMPONENT_TYPE_QUEUE + Audit.EVENT.AUDIT_CHANGED_EVENT.toString();

                logger.trace(String.format("%s: executeRecursive: [Result: %d]: [Queue: %s]: Sending audit message ...",
                        getClass().getName(), result, queueName));

                if(producerPojo == null) {
                    producerPojo = new ProducerPojo(exchange);
                }
                producerPojo.queueData(compare, queueName, exchange.getIn().getHeaders(), logger);
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
                            long insertResponse = executeRecursiveForScheduleAndEvent(connection, object, bShouldInsert, returnVal, bsnEntId, exchange, logger);
                            if (insertResponse == -1) {
                                if (bShouldInsert) {
                                    throw new SandataRuntimeException(format("INSERT: Failed: [%s]",
                                            object.getClass().getName()));
                                } else {
                                    throw new SandataRuntimeException(format("UPDATE: Failed: [%s]",
                                            object.getClass().getName()));
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
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
    }
    
    /**
     * delete Schedule Event with relevant tables
     * @param connection
     * @param schedEvent
     * @return
     */
    private long logicalDeleteScheduleEventWithRelevantTables(Connection connection, ScheduleEvent schedEvent, Exchange exchange, SandataLogger logger) {
        // logical delete Schedule Event
        schedEvent.setCurrentRecordIndicator(false);
        schedEvent.setRecordUpdateTimestamp(new Date());
        schedEvent.setRecordTerminationTimestamp(new Date());
        
        long logicalDeleteEventResult = executeRecursiveForScheduleAndEvent(connection, schedEvent, false, -999, schedEvent.getBusinessEntityID(), exchange, logger);
        if (logicalDeleteEventResult < 0) {
            throw new SandataRuntimeException("Logically delete ScheduleEvent was not successful!");
        }

        // logical delete Schedule Event Service
        String sqlGetSchedEvntService = "SELECT * FROM SCHED_EVNT_SVC WHERE SCHED_EVNT_SK = ?"
                + " AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";
        List<ScheduleEventService> schedEventServiceList = (List<ScheduleEventService>) oracleDataService.getEntitiesForId(
                connection,
                sqlGetSchedEvntService,
                "com.sandata.lab.data.model.dl.model.ScheduleEventService",
                schedEvent.getScheduleEventSK());
        for (ScheduleEventService schedEventService : schedEventServiceList) {
            schedEventService.setCurrentRecordIndicator(false);
            schedEventService.setRecordUpdateTimestamp(new Date());
            schedEventService.setRecordTerminationTimestamp(new Date());
            
            long logicalDeleteEventServiceResult = executeRecursiveForScheduleAndEvent(connection, schedEventService, false, -999, null, exchange, logger);
            if (logicalDeleteEventServiceResult < 0) {
                throw new SandataRuntimeException("Logically delete ScheduleEventService was not successful!");
            }
        }

        // delete Schedule Event Authorization
        String sqlGetSchedEvntAuth = "SELECT * FROM SCHED_EVNT_AUTH WHERE SCHED_EVNT_SK = ?";
        List<ScheduleEventAuthorization> schedEventAuthList = (List<ScheduleEventAuthorization>) oracleDataService.getEntitiesForId(
                connection,
                sqlGetSchedEvntAuth,
                "com.sandata.lab.data.model.dl.model.ScheduleEventAuthorization",
                schedEvent.getScheduleEventSK());
        for (ScheduleEventAuthorization schedEventAuth : schedEventAuthList) {
            long deleteEventServiceResult = oracleDataService.executeDelete(connection, schedEventAuth);
            if (deleteEventServiceResult < 0) {
                throw new SandataRuntimeException("Delete ScheduleEventAuthorization was not successful!");
            }
        }
        
        // delete Visit
        String sqlGetVisitAssociatedWithSchedEvent = "SELECT * FROM VISIT WHERE SCHED_EVNT_SK = ?";
        List<Visit> visitList = (List<Visit>) oracleDataService.getEntitiesForId(
                connection,
                sqlGetVisitAssociatedWithSchedEvent,
                "com.sandata.lab.data.model.dl.model.Visit",
                schedEvent.getScheduleEventSK());

        for (Visit visit : visitList) {
            long deleteVisitResult = oracleDataService.executeDelete(connection, visit);
            if (deleteVisitResult < 0) {
                throw new SandataRuntimeException("Delete Visit was not successful!");
            }
        }
        
        return logicalDeleteEventResult;
    }
    
    /**
     * used only for Schedule Event Only update
     * @param connection
     * @param oldScheduleEvent
     * @param scheduleEventToUdpate
     * @return
     */
    private ScheduleEvent createScheduleEventFromOldEvent(Connection connection, ScheduleEvent oldScheduleEvent, ScheduleEvent scheduleEventToUdpate) {
        List<Schedule> scheduleList = (List<Schedule>) oracleDataService.getEntitiesForId(connection, 
                "SELECT * FROM SCHED WHERE SCHED_SK = ?", 
                "com.sandata.lab.data.model.dl.model.Schedule", 
                oldScheduleEvent.getScheduleSK());
        if (scheduleList == null || scheduleList.isEmpty()) {
            throw new SandataRuntimeException(String.format("The Schedule with ScheduleSK = %s does not exists!", oldScheduleEvent.getScheduleSK()));
        }
        ScheduleExt scheduleExt = new ScheduleExt();
        BeanUtils.copyProperties(scheduleList.get(0), scheduleExt);
        
        List<ScheduleEventService> scheduleEventSvcList = (List<ScheduleEventService>) oracleDataService.getEntitiesForId(connection, 
                "SELECT * FROM SCHED_EVNT_SVC WHERE SCHED_EVNT_SK = ?", 
                "com.sandata.lab.data.model.dl.model.ScheduleEventService", 
                oldScheduleEvent.getScheduleEventSK());
        if (scheduleEventSvcList == null || scheduleEventSvcList.isEmpty()) {
            throw new SandataRuntimeException(String.format("The Schedule Event Service for ScheduleEventSK = %s does not exists!", oldScheduleEvent.getScheduleEventSK()));
        }
        scheduleExt.setServiceSK(scheduleEventSvcList.get(0).getServiceSK());
        
        scheduleEventToUdpate.setRecordCreateTimestamp(new Date());
        scheduleEventToUdpate.setCurrentRecordIndicator(true);
        scheduleEventToUdpate.setRecordUpdateTimestamp(new Date());
        
        scheduleEventToUdpate.getScheduleEventService().add(createOrUpdateScheduleEventService(scheduleEventToUdpate, scheduleExt, null));
        scheduleEventToUdpate.getScheduleEventAuthorization().add(createOrUpdateScheduleEventAuthorization(scheduleEventToUdpate, scheduleExt, null));
        scheduleEventToUdpate.getVisit().add(createOrUpdateVisit(scheduleEventToUdpate, null));
        
        return scheduleEventToUdpate;
    }
    
    /** 
     * 
     * @param tobeUpdatedEvent The Event to be updated, should contain ScheduleEventStartDatetime, ScheduleEventEndDatetime, TimezoneName 
     * @param scheduleSK The Schedule Event SK to be updated for not to check all Events belongs to this Schedule
     * @return
     */
    private String validateStaffOverlapedSchedule(ScheduleEvent tobeUpdatedEvent, BigInteger scheduleSK) {
        String filterTyp = "(T1.STAFF_ID = ?) AND ";
        List<Object> params = new ArrayList<>();
        params.add(tobeUpdatedEvent.getStaffID());
        
        List<ScheduleEventService> originalEventService = tobeUpdatedEvent.getScheduleEventService();
        if (originalEventService.isEmpty()) {
            List<ScheduleEventService> eventServiceList = (List<ScheduleEventService>) oracleDataService.getEntitiesForId(
                    "SELECT * FROM SCHED_EVNT_SVC WHERE SCHED_EVNT_SK = ? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)", 
                    ScheduleEventService.class.getName(), 
                    tobeUpdatedEvent.getScheduleEventSK());
            tobeUpdatedEvent.getScheduleEventService().clear();
            tobeUpdatedEvent.getScheduleEventService().addAll(eventServiceList);
        }
        
        Response response = oracleDataService.findStaff(filterTyp, 
                tobeUpdatedEvent.getBusinessEntityID(), 
                params, 
                "", 
                1, 1, 0, 0, null, null, 
                tobeUpdatedEvent,
                scheduleSK); // Event only
        
        List staffs = (List) response.getData();
        if (staffs.size() == 0) {
            return "Staff has another schedule during the same time as this schedule OR the availability is not cover the schedule";
        }
        
        tobeUpdatedEvent.getScheduleEventService().clear();
        tobeUpdatedEvent.getScheduleEventService().addAll(originalEventService);
        
        return "";
    }
    
    private void safeCloseConnection(Connection conn) {
        this.oracleDataService.closeOracleConnection(conn);
    }

    private String generateUniqueId (){
        return UUID.randomUUID().toString();
    }
}
