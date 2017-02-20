package __GROUP_ID__.integration;

import com.sandata.lab.common.oracle.db.connection.SandataOracleConnection;
import __GROUP_ID__.BaseTestSupport;
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

    @Before
    public void beforeTest() throws UniversalConnectionPoolException {

        sandataOracleConnection.startPool();
    }

    @After
    public void afterTest() throws UniversalConnectionPoolException {
        sandataOracleConnection.stopPool();
    }

    protected BaseIntegrationTest() throws SQLException {

         this.sandataOracleConnection =
                new SandataOracleConnection()
                        //.DatabaseName("sdbdev1")
                        .DatabaseName("sdbdev2")
                        .ServerName("stxdevdb.sandata.com")
                        .PortNumber(1526)
                        .User("coredata")
                        //.Password("z3U0kCdbdN")
                        .Password("Z4fEIRn7D2")
                        .Open();
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
