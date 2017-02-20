package com.sandata.lab.rules.data.service.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.rules.vv.log.utils.LoggingUtils;

import org.apache.camel.LoggingLevel;
import org.apache.camel.PropertyInject;
import org.apache.camel.model.RouteDefinition;

/**
 * Created by thanhxle on 11/7/2016.
 */
public abstract class AbstractRoute  extends AbstractRouteBuilder {

    @PropertyInject("{{exchange.exception.redelivery.delay}}")
    private int redeliveryDelay = 10000;

    @PropertyInject("{{exchange.exception.maximum.redeliveries}}")
    private int maximumRedeliveries = 1;

    @SuppressWarnings("unchecked")
    @Override
    public void configure() throws Exception {
        // Retry if there is an exception with database connection
        //TODO: for specific exception
        /*onException(RecordProcessingException.class, FileProcessingException.class)
                .handled(true)
                // "redeliverPolicy" is a bean id defined in blueprint.xml
                .redeliveryPolicyRef(REDELIVERY_POLICY_REF)
                .log(LoggingLevel.ERROR,
                        LoggingUtils.getLogMessageError(Commons.LOG_TYPES.RECORD_IMPORT_PROCESSING_ERROR_KEYWORD_LOGS, this.getClass().getSimpleName(),"[${header.LogInfoSrcFtpLocId}_${header.LogInfoHhaxAgencyId}][route-id] : [${routeId}] - Failed to redeliver with error ${exception.message}"))
                .log(LoggingLevel.ERROR, LoggingUtils.getLogMessageError(Commons.LOG_TYPES.RECORD_IMPORT_PROCESSING_ERROR_KEYWORD_LOGS, this.getClass().getSimpleName(), "[${header.LogInfoSrcFtpLocId}_${header.LogInfoHhaxAgencyId}] Exception while processing file: ${header.CamelFileName} at line ${property.CamelSplitIndex} (started from 0)"
                        + "Exception message: ${exception.message}. \n"
                        + "Stack trace: ${exception.stacktrace}"));*/
        super.configure();
    }

    @Override
    protected void buildRoute() {

        // Setting action when complete
        onComplete();

        // Setting exception for logging
        onException();

        // Setting route definition
        onRouteDefinition();

    }

    private void onRouteDefinition() {
        RouteDefinition definition = from(getStartEndpoint()).routeId(getRouteId());

        // Add more setting
        addMoreRouteDefinition(definition);

    }

    protected void onComplete() {
        // TODO: Child class will override for use if needed
    }

    protected void onException() {
        onException(Exception.class)
                .log(LoggingLevel.ERROR, LoggingUtils.getErrorLogMessageForRoute(LoggingUtils.SUB_APP_VV_DATA_SERVICE
                		, "[${routeId}] There is unexpected exception: message: ${exception.message}, stacktrace: ${exception.stacktrace} , whole exception ${exception}"))
                .logExhausted(false)
                .redeliveryDelay(redeliveryDelay)
                .maximumRedeliveries(maximumRedeliveries)
                .end();
    }

    protected abstract String getStartEndpoint();

    protected abstract String getRouteId();

    /**
     * Defines main activities for the route
     *
     * @param definition the {@link RouteDefinition} instance to which more route
     *                   activities are defined/added
     */
    protected abstract void addMoreRouteDefinition(final RouteDefinition definition);
}
