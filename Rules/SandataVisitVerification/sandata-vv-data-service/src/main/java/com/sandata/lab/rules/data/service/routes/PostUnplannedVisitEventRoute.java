package com.sandata.lab.rules.data.service.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rules.data.service.utils.RouteUtils;
import com.sandata.lab.rules.vv.log.utils.App;
import org.apache.camel.LoggingLevel;
import org.apache.camel.PropertyInject;
import org.apache.camel.model.RouteDefinition;

/**
 * <p>Route for inserting planned VisitEvents.</p>
 *
 * @author jasonscott
 */
public class PostUnplannedVisitEventRoute extends AbstractRoute {

    @Override
    protected String getStartEndpoint() {
        return Messaging.Names.COMPONENT_TYPE_QUEUE
                + App.ID.POST_UNPLANNED_VISIT_EVENT_ROUTE.toString();
    }

    @Override
    protected String getRouteId() {
        return App.ID.POST_UNPLANNED_VISIT_EVENT_ROUTE.toString();
    }

    @Override
    protected void addMoreRouteDefinition(RouteDefinition definition) {

        definition
        .threads().executorServiceRef("sandataVvDataServiceThreadPool")
        .log(LoggingLevel.INFO, "Inserting unplanned VisitEvent entity.")
        .beanRef("visitVerificationDataService", "insertUnplannedVisitEvent")
        // send unplanned visit to visit exception handling and Auth
        .log("Inserted visit info to database ${body}")
        .multicast()
        .parallelProcessing()
        .to(RouteUtils.getVisitAuthEndpoint(), RouteUtils.getVisitExcpCalAggregateEndpoint(),
                RouteUtils.getGpsExcpEndpoint(), RouteUtils.getProcessedCallsEndpoint());
    }
}
