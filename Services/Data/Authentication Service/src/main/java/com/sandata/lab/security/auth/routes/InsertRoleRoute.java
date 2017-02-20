package com.sandata.lab.security.auth.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.security.auth.utils.constants.App;

public class InsertRoleRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from("seda:" + App.ID.INSERT_ROLE.toString())
                .routeId(App.ID.INSERT_ROLE.toString())
                .threads().executorServiceRef("sandataAuthThreadPool")
                .beanRef("authenticationService", "insertUpdateRole")
                .beanRef("formatTransformer", "toResponse");
    }
}
