package com.sandata.lab.eligibility.routes.system;

import org.apache.camel.LoggingLevel;
import org.apache.camel.PropertyInject;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.eligibility.processors.EligibilityInquiryProcessor;
import com.sandata.lab.eligibility.utils.RouteUtils;
import com.sandata.lab.eligibility.utils.log.LoggingUtils;

/**
 * Route to build the eligibility inquiry JSON file from PT_PAYER_SK in
 * exchange's body and save the file to temporary folder
 */
public class CreateEligibilityInquiryHandlerRoute extends AbstractRouteBuilder {

    @PropertyInject("{{send.eligibility.inquiry.temp.to.endpoint}}")
    private String sendEligibilityInquiryTempToEndpoint;

    @Override
    protected void buildRoute() {
        onException(Exception.class)
            .log(LoggingLevel.ERROR, LoggingUtils.getErrorMessageInfo(this, "Unexpected exception happens in ${routeId}. Exception message: ${exception.message}.\nStack trace: ${exception.stacktrace}"))
            .handled(true)
            .stop();

        from(RouteUtils.getInquiryRequestHandlerEndpoint())
            .routeId(this.getClass().getSimpleName())
            
            .log(LoggingUtils.getLogMessageInfo(this, "Start creating eligibility inquiry JSON file for PT_PAYER_SK = ${body}"))
            
            // build the eligibility inquiry record (Map<String, Object>) from PT_PAYER_SK
            .beanRef(EligibilityInquiryProcessor.BEAN_NAME, EligibilityInquiryProcessor.GET_ELIGIBILITY_INQUIRY_BY_PATIENT_PAYER_SK_METHOD)
            
            // convert the eligibility inquiry record (Map<String, Object>) to JSON file
            .beanRef(EligibilityInquiryProcessor.BEAN_NAME, EligibilityInquiryProcessor.CONVERT_TO_INQUIRY_270_JSON_FILE_METHOD)
            
            // then save the eligibility inquiry JSON file to the temp folder
            .to(sendEligibilityInquiryTempToEndpoint)
            
            // insert a record to ELIG table
            .beanRef(EligibilityInquiryProcessor.BEAN_NAME, EligibilityInquiryProcessor.INSERT_ELIGIBILITY_METHOD)
            
            .log(LoggingUtils.getLogMessageInfo(this, "Complete creating eligibility inquiry JSON file in temp folder = ${header.CamelFileNameProduced}"));
    }
}
