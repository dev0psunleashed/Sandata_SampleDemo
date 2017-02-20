package com.sandata.lab.dl.vv.test.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.data.model.constants.visit.Visit;
import com.sandata.lab.dl.vv.test.utils.constants.App;

/**
 * Date: 11/16/15
 * Time: 4:34 PM
 */

public class TestPatientStaffRequestRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_INTERVAL + App.ID.TEST_PATIENT_STAFF_ROUTE.toString() +
                "?fixedRate=true&period=60000")
                .routeId(App.ID.TEST_PATIENT_STAFF_ROUTE.toString())
                .beanRef("visitEventRepositoryTest", "testPatientStaffEqualsRequest")
                .to(Messaging.Names.COMPONENT_TYPE_QUEUE + Visit.EVENT.PATIENT_STAFF_REQUEST.toString())
                .log("TestPatientStaffRequestRoute: testPatientStaffEqualsRequest: *** Complete ***");
    }
}
