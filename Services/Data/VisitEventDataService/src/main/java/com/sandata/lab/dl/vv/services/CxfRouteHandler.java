package com.sandata.lab.dl.vv.services;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.dl.vv.app.AppContext;
import com.sandata.lab.dl.vv.impl.VisitEventRepository;
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
        VisitEventRepository visitEventRepository = (VisitEventRepository)context.getRegistry().lookupByName("visitEventRepository");

        // Handle Find
        if (httpMethod.equals("GET") && operationName.contains("_getScheduleEvents")) {
            visitEventRepository.getScheduleEvents(exchange);
        }
    }
}
