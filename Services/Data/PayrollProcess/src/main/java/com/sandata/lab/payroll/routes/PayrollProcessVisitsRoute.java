package com.sandata.lab.payroll.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.data.model.constants.TimeSheet;
import com.sandata.lab.data.model.constants.payroll.Payroll;

/**
 * Route processes visits and creates/deletes timesheets accordingly.
 * <p/>
 *
 * @author David Rutgos
 */
public class PayrollProcessVisitsRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + Payroll.EVENT.PAYROLL_PROCESS_VISITS.toString())
                .routeId(Payroll.EVENT.PAYROLL_PROCESS_VISITS.toString())
                .threads().executorServiceRef("payrollProcessThreadPool")
                .choice()
                    // Since the Visit was either inserted or updated, check to see if it was approved and payable
                    .when().simple("${in.header.operationName} contains '_updateVisit_' || ${in.header.operationName} contains '_insertVisit_'")
                        .to("direct:" + Payroll.EVENT.PAYROLL_PROCESS_VISITS.toString() + "_Handler")
                    .otherwise()
                        .log("PayrollProcessVisitsRoute: Unhandled request!")
                    .end()
                .log("PayrollProcessVisitsRoute: Complete!")
                .end();

        from("direct:" + Payroll.EVENT.PAYROLL_PROCESS_VISITS.toString() + "_Handler")
                .routeId(Payroll.EVENT.PAYROLL_PROCESS_VISITS.toString() + "_Handler")
                .beanRef("processVisit", "handler")
                .choice()
                    .when().simple("${body} is 'com.sandata.lab.data.model.payroll.TimeSheetDetailRequest'")
                        .to(Messaging.Names.COMPONENT_TYPE_QUEUE + TimeSheet.EVENT.TIMESHEET_DETAIL_PROCESS.toString())
                    .otherwise()
                        .log(Payroll.EVENT.PAYROLL_PROCESS_VISITS.toString() + "_Handler: " + ": Not Processing TimeSheets!")
                    .end()
                .log(Payroll.EVENT.PAYROLL_PROCESS_VISITS.toString() + "_Handler: Complete!")
                .end();
    }
}
