package com.sandata.lab.rules.vv.imports.data.impl;

import com.sandata.lab.redis.services.SandataRedisService;
import com.sandata.lab.rules.vv.imports.data.transformers.CustomGSONProvider;
import com.sandata.lab.rules.vv.imports.model.EVVCall;
import com.sandata.lab.rules.vv.log.utils.LoggingUtils;
import com.sandata.up.commons.exception.SandataRuntimeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by khangle on 09/22/2016.
 */
public class EVVRedisCallStore extends AbstractEVVCallStore {

    private static final Logger LOG = LoggerFactory.getLogger(EVVRedisCallStore.class);

    private SandataRedisService sandataRedisService;

    @Override
    public void storeCalls(List<EVVCall> calls) {
        try {
            for (EVVCall call : calls) {
                String key = getKey(call);
                //put call as string in JSON format
                String callInfo = CustomGSONProvider.ToJson(call);
                sandataRedisService.put(key, callInfo);

            }
        } catch (Exception e) {
        	
        	String msg = LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_IMPORT_KEYWORD
    				, this.getClass(), "storeCalls", "ERROR while storing calls");
        	 LOG.error(msg);
             throw new SandataRuntimeException(msg, e);
        }
    }

    @Override
    public EVVCall getCallFromStore(EVVCall evvCall) {
        try {
            String key = getKey(evvCall);
            String jsonCallInfo = (String)sandataRedisService.get(key);
            return (EVVCall) CustomGSONProvider.FromJson(jsonCallInfo,EVVCall.class);
            //return (EVVCall) sandataRedisService.get(key);

        } catch (Exception e) {
            LOG.error("[ImportCalls] - [EVVRedisCallStore.getCallFromStore] : ERROR while getting calls - Message: {}", e.getMessage(), e);
        }

        return null;
    }

    @Override
    public void deleteCallFromStore(EVVCall evvCall) {
        try {
            String key = getKey(evvCall);
            sandataRedisService.delete(key);

        } catch (Exception e) {
            String msg = LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_IMPORT_KEYWORD
    				, this.getClass(), "deleteCallFromStore", " ERROR while deleting calls");
            LOG.error(msg);
            throw new SandataRuntimeException(msg, e);

        }
    }

    @Override
    public List<EVVCall> getUnprocessedCalls() {
        List<EVVCall> unprocessedCalls = new ArrayList<>();
        try {
            // Get all keys that used to store EVVCall
            Set<String> keySet = sandataRedisService.keys(EVV_CALL_STORE_KEY_PREFIX + "*");
            String keyPrefix = sandataRedisService.getKeyPrefix() + ".";
            for (String key : keySet) {
                key = key.substring(keyPrefix.length(), key.length());
                String jsonCallInfo = (String)sandataRedisService.get(key);
                EVVCall evvCall = (EVVCall) CustomGSONProvider.FromJson(jsonCallInfo,EVVCall.class);
                if (evvCall != null) {
                    unprocessedCalls.add(evvCall);
                }
            }

        } catch (Exception e) {
            
            String msg = LoggingUtils.getErrorLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_IMPORT_KEYWORD
    				, this.getClass(), "getUnprocessedCalls", "ERROR while getting all calls from REDIS");
            LOG.error(msg);
            throw new SandataRuntimeException(msg, e);
        }

        return unprocessedCalls;
    }

    public SandataRedisService getSandataRedisService() {
        return sandataRedisService;
    }

    public void setSandataRedisService(SandataRedisService sandataRedisService) {
        this.sandataRedisService = sandataRedisService;
    }
}
