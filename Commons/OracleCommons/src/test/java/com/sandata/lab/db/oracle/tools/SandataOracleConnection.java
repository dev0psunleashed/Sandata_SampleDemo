/*
 * Copyright (c) 2015. Sandata Technologies, LLC
 * 26 Harbor Park Drive, Port Washington, NY 11050, 800-544-7263
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Sandata Technologies, LLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with
 * Sandata.
 */

package com.sandata.lab.db.oracle.tools;

import com.sandata.lab.db.oracle.common.util.StringUtil;
import oracle.ucp.UniversalConnectionPoolAdapter;
import oracle.ucp.UniversalConnectionPoolException;
import oracle.ucp.admin.UniversalConnectionPoolManager;
import oracle.ucp.admin.UniversalConnectionPoolManagerImpl;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Date: 8/16/15
 * Time: 12:01 AM
 */

public class SandataOracleConnection {

    private String databaseName;
    private String serverName;
    private int portNumber = 1526;
    private String user;
    private String password;

    private PoolDataSource poolDataSource;

    private UniversalConnectionPoolManager manager;

    public void startPool() throws UniversalConnectionPoolException {
        this.manager.startConnectionPool("JDBC_UCP_SANDATA_POOL");
    }

    public void stopPool() throws UniversalConnectionPoolException {
        this.manager.destroyConnectionPool("JDBC_UCP_SANDATA_POOL");
    }

    public synchronized Connection getConnection() throws SQLException {
        //Get a database connection from the datasource.
        return this.poolDataSource.getConnection();
    }

    public SandataOracleConnection DatabaseName(final String databaseName) {
        this.databaseName = databaseName;
        return this;
    }

    public SandataOracleConnection ServerName(final String serverName) {
        this.serverName = serverName;
        return this;
    }

    public SandataOracleConnection PortNumber(final int portNumber) {
        this.portNumber = portNumber;
        return this;
    }

    public SandataOracleConnection User(final String user) {
        this.user = user;
        return this;
    }

    public SandataOracleConnection Password(final String password) {
        this.password = password;
        return this;
    }

    public SandataOracleConnection Open() throws SQLException {

        if (StringUtil.IsNullOrEmpty(databaseName)) {
            throw new SQLException("SandataOracleConnection.java: databaseName is NULL or EMPTY");
        }

        if (StringUtil.IsNullOrEmpty(serverName)) {
            throw new SQLException("SandataOracleConnection.java: serverName is NULL or EMPTY");
        }

        if (StringUtil.IsNullOrEmpty(user)) {
            throw new SQLException("SandataOracleConnection.java: user is NULL or EMPTY");
        }

        if (StringUtil.IsNullOrEmpty(password)) {
            throw new SQLException("SandataOracleConnection.java: password is NULL or EMPTY");
        }

        try {
            this.manager =
                    UniversalConnectionPoolManagerImpl. getUniversalConnectionPoolManager();

            //Create pool-enabled data source instance.
            this.poolDataSource = PoolDataSourceFactory.getPoolDataSource();

            //set the connection properties on the data source.
            this.poolDataSource.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
            this.poolDataSource.setURL(String.format("jdbc:oracle:thin:@//%s:%d/%s",
                    this.serverName, this.portNumber, this.databaseName));
            this.poolDataSource.setUser(this.user);
            this.poolDataSource.setPassword(this.password);

            this.poolDataSource.setConnectionPoolName("JDBC_UCP_SANDATA_POOL");
            //Override any pool properties.
            this.poolDataSource.setInitialPoolSize(5);
            this.poolDataSource.setMinPoolSize(5);
            this.poolDataSource.setMaxPoolSize(20);
            this.poolDataSource.setInactiveConnectionTimeout(60); // in seconds

            this.manager.createConnectionPool((UniversalConnectionPoolAdapter) this.poolDataSource);

            return this;
        }
        catch (Exception e) {
            throw new SQLException("SandataOracleConnection.java: " +
                        e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
