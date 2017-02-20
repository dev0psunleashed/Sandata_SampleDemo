/*
 * Copyright (c) 2015. Sandata Technologies, LLC
 * 26 Harbor Park Drive, Port Washington, NY 11050, 800-544-7263
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Sandata Technologies, LLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with
 * Sandata.
 */

package com.sandata.lab.rest.oracle.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.data.transformer.FormatTransformer;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rest.oracle.services.CxfRouteHandler;
import com.sandata.lab.rest.oracle.utils.constants.App;


public class RestfulServiceRouter extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.CXFRS + "rsServer?bindingStyle=SimpleConsumer")
                .routeId(App.ID.REST_ROUTE_ENDPOINT.toString())
                .bean(CxfRouteHandler.class, "handler")
                //.recipientList(simple(Messaging.Names.COMPONENT_TYPE_QUEUE + "${header.operationName}?concurrentConsumers={{rest.concurrentConsumers}}"))
                .removeHeader("Content-Length")
                .bean(FormatTransformer.class, "toResponse");
    }
}
