package com.sandata.lab.rest.bsn_ent.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rest.bsn_ent.utils.constants.App;


public class GetBusinessEntityIDsForPayrollByVendorID extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.SANDATA_GET_BSN_ENT_IDS_FOR_PAYROLL_BY_VENDOR_ID.toString())
                .routeId(App.ID.SANDATA_GET_BSN_ENT_IDS_FOR_PAYROLL_BY_VENDOR_ID.toString())
                .threads().executorServiceRef("bsnEntRestThreadPool")
                .beanRef("osgiDataService", "getBusinessEntityIDSsForPayrollByVendorID");
    }
}
