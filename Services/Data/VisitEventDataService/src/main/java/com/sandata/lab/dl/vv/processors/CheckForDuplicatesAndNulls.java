package com.sandata.lab.dl.vv.processors;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.data.model.dl.model.VisitEvent;

/**
 * Created by tom.dornseif on 4/28/2016.
 */
public class CheckForDuplicatesAndNulls implements Processor {
    
    private Logger logger = LoggerFactory.getLogger("visitEventLogger");
    
    
    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        String dnis_staff_ani = null;
        if(in.getBody() instanceof Visit) {
            Visit visit = in.getBody(Visit.class);
            if(visit.getVisitEvent().size() > 0) {
                VisitEvent visitEvent = visit.getVisitEvent().get(0);
                dnis_staff_ani = getDuplicateHeaderId(visitEvent);
                in.setHeader("NO_VISIT_EVENTS", "FALSE");
                if(visit.getVisitEvent().size() > 1) {
                    Iterator<VisitEvent> iterator = visit.getVisitEvent().iterator();
                    visitEvent = iterator.next();
                    while(iterator.hasNext()) {
                        VisitEvent nextVisitEvent = iterator.next();
                        if(compare(visitEvent, nextVisitEvent)) {
                            iterator.remove();
                            logger.warn("duplicate was removed from internal visit.getVisitEvent()::");
                        }
                    }

                }
            }
            else {

                in.setHeader("NO_VISIT_EVENTS", "TRUE");
            }


            in.setHeader("DUPLICATE_ID", dnis_staff_ani);

        }
    }

    private boolean compare(VisitEvent visitEvent1, VisitEvent visitEvent2) {
        if(visitEvent1.getAutomaticNumberIdentification() == visitEvent2.getAutomaticNumberIdentification() &&
                visitEvent1.getDialedNumberIdentificationService() == visitEvent2.getDialedNumberIdentificationService() &&
                visitEvent1.getStaffID() == visitEvent2.getStaffID() &&
                compareDatesToSecond(visitEvent1.getVisitEventDatetime(), visitEvent2.getVisitEventDatetime())) {
            return true;
        }
        return false;
    }

    private boolean compareDatesToSecond(Date date1, Date date2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String compare1 = simpleDateFormat.format(date1);
        String compare2 = simpleDateFormat.format(date2);
        return compare1.equals(compare2);
    }

    private String getDuplicateHeaderId(VisitEvent visitEvent) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String dnis = visitEvent.getDialedNumberIdentificationService();
        String staff = visitEvent.getStaffID();
        String ani = visitEvent.getAutomaticNumberIdentification();
        String callTime = simpleDateFormat.format(visitEvent.getVisitEventDatetime());
        String header =  String.format("%s%s%s%s",dnis,staff,ani,callTime);
        return header;
    }
}
