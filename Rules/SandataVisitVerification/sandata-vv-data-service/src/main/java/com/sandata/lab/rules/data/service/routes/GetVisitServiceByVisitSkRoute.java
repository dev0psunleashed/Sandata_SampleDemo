package com.sandata.lab.rules.data.service.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.model.RouteDefinition;

import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rules.vv.log.utils.App;

/**
 * <p>Route for retrieving phone numbers for a patient.</p>
 *
 * @author richardwu
 */
public class GetVisitServiceByVisitSkRoute extends AbstractRoute {

    @Override
    protected String getStartEndpoint() {
        return Messaging.Names.COMPONENT_TYPE_QUEUE
                + App.ID.GET_VISIT_SERVICE_BY_VISIT_SK_ROUTE.toString();
    }

    @Override
    protected String getRouteId() {
        return App.ID.GET_VISIT_SERVICE_BY_VISIT_SK_ROUTE.toString();
    }

    @Override
    protected void addMoreRouteDefinition(RouteDefinition definition) {

        definition
        .threads().executorServiceRef("sandataVvDataServiceThreadPool")
        .log(LoggingLevel.INFO, "Querying visit service by visit SK.")
        .beanRef("visitVerificationExceptionDataService", "getVisitServiceByVisitSk")
        .log(LoggingLevel.INFO, "visit service by visit SK result: ${body}");
    }
}
