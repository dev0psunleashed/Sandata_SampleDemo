package com.sandata.lab.eligibility.routes.system;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Properties;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelBeanPostProcessor;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class DownloadEligibilityResponseRouteTest extends CamelTestSupport {

    private static final String START_ENDPOINT = "direct:start";
    private static final String MOCK_END_ENDPOINT = "mock:end";

    private AppenderSkeleton logAppenderMock;

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
                // route to test (DownloadEligibilityResponseRoute) will added in
                // #setUp to apply @ProjectInject without using CamelBlueprintTestSupport
            }
        };
    }

    @Override
    protected CamelContext createCamelContext() throws Exception {
        CamelContext context = super.createCamelContext();

        setProperties(context);
        createMocks();

        return context;
    }

    private void setProperties(CamelContext context) {
        PropertiesComponent propComponent = context.getComponent("properties", PropertiesComponent.class);
        Properties properties = new Properties();

        properties.setProperty("elig.response.download.source", START_ENDPOINT);
        properties.setProperty("elig.response.download.target", MOCK_END_ENDPOINT);
        properties.setProperty("elig.response.download.redeliverydelay", "0");
        properties.setProperty("elig.response.download.maximumRedeliveries", "1");

        propComponent.setInitialProperties(properties);
    }

    private void createMocks() {
        this.logAppenderMock = mock(AppenderSkeleton.class);
        Logger.getRootLogger().addAppender(this.logAppenderMock);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        // add route to test here to apply @ProjectInject without using CamelBlueprintTestSupport
        DownloadEligibilityResponseRoute downloadRoute = new DownloadEligibilityResponseRoute();
        new DefaultCamelBeanPostProcessor(context).postProcessBeforeInitialization(downloadRoute, "builder");
        context.addRoutes(downloadRoute);
    }

    @Test
    public void testDownloadEligibilityResponse_should_successfully_download_file() throws Exception {
        // arrange
        ArgumentCaptor<LoggingEvent> loggingEventCaptor = ArgumentCaptor.forClass(LoggingEvent.class);
        String testMessage = "test message";

        MockEndpoint mockEndEndpoint = getMockEndpoint(MOCK_END_ENDPOINT);
        mockEndEndpoint.setExpectedMessageCount(1);
        mockEndEndpoint.expectedBodiesReceived(testMessage);

        // start CamelContext as using adviceWith
        context.start();

        // act
        sendBody(START_ENDPOINT, testMessage);

        // assert
        verify(this.logAppenderMock, atLeastOnce()).doAppend(loggingEventCaptor.capture());
        assertThat(loggingEventCaptor.getValue().getRenderedMessage(), containsString("Completed downloading response JSON file from ELIGIBILL"));
        assertThat(loggingEventCaptor.getValue().getLevel(), equalTo(Level.INFO));

        mockEndEndpoint.assertIsSatisfied();
    }
}
