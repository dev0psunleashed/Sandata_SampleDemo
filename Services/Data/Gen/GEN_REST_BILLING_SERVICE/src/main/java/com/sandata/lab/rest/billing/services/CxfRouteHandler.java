package com.sandata.lab.rest.billing.services;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.rest.billing.app.AppContext;
import com.sandata.lab.rest.billing.impl.InvoiceDataService;
import com.sandata.lab.rest.billing.impl.RestDataService;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;

/**
 * Date: 9/2/15
 * Time: 3:03 AM
 */

public class CxfRouteHandler {

    public void handler(Exchange exchange) throws SandataRuntimeException {

        try {
            String httpMethod = (String) exchange.getIn().getHeader("CamelHttpMethod");
            String operationName = (String) exchange.getIn().getHeader("operationName");

            CamelContext context = AppContext.getContext();
            RestDataService restDataService = (RestDataService) context.getRegistry().lookupByName("restDataService");
            InvoiceDataService invoiceDataService = (InvoiceDataService) context.getRegistry().lookupByName("invoiceDataService");

            if (httpMethod.equals("GET") && operationName.contains("_getBillingRatePK")) {
                restDataService.getBillingRatePK(exchange);

            } else if (httpMethod.equals("GET") && operationName.contains("_getPayerContracts")) {
                restDataService.getPayerContracts(exchange);

            } else if (httpMethod.equals("GET")
                    && operationName.equals("PKG_BILLING_findBilling_Response")) {
                invoiceDataService.findBilling(exchange);

            } else if (httpMethod.equals("GET")
                    && operationName.equals("PKG_BILLING_getBillingDetailForPK_BillingDetail")) {
                invoiceDataService.getBillingDetailForPK(exchange);

            } else if (httpMethod.equals("POST")
                    && operationName.equals("PKG_BILLING_getBillingPreviewLock_Response")) {
                invoiceDataService.getBillingPreviewLock(exchange);

            } else if (httpMethod.equals("GET")
                    && operationName.equals("PKG_BILLING_submitBillingPreviewLock_Response")) {
                invoiceDataService.submitBillingPreviewLock(exchange);

            } else if (httpMethod.equals("DELETE")
                    && operationName.equals("PKG_BILLING_cancelBillingPreviewLock_Response")) {
                invoiceDataService.cancelBillingPreviewLock(exchange);
            }

            // Handle Insert
            else if (httpMethod.equals("POST")) {
                restDataService.insert(exchange);
            }
            // Handle Update
            else if (httpMethod.equals("PUT")) {
                restDataService.update(exchange);
            } else if (httpMethod.equals("DELETE") && operationName.equals("PKG_BILLING_deleteBilling_Billing")) {
                invoiceDataService.deleteBilling(exchange);
            }
            // Handle Delete
            else if (httpMethod.equals("DELETE")) {
                restDataService.delete(exchange);
            }
            // Handle GET
            else if (httpMethod.equals("GET")) {
                restDataService.get(exchange);
            }
        } finally {
            exchange.getIn().removeHeader("Content-Length");
        }
    }
}
