package com.sandata.lab.rest.visit.services;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.dl.model.VisitTaskList;
import com.sandata.lab.data.model.dl.model.extended.VisitTaskListExt;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.common.oracle.db.connection.SandataOracleConnection;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.visit.BaseTestSupport;
import com.sandata.lab.rest.visit.impl.OracleDataService;
import com.sandata.lab.security.auth.services.oracle.MetaDataService;
import com.sandata.lab.security.auth.utils.cache.CacheUtil;
import mockit.Mocked;
import oracle.ucp.UniversalConnectionPoolException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoreDataServiceIntegrationTest extends BaseTestSupport {

    private OracleDataService coreDataService;
    private SandataOracleConnection sandataOracleConnection;

    @Mocked
    private ConnectionPoolDataService connectionPoolDataService;

    @Mocked
    private CacheUtil cacheUtil;


    @Before
    public void beforeTest() throws Exception {
    }

    @After
    public void afterTest() throws UniversalConnectionPoolException {
        sandataOracleConnection.stopPool();
    }

    public CoreDataServiceIntegrationTest() throws Exception {


    }


    @Override
    protected void onSetup() throws Exception {


        this.sandataOracleConnection =
                new com.sandata.lab.common.oracle.db.connection.SandataOracleConnection()
                        .DatabaseName("sdbdev1")
                        .ServerName("stxdevdb.sandata.com")
                        .PortNumber(1526)
                        .User("coredata")
                        .Password("z3U0kCdbdN")
                        .Open();

        sandataOracleConnection.startPool();

        coreDataService = new OracleDataService();


        connectionPoolDataService = new ConnectionPoolDataService() {
            @Override
            public Connection getConnection() throws SandataRuntimeException {

                Connection connection = null;

                try {

                    connection = sandataOracleConnection.getConnection();
                } catch (Exception e) {

                }

                return connection;
            }

            @Override
            public Connection getConnection(ConnectionType connectionType) throws SandataRuntimeException {
                Connection connection = null;

                try {

                    connection = sandataOracleConnection.getConnection();
                } catch (Exception e) {

                }

                return connection;
            }

            @Override
            public void close(Connection conn) throws SandataRuntimeException {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        this.coreDataService.setConnectionPoolDataService(connectionPoolDataService);

    }

    @Test
    public void getVisitTasks() throws Exception {

        List<VisitTaskListExt> visitTaskListExts = coreDataService.getVisitTaskList(BigInteger.valueOf(797328));
        assertTrue(visitTaskListExts.size() > 0);
    }

    @Test
    public void deleteVisitTasks() throws Exception {

        List<Long> visitSks = new ArrayList<>();
        visitSks.add(new Long(6766));
        visitSks.add(new Long(6767));

        coreDataService.deleteVisitTasks(visitSks);
        assertTrue(true);
    }
}
