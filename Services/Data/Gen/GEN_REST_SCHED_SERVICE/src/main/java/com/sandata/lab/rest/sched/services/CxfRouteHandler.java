package com.sandata.lab.rest.sched.services;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.rest.sched.app.AppContext;
import com.sandata.lab.rest.sched.impl.RestDataService;
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
    
            // GEOR-6219: handle all Schedule/Schedule_event processes
            if (httpMethod.equals("POST") && operationName.contains("_insertSchedFullProcess_")) {
                restDataService.createScheduleWithFullProcess(exchange);
            } else if (httpMethod.equals("PUT") && operationName.contains("_updateSchedFullProcess_")) {
                restDataService.updateScheduleWithFullProcess(exchange);
            } else if (httpMethod.equals("PUT") && operationName.contains("_updateStaffIdForSchedule")) {
                restDataService.updateStaffIdForSchedule(exchange);
            } else if (httpMethod.equals("PUT") && operationName.contains("_updateSchedWithDateRange")) {
                restDataService.updateScheduleWithDateRange(exchange);
            }
            else if (httpMethod.equals("POST") && operationName.contains("_saveSchedEvents")) {
                restDataService.saveSchedEvents(exchange);
            }
            else if (httpMethod.equals("POST") && operationName.equals("PKG_SCHEDULE_validateSchedule_ScheduleExt")) {
                restDataService.validateSchedule(exchange);
            }
            else if (httpMethod.equals("POST") && operationName.equals("PKG_SCHEDULE_validateScheduleEvent_ScheduleEvntExt")) {
                restDataService.validateScheduleEvent(exchange);
            }
            else if (httpMethod.equals("GET") && operationName.contains("_deleteSchedule")) {
                restDataService.deleteSchedule(exchange);
            }
            // Handle Find
            else if (httpMethod.equals("GET") && operationName.contains("_findStaff")) {
                restDataService.findStaff(exchange);
            }
            else if (httpMethod.equals("GET") && operationName.contains("_findSchedule")) {
                restDataService.findSchedule(exchange);
            }
            else if (httpMethod.equals("GET") && operationName.contains("_getSchedPtcExcl_")) {
                restDataService.getSchdlPtcExcl(operationName, exchange);
            }
            else if (httpMethod.equals("GET") && operationName.equals("PKG_SCHEDULE_getSchedEvntHistory_ScheduleEvent")) {
                restDataService.getSchedEvntHistory(exchange);
            }
            else if (httpMethod.equals("GET") && operationName.equals("PKG_SCHEDULE_getSchedEvntHistoryDetail_Response")) {
                restDataService.getSchedEvntHistoryDetail(exchange);
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
