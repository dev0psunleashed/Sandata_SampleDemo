package com.sandata.lab.security.auth.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.security.auth.utils.constants.App;

/**
 * <p/>
 *
 * @author Ralph Sylvain
 */
public class GetUserTokenRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from("seda:" + App.ID.GET_USER_TOKEN.toString())
                .routeId(App.ID.GET_USER_TOKEN.toString())
                .threads().executorServiceRef("sandataAuthThreadPool")
                .beanRef("authenticationService", "getUserToken")
                .beanRef("formatTransformer", "toResponse");
    }
}
