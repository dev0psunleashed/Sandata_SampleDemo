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

package com.sandata.lab.imports.calls.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.PropertyInject;
import org.apache.camel.routepolicy.quartz.CronScheduledRoutePolicy;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.imports.calls.utils.constants.App;


/**
 * This is just an example. Delete this file.
 * <p/>
 *
 * @author Ralph Sylvain
 */

public class CallImportRoute extends AbstractRouteBuilder {

    @PropertyInject("{{import.calls.chron}}")
    private String callChron = "*/30 * * * * ?";

    @PropertyInject("{{evv.group.keys}}")
    private String groupKeys = "250"; //EVV Accounts 0056,7348 in Dev

    @Override
    protected void buildRoute() {

        CronScheduledRoutePolicy callImportRoutePolicy = new CronScheduledRoutePolicy();
        callImportRoutePolicy.setRouteStartTime(callChron);

        from("quartz://schedule?cron=" + callChron)
            .routeId(App.ID.CALLS_IMPORT_ROUTE.toString())
            .log("Starting route ...")
            .setBody(simple(groupKeys))
            .split(body(String.class).tokenize(";"))
            .beanRef("visitEventService", "getUnprocessedCalls")
            .choice()
                .when(body().isNotNull())
                    .split(body())
                    .log("Adding DNISGroup to queue containing ${body.visitEvents.size} calls for DNIS: ${body.dnis}")
                    .to(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.PROCESS_CALLS.toString())
                    .beanRef("visitEventService", "storeCallsPostVisitEvent")
                .endChoice()
            .log("Completed processing of a group");
    }

}
