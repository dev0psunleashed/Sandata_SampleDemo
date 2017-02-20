package com.sandata.lab.security.auth.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.security.auth.utils.constants.App;

public class DeleteUserRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from("seda:" + App.ID.DELETE_USER.toString())
                .routeId(App.ID.DELETE_USER.toString())
                .threads().executorServiceRef("sandataAuthThreadPool")
                .beanRef("authenticationService", "deleteUser")
                .beanRef("formatTransformer", "toResponse");
    }
}
