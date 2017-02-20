package com.sandata.lab.rules.call.matching.transform.test;

import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import org.apache.commons.dbcp.BasicDataSource;

public class SantraxOracleDataSource extends BasicDataSource {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(
            SantraxOracleDataSource.class.getName());

    private static final String ORACLE_DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";
    private static final String ORACLE_URL = "jdbc:oracle:thin:@%s:%s:%s";

    public SantraxOracleDataSource() {
        setDriverClassName(ORACLE_DRIVER_CLASS);
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
    public SantraxOracleDataSource(
            final String username,
            final String password,
            final String server,
            final String port,
            final String sid,
            final Integer initialSize,
            final Integer maxActive,
            final Integer maxIdle) {

        setDriverClassName(ORACLE_DRIVER_CLASS);
        setUrl(String.format(ORACLE_URL, server, port, sid));
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
        return LOGGER;
    }
}
