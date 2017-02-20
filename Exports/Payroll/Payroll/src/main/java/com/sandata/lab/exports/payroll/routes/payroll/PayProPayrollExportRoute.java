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
import com.sandata.lab.exports.payroll.model.paypro.PayPro;
import com.sandata.lab.exports.payroll.routes.aggregators.BsnEntAggregator;
import com.sandata.lab.exports.payroll.routes.aggregators.BsnEntLocationAggregator;
import com.sandata.lab.exports.payroll.routes.aggregators.PayrollOutputAggregator;
import com.sandata.lab.exports.payroll.routes.aggregators.StringBodyAggregator;
import com.sandata.lab.exports.payroll.utils.constants.App;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.PropertyInject;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.camel.processor.idempotent.MemoryIdempotentRepository;

import javax.xml.bind.JAXBContext;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p/>
 *
 * @author Ralph Sylvain
 */

public class PayProPayrollExportRoute extends AbstractRouteBuilder {

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

        JaxbDataFormat jaxb = new JaxbDataFormat();

        try {

            jaxb.setContext(JAXBContext.newInstance(PayPro.class));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        from(Messaging.Names.COMPONENT_TYPE_SEDA + App.ID.PAYPRO_PAYROLL_EXPORT_ROUTE.toString())
                .routeId(App.ID.PAYPRO_PAYROLL_EXPORT_ROUTE.toString())
              /*  .onException(Exception.class)
                .log("${exception}")
                .asyncDelayedRedelivery()
                .redeliveryDelay(redeliveryDelay)
                .maximumRedeliveries(maximumRedeliveries)
                .end()*/
               /* .idempotentConsumer(header("bsnEntID"), MemoryIdempotentRepository.memoryIdempotentRepository(1000)
                ).skipDuplicate(true)*/
                .enrich(Messaging.Names.COMPONENT_TYPE_QUEUE + "SANDATA-GET-BSN-ENT?disableTimeToLive=true", bsnEntAggregationStrategy())
                .log("Starting payroll export route ...")
                .beanRef("dataService", "getPayRollEndDate")
                .enrich(Messaging.Names.COMPONENT_TYPE_QUEUE + "SANDATA_GET_PAYROLL_OUT_FOR_PERIOD?disableTimeToLive=true", payrollOutputAggregator())
                .choice()
                    .when(simple("${body.size()} > 0"))
                        .beanRef("prExportTransformer", "prOutPutToPayProTime")
                        .setHeader(Exchange.FILE_NAME, simple("${header.bsnEntInfo[vendorParentBEID]}_${header.bsnEntInfo[vendorBEID]}_Time_${date:now:MM.dd.yyyy_HH_mm_ss}.xml"))
                        .split().tokenize("\n", 1000).streaming()
                        .aggregate(header(Exchange.FILE_NAME), new StringBodyAggregator())
                        .ignoreInvalidCorrelationKeys()
                        .completionInterval(2000L)
                        .eagerCheckCompletion()
                        .to(destination + "?fileExist=Append")
                        .end()
                        .setBody(header("prOutputSks"))
                        .enrich(Messaging.Names.COMPONENT_TYPE_QUEUE + "PAYROLL-SET-EXPORT-DATE")
                .end()

                .log("Completed the payroll export route");
    }

    private AggregationStrategy bsnEntAggregationStrategy() {
        return new BsnEntAggregator();
    }

    private AggregationStrategy payrollOutputAggregator() {
        return new PayrollOutputAggregator();
    }

}
