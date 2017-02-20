package com.sandata.lab.exports.payroll.routes.aggregators;

import com.sandata.lab.data.model.dl.model.BusinessEntity;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import java.util.ArrayList;
import java.util.HashMap;

public class BsnEntAggregator implements AggregationStrategy {


    public BsnEntAggregator() {
        super();
    }

    public Exchange aggregate(Exchange original, Exchange resource) {

         Object originalBody = original.getIn().getBody();
        BusinessEntity resourceResponse = (BusinessEntity) resource.getIn().getBody();

        original.getIn().setHeader("bsnEnt", resourceResponse);

        return original;
    }

}
