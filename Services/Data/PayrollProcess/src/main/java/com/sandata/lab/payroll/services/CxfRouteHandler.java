package com.sandata.lab.payroll.services;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.payroll.app.AppContext;
import com.sandata.lab.payroll.impl.RestDataService;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;

/**
 * Date: 9/2/15
 * Time: 3:03 AM
 */

public class CxfRouteHandler {

    public void handler(Exchange exchange) throws SandataRuntimeException {

        String httpMethod = (String)exchange.getIn().getHeader("CamelHttpMethod");
        String operationName = (String)exchange.getIn().getHeader("operationName");

        CamelContext context = AppContext.getContext();
        RestDataService restDataService = (RestDataService)context.getRegistry().lookupByName("restDataService");

        if (httpMethod.equals("GET") && operationName.equals("status")) {
            restDataService.status(exchange);
        }
    }
}
