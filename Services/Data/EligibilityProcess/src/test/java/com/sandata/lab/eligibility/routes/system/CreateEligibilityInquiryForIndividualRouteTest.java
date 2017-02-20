package com.sandata.lab.eligibility.routes.system;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.JndiRegistry;
import org.apache.camel.impl.PropertyPlaceholderDelegateRegistry;
import org.apache.camel.model.LogDefinition;
import org.apache.camel.model.ToDefinition;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Before;
import org.junit.Test;

public class CreateEligibilityInquiryForIndividualRouteTest extends CamelTestSupport {

    private static final String START_ENDPOINT = "seda:start";
    private static final String MOCK_HANDLER_ENDPOINT = "mock:request-handler";
    private static final String MOCK_UNHANDLED_REQUEST_ENDPOINT = "mock:unhandled-request";

    @Override
    public boolean isUseAdviceWith() {
        return true;
    }

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new CreateEligibilityInquiryForIndividualRoute();
    }

    @Override
    protected CamelContext createCamelContext() throws Exception {
        CamelContext context = super.createCamelContext();

        PropertyPlaceholderDelegateRegistry registry = (PropertyPlaceholderDelegateRegistry) context.getRegistry();
        JndiRegistry jndiRegistry = (JndiRegistry) registry.getRegistry();

        jndiRegistry.bind("eligibilityProcessThreadPool", new ThreadPoolExecutor(1, 1, 1, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(1)));

        return context;
    }

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();

        context.getRouteDefinitions().get(0).adviceWith(context, new AdviceWithRouteBuilder() {

            @Override
            public void configure() throws Exception {
                // replace 'from' endpoint of activemq with seda:start
                replaceFromWith(START_ENDPOINT);

                // mock 'to' endpoint of request handler
                weaveByType(ToDefinition.class).selectFirst().replace().to(MOCK_HANDLER_ENDPOINT);
                
                // forward unhandled requests to mock endpoint
                weaveByType(LogDefinition.class).selectLast().after().to(MOCK_UNHANDLED_REQUEST_ENDPOINT);
            }

        });
    }

    @Test
    public void testCreateEligibilityInquiryForIndividualRoute_should_forward_message_to_handler_when_patient_payer_is_inserted_or_updated() throws Exception {
        // start CamelContext as using adviceWith
        context.start();

        // arrange
        // 1 for _insertPtPayer_; 1 for _updatePtPayer_ (=2 handled requests)
        MockEndpoint mockHandlerEndpoint = getMockEndpoint(MOCK_HANDLER_ENDPOINT);
        mockHandlerEndpoint.setExpectedMessageCount(2);
        mockHandlerEndpoint.expectedBodiesReceivedInAnyOrder(1, 2);

        // 2 unhandled requests
        MockEndpoint mockUnhandledRequestEndpoint = getMockEndpoint(MOCK_UNHANDLED_REQUEST_ENDPOINT);
        mockUnhandledRequestEndpoint.setExpectedMessageCount(2);
        mockUnhandledRequestEndpoint.expectedBodiesReceivedInAnyOrder(3, 4);

        // act: when a patient-payer is inserted or updated by GEN_REST_PATIENT_SERVICE, a message is sent to queue:ELIGIBILITY_PROCESS_SENDS_INQUIRY_270_REQUEST
        template.sendBodyAndHeader(START_ENDPOINT, 1, "operationName", "PKG_PATIENT_insertPtPayer_PatientPayer");
        template.sendBodyAndHeader(START_ENDPOINT, 2, "operationName", "PKG_PATIENT_updatePtPayer_PatientPayer");

        // should not handler other operationNames
        template.sendBodyAndHeader(START_ENDPOINT, 3, "operationName", "PKG_PATIENT_deletePtPayer_PatientPayer");
        template.sendBodyAndHeader(START_ENDPOINT, 4, "operationName", "PKG_PATIENT_getPtPayer_PatientPayer");

        // assert
        assertNotNull(context.hasEndpoint(MOCK_HANDLER_ENDPOINT));
        assertMockEndpointsSatisfied();
    }
}
