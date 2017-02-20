package com.sandata.lab.rules.dl.processors;

import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.rules.call.model.VisitFact;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

/**
 * Created by tom.dornseif on 4/19/2016.
 */
public class ProcessVisitToVisitEvent implements Processor {
    private Logger visitDlLog = LoggerFactory.getLogger("visitDlLog");
    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        if(in.getBody() instanceof VisitFact) {
            VisitFact vf = in.getBody(VisitFact.class);
            String ani = vf.getAni();
            if(ani == null) {
                ani = "null";
            }
            if(vf.getVisit() != null && vf.getVisit().getVisitEvent() != null && vf.getVisit().getVisitEvent().size() > 0) {
                VisitEvent ve = vf.getVisit().getVisitEvent().get(vf.getVisit().getVisitEvent().size() - 1);
                in.setBody(ve, VisitEvent.class);
                BigInteger sk =  ve.getVisitSK();
                String visitSk = "null";
                if(sk != null) {
                    visitSk = sk.toString();
                }
                getVisitDlLog().info(String.format("%s :: VisitEvent was passed to process to retract. visitSk=%s, ani=%s ", ProcessVisitToVisitEvent.class.getCanonicalName(), visitSk, ani));
                exchange.setIn(in);
            }
            else {
                getVisitDlLog().info(String.format("%s :: No VisitEvent was passed to process so nothing to retract. ani=%s ", ProcessVisitToVisitEvent.class.getCanonicalName(), ani));
            }
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
