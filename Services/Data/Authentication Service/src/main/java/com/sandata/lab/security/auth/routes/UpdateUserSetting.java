package com.sandata.lab.security.auth.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.security.auth.utils.constants.App;

/**
 * Created by vuto on 7/4/2016.
 */
public class UpdateUserSetting extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from("seda:" + App.ID.UPDATE_USER_SETTING.toString())
                .routeId(App.ID.UPDATE_USER_SETTING.toString())
                .threads().executorServiceRef("sandataAuthThreadPool")
                .beanRef("userService", "updateUserSetting")
                .beanRef("formatTransformer", "toResponse");
    }
}
