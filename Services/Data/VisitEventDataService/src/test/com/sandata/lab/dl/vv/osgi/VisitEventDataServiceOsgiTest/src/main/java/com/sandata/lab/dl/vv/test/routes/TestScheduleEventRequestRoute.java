package com.sandata.lab.dl.vv.test.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.data.transformer.FormatTransformer;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.data.model.constants.visit.Visit;
import com.sandata.lab.dl.vv.test.utils.constants.App;

/**
 * Date: 9/1/15
 * Time: 10:38 PM
 */

public class TestScheduleEventRequestRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_INTERVAL + App.ID.TEST_SCHEDULE_EVENT_ROUTE.toString() +
                    "?fixedRate=true&period=30000")
                .routeId(App.ID.TEST_SCHEDULE_EVENT_ROUTE.toString())
                .beanRef("visitEventRepositoryTest", "testSchedEvntRequest")
                .to(Messaging.Names.COMPONENT_TYPE_QUEUE + Visit.EVENT.SCHEDULE_EVENT_REQUEST.toString())
                .log("TestScheduleEventRequestRoute: testSchedEvntRequest: *** Complete ***");
    }
}
