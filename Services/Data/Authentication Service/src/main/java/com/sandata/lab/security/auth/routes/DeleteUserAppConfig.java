package com.sandata.lab.security.auth.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.security.auth.utils.constants.App;

public class DeleteUserAppConfig extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from("seda:" + App.ID.DELETE_USER_CONFIG.toString())
                .routeId(App.ID.DELETE_USER_CONFIG.toString())
                .threads().executorServiceRef("sandataAuthThreadPool")
                .beanRef("userService", "deleteUserAppConfig")
                .beanRef("formatTransformer", "toResponse");
    }
}
