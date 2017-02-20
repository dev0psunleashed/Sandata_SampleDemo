package com.sandata.one.aggregator.lookup.impl;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.exception.SandataValidationException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.response.Response;
import com.sandata.one.aggregator.lookup.api.DataService;
import com.sandata.one.aggregator.lookup.utils.data.ReferenceValueUtil;
import org.apache.camel.Exchange;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

import static com.sandata.lab.common.utils.string.StringUtil.IsNullOrEmpty;
import static com.sandata.one.aggregator.lookup.utils.log.OracleDataLogger.CreateLogger;
import static java.lang.String.format;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */
@SuppressWarnings("unchecked")
public class RestDataService implements DataService {

    private OracleDataService oracleDataService;

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

    public void getPayerLookup(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            int page = (Integer)exchange.getIn().getHeader("page");
            int pageSize = (Integer)exchange.getIn().getHeader("page_size");
            String sortOn = (String)exchange.getIn().getHeader("sort_on");
            String direction = (String)exchange.getIn().getHeader("direction");

            Response response = oracleDataService.getPayerLookup(page, pageSize, sortOn, direction);

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

    public void getContractLookup(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String payerId = (String)exchange.getIn().getHeader("payer_id");

            if (StringUtils.isEmpty(payerId)) {
                throw new SandataRuntimeException("PayerID (payer_id) is required!");
            }

            String[] payerIdList = payerId.split(",");

            int page = (Integer)exchange.getIn().getHeader("page");
            int pageSize = (Integer)exchange.getIn().getHeader("page_size");
            String sortOn = (String)exchange.getIn().getHeader("sort_on");
            String direction = (String)exchange.getIn().getHeader("direction");

            Response response = oracleDataService.getContractLookup(page, pageSize, sortOn, direction, payerIdList);

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

    public void getBeExcpLst(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            int page = (Integer)exchange.getIn().getHeader("page");
            int pageSize = (Integer)exchange.getIn().getHeader("page_size");
            String sortOn = (String)exchange.getIn().getHeader("sort_on");
            String direction = (String)exchange.getIn().getHeader("direction");

            Response response = oracleDataService.getBeExcpLst(page, pageSize, sortOn, direction);

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

    public void getCoordinatorLookup(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String coordinatorName = (String)exchange.getIn().getHeader("coordinator_name");
            String coordinatorId = (String)exchange.getIn().getHeader("coordinator_id");
            String bsnEntId = (String)exchange.getIn().getHeader("bsn_ent_id");

            // validate input params
            if (IsNullOrEmpty(bsnEntId)) {
                throw new SandataValidationException("BusinessEntityID (bsn_ent_id) is required!");
            }

            if (IsNullOrEmpty(coordinatorName)
                    && IsNullOrEmpty(coordinatorId) && IsNullOrEmpty(bsnEntId)) {

                throw new SandataValidationException(
                        "At least one of the following parameters is required: coordinatorName, coordinator_id");
            }

            if (!IsNullOrEmpty(coordinatorName) && coordinatorName.length() < 3) {
                throw new SandataValidationException("coordinator_name = '" + coordinatorName + "'. Length of coordinator_name must be equal to or greater than 3");
            }

            int page = (Integer)exchange.getIn().getHeader("page");
            int pageSize = (Integer)exchange.getIn().getHeader("page_size");
            String sortOn = (String)exchange.getIn().getHeader("sort_on");
            String direction = (String)exchange.getIn().getHeader("direction");

            Response response = oracleDataService.getCoordintorLookup(coordinatorName, coordinatorId, bsnEntId, page, pageSize, sortOn, direction);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());

        } catch (SandataValidationException e) {
            throw e;

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getPatientLookup(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String lastName = (String)exchange.getIn().getHeader("last_name");
            String firstName = (String)exchange.getIn().getHeader("first_name");
            String patientId = (String)exchange.getIn().getHeader("patient_id");
            String medicaidId = (String)exchange.getIn().getHeader("medicaid_id");

            // validate input params
            if (IsNullOrEmpty(lastName) && IsNullOrEmpty(firstName)
                    && IsNullOrEmpty(patientId) && IsNullOrEmpty(medicaidId)) {

                throw new SandataValidationException(
                        "At least one of the following parameters is required: last_name, first_name, patient_id, medicaid_id");
            }

            if (!IsNullOrEmpty(lastName) && lastName.length() < 3) {
                throw new SandataValidationException("last_name = '" + lastName + "'. Length of last_name must be equal to or greater than 3");
            }

            if (!IsNullOrEmpty(firstName) && firstName.length() < 3) {
                throw new SandataValidationException("first_name = '" + firstName + "'. Length of first_name must be equal to or greater than 3");
            }

            int page = (Integer)exchange.getIn().getHeader("page");
            int pageSize = (Integer)exchange.getIn().getHeader("page_size");
            String sortOn = (String)exchange.getIn().getHeader("sort_on");
            String direction = (String)exchange.getIn().getHeader("direction");

            Response response = oracleDataService.getPatientLookup(lastName, firstName, patientId, medicaidId, page, pageSize, sortOn, direction);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());

        } catch (SandataValidationException e) {
            throw e;

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getStaffLookup(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String lastName = (String)exchange.getIn().getHeader("last_name");
            String firstName = (String)exchange.getIn().getHeader("first_name");
            String staffId = (String)exchange.getIn().getHeader("staff_id");
            String staffSSN = (String)exchange.getIn().getHeader("staff_ssn");

            // validate input params
            if (IsNullOrEmpty(lastName) && IsNullOrEmpty(firstName)
                    && IsNullOrEmpty(staffId) && IsNullOrEmpty(staffSSN)) {

                throw new SandataValidationException(
                        "At least one of the following parameters is required: last_name, first_name, staff_id, staff_ssn");
            }

            if (!IsNullOrEmpty(lastName) && lastName.length() < 3) {
                throw new SandataValidationException("last_name = '" + lastName + "'. Length of last_name must be equal to or greater than 3");
            }

            if (!IsNullOrEmpty(firstName) && firstName.length() < 3) {
                throw new SandataValidationException("first_name = '" + firstName + "'. Length of first_name must be equal to or greater than 3");
            }

            int page = (Integer)exchange.getIn().getHeader("page");
            int pageSize = (Integer)exchange.getIn().getHeader("page_size");
            String sortOn = (String)exchange.getIn().getHeader("sort_on");
            String direction = (String)exchange.getIn().getHeader("direction");

            Response response = oracleDataService.getStaffLookup(lastName, firstName, staffId, staffSSN, page, pageSize, sortOn, direction);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());

        } catch (SandataValidationException e) {
            throw e;

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getAgencyLookup(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            int page = (Integer)exchange.getIn().getHeader("page");
            int pageSize = (Integer)exchange.getIn().getHeader("page_size");
            String sortOn = (String)exchange.getIn().getHeader("sort_on");
            String direction = (String)exchange.getIn().getHeader("direction");

            Response response = oracleDataService.getAgencyLookup(page, pageSize, sortOn, direction);

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

    public void getContractService(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String payerId = (String)exchange.getIn().getHeader("payer_id");
            String contractId = (String)exchange.getIn().getHeader("contract_id");

            exchange.getIn().setBody(oracleDataService.getContractService(
                    (payerId == null) ? Optional.empty() : Optional.of(payerId),
                    (contractId == null) ? Optional.empty() : Optional.of(contractId)));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg);

        } finally {
            logger.stop();
        }
    }

    public void getAppRoleLookup(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            int page = (Integer)exchange.getIn().getHeader("page");
            int pageSize = (Integer)exchange.getIn().getHeader("page_size");
            String sortOn = (String)exchange.getIn().getHeader("sort_on");
            String direction = (String)exchange.getIn().getHeader("direction");

            Response response = oracleDataService.getAppRoleLookup(page, pageSize, sortOn, direction);

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

    public OracleDataService getOracleDataService() {
        return oracleDataService;
    }

    public void setOracleDataService(OracleDataService oracleDataService) {
        this.oracleDataService = oracleDataService;
    }



    /**
     * Get all permissions
     * @param exchange
     */
    public void getAllPermission(Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            Response response = oracleDataService.getAllPermission();
            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    //TODO:
    public void getPermissionForRole(Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);
        logger.start();

        try {

            if (exchange.getIn().getHeader("role_sk") == null) {
                throw new SandataRuntimeException("App role sk is missing , please correct the url");
            }
            long appRoleSk = (long) exchange.getIn().getHeader("role_sk");

            Response response = oracleDataService.getPermissionForRole(appRoleSk);
            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }
}
