package com.sandata.lab.security.auth.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.security.auth.utils.constants.App;

/**
 * <p>GetModulesRoute</p>
 * <p><t>Endpoint for getting all defined application modules.</t></p>
 *
 * @author jasonscott
 */
public class GetSecureGroupsRoute extends AbstractRouteBuilder {
    @Override
    protected void buildRoute() {
        from("seda:" + App.ID.GET_SECURE_GROUPS.toString())
                .routeId(App.ID.GET_SECURE_GROUPS.toString())
                .threads().executorServiceRef("sandataAuthThreadPool")
                .beanRef("authenticationService", "getSecureGroups")
                .beanRef("formatTransformer", "toResponse");
    }
}
