package com.sandata.lab.eligibility.processors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

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
import com.sandata.lab.eligibility.providers.GsonProvider;
import com.sandata.lab.eligibility.utils.camel.CamelUtils;
import com.sandata.lab.eligibility.utils.log.LoggingUtils;

/**
 * Processor to parse and process response 271 JSON files from ELIGIBILL
 */
public class EligibilityResponseProcessor {

    /**
     * Bean identification is configured in blueprint.xml
     */
    public static final String BEAN_NAME = "eligibilityResponseProcessor";

    /**
     * Method {@link #lookupEligibilityStatusByTraceNumber(Exchange)} is used in Camel routes
     */
    public static final String LOOKUP_ELIG_STATUS_BY_TRACE_NUMBER_METHOD = "lookupEligibilityStatusByTraceNumber";

    /**
     * Method {@link #updateEligiblityStatus(Exchange)} is used in Camel routes
     */
    public static final String UPDATE_ELIG_STATUS_METHOD = "updateEligiblityStatus";


    /**
     * Method {@link #convertJsonToResponseMessage(Exchange) is used in Camel routes
     */
    public static final String CONVERT_JSON_TO_RESPONSE_MESSAGE_METHOD = "convertJsonToResponseMessage";

    private static final Logger LOGGER = LoggerFactory.getLogger(EligibilityResponseProcessor.class);

    private Map<BenefitInformationCodeType, EligibilityStatusName> eligibilityStatusMapping;

    private OracleDataService oracleDataService;
    private KeyValueStorageService keyValueStorageService;

    /**
     * Initializes {@link EligibilityResponseProcessor} without parameters
     */
    public EligibilityResponseProcessor() {
        this.eligibilityStatusMapping = new HashMap<BenefitInformationCodeType, EligibilityStatusName>();
    }

    /**
     * Initializes {@link EligibilityResponseProcessor} with Eligibility Status
     * Mapping
     * 
     * @param eligibilityStatusMappingStr
     *            eligibility status mapping in format of
     *            {BenefitInformationCodeType1=EligibilityStatusName1,
     *            ActiveCoverage=Active}
     */
    public EligibilityResponseProcessor(String eligibilityStatusMappingStr) {
        this.eligibilityStatusMapping = toMap(eligibilityStatusMappingStr);
    }

    /**
     * @return the oracleDataService
     */
    public OracleDataService getOracleDataService() {
        return oracleDataService;
    }

    /**
     * @param oracleDataService the oracleDataService to set
     */
    public void setOracleDataService(OracleDataService oracleDataService) {
        this.oracleDataService = oracleDataService;
    }

    /**
     * @return the keyValueStorageService
     */
    public KeyValueStorageService getKeyValueStorageService() {
        return keyValueStorageService;
    }

    /**
     * @param keyValueStorageService the keyValueStorageService to set
     */
    public void setKeyValueStorageService(KeyValueStorageService keyValueStorageService) {
        this.keyValueStorageService = keyValueStorageService;
    }

    /**
     * @return the eligibilityStatusMapping
     */
    public Map<BenefitInformationCodeType, EligibilityStatusName> getEligibilityStatusMapping() {
        return eligibilityStatusMapping;
    }

    /**
     * Converts response 271 JSON to {@link ResponseMessage}
     * 
     * @param exchange
     *            an instance of {@link Exchange} whose body contains response
     *            271 JSON
     * @return an instance of {@link ResponseMessage} from JSON
     * 
     * @throws SandataRuntimeException
     *             (runtime) if an exception happens when deserializing JSON to
     *             ResponseMessage object
     */
    public ResponseMessage convertJsonToResponseMessage(final Exchange exchange) {
        String json = exchange.getIn().getBody(String.class);
        String dateTimeFormat = "yyyy-MM-dd'T'HH:mm:ss";
        ResponseMessage responseMessage = null;
        try {
            responseMessage = new GsonProvider(dateTimeFormat).fromJson(json, ResponseMessage.class);
            LOGGER.info(LoggingUtils.getLogMessageInfo(this, CONVERT_JSON_TO_RESPONSE_MESSAGE_METHOD,
                    "Completed converting JSON to POJO-ResponseMessage"));

        } catch (Exception e) {
            String errorMessage = LoggingUtils.getErrorMessageInfo(this, CONVERT_JSON_TO_RESPONSE_MESSAGE_METHOD,
                    new StringBuilder()
                            .append("An exception happens when converting response 271 JSON to ReponseMessage object, error: ")
                            .append(e.getMessage()).toString());
            LOGGER.error(errorMessage, e);
            throw new SandataRuntimeException(errorMessage, e);
        }

        return responseMessage;
    }

