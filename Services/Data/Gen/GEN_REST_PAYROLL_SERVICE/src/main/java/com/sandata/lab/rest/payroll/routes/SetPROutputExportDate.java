package com.sandata.lab.rest.payroll.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rest.payroll.utils.constants.App;


public class SetPROutputExportDate extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.PAYPRO_SET_EXPORT_DATE.toString())
                .routeId(App.ID.PAYPRO_SET_EXPORT_DATE.toString())
                .threads().executorServiceRef("payrollRestThreadPool")
                .beanRef("osgiDataService", "setPROutputExportDate");
    }
}
