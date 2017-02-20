package com.sandata.lab.security.auth.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.security.auth.utils.constants.App;

public class GetRolesRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from("seda:" + App.ID.GET_ROLES.toString())
                .routeId(App.ID.GET_ROLES.toString())
                .threads().executorServiceRef("sandataAuthThreadPool")
                .beanRef("authenticationService", "getRoles")
                .beanRef("formatTransformer", "toResponse");
    }
}
