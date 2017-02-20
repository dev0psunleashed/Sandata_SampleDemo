package com.sandata.lab.security.auth.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.security.auth.utils.constants.App;

/**
 * <p/>
 *
 * @author Ralph Sylvain
 */
public class RefreshAccessTokenRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from("seda:" + App.ID.GET_REFRESH_TOKEN.toString())
                .routeId(App.ID.GET_REFRESH_TOKEN.toString())
                .threads().executorServiceRef("sandataAuthThreadPool")
                .beanRef("authenticationService", "refreshToken")
                .beanRef("formatTransformer", "toResponse");
    }
}
