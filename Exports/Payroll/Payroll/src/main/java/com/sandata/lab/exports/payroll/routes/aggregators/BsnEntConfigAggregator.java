package com.sandata.lab.exports.payroll.routes.aggregators;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import java.util.HashMap;

public class BsnEntConfigAggregator implements AggregationStrategy {


    public BsnEntConfigAggregator() {
        super();
    }


    public Exchange aggregate(Exchange original, Exchange resource) {

        Object originalBody = original.getIn().getBody();
        boolean resourceResponse = (boolean) resource.getIn().getBody();

        original.getIn().setHeader("processLocationsPayroll" ,resourceResponse);

        return original;
    }

}
