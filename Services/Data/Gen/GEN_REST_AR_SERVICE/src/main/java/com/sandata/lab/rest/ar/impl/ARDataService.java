package com.sandata.lab.rest.ar.impl;

import static java.lang.String.format;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.sandata.lab.common.utils.date.DateUtil;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.model.find.AccountsReceivableTransactionBatchDetailExt;
import com.sandata.lab.data.model.response.Response;
import org.apache.camel.Exchange;
import org.apache.cxf.message.MessageContentsList;

import static com.sandata.lab.rest.ar.utils.log.OracleDataLogger.CreateLogger;


public class ARDataService extends RestDataService {

    /**
     * @param exchange
     * @return
     * @throws Exception
     */
    public void findAR(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList params = (MessageContentsList) exchange.getIn().getBody();

            String bsnEntId = (String) params.get(0);
            String payerId = (String) params.get(1);
            String contractId = (String) params.get(2);
            String invDateFrom = (String) params.get(3);
            String invDateTo = (String) params.get(4);
            // Convert to Date to check format
            if (!StringUtil.IsNullOrEmpty(invDateFrom)) {
                invDateFrom = DateUtil.convertStringUTCtoStringDate(invDateFrom, "inv_date_from");
            }

            if (!StringUtil.IsNullOrEmpty(invDateTo)) {
                invDateTo = DateUtil.convertStringUTCtoStringDate(invDateTo, "inv_date_to");
            }

            String invNum = (String) params.get(5);

            String invStartDate = (String) params.get(6);
            String invEndDate = (String) params.get(7);

            if (!StringUtil.IsNullOrEmpty(invStartDate)) {
                invStartDate = DateUtil.convertStringUTCtoStringDate(invStartDate, "inv_start_date");
            }

            if (!StringUtil.IsNullOrEmpty(invEndDate)) {
                invEndDate = DateUtil.convertStringUTCtoStringDate(invEndDate, "inv_end_date");
            }

            String ptFirstName = (String) params.get(8);
            String ptLastName = (String) params.get(9);
            String beLocId = (String) params.get(10);
            String beLobId = (String) params.get(11);

            BigDecimal billed = (BigDecimal) params.get(12);
            BigDecimal paid = (BigDecimal) params.get(13);
            boolean openBalance = (boolean) params.get(14);
            String invStatusCode = (String) params.get(15);
            String arTxnCode = (String) params.get(16);

            int page = (Integer) params.get(17);
            int pageSize = (Integer) params.get(18);

            String orderByColumn = (String) params.get(19);
            String orderByDirection = (String) params.get(20);

            Response response = oracleDataService.findAR(bsnEntId, payerId, contractId, invDateFrom, invDateTo, invNum,
                    invStartDate, invEndDate, ptLastName, ptFirstName, beLocId, beLobId, billed, paid, openBalance, invStatusCode,
                    arTxnCode, page, pageSize, orderByColumn, orderByDirection);


            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {

            e.printStackTrace();

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getARServiceDetails(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            String payerId = (String) exchange.getIn().getHeader("payer_id");
            String contractId = (String) exchange.getIn().getHeader("contract_id");
            String invoiceNumber = (String) exchange.getIn().getHeader("inv_num");

            int page = (Integer) exchange.getIn().getHeader("page");
            int pageSize = (Integer) exchange.getIn().getHeader("page_size");

            String orderByColumn = (String) exchange.getIn().getHeader("sort_on");
            String orderByDirection = (String) exchange.getIn().getHeader("direction");

            Response response = oracleDataService.getARServiceDetails(bsnEntId, payerId,
                    contractId, invoiceNumber, page, pageSize, orderByColumn, orderByDirection);


            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {

            e.printStackTrace();

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }


    }

    public void getAccountsReceivableTransactionsByInvoiceNumber(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String bsnEntID = (String) exchange.getIn().getHeader("bsn_ent_id");

            if (StringUtil.IsNullOrEmpty(bsnEntID)) {
                throw new SandataRuntimeException("Business entity ID (bsn_ent_id) is required!");
            }

            String invoiceNumber = (String) exchange.getIn().getHeader("invoice_number");

            if (StringUtil.IsNullOrEmpty(invoiceNumber)) {
                throw new SandataRuntimeException("Invoice Number (invoice_number) is required!");
            }

            int page = (int) exchange.getIn().getHeader("page");
            int pageSize = (int) exchange.getIn().getHeader("page_size");
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");


            Response response = oracleDataService.getTransactionsByARInvoiceNumber(bsnEntID, invoiceNumber, page, pageSize, null, direction);


            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {

            e.printStackTrace();
            throw new SandataRuntimeException(e.getMessage(), e);
        }

    }

    public void getAccountsReceivableTransactionDetailBySK(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            BigInteger sequenceKey = (BigInteger) exchange.getIn().getHeader("sequence_key");

            AccountsReceivableTransactionBatchDetailExt accountsReceivableTransactionBatchDetailExt = oracleDataService.getAccountsReceivableTransactionBySK(sequenceKey);

            exchange.getIn().setHeader("TOTAL_ROWS", 1);


            exchange.getIn().setBody(accountsReceivableTransactionBatchDetailExt);

        } catch (Exception e) {

            e.printStackTrace();
            throw new SandataRuntimeException(e.getMessage(), e);
        }

    }


    public void getAccountsReceivableTransactionOpenBatch(final Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            String userName = (String) exchange.getIn().getHeader("username");
            if (StringUtil.IsNullOrEmpty(userName)) {
                throw new SandataRuntimeException(exchange, "User Name (username) is required!");
            }

            String userGuid = (String) exchange.getIn().getHeader("user_guid");
            if (StringUtil.IsNullOrEmpty(userGuid)) {
                throw new SandataRuntimeException(exchange, "User GUID (user_guid) is required!");
            }

            String payerId = (String) exchange.getIn().getHeader("payer_id");


            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException(exchange, "Business entity ID (bsn_ent_id) is required!");
            }

            exchange.getIn().setBody(oracleDataService.getAccountsReceivableTransactionOpenBatch(userName, userGuid, payerId, bsnEntId));

        } catch (Exception e) {
            String errMsg = format("%s: getAccountsReceivableTransactionOpenBatch: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Find a list of AccountsReceivableTransactionBatch by payerId, bsnEntId and batchNumber
     *
     * @param exchange
     * @throws SandataRuntimeException
     */
    public void findBatchByPayer(final Exchange exchange) throws SandataRuntimeException {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String payerId = (String) exchange.getIn().getHeader("payer_id");

            //We do not check the payerId as a required field as in Private Pay, we will not have payerId

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String batchNumber = (String) exchange.getIn().getHeader("batch_number");
            if (StringUtil.IsNullOrEmpty(batchNumber)) {
                throw new SandataRuntimeException(exchange, "BatchNumber (batch_number) is required!");
            }

            exchange.getIn().setBody(oracleDataService.findBatchByPayer(payerId, bsnEntId, batchNumber));

        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(e.getMessage(), e);

        } finally {
            logger.stop();
        }
    }

    public void getOpenCheckInformation(final Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            String payerId = (String) exchange.getIn().getHeader("payer_id");
            if (StringUtil.IsNullOrEmpty(payerId)) {
                throw new SandataRuntimeException(exchange, "PayerID (payer_id) is required!");
            }

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String batchNumber = (String) exchange.getIn().getHeader("batch_number");
            if (StringUtil.IsNullOrEmpty(batchNumber)) {
                throw new SandataRuntimeException(exchange, "BatchNumber (batch_number) is required!");
            }

            exchange.getIn().setBody(oracleDataService.getOpenCheckInformation(payerId, bsnEntId, batchNumber));

        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(e.getMessage(), e);

        } finally {
            logger.stop();
        }
    }

    public void getCheckExists(final Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            String payerId = (String) exchange.getIn().getHeader("payer_id");
            if (StringUtil.IsNullOrEmpty(payerId)) {
                throw new SandataRuntimeException(exchange, "PayerID (payer_id) is required!");
            }

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String paymentNumber = (String) exchange.getIn().getHeader("payment_number");
            if (StringUtil.IsNullOrEmpty(paymentNumber)) {
                throw new SandataRuntimeException(exchange, "PaymentNumber (payment_number) is required!");
            }

            String paymentAmount = (String) exchange.getIn().getHeader("payment_amount");
            if (StringUtil.IsNullOrEmpty(paymentAmount)) {
                throw new SandataRuntimeException(exchange, "PaymentAmount (payment_amount) is required!");
            }


            exchange.getIn().setBody(oracleDataService.checkExists(bsnEntId,
                    payerId,
                    paymentNumber,
                    paymentAmount));

        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(e.getMessage(), e);

        } finally {
            logger.stop();
        }
    }
}