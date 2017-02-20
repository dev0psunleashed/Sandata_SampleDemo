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

package com.sandata.lab.dl.db.pool.model;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.base.BaseObject;
import oracle.ucp.UniversalConnectionPool;
import oracle.ucp.UniversalConnectionPoolAdapter;
import oracle.ucp.UniversalConnectionPoolException;
import oracle.ucp.UniversalConnectionPoolLifeCycleState;
import oracle.ucp.admin.UniversalConnectionPoolManager;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

import java.sql.Connection;

/**
 * Models the properties that will be needed in order to instantiate an @oracle.ucp.jdbc.PoolDataSource object.
 * <p/>
 *
 * https://docs.oracle.com/cd/E11882_01/java.112/e12826/oracle/ucp/jdbc/PoolDataSource.html
 * https://docs.oracle.com/cd/E18283_01/java.112/e12265/optimize.htm
 *
 * @author David Rutgos
 */
public class SandataOracleDataSource extends BaseObject implements SandataDataSource {

    private static final long serialVersionUID = 1L;

    private int maxConnectionRetryCount = 5;
    private int retryWaitMs = 1000;

    private String databaseName;
    private String serverName;
    private int portNumber = 1526;
    private String user;
    private String password;

    private int initialPoolSize = 5;
    private int minPoolSize = 5;
    private int maxPoolSize = 20;
    private int maxStatementSize = 20;
    private int inactiveTimeoutSeconds = 60;

    // Optimization / Stale Connections
    // https://docs.oracle.com/cd/E18283_01/java.112/e12265/optimize.htm

    /**
     * This feature is typically used when a firewall exists between the pool tier and the database tier and is setup
     * to block connections based on time restrictions. The blocked connections remain in the pool even though they are
     * unusable. In such scenarios, the connection reuse time is set to a smaller value than the firewall timeout policy.
     */
    private long maxConnectionReuseTime = 0L;   // 0 = Disabled (Seconds)

    /**
     * The maximum connection reuse count allows connections to be gracefully closed and removed from the connection pool
     * after a connection has been borrowed a specific number of times. This property is typically used to periodically
     * recycle connections in order to eliminate issues such as memory leaks.
     */
    private int maxConnectionReuseCount = 100;  // 0 = Disabled

    /**
     * The abandoned connection timeout enables borrowed connections to be reclaimed back into the connection pool after
     * a connection has not been used for a specific amount of time. Abandonment is determined by monitoring calls to the
     * database. This timeout feature helps maximize connection reuse and conserves system resources that are otherwise
     * lost on maintaining borrowed connections that are no longer in use.
     */
    private int abandonedConnectionTimeout = 120;   // 0 = Disabled (Seconds)

    /**
     * The time-to-live connection timeout enables borrowed connections to remain borrowed for a specific amount of time
     * before the connection is reclaimed by the pool. This timeout feature helps maximize connection reuse and helps
     * conserve systems resources that are otherwise lost on maintaining connections longer than their expected usage.
     */
    private int timeToLiveConnectionTimeout = 60;   // 0 = Disabled (Seconds)

    /**
     * The connection wait timeout specifies how long an application request waits to obtain a connection if there are
     * no longer any connections in the pool. A connection pool runs out of connections if all connections in the pool
     * are being used (borrowed) and if the pool size has reached it's maximum connection capacity as specified by the
     * maximum pool size property. The request receives an SQL exception if the timeout value is reached. The application
     * can then retry getting a connection. This timeout feature improves overall application usability by minimizing the
     * amount of time an application is blocked and provides the ability to implement a graceful recovery.
     */
    private int connectionWaitTimeout = 120;    // 0 = Disabled (Seconds)

    /**
     * The inactive connection timeout specifies how long an available connection can remain idle before it is closed
     * and removed from the pool. This timeout property is only applicable to available connections and does not affect
     * borrowed connections. This property helps conserve resources that are otherwise lost on maintaining connections
     * that are no longer being used. The inactive connection timeout (together with the maximum pool size) allows a
     * connection pool to grow and shrink as application load changes.
     */
    private int inactiveConnectionTimeout = 60; // 0 = Disabled (Seconds)

