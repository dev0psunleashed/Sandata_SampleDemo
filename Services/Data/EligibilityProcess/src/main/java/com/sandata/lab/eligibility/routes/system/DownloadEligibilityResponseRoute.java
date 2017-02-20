package com.sandata.lab.eligibility.routes.system;

import org.apache.camel.LoggingLevel;
import org.apache.camel.PropertyInject;
import org.apache.camel.ShutdownRunningTask;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.eligibility.utils.constants.App;
import com.sandata.lab.eligibility.utils.log.LoggingUtils;

/**
 * Route to get  eligibility response 271 JSON from remote server to local directory
 */
public class DownloadEligibilityResponseRoute extends AbstractRouteBuilder{

    @PropertyInject("{{elig.response.download.source}}")
    private String eligResponseDownloadSourceUrl;

    @PropertyInject("{{elig.response.download.target}}")
    private String eligResponseDownloadTargetUrl;

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

        from(eligResponseDownloadSourceUrl)
            .routeId(this.getClass().getSimpleName())
            .shutdownRunningTask(ShutdownRunningTask.CompleteAllTasks)
            
            .log(LoggingLevel.INFO, LoggingUtils.getLogMessageInfo(this, "Start downloading response JSON file:${file:name} from ELIGIBILL"))
            
            //TODO: should not retry with general Exception. Should retry with specific Exceptions such as network connection timeout
            .onException(Exception.class)
                .redeliveryDelay(redeliveryDelay)
                .maximumRedeliveries(maximumRedeliveries)
                .log(LoggingLevel.WARN, LoggingUtils.getLogMessageInfo(this, "Exception happens. ${exception}\n Retrying..."))
            .end()
            
            // save eligibility responses from ELIGIBILL to local folder
            .to(eligResponseDownloadTargetUrl)
            
            .log(LoggingUtils.getLogMessageInfo(this,
                    new StringBuilder(
                            "Completed downloading response JSON file from ELIGIBILL to ${header.CamelFileNameProduced} at ")
                                    .append(App.DATE_NOW_IN_CAMEL_LOGGING).toString()));
    }
}
