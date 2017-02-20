package com.sandata.lab.rest.ar.impl;

import com.sandata.lab.common.utils.date.DateUtil;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.response.Response;
import org.apache.camel.Exchange;

import java.math.BigDecimal;

import static com.sandata.lab.common.utils.string.StringUtil.IsNullOrEmpty;
import static com.sandata.lab.rest.ar.utils.log.OracleDataLogger.CreateLogger;
import static java.lang.String.format;


public class RestUnappliedDataService extends RestDataService {

    public void setOracleDataService(OracleDataService oracleDataService) {
        this.oracleDataService = oracleDataService;
    }


    protected OracleDataService oracleDataService;


    public void findUnappliedBalances(Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            String transactionTypeString = (String) exchange.getIn().getHeader("transaction_type");

            String transactionCodeString = (String) exchange.getIn().getHeader("transaction_code");


            int page = (int) exchange.getIn().getHeader("page");
            int pageSize = (int) exchange.getIn().getHeader("page_size");

            String payerName = (String) exchange.getIn().getHeader("payer_id");
            String fromDate = (String) exchange.getIn().getHeader("transaction_from_date");
            String toDate = (String) exchange.getIn().getHeader("transaction_to_date");

            // Validate the correct date format is being used. Throws SandataRuntimeException is date/time are not valid and/or null;
            if (!StringUtil.IsNullOrEmpty(fromDate)) {
                fromDate = DateUtil.convertStringUTCtoStringDate(fromDate, "transaction_from_date");
            }

            // Validate the correct date format is being used. Throws SandataRuntimeException is date/time are not valid and/or null;
            if (!StringUtil.IsNullOrEmpty(toDate)) {
                toDate = DateUtil.convertStringUTCtoStringDate(toDate, "transaction_to_date");
            }

            String postedByUser = (String) exchange.getIn().getHeader("user_name");
            String paymentNumber = (String) exchange.getIn().getHeader("payment_number");

            String checkDate = (String) exchange.getIn().getHeader("check_date");
            if (!StringUtil.IsNullOrEmpty(checkDate)) {
                checkDate = DateUtil.convertStringUTCtoStringDate(checkDate, "check_date");
            }
            String batchNumber = (String) exchange.getIn().getHeader("batch_number");
            BigDecimal unappliedBalanceRangeFrom = (BigDecimal) exchange.getIn().getHeader("balance_from");
            BigDecimal unappliedBalanceRangeTo = (BigDecimal) exchange.getIn().getHeader("balance_to");


            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            Response response = oracleDataService.findUnappliedBalances(bsnEntId,
                    payerName,
                    fromDate,
                    toDate,
                    transactionCodeString,
                    transactionTypeString,
                    postedByUser,
                    paymentNumber,
                    checkDate,
                    batchNumber,
                    unappliedBalanceRangeFrom,
                    unappliedBalanceRangeTo,
                    sortOn,
                    direction,
                    page,
                    pageSize

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

    public void findUnappliedUsers(Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }


            int page = (int) exchange.getIn().getHeader("page");
            int pageSize = (int) exchange.getIn().getHeader("page_size");


            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            Response response = oracleDataService.getUnappliedUsers(
                    bsnEntId,
                    page,
                    pageSize,
                    sortOn,
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
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }
}
