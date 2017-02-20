package com.sandata.lab.eligibility.processors;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.JndiRegistry;
import org.apache.camel.impl.PropertyPlaceholderDelegateRegistry;
import org.apache.camel.test.junit4.ExchangeTestSupport;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.dl.model.Eligibility;
import com.sandata.lab.data.model.jpub.model.EligT;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.eligibility.api.KeyValueStorageService;
import com.sandata.lab.eligibility.impl.OracleDataService;
import com.sandata.lab.eligibility.utils.constants.App;
import com.sandata.lab.eligibility.utils.test.TestUtils;

public class EligibilityInquiryProcessorTest extends ExchangeTestSupport {

    private static final String KEY_VALUE_STORAGE_SERVICE_BEAN_ID = "keyValueStorageService";

    private AppenderSkeleton logAppenderMock;
    private OracleDataService oracleDataServiceMock;
    private KeyValueStorageService keyValueStorageServiceMock;

    @Before
    public void setupMocks() {
        this.logAppenderMock = mock(AppenderSkeleton.class);
        this.oracleDataServiceMock = mock(OracleDataService.class);

        Logger.getRootLogger().addAppender(this.logAppenderMock);
    }

    @Override
    protected CamelContext createCamelContext() throws Exception {
        CamelContext context = super.createCamelContext();

        PropertyPlaceholderDelegateRegistry registry = (PropertyPlaceholderDelegateRegistry) context.getRegistry();
        JndiRegistry jndiRegistry = (JndiRegistry) registry.getRegistry();

        this.keyValueStorageServiceMock = jndiRegistry.lookupByNameAndType(KEY_VALUE_STORAGE_SERVICE_BEAN_ID, KeyValueStorageService.class);
        if (this.keyValueStorageServiceMock == null) {
            this.keyValueStorageServiceMock = mock(KeyValueStorageService.class);
            jndiRegistry.bind(KEY_VALUE_STORAGE_SERVICE_BEAN_ID, this.keyValueStorageServiceMock);
        }

        return context;
    }

    @Test
    public void testGetEligibilityInquiryByPatientPayerSk_should_log_error_message_and_stop_exchange_when_patient_payer_sk_is_invalid() {
        // arrange
        ArgumentCaptor<LoggingEvent> loggingEventCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        EligibilityInquiryProcessor eligibilityInquiryProcessor = new EligibilityInquiryProcessor();
        eligibilityInquiryProcessor.setOracleDataService(this.oracleDataServiceMock);

        List<Integer> invalidPatientPayerSks = new ArrayList<Integer>();
        invalidPatientPayerSks.add(null);
        invalidPatientPayerSks.add(-1);
        invalidPatientPayerSks.add(0);

        for (Integer invalidPatientPayerSk : invalidPatientPayerSks) {
            exchange.getIn().setBody(invalidPatientPayerSk);

            // act
            eligibilityInquiryProcessor.getEligibilityInquiryByPatientPayerSk(exchange);

            // assert
            verify(this.logAppenderMock, atLeastOnce()).doAppend(loggingEventCaptor.capture());
            assertThat(loggingEventCaptor.getValue().getRenderedMessage(),
                    endsWith(String.format("Cannot create eligibility inquiry as PT_PAYER_SK = %s",
                            (invalidPatientPayerSk == null ? "null" : invalidPatientPayerSk).toString())));
            assertThat(loggingEventCaptor.getValue().getLevel(), equalTo(Level.ERROR));
            assertThat(TestUtils.isExchangeStopped(exchange), is(equalTo(Boolean.TRUE)));
        }
    }

    @Test
    public void testGetEligibilityInquiryByPatientPayerSk_should_log_error_message_and_stop_exchange_when_oracleDataService_returns_null() {
        // arrange
        Integer testPatientPayerSk = 123;
        ArgumentCaptor<LoggingEvent> loggingEventCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        when(this.oracleDataServiceMock.getEligibilityInquiryByPatientPayerSk(testPatientPayerSk))
            .thenReturn(null);

        EligibilityInquiryProcessor eligibilityInquiryProcessor = new EligibilityInquiryProcessor();
        eligibilityInquiryProcessor.setOracleDataService(this.oracleDataServiceMock);

        exchange.getIn().setBody(testPatientPayerSk);

        // act
        Map<String, Object> actualMap = eligibilityInquiryProcessor.getEligibilityInquiryByPatientPayerSk(exchange);

        // assert
        assertThat(actualMap, is(nullValue()));
        verify(this.logAppenderMock, atLeastOnce()).doAppend(loggingEventCaptor.capture());
        assertThat(loggingEventCaptor.getValue().getRenderedMessage(),
                endsWith(String.format(
                        "Cannot create eligibility inquiry for PT_PAYER_SK = %s as cannot get enough information by joining tables: PT, BE, PT_PAYER and PT_PAYER_INS for creating inquiry",
                        (testPatientPayerSk == null ? "null" : testPatientPayerSk).toString())));
        assertThat(loggingEventCaptor.getValue().getLevel(), equalTo(Level.ERROR));
        assertThat(TestUtils.isExchangeStopped(exchange), is(equalTo(Boolean.TRUE)));
    }

