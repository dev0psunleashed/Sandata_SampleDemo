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

package com.sandata.lab.rules.vv.imports.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;

import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rules.vv.log.utils.App;
import com.sandata.lab.rules.vv.log.utils.LoggingUtils;
import org.apache.camel.PropertyInject;


/**
 * This route triggered by cron and pull the calls by group key fom EVV database, then put result to queue for further processing.
 *
 * @author Thanhxle
 */

public class EVVCallImportRoute extends AbstractRouteBuilder {

    @PropertyInject("{{import.calls.chron}}")
    private String callChron = "*/30 * * * * ?";

    //EVV Accounts 0056,7348 in Dev
    @PropertyInject("{{evv.group.keys}}")
    private String groupKeys = "250"; 

    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_QUARTZ.toString() + "schedule?cron=" + callChron)
            .routeId(App.ID.CALLS_IMPORT_QUARTZ_ROUTE.toString())
            .log(LoggingUtils.getLogMessageForRoute(LoggingUtils.SUB_APP_CALL_IMPORT_KEYWORD,"Starting call import cron route"))
            .setBody(simple(groupKeys))
            .split(body(String.class).tokenize(";"))
            .beanRef("visitEventService", "getUnprocessedCalls")
            // send List<Call> to queue
            .to(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.EVV_CALLS_TRANSFORM_ROUTE.toString());
        
    }

}
