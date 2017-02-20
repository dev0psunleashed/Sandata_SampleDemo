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

package com.sandata.lab.security.auth.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.security.auth.utils.constants.App;

import static org.apache.camel.builder.ProcessorBuilder.removeHeader;

public class RestfulServiceRouter extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.CXFRS + "rsServer?bindingStyle=SimpleConsumer&continuationTimeout=0")
                .routeId(App.ID.CXF_ROUTE_RECIPIENT_LIST.toString())
                .threads().executorServiceRef("sandataAuthThreadPool")
                .removeHeader("Content-Length")
                .recipientList(simple("seda:" + "${header.operationName}"));


    }
}