    @Test
    public void testGetEligibilityInquiryByPatientPayerSk_should_return_what_oracleDataService_returns() {
        // arrange
        Integer testPatientPayerSk = 123;
        Map<String, Object> expectedMap = new HashMap<String, Object>();
        expectedMap.put("PT_ID", "Patient_1");
        expectedMap.put("PAYER_ID", "Payer_1");
        expectedMap.put("BE_ID", "Be_1");

        when(this.oracleDataServiceMock.getEligibilityInquiryByPatientPayerSk(testPatientPayerSk))
            .thenReturn(expectedMap);

        EligibilityInquiryProcessor eligibilityInquiryProcessor = new EligibilityInquiryProcessor();
        eligibilityInquiryProcessor.setOracleDataService(this.oracleDataServiceMock);

        exchange.getIn().setBody(testPatientPayerSk);

        // act
        Map<String, Object> actualMap = eligibilityInquiryProcessor.getEligibilityInquiryByPatientPayerSk(exchange);

        // assert
        assertThat(actualMap, is(equalTo(expectedMap)));
    }

    @Test
    public void testInsertEligibility_should_log_error_message_and_stop_exchange_when_oracleDataService_throws_SandataRuntimeExpection() {
        // arrange
        String fakeErrorMessage = "this is fake exception";

        when(this.oracleDataServiceMock.execute(any(ConnectionType.class), anyString(), anyString(), any(EligT.class)))
            .thenThrow(new SandataRuntimeException(fakeErrorMessage));

        ArgumentCaptor<LoggingEvent> loggingEventCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        exchange.getIn().setHeader(App.INQUIRY_RECORD_HEADER, new HashMap<String, Object>());

        EligibilityInquiryProcessor eligibilityInquiryProcessor = new EligibilityInquiryProcessor();
        eligibilityInquiryProcessor.setOracleDataService(this.oracleDataServiceMock);

        // act
        eligibilityInquiryProcessor.insertEligibility(exchange);

        // assert
        verify(this.logAppenderMock, atLeastOnce()).doAppend(loggingEventCaptor.capture());
        assertThat(loggingEventCaptor.getValue().getRenderedMessage(), endsWith(String
                .format("An exception happens when inserting Eligibility to database. Error: %s", fakeErrorMessage)));
        assertThat(loggingEventCaptor.getValue().getLevel(), equalTo(Level.ERROR));
        assertThat(TestUtils.isExchangeStopped(exchange), is(equalTo(Boolean.TRUE)));
    }

    @Test
    public void testInsertEligibility_should_log_error_message_and_stop_exchange_when_keyValueStorageService_throws_SandataRuntimeExpection() {
        // arrange
        String fakeErrorMessage = "this is fake exception";
        String testTraceNumber = "testTraceNumber";

        when(this.oracleDataServiceMock.execute(any(ConnectionType.class), anyString(), anyString(), any(EligT.class)))
            .thenReturn(1);

        doThrow(new SandataRuntimeException(fakeErrorMessage)).when(this.keyValueStorageServiceMock).put(anyString(), any(Serializable.class));

        ArgumentCaptor<LoggingEvent> loggingEventCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        exchange.getIn().setHeader(App.INQUIRY_RECORD_HEADER, new HashMap<String, Object>());
        exchange.getIn().setHeader(App.TRACE_NUMBER_HEADER, "testTraceNumber");

        EligibilityInquiryProcessor eligibilityInquiryProcessor = new EligibilityInquiryProcessor();
        eligibilityInquiryProcessor.setOracleDataService(this.oracleDataServiceMock);

        // act
        eligibilityInquiryProcessor.insertEligibility(exchange);

        // assert
        verify(this.logAppenderMock, atLeastOnce()).doAppend(loggingEventCaptor.capture());
        assertThat(loggingEventCaptor.getValue().getRenderedMessage(),
                endsWith(String.format(
                        "An exception happens when putting Eligibility for trace number=%s to key-value storage. Error: %s",
                        testTraceNumber, fakeErrorMessage)));
        assertThat(loggingEventCaptor.getValue().getLevel(), equalTo(Level.ERROR));
        assertThat(TestUtils.isExchangeStopped(exchange), is(equalTo(Boolean.TRUE)));
    }

