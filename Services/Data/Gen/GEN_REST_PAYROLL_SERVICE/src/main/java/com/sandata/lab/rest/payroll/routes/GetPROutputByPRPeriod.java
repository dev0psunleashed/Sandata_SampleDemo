package com.sandata.lab.rest.payroll.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rest.payroll.utils.constants.App;


public class GetPROutputByPRPeriod extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.GET_PAYROLL_OUT_FOR_PERIOD.toString())
                .routeId(App.ID.GET_PAYROLL_OUT_FOR_PERIOD.toString())
                .threads().executorServiceRef("payrollRestThreadPool")
                .beanRef("osgiDataService", "getPayrollOutputForPayrollPeriod");
    }
}
