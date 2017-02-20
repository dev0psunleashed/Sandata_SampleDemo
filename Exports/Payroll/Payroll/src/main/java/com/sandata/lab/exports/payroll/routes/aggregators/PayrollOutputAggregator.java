package com.sandata.lab.exports.payroll.routes.aggregators;

import com.sandata.lab.data.model.dl.model.extended.PayrollOutputExt;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PayrollOutputAggregator implements AggregationStrategy {


    public PayrollOutputAggregator() {
        super();
    }

    public Exchange aggregate(Exchange original, Exchange resource) {

        Object originalBody = original.getIn().getBody();
        List<PayrollOutputExt> resourceResponse = (List<PayrollOutputExt>) resource.getIn().getBody();

        original.getIn().setBody(resourceResponse);

        return original;
    }

}
