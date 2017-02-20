package com.sandata.lab.rules.call.matching.processors;


import com.google.gson.Gson;
import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.data.model.dl.model.extended.ScheduleEventExt;
import com.sandata.lab.data.model.request.visit.ScheduleEventRequest;
import com.sandata.lab.data.model.response.visit.ScheduleEventResponse;
import com.sandata.lab.rules.cache.client.CacheClient;
import com.sandata.lab.rules.call.matching.aggregate.PrepForAggregation;
import com.sandata.lab.rules.call.matching.routes.AggregateVisitRoute;
import com.sandata.lab.rules.call.model.CallPreferences;
import com.sandata.lab.rules.call.model.State;
import com.sandata.lab.rules.call.model.VisitFact;
import org.apache.camel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by tom.dornseif on 11/21/2015.
 */
public class ScheduleEventResponseProcessor implements Processor {
    private static final String CEP_ENG_SCHEDULE_RESPONSE_LOGGER = "scheduleResponseLog";
    private Logger scheduleResponseLog = LoggerFactory.getLogger(CEP_ENG_SCHEDULE_RESPONSE_LOGGER);

    @Produce
    private ProducerTemplate producer;
    @Override
    public void process(Exchange exchange) throws Exception {

        this.getScheduleResponseLog().info("In ScheduleResponseRoute::ScheduleResponseRoute::process");
        ProducerPojo producerPojoSub = new ProducerPojo();
        if(exchange != null) {
            Message in = exchange.getIn();
            this.getScheduleResponseLog().info("In ScheduleResponseRoute::ScheduleResponseRoute::process exchange not null");
            ArrayList<VisitFact> arrayList = new ArrayList<VisitFact>();
            if (in.getBody() instanceof ScheduleEventResponse) {
                ScheduleEventResponse scheduleEventResponse = in.getBody(ScheduleEventResponse.class);
                if (scheduleEventResponse.getErrorMessage() != null) {
                    this.getScheduleResponseLog().info(String.format("WARNING:: ErrorMessage : %s", scheduleEventResponse.getErrorMessage()));
                }
                if (scheduleEventResponse.getScheduleEventRequest() != null) {
                    ScheduleEventRequest scheduleEventRequest = scheduleEventResponse.getScheduleEventRequest();
                    SchdlRequest request = new SchdlRequest(scheduleEventRequest);
                    String infoString = String.format("ScheduleResponse  : ScheduleRequest, DNIS : %s, staffId : %s, from : %s, toDate : %s, requestedAni : %s, requestedPatientId : %s",
                            request.getDnis(), request.getStaffId(), request.getFromDateString(), request.getToDateString(), request.getRequestedAni(), request.getRequestedPatientId());
                    this.getScheduleResponseLog().info(infoString);
                    if (isListNullOrEmpty(scheduleEventResponse.getScheduleEventsExt())) {
                        if(scheduleEventResponse.getErrorMessage() == null ) {
                            this.getScheduleResponseLog().info("Status : No schedule events, possibly staff was entered wrong so send to staff_aggregation!");
                            State state = getCallPreferenceStateForUnmatchedStaff(request.getDnis());
                            VisitFact visitFact = createVisitFact(null, request, state, null);
                            String uuid = UUID.randomUUID().toString();
                            arrayList.add(visitFact);
                            this.getScheduleResponseLog().info("schedule created From = %s, To = %s", request.getFromDateString(), request.getToDateString());
                        }
                        else
                        {
                            this.getScheduleResponseLog().info("ERROR::Status : No schedule events and an error was returned so do not create visits likely invalid dnis or staff.  ");
                        }

                    } else {
                        if(!isStringNullOrEmtpy(request.getSantraxId())) {
                            in.setHeader(AggregateVisitRoute.SANTRAX_ID, request.getSantraxId());
                            in.setHeader(PrepForAggregation.DATABASE_PROVIDED_STAFF_ID, request.staffId);
                        }
                        List<ScheduleEventExt> scheduleEvents = scheduleEventResponse.getScheduleEventsExt();
                        for (com.sandata.lab.data.model.dl.model.extended.ScheduleEventExt scheduleExt : scheduleEvents) {
                            if (scheduleExt.getScheduleEventSK() != null ) {
                                if (scheduleExt.getVisit() == null || scheduleExt.getVisit().isEmpty()) {
                                    this.getScheduleResponseLog().info("ERROR::Empty visits are created as part of schedule creation! An empty visit list is not possible!  Check request");
                                }
                                else {
                                    this.getScheduleResponseLog().info("ScheduleResponse  : Had valid ScheduleExt with Visits list present > 0");
                                    int numVisits = 0;
                                    for (Visit v : scheduleExt.getVisit()) {
                                        VisitFact visitFact = createVisitFact(v, request, State.AGG_INSERTED_FROM_RESPONSE, scheduleExt);
                                        if ((visitFact.getCallIn() == null && visitFact.getCallOut() == null && scheduleExt.getScheduleEventSK() != null)) {
                                            //bad visit remove it
                                            infoString =  String.format(" Scheduled Visit with no callin and no callout and sk %s will be added added => dnis : %s, visitSk : %s Returned from UNION with scheduled Visits not yet started.",
                                                    scheduleExt.getScheduleEventSK().toString(), visitFact.getDnis(), v.getVisitSK() == null ? "(null)" : v.getVisitSK().toString());//we already checked for null scheduleEventSk
                                            this.getScheduleResponseLog().info(infoString);
                                        } else {
                                            infoString = String.format(" Schedule with sk %s was added => dnis : %s",
                                                    scheduleExt.getScheduleEventSK() == null ? "(null)" : scheduleExt.getScheduleEventSK().toString(), request.getDnis());
                                            this.getScheduleResponseLog().info("requested staffId : " + request.getStaffId() + "scheduleExt staffId : " + scheduleExt.getStaffID() +  "ani : " + request.getRequestedAni() +
                                                    " startDate : " + (visitFact.getScheduledFrom()  == null ? "(null)" : visitFact.getScheduledFrom().toString() ) + " endDate : " + (visitFact.getScheduledTo()  == null ? "(null)" : visitFact.getScheduledTo().toString()) +
                                                    " visit number " + Integer.toString(numVisits) + " in Visits List.");
                                            if (numVisits > 1) {
                                                this.getScheduleResponseLog().info(" <==== WARNING:: number of visits  WAS GREATER THAN 1 NOT LIKELY TO OCCUR");
                                            }
                                        }
                                        numVisits++;
                                        arrayList.add(visitFact);
                                    }
                                }
                            }
                            else {
                                this.getScheduleResponseLog().info("Status : No schedule events, but valid staff was found so send to unplanned visit!");
                                //setting new to and from dates
                                VisitFact visitFact = createVisitFact(null, request, State.NO_SCHEDULES, null);
                                arrayList.add(visitFact);
                                this.getScheduleResponseLog().info(String.format("schedule created From: %s To: %s", request.getFromDateString(), request.getToDateString()));
                                //send this to the aggregator so that the call event can be sent to the resubmission queue
                            }
                        }


                    }
                    exchange.getIn().setBody(arrayList, ArrayList.class);
                    String dnisandstaffidandpatient = request.getRequestedDnis() + request.getRequestedStaffId();
                    if(request.isClientEntered()) {
                        dnisandstaffidandpatient += request.getRequestedPatientId();
                    }
                    else {
                        dnisandstaffidandpatient += request.getRequestedAni();
                    }
                    exchange.getIn().setHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT, dnisandstaffidandpatient);
                    exchange.getIn().setHeader(PrepForAggregation.REQUEST_UUID_FOR_AGGREGATION, request.getRequestId());
                }

            }
            else {
                this.getScheduleResponseLog().info("ERROR::ScheduleResponseRoute::ScheduleResponseRoute::process::224 exchange was not instanceof ScheduleEventResponse");
            }

        }
        else {
            this.getScheduleResponseLog().info("ERROR::In ScheduleResponseRoute::ScheduleResponseRoute::process::229 exchange was null");
        }
    }

    private State getCallPreferenceStateForUnmatchedStaff(String dnis) {
        CacheClient client = null;
        State state;
        CallPreferences callPreferences = null;

        try {
            client = CacheClient.getClient();
        }
        catch(Exception exception) {
            this.getScheduleResponseLog().info("ERROR::Error convertin json string preventing catastrophic failure by using defaults for callPreferences.");
            callPreferences = new CallPreferences();
        }
        if(client != null) {
            Gson gson = new Gson();
            String json = client.getCallPreferencesForDnis(dnis);
            callPreferences = gson.fromJson(json, CallPreferences.class);
        }
        if(callPreferences == null) {
            //ERROR
            this.getScheduleResponseLog().info("ERROR::Error convertin json string preventing catastrophic failure by using defaults for callPreferences.");
            callPreferences = new CallPreferences();
        }
        //ovverride default for testing purposes.
        callPreferences.getStaffIdMatchLength();
        int minMatch = callPreferences.getStaffIdMatchLength();
        if(minMatch == 9) {
            state = State.INVALID_STAFF;
        }
        else {
            state = State.TRY_FUZZY_MATCH_FOR_STAFF_ID;
        }
        return state;
    }

    public Logger getScheduleResponseLog() {
        if(scheduleResponseLog != null) {
            return scheduleResponseLog;
        }
        else {
            this.scheduleResponseLog = LoggerFactory.getLogger("scheduleResponseLog");
            return this.scheduleResponseLog;
        }
    }

    public void setScheduleResponseLog(Logger scheduleResponseLog) {
        this.scheduleResponseLog = scheduleResponseLog;
    }

    private boolean isListNullOrEmpty(List l) {
        return (l == null || l.isEmpty());
    }

    private boolean isStringNullOrEmtpy(String s) { return (s == null || s.isEmpty() ); }

    private VisitFact createVisitFact(Visit visit, SchdlRequest request, State state, ScheduleEventExt scheduleExt) {
        VisitFact visitFact = null;
        if(visit == null) {
            visitFact = new VisitFact();
            visitFact.setScheduledFrom(request.getFromDate());
            visitFact.setScheduledTo(request.getToDate());
            visitFact.setDnis(request.getDnis());
            visitFact.setStaffId(request.getRequestedStaffId());
            visitFact.setVv_id(request.getSantraxId());
            visitFact.setAni(request.getRequestedAni());
            visitFact.setState(state);
            if (request.isClientEntered()) {
                visitFact.setPatientId(request.getRequestedPatientId());
                visitFact.setClientEntered(true);
            }
        }
        else {
            visitFact = new VisitFact(visit);
            visitFact.setState(state);
            visitFact.setDnis(request.getDnis());
            Date startDate = scheduleExt.getScheduleEventStartDatetime();
            Date endDate = scheduleExt.getScheduleEventEndDatetime();
            visitFact.setScheduledFrom(startDate);
            visitFact.setScheduledTo(endDate);
            //staffID and scheduleEventSK are both set in visitFact constructor. from visit object
            if ( !isStringNullOrEmtpy(scheduleExt.getVv_id()) ) {
                visitFact.setVv_id(scheduleExt.getVv_id());
            } else if ( !isStringNullOrEmtpy(request.getSantraxId()) ) {
                visitFact.setVv_id(request.getSantraxId());
            }
            String ani = scheduleExt.getAni();
            visitFact.setAni(scheduleExt.getAni());
        }
        return visitFact;
    }

    private class SchdlRequest {
        private String requestId;
        private String dnis;
        private String staffId;
        private String santraxId;
        private String requestedStaffId;
        private String requestedDnis;
        private Date fromDate;
        private Date toDate;
        private String fromDateString;
        private String toDateString;
        private String requestedAni;
        private String requestedPatientId;
        private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        private boolean clientEntered;

        public SchdlRequest(ScheduleEventRequest scheduleEventRequest) {
            this.setRequestId(scheduleEventRequest.getRequestId().toString());
            this.setDnis(scheduleEventRequest.getDnis());
            this.setStaffId(scheduleEventRequest.getStaffId());
            this.setSantraxId(scheduleEventRequest.getVv_id());
            this.setRequestedStaffId(getStaffId());
            this.setRequestedDnis(getDnis());
            this.setFromDate(scheduleEventRequest.getFromDate());
            this.setFromDateString(formatDate(this.getFromDate()));
            this.setToDate(scheduleEventRequest.getToDate());
            this.setToDateString(formatDate(this.getToDate()));
            this.setRequestedAni(scheduleEventRequest.getAni());
            this.setRequestedPatientId(scheduleEventRequest.getPatientId());
            this.setClientEntered(false);
            if(this.getRequestedPatientId() != null)
                this.setClientEntered(true);
            else
                this.setRequestedPatientId("<null>");

        }
        private String formatDate(Date d) {
            String result = "(null)";
            if(d != null) {
                result = this.sdf.format(d);
            }
            return result;
        }
        public boolean isClientEntered() {
            return clientEntered;
        }

        public void setClientEntered(boolean clientEntered) {
            this.clientEntered = clientEntered;
        }

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public String getDnis() {
            return dnis;
        }

        public void setDnis(String dnis) {
            this.dnis = dnis;
        }

        public String getStaffId() {
            return staffId;
        }

        public void setStaffId(String staffId) {
            this.staffId = staffId;
        }

        public String getSantraxId() {
            return santraxId;
        }

        public void setSantraxId(String santraxId) {
            this.santraxId = santraxId;
        }

        public String getRequestedStaffId() {
            return requestedStaffId;
        }

        public void setRequestedStaffId(String requestedStaffId) {
            this.requestedStaffId = requestedStaffId;
        }

        public String getRequestedDnis() {
            return requestedDnis;
        }

        public void setRequestedDnis(String requestedDnis) {
            this.requestedDnis = requestedDnis;
        }

        public Date getFromDate() {
            return fromDate;
        }

        public void setFromDate(Date fromDate) {
            this.fromDate = fromDate;
        }

        public Date getToDate() {
            return toDate;
        }

        public void setToDate(Date toDate) {
            this.toDate = toDate;
        }

        public String getFromDateString() {
            return fromDateString;
        }

        public void setFromDateString(String fromDateString) {
            this.fromDateString = fromDateString;
        }

        public String getToDateString() {
            return toDateString;
        }

        public void setToDateString(String toDateString) {
            this.toDateString = toDateString;
        }

        public String getRequestedAni() {
            return requestedAni;
        }

        public void setRequestedAni(String requestedAni) {
            this.requestedAni = requestedAni;
        }

        public String getRequestedPatientId() {
            return requestedPatientId;
        }

        public void setRequestedPatientId(String requestedPatientId) {
            this.requestedPatientId = requestedPatientId;
        }

    }
}
