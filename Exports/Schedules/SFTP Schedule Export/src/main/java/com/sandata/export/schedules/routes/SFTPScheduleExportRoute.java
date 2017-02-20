package com.sandata.export.schedules.routes;

import com.sandata.export.schedules.impl.ScheduleExportService;
import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.export.schedules.utils.constants.App;
import com.sandata.export.schedules.utils.constants.Messaging;
import org.apache.camel.Exchange;
import org.apache.camel.routepolicy.quartz.CronScheduledRoutePolicy;
import org.apache.camel.dataformat.beanio.BeanIODataFormat;
import org.apache.camel.spi.DataFormat;


import java.util.Date;

public class SFTPScheduleExportRoute extends AbstractRouteBuilder
{

    private String destinationPath;
    private String ftpChron;

    DataFormat scheduleDataFormat = new BeanIODataFormat("com/sandata/lab/data/model/mapping/beanio/scheduleBeanIOMapping.xml", "scheduleFile");

    /*
    *To DO
    * Settings to come from Config Engine
     */
    public void setRouteSettings()
    {
        destinationPath = "sftp://sansftp26@Rd-lab-lsftp01?password=san26hpd";
        ftpChron = "*/3 * * * * ?";
    }





    @Override
    protected final void buildRoute()
    {
        setRouteSettings();



        from("quartz://schedule?cron=" + ftpChron)
                .to(Messaging.Names.COMPONENT_TYPE_SEDA
                        + App.ID.SFTP_EXPORT_SCHEDULES.toString());
        ;

        from(Messaging.Names.COMPONENT_TYPE_SEDA
                + App.ID.SFTP_EXPORT_SCHEDULES.toString())
                .routeId(App.ID.SFTP_EXPORT_SCHEDULES.toString())
                .log(App.ID.SFTP_EXPORT_SCHEDULES.toString() + ": *** Starting Route ***")
                .beanRef("scheduleExportService", "setAgencySettings")
                .beanRef("scheduleExportService", "exportSchedules")
                .choice()
                    .when(simple("${header.requestStatus} == 'Success'"))
                        .marshal(scheduleDataFormat)
                        .to(destinationPath)
                    .otherwise()
                        .log("Schedule list is empty. File not created. Ending Route.")
                .endChoice()
                .log(App.ID.SFTP_EXPORT_SCHEDULES.toString() + ": *** Route completed successfully ***");
    }
}
