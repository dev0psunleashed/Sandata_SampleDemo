package com.sandata.lab.rules.exceptions.aggregation;

import com.sandata.lab.rules.exceptions.processors.PrepareForAggregation;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 8/16/2016
 * Time: 5:31 PM
 */
public class TimedOutProcess {
    private Logger visitExcpLog = LoggerFactory.getLogger("visitExcpLog");
    @Handler
    public void processMatches(Exchange exchange) throws Exception {

        Message in  = exchange.getIn();
        if(in instanceof ArrayList) {
            ArrayList list = in.getBody(ArrayList.class);
            if(list != null && list.size() == 1) {
                String scheduleEventSk = (String)in.getHeader(PrepareForAggregation.SCHEDULE_EVNT_SK);
                getVisitExcpLog().info(String.format("ERROR::VisitAndScheduleAggregation likely timed out waiting for createVisitRequest to return visit with scheduleEvntSk of %s.", scheduleEventSk ));
                exchange.getIn().setBody(null);
            }

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
