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
import org.apache.camel.LoggingLevel;
import org.apache.camel.PropertyInject;

/**
 * 
 * @author thanhxle
 *
 */
public class EVVCallTransformRoute extends AbstractRouteBuilder {

    @PropertyInject("{{exchange.exception.redelivery.delay}}")
    private int redeliveryDelay = 10000;

    @PropertyInject("{{exchange.exception.maximum.redeliveries}}")
    private int maximumRedeliveries = 1;

    @Override
    protected void buildRoute() {
        
        /*
         * Seperated to another route for dummmy call creator send json request
         * from dummy service - for dummy only. This route also receive dummy
         * call from an external rest-dummy-call-creator.
         */
        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.EVV_CALLS_TRANSFORM_ROUTE.toString())
                .routeId(App.ID.EVV_CALLS_TRANSFORM_ROUTE.toString())
                .onException(Exception.class)
                .log(LoggingLevel.ERROR, LoggingUtils.getErrorLogMessageForRoute(LoggingUtils.SUB_APP_VV_DATA_SERVICE
                        , "[${routeId}] There is unexpected exception: message: ${exception.message}, stacktrace: ${exception.stacktrace} , whole exception ${exception}"))
                .logExhausted(false)
                .redeliveryDelay(redeliveryDelay)
                .maximumRedeliveries(maximumRedeliveries)
                .end()
                .log(LoggingUtils.getLogMessageForRoute(LoggingUtils.SUB_APP_CALL_IMPORT_KEYWORD,"Starting call import route"))
                .choice()
                    .when(body().isNotNull())
                        .beanRef("visitEventService", "transformCallsToVisitEventGroups")
                        .split(body())
                        .log(LoggingUtils.getLogMessageForRoute(LoggingUtils.SUB_APP_CALL_IMPORT_KEYWORD,"Adding DNISGroup to queue containing ${body.visitEvents.size} calls for DNIS: ${body.dnis}"))
                        .wireTap(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.CALL_MATCHING_PROCESS_VISIT_EVENT_GROUPS.toString())
                        // store calls to cache
                        .beanRef("visitEventService", "storeCallsPostVisitEvent")
                        .end()
                .endChoice()
                .log(LoggingUtils.getLogMessageForRoute(LoggingUtils.SUB_APP_CALL_IMPORT_KEYWORD,"Completing getUnprocessedCalls"));
    }

}
