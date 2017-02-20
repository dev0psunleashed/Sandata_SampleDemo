package com.sandata.lab.rules.call.matching.listeners;

import com.sandata.lab.data.model.dl.model.ScheduleEvent;
import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.rules.call.matching.app.AppContext;
import com.sandata.lab.rules.call.model.Schedule;
import com.sandata.lab.rules.call.model.State;
import com.sandata.lab.rules.call.model.VisitAndSchedule;
import com.sandata.lab.rules.call.model.VisitFact;
import org.apache.camel.CamelContext;
import org.apache.camel.ServiceStatus;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 12/15/2015
 * Time: 12:57 PM
 */
public class VisitWMListener implements RuleRuntimeEventListener {
    private Logger rulesLog = LoggerFactory.getLogger("rulesLog");

    @Override
    public void objectInserted(ObjectInsertedEvent objectInsertedEvent) {
        String msg = "kieSession Object Inserted ";
        if(objectInsertedEvent.getObject() instanceof VisitFact) {
            msg+= " VisitFact ";
            VisitFact visitFact = (VisitFact)objectInsertedEvent.getObject();
            msg = getVisitDescription(visitFact, msg);
            sendMsg(msg);
            if(visitFact.getState() == State.AGG_WAITING_FOR_RESPONSE) {
                getRulesLog().info(String.format(" From Call Server : call=%s", msg));
            }
            else if(visitFact.getState() == State.AGG_INSERTED_FROM_RESPONSE) {
                getRulesLog().info(String.format(" From Database request for schedules : schedule or visit=%s", msg));
            }
            else if(visitFact.getState() == State.POSSIBLE_MATCH_WAITING_FOR_RESPONSE){
                getRulesLog().info(String.format("  From Call Server : call==%s", msg));
            }
            else if(visitFact.getState() == State.POSSIBLE_MATCH_FROM_RESPONSE){
                getRulesLog().info(String.format("  From Database request for schedules : schedule or visit=%s", msg));
            }
            else {
                getRulesLog().info(String.format("  Unexpected schedule or call=%s", msg));
            }


        }
        else if(objectInsertedEvent.getObject() != null){
            getRulesLog().info(String.format("Unexpected Object type=%s", objectInsertedEvent.getObject().getClass().getCanonicalName()));
        }
    }
    private String getVisitDescription(VisitFact visitFact, String msg) {
        msg+= " state : " + visitFact.getState().name();
        msg+= " staffId : " + visitFact.getStaffId();
        if(visitFact.getAni() == null) {
            msg+= " ani : was null";
        }
        else {
            msg += " ani : " + visitFact.getAni();
        }
        String patientId = visitFact.getPatientId();
        if(patientId == null) {
            patientId = visitFact.getVisit().getPatientID();
            if(patientId == null)
                patientId = "<null>";
        }
        if(visitFact.isClientEntered()) {
            msg += " clientID was entered : " +  patientId;
        }
        else {
            msg += " clientId was not flagged as entered : " + patientId;
        }
        if(visitFact.getCallIn() != null) {
            msg += " call in : " + visitFact.getCallIn().toString();
        }
        if(visitFact.getCallOut() != null) {
            msg += " call out : " + visitFact.getCallOut().toString();
        }
        if(visitFact.getState() != null && visitFact.getState().name() != null) {
            msg += " State = " + visitFact.getState().name();
        }
        if(!visitFact.getTaskList().isEmpty()) {
            for(int i = 0;i < visitFact.getTaskList().size();i++) {
                msg += " taskList : " + visitFact.getTaskList().get(i).getVisitTaskListID();
            }
        }
        return msg;
    }
    @Override
    public void objectUpdated(ObjectUpdatedEvent objectUpdatedEvent) {
        String msg = "kieSession Object Updated ";
        if(objectUpdatedEvent.getObject() instanceof VisitFact) {
            msg+= " VisitFact ";
            VisitFact visitFact = (VisitFact)objectUpdatedEvent.getObject();
            msg += getVisitDescription(visitFact, msg);
            sendMsg(msg);
            if(visitFact.getState() == State.MATCHED) {
                getRulesLog().info(String.format("Creating matched visit=%s", msg));
                prepareIfScheduled(visitFact);
                createVisit(visitFact);

            }
            else if (visitFact.getState() == State.CREATE_UNPLANNED_VISIT) {
                getRulesLog().info(String.format("Creating unplanned visit=%s", msg));
                createVisit(visitFact);
            }

        }
    }

