package com.sandata.lab.logger.services;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.logger.app.AppContext;
import com.sandata.lab.logger.impl.RestDataService;
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

        } else if (httpMethod.equals("GET") && operationName.equals("PKG_APP_getAppLogPK_ApplicationLog")) {
            restDataService.getAppLogPK(exchange);

        } else if (httpMethod.equals("GET") && operationName.equals("PKG_APP_getAppSessPK_ApplicationSession")) {
            restDataService.getAppSessPK(exchange);

        } else if (httpMethod.equals("GET") && operationName.equals("PKG_APP_getAppLogHistory_ApplicationLog")) {
            restDataService.getAppLogHistory(exchange);

        } else if (httpMethod.equals("POST") && operationName.equals("PKG_APP_addLogForUserGUID_Response")) {
            restDataService.addLogForUserGUID(exchange);

        } else if (httpMethod.equals("POST") && operationName.equals("PKG_APP_addLog_Response")) {
            restDataService.addLog(exchange);
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
