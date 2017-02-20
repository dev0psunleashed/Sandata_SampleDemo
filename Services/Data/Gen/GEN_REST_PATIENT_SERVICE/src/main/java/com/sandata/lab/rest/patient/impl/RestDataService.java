package com.sandata.lab.rest.patient.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.date.DateUtil;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.rest.RestUtil;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.extended.AuthorizationExt;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.data.model.result.SequenceKeyValueResult;
import com.sandata.lab.data.model.service.ServiceStatus;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.patient.api.DataService;
import com.sandata.lab.rest.patient.handler.AdminDataHandler;
import com.sandata.lab.rest.patient.model.extended.PatientDiagnosisExt;
import com.sandata.lab.rest.patient.model.extended.PatientExt;
import com.sandata.lab.rest.patient.model.extended.PatientPayerExt;
import com.sandata.lab.rest.patient.utils.log.OracleDataLogger;
import org.apache.camel.Exchange;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.message.MessageContentsList;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.sandata.lab.rest.patient.utils.log.OracleDataLogger.CreateLogger;
import static java.lang.String.format;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */
@SuppressWarnings("unchecked")
public class RestDataService implements DataService {

    private OracleDataService oracleDataService;

    public void findPatientsForStaffAgency(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String staffId = (String) mcl.get(0);
            if (staffId == null || staffId.length() == 0) {
                throw new SandataRuntimeException(exchange, "StaffID (staff_id) is required!");
            }

            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            int page = (Integer) mcl.get(2);
            int pageSize = (Integer) mcl.get(3);
            String sortOn = (String) mcl.get(4);
            String direction = (String) mcl.get(5);

            Response response = oracleDataService.findPatientsForStaffAgency(
                    staffId, bsnEntId, page, pageSize, sortOn, direction);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getPdContactDetlForID(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String patientId = (String) mcl.get(0);
            if (patientId == null || patientId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PatientID (patient_id) is required!");
            }

            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String sortOn = (String) mcl.get(2);
            if (sortOn == null || sortOn.length() == 0) {
                sortOn = "REC_CREATE_TMSTP";
            }

            String direction = (String) mcl.get(3);

            exchange.getIn().setBody(oracleDataService.getPdContactDetlForID(patientId, bsnEntId, sortOn, direction));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getPatientForID(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String patientId = (String) mcl.get(0);
            if (patientId == null || patientId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PatientID (patient_id) is required!");
            }

            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            //dmr--GEOR-2629
            Patient patient = oracleDataService.getPatientForID(patientId, bsnEntId);
            if (null != patient) {
                PatientExt patientExt = new PatientExt(patient);

                //dmr--GEOR-3511
                exchange.getIn().setBody(getRelatedPatientData(patientExt));
            } else {
                exchange.getIn().setBody(null);
            }

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getPatientContactDetails(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String patientId = (String) mcl.get(0);
            if (patientId == null || patientId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PatientID (patient_id) is required!");
            }

            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String sortOn = (String) mcl.get(2);
            if (sortOn == null || sortOn.length() == 0) {
                sortOn = "REC_CREATE_TMSTP";
            }

            String direction = (String) mcl.get(3);

            exchange.getIn().setBody(oracleDataService.getPatientContactDetails(patientId, bsnEntId, sortOn, direction));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getPatientContactEmailID(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String patientId = (String) mcl.get(0);
            if (patientId == null || patientId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PatientID (patient_id) is required!");
            }

            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            exchange.getIn().setBody(oracleDataService.getPatientContactEmailID(patientId, bsnEntId));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getPatientContactPhoneID(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String patientId = (String) mcl.get(0);
            if (patientId == null || patientId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PatientID (patient_id) is required!");
            }

            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String sortOn = (String) mcl.get(2);
            if (sortOn == null || sortOn.length() == 0) {
                sortOn = "REC_CREATE_TMSTP";
            }

            String direction = (String) mcl.get(3);

            exchange.getIn().setBody(oracleDataService.getPatientContactPhoneID(patientId, bsnEntId, sortOn, direction));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getPatientCntctAddrForID(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String patientId = (String) mcl.get(0);
            if (patientId == null || patientId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PatientID (patient_id) is required!");
            }

            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String sortOn = (String) mcl.get(2);
            if (sortOn == null || sortOn.length() == 0) {
                sortOn = "REC_CREATE_TMSTP";
            }

            String direction = (String) mcl.get(3);

            exchange.getIn().setBody(oracleDataService.getPatientCntctAddrForID(patientId, bsnEntId, sortOn, direction));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getPatientAllergyForID(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String patientId = (String) mcl.get(0);
            if (patientId == null || patientId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PatientID (patient_id) is required!");
            }

            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            exchange.getIn().setBody(oracleDataService.getPatientAllergyForID(patientId));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void distinctFirstNames(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            int page = (Integer) mcl.get(1);
            int pageSize = (Integer) mcl.get(2);

            Response response = oracleDataService.distinctFirstNames(bsnEntId, page, pageSize);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", page);
            exchange.getIn().setHeader("PAGE_SIZE", pageSize);

            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void distinctLastNames(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID is required!");
            }

            int page = (Integer) mcl.get(1);
            int pageSize = (Integer) mcl.get(2);

            Response response = oracleDataService.distinctLastNames(bsnEntId, page, pageSize);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", page);
            exchange.getIn().setHeader("PAGE_SIZE", pageSize);

            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void validatePatientId(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String patientId = (String) mcl.get(0);
            if (patientId == null || patientId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PatientID (patient_id) is required!");
            }

            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            exchange.getIn().setBody(oracleDataService.validatePatientId(patientId, bsnEntId, logger));

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    @Override
    public void findPatients(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            // Throw an exception if no filters have been provided!
            boolean bHasAtLeastOneFilter = false;

            StringBuilder patientFilterTyp = new StringBuilder();

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            List<String> params = new ArrayList<>();

            // handles keyword possible cases
            String keywordString = (String) mcl.get(0);
            if (keywordString != null && keywordString.trim().length() > 0) {

                String[] rawKeywords = keywordString.split(" ");

                List<String> nonEmptyKeywords = new ArrayList<>();

                for (String rawKeyword : rawKeywords) {
                    if (rawKeyword != null && !rawKeyword.isEmpty()) {
                        nonEmptyKeywords.add(rawKeyword);
                    }
                }

                if (nonEmptyKeywords.isEmpty()) {
                    // nothing to filter here
                } else if (nonEmptyKeywords.size() == 1) {
                    params.add(nonEmptyKeywords.get(0).toUpperCase(Locale.ENGLISH) + "%");
                    params.add(nonEmptyKeywords.get(0).toUpperCase(Locale.ENGLISH) + "%");

                    patientFilterTyp.append("(UPPER(T1.PT_FIRST_NAME) LIKE ? " +
                            "OR UPPER(T1.PT_LAST_NAME) LIKE ?) AND ");
                } else if (nonEmptyKeywords.size() == 2) {
                    // Example: Tom Hanks
                    params.add(nonEmptyKeywords.get(0).toUpperCase(Locale.ENGLISH) + "%");
                    params.add(nonEmptyKeywords.get(1).toUpperCase(Locale.ENGLISH) + "%");

                    // Flip params around
                    // Example: Hanks Tom
                    params.add(nonEmptyKeywords.get(1).toUpperCase(Locale.ENGLISH) + "%");
                    params.add(nonEmptyKeywords.get(0).toUpperCase(Locale.ENGLISH) + "%");

                    // Last name may have space between Ex: "Martin St. Luis" where St. Luis is last Name
                    params.add(nonEmptyKeywords.get(0).toUpperCase(Locale.ENGLISH) + " " + nonEmptyKeywords.get(1).toUpperCase(Locale.ENGLISH) + "%");

                    patientFilterTyp.append("((UPPER(T1.PT_FIRST_NAME) LIKE ? AND UPPER(T1.PT_LAST_NAME) LIKE ?) OR " +
                            "(UPPER(T1.PT_FIRST_NAME) LIKE ? AND UPPER(T1.PT_LAST_NAME) LIKE ?) OR "
                            + "(UPPER(T1.PT_LAST_NAME) LIKE ?)) AND ");
                } else if (nonEmptyKeywords.size() == 3) {

                    // Example: Tom H Hanks
                    params.add(nonEmptyKeywords.get(0).toUpperCase(Locale.ENGLISH) + "%");
                    params.add(nonEmptyKeywords.get(1).toUpperCase(Locale.ENGLISH) + "%");
                    params.add(nonEmptyKeywords.get(2).toUpperCase(Locale.ENGLISH) + "%");

                    // Example: Hanks H Tom
                    params.add(nonEmptyKeywords.get(2).toUpperCase(Locale.ENGLISH) + "%");
                    params.add(nonEmptyKeywords.get(1).toUpperCase(Locale.ENGLISH) + "%");
                    params.add(nonEmptyKeywords.get(0).toUpperCase(Locale.ENGLISH) + "%");

                    // For example, "Martin St. Luis" where St. Luis is last Name.
                    params.add(nonEmptyKeywords.get(0).toUpperCase(Locale.ENGLISH) + "%");
                    params.add(nonEmptyKeywords.get(1).toUpperCase(Locale.ENGLISH) + " " + nonEmptyKeywords.get(2).toUpperCase(Locale.ENGLISH) + "%");

                    // For example, "St. Luis Martin" where St. Luis is last Name.
                    params.add(nonEmptyKeywords.get(2).toUpperCase(Locale.ENGLISH) + "%");
                    params.add(nonEmptyKeywords.get(0).toUpperCase(Locale.ENGLISH) + " " + nonEmptyKeywords.get(1).toUpperCase(Locale.ENGLISH) + "%");


                    patientFilterTyp
                            .append("(")
                            .append("(UPPER(T1.PT_FIRST_NAME) LIKE ? AND UPPER(T1.PT_MIDDLE_NAME) LIKE ? AND UPPER(T1.PT_LAST_NAME) LIKE ?) OR ")
                            .append("(UPPER(T1.PT_FIRST_NAME) LIKE ? AND UPPER(T1.PT_MIDDLE_NAME) LIKE ? AND UPPER(T1.PT_LAST_NAME) LIKE ?) OR ")
                            .append("(UPPER(T1.PT_FIRST_NAME) LIKE ? AND UPPER(T1.PT_LAST_NAME) LIKE ?) OR ")
                            .append("(UPPER(T1.PT_FIRST_NAME) LIKE ? AND UPPER(T1.PT_LAST_NAME) LIKE ?)")
                            .append(") AND ");

                } else if (nonEmptyKeywords.size() >= 4) {
                    // we just support for maximum 4 keywords, the 5th keyword will be ignored
                    // Example: "Martin H St. Luis" where St. Luis is last Name.
                    params.add(nonEmptyKeywords.get(0).toUpperCase(Locale.ENGLISH) + "%");
                    params.add(nonEmptyKeywords.get(1).toUpperCase(Locale.ENGLISH) + "%");
                    params.add(nonEmptyKeywords.get(2).toUpperCase(Locale.ENGLISH) + " " + nonEmptyKeywords.get(3).toUpperCase(Locale.ENGLISH) + "%");

                    // Example: "St. Luis H Martin" where St. Luis is last Name.
                    params.add(nonEmptyKeywords.get(3).toUpperCase(Locale.ENGLISH) + "%");
                    params.add(nonEmptyKeywords.get(2).toUpperCase(Locale.ENGLISH) + "%");
                    params.add(nonEmptyKeywords.get(0).toUpperCase(Locale.ENGLISH) + " " + nonEmptyKeywords.get(1).toUpperCase(Locale.ENGLISH) + "%");

                    patientFilterTyp
                            .append("(")
                            .append("(UPPER(T1.PT_FIRST_NAME) LIKE ? AND UPPER(T1.PT_MIDDLE_NAME) LIKE ? AND UPPER(T1.PT_LAST_NAME) LIKE ?) OR ")
                            .append("(UPPER(T1.PT_FIRST_NAME) LIKE ? AND UPPER(T1.PT_MIDDLE_NAME) LIKE ? AND UPPER(T1.PT_LAST_NAME) LIKE ?) ")
                            .append(") AND ");
                }

                bHasAtLeastOneFilter = true;
            }

            String patientId = (String) mcl.get(1);
            if (patientId != null && patientId.length() > 0) {
                params.add(patientId.toUpperCase() + "%");
                patientFilterTyp.append("(UPPER(T1.PT_ID) LIKE ?) AND ");
                bHasAtLeastOneFilter = true;
            }

            String ssn = (String) exchange.getIn().getHeader("ssn");
            if (!StringUtil.IsNullOrEmpty(ssn)) {

                params.add(ssn);
                patientFilterTyp.append("T1.PT_TIN = ? AND ");

                params.add(TaxpayerIdentificationNumberQualifier.SSN.value().toUpperCase());
                patientFilterTyp.append("UPPER(T1.PT_TIN_QLFR) = ? AND ");
                bHasAtLeastOneFilter = true;
            }

            String homePhone = (String) mcl.get(2);
            if (!StringUtil.IsNullOrEmpty(homePhone)) {
                //As in SAN-526, we dont need to have the validation the 10 characters length for the phone.
                params.add(homePhone + "%");

                patientFilterTyp.append("(T1.PT_ID IN ( ")
                        .append("SELECT DISTINCT PT_ID FROM PT_CONT_PHONE ")
                        .append("WHERE PT_PHONE LIKE ? AND BE_ID = T1.BE_ID AND PT_ID = T1.PT_ID ")
                        .append("    AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)")
                        .append(")) AND ");
                bHasAtLeastOneFilter = true;
            }

            String email = (String) mcl.get(3);
            if (email != null && email.length() > 0) {
                params.add(email + "%");
                patientFilterTyp.append("(T6.PT_EMAIL LIKE ?) AND ");
                bHasAtLeastOneFilter = true;
            }

            String firstName = (String) mcl.get(9);
            if (firstName != null && firstName.length() > 0) {
                params.add(firstName.toUpperCase() + "%");
                patientFilterTyp.append("UPPER(T1.PT_FIRST_NAME) LIKE ? AND ");
                bHasAtLeastOneFilter = true;
            }

            String lastName = (String) mcl.get(10);
            if (lastName != null && lastName.length() > 0) {
                params.add(lastName.toUpperCase() + "%");
                patientFilterTyp.append("UPPER(T1.PT_LAST_NAME) LIKE ? AND ");
                bHasAtLeastOneFilter = true;
            }

            // Handle status filter which accepts multiple values as a list
            List<String> statusList = RestUtil.normalizeParamList((List<String>) exchange.getIn().getHeader("status"));
            if (statusList != null && statusList.size() > 0) {
                patientFilterTyp.append("(");
                for (int i = 0; i < statusList.size(); i++) {
                    params.add(statusList.get(i).toUpperCase(Locale.ENGLISH) + '%');
                    patientFilterTyp.append("UPPER(T1.PT_STATUS_NAME) LIKE ?");
                    if (i < (statusList.size() - 1)) {
                        patientFilterTyp.append(" OR ");
                    }
                }
                patientFilterTyp.append(") AND ");
                bHasAtLeastOneFilter = true;
            }

            // collects all address filter of PT_CONT_ADDR table then unify to one condition
            StringBuilder addressFilter = new StringBuilder();

            // Handle city filter which accepts multiple values as a list
            List<String> cityList = RestUtil.normalizeParamList((List<String>) exchange.getIn().getHeader("city"));
            if (cityList != null && cityList.size() > 0) {
                addressFilter.append("(");
                for (int i = 0; i < cityList.size(); i++) {
                    params.add(cityList.get(i).toUpperCase(Locale.ENGLISH) + '%');
                    addressFilter.append("UPPER(T2.PT_CITY) LIKE ?");
                    if (i < (cityList.size() - 1)) {
                        addressFilter.append(" OR ");
                    }
                }
                addressFilter.append(") AND ");
                bHasAtLeastOneFilter = true;
            }

            // Handle state filter which accepts multiple values as a list
            List<String> stateList = RestUtil.normalizeParamList((List<String>) exchange.getIn().getHeader("state"));
            if (stateList != null && stateList.size() > 0) {
                addressFilter.append("(");
                for (int i = 0; i < stateList.size(); i++) {
                    params.add(stateList.get(i).toUpperCase(Locale.ENGLISH) + '%');
                    addressFilter.append("UPPER(T2.PT_STATE) LIKE ?");
                    if (i < (stateList.size() - 1)) {
                        addressFilter.append(" OR ");
                    }
                }
                addressFilter.append(") AND ");
                bHasAtLeastOneFilter = true;
            }

            // Handle list of addresses where each address is a concatenated string of (address1, address2, aptNum)
            List<String> addressList = RestUtil.normalizeParamList((List<String>) exchange.getIn().getHeader("address"));
            if (addressList != null && addressList.size() > 0) {
                addressFilter.append("(");
                for (int i = 0; i < addressList.size(); i++) {
                    String addressWithCommas = addressList.get(i);
                    String[] addressArray = addressWithCommas.split(",", 3);

                    if (addressArray.length == 1) {
                        params.add(addressArray[0].trim().toUpperCase(Locale.ENGLISH) + '%');
                        params.add(addressArray[0].trim().toUpperCase(Locale.ENGLISH) + '%');
                        params.add(addressArray[0].trim().toUpperCase(Locale.ENGLISH) + '%');

                        addressFilter.append("(UPPER(T2.PT_ADDR1) LIKE ? OR UPPER(T2.PT_ADDR2) LIKE ? OR UPPER(T2.PT_APT_NUM) LIKE ?)");

                    } else if (addressArray.length == 2) {
                        params.add(addressArray[0].trim().toUpperCase(Locale.ENGLISH) + '%');
                        params.add(addressArray[1].trim().toUpperCase(Locale.ENGLISH) + '%');
                        params.add(addressArray[0].trim().toUpperCase(Locale.ENGLISH) + '%');
                        params.add(addressArray[1].trim().toUpperCase(Locale.ENGLISH) + '%');
                        params.add(addressArray[0].trim().toUpperCase(Locale.ENGLISH) + '%');
                        params.add(addressArray[1].trim().toUpperCase(Locale.ENGLISH) + '%');

                        addressFilter.append("((UPPER(T2.PT_ADDR1) LIKE ? AND UPPER(T2.PT_ADDR2) LIKE ?) OR ")
                                .append("(UPPER(T2.PT_ADDR1) LIKE ? AND UPPER(T2.PT_APT_NUM) LIKE ?) OR ")
                                .append("(UPPER(T2.PT_ADDR2) LIKE ? AND UPPER(T2.PT_APT_NUM) LIKE ?))");

                    } else if (addressArray.length == 3) {
                        params.add(addressArray[0].trim().toUpperCase(Locale.ENGLISH) + '%');
                        params.add(addressArray[1].trim().toUpperCase(Locale.ENGLISH) + '%');
                        params.add(addressArray[2].trim().toUpperCase(Locale.ENGLISH) + '%');

                        addressFilter.append("(UPPER(T2.PT_ADDR1) LIKE ? AND UPPER(T2.PT_ADDR2) LIKE ? AND UPPER(T2.PT_APT_NUM) LIKE ?)");
                    }

                    if (i < (addressList.size() - 1)) {
                        addressFilter.append(" OR ");
                    }
                }
                addressFilter.append(") AND ");
                bHasAtLeastOneFilter = true;
            }

            String zipCode = (String) mcl.get(23);
            if (!StringUtil.IsNullOrEmpty(zipCode)) {
                params.add(zipCode.toUpperCase(Locale.US));
                addressFilter.append("UPPER(T2.PT_PSTL_CODE) = ? AND ");
                bHasAtLeastOneFilter = true;
            }


            // if we have address filters, append to the filter
            if (addressFilter.length() > 0) {
                patientFilterTyp.append("(T1.PT_ID IN ( ")
                        .append("SELECT DISTINCT PT_ID FROM PT_CONT_ADDR T2 ")
                        .append("WHERE T2.BE_ID = T1.BE_ID AND T2.PT_ID = T1.PT_ID ")
                        .append("    AND (TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T2.CURR_REC_IND = 1) ")
                        .append("    AND ").append(addressFilter)
                        .append("    (1 = 1)")
                        .append(")) AND ").toString();
            }

            // Handle priorityLevel filter which accepts multiple values as a list
            List<String> priorityLevelsList = RestUtil.normalizeParamList((List<String>) exchange.getIn().getHeader("priority_levels"));
            if (priorityLevelsList != null && priorityLevelsList.size() > 0) {
                patientFilterTyp.append("(");
                for (int i = 0; i < priorityLevelsList.size(); i++) {
                    params.add(priorityLevelsList.get(i).toUpperCase(Locale.ENGLISH) + '%');
                    patientFilterTyp.append("UPPER(T1.PT_PRIO_LVL_CODE) LIKE ?");
                    if (i < (priorityLevelsList.size() - 1)) {
                        patientFilterTyp.append(" OR ");
                    }
                }
                patientFilterTyp.append(") AND ");
                bHasAtLeastOneFilter = true;
            }
            
            String medicaidId = (String) exchange.getIn().getHeader("medicaid_id");
            if (!StringUtil.IsNullOrEmpty(medicaidId)) {
                params.add(medicaidId);
                patientFilterTyp.append("T1.PT_MEDICAID_ID = ? AND");
                bHasAtLeastOneFilter = true;
            }

            String coordinatorId = (String) mcl.get(20);
            if (!StringUtil.IsNullOrEmpty(coordinatorId)) {
                bHasAtLeastOneFilter = true;
            }

            String payerId = (String) mcl.get(21);
            if (!StringUtil.IsNullOrEmpty(payerId)) {
                bHasAtLeastOneFilter = true;
            }

            String contractId = (String) mcl.get(22);
            if (!StringUtil.IsNullOrEmpty(contractId)) {
                bHasAtLeastOneFilter = true;
            }

            String service = (String) mcl.get(11);
            if (!StringUtil.IsNullOrEmpty(service)) {
                bHasAtLeastOneFilter = true;
            }
            
            String nurseId = (String) exchange.getIn().getHeader("nurse_id");
            if (!StringUtil.IsNullOrEmpty(nurseId)) {
                bHasAtLeastOneFilter = true;
            }

            if (!bHasAtLeastOneFilter) {
                throw new SandataRuntimeException(exchange, "No filter option provided!");
            }

            int page = (Integer) mcl.get(4);
            int pageSize = (Integer) mcl.get(5);

            // Settings: ln = last_name, fn = first_name, hp = home_phone, in = insurance_id
            String sortOn = (String) mcl.get(6);

            String orderByColumn = "UPPER(T1.PT_LAST_NAME)"; // Default
            switch (sortOn.toLowerCase(Locale.ENGLISH)) {
                case "fn":
                    orderByColumn = "UPPER(T1.PT_FIRST_NAME)";
                    break;
                case "hp":
                    orderByColumn = "PT_PHONE";
                    break;
                case "in":
                    orderByColumn = "T4.PT_INS_ID_NUM";
                    break;
                case "dob":
                    orderByColumn = "T1.PT_DOB";
                    break;
                case "pi":
                    orderByColumn = "T1.PT_ID";
                    break;
                case "status":
                case "stt":
                    orderByColumn = "T1.PT_STATUS_NAME";
                    break;
                case "cln":
                    orderByColumn = "UPPER(T10.COOR_ADMIN_STAFF_LAST_NAME)";
                    break;
                case "cfn":
                    orderByColumn = "UPPER(T10.COOR_ADMIN_STAFF_FIRST_NAME)";
                    break;
            }

            String direction = (String) mcl.get(7);

            String bsnEntId = (String) mcl.get(8);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            Response response = null;

            DateFormat format = new SimpleDateFormat(DateUtil.SANDATA_UTC_DATE_TIME_FORMAT, Locale.ENGLISH);

            // Validate schedule from date time
            String schedFromDateTimeString = (String) mcl.get(12);
            Date schedFromDateTime = null;
            if (!StringUtil.IsNullOrEmpty(schedFromDateTimeString)) {
                try {
                    schedFromDateTime = format.parse(schedFromDateTimeString);
                } catch (ParseException pe) {
                    throw new SandataRuntimeException(exchange,
                            format("Schedule From Date Time: [%s]: Is NOT a valid date and/or the format is incorrect!",
                                    schedFromDateTimeString));
                }
            }

            // Validate schedule to date time
            String schedToDateTimeString = (String) mcl.get(13);
            Date schedToDateTime = null;
            if (!StringUtil.IsNullOrEmpty(schedToDateTimeString)) {
                try {
                    schedToDateTime = format.parse(schedToDateTimeString);
                } catch (ParseException pe) {
                    throw new SandataRuntimeException(exchange,
                            format("Schedule To Date Time: [%s]: Is NOT a valid date and/or the format is incorrect!",
                                    schedToDateTimeString));
                }
            }

            // If service is not provided, then handle differently
            if (StringUtil.IsNullOrEmpty(service)) {
                response = oracleDataService.findPatients(
                        patientFilterTyp.toString(),
                        params,
                        orderByColumn,
                        page,
                        pageSize,
                        direction.toUpperCase(),
                        bsnEntId,
                        schedFromDateTime,
                        schedToDateTime,
                        coordinatorId,
                        payerId,
                        contractId,
                        nurseId,
                        medicaidId
                );
            } else {

                String staffId = (String) mcl.get(14);

                if (schedFromDateTime == null) {
                    throw new SandataRuntimeException(exchange, "Schedule From Date Time is required!");
                }

                if (schedToDateTime == null) {
                    throw new SandataRuntimeException(exchange, "Schedule To Date Time is required!");
                }

                response = oracleDataService.findPatientsForService(
                        patientFilterTyp.toString(),
                        params,
                        orderByColumn,
                        page,
                        pageSize,
                        direction.toUpperCase(),
                        bsnEntId,
                        service,
                        schedFromDateTime,
                        schedToDateTime,
                        staffId,
                        coordinatorId,
                        payerId,
                        contractId,
                        nurseId,
                        medicaidId
                );
            }

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    @Override
    public void get(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String operationName = (String) exchange.getIn().getHeader("operationName");

            String[] methodParts = operationName.split("_");

            String packageName = methodParts[0] + "_" + methodParts[1];
            String methodName = methodParts[2];
            String className = "com.sandata.lab.data.model.dl.model."
                    + ("PatientPayerExt".equals(methodParts[3]) ? "PatientPayer" : methodParts[3]);

            Object result;
            Object body = exchange.getIn().getBody();

            if (exchange.getIn().getHeader("sequence_key") != null) {
                long sequenceKey = (long) exchange.getIn().getHeader("sequence_key");

                result = oracleDataService.executeGet(
                        packageName,
                        methodName,
                        className,
                        sequenceKey
                );

                ArrayList result2 = (ArrayList<Object>) result;

                if (result2 != null && result2.size() > 0) {
                    result = result2.get(0);

                    if (methodParts[3].equals("Patient")) {

                        PatientExt patientExt = new PatientExt((Patient) result);
                        exchange.getIn().setBody(getRelatedPatientData(patientExt));

                    } else if (methodParts[3].equals("PatientPayerExt")) {
                        exchange.getIn().setBody(getEligibility((PatientPayer) result));
                    } else if (methodParts[3].equals("PatientDiagnosis")) {
                        exchange.getIn().setBody(getICDDxCodeDescriptions((PatientDiagnosis) result));
                    } else {
                        exchange.getIn().setBody(result);
                    }
                } else {
                    exchange.getIn().setBody(null);
                }
            } else {

                MessageContentsList mcl = (MessageContentsList) body;

                Object[] params = new Object[mcl.size()];

                for (int index = 0; index < mcl.size(); index++) {
                    params[index] = mcl.get(index);
                }

                result = oracleDataService.executeGet(
                        packageName,
                        methodName,
                        className,
                        params
                );

                if (methodParts[3].equals("Patient")) {

                    List patientExtList = new ArrayList();
                    List patientList = (List) result;
                    for (Object object : patientList) {
                        Patient patient = (Patient) object;
                        PatientExt patientExt = new PatientExt(patient);
                        patientExtList.add(patientExt);
                    }

                    exchange.getIn().setBody(patientExtList);

                } else {

                    exchange.getIn().setBody(result);
                }
            }
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    @Override
    public void delete(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String operationName = (String) exchange.getIn().getHeader("operationName");

            String[] methodParts = operationName.split("_");

            String packageName = methodParts[0] + "_" + methodParts[1];
            String methodName = methodParts[2];

            long sequenceKey = (long) exchange.getIn().getHeader("sequence_key");

            long result = oracleDataService.execute(
                    packageName,
                    methodName,
                    sequenceKey
            );

            exchange.getIn().setBody(result);
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    @Override
    public void update(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;

        try {

            PatientExt patientExt = null;
            Patient patient = null;

            Object data = exchange.getIn().getBody();

            //dmr--GEOR-2629
            if (data instanceof PatientExt) {
                patientExt = (PatientExt) data;

                if (!patientExt.isDnrAdOptionValid()) {

                    throw new SandataRuntimeException(exchange, format("update: %s: [ERROR_MSG=DNR/AD Option (%s) is NOT valid!]",
                            getClass().getName(), patientExt.getDnrAdOption()));
                }

                patient = patientExt.getPatient();
            }

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);
            long returnVal = executeRecursive(exchange, connection, (patient != null) ? patient : data, false /* UPDATE */, -999,
                    (patientExt != null ? patientExt.getPatientID() : null));

            if (returnVal > 0) {

                connection.commit();
                exchange.getIn().setBody(returnVal);
            } else {
                throw new SandataRuntimeException(exchange, "Update was not successful!");
            }

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    @Override
    public void insert(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;

        try {

            Object data = exchange.getIn().getBody();

            PatientExt patientExt = null;
            Patient patient = null;

            if (data instanceof PatientExt) {
                patientExt = (PatientExt) data;

                //dmr--GEOR-2629
                if (!patientExt.isDnrAdOptionValid()) {

                    throw new SandataRuntimeException(exchange, format("insert: %s: [ERROR_MSG=DNR/AD Option (%s) is NOT valid!]",
                            getClass().getName(), patientExt.getDnrAdOption()));
                }

                patient = patientExt.getPatient();

                // validate that the timezone is provided
                if (patientExt.getTimezoneName() == null || patientExt.getTimezoneName().length() == 0) {

                    throw new SandataRuntimeException(exchange, format("%s: ERROR: Timezone is NULL or EMPTY!",
                            getClass().getName()));
                } else {
                    boolean timeZoneFound = false;
                    String[] timeZoneNames = TimeZone.getAvailableIDs();
                    for (String tz : timeZoneNames) {
                        if (tz.equals(patientExt.getTimezoneName())) {
                            timeZoneFound = true;
                            break;
                        }
                    }

                    if (!timeZoneFound) {
                        throw new SandataRuntimeException(exchange, format("%s: ERROR: Timezone [%s] is not a valid timezone name!",
                                getClass().getName(), patientExt.getTimezoneName()));
                    }
                }
            } else if (data instanceof PatientPayer) {
                //dmr--GEOR-2785 3/18/2016
                //dmr--GEOR-2995 3/29/2016: It should only do this for a date that is null per Hiren's comments;
                PatientPayer patientPayer = (PatientPayer) data;
                if (patientPayer.getPatientPayerTerminationDate() == null) {
                    patientPayer.setPatientPayerTerminationDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("9999-12-31 00:00:00"));
                }
            }

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);
            long resultVal = executeRecursive(exchange, connection, (patient != null) ? patient : data, true, -999, (patientExt != null ? patientExt.getPatientID() : null));
            if (resultVal > 0) {

                connection.commit();
                exchange.getIn().setBody(resultVal);
            } else {
                throw new SandataRuntimeException(exchange, "Insert was not successful!");
            }

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    private void setPatientId(final Object jpubType, String patientId) {

        if (patientId == null || patientId.length() == 0) {
            return;
        }

        try {

            Method method = jpubType.getClass().getDeclaredMethod("setPatientId", String.class);
            method.invoke(jpubType, patientId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setPatientSk(final Object jpubType, long sequenceKey) throws NoSuchMethodException {

        if (sequenceKey <= 0) {
            return;
        }

        try {

            Method method = jpubType.getClass().getDeclaredMethod("setPatientSk", BigDecimal.class);
            method.invoke(jpubType, BigDecimal.valueOf(sequenceKey));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private long executeRecursive(
            final Exchange exchange, final Connection connection, final Object data, final boolean bShouldInsert,
            long returnVal, String patientId) throws SandataRuntimeException {

        try {
            // GEOR-6612
            String timezoneFieldErrMsg = RestUtil.validateRequiredTimezoneName(data);
            if (timezoneFieldErrMsg.length() > 0) {
                throw new SandataRuntimeException(timezoneFieldErrMsg);
            }

            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(data);

            Object jpubType = new DataMapper().map(data);


            try {
                // set the PT_SK for all child objects
                setPatientSk(jpubType, returnVal);

            } catch (NoSuchMethodException e) {
                setPatientId(jpubType, patientId);
            }

            long result = 0;

            if (bShouldInsert) {
                result = oracleDataService.execute(
                        connection,
                        ConnectionType.COREDATA,
                        oracleMetadata.packageName(),
                        oracleMetadata.insertMethod(),
                        jpubType
                );
            } else {
                if (data instanceof Patient) {
                    returnVal = ((Patient) data).getPatientSK().intValue();

                    // UPDATE
                    result = oracleDataService.execute(
                            connection,
                            ConnectionType.COREDATA,
                            "PKG_HIST",
                            "updatePatient",
                            jpubType
                    );

                } else {
                    if (data instanceof PatientContactAddress) {
                        returnVal = ((PatientContactAddress) data).getPatientContactAddressSK().intValue();
                    } else if (data instanceof PatientContactPhone) {
                        returnVal = ((PatientContactPhone) data).getPatientContactPhoneSK().intValue();
                    } else if (data instanceof PatientContactEmail) {
                        returnVal = ((PatientContactEmail) data).getPatientContactEmailSK().intValue();
                    }

                    // UPDATE
                    result = oracleDataService.execute(
                            connection,
                            ConnectionType.COREDATA,
                            oracleMetadata.packageName(),
                            oracleMetadata.updateMethod(),
                            jpubType
                    );
                }
            }

            if (result > 0) {

                if (returnVal == -999) {
                    returnVal = result;
                }

                // Check if there are any lists that need to be inserted
                for (Field field : data.getClass().getDeclaredFields()) {

                    field.setAccessible(true);

                    Object property = field.get(data);
                    if (property != null && property instanceof List) {

                        List list = (List) property;
                        for (Object object : list) {

                            // Try to insert the object!

                            // WARNING: RECURSIVE!!!!
                            long insertResponse = executeRecursive(exchange, connection, object, bShouldInsert, returnVal, patientId);
                            if (insertResponse == -1) {
                                if (bShouldInsert) {
                                    throw new SandataRuntimeException(exchange, format("INSERT: Failed: [%s]",
                                            object.getClass().getName()));
                                } else {
                                    throw new SandataRuntimeException(exchange, format("UPDATE: Failed: [%s]",
                                            object.getClass().getName()));
                                }
                            }
                        }
                    }
                }

                // SUCCESS
                return returnVal;

            } // if (result > 0)

            // FAILED
            return -1;
        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        }
    }

    private PatientExt getRelatedPatientData(final PatientExt patient) throws SandataRuntimeException {

        // Get the subtables that are required for the Main Model... for example, Patient would also need to get Patient Contact Details...
        // Eligibility
        List<Eligibility> eList = (List<Eligibility>) oracleDataService.getEntitiesForId(
                "SELECT * FROM ELIG WHERE PT_ID = ? AND BE_ID = ? " +
                        "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                "com.sandata.lab.data.model.dl.model.Eligibility",
                patient.getPatientID(),
                patient.getBusinessEntityID()
        );

        for (Eligibility item : eList) {
            patient.getEligibility().add(item);
        }

        // PatientSafetyMeasure
        List<PatientSafetyMeasure> smList = (List<PatientSafetyMeasure>) oracleDataService.getEntitiesForId(
                "SELECT * FROM PT_SFTY_MEASURE WHERE PT_ID = ? AND BE_ID = ? " +
                        "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                "com.sandata.lab.data.model.dl.model.PatientSafetyMeasure",
                patient.getPatientID(),
                patient.getBusinessEntityID());

        for (PatientSafetyMeasure item : smList) {
            patient.getPatientSafetyMeasure().add(item);
        }

        // PatientAllergy
        List<PatientAllergy> alList = (List<PatientAllergy>) oracleDataService.getEntitiesForId(
                "SELECT * FROM PT_ALLERGY WHERE PT_ID = ? AND BE_ID = ? " +
                        "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                "com.sandata.lab.data.model.dl.model.PatientAllergy",
                patient.getPatientID(),
                patient.getBusinessEntityID());

        for (PatientAllergy item : alList) {
            patient.getPatientAllergy().add(item);
        }

        // PatientNutritionalReqs
        List<PatientNutritionalRequirement> nutrList = (List<PatientNutritionalRequirement>) oracleDataService.getEntitiesForId(
                "SELECT * FROM PT_NUTR_RQMT WHERE PT_ID = ? AND BE_ID = ? " +
                        "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                "com.sandata.lab.data.model.dl.model.PatientNutritionalRequirement",
                patient.getPatientID(),
                patient.getBusinessEntityID());

        for (PatientNutritionalRequirement item : nutrList) {
            patient.getPatientNutritionalRequirement().add(item);
        }

        // PatientContactAddress
        List<PatientContactAddress> contactAddressList = (List<PatientContactAddress>) oracleDataService.getEntitiesForId(
                "SELECT * FROM PT_CONT_ADDR WHERE PT_ID = ? AND BE_ID = ? " +
                        "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                        "ORDER BY REC_CREATE_TMSTP ASC, PT_CONT_ADDR_SK ASC",
                "com.sandata.lab.data.model.dl.model.PatientContactAddress",
                patient.getPatientID(),
                patient.getBusinessEntityID());

        for (PatientContactAddress item : contactAddressList) {
            patient.getPatientContactAddress().add(item);
        }

        // PatientContactPhone
        List<PatientContactPhone> contactPhoneList = (List<PatientContactPhone>) oracleDataService.getEntitiesForId(
                "SELECT * FROM PT_CONT_PHONE WHERE PT_ID = ? AND BE_ID = ? " +
                        "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                        "ORDER BY REC_CREATE_TMSTP ASC, PT_CONT_PHONE_SK ASC",
                "com.sandata.lab.data.model.dl.model.PatientContactPhone",
                patient.getPatientID(),
                patient.getBusinessEntityID());

        for (PatientContactPhone item : contactPhoneList) {
            patient.getPatientContactPhone().add(item);
        }

        // PatientContactEmail
        List<PatientContactEmail> contactEmailList = (List<PatientContactEmail>) oracleDataService.getEntitiesForId(
                "SELECT * FROM PT_CONT_EMAIL WHERE PT_ID = ? AND BE_ID = ? " +
                        "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                        "ORDER BY REC_CREATE_TMSTP ASC, PT_CONT_EMAIL_SK ASC",
                "com.sandata.lab.data.model.dl.model.PatientContactEmail",
                patient.getPatientID(),
                patient.getBusinessEntityID());

        for (PatientContactEmail item : contactEmailList) {
            patient.getPatientContactEmail().add(item);
        }

        // PatientDMEAndSupplies
        List<PatientDurableMedicalEquipmentAndSupply> dmeAndSuppliesList = (List<PatientDurableMedicalEquipmentAndSupply>) oracleDataService.getEntitiesForId(
                "SELECT * FROM PT_DME_AND_SUPPLY WHERE PT_ID = ? AND BE_ID = ? " +
                        "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                "com.sandata.lab.data.model.dl.model.PatientDurableMedicalEquipmentAndSupply",
                patient.getPatientID(),
                patient.getBusinessEntityID());

        for (PatientDurableMedicalEquipmentAndSupply item : dmeAndSuppliesList) {
            patient.getPatientDurableMedicalEquipmentAndSupply().add(item);
        }

        // PatientRequirements
        List<PatientRequirement> patientRequirementList = (List<PatientRequirement>) oracleDataService.getEntitiesForId(
                "SELECT * FROM PT_RQMT WHERE PT_ID = ? AND BE_ID = ? AND " +
                        "(TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                "com.sandata.lab.data.model.dl.model.PatientRequirement",
                patient.getPatientID(),
                patient.getBusinessEntityID());

        for (PatientRequirement item : patientRequirementList) {
            patient.getPatientRequirement().add(item);
        }

        // PatientMedications
        List<PatientMedication> medicationsList = (List<PatientMedication>) oracleDataService.getEntitiesForId(
                "SELECT * FROM PT_MED WHERE PT_ID = ? AND BE_ID = ? AND " +
                        "(TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                "com.sandata.lab.data.model.dl.model.PatientMedication",
                patient.getPatientID(),
                patient.getBusinessEntityID());

        for (PatientMedication item : medicationsList) {
            patient.getPatientMedication().add(item);
        }

        // PatientMedicalHistory
        List<PatientMedicalHistory> patientMedicalHistoryList = (List<PatientMedicalHistory>) oracleDataService.getEntitiesForId(
                "SELECT * FROM PT_MEDCL_HIST WHERE PT_ID = ? AND BE_ID = ? AND " +
                        "(TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                "com.sandata.lab.data.model.dl.model.PatientMedicalHistory",
                patient.getPatientID(),
                patient.getBusinessEntityID());

        for (PatientMedicalHistory item : patientMedicalHistoryList) {
            patient.getPatientMedicalHistory().add(item);
        }

        // Reference
        List<Reference> reference = (List<Reference>) oracleDataService.getEntitiesForId(
                "SELECT * FROM REF WHERE PT_ID = ? AND BE_ID = ? AND " +
                        "(TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                "com.sandata.lab.data.model.dl.model.Reference",
                patient.getPatientID(),
                patient.getBusinessEntityID());

        for (Reference item : reference) {
            patient.getReference().add(item);
        }

        List<Authorization> authorizations = (List<Authorization>) oracleDataService.getEntitiesForId(
                "SELECT * FROM AUTH WHERE PT_ID = ? AND BE_ID = ? AND " +
                        "(TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                "com.sandata.lab.data.model.dl.model.Authorization",
                patient.getPatientID(),
                patient.getBusinessEntityID());

        String sqlGetAuthSvcByAuth = "SELECT * FROM AUTH_SVC WHERE AUTH_SK = ? ";
        for (Authorization item : authorizations) {
            List<AuthorizationService> authSvcList = (List<AuthorizationService>) oracleDataService.getEntitiesForId(
                    sqlGetAuthSvcByAuth,
                    "com.sandata.lab.data.model.dl.model.AuthorizationService",
                    item.getAuthorizationSK());

            item.getAuthorizationService().addAll(authSvcList);

            // Calculate units used and units remaining.
            AuthorizationExt authorizationExt = calculateAuthorizationUnitsUsedAndRemaining(item);
            patient.getAuthorization().add(authorizationExt);
        }

        List<BillingRate> billingRateList = (List<BillingRate>) oracleDataService.getEntitiesForId(
                "SELECT * FROM BILLING_RATE WHERE PT_ID = ? AND BE_ID = ? AND " +
                        "(TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                "com.sandata.lab.data.model.dl.model.BillingRate",
                patient.getPatientID(),
                patient.getBusinessEntityID());

        for (BillingRate item : billingRateList) {
            patient.getBillingRate().add(item);
        }

        List<PatientPayer> patientPayers = (List<PatientPayer>) oracleDataService.getEntitiesForId(
                "SELECT * FROM PT_PAYER WHERE PT_ID = ? AND BE_ID = ? AND " +
                        "(TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                "com.sandata.lab.data.model.dl.model.PatientPayer",
                patient.getPatientID(),
                patient.getBusinessEntityID());

        for (PatientPayer item : patientPayers) {
            patient.getPatientPayer().add(item);

            List<PatientPayerLimit> patientPayerLimits = (List<PatientPayerLimit>) oracleDataService.getEntitiesForId(
                    "SELECT * FROM PT_PAYER_LMT WHERE PT_ID = ? AND PAYER_ID = ? AND BE_ID = ? AND " +
                            "(TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                    "com.sandata.lab.data.model.dl.model.PatientPayerLimit",
                    item.getPatientID(),
                    item.getPayerID(),
                    item.getBusinessEntityID());

            for (PatientPayerLimit patientPayerLimit : patientPayerLimits) {
                item.getPatientPayerLimit().add(patientPayerLimit);
            }
        }

        AdminDataHandler adminDataHandler = new AdminDataHandler(oracleDataService);
        patient.setCoordinatorId(adminDataHandler.getCoordinatorIdForPatientId(
                patient.getBusinessEntityID(), patient.getPatientID()));

        patient.setNurseId(adminDataHandler.getNurseIdForPatientId(
                patient.getBusinessEntityID(), patient.getPatientID()));

        return patient;
    }

    public void getPatientNoteForID(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String patientId = (String) mcl.get(0);
            if (patientId == null || patientId.length() == 0) {
                throw new SandataRuntimeException("PatientID (patient_id) is required!");
            }

            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            exchange.getIn().setBody(oracleDataService.getPatientNoteForID(patientId, bsnEntId));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Handles POST request for inserting into COREDATA.PT_DME_AND_SUPPLY table
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void insertPatientDmeAndSupplyList(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;
        try {
            // Validating params
            String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String patientId = exchange.getIn().getHeader("patient_id", String.class);
            if (patientId == null || patientId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PatientID (patient_id) is required!");
            }

            List<String> dmeSupplyIdList = exchange.getIn().getBody(List.class);

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            oracleDataService.insertPatientDmeAndSupplyList(connection, bsnEntId, patientId, dmeSupplyIdList);

            connection.commit();

            exchange.getIn().setBody(ServiceStatus.SUCCESS);

        } catch (Exception e) {
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    /**
     * Handles PUT request for updating records in COREDATA.PT_DME_AND_SUPPLY table. This methods will firstly
     * deletes all records that match the combination of (bsnEntId, patientId, dmeSupplyIdList), then inserts
     * new records into the table
     *
     * @param exchange
     */
    public void updatePatientDmeAndSupplyList(final Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            // Validating params
            String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String patientId = exchange.getIn().getHeader("patient_id", String.class);
            if (patientId == null || patientId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PatientID (patient_id) is required!");
            }

            List<String> dmeSupplyIdList = exchange.getIn().getBody(List.class);
            if (dmeSupplyIdList == null || dmeSupplyIdList.size() == 0) {
                throw new SandataRuntimeException(exchange, "dmeSupplyId is required!");
            }

            // Delete ALL for the given Patient and Business Entity
            oracleDataService.updatePatientDmeAndSupplyList(bsnEntId, patientId, dmeSupplyIdList);

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Deletes records in PT_DME_AND_SUPPLY table by BE_ID and PT_ID and DME_SUPPLY_ID
     *
     * @param exchange
     */
    public void deletePatientDmeAndSupplyList(final Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            // Validating params
            String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String patientId = exchange.getIn().getHeader("patient_id", String.class);
            if (patientId == null || patientId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PatientID (patient_id) is required!");
            }

            List<String> dmeSupplyIdList = exchange.getIn().getBody(List.class);
            if (dmeSupplyIdList == null || dmeSupplyIdList.size() == 0) {
                throw new SandataRuntimeException(exchange, "dmeSupplyId is required!");
            }

            int result = oracleDataService.deletePatientDmeAndSupplyList(bsnEntId, patientId, dmeSupplyIdList);
            logger.info(format("deletePatientDmeAndSupplyList: PatientDmeAndSupplyList deletion result: %s", result));

            //dmr--exchange.getIn().setBody(Arrays.asList(result));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Handles POST request for inserting into COREDATA.PT_ALLERGY table
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void insertPatientAllergyList(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;
        try {
            // Validating params
            String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String patientId = exchange.getIn().getHeader("patient_id", String.class);
            if (patientId == null || patientId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PatientID (patient_id) is required!");
            }

            List<String> allergyNameList = exchange.getIn().getBody(List.class);

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            oracleDataService.insertPatientAllergyList(connection, bsnEntId, patientId, allergyNameList);

            connection.commit();

            exchange.getIn().setBody(ServiceStatus.SUCCESS);

        } catch (Exception e) {
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    /**
     * Deletes records in PT_ALLERGY table by BE_ID and PT_ID and ALLERGY_NAME
     *
     * @param exchange
     */
    public void deletePatientAllergyList(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            // Validating params
            String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String patientId = exchange.getIn().getHeader("patient_id", String.class);
            if (patientId == null || patientId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PatientID (patient_id) is required!");
            }

            List<String> allergyNameList = exchange.getIn().getBody(List.class);
            if (allergyNameList == null || allergyNameList.size() == 0) {
                throw new SandataRuntimeException(exchange, "allergyNameList is required!");
            }

            oracleDataService.deletePatientAllergyList(bsnEntId, patientId, allergyNameList);

            exchange.getIn().setBody(0);

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Handles PUT request for updating records in COREDATA.PT_ALLERGY table. This methods will firstly
     * mark deleted all records that match the combination of (bsnEntId, patientId, allergyNameList), then insert
     * new records into the table
     *
     * @param exchange
     */
    public void updatePatientAllergyList(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            // Validating params
            String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String patientId = exchange.getIn().getHeader("patient_id", String.class);
            if (patientId == null || patientId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PatientID (patient_id) is required!");
            }

            List<String> allergyNameList = exchange.getIn().getBody(List.class);

            // Mark deleted ALL for the given Patient and Business Entity, then insert new records
            oracleDataService.updatePatientAllergyList(bsnEntId, patientId, allergyNameList);

            exchange.getIn().setBody(ServiceStatus.SUCCESS);

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public OracleDataService getOracleDataService() {
        return oracleDataService;
    }

    public void setOracleDataService(OracleDataService oracleDataService) {
        this.oracleDataService = oracleDataService;
    }

    /**
     * accepts a PUT request of a list of Nutr Req IDs and removes any existing items associated with the given Patient
     * and Business entity before inserting new Nutr Req IDs into the PT_NUTR_RQMT table
     *
     * @param exchange
     */
    public void updatePatientNutrRqmt(final Exchange exchange) {
        // step1: find PT_NUTR_RQMT by patient id and bsn_ent_id and delete logically

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            // Validating params
            String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String patientId = exchange.getIn().getHeader("patient_id", String.class);
            if (patientId == null || patientId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PatientID (patient_id) is required!");
            }

            List<String> nutrRqmtNames = exchange.getIn().getBody(List.class);

            // Delete ALL logically for the given Patient and Business Entity
            oracleDataService.updatePatientNutrRqmt(bsnEntId, patientId, nutrRqmtNames);

            exchange.getIn().setBody(ServiceStatus.SUCCESS);

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * accepts a POST request of a list of Nutr Req IDs and inserts into PT_NUTR_RQMT table
     *
     * @param exchange
     */
    public void insertPatientNutrRqmntList(final Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;
        try {
            // Validating params
            String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String patientId = exchange.getIn().getHeader("patient_id", String.class);
            if (patientId == null || patientId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PatientID (patient_id) is required!");
            }

            List<String> nutrRqmntnames = exchange.getIn().getBody(List.class);

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);
            //logger.logger().info("thanh-le" + bsnEntId + "/"+patientId + "/" +nutrRqmntnames.get(0));
            oracleDataService.insertPatientNutrRqmntList(connection, bsnEntId, patientId, nutrRqmntnames);

            connection.commit();

            exchange.getIn().setBody(ServiceStatus.SUCCESS);

        } catch (Exception e) {
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }

    }

    /**
     * accepts a DELETE request of a list of Nutr Req IDs and removes the items from PT_NUTR_RQMT table
     *
     * @param exchange
     */
    public void deletePatientNutrRqmt(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            // Validating params
            String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String patientId = exchange.getIn().getHeader("patient_id", String.class);
            if (patientId == null || patientId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PatientID (patient_id) is required!");
            }

            List<String> nutrRqmntnames = exchange.getIn().getBody(List.class);
            if (nutrRqmntnames == null || nutrRqmntnames.size() == 0) {
                throw new SandataRuntimeException(exchange, "dmeSupplyId is required!");
            }

            oracleDataService.deletePatientnutrRqmntList(bsnEntId, patientId, nutrRqmntnames);

            exchange.getIn().setBody(0);

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Handles PUT request to update Patient Payer and Eligibility
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void updatePatientPayerEligibility(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;

        try {

            PatientPayerExt patientPayerExt = exchange.getIn().getBody(PatientPayerExt.class);
            PatientPayer patientPayer = patientPayerExt.getPatientPayer();
            Eligibility eligibility = patientPayerExt.getEligibility();

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            long returnValForPtPayer = executeRecursive(exchange, connection, patientPayer, false /*UPDATE*/, -999, null);
            long returnValForElig = executeRecursive(exchange, connection, eligibility, false /*UPDATE*/, -999, null);
            if (returnValForPtPayer > 0 && returnValForElig > 0) {
                connection.commit();
                exchange.getIn().setBody(returnValForPtPayer);
            } else {
                throw new SandataRuntimeException(exchange, "Update was not successful!");
            }

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    /**
     * Handles GET request to get a list of Patient Payers along with
     * Eligibility by PT_ID and BE_ID
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void getPatientPayerEligibility(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String patientId = (String) mcl.get(0);
            if (patientId == null || patientId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PatientID (patient_id) is required!");
            }

            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            List<PatientPayerExt> patientPayerExts = oracleDataService.getPatientPayerEligibility(patientId, bsnEntId);
            exchange.getIn().setBody(getRelatedPatientPayerData(patientPayerExts));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getPatientCoordinators(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String patientId = (String) mcl.get(0);

            String bsnEntId = (String) mcl.get(1);
            if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            Response response = oracleDataService.getPatientCoordinators(bsnEntId, patientId);

            exchange.getIn().setBody(response.getData());
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    private List<PatientPayerExt> getRelatedPatientPayerData(final List<PatientPayerExt> patientPayerExts) {
        for (PatientPayerExt patientPayerExt : patientPayerExts) {

            // patient payer insurance
            List<PatientPayerInsurance> ppiList = (List<PatientPayerInsurance>) oracleDataService.getEntitiesForId(
                    "SELECT * FROM PT_PAYER_INS WHERE PT_ID = ? AND BE_ID = ? AND PAYER_ID = ? " +
                            "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                    "com.sandata.lab.data.model.dl.model.PatientPayerInsurance",
                    patientPayerExt.getPatientID(),
                    patientPayerExt.getBusinessEntityID(),
                    patientPayerExt.getPayerID()
            );
            patientPayerExt.getPatientPayerInsurance().addAll(ppiList);

            // contract
            List<Contract> contractList = (List<Contract>) oracleDataService.getEntitiesForId(
                    "SELECT * FROM CONTR WHERE BE_ID = ? AND PAYER_ID = ? " +
                            "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                    "com.sandata.lab.data.model.dl.model.Contract",
                    patientPayerExt.getBusinessEntityID(),
                    patientPayerExt.getPayerID()
            );
            patientPayerExt.getContract().addAll(contractList);

            // line of business
            List<PayerLineOfBusinessList> payerLOBList = (List<PayerLineOfBusinessList>) oracleDataService.getEntitiesForId(
                    "SELECT * FROM PAYER_LOB_LST WHERE BE_ID = ? AND PAYER_ID = ? " +
                            "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                    "com.sandata.lab.data.model.dl.model.PayerLineOfBusinessList",
                    patientPayerExt.getBusinessEntityID(),
                    patientPayerExt.getPayerID()
            );
            patientPayerExt.getPayerLineOfBusinessList().addAll(payerLOBList);
        }

        return patientPayerExts;
    }

    private PatientPayerExt getEligibility(PatientPayer patientPayer) {
        PatientPayerExt patientPayerExt = new PatientPayerExt(patientPayer);

        List<Eligibility> eligibilities = (List<Eligibility>) oracleDataService.getEntitiesForId(
                "SELECT * FROM ELIG WHERE PT_ID = ? AND BE_ID = ? AND PAYER_ID = ? " +
                        "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                "com.sandata.lab.data.model.dl.model.Eligibility",
                patientPayerExt.getPatientID(),
                patientPayerExt.getBusinessEntityID(),
                patientPayerExt.getPayerID()
        );
        if (eligibilities != null && !eligibilities.isEmpty()) {
            patientPayerExt.setEligibility(eligibilities.get(0));
        }

        return patientPayerExt;
    }

    /**
     * Return PatientDiagnosisExt instance with ICDDiagnosisCodeShortDescription and ICDDiagnosisCodeLongDescription
     *
     * @param ptDx
     * @return
     */
    private PatientDiagnosisExt getICDDxCodeDescriptions(PatientDiagnosis ptDx) {
        PatientDiagnosisExt ptDxExt = new PatientDiagnosisExt(ptDx);
        List<ICDDiagnosisLookup> icdDxList = (List<ICDDiagnosisLookup>) oracleDataService.getEntitiesForId(
                "SELECT * FROM ICD_DX_LKUP WHERE ICD_DX_CODE = ? AND ICD_DX_CODE_REVISION_QLFR = ? " +
                        "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                "com.sandata.lab.data.model.dl.model.ICDDiagnosisLookup",
                ptDx.getICDDiagnosisCode(),
                ptDx.getICDDiagnosisCodeRevisionQualifier().value());

        if (icdDxList != null && !icdDxList.isEmpty()) {
            ptDxExt.setIcdDiagnosisCodeShortDescription(icdDxList.get(0).getICDDiagnosisCodeShortDescription());
            ptDxExt.setIcdDiagnosisCodeLongDescription(icdDxList.get(0).getICDDiagnosisCodeLongDescription());
        }

        return ptDxExt;
    }

    public void insertPatient(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

        logger.start();

        Connection connection = null;

        try {

            connection = oracleDataService.getConnectionPoolDataService().getConnection();
            connection.setAutoCommit(false);

            AdminDataHandler adminDataHandler = new AdminDataHandler(getOracleDataService());

            PatientExt patientExt = exchange.getIn().getBody(PatientExt.class);

            // Insert Coordinator if one was provided
            if (!StringUtil.IsNullOrEmpty(patientExt.getCoordinatorId())) {

                adminDataHandler.insertCoordinator(
                        connection,
                        patientExt.getBusinessEntityID(),
                        patientExt.getPatientID(),
                        patientExt.getCoordinatorId());
            }

            // Insert Nurse if one was provided
            if (!StringUtil.IsNullOrEmpty(patientExt.getNurseId())) {

                adminDataHandler.insertNurse(
                        connection,
                        patientExt.getBusinessEntityID(),
                        patientExt.getPatientID(),
                        patientExt.getNurseId()
                );
            }

            //dmr--Patient patient = patientExt.getPatient();

            //dmr--exchange.getIn().setBody(patient);

            insert(exchange);

            connection.commit();

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {

            // Close the connection
            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    public void updatePatient(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

        logger.start();

        Connection connection = null;

        try {

            connection = oracleDataService.getConnectionPoolDataService().getConnection();
            connection.setAutoCommit(false);

            AdminDataHandler adminDataHandler = new AdminDataHandler(getOracleDataService());

            PatientExt patientExt = exchange.getIn().getBody(PatientExt.class);

            List<SequenceKeyValueResult> resultList = adminDataHandler.getAdminIdForPatientId(
                    connection,
                    patientExt.getBusinessEntityID(),
                    patientExt.getPatientID(),
                    "Coordinator"
            );

            // If coordinator is null, then check to see if there is an association and delete it if one exists
            if (StringUtil.IsNullOrEmpty(patientExt.getCoordinatorId())) {
                adminDataHandler.deleteAdminStaffPatientXwalkForList(connection, resultList, logger);
            } else {
                boolean bFound = false;
                for (SequenceKeyValueResult skv : resultList) {

                    // The passed in coordinatorId is different
                    if (!skv.getValue().equals(patientExt.getCoordinatorId())) {
                        adminDataHandler.deleteAdminStaffPatientXwalk(connection, skv, logger);
                    } else {
                        bFound = true;
                    }
                }

                // Coordinator Not found! Insert new record
                if (!bFound) {
                    adminDataHandler.insertCoordinator(
                            connection,
                            patientExt.getBusinessEntityID(),
                            patientExt.getPatientID(),
                            patientExt.getCoordinatorId());
                }
            }

            resultList = adminDataHandler.getAdminIdForPatientId(
                    connection,
                    patientExt.getBusinessEntityID(),
                    patientExt.getPatientID(),
                    "Nurse"
            );

            // If nurse is null, then check to see if there is an association and delete it if one exists
            if (StringUtil.IsNullOrEmpty(patientExt.getNurseId())) {
                adminDataHandler.deleteAdminStaffPatientXwalkForList(connection, resultList, logger);
            } else {
                boolean bFound = false;
                for (SequenceKeyValueResult skv : resultList) {

                    // The passed in nurseId is different
                    if (!skv.getValue().equals(patientExt.getNurseId())) {
                        adminDataHandler.deleteAdminStaffPatientXwalk(connection, skv, logger);
                    } else {
                        bFound = true;
                    }
                }

                // Nurse Not found! Insert new record
                if (!bFound) {
                    adminDataHandler.insertNurse(
                            connection,
                            patientExt.getBusinessEntityID(),
                            patientExt.getPatientID(),
                            patientExt.getNurseId()
                    );
                }
            }

            if (patientExt.getPatientStatusName() != null && patientExt.getPatientStatusName().equals(PatientStatusName.DISCHARGED)
                    && patientExt.getPatientDischargeDate() != null) {

                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                String patientDischargeDateString = dateFormat.format(patientExt.getPatientDischargeDate());

                // End date all Authorizations.
                long numAuthsEndDated = oracleDataService.endDateAuthForDischargedPatient(connection,
                        patientExt.getBusinessEntityID(),
                        patientExt.getPatientID(),
                        patientDischargeDateString);
                logger.info(String.format("%s authorization(s) end-dated for business entity ID %s and patient ID %s.",
                        numAuthsEndDated,
                        patientExt.getBusinessEntityID(),
                        patientExt.getPatientID()));

                // End date all Orders.
                long numOrdsEndDated = oracleDataService.endDateOrdForDischargedPatient(connection,
                        patientExt.getBusinessEntityID(),
                        patientExt.getPatientID(),
                        patientDischargeDateString);
                logger.info(String.format("%s order(s) end-dated for business entity ID %s and patient ID %s.",
                        numOrdsEndDated,
                        patientExt.getBusinessEntityID(),
                        patientExt.getPatientID()));

                // End date all Plan of Cares.
                long numPocsEndDated = oracleDataService.endDatePocForDischargedPatient(connection,
                        patientExt.getBusinessEntityID(),
                        patientExt.getPatientID(),
                        patientDischargeDateString);
                logger.info(String.format("%s plan of care(s) end-dated for business entity ID %s and patient ID %s.",
                        numPocsEndDated,
                        patientExt.getBusinessEntityID(),
                        patientExt.getPatientID()));

                // Terminate all future schedules.
                long numSchedEventsCancelled = oracleDataService.terminateSchedEventsForDischargedPatient(connection,
                        patientExt.getBusinessEntityID(),
                        patientExt.getPatientID(),
                        patientDischargeDateString);
                logger.info(String.format("%s schedule event(s) cancelled for business entity ID %s and patient ID %s.",
                        numSchedEventsCancelled,
                        patientExt.getBusinessEntityID(),
                        patientExt.getPatientID()));

            } else if (patientExt.getPatientStatusName() != null && patientExt.getPatientStatusName().equals(PatientStatusName.HOLD)) {
                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                String patientDischargeDateString = dateFormat.format(new Date());

                // Cancel all future schedules.
                long numSchedEventsCancelled = oracleDataService.terminateSchedEventsForDischargedPatient(connection,
                        patientExt.getBusinessEntityID(),
                        patientExt.getPatientID(),
                        patientDischargeDateString);
                logger.info(String.format("%s schedule event(s) cancelled for business entity ID %s and patient ID %s.",
                        numSchedEventsCancelled,
                        patientExt.getBusinessEntityID(),
                        patientExt.getPatientID()));
            }

            Patient patient = patientExt.getPatient();

            exchange.getIn().setBody(patient);

            update(exchange);

            connection.commit();

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {

            // Close the connection
            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    /**
     * Get Patient Contact Address by City and State
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void getAddressByCityAndState(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            List<String> cityList = RestUtil.normalizeParamList((List<String>) mcl.get(1));
            if (cityList == null || cityList.size() == 0) {
                throw new SandataRuntimeException(exchange, "City (city) is required!");
            }

            List<String> stateList = RestUtil.normalizeParamList((List<String>) mcl.get(2));
            if (stateList == null || stateList.size() == 0) {
                throw new SandataRuntimeException(exchange, "State (state) is required!");
            }

            int page = (Integer) mcl.get(3);
            int pageSize = (Integer) mcl.get(4);
            String sortOn = (String) mcl.get(5);
            String orderByColumn = "PT_ADDR1"; // Default
            switch (sortOn) {
                case "addr1":
                    orderByColumn = "PT_ADDR1";
                    break;
                case "addr2":
                    orderByColumn = "PT_ADDR2";
                    break;
                case "aptNum":
                    orderByColumn = "PT_APT_NUM";
                    break;
            }
            String direction = (String) mcl.get(6);

            Response response = oracleDataService.getAddressByCityAndState(bsnEntId, cityList, stateList, page, pageSize, orderByColumn, direction);
            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());
        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Get all cities of a State
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void getCityByState(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            List<String> stateList = RestUtil.normalizeParamList((List<String>) mcl.get(1));
            if (stateList == null || stateList.size() == 0) {
                throw new SandataRuntimeException(exchange, "State (state) is required!");
            }

            int page = (Integer) mcl.get(2);
            int pageSize = (Integer) mcl.get(3);
            String sortOn = (String) mcl.get(4);
            String orderByColumn = "PT_CITY"; // Default
            switch (sortOn) {
                case "city":
                    orderByColumn = "PT_CITY";
                    break;
            }

            String direction = (String) mcl.get(5);

            Response response = oracleDataService.getCityByState(bsnEntId, stateList, page, pageSize, orderByColumn, direction);
            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());
        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    private AuthorizationExt calculateAuthorizationUnitsUsedAndRemaining(Authorization authorization) {
        AuthorizationExt authorizationExt = new AuthorizationExt(authorization);

        Map<String, BigDecimal> pendingUnitsMap = oracleDataService.getPendingUnitsForAuthorization(authorizationExt.getAuthorizationSK());
        Map<String, BigDecimal> confirmedUnitsMap = oracleDataService.getConfirmedUnitsForAuthorization(authorizationExt.getAuthorizationSK());

        BigDecimal authorizationUnitsUsed = new BigDecimal(0);
        BigDecimal authorizationUnitsRemaining = new BigDecimal(0);
        if (authorizationExt.getAuthorizationServiceUnitName() != null
                && authorizationExt.getAuthorizationServiceUnitName().equals(ServiceUnitName.HOUR)) {
            if (pendingUnitsMap.get("PENDING_HOURS") != null
                    && confirmedUnitsMap.get("CONFIRMED_HOURS") != null) {
                authorizationUnitsUsed = pendingUnitsMap.get("PENDING_HOURS").add(confirmedUnitsMap.get("CONFIRMED_HOURS"));
            }
        } else {
            if (pendingUnitsMap.get("PENDING_COUNT") != null
                    && confirmedUnitsMap.get("CONFIRMED_COUNT") != null) {
                authorizationUnitsUsed = pendingUnitsMap.get("PENDING_COUNT").add(confirmedUnitsMap.get("CONFIRMED_COUNT"));
            }
        }

        if (authorizationExt.getAuthorizationLimit() != null) {
            authorizationUnitsRemaining = authorizationExt.getAuthorizationLimit().subtract(authorizationUnitsUsed);
        }

        authorizationExt.setAuthorizationUnitsUsed(authorizationUnitsUsed);
        authorizationExt.setAuthorizationUnitsRemaining(authorizationUnitsRemaining);

        return authorizationExt;
    }

    /**
     * Gets ICD codes for specified patient ID and service name. Returns all ICD codes if service name is not provided
     *
     * @param exchange the exchange
     */
    public void getPatientDiagnosis(final Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String patientId = (String) mcl.get(0);
            if (StringUtils.isEmpty(patientId)) {
                throw new SandataRuntimeException(exchange, "PatientID (patient_id) is required!");
            }

            String serviceName = (String) mcl.get(1);

            String bsnEntId = (String) mcl.get(2);
            if (StringUtils.isEmpty(bsnEntId)) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String sql = new StringBuilder()
                    .append("SELECT * FROM PT_DX")
                    .append("   WHERE PT_ID=?")
                    .append(StringUtils.isEmpty(serviceName) ? "" : "AND SVC_NAME=?")
                    .append("AND BE_ID=?")
                    .append("   AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')")
                    .toString();

            String className = "com.sandata.lab.data.model.dl.model.PatientDiagnosis";

            Object result;
            if (StringUtils.isEmpty(serviceName)) {
                result = oracleDataService.getEntitiesForId(sql, className, patientId, bsnEntId);
            } else {
                result = oracleDataService.getEntitiesForId(sql, className, patientId, serviceName, bsnEntId);
            }

            // If result is a list, convert each PatientDiagnosis into PatientDiagnosisExt
            if (result instanceof ArrayList) {
                ArrayList<PatientDiagnosis> resultList = (ArrayList) result;
                ArrayList<PatientDiagnosisExt> ptDxExtList = new ArrayList<PatientDiagnosisExt>();
                for (PatientDiagnosis ptDx : resultList) {
                    ptDxExtList.add(getICDDxCodeDescriptions(ptDx));
                }

                exchange.getIn().setBody(ptDxExtList);

            } else {
                exchange.getIn().setBody(getICDDxCodeDescriptions((PatientDiagnosis) result));
            }

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }
    
    /**
     * GEOR-7001: get patient payers (both types) with Payer, Eligibility, Contract, LineOfBusiness, Insurance entities
     * 
     * @param exchange
     */
    public void getPatientPayerFull(final Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String patientId = (String) mcl.get(0);
            if (StringUtils.isEmpty(patientId)) {
                throw new SandataRuntimeException(exchange, "PatientID (patient_id) is required!");
            }

            String bsnEntId = (String) mcl.get(1);
            if (StringUtils.isEmpty(bsnEntId)) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            Response response = oracleDataService.getPatientPayerFull(patientId, bsnEntId);
            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }
}
