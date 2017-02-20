package com.sandata.lab.rest.patient.api;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;

import java.sql.Connection;

public interface OracleService {

    long execute(String packageName, String methodName, Object jpubType) throws SandataRuntimeException;
    long execute(String packageName, String methodName, long sequenceKey) throws SandataRuntimeException;
    long execute(Connection connection, ConnectionType connectionType, String packageName, String methodName, Object jpubType) throws SandataRuntimeException;
    Object executeGet(String packageName, String methodName, String className, long sequenceKey) throws SandataRuntimeException;
    Object executeGet(String packageName, String methodName, String className, Object... params) throws SandataRuntimeException;
}
