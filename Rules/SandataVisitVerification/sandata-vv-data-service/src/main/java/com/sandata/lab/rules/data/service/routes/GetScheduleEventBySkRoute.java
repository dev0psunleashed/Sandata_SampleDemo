package com.sandata.lab.rules.data.service.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rules.vv.log.utils.App;
import org.apache.camel.LoggingLevel;
import org.apache.camel.PropertyInject;
import org.apache.camel.model.RouteDefinition;

/**
 * <p>Route for retrieving schedule event by sk.</p>
 *
 * @author thanhxle
 */
public class GetScheduleEventBySkRoute extends AbstractRoute {

    @Override
    protected String getStartEndpoint() {
        return Messaging.Names.COMPONENT_TYPE_QUEUE
                + App.ID.GET_SCHED_EVENT_BY_SK_ROUTE.toString();
    }

    @Override
    protected String getRouteId() {
        return App.ID.GET_SCHED_EVENT_BY_SK_ROUTE.toString();
    }

    @Override
    protected void addMoreRouteDefinition(RouteDefinition definition) {

        definition
        .threads().executorServiceRef("sandataVvDataServiceThreadPool")
        .log(LoggingLevel.INFO, "Querying schedule event by sk.")
        .beanRef("visitVerificationDataService", "getScheduleEventBySk")
        .log(LoggingLevel.INFO, "Schedule event by sk result: ${body}");
    }
}