    /**
     * Parses response 271 JSON and lookup eligibility status by trace number
     * (link between Eligibility SK and trace name is stored in levelDB). The
     * exchange header {@link Exchange#ROUTE_STOP} is set to
     * {@link Boolean#TRUE} if an error happens
     * 
     * @param exchange
     *            an instance of {@link Exchange} whose body contains an
     *            instance of {@link ResponseMessage}
     * @throws IllegalArgumentException
     *             (runtime) if exchange body does not contain an instance of
     *             {@link ResponseMessage}
     */
    public void lookupEligibilityStatusByTraceNumber(final Exchange exchange) {
        ResponseMessage responseMessage = exchange.getIn().getBody(ResponseMessage.class);
        Validate.isTrue(responseMessage != null, "Exchange body must be a ResponseMessage");

        try {
            List<Eligibility> eligibilites = new ArrayList<>();
            for (InquiryResponseTransaction inquiryResponseTransaction : getInquiryResponseTransactions(responseMessage)) {
                processResponse(inquiryResponseTransaction, eligibilites);
            }

            // let next processor update eligibility status to database
            exchange.getIn().setBody(eligibilites);

        } catch (Exception e) {
            LOGGER.error(
                    LoggingUtils.getLogMessageInfo(this, LOOKUP_ELIG_STATUS_BY_TRACE_NUMBER_METHOD,
                            "An exception happens when looking up eligibility status from response 271 JSON file, error: {}"),
                    e.getMessage(), e);

            CamelUtils.stopProcessingExchange(exchange);
        }
    }

    private List<InquiryResponseTransaction> getInquiryResponseTransactions(ResponseMessage responseMessage) {
        List<InquiryResponseTransaction> inquiryResponseTransactions = new ArrayList<InquiryResponseTransaction>();
        if (responseMessage.getInquiryResponseTransactions() != null) {
            inquiryResponseTransactions.addAll(responseMessage.getInquiryResponseTransactions());
        }

        return inquiryResponseTransactions;
    }

    private List<Payer> getPayers(InquiryResponseTransaction inquiryResponseTransaction) {
        List<Payer> payers = new ArrayList<Payer>();
        if (inquiryResponseTransaction != null && inquiryResponseTransaction.getPayers() != null) {
            payers.addAll(inquiryResponseTransaction.getPayers());
        }

        return payers;
    }

    private List<Provider> getProviders(Payer payer) {
        List<Provider> providers = new ArrayList<Provider>();
        if (payer != null && payer.getProviders() != null) {
            providers.addAll(payer.getProviders());
        }

        return providers;
    }

    private List<Person> getPersons(Provider provider) {
        List<Person> persons = new ArrayList<Person>();
        if (provider != null && provider.getPeople() != null) {
            persons.addAll(provider.getPeople());
        }

        return persons;
    }

    private void processResponse(final InquiryResponseTransaction inquiryResponseTransaction, final List<Eligibility> eligibilites) {
        List<Payer> payers = getPayers(inquiryResponseTransaction);
        for (Payer payer : payers) {
            processPayer(payer, eligibilites, payers);
        }
    }

    private void processPayer(Payer payer, List<Eligibility> eligibilites, final List<Payer> payers) {
        for (Provider provider : getProviders(payer)) {
            processProvider(provider, eligibilites, payers);
        }
    }

    private void processProvider(Provider provider, List<Eligibility> eligibilites, final List<Payer> payers) {
        for (Person person : getPersons(provider)) {
            processPerson(person, eligibilites, payers);
        }
    }

