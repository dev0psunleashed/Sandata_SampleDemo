package com.sandata.lab.imports.payroll.routes.aggregators;

import com.sandata.lab.data.model.dl.model.BusinessEntity;
import com.sandata.lab.data.model.dl.model.BusinessEntityIDCrosswalk;
import com.sandata.lab.imports.payroll.model.Check;
import com.sandata.lab.imports.payroll.model.Earning;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BsnEntIdAggregator implements AggregationStrategy {


    public BsnEntIdAggregator() {
        super();
    }

    //Compile list of TimeSheetSummary IDS from imported checks
    public Exchange aggregate(Exchange original, Exchange resource) {

        Object originalBody = original.getIn().getBody();
        HashMap<String, Object> resourceResponse = (HashMap) resource.getIn().getBody();


        original.getIn().setHeader("bsnEntID", resourceResponse.get("bsnEntID"));
        original.getIn().setHeader("interfaceID", resourceResponse.get("interfaceID"));
        original.getIn().setBody(originalBody);

        return original;
    }

}
