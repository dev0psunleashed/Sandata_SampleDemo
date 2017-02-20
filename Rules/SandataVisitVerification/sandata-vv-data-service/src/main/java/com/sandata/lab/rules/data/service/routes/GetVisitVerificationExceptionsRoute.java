package com.sandata.lab.rules.data.service.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.model.RouteDefinition;

import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rules.vv.log.utils.App;

/**
 * <p>Route for retrieving visit verification exceptions.</p>
 *
 * @author jasonscott
 */
public class GetVisitVerificationExceptionsRoute extends AbstractRoute {

    public static final String VV_EXCP_LKUP_CACHE_KEY = "VV_EXCP_LKUP";


    @Override
    protected String getStartEndpoint() {
        return Messaging.Names.COMPONENT_TYPE_QUEUE
                + App.ID.GET_VISIT_VERIFICATION_EXCEPTIONS_ROUTE.toString();
    }

    @Override
    protected String getRouteId() {
        return App.ID.GET_VISIT_VERIFICATION_EXCEPTIONS_ROUTE.toString();
    }

    @Override
    protected void addMoreRouteDefinition(RouteDefinition definition) {

        definition
        .threads().executorServiceRef("sandataVvDataServiceThreadPool")
        .log(LoggingLevel.INFO, "Querying visit verification exception list from COREDATA or REDIS.")
        //get data from cache by cache key
        .setHeader("cacheKey", constant(VV_EXCP_LKUP_CACHE_KEY))
        .bean("visitVerificationCacheHandler","getCachedData")
        .choice()
        .when(body().isNull())
        // in case of there no data from cache, then get from database
        .bean("visitVerificationDataService", "getVisitVerificationExceptionList")
        //put result from database to REDIS
        .bean("visitVerificationCacheHandler","insertData")
        .otherwise()
        .bean("visitVerificationCacheHandler","toVisitVerificationException")
        .endChoice()
        .end()
        .log(LoggingLevel.INFO, "${body}");
    }
}