    private void processPerson(Person person, List<Eligibility> eligibilities, List<Payer> payers) {
        if (person == null) {
            return;
        }

        String originatingTraceNumber = person.getOriginatingTraceNumber();

        // looking up eligibility by originating trace number in LevelDB
        String json = getKeyValueStorageService().get(originatingTraceNumber, String.class);
        Eligibility eligibility = (Eligibility) GSONProvider.FromJson(json, Eligibility.class);

        if (eligibility == null) {
            // not found eligibility in LevelDB
            LOGGER.info(LoggingUtils.getLogMessageInfo(this, LOOKUP_ELIG_STATUS_BY_TRACE_NUMBER_METHOD,
                    "Not found eligibility in LevelDB for trace number: {}"), originatingTraceNumber);
            return;
        }

        LOGGER.info(LoggingUtils.getLogMessageInfo(this, LOOKUP_ELIG_STATUS_BY_TRACE_NUMBER_METHOD,
                "Found eligibility in LevelDB for trace number: {}"), originatingTraceNumber);

        if (isPayerMatched(eligibility.getPayerID(), payers)) {
            // update eligibility status based on response's benefit code
            LOGGER.info(
                    LoggingUtils.getLogMessageInfo(this, LOOKUP_ELIG_STATUS_BY_TRACE_NUMBER_METHOD,
                            "Found payerId: {} in response 271 JSON file for trace number: {}, eligibility sk: {}"),
                    eligibility.getPayerID(), originatingTraceNumber, eligibility.getEligibilitySK());
            updateEligibilityFromBenefits(eligibility, person.getBenefits(), originatingTraceNumber);

        } else {
            // no payer matched, set eligibility status to Inactive
            eligibility.setEligibilityStatusName(EligibilityStatusName.INACTIVE);
            LOGGER.info(
                    LoggingUtils.getLogMessageInfo(this, LOOKUP_ELIG_STATUS_BY_TRACE_NUMBER_METHOD,
                            "Not found payerId: {} in response 271 JSON file for trace number: {}, set eligibility status to INACTIVE for eligibility sk: {}"),
                    eligibility.getPayerID(), originatingTraceNumber, eligibility.getEligibilitySK());
        }

        // put eligibility to list to be updated to DB later
        eligibilities.add(eligibility);
    }

    private void updateEligibilityFromBenefits(Eligibility eligibility, Collection<Benefit> benefits, String originatingTraceNumber) {
        if (!CollectionUtils.isEmpty(benefits)) {
            // TODO: to confirm with US team for the rules
            // temporarily get the first benefit to compare BenefitInformationCodeType and set eligibility status.
            Benefit benifit = new ArrayList<>(benefits).get(0);

            if (BenefitInformationCodeType.ActiveCoverage.equals(benifit.getInformationCode())) {
                eligibility.setEligibilityStatusName(EligibilityStatusName.ACTIVE);

                LOGGER.info(
                        LoggingUtils.getLogMessageInfo(this, LOOKUP_ELIG_STATUS_BY_TRACE_NUMBER_METHOD,
                                "BenefitInformationCodeType: {} in response 271 JSON file for trace number: {}, set eligibility status to ACTIVE for eligibility sk: {}"),
                        benifit.getInformationCode(), originatingTraceNumber, eligibility.getEligibilitySK());

            } else {
                eligibility.setEligibilityStatusName(EligibilityStatusName.INACTIVE);

                LOGGER.info(
                        LoggingUtils.getLogMessageInfo(this, LOOKUP_ELIG_STATUS_BY_TRACE_NUMBER_METHOD,
                                "BenefitInformationCodeType: {} in response 271 JSON file for trace number: {}, set eligibility status to INACTIVE for eligibility sk: {}"),
                        benifit.getInformationCode(), originatingTraceNumber, eligibility.getEligibilitySK());
            }

        } else {
            eligibility.setEligibilityStatusName(EligibilityStatusName.INACTIVE);

            LOGGER.info(
                    LoggingUtils.getLogMessageInfo(this, LOOKUP_ELIG_STATUS_BY_TRACE_NUMBER_METHOD,
                            "No Benefits found in response 271 JSON file for trace number: {}, set eligibility status to INACTIVE for eligibility sk: {}"),
                    originatingTraceNumber, eligibility.getEligibilitySK());
        }
    }

    private boolean isPayerMatched(String payerId, List<Payer> payers) {
        for (Payer payer : payers) {
            if (StringUtils.equals(payerId, String.valueOf(payer.getId()))) {
                return true;
            }
        }

        return false;
    }