    /**
     * The timeout check interval property controls how frequently the timeout properties (abandoned connection timeout,
     * time-to-live connection timeout, and inactive connection timeout) are enforced. Connections that have timed-out
     * are reclaimed when the timeout check cycle runs. This means that a connection may not actually be reclaimed by
     * the pool at the moment that the connection times-out. The lag time between the connection timeout and actually
     * reclaiming the connection may be considerable depending on the size of the timeout check interval.
     */
    private int timeoutCheckInterval = 30;  // Default = 30 (Seconds)
    //

    private String dataSourceName = "oracle.jdbc.pool.OracleDataSource";

    private String connectionPoolName;

    private UniversalConnectionPoolManager manager;

    private PoolDataSource poolDataSource;

    public void create(UniversalConnectionPoolManager manager) throws SandataRuntimeException {

        try {
            this.manager = manager;

            destroy();

            validate(this);

            poolDataSource = PoolDataSourceFactory.getPoolDataSource();

            // High-Performance Oracle JDBC Programming
            // http://www.oracle.com/technetwork/articles/vasiliev-oracle-jdbc-090470.html
            poolDataSource.setValidateConnectionOnBorrow(true);

            // Set the connection properties on the data source
            poolDataSource.setConnectionFactoryClassName(getDataSourceName());
            poolDataSource.setURL(getURL());
            poolDataSource.setUser(getUser());
            poolDataSource.setPassword(getPassword());
            poolDataSource.setConnectionPoolName(getConnectionPoolName());

            // Override any pool properties
            poolDataSource.setInitialPoolSize(getInitialPoolSize());
            poolDataSource.setMinPoolSize(getMinPoolSize());
            poolDataSource.setMaxPoolSize(getMaxPoolSize());
            poolDataSource.setInactiveConnectionTimeout(getInactiveTimeoutSeconds());
            poolDataSource.setMaxStatements(getMaxStatementSize());

            // Optimization / Stale Connections
            poolDataSource.setMaxConnectionReuseTime(getMaxConnectionReuseTime());
            poolDataSource.setMaxConnectionReuseCount(getMaxConnectionReuseCount());
            poolDataSource.setAbandonedConnectionTimeout(getAbandonedConnectionTimeout());
            poolDataSource.setTimeToLiveConnectionTimeout(getTimeToLiveConnectionTimeout());
            poolDataSource.setConnectionWaitTimeout(getConnectionWaitTimeout());
            poolDataSource.setInactiveConnectionTimeout(getInactiveConnectionTimeout());
            poolDataSource.setTimeoutCheckInterval(getTimeoutCheckInterval());

            start();

        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("SandataOracleDataSource: [POOL_NAME=%s]: create: %s: %s",
                    getConnectionPoolName(), e.getClass().getName(), e.getMessage()));
        }
    }

    public String getURL() {
        return String.format("jdbc:oracle:thin:@//%s:%d/%s", getServerName(), getPortNumber(), getDatabaseName());
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getInitialPoolSize() {
        return initialPoolSize;
    }

    public void setInitialPoolSize(int initialPoolSize) {
        this.initialPoolSize = initialPoolSize;
    }

    public int getMinPoolSize() {
        return minPoolSize;
    }

    public void setMinPoolSize(int minPoolSize) {
        this.minPoolSize = minPoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public int getMaxStatementSize() {
        return maxStatementSize;
    }

    public void setMaxStatementSize(int maxStatementSize) {
        this.maxStatementSize = maxStatementSize;
    }

    public int getInactiveTimeoutSeconds() {
        return inactiveTimeoutSeconds;
    }

    public void setInactiveTimeoutSeconds(int inactiveTimeoutSeconds) {
        this.inactiveTimeoutSeconds = inactiveTimeoutSeconds;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public String getConnectionPoolName() {
        return connectionPoolName;
    }

    public void setConnectionPoolName(String connectionPoolName) {
        this.connectionPoolName = connectionPoolName;
    }

    public int getMaxConnectionRetryCount() {
        return maxConnectionRetryCount;
    }

    public void setMaxConnectionRetryCount(int maxConnectionRetryCount) {
        this.maxConnectionRetryCount = maxConnectionRetryCount;
    }

    public int getRetryWaitMs() {
        return retryWaitMs;
    }

    public void setRetryWaitMs(int retryWaitMs) {
        this.retryWaitMs = retryWaitMs;
    }

    public long getMaxConnectionReuseTime() {
        return maxConnectionReuseTime;
    }

    public void setMaxConnectionReuseTime(long maxConnectionReuseTime) {
        this.maxConnectionReuseTime = maxConnectionReuseTime;
    }

    public int getMaxConnectionReuseCount() {
        return maxConnectionReuseCount;
    }

    public void setMaxConnectionReuseCount(int maxConnectionReuseCount) {
        this.maxConnectionReuseCount = maxConnectionReuseCount;
    }

    public int getAbandonedConnectionTimeout() {
        return abandonedConnectionTimeout;
    }

    public void setAbandonedConnectionTimeout(int abandonedConnectionTimeout) {
        this.abandonedConnectionTimeout = abandonedConnectionTimeout;
    }

    public int getTimeToLiveConnectionTimeout() {
        return timeToLiveConnectionTimeout;
    }

    public void setTimeToLiveConnectionTimeout(int timeToLiveConnectionTimeout) {
        this.timeToLiveConnectionTimeout = timeToLiveConnectionTimeout;
    }

    public int getConnectionWaitTimeout() {
        return connectionWaitTimeout;
    }

    public void setConnectionWaitTimeout(int connectionWaitTimeout) {
        this.connectionWaitTimeout = connectionWaitTimeout;
    }

    public int getInactiveConnectionTimeout() {
        return inactiveConnectionTimeout;
    }

    public void setInactiveConnectionTimeout(int inactiveConnectionTimeout) {
        this.inactiveConnectionTimeout = inactiveConnectionTimeout;
    }

    public int getTimeoutCheckInterval() {
        return timeoutCheckInterval;
    }

    public void setTimeoutCheckInterval(int timeoutCheckInterval) {
        this.timeoutCheckInterval = timeoutCheckInterval;
    }

    private void validate(SandataDataSource dataSource) throws SandataRuntimeException {

        if (dataSource == null) {
            throw new SandataRuntimeException("SandataConnection: validate: dataSource == NULL");
        }

        if (StringUtil.IsNullOrEmpty(dataSource.getDatabaseName())) {
            throw new SandataRuntimeException("SandataConnection: databaseName is NULL or EMPTY");
        }

        if (StringUtil.IsNullOrEmpty(dataSource.getServerName())) {
            throw new SandataRuntimeException("SandataConnection: validate: serverName is NULL or EMPTY");
        }

        if (StringUtil.IsNullOrEmpty(dataSource.getUser())) {
            throw new SandataRuntimeException("SandataConnection: validate: user is NULL or EMPTY");
        }

        if (StringUtil.IsNullOrEmpty(dataSource.getPassword())) {
            throw new SandataRuntimeException("SandataConnection: validate: password is NULL or EMPTY");
        }

        if (StringUtil.IsNullOrEmpty(dataSource.getConnectionPoolName())) {
            throw new SandataRuntimeException("SandataConnection: validate: connectionPoolName is NULL or EMPTY");
        }
    }

    private void createDataPool() throws SandataRuntimeException {

        try {

            UniversalConnectionPool universalConnectionPool = null;
            try {
                universalConnectionPool = manager.getConnectionPool(getConnectionPoolName());

                if (universalConnectionPool == null
                        || universalConnectionPool.getLifeCycleState() == UniversalConnectionPoolLifeCycleState.LIFE_CYCLE_STOPPED
                        || universalConnectionPool.getLifeCycleState() == UniversalConnectionPoolLifeCycleState.LIFE_CYCLE_FAILED) {

                    manager.createConnectionPool((UniversalConnectionPoolAdapter) poolDataSource);
                }

            } catch (UniversalConnectionPoolException ucpe) {
                if (ucpe.getMessage().contains("Universal Connection Pool not found in Universal Connection Pool Manager")) {
                    manager.createConnectionPool((UniversalConnectionPoolAdapter) poolDataSource);

                } else if (ucpe.getMessage().contains("Universal Connection Pool already exists in the Universal Connection Pool Manager")) {
                    manager.destroyConnectionPool(poolDataSource.getConnectionPoolName());
                    manager.createConnectionPool((UniversalConnectionPoolAdapter) poolDataSource);

                } else {
                    throw new UniversalConnectionPoolException(ucpe);
                }
            }

            System.out.println(String.format("SandataOracleDataSource: createDataPool: [POOL_NAME=%s]: [DB_URL:%s]: initConnection with [%s]...",
                    getConnectionPoolName(), getURL(), getUser()));

        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("SandataOracleDataSource: createDataPool: %s: %s",
                    e.getClass().getName(), e.getMessage()));
        }
    }

    private void start() throws SandataRuntimeException {

        try {

            createDataPool();

            UniversalConnectionPool universalConnectionPool = manager.getConnectionPool(getConnectionPoolName());
            if (universalConnectionPool.getLifeCycleState() == UniversalConnectionPoolLifeCycleState.LIFE_CYCLE_STOPPED
                    || universalConnectionPool.getLifeCycleState() == UniversalConnectionPoolLifeCycleState.LIFE_CYCLE_FAILED) {

                manager.startConnectionPool(getConnectionPoolName());
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("SandataOracleDataSource: start: [POOL_NAME=%s]: %s: %s",
                    getConnectionPoolName(), e.getClass().getName(), e.getMessage()));
        }
    }

    public void destroy() {

        try {
            manager.destroyConnectionPool(getConnectionPoolName());

            System.out.println(String.format("SandataOracleDataSource: destroy: [POOL_NAME=%s]: Destroyed ...", getConnectionPoolName()));

        } catch (Exception e) {
            if (e.getMessage().contains("Universal Connection Pool not found in Universal Connection Pool Manager")) {
                // Not a problem, nothing to stop!
                return;
            }

            String errMsg = String.format("SandataOracleDataSource: destroy: [POOL_NAME=%s]: %s: %s: ",
                                    getConnectionPoolName(), e.getClass().getName(), e.getMessage());
            System.out.println(errMsg);
        }
    }

    public synchronized void reset() throws SandataRuntimeException {

        try {

            UniversalConnectionPool universalConnectionPool = manager.getConnectionPool(getConnectionPoolName());
            if (universalConnectionPool.getLifeCycleState() == UniversalConnectionPoolLifeCycleState.LIFE_CYCLE_STARTING) {
                // Pool is already starting
                System.out.println("SandataOracleDataSource: reset: Pool already starting...");
                return;
            }

            create(manager);

            System.out.println(String.format("SandataOracleDataSource: reset: [POOL_NAME=%s]:",
                    getConnectionPoolName()));

        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("%s: resetConnection: %s: [ERROR_MSG=%s: FAILED!]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()));
        }
    }

    public Connection getConnection() throws SandataRuntimeException {
        try {
            return getConnection(0);

        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("%s: getConnection: %s: [ERROR_MSG=%s: FAILED!]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()));
        }
    }

    public Connection getConnection(int retryCount) throws SandataRuntimeException {

        try {

            //Borrowing a connection from the pool
            return poolDataSource.getConnection();

        } catch (Exception e) {

            if (retryCount >= getMaxConnectionRetryCount()) {
                e.printStackTrace();
                throw new SandataRuntimeException(String.format("%s: getConnection: %s: [RETRY_COUNT:%d]: [ERROR_MSG=%s: FAILED!]",
                        getClass().getName(), e.getClass().getName(), retryCount, e.getMessage()));
            }

            System.out.println(String.format("SandataOracleDataSource: getConnection: [RETRY_COUNT:%d]: Retry ...", retryCount));
            try {
                // If an exception is thrown, try to reset the connection
                reset();

                System.out.println(String.format("SandataOracleDataSource: getConnection: [RETRY_COUNT:%d]: [RETRY_WAIT_MS: %s]: Waiting ...", retryCount, getRetryWaitMs()));
                try {
                    Thread.sleep(getRetryWaitMs());
                } catch (InterruptedException ie) {
                    System.out.println(String.format("SandataOracleDataSource: getConnection: InterruptedException: [ERROR_MSG=%s:]", ie.getMessage()));
                }

                // WARNING: Recursive!!!!!
                return getConnection(++retryCount);

            } catch (Exception e2) {
                e2.printStackTrace();
                throw new SandataRuntimeException(String.format("%s: getConnection: INNER EXCEPTION: %s: [RETRY_COUNT:%d]: [ERROR_MSG=%s: FAILED!]",
                        getClass().getName(), e2.getClass().getName(), retryCount, e2.getMessage()));
            }
        }
    }
}
