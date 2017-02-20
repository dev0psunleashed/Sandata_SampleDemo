package com.sandata.one.aggregator.audit.services;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.one.aggregator.audit.app.AppContext;
import com.sandata.one.aggregator.audit.impl.RestDataService;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;

/**
 * Date: 9/2/15
 * Time: 3:03 AM
 */

public class CxfRouteHandler {

    public void handler(Exchange exchange) throws SandataRuntimeException {

        try {
            String httpMethod = (String)exchange.getIn().getHeader("CamelHttpMethod");
            String operationName = (String)exchange.getIn().getHeader("operationName");
    
            CamelContext context = AppContext.getContext();
            RestDataService restDataService = (RestDataService)context.getRegistry().lookupByName("restDataService");

            if (httpMethod.equals("GET") && operationName.equals("PKG_forcedLogout_Response")) {
                restDataService.forcedLogout(exchange);
            }
            

        } finally {
            exchange.getIn().removeHeader("Content-Length");
        }
    }
}
