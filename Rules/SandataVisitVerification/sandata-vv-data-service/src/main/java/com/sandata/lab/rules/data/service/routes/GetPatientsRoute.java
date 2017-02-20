package com.sandata.lab.rules.data.service.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rules.vv.log.utils.App;
import org.apache.camel.LoggingLevel;
import org.apache.camel.PropertyInject;
import org.apache.camel.model.RouteDefinition;

/**
 * <p>Route for retrieving Staffs entities.</p>
 *
 * @author jasonscott
 */
public class GetPatientsRoute extends AbstractRoute {

    @Override
    protected String getStartEndpoint() {
        return Messaging.Names.COMPONENT_TYPE_QUEUE
                + App.ID.GET_PATIENTS_ROUTE.toString();
    }

    @Override
    protected String getRouteId() {
        return App.ID.GET_PATIENTS_ROUTE.toString();
    }

    @Override
    protected void addMoreRouteDefinition(RouteDefinition definition) {

        definition
        .threads().executorServiceRef("sandataVvDataServiceThreadPool")
        .log(LoggingLevel.INFO, "Querying Patient Entities.")
        .beanRef("visitVerificationDataService", "getPatientEntities")
        .log(LoggingLevel.INFO, "Patient list from database: ${body}");
    }
}