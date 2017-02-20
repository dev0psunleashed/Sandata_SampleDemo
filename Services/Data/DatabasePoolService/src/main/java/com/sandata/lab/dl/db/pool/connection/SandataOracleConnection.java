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

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataHandler;
import com.sandata.lab.dl.db.pool.model.SandataOracleDataSource;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.dl.db.pool.utils.log.ConnectionPoolDataServiceLogger;
import oracle.ucp.UniversalConnectionPoolException;
import oracle.ucp.admin.UniversalConnectionPoolManager;
import oracle.ucp.admin.UniversalConnectionPoolManagerImpl;
import org.apache.camel.CamelContext;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Implements a UCP Oracle connection pool for managing multiple Oracle connection.
 * <p/>
 *
 * @author David Rutgos
 */
public class SandataOracleConnection extends SandataConnection implements ConnectionPoolDataHandler {

    private SandataLogger logger = ConnectionPoolDataServiceLogger.CreateLogger();

    private UniversalConnectionPoolManager manager;

    // CORE DATA
    private SandataOracleDataSource coreData;

    // CORE DATA MART
    private SandataOracleDataSource coreDataMart;

    // LOB DATA
    private SandataOracleDataSource lobData;

    // META DATA
    private SandataOracleDataSource metaData;

    public SandataOracleConnection() {

        try {

            manager = UniversalConnectionPoolManagerImpl.getUniversalConnectionPoolManager();

            // Enable JMX
            manager.setJmxEnabled(true);

        } catch (UniversalConnectionPoolException ucpe) {
            ucpe.printStackTrace();
        }
    }

    /**
     * Gets the appropriate connection for the given type.
     * @param connectionType Is the supported connections that can be retrieved from the pool.
     * @return Returns a @java.sql.Connection instance for a known and configured ConnectionType.
     * @throws SandataRuntimeException
     */
    public Connection getConnection(ConnectionType connectionType) throws SandataRuntimeException {

        Connection connection;

        // Get a database connection from the datasource
        switch (connectionType) {

            case METADATA:
                connection = metaData.getConnection();
                break;

            case LOBDATA:
                connection = lobData.getConnection();
                break;

            case COREDATAMART:
                connection = coreDataMart.getConnection();
                break;

            case COREDATA:
            default:
                connection = coreData.getConnection();
        }

        //Set default schema to support dal_user and then close the query statement right after
        try {
            Statement stmtDefaultSchema = connection.createStatement();
            stmtDefaultSchema.execute(String.format("alter session set current_schema=%s", connectionType));
            stmtDefaultSchema.close();
        }catch (SQLException sqlEx){
            throw new SandataRuntimeException(String.format("Cannot set the default schema %s to Connection - Exception %s", connectionType, sqlEx.getMessage()), sqlEx);
        }

        return connection;
    }

