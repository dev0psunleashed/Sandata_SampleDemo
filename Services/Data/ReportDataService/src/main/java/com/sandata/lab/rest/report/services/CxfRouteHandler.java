package com.sandata.lab.rest.report.services;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.rest.report.app.AppContext;
import com.sandata.lab.rest.report.impl.RestDataService;

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

        /*
        // Handle GET
        if (httpMethod.equals("GET") && ( operationName.contains("TotalPayrollHours") || operationName.contains("TotalPayrollDollars") || operationName.contains("TotalOverTimeHours") ) ) {
            restDataService.getTotalOrPendingValues(exchange, operationName);
    */
        
    }
}
