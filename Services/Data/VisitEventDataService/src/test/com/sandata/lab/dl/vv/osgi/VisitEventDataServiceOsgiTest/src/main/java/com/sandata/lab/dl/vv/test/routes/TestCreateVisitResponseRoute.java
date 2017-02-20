package com.sandata.lab.dl.vv.test.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.data.model.constants.visit.Visit;

/**
 * Route to test the response after creating a Visit.
 * <p/>
 *
 * @author David Rutgos
 */
public class TestCreateVisitResponseRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {
        from(Messaging.Names.COMPONENT_TYPE_QUEUE + Visit.EVENT.CREATE_VISIT_RESPONSE.toString())
                .routeId(Visit.EVENT.CREATE_VISIT_RESPONSE.toString())
                .beanRef("visitEventRepositoryTest", "testHandleCreateVisitResponse")
                .log("TestCreateVisitResponseRoute: testHandleCreateVisitResponse: *** Complete ***");
    }
}
