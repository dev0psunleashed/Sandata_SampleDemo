package com.sandata.lab.rest.payroll.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rest.payroll.utils.constants.App;


public class Insert extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.PAYROLL_INSERT.toString())
                .routeId(App.ID.PAYROLL_INSERT.toString())
                .threads().executorServiceRef("payrollRestThreadPool")
                .beanRef("restDataService", "insert");
    }
}
