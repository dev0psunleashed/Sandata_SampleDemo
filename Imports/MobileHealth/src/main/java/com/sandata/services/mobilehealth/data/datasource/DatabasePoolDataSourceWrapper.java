/**
 * 
 */
package com.sandata.services.mobilehealth.data.datasource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;

/**
 * used only for Camel Jdbc component with streamList option to iterate the resultset
 * 
 * @author huyvo
 *
 */
public class DatabasePoolDataSourceWrapper implements DataSource {
    
    private ConnectionPoolDataService connectionPoolDataService;
    
    @Override
    public Connection getConnection() throws SQLException {
        if (connectionPoolDataService != null) {
            return connectionPoolDataService.getConnection();
        } else {
            throw new SandataRuntimeException("The ConnectionPoolDataService is NULL! Cannot get the connection!");
        }
    }

    /**
     * @return the connectionPoolDataService
     */
    public ConnectionPoolDataService getConnectionPoolDataService() {
        return connectionPoolDataService;
    }

    /**
     * @param connectionPoolDataService the connectionPoolDataService to set
     */
    public void setConnectionPoolDataService(ConnectionPoolDataService connectionPoolDataService) {
        this.connectionPoolDataService = connectionPoolDataService;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        throw new SandataRuntimeException("NOT support getLogWriter() function");
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        throw new SandataRuntimeException("NOT support getLoginTimeout() function");
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new SandataRuntimeException("NOT support getParentLogger() function");
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        throw new SandataRuntimeException("NOT support setLogWriter(PrintWriter out) function");
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        throw new SandataRuntimeException("NOT support setLoginTimeout(int seconds) function");
    }

    @Override
    public boolean isWrapperFor(Class<?> arg0) throws SQLException {
        throw new SandataRuntimeException("NOT support isWrapperFor(Class<?> arg0) function");
    }

    @Override
    public <T> T unwrap(Class<T> arg0) throws SQLException {
        throw new SandataRuntimeException("NOT support unwrap(Class<T> arg0) function");
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        throw new SandataRuntimeException("NOT support getConnection(String username, String password) function");
    }

    
}
