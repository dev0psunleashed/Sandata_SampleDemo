/*
 * Copyright (c) 2015. Sandata Technologies, LLC
 * 26 Harbor Park Drive, Port Washington, NY 11050, 800-544-7263
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Sandata Technologies, LLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with
 * Sandata.
 */

package com.sandata.lab.exports.payroll.routes.payroll;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.exports.payroll.routes.aggregators.BsnEntConfigsAggregator;
import com.sandata.lab.exports.payroll.routes.aggregators.BsnEntIdAggregator;
import com.sandata.lab.exports.payroll.routes.payroll.dynamic.PayrollRouteManager;
import com.sandata.lab.exports.payroll.utils.constants.App;
import org.apache.camel.PropertyInject;
import org.apache.camel.processor.aggregate.AggregationStrategy;

/**
 * <p/>
 *
 * @author Ralph Sylvain
 */

public class AgencyPayrollSchedulerRoute extends AbstractRouteBuilder {

    @PropertyInject("{{agency.settings.chron}}")
    private String settingsChron = "*/10 * * * * ?";

    @PropertyInject("{{jms.timeout}}")
    private String timeOut = "3600s";

    @Override
    protected void buildRoute() {
/*
        from("quartz2://payroll//payrollScheduler?cron=" + settingsChron)
                .routeId(App.ID.AGENCY_PAYROLL_SCHEDULER_ROUTE.toString())
                .log("Starting route ...")
               .enrich(Messaging.Names.COMPONENT_TYPE_QUEUE + "SANDATA-GET-BSN-ENT-IDS-FOR-PAYROLL?disableTimeToLive=true", bsnEntIdAggregationStrategy())
                .choice()
                    .when(body().isNotNull())
                        .enrich(Messaging.Names.COMPONENT_TYPE_QUEUE + "SANDATA-GET-TENANT-CONFIGS?disableTimeToLive=true", bsnEntConfigsAggregationStrategy())
                        .split().body()
                        .bean(PayrollRouteManager.class, "updateRoutes")
                .endChoice()
               .log("Completed updating the payroll schedules");
               */
    }

    private AggregationStrategy bsnEntIdAggregationStrategy() {
        return new BsnEntIdAggregator();
    }

    private AggregationStrategy bsnEntConfigsAggregationStrategy() {
        return new BsnEntConfigsAggregator();
    }
}
