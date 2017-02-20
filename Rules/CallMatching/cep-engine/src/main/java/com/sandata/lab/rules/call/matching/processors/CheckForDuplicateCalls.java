package com.sandata.lab.rules.call.matching.processors;


import com.sandata.lab.rules.call.model.VisitEventFact;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;


/**
 * Created by tom.dornseif on 4/19/2016.
 */
public class CheckForDuplicateCalls implements Processor {

    private Logger dupesLog = LoggerFactory.getLogger("dupesLog");
    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        if(in.getBody() instanceof VisitEventFact) {
            VisitEventFact visitEventFact = in.getBody(VisitEventFact.class);
            String dnis_staff_ani = getDuplicateHeaderId(visitEventFact);
            if(in.getHeader("DUPLICATE_ID") != null) {
                dnis_staff_ani = (String)in.getHeader("DUPLICATE_ID");
                getDupesLog().info(String.format("READ DUPLICATE_ID=%s", dnis_staff_ani));
            }
            else
                getDupesLog().info(String.format("SET DUPLICATE_ID=%s", dnis_staff_ani));

            in.setHeader("DUPLICATE_ID", dnis_staff_ani);
        }
    }

    private String getDuplicateHeaderId(VisitEventFact visitEventFact) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String dnis = visitEventFact.getDnis();
        String staff = visitEventFact.getStaffID();
        String ani = visitEventFact.getAni();
        String callTime = simpleDateFormat.format(visitEventFact.getVisitEventDatetime());
        String header =  String.format("%s%s%s%s",dnis,staff,ani,callTime);
        return header;
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
