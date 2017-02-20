package com.sandata.lab.rules.dl.processors;

import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.rules.call.model.VisitFact;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

/**
 * Created by tom.dornseif on 2/20/2016.
 */
public class PullVisitSKFromVisitProcessor implements Processor{
    private Logger visitDlLog = LoggerFactory.getLogger("visitDlLog");
    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        getVisitDlLog().info("Inside PullVisitSKFromVisitProcessor Pulling VisitSK ");
        if(in.getBody() instanceof VisitFact) {
            VisitFact visitFact = in.getBody(VisitFact.class);
            String ani = visitFact.getAni();
            if(ani == null) {
                ani = "null";
            }
            if(visitFact.getVisit() == null) {
                getVisitDlLog().info(String.format("%s :: ERROR :: Visit passed was null. ani=%s ", PullVisitSKFromVisitProcessor.class.getCanonicalName(),  ani));
            }
            else {
                BigInteger sk = visitFact.getVisit().getVisitSK();
                int visitSk = -1;
                if (sk != null) {
                    visitSk = sk.intValue();
                }
                getVisitDlLog().info(String.format("%s :: Visit was passed to process to clear exceptions for visit with visitSk=%d, ani=%s ", PullVisitSKFromVisitProcessor.class.getCanonicalName(), visitSk, ani));
                in.setHeader("VISIT_SK", visitSk);
                //in.setBody(visitSk);
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
