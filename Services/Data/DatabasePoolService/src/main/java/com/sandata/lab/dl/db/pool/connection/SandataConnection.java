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

package com.sandata.lab.dl.db.pool.connection;

import com.sandata.lab.dl.db.pool.model.SandataOracleDataSource;

/**
 * Abstract class that defines the standard connection methods that can be utilized across all connection types.
 * <p/>
 *
 * @author David Rutgos
 */
public abstract class SandataConnection {

    public SandataConnection DatabaseName(final String databaseName, final SandataOracleDataSource dataSource) {
        dataSource.setDatabaseName(databaseName);
        return this;
    }

    public SandataConnection ServerName(final String serverName, final SandataOracleDataSource dataSource) {
        dataSource.setServerName(serverName);
        return this;
    }

    public SandataConnection PortNumber(final int portNumber, final SandataOracleDataSource dataSource) {
        dataSource.setPortNumber(portNumber);
        return this;
    }

    public SandataConnection User(final String user, final SandataOracleDataSource dataSource) {
        dataSource.setUser(user);
        return this;
    }

    public SandataConnection Password(final String password, final SandataOracleDataSource dataSource) {
        dataSource.setPassword(password);
        return this;
    }

    public SandataConnection InitialPoolSize(final int initialPoolSize, final SandataOracleDataSource dataSource) {
        dataSource.setInitialPoolSize(initialPoolSize);
        return this;
    }

    public SandataConnection MinPoolSize(final int minPoolSize, final SandataOracleDataSource dataSource) {
        dataSource.setMinPoolSize(minPoolSize);
        return this;
    }

    public SandataConnection MaxPoolSize(final int maxPoolSize, final SandataOracleDataSource dataSource) {
        dataSource.setMaxPoolSize(maxPoolSize);
        return this;
    }

    public SandataConnection InactiveTimeoutSeconds(final int inactiveTimeoutSeconds, final SandataOracleDataSource dataSource) {
        dataSource.setInactiveTimeoutSeconds(inactiveTimeoutSeconds);
        return this;
    }
}
