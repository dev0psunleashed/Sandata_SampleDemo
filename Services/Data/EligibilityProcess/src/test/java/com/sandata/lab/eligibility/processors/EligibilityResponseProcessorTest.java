package com.sandata.lab.eligibility.processors;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.apache.camel.test.junit4.ExchangeTestSupport;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.dl.model.Eligibility;
import com.sandata.lab.data.model.dl.model.EligibilityStatusName;
import com.sandata.lab.eligibility.api.KeyValueStorageService;
import com.sandata.lab.eligibility.impl.OracleDataService;
import com.sandata.lab.eligibility.model.response.Benefit;
import com.sandata.lab.eligibility.model.response.InquiryResponseTransaction;
import com.sandata.lab.eligibility.model.response.Payer;
import com.sandata.lab.eligibility.model.response.Person;
import com.sandata.lab.eligibility.model.response.Provider;
import com.sandata.lab.eligibility.model.response.ResponseMessage;
import com.sandata.lab.eligibility.model.response.enums.BenefitInformationCodeType;
import com.sandata.lab.eligibility.utils.test.TestUtils;

public class EligibilityResponseProcessorTest  extends ExchangeTestSupport{

    private AppenderSkeleton logAppenderMock;
    private OracleDataService oracleDataServiceMock;
    private KeyValueStorageService keyValueStorageServiceMock;

