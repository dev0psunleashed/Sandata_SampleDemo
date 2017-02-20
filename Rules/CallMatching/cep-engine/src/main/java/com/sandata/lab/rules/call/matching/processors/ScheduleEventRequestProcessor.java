package com.sandata.lab.rules.call.matching.processors;

import com.sandata.lab.data.model.request.visit.ScheduleEventRequest;
import com.sandata.lab.rules.call.matching.app.AppContext;
import com.sandata.lab.rules.call.model.ScheduleEventRequestExt;
import com.sandata.lab.rules.call.model.VisitEventFact;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by tom.dornseif on 11/28/2015.
 */
public class ScheduleEventRequestProcessor implements Processor {
    private static final String CEP_ENG_SCHEDULE_REQUEST_LOGGER = "scheduleRequestLog";
    private Logger scheduleRequestLog = LoggerFactory.getLogger(CEP_ENG_SCHEDULE_REQUEST_LOGGER);

    private ScheduleEventRequestExt scheduleEventRequestExt;
    @Override
    public void process(Exchange exchange) throws Exception {

        if(exchange.getIn().getBody() instanceof VisitEventFact) {
            VisitEventFact visitEventFact = exchange.getIn().getBody(VisitEventFact.class);
            String dnis = visitEventFact.getDnis();
            String ani = visitEventFact.getAni();
            String staffId = visitEventFact.getStaffID();
            String patientId = visitEventFact.getPatientID();
            if(patientId != null && patientId.length() > 0 && patientId.startsWith("0")) {
                this.getScheduleRequestLog().info(String.format("WARNING::EVV PADS WITH ZEROS GEORGE SEES PATIENT_ID AS A ALPHA AND MAY NOT TREAT IT AS A NUMBER!  Original PatientId was (%s)", patientId));
                String temporaryPtId = patientId.replaceFirst("^0+(?!$)", "");
                //patientId = temporaryPtId;
                this.getScheduleRequestLog().info(String.format("WARNING::EVV PADS WITH ZEROS GEORGE SEES PATIENT_ID AS A ALPHA AND DOESNT TREAT IT AS A NUMBER!  If we remove leading zero(s) new PatientId is (%s)", temporaryPtId));
            }
            Date center = visitEventFact.getCallInTime();
            ScheduleEventRequestExt seRequest = new ScheduleEventRequestExt(dnis, center, AppContext.OFFSET_PAST, AppContext.OFFSET_FUTURE, staffId);
            seRequest.setAni(ani);
            seRequest.setPatientId(patientId);
            if(patientId == null)
                patientId = "<null>";
            this.getScheduleRequestLog().info(String.format("cep-engine : ScheduleEventRequestProcessor : preparing request, dnis : %s, ani : %s, staffId : %s, pateintId : %s", dnis, ani, staffId, patientId));
            exchange.getIn().setHeader("ani", ani);
            ScheduleEventRequest scheduleEventRequest = seRequest.getScheduleRequest();
            scheduleEventRequest.setRequestId(visitEventFact.getRequestId());
            exchange.getIn().setBody(scheduleEventRequest, ScheduleEventRequest.class);
        }
    }
    public Logger getScheduleRequestLog() {
        if(scheduleRequestLog != null) {
            return scheduleRequestLog;
        }
        else {
            this.scheduleRequestLog = LoggerFactory.getLogger("scheduleRequestLog");
            return this.scheduleRequestLog;
        }
    }

    public void setScheduleRequestLog(Logger scheduleRequestLog) {
        this.scheduleRequestLog = scheduleRequestLog;
    }

}
