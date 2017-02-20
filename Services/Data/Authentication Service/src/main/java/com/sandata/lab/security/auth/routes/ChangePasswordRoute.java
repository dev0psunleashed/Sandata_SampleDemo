package com.sandata.lab.security.auth.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.security.auth.utils.constants.App;

public class ChangePasswordRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from("seda:" + App.ID.CHANGE_USER_PASSWORD.toString())
                .routeId(App.ID.CHANGE_USER_PASSWORD.toString())
                .threads().executorServiceRef("sandataAuthThreadPool")
                .beanRef("authenticationService", "changePassword")
                .beanRef("formatTransformer", "toResponse");
    }
}
