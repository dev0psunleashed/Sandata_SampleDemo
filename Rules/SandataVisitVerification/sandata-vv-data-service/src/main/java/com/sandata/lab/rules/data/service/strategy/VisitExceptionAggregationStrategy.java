package com.sandata.lab.rules.data.service.strategy;

import com.sandata.lab.data.model.dl.model.VisitException;
import com.sandata.lab.rules.vv.fact.VisitEventFact;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Aggregation strategy for VisitException.</p>
 *
 * @author thanhxle
 */
public class VisitExceptionAggregationStrategy implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

        List<VisitException> visitExceptionList;

        if (oldExchange == null) {
            oldExchange = newExchange;
            visitExceptionList = new ArrayList<>();
        } else {
            visitExceptionList = (List<VisitException>) oldExchange.getIn().getBody();
        }

        VisitException visitException = (VisitException) newExchange.getIn().getBody();
        visitExceptionList.add(visitException);

        oldExchange.getIn().setBody(visitExceptionList);


        return oldExchange;
    }
}
