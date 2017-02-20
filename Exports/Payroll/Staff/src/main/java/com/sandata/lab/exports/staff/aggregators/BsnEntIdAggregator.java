package com.sandata.lab.exports.staff.aggregators;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BsnEntIdAggregator implements AggregationStrategy {


    public BsnEntIdAggregator() {
        super();
    }

    //Compile list of TimeSheetSummary IDS from imported checks
    public Exchange aggregate(Exchange original, Exchange resource) {

        Object originalBody = original.getIn().getBody();
        HashMap<String, Object> resourceResponse = (HashMap) resource.getIn().getBody();

        ArrayList<Object> bsnEntInfos = new ArrayList<>();

        for (Map.Entry<String, Object> entry : resourceResponse.entrySet()) {

            bsnEntInfos.add(entry.getValue());
        }

        original.getIn().setBody(bsnEntInfos);

        return original;
    }

}
