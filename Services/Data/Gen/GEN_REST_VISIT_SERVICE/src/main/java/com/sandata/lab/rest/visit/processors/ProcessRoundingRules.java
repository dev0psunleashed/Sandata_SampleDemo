package com.sandata.lab.rest.visit.processors;

import com.sandata.lab.data.model.dl.model.Visit;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;


/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 1/27/2017
 * Time: 8:19 AM
 */
public class ProcessRoundingRules implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();

        if(in.getBody() instanceof Long) {
            long visitSk = in.getBody(Long.class);

        }
    }
}
