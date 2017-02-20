package com.sandata.lab.rest.oracle.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.data.transformer.FormatTransformer;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rest.oracle.utils.constants.App;

/**
 * Date: 9/1/15
 * Time: 10:38 PM
 */

public class RestInsertRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        // TODO: Need to figure out why the UCP does not work if I remove one route from here.... WHICH IS NOT EVEN USED!!!????!!

        // INSERT
        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_PATIENT_INSERT.toString())
                .routeId(App.ID.REST_PATIENT_INSERT.toString())
                .beanRef("restDataService", "insert")
                .bean(FormatTransformer.class, "toResponse");

        /*
        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_PATIENT_CONTACT_DETAILS_INSERT.toString())
                .routeId(App.ID.REST_PATIENT_CONTACT_DETAILS_INSERT.toString())
                .beanRef("restDataService", "insert")
                .bean(FormatTransformer.class, "toResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_PATIENT_DME_SUPPLIES_INSERT.toString())
                .routeId(App.ID.REST_PATIENT_DME_SUPPLIES_INSERT.toString())
                .beanRef("restDataService", "insert")
                .bean(FormatTransformer.class, "toResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_PATIENT_MEDICATIONS_INSERT.toString())
                .routeId(App.ID.REST_PATIENT_MEDICATIONS_INSERT.toString())
                .beanRef("restDataService", "insert")
                .bean(FormatTransformer.class, "toResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.REST_PATIENT_ALLERGIES_INSERT.toString())
                .routeId(App.ID.REST_PATIENT_ALLERGIES_INSERT.toString())
                .beanRef("restDataService", "insert")
                .bean(FormatTransformer.class, "toResponse");
                */
    }
}
