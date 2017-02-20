package com.sandata.lab.billing.edi.routes.system;

import org.apache.camel.LoggingLevel;
import org.apache.camel.PropertyInject;

import com.sandata.lab.billing.edi.utils.log.LoggingUtils;
import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;

/**
 * Route to send 837 ANSI x12 files from temporary folder to Clearing Magic
 */
public class Send837AnsiX12FileToClearingMagicRoute extends AbstractRouteBuilder {

    @PropertyInject("{{send.837.ansi.x12.file.to.clearing.magic.route.from.endpoint}}")
    private String send837AnsiX12FileFromEndpoint;

    @PropertyInject("{{send.837.ansi.x12.file.to.clearing.magic.route.to.endpoint}}")
    private String send837AnsiX12FileToEndpoint;

    @Override
    protected void buildRoute() {
        onException(Exception.class)
            .log(LoggingLevel.ERROR, LoggingUtils.getErrorMessageInfo(this, "Unexpected exception happens in ${routeId}. Exception message: ${exception.message}.\nStack trace: ${exception.stacktrace}"))
            .handled(true)
            .stop();

        from(send837AnsiX12FileFromEndpoint)
            .routeId(this.getClass().getSimpleName())
            .to(send837AnsiX12FileToEndpoint)
            .log(LoggingLevel.INFO, LoggingUtils.getLogMessageInfo(this, "Sent file to Clearing Magic, file = ${header.CamelFileNameProduced}"));;
    }

}
