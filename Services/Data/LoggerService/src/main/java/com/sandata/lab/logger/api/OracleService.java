package com.sandata.lab.logger.api;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;

import java.sql.Connection;

public interface OracleService {

    int execute(ConnectionType connectionType, String packageName, String methodName, Object jpubType) throws SandataRuntimeException;
    int execute(ConnectionType connectionType, String packageName, String methodName, int sequenceKey) throws SandataRuntimeException;
    int execute(Connection connection, ConnectionType connectionType, String packageName, String methodName, Object jpubType) throws SandataRuntimeException;
    Object executeGet(ConnectionType connectionType, String packageName, String methodName, String className, int sequenceKey) throws SandataRuntimeException;
    Object executeGet(ConnectionType connectionType, String packageName, String methodName, String className, Object... params) throws SandataRuntimeException;
}
