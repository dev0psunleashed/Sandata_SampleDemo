package com.sandata.lab.rules.visit.exception.service.routes;

import com.sandata.lab.rules.vv.fact.VisitEventFact;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.PropertyInject;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rules.visit.exception.service.strategy.VisitExceptionEnrichmentStrategy;
import com.sandata.lab.rules.visit.exception.service.utils.VisitExceptionUtils;
import com.sandata.lab.rules.vv.log.utils.App;
import com.sandata.lab.rules.vv.log.utils.LoggingUtils;

/**
 * <p>Route is the entry point to visit exception engine that received input data as VISIT_SK.
 * VISIT_SK comes from different sources: manual changes of visit/schedule in UI , call center.
 * </p>
 *
 * @author thanhxle
 */
public class VisitExceptionsEntryRoute extends AbstractRouteBuilder {

    @PropertyInject("{{exchange.exception.redelivery.delay}}")
    private int redeliveryDelay = 10000;

    @PropertyInject("{{exchange.exception.maximum.redeliveries}}")
    private int maximumRedeliveries = 0;

    @PropertyInject("{{wildfly.kie.session.id}}")
    private String kieSessionId = "sandadaOneDroolsSession";

    @PropertyInject("{{wildfly.kie.agendagroup}}")
    private String agendaGroupName = "";
    

    @Override
    protected void buildRoute() {
        VisitExceptionEnrichmentStrategy visitExceptionEnrichmentStrategy = new VisitExceptionEnrichmentStrategy();

        from(Messaging.Names.COMPONENT_TYPE_QUEUE
            + App.ID.VISIT_EXCEPTION_ENTRY_ROUTE.toString())
            .routeId(App.ID.VISIT_EXCEPTION_ENTRY_ROUTE.toString())
            .onException(Exception.class)
            .log(LoggingLevel.ERROR, "[${routeId}] There is unexpected exception: message: ${exception.message}, stacktrace: ${exception.stacktrace} , whole exception ${exception}")
            .logExhausted(false)
            .redeliveryDelay(redeliveryDelay)
            .maximumRedeliveries(maximumRedeliveries)
            .end()
            .log(LoggingUtils.getLogMessageForRoute(LoggingUtils.SUB_APP_VISIT_EXCEPTION_KEYWORD, "Beginning of visit excp engine for visit_sk: ${body}"))
            // If body is VisitEventFact, replace body with VisitSk value
            .process(new Processor() {
                @Override
                public void process(Exchange exchange) throws Exception {
                    if (null != exchange.getIn().getBody() && exchange.getIn().getBody() instanceof VisitEventFact) {
                        exchange.getIn().setBody(String.valueOf(((VisitEventFact)exchange.getIn().getBody()).getVisitSK()));
                    }
                }
            })
            //look up visit info by sk
            .enrich(Messaging.Names.COMPONENT_TYPE_QUEUE.toString()
                    + App.ID.GET_VISIT_INFO_BY_SK_ROUTE.toString(), visitExceptionEnrichmentStrategy)
            // look up ScheduleEvent by visit
            .enrich(Messaging.Names.COMPONENT_TYPE_QUEUE.toString()
                    + App.ID.GET_SCHED_EVENT_FACT_BY_VISIT_ROUTE.toString(), visitExceptionEnrichmentStrategy)
            // look up VisitEvent by visit
            .enrich(Messaging.Names.COMPONENT_TYPE_QUEUE.toString()
                    + App.ID.GET_VISIT_EVENT_FACT_BY_VISIT_ROUTE.toString(), visitExceptionEnrichmentStrategy)
            // look up Patient Contact Phone Number List by patient ID.
            .enrich(Messaging.Names.COMPONENT_TYPE_QUEUE.toString()
                    + App.ID.GET_PHONE_NUMBERS_BY_PATIENT_ID_ROUTE.toString(), visitExceptionEnrichmentStrategy)
            // look up service for a visit by visit SK.
            .enrich(Messaging.Names.COMPONENT_TYPE_QUEUE.toString()
                    + App.ID.GET_VISIT_SERVICE_BY_VISIT_SK_ROUTE.toString(), visitExceptionEnrichmentStrategy)
            //get visit exception setting for agency level
            .enrich(Messaging.Names.COMPONENT_TYPE_QUEUE.toString()
                    + App.ID.GET_AGENCY_LEVEL_EXCP_LIST_ROUTE.toString(), visitExceptionEnrichmentStrategy)
            //get EXCP_LKUP FOR MODE_NAME = 'VISIT VERIFICATION'
            //Get all the pre-defined exception from EXCP_LKUP database table
            .enrich(Messaging.Names.COMPONENT_TYPE_QUEUE.toString()
                    + App.ID.GET_VISIT_VERIFICATION_EXCEPTIONS_ROUTE.toString(), visitExceptionEnrichmentStrategy)   

            // For unplanned visit, we just get exception setting at the agency level
            .choice()
                .when(header("IS_PLANNED_VISIT").isEqualTo(true))
                    //lookup the visit exception setting for contract level , only apply for planned visit.
                    .enrich(Messaging.Names.COMPONENT_TYPE_QUEUE.toString()
                        + App.ID.GET_CONTRACT_LEVEL_EXCP_LIST_ROUTE.toString(), visitExceptionEnrichmentStrategy)
            .endChoice() 
            .end()
            .bean(VisitExceptionUtils.class,"setApplicableExcpToVisitfact")
            .log(LoggingLevel.INFO, body().toString())
            // Prepare to send to Kie Execution Server
            .choice()
                .when(body().isNotNull())
                    .setHeader("agendaGroupName", constant(agendaGroupName))
                    .setHeader("kieSessionId", constant(kieSessionId))
                    .bean("kieServerHandler","executeRules")
            .endChoice()
            .end()
            ;
    }
}
