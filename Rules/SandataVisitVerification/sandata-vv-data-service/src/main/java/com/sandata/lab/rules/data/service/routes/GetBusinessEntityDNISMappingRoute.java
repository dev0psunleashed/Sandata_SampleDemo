package com.sandata.lab.rules.data.service.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rules.vv.log.utils.App;
import org.apache.camel.LoggingLevel;
import org.apache.camel.PropertyInject;
import org.apache.camel.model.RouteDefinition;

/**
 * <p>Route for retrieving Business Entity ID to DNIS mapping.</p>
 *
 * @author jasonscott
 */
public class GetBusinessEntityDNISMappingRoute extends AbstractRoute {

    @Override
    protected String getStartEndpoint() {
        return Messaging.Names.COMPONENT_TYPE_QUEUE.toString()
                + App.ID.GET_BUSINESS_ENTITY_DNIS_MAPPING_ROUTE.toString();
    }

    @Override
    protected String getRouteId() {
        return App.ID.GET_BUSINESS_ENTITY_DNIS_MAPPING_ROUTE.toString();
    }

    @Override
    protected void addMoreRouteDefinition(RouteDefinition definition) {
        definition
        .threads().executorServiceRef("sandataVvDataServiceThreadPool")
        .log(LoggingLevel.INFO, "Querying Business Entity ID to DNIS values from metadata.")
        .beanRef("visitVerificationDataService", "getBsnEntIdDnisMapping");
        //.log(LoggingLevel.DEBUG, "${body}");
    }
}
