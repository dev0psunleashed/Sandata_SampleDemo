package com.sandata.lab.imports.payroll.routes.aggregators;

import com.sandata.lab.imports.payroll.model.Check;
import com.sandata.lab.imports.payroll.model.Earning;
import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.PropertyInject;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckBatchAggregator implements AggregationStrategy {


    public CheckBatchAggregator() {
        super();
    }

    //Compile list of TimeSheetSummary IDS from imported checks
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

        Message newMessage = newExchange.getIn();
        Check newBody = (Check) newExchange.getIn().getBody();
        List list = null;
        String header = null;

        if (oldExchange == null) {

            list = new ArrayList();

            compileList(list, header, newMessage);

            newExchange.getIn().setHeader("staffSSN", newBody.getStaffSSN());
            newExchange.getIn().setBody(list);

            return newExchange;

        } else {

            Message in = oldExchange.getIn();
            list = in.getBody(ArrayList.class);
            compileList(list, header, newMessage);
            return oldExchange;
        }
    }

    private void compileList(List<Check> list, String header, Message message) {

        Check check = (Check) message.getBody();

        if (check != null) {

            list.add(check);

        }

    }

}
