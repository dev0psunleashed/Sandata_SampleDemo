package com.sandata.lab.security.auth.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.security.auth.utils.constants.App;

public class UpdateRoleUsers extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_SEDA + App.ID.UPDATE_ROLE_USERS.toString())
                .routeId(App.ID.UPDATE_ROLE_USERS.toString())
                .threads().executorServiceRef("sandataAuthThreadPool")
                .beanRef("authenticationService", "updateRoleUsers")
                .beanRef("formatTransformer", "toResponse");
    }
}
