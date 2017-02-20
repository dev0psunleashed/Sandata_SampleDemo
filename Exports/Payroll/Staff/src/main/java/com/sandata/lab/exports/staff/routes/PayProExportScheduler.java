package com.sandata.lab.exports.staff.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.exports.staff.aggregators.BsnEntIdAggregator;
import com.sandata.lab.exports.staff.aggregators.StaffAggregator;
import com.sandata.lab.exports.staff.aggregators.StringBodyAggregator;
import com.sandata.lab.exports.staff.model.PayPro;
import com.sandata.lab.exports.staff.utils.constants.App;
import org.apache.camel.Exchange;
import org.apache.camel.PropertyInject;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import javax.xml.bind.JAXBContext;

public class PayProExportScheduler extends AbstractRouteBuilder {


    @PropertyInject("{{paypro.chron}}")
    private String payrollChron = "* */59 * * * *";

    @Override
    protected void buildRoute() {


        from("quartz2://payProStaffExport?cron=" + payrollChron)
                .routeId(App.ID.STAFF_PAYPRO_SCHEDULER_ROUTE.toString())
                .to(Messaging.Names.COMPONENT_TYPE_SEDA + App.ID.STAFF_PAYPRO_SCHEDULE_ROUTE.toString());


    }


}
