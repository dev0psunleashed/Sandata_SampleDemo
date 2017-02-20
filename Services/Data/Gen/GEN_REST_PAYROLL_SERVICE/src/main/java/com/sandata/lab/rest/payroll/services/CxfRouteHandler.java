package com.sandata.lab.rest.payroll.services;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.rest.payroll.app.AppContext;
import com.sandata.lab.rest.payroll.impl.RestDataService;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;

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

        // Handle Custom
        if (httpMethod.equals("GET") && operationName.contains("_findPayrollDetail")) {
            restDataService.findPayrollDetail(exchange);
        } else if (httpMethod.equals("GET") && operationName.contains("_getPayrollDetail")) {
            restDataService.getPayrollDetail(exchange);
        } else if (httpMethod.equals("GET") && operationName.contains("_getPrRateMatrixExchange")) {
            restDataService.getPrRateMatrixExchange(exchange);
        } else if (httpMethod.equals("DELETE") && operationName.contains("_deletePrRateMatrixExchange")) {
            restDataService.deletePrRateMatrixExchange(exchange);
        } else if (httpMethod.equals("PUT") && operationName.contains("_updatePrRateMatrixExchange")) {
            restDataService.updatePrRateMatrixExchange(exchange);
        } else if (httpMethod.equals("POST") && operationName.contains("_insertPrRateMatrixExchange")) {
            restDataService.insertPrRateMatrixExchange(exchange);
        } else if (httpMethod.equals("POST") && operationName.equalsIgnoreCase("PAYROLL_submitPayroll")) {

            String businessEntityID = (String) exchange.getIn().getHeader("bsn_ent_id");

            if (StringUtil.IsNullOrEmpty(businessEntityID)) {
                throw new SandataRuntimeException("bsn_ent_id is required!!");

            }

            ProducerTemplate template = context.createProducerTemplate();
            template.sendBodyAndHeader("activemq:queue:PAYROLL-MANUAL-ROUTE", null, "bsnEntID", businessEntityID);

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
