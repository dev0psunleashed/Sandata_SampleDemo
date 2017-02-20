package com.sandata.lab.exports.payroll.routes.aggregators;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import java.util.ArrayList;
import java.util.HashMap;

public class BsnEntIdAggregator implements AggregationStrategy {


    public BsnEntIdAggregator() {
        super();
    }

    public Exchange aggregate(Exchange original, Exchange resource) {

        Object originalBody = original.getIn().getBody();
        HashMap<String, Object> resourceResponse = (HashMap) resource.getIn().getBody();

        original.getIn().setHeader("bsnEnts", resourceResponse);
        original.getIn().setBody(new ArrayList<String>(resourceResponse.keySet()));

        return original;
    }

}
