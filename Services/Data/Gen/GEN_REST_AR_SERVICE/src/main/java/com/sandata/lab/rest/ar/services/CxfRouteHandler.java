package com.sandata.lab.rest.ar.services;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.rest.ar.app.AppContext;
import com.sandata.lab.rest.ar.impl.*;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;

/**
 * Date: 9/2/15
 * Time: 3:03 AM
 */

public class CxfRouteHandler {

    public void handler(Exchange exchange) throws SandataRuntimeException {

        String httpMethod = (String) exchange.getIn().getHeader("CamelHttpMethod");
        String operationName = (String) exchange.getIn().getHeader("operationName");

        CamelContext context = AppContext.getContext();
        RestDataService restDataService = (RestDataService) context.getRegistry().lookupByName("restDataService");
        RestUnappliedDataService restUnappliedDataService = (RestUnappliedDataService) context.getRegistry().lookupByName("restUnappliedDataService");

        RestManualPostsDataService restManualPostsDataService = (RestManualPostsDataService) context.getRegistry().lookupByName("restManualPostsDataService");

        PostDataService postDataService = (PostDataService) context.getRegistry().lookupByName("postDataService");

        ARDataService arDataService = (ARDataService) context.getRegistry().lookupByName("arDataService");


        if (httpMethod.equals("GET") && operationName.contains("_findUnappliedTransactions_")) {
            restUnappliedDataService.findUnappliedBalances(exchange);
        } else if (httpMethod.equals("GET") && operationName.contains("_CheckExists_")) {
            arDataService.getCheckExists(exchange);
        } else if (httpMethod.equals("GET") && operationName.contains("_getArTxnBatchDetail_")) {
            arDataService.getAccountsReceivableTransactionDetailBySK(exchange);
        } else if (httpMethod.equals("GET") && operationName.contains("_findUnappliedUsers_")) {
            restUnappliedDataService.findUnappliedUsers(exchange);
        } else if (httpMethod.equals("GET") && operationName.contains("_getARDetails_")) {
            postDataService.getAutopostDetails(exchange);
        } else if (httpMethod.equals("GET") && operationName.contains("_findARDetail_")) {
            postDataService.findAutoPost(exchange);
        } else if (httpMethod.equals("GET") && operationName.contains("_getArTxnBatchByInvoiceNumber_")) {
            arDataService.getAccountsReceivableTransactionsByInvoiceNumber(exchange);
        } else if (httpMethod.equals("GET") && operationName.contains("_getARSummary_")) {
            postDataService.getAutopostSummary(exchange);
        } else if (httpMethod.equals("GET")
                && operationName.contains("_findArTxnBatch_")) {
            restManualPostsDataService.findArTxnBatch(exchange);
        } else if (httpMethod.equals("GET")
                && operationName.equals("PKG_AR_findArByBatchNumber_Response")) {
            restManualPostsDataService.findArByBatchNumber(exchange);
        } else if (httpMethod.equals("GET") && operationName.equals("PKG_INV_findInvoiceByInvoiceNumber_InvoiceExt")) {
            restManualPostsDataService.findInvoiceByInvoiceNumber(exchange);
        } else if (httpMethod.equals("GET")
                && operationName.contains("_findAR_")) {
            arDataService.findAR(exchange);
        } else if (httpMethod.equals("GET")
                && operationName.contains("_getARServiceDetails_")) {
            arDataService.getARServiceDetails(exchange);
        } else if (httpMethod.equals("GET") && operationName.contains("_getArTxnOpenBatch_")) {
            arDataService.getAccountsReceivableTransactionOpenBatch(exchange);
        } else if (httpMethod.equals("GET")
                && operationName.contains("_findBatchByPayer_")) {
            arDataService.findBatchByPayer(exchange);
        } else if (httpMethod.equals("GET") && operationName.contains("_getOpenCheck_")) {
            arDataService.getOpenCheckInformation(exchange);
        }
        // Handle Insert
        else if (httpMethod.equals("POST")) {
            restDataService.insert(exchange);
        }
        // Handle Update
        else if (httpMethod.equals("PUT")) {
            restDataService.update(exchange);
        }
        // Handle Delete
        else if (httpMethod.equals("DELETE")) {
            restDataService.delete(exchange);
        }
        // Handle GET
        else if (httpMethod.equals("GET")) {
            restDataService.get(exchange);
        }
    }
}
