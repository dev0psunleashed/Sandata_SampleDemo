package com.sandata.one.aggregator.lookup.api;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;

public interface OracleService {

    Object getLookup(String methodName, String className) throws SandataRuntimeException;
    Object getLookupWithBsnEntId(String methodName, String className, String bsnEntId) throws SandataRuntimeException;
}
