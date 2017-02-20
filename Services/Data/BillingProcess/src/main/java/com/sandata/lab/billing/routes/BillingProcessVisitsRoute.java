package com.sandata.lab.billing.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.data.model.constants.billing.Billing;

/**
 * Route processes visits and creates/deletes billing records accordingly.
 * <p/>
 *
 * @author David Rutgos
 */
public class BillingProcessVisitsRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + Billing.EVENT.BILLING_PROCESS_VISITS.toString())
                .routeId(Billing.EVENT.BILLING_PROCESS_VISITS.toString())
                .threads().executorServiceRef("billingProcessThreadPool")
                .choice()
                    // Since the Visit was either inserted or updated, check to see if it was approved and payable
                    .when().simple("${in.header.operationName} contains '_updateVisit_' || ${in.header.operationName} contains '_insertVisit_'")
                        .to("direct:" + Billing.EVENT.BILLING_PROCESS_VISITS.toString() + "_Handler")
                    .otherwise()
                        .log("BillingProcessVisitsRoute: Unhandled request!")
                    .end()
                .log("BillingProcessVisitsRoute: Complete!")
                .end();

        from("direct:" + Billing.EVENT.BILLING_PROCESS_VISITS.toString() + "_Handler")
                .routeId(Billing.EVENT.BILLING_PROCESS_VISITS.toString() + "_Handler")
                .beanRef("processVisit", "handler")
                .log("BillingProcessVisitsRoute: processVisit: handler: Complete!")
                .end();

        /*from("direct:" + Billing.EVENT.PAYROLL_PROCESS_VISITS.toString() + "_Handler")
                .routeId(Billing.EVENT.PAYROLL_PROCESS_VISITS.toString() + "_Handler")
                .beanRef("processVisit", "handler")
                .choice()
                    .when().simple("${body} is 'com.sandata.lab.data.model.billing.TimeSheetDetailRequest'")
                        .to(Messaging.Names.COMPONENT_TYPE_QUEUE + TimeSheet.EVENT.TIMESHEET_DETAIL_PROCESS.toString())
                    .otherwise()
                        .log(Billing.EVENT.PAYROLL_PROCESS_VISITS.toString() + "_Handler: " + ": Not Processing TimeSheets!")
                    .end()
                .log(Billing.EVENT.PAYROLL_PROCESS_VISITS.toString() + "_Handler: Complete!")
                .end();*/
    }
}
