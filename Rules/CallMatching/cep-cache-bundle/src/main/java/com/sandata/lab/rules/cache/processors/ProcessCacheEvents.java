package com.sandata.lab.rules.cache.processors;

import com.sandata.lab.rules.cache.routes.CacheRoute;
import com.sandata.lab.rules.call.model.CallPreferences;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.component.cache.CacheConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 7/7/2016
 * Time: 6:44 AM
 */
public class ProcessCacheEvents implements Processor {
    Logger logger = LoggerFactory.getLogger(ProcessCacheEvents.class);
    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("::processing cache event");
        Message in = exchange.getIn();
        String operation = (String) in.getHeader(CacheConstants.CACHE_OPERATION);
        String key = (String) in.getHeader(CacheConstants.CACHE_KEY);
        if(key == null)
            key = (String) in.getHeader(CacheRoute.CALL_MATCHING_KEY);
        Object body = in.getBody();
        logger.info(String.format("Processing cache event operation (%s) with key (%s)", operation, key));
        if(body instanceof CallPreferences) {
            CallPreferences callPreferences = (CallPreferences) in.getBody(CallPreferences.class);
            logger.info(String.format("CallPreferences Object passed to Cache operation:  <Object> : [%s]",callPreferences.toString()));

        }
        else if(body instanceof String) {
            String string = in.getBody(String.class);
            logger.info(String.format("String passed to Cache operation:  <String> : [%s]",string));

        }
        else if(body != null ) {

            logger.error(String.format("Cache passed unhandled type of (%s)", body.toString()));
        }
        else {
            logger.info(String.format("Cache did not pass object."));
        }


    }
}
