package com.sandata.lab.eligibility.routes.system;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Route;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelBeanPostProcessor;
import org.apache.camel.impl.JndiRegistry;
import org.apache.camel.impl.PropertyPlaceholderDelegateRegistry;
import org.apache.camel.model.ToDefinition;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.sandata.lab.data.model.dl.model.ApplicationTenantBusinessEntityCrosswalk;
import com.sandata.lab.data.model.dl.model.ApplicationTenantKeyConfiguration;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.eligibility.data.SandataOracleCoreDataSourceAdapter;
import com.sandata.lab.eligibility.impl.OracleDataService;
import com.sandata.lab.eligibility.processors.EligibilityInquiryProcessor;
import com.sandata.lab.eligibility.routes.dynamic.DynamicCreateEligibilityInquiryForAgencyRouteBuilder;
import com.sandata.lab.eligibility.utils.constants.App;

public class CreateEligibilityInquiryForAgencyMainRouteTest extends CamelTestSupport {

    private static final String MOCK_MAIN_ROUTE_START_ENDPOINT = "mock:start-main-route";
    private static final String MOCK_DATASOURCE_ENDPOINT = "mock:datasource";
    private static final String MOCK_START_IMMEDIATELY_ROUTE_START_ENDPOINT = "mock:start-immediate-route";
    private static final String MOCK_START_IMMEDIATELY_ROUTE_END_ENDPOINT = "mock:end-immediate-route";
    private static final String MOCK_FREQUENCY_ROUTE_END_ENDPOINT = "mock:end-frequency-route";

    private static final int START_FREQUENCY_ROUTE_AFTER_MILLIS = 5000;

    // mocks
    private OracleDataService oracleDataServiceMock;
    private EligibilityInquiryProcessor eligibilityInquiryProcessorMock;
    private SandataOracleCoreDataSourceAdapter coreDataSourceMock;

