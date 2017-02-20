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
import com.sandata.lab.exports.payroll.routes.aggregators.BsnEntAggregator;
import com.sandata.lab.exports.payroll.routes.aggregators.BsnEntConfigsAggregator;
import com.sandata.lab.exports.payroll.routes.aggregators.BsnEntLocationAggregator;
import com.sandata.lab.exports.payroll.utils.constants.App;
import org.apache.camel.PropertyInject;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.camel.processor.idempotent.MemoryIdempotentRepository;


/**
 * <p/>
 *
 * @author Ralph Sylvain
 */

public class PayrollManualTriggerRoute extends AbstractRouteBuilder {

    @PropertyInject("{{paypro.destination}}")
    private String destination = "file:target/data";

    @PropertyInject("{{jms.timeout}}")
    private String timeOut = "3600s";

    @PropertyInject("{{redeliverydelay}}")
    private int redeliveryDelay = 10000;

    @PropertyInject("{{maximumRedeliveries}}")
    private int maximumRedeliveries = 0;

    @Override
    protected void buildRoute() {


        from(Messaging.Names.COMPONENT_TYPE_QUEUE + App.ID.PAYROLL_MANUAL_ROUTE.toString())
                .routeId(App.ID.PAYROLL_MANUAL_ROUTE.toString())
                .onException(Exception.class)
                .log("${exception}")
                .asyncDelayedRedelivery()
                .redeliveryDelay(redeliveryDelay)
                .maximumRedeliveries(maximumRedeliveries)
                .end()
             /*   .idempotentConsumer(header("bsnEntID"), MemoryIdempotentRepository.memoryIdempotentRepository(1000)
                ).skipDuplicate(true) */
                .enrich(Messaging.Names.COMPONENT_TYPE_QUEUE + "SANDATA_GET_BSN_ENT_XWALK_BY_ID", bsnEntLocationAggregationStrategy())
                .enrich(Messaging.Names.COMPONENT_TYPE_QUEUE + "SANDATA-GET-TENANT-CONFIGS?disableTimeToLive=true", bsnEntConfigsAggregationStrategy())
                 //Wait for response to prevent a duplicate exchange from processing
                .enrich(Messaging.Names.COMPONENT_TYPE_SEDA + App.ID.PAYPRO_PAYROLL_EXPORT_ROUTE.toString())
                .log("Completed the payroll manual trigger");

        from(Messaging.Names.COMPONENT_TYPE_SEDA + App.ID.PAYROLL_LOCATION_ROUTE.toString())
                .routeId(App.ID.PAYROLL_LOCATION_ROUTE.toString())
                .onException(Exception.class)
                .log("${exception}")
                .asyncDelayedRedelivery()
                .redeliveryDelay(redeliveryDelay)
                .maximumRedeliveries(maximumRedeliveries)
                .end()
                .enrich(Messaging.Names.COMPONENT_TYPE_QUEUE + "SANDATA-GET-TENANT-CONFIG_BY_KEY", bsnEntLocationAggregationStrategy())
                .choice()
                    .when(header("processLocationsPayroll").isEqualTo(true))
                        .log("Processing locations for payroll...")
                        .enrich(Messaging.Names.COMPONENT_TYPE_QUEUE + "SANDATA-GET-BSN-ENT-LOCATION-IDS")
                        .choice()
                            .when(body().isNotNull())
                            .split(body()).setHeader("bsnEntID", body())
                            .to(Messaging.Names.COMPONENT_TYPE_VM + App.ID.PAYROLL_MANUAL_ROUTE.toString())
                        .end()
                .end();

    }

    private AggregationStrategy bsnEntLocationAggregationStrategy() {
        return new BsnEntLocationAggregator();
    }

    private AggregationStrategy bsnEntConfigsAggregationStrategy() {
        return new BsnEntConfigsAggregator();
    }
}