    @Test
    public void testInsertEligibility_should_call_oracleDataService_to_insert_eligibility_to_coredata_and_put_tracenumber_eligibility_pair_to_level_db() {
        // arrange
        BigInteger expectedEligibilitySk = BigInteger.valueOf(123);
        String expectedPatientId = "PatientId_1";
        String expectedPayerId = "PayerId_1";
        String expectedBeId = "BeId_1";

        String pkgEligibility = "PKG_ELIGIBILITY";
        String insertEligMethod = "insertElig";

        String testTraceNumber = UUID.randomUUID().toString();

        Map<String, Object> eligibilityRecord = new HashMap<String, Object>();
        eligibilityRecord.put("PT_ID", expectedPatientId);
        eligibilityRecord.put("PAYER_ID", expectedPayerId);
        eligibilityRecord.put("BE_ID", expectedBeId);

        when(this.oracleDataServiceMock.execute(eq(ConnectionType.COREDATA), eq(pkgEligibility), eq(insertEligMethod), Matchers.any(EligT.class)))
            .thenReturn(expectedEligibilitySk.intValue());

        EligibilityInquiryProcessor eligibilityInquiryProcessor = new EligibilityInquiryProcessor();
        eligibilityInquiryProcessor.setOracleDataService(this.oracleDataServiceMock);

        exchange.getIn().setHeader(App.INQUIRY_RECORD_HEADER, eligibilityRecord);
        exchange.getIn().setHeader(App.TRACE_NUMBER_HEADER, testTraceNumber);

        // act
        eligibilityInquiryProcessor.insertEligibility(exchange);

        // assert
        verify(this.oracleDataServiceMock, times(1)).execute(eq(ConnectionType.COREDATA), eq(pkgEligibility), eq(insertEligMethod), Matchers.any(EligT.class));

        ArgumentCaptor<Serializable> valueArgumentCaptor = ArgumentCaptor.forClass(Serializable.class);
        verify(this.keyValueStorageServiceMock, times(1)).put(eq(testTraceNumber), valueArgumentCaptor.capture());
        assertThat(valueArgumentCaptor.getValue(), notNullValue());
        assertThat(valueArgumentCaptor.getValue(), CoreMatchers.instanceOf(String.class));
        Eligibility actualEligibility = (Eligibility) GSONProvider.FromJson((String) valueArgumentCaptor.getValue(), Eligibility.class);

        assertThat(actualEligibility, notNullValue());
        assertThat(actualEligibility.getEligibilitySK(), equalTo(expectedEligibilitySk));
        assertThat(actualEligibility.getPatientID(), equalTo(expectedPatientId));
        assertThat(actualEligibility.getPayerID(), equalTo(expectedPayerId));
        assertThat(actualEligibility.getBusinessEntityID(), equalTo(expectedBeId));
    }

    @Test
    public void testConvertToInquiry270JsonFile_should_convert_inquiry_map_to_ByteArrayInputStream() {
        // arrange
        String patientId = "Patient1";
        String payerId = "Payer1";
        String beId = "Be1";

        String expectedFileNamePrefix = new StringBuilder("Inquiry")
                .append("_").append(beId)
                .append("_").append(patientId)
                .append("_").append(payerId)
                .append("_").append(new SimpleDateFormat("YYYYMMDDHH").format(new Date()))
                .toString();
        
        Map<String, Object> inquiryMap = new HashMap<String, Object>();
        inquiryMap.put("PT_ID", patientId);
        inquiryMap.put("PAYER_ID", payerId);
        inquiryMap.put("BE_ID", beId);
        
        EligibilityInquiryProcessor eligibilityInquiryProcessor = new EligibilityInquiryProcessor();
        eligibilityInquiryProcessor.setOracleDataService(this.oracleDataServiceMock);

        exchange.getIn().setBody(inquiryMap);

        // act
        eligibilityInquiryProcessor.convertToInquiry270JsonFile(exchange);

        // assert
        // headers assertion
        String actualFileName = exchange.getIn().getHeader(Exchange.FILE_NAME, String.class);
        assertThat(actualFileName, notNullValue());
        assertThat(actualFileName, allOf(startsWith(expectedFileNamePrefix), endsWith(".json")));
        assertThat(exchange.getIn().getHeader(App.TRACE_NUMBER_HEADER, String.class), notNullValue());
        assertThat((Map<String, Object>) exchange.getIn().getHeader(App.INQUIRY_RECORD_HEADER), equalTo(inquiryMap));
        // body assertion
        assertThat(exchange.getIn().getBody(ByteArrayInputStream.class), notNullValue());
    }
}
