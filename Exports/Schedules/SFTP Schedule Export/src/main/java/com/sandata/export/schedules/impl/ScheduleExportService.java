package com.sandata.export.schedules.impl;

import com.sandata.export.schedules.utils.log.ScheduleExportLogger;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.data.model.schedule.Schedule;
import com.sandata.lab.data.model.service.ServiceStatus;
import com.sandata.lab.wcf.export.api.ExportService;
import org.apache.camel.Exchange;

import java.util.Date;
import java.util.List;


public class ScheduleExportService
{
    SandataLogger logger = ScheduleExportLogger.CreateLogger();

    ExportService exportService;

    public void setExportService(ExportService exportService)
    {
        this.exportService = exportService;
    }

    /*
    To be replaced once configuration setup has been defined
     */
    public static class AgencySettings
    {
        public int agencyId;
        public Date scheduleStart;
        public Date scheduleEnd;
    }

    public void exportSchedules(final Exchange exchange) throws Exception
    {
        try
        {
            AgencySettings settings = (AgencySettings) exchange.getIn().getBody();
            getSchedules(settings, exchange);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage());
        }
    }

    private void getSchedules(AgencySettings settings, Exchange exchange) throws Exception
    {
       Response response = exportService.exportSchedule(settings.agencyId,settings.scheduleStart, settings.scheduleEnd);

       if(response.getStatus() == ServiceStatus.FAILED)
       {
           logger.info(response.getErrorMessage());
           throw new SandataRuntimeException("There was an error when attempting to export the schedules from the ExportService.");
       }
        else if(response.getStatus() == ServiceStatus.SUCCESS)
       {
           logger.info("Successfully exported schedules.");

           List<Schedule> scheduleList = (List<Schedule>)response.getData();

           exchange.getIn().setHeader("requestStatus", "Success");
           exchange.getIn().setBody(scheduleList);
       }
    }

    /*
    * Stub for config service
    * */
    public ScheduleExportService.AgencySettings settings = new ScheduleExportService.AgencySettings();
    public void setAgencySettings(final  Exchange exchange)
    {
        settings.agencyId = 123;
        settings.scheduleStart = new Date();
        settings.scheduleEnd = new Date();

        exchange.getIn().setBody(settings);
    }

}
