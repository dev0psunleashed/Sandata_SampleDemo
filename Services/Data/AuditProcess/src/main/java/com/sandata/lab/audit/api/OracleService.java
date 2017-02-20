package com.sandata.lab.audit.api;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;

import java.sql.Connection;

public interface OracleService {

    int execute(String packageName, String methodName, Object jpubType) throws SandataRuntimeException;
    int execute(String packageName, String methodName, int sequenceKey) throws SandataRuntimeException;
    int execute(Connection connection, String packageName, String methodName, Object jpubType) throws SandataRuntimeException;
    Object executeGet(String packageName, String methodName, String className, int sequenceKey) throws SandataRuntimeException;
    Object executeGet(String packageName, String methodName, String className, Object... params) throws SandataRuntimeException;
}
