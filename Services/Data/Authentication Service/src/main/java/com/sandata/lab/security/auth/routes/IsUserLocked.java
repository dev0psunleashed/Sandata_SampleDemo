package com.sandata.lab.security.auth.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.security.auth.utils.constants.App;

public class IsUserLocked extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from("seda:" + App.ID.IS_USER_LOCKED.toString())
                .routeId(App.ID.IS_USER_LOCKED.toString())
                .threads().executorServiceRef("sandataAuthThreadPool")
                .beanRef("authenticationService", "isUserLocked")
                .beanRef("formatTransformer", "toResponse");
    }
}
