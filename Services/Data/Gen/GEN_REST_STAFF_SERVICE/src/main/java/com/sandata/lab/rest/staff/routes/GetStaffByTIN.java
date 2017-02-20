package com.sandata.lab.rest.staff.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rest.staff.utils.constants.App;


public class GetStaffByTIN extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.SANDATA_GET_STAFF_BY_TIN.toString())
                .routeId(App.ID.SANDATA_GET_STAFF_BY_TIN.toString())
                .threads().executorServiceRef("staffRestThreadPool")
                .beanRef("osgiDataService", "getStaffIDByTIN");
    }
}
