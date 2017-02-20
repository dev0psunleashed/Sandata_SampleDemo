package com.sandata.lab.rules.data.service.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.model.RouteDefinition;

import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rules.data.service.impl.VisitVerificationExceptionDataService;
import com.sandata.lab.rules.vv.log.utils.App;

/**
 * <p>Route for retrieving schedule event by sk.</p>
 *
 * @author thanhxle
 */
public class GetVisitInfoBySkRoute extends AbstractRoute {

    @Override
    protected String getStartEndpoint() {
        return Messaging.Names.COMPONENT_TYPE_QUEUE
                + App.ID.GET_VISIT_INFO_BY_SK_ROUTE.toString();
    }

    @Override
    protected String getRouteId() {
        return App.ID.GET_VISIT_INFO_BY_SK_ROUTE.toString();
    }

    @Override
    protected void addMoreRouteDefinition(RouteDefinition definition) {

        definition
        .threads().executorServiceRef("sandataVvDataServiceThreadPool")
        .log(LoggingLevel.INFO, "Querying visit by sk= ${body}.")
        .beanRef("visitVerificationExceptionDataService", "getVisitBySk")
        .log(LoggingLevel.INFO, "Visit by sk result: ${body}");
    }
}
