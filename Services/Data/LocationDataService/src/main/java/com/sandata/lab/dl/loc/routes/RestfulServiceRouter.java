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

package com.sandata.lab.dl.loc.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;

public class RestfulServiceRouter extends AbstractRouteBuilder {

    @Override
    protected final void buildRoute() {
        from("cxfrs://bean://rsServer?bindingStyle=SimpleConsumer")
                .routeId("LOCATION_DATA_RESTFUL_SERVICES")
                .recipientList(simple(Messaging.Names.COMPONENT_TYPE_SEDA + "${header.operationName}?concurrentConsumers={{concurrent.consumers}}&timeout={{seda.timeout}}"))
                .removeHeader("Content-Length");
    }
}
