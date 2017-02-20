package com.sandata.lab.rest.visit.services;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.rest.visit.app.AppContext;
import com.sandata.lab.rest.visit.impl.RestDataService;
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
    
            // Handle Find
            if (httpMethod.equals("PUT") && operationName.contains("_acceptCalls")) {
                restDataService.acceptCalls(exchange);
            }
            else if (httpMethod.equals("PUT") && operationName.contains("_updateVvRoundingRules_")) {
                restDataService.updateVVBillableHrs(exchange);
            }
            else if (httpMethod.equals("PUT") && operationName.equals("PKG_VISIT_updateVisitDetails_Response")) {
                restDataService.updateVisitDetails(exchange);
            }
            else if (httpMethod.equals("GET") && operationName.equals("PKG_VISIT_findVisitDetails_Response")) {
                restDataService.findVisitDetails(exchange);
            }
            else if (httpMethod.equals("GET") && operationName.contains("_findVisit")) {
                restDataService.findVisit(exchange);
            }
            else if (httpMethod.equals("GET") && operationName.contains("_getVisitsForPatient")) {
                restDataService.getVisitsForPatient(exchange);
            }
            else if (httpMethod.equals("GET") && operationName.equals("PKG_VISIT_getVisitHistDetail_Response")) {
                restDataService.getVisitHistDetail(exchange);
                
            }  else if (httpMethod.equals("GET") && operationName.equals("PKG_VISIT_getVisitNoteList_VisitNote_List")) {
                restDataService.getVisitNoteList(exchange);
            }
            else if(httpMethod.equals("GET") && operationName.equals("getVisitTaskListDetails")){
                restDataService.getVisitTaskDetailsForVisit(exchange);
                
            } else if (httpMethod.equals("GET") && operationName.equals("findStaffsForSearch")) { //rwu
                restDataService.getStaffsForSearch(exchange);
            
            } else if (httpMethod.equals("GET") && operationName.equals("findPatientsForSearch")) { //rwu
                restDataService.getPatientsForSearch(exchange);
            }
            else if(httpMethod.equals("POST") && operationName.equals("insertVisitTaskListDetails")){
                restDataService.insertVisitTaskDetailsForVisit(exchange);
            }
            else if(httpMethod.equals("PUT") && operationName.equals("updateVisitTaskListDetails")){
                restDataService.updateVisitTaskDetailsForVisit(exchange);
            }
            else if(httpMethod.equals("DELETE") && operationName.equals("deleteVisitTasks")){
                restDataService.deleteVisitTasks(exchange);
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
        } finally {
            exchange.getIn().removeHeader("Content-Length");
        }
    }
}
