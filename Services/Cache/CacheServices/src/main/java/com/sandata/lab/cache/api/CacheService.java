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

package com.sandata.lab.cache.api;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.cache.Cache;

/**
 * Cache service interface.
 * <p/>
 *
 * @author David Rutgos
 */
public interface CacheService<T> {

    public String create(Object data) throws SandataRuntimeException;
    public void processing(Object data, String key) throws SandataRuntimeException;
    public void put(T data, String key) throws SandataRuntimeException;
    public Cache get(String key) throws SandataRuntimeException;
    public Object getRequest(String key) throws SandataRuntimeException;
    public Object getResult(String key) throws SandataRuntimeException;
}
