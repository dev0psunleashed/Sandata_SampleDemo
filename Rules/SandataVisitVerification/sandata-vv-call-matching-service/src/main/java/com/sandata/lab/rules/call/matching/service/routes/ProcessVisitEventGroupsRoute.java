package com.sandata.lab.rules.call.matching.service.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.PropertyInject;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rules.call.matching.service.strategy.CallMatchingEnrichmentStrategy;
import com.sandata.lab.rules.call.matching.service.wildfly.rest.KieServerJMSRestHandler_willrollback;
import com.sandata.lab.rules.call.matching.service.wildfly.rest.KieServerRestHandler;
import com.sandata.lab.rules.call.matching.service.wildfly.rest.KieServerJMSHandler;
import com.sandata.lab.rules.vv.log.utils.App;
import com.sandata.lab.rules.vv.log.utils.LoggingUtils;

/**
 * <p>Route for processing Visit Events from import calls service grouped by DNIS.</p>
 *
 * @author thanhxle
 */
public class ProcessVisitEventGroupsRoute extends AbstractRouteBuilder {

    @PropertyInject("{{exchange.exception.redelivery.delay}}")
    private int redeliveryDelay = 10000;

    @PropertyInject("{{exchange.exception.maximum.redeliveries}}")
    private int maximumRedeliveries = 0;

    @PropertyInject("{{visit.event.dnis.agenda.group}}")
    private String visitEventDnisAgendaGroup = "call-matching-dnis";
    
    @PropertyInject("{{call.matching.kie.session.name}}")
    private String kieSessionId = "callMatchingSession";

    @Override
    protected void buildRoute() {
        from(getFromEndpointUrl())
            .routeId(App.ID.CALL_MATCHING_PROCESS_VISIT_EVENT_GROUPS.toString())
            .onException(Exception.class)
            .log(LoggingLevel.ERROR, "[${routeId}] There is unexpected exception: message: ${exception.message}, stacktrace: ${exception.stacktrace} , whole exception ${exception}")
            .logExhausted(false)
            .redeliveryDelay(redeliveryDelay)
            .maximumRedeliveries(maximumRedeliveries)
            .end()
            .threads().executorServiceRef("sandataVvRulesMatchingServiceThreadPool")
            .choice().when(body().isNull())
            .log(LoggingLevel.ERROR, "NULL body received!")
            .otherwise()
            .log(LoggingUtils.getLogMessageForRoute(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD
            		,"VisitEventDNISGroup for DNIS ${body?.getDnis} with ${body?.getVisitEvents?.size} VisitEvent(s)"))
            // Get BsnEnt to DNIS Mapping via vv-data-services and verify.
            .enrich(getEnrichEndpointUrl(),
                new CallMatchingEnrichmentStrategy())
            .choice().when(body().isNull())
            .log(LoggingLevel.ERROR, "NULL business entity dnis mapping received!")
            .otherwise()
            .setHeader("agendaGroupName", constant(visitEventDnisAgendaGroup))
            .setHeader("kieSessionId", constant(kieSessionId))
            //TODO: switch to rest api
            //.bean(KieServerRestHandler.class, "executeRules")
            .bean(KieServerRestHandler.class, "executeRules")
            //.to(Messaging.Names.COMPONENT_TYPE_QUEUE + "jms/queue/KIE.SERVER.REQUEST")
            //log response from wildfly rest 
            .log(LoggingUtils.getLogMessageForRoute(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD,"Response from kie server: ${body}"))
            .endChoice()
            .endChoice()
            .end();
    }


    /**
     * get endpoint route url
     *
     * @return
     */
    public String getFromEndpointUrl() {

        return Messaging.Names.COMPONENT_TYPE_QUEUE.toString()
            + App.ID.CALL_MATCHING_PROCESS_VISIT_EVENT_GROUPS.toString();
    }


    public String getEnrichEndpointUrl() {

        return Messaging.Names.COMPONENT_TYPE_QUEUE.toString()
            + App.ID.GET_BUSINESS_ENTITY_DNIS_MAPPING_ROUTE.toString();
    }
}
