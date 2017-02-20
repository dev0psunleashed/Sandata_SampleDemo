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

package com.sandata.lab.intake.schedule.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.data.transformer.FormatTransformer;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.intake.schedule.utils.constants.App;

/**
 * Route intercepts cxf insert schedule resource url.
 * <p/>
 *
 * @author David Rutgos
 */
public class InsertScheduleRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.INSERT_SCHEDULES.toString())
                .routeId(App.ID.INSERT_SCHEDULES.toString())
                .beanRef("restScheduleIntakeService", "createUUID")
                .wireTap(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.INSERT_SCHEDULES.toString() + "_ExecuteInsert")
                .setBody(simple("${in.header.SCHEDULE_GUID}"))
                .bean(FormatTransformer.class, "toResponse");

        /**
         * EXECUTE the actual insert on another route
         */

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.INSERT_SCHEDULES.toString() + "_ExecuteInsert")
                .routeId(App.ID.INSERT_SCHEDULES.toString() + "_ExecuteInsert")
                .beanRef("restScheduleIntakeService", "insertSchedules")
                .log("[${in.header.SCHEDULE_GUID}]: InsertSchedules: COMPLETE!");

    }
}
