package com.sandata.lab.rules.data.service.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rules.vv.log.utils.App;
import org.apache.camel.LoggingLevel;
import org.apache.camel.model.RouteDefinition;

/**
 * <p>Route for retrieving ScheduleEvent entities.</p>
 *
 * @author jasonscott
 */
public class GetScheduleEventsRoute extends AbstractRoute {

    @Override
    protected String getStartEndpoint() {
        return Messaging.Names.COMPONENT_TYPE_QUEUE
                + App.ID.GET_SCHEDULE_EVENTS_ROUTE.toString();
    }

    @Override
    protected String getRouteId() {
        return App.ID.GET_SCHEDULE_EVENTS_ROUTE.toString();
    }

    @Override
    protected void addMoreRouteDefinition(RouteDefinition definition) {

        definition
        .threads().executorServiceRef("sandataVvDataServiceThreadPool")
        .log(LoggingLevel.INFO, "Querying ScheduleEvent Entities.")
        .beanRef("visitVerificationDataService", "getScheduleEventEntities")
        .log(LoggingLevel.INFO, "Schedule Events list from database: ${body}");

    }
}
