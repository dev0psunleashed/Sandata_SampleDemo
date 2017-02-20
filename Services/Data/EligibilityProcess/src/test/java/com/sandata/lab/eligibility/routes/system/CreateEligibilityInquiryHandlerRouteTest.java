package com.sandata.lab.eligibility.routes.system;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.JndiRegistry;
import org.apache.camel.impl.PropertyPlaceholderDelegateRegistry;
import org.apache.camel.model.ToDefinition;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.util.FileSystemUtils;

import com.sandata.lab.eligibility.processors.EligibilityInquiryProcessor;

public class CreateEligibilityInquiryHandlerRouteTest extends CamelTestSupport {

    private static final String START_ENDPOINT = "seda:start";
    private static final String MOCK_TEMP_FOLDER_ENDPOINT = "mock:temp-folder";
    private static final String MOCK_END_ENDPOINT = "mock:end";
    private static final String TEMP_FOLDER = "target/temp-folder";

    private EligibilityInquiryProcessor eligibilityInquiryProcessorMock;
    private AppenderSkeleton logAppenderMock;
    
    private StringBuilder actualBeanCallsOrder;
    
    @Override
    public boolean isUseAdviceWith() {
        return true;
    }

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new CreateEligibilityInquiryHandlerRoute();
    }

    @Override
    protected CamelContext createCamelContext() throws Exception {
        CamelContext context = super.createCamelContext();

        PropertyPlaceholderDelegateRegistry registry = (PropertyPlaceholderDelegateRegistry) context.getRegistry();
        JndiRegistry jndiRegistry = (JndiRegistry) registry.getRegistry();

        this.eligibilityInquiryProcessorMock = jndiRegistry.lookupByNameAndType(EligibilityInquiryProcessor.BEAN_NAME, EligibilityInquiryProcessor.class);
        if (this.eligibilityInquiryProcessorMock == null) {
            this.eligibilityInquiryProcessorMock = mock(EligibilityInquiryProcessor.class);
            jndiRegistry.bind(EligibilityInquiryProcessor.BEAN_NAME, this.eligibilityInquiryProcessorMock);
        }

        return context;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        context.getRouteDefinitions().get(0).adviceWith(context, new AdviceWithRouteBuilder() {

            @Override
            public void configure() throws Exception {
                // replace 'from' endpoint of activemq with seda:start
                replaceFromWith(START_ENDPOINT);

                // mock 'to' endpoint of temp folder
                weaveByType(ToDefinition.class).selectFirst().replace().to(MOCK_TEMP_FOLDER_ENDPOINT).to("file:" + TEMP_FOLDER)
                        .process(new Processor() {
                            @Override
                            public void process(Exchange exchange) throws Exception {
                                actualBeanCallsOrder.append("-toDefinition#toTempFolderEndpoint");
                            }
                        });

                weaveAddLast().to(MOCK_END_ENDPOINT);
            }

        });
    }

    @Before
    public void setupMocks() {
        this.logAppenderMock = mock(AppenderSkeleton.class);
        Logger.getRootLogger().addAppender(this.logAppenderMock);
    }

    @Before
    @After
    public void cleanUp() {
        FileSystemUtils.deleteRecursively(new File(TEMP_FOLDER));
    }

    @Test
    public void testCreateEligibilityInquiryHandlerRoute_should_create_json_file_in_temp_folder_when_receiving_patient_payer_sk() throws Exception {
        // arrange
        MockEndpoint mockTempFolderEndpoint = getMockEndpoint(MOCK_TEMP_FOLDER_ENDPOINT);
        mockTempFolderEndpoint.setExpectedMessageCount(1);
        
        MockEndpoint mockEndEndpoint = getMockEndpoint(MOCK_END_ENDPOINT);
        mockEndEndpoint.setExpectedMessageCount(1);

        String expectedProviderNpi = "123456";
        String expectedPatientFirstName = "John";
        String expectedPatientLastName = "Smith";

        final Map<String, Object> inquiryMap = new HashMap<String, Object>();
        inquiryMap.put("PT_ID", "Patient1");
        inquiryMap.put("PAYER_ID", "Payer1");
        inquiryMap.put("BE_ID", "Be1");
        inquiryMap.put("BE_NPI", expectedProviderNpi);
        inquiryMap.put("PT_FIRST_NAME", expectedPatientFirstName);
        inquiryMap.put("PT_LAST_NAME", expectedPatientLastName);

        when(this.eligibilityInquiryProcessorMock.getEligibilityInquiryByPatientPayerSk(any(Exchange.class)))
                .thenAnswer(new Answer<Map<String, Object>>() {
                    @Override
                    public Map<String, Object> answer(InvocationOnMock invocation) throws Throwable {
                        actualBeanCallsOrder.append("-eligibilityInquiryProcessor#getEligibilityInquiryByPatientPayerSk");
                        return inquiryMap;
                    }
                });

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                actualBeanCallsOrder.append("-eligibilityInquiryProcessor#convertToInquiry270JsonFile");
                invocation.callRealMethod();
                return null;
            }
        }).when(this.eligibilityInquiryProcessorMock).convertToInquiry270JsonFile(any(Exchange.class));

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                actualBeanCallsOrder.append("-eligibilityInquiryProcessor#insertEligibility");
                return null;
            }
        }).when(this.eligibilityInquiryProcessorMock).insertEligibility(any(Exchange.class));

        StringBuilder expectedBeanCallsOrder = new StringBuilder();
        expectedBeanCallsOrder.append("-eligibilityInquiryProcessor#getEligibilityInquiryByPatientPayerSk");
        expectedBeanCallsOrder.append("-eligibilityInquiryProcessor#convertToInquiry270JsonFile");
        expectedBeanCallsOrder.append("-toDefinition#toTempFolderEndpoint");
        expectedBeanCallsOrder.append("-eligibilityInquiryProcessor#insertEligibility");

        this.actualBeanCallsOrder = new StringBuilder();

        // start CamelContext as using adviceWith
        context.start();

        // act
        sendBody(START_ENDPOINT, 123);

        // assert
        assertMockEndpointsSatisfied();

        assertThat(actualBeanCallsOrder.toString(), equalTo(expectedBeanCallsOrder.toString()));

        // assertions for JSON file created in temp folder and its content
        Exchange actualExchange = mockEndEndpoint.getReceivedExchanges().get(0);
        String jsonFileProcuded = actualExchange.getIn().getHeader(Exchange.FILE_NAME_PRODUCED, String.class);
        assertThat(jsonFileProcuded, notNullValue());
        assertFileExists(jsonFileProcuded);
        String json = new String(Files.readAllBytes(Paths.get(jsonFileProcuded)));
        assertThat(json, allOf(containsString(expectedProviderNpi), containsString(expectedPatientFirstName), containsString(expectedPatientLastName)));
    }

    @Test
    public void testCreateEligibilityInquiryHandlerRoute_should_log_error_and_stop_exchange_when_exception_happens_in_processor() throws Exception {
        // arrange
        String fakeErrorMessage = "this a fake exception";
        ArgumentCaptor<LoggingEvent> loggingEventCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        MockEndpoint mockTempFolderEndpoint = getMockEndpoint(MOCK_TEMP_FOLDER_ENDPOINT);
        mockTempFolderEndpoint.setExpectedMessageCount(0);

        MockEndpoint mockEndEndpoint = getMockEndpoint(MOCK_END_ENDPOINT);
        mockEndEndpoint.setExpectedMessageCount(0);
        
        when(this.eligibilityInquiryProcessorMock.getEligibilityInquiryByPatientPayerSk(any(Exchange.class)))
                .thenThrow(new RuntimeException(fakeErrorMessage));
        
        // start CamelContext as using adviceWith
        context.start();

        // act
        sendBody(START_ENDPOINT, 123);

        // assert
        assertMockEndpointsSatisfied(1, TimeUnit.SECONDS);

        // assertion for logging
        Thread.sleep(3000);
        String expectedErrorMessage = new StringBuilder()
                .append("Unexpected exception happens in ").append(context().getRoutes().get(0).getId())
                .toString();
        verify(this.logAppenderMock, atLeastOnce()).doAppend(loggingEventCaptor.capture());
        assertThat(loggingEventCaptor.getValue().getLevel(), equalTo(Level.ERROR));
        assertThat(loggingEventCaptor.getValue().getRenderedMessage(), allOf(containsString(expectedErrorMessage), containsString(fakeErrorMessage)));
    }
}
