package com.sandata.lab.rules.exceptions.processors;

import com.sandata.lab.rules.call.model.State;
import com.sandata.lab.rules.call.model.VisitAndSchedule;
import com.sandata.lab.rules.call.model.VisitAndScheduleType;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 9/12/2016
 * Time: 8:20 AM
 */
public class PrepCheckerResponseForAggregation implements Processor{
    private Logger visitExcpLog = LoggerFactory.getLogger("visitExcpLog");

    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        String scheduleEvntSk = null;
        List<VisitAndSchedule> list = new ArrayList<>();
        if (in.getBody() instanceof VisitAndSchedule) {
            //for this checker we will know the scheduleeventsk from the object.
            VisitAndSchedule visitAndSchedule = in.getBody(VisitAndSchedule.class);
            scheduleEvntSk = visitAndSchedule.getScheduleEventSk();
            in.setHeader(PrepareForAggregation.SCHEDULE_EVNT_SK, scheduleEvntSk);
            //verify type of visitAndSchedule object
            getVisitExcpLog().info(String.format("PrepareForAggregation::Processing VisitAndScheduleObject with Type of %s", visitAndSchedule.getType().name()));
            list.add(visitAndSchedule);
            in.setHeader("STATE", State.AGG_WAITING_FOR_RESPONSE.name());
            getVisitExcpLog().info(String.format("PrepareForAggregation::RULES -=> Adding VisitAndSchedule from doScheduledVisitExceptionsChecker to aggregation.  Has scheduleEventSk=%s", scheduleEvntSk));
            in.setBody(list);
        }
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
