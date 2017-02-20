package com.sandata.lab.security.auth.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.security.auth.utils.constants.App;

/**
 * <p>GetModulesRoute</p>
 * <p><t>Endpoint for getting all defined application modules.</t></p>
 *
 * @author jasonscott
 */
public class GetModulesRoute extends AbstractRouteBuilder {
    @Override
    protected void buildRoute() {
        from("seda:" + App.ID.GET_MODULES.toString())
                .routeId(App.ID.GET_MODULES.toString())
                .threads().executorServiceRef("sandataAuthThreadPool")
                .beanRef("authenticationService", "getApplicationModules")
                .beanRef("formatTransformer", "toResponse");
    }
}
