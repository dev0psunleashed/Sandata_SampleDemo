package com.sandata.lab.rules.dl.routes;

import com.sandata.lab.rules.dl.processors.*;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;

import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.data.model.constants.visit.Visit;

/**
 * Created by tom.dornseif on 11/22/2015.
 */
public class VisitRoute extends RouteBuilder {
    private static final String CREATE_VISIT_AUTH_REQUEST = "activemq:queue:CREATE_VISIT_AUTH_REQUEST";

    private VisitProcessorProducer visitProcessorProducer;
    private UnplannedVisitProcessorProducer unplannedVisitProcessorProducer;
    private VisitProcessorConsumerVisitEventStaffId visitProcessorConsumerVisitEventStaffId;
    private VisitProcessorConsumerVisitStaffId visitProcessorConsumerVisitStaffId;
    private PullVisitSKFromVisitProcessor pullVisitSKFromVisitsProcessor;
    private VisitAuthRequestProducer visitAuthRequestProducer;
    @Override
    public void configure() throws Exception {


        visitProcessorProducer = new VisitProcessorProducer();
        unplannedVisitProcessorProducer = new UnplannedVisitProcessorProducer();
        visitProcessorConsumerVisitEventStaffId = new VisitProcessorConsumerVisitEventStaffId();
        visitProcessorConsumerVisitStaffId = new VisitProcessorConsumerVisitStaffId();

        pullVisitSKFromVisitsProcessor = new PullVisitSKFromVisitProcessor();
        visitAuthRequestProducer = new VisitAuthRequestProducer();

        from("activemq:queue:CEP_ENGINE_INSERTED_UNPLANNED_VISIT")
                .process(unplannedVisitProcessorProducer)
                .to(Messaging.Names.COMPONENT_TYPE_QUEUE + Visit.EVENT.CREATE_VISIT_REQUEST.toString());

        from("activemq:queue:CEP_ENGINE_VISITS?destination.consumer.exclusive=true")
                .process(visitProcessorProducer)
                .to("activemq:queue:CREATE_VISIT_REQUEST");//Messaging.Names.COMPONENT_TYPE_QUEUE + Visit.EVENT.CREATE_VISIT_REQUEST.toString()



        from("activemq:queue:CREATE_VISIT_RESPONSE")
                .multicast()
                .parallelProcessing()
                .to("seda:useVisitStaff", "direct:useVisitEventStaff");

        from("direct:useVisitEventStaff")
                .process(visitProcessorConsumerVisitEventStaffId)
                .multicast()
                .parallelProcessing()
                .to("activemq:queue:CEP_VISIT_AUTH_ROUTE", "direct:sendToImportServiceForRetraction");

        from("seda:useVisitStaff")
                .process(visitProcessorConsumerVisitStaffId) /* this extracts a VisitFact from a Visit */
                .to(ExchangePattern.InOut, "seda:preClearExceptions")
                .process(new TestObjectInExchange())
                .multicast()
                .parallelProcessing()
                .to("{{activeMQ.endpoint.CEP_ENGINE_VISIT_EXCEPTION_ROUTE}}", "activemq:queue:AGGREGATE_VISIT_AND_SCHEDULE_EXCP_EVENTS");

        from("seda:preClearExceptions?exchangePattern=InOut")
                .process(pullVisitSKFromVisitsProcessor)
                .to("activemq:queue:CEP_CLEAR_VISIT_EXCEPTIONS_REQUEST?exchangePattern=InOut");





        from("direct:sendToImportServiceForRetraction")
                .process(new ProcessVisitToVisitEvent())
                .to("activemq:queue:RETRACTED_CALLS");

        from("activemq:queue:CEP_VISIT_AUTH_ROUTE").routeId("CEP_VISIT_AUTH_ROUTE")
                .log("received request for visit authorization request")
                .process(visitAuthRequestProducer)
                .choice()
                    .when().simple("${header.HAS_PATIENT_ID} == 'true'")
                        .to(CREATE_VISIT_AUTH_REQUEST)
                        .log("VISIT_AUTH_REQUEST ROUTE COMPLETED!")
                    .otherwise()
                        .log("COULD NOT SEND VISIT_AUTH REQUEST check logs for reason")
                .end();


//Messaging.Names.COMPONENT_TYPE_QUEUE + Visit.EVENT.CREATE_VISIT_RESPONSE.toString())


    }
}
