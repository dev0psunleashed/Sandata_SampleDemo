package com.sandata.lab.dl.vv.test.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.data.model.constants.visit.Visit;
import com.sandata.lab.dl.vv.test.utils.constants.App;

public class TestCreateVisitRequestRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {
        from(Messaging.Names.COMPONENT_TYPE_INTERVAL + App.ID.TEST_CREATE_VISIT_REQUEST_ROUTE.toString() +
                "?fixedRate=true&period=30000")
                .routeId(App.ID.TEST_CREATE_VISIT_REQUEST_ROUTE.toString())
                .beanRef("visitEventRepositoryTest", "testCreateVisitRequest")
                .to(Messaging.Names.COMPONENT_TYPE_QUEUE + Visit.EVENT.CREATE_VISIT_REQUEST.toString())
                .log("TestScheduleEventRequestRoute: testCreateVisitRequest: *** Complete ***");
    }
}
