package com.sandata.lab.rules.data.service.routes;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.PropertyInject;
import org.apache.camel.model.RouteDefinition;

import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.data.model.dl.model.VisitException;
import com.sandata.lab.rules.data.service.strategy.VisitExceptionAggregationStrategy;
import com.sandata.lab.rules.vv.log.utils.App;

/**
 * <p>Route for processing Visit Exception and insert to database.</p>
 *
 * @author thanhxle
 */
public class VisitExceptionAggregator extends AbstractRoute {

    @PropertyInject("{{visit.event.aggregation.time}}")
    private Long visitEventAggregationTime = 30000L;

    @Override
    protected String getStartEndpoint() {
        return Messaging.Names.COMPONENT_TYPE_QUEUE.toString()
                + App.ID.INSERT_VISIT_EXCEPTION_AGGREGATOR.toString();
    }

    @Override
    protected String getRouteId() {
        return App.ID.INSERT_VISIT_EXCEPTION_AGGREGATOR.toString();
    }

    @Override
    protected void addMoreRouteDefinition(RouteDefinition definition) {

        definition
        .log(LoggingLevel.INFO, "Saving visit exceptions for visit_sk=${header.existingVisitSk}: ${body}")
        .bean("visitVerificationDataService","saveVisitExceptions")
        .log(LoggingLevel.INFO, "Completed saving exception for visit_sk=${header.existingVisitSk}");
    }

}
