package com.sandata.lab.exports.payroll.routes.aggregators;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import java.util.HashMap;

public class BsnEntConfigsAggregator implements AggregationStrategy {


    public BsnEntConfigsAggregator() {
        super();
    }


    public Exchange aggregate(Exchange original, Exchange resource) {

        Object originalBody = original.getIn().getBody();
        HashMap<String, Object> resourceResponse = (HashMap) resource.getIn().getBody();

        original.getIn().setBody(resourceResponse);

        return original;
    }

}
