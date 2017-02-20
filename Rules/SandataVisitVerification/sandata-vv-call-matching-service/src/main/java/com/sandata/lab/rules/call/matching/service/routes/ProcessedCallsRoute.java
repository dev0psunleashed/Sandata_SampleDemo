package com.sandata.lab.rules.call.matching.service.routes;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.PropertyInject;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rules.vv.fact.VisitEventFact;
import com.sandata.lab.rules.vv.log.utils.App;
import com.sandata.lab.rules.vv.log.utils.LoggingUtils;

/**
 * <p>Route for Sending processed visit evnt back to EVV import call.</p>
 *
 * @author thanhxle
 */
public class ProcessedCallsRoute extends AbstractRouteBuilder {

    @PropertyInject("{{exchange.exception.redelivery.delay}}")
    private int redeliveryDelay = 10000;

    @PropertyInject("{{exchange.exception.maximum.redeliveries}}")
    private int maximumRedeliveries = 0;

    @Override
    protected void buildRoute() {
        from(getFromEndpointUrl())
            .routeId(App.ID.PROCESSED_CALL_ROUTE.toString())
            .onException(Exception.class)
            .log(LoggingLevel.ERROR, "[${routeId}] There is unexpected exception: message: ${exception.message}, stacktrace: ${exception.stacktrace} , whole exception ${exception}")
            .logExhausted(false)
            .redeliveryDelay(redeliveryDelay)
            .maximumRedeliveries(maximumRedeliveries)
            .end()
            .process(new Processor() {
                @Override
                public void process(Exchange exchange) throws Exception {
                    VisitEventFact visitEventFact = ( VisitEventFact ) exchange.getIn().getBody();
                    if (visitEventFact != null) {
                        exchange.getIn().setBody(visitEventFact.getVisitEventExt());
                    }
                }
            })
            //back to evv call import, to mark as processed in db
            .log(LoggingUtils.getLogMessageForRoute(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD, "Sending processed visit evnt back to EVV import call: ${body}"))
            .to(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.PROCESSED_VV_CALLS_IMPORT_ROUTE.toString());
    }


    /**
     * get endpoint route url
     *
     * @return
     */
    public String getFromEndpointUrl() {

        return Messaging.Names.COMPONENT_TYPE_QUEUE.toString()
            + App.ID.PROCESSED_CALL_ROUTE.toString();
    }



}