    @Before
    public void setupMocks() throws Exception {
        this.logAppenderMock = mock(AppenderSkeleton.class);
        Logger.getRootLogger().addAppender(this.logAppenderMock);

        this.oracleDataServiceMock = mock(OracleDataService.class);
        this.keyValueStorageServiceMock = mock(KeyValueStorageService.class);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testLookupEligibilityStatusByTraceNumber_should_throw_IllegalArgumentException_when_exchange_body_is_not_a_ResponseMessage() {
        // arrange
        EligibilityResponseProcessor eligibilityResponseProcessor = new EligibilityResponseProcessor();
        eligibilityResponseProcessor.setKeyValueStorageService(this.keyValueStorageServiceMock);

        exchange.getIn().setBody("invalid ResponseMessage");

        try {
            // act
            eligibilityResponseProcessor.lookupEligibilityStatusByTraceNumber(exchange);

        } catch(IllegalArgumentException e) {
            // assert
            Assert.assertThat(e.getMessage(), containsString("Exchange body must be a ResponseMessage"));
            verify(this.keyValueStorageServiceMock, never()).get(anyString(), isA(Class.class));

            return;
        }

        fail("IllegalArgumentException is expected to be thrown, but it was not.");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testLookupEligibilityStatusByTraceNumber_should_do_nothing_when_InquiryResponseTransactionList_is_null() {
        // arrange
        EligibilityResponseProcessor eligibilityResponseProcessor = new EligibilityResponseProcessor();
        eligibilityResponseProcessor.setKeyValueStorageService(this.keyValueStorageServiceMock);

        Collection<InquiryResponseTransaction> inquiryResponseTransactions = null;

        ResponseMessage testResponseMessage = new ResponseMessage();
        testResponseMessage.setInquiryResponseTransactions(inquiryResponseTransactions);

        exchange.getIn().setBody(testResponseMessage);

        // act
        eligibilityResponseProcessor.lookupEligibilityStatusByTraceNumber(exchange);

        // assert
        verify(this.keyValueStorageServiceMock, never()).get(anyString(), isA(Class.class));

        List<Eligibility> actualEligibilities = exchange.getIn().getBody(List.class);
        assertThat(actualEligibilities, notNullValue());
        assertThat(actualEligibilities.size(), equalTo(0));
        assertThat(TestUtils.isExchangeStopped(exchange), equalTo(false));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testLookupEligibilityStatusByTraceNumber_should_do_nothing_when_InquiryResponseTransactionList_is_empty() {
        // arrange
        EligibilityResponseProcessor eligibilityResponseProcessor = new EligibilityResponseProcessor();
        eligibilityResponseProcessor.setKeyValueStorageService(this.keyValueStorageServiceMock);

        Collection<InquiryResponseTransaction> inquiryResponseTransactions = new ArrayList<InquiryResponseTransaction>();

        ResponseMessage testResponseMessage = new ResponseMessage();
        testResponseMessage.setInquiryResponseTransactions(inquiryResponseTransactions);
        
        exchange.getIn().setBody(testResponseMessage);

        // act
        eligibilityResponseProcessor.lookupEligibilityStatusByTraceNumber(exchange);

        // assert
        verify(this.keyValueStorageServiceMock, never()).get(anyString(), isA(Class.class));

        List<Eligibility> actualEligibilities = exchange.getIn().getBody(List.class);
        assertThat(actualEligibilities, notNullValue());
        assertThat(actualEligibilities.size(), equalTo(0));
        assertThat(TestUtils.isExchangeStopped(exchange), equalTo(false));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testLookupEligibilityStatusByTraceNumber_should_do_nothing_when_InquiryResponseTransaction_is_null() {
        // arrange
        EligibilityResponseProcessor eligibilityResponseProcessor = new EligibilityResponseProcessor();
        eligibilityResponseProcessor.setKeyValueStorageService(this.keyValueStorageServiceMock);

        InquiryResponseTransaction inquiryResponseTransaction = null;

        Collection<InquiryResponseTransaction> inquiryResponseTransactions = new ArrayList<InquiryResponseTransaction>();
        inquiryResponseTransactions.add(inquiryResponseTransaction);

        ResponseMessage testResponseMessage = new ResponseMessage();
        testResponseMessage.setInquiryResponseTransactions(inquiryResponseTransactions);

        exchange.getIn().setBody(testResponseMessage);

        // act
        eligibilityResponseProcessor.lookupEligibilityStatusByTraceNumber(exchange);

        // assert
        verify(this.keyValueStorageServiceMock, never()).get(anyString(), isA(Class.class));

        List<Eligibility> actualEligibilities = exchange.getIn().getBody(List.class);
        assertThat(actualEligibilities, notNullValue());
        assertThat(actualEligibilities.size(), equalTo(0));
        assertThat(TestUtils.isExchangeStopped(exchange), equalTo(false));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testLookupEligibilityStatusByTraceNumber_should_do_nothing_when_PayerList_is_null() {
        // arrange
        EligibilityResponseProcessor eligibilityResponseProcessor = new EligibilityResponseProcessor();
        eligibilityResponseProcessor.setKeyValueStorageService(this.keyValueStorageServiceMock);

        List<Payer> payers = null;

        InquiryResponseTransaction inquiryResponseTransaction = new InquiryResponseTransaction();
        inquiryResponseTransaction.setPayers(payers);

        Collection<InquiryResponseTransaction> inquiryResponseTransactions = new ArrayList<InquiryResponseTransaction>();
        inquiryResponseTransactions.add(inquiryResponseTransaction);

        ResponseMessage testResponseMessage = new ResponseMessage();
        testResponseMessage.setInquiryResponseTransactions(inquiryResponseTransactions);

        exchange.getIn().setBody(testResponseMessage);

        // act
        eligibilityResponseProcessor.lookupEligibilityStatusByTraceNumber(exchange);

        // assert
        verify(this.keyValueStorageServiceMock, never()).get(anyString(), isA(Class.class));

        List<Eligibility> actualEligibilities = exchange.getIn().getBody(List.class);
        assertThat(actualEligibilities, notNullValue());
        assertThat(actualEligibilities.size(), equalTo(0));
        assertThat(TestUtils.isExchangeStopped(exchange), equalTo(false));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testLookupEligibilityStatusByTraceNumber_should_do_nothing_when_PayerList_is_empty() {
        // arrange
        EligibilityResponseProcessor eligibilityResponseProcessor = new EligibilityResponseProcessor();
        eligibilityResponseProcessor.setKeyValueStorageService(this.keyValueStorageServiceMock);

        List<Payer> payers = new ArrayList<Payer>();

        InquiryResponseTransaction inquiryResponseTransaction = new InquiryResponseTransaction();
        inquiryResponseTransaction.setPayers(payers);

        Collection<InquiryResponseTransaction> inquiryResponseTransactions = new ArrayList<InquiryResponseTransaction>();
        inquiryResponseTransactions.add(inquiryResponseTransaction);

        ResponseMessage testResponseMessage = new ResponseMessage();
        testResponseMessage.setInquiryResponseTransactions(inquiryResponseTransactions);

        exchange.getIn().setBody(testResponseMessage);

        // act
        eligibilityResponseProcessor.lookupEligibilityStatusByTraceNumber(exchange);

        // assert
        verify(this.keyValueStorageServiceMock, never()).get(anyString(), isA(Class.class));

        List<Eligibility> actualEligibilities = exchange.getIn().getBody(List.class);
        assertThat(actualEligibilities, notNullValue());
        assertThat(actualEligibilities.size(), equalTo(0));
        assertThat(TestUtils.isExchangeStopped(exchange), equalTo(false));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testLookupEligibilityStatusByTraceNumber_should_do_nothing_when_Payer_is_null() {
        // arrange
        EligibilityResponseProcessor eligibilityResponseProcessor = new EligibilityResponseProcessor();
        eligibilityResponseProcessor.setKeyValueStorageService(this.keyValueStorageServiceMock);

        Payer payer = null;

        List<Payer> payers = new ArrayList<Payer>();
        payers.add(payer);

        InquiryResponseTransaction inquiryResponseTransaction = new InquiryResponseTransaction();
        inquiryResponseTransaction.setPayers(payers);

        Collection<InquiryResponseTransaction> inquiryResponseTransactions = new ArrayList<InquiryResponseTransaction>();
        inquiryResponseTransactions.add(inquiryResponseTransaction);

        ResponseMessage testResponseMessage = new ResponseMessage();
        testResponseMessage.setInquiryResponseTransactions(inquiryResponseTransactions);

        exchange.getIn().setBody(testResponseMessage);

        // act
        eligibilityResponseProcessor.lookupEligibilityStatusByTraceNumber(exchange);

        // assert
        verify(this.keyValueStorageServiceMock, never()).get(anyString(), isA(Class.class));

        List<Eligibility> actualEligibilities = exchange.getIn().getBody(List.class);
        assertThat(actualEligibilities, notNullValue());
        assertThat(actualEligibilities.size(), equalTo(0));
        assertThat(TestUtils.isExchangeStopped(exchange), equalTo(false));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testLookupEligibilityStatusByTraceNumber_should_do_nothing_when_ProviderList_is_null() {
        // arrange
        EligibilityResponseProcessor eligibilityResponseProcessor = new EligibilityResponseProcessor();
        eligibilityResponseProcessor.setKeyValueStorageService(this.keyValueStorageServiceMock);

        Payer payer = new Payer();
        payer.setProviders(null);

        List<Payer> payers = new ArrayList<Payer>();
        payers.add(payer);

        InquiryResponseTransaction inquiryResponseTransaction = new InquiryResponseTransaction();
        inquiryResponseTransaction.setPayers(payers);

        Collection<InquiryResponseTransaction> inquiryResponseTransactions = new ArrayList<InquiryResponseTransaction>();
        inquiryResponseTransactions.add(inquiryResponseTransaction);

        ResponseMessage testResponseMessage = new ResponseMessage();
        testResponseMessage.setInquiryResponseTransactions(inquiryResponseTransactions);

        exchange.getIn().setBody(testResponseMessage);

        // act
        eligibilityResponseProcessor.lookupEligibilityStatusByTraceNumber(exchange);

        // assert
        verify(this.keyValueStorageServiceMock, never()).get(anyString(), isA(Class.class));

        List<Eligibility> actualEligibilities = exchange.getIn().getBody(List.class);
        assertThat(actualEligibilities, notNullValue());
        assertThat(actualEligibilities.size(), equalTo(0));
        assertThat(TestUtils.isExchangeStopped(exchange), equalTo(false));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testLookupEligibilityStatusByTraceNumber_should_do_nothing_when_ProviderList_is_empty() {
        // arrange
        EligibilityResponseProcessor eligibilityResponseProcessor = new EligibilityResponseProcessor();
        eligibilityResponseProcessor.setKeyValueStorageService(this.keyValueStorageServiceMock);

        Payer payer = new Payer();
        payer.setProviders(new ArrayList<Provider>());

        List<Payer> payers = new ArrayList<Payer>();
        payers.add(payer);

        InquiryResponseTransaction inquiryResponseTransaction = new InquiryResponseTransaction();
        inquiryResponseTransaction.setPayers(payers);

        Collection<InquiryResponseTransaction> inquiryResponseTransactions = new ArrayList<InquiryResponseTransaction>();
        inquiryResponseTransactions.add(inquiryResponseTransaction);

        ResponseMessage testResponseMessage = new ResponseMessage();
        testResponseMessage.setInquiryResponseTransactions(inquiryResponseTransactions);

        exchange.getIn().setBody(testResponseMessage);

        // act
        eligibilityResponseProcessor.lookupEligibilityStatusByTraceNumber(exchange);

        // assert
        verify(this.keyValueStorageServiceMock, never()).get(anyString(), isA(Class.class));

        List<Eligibility> actualEligibilities = exchange.getIn().getBody(List.class);
        assertThat(actualEligibilities, notNullValue());
        assertThat(actualEligibilities.size(), equalTo(0));
        assertThat(TestUtils.isExchangeStopped(exchange), equalTo(false));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testLookupEligibilityStatusByTraceNumber_should_do_nothing_when_Provider_is_null() {
        // arrange
        EligibilityResponseProcessor eligibilityResponseProcessor = new EligibilityResponseProcessor();
        eligibilityResponseProcessor.setKeyValueStorageService(this.keyValueStorageServiceMock);

        Provider provider = null;
        List<Provider> providers = new ArrayList<Provider>();
        providers.add(provider);

        Payer payer = new Payer();
        payer.setProviders(providers);

        List<Payer> payers = new ArrayList<Payer>();
        payers.add(payer);

        InquiryResponseTransaction inquiryResponseTransaction = new InquiryResponseTransaction();
        inquiryResponseTransaction.setPayers(payers);

        Collection<InquiryResponseTransaction> inquiryResponseTransactions = new ArrayList<InquiryResponseTransaction>();
        inquiryResponseTransactions.add(inquiryResponseTransaction);

        ResponseMessage testResponseMessage = new ResponseMessage();
        testResponseMessage.setInquiryResponseTransactions(inquiryResponseTransactions);

        exchange.getIn().setBody(testResponseMessage);

        // act
        eligibilityResponseProcessor.lookupEligibilityStatusByTraceNumber(exchange);

        // assert
        verify(this.keyValueStorageServiceMock, never()).get(anyString(), isA(Class.class));

        List<Eligibility> actualEligibilities = exchange.getIn().getBody(List.class);
        assertThat(actualEligibilities, notNullValue());
        assertThat(actualEligibilities.size(), equalTo(0));
        assertThat(TestUtils.isExchangeStopped(exchange), equalTo(false));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testLookupEligibilityStatusByTraceNumber_should_do_nothing_when_PersonList_is_null() {
        // arrange
        EligibilityResponseProcessor eligibilityResponseProcessor = new EligibilityResponseProcessor();
        eligibilityResponseProcessor.setKeyValueStorageService(this.keyValueStorageServiceMock);

        List<Person> people = null;

        Provider provider = new Provider();
        provider.setPeople(people);

        List<Provider> providers = new ArrayList<Provider>();
        providers.add(provider);

        Payer payer = new Payer();
        payer.setProviders(providers);

        List<Payer> payers = new ArrayList<Payer>();
        payers.add(payer);

        InquiryResponseTransaction inquiryResponseTransaction = new InquiryResponseTransaction();
        inquiryResponseTransaction.setPayers(payers);

        Collection<InquiryResponseTransaction> inquiryResponseTransactions = new ArrayList<InquiryResponseTransaction>();
        inquiryResponseTransactions.add(inquiryResponseTransaction);

        ResponseMessage testResponseMessage = new ResponseMessage();
        testResponseMessage.setInquiryResponseTransactions(inquiryResponseTransactions);

        exchange.getIn().setBody(testResponseMessage);

        // act
        eligibilityResponseProcessor.lookupEligibilityStatusByTraceNumber(exchange);

        // assert
        verify(this.keyValueStorageServiceMock, never()).get(anyString(), isA(Class.class));

        List<Eligibility> actualEligibilities = exchange.getIn().getBody(List.class);
        assertThat(actualEligibilities, notNullValue());
        assertThat(actualEligibilities.size(), equalTo(0));
        assertThat(TestUtils.isExchangeStopped(exchange), equalTo(false));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testLookupEligibilityStatusByTraceNumber_should_do_nothing_when_PersonList_is_emty() {
        // arrange
        EligibilityResponseProcessor eligibilityResponseProcessor = new EligibilityResponseProcessor();
        eligibilityResponseProcessor.setKeyValueStorageService(this.keyValueStorageServiceMock);

        List<Person> people = new ArrayList<Person>();

        Provider provider = new Provider();
        provider.setPeople(people);

        List<Provider> providers = new ArrayList<Provider>();
        providers.add(provider);

        Payer payer = new Payer();
        payer.setProviders(providers);

        List<Payer> payers = new ArrayList<Payer>();
        payers.add(payer);

        InquiryResponseTransaction inquiryResponseTransaction = new InquiryResponseTransaction();
        inquiryResponseTransaction.setPayers(payers);

        Collection<InquiryResponseTransaction> inquiryResponseTransactions = new ArrayList<InquiryResponseTransaction>();
        inquiryResponseTransactions.add(inquiryResponseTransaction);

        ResponseMessage testResponseMessage = new ResponseMessage();
        testResponseMessage.setInquiryResponseTransactions(inquiryResponseTransactions);

        exchange.getIn().setBody(testResponseMessage);

        // act
        eligibilityResponseProcessor.lookupEligibilityStatusByTraceNumber(exchange);

        // assert
        verify(this.keyValueStorageServiceMock, never()).get(anyString(), isA(Class.class));

        List<Eligibility> actualEligibilities = exchange.getIn().getBody(List.class);
        assertThat(actualEligibilities, notNullValue());
        assertThat(actualEligibilities.size(), equalTo(0));
        assertThat(TestUtils.isExchangeStopped(exchange), equalTo(false));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testLookupEligibilityStatusByTraceNumber_should_do_nothing_when_Person_is_null() {
        // arrange
        EligibilityResponseProcessor eligibilityResponseProcessor = new EligibilityResponseProcessor();
        eligibilityResponseProcessor.setKeyValueStorageService(this.keyValueStorageServiceMock);

        Person person = null;

        List<Person> people = new ArrayList<Person>();
        people.add(person);

        Provider provider = new Provider();
        provider.setPeople(people);

        List<Provider> providers = new ArrayList<Provider>();
        providers.add(provider);

        Payer payer = new Payer();
        payer.setProviders(providers);

        List<Payer> payers = new ArrayList<Payer>();
        payers.add(payer);

        InquiryResponseTransaction inquiryResponseTransaction = new InquiryResponseTransaction();
        inquiryResponseTransaction.setPayers(payers);

        Collection<InquiryResponseTransaction> inquiryResponseTransactions = new ArrayList<InquiryResponseTransaction>();
        inquiryResponseTransactions.add(inquiryResponseTransaction);

        ResponseMessage testResponseMessage = new ResponseMessage();
        testResponseMessage.setInquiryResponseTransactions(inquiryResponseTransactions);

        exchange.getIn().setBody(testResponseMessage);

        // act
        eligibilityResponseProcessor.lookupEligibilityStatusByTraceNumber(exchange);

        // assert
        verify(this.keyValueStorageServiceMock, never()).get(anyString(), isA(Class.class));

        List<Eligibility> actualEligibilities = exchange.getIn().getBody(List.class);
        assertThat(actualEligibilities, notNullValue());
        assertThat(actualEligibilities.size(), equalTo(0));
        assertThat(TestUtils.isExchangeStopped(exchange), equalTo(false));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testLookupEligibilityStatusByTraceNumber_should_log_error_message_when_exception_happens_in_KeyValueStorageService() {
        // arrange
        ArgumentCaptor<LoggingEvent> loggingEventCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        String testTraceNumber = UUID.randomUUID().toString();
        String fakeExceptionMessage = "this is a fake exception";

        Mockito.when(this.keyValueStorageServiceMock.get(eq(testTraceNumber), eq(String.class)))
                .thenThrow(new RuntimeException(fakeExceptionMessage));

        EligibilityResponseProcessor eligibilityResponseProcessor = new EligibilityResponseProcessor();
        eligibilityResponseProcessor.setKeyValueStorageService(this.keyValueStorageServiceMock);

        Person person = new Person();
        person.setOriginatingTraceNumber(testTraceNumber);

        List<Person> people = new ArrayList<Person>();
        people.add(person);

        Provider provider = new Provider();
        provider.setPeople(people);

        List<Provider> providers = new ArrayList<Provider>();
        providers.add(provider);

        Payer payer = new Payer();
        payer.setProviders(providers);

        List<Payer> payers = new ArrayList<Payer>();
        payers.add(payer);

        InquiryResponseTransaction inquiryResponseTransaction = new InquiryResponseTransaction();
        inquiryResponseTransaction.setPayers(payers);

        Collection<InquiryResponseTransaction> inquiryResponseTransactions = new ArrayList<InquiryResponseTransaction>();
        inquiryResponseTransactions.add(inquiryResponseTransaction);

        ResponseMessage testResponseMessage = new ResponseMessage();
        testResponseMessage.setInquiryResponseTransactions(inquiryResponseTransactions);

        exchange.getIn().setBody(testResponseMessage);

        // act
        eligibilityResponseProcessor.lookupEligibilityStatusByTraceNumber(exchange);

        // assert
        verify(this.keyValueStorageServiceMock, times(1)).get(eq(testTraceNumber), isA(Class.class));

        List<Eligibility> actualEligibilities = exchange.getIn().getBody(List.class);
        assertThat(actualEligibilities, nullValue());
        assertThat(TestUtils.isExchangeStopped(exchange), equalTo(true));

        // assertion for logging
        verify(this.logAppenderMock, atLeastOnce()).doAppend(loggingEventCaptor.capture());
        assertThat(loggingEventCaptor.getValue().getRenderedMessage(),
                allOf(containsString(
                        "An exception happens when looking up eligibility status from response 271 JSON file, error:"),
                        endsWith(fakeExceptionMessage)));
        assertThat(loggingEventCaptor.getValue().getLevel(), equalTo(Level.ERROR));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testLookupEligibilityStatusByTraceNumber_should_log_message_when_eligibility_is_not_found_in_level_db() {
        // arrange
        ArgumentCaptor<LoggingEvent> loggingEventCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        String testTraceNumber = UUID.randomUUID().toString();

        Mockito.when(this.keyValueStorageServiceMock.get(eq(testTraceNumber), eq(String.class)))
                .thenReturn(null);

        EligibilityResponseProcessor eligibilityResponseProcessor = new EligibilityResponseProcessor();
        eligibilityResponseProcessor.setKeyValueStorageService(this.keyValueStorageServiceMock);

        Person person = new Person();
        person.setOriginatingTraceNumber(testTraceNumber);

        List<Person> people = new ArrayList<Person>();
        people.add(person);

        Provider provider = new Provider();
        provider.setPeople(people);

        List<Provider> providers = new ArrayList<Provider>();
        providers.add(provider);

        Payer payer = new Payer();
        payer.setProviders(providers);

        List<Payer> payers = new ArrayList<Payer>();
        payers.add(payer);

        InquiryResponseTransaction inquiryResponseTransaction = new InquiryResponseTransaction();
        inquiryResponseTransaction.setPayers(payers);

        Collection<InquiryResponseTransaction> inquiryResponseTransactions = new ArrayList<InquiryResponseTransaction>();
        inquiryResponseTransactions.add(inquiryResponseTransaction);

        ResponseMessage testResponseMessage = new ResponseMessage();
        testResponseMessage.setInquiryResponseTransactions(inquiryResponseTransactions);

        exchange.getIn().setBody(testResponseMessage);

        // act
        eligibilityResponseProcessor.lookupEligibilityStatusByTraceNumber(exchange);

        // assert
        verify(this.keyValueStorageServiceMock, times(1)).get(eq(testTraceNumber), isA(Class.class));

        List<Eligibility> actualEligibilities = exchange.getIn().getBody(List.class);
        assertThat(actualEligibilities, notNullValue());
        assertThat(actualEligibilities.size(), equalTo(0));
        assertThat(TestUtils.isExchangeStopped(exchange), equalTo(false));

        // assertion for logging
        verify(this.logAppenderMock, atLeastOnce()).doAppend(loggingEventCaptor.capture());
        assertThat(loggingEventCaptor.getValue().getRenderedMessage(), allOf(
                containsString("Not found eligibility in LevelDB for trace number:"), containsString(testTraceNumber)));
        assertThat(loggingEventCaptor.getValue().getLevel(), equalTo(Level.INFO));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testLookupEligibilityStatusByTraceNumber_should_set_eligibility_status_to_inactive_when_payer_is_not_matched() {
        // arrange
        ArgumentCaptor<LoggingEvent> loggingEventCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        String testPayerId = "testPayerId";
        BigInteger testEligibilitySk = BigInteger.TEN;

        Eligibility testEligibility = new Eligibility();
        testEligibility.setEligibilitySK(testEligibilitySk);
        testEligibility.setEligibilityStatusName(EligibilityStatusName.PENDING);
        testEligibility.setPayerID(testPayerId);

        String testTraceNumber = UUID.randomUUID().toString();

        Mockito.when(this.keyValueStorageServiceMock.get(eq(testTraceNumber), eq(String.class)))
                .thenReturn(GSONProvider.ToJson(testEligibility));

        EligibilityResponseProcessor eligibilityResponseProcessor = new EligibilityResponseProcessor();
        eligibilityResponseProcessor.setKeyValueStorageService(this.keyValueStorageServiceMock);

        Person person = new Person();
        person.setOriginatingTraceNumber(testTraceNumber);

        List<Person> people = new ArrayList<Person>();
        people.add(person);

        Provider provider = new Provider();
        provider.setPeople(people);

        List<Provider> providers = new ArrayList<Provider>();
        providers.add(provider);

        Payer payer = new Payer();
        payer.setProviders(providers);

        List<Payer> payers = new ArrayList<Payer>();
        payers.add(payer);

        InquiryResponseTransaction inquiryResponseTransaction = new InquiryResponseTransaction();
        inquiryResponseTransaction.setPayers(payers);

        Collection<InquiryResponseTransaction> inquiryResponseTransactions = new ArrayList<InquiryResponseTransaction>();
        inquiryResponseTransactions.add(inquiryResponseTransaction);

        ResponseMessage testResponseMessage = new ResponseMessage();
        testResponseMessage.setInquiryResponseTransactions(inquiryResponseTransactions);

        exchange.getIn().setBody(testResponseMessage);

        // act
        eligibilityResponseProcessor.lookupEligibilityStatusByTraceNumber(exchange);

        // assert
        verify(this.keyValueStorageServiceMock, times(1)).get(eq(testTraceNumber), isA(Class.class));

        List<Eligibility> actualEligibilities = exchange.getIn().getBody(List.class);
        assertThat(actualEligibilities, notNullValue());
        assertThat(actualEligibilities.size(), equalTo(1));
        assertThat(actualEligibilities.get(0).getEligibilitySK(), equalTo(testEligibilitySk));
        assertThat(actualEligibilities.get(0).getEligibilityStatusName(), equalTo(EligibilityStatusName.INACTIVE));
        assertThat(TestUtils.isExchangeStopped(exchange), equalTo(false));

        // assertion for logging
        verify(this.logAppenderMock, atLeastOnce()).doAppend(loggingEventCaptor.capture());
        assertThat(loggingEventCaptor.getValue().getRenderedMessage(),
                allOf(containsString("Not found payerId:"), containsString(testPayerId),
                        containsString("in response 271 JSON file for trace number:"), containsString(testTraceNumber),
                        containsString("set eligibility status to INACTIVE for eligibility sk:"),
                        containsString(testEligibilitySk.toString())));
        assertThat(loggingEventCaptor.getValue().getLevel(), equalTo(Level.INFO));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testLookupEligibilityStatusByTraceNumber_should_set_eligibility_status_to_inactive_when_payer_is_matched_but_no_benefits() {
        // arrange
        ArgumentCaptor<LoggingEvent> loggingEventCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        long testPayerId = 123;
        BigInteger testEligibilitySk = BigInteger.TEN;

        Eligibility testEligibility = new Eligibility();
        testEligibility.setEligibilitySK(testEligibilitySk);
        testEligibility.setEligibilityStatusName(EligibilityStatusName.PENDING);
        testEligibility.setPayerID(String.valueOf(testPayerId));

        String testTraceNumber = UUID.randomUUID().toString();

        Mockito.when(this.keyValueStorageServiceMock.get(eq(testTraceNumber), eq(String.class)))
                .thenReturn(GSONProvider.ToJson(testEligibility));

        EligibilityResponseProcessor eligibilityResponseProcessor = new EligibilityResponseProcessor();
        eligibilityResponseProcessor.setKeyValueStorageService(this.keyValueStorageServiceMock);

        Person person = new Person();
        person.setOriginatingTraceNumber(testTraceNumber);

        List<Person> people = new ArrayList<Person>();
        people.add(person);

        Provider provider = new Provider();
        provider.setPeople(people);

        List<Provider> providers = new ArrayList<Provider>();
        providers.add(provider);

        Payer payer = new Payer();
        payer.setId(testPayerId);
        payer.setProviders(providers);

        List<Payer> payers = new ArrayList<Payer>();
        payers.add(payer);

        InquiryResponseTransaction inquiryResponseTransaction = new InquiryResponseTransaction();
        inquiryResponseTransaction.setPayers(payers);

        Collection<InquiryResponseTransaction> inquiryResponseTransactions = new ArrayList<InquiryResponseTransaction>();
        inquiryResponseTransactions.add(inquiryResponseTransaction);

        ResponseMessage testResponseMessage = new ResponseMessage();
        testResponseMessage.setInquiryResponseTransactions(inquiryResponseTransactions);

        exchange.getIn().setBody(testResponseMessage);

        // act
        eligibilityResponseProcessor.lookupEligibilityStatusByTraceNumber(exchange);

        // assert
        verify(this.keyValueStorageServiceMock, times(1)).get(eq(testTraceNumber), isA(Class.class));

        List<Eligibility> actualEligibilities = exchange.getIn().getBody(List.class);
        assertThat(actualEligibilities, notNullValue());
        assertThat(actualEligibilities.size(), equalTo(1));
        assertThat(actualEligibilities.get(0).getEligibilitySK(), equalTo(testEligibilitySk));
        assertThat(actualEligibilities.get(0).getEligibilityStatusName(), equalTo(EligibilityStatusName.INACTIVE));
        assertThat(TestUtils.isExchangeStopped(exchange), equalTo(false));

        // assertion for logging
        verify(this.logAppenderMock, atLeastOnce()).doAppend(loggingEventCaptor.capture());
        assertThat(loggingEventCaptor.getValue().getRenderedMessage(),
                allOf(containsString("No Benefits found in response 271 JSON file for trace number:"),
                        containsString(testTraceNumber),
                        containsString("set eligibility status to INACTIVE for eligibility sk:"),
                        containsString(testEligibilitySk.toString())));
        assertThat(loggingEventCaptor.getValue().getLevel(), equalTo(Level.INFO));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testLookupEligibilityStatusByTraceNumber_should_set_eligibility_status_to_inactive_when_payer_is_matched_and_BenefitInformationCodeType_is_not_ActiveCoverage() {
        // arrange
        ArgumentCaptor<LoggingEvent> loggingEventCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        long testPayerId = 123;
        BigInteger testEligibilitySk = BigInteger.TEN;
        BenefitInformationCodeType testBenefitInformationCodeType = BenefitInformationCodeType.CostContainment;

        Eligibility testEligibility = new Eligibility();
        testEligibility.setEligibilitySK(testEligibilitySk);
        testEligibility.setEligibilityStatusName(EligibilityStatusName.PENDING);
        testEligibility.setPayerID(String.valueOf(testPayerId));

        String testTraceNumber = UUID.randomUUID().toString();

        Mockito.when(this.keyValueStorageServiceMock.get(eq(testTraceNumber), eq(String.class)))
                .thenReturn(GSONProvider.ToJson(testEligibility));

        EligibilityResponseProcessor eligibilityResponseProcessor = new EligibilityResponseProcessor();
        eligibilityResponseProcessor.setKeyValueStorageService(this.keyValueStorageServiceMock);

        Benefit benefit = new Benefit();
        benefit.setInformationCode(testBenefitInformationCodeType);

        List<Benefit> benefits = new ArrayList<Benefit>();
        benefits.add(benefit);

        Person person = new Person();
        person.setOriginatingTraceNumber(testTraceNumber);
        person.setBenefits(benefits);

        List<Person> people = new ArrayList<Person>();
        people.add(person);

        Provider provider = new Provider();
        provider.setPeople(people);

        List<Provider> providers = new ArrayList<Provider>();
        providers.add(provider);

        Payer payer = new Payer();
        payer.setId(testPayerId);
        payer.setProviders(providers);

        List<Payer> payers = new ArrayList<Payer>();
        payers.add(payer);

        InquiryResponseTransaction inquiryResponseTransaction = new InquiryResponseTransaction();
        inquiryResponseTransaction.setPayers(payers);

        Collection<InquiryResponseTransaction> inquiryResponseTransactions = new ArrayList<InquiryResponseTransaction>();
        inquiryResponseTransactions.add(inquiryResponseTransaction);

        ResponseMessage testResponseMessage = new ResponseMessage();
        testResponseMessage.setInquiryResponseTransactions(inquiryResponseTransactions);

        exchange.getIn().setBody(testResponseMessage);

        // act
        eligibilityResponseProcessor.lookupEligibilityStatusByTraceNumber(exchange);

        // assert
        verify(this.keyValueStorageServiceMock, times(1)).get(eq(testTraceNumber), isA(Class.class));

        List<Eligibility> actualEligibilities = exchange.getIn().getBody(List.class);
        assertThat(actualEligibilities, notNullValue());
        assertThat(actualEligibilities.size(), equalTo(1));
        assertThat(actualEligibilities.get(0).getEligibilitySK(), equalTo(testEligibilitySk));
        assertThat(actualEligibilities.get(0).getEligibilityStatusName(), equalTo(EligibilityStatusName.INACTIVE));
        assertThat(TestUtils.isExchangeStopped(exchange), equalTo(false));

        // assertion for logging
        verify(this.logAppenderMock, atLeastOnce()).doAppend(loggingEventCaptor.capture());
        assertThat(loggingEventCaptor.getValue().getRenderedMessage(),
                allOf(containsString("BenefitInformationCodeType:"),
                        containsString(testBenefitInformationCodeType.toString()),
                        containsString("in response 271 JSON file for trace number:"), containsString(testTraceNumber),
                        containsString("set eligibility status to INACTIVE for eligibility sk:"),
                        containsString(testEligibilitySk.toString())));
        assertThat(loggingEventCaptor.getValue().getLevel(), equalTo(Level.INFO));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testLookupEligibilityStatusByTraceNumber_should_set_eligibility_status_to_active_when_payer_is_matched_and_BenefitInformationCodeType_is_ActiveCoverage() {
        // arrange
        ArgumentCaptor<LoggingEvent> loggingEventCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        long testPayerId = 123;
        BigInteger testEligibilitySk = BigInteger.TEN;
        BenefitInformationCodeType testBenefitInformationCodeType = BenefitInformationCodeType.ActiveCoverage;

        Eligibility testEligibility = new Eligibility();
        testEligibility.setEligibilitySK(testEligibilitySk);
        testEligibility.setEligibilityStatusName(EligibilityStatusName.PENDING);
        testEligibility.setPayerID(String.valueOf(testPayerId));

        String testTraceNumber = UUID.randomUUID().toString();

        Mockito.when(this.keyValueStorageServiceMock.get(eq(testTraceNumber), eq(String.class)))
                .thenReturn(GSONProvider.ToJson(testEligibility));

        EligibilityResponseProcessor eligibilityResponseProcessor = new EligibilityResponseProcessor();
        eligibilityResponseProcessor.setKeyValueStorageService(this.keyValueStorageServiceMock);

        Benefit benefit = new Benefit();
        benefit.setInformationCode(testBenefitInformationCodeType);

        List<Benefit> benefits = new ArrayList<Benefit>();
        benefits.add(benefit);

        Person person = new Person();
        person.setOriginatingTraceNumber(testTraceNumber);
        person.setBenefits(benefits);

        List<Person> people = new ArrayList<Person>();
        people.add(person);

        Provider provider = new Provider();
        provider.setPeople(people);

        List<Provider> providers = new ArrayList<Provider>();
        providers.add(provider);

        Payer payer = new Payer();
        payer.setId(testPayerId);
        payer.setProviders(providers);

        List<Payer> payers = new ArrayList<Payer>();
        payers.add(payer);

        InquiryResponseTransaction inquiryResponseTransaction = new InquiryResponseTransaction();
        inquiryResponseTransaction.setPayers(payers);

        Collection<InquiryResponseTransaction> inquiryResponseTransactions = new ArrayList<InquiryResponseTransaction>();
        inquiryResponseTransactions.add(inquiryResponseTransaction);

        ResponseMessage testResponseMessage = new ResponseMessage();
        testResponseMessage.setInquiryResponseTransactions(inquiryResponseTransactions);

        exchange.getIn().setBody(testResponseMessage);

        // act
        eligibilityResponseProcessor.lookupEligibilityStatusByTraceNumber(exchange);

        // assert
        verify(this.keyValueStorageServiceMock, times(1)).get(eq(testTraceNumber), isA(Class.class));

        List<Eligibility> actualEligibilities = exchange.getIn().getBody(List.class);
        assertThat(actualEligibilities, notNullValue());
        assertThat(actualEligibilities.size(), equalTo(1));
        assertThat(actualEligibilities.get(0).getEligibilitySK(), equalTo(testEligibilitySk));
        assertThat(actualEligibilities.get(0).getEligibilityStatusName(), equalTo(EligibilityStatusName.ACTIVE));
        assertThat(TestUtils.isExchangeStopped(exchange), equalTo(false));

        // assertion for logging
        verify(this.logAppenderMock, atLeastOnce()).doAppend(loggingEventCaptor.capture());
        assertThat(loggingEventCaptor.getValue().getRenderedMessage(),
                allOf(containsString("BenefitInformationCodeType:"),
                        containsString(testBenefitInformationCodeType.toString()),
                        containsString("in response 271 JSON file for trace number:"), containsString(testTraceNumber),
                        containsString("set eligibility status to ACTIVE for eligibility sk:"),
                        containsString(testEligibilitySk.toString())));
        assertThat(loggingEventCaptor.getValue().getLevel(), equalTo(Level.INFO));
    }

    @Test
    public void testUpdateEligiblityStatus_should_throw_IllegalArgumentException_when_exchange_body_is_not_instance_of_list_of_eligibility() {
        // arrange
        EligibilityResponseProcessor eligibilityResponseProcessor = new EligibilityResponseProcessor();
        eligibilityResponseProcessor.setOracleDataService(this.oracleDataServiceMock);

        exchange.getIn().setBody(new Eligibility());

        try {
            // act
            eligibilityResponseProcessor.updateEligiblityStatus(exchange);

        } catch (IllegalArgumentException e) {
            // assert
            Assert.assertThat(e.getMessage(), containsString("Exchange body must be a list of eligibilities (List<Eligibility>)"));
            verify(this.oracleDataServiceMock, never()).updateEligibilityStatus(any(Eligibility.class));

            return;
        }

        fail("IllegalArgumentException is expected to be thrown, but it was not.");
    }

    @Test
    public void testUpdateEligiblityStatus_should_call_OracleDataService_to_update_status_and_log_error_when_exception_happens() {
        // arrange
        ArgumentCaptor<LoggingEvent> loggingEventCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        Eligibility eligibility1 = new Eligibility();
        eligibility1.setEligibilitySK(BigInteger.ONE);
        eligibility1.setEligibilityStatusName(EligibilityStatusName.ACTIVE);

        Eligibility eligibility2 = new Eligibility();
        eligibility2.setEligibilitySK(BigInteger.TEN);
        eligibility2.setEligibilityStatusName(EligibilityStatusName.INACTIVE);

        final Eligibility eligibilityFailed = new Eligibility();
        eligibilityFailed.setEligibilitySK(BigInteger.valueOf(5));
        eligibilityFailed.setEligibilityStatusName(EligibilityStatusName.OTHER);

        List<Eligibility> testEligibilities = new ArrayList<Eligibility>();
        testEligibilities.add(eligibility1);
        testEligibilities.add(eligibility2);
        testEligibilities.add(eligibilityFailed);

        final String fakeExceptionMessage = "this is a fake Exception";

        Mockito.doAnswer(new Answer<Void>(){
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Eligibility eligibility = invocation.getArgumentAt(0, Eligibility.class);
                if (eligibility.equals(eligibilityFailed)) {
                    throw new SandataRuntimeException(fakeExceptionMessage);
                }
                return null;
            }
        }).when(this.oracleDataServiceMock).updateEligibilityStatus(any(Eligibility.class));

        EligibilityResponseProcessor eligibilityResponseProcessor = new EligibilityResponseProcessor();
        eligibilityResponseProcessor.setOracleDataService(this.oracleDataServiceMock);

        exchange.getIn().setBody(testEligibilities);

        // act
        eligibilityResponseProcessor.updateEligiblityStatus(exchange);

        // assert
        verify(this.oracleDataServiceMock, times(1)).updateEligibilityStatus(eq(eligibility1));
        verify(this.oracleDataServiceMock, times(1)).updateEligibilityStatus(eq(eligibility2));

        // assertion for logging
        verify(this.logAppenderMock, atLeastOnce()).doAppend(loggingEventCaptor.capture());
        assertThat(loggingEventCaptor.getValue().getRenderedMessage(), containsString("success: 2, failed: 1"));
        assertThat(loggingEventCaptor.getValue().getLevel(), equalTo(Level.INFO));

        //  assertion for logging error
        LoggingEvent loggingErrorEvent = null;
        for (LoggingEvent le : loggingEventCaptor.getAllValues()) {
            if (le.getLevel().equals(Level.ERROR)) {
                loggingErrorEvent = le;
            }
        }
        assertThat(loggingErrorEvent, notNullValue());
        assertThat(loggingErrorEvent.getRenderedMessage(),
                allOf(containsString("An error happened when updating Eligibility Status"),
                        containsString(eligibilityFailed.getEligibilitySK().toString()),
                        containsString(eligibilityFailed.getEligibilityStatusName().toString()),
                        endsWith(fakeExceptionMessage)));
    }
    
}
