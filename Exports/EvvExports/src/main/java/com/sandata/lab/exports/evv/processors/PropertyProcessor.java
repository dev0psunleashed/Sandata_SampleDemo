package com.sandata.lab.exports.evv.processors;

import com.sandata.lab.exports.evv.app.AppContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 2/7/2017
 * Time: 10:38 AM
 */
public class PropertyProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        exchange.getIn().setHeader("logResponse", AppContext.getContext().resolvePropertyPlaceholders("{{logResponse}}"));
    }
}
