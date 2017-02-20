package com.sandata.lab.rest.task.services;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.rest.task.app.AppContext;
import com.sandata.lab.rest.task.impl.RestDataService;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;

/**
 * Date: 9/2/15
 * Time: 3:03 AM
 */

public class CxfRouteHandler {

    public void handler(Exchange exchange) throws SandataRuntimeException {

        String httpMethod = (String)exchange.getIn().getHeader("CamelHttpMethod");
        String operationName = (String) exchange.getIn().getHeader("operationName");

        CamelContext context = AppContext.getContext();
        RestDataService restDataService = (RestDataService)context.getRegistry().lookupByName("restDataService");

        if (httpMethod.equals("GET") && operationName.equals("PKG_TASKS_getTaskPK_Task")) {
            restDataService.getTasks(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.equals("PKG_TASKS_getTasksForService_Task")) {
            restDataService.getTasksForService(exchange);
        }
        else if (httpMethod.equals("POST") && operationName.equals("PKG_TASKS_insertTask_TaskExt")) {
            restDataService.insertTask(exchange);
        }
        else if (httpMethod.equals("PUT") && operationName.equals("PKG_TASKS_updateTask_TaskExt")) {
            restDataService.updateTask(exchange);
        }
        else if (httpMethod.equals("DELETE") && operationName.equals("PKG_TASKS_deleteTask_Task")) {
            restDataService.deleteTask(exchange);
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
