package com.sandata.lab.rest.ar.impl;

import com.sandata.lab.common.utils.date.DateUtil;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.response.Response;
import org.apache.camel.Exchange;

import org.apache.cxf.message.MessageContentsList;

import java.math.BigDecimal;


import static com.sandata.lab.rest.ar.utils.log.OracleDataLogger.CreateLogger;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */
@SuppressWarnings("unchecked")
public class PostDataService extends RestDataService {

    public void findAutoPost(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String providerName = (String) exchange.getIn().getHeader("provider_name");
            String taxId = (String) exchange.getIn().getHeader("tax_id");
            String payerId = (String) exchange.getIn().getHeader("payer_id");

            String checkDateVal = (String) exchange.getIn().getHeader("check_date");
            String eraReceivedDateVal = (String) exchange.getIn().getHeader("era_rcvd_date");
            String checkReceivedDateVal = (String) exchange.getIn().getHeader("check_rcvd_date");

            if(!StringUtil.IsNullOrEmpty(checkDateVal)) {
                checkDateVal = DateUtil.convertStringUTCtoStringDate(checkDateVal, "check_date");
            }
            if(!StringUtil.IsNullOrEmpty(eraReceivedDateVal)) {
                eraReceivedDateVal = DateUtil.convertStringUTCtoStringDate(eraReceivedDateVal, "era_rcvd_date");
            }
            if(!StringUtil.IsNullOrEmpty(checkReceivedDateVal)) {
                checkReceivedDateVal = DateUtil.convertStringUTCtoStringDate(checkReceivedDateVal, "check_rcvd_date");
            }

            String checkNumber = (String) exchange.getIn().getHeader("check_num");
            BigDecimal checkAmount = (BigDecimal) exchange.getIn().getHeader("check_amount");
            Boolean isPLB = (Boolean) exchange.getIn().getHeader("is_plb");

            String status = (String) exchange.getIn().getHeader("status");

            int page = (int) exchange.getIn().getHeader("page");
            int pageSize = (int) exchange.getIn().getHeader("page_size");
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");


        /*    Response response = oracleDataService.arFind(bsnEntId, providerName, taxId, payerId, checkDateVal, eraReceivedDateVal, checkReceivedDateVal
                    , checkNumber, checkAmount, isPLB, status, page, pageSize, sortOn, direction);


            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());*/

        } catch (Exception e) {

            e.printStackTrace();
            throw new SandataRuntimeException(e.getMessage(), e);
        }

    }

    public void getAutopostDetails(Exchange exchange) throws SandataRuntimeException{


        try {


            long arSk = (long) exchange.getIn().getHeader("sequence_key");


            int page = (int) exchange.getIn().getHeader("page");
            int pageSize = (int) exchange.getIn().getHeader("page_size");
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

         /*   Response response = oracleDataService.getARDetails(arSk, page, pageSize, sortOn, direction);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());*/
        }catch (Exception e){

            e.printStackTrace();
            throw new SandataRuntimeException(e.getMessage(), e);

        }
    }

    public void getAutopostSummary(Exchange exchange) throws SandataRuntimeException{


        try {


            long arSk = (long) exchange.getIn().getHeader("sequence_key");


            Response response = oracleDataService.getARSummary(arSk);


            exchange.getIn().setBody(response.getData());
        }catch (Exception e){

            e.printStackTrace();
            throw new SandataRuntimeException(e.getMessage(), e);

        }
    }

}
