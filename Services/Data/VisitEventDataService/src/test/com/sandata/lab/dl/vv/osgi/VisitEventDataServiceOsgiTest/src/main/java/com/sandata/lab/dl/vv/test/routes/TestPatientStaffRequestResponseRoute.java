package com.sandata.lab.dl.vv.test.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.data.model.constants.visit.Visit;
import com.sandata.lab.dl.vv.test.utils.constants.App;

public class TestPatientStaffRequestResponseRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {
        from(Messaging.Names.COMPONENT_TYPE_QUEUE + Visit.EVENT.PATIENT_STAFF_RESPONSE.toString())
                .routeId(Visit.EVENT.PATIENT_STAFF_RESPONSE.toString())
                .beanRef("visitEventRepositoryTest", "testHandlePatientStaffResponse")
                .log("TestPatientStaffRequestResponseRoute: testHandlePatientStaffResponse: *** Complete ***");
    }
}
