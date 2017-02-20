package com.sandata.lab.rest.am.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rest.am.utils.constants.App;


public class GetTenantConfigByKey extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.SANDATA_GET_TENANT_CONFIG_BY_KEY.toString())
                .routeId(App.ID.SANDATA_GET_TENANT_CONFIG_BY_KEY.toString())
                .threads().executorServiceRef("agencyManagementThreadPool")
                .beanRef("osgiDataService", "getTenantConfigsByKey");
    }
}
