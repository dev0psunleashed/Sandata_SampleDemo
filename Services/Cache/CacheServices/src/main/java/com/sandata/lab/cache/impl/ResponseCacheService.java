/*
 * Copyright (c) 2015. Sandata Technologies, LLC
 * 26 Harbor Park Drive, Port Washington, NY 11050, 800-544-7263
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Sandata Technologies, LLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with
 * Sandata.
 */

package com.sandata.lab.cache.impl;

import com.sandata.lab.cache.api.CacheService;
import com.sandata.lab.cache.data.manager.CacheManager;
import com.sandata.lab.cache.utils.log.CacheServiceLogger;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.cache.Cache;
import com.sandata.lab.data.model.status.Status;

/**
 * Implementation of the CacheService interface for Response objects.
 * <p/>
 *
 * @author David Rutgos
 */
@SuppressWarnings("unchecked")
public class ResponseCacheService implements CacheService<Object> {

    private CacheManager cacheManager;

    @Override
    public String create(Object data) throws SandataRuntimeException {

        SandataLogger logger = CacheServiceLogger.CreateLogger();
        logger.setMethodName("CacheManager::create");

        Cache cache = new Cache();
        cache.setStatus(Status.PENDING);
        cache.setRequest(data);

        String uuid = cacheManager.put(cache);

        logger.info(String.format("CacheManager::create: [%s]", uuid));

        logger.stop();

        return uuid;
    }

    @Override
    public void processing(Object data, String key) throws SandataRuntimeException {

        SandataLogger logger = CacheServiceLogger.CreateLogger();
        logger.setMethodName("CacheManager::processing");

        Cache cache = get(key);

        cache.setStatus(Status.PROCESSING);
        cache.setRequest(data);

        String uuid = cacheManager.put(cache, key);

        logger.info(String.format("CacheManager::processing: [%s]", uuid));

        logger.stop();
    }

    @Override
    public void put(Object data, String key) throws SandataRuntimeException {

        SandataLogger logger = CacheServiceLogger.CreateLogger();
        logger.setMethodName("CacheManager::put");

        Cache cache = get(key);
        cache.setStatus(Status.PROCESSED);
        cache.setResult(data);

        String uuid = cacheManager.put(cache, key);

        logger.info(String.format("CacheManager::processing: [%s]", uuid));

        logger.stop();
    }

    @Override
    public Cache get(String key) throws SandataRuntimeException {

        SandataLogger logger = CacheServiceLogger.CreateLogger();
        logger.setMethodName("CacheManager::get");

        logger.info(String.format("CacheManager::get: [%s]", key));

        Cache cache = (Cache) cacheManager.get(key);

        if (cache == null) {
            throw new SandataRuntimeException(String.format("KEY: [%s]: Not Found!", key));
        }

        logger.stop();

        return cache;
    }

    @Override
    public Object getRequest(String key) throws SandataRuntimeException {

        SandataLogger logger = CacheServiceLogger.CreateLogger();
        logger.setMethodName("CacheManager::getRequest");

        Cache cache = get(key);

        Object data = cache.getRequest();

        logger.stop();

        return data;
    }

    @Override
    public Object getResult(String key) throws SandataRuntimeException {

        SandataLogger logger = CacheServiceLogger.CreateLogger();
        logger.setMethodName("CacheManager::getRequest");

        Cache cache = get(key);

        Object data = cache.getResult();

        logger.stop();

        return data;
    }

    public CacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
}
