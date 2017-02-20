package com.sandata.lab.rules.call.matching.processors;

import com.sandata.lab.rules.call.model.VisitEventFact;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.Date;
import java.util.Hashtable;

/**
 * Created by tom.dornseif on 2/25/2016.
 */
public class ConfigsPropertiesAndLookupsProcessor implements Processor{
    private static final Hashtable<String, Date> dnisCalls = new Hashtable<>();
    @Override
    public void process(Exchange exchange) throws Exception {
        if(exchange.getIn().getBody() instanceof VisitEventFact) {
            VisitEventFact visitEventFact = exchange.getIn().getBody(VisitEventFact.class);
            String dnis = visitEventFact.getDnis();
            Date now = new Date();
            if(dnisCalls.containsKey(dnis))
            {
                Date lastCall = (Date)dnisCalls.get(dnis);
                Date test = new Date(lastCall.getTime() + 300000);//
                if(lastCall.before(now) ) {
                    exchange.getIn().setBody(dnis);
                }
                else {
                    exchange.getIn().setBody(null);
                }


            }
            else
            {
                dnisCalls.put(dnis, now);
                exchange.getIn().setBody(dnis);
            }

        }
    }
}
