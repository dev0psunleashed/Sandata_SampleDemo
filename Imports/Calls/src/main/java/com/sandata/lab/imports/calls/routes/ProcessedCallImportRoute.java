package com.sandata.lab.imports.calls.routes;

import com.sandata.lab.imports.calls.utils.constants.App;
import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import org.apache.camel.routepolicy.quartz.CronScheduledRoutePolicy;

public class ProcessedCallImportRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.PROCESSED_CALLS.toString())
                .routeId(App.ID.PROCESSED_CALLS_IMPORT_ROUTE.toString())
                .log("Starting route ...")
                .beanRef("visitEventService", "processedVisitEvent")
                .log("Completed processing of a group");
    }
}
