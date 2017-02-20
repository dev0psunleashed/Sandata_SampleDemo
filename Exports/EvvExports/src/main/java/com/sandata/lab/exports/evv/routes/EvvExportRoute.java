package com.sandata.lab.exports.evv.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.exports.evv.model.UploadSchedules;
import com.sandata.lab.exports.evv.processors.ErrorProcessor;
import com.sandata.lab.exports.evv.processors.EvvProcessor;
import com.sandata.lab.exports.evv.processors.PropertyProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.dataformat.soap.name.TypeNameStrategy;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.apache.camel.model.dataformat.SoapJaxbDataFormat;
import org.apache.camel.dataformat.soap.name.ServiceInterfaceStrategy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 10/8/2016
 * Time: 9:02 AM
 */
public class EvvExportRoute extends AbstractRouteBuilder{
    public static Date lastFireTime;
    public final static String LASTEXPORTED = "evvLastExportDate";

    @Override
    protected void buildRoute() {
        SoapJaxbDataFormat soap = new SoapJaxbDataFormat("com.sandata.lab.exports.evv.model", new TypeNameStrategy());
        //String WS_URI="cxf://https://main-dev-evv.sandata.com/SantraxTransferService/SantraxTransfer.asmx?serviceClass=com.sandata.lab.exports.evv.model.UploadSchedules&dataFormat=MESSAGE";
        soap.setVersion("1.1");
        //soap.setNamespacePrefixRef("helper.prefix");
        from("timer://evvExportUploadSchedulesStaffAndPatients?delay={{evvExportDelay}}&period={{evvExportPeriod}}")
                .bean("evvExportRepository", "getAccountsScheduledToBeExported")
                .choice().when(body().isNotNull())
                    .split(body())
                    .to("{{scheduledEvvExportsRequest}}").endChoice()
                .otherwise()
                    .log("nothing scheduled");




        from("{{scheduledEvvExportsRequest}}")
                .process(new EvvProcessor())
                .bean("evvExportRepository", "getUploadSchedules")
                .setHeader(Exchange.CONTENT_TYPE, constant("text/xml"))
                .setHeader("SOAPAction", constant("uploadSchedules"))
                .marshal(soap).to("{{xmlLoggingService}}", "{{santraxTransferService}}");


         from("{{santraxTransferService}}")
                .to("{{ws_url}}serviceClass=com.sandata.lab.exports.evv.model.UploadSchedules&dataFormat=MESSAGE")
                 .onException(Exception.class)
                    .handled(true)
                    .process(new ErrorProcessor())
                .end()
                .unmarshal(soap)
                .to("{{xmlLoggingServiceResponse}}");



         from("{{xmlLoggingService}}")
                 .to("file://data/out/?fileName=uploadSchedules-${date:now:yyyyMMddhhmmss}.xml&charset=utf-8");

         from("{{xmlLoggingServiceResponse}}")
                    .process(new PropertyProcessor())
                    .choice().when(header("logResponse").isNotEqualTo("0"))
                        .to("file://data/log/evvexport/response/?fileName=uploadSchedulesResponse-${date:now:yyyyMMddhhmmss}.xml&charset=utf-8")
                 .otherwise().log("--response not logged--");

    }

}
