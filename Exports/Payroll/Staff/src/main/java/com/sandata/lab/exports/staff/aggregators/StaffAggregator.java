package com.sandata.lab.exports.staff.aggregators;

import com.sandata.lab.data.model.dl.model.Staff;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import java.util.ArrayList;

public class StaffAggregator implements AggregationStrategy {


        public StaffAggregator() {
            super();
        }

        //Compile list of TimeSheetSummary IDS from imported checks
        public Exchange aggregate(Exchange original, Exchange resource) {

            Object originalBody = original.getIn().getBody();
            ArrayList<Staff> resourceResponse = (ArrayList<Staff>) resource.getIn().getBody();

            original.getIn().setBody(resourceResponse);

            return original;
        }

    }
