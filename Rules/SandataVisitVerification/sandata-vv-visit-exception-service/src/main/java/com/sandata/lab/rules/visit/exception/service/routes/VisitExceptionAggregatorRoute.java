package com.sandata.lab.rules.visit.exception.service.routes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.PropertyInject;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.data.model.dl.model.VisitException;
import com.sandata.lab.rules.vv.fact.VisitFact;
import com.sandata.lab.rules.vv.log.utils.App;
import com.sandata.lab.rules.vv.log.utils.LoggingUtils;

/**
 * <p>This route handles incoming message (VisitFact) from Kie Execution Server after all the rules have been executed
 * </p>
 *
 * @author thanhxle
 */
public class VisitExceptionAggregatorRoute extends AbstractRouteBuilder {

    @PropertyInject("{{exchange.exception.redelivery.delay}}")
    private int redeliveryDelay = 10000;

    @PropertyInject("{{exchange.exception.maximum.redeliveries}}")
    private int maximumRedeliveries = 0;

    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.SANDATA_VALIDATION_AGGREGATOR.toString())
            .routeId(App.ID.SANDATA_VALIDATION_AGGREGATOR.toString())
            .onException(Exception.class)
            .log(LoggingLevel.ERROR, "[${routeId}] There is unexpected exception: message: ${exception.message}, stacktrace: ${exception.stacktrace} , whole exception ${exception}")
            .logExhausted(false)
            .redeliveryDelay(redeliveryDelay)
            .maximumRedeliveries(maximumRedeliveries)
            .end()
            .log(LoggingUtils.getLogMessageForRoute(LoggingUtils.SUB_APP_VISIT_EXCEPTION_KEYWORD, "Receiving visit exception from kie-server , ${body}"))
            .process(new Processor() {
                @Override
                public void process(final Exchange exchange) throws Exception {
                    if(exchange.getIn().getBody() instanceof VisitFact) {
                        VisitFact visitFact = exchange.getIn().getBody(VisitFact.class);
                        if (visitFact != null) {
                            exchange.getIn().setHeader("existingVisitSk", visitFact.getVisitSk().longValue());
                            exchange.getIn().setBody(visitFact.getVisitExceptions());
                        }
                    }
                }
            })
            .to(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.INSERT_VISIT_EXCEPTION_AGGREGATOR.toString())
            .log(LoggingUtils.getLogMessageForRoute(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD,"Send visit exceptions to data service"));
    }

}
