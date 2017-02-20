package com.sandata.lab.rest.ar.impl;

import com.sandata.lab.common.utils.date.DateUtil;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.response.Response;
import org.apache.camel.Exchange;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.sandata.lab.rest.ar.utils.log.OracleDataLogger.CreateLogger;
import static java.lang.String.format;

/**
 * <p>RestManualPostsDataService</p>
 * <p>Date: 7/29/2016</p>
 * <p>Time: 3:00 PM</p>
 *
 * @author jasonscott
 */
public class RestManualPostsDataService extends RestDataService {

    private OracleDataService oracleDataService;

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

    public void findArTxnBatch(Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (bsnEntId == null
                || bsnEntId.isEmpty()) {
                throw new SandataRuntimeException("Business entity ID (bsn_ent_id) is required!");
            }

            String batchNumber = (String) exchange.getIn().getHeader("batch_number");
            String payerId = (String) exchange.getIn().getHeader("payer_id");
            String checkNumber = (String) exchange.getIn().getHeader("check_number");
            String checkDepositDateString = (String) exchange.getIn().getHeader("check_deposit_date");
            BigDecimal totalAmount = (BigDecimal) exchange.getIn().getHeader("total_amount");
            BigDecimal paymentPostedUnapplied = (BigDecimal) exchange.getIn().getHeader("payment_posted_unapplied");
            BigDecimal totalDenied = (BigDecimal) exchange.getIn().getHeader("total_denied");
            BigDecimal totalPaid = (BigDecimal) exchange.getIn().getHeader("total_paid");
            BigDecimal totalOpen = (BigDecimal) exchange.getIn().getHeader("total_open");
            Boolean batchPosted = (Boolean) exchange.getIn().getHeader("batch_posted");

            Integer page = (Integer) exchange.getIn().getHeader("page");
            Integer pageSize = (Integer) exchange.getIn().getHeader("page_size");
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            String orderBy = "AR_TXN_BATCH_ID";

            if (!StringUtil.IsNullOrEmpty(checkDepositDateString)){
                checkDepositDateString = DateUtil.convertStringUTCtoStringDate(checkDepositDateString, "check_deposit_date");
            }

            Response response =
                oracleDataService.findArTxnBatch(bsnEntId,
                    batchNumber,
                    payerId,
                    checkNumber,
                    checkDepositDateString,
                    totalAmount,
                    paymentPostedUnapplied,
                    totalDenied,
                    totalPaid,
                    totalOpen,
                    batchPosted,
                    page,
                    pageSize,
                   orderBy,
                    direction);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());
            exchange.getIn().setBody(response.getData());

        } catch (java.lang.Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        } finally {
            logger.stop();
        }

    }

    public void findArByBatchNumber(Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException("Business Entity ID (bsn_ent_id) is required!");
            }

            String batchNumber = (String) exchange.getIn().getHeader("batch_number");
            if (StringUtil.IsNullOrEmpty(batchNumber)) {
                throw new SandataRuntimeException("Batch Number (batch_number) is required!");
            }

            String invoiceNumber = (String) exchange.getIn().getHeader("invoice_number");

            Boolean batchIndicator = (Boolean) exchange.getIn().getHeader("unapplied_ind");

            long batchDetSK = (long) exchange.getIn().getHeader("batch_det_sk");

            Integer page = (Integer) exchange.getIn().getHeader("page");
            Integer pageSize = (Integer) exchange.getIn().getHeader("page_size");
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            Response response = oracleDataService.findArByBatchNumber(
                                    bsnEntId,
                                    batchNumber,
                                    invoiceNumber,
                                    batchDetSK,
                                    batchIndicator,
                                    page,
                                    pageSize,
                                    sortOn,
                                    direction);

            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);


            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());
            exchange.getIn().setBody(response.getData());

        } catch (java.lang.Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        } finally {
            logger.stop();
        }
    }

    public void findInvoiceByInvoiceNumber(final Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException("Business Entity ID (bsn_ent_id) is required!");
            }

            String invoiceNumber = (String) exchange.getIn().getHeader("invoice_number");
            if (StringUtil.IsNullOrEmpty(invoiceNumber)) {
                throw new SandataRuntimeException("Invoice Number (invoice_number) is required!");
            }

            String payerId = (String) exchange.getIn().getHeader("payer_id");
            //dmr--BAR-271: Not mandatory field as per Hiren
            /*if (StringUtil.IsNullOrEmpty(payerId)) {
                throw new SandataRuntimeException("Payer ID (payer_id) is required!");
            }*/

            exchange.getIn().setBody(oracleDataService.findInvoiceByInvoiceNumber(invoiceNumber, payerId, bsnEntId));

        } catch(Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        } finally {
            logger.stop();
        }
    }
}
