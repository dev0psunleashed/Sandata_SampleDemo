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

package com.sandata.lab.logger.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;


public class RestfulServiceRouter extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        //Removed REST Endpoints from Logger since logger is used in export/import services as well and will have a port 9090 conflict
        /*from(Messaging.Names.CXFRS + "rsServer?bindingStyle=SimpleConsumer")
                .routeId(App.ID.REST_ROUTE_ENDPOINT.toString())
                .threads().executorServiceRef("loggerProcessThreadPool")
                .beanRef("cxfRouteHandler", "handler")
                .removeHeader("Content-Length")
                .beanRef("formatTransformer", "toResponse");*/
    }
}
