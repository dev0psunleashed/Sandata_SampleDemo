package com.sandata.lab.security.auth.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.security.auth.utils.constants.App;

/**
 * <p/>
 *
 * @author Ralph Sylvain
 */
public class ResetPasswordRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from("seda:" + App.ID.RESET_USER_PASSWORD.toString())
                .routeId(App.ID.RESET_USER_PASSWORD.toString())
                .threads().executorServiceRef("sandataAuthThreadPool")
                .beanRef("authenticationService", "resetPassword")
                .beanRef("formatTransformer", "toResponse");
    }
}
