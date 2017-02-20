package com.sandata.lab.rest.staff.integration;

import com.sandata.lab.common.oracle.db.connection.SandataOracleConnection;
import com.sandata.lab.rest.staff.BaseTestSupport;
import oracle.ucp.UniversalConnectionPoolException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Date: 8/13/15
 * Time: 5:59 PM
 */

public class BaseIntegrationTest extends BaseTestSupport {

    private SandataOracleConnection sandataOracleConnection;

    protected enum Server {
        DEV1,
        DEV2,
        DEV3,
        META1,
        META2,
        META3,
        QA,
        DEMO,
        ETL
    }

    protected Server getDbServer() {
        return Server.DEV1; // DEFAULT
    }

    @Before
    public void beforeTest() throws UniversalConnectionPoolException {
        sandataOracleConnection.startPool();
        try {
            afterConnectionPoolStarted();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void afterTest() throws UniversalConnectionPoolException {
        try {
            beforeConnectionPoolStopped();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sandataOracleConnection.stopPool();
    }

    protected void afterConnectionPoolStarted() throws Exception {
    }

    protected void beforeConnectionPoolStopped() throws Exception {
    }

    protected BaseIntegrationTest() throws SQLException {

        switch (getDbServer()) {
            case DEV1:
                this.sandataOracleConnection =
                        new SandataOracleConnection()
                                .DatabaseName("sdbdev1")
                                .ServerName("stxdevdb.sandata.com")
                                .PortNumber(1526)
                                .User("coredata")
                                .Password("z3U0kCdbdN")
                                .Open();
                break;
            case DEV2:
                this.sandataOracleConnection =
                        new SandataOracleConnection()
                                .DatabaseName("sdbdev2")
                                .ServerName("stxdevdb.sandata.com")
                                .PortNumber(1526)
                                .User("coredata")
                                .Password("Z4fEIRn7D2")
                                .Open();
                break;
            case DEV3:
                this.sandataOracleConnection =
                        new SandataOracleConnection()
                                .DatabaseName("sdbdev3")
                                .ServerName("stxdevdb.sandata.com")
                                .PortNumber(1526)
                                .User("coredata")
                                .Password("E0AJOlYeL8")
                                .Open();
                break;
            case META1:
                this.sandataOracleConnection =
                        new SandataOracleConnection()
                                .DatabaseName("sdbdev1")
                                .ServerName("stxdevdb.sandata.com")
                                .PortNumber(1526)
                                .User("metadata")
                                .Password("KjmSX5RE8O")
                                .Open();
                break;
            case META2:
                this.sandataOracleConnection =
                        new SandataOracleConnection()
                                .DatabaseName("sdbdev2")
                                .ServerName("stxdevdb.sandata.com")
                                .PortNumber(1526)
                                .User("metadata")
                                .Password("R63JumYQ6L")
                                .Open();
                break;
            case META3:
                this.sandataOracleConnection =
                        new SandataOracleConnection()
                                .DatabaseName("sdbdev3")
                                .ServerName("stxdevdb.sandata.com")
                                .PortNumber(1526)
                                .User("metadata")
                                .Password("vOo4czfuEH")
                                .Open();
                break;
            case QA:
                this.sandataOracleConnection =
                        new SandataOracleConnection()
                                .DatabaseName("sdbqa1")
                                .ServerName("stxqadb.sandata.com")
                                .PortNumber(1526)
                                .User("coredata")
                                .Password("YO47aJD3tP")
                                .Open();
                break;
            case DEMO:
                this.sandataOracleConnection =
                        new SandataOracleConnection()
                                .DatabaseName("sdbqa3")
                                .ServerName("stxqadb.sandata.com")
                                .PortNumber(1526)
                                .User("coredata")
                                .Password("HaXiYDk12o")
                                .Open();
                break;
            case ETL:
                this.sandataOracleConnection =
                        new SandataOracleConnection()
                                .DatabaseName("sdbqa2")
                                .ServerName("stxqadb.sandata.com")
                                .PortNumber(1526)
                                .User("coredata")
                                .Password("DtLxsN19w9")
                                .Open();
                break;
        }
    }

    protected Connection openConnection() throws SQLException {

        Assert.assertNotNull(sandataOracleConnection);

        Connection connection = sandataOracleConnection.getConnection();

        Assert.assertNotNull(connection);

        connection.setAutoCommit(true);

        return connection;
    }

    protected Object call(final String method, int outputParameterType, Object[] params) throws SQLException {

        Connection connection = null;
        try {

            connection = openConnection();

            CallableStatement callableStatement = connection.prepareCall(method);

            callableStatement.registerOutParameter(1, outputParameterType);

            int index = 2;
            for (Object param : params) {
                callableStatement.setObject(index++, param);
            }

            callableStatement.execute();
            return callableStatement.getObject(1);

        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            }
            catch (Exception e) {}
        }
    }

    protected Object call(final Connection connection, final String method, int outputParameterType, Object[] params) throws SQLException {

        CallableStatement callableStatement = connection.prepareCall(method);

        callableStatement.registerOutParameter(1, outputParameterType);

        int index = 2;
        for (Object param : params) {
            callableStatement.setObject(index++, param);
        }

        callableStatement.execute();
        return callableStatement.getObject(1);

    }

    protected Object call(final Connection connection, final String method, int outputParameterType) throws SQLException {

        CallableStatement callableStatement = connection.prepareCall(method);

        callableStatement.registerOutParameter(1, outputParameterType);

        callableStatement.execute();
        return callableStatement.getObject(1);

    }

    public SandataOracleConnection getSandataOracleConnection() {
        return sandataOracleConnection;
    }

    @Override
    protected void onSetup() throws Exception {
    }
}
