package com.sandata.lab.rest.auth.services;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.rest.auth.app.AppContext;
import com.sandata.lab.rest.auth.impl.RestDataService;
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

        if (httpMethod.equals("GET")
            && (operationName.contains("_getActiveAuth")
            || operationName.contains("_getHistoricAuth"))) {
            restDataService.getActiveOrHistoricAuthAndOrderList(exchange);
        } else if (httpMethod.equals("GET")
            && (operationName.contains("_getAuthSvc")
            || operationName.contains("_getOrdSvc"))
            && (exchange.getIn().getHeader("auth_sk") != null)
            || exchange.getIn().getHeader("ord_sk") != null) {
            restDataService.getAuthOrOrdSvcForSk(exchange);
        } else if (httpMethod.equals("POST")) {
            restDataService.insert(exchange);
        } else if (httpMethod.equals("PUT")
            && (operationName.contains("_updateAuth_")
            || operationName.contains("_updateOrd_"))) {
            restDataService.updateAuthOrOrd(exchange);
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
