package com.sandata.lab.security.auth.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.security.auth.utils.constants.App;

public class DeleteRoleRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from("seda:" + App.ID.DELETE_ROLE.toString())
                .routeId(App.ID.DELETE_ROLE.toString())
                .threads().executorServiceRef("sandataAuthThreadPool")
                .beanRef("authenticationService", "deleteRole")
                .beanRef("formatTransformer", "toResponse");
    }
}
