package com.sandata.lab.rules.test;

import com.sandata.lab.data.model.constants.visit.Visit;
import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.data.model.dl.model.VisitTaskList;
import com.sandata.lab.data.model.dl.model.extended.ScheduleEventExt;
import com.sandata.lab.data.model.request.visit.ScheduleEventRequest;
import com.sandata.lab.data.model.response.visit.ScheduleEventResponse;
import com.sandata.lab.rules.call.matching.aggregate.PrepForAggregation;
import com.sandata.lab.rules.call.model.*;
import org.apache.camel.*;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.JndiRegistry;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.spi.Language;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.apache.camel.util.KeyValueHolder;
import org.drools.core.command.impl.GenericCommand;
import org.drools.core.command.runtime.BatchExecutionCommandImpl;
import org.junit.Before;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;



/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 12/11/2015
 * Time: 3:48 PM
 */
public class StaffAndVisitAggregationTest extends CamelBlueprintTestSupport {

    private final static String activemq_AGGREGATE_STAFF = "direct:AGGREGATE_STAFF";
    private final static String activemq_AGGREGATE_VISIT_EVENTS = "direct:AGGREGATE_VISIT_EVENTS";
    private CallPreferences callPreferences;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");

    private Logger logger = LoggerFactory.getLogger(StaffAndVisitAggregationTest.class);
    private boolean preExisitngVisits = true;
    private int visitToUpdate = -1;
    private Map<String, Object> headers = new HashMap<>();
    private String staffId  = "123123123";
    private String dnis = "1231231234";
    private String ani = "1231231230";
    private List<VisitEvent> visitEventList;


    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return super.createRouteBuilder();//AggregateVisitRoute();//
    }

    @Override
    public boolean isUseAdviceWith() {
        return true;
        //return super.isUseAdviceWith();
    }

    @Override
    protected void doPostSetup() throws Exception {
        super.doPostSetup();




    }

    @Before
    public void initialize() throws Exception {

     /*   context.getRouteDefinition("DirectSendVisitEvent").adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                interceptSendToEndpoint("activemq:queue:"+ Visit.EVENT.CREATE_VISIT_EVENT_REQUEST.toString())
                        .skipSendToOriginalEndpoint()
                        .to("mock:fooCreateVisitEventRequest");
            }
        });
        */
        /*
        context.getRouteDefinition("direct-sendVisit").adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                interceptSendToEndpoint("activemq:queue:CEP_ENGINE_VISITS")
                        .skipSendToOriginalEndpoint()
                        .to("mock:CEP_ENGINE_VISITS");
            }
        });*/
        /*
        //direct-prepStaffToAggregation
        context.getRouteDefinition("direct-prepStaffToAggregation").adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                interceptSendToEndpoint("activemq:queue:AGGREGATE_STAFF")
                        .skipSendToOriginalEndpoint()
                        .to("direct:AGGREGATE_STAFF");
            }
        });
        context.getRouteDefinition("activemq-AGGREGATE_VISIT_EVENTS").adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                interceptSendToEndpoint("direct:prepForStaffRules")
                        .skipSendToOriginalEndpoint()
                        .to("mock:prepforstaff");
                interceptSendToEndpoint("direct:aggregationCompleted")
                        .skipSendToOriginalEndpoint()
                        .to("mock:aggregationCompleted");
            }
        });
        */
        callPreferences = new CallPreferences();
        headers.put(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT, dnis+staffId);

    }

    private VisitEvent getVisitEvent(VisitEvent visitEvent, Date date) {
        visitEvent.setAutomaticNumberIdentification(ani);
        visitEvent.setChangeReasonMemo("testing");
        visitEvent.setTimezoneName("US/Eastern");
        visitEvent.setStaffID(staffId);
        visitEvent.setDialedNumberIdentificationService(dnis);
        visitEvent.setVisitEventDatetime(date);

        return visitEvent;
    }
    @Override
    protected Properties useOverridePropertiesWithPropertiesComponent() {
        Properties properties = super.useOverridePropertiesWithPropertiesComponent();
        //Must have predefined tmp which is String object returned from super
        //This is high overhead and should only be used for testing and debug!
        //Tom Dornseif
        //<cm:property name="activeMQ.endpoint.AGGREGATE_VISIT_EVENTS_FROM" value="activemq:queue:AGGREGATE_VISIT_EVENTS?concurrentConsumers=1&amp;maxMessagesPerTask=1&amp;destination.consumer.exclusive=true&amp;destination.consumer.priority=10" />
        //<cm:property name="activeMQ.endpoint.AGGREGATE_VISIT_EVENTS_TO" value="activemq:queue:AGGREGATE_VISIT_EVENTS" />
        Object o = new Object() {
        };
        String className = o.getClass().getEnclosingClass().getName();
        String methodName = o.getClass().getEnclosingMethod().getName();
        String typeName = o.getClass().getEnclosingMethod().getReturnType().getName();

        if(properties != null) {
            for (Map.Entry p : properties.entrySet()) {
                String tmp = "property = " + p.getKey().toString() + ", value = " + p.getValue();

                org.slf4j.LoggerFactory.getLogger(o.getClass().getEnclosingClass()).info(String.format(
                        "%s : method = %s : super returned type : %s with value of %s ", className, methodName, typeName, tmp));
            }
        }
        else
        {
            properties = new Properties();
            properties.setProperty("activeMQ.endpoint.AGGREGATE_VISIT_EVENTS_FROM", "seda:AGGREGATE_VISIT_EVENTS");
            properties.setProperty("activeMQ.endpoint.AGGREGATE_VISIT_EVENTS_TO", "seda:AGGREGATE_VISIT_EVENTS");
            properties.setProperty("activeMQ.endpoint.SCHEDULE_EVENT_RESPONSE", "seda:SCHEDULE_EVENT_RESPONSE");
            properties.setProperty("activeMQ.endpoint.CREATE_VISIT_EVENT_RESPONSE", "seda:CREATE_VISIT_EVENT_RESPONSE");
            properties.setProperty("activeMQ.endpoint.CREATE_VISIT_EVENT_REQUEST", "seda:CREATE_VISIT_EVENT_REQUEST");

            properties.setProperty("activeMQ.endpoint.CEP_ENGINE_VISITS", "mock:CEP_ENGINE_VISITS");
        }

        return properties;
    }
    @Override
    public void setUp() throws Exception {
        org.slf4j.LoggerFactory.getLogger(StaffAndVisitAggregationTest.class).info("StaffAggregationTest : super.setUp()");
        //replaceRouteFromWith("AGGREGATE_STAFF", "direct:AGGREGATE_STAFF");
        //replaceRouteFromWith("activemq-AGGREGATE_VISIT_EVENTS", "direct:AGGREGATE_VISIT_EVENTS");
        //replaceRouteFromWith(Visit.EVENT.CREATE_VISIT_EVENT_RESPONSE.toString(), "seda:fooVisitEventResponse");
        //replaceRouteFromWith("activemq-AGGREGATE_VISIT_EVENTS","seda:fooVisitEvents");

        super.setUp();
    }

    @Test
    public void testVisitEventsWhenScheduledOccursDuringUnplannedInprogressSameAni() throws Exception {

        visitEventList = new ArrayList();
        Date call = simpleDateFormat.parse("2016-04-14 14:30:00 EDT");
        Date unplannedFrom = simpleDateFormat.parse("2016-04-14 13:00:00 EDT");
        Date unplannedTo = simpleDateFormat.parse("2016-04-14 14:00:00 EDT");//make sure you test an overlapper
        Date scheduledFrom = simpleDateFormat.parse("2016-04-14 15:00:00 EDT");
        Date scheduledTo = simpleDateFormat.parse("2016-04-14 16:00:00 EDT");//make sure you test an overlapper
        BigInteger unplannedVisitSk = BigInteger.TEN;
        BigInteger scheduledVisitSk = unplannedVisitSk.add(BigInteger.ONE);

        VisitEvent visitEvent = getVisitEvent(new VisitEvent(), call);
        Date now = new Date();
        VisitEventFact inserted = new VisitEventFact(visitEvent);
        inserted.setCallInTime(call);

        String from = "2016-04-13 14:30:00 EDT";//"2015-12-14 08:00:00 EDT";
        String to = "2016-04-15 14:30:00 EDT";

        ScheduleEventResponse scheduleEventResponse = getScheduleEventResponse(from, to);
        //create an unplanned first
        String patientId = null;

        ScheduleEventExt scheduleEventExtUnplanned = getScheduleEventExt("1", null, unplannedFrom, unplannedTo, patientId, unplannedVisitSk, unplannedFrom, unplannedTo);
        ScheduleEventExt scheduleEventExtSched = getScheduleEventExt("1", BigInteger.TEN, scheduledFrom, scheduledTo, patientId, scheduledVisitSk, null, null );
        //ScheduleEventExt scheduleEventExtUnplanned = getScheduleEventExt("1", null, scheduledFrom, scheduledTo, patientId, unplannedVisitSk, scheduledFrom, scheduledTo);
        //ScheduleEventExt scheduleEventExtSched = getScheduleEventExt("1", BigInteger.TEN, unplannedFrom, unplannedTo, patientId, scheduledVisitSk, null, null );

        List<ScheduleEventExt> scheduleEventExtList = new ArrayList<>();
        scheduleEventExtList.add(scheduleEventExtUnplanned);
        scheduleEventExtList.add(scheduleEventExtSched);
        scheduleEventResponse.setScheduleEventsExt(scheduleEventExtList);
        MockEndpoint mockOut2 = getMockEndpoint("mock:CEP_ENGINE_VISITS");
        startCamelContext();
        mockOut2.setExpectedCount(1);
        template.sendBody("seda:SCHEDULE_EVENT_RESPONSE", scheduleEventResponse);
        template.sendBody("seda:AGGREGATE_VISIT_EVENTS", inserted);

        mockOut2.assertIsSatisfied();
            logger.info("mock2 10 seconds past without satisfaction!");

        ServiceStatus status = mockOut2.getStatus();

        logger.info("Status of mock2 = " + status.name());

        //if(status.isStopped()) {
            VisitFact visitFact = mockOut2.getExchanges().get(0).getIn().getBody(VisitFact.class);
            String msg = "\r\n**************  VISIT EVENT INSERTED INTO VISIT *****************\r\n";
            msg = getVisitDescription(visitFact, msg);
            logger.info(msg);
            if(visitFact.getState() == State.MATCHED && visitFact.getAni().equals(ani) && visitFact.getStaffId().equals(staffId)
                    && visitFact.getScheduledFrom().equals(scheduledFrom) && visitFact.getVisit().getVisitSK().equals(scheduledVisitSk)
                    && visitFact.getVisit().getVisitActualStartTimestamp().equals(call)) {
                mockOut2.assertIsSatisfied();
            }
            else
            {
                mockOut2.assertIsNotSatisfied();
            }




//        }

        stopCamelContext();
    }

    private ScheduleEventExt getScheduleEventExt(String businessEntityId, BigInteger schedEventSk, Date from, Date to,
                                                 String patientId, BigInteger visitSk, Date visitActualStartDate, Date visitActualEndDate) {
        ScheduleEventExt scheduleEventExt = new ScheduleEventExt();
        scheduleEventExt.setAni(ani);
        scheduleEventExt.setBusinessEntityID("1");
        if(schedEventSk != null)
            scheduleEventExt.setScheduleEventSK(schedEventSk);
        else
            scheduleEventExt.setScheduleEventSK(BigInteger.valueOf(visitSk.negate().longValue()));
        scheduleEventExt.setScheduleEventStartDatetime(from);
        scheduleEventExt.setScheduleEventEndDatetime(to);
        scheduleEventExt.setStaffID(staffId);
        com.sandata.lab.data.model.dl.model.Visit visit = null;

        if ( visitSk != null ) {
            if (scheduleEventExt.getVisit().size() > 0) {
                visit = scheduleEventExt.getVisit().get(0);
            } else {
                visit = new com.sandata.lab.data.model.dl.model.Visit();
            }
            if (schedEventSk != null && visit.getScheduleEventSK() != null && BigInteger.valueOf(schedEventSk.longValue()) != visit.getScheduleEventSK()) {
                LoggerFactory.getLogger("VisitRepository").error("Visit had mismatched SCHED_EVNT_SK ");
            } else if (schedEventSk != null) {
                visit.setScheduleEventSK(BigInteger.valueOf(schedEventSk.longValue()));
            }
            visit.setVisitSK(BigInteger.valueOf(visitSk.longValue()));
            if (patientId != null && patientId.length() > 0) {
                visit.setPatientID(patientId);
            }
            if (staffId != null && staffId.length() > 0) {
                visit.setStaffID(staffId);
            }
            visit.setBusinessEntityID(businessEntityId);
            if (visitActualStartDate != null) {
                visit.setVisitActualStartTimestamp(new Date(visitActualStartDate.getTime()));
            }
            if (visitActualEndDate != null) {
                visit.setVisitActualEndTimestamp(new Date(visitActualEndDate.getTime()));
            }
            scheduleEventExt.getVisit().add(visit);
        }
        return scheduleEventExt;
    }


    private ScheduleEventResponse getScheduleEventResponse(String fromDate, String toDate) throws Exception {
        ScheduleEventResponse scheduleEventResponse = new ScheduleEventResponse();
        ScheduleEventRequest scheduleEventRequest = new ScheduleEventRequest();
        scheduleEventRequest.setAni(ani);
        scheduleEventRequest.setFromDate(simpleDateFormat.parse(fromDate));
        scheduleEventRequest.setToDate(simpleDateFormat.parse(toDate));
        scheduleEventRequest.setStaffId(staffId);
        scheduleEventRequest.setDnis(dnis);
        scheduleEventResponse.setScheduleEventRequest(scheduleEventRequest);
        return scheduleEventResponse;
    }


    private ArrayList<VisitFact> getVisitFactArray(String dnis, String staffId, String ani, Date scheduleDate, Date testDate, int size) {
        int offset = 1;
        ArrayList<VisitFact> arrayList = new ArrayList<>();
        if(size == 4)
            offset = 4;


        for(int i = 1; i < size;i++) {

            VisitFact visitFact2 = new VisitFact();

            Date fromDate = scheduleDate;
            Date toDate = Schedule.CreateDateWithOffset(scheduleDate, 1);
            visitFact2.setScheduledFrom(fromDate);
            visitFact2.setScheduledTo(toDate);
            visitFact2.setDnis(dnis);
            visitFact2.setStaffId(staffId);

            String uuid = UUID.randomUUID().toString();
            visitFact2.setScheduleEventSK(new BigInteger("156"));
            visitFact2.getVisit().setStaffID("100");
            visitFact2.getVisit().setPatientID("101");
            if(testDate.after(fromDate)  && testDate.before(toDate)) {
                visitFact2.setAni(ani);
                if(preExisitngVisits) {
                    VisitEvent visitEvent = new VisitEvent();
                    visitEvent.setVisitEventDatetime(Schedule.CreateDateWithOffsetHourMinute(visitFact2.getScheduledFrom(), 0, 5));
                    visitEvent.setStaffID(visitFact2.getStaffId());
                    visitEvent.setDialedNumberIdentificationService(visitFact2.getDnis());
                    visitEvent.setAutomaticNumberIdentification(visitFact2.getAni());
                    visitFact2.setCallCallIn(new VisitEventFact(visitEvent));

                }
                visitToUpdate = i;
            }
            else {
                String tmpAni = ani.substring(0,6) + Integer.toString(i)+ Integer.toString(i)+ Integer.toString(i)+ Integer.toString(i);
                visitFact2.setAni(tmpAni);
            }

            visitFact2.setState(State.AGG_INSERTED_FROM_RESPONSE);
            arrayList.add(visitFact2);
            logger.info("schedule created SK : " + visitFact2.getVisit().getScheduleEventSK() + ", fromDate : " + fromDate.toString() + ", toDate : " + toDate.toString() );
            scheduleDate = Schedule.CreateDateWithOffset(scheduleDate, offset);


        }
        return arrayList;
    }
    private VisitEventFact updateVisitEventFact(VisitEventFact visitEventFact, Date eventTime, String taskId) {
        visitEventFact.setCallInTime(eventTime);
        List<VisitTaskList> taskList2 = new ArrayList<>();
        VisitTaskList vtl2 = new VisitTaskList();
        vtl2.setVisitSK(BigInteger.ONE);
        vtl2.setCriticalTaskIndicator(true);
        vtl2.setVisitTaskListID(taskId);
        taskList2.add(vtl2);
        visitEventFact.setTasks(taskList2);
        return visitEventFact;

    }

    private VisitEventFact getVisitEventFact(String dnis, String ani, String staffId, Date visitDate, String taskId) {
        VisitEventFact visitEventFact = new VisitEventFact();
        visitEventFact.setAutomaticNumberIdentification(ani);
        visitEventFact.setDialedNumberIdentificationService(dnis);
        visitEventFact.setStaffID(staffId);
        List<VisitTaskList> taskList = new ArrayList<>();
        VisitTaskList vtl = new VisitTaskList();
        vtl.setVisitSK(BigInteger.ONE);
        vtl.setCriticalTaskIndicator(true);
        vtl.setVisitTaskListID(taskId);
        taskList.add(vtl);


        visitEventFact.setVisitEventDatetime(visitDate);
        visitEventFact.setTasks(taskList);
        return visitEventFact;
    }

    private String getVisitDescription(VisitFact visitFact, String msg) {
        msg+= "\r\n\t\t\t\t state : " + visitFact.getState().name();
        msg+= "\r\n\t\t\t\t staffId : " + visitFact.getStaffId();
        msg+= "\r\n\t\t\t\t ani : " + visitFact.getAni();
        if(visitFact.getScheduledFrom() != null)
            msg+= "\r\n\t\t\t\t scheduled from : " + visitFact.getScheduledFrom().toString();
        if(visitFact.getScheduledTo() != null)
            msg+= "\r\n\t\t\t\t scheduled to : " + visitFact.getScheduledTo().toString();

        if(visitFact.getCallIn() != null)
            msg+= "\r\n\t\t\t\t call in : " + visitFact.getCallIn().toString();
        if(visitFact.getCallOut() != null)
            msg+= "\r\n\t\t\t\t call out : " + visitFact.getCallOut().toString();
        if(visitFact.getTaskList().size() > 0) {
            for(int i = 0;i < visitFact.getTaskList().size();i++) {
                msg += "\r\n\t\t\t\t taskList : " + visitFact.getTaskList().get(i).getVisitTaskListID();
            }
        }
        if(visitFact.getVisit().getVisitEvent().size()>0) {
            msg += "\r\n\t\t\t\t VisitEvents Found  ( " + Integer.toString(visitFact.getVisit().getVisitEvent().size()) + " )";
            for(int j = 0;j<visitFact.getVisit().getVisitEvent().size();j++) {
                VisitEvent ve = visitFact.getVisit().getVisitEvent().get(j);
                msg += "\r\n\t\t\t\t\t VisitEvent ("+ Integer.toString(j)+ ") " + ve.getVisitEventDatetime().toString();

            }
        }
        return msg;
    }


    /*
    @Test
    public void testVisitEventsFromResponseOutsideSchedule() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        String startTime = "2015-12-14 08:00:00 EDT";
        String testTime = "2015-12-14 10:00:00 EDT";
        VisitFact visitFactUpdated = null;

        preExisitngVisits = false;
        boolean runSecondSubTest = true;
        boolean runThirdSubTest = false;
        int mock1 =0, mock2 = 1;
        if(runSecondSubTest)
            mock2++;
        if(runThirdSubTest)
            mock2++;
        MockEndpoint mockOut1 = getMockEndpoint("mock:prepforstaff");
        MockEndpoint mockOut2 = getMockEndpoint("mock:CEP_ENGINE_VISITS");

        Date date = simpleDateFormat.parse(testTime);
        VisitEventFact visitEventFact = getVisitEventFact(dnis, ani, staffId, date, "0015" );
        //simulate this from call server
        template.sendBodyAndHeaders(activemq_AGGREGATE_VISIT_EVENTS,visitEventFact, headers);
        Date scheduleDate = simpleDateFormat.parse(startTime);
        visitToUpdate = -1;
        ArrayList arrayList = getVisitFactArray(dnis,staffId,ani,scheduleDate, date, 4);
        ((VisitFact)arrayList.get(0)).setAni(ani);
        template.sendBodyAndHeaders(activemq_AGGREGATE_VISIT_EVENTS,arrayList, headers);
        mockOut1.setExpectedMessageCount(mock1);
        mockOut2.setExpectedMessageCount(mock2);
        ServiceStatus status = mockOut2.getStatus();
        logger.info("Status of mock2 = " + status.name());

        if(mock2 > 0) {
            VisitFact visitFact = mockOut2.getExchanges().get(0).getIn().getBody(VisitFact.class);
            String msg = "\r\n**************  VISIT EVENT INSERTED INTO VISIT *****************\r\n";
            msg = getVisitDescription(visitFact, msg);
            logger.info(msg);
            visitFactUpdated = visitFact;


        }
        else {
            ArrayList list = mockOut1.getExchanges().get(0).getIn().getBody(ArrayList.class);
            for (int i = 0; i < list.size(); i++) {
                logger.info("visit for staff : " + ((VisitFact) list.get(0)).getStaffId() + "returned.");

            }
        }
        if(runSecondSubTest) {
            String testTime2 = "2015-12-14 10:45:00 EDT";//added 10 minutes still closer to from
            // now the out isnt null though

            visitEventFact = updateVisitEventFact(visitEventFact, simpleDateFormat.parse(testTime2), "0017");

            template.sendBodyAndHeaders(activemq_AGGREGATE_VISIT_EVENTS,visitEventFact, headers);
            visitToUpdate = 0;
            arrayList.remove(visitToUpdate);
            visitFactUpdated.setState(State.AGG_INSERTED_FROM_RESPONSE);
            arrayList.add(visitToUpdate, visitFactUpdated);
            template.sendBodyAndHeaders(activemq_AGGREGATE_VISIT_EVENTS, arrayList, headers);
            if(mock2 > 0) {
                VisitFact visitFact = mockOut2.getExchanges().get(1).getIn().getBody(VisitFact.class);
                String msg = "\r\n**************  VISIT EVENT INSERTED INTO VISIT *****************\r\n";
                msg = getVisitDescription(visitFact, msg);
                logger.info(msg);
                visitFactUpdated = visitFact;
            }

        }
        assertMockEndpointsSatisfied();
    }


    @Test
    public void testVisitEventsFromResponseAtEnd() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        String startTime = "2015-12-14 08:00:00 EDT";
        String testTime = "2015-12-14 10:35:00 EDT";
        VisitFact visitFactUpdated = null;

        preExisitngVisits = false;
        boolean runSecondSubTest = true;
        boolean runThirdSubTest = false;
        int mock1 =0, mock2 = 1;
        if(runSecondSubTest)
            mock2++;
        if(runThirdSubTest)
            mock2++;
        MockEndpoint mockOut1 = getMockEndpoint("mock:prepforstaff");
        MockEndpoint mockOut2 = getMockEndpoint("mock:CEP_ENGINE_VISITS");

        Date date = simpleDateFormat.parse(testTime);
        VisitEventFact visitEventFact = getVisitEventFact(dnis, ani, staffId, date, "0015" );
        //simulate this from call server
        template.sendBodyAndHeaders(activemq_AGGREGATE_VISIT_EVENTS,visitEventFact, headers);
        Date scheduleDate = simpleDateFormat.parse(startTime);
        visitToUpdate = -1;
        ArrayList arrayList = getVisitFactArray(dnis,staffId,ani,scheduleDate, date, 9);
        template.sendBodyAndHeaders(activemq_AGGREGATE_VISIT_EVENTS,arrayList, headers);
        mockOut1.setExpectedMessageCount(mock1);
        mockOut2.setExpectedMessageCount(mock2);
        ServiceStatus status = mockOut2.getStatus();
        logger.info("Status of mock2 = " + status.name());

        if(mock2 > 0) {
            VisitFact visitFact = mockOut2.getExchanges().get(0).getIn().getBody(VisitFact.class);
            String msg = "\r\n**************  VISIT EVENT INSERTED INTO VISIT *****************\r\n";
            msg = getVisitDescription(visitFact, msg);
            logger.info(msg);
            visitFactUpdated = visitFact;


        }
        else {
            ArrayList list = mockOut1.getExchanges().get(0).getIn().getBody(ArrayList.class);
            for (int i = 0; i < list.size(); i++) {
                logger.info("visit for staff : " + ((VisitFact) list.get(0)).getStaffId() + "returned.");

            }
        }
        if(runSecondSubTest) {
            String testTime2 = "2015-12-14 10:45:00 EDT";//added 10 minutes still closer to from
            // now the out isnt null though
            visitEventFact = updateVisitEventFact(visitEventFact, simpleDateFormat.parse(testTime2), "0017");
            template.sendBodyAndHeaders(activemq_AGGREGATE_VISIT_EVENTS,visitEventFact, headers);

            arrayList.remove(visitToUpdate);

            arrayList.add(visitToUpdate, visitFactUpdated);
            template.sendBodyAndHeaders(activemq_AGGREGATE_VISIT_EVENTS, arrayList, headers);
            if(mock2 > 0) {
                VisitFact visitFact = mockOut2.getExchanges().get(1).getIn().getBody(VisitFact.class);
                String msg = "\r\n**************  VISIT EVENT INSERTED INTO VISIT *****************\r\n";
                msg = getVisitDescription(visitFact, msg);
                logger.info(msg);
                visitFactUpdated = visitFact;
            }

        }
        assertMockEndpointsSatisfied();
    }

    @Test
    public void testVisitEventsFromResponse() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");

        String startTime = "2015-12-14 08:00:00 EDT";
        String testTime = "2015-12-14 10:15:00 EDT";
        boolean runSecondSubTest = true;
        boolean runThirdSubTest = true;
        boolean preExisitngVisits = true;
        VisitFact visitFactUpdated = null;
        int mock1 =0, mock2 = 1;
        if(runSecondSubTest)
            mock2++;
        if(runThirdSubTest)
            mock2++;
        //for positive case make sure mock1 == 0 and mock2 == 1
        //and startTime is within 8 hours of testTime.
        //switch for negative case and make sure startTIme is greater than 8 hours
        //from now


        MockEndpoint mockOut1 = getMockEndpoint("mock:prepforstaff");
        MockEndpoint mockOut2 = getMockEndpoint("mock:CEP_ENGINE_VISITS");

        Date date = simpleDateFormat.parse(testTime);
        VisitEventFact visitEventFact = getVisitEventFact(dnis, ani, staffId, date, "0015" );
        //simulate this from call server
        template.sendBodyAndHeaders(activemq_AGGREGATE_VISIT_EVENTS,visitEventFact, headers);
        Date scheduleDate = simpleDateFormat.parse(startTime);
        visitToUpdate = -1;
        ArrayList arrayList = getVisitFactArray(dnis, staffId, ani,scheduleDate, date, 9);
        template.sendBodyAndHeaders(activemq_AGGREGATE_VISIT_EVENTS,arrayList, headers);
        mockOut1.setExpectedMessageCount(mock1);
        mockOut2.setExpectedMessageCount(mock2);
        ServiceStatus status = mockOut2.getStatus();
        logger.info("Status of mock2 = " + status.name());

        if(mock2 > 0) {
            VisitFact visitFact = mockOut2.getExchanges().get(0).getIn().getBody(VisitFact.class);
            String msg = "\r\n**************  VISIT EVENT INSERTED INTO VISIT *****************\r\n";
            msg = getVisitDescription(visitFact, msg);
            logger.info(msg);
            visitFactUpdated = visitFact;

        }
        else {
            ArrayList list = mockOut1.getExchanges().get(0).getIn().getBody(ArrayList.class);
            for (int i = 0; i < list.size(); i++) {
                logger.info("visit for staff : " + ((VisitFact) list.get(0)).getStaffId() + "returned.");

            }
        }

        if(runSecondSubTest) {
            String testTime2 = "2015-12-14 10:25:00 EDT";//added 10 minutes still closer to from
            // now the out isnt null though
            visitEventFact = updateVisitEventFact(visitEventFact, simpleDateFormat.parse(testTime2), "0017");
            template.sendBodyAndHeaders(activemq_AGGREGATE_VISIT_EVENTS,visitEventFact, headers);
            arrayList.remove(visitToUpdate);
            arrayList.add(visitToUpdate, visitFactUpdated);
            template.sendBodyAndHeaders(activemq_AGGREGATE_VISIT_EVENTS,arrayList, headers);
            if(mock2 > 0) {
                VisitFact visitFact = mockOut2.getExchanges().get(1).getIn().getBody(VisitFact.class);
                String msg = "\r\n**************  VISIT EVENT INSERTED INTO VISIT *****************\r\n";
                msg = getVisitDescription(visitFact, msg);
                logger.info(msg);
                visitFactUpdated = visitFact;
            }

        }
        if(runThirdSubTest) {
            String testTime2 = "2015-12-14 10:45:00 EDT";//added 10 minutes still closer to to
            // now the out isnt null though
            visitEventFact = updateVisitEventFact(visitEventFact, simpleDateFormat.parse(testTime2), "0021");
            template.sendBodyAndHeaders(activemq_AGGREGATE_VISIT_EVENTS,visitEventFact, headers);
            arrayList.remove(visitToUpdate);
            arrayList.add(visitToUpdate, visitFactUpdated);
            template.sendBodyAndHeaders(activemq_AGGREGATE_VISIT_EVENTS,arrayList, headers);
            if(mock2 > 0) {
                VisitFact visitFact = mockOut2.getExchanges().get(2).getIn().getBody(VisitFact.class);
                String msg = "\r\n**************  VISIT EVENT INSERTED INTO VISIT *****************\r\n";
                msg = getVisitDescription(visitFact, msg);
                logger.info(msg);
                visitFactUpdated = visitFact;
            }
        }
        assertMockEndpointsSatisfied();

    }

    @Test
    public void testAggregation_Match_9_of_9( ) throws Exception {
*/
        /*KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("ksessioncallMatching1");
        */
        /*
        context.getRouteDefinitions().get(0).adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {

                weaveByToString(".*ksession*.").after()
                        .process(new ProcessBatchExecuteResult());
            }
        });*/
