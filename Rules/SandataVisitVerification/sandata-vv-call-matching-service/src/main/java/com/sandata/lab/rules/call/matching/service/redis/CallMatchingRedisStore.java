package com.sandata.lab.rules.call.matching.service.redis;

import com.sandata.lab.redis.services.SandataRedisService;
import com.sandata.lab.rules.call.matching.service.cache.AbstractCallMatchingStore;
import com.sandata.lab.rules.vv.log.utils.LoggingUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by thanhxle on 10/14/2016.
 */
public class CallMatchingRedisStore extends AbstractCallMatchingStore {

    private static final Logger LOG = LoggerFactory.getLogger(CallMatchingRedisStore.class);

    private SandataRedisService sandataRedisService;

    public SandataRedisService getSandataRedisService() {
        return sandataRedisService;
    }

    public void setSandataRedisService(SandataRedisService sandataRedisService) {
        this.sandataRedisService = sandataRedisService;
    }

    @Override
    public void storeCallIdentifier(String callIdentifierKey, String callIdentifierValue) {
        try {
        	
            sandataRedisService.put(callIdentifierKey,callIdentifierValue);
        
    	} catch (Exception ex) {
    		LOG.error(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD
    				, this.getClass(), "storeCallIdentifier", "Unexpected error while setting value to REDIS and just ignore this , exp = {}"),ex);
    		//do nothing because, don't wann to throw excpetion will break the main flow down.
    	}
    }

    @Override
    public String getCallIdentifier(String callIdentifierKey) {

    	try {
    	
    		return ( String ) sandataRedisService.get(callIdentifierKey);
        
    	} catch (Exception ex) {
    		LOG.error(LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD
    				, this.getClass(), "getCallIdentifier", "Unexpected error while getting value from REDIS and just ignore this , exp = {}"),ex);
    		//do nothing because, don't wann to throw excpetion will break the main flow down.
    	}
    	
    	return null;
    }

}
