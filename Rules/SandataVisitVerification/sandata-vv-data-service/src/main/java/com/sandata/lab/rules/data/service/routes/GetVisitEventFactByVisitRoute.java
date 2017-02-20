package com.sandata.lab.rules.data.service.routes;

import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rules.vv.log.utils.App;
import org.apache.camel.LoggingLevel;
import org.apache.camel.model.RouteDefinition;

/**
 * <p>Route for retrieving schedule event by sk.</p>
 *
 * @author thanhxle
 */
public class GetVisitEventFactByVisitRoute extends AbstractRoute {

    @Override
    protected String getStartEndpoint() {
        return Messaging.Names.COMPONENT_TYPE_QUEUE
                + App.ID.GET_VISIT_EVENT_FACT_BY_VISIT_ROUTE.toString();
    }

    @Override
    protected String getRouteId() {
        return App.ID.GET_VISIT_EVENT_FACT_BY_VISIT_ROUTE.toString();
    }

    @Override
    protected void addMoreRouteDefinition(RouteDefinition definition) {

        definition
        .threads().executorServiceRef("sandataVvDataServiceThreadPool")
        .log(LoggingLevel.INFO, "Querying visit event by visit.")
        .bean("visitVerificationExceptionDataService", "getVisitEventFactByVisit")
        .log(LoggingLevel.INFO, "Visit event by visit result: ${body}");
    }
}
