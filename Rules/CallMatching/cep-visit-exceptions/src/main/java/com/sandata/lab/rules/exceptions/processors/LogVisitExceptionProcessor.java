package com.sandata.lab.rules.exceptions.processors;

import com.sandata.lab.data.model.dl.model.VisitException;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by tom.dornseif on 2/19/2016.
 */
public class LogVisitExceptionProcessor implements Processor {
    private Logger visitExcpLog = LoggerFactory.getLogger("visitExcpLog");
    @Override
    public void process(Exchange exchange) throws Exception {
        Logger logger = LoggerFactory.getLogger(LogVisitExceptionProcessor.class);

        Message in = exchange.getIn();
        if(in.getBody() instanceof VisitException) {
            VisitException visitException = in.getBody(VisitException.class);
            if(visitException != null && visitException.getVisitExceptionSK() != null)
                getVisitExcpLog().info(String.format("VisitSK (%s) ExceptionLookupSK(%s), ExceptionID(%s)", visitException.getVisitSK().toString(), visitException.getVisitExceptionSK(),visitException.getExceptionID().toString()));

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
