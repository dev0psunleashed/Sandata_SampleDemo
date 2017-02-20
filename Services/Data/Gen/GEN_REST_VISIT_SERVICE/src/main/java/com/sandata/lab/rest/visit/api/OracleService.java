package com.sandata.lab.rest.visit.api;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;

import java.sql.Connection;
import java.sql.SQLException;

public interface OracleService {

    long execute(String packageName, String methodName, Object jpubType) throws SandataRuntimeException;
    long execute(String packageName, String methodName, long sequenceKey) throws SandataRuntimeException;
    long execute(Connection connection, String packageName, String methodName, Object jpubType) throws SandataRuntimeException;
    Object executeGet(String packageName, String methodName, String className, long sequenceKey) throws SandataRuntimeException;
    Object executeGet(String packageName, String methodName, String className, Object... params) throws SandataRuntimeException;
}
