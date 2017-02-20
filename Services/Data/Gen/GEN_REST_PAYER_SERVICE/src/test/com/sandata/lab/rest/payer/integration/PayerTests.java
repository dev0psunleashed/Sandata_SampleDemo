package com.sandata.lab.rest.payer.integration;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.payer.impl.OracleDataService;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Date: 9/17/15
 * Time: 1:27 PM
 */

public class PayerTests extends BaseIntegrationTest {

    private OracleDataService oracleDataService;

    @Mocked
    private ConnectionPoolDataService connectionPoolDataService;

    public PayerTests() throws SQLException {
        super();
    }

    @Test
    public void should_find_a_payer_record_for_a_give_business_entity() throws Exception  {

        Response response = oracleDataService.getPayerByBsnEnt("1", 1, 10);

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getData());
        Assert.assertTrue(response.getTotalRows() > 0);
    }

    @Override
    protected void onSetup() {
        connectionPoolDataService = new ConnectionPoolDataService() {
            @Override
            public Connection getConnection() throws SandataRuntimeException {

                Connection connection = null;

                try {

                    connection = getSandataOracleConnection().getConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return connection;
            }

            @Override
            public Connection getConnection(ConnectionType connectionType) throws SandataRuntimeException {
                Connection connection = null;

                try {

                    connection = getSandataOracleConnection().getConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return connection;
            }
        };

        oracleDataService = new OracleDataService();
        oracleDataService.setConnectionPoolDataService(connectionPoolDataService);
    }
}
