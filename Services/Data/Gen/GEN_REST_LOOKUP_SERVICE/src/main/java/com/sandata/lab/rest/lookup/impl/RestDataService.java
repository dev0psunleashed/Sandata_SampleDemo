package com.sandata.lab.rest.lookup.impl;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.query.QueryUtil;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.model.ApplicationModuleName;
import com.sandata.lab.data.model.dl.model.StateLookup;
import com.sandata.lab.data.model.dl.model.VisitVerificationRoundingRuleID;
import com.sandata.lab.data.model.dl.model.VisitVerificationRoundingRuleQualifier;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.rest.lookup.api.DataService;
import com.sandata.lab.rest.lookup.utils.data.EnumLookupUtil;
import com.sandata.lab.rest.lookup.utils.data.ReferenceValueUtil;
import com.sandata.lab.rest.lookup.utils.log.OracleDataLogger;
import org.apache.camel.Exchange;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.message.MessageContentsList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.sandata.lab.common.utils.string.StringUtil.IsNullOrEmpty;
import static com.sandata.lab.rest.lookup.utils.log.OracleDataLogger.CreateLogger;
import static java.lang.String.format;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */
@SuppressWarnings("unchecked")
public class RestDataService implements DataService {

    private OracleDataService oracleDataService;

    public void getStaffPositionLookup(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String sortOn = (String)mcl.get(1);
            if (sortOn == null || sortOn.length() == 0) {
                sortOn = "SVC_NAME";
            }

            String direction = (String)mcl.get(2);

            exchange.getIn().setBody(oracleDataService.getStaffPositionLookup(bsnEntId, sortOn, direction));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
              throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getServiceLookup(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0); // Required!
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String patientId = (String) mcl.get(1); // Optional!

            exchange.getIn().setBody(oracleDataService.getServiceLookup(patientId,bsnEntId));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
              throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getVisitTaskLst(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            Long visitSk = (Long) mcl.get(0);
            if (visitSk == null || visitSk <= 0) {
                throw new SandataRuntimeException("VisitSK (visit_sk) is required!");
            }

            exchange.getIn().setBody(oracleDataService.getVisitTaskLst(visitSk));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getTaskLookup(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            exchange.getIn().setBody(oracleDataService.getTaskLookup(bsnEntId));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
              throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getReferenceValue(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try
        {
            String operationName = (String)exchange.getIn().getHeader("operationName");

            String[] methodParts = operationName.split("_");

            String entityName = methodParts[2];

            exchange.getIn().setBody(ReferenceValueUtil.GetReferenceValueList(entityName));
        }
        catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
              throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Get Referenece value with ASC sorting
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void getReferenceValueWithSorting(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try
        {
            String operationName = (String)exchange.getIn().getHeader("operationName");

            String[] methodParts = operationName.split("_");

            String entityName = methodParts[2];

            List<? extends Enum> resultList = ReferenceValueUtil.GetReferenceValueList(entityName);
            Collections.sort(resultList, new Comparator<Enum>() {
                @Override
                public int compare(Enum s1, Enum s2) {
                    return s1.name().compareTo(s2.name());
                }
            });

            exchange.getIn().setBody(resultList);
        }
        catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
              throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    @Override
    public void getEnumLookup(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try
        {
            String operationName = (String)exchange.getIn().getHeader("operationName");

            String[] methodParts = operationName.split("_");

            String methodName = methodParts[1];

            exchange.getIn().setBody(EnumLookupUtil.GetEnumList(methodName));
        }
        catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
              throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    @Override
    public void getDashboardLookup(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try
        {
            String operationName = (String)exchange.getIn().getHeader("operationName");

            String[] methodParts = operationName.split("_");

            String methodName = methodParts[1];

            exchange.getIn().setBody(EnumLookupUtil.GetEnumList(methodName));
        }
        catch (Exception e) {

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

        try
        {
            String operationName = (String)exchange.getIn().getHeader("operationName");

            String[] methodParts = operationName.split("_");

            String methodName = methodParts[1];
            String className = "com.sandata.lab.data.model.dl.model." + methodParts[2];

            Object result = oracleDataService.getLookup(
                    methodName,
                    className
            );

            exchange.getIn().setBody(result);
        }
        catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
              throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getStateLookup(Exchange exchange) throws SandataRuntimeException {

        try {

            String sortBy = (String) exchange.getIn().getHeader("sort_on");

            String orderBy = "STATE_CODE";

            if(sortBy.equalsIgnoreCase("name")){

                orderBy = "STATE_NAME";
            }


            List<StateLookup> stateLookup = oracleDataService.getStateLookup(orderBy);

            exchange.getIn().setBody(stateLookup);

        }catch (Exception e){

            throw new SandataRuntimeException(e.getMessage(),e);
        }
    }

    public void getLookupWithBsnEntId(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();
            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String operationName = (String)exchange.getIn().getHeader("operationName");

            String[] methodParts = operationName.split("_");

            String methodName = methodParts[1];
            String className = "com.sandata.lab.data.model.dl.model." + methodParts[2];

            Object result = oracleDataService.getLookupWithBsnEntId(
                    methodName,
                    className,
                    bsnEntId
            );

            exchange.getIn().setBody(result);
        }
        catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
              throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void lookupFedTaxId(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            exchange.getIn().setBody(oracleDataService.lookupFedTaxId(bsnEntId));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
              throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void lookupBiller(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            exchange.getIn().setBody(oracleDataService.lookupBiller(bsnEntId));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
              throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getHcpcsLkup(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String keyword = (String) mcl.get(0);
            String code = (String) mcl.get(1);
            int page = (int) mcl.get(2);
            int pageSize = (int) mcl.get(3);
            String sortOn = (String) mcl.get(4);
            String direction = (String) mcl.get(5);

            StringBuilder filter = new StringBuilder();
            if (!IsNullOrEmpty(keyword)) {
                filter.append(format("WHERE (UPPER(HCPCS_SHORT_DESC) LIKE '%%%s%%' " +
                                "OR UPPER(HCPCS_LONG_DESC) LIKE '%%%s%%')",
                                keyword.toUpperCase(), keyword.toUpperCase()));
            }

            if (!IsNullOrEmpty(code)) {

                // If the filter is empty then append Where clause
                if (IsNullOrEmpty(filter.toString())) {
                    filter.append("WHERE ");
                } else {
                    filter.append(" AND ");
                }

                filter.append(format("UPPER(HCPCS_CODE) LIKE '%%%s%%'", code.toUpperCase()));
            }

            String orderByColumn = "HCPCS_CODE"; // Default
            switch (sortOn.toUpperCase()) {
                case "SD": orderByColumn = "HCPCS_SHORT_DESC"; break;
                case "LD": orderByColumn = "HCPCS_LONG_DESC"; break;
                case "VERSION": orderByColumn = "HCPCS_CODE_VERSION"; break;
                case "CREATED": orderByColumn = "REC_CREATE_TMSTP"; break;
                case "UPDATED": orderByColumn = "REC_UPDATE_TMSTP"; break;
                case "EFFECTIVE": orderByColumn = "REC_EFF_TMSTP"; break;
            }

            Response response = oracleDataService.getHcpcsLkup(
                                    filter.toString(),
                                    page,
                                    pageSize,
                                    orderByColumn,
                                    direction
            );

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

    public void getIcdDiagnosisLkup(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String keyword = (String) mcl.get(0);
            String code = (String) mcl.get(1);
            String revision = (String) mcl.get(2);
            String effectiveDate = (String) mcl.get(3);
            String termDate = (String) mcl.get(4);
            int page = (int) mcl.get(5);
            int pageSize = (int) mcl.get(6);
            String sortOn = (String) mcl.get(7);
            String direction = (String) mcl.get(8);

            StringBuilder filter = new StringBuilder();
            if (!IsNullOrEmpty(keyword)) {
                filter.append(format("AND (UPPER(ICD_DX_CODE_SHORT_DESC) LIKE '%%%s%%' " +
                                "OR UPPER(ICD_DX_CODE_LONG_DESC) LIKE '%%%s%%') ",
                        keyword.toUpperCase(), keyword.toUpperCase()));
            }

            if (!IsNullOrEmpty(code)) {
                filter.append(format("AND UPPER(ICD_DX_CODE) LIKE '%%%s%%' ", code.toUpperCase()));
            }

            if (!IsNullOrEmpty(revision)) {
                filter.append(format("AND UPPER(ICD_DX_CODE_REVISION_QLFR) LIKE '%%%s%%' ", revision.toUpperCase()));
            }

            if (!IsNullOrEmpty(effectiveDate)) {
                filter.append(format("AND ICD_DX_CODE_EFF_DATE >= TO_DATE('%s', 'YYYY-MM-DD') ", effectiveDate));
            }

            String orderByColumn = "ICD_DX_CODE"; // Default
            switch (sortOn.toUpperCase()) {
                case "SD": orderByColumn = "ICD_DX_CODE_SHORT_DESC"; break;
                case "LD": orderByColumn = "ICD_DX_CODE_LONG_DESC"; break;
                case "EDT": orderByColumn = "ICD_DX_CODE_EFF_DATE"; break;
                case "TDT": orderByColumn = "ICD_DX_CODE_TERM_DATE"; break;
                case "REV": orderByColumn = "ICD_DX_CODE_REVISION_QLFR"; break;
                case "CREATED": orderByColumn = "REC_CREATE_TMSTP"; break;
                case "UPDATED": orderByColumn = "REC_UPDATE_TMSTP"; break;
            }

            Response response = oracleDataService.getIcdDiagnosisLkup(
                    termDate,
                    filter.toString(),
                    page,
                    pageSize,
                    orderByColumn,
                    direction
            );

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

    public void getRaceEthnicityLkup(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            int page = (int) mcl.get(0);
            int pageSize = (int) mcl.get(1);
            String sortOn = (String) mcl.get(2);
            String direction = (String) mcl.get(3);

            String orderByColumn = "RACE_ETHNICITY_NAME"; // Default
            switch (sortOn.toUpperCase()) {
                case "CODE": orderByColumn = "RACE_ETHNICITY_CODE"; break;
                case "EFFECTIVE": orderByColumn = "REC_EFF_TMSTP"; break;
                case "TERM": orderByColumn = "REC_TERM_TMSTP"; break;
                case "CREATED": orderByColumn = "REC_CREATE_TMSTP"; break;
                case "UPDATED": orderByColumn = "REC_UPDATE_TMSTP"; break;
            }

            Response response = oracleDataService.getRaceEthnicityLkup(
                    page,
                    pageSize,
                    orderByColumn,
                    direction
            );

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

    public void getContrChangeReasonLkup(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String appModule = (String) exchange.getIn().getHeader("app_module");
            String payerId = (String) exchange.getIn().getHeader("payer_id");
            String contractId = (String) exchange.getIn().getHeader("contract_id");
            String code = (String) exchange.getIn().getHeader("code");
            String name = (String) exchange.getIn().getHeader("name");
            int page = (int) exchange.getIn().getHeader("page");
            int pageSize = (int) exchange.getIn().getHeader("page_size");
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            String orderByColumn = "CHANGE_REASON_NAME"; // Default
            switch (sortOn.toUpperCase()) {
                case "CODE": orderByColumn = "CHANGE_REASON_CODE"; break;
                case "NAME": orderByColumn = "CHANGE_REASON_NAME"; break;
                case "PAYER_ID": orderByColumn = "PAYER_ID"; break;
                case "CONTR_ID": orderByColumn = "CONTR_ID"; break;
                case "MODULE": orderByColumn = "APP_MOD_NAME"; break;
                case "EFFECTIVE": orderByColumn = "CHANGE_REASON_EFF_DATE"; break;
                case "TERM": orderByColumn = "CHANGE_REASON_TERM_DATE"; break;
                case "CREATED": orderByColumn = "REC_CREATE_TMSTP"; break;
                case "UPDATED": orderByColumn = "REC_UPDATE_TMSTP"; break;
            }

            Response response = oracleDataService.getContrChangeReasonLkup(
                    appModule,
                    payerId,
                    contractId,
                    code,
                    name,
                    page,
                    pageSize,
                    orderByColumn,
                    direction
            );

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

    public void getPayerBillingCodeLkup(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
        if (StringUtils.isEmpty(bsnEntId)) {
            throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
        }

        try {

            String payerId = (String) exchange.getIn().getHeader("payer_id");
            String code = (String) exchange.getIn().getHeader("code");
            String description = (String) exchange.getIn().getHeader("description");
            int page = (int) exchange.getIn().getHeader("page");
            int pageSize = (int) exchange.getIn().getHeader("page_size");
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            String orderByColumn = "BILLING_CODE"; // Default
            switch (sortOn.toUpperCase()) {
                case "PAYER_ID": orderByColumn = "PAYER_ID"; break;
                case "SHORT_DESC": orderByColumn = "BILLING_CODE_SHORT_DESC"; break;
                case "LONG_DESC": orderByColumn = "BILLING_CODE_LONG_DESC"; break;
                case "EFFECTIVE": orderByColumn = "BILLING_CODE_EFF_DATE"; break;
                case "TERM": orderByColumn = "BILLING_CODE_TERM_DATE"; break;
                case "CREATED": orderByColumn = "REC_CREATE_TMSTP"; break;
                case "UPDATED": orderByColumn = "REC_UPDATE_TMSTP"; break;
            }

            Response response = oracleDataService.getPayerBillingCodeLkup(
                    bsnEntId,
                    payerId,
                    code,
                    description,
                    page,
                    pageSize,
                    orderByColumn,
                    direction
            );

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

    public void getVvRndingRuleLkup(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String id = (String) exchange.getIn().getHeader("id");

            if (StringUtils.isNotEmpty(id)) {

                try {
                    VisitVerificationRoundingRuleID.fromValue(id.toUpperCase());
                } catch (IllegalArgumentException iae) {
                    throw new SandataRuntimeException(exchange, String.format("ID [%s] is not valid!", id), iae);
                }
            }

            String name = (String) exchange.getIn().getHeader("name");
            String description = (String) exchange.getIn().getHeader("description");
            String qualifier = (String) exchange.getIn().getHeader("qualifier");

            if (StringUtils.isNotEmpty(qualifier)) {

                try {
                    VisitVerificationRoundingRuleQualifier.fromValue(qualifier.toUpperCase());
                } catch (IllegalArgumentException iae) {
                    throw new SandataRuntimeException(exchange, String.format("Qualifier [%s] is not valid!", qualifier), iae);
                }
            }

            int page = (int) exchange.getIn().getHeader("page");
            int pageSize = (int) exchange.getIn().getHeader("page_size");
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            String orderByColumn = "VV_RNDING_RULE_NAME"; // Default
            switch (sortOn.toUpperCase()) {
                case "ID": orderByColumn = "VV_RNDING_RULE_ID"; break;
                case "NAME": orderByColumn = "VV_RNDING_RULE_NAME"; break;
                case "DESC": orderByColumn = "VV_RNDING_RULE_DESC"; break;
                case "QUALIFIER": orderByColumn = "VV_RNDING_RULE_QLFR"; break;
                case "EFFECTIVE": orderByColumn = "VV_RNDING_RULE_EFF_DATE"; break;
                case "TERM": orderByColumn = "VV_RNDING_RULE_TERM_DATE"; break;
                case "CREATED": orderByColumn = "REC_CREATE_TMSTP"; break;
                case "UPDATED": orderByColumn = "REC_UPDATE_TMSTP"; break;
            }

            Response response = oracleDataService.getVvRndingRuleLkup(
                    id,
                    name,
                    description,
                    qualifier,
                    page,
                    pageSize,
                    orderByColumn,
                    direction
            );

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

    public void getMedLkup(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String keyword = (String) mcl.get(0);
            String code = (String) mcl.get(1);
            int page = (int) mcl.get(2);
            int pageSize = (int) mcl.get(3);
            String sortOn = (String) mcl.get(4);
            String direction = (String) mcl.get(5);

            StringBuilder filter = new StringBuilder();
            if (!IsNullOrEmpty(keyword)) {
                filter.append(format("WHERE (UPPER(MED_GENERIC_NAME) LIKE '%%%s%%' " +
                                "OR UPPER(MED_DESC) LIKE '%%%s%%')",
                        keyword.toUpperCase(), keyword.toUpperCase()));
            }

            if (!IsNullOrEmpty(code)) {

                // If the filter is empty then append Where clause
                if (IsNullOrEmpty(filter.toString())) {
                    filter.append("WHERE ");
                } else {
                    filter.append(" AND ");
                }

                filter.append(format("UPPER(MED_NDC_CODE) LIKE '%%%s%%'", code.toUpperCase()));
            }

            String orderByColumn = "MED_GENERIC_NAME"; // Default
            switch (sortOn.toUpperCase()) {
                case "CODE": orderByColumn = "MED_NDC_CODE"; break;
                case "DESC": orderByColumn = "MED_DESC"; break;
                case "CREATED": orderByColumn = "REC_CREATE_TMSTP"; break;
                case "UPDATED": orderByColumn = "REC_UPDATE_TMSTP"; break;
            }

            Response response = oracleDataService.getMedLkup(
                    filter.toString(),
                    page,
                    pageSize,
                    orderByColumn,
                    direction
            );

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
     * Get all TransportationMode Lookup
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void getTransportModeLkup(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            String operationName = (String)exchange.getIn().getHeader("operationName");
            String[] methodParts = operationName.split("_");
            String entityName = methodParts[2];

            Response response = oracleDataService.getLookupData("COREDATA.TRANSPORT_MODE_LKUP", "TRANSPORT_MODE_NAME", "ASC", entityName);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
              throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Get all Language lookup
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void getLangLkup(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            String operationName = (String)exchange.getIn().getHeader("operationName");
            String[] methodParts = operationName.split("_");
            String entityName = methodParts[2];

            Response response = oracleDataService.getLanguageData("LANG_NAME", "ASC", entityName);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
              throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Get all Gender lookup
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void getGenderTypLkup(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            String operationName = (String)exchange.getIn().getHeader("operationName");
            String[] methodParts = operationName.split("_");
            String entityName = methodParts[2];

            Response response = oracleDataService.getLookupData("COREDATA.GENDER_TYP_LKUP", "GENDER_TYP_NAME", "ASC", entityName);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
              throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Get all Address Type lookup
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void getAddrTypLkup(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            String operationName = (String)exchange.getIn().getHeader("operationName");
            String[] methodParts = operationName.split("_");
            String entityName = methodParts[2];

            Response response = oracleDataService.getLookupData("COREDATA.ADDR_TYP_LKUP", "ADDR_TYP_NAME", "ASC", entityName);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
              throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Get all Phone Type lookup
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void getPhoneTypLkup(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            String operationName = (String)exchange.getIn().getHeader("operationName");
            String[] methodParts = operationName.split("_");
            String entityName = methodParts[2];

            Response response = oracleDataService.getLookupData("COREDATA.PHONE_TYP_LKUP", "PHONE_TYP_NAME", "ASC", entityName);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
              throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Get all Marital Status lookup
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void getMrtlStatusLkup(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            String operationName = (String)exchange.getIn().getHeader("operationName");
            String[] methodParts = operationName.split("_");
            String entityName = methodParts[2];

            Response response = oracleDataService.getLookupData("COREDATA.MRTL_STATUS_LKUP", "MRTL_STATUS_NAME", "ASC", entityName);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
              throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Get all Contact Method lookup
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void getContMthdLkup(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            String operationName = (String)exchange.getIn().getHeader("operationName");
            String[] methodParts = operationName.split("_");
            String entityName = methodParts[2];

            Response response = oracleDataService.getLookupData("COREDATA.CONT_MTHD_LKUP", "CONT_MTHD_NAME", "ASC", entityName);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
              throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getCountyLkup(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            List<Object> params = new ArrayList<>();
            StringBuilder filter = new StringBuilder();

            String countyFipsCode = (String) exchange.getIn().getHeader("county_fips_code");
            if (!IsNullOrEmpty(countyFipsCode)) {
                List<Object> list = QueryUtil.DelimStringToParams(countyFipsCode, ",");
                params.addAll(list);
                filter.append(format(" AND (COUNTY_FIPS_CODE IN %s) ", QueryUtil.ParamList(list.size())));
            }

            String stateFipsCode = (String) exchange.getIn().getHeader("state_fips_code");
            if (!IsNullOrEmpty(stateFipsCode)) {
                List<Object> list = QueryUtil.DelimStringToParams(stateFipsCode, ",");
                params.addAll(list);
                filter.append(format(" AND (STATE_FIPS_CODE IN %s) ", QueryUtil.ParamList(list.size())));
            }

            String fipsClsCode = (String) exchange.getIn().getHeader("fips_cls_code");
            if (!IsNullOrEmpty(fipsClsCode)) {
                List<Object> list = QueryUtil.DelimStringToParams(fipsClsCode, ",");
                params.addAll(list);
                filter.append(format(" AND (TRIM(FIPS_CLS_CODE) IN %s) ", QueryUtil.ParamList(list.size())));
            }

            String countyName = (String) exchange.getIn().getHeader("county_name");
            if (!IsNullOrEmpty(countyName)) {
                String countyNameParam = "%" + countyName.toUpperCase() + "%";
                params.add(countyNameParam);
                filter.append(" AND UPPER(COUNTY_NAME) LIKE ?");
            }

            String stateCode = (String) exchange.getIn().getHeader("state_code");
            if (!IsNullOrEmpty(stateCode)) {
                List<Object> list = QueryUtil.DelimStringToParams(stateCode, ",");
                params.addAll(list);
                filter.append(format(" AND (STATE_CODE IN %s) ", QueryUtil.ParamList(list.size())));
            }

            int page = (int) exchange.getIn().getHeader("page");
            int pageSize = (int) exchange.getIn().getHeader("page_size");
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            String orderByColumn = "COUNTY_NAME"; // Default
            switch (sortOn.toLowerCase()) {
                case "county_fips": orderByColumn = "COUNTY_FIPS_CODE"; break;
                case "state_fips": orderByColumn = "STATE_FIPS_CODE"; break;
                case "state": orderByColumn = "STATE_CODE"; break;
                case "fips_cls": orderByColumn = "FIPS_CLS_CODE"; break;
                case "created": orderByColumn = "REC_CREATE_TMSTP"; break;
                case "updated": orderByColumn = "REC_UPDATE_TMSTP"; break;
            }

            Response response = oracleDataService.getCountyLkup(
                    filter.toString(),
                    page,
                    pageSize,
                    orderByColumn,
                    direction,
                    params
            );

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

    public void getCountySubdivLkup(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            List<Object> params = new ArrayList<>();
            StringBuilder filter = new StringBuilder();

            String countySubdivFipsCode = (String) exchange.getIn().getHeader("subdiv_fips_code");
            if (!IsNullOrEmpty(countySubdivFipsCode)) {
                List<Object> list = QueryUtil.DelimStringToParams(countySubdivFipsCode, ",");
                params.addAll(list);
                filter.append(format(" AND (COUNTY_SUBDIV_FIPS_CODE IN %s) ", QueryUtil.ParamList(list.size())));
            }

            String countySubdivQlfr = (String) exchange.getIn().getHeader("subdiv_qualifier");
            if (!IsNullOrEmpty(countySubdivQlfr)) {
                List<Object> list = QueryUtil.DelimStringToParams(countySubdivQlfr, ",");
                params.addAll(list);
                filter.append(format(" AND (COUNTY_SUBDIV_QLFR IN %s) ", QueryUtil.ParamList(list.size())));
            }

            String countySubdivName = (String) exchange.getIn().getHeader("subdiv_name");
            if (!IsNullOrEmpty(countySubdivName)) {
                String countySubdivNameParam = "%" + countySubdivName.toUpperCase() + "%";
                params.add(countySubdivNameParam);
                filter.append(" AND (UPPER(COUNTY_SUBDIV_NAME) LIKE ?) ");
            }

            String countyFipsCode = (String) exchange.getIn().getHeader("county_fips_code");
            if (!IsNullOrEmpty(countyFipsCode)) {
                List<Object> list = QueryUtil.DelimStringToParams(countyFipsCode, ",");
                params.addAll(list);
                filter.append(format(" AND (COUNTY_FIPS_CODE IN %s) ", QueryUtil.ParamList(list.size())));
            }

            String stateFipsCode = (String) exchange.getIn().getHeader("state_fips_code");
            if (!IsNullOrEmpty(stateFipsCode)) {
                List<Object> list = QueryUtil.DelimStringToParams(stateFipsCode, ",");
                params.addAll(list);
                filter.append(format(" AND (STATE_FIPS_CODE IN %s) ", QueryUtil.ParamList(list.size())));
            }

            String stateCode = (String) exchange.getIn().getHeader("state_code");
            if (!IsNullOrEmpty(stateCode)) {
                List<Object> list = QueryUtil.DelimStringToParams(stateCode, ",");
                params.addAll(list);
                filter.append(format(" AND (STATE_CODE IN %s) ", QueryUtil.ParamList(list.size())));
            }

            int page = (int) exchange.getIn().getHeader("page");
            int pageSize = (int) exchange.getIn().getHeader("page_size");
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            String orderByColumn = "STATE_CODE"; // Default
            switch (sortOn.toLowerCase()) {
                case "subdiv_fips_code": orderByColumn = "COUNTY_SUBDIV_FIPS_CODE"; break;
                case "subdiv_qualifier": orderByColumn = "COUNTY_SUBDIV_QLFR"; break;
                case "subdiv_name": orderByColumn = "COUNTY_SUBDIV_NAME"; break;
                case "county_fips_code": orderByColumn = "COUNTY_FIPS_CODE"; break;
                case "state_fips_code": orderByColumn = "STATE_FIPS_CODE"; break;
                case "state_code": orderByColumn = "STATE_CODE"; break;
                case "created": orderByColumn = "REC_CREATE_TMSTP"; break;
                case "updated": orderByColumn = "REC_UPDATE_TMSTP"; break;
            }

            Response response = oracleDataService.getCountySubdivLkup(
                    filter.toString(),
                    page,
                    pageSize,
                    orderByColumn,
                    direction,
                    params
            );

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

    public void getPstlCodeLkup(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

        logger.start();

        try {

            List<Object> params = new ArrayList<>();
            StringBuilder filter = new StringBuilder();

            String postalCode = (String) exchange.getIn().getHeader("postal_code");
            if (!IsNullOrEmpty(postalCode)) {
                List<Object> list = QueryUtil.DelimStringToParams(postalCode, ",");
                if (list.size() > 0) {

                    filter.append("AND (");
                    for (int i = 0; i < list.size() - 1; i++) {
                        params.add(format("%%%s%%", list.get(i)));
                        filter.append("PSTL_CODE LIKE ? OR ");
                    }

                    params.add(format("%%%s%%", list.get(list.size() - 1))); // last element
                    filter.append("PSTL_CODE LIKE ?) ");
                }
            }

            String cityName = (String) exchange.getIn().getHeader("city_name");
            if (!IsNullOrEmpty(cityName)) {
                filter.append("AND UPPER(PSTL_CODE_CITY_NAME) LIKE ? ");
                params.add(format("%%%s%%", cityName.toUpperCase()));
            }

            String countyName = (String) exchange.getIn().getHeader("county_name");
            if (!IsNullOrEmpty(countyName)) {
                filter.append("AND UPPER(PSTL_CODE_COUNTY_NAME) LIKE ? ");
                params.add(format("%%%s%%", countyName.toUpperCase()));
            }

            String countyFipsCode = (String) exchange.getIn().getHeader("county_fips_code");
            if (!IsNullOrEmpty(countyFipsCode)) {
                filter.append("AND COUNTY_FIPS_CODE = ? ");
                params.add(countyFipsCode);
            }

            String stateName = (String) exchange.getIn().getHeader("state_name");
            if (!IsNullOrEmpty(stateName)) {
                filter.append("AND UPPER(PSTL_CODE_STATE_NAME) LIKE ? ");
                params.add(format("%%%s%%", stateName.toUpperCase()));
            }

            String stateCode = (String) exchange.getIn().getHeader("state_code");
            if (!IsNullOrEmpty(stateCode)) {
                filter.append("AND UPPER(PSTL_CODE_STATE_CODE) LIKE ? ");
                params.add(format("%%%s%%", stateCode.toUpperCase()));
            }

            String stateFipsCode = (String) exchange.getIn().getHeader("state_fips_code");
            if (!IsNullOrEmpty(stateFipsCode)) {
                filter.append("AND STATE_FIPS_CODE = ? ");
                params.add(stateFipsCode);
            }

            String countyMsaCode = (String) exchange.getIn().getHeader("county_msa_code");
            if (!IsNullOrEmpty(countyMsaCode)) {
                filter.append("AND PSTL_CODE_MSA_CODE = ? ");
                params.add(countyMsaCode);
            }

            String areaCode = (String) exchange.getIn().getHeader("area_code");
            if (!IsNullOrEmpty(areaCode)) {
                filter.append("AND PSTL_CODE_AREA_CODE LIKE ? ");
                params.add(format("%%%s%%", stateCode));
            }

            int page = (int) exchange.getIn().getHeader("page");
            int pageSize = (int) exchange.getIn().getHeader("page_size");
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            String orderByColumn = "PSTL_CODE_CITY_NAME"; // Default
            switch (sortOn.toLowerCase()) {
                case "postal_code": orderByColumn = "PSTL_CODE"; break;
                case "county_name": orderByColumn = "PSTL_CODE_COUNTY_NAME"; break;
                case "county_fips": orderByColumn = "COUNTY_FIPS_CODE"; break;
                case "state_name": orderByColumn = "PSTL_CODE_STATE_NAME"; break;
                case "state_code": orderByColumn = "PSTL_CODE_STATE_CODE"; break;
                case "state_fips": orderByColumn = "STATE_FIPS_CODE"; break;
                case "msa_code": orderByColumn = "PSTL_CODE_MSA_CODE"; break;
                case "fips_cls": orderByColumn = "FIPS_CLS_CODE"; break;
                case "created": orderByColumn = "REC_CREATE_TMSTP"; break;
                case "updated": orderByColumn = "REC_UPDATE_TMSTP"; break;
            }

            Response response = oracleDataService.getPstlCodeLkup(
                    filter.toString(),
                    page,
                    pageSize,
                    orderByColumn,
                    direction,
                    params
            );

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }
    
    public void getExcpLkup(final Exchange exchange) {
        SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

        logger.start();

        try {
            StringBuilder filterTyp = new StringBuilder();
            List<Object> params = new ArrayList<>();
            
            String exceptionTypeName = (String) exchange.getIn().getHeader("exception_type_name");
            
            if (!StringUtil.IsNullOrEmpty(exceptionTypeName)) {
                try {
                    ApplicationModuleName.fromValue(exceptionTypeName);
                } catch (Exception e) {
                    StringBuilder appModName = new StringBuilder();
                    appModName.append("[");
                    int i = 0;
                    for (ApplicationModuleName a : ApplicationModuleName.values()) {
                        appModName.append(a.value() + (i < ApplicationModuleName.values().length - 1 ? ", " : ""));
                        i++;
                    }
                    appModName.append("]");
                    
                    throw new SandataRuntimeException(
                            String.format("The Exception Type Name (exception_type_name) should be in one of following values %s", appModName), e
                    );
                }
                
                filterTyp.append("UPPER(APP_MOD_NAME) = ? AND ");
                params.add(exceptionTypeName.toUpperCase());
            }
            
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");
            
            Response response = oracleDataService.getExcpLkup(filterTyp.toString(), params, sortOn, direction);
            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());
            exchange.getIn().setBody(response.getData());
        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
    
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
}