    private void prepareIfScheduled(VisitFact visitFact) {
        if(visitFact.getVisit() != null && visitFact.getVisit().getScheduleEventSK() != null) {
            Visit visit = visitFact.getVisit();
            BigInteger scheduleEventSk = visit.getScheduleEventSK();
            getRulesLog().info(String.format("visitWMListener::In Update Check for schedule passed -=> was a schedule, had scheduleSk=%s.", scheduleEventSk.toString()));
            Date from = visitFact.getScheduledFrom();
            Date to = visitFact.getScheduledTo();
            if(from != null && to != null) {
                Schedule schedule = new Schedule(from, to);
                schedule.getScheduleEventExt().setStaffID(visitFact.getStaffId());
                schedule.setState(State.LOADED);
                VisitAndSchedule visitAndSchedule = new VisitAndSchedule();
                visitAndSchedule.setSchedule(schedule);
                visitAndSchedule.setScheduleEventSk(scheduleEventSk.toString());
                CamelContext context = AppContext.getContext();
                if(context.getStatus() == ServiceStatus.Started) {
                    context.createProducerTemplate().sendBodyAndHeader("activemq:queue:AGGREGATE_VISIT_AND_SCHEDULE_EXCP_EVENTS", visitAndSchedule, "scheduleEvntSk", scheduleEventSk.toString());
                }


            }
            else {
                getRulesLog().info(String.format("visitWMListener::In Update Check for schedule passed -=> was a schedule, had scheduleSk=null. But not valid from to dates", scheduleEventSk.toString()));

            }
        }
        else {
            getRulesLog().info("visitWMListener::In Update Check for schedule failed -=> not a schedule.");
        }
    }

    private void createVisit(VisitFact visitFact) {
        CamelContext context = AppContext.getContext();
        if(context.getStatus() == ServiceStatus.Started) {
            //lets make sure we have a staffId.
            if(visitFact.getStaffId() == null)
            {
                if(visitFact.getCallCallIn() != null) {
                    if(visitFact.getCallCallIn().getStaffID() != null) {
                        visitFact.setStaffId(visitFact.getCallCallIn().getStaffID());
                    }
                }
                else if(visitFact.getCallCallOut() != null){
                    if(visitFact.getCallCallOut().getStaffID() != null) {
                        visitFact.setStaffId(visitFact.getCallCallOut().getStaffID());
                    }
                }
            }
            context.createProducerTemplate().sendBody("direct:sendVisit", visitFact);
        }
    }

    @Override
    public void objectDeleted(ObjectDeletedEvent objectDeletedEvent) {
        String msg = "kieSession Object Deleted ";

        if(objectDeletedEvent.getOldObject() instanceof VisitFact) {
            msg+= " VisitFact ";
            VisitFact visitFact = (VisitFact)objectDeletedEvent.getOldObject();
            msg = getVisitDescription(visitFact, msg);
            sendMsg(msg);
            getRulesLog().info(msg);
        }
    }

    private void sendMsg(String msg) {
        CamelContext context = AppContext.getContext();
        if(context.getStatus() == ServiceStatus.Started) {
            context.createProducerTemplate().sendBody("mock:CEP_MESSAGES", msg);
        }

    }

    public Logger getRulesLog() {
        if(this.rulesLog != null) {
            return rulesLog;
        }
        else {
            rulesLog = LoggerFactory.getLogger("rulesLog");
            return rulesLog;
        }
    }

    public void setRulesLog(Logger rulesLog) {
        this.rulesLog = rulesLog;
    }
}
