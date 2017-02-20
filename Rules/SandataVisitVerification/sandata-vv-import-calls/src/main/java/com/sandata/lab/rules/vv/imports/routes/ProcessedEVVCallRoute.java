package com.sandata.lab.rules.vv.imports.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rules.vv.log.utils.App;
import com.sandata.lab.rules.vv.log.utils.LoggingUtils;
import org.apache.camel.LoggingLevel;
import org.apache.camel.PropertyInject;

/**
 * 
 * @author Ralph Sylvain
 * This route receives the visit after matching process from sandata-vv-call-matching-rules
 * and mark record as processed and update to database.
 *
 */
public class ProcessedEVVCallRoute extends AbstractRouteBuilder {

	@PropertyInject("{{exchange.exception.redelivery.delay}}")
    private int redeliveryDelay = 10000;

    @PropertyInject("{{exchange.exception.maximum.redeliveries}}")
    private int maximumRedeliveries = 0;
	    
    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.PROCESSED_VV_CALLS_IMPORT_ROUTE.toString())
                .routeId(App.ID.PROCESSED_VV_CALLS_IMPORT_ROUTE.toString())
                .onException(Exception.class)
	            .log(LoggingLevel.ERROR, "[${routeId}] There is unexpected exception: message: ${exception.message}, stacktrace: ${exception.stacktrace} , whole exception ${exception}")
	            .logExhausted(false)
	            .redeliveryDelay(redeliveryDelay)
	            .maximumRedeliveries(maximumRedeliveries)
	            .end()
                .log(LoggingUtils.getLogMessageForRoute(LoggingUtils.SUB_APP_CALL_IMPORT_KEYWORD, "Starting process return calls"))
                .beanRef("visitEventService", "processedVisitEvent")
                .log(LoggingUtils.getLogMessageForRoute(LoggingUtils.SUB_APP_CALL_IMPORT_KEYWORD,"Completed processing return calls"));
    }
}
