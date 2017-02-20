package com.sandata.lab.eligibility.processors;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.Eligibility;
import com.sandata.lab.data.model.dl.model.EligibilityStatusName;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.eligibility.api.KeyValueStorageService;
import com.sandata.lab.eligibility.impl.OracleDataService;
import com.sandata.lab.eligibility.model.inquiry.ClientFileDetail;
import com.sandata.lab.eligibility.utils.camel.CamelUtils;
import com.sandata.lab.eligibility.utils.constants.App;
import com.sandata.lab.eligibility.utils.data.DataMapper;
import com.sandata.lab.eligibility.utils.log.LoggingUtils;

/**
 * Processor to create and send eligibility inquiries to ELIGBILL
 */
public class EligibilityInquiryProcessor {
    /**
     * Bean identification is configured in blueprint.xml
     */
    public static final String BEAN_NAME = "eligibilityInquiryProcessor";

    /**
     * Method {@link #getEligibilityInquiryByPatientPayerSk(Exchange)} is used in Camel routes
     */
    public static final String GET_ELIGIBILITY_INQUIRY_BY_PATIENT_PAYER_SK_METHOD = "getEligibilityInquiryByPatientPayerSk";

    /**
     * Method {@link #convertToInquiry270JsonFile(Exchange)} is used in Camel routes
     */
    public static final String CONVERT_TO_INQUIRY_270_JSON_FILE_METHOD = "convertToInquiry270JsonFile";

    /**
     * Method {@link #insertEligibility(Exchange)} is used in Camel routes
     */
    public static final String INSERT_ELIGIBILITY_METHOD = "insertEligibility";

    /**
     * Method {@link #getSqlToQueryAllPatientPayersByBeId(Exchange)} is used in Camel routes
     */
    public static final String GET_SQL_TO_QUERY_ALL_PATIENT_PAYERS_BY_BE_ID_METHOD = "getSqlToQueryAllPatientPayersByBeId";

    private static final Logger LOGGER = LoggerFactory.getLogger(EligibilityInquiryProcessor.class);

    private OracleDataService oracleDataService;

    public OracleDataService getOracleDataService() {
        return oracleDataService;
    }

    public void setOracleDataService(OracleDataService oracleDataService) {
        this.oracleDataService = oracleDataService;
    }

    /**
     * Builds SQL to get all PT_PAYER_SK for active patients who has
     * CIN#(PT.PT_MEDICAID_ID) for the agency (business entity id)
     * 
     * @param exchange
     *            an instance of {@link Exchange} whose body contains Business
     *            Entity ID
     * @return a SQL query
     */
    public String getSqlToQueryAllPatientPayersByBeId(final Exchange exchange) {
        String beId = exchange.getIn().getBody(String.class);

        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("SELECT PP.PT_PAYER_SK");
        sqlBuilder.append(" ");
        sqlBuilder.append("FROM ");
        sqlBuilder.append("  COREDATA.PT JOIN COREDATA.PT_PAYER PP ON PT.PT_ID = PP.PT_ID AND PT.BE_ID = PP.BE_ID");
        sqlBuilder.append(" ");
        sqlBuilder.append("WHERE ");
        sqlBuilder.append("  (TO_CHAR(PT.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND PT.CURR_REC_IND = '1')");
        sqlBuilder.append("  AND (TO_CHAR(PP.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND PP.CURR_REC_IND = '1')");
        sqlBuilder.append("  AND TO_CHAR(PP.PT_PAYER_TERM_DATE, 'YYYY-MM-DD') = '9999-12-31'");
        sqlBuilder.append("  AND PT.BE_ID = '").append(beId).append("'");
        sqlBuilder.append("  AND UPPER(PT.PT_STATUS_NAME) = 'ACTIVE'");
        sqlBuilder.append("  AND PT.PT_MEDICAID_ID IS NOT NULL");

        return sqlBuilder.toString();
    }