    @Override
    public boolean isUseAdviceWith() {
        return true;
    }

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // no route definition here
                // route to test (CreateEligibilityInquiryForAgencyMainRoute) will added in
                // #setUp to apply @ProjectInject without using CamelBlueprintTestSupport
            }
        };
    }

    @Override
    protected CamelContext createCamelContext() throws Exception {
        CamelContext context = super.createCamelContext();

        setProperties(context);
        createMocks();
        registerBeansToContext(context);

        return context;
    }

    private void setProperties(CamelContext context) {
        PropertiesComponent propComponent = context.getComponent("properties", PropertiesComponent.class);
        Properties properties = new Properties();

        // frequency period
        properties.setProperty("send.eligibility.inquiry.all.agencies.route.frequency.period", "2h");

        // first fire = #START_FREQUENCY_ROUTE_AFTER_MILLIS to test
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Date firstFireAt = new Date(System.currentTimeMillis() + START_FREQUENCY_ROUTE_AFTER_MILLIS);
        properties.setProperty("send.eligibility.inquiry.all.agencies.route.first.fire", simpleDateFormat.format(firstFireAt));

        propComponent.setInitialProperties(properties);
    }

    private void createMocks() {
        this.oracleDataServiceMock = mock(OracleDataService.class);
        this.eligibilityInquiryProcessorMock = mock(EligibilityInquiryProcessor.class);
        this.coreDataSourceMock = mock(SandataOracleCoreDataSourceAdapter.class);
    }

    private void registerBeansToContext(CamelContext context) {
        PropertyPlaceholderDelegateRegistry registry = (PropertyPlaceholderDelegateRegistry) context.getRegistry();
        JndiRegistry jndiRegistry = (JndiRegistry) registry.getRegistry();
        if (jndiRegistry.lookup("oracleDataService") == null) {
            jndiRegistry.bind("oracleDataService", this.oracleDataServiceMock);
        }

        if (jndiRegistry.lookup("eligibilityInquiryProcessor") == null) {
            jndiRegistry.bind("eligibilityInquiryProcessor", this.eligibilityInquiryProcessorMock);
        }

        if (jndiRegistry.lookup("coreDataSource") == null) {
            jndiRegistry.bind("coreDataSource", this.coreDataSourceMock);
        }
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        // add route to test here to apply @ProjectInject without using CamelBlueprintTestSupport
        CreateEligibilityInquiryForAgencyMainRoute mainRoute = new CreateEligibilityInquiryForAgencyMainRoute();
        new DefaultCamelBeanPostProcessor(context).postProcessBeforeInitialization(mainRoute, "builder");
        context.addRoutes(mainRoute);

        // add mock endpoints to the one-time route (start immediately)
        context.getRouteDefinition(CreateEligibilityInquiryForAgencyMainRoute.START_IMMEDIATELY_ROUTE_ID).adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                interceptFrom().to(MOCK_START_IMMEDIATELY_ROUTE_START_ENDPOINT);
                
                weaveAddLast().to(MOCK_START_IMMEDIATELY_ROUTE_END_ENDPOINT);
            }
        });

        // add mock endpoints to the frequency route
        context.getRouteDefinition(CreateEligibilityInquiryForAgencyMainRoute.FREQUENCY_ROUTE_ID).adviceWith(context, new AdviceWithRouteBuilder() {

            @Override
            public void configure() throws Exception {
                weaveAddLast().to(MOCK_FREQUENCY_ROUTE_END_ENDPOINT);
            }
        });

        // add mock endpoints to the main route
        context.getRouteDefinition(CreateEligibilityInquiryForAgencyMainRoute.class.getSimpleName()).adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                interceptFrom().to(MOCK_MAIN_ROUTE_START_ENDPOINT);

                // mock 'to' endpoint jdbc:datasource
                weaveByType(ToDefinition.class).selectFirst().replace().to(MOCK_DATASOURCE_ENDPOINT);
            }
        });
    }

    private boolean isStartImmediatelyRouteRun;

    private boolean isStartImmediatelyRouteRun() {
        return this.isStartImmediatelyRouteRun;
    }

    private void setIsStartImmediatelyRouteRun(boolean isStartImmediatelyRouteRun) {
        this.isStartImmediatelyRouteRun = isStartImmediatelyRouteRun;
    }

    @Test
    public void testCreateEligibilityInquiryForAgencyMainRoute_should_start_frequency_route_when_exception_happens_in_one_time_route() throws Exception {
        // arrange

        int assertPeriod = 1000;

        // mock 'start' endpoint for one-time (start immediately) route
        MockEndpoint mockImmediateRouteStartEndpoint = getMockEndpoint(MOCK_START_IMMEDIATELY_ROUTE_START_ENDPOINT);
        mockImmediateRouteStartEndpoint.whenAnyExchangeReceived(new Processor(){
            @Override
            public void process(Exchange arg0) throws Exception {
                throw new RuntimeException("this is a fake exception that happens in " + CreateEligibilityInquiryForAgencyMainRoute.START_IMMEDIATELY_ROUTE_ID);
            }
        });

        // mock 'end' endpoint for one-time (start immediately) route
        MockEndpoint mockImmediateRouteEndEndpoint = getMockEndpoint(MOCK_START_IMMEDIATELY_ROUTE_END_ENDPOINT);
        mockImmediateRouteEndEndpoint.setExpectedMessageCount(0);
        mockImmediateRouteEndEndpoint.setAssertPeriod(assertPeriod);

        // frequency route should have 01 message
        MockEndpoint mockFrequencyRouteEndEndpoint = getMockEndpoint(MOCK_FREQUENCY_ROUTE_END_ENDPOINT);
        mockFrequencyRouteEndEndpoint.setExpectedMessageCount(1);
        mockFrequencyRouteEndEndpoint.setAssertPeriod(assertPeriod);

        // main route should have only 01 message (from frequency route as timer route has exception)
        MockEndpoint mockMainRouteStartEndpoint = getMockEndpoint(MOCK_MAIN_ROUTE_START_ENDPOINT);
        mockMainRouteStartEndpoint.setExpectedMessageCount(1);
        mockMainRouteStartEndpoint.setAssertPeriod(assertPeriod);

        // data source
        MockEndpoint mockDataSouceEndpoint = getMockEndpoint(MOCK_DATASOURCE_ENDPOINT);
        mockDataSouceEndpoint.whenAnyExchangeReceived(new Processor() {
            @Override
            public void process(Exchange exchange) {
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                exchange.getIn().setBody(list);
            }
        });

        // act
        context.start();

        // assert
        mockImmediateRouteEndEndpoint.assertIsSatisfied();
        mockFrequencyRouteEndEndpoint.assertIsSatisfied();
        mockMainRouteStartEndpoint.assertIsSatisfied();

        // There should be 03 static routes in the context
        assertThat(context.getRoutes().size(), equalTo(3));

        // all routes should be started
        for (Route route : context.getRoutes()) {
            assertThat(route.getId(),
                    anyOf(equalTo(CreateEligibilityInquiryForAgencyMainRoute.class.getSimpleName()),
                            equalTo(CreateEligibilityInquiryForAgencyMainRoute.START_IMMEDIATELY_ROUTE_ID),
                            equalTo(CreateEligibilityInquiryForAgencyMainRoute.FREQUENCY_ROUTE_ID)));
            assertThat(context.getRouteStatus(route.getId()).isStarted(), is(true));
        }
    }

    @Test
    public void testCreateEligibilityInquiryForAgencyMainRoute_should_successfully_create_dynamic_routes_by_first_run_and_by_scheduled_route_for_all_cases() throws Exception {
        // arrange for all
        final String testBeId1Valid = "TestBeId1";
        final String testBeId2Valid = "TestBeId2";
        final String testBeId3AppTenantSkNull = "TestBeId3AppTenantSkNull";
        final String testBeId4AppTenantKeyConfigNotFoundForKeyName = "testBeId4AppTenantKeyConfigNotFoundForKeyName";
        final String testBeId5AppTenantKeyConfigValueNull = "testBeId5AppTenantKeyConfigValueNull";
        final String testBeId6SendInquiryCronScheduleEmpty = "testBeId6SendInquiryCronScheduleEmpty";

        final int testAppTenantSk1 = 1;
        final int testAppTenantSk2 = 2;
        final int testAppTenantSk3 = 3;
        final int testAppTenantSk4 = 4;
        final int testAppTenantSk5 = 5;
        final int testAppTenantSk6 = 6;

        MockEndpoint mockDataSouceEndpoint = getMockEndpoint(MOCK_DATASOURCE_ENDPOINT);
        mockDataSouceEndpoint.whenAnyExchangeReceived(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                String sql = exchange.getIn().getBody(String.class);
                if (StringUtils.isEmpty(sql) || !sql.startsWith("SELECT * FROM COREDATA.BE WHERE")) {
                    return;
                }

                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                list.add(createTestBusinessEntity(1, testBeId1Valid));

                if (!isStartImmediatelyRouteRun()) {
                    // more BEs to test more cases when the frequency route runs
                    list.add(createTestBusinessEntity(2, testBeId2Valid));
                    list.add(createTestBusinessEntity(3, testBeId3AppTenantSkNull));
                    list.add(createTestBusinessEntity(4, testBeId4AppTenantKeyConfigNotFoundForKeyName));
                    list.add(createTestBusinessEntity(5, testBeId5AppTenantKeyConfigValueNull));
                    list.add(createTestBusinessEntity(6, testBeId6SendInquiryCronScheduleEmpty));
                }

                exchange.getIn().setBody(list);
            }
        });

        String className = "com.sandata.lab.data.model.dl.model.ApplicationTenantBusinessEntityCrosswalk";
        when(this.oracleDataServiceMock.executeGet(eq(ConnectionType.METADATA), eq("PKG_APP"), eq("getAppTenantBeXwalk"), eq(className), anyString()))
            .thenAnswer(new Answer<Object>() {
                @Override
                public Object answer(InvocationOnMock invocation) throws Throwable {
                    String beId = invocation.getArgumentAt(4, String.class);
                    switch (beId) {
                        case testBeId1Valid:
                            return createTestAppTenantBeXwalkList(testAppTenantSk1, testBeId1Valid);

                        case testBeId2Valid:
                            return createTestAppTenantBeXwalkList(testAppTenantSk2, testBeId2Valid);

                        case testBeId3AppTenantSkNull:
                            return createTestAppTenantBeXwalkList(testAppTenantSk3, testBeId3AppTenantSkNull);

                        case testBeId4AppTenantKeyConfigNotFoundForKeyName:
                            return createTestAppTenantBeXwalkList(testAppTenantSk4, testBeId4AppTenantKeyConfigNotFoundForKeyName);

                        case testBeId5AppTenantKeyConfigValueNull:
                            return createTestAppTenantBeXwalkList(testAppTenantSk5, testBeId5AppTenantKeyConfigValueNull);

                        case testBeId6SendInquiryCronScheduleEmpty:
                            return createTestAppTenantBeXwalkList(testAppTenantSk6, testBeId6SendInquiryCronScheduleEmpty);

                        default:
                            return null;
                    }
                }
            });

        when(this.oracleDataServiceMock.getAppTenantKeyConfigurationByAppTenantSkAndKeyName(anyLong(), eq(App.ELIGIBILITY_PROCESS_CONF_KEY_NAME)))
            .thenAnswer(new Answer<ApplicationTenantKeyConfiguration>() {
                @Override
                public ApplicationTenantKeyConfiguration answer(InvocationOnMock invocation) throws Throwable {
                    Long appTenantSk = invocation.getArgumentAt(0, Long.class);
                    return createTestApplicationTenantKeyConfiguration(appTenantSk.intValue());
                }
            });

        ///////////////////////////////////////////////
        // 1. start one-time (start immediately) route
        //////////////////////////////////////////////

        // arrange for #1

        // one-time (start immediately) route should have only 01 message
        MockEndpoint mockImmediateRouteEndEndpoint = getMockEndpoint(MOCK_START_IMMEDIATELY_ROUTE_END_ENDPOINT);
        mockImmediateRouteEndEndpoint.setExpectedMessageCount(1);

        // main route should have only 01 message (from one-time route)
        MockEndpoint mockMainRouteStartEndpoint = getMockEndpoint(MOCK_MAIN_ROUTE_START_ENDPOINT);
        mockMainRouteStartEndpoint.setExpectedMessageCount(1);

        this.setIsStartImmediatelyRouteRun(true);

        // act
        context.start();

        // assert
        mockImmediateRouteEndEndpoint.assertIsSatisfied();
        mockMainRouteStartEndpoint.assertIsSatisfied();

        // There should be 04 routes (03 static + 01 dynamic) in the context
        Thread.sleep(2000);
        assertThat(context.getRoutes().size(), equalTo(4));

        // all routes should be started
        for (Route route : context.getRoutes()) {
            assertThat(route.getId(),
                    anyOf(equalTo(CreateEligibilityInquiryForAgencyMainRoute.class.getSimpleName()),
                            equalTo(CreateEligibilityInquiryForAgencyMainRoute.START_IMMEDIATELY_ROUTE_ID),
                            equalTo(CreateEligibilityInquiryForAgencyMainRoute.FREQUENCY_ROUTE_ID),
                            startsWith(DynamicCreateEligibilityInquiryForAgencyRouteBuilder.DYNAMIC_ROUTE_PREFIX)));
            assertThat(context.getRouteStatus(route.getId()).isStarted(), is(true));
        }

        /////////////////////////////////////////////////////////////////////////////////////
        // 2. the frequency route should be started after #START_FREQUENCY_ROUTE_AFTER_MILLIS
        /////////////////////////////////////////////////////////////////////////////////////

        // arrange for #2

        // set assert period to wait the frequency route to trigger and complete
        int assertPeriod = 1000;

        // one-time (start immediately) route should have no message now
        mockImmediateRouteEndEndpoint.reset();
        mockImmediateRouteEndEndpoint.setExpectedMessageCount(0);
        mockImmediateRouteEndEndpoint.setAssertPeriod(assertPeriod);

        // frequency route should have 01 message
        MockEndpoint mockFrequencyRouteEndEndpoint = getMockEndpoint(MOCK_FREQUENCY_ROUTE_END_ENDPOINT);
        mockFrequencyRouteEndEndpoint.setExpectedMessageCount(1);
        mockFrequencyRouteEndEndpoint.setAssertPeriod(assertPeriod);

        // main route should have 02 messages now (one from one-time route, one from frequency route)
        mockMainRouteStartEndpoint.setExpectedMessageCount(2);
        mockMainRouteStartEndpoint.setAssertPeriod(assertPeriod);

        // act: no need to act, it is fired by schedule
        this.setIsStartImmediatelyRouteRun(false);

        // assert
        mockImmediateRouteEndEndpoint.assertIsSatisfied(assertPeriod);
        mockFrequencyRouteEndEndpoint.assertIsSatisfied(assertPeriod);
        mockMainRouteStartEndpoint.assertIsSatisfied(assertPeriod);

        // There should be 05 routes (03 static + 02 dynamic) in the context
        assertThat(context.getRoutes().size(), equalTo(5));

        // all routes should be started
        for (Route route : context.getRoutes()) {
            assertThat(route.getId(),
                    anyOf(equalTo(CreateEligibilityInquiryForAgencyMainRoute.class.getSimpleName()),
                            equalTo(CreateEligibilityInquiryForAgencyMainRoute.START_IMMEDIATELY_ROUTE_ID),
                            equalTo(CreateEligibilityInquiryForAgencyMainRoute.FREQUENCY_ROUTE_ID),
                            startsWith(DynamicCreateEligibilityInquiryForAgencyRouteBuilder.DYNAMIC_ROUTE_PREFIX)));
            assertThat(context.getRouteStatus(route.getId()).isStarted(), is(true));
        }
    }

    private Map<String, Object> createTestBusinessEntity(int beSk, String beId) {
        Map<String, Object> be = new HashMap<String, Object>();
        be.put("BE_SK", BigDecimal.valueOf(beSk));
        be.put("BE_ID", beId);
        return be;
    }

    private List<ApplicationTenantBusinessEntityCrosswalk> createTestAppTenantBeXwalkList(int testAppTenantSk, String testBeId) {
        List<ApplicationTenantBusinessEntityCrosswalk> list = new ArrayList<ApplicationTenantBusinessEntityCrosswalk>();
        if (testBeId != "TestBeId3AppTenantSkNull") {
            ApplicationTenantBusinessEntityCrosswalk appTenantBeXwalk = new ApplicationTenantBusinessEntityCrosswalk();
            appTenantBeXwalk.setApplicationTenantSK(BigInteger.valueOf(testAppTenantSk));
            appTenantBeXwalk.setBusinessEntityID(testBeId);
            list.add(appTenantBeXwalk);
        }
        return list;
    }

    private ApplicationTenantKeyConfiguration createTestApplicationTenantKeyConfiguration(int testAppTenantSk) {
        ApplicationTenantKeyConfiguration config = new ApplicationTenantKeyConfiguration();
        config.setApplicationTenantSK(BigInteger.valueOf(testAppTenantSk));
        config.setKeyName(App.ELIGIBILITY_PROCESS_CONF_KEY_NAME);

        switch (testAppTenantSk) {
            // valid sendInquiryCronSchedule
            case 1:
                config.setTenantKeyConfigurationValue("{sendInquiryCronSchedule:\"0+0+1+?+*+*+2088\"}");
                break;

            // valid sendInquiryCronSchedule
            case 2:
                config.setTenantKeyConfigurationValue("{sendInquiryCronSchedule:\"0+0+2+?+*+*+2099\"}");
                break;

            // testAppTenantSk3 should be null
            case 3:
                break;

            // testAppTenantSk4 - AppTenantKeyConfig Not Found (=null) for KEY_NAME = App#ELIGIBILITY_PROCESS_CONF_KEY_NAME
            case 4:
                config = null;
                break;

            // testAppTenantSk5 - AppTenantKeyConfigValue is null
            case 5:
                config.setTenantKeyConfigurationValue(null);
                break;

            // testAppTenantSk6 - SendInquiryCronSchedule is empty
            case 6:
                config.setTenantKeyConfigurationValue("{sendInquiryCronSchedule:\"\"}");
                break;

            default:
                break;
        }

        return config;
    }
}
