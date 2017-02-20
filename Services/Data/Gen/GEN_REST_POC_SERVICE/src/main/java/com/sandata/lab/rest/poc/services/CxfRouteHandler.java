package com.sandata.lab.rest.poc.services;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.rest.poc.app.AppContext;
import com.sandata.lab.rest.poc.impl.RestDataService;
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

        // Handle Insert
        if (httpMethod.equals("POST")) {
            restDataService.insert(exchange);
        }
        // Handle Update
        else if (httpMethod.equals("PUT")) {
            restDataService.update(exchange);
        }
        // Handle Delete
        else if (httpMethod.equals("DELETE")) {
            restDataService.delete(exchange);
        } else if (httpMethod.equals("GET")
            && operationName.contains("_getPocTaskLstForPocSk")) {
            restDataService.getPocTaskListForPocSk(exchange);
        }else if (httpMethod.equals("GET")
            && operationName.contains("_getPocTaskLstForTaskId")) {
            restDataService.getPocTaskLstForTaskId(exchange);
        }
        // Handle GET
        else if (httpMethod.equals("GET")) {
            restDataService.get(exchange);
        }
    }
}
