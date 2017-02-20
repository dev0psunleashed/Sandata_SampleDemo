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

package com.sandata.lab.status.service.impl;

import com.sandata.lab.cache.api.CacheService;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.status.service.api.StatusService;
import com.sandata.lab.status.service.utils.log.StatusServiceLogger;
import org.apache.camel.Exchange;
import org.apache.cxf.message.MessageContentsList;

/**
 * REST implementation of the StatusService interface.
 * <p/>
 *
 * @author David Rutgos
 */

public class RestStatusService implements StatusService {

    private CacheService cacheService;

    @Override
    public void getStatus(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = StatusServiceLogger.CreateLogger(exchange);
        logger.setMethodName("getStatus");

        logger.start();

        MessageContentsList params = exchange.getIn().getBody(MessageContentsList.class);

        String guid = (String) params.get(0);
        boolean request = (Boolean) params.get(1);

        if (! request) {
            Object result = cacheService.getResult(guid);
            exchange.getIn().setBody(result);
        }
        else {
            Object result = cacheService.getRequest(guid);
            exchange.getIn().setBody(result);
        }

        logger.stop();
    }

    public CacheService getCacheService() {
        return cacheService;
    }

    public void setCacheService(CacheService cacheService) {
        this.cacheService = cacheService;
    }
}
