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

package com.sandata.lab.intake.staff.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.data.transformer.FormatTransformer;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.intake.staff.utils.constants.App;

/**
 * Route intercepts cxf insert staff resource url.
 * <p/>
 *
 * @author David Rutgos
 */
public class InsertStaffRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.INSERT_STAFF_MEMBER.toString())
                .routeId(App.ID.INSERT_STAFF_MEMBER.toString())
                .beanRef("restStaffIntakeService", "createUUID")
                .wireTap(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.INSERT_STAFF_MEMBER.toString() + "_ExecuteInsert")
                .setBody(simple("${in.header.STAFF_GUID}"))
                .bean(FormatTransformer.class, "toResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.INSERT_STAFF_MEMBERS.toString())
                .routeId(App.ID.INSERT_STAFF_MEMBERS.toString())
                .beanRef("restStaffIntakeService", "createUUID")
                .wireTap(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.INSERT_STAFF_MEMBERS.toString() + "_ExecuteInsert")
                .setBody(simple("${in.header.STAFF_GUID}"))
                .bean(FormatTransformer.class, "toResponse");

        /**
         * EXECUTE the actual insert on another route
         */

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.INSERT_STAFF_MEMBER.toString() + "_ExecuteInsert")
                .routeId(App.ID.INSERT_STAFF_MEMBER.toString() + "_ExecuteInsert")
                .beanRef("restStaffIntakeService", "insertStaffMember")
                .log("[${in.header.STAFF_GUID}]: InsertStaffMember: COMPLETE!");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.INSERT_STAFF_MEMBERS.toString() + "_ExecuteInsert")
                .routeId(App.ID.INSERT_STAFF_MEMBERS.toString() + "_ExecuteInsert")
                .beanRef("restStaffIntakeService", "insertStaffMembers")
                .log("[${in.header.STAFF_GUID}]: InsertStaffMembers: COMPLETE!");

    }
}
