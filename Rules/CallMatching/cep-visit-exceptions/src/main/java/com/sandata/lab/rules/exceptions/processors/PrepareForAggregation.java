package com.sandata.lab.rules.exceptions.processors;

import com.sandata.lab.rules.call.model.Schedule;
import com.sandata.lab.rules.call.model.State;
import com.sandata.lab.rules.call.model.VisitAndSchedule;
import com.sandata.lab.rules.call.model.VisitFact;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 8/16/2016
 * Time: 4:25 PM
 */
public class PrepareForAggregation implements org.apache.camel.Processor {
    public static final String SCHEDULE_EVNT_SK = "scheduleEvntSk";
    private Logger visitExcpLog = LoggerFactory.getLogger("visitExcpLog");
    @Override
    public void process(Exchange exchange) throws Exception {

        Message in = exchange.getIn();
        String scheduleEvntSk = null;
        List<VisitAndSchedule> list = new ArrayList<>();
        if(in.getBody() instanceof VisitFact) {
            VisitFact visitFact = in.getBody(VisitFact.class);
            VisitAndSchedule visitAndSchedule = new VisitAndSchedule();
            if(visitFact.getVisit() != null && visitFact.getVisit().getScheduleEventSK() != null) {
                scheduleEvntSk = visitFact.getVisit().getScheduleEventSK().toString();
                visitAndSchedule.setScheduleEventSk(scheduleEvntSk);
                visitAndSchedule.setVisit(visitFact);
                list.add(visitAndSchedule);
                in.setHeader("STATE", State.AGG_INSERTED_FROM_RESPONSE.name());
                getVisitExcpLog().info(String.format("PrepareForAggregation::DATABASE -=> Adding visitFact from CreateVisitRequest to aggregation.  Has scheduleEventSk=%s", scheduleEvntSk));
                in.setBody(list, List.class);
            }
            else {
                in.setBody(null);
            }
        }
        else if(in.getBody() instanceof Schedule) {
            Schedule schedule = in.getBody(Schedule.class);
            VisitAndSchedule visitAndSchedule = new VisitAndSchedule();
            visitAndSchedule.setSchedule(schedule);//this will also set the internal type to SCHEDULE
            scheduleEvntSk = (String)in.getHeader(SCHEDULE_EVNT_SK);
            visitAndSchedule.setScheduleEventSk(scheduleEvntSk);
            list.add(visitAndSchedule);
            in.setHeader("STATE", State.AGG_WAITING_FOR_RESPONSE.name());
            getVisitExcpLog().info(String.format("PrepareForAggregation::RULES -=> Adding Schedule from rules to aggregation.  Has scheduleEventSk=%s", scheduleEvntSk));
            in.setBody(list, List.class);
        }
        else if (in.getBody() instanceof VisitAndSchedule) {
            VisitAndSchedule visitAndSchedule = in.getBody(VisitAndSchedule.class);
            scheduleEvntSk = (String)in.getHeader(SCHEDULE_EVNT_SK);
            visitAndSchedule.setScheduleEventSk(scheduleEvntSk);
            //verify type of visitAndSchedule object
            getVisitExcpLog().info(String.format("PrepareForAggregation::Processing VisitAndScheduleObject with Type of %s", visitAndSchedule.getType().name()));
            list.add(visitAndSchedule);
            in.setHeader("STATE", State.AGG_WAITING_FOR_RESPONSE.name());
            getVisitExcpLog().info(String.format("PrepareForAggregation::RULES -=> Adding VisitAndSchedule from rules to aggregation, waiting for response.  Has scheduleEventSk=%s", scheduleEvntSk));
            in.setBody(list, List.class);
        }
        else {
            Object object = in.getBody();
            if(object == null) {
                getVisitExcpLog().info("ERROR :: ALERT::NULL passed into PrepareForAggregation!");
            }
            else {
                String typeName = object.getClass().getSimpleName();
                getVisitExcpLog().info(String.format("ERROR :: ALERT::Unexpected Type in PrepareForAggregation:: type=%s!", typeName));
                getVisitExcpLog().info(String.format("ERROR :: ALERT::Unexpected Type in PrepareForAggregation:: info=%s!", object.toString()));
            }

            getVisitExcpLog().info("ERROR :: ALERT::Unexpected Type or already null in PrepareForAggregation nulling out body of exchange to prevent further processing!");
            in.setBody(null);
        }

        exchange.setIn(in);
    }

    public Logger getVisitExcpLog() {
        if(this.visitExcpLog != null) {
            return this.visitExcpLog;
        }
        else {
            this.visitExcpLog = LoggerFactory.getLogger("visitExcpLog");
            return this.visitExcpLog;
        }
    }

    public void setVisitExcpLog(Logger visitExcpLog) {
        this.visitExcpLog = visitExcpLog;
    }
}
