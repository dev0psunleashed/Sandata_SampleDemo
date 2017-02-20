package com.sandata.lab.security.auth.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.security.auth.utils.constants.App;

/**
 * <p>UserExistsForBsnEntIdRoute.java</p>
 * <p>Date: 6/7/2016</p>
 * <p>Time: 1:35 PM</p>
 *
 * @author jasonscott
 */
public class UserExistsForBsnEntIdRoute extends AbstractRouteBuilder {
    @Override
    protected void buildRoute() {
        from("seda:" + App.ID.USER_EXISTS_FOR_BSN_ENT_ID.toString())
                .routeId(App.ID.USER_EXISTS_FOR_BSN_ENT_ID.toString())
                .threads().executorServiceRef("sandataAuthThreadPool")
                .beanRef("authenticationService", "userExistsForBsnEntId")
                .beanRef("formatTransformer", "toResponse");
    }
}
