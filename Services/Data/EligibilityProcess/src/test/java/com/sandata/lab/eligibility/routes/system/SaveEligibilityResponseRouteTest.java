package com.sandata.lab.eligibility.routes.system;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Properties;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelBeanPostProcessor;
import org.apache.camel.impl.JndiRegistry;
import org.apache.camel.impl.PropertyPlaceholderDelegateRegistry;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.sandata.lab.eligibility.processors.EligibilityResponseProcessor;

public class SaveEligibilityResponseRouteTest extends CamelTestSupport {

    private static final String START_ENDPOINT = "direct:start";

    private EligibilityResponseProcessor eligibilityResponseProcessorMock;
    private AppenderSkeleton logAppenderMock;

    private StringBuilder actualBeanCallsOrder;

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
                // route to test (SaveEligibilityResponseRoute) will added in
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

        properties.setProperty("elig.response.process.local.dir", START_ENDPOINT);

        properties.setProperty("elig.response.download.redeliverydelay", "0");
        properties.setProperty("elig.response.download.maximumRedeliveries", "1");

        propComponent.setInitialProperties(properties);
    }

    private void createMocks() {
        this.logAppenderMock = mock(AppenderSkeleton.class);
        Logger.getRootLogger().addAppender(this.logAppenderMock);

        this.eligibilityResponseProcessorMock = mock(EligibilityResponseProcessor.class);

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                actualBeanCallsOrder.append("-eligibilityResponseProcessor#lookupEligibilityStatusByTraceNumber()");
                return null;
            }
        }).when(this.eligibilityResponseProcessorMock).lookupEligibilityStatusByTraceNumber(any(Exchange.class));

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                actualBeanCallsOrder.append("-eligibilityResponseProcessor#updateEligiblityStatus()");
                return null;
            }
        }).when(this.eligibilityResponseProcessorMock).updateEligiblityStatus(any(Exchange.class));
    }

    private void registerBeansToContext(CamelContext context) {
        PropertyPlaceholderDelegateRegistry registry = (PropertyPlaceholderDelegateRegistry) context.getRegistry();
        JndiRegistry jndiRegistry = (JndiRegistry) registry.getRegistry();
        if (jndiRegistry.lookup(EligibilityResponseProcessor.BEAN_NAME) == null) {
            jndiRegistry.bind(EligibilityResponseProcessor.BEAN_NAME, this.eligibilityResponseProcessorMock);
        }
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        addRoutesToTest();
    }

    private void addRoutesToTest() throws Exception {
        // add route to test here to apply @ProjectInject without using CamelBlueprintTestSupport
        SaveEligibilityResponseRoute routeToTest = new SaveEligibilityResponseRoute();
        new DefaultCamelBeanPostProcessor(context).postProcessBeforeInitialization(routeToTest, "builder");
        context.addRoutes(routeToTest);
    }

    @Test
    public void testSaveEligibilityResponseRoute_should_call_correct_processor_methods() throws Exception {
        // arrange
        ArgumentCaptor<LoggingEvent> loggingEventCaptor = ArgumentCaptor.forClass(LoggingEvent.class);
        String testMessage = "{\"Id\":1, \"Name\":\"Responses.json\", \"InquiryResponseTransactions\":[]}";

        StringBuilder expectedBeanCallsOrder = new StringBuilder();
        expectedBeanCallsOrder.append("-eligibilityResponseProcessor#lookupEligibilityStatusByTraceNumber()");
        expectedBeanCallsOrder.append("-eligibilityResponseProcessor#updateEligiblityStatus()");

        // start CamelContext as using adviceWith
        context.start();

        this.actualBeanCallsOrder = new StringBuilder();

        // act
        sendBody(START_ENDPOINT, testMessage);

        // assert
        assertThat(this.actualBeanCallsOrder.toString(), equalTo(expectedBeanCallsOrder.toString()));

        verify(this.logAppenderMock, atLeastOnce()).doAppend(loggingEventCaptor.capture());
        assertThat(loggingEventCaptor.getValue().getRenderedMessage(), containsString("Completed processing response JSON file"));
        assertThat(loggingEventCaptor.getValue().getLevel(), equalTo(Level.INFO));
    }
}
