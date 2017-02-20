package com.sandata.lab.rules.data.service.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rules.vv.log.utils.App;
import org.apache.camel.LoggingLevel;
import org.apache.camel.PropertyInject;
import org.apache.camel.model.RouteDefinition;

/**
 * <p>Route for retrieving business entity visit verification settings.</p>
 *
 * @author jasonscott
 */
public class GetBusinessEntityVisitVerificationSettingsRoute extends AbstractRoute {

    public static final String VV_SETTINGS_CACHE_KEY_PREFIX = "VV_SETTINGS_";

    @Override
    protected String getStartEndpoint() {
        return Messaging.Names.COMPONENT_TYPE_QUEUE
                + App.ID.GET_BUSINESS_ENTITY_VISIT_VERIFICATION_SETTINGS_ROUTE.toString();
    }

    @Override
    protected String getRouteId() {
        return App.ID.GET_BUSINESS_ENTITY_VISIT_VERIFICATION_SETTINGS_ROUTE.toString();
    }

    @Override
    protected void addMoreRouteDefinition(RouteDefinition definition) {

        definition
        .threads().executorServiceRef("sandataVvDataServiceThreadPool")
        .log(LoggingLevel.INFO, "Querying visit verification settings for business entity ID ${header.bsnEntId} from METADATA or REDIS.")
        // Get data from cache by cache key
        .beanRef("visitVerificationCacheHandler", "setVisitVerificationSettingsCacheKeyToHeader")
        .beanRef("visitVerificationCacheHandler", "getCachedData")
        .choice()
        .when(body().isNull())
        // In case of there no data from cache, then get from database
        .beanRef("visitVerificationDataService", "getBusinessEntityVisitVerificationSettings")
        // Put result from database to REDIS
        .beanRef("visitVerificationCacheHandler", "insertData")
        .otherwise()
        .beanRef("visitVerificationCacheHandler", "toAgencyVisitVerificationSettings")
        .endChoice()
        .end()
        .log(LoggingLevel.INFO, "${body}");
    }
}
