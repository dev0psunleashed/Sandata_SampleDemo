package com.sandata.lab.dl.db.pool.model;

/**
 * Defines the base contract for a SandataDataSource instance.
 * <p/>
 *
 * @author David Rutgos
 */
public interface SandataDataSource {

    String getURL();
    String getDatabaseName();
    void setDatabaseName(String databaseName);
    String getServerName();
    void setServerName(String serverName);
    int getPortNumber();
    void setPortNumber(int portNumber);
    String getUser();
    void setUser(String user);
    String getPassword();
    void setPassword(String password);
    int getInitialPoolSize();
    void setInitialPoolSize(int initialPoolSize);
    int getMinPoolSize();
    void setMinPoolSize(int minPoolSize);
    int getMaxPoolSize();
    void setMaxPoolSize(int maxPoolSize);
    int getMaxStatementSize();
    void setMaxStatementSize(int maxStatementSize);
    int getInactiveTimeoutSeconds();
    void setInactiveTimeoutSeconds(int inactiveTimeoutSeconds);
    String getDataSourceName();
    void setDataSourceName(String dataSourceName);
    String getConnectionPoolName();
    void setConnectionPoolName(String connectionPoolName);
}
