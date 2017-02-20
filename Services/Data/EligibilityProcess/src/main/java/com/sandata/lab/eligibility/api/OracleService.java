package com.sandata.lab.eligibility.api;

import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;

public interface OracleService {
    int execute(ConnectionType connectionType, String packageName, String methodName, Object jpubType);
    Object executeGet(ConnectionType connectionType, String packageName, String methodName, String className, String entityId);
}
