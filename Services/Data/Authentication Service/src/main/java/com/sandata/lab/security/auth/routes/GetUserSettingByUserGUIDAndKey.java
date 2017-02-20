package com.sandata.lab.security.auth.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.security.auth.utils.constants.App;

/**
 * Created by vuto on 7/4/2016.
 */
public class GetUserSettingByUserGUIDAndKey extends AbstractRouteBuilder {
    @Override
    protected void buildRoute() {
        from("seda:" + App.ID.GET_USER_SETTING_BY_USER_GUID_AND_KEY.toString())
                .routeId(App.ID.GET_USER_SETTING_BY_USER_GUID_AND_KEY.toString())
                .threads().executorServiceRef("sandataAuthThreadPool")
                .beanRef("userService", "getUserSettingByUserGUIDAndKey")
                .beanRef("formatTransformer", "toResponse");
    }
}