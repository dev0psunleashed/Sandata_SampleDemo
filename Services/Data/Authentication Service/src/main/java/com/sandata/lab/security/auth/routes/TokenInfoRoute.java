package com.sandata.lab.security.auth.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.security.auth.utils.constants.App;

/**
 * <p/>
 *
 * @author Ralph Sylvain
 */
public class TokenInfoRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from("seda:" + App.ID.GET_TOKEN_INFO.toString())
                .routeId(App.ID.GET_TOKEN_INFO.toString())
                .threads().executorServiceRef("sandataAuthThreadPool")
                .beanRef("authenticationService", "getTokenInfo")
                .beanRef("formatTransformer", "toResponse");
    }
}
