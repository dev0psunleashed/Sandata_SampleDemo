/*
 * Copyright (c) 2016. Sandata Technologies, LLC
 * 26 Harbor Park Drive, Port Washington, NY 11050, 800-544-7263
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Sandata Technologies, LLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with
 * Sandata.
 */

package com.sandata.lab.dl.db.pool.impl;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.connection.SandataOracleConnection;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import org.apache.camel.Exchange;

import java.sql.Connection;

/**
 * Implements @ConnectionPoolDataService contract for retrieving @java.sql.Connection instances.
 * <p/>
 *
 * @author David Rutgos
 */
public class SandataConnectionPoolDataService implements ConnectionPoolDataService {

    private SandataOracleConnection sandataOracleConnection;

    @Override
    public Connection getConnection() throws SandataRuntimeException {
        return sandataOracleConnection.getConnection(ConnectionType.COREDATA);
    }

    @Override
    public Connection getConnection(ConnectionType connectionType) throws SandataRuntimeException {
        try {
            return sandataOracleConnection.getConnection(connectionType);

        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("SandataConnectionPoolDataService: getConnection: [ConnectType=%s]: %s: %s: ",
                    connectionType.toString(), e.getClass().getName(), e.getMessage()));
        }
    }

    @Override
    public void close(Connection conn) throws SandataRuntimeException {
        try {
            sandataOracleConnection.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("SandataConnectionPoolDataService: close: %s: %s: ",e.getClass().getName(), e.getMessage()));
        }
    }

    public SandataOracleConnection getSandataOracleConnection() {
        return sandataOracleConnection;
    }

    public void setSandataOracleConnection(SandataOracleConnection sandataOracleConnection) {
        this.sandataOracleConnection = sandataOracleConnection;
    }

    public void getConnection(Exchange exchange) throws SandataRuntimeException {
    }
}
