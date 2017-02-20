package com.sandata.lab.rest.payer.integration;

import com.sandata.lab.common.oracle.db.connection.SandataOracleConnection;
import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.dl.model.ApplicationTenantKeyConfiguration;
import com.sandata.lab.data.model.payer.PayerAdditionalSettings;
import com.sandata.lab.data.model.payer.PayerContractSettings;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.payer.BaseTestSupport;
import com.sandata.lab.rest.payer.impl.OracleDataService;
import com.sandata.lab.rest.payer.impl.RestDataService;
import mockit.Mocked;
import oracle.ucp.UniversalConnectionPoolException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

/**
 * <p>PayerSettingsTest.java</p>
 * <p>Date: 6/9/2016</p>
 * <p>Time: 4:01:06 PM</p>
 *
 * @author jasonscott
 */
public class PayerSettingsTest extends BaseTestSupport {

    private SandataOracleConnection sandataOracleConnection;
    private ApplicationTenantKeyConfiguration payerAppTenantKeyConfiguration;
    private ApplicationTenantKeyConfiguration payerContractAppTenantKeyConfiguration;
    private RestDataService restDataService;

    @Mocked
    private ConnectionPoolDataService connectionPoolDataService;

    @Before
    public void beforeTest() throws UniversalConnectionPoolException, SQLException {
        this.sandataOracleConnection =
            new SandataOracleConnection()
//                .DatabaseName("sdbdev1")
                .DatabaseName("sdbdev2")
                .ServerName("stxdevdb.sandata.com")
                .PortNumber(1526)
                .User("metadata")
//                .Password("KjmSX5RE8O")
                .Password("R63JumYQ6L")
                .Open();

        sandataOracleConnection.startPool();
    }

    @After
    public void afterTest() throws UniversalConnectionPoolException {
        sandataOracleConnection.stopPool();
    }

