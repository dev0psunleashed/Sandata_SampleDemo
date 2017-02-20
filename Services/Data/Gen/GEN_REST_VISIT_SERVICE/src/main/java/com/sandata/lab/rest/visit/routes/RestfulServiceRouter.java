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

package com.sandata.lab.rest.visit.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.data.model.constants.billing.Billing;
import com.sandata.lab.data.model.constants.payroll.Payroll;
import com.sandata.lab.rest.visit.utils.constants.App;

@SuppressWarnings("unchecked")
public class RestfulServiceRouter extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.CXFRS + "rsServer?bindingStyle=SimpleConsumer")
                .routeId(App.ID.REST_ROUTE_ENDPOINT.toString())
                .threads().executorServiceRef("visitRestThreadPool")
                .beanRef("cxfRouteHandler", "handler")
                .wireTap(Messaging.Names.COMPONENT_TYPE_QUEUE + Payroll.EVENT.PAYROLL_PROCESS_VISITS.toString())
                .wireTap(Messaging.Names.COMPONENT_TYPE_QUEUE + Billing.EVENT.BILLING_PROCESS_VISITS.toString())
                .removeHeader("Content-Length")
                .beanRef("formatTransformer", "toResponse");
    }
}
