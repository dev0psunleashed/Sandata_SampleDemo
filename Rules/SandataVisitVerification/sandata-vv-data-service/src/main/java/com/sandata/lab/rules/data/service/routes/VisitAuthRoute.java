package com.sandata.lab.rules.data.service.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rules.vv.log.utils.App;
import com.sandata.lab.rules.vv.fact.VisitEventFact;
import com.sandata.lab.rules.vv.model.VisitState;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.model.RouteDefinition;

/**
 * <p>Route for inserting Visit Auth.</p>
 *
 * @author jasonscott
 */
public class VisitAuthRoute extends AbstractRoute {

    @Override
    protected String getStartEndpoint() {
        return Messaging.Names.COMPONENT_TYPE_QUEUE
                + App.ID.CREATE_VISIT_AUTH_ROUTE.toString();
    }

    @Override
    protected String getRouteId() {
        return App.ID.CREATE_VISIT_AUTH_ROUTE.toString();
    }

    @Override
    protected void addMoreRouteDefinition(RouteDefinition definition) {

        definition
        .threads().executorServiceRef("sandataVvDataServiceThreadPool")
        .log(LoggingLevel.INFO, "Processing VISIT AUTH.")
        .process(new Processor() {
            @Override
            public void process(final Exchange exchange) throws Exception {

                VisitEventFact visitEventFact = exchange.getIn().getBody(VisitEventFact.class);
                if (visitEventFact != null) {
                    //if PLANNED VISIT
                    if (visitEventFact.getVisitState() == VisitState.MATCHED && visitEventFact.getPatientID() != null) {
                        exchange.getIn().setHeader("HAS_PATIENT_ID", constant(true));

                    } else if (visitEventFact.getVisitState()  == VisitState.NOT_MATCHED
                            //FOR UNPLANNED VISIT
                            && visitEventFact.getPatientFact() != null
                            &&  visitEventFact.getPatientFact().getVisitState() == VisitState.MATCHED_PATIENT
                            && visitEventFact.getPatientFact().getPatientId() != null) {

                        exchange.getIn().setHeader("HAS_PATIENT_ID", constant(true));
                    }
                }
            }
        })
        .choice()
        .when().simple("${header.HAS_PATIENT_ID} == 'true'")
        .beanRef("visitVerificationDataService", "createVisitAuth")
        .log("VISIT_AUTH_REQUEST ROUTE COMPLETED!")
        .otherwise()
        .log("COULD NOT SEND VISIT_AUTH REQUEST because of unknown patient")
        .end();
    }
}
