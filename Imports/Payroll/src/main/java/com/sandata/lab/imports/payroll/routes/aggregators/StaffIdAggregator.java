package com.sandata.lab.imports.payroll.routes.aggregators;

import com.sandata.lab.data.model.dl.model.BusinessEntityIDCrosswalk;
import com.sandata.lab.data.model.dl.model.Staff;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class StaffIdAggregator implements AggregationStrategy {


        public StaffIdAggregator() {
            super();
        }

        //Compile list of TimeSheetSummary IDS from imported checks
        public Exchange aggregate(Exchange original, Exchange resource) {

            Object originalBody = original.getIn().getBody();
            String resourceResponse = (String) resource.getIn().getBody();

            original.getIn().setHeader("staffID", resourceResponse);
            original.getIn().setBody(originalBody);

            return original;
        }

    }