/*        MockEndpoint mockOut = getMockEndpoint("mock:CEP_ENGINE_MESSAGES");
        BatchExecutionCommandImpl command = new BatchExecutionCommandImpl();
        List<GenericCommand<?>> commandList = command.getCommands();//new ArrayList<>();

        StaffFact staff = new StaffFact("123123123", "1231231234", State.LOADED);
        OraStaffFact dbStaff = new OraStaffFact("123123123", "1231231234");
        OraStaffFact dbStaff2 = new OraStaffFact("993123129", "1231231234");
        OraStaffFact dbStaff3 = new OraStaffFact("423123126", "1231231234");
        ArrayList list = new ArrayList();
        //list.add(staff);

        list .add(dbStaff2);
        list .add(dbStaff);
        list .add(dbStaff3);
*/
     /*
        commandList.add(new AgendaGroupSetFocusCommand("staff-matching"));
        commandList.add(new InsertObjectCommand(staff));
        commandList.add(new InsertObjectCommand(dbStaff));
        commandList.add(new InsertObjectCommand(dbStaff2));
        commandList.add(new InsertObjectCommand(dbStaff3));
        commandList.add(new FireAllRulesCommand());
        */
        /*kieSession.execute(command);
        long i = 0;
        while( (i = kieSession.getFactCount()) > 0)
        {
            System.out.println("Fact count > 0, Fact count = %s" + Long.toString(i));
        }
        kieSession.dispose();
        */
        //template.sendBody("direct:AGGREGATE_STAFF",command);
