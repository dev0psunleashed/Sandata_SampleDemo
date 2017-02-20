package com.sandata.lab.eligibility.data;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;

/**
 * An abstract adapter is used to get connection from {@link ConnectionPoolDataService}
 * for Camel JDBC component
 */
public abstract class AbstractOracleDataSourceAdapter implements DataSource {

    private ConnectionPoolDataService connectionPoolDataService;

    /**
     * Initializes an instance of {@link AbstractOracleDataSourceAdapter}
     * 
     * @param connectionPoolDataService
     *            an instance of {@link ConnectionPoolDataService}
     */
    public AbstractOracleDataSourceAdapter(ConnectionPoolDataService connectionPoolDataService) {
        if (connectionPoolDataService == null) {
            throw new IllegalArgumentException("connectionPoolDataService must not be null.");
        }
        this.connectionPoolDataService = connectionPoolDataService;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        throw new UnsupportedOperationException("getLogWriter()");
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        throw new UnsupportedOperationException("getLoginTimeout()");
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return Logger.getLogger(AbstractOracleDataSourceAdapter.class.getName());
    }

    @Override
    public void setLogWriter(PrintWriter arg0) throws SQLException {
        throw new UnsupportedOperationException("setLogWriter(PrintWriter arg0)");
    }

    @Override
    public void setLoginTimeout(int arg0) throws SQLException {
        throw new UnsupportedOperationException("setLoginTimeout(int arg0)");
    }

    @Override
    public boolean isWrapperFor(Class<?> arg0) throws SQLException {
        throw new UnsupportedOperationException("isWrapperFor(Class<?> arg0)");
    }

    @Override
    public <T> T unwrap(Class<T> arg0) throws SQLException {
        throw new UnsupportedOperationException("unwrap(Class<T> arg0)");
    }

    @Override
    public Connection getConnection() throws SQLException {
        return connectionPoolDataService.getConnection(getConnectionType());
    }

    @Override
    public Connection getConnection(String arg0, String arg1) throws SQLException {
        throw new UnsupportedOperationException("getConnection(String arg0, String arg1)");
    }

    protected abstract ConnectionType getConnectionType();
}