    /**
     * Gets eligibility inquiry in Map&lt;String, Object&gt; for specified
     * PatientPayerSk
     * 
     * @param exchange
     *            an instance of {@link Exchange} whose body contains
     *            PatientPayerSk
     * @return an instance of Map&lt;String, Object&gt; for eligibility inquiry;
     *         the header {@link Exchange#ROUTE_STOP} is set to
     *         {@link Boolean#TRUE} if an error happens or cannot build
     *         eligibility inquiry map for the specified PatientPayerSk
     */
    public Map<String, Object> getEligibilityInquiryByPatientPayerSk(final Exchange exchange) {
        Map<String, Object> inquiry = null;

        Integer patientPayerSk = exchange.getIn().getBody(Integer.class);

        if (patientPayerSk == null || patientPayerSk <= 0) {
            LOGGER.error(
                    LoggingUtils.getErrorMessageInfo(this, GET_ELIGIBILITY_INQUIRY_BY_PATIENT_PAYER_SK_METHOD,
                            "Cannot create eligibility inquiry as PT_PAYER_SK = {}"),
                    patientPayerSk == null ? "null" : patientPayerSk);
            CamelUtils.stopProcessingExchange(exchange);
        }

        try {
            inquiry = oracleDataService.getEligibilityInquiryByPatientPayerSk(patientPayerSk);
        } catch (SandataRuntimeException e) {
            LOGGER.error(
                    LoggingUtils.getErrorMessageInfo(this, GET_ELIGIBILITY_INQUIRY_BY_PATIENT_PAYER_SK_METHOD,
                            "An exception happens when querying database to create eligibility inquiry for PT_PAYER_SK = {}. Error message: {}"),
                    patientPayerSk, e.getMessage(), e);
            CamelUtils.stopProcessingExchange(exchange);
        }

        if (inquiry == null) {
            LOGGER.error(
                    LoggingUtils.getErrorMessageInfo(this, GET_ELIGIBILITY_INQUIRY_BY_PATIENT_PAYER_SK_METHOD,
                            "Cannot create eligibility inquiry for PT_PAYER_SK = {} as cannot get enough information by joining tables: PT, BE, PT_PAYER and PT_PAYER_INS for creating inquiry"),
                    patientPayerSk);
            CamelUtils.stopProcessingExchange(exchange);
        }

        return inquiry;
    }

