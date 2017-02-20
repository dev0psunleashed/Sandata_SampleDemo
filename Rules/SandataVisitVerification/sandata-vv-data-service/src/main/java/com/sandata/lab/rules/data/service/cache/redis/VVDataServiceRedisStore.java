package com.sandata.lab.rules.data.service.cache.redis;

import com.sandata.lab.redis.services.SandataRedisService;
import com.sandata.lab.rules.data.service.cache.AbstractVVDataServiceStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by thanhxle on 10/14/2016.
 */
public class VVDataServiceRedisStore extends AbstractVVDataServiceStore {

    private static final Logger LOG = LoggerFactory.getLogger(VVDataServiceRedisStore.class);

    private SandataRedisService sandataRedisService;

    public SandataRedisService getSandataRedisService() {
        return sandataRedisService;
    }

    public void setSandataRedisService(SandataRedisService sandataRedisService) {
        this.sandataRedisService = sandataRedisService;
    }

    @Override
    public void storeData(String cachedKey, String cachedData) {
        sandataRedisService.put(cachedKey,cachedData);
    }

    @Override
    public String getCachedData(String cachedKey) {
        return ( String ) sandataRedisService.get(cachedKey);
    }

}
