package com.sandata.lab.rest.oracle.api;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;

import java.sql.Connection;
import java.sql.SQLException;

public interface OracleService {

    int execute(String packageName, String methodName, Object jpubType) throws SandataRuntimeException;
    int execute(String packageName, String methodName, int sequenceKey) throws SandataRuntimeException;
    int execute(Connection connection, String packageName, String methodName, Object jpubType) throws SQLException;
    Object executeGet(String packageName, String methodName, String className, int sequenceKey) throws SandataRuntimeException;
    Object executeGet(String packageName, String methodName, String className, String... primaryKeys) throws SandataRuntimeException;
    Object executeGet(String packageName, String methodName, String className, Object... paramaters) throws SandataRuntimeException;
}
