package com.sandata.lab.dl.vv.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.data.model.constants.visit.Visit;

/**
 * Date: 11/16/15
 * Time: 3:49 PM
 */

public class PatientStaffRequestRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + Visit.EVENT.PATIENT_STAFF_REQUEST.toString())
                .routeId(Visit.EVENT.PATIENT_STAFF_REQUEST.toString())
                .beanRef("visitEventRepository", "getPatientStaffRequest")
                .to(Messaging.Names.COMPONENT_TYPE_QUEUE + Visit.EVENT.PATIENT_STAFF_RESPONSE.toString())
                .log("getPatientStaffRequest: Complete!");
    }
}
