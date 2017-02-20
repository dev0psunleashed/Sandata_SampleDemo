package com.sandata.services.mobilehealth.data.datasource;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * Date: 11/28/13.
 * Time: 8:04 PM.
 */

public class SandataOracleDataSource extends BasicDataSource {
    /**
     * Logger.
     */
    private static Logger logger = Logger.getLogger(
            SandataOracleDataSource.class.getName());

    /**
     * Common DataSources.
     */
    public SandataOracleDataSource() {
        setDriverClassName("oracle.jdbc.driver.OracleDriver");
        setAccessToUnderlyingConnectionAllowed(true);
    }

    /**
     * @param username username
     * @param password password
     * @param server server
     * @param port port
     * @param sid sid
     * @param initialSize initialSize
     * @param maxActive maxActive
     * @param maxIdle maxIdle
     */
    public SandataOracleDataSource(
            final String username,
            final String password,
            final String server,
            final String port,
            final String sid,
            final Integer initialSize,
            final Integer maxActive,
            final Integer maxIdle) {

        setDriverClassName("oracle.jdbc.driver.OracleDriver");
        setUrl(String.format("jdbc:oracle:thin:@%s:%s:%s", server, port, sid));
        setUsername(username);
        setPassword(password);
        setInitialSize(initialSize);
        setMaxActive(maxActive);
        setMaxIdle(maxIdle);
        setAccessToUnderlyingConnectionAllowed(true);
    }

    @Override
    public final Logger getParentLogger()
            throws SQLFeatureNotSupportedException {
        return logger;
    }
}
