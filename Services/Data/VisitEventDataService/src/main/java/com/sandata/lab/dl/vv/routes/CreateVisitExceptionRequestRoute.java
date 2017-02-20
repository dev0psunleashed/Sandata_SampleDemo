package com.sandata.lab.dl.vv.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;

/**
 * Created by tom.dornseif on 2/25/2016.
 */
public class CreateVisitExceptionRequestRoute extends AbstractRouteBuilder {
    @Override
    protected void buildRoute() {
        from("activemq:queue:CEP_VISIT_EXCEPTIONS_REQUEST?concurrentConsumers=5").routeId("CREATE_VISIT_EXCEPTION_REQUEST")
                .beanRef("visitEventRepository", "createVisitExceptionRequest")
                .to("activemq:queue:CEP_VISIT_EXCEPTIONS_RESPONSE")
                .log("CreateVisitExceptionRequest: CreateVisitExceptionRequest: Complete!");

        from("activemq:queue:CEP_CLEAR_VISIT_EXCEPTIONS_REQUEST?exchangePattern=InOut").routeId("CLEAR_VISIT_EXCEPTION_REQUEST")
                .beanRef("visitEventRepository", "clearExceptions")
                .log("Exceptions Cleared ${body}");

        from("activemq:queue:CEP_EXC_LKUP_REQUEST").routeId("CEP_EXC_LKUP_REQUEST")
                .beanRef("visitEventRepository", "createExcLookupRequest")
                .to("activemq:queue:CEP_EXC_LKUP_RESPONSE")
                .log("CreateExcLookupRequest: createExcLookupResponse: Complete!");

        from("activemq:queue:CEP_CLEAR_VISIT_EXCEPTIONS_ARRAY_REQUEST").routeId("CLEAR_VISIT_EXCEPTIONS_ARRAY_REQUEST")
                .beanRef("visitEventRepository", "clearExceptionsArray")
                .log("Exceptions Cleared ${body}");

        from("activemq:queue:CEP_CLEAR_SCHEDULED_VISIT_EXCEPTIONS_ARRAY_REQUEST").routeId("CLEAR_SCHEDULED_VISIT_EXCEPTIONS_ARRAY_REQUEST")
                .beanRef("visitEventRepository", "clearScheduledVisitisExceptionsArray")
                .log("Exceptions Cleared ${body}");

    }
}
