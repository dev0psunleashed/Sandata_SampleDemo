package com.sandata.lab.rules.dl.processors;

import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.rules.call.model.UnplannedVisit;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tom.dornseif on 11/22/2015.
 */
public class UnplannedVisitProcessorProducer implements Processor {
    private Logger visitDlLog = LoggerFactory.getLogger("visitDlLog");
    @Override
    public void process(Exchange exchange) throws Exception {
        if(exchange.getIn().getBody() instanceof UnplannedVisit) {
            UnplannedVisit unplannedVisit = exchange.getIn().getBody(UnplannedVisit.class);
            Visit visit = unplannedVisit.getVisit();
            if(visit.getVisitEvent() != null && visit.getVisitEvent().size() > 0) {
                VisitEvent ve = visit.getVisitEvent().get(0);
                String ani = ve.getAutomaticNumberIdentification();
                if (ani == null) {
                    ani = "null";
                }
                getVisitDlLog().info(String.format(" Putting a Unplanned Visit on the queue! ani=%s", ani));
            }
            exchange.getIn().setBody(visit);
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