    public void close(Connection conn) throws SandataRuntimeException{

        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error(String.format("SandataOracleConnection: close - conn %s", e.getMessage()));
            }
        }
    }

    @Override
    public synchronized void destroyConnection() throws SandataRuntimeException {

        logger.info("destroyConnection: Destroying connections ...");

        try {

            coreData.destroy();

        } catch (Exception e) {
            e.printStackTrace();
            String errMsg = String.format("SandataOracleConnection: destroyConnection: %s: [ERROR_MSG=%s]: [POOL_NAME=%s]",
                    e.getClass().getName(), e.getMessage(), coreData.getConnectionPoolName());
            logger.error(String.format("destroyConnection: %s", errMsg));
        }

        try {

            coreDataMart.destroy();

        } catch (Exception e) {
            e.printStackTrace();
            String errMsg = String.format("SandataOracleConnection: destroyConnection: %s: [ERROR_MSG=%s]: [POOL_NAME=%s]",
                    e.getClass().getName(), e.getMessage(), coreData.getConnectionPoolName());
            logger.error(String.format("destroyConnection: %s", errMsg));
        }

        try {

            lobData.destroy();

        } catch (Exception e) {
            e.printStackTrace();
            String errMsg = String.format("SandataOracleConnection: destroyConnection: %s: [ERROR_MSG=%s]: [POOL_NAME=%s]",
                    e.getClass().getName(), e.getMessage(), lobData.getConnectionPoolName());
            logger.error(String.format("destroyConnection: %s", errMsg));
        }

        try {

            metaData.destroy();

        } catch (Exception e) {
            e.printStackTrace();
            String errMsg = String.format("SandataOracleConnection: destroyConnection: %s: [ERROR_MSG=%s]: [POOL_NAME=%s]",
                    e.getClass().getName(), e.getMessage(), metaData.getConnectionPoolName());
            logger.error(String.format("destroyConnection: %s", errMsg));
        }

        logger.info("destroyConnection: Connections destroyed!");
    }

    public void initConnection() throws SandataRuntimeException {

        // Create pool-enabled data source instance.
        logger.info("initConnection: Initializing connections ...");

        try {
            // SCHEMA: COREDATA
            coreData.create(manager);
        } catch (Exception e) {
            e.printStackTrace();
            String errMsg = String.format("SandataOracleConnection: initConnection: %s: [ERROR_MSG=%s]: [POOL_NAME=%s]",
                    e.getClass().getName(), e.getMessage(), coreData.getConnectionPoolName());
            logger.error(String.format("initConnection: %s", errMsg));
        }

        try {
            // SCHEMA: COREDATA
            coreDataMart.create(manager);
        } catch (Exception e) {
            e.printStackTrace();
            String errMsg = String.format("SandataOracleConnection: initConnection: %s: [ERROR_MSG=%s]: [POOL_NAME=%s]",
                    e.getClass().getName(), e.getMessage(), coreData.getConnectionPoolName());
            logger.error(String.format("initConnection: %s", errMsg));
        }

        try {
            // SCHEMA: LOBDATA
            lobData.create(manager);
        } catch (Exception e) {
            e.printStackTrace();
            String errMsg = String.format("SandataOracleConnection: initConnection: %s: [ERROR_MSG=%s]: [POOL_NAME=%s]",
                    e.getClass().getName(), e.getMessage(), lobData.getConnectionPoolName());
            logger.error(String.format("initConnection: %s", errMsg));
        }

        try {
            // SCHEMA: METADATA
            metaData.create(manager);
        }
        catch (Exception e) {
            e.printStackTrace();
            String errMsg = String.format("SandataOracleConnection: initConnection: %s: [ERROR_MSG=%s]: [POOL_NAME=%s]",
                    e.getClass().getName(), e.getMessage(), metaData.getConnectionPoolName());
            logger.error(String.format("initConnection: %s", errMsg));
        }

        logger.info("initConnection: Connections initialized!");
    }


    public static SandataOracleConnection getInstance(CamelContext context) {

        if(context != null) {
            SandataOracleConnection instance = (SandataOracleConnection) context.getRegistry().lookupByName("sandataOracleConnectionBean");
            if (instance != null) {
                return instance;
            }
        }

        return new SandataOracleConnection();
    }

    public SandataOracleConnection DatabaseName(final String databaseName) {
        coreData.setDatabaseName(databaseName);
        return this;
    }

    public SandataOracleConnection ServerName(final String serverName) {
        coreData.setServerName(serverName);
        return this;
    }

    public SandataOracleConnection PortNumber(final int portNumber) {
        coreData.setPortNumber(portNumber);
        return this;
    }

    public SandataOracleConnection User(final String user) {
        coreData.setUser(user);
        return this;
    }

    public SandataOracleConnection Password(final String password) {
        coreData.setPassword(password);
        return this;
    }

    public SandataOracleConnection InitialPoolSize(final int initialPoolSize) {
        coreData.setInitialPoolSize(initialPoolSize);
        return this;
    }

    public SandataOracleConnection MinPoolSize(final int minPoolSize) {
        coreData.setMinPoolSize(minPoolSize);
        return this;
    }

    public SandataOracleConnection MaxPoolSize(final int maxPoolSize) {
        coreData.setMaxPoolSize(maxPoolSize);
        return this;
    }

    public SandataOracleConnection InactiveTimeoutSeconds(final int inactiveTimeoutSeconds) {
        coreData.setInactiveTimeoutSeconds(inactiveTimeoutSeconds);
        return this;
    }

    public SandataOracleDataSource getCoreData() {
        return coreData;
    }

    public void setCoreData(SandataOracleDataSource coreData) {
        this.coreData = coreData;
    }

    public SandataOracleDataSource getLobData() {
        return lobData;
    }

    public void setLobData(SandataOracleDataSource lobData) {
        this.lobData = lobData;
    }

    public SandataOracleDataSource getMetaData() {
        return metaData;
    }

    public void setMetaData(SandataOracleDataSource metaData) {
        this.metaData = metaData;
    }

    public SandataOracleDataSource getCoreDataMart() {
        return coreDataMart;
    }

    public void setCoreDataMart(SandataOracleDataSource coreDataMart) {
        this.coreDataMart = coreDataMart;
    }
}