    /**
     * Updates eligibilities' status (which are from response 271 JSON file) to
     * database
     * 
     * @param exchange
     *            an instance of {@link Exchange} whose body contains a list of
     *            eligibilities
     * @throws IllegalArgumentException
     *             (runtime) if exchange body does not contain an instance of
     *             {@code List<Eligibility>}
     */
    public void updateEligiblityStatus(final Exchange exchange) {
        @SuppressWarnings("unchecked")
        List<Eligibility> eligibilities = exchange.getIn().getBody(List.class);

        Validate.isTrue(eligibilities != null, "Exchange body must be a list of eligibilities (List<Eligibility>)");

        String fileName = exchange.getIn().getHeader(Exchange.FILE_NAME, String.class);
        int numberOfSuccess = 0;
        int numberOfFailed = 0;

        LOGGER.info(
                LoggingUtils.getLogMessageInfo(this, UPDATE_ELIG_STATUS_METHOD,
                        "Updating eligibility status from response 271 JSON file:{} to database for {} eligibiliti(es) "),
                fileName, eligibilities.size());

        for (Eligibility eligibility : eligibilities) {
            try {
                getOracleDataService().updateEligibilityStatus(eligibility);
                numberOfSuccess++;

            } catch (SandataRuntimeException e) {

                numberOfFailed++;
                LOGGER.error(
                        LoggingUtils.getErrorMessageInfo(this, UPDATE_ELIG_STATUS_METHOD,
                                "An error happened when updating Eligibility Status:{} for Eligibility SK:{} , error: {}"),
                        eligibility.getEligibilityStatusName(), eligibility.getEligibilitySK(), e.getMessage(), e);
            }
        }

        LOGGER.info(
                LoggingUtils.getLogMessageInfo(this, UPDATE_ELIG_STATUS_METHOD,
                        "Updated eligibility status from response 271 JSON file:{} to database for {} eligibiliti(es), success: {}, failed: {}"),
                fileName, eligibilities.size(), numberOfSuccess, numberOfFailed);
    }

    /**
     * Converts a string in format of
     * '{BenefitInformationCodeType1=EligibilityStatusName1,
     * BenefitInformationCodeType2=EligibilityStatusName2}' to a
     * {@code Map<BenefitInformationCodeType, EligibilityStatusName>}
     * 
     * @param value
     *            a string in format of
     *            '{BenefitInformationCodeType1=EligibilityStatusName1,
     *            BenefitInformationCodeType2=EligibilityStatusName2}'
     * @return a {@code Map<BenefitInformationCodeType, EligibilityStatusName>}
     * @throws SandataRuntimeException
     *             (runtime) if specified values are not found in
     *             BenefitInformationCodeType or EligibilityStatusName
     *             enumerations
     */
    private Map<BenefitInformationCodeType, EligibilityStatusName> toMap(String value) {
        Map<BenefitInformationCodeType, EligibilityStatusName> map = new HashMap<BenefitInformationCodeType, EligibilityStatusName>();

        final String methodName = "toMap";

        if (!StringUtils.isEmpty(value)) {
            value = StringUtils.removeStart(value, "{");
            value = StringUtils.removeEnd(value, "}");

            // split the string to create key-value pairs
            String[] keyValuePairs = value.split(",");

            for (String pair : keyValuePairs) {
                String[] entry = pair.split("=");
                
                BenefitInformationCodeType benefitInformationCodeType;
                EligibilityStatusName eligibilityStatusName;

                try {
                    benefitInformationCodeType = Enum.valueOf(BenefitInformationCodeType.class, StringUtils.trimToEmpty(entry[0]));
                } catch (IllegalArgumentException ex) {
                    String errorMessage = LoggingUtils.getErrorMessageInfo(this, methodName, "'" + entry[0] + "' is not found in the BenefitInformationCodeType enum");
                    LOGGER.error(errorMessage, ex);
                    throw new SandataRuntimeException(errorMessage, ex);
                }

                try {
                    eligibilityStatusName = Enum.valueOf(EligibilityStatusName.class, StringUtils.upperCase(StringUtils.trimToEmpty(entry[1])));
                } catch (IllegalArgumentException ex) {
                    String errorMessage = LoggingUtils.getErrorMessageInfo(this, methodName, "'" + entry[1] + "' is not found in the EligibilityStatusName enum");
                    LOGGER.error(errorMessage, ex);
                    throw new SandataRuntimeException(errorMessage, ex);
                }

                map.put(benefitInformationCodeType, eligibilityStatusName);
            }
        }

        return map;
    }
}
