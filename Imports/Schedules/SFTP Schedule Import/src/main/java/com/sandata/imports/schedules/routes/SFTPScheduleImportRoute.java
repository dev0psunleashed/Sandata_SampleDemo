package com.sandata.imports.schedules.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.imports.schedules.utils.constants.App;
import org.apache.camel.Exchange;
import org.apache.camel.dataformat.beanio.BeanIODataFormat;
import org.apache.camel.routepolicy.quartz.CronScheduledRoutePolicy;
import org.apache.camel.spi.DataFormat;

public class SFTPScheduleImportRoute extends AbstractRouteBuilder
{

    private String sourcePath;
    private String destinationPath;
    private String ftpChron;

    DataFormat scheduleDataFormat = new BeanIODataFormat("com/sandata/lab/data/model/mapping/beanio/scheduleBeanIOMapping.xml", "scheduleFile");


    /*
    *To DO
    * Settings to come from Config Engine
     */
    private void setRouteSettings()
    {
        sourcePath = "sftp://sansftp26@Rd-lab-lsftp01/accounts?password=san26hpd";
        destinationPath = "sftp://sansftp26@Rd-lab-lsftp01/${file:parent}/.error?password=san26hpd";
        ftpChron = "*/3 * * * * ?";
    }

    @Override
    protected final void buildRoute()
    {
        setRouteSettings();

        CronScheduledRoutePolicy sftpRoutePolicy = new CronScheduledRoutePolicy();
        sftpRoutePolicy.setRouteStartTime(ftpChron);

        from(sourcePath + "&recursive=true&idempotentRepository=#idempotentFileStore&idempotent=true&consumer.delay=10s&move=.processed&idempotentKey=${file:modified}")
                .routeId(App.ID.SFTP_SCHEDULE_IMPORT_ROUTE.toString()).routePolicy(sftpRoutePolicy)
                .onException(Exception.class)
                    .asyncDelayedRedelivery()
                .end()
                .log(App.ID.SFTP_SCHEDULE_IMPORT_ROUTE.toString() + ": *** Starting Route ***")
                .unmarshal(scheduleDataFormat)
                .beanRef("scheduleImportService", "sendSchedulestoIntakeService")
                .choice()
                    .when(header("requestStatus").isEqualTo("Error"))
                        .log(App.ID.SFTP_SCHEDULE_IMPORT_ROUTE.toString() + ": *** Creating Error File ***")
                        .setHeader(Exchange.FILE_NAME, simple("error_${date:now:yyyy.MM.dd_HH_mm_ss}.csv"))
                        .recipientList(simple(destinationPath))
                .endChoice()
                .log(App.ID.SFTP_SCHEDULE_IMPORT_ROUTE.toString() + ": *** Route completed successfully ***");

    }
}
