package com.sandata.lab.billing.edi.routes.system;

import org.apache.camel.LoggingLevel;

import com.sandata.lab.billing.edi.processors.InstitutionalClaimProcessor;
import com.sandata.lab.billing.edi.utils.constants.App;
import com.sandata.lab.billing.edi.utils.log.LoggingUtils;
import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;

/**
 * A route to generate Institutional Claim (837I) in JSON format from database
 */
public class GenerateInstitutionalClaimJsonRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {
        onException(Exception.class)
            .log(LoggingLevel.ERROR, LoggingUtils.getErrorMessageInfo(this, "Unexpected exception happens in ${routeId}. Exception message: ${exception.message}.\nStack trace: ${exception.stacktrace}"))
            .handled(true)
            .stop();

        from("timer://tempTimer?repeatCount=1")
            .routeId(this.getClass().getSimpleName())
            .beanRef(InstitutionalClaimProcessor.BEAN_NAME, InstitutionalClaimProcessor.CREATE_CLAIM_MESSAGE_METHOD)
            .to(Messaging.Names.COMPONENT_TYPE_QUEUE + App.Id.CONVERT_CLAIM_IN_JSON_TO_ANSI_X12.toString())
            .log(LoggingLevel.INFO, LoggingUtils.getLogMessageInfo(this, "Created institutional claims in JSON"));
    }

}
