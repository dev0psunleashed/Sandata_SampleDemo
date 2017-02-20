package com.sandata.lab.rules.cache.processors;

import com.google.gson.Gson;
import com.sandata.lab.rules.call.model.CallPreferences;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.component.cache.CacheConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 7/5/2016
 * Time: 1:31 PM
 */
public class ProcessCacheObjects implements Processor {
    Logger logger = LoggerFactory.getLogger(ProcessCacheObjects.class);
    Logger cacheLog = LoggerFactory.getLogger("cacheLog");
    @Override
    public void process(Exchange exchange) throws Exception {
        Message in;
        in = exchange.getIn();
        Date d = new Date();
        if(in.getBody() instanceof CallPreferences)
        {
            CallPreferences callPreferences = in.getBody(CallPreferences.class);
            String be_id =  callPreferences.getBe_id();
            in.setHeader(CacheConstants.CACHE_KEY, be_id);
            in.setHeader(CacheConstants.CACHE_OPERATION, CacheConstants.CACHE_OPERATION_ADD);
            in.setBody(callPreferences);

            logger.info(String.format("DATE=%s : Setting callPreference from meta data for business entity (%s).", d.toString(), be_id));
            cacheLog.info(String.format("DATE=%s : Setting callPreference from meta data for business entity (%s).", d.toString(), be_id));
        }
        else if(in.getBody() instanceof String)
        {
            String str = in.getBody(String.class);
            if(str != null && str.contains("minVisitDurationMS")) {
                Gson gson = new Gson();
                CallPreferences callPreferences = gson.fromJson(str, CallPreferences.class);
                String be_id = callPreferences.getBe_id();
                in.setHeader(CacheConstants.CACHE_KEY, be_id);
                in.setHeader(CacheConstants.CACHE_OPERATION, CacheConstants.CACHE_OPERATION_ADD);
                in.setBody(callPreferences);
                cacheLog.info(String.format("DATE=%s, Setting callPreference from meta data for business entity (%s).", d.toString(), be_id));
            }
            logger.info(String.format("String returned in body was not a CallPreferences object and contianed::(%s).", str));
        }
        else if (exchange.getIn().getBody() != null) {
                exchange.getIn().setBody(null);
                logger.error(String.format("ALERT DATE=%s , UNEXPECTED TYPE RETURNED FROM QRY FOR BUSINESS ENTITY CALL PREFERENCES.", d.toString()));
                cacheLog.info(String.format("ALERT DATE=%s , UNEXPECTED TYPE RETURNED FROM QRY FOR BUSINESS ENTITY CALL PREFERENCES.", d.toString()));

        }
        else {
            logger.error("NULL RETURNED FROM QRY FOR BUSINESS ENTITY CALL PREFERENCES.");
            cacheLog.info("NULL RETURNED FROM QRY FOR BUSINESS ENTITY CALL PREFERENCES.");
        }
    }

}
