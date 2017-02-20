package com.sandata.lab.rest.timesheet.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.data.model.constants.TimeSheet;

/**
 * Handles request that come in from payroll process to verify or create timesheet entities.
 * <p/>
 *
 * @author David Rutgos
 */
public class TimeSheetDetailProcessRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + TimeSheet.EVENT.TIMESHEET_DETAIL_PROCESS.toString())
                .routeId(TimeSheet.EVENT.TIMESHEET_DETAIL_PROCESS.toString())
                .threads().executorServiceRef("timesheetRestThreadPool")
                .beanRef("restDataService", "timeSheetDetailProcess")
                .log("TimeSheetDetailProcessRoute: Complete!");
    }
}
