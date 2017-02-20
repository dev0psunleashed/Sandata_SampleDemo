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
import com.sandata.lab.rules.vv.imports.services.process.EVVCallValidator;
import com.sandata.lab.rules.vv.log.utils.App;
import com.sandata.lab.rules.vv.log.utils.LoggingUtils;
import org.apache.camel.ExchangePattern;


/**
 * This route triggered by cron and pull the calls by group key fom EVV database, then put result to queue for further processing.
 *
 * @author Thanhxle
 */

public class EVVCallImportRestRoute extends AbstractRouteBuilder {

    private final static String EVV_CALLS_IMPORT_REST_ROUTE_ID = "EVV_CALLS_IMPORT_REST_ROUTE";
    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_SEDA.toString() + EVV_CALLS_IMPORT_REST_ROUTE_ID)
                .routeId(EVV_CALLS_IMPORT_REST_ROUTE_ID)
                .setExchangePattern(ExchangePattern.InOnly)
                .log(LoggingUtils.getErrorLogMessageForRoute(LoggingUtils.SUB_APP_CALL_IMPORT_KEYWORD,"Raw JSON request ${body}"))
                 //validate required fields , and remove not valid call
                .bean(EVVCallValidator.class,"validateAndFilter")
                .choice()
                    .when(body().isNotNull())
                        .to(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.EVV_CALLS_TRANSFORM_ROUTE.toString())
                        .setBody(constant("Sent message to process match"))
                    .otherwise()
                        .setBody(constant("Invalid data,please check"))
                .end()
                .log("End at ${date:now:MM/dd/yyyy HH:mm:ss.SSS}");
        
    }

}
