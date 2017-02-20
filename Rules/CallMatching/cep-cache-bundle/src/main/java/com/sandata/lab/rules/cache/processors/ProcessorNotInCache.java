package com.sandata.lab.rules.cache.processors;

import com.sandata.lab.rules.cache.routes.CacheRoute;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.component.cache.CacheConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 7/11/2016
 * Time: 9:20 PM
 */
public class ProcessorNotInCache implements org.apache.camel.Processor {
    Logger logger = LoggerFactory.getLogger(ProcessorNotInCache.class);
    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        String key = (String)in.getHeader(CacheConstants.CACHE_KEY);
        String value = in.getBody(String.class);
        if(value == null) {
            value = "<null>";
        }
        if (key == null) {
            key = "<null>";
            key = (String)in.getHeader(CacheRoute.CALL_MATCHING_KEY);
        }
        logger.info(String.format("Cache returned (%s) for key (%s)", value, key));
        //since call to cache will remove key lets set our own.
        in.setHeader(CacheRoute.CALL_MATCHING_KEY, key);

    }
}
