package com.sandata.lab.rules.data.service.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rules.vv.log.utils.App;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.PropertyInject;
import org.apache.camel.model.RouteDefinition;

/**
 * <p>Route for retrieving business entity call preferences.</p>
 *
 * @author jasonscott
 */
public class GetBusinessEntityCallPreferences extends AbstractRoute {

    public static final String CALL_PREFERENCES_CACHE_KEY_PREFIX = "CALL_PREFERENCE_KEY_";

    @Override
    protected String getStartEndpoint() {
        return Messaging.Names.COMPONENT_TYPE_QUEUE.toString()
                + App.ID.GET_BUSINESS_ENTITY_CALL_PREFERENCES.toString();
    }

    @Override
    protected String getRouteId() {
        return App.ID.GET_BUSINESS_ENTITY_CALL_PREFERENCES.toString();
    }

    @Override
    protected void addMoreRouteDefinition(RouteDefinition definition) {

        definition
        .threads().executorServiceRef("sandataVvDataServiceThreadPool")
        .log(LoggingLevel.INFO, "Querying call preferences by business entity ID from metadata or REDIS.")
        //get data from cache by cache key
        .beanRef("visitVerificationCacheHandler","setCallPreferenceCacheKeyToHeader")
        .beanRef("visitVerificationCacheHandler","getCachedData")
        .log("Call-preference from cache : ${body}")
        .choice()
        .when(body().isNull())
        // in case of there no data from cache, then get from database
        .beanRef("visitVerificationDataService", "getBusinessEntityCallPreferences")
        //put result from database to REDIS
        .beanRef("visitVerificationCacheHandler","insertData")
        .otherwise()
        //convert cached string data to call preferences
        .beanRef("visitVerificationCacheHandler","toCallPreferences")
        .end()
        .log(LoggingLevel.INFO, "Callpreference enrich result: ${body}");
    }
}
