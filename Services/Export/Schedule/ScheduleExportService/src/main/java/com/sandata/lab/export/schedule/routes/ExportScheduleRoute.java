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

package com.sandata.lab.export.schedule.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.data.transformer.FormatTransformer;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.export.schedule.utils.constants.App;

/**
 * Route intercepts cxf export schedule resource url.
 * <p/>
 *
 * @author David Rutgos
 */
public class ExportScheduleRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.EXPORT_SCHEDULE.toString())
                .routeId(App.ID.EXPORT_SCHEDULE.toString())
                .beanRef("restScheduleExportService", "createUUID")
                .wireTap(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.EXPORT_SCHEDULE.toString() + "_ExecuteInsert")
                .setBody(simple("${in.header.SCHEDULE_GUID}"))
                .bean(FormatTransformer.class, "toResponse");

        /**
         * EXECUTE the actual insert on another route
         */

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.EXPORT_SCHEDULE.toString() + "_ExecuteInsert")
                .routeId(App.ID.EXPORT_SCHEDULE.toString() + "_ExecuteInsert")
                .beanRef("restScheduleExportService", "exportSchedule")
                .log("[${in.header.SCHEDULE_GUID}]: ExportSchedule: COMPLETE!");
    }
}