/*        String staffId  = "123123123";
        String dnis = "1231231234";
        Map<String, Object> headers = new HashMap<>();
        headers.put(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT, dnis+staffId);
        headers.put("staffId", staffId);
        headers.put("dnis", dnis);
        template.sendBodyAndHeaders("direct:AGGREGATE_STAFF",staff, headers);
        template.sendBodyAndHeaders("direct:AGGREGATE_STAFF",list, headers);
        mockOut.setExpectedMessageCount(12);
        mockOut.message(8).body().isEqualTo("StaffFact with STATE == STAFF_MATCHED_9_OF_9");
        mockOut.message(10).body().isEqualTo("OraStaffFact with STATE == STAFF_MATCHED_9_OF_9");

        List<Exchange> exchangeList = mockOut.getExchanges();

        for (Exchange e : exchangeList) {
            org.slf4j.LoggerFactory.getLogger(StaffAndVisitAggregationTest.class).info(e.getIn().getBody(String.class));
        }

        assertMockEndpointsSatisfied();



    }

    @Test
    public void testAggregation_Match_7_of_9( ) throws Exception {

        MockEndpoint mockOut = getMockEndpoint("mock:CEP_ENGINE_MESSAGES");
        BatchExecutionCommandImpl command = new BatchExecutionCommandImpl();
        List<GenericCommand<?>> commandList = command.getCommands();//new ArrayList<>();

        StaffFact staff = new StaffFact("123123123", "1231231234", State.LOADED);
        OraStaffFact dbStaff = new OraStaffFact("999123999", "1231231234");
        OraStaffFact dbStaff2 = new OraStaffFact("993123129", "1231231234");
        OraStaffFact dbStaff3 = new OraStaffFact("423123126", "1231231234");
        ArrayList list = new ArrayList();
        //list.add(staff);

        list .add(dbStaff2);
        list .add(dbStaff);
        list .add(dbStaff3);
        String staffId  = "123123123";
        String dnis = "1231231234";
        Map<String, Object> headers = new HashMap<>();
        headers.put(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT, dnis+staffId);
        headers.put("staffId", staffId);
        headers.put("dnis", dnis);
        template.sendBodyAndHeaders("direct:AGGREGATE_STAFF",staff, headers);
        template.sendBodyAndHeaders("direct:AGGREGATE_STAFF",list, headers);
        mockOut.setExpectedMessageCount(18);
        mockOut.message(17).body().isEqualTo(" objectUpdated  was a OraStaffFact by rule cep-engine-staff-7-of-9 with State = STAFF_MATCHED_7_OF_9");

        List<Exchange> exchangeList = mockOut.getExchanges();

        for (Exchange e : exchangeList) {
            org.slf4j.LoggerFactory.getLogger(StaffAndVisitAggregationTest.class).info(e.getIn().getBody(String.class));
        }

        assertMockEndpointsSatisfied();



    }
    @Test
    public void testAggregation_Match_6_of_9( ) throws Exception {

        MockEndpoint mockOut = getMockEndpoint("mock:CEP_ENGINE_MESSAGES");
        BatchExecutionCommandImpl command = new BatchExecutionCommandImpl();
        List<GenericCommand<?>> commandList = command.getCommands();//new ArrayList<>();

        StaffFact staff = new StaffFact("123123123", "1231231234", State.LOADED);
        OraStaffFact dbStaff = new OraStaffFact("999123999", "1231231234");
        dbStaff.setState(State.AGG_INSERTED_FROM_RESPONSE);
        OraStaffFact dbStaff2 = new OraStaffFact("993123129", "1231231234");
        OraStaffFact dbStaff3 = new OraStaffFact("499123126", "1231231234");
        ArrayList list = new ArrayList();

        list .add(dbStaff2);
        list .add(dbStaff);
        list .add(dbStaff3);
        String staffId  = "123123123";
        String dnis = "1231231234";
        Map<String, Object> headers = new HashMap<>();
        headers.put(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT, dnis+staffId);
        headers.put("staffId", staffId);
        headers.put("dnis", dnis);
        template.sendBodyAndHeaders("direct:AGGREGATE_STAFF",staff, headers);
        template.sendBodyAndHeaders("direct:AGGREGATE_STAFF",list, headers);
        mockOut.setExpectedMessageCount(24);

        mockOut.message(23).body().isEqualTo(" objectUpdated  was a OraStaffFact by rule cep-engine-staff-6-of-9 with State = STAFF_MATCHED_6_OF_9");

        List<Exchange> exchangeList = mockOut.getExchanges();

        for (Exchange e : exchangeList) {
            org.slf4j.LoggerFactory.getLogger(StaffAndVisitAggregationTest.class).info(e.getIn().getBody(String.class));
        }

        assertMockEndpointsSatisfied();


    }
*/

    @Override
    protected String getBlueprintDescriptor() {
        String tmp = super.getBlueprintDescriptor();
        //Must have predefined tmp which is String object returned from super
        //This is high overhead and should only be used for testing and debug!
        //Tom Dornseif
        Object o = new Object(){};
        String className = o.getClass().getEnclosingClass().getName();
        String methodName = o.getClass().getEnclosingMethod().getName();
        String typeName = o.getClass().getEnclosingMethod().getReturnType().getName();
        org.slf4j.LoggerFactory.getLogger(o.getClass().getEnclosingClass()).info(String.format(
                "%s : method = %s : super returned type : %s with value of %s ", className, methodName, typeName, tmp));
        return "/com/sandata/lab/rules/call/matching/test/blueprint/staff/staff-aggregate.xml,/OSGI-INF/blueprint/kie.xml";
    }

    private boolean debugBeforeMethodCalled;
    private boolean debugAfterMethodCalled;

    @Override
    protected void debugBefore(Exchange exchange, Processor processor, ProcessorDefinition<?> definition, String id, String label) {
        //super.debugBefore(exchange, processor, definition, id, label);
        log.info("Before " + definition + " with body " + exchange.getIn().getBody());
        debugBeforeMethodCalled = true;
    }

    @Override
    protected void debugAfter(Exchange exchange, Processor processor, ProcessorDefinition<?> definition, String id, String label, long timeTaken) {
        //super.debugAfter(exchange, processor, definition, id, label, timeTaken);
        log.info("After " + definition + " with body " + exchange.getIn().getBody());
        debugAfterMethodCalled = true;
    }
    @Override
    public boolean isCreateCamelContextPerClass() {
        boolean value = super.isCreateCamelContextPerClass();
        //Must have predefined tmp which is String object returned from super
        //This is high overhead and should only be used for testing and debug!
        //Tom Dornseif
        String tmp = Boolean.toString(value);
        Object o = new Object() {
        };
        String className = o.getClass().getEnclosingClass().getName();
        String methodName = o.getClass().getEnclosingMethod().getName();
        String typeName = o.getClass().getEnclosingMethod().getReturnType().getName();
        org.slf4j.LoggerFactory.getLogger(o.getClass().getEnclosingClass()).info(String.format(
                "%s : method = %s : super returned type : %s with value of %s ", className, methodName, typeName, tmp));
        return value;
    }

    public StaffAndVisitAggregationTest() {
        super();
    }



    @Override
    protected void addServicesOnStartup(Map<String, KeyValueHolder<Object, Dictionary>> services) {
        //Must have predefined tmp which is String object returned from super
        //This is high overhead and should only be used for testing and debug!
        //Tom Dornseif

        Object o = new Object() {
        };
        String className = o.getClass().getEnclosingClass().getName();
        String methodName = o.getClass().getEnclosingMethod().getName();
        String typeName = o.getClass().getEnclosingMethod().getReturnType().getName();
        String tmp = null;
        for (Map.Entry entry : services.entrySet()) {
            tmp = (String) entry.getKey();
            org.slf4j.LoggerFactory.getLogger(o.getClass().getEnclosingClass()).info(String.format(
                    "%s : method = %s : super returned type : %s with value of %s ", className, methodName, typeName, tmp));
        }
        super.addServicesOnStartup(services);
    }

    @Override
    protected String useOverridePropertiesWithConfigAdmin(Dictionary props) throws Exception {
        //props.put("activeMQ.endpoint","seda:");

        String tmp = super.useOverridePropertiesWithConfigAdmin(props);
        //Must have predefined tmp which is String object returned from super
        //This is high overhead and should only be used for testing and debug!
        //Tom Dornseif
        Object o = new Object() {
        };
        String className = o.getClass().getEnclosingClass().getName();
        String methodName = o.getClass().getEnclosingMethod().getName();
        String typeName = o.getClass().getEnclosingMethod().getReturnType().getName();
        org.slf4j.LoggerFactory.getLogger(o.getClass().getEnclosingClass()).info(String.format(
                "%s : method = %s : super returned type : %s with value of %s ", className, methodName, typeName, tmp));

        return tmp;
    }

    @Override
    protected String[] loadConfigAdminConfigurationFile() {
        String[] tmps = super.loadConfigAdminConfigurationFile();
        //Must have predefined tmp which is String object returned from super
        //This is high overhead and should only be used for testing and debug!
        //Tom Dornseif
        Object o = new Object() {
        };
        String className = o.getClass().getEnclosingClass().getName();
        String methodName = o.getClass().getEnclosingMethod().getName();
        String typeName = o.getClass().getEnclosingMethod().getReturnType().getName();
        if(tmps != null) {
            for (String tmp : tmps) {
                org.slf4j.LoggerFactory.getLogger(o.getClass().getEnclosingClass()).info(String.format(
                        "%s : method = %s : super returned type : %s with value of %s ", className, methodName, typeName, tmp));
            }
        }

        return tmps;
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Override
    protected BundleContext getBundleContext() {
        BundleContext context = super.getBundleContext();
        String tmp = context.toString();
        //Must have predefined tmp which is String object returned from super
        //This is high overhead and should only be used for testing and debug!
        //Tom Dornseif
        Object o = new Object() {
        };
        String className = o.getClass().getEnclosingClass().getName();
        String methodName = o.getClass().getEnclosingMethod().getName();
        String typeName = o.getClass().getEnclosingMethod().getReturnType().getName();
        org.slf4j.LoggerFactory.getLogger(o.getClass().getEnclosingClass()).info(String.format(
                "%s : method = %s : super returned type : %s with value of %s ", className, methodName, typeName, tmp));
        return context;
    }

    @Override
    protected String getBundleFilter() {
        String tmp = super.getBundleFilter();

        //Must have predefined tmp which is String object returned from super
        //This is high overhead and should only be used for testing and debug!
        //Tom Dornseif
        Object o = new Object() {
        };
        String className = o.getClass().getEnclosingClass().getName();
        String methodName = o.getClass().getEnclosingMethod().getName();
        String typeName = o.getClass().getEnclosingMethod().getReturnType().getName();
        org.slf4j.LoggerFactory.getLogger(o.getClass().getEnclosingClass()).info(String.format(
                "%s : method = %s : super returned type : %s with value of %s ", className, methodName, typeName, tmp));

        tmp = "(!(Bundle-SymbolicName=org.apache.cxf))";
        //tmp = "(!(Bundle-SymbolicName=org.osgi.service.cm))";
        return tmp;

    }

    @Override
    protected String getBundleVersion() {
        String tmp = super.getBundleVersion();
        //Must have predefined tmp which is String object returned from super
        //This is high overhead and should only be used for testing and debug!
        //Tom Dornseif
        Object o = new Object() {
        };
        String className = o.getClass().getEnclosingClass().getName();
        String methodName = o.getClass().getEnclosingMethod().getName();
        String typeName = o.getClass().getEnclosingMethod().getReturnType().getName();
        org.slf4j.LoggerFactory.getLogger(o.getClass().getEnclosingClass()).info(String.format(
                "%s : method = %s : super returned type : %s with value of %s ", className, methodName, typeName, tmp));
        return tmp;
    }

    @Override
    protected String getBundleDirectives() {
        String tmp = super.getBundleDirectives();
        //Must have predefined tmp which is String object returned from super
        //This is high overhead and should only be used for testing and debug!
        //Tom Dornseif
        Object o = new Object() {
        };
        String className = o.getClass().getEnclosingClass().getName();
        String methodName = o.getClass().getEnclosingMethod().getName();
        String typeName = o.getClass().getEnclosingMethod().getReturnType().getName();
        org.slf4j.LoggerFactory.getLogger(o.getClass().getEnclosingClass()).info(String.format(
                "%s : method = %s : super returned type : %s with value of %s ", className, methodName, typeName, tmp));
        return tmp;
    }

    @Override
    protected Long getCamelContextCreationTimeout() {
        Long timeout = super.getCamelContextCreationTimeout();
        if(timeout != null) {
            String tmp = timeout.toString();
            //Must have predefined tmp which is String object returned from super
            //This is high overhead and should only be used for testing and debug!
            //Tom Dornseif
            Object o = new Object() {
            };
            String className = o.getClass().getEnclosingClass().getName();
            String methodName = o.getClass().getEnclosingMethod().getName();
            String typeName = o.getClass().getEnclosingMethod().getReturnType().getName();
            org.slf4j.LoggerFactory.getLogger(o.getClass().getEnclosingClass()).info(String.format(
                    "%s : method = %s : super returned type : %s with value of %s ", className, methodName, typeName, tmp));
        }
        return timeout;
    }

    @Override
    protected <T> T getOsgiService(Class<T> type) {
        return super.getOsgiService(type);
    }

    @Override
    protected <T> T getOsgiService(Class<T> type, long timeout) {
        return super.getOsgiService(type, timeout);
    }

    @Override
    protected <T> T getOsgiService(Class<T> type, String filter) {
        return super.getOsgiService(type, filter);
    }

    @Override
    protected <T> T getOsgiService(Class<T> type, String filter, long timeout) {
        return super.getOsgiService(type, filter, timeout);
    }

    @Override
    public boolean isUseRouteBuilder() {
        //Must have predefined tmp which is String object returned from super
        //This is high overhead and should only be used for testing and debug!
        //Tom Dornseif
        boolean value = super.isUseRouteBuilder();
        String tmp;
        Object o = new Object() {
        };
        String className = o.getClass().getEnclosingClass().getName();
        String methodName = o.getClass().getEnclosingMethod().getName();
        String typeName = o.getClass().getEnclosingMethod().getReturnType().getName();
        tmp = Boolean.toString(value);
        //tmp = value.toString();
        //tmp = value;
        org.slf4j.LoggerFactory.getLogger(o.getClass().getEnclosingClass()).info(String.format(
                "%s : method = %s : super returned type : %s with value of %s ", className, methodName, typeName, tmp));
        return super.isUseRouteBuilder();
    }

    @Override
    public void setUseRouteBuilder(boolean useRouteBuilder) {
        super.setUseRouteBuilder(useRouteBuilder);
    }


    @Override
    public String isMockEndpoints() {
        return super.isMockEndpoints();
    }

    @Override
    public void replaceRouteFromWith(String routeId, String fromEndpoint) {
        super.replaceRouteFromWith(routeId, fromEndpoint);
    }

    @Override
    public boolean isUseDebugger() {
        return true;//super.isUseDebugger();
    }

    @Override
    public Service getCamelContextService() {
        return super.getCamelContextService();
    }

    @Override
    public Service camelContextService() {
        return super.camelContextService();
    }

    @Override
    public ProducerTemplate template() {
        return super.template();
    }

    @Override
    public ConsumerTemplate consumer() {
        return super.consumer();
    }

    @Override
    public void setCamelContextService(Service service) {
        if(service != null) {
            String tmp = service.getClass().getName();
            //Must have predefined tmp which is String object returned from super
            //This is high overhead and should only be used for testing and debug!
            //Tom Dornseif
            Object o = new Object() {
            };
            String className = o.getClass().getEnclosingClass().getName();
            String methodName = o.getClass().getEnclosingMethod().getName();
            String typeName = o.getClass().getEnclosingMethod().getReturnType().getName();
            org.slf4j.LoggerFactory.getLogger(o.getClass().getEnclosingClass()).info(String.format(
                    "%s : method = %s : super returned type : %s with value of %s ", className, methodName, typeName, tmp));
        }
        super.setCamelContextService(service);
    }

    @Override
    protected void doPreSetup() throws Exception {
        org.slf4j.LoggerFactory.getLogger(StaffAndVisitAggregationTest.class).info("StaffAggregationTest : doPreSetup()");
        super.doPreSetup();
    }

    @Override
    protected int getShutdownTimeout() {
        return super.getShutdownTimeout();
    }

    @Override
    protected boolean useJmx() {
        boolean use =super.useJmx();
        String tmp = Boolean.toString(use);
        //Must have predefined tmp which is String object returned from super
        //This is high overhead and should only be used for testing and debug!
        //Tom Dornseif
        Object o = new Object() {
        };
        String className = o.getClass().getEnclosingClass().getName();
        String methodName = o.getClass().getEnclosingMethod().getName();
        String typeName = o.getClass().getEnclosingMethod().getReturnType().getName();
        org.slf4j.LoggerFactory.getLogger(o.getClass().getEnclosingClass()).info(String.format(
                "%s : method = %s : super returned type : %s with value of %s ", className, methodName, typeName, tmp));
        return use;
    }

    /**
     * @deprecated
     */
    @Override
    protected boolean isLazyLoadingTypeConverter() {
        boolean is = super.isLazyLoadingTypeConverter();
        String tmp = Boolean.toString(is);
        //Must have predefined tmp which is String object returned from super
        //This is high overhead and should only be used for testing and debug!
        //Tom Dornseif
        Object o = new Object() {
        };
        String className = o.getClass().getEnclosingClass().getName();
        String methodName = o.getClass().getEnclosingMethod().getName();
        String typeName = o.getClass().getEnclosingMethod().getReturnType().getName();
        org.slf4j.LoggerFactory.getLogger(o.getClass().getEnclosingClass()).info(String.format(
                "%s : method = %s : super returned type : %s with value of %s ", className, methodName, typeName, tmp));

        return is;
    }



    @Override
    protected Boolean ignoreMissingLocationWithPropertiesComponent() {
        return super.ignoreMissingLocationWithPropertiesComponent();
    }

    @Override
    protected void postProcessTest() throws Exception {
        super.postProcessTest();
    }

    @Override
    protected void applyCamelPostProcessor() throws Exception {
        super.applyCamelPostProcessor();
    }

    @Override
    protected void stopCamelContext() throws Exception {
        super.stopCamelContext();
    }

    @Override
    protected void startCamelContext() throws Exception {
        super.startCamelContext();


    }

    @Override
    protected JndiRegistry createRegistry() throws Exception {
        return super.createRegistry();
    }

    @Override
    protected Context createJndiContext() throws Exception {
        return super.createJndiContext();
    }

    @Override
    protected RouteBuilder[] createRouteBuilders() throws Exception {
        RouteBuilder[] routeBuilders = null;
        try {
            routeBuilders = super.createRouteBuilders();
        }
        catch(Exception ex) {

            org.slf4j.LoggerFactory.getLogger(StaffAndVisitAggregationTest.class).info(ex.getLocalizedMessage());
        }
        return routeBuilders;
    }

    @Override
    protected Endpoint resolveMandatoryEndpoint(String uri) {
        return super.resolveMandatoryEndpoint(uri);
    }

    @Override
    protected <T extends Endpoint> T resolveMandatoryEndpoint(String uri, Class<T> endpointType) {
        return super.resolveMandatoryEndpoint(uri, endpointType);
    }

    @Override
    protected MockEndpoint getMockEndpoint(String uri) {
        return super.getMockEndpoint(uri);
    }

    @Override
    protected MockEndpoint getMockEndpoint(String uri, boolean create) throws NoSuchEndpointException {
        return super.getMockEndpoint(uri, create);
    }

    @Override
    protected void sendBody(String endpointUri, Object body) {
        super.sendBody(endpointUri, body);
    }

    @Override
    protected void sendBody(String endpointUri, Object body, Map<String, Object> headers) {
        super.sendBody(endpointUri, body, headers);
    }

    @Override
    protected void sendBodies(String endpointUri, Object... bodies) {
        super.sendBodies(endpointUri, bodies);
    }

    @Override
    protected Exchange createExchangeWithBody(Object body) {
        return super.createExchangeWithBody(body);
    }

    @Override
    protected void assertExpression(Exchange exchange, String languageName, String expressionText, Object expectedValue) {
        super.assertExpression(exchange, languageName, expressionText, expectedValue);
    }

    @Override
    protected void assertPredicate(String languageName, String expressionText, Exchange exchange, boolean expected) {
        super.assertPredicate(languageName, expressionText, exchange, expected);
    }

    @Override
    protected Language assertResolveLanguage(String languageName) {
        return super.assertResolveLanguage(languageName);
    }

    @Override
    protected void assertMockEndpointsSatisfied() throws InterruptedException {
        super.assertMockEndpointsSatisfied();
    }

    @Override
    protected void assertMockEndpointsSatisfied(long timeout, TimeUnit unit) throws InterruptedException {
        super.assertMockEndpointsSatisfied(timeout, unit);
    }

    @Override
    protected void resetMocks() {
        super.resetMocks();
    }

    @Override
    protected void assertValidContext(CamelContext context) {
        super.assertValidContext(context);
    }

    @Override
    protected <T extends Endpoint> T getMandatoryEndpoint(String uri, Class<T> type) {
        return super.getMandatoryEndpoint(uri, type);
    }

    @Override
    protected Endpoint getMandatoryEndpoint(String uri) {
        return super.getMandatoryEndpoint(uri);
    }

    @Override
    protected void disableJMX() {
        super.disableJMX();
    }

    @Override
    protected void enableJMX() {
        super.enableJMX();
    }



    @Override
    protected Exchange createExchangeWithBody(CamelContext camelContext, Object body) {
        return super.createExchangeWithBody(camelContext, body);
    }

    @Override
    public String getTestMethodName() {
        String tmp = super.getTestMethodName();
        //Must have predefined tmp which is String object returned from super
        //This is high overhead and should only be used for testing and debug!
        //Tom Dornseif
        Object o = new Object() {
        };
        String className = o.getClass().getEnclosingClass().getName();
        String methodName = o.getClass().getEnclosingMethod().getName();
        String typeName = o.getClass().getEnclosingMethod().getReturnType().getName();
        org.slf4j.LoggerFactory.getLogger(o.getClass().getEnclosingClass()).info(String.format(
                "%s : method = %s : super returned type : %s with value of %s ", className, methodName, typeName, tmp));
        return tmp;
    }

    /**
     * Returns a hash code value for the object. This method is
     * supported for the benefit of hash tables such as those provided by
     * {@link HashMap}.
     * <p/>
     * The general contract of {@code hashCode} is:
     * <ul>
     * <li>Whenever it is invoked on the same object more than once during
     * an execution of a Java application, the {@code hashCode} method
     * must consistently return the same integer, provided no information
     * used in {@code equals} comparisons on the object is modified.
     * This integer need not remain consistent from one execution of an
     * application to another execution of the same application.
     * <li>If two objects are equal according to the {@code equals(Object)}
     * method, then calling the {@code hashCode} method on each of
     * the two objects must produce the same integer result.
     * <li>It is <em>not</em> required that if two objects are unequal
     * according to the {@link Object#equals(Object)}
     * method, then calling the {@code hashCode} method on each of the
     * two objects must produce distinct integer results.  However, the
     * programmer should be aware that producing distinct integer results
     * for unequal objects may improve the performance of hash tables.
     * </ul>
     * <p/>
     * As much as is reasonably practical, the hashCode method defined by
     * class {@code Object} does return distinct integers for distinct
     * objects. (This is typically implemented by converting the internal
     * address of the object into an integer, but this implementation
     * technique is not required by the
     * Java<font size="-2"><sup>TM</sup></font> programming language.)
     *
     * @return a hash code value for this object.
     * @see Object#equals(Object)
     * @see System#identityHashCode
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p/>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     * <ul>
     * <li>It is <i>reflexive</i>: for any non-null reference value
     * {@code x}, {@code x.equals(x)} should return
     * {@code true}.
     * <li>It is <i>symmetric</i>: for any non-null reference values
     * {@code x} and {@code y}, {@code x.equals(y)}
     * should return {@code true} if and only if
     * {@code y.equals(x)} returns {@code true}.
     * <li>It is <i>transitive</i>: for any non-null reference values
     * {@code x}, {@code y}, and {@code z}, if
     * {@code x.equals(y)} returns {@code true} and
     * {@code y.equals(z)} returns {@code true}, then
     * {@code x.equals(z)} should return {@code true}.
     * <li>It is <i>consistent</i>: for any non-null reference values
     * {@code x} and {@code y}, multiple invocations of
     * {@code x.equals(y)} consistently return {@code true}
     * or consistently return {@code false}, provided no
     * information used in {@code equals} comparisons on the
     * objects is modified.
     * <li>For any non-null reference value {@code x},
     * {@code x.equals(null)} should return {@code false}.
     * </ul>
     * <p/>
     * The {@code equals} method for class {@code Object} implements
     * the most discriminating possible equivalence relation on objects;
     * that is, for any non-null reference values {@code x} and
     * {@code y}, this method returns {@code true} if and only
     * if {@code x} and {@code y} refer to the same object
     * ({@code x == y} has the value {@code true}).
     * <p/>
     * Note that it is generally necessary to override the {@code hashCode}
     * method whenever this method is overridden, so as to maintain the
     * general contract for the {@code hashCode} method, which states
     * that equal objects must have equal hash codes.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     * @see #hashCode()
     * @see HashMap
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Creates and returns a copy of this object.  The precise meaning
     * of "copy" may depend on the class of the object. The general
     * intent is that, for any object {@code x}, the expression:
     * <blockquote>
     * <pre>
     * x.clone() != x</pre></blockquote>
     * will be true, and that the expression:
     * <blockquote>
     * <pre>
     * x.clone().getClass() == x.getClass()</pre></blockquote>
     * will be {@code true}, but these are not absolute requirements.
     * While it is typically the case that:
     * <blockquote>
     * <pre>
     * x.clone().equals(x)</pre></blockquote>
     * will be {@code true}, this is not an absolute requirement.
     *
     * By convention, the returned object should be obtained by calling
     * {@code super.clone}.  If a class and all of its superclasses (except
     * {@code Object}) obey this convention, it will be the case that
     * {@code x.clone().getClass() == x.getClass()}.
     *
     * By convention, the object returned by this method should be independent
     * of this object (which is being cloned).  To achieve this independence,
     * it may be necessary to modify one or more fields of the object returned
     * by {@code super.clone} before returning it.  Typically, this means
     * copying any mutable objects that comprise the internal "deep structure"
     * of the object being cloned and replacing the references to these
     * objects with references to the copies.  If a class contains only
     * primitive fields or references to immutable objects, then it is usually
     * the case that no fields in the object returned by {@code super.clone}
     * need to be modified.
     *
     * The method {@code clone} for class {@code Object} performs a
     * specific cloning operation. First, if the class of this object does
     * not implement the interface {@code Cloneable}, then a
     * {@code CloneNotSupportedException} is thrown. Note that all arrays
     * are considered to implement the interface {@code Cloneable} and that
     * the return type of the {@code clone} method of an array type {@code T[]}
     * is {@code T[]} where T is any reference or primitive type.
     * Otherwise, this method creates a new instance of the class of this
     * object and initializes all its fields with exactly the contents of
     * the corresponding fields of this object, as if by assignment; the
     * contents of the fields are not themselves cloned. Thus, this method
     * performs a "shallow copy" of this object, not a "deep copy" operation.
     *
     * The class {@code Object} does not itself implement the interface
     * {@code Cloneable}, so calling the {@code clone} method on an object
     * whose class is {@code Object} will result in throwing an
     * exception at run time.
     *
     * @return a clone of this instance.
     * @throws CloneNotSupportedException if the object's class does not
     *                                    support the {@code Cloneable} interface. Subclasses
     *                                    that override the {@code clone} method can also
     *                                    throw this exception to indicate that an instance cannot
     *                                    be cloned.
     * @see Cloneable
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p/>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Called by the garbage collector on an object when garbage collection
     * determines that there are no more references to the object.
     * A subclass overrides the {@code finalize} method to dispose of
     * system resources or to perform other cleanup.
     * <p/>
     * The general contract of {@code finalize} is that it is invoked
     * if and when the Java<font size="-2"><sup>TM</sup></font> virtual
     * machine has determined that there is no longer any
     * means by which this object can be accessed by any thread that has
     * not yet died, except as a result of an action taken by the
     * finalization of some other object or class which is ready to be
     * finalized. The {@code finalize} method may take any action, including
     * making this object available again to other threads; the usual purpose
     * of {@code finalize}, however, is to perform cleanup actions before
     * the object is irrevocably discarded. For example, the finalize method
     * for an object that represents an input/output connection might perform
     * explicit I/O transactions to break the connection before the object is
     * permanently discarded.
     * <p/>
     * The {@code finalize} method of class {@code Object} performs no
     * special action; it simply returns normally. Subclasses of
     * {@code Object} may override this definition.
     * <p/>
     * The Java programming language does not guarantee which thread will
     * invoke the {@code finalize} method for any given object. It is
     * guaranteed, however, that the thread that invokes finalize will not
     * be holding any user-visible synchronization locks when finalize is
     * invoked. If an uncaught exception is thrown by the finalize method,
     * the exception is ignored and finalization of that object terminates.
     * <p/>
     * After the {@code finalize} method has been invoked for an object, no
     * further action is taken until the Java virtual machine has again
     * determined that there is no longer any means by which this object can
     * be accessed by any thread that has not yet died, including possible
     * actions by other objects or classes which are ready to be finalized,
     * at which point the object may be discarded.
     * <p/>
     * The {@code finalize} method is never invoked more than once by a Java
     * virtual machine for any given object.
     * <p/>
     * Any exception thrown by the {@code finalize} method causes
     * the finalization of this object to be halted, but is otherwise
     * ignored.
     *
     * @throws Throwable the {@code Exception} raised by this method
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public CamelContext context() {
        return super.context();
    }

    @Override
    public String isMockEndpointsAndSkip() {
        return "(activemq):(.*)";
    }

    @Override
    protected BundleContext createBundleContext() throws Exception {
        BundleContext context = super.createBundleContext();
        String tmp = context.toString();
        //Must have predefined tmp which is String object returned from super
        //This is high overhead and should only be used for testing and debug!
        //Tom Dornseif
        Object o = new Object() {
        };
        String className = o.getClass().getEnclosingClass().getName();
        String methodName = o.getClass().getEnclosingMethod().getName();
        String typeName = o.getClass().getEnclosingMethod().getReturnType().getName();
        org.slf4j.LoggerFactory.getLogger(o.getClass().getEnclosingClass()).info(String.format(
                "%s : method = %s : super returned type : %s with value of %s ", className, methodName, typeName, tmp));
        return context;
    }

    @Override
    protected CamelContext createCamelContext() throws Exception {
        return super.createCamelContext();
    }





}
