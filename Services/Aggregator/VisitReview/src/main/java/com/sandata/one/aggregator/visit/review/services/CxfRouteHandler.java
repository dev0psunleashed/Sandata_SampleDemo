package com.sandata.one.aggregator.visit.review.services;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.one.aggregator.visit.review.app.AppContext;
import com.sandata.one.aggregator.visit.review.impl.RestDataService;
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

            if (httpMethod.equals("GET") && operationName.equals("PKG_VISIT_reviewVisit_Response")) {
                restDataService.reviewVisit(exchange);

            } else if (httpMethod.equals("GET") && operationName.equals("PKG_AUDIT_getVisitHistory_Response")) {
                restDataService.getVisitHistory(exchange);

            } else if (httpMethod.equals("GET") && operationName.equals("PKG_VISIT_getVisitPatientDetails_Response")) {
                restDataService.getVisitPatientDetails(exchange);

            } else if (httpMethod.equals("GET") && operationName.equals("PKG_VISIT_getVisitNotes_Response")) {
                restDataService.getVisitNotes(exchange);
            } else if (httpMethod.equals("GET") && operationName.equals("PKG_VISIT_getVisitExcprReview_VisitException")) {
                restDataService.getVisitExcpForReview(exchange);

            } else if (httpMethod.equals("GET") && operationName.equals("PKG_VISIT_getVisitTasks_Response")) {
                restDataService.getVisitTasks(exchange);
            } else if (httpMethod.equals("GET") && operationName.equals("PKG_VISIT_getStaffDetail_VisitReview")) {
                restDataService.getStaffDetail(exchange);
            } else if (httpMethod.equals("GET") && operationName.equals("PKG_VISIT_getCallLogs_Response")) {
                restDataService.getCallLogs(exchange);
            }

            else if (httpMethod.equals("GET") && operationName.equals("PKG_VISIT_getVisitDetails_Response")) {
                restDataService.getVisitDetail(exchange);
            }


        } finally {
            exchange.getIn().removeHeader("Content-Length");
        }
    }
}
