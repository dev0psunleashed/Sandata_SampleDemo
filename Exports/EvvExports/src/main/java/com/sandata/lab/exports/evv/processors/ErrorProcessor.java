package com.sandata.lab.exports.evv.processors;

import com.sandata.lab.exports.evv.utils.Log;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 2/6/2017
 * Time: 8:54 AM
 */
public class ErrorProcessor implements Processor {
    Log log = new Log();
    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        //log.getExportExceptions().info(String.format("Exception exchange contained %s", in.getBody().toString()));
        String input = in.getBody(String.class);
        if(input == null || input.isEmpty()) {
            log.getExportExceptions().info("Response was empty or null.  Likely bad account username or incorrect password");
        }
        else {
            log.getExportExceptions().info("Unknown error in SOAP response from transfer service.  Check Logs on transfer-service server");
        }
    }
}
