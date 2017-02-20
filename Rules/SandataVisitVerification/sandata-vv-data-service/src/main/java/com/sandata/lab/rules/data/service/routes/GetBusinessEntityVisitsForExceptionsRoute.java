package com.sandata.lab.rules.data.service.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rules.vv.log.utils.App;
import org.apache.camel.LoggingLevel;
import org.apache.camel.PropertyInject;
import org.apache.camel.model.RouteDefinition;

/**
 * <p>Route for retrieving business entity visits for exceptions service.</p>
 *
 * @author jasonscott
 */
public class GetBusinessEntityVisitsForExceptionsRoute extends AbstractRoute {

    @Override
    protected String getStartEndpoint() {
        return Messaging.Names.COMPONENT_TYPE_QUEUE
                + App.ID.GET_BUSINESS_ENTITY_VISITS_FOR_EXCEPTIONS_ROUTE.toString();
    }

    @Override
    protected String getRouteId() {
        return App.ID.GET_BUSINESS_ENTITY_VISITS_FOR_EXCEPTIONS_ROUTE.toString();
    }

    @Override
    protected void addMoreRouteDefinition(RouteDefinition definition) {
        definition
        .threads().executorServiceRef("sandataVvDataServiceThreadPool")
        .log(LoggingLevel.INFO, "Querying visits for exceptions service for business entity ID ${header.bsnEntId} from COREDATA.")
        .beanRef("visitVerificationDataService", "getBusinessEntityVisitsForExceptions")
        .log(LoggingLevel.INFO, "${body}");
    }
}
