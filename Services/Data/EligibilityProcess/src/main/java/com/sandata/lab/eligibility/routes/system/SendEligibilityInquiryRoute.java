package com.sandata.lab.eligibility.routes.system;

import org.apache.camel.LoggingLevel;
import org.apache.camel.PropertyInject;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.eligibility.utils.log.LoggingUtils;

/**
 * Route to send eligibility inquiry 270 JSON files from temporary folder to ELIGIBILL
 */
public class SendEligibilityInquiryRoute extends AbstractRouteBuilder {

    @PropertyInject("{{send.eligibility.inquiry.temp.from.endpoint}}")
    private String sendEligibilityInquiryTempFromEndpoint;

    @PropertyInject("{{send.eligibility.inquiry.endpoint}}")
    private String sendEligibilityInquiryEndpoint;

    @Override
    protected void buildRoute() {
        onException(Exception.class)
            .log(LoggingLevel.ERROR, LoggingUtils.getErrorMessageInfo(this, "Unexpected exception happens in ${routeId}. Exception message: ${exception.message}.\nStack trace: ${exception.stacktrace}"));

        from(sendEligibilityInquiryTempFromEndpoint)
            .routeId(this.getClass().getSimpleName())
            .to(sendEligibilityInquiryEndpoint)
            .log(LoggingLevel.INFO, LoggingUtils.getLogMessageInfo(this, "Sent file to ELIGIBILL, file = ${header.CamelFileNameProduced}"));
    }
}
