package com.sandata.lab.billing.edi.routes.system;

import com.sandata.lab.billing.edi.utils.log.LoggingUtils;
import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;

/**
 * Routes to process 837I, 837P and 835 files
 * 
 */
public class ProcessEdiFileDummyRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {
        //TODO: build routes here
        from("timer:dummyTimer?repeatCount=1")
            .routeId(this.getClass().getSimpleName())
            .log(LoggingUtils.getLogMessageInfo(this, "this is a dummy route to test"));
    }
}
