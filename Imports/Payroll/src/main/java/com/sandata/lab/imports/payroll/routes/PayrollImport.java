package com.sandata.lab.imports.payroll.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.imports.payroll.model.Check;
import com.sandata.lab.imports.payroll.model.Transaction;
import com.sandata.lab.imports.payroll.routes.aggregators.BsnEntIdAggregator;
import com.sandata.lab.imports.payroll.routes.aggregators.CheckBatchAggregator;
import com.sandata.lab.imports.payroll.routes.aggregators.StaffIdAggregator;
import com.sandata.lab.imports.payroll.utils.constants.App;
import org.apache.camel.PropertyInject;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.processor.aggregate.AggregationStrategy;


import javax.xml.bind.JAXBContext;

public class PayrollImport extends AbstractRouteBuilder {


    @PropertyInject("{{import.payroll.chron}}")
    private String payrollChron = "*/3 * * * * ?";

    @PropertyInject("{{paypro.import.sftp}}")
    private String payrollSourcePath = "file:target/data";

    @PropertyInject("{{import.paypro.name}}")
    private String payProName = "PayPro";

    @Override
    protected void buildRoute() {

        JaxbDataFormat jaxb = new JaxbDataFormat();
        JaxbDataFormat jaxb2 = new JaxbDataFormat();

        try {

            jaxb.setContext(JAXBContext.newInstance(Check.class));
            jaxb2.setContext(JAXBContext.newInstance(Transaction.class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        from(payrollSourcePath + "?scheduler=quartz2&scheduler.cron=" + payrollChron)
                .routeId(App.ID.PAYROLL_IMPORT_ROUTE.toString())
                .setHeader("externalBsnEntID", xpath("/PayPro/Transaction/@CO").stringResult())
                .setHeader("vendor", simple(payProName))
                .enrich(Messaging.Names.COMPONENT_TYPE_QUEUE + "SANDATA-GET-BSN_ENT-CROSSWALK-FROM-COMPANY", bsnEntIdAggregationStrategy())
                //Stream so the entire XML doesn't need to be loaded into memory
                .split().tokenizeXML("Check").streaming()
                .unmarshal(jaxb)
                .aggregate(simple("${body.staffSSN}"), batchAggregationStrategy())
                .ignoreInvalidCorrelationKeys()
                .completionInterval(500L)
                .eagerCheckCompletion()
                .enrich(Messaging.Names.COMPONENT_TYPE_QUEUE + "SANDATA-GET-STAFF-BY-TIN", staffIdAggregationStrategy())
                .parallelProcessing()
                .beanRef("prTransformer", "checkToPRInput")
                .split(body())
                .to(Messaging.Names.COMPONENT_TYPE_QUEUE + "SANDATA-PAYROLL-INSERT");

    }

    private AggregationStrategy batchAggregationStrategy() {
        return new CheckBatchAggregator();
    }

    private AggregationStrategy bsnEntIdAggregationStrategy() {
        return new BsnEntIdAggregator();
    }

    private AggregationStrategy staffIdAggregationStrategy() {
        return new StaffIdAggregator();
    }
}
