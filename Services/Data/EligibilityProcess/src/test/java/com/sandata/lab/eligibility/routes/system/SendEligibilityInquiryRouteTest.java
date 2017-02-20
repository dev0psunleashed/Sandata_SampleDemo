package com.sandata.lab.eligibility.routes.system;

import java.util.Properties;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelBeanPostProcessor;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class SendEligibilityInquiryRouteTest extends CamelTestSupport {
    private static final String START_ENDPOINT = "direct:start";
    private static final String MOCK_END_ENDPOINT = "mock:end";

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
                // route to test (SendEligibilityInquiryRoute) will added in
                // #setUp to apply @ProjectInject without using CamelBlueprintTestSupport
            }
        };
    }

    @Override
    protected CamelContext createCamelContext() throws Exception {
        CamelContext context = super.createCamelContext();

        setProperties(context);

        return context;
    }

    private void setProperties(CamelContext context) {
        PropertiesComponent propComponent = context.getComponent("properties", PropertiesComponent.class);
        Properties properties = new Properties();

        properties.setProperty("send.eligibility.inquiry.temp.from.endpoint", START_ENDPOINT);
        properties.setProperty("send.eligibility.inquiry.endpoint", MOCK_END_ENDPOINT);

        propComponent.setInitialProperties(properties);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        addRoutesToTest();
    }

    private void addRoutesToTest() throws Exception {
        // add route to test here to apply @ProjectInject without using CamelBlueprintTestSupport
        SendEligibilityInquiryRoute routeToTest = new SendEligibilityInquiryRoute();
        new DefaultCamelBeanPostProcessor(context).postProcessBeforeInitialization(routeToTest, "builder");
        context.addRoutes(routeToTest);
    }

    @Test
    public void testSendEligibilityInquiryRoute_should_send_message_from_start_to_end() throws Exception {
        // arrange
        String expectedBody = "test body";
        MockEndpoint mockEndEndpoint = getMockEndpoint(MOCK_END_ENDPOINT);
        mockEndEndpoint.setExpectedMessageCount(1);
        mockEndEndpoint.expectedBodiesReceived(expectedBody);

        // act
        context.start();
        sendBody(START_ENDPOINT, expectedBody);

        // assert
        mockEndEndpoint.assertIsSatisfied();
    }
}
