package com.sandata.lab.common.oracle.db.connection;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.string.StringUtil;
import oracle.ucp.UniversalConnectionPoolAdapter;
import oracle.ucp.UniversalConnectionPoolException;
import oracle.ucp.admin.UniversalConnectionPoolManager;
import oracle.ucp.admin.UniversalConnectionPoolManagerImpl;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;
import org.apache.camel.CamelContext;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Date: 8/24/15
 * Time: 6:02 AM
 */

public class SandataOracleConnection {

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

    private String connectionPoolName = "JDBC_UCP_SANDATA_POOL";

    private PoolDataSource poolDataSource;

    private UniversalConnectionPoolManager manager;

    public void startPool() throws UniversalConnectionPoolException {
        this.manager.startConnectionPool(getConnectionPoolName());
    }

    public void stopPool() throws UniversalConnectionPoolException {
        this.manager.destroyConnectionPool(getConnectionPoolName());
    }

    public synchronized Connection getConnection() throws SQLException {
        //Get a database connection from the datasource.
        Connection connection = this.poolDataSource.getConnection();

        // Check if the connection is valid, otherwise reset and try again...
        if (! connection.isValid(30000)) {
            resetConnection();
            connection = this.poolDataSource.getConnection();
        }

        return connection;
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

    public SandataOracleConnection InitialPoolSize(final int initialPoolSize) {
        this.initialPoolSize = initialPoolSize;
        return this;
    }

    public SandataOracleConnection MinPoolSize(final int minPoolSize) {
        this.minPoolSize = minPoolSize;
        return this;
    }

    public SandataOracleConnection MaxPoolSize(final int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
        return this;
    }

    public SandataOracleConnection InactiveTimeoutSeconds(final int inactiveTimeoutSeconds) {
        this.inactiveTimeoutSeconds = inactiveTimeoutSeconds;
        return this;
    }

    public void destroyConnection() throws SQLException {

        try {
            stopPool();

        } catch (UniversalConnectionPoolException e) {
            e.printStackTrace();
        }

        System.out.println("SandataOracleConnection: destroyConnection...");
    }

    public void initConnection() throws SQLException {
        Open();

        System.out.println("SandataOracleConnection: initConnection...");

        try {
            startPool();

        } catch (UniversalConnectionPoolException e) {
            e.printStackTrace();
        }
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
                    UniversalConnectionPoolManagerImpl.getUniversalConnectionPoolManager();

            //Create pool-enabled data source instance.
            this.poolDataSource = PoolDataSourceFactory.getPoolDataSource();

            //set the connection properties on the data source.
            this.poolDataSource.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
            this.poolDataSource.setURL(String.format("jdbc:oracle:thin:@//%s:%d/%s",
                    this.serverName, this.portNumber, this.databaseName));
            this.poolDataSource.setUser(this.user);
            this.poolDataSource.setPassword(this.password);

            this.poolDataSource.setConnectionPoolName(getConnectionPoolName());
            //Override any pool properties.
            this.poolDataSource.setInitialPoolSize(getInitialPoolSize());
            this.poolDataSource.setMinPoolSize(getMinPoolSize());
            this.poolDataSource.setMaxPoolSize(getMaxPoolSize());
            this.poolDataSource.setInactiveConnectionTimeout(getInactiveTimeoutSeconds());
            this.poolDataSource.setMaxStatements(getMaxStatementSize());

            this.manager.createConnectionPool((UniversalConnectionPoolAdapter) this.poolDataSource);

            return this;
        }
        catch (Exception e) {
            throw new SQLException("SandataOracleConnection.java: " +
                    e.getClass().getName() + ": " + e.getMessage());
        }
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

    public int getInactiveTimeoutSeconds() {
        return inactiveTimeoutSeconds;
    }

    public void setInactiveTimeoutSeconds(int inactiveTimeoutSeconds) {
        this.inactiveTimeoutSeconds = inactiveTimeoutSeconds;
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

    public String getConnectionPoolName() {
        return connectionPoolName;
    }

    public void setConnectionPoolName(String connectionPoolName) {
        this.connectionPoolName = connectionPoolName;
    }

    public int getMaxStatementSize() {
        return maxStatementSize;
    }

    public void setMaxStatementSize(int maxStatementSize) {
        this.maxStatementSize = maxStatementSize;
    }

    public SandataOracleConnection() {
    }

    public SandataOracleConnection(String connectionPoolName) {

        this.connectionPoolName = connectionPoolName;
    }

    public static SandataOracleConnection getInstance(CamelContext context, String instanceName) {

        if(context != null) {
            SandataOracleConnection instance = (SandataOracleConnection) context.getRegistry().lookupByName(instanceName);
            if (instance != null) {
                return instance;
            }
        }

        return new SandataOracleConnection();
    }

    public static SandataOracleConnection getInstance(CamelContext context) {

        if(context != null) {
            SandataOracleConnection instance = (SandataOracleConnection) context.getRegistry().lookupByName("sandataOracleConnection");
            if (instance != null) {
                return instance;
            }
        }

        return new SandataOracleConnection();
    }

    private void resetConnection() throws SandataRuntimeException {

        try {
            this.stopPool();
            Open();
            this.startPool();

            System.out.println("SandataOracleConnection: resetConnection...");
        }
        catch (Exception e) {

            e.printStackTrace();
            throw new SandataRuntimeException(String.format("%s: %s: %s: resetConnection FAILED!",
                    getClass().getName(), e.getClass().getName(), e.getMessage()));
        }
    }
}
