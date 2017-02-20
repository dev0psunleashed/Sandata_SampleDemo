package com.sandata.lab.rules.call.matching.service.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.PropertyInject;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rules.vv.log.utils.App;
import com.sandata.lab.rules.vv.log.utils.LoggingUtils;



/**
 * <p>Route for processing visit event fact after matching processing.</p>
 *
 * @author thanhxle
 */
public class MatchedVisitEventAggregator extends AbstractRouteBuilder {

    @PropertyInject("{{exchange.exception.redelivery.delay}}")
    private int redeliveryDelay = 10000;

    @PropertyInject("{{exchange.exception.maximum.redeliveries}}")
    private int maximumRedeliveries = 0;

    @PropertyInject("{{visit.event.aggregation.time}}")
    private Long visitEventAggregationTime = 30000L;

    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_QUEUE
            + App.ID.CALL_MATCHING_MATCHED_VISIT_EVENT_AGGREGATOR.toString())
            .routeId(App.ID.CALL_MATCHING_MATCHED_VISIT_EVENT_AGGREGATOR.toString())
            .onException(Exception.class)
            .log(LoggingLevel.ERROR, "[${routeId}] There is unexpected exception: message: ${exception.message}, stacktrace: ${exception.stacktrace} , whole exception ${exception}")
            .logExhausted(false)
            .redeliveryDelay(redeliveryDelay)
            .maximumRedeliveries(maximumRedeliveries)
            .end()
            //check the VISIT EVENT FACT IS PLANNED OR UNPLANNED
            .log(LoggingUtils.getLogMessageForRoute(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD
            		,"Call state as : ${body?.getVisitState}"))
            .choice()
            .when().simple("${body?.getVisitState} == ${type:com.sandata.lab.rules.vv.model.VisitState.MATCHED}")
            //for planned visit
            .to(Messaging.Names.COMPONENT_TYPE_QUEUE
                        + App.ID.POST_PLANNED_VISIT_EVENT_ROUTE.toString())
            .otherwise()
            // for unplanned visit working flow
            .to(Messaging.Names.COMPONENT_TYPE_QUEUE
                        + App.ID.POST_UNPLANNED_VISIT_EVENT_ROUTE.toString())
            .end();
    }
}
