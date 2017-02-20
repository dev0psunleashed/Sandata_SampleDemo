package com.sandata.export.schedules.impl;

import com.sandata.export.schedules.BaseTestSupport;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.data.model.schedule.Schedule;
import com.sandata.lab.data.model.service.ServiceStatus;
import com.sandata.lab.data.model.staff.Staff;
import com.sandata.lab.wcf.export.api.ExportService;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScheduleExportServiceTest extends BaseTestSupport
{
    private ScheduleExportService scheduleExportService;

    @Mocked(stubOutClassInitialization = true)
    private ExportService exportService;


    @Before
    public void setup()
    {
        scheduleExportService = new ScheduleExportService();

        scheduleExportService.setExportService(exportService);
    }

    @Override
    protected void onSetup() throws IOException {

    }

    @Test
    public void when_schedule_export_service_is_called_and_schedules_are_returned() throws Exception
    {
        final int agencyId = 123;
        final Date startDate = new Date();
        final Date endDate = new Date();

        Schedule schedule = new Schedule();


        List<Schedule> scheduleList = new ArrayList<>();
        scheduleList.add(schedule);

        ScheduleExportService.AgencySettings settings = new ScheduleExportService.AgencySettings();

        settings.agencyId = agencyId;
        settings.scheduleStart = startDate;
        settings.scheduleEnd = endDate;

        final Response response = new Response();
        response.setStatus(ServiceStatus.SUCCESS);
        response.setData(scheduleList);

        new Expectations()
        {{ // an "expectation block"


            // Record an expectation, with a given value to be returned:
            exportService.exportSchedule(agencyId,startDate,endDate); result = response;

        }};

        exchange.getIn().setBody(settings);

        scheduleExportService.exportSchedules(exchange);

        List<Schedule> responseScheduleList = (ArrayList) exchange.getIn().getBody();

        assertEquals(scheduleList, responseScheduleList);
    }

    @Test(expected = SandataRuntimeException.class)
    public void when_schedule_export_service_is_called_and_response_staus_is_error() throws Exception
    {
        final int agencyId = 123;
        final Date startDate = new Date();
        final Date endDate = new Date();

        ScheduleExportService.AgencySettings settings = new ScheduleExportService.AgencySettings();

        settings.agencyId = agencyId;
        settings.scheduleStart = startDate;
        settings.scheduleEnd = endDate;

        final Response response = new Response();
        response.setStatus(ServiceStatus.FAILED);

        new Expectations()
        {{ // an "expectation block"


                // Record an expectation, with a given value to be returned:
                exportService.exportSchedule(agencyId,startDate,endDate); result = response;

            }};

        exchange.getIn().setBody(settings);

        scheduleExportService.exportSchedules(exchange);
    }


}
