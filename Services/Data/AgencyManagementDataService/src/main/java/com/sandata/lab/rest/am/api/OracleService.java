package com.sandata.lab.rest.am.api;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;

import java.math.BigInteger;
import java.sql.Connection;

public interface OracleService {

    long execute(ConnectionType connectionType, String packageName, String methodName, Object jpubType) throws SandataRuntimeException;

    long execute(ConnectionType connectionType, String packageName, String methodName, long sequenceKey) throws SandataRuntimeException;

    long execute(Connection connection, ConnectionType connectionType, String packageName, String methodName, long sequenceKey) throws SandataRuntimeException;

    long execute(Connection connection, ConnectionType connectionType, String packageName, String methodName, Object jpubType) throws SandataRuntimeException;

    Object executeGet(ConnectionType connectionType, String packageName, String methodName, String className, long sequenceKey) throws SandataRuntimeException;

    Object executeGet(ConnectionType connectionType, String packageName, String methodName, String className, String entityId) throws SandataRuntimeException;

    Object executeGet(ConnectionType connectionType, String packageName, String methodName, String className, Object... params) throws SandataRuntimeException;
}
