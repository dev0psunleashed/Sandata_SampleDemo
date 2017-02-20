package com.sandata.lab.rules.dl.processors;

import com.sandata.lab.rules.call.model.VisitFact;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 9/25/2016
 * Time: 11:14 AM
 */
public class TestObjectInExchange implements org.apache.camel.Processor {
    private Logger visitDlLog = LoggerFactory.getLogger("visitDlLog");
    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();

        if( in.getBody() == null)
        {
            getVisitDlLog().info("TestObjectInExchange::Object was null");
        }
        else if(in.getBody() instanceof VisitFact) {
            getVisitDlLog().info("TestObjectInExchange::Object was VisitFact");
        }
        else if( in.getBody() instanceof java.lang.String) {
            getVisitDlLog().info("TestObjectInExchange::Object was string");
        }
        else {
            getVisitDlLog().info("TestObjectInExchange::Object was " + in.getBody().toString());
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
