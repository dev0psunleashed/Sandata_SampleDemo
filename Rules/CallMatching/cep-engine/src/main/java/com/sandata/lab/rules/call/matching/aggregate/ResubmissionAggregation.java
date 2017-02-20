package com.sandata.lab.rules.call.matching.aggregate;

import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.rules.call.matching.exceptions.CepException;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created by tom.dornseif on 12/23/2015.
 */
public class ResubmissionAggregation {
    Logger logger = LoggerFactory.getLogger(ResubmissionAggregation.class);

    @Handler
    public void processMatches(Exchange exchange) throws CepException {
        Message in = exchange.getIn();
        if (in.getBody() instanceof ArrayList) {
            ArrayList list = in.getBody(ArrayList.class);
            VisitEvent visitEvent = (VisitEvent)list.get(0);
            in.setBody(visitEvent);

        }
    }
}