    /**
     * Converts Map&lt;String, Object&gt; for eligibility inquiry record to
     * {@link ClientFileDetail}. After converting, set value for the following
     * <ul>
     * <li>Exchange header {@link Exchange#FILE_NAME} to file name for saving inquiry JSON to file</li>
     * <li>Exchange header {@link App#TRACE_NUMBER_HEADER} to automatically-generated trace number</li>
     * <li>Exchange header {@link App#INQUIRY_RECORD_HEADER} to Map&lt;String, Object&gt; for eligibility inquiry record</li>
     * <li>Exchange body to an instance of {@link ByteArrayInputStream} for saving inquiry JSON to file</li>
     * </ul>
     * 
     * @param exchange
     *            an instance of {@link Exchange} whose body contains an
     *            instance of Map&lt;String, Object&gt; for eligibility inquiry
     */
    public void convertToInquiry270JsonFile(final Exchange exchange) {
        @SuppressWarnings("unchecked")
        Map<String, Object> record = exchange.getIn().getBody(Map.class);

        ClientFileDetail inquiry = (ClientFileDetail) new DataMapper().map(record, "com.sandata.lab.eligibility.model.inquiry.ClientFileDetail");
        inquiry.setTraceNumber(UUID.randomUUID().toString());

        // GEOR-5593 - Make AmountBilled optional in Inquiry_270.Json
        // ELIGIBILL service does not work if AmountBilled == null, so we need to set it to zero when creating inquiry 270 JSON file
        if (inquiry.getAmountBilled() == null) {
            inquiry.setAmountBilled(BigDecimal.ZERO);
        }

        // default Service Start Date to today
        if (inquiry.getServiceStartDate() == null) {
            inquiry.setServiceStartDate(new Date());
        }

        // default Service End Date to today
        if (inquiry.getServiceEndDate() == null) {
            inquiry.setServiceEndDate(new Date());
        }

        @SuppressWarnings("static-access")
        String json = new GSONProvider(App.DEFAULT_DATE_TIME_FORMAT_IN_INQUIRY_JSON).ToJson(inquiry);

        exchange.getIn().setHeader(Exchange.FILE_NAME, createInquiryFileName(record));
        exchange.getIn().setHeader(App.TRACE_NUMBER_HEADER, inquiry.getTraceNumber());
        exchange.getIn().setHeader(App.INQUIRY_RECORD_HEADER,  record);
        exchange.getIn().setBody(new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * Inserts a record to CoreData database based on eligibility inquiry
     * Map&lt;String, Object&gt;; then puts key=value of header
     * {@link App#TRACE_NUMBER_HEADER} and value=JSON of {@link Eligibility} to
     * LevelDB for keep the relationship between trace number and EligibilitySk
     * 
     * @param exchange
     *            an instance of {@link Exchange} whose header of
     *            {@link App#INQUIRY_RECORD_HEADER} has value
     */
    public void insertEligibility(final Exchange exchange) {
        Eligibility eligibility = createEligibility(exchange);
        int eligibilitySk;

        // insert eligibility to DB
        try {
            OracleMetadata oracleMetadata = com.sandata.lab.common.utils.data.DataMapper.getOracleMetadata(eligibility);
            Object jpubType = new com.sandata.lab.common.utils.data.DataMapper().map(eligibility);

            LOGGER.debug(
                    LoggingUtils.getLogMessageInfo(this, INSERT_ELIGIBILITY_METHOD,
                            "Inserting an eligibility to database, patientId={}, payerId={}"),
                    eligibility.getPatientID(), eligibility.getPayerID());

            eligibilitySk = oracleDataService.execute(ConnectionType.COREDATA, oracleMetadata.packageName(), oracleMetadata.insertMethod(), jpubType);

            LOGGER.debug(
                    LoggingUtils.getLogMessageInfo(this, INSERT_ELIGIBILITY_METHOD,
                            "Inserted an eligibility to database, patientId={}, payerId={}, eligibilitySk={}"),
                    eligibility.getPatientID(), eligibility.getPayerID(), eligibilitySk);

        } catch (SandataRuntimeException e) {
            LOGGER.error(
                    LoggingUtils.getErrorMessageInfo(this, INSERT_ELIGIBILITY_METHOD,
                            "An exception happens when inserting Eligibility to database. Error: {}"),
                    e.getMessage(), e);
            CamelUtils.stopProcessingExchange(exchange);
            return;
        }

        eligibility.setEligibilitySK(BigInteger.valueOf(eligibilitySk));
        String traceNumber = exchange.getIn().getHeader(App.TRACE_NUMBER_HEADER, String.class);

        // put key=trace-number / value = eligibility to key-value storage
        try {
            LOGGER.debug(
                    LoggingUtils.getLogMessageInfo(this, INSERT_ELIGIBILITY_METHOD,
                            "Putting key=traceNumber={} and value=eligibility to key-value storage, patientId={}, payerId={}"),
                    traceNumber, eligibility.getPatientID(), eligibility.getPayerID());

            @SuppressWarnings("static-access")
            String inquiryJson = new GSONProvider(App.DEFAULT_DATE_TIME_FORMAT_IN_JSON).ToJson(eligibility);
            KeyValueStorageService keyValueStorageService = exchange.getContext().getRegistry().lookupByNameAndType("keyValueStorageService", KeyValueStorageService.class);
            keyValueStorageService.put(traceNumber, inquiryJson);

            LOGGER.info(
                    LoggingUtils.getLogMessageInfo(this, INSERT_ELIGIBILITY_METHOD,
                            "Put key=traceNumber={} and value=eligibility to key-value storage, patientId={}, payerId={}"),
                    traceNumber, eligibility.getPatientID(), eligibility.getPayerID());

        } catch (SandataRuntimeException e) {
            LOGGER.error(
                    LoggingUtils.getErrorMessageInfo(this, INSERT_ELIGIBILITY_METHOD,
                            "An exception happens when putting Eligibility for trace number={} to key-value storage. Error: {}"),
                    traceNumber, e.getMessage(), e);
            CamelUtils.stopProcessingExchange(exchange);
        }
    }

    private String createInquiryFileName(Map<String, Object> record) {
        return
                new StringBuilder("Inquiry")
                .append("_").append(record.get("BE_ID"))
                .append("_").append(record.get("PT_ID"))
                .append("_").append(record.get("PAYER_ID"))
                .append("_").append(new SimpleDateFormat("YYYYMMDDHHmmssSSS").format(new Date()))
                .append(".json")
                .toString();
    }

    private Eligibility createEligibility(final Exchange exchange) {
        @SuppressWarnings("unchecked")
        Map<String, Object> record = exchange.getIn().getHeader(App.INQUIRY_RECORD_HEADER, Map.class);

        Date currentDateTime = new Date();

        Eligibility eligibility = new Eligibility();
        eligibility.setRecordCreateTimestamp(currentDateTime);
        eligibility.setRecordUpdateTimestamp(currentDateTime);
        eligibility.setRecordEffectiveTimestamp(currentDateTime);
        try {
            eligibility.setRecordTerminationTimestamp(new SimpleDateFormat("YYYY-MM-DD").parse("9999-12-31"));
        } catch (ParseException e) {
            // exception should not happen here as parsing the hard-coded value
            LOGGER.error("An error happens when parsing string of '9999-12-31' to Date object.", e);
        }
        eligibility.setRecordCreatedBy(App.APPLICATION_NAME);
        eligibility.setCurrentRecordIndicator(true);
        eligibility.setChangeVersionID(BigInteger.valueOf(1));
        eligibility.setBusinessEntityID((String) record.get("BE_ID"));
        eligibility.setPayerID((String) record.get("PAYER_ID"));
        eligibility.setPatientID((String) record.get("PT_ID"));
        eligibility.setLastEligibilityCheckDate(currentDateTime);
        eligibility.setEligibilityStatusName(EligibilityStatusName.PENDING);

        return eligibility;
    }
}
