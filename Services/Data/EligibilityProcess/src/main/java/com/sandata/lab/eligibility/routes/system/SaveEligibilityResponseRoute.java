package com.sandata.lab.eligibility.routes.system;

import org.apache.camel.LoggingLevel;
import org.apache.camel.PropertyInject;
import org.apache.camel.ShutdownRunningTask;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.eligibility.processors.EligibilityResponseProcessor;
import com.sandata.lab.eligibility.utils.constants.App;
import com.sandata.lab.eligibility.utils.log.LoggingUtils;

/**
 * Route to save eligibility response 271 JSON from ELIGIBILL to database
 */
public class SaveEligibilityResponseRoute extends AbstractRouteBuilder{

    @PropertyInject("{{elig.response.process.local.dir}}")
    private String eligResponseProcessingDir;

    @PropertyInject("{{elig.response.download.redeliverydelay}}")
    private int redeliveryDelay;

    @PropertyInject("{{elig.response.download.maximumRedeliveries}}")
    private int maximumRedeliveries;

    @Override
    protected void buildRoute() {

        onException(Exception.class)
            .log(LoggingLevel.ERROR, LoggingUtils.getErrorMessageInfo(this, "Unexpected exception happens in ${routeId}. Exception message: ${exception.message}.\nStack trace: ${exception.stacktrace}"))
            .handled(true)
            .stop();

        from(eligResponseProcessingDir)
             .routeId(this.getClass().getSimpleName())
             
             // When shutting down, Camel will wait until the batch completed
             .shutdownRunningTask(ShutdownRunningTask.CompleteAllTasks)
             
             //TODO: should not retry with general Exception. Should retry with specific Exceptions such as database connection timeout
             .onException(Exception.class)
                 .redeliveryDelay(redeliveryDelay)
                 .maximumRedeliveries(maximumRedeliveries)
                 .log(LoggingLevel.WARN, LoggingUtils.getLogMessageInfo(this, "Exception happens. ${exception}\n Retrying..."))
             .end()
             
             .log(LoggingUtils.getLogMessageInfo(this, "Processing response JSON file:${header.CamelFileName}"))
             
             // converting response 271 JSON to POJO ResponseMessage
             .beanRef(EligibilityResponseProcessor.BEAN_NAME, EligibilityResponseProcessor.CONVERT_JSON_TO_RESPONSE_MESSAGE_METHOD)
             
             // look up eligibility status from ResponseMessage based on trace number (link between Eligibility SK and trace name is stored in levelDB)
             .beanRef(EligibilityResponseProcessor.BEAN_NAME, EligibilityResponseProcessor.LOOKUP_ELIG_STATUS_BY_TRACE_NUMBER_METHOD)
             
             // update eligibility status to database
             .beanRef(EligibilityResponseProcessor.BEAN_NAME, EligibilityResponseProcessor.UPDATE_ELIG_STATUS_METHOD)
             
             .log(LoggingUtils.getLogMessageInfo(this,
                    new StringBuilder("Completed processing response JSON file:${header.CamelFileName} at ")
                            .append(App.DATE_NOW_IN_CAMEL_LOGGING).toString()));
    }
    

}
