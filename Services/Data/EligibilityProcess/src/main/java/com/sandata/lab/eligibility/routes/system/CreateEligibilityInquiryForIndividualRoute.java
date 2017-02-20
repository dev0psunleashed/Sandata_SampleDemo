package com.sandata.lab.eligibility.routes.system;

import org.apache.camel.LoggingLevel;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.data.model.constants.eligibility.Eligibility;
import com.sandata.lab.eligibility.utils.RouteUtils;
import com.sandata.lab.eligibility.utils.log.LoggingUtils;

public class CreateEligibilityInquiryForIndividualRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {
        from(getStartEndpoint())
            .routeId(this.getClass().getSimpleName())
            .threads().executorServiceRef("eligibilityProcessThreadPool")
            .choice()
                // send eligibility inquiry when a patient-payer is inserted or updated
                .when().simple(getConditionToCreateEligibilityInquiry())
                    .log(LoggingLevel.INFO, LoggingUtils.getLogMessageInfo(this, "Handled request - operationName=${in.header.operationName}, body=${body}"))
                    .to(RouteUtils.getInquiryRequestHandlerEndpoint())
                    .log(LoggingLevel.INFO, LoggingUtils.getLogMessageInfo(this, String.format("Forwarded request to request handler = %s", RouteUtils.getInquiryRequestHandlerEndpoint())))
                .otherwise()
                    .log(LoggingLevel.DEBUG, LoggingUtils.getLogMessageInfo(this, "Unhandled request - operationName=${in.header.operationName}"))
                .end()
            .end();
    }

    protected String getStartEndpoint() {
        return new StringBuilder()
                .append(Messaging.Names.COMPONENT_TYPE_QUEUE).append(Eligibility.EVENT.ELIGIBILITY_PROCESS_SENDS_INQUIRY_270_REQUEST)
                .toString();
    }

    private String getConditionToCreateEligibilityInquiry() {
        return new StringBuilder()
                .append("${in.header.operationName} contains '_insertPtPayer_'")
                .append(" || ${in.header.operationName} contains '_updatePtPayer_'")
                .toString();
    }
}
