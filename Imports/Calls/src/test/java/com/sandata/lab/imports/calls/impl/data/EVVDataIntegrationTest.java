package com.sandata.lab.imports.calls.impl.data;

import com.sandata.lab.imports.calls.BaseTestSupport;
import com.sandata.lab.imports.calls.impl.data.transformers.EVVCallTransformer;
import com.sandata.lab.common.oracle.db.connection.SandataOracleConnection;
import oracle.ucp.UniversalConnectionPoolException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Date: 8/13/15
 * Time: 5:59 PM
 */

public class EVVDataIntegrationTest extends BaseTestSupport {

    private SandataOracleConnection sandataOracleConnection;
    private EVVCallTransformer evvCallTransformer;
    private EVVOracleDataService evvOracleDataService;

    @Before
    public void beforeTest() throws UniversalConnectionPoolException {

        sandataOracleConnection.startPool();
    }

    @After
    public void afterTest() throws UniversalConnectionPoolException {
        sandataOracleConnection.stopPool();
    }

    public EVVDataIntegrationTest() throws SQLException {

         this.sandataOracleConnection =
                new SandataOracleConnection()
                        .DatabaseName("stxdev")
                        .ServerName("stxdevdb.sandata.com")
                        .PortNumber(1526)
                        .User("inbox")
                        .Password("santrax")
                        .Open();
    }

    protected Connection openConnection() throws SQLException {

        Assert.assertNotNull(sandataOracleConnection);

        Connection connection = sandataOracleConnection.getConnection();

        Assert.assertNotNull(connection);

        connection.setAutoCommit(true);

        return connection;
    }



    private SandataOracleConnection getSandataOracleConnection() {
        return sandataOracleConnection;
    }

    @Override
    protected void onSetup() throws IOException {
        evvOracleDataService = new EVVOracleDataService();
        evvOracleDataService.setSandataOracleConnection(getSandataOracleConnection());
        evvOracleDataService.setEvvCallTransformer(new EVVCallTransformer());
    }

    @Test
    public void getCallsFromEvv()
    {


        evvOracleDataService.getUnprocessedCalls("250","118",3);
    }

    @Test
    public void mark_call_as_exported()
    {
        evvOracleDataService.setCallsAsExported("35276643");
    }
}
