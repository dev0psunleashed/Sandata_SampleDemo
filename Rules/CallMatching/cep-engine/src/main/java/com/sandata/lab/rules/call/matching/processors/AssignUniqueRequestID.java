package com.sandata.lab.rules.call.matching.processors;

import com.sandata.lab.rules.call.model.VisitEventFact;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 7/13/2016
 * Time: 3:20 PM
 */
public class AssignUniqueRequestID implements Processor {
    Logger logger = LoggerFactory.getLogger(AssignUniqueRequestID.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        if(in.getBody() instanceof VisitEventFact) {
            VisitEventFact visitEventFact = in.getBody(VisitEventFact.class);
            visitEventFact.setRequestId(UUID.randomUUID());
            in.setBody(visitEventFact);
            exchange.setIn(in);
        }
    }
}
