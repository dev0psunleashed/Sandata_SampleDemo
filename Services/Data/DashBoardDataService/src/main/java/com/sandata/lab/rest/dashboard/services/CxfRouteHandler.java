package com.sandata.lab.rest.dashboard.services;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.rest.dashboard.app.AppContext;
import com.sandata.lab.rest.dashboard.impl.RestDataService;

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

        // Handle GET
        if (httpMethod.equals("GET") && ( operationName.contains("TotalPayrollHours") || operationName.contains("TotalPayrollDollars") || operationName.contains("TotalOverTimeHours") ) ) {
            restDataService.getTotalOrPendingValues(exchange, operationName);
        } else if (httpMethod.equals("GET") && ( operationName.contains("getScheduledHoursDetails"))) {
            restDataService.getTotalScheduledHoursDetails(exchange);
        } else if (httpMethod.equals("GET") && ( operationName.contains("getScheduledHours"))) {
            restDataService.getTotalScheduledHours(exchange);
        } else if (httpMethod.equals("GET") && ( operationName.contains("getExpiredAuthDetails"))) {
            restDataService.getExpiredAuthDetails(exchange);
        } else if (httpMethod.equals("GET") && ( operationName.contains("getExpiredAuth"))) {
            restDataService.getExpiredAuth(exchange);
        } else if (httpMethod.equals("GET") && ( operationName.contains("getVerifiedHoursDetails"))) {
        restDataService.getVerifiedHoursDetails(exchange);
        } else if (httpMethod.equals("GET") && ( operationName.contains("getVerifiedHours"))) {
            restDataService.getVerifiedHours(exchange);
        }else if((httpMethod.equals("GET") && ( operationName.contains("getVisitExcpBreakdown")))){
            restDataService.getVisitExcpBreakdown(exchange);
        }else if((httpMethod.equals("GET") && ( operationName.contains("getVisitExceptions")))){
            restDataService.getVisitExceptions(exchange);
        }else if( operationName.contains("getStaffsExceptions")){
            restDataService.getStaffVisitExceptions(exchange);
        }else if((httpMethod.equals("GET") && ( operationName.contains("getOpenOrders")))){
            restDataService.getOpenOrder(exchange);
        }else if((httpMethod.equals("GET") && ( operationName.contains("getListCoordinators")))){
            restDataService.getListCoordinators(exchange);
        }else if((httpMethod.equals("GET") && ( operationName.contains("getListPatients")))){
            restDataService.getListPatients(exchange);
        } else if (httpMethod.equals("GET") && operationName.contains("getOutOfComplianceDetail")) {
        	restDataService.getOutOfComplianceDetails(exchange);
        } else if (httpMethod.equals("GET") && operationName.contains("getOutOfCompliance")) {
        	restDataService.getOutOfCompliance(exchange);
        } else if (httpMethod.equals("GET") && operationName.contains("getNonEligiblePatientsDetails")) {
            restDataService.getNonEligiblePatientDetails(exchange);

        } else if (httpMethod.equals("GET") && operationName.contains("getNonEligiblePatients")) {
            restDataService.getNonEligiblePatients(exchange);
        }
        
        
    }
}
