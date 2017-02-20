package com.sandata.lab.exports.payroll.routes.aggregators;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import java.util.HashMap;

public class BsnEntLocationAggregator implements AggregationStrategy {


    public BsnEntLocationAggregator() {
        super();
    }


    public Exchange aggregate(Exchange original, Exchange resource) {

        Object originalBody = original.getIn().getBody();
        HashMap<String, String> resourceResponse = (HashMap) resource.getIn().getBody();

        original.getIn().setHeader("bsnEntInfo",resourceResponse);

        return original;
    }

}
