package com.sandata.lab.rules.call.matching.service.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.PropertyInject;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rules.call.matching.service.strategy.CallMatchingEnrichmentStrategy;
import com.sandata.lab.rules.call.matching.service.strategy.VisitEventAggregationStrategy;
import com.sandata.lab.rules.call.matching.service.utils.CallMatchingUtil;
import com.sandata.lab.rules.call.matching.service.wildfly.rest.KieServerRestHandler;
import com.sandata.lab.rules.vv.log.utils.App;
import com.sandata.lab.rules.vv.log.utils.LoggingUtils;

/**
 * <p>Route for processing Visit Events from import calls service grouped by DNIS.</p>
 *
 * @author jasonscott
 */
public class VisitEventStaffAggregatorRoute extends AbstractRouteBuilder {

    @PropertyInject("{{exchange.exception.redelivery.delay}}")
    private int redeliveryDelay = 10000;

    @PropertyInject("{{exchange.exception.maximum.redeliveries}}")
    private int maximumRedeliveries = 0;

    @PropertyInject("{{visit.event.aggregation.time}}")
    private Long visitEventAggregationTime = 30000L;

    @PropertyInject("{{visit.event.staff.agenda.group}}")
    private String callMatchingStaff = "call-matching-staff";
    
    @PropertyInject("{{call.matching.kie.session.name}}")
    private String kieSessionId = "callMatchingSession";

    @Override
    protected void buildRoute() {

        CallMatchingEnrichmentStrategy callMatchingEnrichmentStrategy = new CallMatchingEnrichmentStrategy();

        from(getFromEndpointUrl())
            .routeId(App.ID.CALL_MATCHING_VISIT_EVENT_STAFF_AGGREGATOR.toString())
            .onException(Exception.class)
            .log(LoggingLevel.ERROR, "[${routeId}] There is unexpected exception: message: ${exception.message}, stacktrace: ${exception.stacktrace} , whole exception ${exception}")
            .logExhausted(false)
            .redeliveryDelay(redeliveryDelay)
            .maximumRedeliveries(maximumRedeliveries)
            .end()
            .aggregate(simple("${body?.getBusinessEntityId}"), new VisitEventAggregationStrategy())
            .completionInterval(visitEventAggregationTime)
            .log(LoggingLevel.INFO, "Completed to aggregate VisitEventFact for staff matching: ${body}")
            .bean(CallMatchingUtil.class, "extractStaffIds")
            // Get ScheduleEvents for business entity ID and lower and upper limit dates.
            .enrich(Messaging.Names.COMPONENT_TYPE_QUEUE.toString()
                            + App.ID.GET_STAFFS_ROUTE.toString(),
                callMatchingEnrichmentStrategy)
            .choice().when(body().isNull())
            .log(LoggingLevel.ERROR, "NULL body after staff enrichment!")
            .otherwise()
            .log(LoggingLevel.INFO, "Body after staff enrichment: ${body}")
            .setHeader("agendaGroupName", constant(callMatchingStaff))
            .setHeader("kieSessionId", constant(kieSessionId))
            .bean(KieServerRestHandler.class, "executeRules")
            //log response from wildfly rest 
            .log(LoggingUtils.getLogMessageForRoute(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD,"Response from kie server: ${body}"))
            .endChoice();
    }


    /**
     * get endpoint route url
     *
     * @return
     */
    public String getFromEndpointUrl() {

        return Messaging.Names.COMPONENT_TYPE_QUEUE.toString()
            + App.ID.CALL_MATCHING_VISIT_EVENT_STAFF_AGGREGATOR.toString();
    }

  
}
