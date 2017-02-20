package com.sandata.lab.rules.call.matching.service.strategy;

import com.sandata.lab.rules.vv.fact.VisitEventFact;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Aggregation strategy for VisitEventFacts.</p>
 *
 * @author jasonscott
 */
public class VisitEventAggregationStrategy implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

        List<VisitEventFact> visitEventFactList;

        if (oldExchange == null) {
            oldExchange = newExchange;
            visitEventFactList = new ArrayList<>();
        } else {
            visitEventFactList = (List<VisitEventFact>) oldExchange.getIn().getBody();
        }

        VisitEventFact visitEventFact = (VisitEventFact) newExchange.getIn().getBody();
        visitEventFactList.add(visitEventFact);

        oldExchange.getIn().setBody(visitEventFactList);

        //put business entity id to header
        oldExchange.getIn().setHeader("bsnEntId", visitEventFact.getBusinessEntityId());
        
        return oldExchange;
    }
}
