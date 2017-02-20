package com.sandata.lab.rules.data.service.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rules.vv.log.utils.App;

/**
 * <p></p>
 *
 * @author jasonscott
 */
public class RestfulServiceRouter extends AbstractRouteBuilder {
    @Override
    protected void buildRoute() {
        from(Messaging.Names.CXFRS + "rsServer?bindingStyle=SimpleConsumer")
            .routeId(App.ID.REST_VV_DATA_SERVICE_ROUTE_ENDPOINT.toString())
            .threads().executorServiceRef("sandataVvDataServiceThreadPool")
            .removeHeader("Content-Length")
            .recipientList(simple(Messaging.Names.COMPONENT_TYPE_QUEUE + "${header.operationName}"))
            .beanRef("formatTransformer", "toResponse");
    }
}
