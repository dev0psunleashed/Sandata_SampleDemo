package com.sandata.lab.rules.call.matching.processors;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 10/29/2016
 * Time: 11:15 AM
 */
public class DuplicateLoggerProcessor implements org.apache.camel.Processor {
    private Logger dupesLog = LoggerFactory.getLogger("dupesLog");

    @Override
    public void process(Exchange exchange) throws Exception {
        String dnis_staff_ani = (String)exchange.getIn().getHeader("DUPLICATE_ID");
        String info = String.format("DISCARDED DUPLICATE_ID=%s", dnis_staff_ani);
        getDupesLog().info(info);
        //check if its really making it to the database.
    }


    public Logger getDupesLog() {
        if(this.dupesLog == null) {
            this.dupesLog = LoggerFactory.getLogger("dupesLog");
        }
        return dupesLog;
    }

    public void setDupesLog(Logger dupesLog) {
        this.dupesLog = dupesLog;
    }

}
