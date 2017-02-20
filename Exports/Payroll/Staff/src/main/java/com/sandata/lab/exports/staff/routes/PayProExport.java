package com.sandata.lab.exports.staff.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.exports.staff.model.PayPro;
import com.sandata.lab.exports.staff.aggregators.StaffAggregator;
import com.sandata.lab.exports.staff.aggregators.BsnEntIdAggregator;
import com.sandata.lab.exports.staff.aggregators.StringBodyAggregator;
import com.sandata.lab.exports.staff.utils.constants.App;
import org.apache.camel.Exchange;
import org.apache.camel.PropertyInject;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import javax.xml.bind.JAXBContext;

public class PayProExport extends AbstractRouteBuilder {


    @PropertyInject("{{paypro.chron}}")
    private String payrollChron = "* */59 * * * *";


    @PropertyInject("{{paypro.name}}")
    private String payProName = "PayPro";

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


        from(Messaging.Names.COMPONENT_TYPE_SEDA + App.ID.STAFF_PAYPRO_SCHEDULE_ROUTE.toString())
                .routeId(App.ID.STAFF_PAYPRO_SCHEDULE_ROUTE.toString())
                .onException(Exception.class)
                .log("${exception}")
                .asyncDelayedRedelivery()
                .redeliveryDelay(redeliveryDelay)
                .maximumRedeliveries(maximumRedeliveries)
                .end()
                .setHeader("vendorName", simple(payProName))
                .enrich(Messaging.Names.COMPONENT_TYPE_QUEUE + "SANDATA-GET-BSN-ENT-IDS-FOR-PAYROLL-BY-VENDOR-ID", bsnEntIdAggregationStrategy())
                .split(body())
                .setHeader("bsnEntID", simple("${body[bsnEntID]}"))
                .setHeader("bsnEntInfo", simple("${body}"))
                .to(Messaging.Names.COMPONENT_TYPE_SEDA + App.ID.STAFF_PAYPRO_EXPORT_ROUTE.toString());

        JaxbDataFormat jaxb = new JaxbDataFormat();

        try {


            jaxb.setContext(JAXBContext.newInstance(PayPro.class));

            from(Messaging.Names.COMPONENT_TYPE_SEDA + App.ID.STAFF_PAYPRO_EXPORT_ROUTE.toString())
                    .routeId(App.ID.STAFF_PAYPRO_EXPORT_ROUTE.toString())
                    .threads().executorServiceRef("payrollRestThreadPool")
                    .enrich(Messaging.Names.COMPONENT_TYPE_QUEUE + "SANDATA-GET-STAFF-BY-BUSINESS-ENTITY-ID?requestTimeout=" + timeOut, staffAggregationStrategy())
                    .setHeader(Exchange.FILE_NAME, simple("${header.bsnEntInfo[vendorParentBEID]}_${header.bsnEntInfo[vendorBEID]}_EMP_${date:now:MM.dd.yyyy_HH_mm_ss}.xml"))
                    .beanRef("staffTransformer", "staffToPayProStaff")
                    .marshal(jaxb)
                    .split().tokenize("\n",1000).streaming()
                        .aggregate(header(Exchange.FILE_NAME), new StringBodyAggregator())
                        .ignoreInvalidCorrelationKeys()
                        .completionInterval(2000L)
                        .eagerCheckCompletion()
                        .to(destination + "?fileExist=Append")
                    .end()
                    .log("Completed the payroll export route");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private AggregationStrategy bsnEntIdAggregationStrategy() {
        return new BsnEntIdAggregator();
    }

    private AggregationStrategy staffAggregationStrategy() {
        return new StaffAggregator();
    }
}
