package com.sandata.lab.auth.services;

import com.sandata.lab.auth.BaseTestSupport;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.dl.model.ApplicationModule;
import com.sandata.lab.data.model.dl.model.extended.ApplicationUserExt;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.common.oracle.db.connection.SandataOracleConnection;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.security.auth.model.jpub.TmpAppModPrivTab;
import com.sandata.lab.security.auth.model.jpub.TmpAppModPrivTyp;
import com.sandata.lab.security.auth.model.jpub.TmpAppRoleTab;
import com.sandata.lab.security.auth.model.jpub.TmpAppRoleTyp;
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
import java.util.List;

public class MetaDataServiceIntegrationTest extends BaseTestSupport{

    private MetaDataService metaDataService;
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

    public MetaDataServiceIntegrationTest() throws Exception {


    }




    @Override
    protected void onSetup() throws Exception {


        this.sandataOracleConnection =
                new com.sandata.lab.common.oracle.db.connection.SandataOracleConnection()
                        .DatabaseName("sdbdev1")
                        .ServerName("stxdevdb.sandata.com")
                        .PortNumber(1526)
                        .User("metadata")
                        .Password("KjmSX5RE8O")
                        .Open();

        sandataOracleConnection.startPool();

        metaDataService = new MetaDataService();


        connectionPoolDataService = new ConnectionPoolDataService() {
            @Override
            public Connection getConnection() throws SandataRuntimeException {

                Connection connection = null;

                try {

                    connection = sandataOracleConnection.getConnection();
                }catch (Exception e)
                {

                }

                return connection;
            }

            @Override
            public Connection getConnection(ConnectionType connectionType) throws SandataRuntimeException {
                Connection connection = null;

                try {

                    connection = sandataOracleConnection.getConnection();
                }catch (Exception e)
                {

                }

                return connection;
            }

            @Override
            public void close(Connection conn) throws SandataRuntimeException {
                if(conn != null){
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        this.metaDataService.setConnectionPoolDataService(connectionPoolDataService);
        this.metaDataService.setCacheUtil(new CacheUtil());
    }



    @Test
    public void get_tenant_sk_by_be_id() {

       String result = metaDataService.getTenantSKByBE("1");

        assertNotNull(result);
    }

    @Test
    public void insert_role() throws Exception {

        TmpAppModPrivTyp tmpAppModPrivTyp = new TmpAppModPrivTyp(BigDecimal.valueOf(1), BigDecimal.valueOf(1),"Patient Intake",BigDecimal.valueOf(1),BigDecimal.valueOf(1), null);
        TmpAppModPrivTab tmpAppModPrivTab = new TmpAppModPrivTab();

        TmpAppModPrivTyp[] tmpAppModPrivTyps = new TmpAppModPrivTyp[]{tmpAppModPrivTyp};

        tmpAppModPrivTab.setArray(tmpAppModPrivTyps);


        TmpAppRoleTyp tmpAppRoleTyp = new TmpAppRoleTyp(null,BigDecimal.valueOf(1),"TestRoleTestJune",BigDecimal.valueOf(0),tmpAppModPrivTab, null);


         metaDataService.insertUpdateRole(tmpAppRoleTyp);
    }

    @Test
    public void get_role() throws Exception {

        BigInteger bigInteger = BigInteger.valueOf(3);

        TmpAppRoleTyp tmpAppRoleTyp = metaDataService.getRole(bigInteger);
        assertNotNull(tmpAppRoleTyp);
    }

    @Test
    public void testGetApplicationModules() {
        List<ApplicationModule> applicationModuleList = metaDataService.getApplicationModules();
        assertNotNull(applicationModuleList);
    }


    @Test
    public void get_roles() throws Exception {

        TmpAppRoleTab tmpAppRoleTab = metaDataService.getRoles("1");
        assertNotNull(tmpAppRoleTab);
    }

    @Test
    public void get_secure_groups() throws Exception {

        List applicationSecureGroups = metaDataService.getApplicationSecureGroups();
        assertNotNull(applicationSecureGroups);
    }

    @Test
    public void get_user() throws Exception {
        ApplicationUserExt applicationUserExt = metaDataService.getApplicationUserByUserName("sysadmin",new BigDecimal(1));
        assertNotNull(applicationUserExt);
    }

    @Test
    public void delete_user_from_tenant() throws Exception {

        long result = metaDataService.deleteUserUserAppRolesForTenant(new BigDecimal(2),new BigDecimal(1));
        assertEquals(result, 1);
    }
}