    @Override
    protected void onSetup() {
        connectionPoolDataService = new ConnectionPoolDataService() {
            @Override
            public Connection getConnection() throws SandataRuntimeException {

                Connection connection = null;

                try {

                    connection = sandataOracleConnection.getConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return connection;
            }

            @Override
            public Connection getConnection(ConnectionType connectionType) throws SandataRuntimeException {
                Connection connection = null;

                try {

                    connection = sandataOracleConnection.getConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return connection;
            }
        };

        OracleDataService oracleDataService = new OracleDataService();
        oracleDataService.setConnectionPoolDataService(connectionPoolDataService);
        restDataService = new RestDataService();
        restDataService.setOracleDataService(oracleDataService);

        PayerAdditionalSettings payerAdditionalSettings = new PayerAdditionalSettings();
        payerAdditionalSettings.setIncludeAgencyRemitAddress(true);
        payerAdditionalSettings.setUseScheduledTimesForBillingAndPayrollTimes(false);
        payerAdditionalSettings.setRequiresAuthorizationId(false);
        payerAdditionalSettings.setRequiresPhysicianNpiOnInvoice(true);
        payerAdditionalSettings.setSplitInvoicesByServiceOnPaperClaims(false);
        payerAdditionalSettings.setAssignClaimReferenceNumOnInvoice(true);

        payerAppTenantKeyConfiguration = new ApplicationTenantKeyConfiguration();
        Date nowDate = new Date();
        payerAppTenantKeyConfiguration.setRecordCreateTimestamp(nowDate);
        payerAppTenantKeyConfiguration.setRecordUpdateTimestamp(nowDate);
        payerAppTenantKeyConfiguration.setKeyName("PAYER_ADD_SETTINGS");
        payerAppTenantKeyConfiguration.setTenantKeyConfigurationValue(GSONProvider.ToJson(payerAdditionalSettings));

        PayerContractSettings payerContractSettings = new PayerContractSettings();
        payerContractSettings.setIncludeAgencyRemitAddress(true);
        payerContractSettings.setUseScheduledTimesForBillingAndPayrollTimes(false);
        payerContractSettings.setRequiresAuthorizationId(false);
        payerContractSettings.setRequiresPhysicianNpiOnInvoice(true);
        payerContractSettings.setSplitInvoicesByServiceOnPaperClaims(false);
        payerContractSettings.setAssignClaimReferenceNumOnInvoice(true);

        payerContractAppTenantKeyConfiguration = new ApplicationTenantKeyConfiguration();
        payerContractAppTenantKeyConfiguration.setRecordCreateTimestamp(nowDate);
        payerContractAppTenantKeyConfiguration.setRecordUpdateTimestamp(nowDate);
        payerContractAppTenantKeyConfiguration.setKeyName("CONT_ADD_SETTINGS");
        payerContractAppTenantKeyConfiguration.setTenantKeyConfigurationValue(GSONProvider.ToJson(payerContractSettings));
    }

    @Test
    public void printTestEntities() {
        System.out.println(GSONProvider.ToJson(payerAppTenantKeyConfiguration));
        System.out.println(GSONProvider.ToJson(payerContractAppTenantKeyConfiguration));
    }

    @Test
    public void testCreatePayerSettings() {

        exchange.getIn().setHeader("bsn_ent_id", "5");
        exchange.getIn().setHeader("payer_id", "Test_Payer_1");
        exchange.getIn().setHeader("connectionType", ConnectionType.METADATA);
        exchange.getIn().setHeader("CamelHttpMethod", "POST");
        exchange.getIn().setHeader("operationName", "PKG_APP_insertAppTenantKeyConf_ApplicationTenantKeyConfiguration");
        exchange.getIn().setBody(payerAppTenantKeyConfiguration);
        restDataService.createAppTenantKeyConfForPayerOrContract(exchange);
        System.out.println(exchange.getIn().getBody());
    }

    @Test
    public void testCreateContractSettings() {
        exchange.getIn().setHeader("bsn_ent_id", "5");
        exchange.getIn().setHeader("payer_id", "Test_Payer_1");
        exchange.getIn().setHeader("contract_id", "Test_Contract_1");
        exchange.getIn().setHeader("connectionType", ConnectionType.METADATA);
        exchange.getIn().setHeader("CamelHttpMethod", "POST");
        exchange.getIn().setHeader("operationName", "PKG_APP_insertAppTenantKeyConf_ApplicationTenantKeyConfiguration");
        exchange.getIn().setBody(payerContractAppTenantKeyConfiguration);
        restDataService.createAppTenantKeyConfForPayerOrContract(exchange);
        System.out.println(exchange.getIn().getBody());
    }

    @Test
    public void testRetrieveContractSettings() {
        String bsnEntId = "5";
        String payerId = "Test_Payer_1";
        String contractId = "Test_Contract_1";
        String keyName = "CONT_ADD_SETTINGS";

        exchange.getIn().setHeader("bsn_ent_id", bsnEntId);
        exchange.getIn().setHeader("payer_id", payerId);
        exchange.getIn().setHeader("contract_id", contractId);
        exchange.getIn().setHeader("key_name", keyName);

        exchange.getIn().setHeader("CamelHttpMethod", "GET");
        exchange.getIn().setHeader("connectionType", ConnectionType.METADATA);


        restDataService.getAppTenantKeyConfForBePayerContAndKey(exchange);

        ApplicationTenantKeyConfiguration payerContractSettingsAppTenantKeyConf = (ApplicationTenantKeyConfiguration) exchange.getIn().getBody();
        System.out.println(GSONProvider.ToJson(payerContractSettingsAppTenantKeyConf));
    }

    @Test
    public void testRetrievePayerSettings() {
        String bsnEntId = "5";
        String payerId = "Test_Payer_1";
        String keyName = "PAYER_ADD_SETTINGS";

        exchange.getIn().setHeader("bsn_ent_id", bsnEntId);
        exchange.getIn().setHeader("payer_id", payerId);
        exchange.getIn().setHeader("key_name", keyName);

        exchange.getIn().setHeader("CamelHttpMethod", "GET");
        exchange.getIn().setHeader("connectionType", ConnectionType.METADATA);

        restDataService.getAppTenantKeyConfForBePayerContAndKey(exchange);

        ApplicationTenantKeyConfiguration payerSettingsAppTenantKeyConf = (ApplicationTenantKeyConfiguration) exchange.getIn().getBody();

        System.out.println(GSONProvider.ToJson(payerSettingsAppTenantKeyConf));

    }

    @Test
    public void testRetrieveContractSettingsForSk() {
        Integer sequenceKey = 255;
        exchange.getIn().setHeader("sequence_key", sequenceKey);
        exchange.getIn().setHeader("CamelHttpMethod", "GET");
        exchange.getIn().setHeader("operationName", "PKG_APP_getAppTenantKeyConfContract_ApplicationTenantKeyConfiguration");
        exchange.getIn().setHeader("connectionType", ConnectionType.METADATA);

        restDataService.getAppTenantKeyConfForSk(exchange);

        ApplicationTenantKeyConfiguration payerSettingsAppTenantKeyConf = (ApplicationTenantKeyConfiguration) exchange.getIn().getBody();
        System.out.println(GSONProvider.ToJson(payerSettingsAppTenantKeyConf));
    }

    @Test
    public void testRetrievePayerSettingsForSk() {
        Integer sequenceKey = 248;
        exchange.getIn().setHeader("sequence_key", sequenceKey);
        exchange.getIn().setHeader("CamelHttpMethod", "GET");
        exchange.getIn().setHeader("operationName", "PKG_APP_getAppTenantKeyConfPayer_ApplicationTenantKeyConfiguration");
        exchange.getIn().setHeader("connectionType", ConnectionType.METADATA);

        restDataService.getAppTenantKeyConfForSk(exchange);

        ApplicationTenantKeyConfiguration payerSettingsAppTenantKeyConf = (ApplicationTenantKeyConfiguration) exchange.getIn().getBody();
        System.out.println(GSONProvider.ToJson(payerSettingsAppTenantKeyConf));
    }

    @Test
    public void testUpdateContractSettings() {
        testRetrieveContractSettingsForSk();
        exchange.getIn().setHeader("sequence_key", null);
        payerAppTenantKeyConfiguration = (ApplicationTenantKeyConfiguration) exchange.getIn().getBody();
        payerAppTenantKeyConfiguration.setRecordUpdateTimestamp(new Date());
        exchange.getIn().setHeader("CamelHttpMethod", "PUT");
        exchange.getIn().setHeader("operationName", "PKG_APP_updateAppTenantKeyConf_ApplicationTenantKeyConfiguration");
        exchange.getIn().setHeader("connectionType", ConnectionType.METADATA);

        restDataService.updateAppTenantKeyConf(exchange);

        System.out.println(exchange.getIn().getBody());
    }

    @Test
    public void testUpdatePayerSettings() {
        testRetrievePayerSettingsForSk();
        exchange.getIn().setHeader("sequence_key", null);
        payerAppTenantKeyConfiguration = (ApplicationTenantKeyConfiguration) exchange.getIn().getBody();
        payerAppTenantKeyConfiguration.setRecordUpdateTimestamp(new Date());
        exchange.getIn().setHeader("CamelHttpMethod", "PUT");
        exchange.getIn().setHeader("operationName", "PKG_APP_updateAppTenantKeyConf_ApplicationTenantKeyConfiguration");
        exchange.getIn().setHeader("connectionType", ConnectionType.METADATA);

        restDataService.updateAppTenantKeyConf(exchange);

        System.out.println(exchange.getIn().getBody());
    }

    @Test
    public void testDeleteContractSettings() {
        Integer sequenceKey = 255;
        exchange.getIn().setHeader("sequence_key", sequenceKey);
        exchange.getIn().setHeader("CamelHttpMethod", "DELETE");
        exchange.getIn().setHeader("operationName", "PKG_APP_deleteAppTenantKeyConfContract_ApplicationTenantKeyConfiguration");
        exchange.getIn().setHeader("connectionType", ConnectionType.METADATA);

        restDataService.deleteAppTenantKeyConf(exchange);

        System.out.println(exchange.getIn().getBody());
    }

    @Test
    public void testDeletePayerSettings() {
        Integer sequenceKey = 248;
        exchange.getIn().setHeader("sequence_key", sequenceKey);
        exchange.getIn().setHeader("CamelHttpMethod", "DELETE");
        exchange.getIn().setHeader("operationName", "PKG_APP_deleteAppTenantKeyConfPayer_ApplicationTenantKeyConfiguration");
        exchange.getIn().setHeader("connectionType", ConnectionType.METADATA);

        restDataService.deleteAppTenantKeyConf(exchange);

        System.out.println(exchange.getIn().getBody());
    }
}
