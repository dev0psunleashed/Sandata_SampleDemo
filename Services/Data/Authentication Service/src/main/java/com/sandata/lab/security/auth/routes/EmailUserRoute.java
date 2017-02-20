package com.sandata.lab.security.auth.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.security.auth.utils.constants.App;

public class EmailUserRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from("seda:" + App.ID.EMAIL_USER.toString())
                .routeId(App.ID.EMAIL_USER.toString())
                .threads().executorServiceRef("sandataAuthThreadPool")
                .beanRef("authenticationService", "emailUser")
                .beanRef("formatTransformer", "toResponse");
    }
}
