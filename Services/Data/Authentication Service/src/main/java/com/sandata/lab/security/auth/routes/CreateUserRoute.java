package com.sandata.lab.security.auth.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.security.auth.utils.constants.App;

public class CreateUserRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from("seda:" + App.ID.CREATE_USER.toString())
                .routeId(App.ID.CREATE_USER.toString())
                .threads().executorServiceRef("sandataAuthThreadPool")
                .beanRef("authenticationService", "createUser")
                .beanRef("formatTransformer", "toResponse");
    }
}
