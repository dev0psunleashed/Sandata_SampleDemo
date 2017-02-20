package com.sandata.staff.imports.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.staff.imports.utils.constants.App;
import com.sandata.staff.imports.utils.constants.Messaging;
import org.apache.camel.routepolicy.quartz.CronScheduledRoutePolicy;

public class StaffImportFTPRoute extends AbstractRouteBuilder
{

    private String sourcePath = "file:target/data/agency";
    private String ftpChron = "*/3 * * * * ?";
    private String processedDirectory = ".processed";

    @Override
    protected final void buildRoute()
    {
        CronScheduledRoutePolicy staffImportFTPRoutePolicy = new CronScheduledRoutePolicy();
        staffImportFTPRoutePolicy.setRouteStartTime(ftpChron);

        from(sourcePath + "?move="+ processedDirectory +"&idempotentRepository=#idempotentFileStore&idempotent=true&consumer.delay=10s&idempotentKey=${file:modified}&readLock=changed&recursive=true")
                .routeId(App.ID.FTP_IMPORT_STAFF.toString()).routePolicy(staffImportFTPRoutePolicy)
                .onException(Exception.class)
                    .asyncDelayedRedelivery()
                .end()
                .log("${body}")
                .log(App.ID.FTP_IMPORT_STAFF.toString() + ": *** Route completed successfully ***");
    }
}
