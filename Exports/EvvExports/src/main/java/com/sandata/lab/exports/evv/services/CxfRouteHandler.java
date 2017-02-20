package com.sandata.lab.exports.evv.services;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.exports.evv.app.AppContext;
import com.sandata.lab.exports.evv.impl.EvvExportRepository;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 10/8/2016
 * Time: 8:03 AM
 */
public class CxfRouteHandler {
    public void handler(Exchange exchange) throws SandataRuntimeException {
        String httpMethod = (String)exchange.getIn().getHeader("CamelHttpMethod");
        String operationName = (String)exchange.getIn().getHeader("operationName");

        CamelContext context = AppContext.getContext();
        EvvExportRepository evvExportRepository = (EvvExportRepository)context.getRegistry().lookupByName("evvExportRepository");

        // Handle getUploadSchedules
        if (httpMethod.equals("GET") && operationName.contains("_getUploadSchedules")) {
            evvExportRepository.getUploadSchedules(exchange);
        }
        else if(httpMethod.equals("GET") && operationName.contains("_getAccountsScheduledToBeExported")) {
            evvExportRepository.getAccountsScheduledToBeExported(exchange);
        }
    }
}
