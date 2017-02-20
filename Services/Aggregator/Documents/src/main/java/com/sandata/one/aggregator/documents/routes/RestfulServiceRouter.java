/*
 * Copyright (c) 2016. Sandata Technologies, LLC
 * 26 Harbor Park Drive, Port Washington, NY 11050, 800-544-7263
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Sandata Technologies, LLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with
 * Sandata.
 */

package com.sandata.one.aggregator.documents.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.one.aggregator.documents.utils.constants.App;

public class RestfulServiceRouter extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.CXFRS + "rsServer?bindingStyle=SimpleConsumer&continuationTimeout=0")
                .routeId(App.ID.REST_ROUTE_ENDPOINT.toString())
                .threads().executorServiceRef("aggregatorDocumentRestThreadPool")
                .bean("cxfRouteHandler", "handler")
                .choice()
                    .when().simple("${in.header.CXF_ROUTE_HANDLED} == true")
                        .bean("formatTransformer", "toResponse")
                        .log("RestfulServiceRouter: " + App.ID.CXF_ROUTE_HANDLER.toString() + ": Handled!")
                        .removeHeader("Content-Length")
                    .otherwise()
                        .to("direct:" + App.ID.CXF_ROUTE_RECIPIENT_LIST.toString())
                .end();

        from("direct:" + App.ID.CXF_ROUTE_RECIPIENT_LIST.toString())
                .routeId(App.ID.CXF_ROUTE_RECIPIENT_LIST.toString())
                .recipientList(simple("direct:" + "${header.operationName}"))
                .log("RestfulServiceRouter: " + App.ID.CXF_ROUTE_RECIPIENT_LIST.toString() + ": Handled!");
    }
}
