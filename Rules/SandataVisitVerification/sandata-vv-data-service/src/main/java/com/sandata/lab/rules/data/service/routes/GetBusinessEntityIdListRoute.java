package com.sandata.lab.rules.data.service.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rules.vv.log.utils.App;
import org.apache.camel.LoggingLevel;
import org.apache.camel.PropertyInject;
import org.apache.camel.model.RouteDefinition;

/**
 * <p>Route for retrieving list of active business entity IDs.</p>
 *
 * @author jasonscott
 */
public class GetBusinessEntityIdListRoute extends AbstractRoute {

    public static final String BSN_ENT_ID_LIST_CACHE_KEY = "BSN_ENT_ID_LIST";

    @Override
    protected String getStartEndpoint() {
        return Messaging.Names.COMPONENT_TYPE_QUEUE
                + App.ID.GET_BUSINESS_ENTITY_ID_LIST_ROUTE.toString();
    }

    @Override
    protected String getRouteId() {
        return App.ID.GET_BUSINESS_ENTITY_ID_LIST_ROUTE.toString();
    }

    @Override
    protected void addMoreRouteDefinition(RouteDefinition definition) {

        definition
        .threads().executorServiceRef("sandataVvDataServiceThreadPool")
        .log(LoggingLevel.INFO, "Querying active business entity ID list from COREDATA or REDIS.")
        // Get data from cache by cache key
        .setHeader("cacheKey", constant(BSN_ENT_ID_LIST_CACHE_KEY))
        .beanRef("visitVerificationCacheHandler", "getCachedData")
        .choice()
        .when(body().isNull())
        // In case of there no data from cache, then get from database
        .beanRef("visitVerificationDataService", "getBusinessEntityIdList")
        // Put result from database to REDIS
        .beanRef("visitVerificationCacheHandler", "insertData")
        .otherwise()
        .beanRef("visitVerificationCacheHandler", "toBsnEntIdList")
        .endChoice()
        .end()
        .log(LoggingLevel.INFO, "${body}");
    }
}
