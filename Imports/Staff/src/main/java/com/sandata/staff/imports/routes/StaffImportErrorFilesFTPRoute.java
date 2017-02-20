package com.sandata.staff.imports.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.staff.imports.utils.constants.App;
import com.sandata.staff.imports.utils.constants.Messaging;
import org.apache.camel.Exchange;

public class StaffImportErrorFilesFTPRoute extends AbstractRouteBuilder
{

    private String destinationPath = "file:target/data";

    @Override
    protected final void buildRoute()
    {
        from(Messaging.Names.COMPONENT_TYPE_SEDA
                + App.ID.FTP_STAFF_IMPORT_ERROR_FILES.toString())
                .routeId(App.ID.FTP_STAFF_IMPORT_ERROR_FILES.toString())
                .onException(Exception.class)
                    .asyncDelayedRedelivery()
                .end()
                .log("${body}")
                .setHeader(Exchange.FILE_NAME, simple("error_${date:now:yyyy.MM.dd_HH_mm_ss}.csv"))
                .to(destinationPath + "?charset=utf-8&autoCreate=true")
                .log(App.ID.FTP_STAFF_IMPORT_ERROR_FILES.toString() + ": *** Route completed successfully ***");
    }
}
