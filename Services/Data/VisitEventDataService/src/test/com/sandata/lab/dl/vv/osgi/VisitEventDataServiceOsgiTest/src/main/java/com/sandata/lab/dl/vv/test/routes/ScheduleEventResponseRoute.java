package com.sandata.lab.dl.vv.test.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.data.model.constants.visit.Visit;

/**
 * Date: 11/12/15
 * Time: 2:24 PM
 */

public class ScheduleEventResponseRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + Visit.EVENT.SCHEDULE_EVENT_RESPONSE.toString())
                .routeId(Visit.EVENT.SCHEDULE_EVENT_RESPONSE.toString())
                .beanRef("visitEventRepositoryTest", "testHandleSchedEvntResponse")
                .log("testHandleSchedEvntResponse: *** Complete ***");
    }
}
