package com.sandata.lab.rules.exceptions.aggregation;

import com.sandata.lab.rules.call.model.State;
import com.sandata.lab.rules.exceptions.processors.PrepareForAggregation;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 8/16/2016
 * Time: 4:33 PM
 */
public class VisitAndScheduleAggregationStrategy implements AggregationStrategy {

    private Logger visitExcpLog = LoggerFactory.getLogger("visitExcpLog");

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

        ArrayList data = newExchange.getIn().getBody(ArrayList.class);
        String scheduleEventSk = (String) newExchange.getIn().getHeader(PrepareForAggregation.SCHEDULE_EVNT_SK);
        String name = (String)newExchange.getIn().getHeader("STATE");
        if(name == null) {
            name = null;
        }
        getVisitExcpLog().info(String.format("VisitAndScheduleAggregationStrategy.aggregate() : scheduleEvntSk = %s",scheduleEventSk));
        getVisitExcpLog().info(String.format("VisitAggregationStrategy:: newExchange Header STATE (%s)", name));
        ArrayList aggregatedData;
        Exchange result = newExchange;
        // first time
        if(oldExchange == null) {
            getVisitExcpLog().info(" VisitAggregationStrategy:: oldExchange was null so first time in (from rules, waiting for database)");
            aggregatedData = new ArrayList();
        }
        else {
            aggregatedData = oldExchange.getIn().getBody(ArrayList.class);
            if(oldExchange.getIn().getHeader("STATE") != null && oldExchange.getIn().getHeader("STATE").equals(State.AGG_WAITING_FOR_RESPONSE.name())) {
                //we should make sure rules didnt pass a second schedule.
                name = State.AGG_WAITING_FOR_RESPONSE.name();
                getVisitExcpLog().info("Old message had expected result and was waiting for database to respond with visit old STATE = AGG_WAITING FOR RESPONSE");
            }
            getVisitExcpLog().info("VisitAggregationStrategy:: these match :: oldExchange Header (%s) newExchange Header (%s)",
            (String)oldExchange.getIn().getHeader(PrepareForAggregation.SCHEDULE_EVNT_SK),
                    (String)newExchange.getIn().getHeader(PrepareForAggregation.SCHEDULE_EVNT_SK) );
            if(scheduleEventSk != null) {
                result.getIn().setHeader(PrepareForAggregation.SCHEDULE_EVNT_SK, scheduleEventSk);
            }
        }
        aggregatedData.addAll(data);
        result.getIn().setHeader(PrepareForAggregation.SCHEDULE_EVNT_SK, scheduleEventSk);

        result.getIn().setHeader("STATE", name);
        result.getIn().setBody(aggregatedData);

        getVisitExcpLog().info(String.format("inside aggregation strategy: Returned Result exchange with body of ArrayList size=%d, scheduleEventSk=%s, STATE=%s", aggregatedData.size(), scheduleEventSk, name));

        return result;
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
