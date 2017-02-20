package com.sandata.lab.rules.data.service.routes;

import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rules.vv.log.utils.App;
import org.apache.camel.LoggingLevel;
import org.apache.camel.model.RouteDefinition;

/**
 * <p>Route for delete all visit excp by visit sk.</p>
 *
 * @author thanhxle
 */
public class ClearVisitExcpByVisitSkRoute extends AbstractRoute {

    @Override
    protected String getStartEndpoint() {
        return Messaging.Names.COMPONENT_TYPE_QUEUE
                + App.ID.CLEAR_VISIT_EXCP_FOR_VISIT.toString();
    }

    @Override
    protected String getRouteId() {
        return App.ID.CLEAR_VISIT_EXCP_FOR_VISIT.toString();
    }

    //REQUEST COMES FROM UI , REST API , VISIT SERVICE , when accepting call from UI, also send request to clear visit excp
    @Override
    protected void addMoreRouteDefinition(RouteDefinition definition) {

        definition
        .threads().executorServiceRef("sandataVvDataServiceThreadPool")
        .log(LoggingLevel.INFO, "Prepare to delete all visit excp by visit sk.")
         //TODO: see activemq:queue:CEP_CLEAR_VISIT_EXCEPTIONS_REQUEST
        //.beanRef("visitVerificationDataService", "clearExcefptionsByVisitSk")
        .log(LoggingLevel.INFO, "Exceptions Cleared: ${body}");
    }
}
