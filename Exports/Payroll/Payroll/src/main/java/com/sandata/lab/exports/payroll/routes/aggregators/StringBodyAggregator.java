package com.sandata.lab.exports.payroll.routes.aggregators;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class StringBodyAggregator implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

        if (oldExchange == null) {

            newExchange.getIn().setBody(new StringBuilder(newExchange.getIn().getBody(String.class)));

            return newExchange;
        }

        oldExchange.getIn().getBody(StringBuilder.class).append(newExchange.getIn().getBody(String.class));
        return oldExchange;
    }
}