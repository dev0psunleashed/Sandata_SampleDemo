package com.sandata.lab.billing.edi.routes.system;

import org.apache.camel.LoggingLevel;
import org.apache.camel.PropertyInject;

import com.sandata.lab.billing.edi.utils.constants.App;
import com.sandata.lab.billing.edi.utils.log.LoggingUtils;
import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;

/**
 * A route to send Professional Claim (837P) and Institutional Claim (837I) in
 * JSON format to ELIGIBILL web service to generate 837 EDI (ANSI x12) files
 */
public class Generate837AnsiX12FileRoute extends AbstractRouteBuilder {

    @PropertyInject("{{send.837.ansi.x12.file.to.clearing.magic.route.from.endpoint}}")
    private String generate837AnsiX12FileToEndpoint;

    @Override
    protected void buildRoute() {
        onException(Exception.class)
            .log(LoggingLevel.ERROR, LoggingUtils.getErrorMessageInfo(this, "Unexpected exception happens in ${routeId}. Exception message: ${exception.message}.\nStack trace: ${exception.stacktrace}"))
            .handled(true)
            .stop();

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.Id.CONVERT_CLAIM_IN_JSON_TO_ANSI_X12.toString())
            // TODO: call Eligibill REST web service /CreateAnsi to create 837 ANSI X12 file
            .to(generate837AnsiX12FileToEndpoint)
            .log(LoggingLevel.INFO, LoggingUtils.getLogMessageInfo(this, "Created 837 ANSI X12 file, file = ${header.CamelFileNameProduced}"));
    }

}
