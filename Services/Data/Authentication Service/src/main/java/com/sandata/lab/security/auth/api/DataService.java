package com.sandata.lab.security.auth.api;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;

import java.math.BigDecimal;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */

public interface DataService {

    long insert(String packageName, String methodName, Object data) throws SandataRuntimeException;
    long update(String packageName, String methodName, Object data) throws SandataRuntimeException;
    long delete(String packageName, String methodName, long sequenceKey) throws SandataRuntimeException;
    void get(String packageName, String methodName, Object data, String className, BigDecimal sequenceKey, String[] params) throws SandataRuntimeException;
}
