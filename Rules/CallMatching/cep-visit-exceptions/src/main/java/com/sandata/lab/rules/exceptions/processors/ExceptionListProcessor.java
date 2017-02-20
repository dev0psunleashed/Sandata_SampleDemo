package com.sandata.lab.rules.exceptions.processors;

import com.sandata.lab.data.model.dl.model.ExceptionLookup;
import com.sandata.lab.rules.exceptions.routes.VisitExceptionRoute;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;

/**
 * Created by tom.dornseif on 2/25/2016.
 */
public class ExceptionListProcessor implements Processor{
    private Logger visitExcpLog = LoggerFactory.getLogger("visitExcpLog");
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
    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        if(in.getBody() instanceof List) {
            List<ExceptionLookup> list = in.getBody(List.class);
            int count = list.size();
            getVisitExcpLog().info(String.format("There were (%d) Exceptions returned by Visit Exceptions lookup!", count));
            getVisitExcpLog().info("VisitExceptionRoute.exceptionLookupSize was set to prevent using empty list");
            VisitExceptionRoute.exceptionLookupSize = count;
            if(count > 0 && count == VisitExceptionRoute.getExceptionLookupHashtable().size()) {
                  getVisitExcpLog().info("Exception Lookup Hashtable already loaded and contains correct number of exception definitions");
            }
            else if(count > 0 ) {
                VisitExceptionRoute.getExceptionLookupHashtable().clear();

                for(int i = 0; i < count; i++) {

                    ExceptionLookup exceptionLookup = list.get(i);
                    String ID;
                    if (exceptionLookup != null) {
                        VisitExceptionRoute.getExceptionLookupHashtable().put(exceptionLookup.getExceptionID(), exceptionLookup);
                        String nonFixable;
                        String canAcknowledge;
                        if (exceptionLookup.isExceptionNonFixableIndicator())
                            nonFixable = "TRUE";
                        else
                            nonFixable = "FALSE";

                        if (exceptionLookup.isExceptionAcknowledgableIndicator()) {
                            canAcknowledge = "TRUE";
                        } else {
                            canAcknowledge = "FALSE";
                        }
                        if (exceptionLookup.getExceptionID() != null) {
                            ID = exceptionLookup.getExceptionID().toString();
                        } else {
                            ID = "(null)";
                        }
                        getVisitExcpLog().info(String.format("VisitExceptions, SK = (%d) : ID = (%s) : Name= (%s) : Description = (%s) : non fixable = (%s) : Acknowledge = (%s)",
                                exceptionLookup.getExceptionLookupSK(), ID, exceptionLookup.getExceptionName(), exceptionLookup.getExceptionDescription(),
                                nonFixable, canAcknowledge));
                    } else {
                        getVisitExcpLog().info("ERROR::  exceptionLookup was NULL!!!              ");
                    }
                }


            }
        }
        else
        {
            getVisitExcpLog().info("ERROR::UNKNOWN EXCHANGE IN EXCEPTIONLISTPROCESSOR");
        }
    }
}
