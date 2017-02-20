package com.sandata.lab.rules.dl.processors;

import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.rules.call.model.State;
import com.sandata.lab.rules.call.model.VisitFact;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@SuppressWarnings("Duplicates")
/**
 * Created by tom.dornseif on 11/22/2015.
 */
public class VisitProcessorConsumerVisitStaffId implements Processor {
    private Logger visitDlLog = LoggerFactory.getLogger("visitDlLog");
    @Override
    public void process(Exchange exchange) throws Exception {
        String msg = "VisitProcessorConsumerVisitStaffId:";
        getVisitDlLog().info("VisitProcessorConsumerVisitStaffId:etered from CREATE_VISIT_RESPONSE");
        if(exchange.getIn().getBody() instanceof Visit) {
            Visit visit = exchange.getIn().getBody(Visit.class);
            getVisitDlLog().info(VisitProcessorConsumerVisitStaffId.class.getCanonicalName() + " Started::Have Visit");
            if (visit.getVisitEvent() == null || visit.getVisitEvent().size() == 0) {
                getVisitDlLog().info("WARNING::Visit was returned from activemq:queue:CREATE_VISIT_RESPONSE with no VISIT_EVENTS");
                getVisitDlLog().info("Previous warning was likely caused by new sched event in UI ");
            }
            String staffId;
            VisitFact vf = null;
            //Check if this vist came from special Schedule to visit creation process if it did it will not have a visit event.
            if (visit.getScheduleEventSK() != null && visit.getVisitActualStartTimestamp() == null && visit.getVisitActualEndTimestamp() == null) {
                staffId = visit.getStaffID();
                String patientId = visit.getPatientID();
                getVisitDlLog().info("visit response came back from schedule creation.  Adding patient to header" + patientId + "for visit for staff " + staffId);
                visitDlLog.info("visit response came back from schedule creation.  Adding patient to header" + patientId + "for visit for staff " + staffId);
                //WORK AROUND?
                if(visit.getVisitEvent().size() == 0) {
                    VisitEvent visitEvent = new VisitEvent();
                    visit.getVisitEvent().add(visitEvent);
                }
                vf = new VisitFact(visit);
                vf.setState(State.SCHEDULED);
                msg += String.format("Visit returned:VisitSK (%d), callIn (%s), callOut(%s), staffId (%s), patientId(%s)",
                        vf.getVisit().getVisitSK().intValue(), "<null>", "<null>", vf.getStaffId(), vf.getPatientId());
                getVisitDlLog().info(msg);
                //
            }
            else {
                //String dnis = visit.getVisitEvent().get(0).getDialedNumberIdentificationService();
                //String ani = visit.getVisitEvent().get(0).getAutomaticNumberIdentification();
                //staffId = visit.getVisitEvent().get(0).getStaffID();
                //getVisitDlLog().info("visit response came back.  Adding dnis to header" + dnis + "for visit for staff " + staffId);
                vf = new VisitFact(visit);
                getVisitDlLog().info(String.format("%s::CreateVisitFact(visit)", msg));
                boolean unexpectedNull = false;
                boolean unexpectedError = false;
                String ani;
                String visitSK;
                String scheduleEvntSk;
                String patientId = vf.getPatientId();
                if(patientId == null) {
                    patientId = "null";
                }
                if(vf.getVisit().getVisitSK() == null) {
                    visitSK = "null";
                    unexpectedNull = true;
                }
                else {
                    visitSK = vf.getVisit().getVisitSK().toString();
                }
                if(visit.getStaffID() == null) {
                    staffId = "null";
                    unexpectedNull = true;
                }
                else if(vf.getStaffId() == null) {
                    staffId = "null";
                    unexpectedError = true;
                }
                else {
                    staffId = vf.getStaffId();
                }
                //FOR PROPER VISIT EXCEPTION HANDLING WE MUST USE THE VISITS
                // STAFFID WHICH REFLECTS THE VALIDITY OF THE STAFF.
                // THE VISITEVENT REFLECTS WHAT WAS ENTERED BY CALLER.
                // WHICH MAY NOT BE VALID.
                vf.setStaffId(visit.getStaffID());
                //
                if(vf.getAni() == null) {
                    ani = "null";
                    unexpectedNull = true;
                }
                else {
                    ani = vf.getAni();
                }
                String state = "NOT SET";
                if (visit.getScheduleEventSK() == null) {
                    vf.setState(State.UNPLANNED);
                    state = "UNPLANNED";
                    exchange.getIn().setHeader("STATE", State.UNSCHEDULED);
                    getVisitDlLog().info(String.format("%s::UNSCHEDULED CANNOT SEND TO AGGREGATION!  ANI=%s", msg, ani ));
                    scheduleEvntSk = "INVALID";
                }
                else {
                    vf.setState(State.NOT_MATCHED);
                    state = "NOT_MATCHED";
                    exchange.getIn().setHeader("STATE", State.SCHEDULED);
                    scheduleEvntSk = visit.getScheduleEventSK().toString();
                    getVisitDlLog().info(String.format("%s::SCHEDULED SEND TO AGGREGATION!  SCHEDULE_EVNT_SK=%s, ANI=%s", msg, scheduleEvntSk, ani ));
                    exchange.getIn().setHeader("scheduleEvntSk", scheduleEvntSk);

                }
                String callIn = "null";
                String callOut = "null";
                if (vf.getCallIn() != null) {
                    callIn = vf.getCallIn().toString();
                }
                if (vf.getCallOut() != null) {
                    callOut = vf.getCallOut().toString();
                }
                msg += String.format("Visit returned:VisitSK (%s) with STATE of (%s), callIn (%s), callOut(%s), staffId (%s), ani(%s), patientId(%s)",
                        visitSK, state, callIn, callOut, staffId, ani, patientId);
                if(unexpectedError) {
                    getVisitDlLog().error("Unexpected null: visit.getStaffId() had staff but visitFact.getStaffId(), patientId(%s) returned null :: " + msg);
                    visitDlLog.info("Unexpected null: visit.getStaffId() had staff but visitFact.getStaffId(), patientId(%s) returned null :: " + msg);
                }
                else if (unexpectedNull) {
                    getVisitDlLog().warn("Unexpected null :: " + msg);
                    visitDlLog.info("Unexpected null :: " + msg);

                }
                else {
                    getVisitDlLog().info(msg);
                    visitDlLog.info(msg);
                }
                //vf.setDnis(dnis);
                //vf.setAni(ani);
                //verbose since set in construcotr
                //vf.setStaffId(staffId);
                //exchange.getIn().setHeader("DNIS", dnis);
            }

            exchange.getIn().setBody(vf, VisitFact.class);
        }
    }
    public Logger getVisitDlLog() {
        if(this.visitDlLog != null) {
            return this.visitDlLog;
        }
        else {
            this.visitDlLog = LoggerFactory.getLogger("visitDlLog");
            return this.visitDlLog;
        }

    }

    public void setVisitDlLog(Logger visitDlLog) {
        this.visitDlLog = visitDlLog;
    }
}
