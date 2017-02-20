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

#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.routes;

import com.sandata.up.commons.camel.AbstractRouteBuilder;
import ${package}.utils.constants.App;
import ${package}.utils.constants.Messaging;

/**
 * Route that intercepts cxfrs calls and dispatches them to their respective camel endpoints.
 * <p/>
 *
 * @author David Rutgos
 */
public class RestfulServiceRouter extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.CXFRS + "rsServer?bindingStyle=SimpleConsumer")
                .routeId(App.ID.REST_ROUTE_ENDPOINT.toString())
                .recipientList(simple(Messaging.Names.COMPONENT_TYPE_QUEUE + "${header.operationName}?concurrentConsumers={{rest.concurrentConsumers}}"))
                .removeHeader("Content-Length");
    }
}
