package com.sandata.imports.schedules.impl;

import com.sandata.imports.schedules.utils.log.ScheduleSFTPImportLogger;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.error.*;
import com.sandata.lab.data.model.error.Error;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.data.model.schedule.Schedule;
import com.sandata.lab.data.model.service.ServiceStatus;
import com.sandata.lab.wcf.intake.api.IntakeService;
import org.apache.camel.Exchange;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class ScheduleImportService
{
    SandataLogger logger = ScheduleSFTPImportLogger.CreateLogger();

    IntakeService intakeService;

    public void setIntakeService(IntakeService intakeService)
    {
        this.intakeService = intakeService;
    }



    public void sendSchedulestoIntakeService(final Exchange exchange) throws Exception
    {
        try
        {
            List<Schedule> scheduleList = (ArrayList<Schedule>) exchange.getIn().getBody();
            submitSchedules(scheduleList, exchange);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage());
        }
    }

    private void submitSchedules(List<Schedule> scheduleList, Exchange exchange) throws Exception
    {
       Response response = intakeService.insertSchedules(scheduleList);

       if(response.getStatus() == ServiceStatus.FAILED)
       {


           ArrayList<String> errorMessages = new ArrayList<>();
           ArrayList<Error> errors = (ArrayList<Error>)response.getData();

           logger.info(String.format("%d errors were returned on schedule import",errors.size()));

           for(Error err : errors)
           {
               if(err != null)
               {
                   errorMessages.add(err.getMessage());
               }
           }

           logger.info(response.getErrorMessage());

           exchange.getIn().setBody(StringUtils.join(errorMessages,"\n"));
           exchange.getIn().setHeader("requestStatus", "Error");

       }
        else if(response.getStatus() == ServiceStatus.SUCCESS)
       {
           logger.info("Successfully exported schedules.");


           exchange.getIn().setHeader("requestStatus", "Success");
       }
    }

}
