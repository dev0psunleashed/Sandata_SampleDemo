package com.sandata.lab.security.auth.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.security.auth.utils.constants.App;

public class UpdateUserRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_SEDA + App.ID.UPDATE_USER.toString())
                .routeId(App.ID.UPDATE_USER.toString())
                .threads().executorServiceRef("sandataAuthThreadPool")
                .beanRef("authenticationService", "updateUser")
                .beanRef("formatTransformer", "toResponse");
    }
}
