package com.sandata.imports.schedules.impl;

import com.sandata.imports.schedules.BaseTestSupport;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.error.*;
import com.sandata.lab.data.model.error.Error;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.data.model.schedule.Schedule;
import com.sandata.lab.data.model.service.ServiceStatus;
import com.sandata.lab.wcf.intake.api.IntakeService;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(JMockit.class)
public class ScheduleImportServiceTest extends BaseTestSupport
{
    private ScheduleImportService scheduleImportService;

    @Mocked(stubOutClassInitialization = true)
    private IntakeService intakeService;


    @Before
    public void setup()
    {
        scheduleImportService = new ScheduleImportService();

        scheduleImportService.setIntakeService(intakeService);
    }

    @Override
    protected void onSetup() throws IOException {

    }

    @Test
    public void when_schedule_import_service_is_called_and_schedules_are_successfully_imported_and_request_header_is_success() throws Exception
    {
        Schedule schedule = new Schedule();

        final List<Schedule> scheduleList = new ArrayList<>();
        scheduleList.add(schedule);

        final Response response = new Response();
        response.setStatus(ServiceStatus.SUCCESS);


        new Expectations()
        {{ // an "expectation block"
            // Record an expectation, with a given value to be returned:
            intakeService.insertSchedules(scheduleList); result = response;

        }};

        exchange.getIn().setBody(scheduleList);

        scheduleImportService.sendSchedulestoIntakeService(exchange);

        String status = (String) exchange.getIn().getHeader("requestStatus");

        assertEquals("Success", status);
    }

    @Test
    public void when_schedule_import_service_is_called_and_1_schedule_imported_and_3_failed() throws Exception
    {
        Schedule schedule = new Schedule();
        Schedule schedule1 = new Schedule();
        Schedule schedule2 = new Schedule();
        Schedule schedule3 = new Schedule();

        final List<Schedule> scheduleList = new ArrayList<>();
        scheduleList.add(schedule);
        scheduleList.add(schedule1);
        scheduleList.add(schedule2);
        scheduleList.add(schedule3);

        com.sandata.lab.data.model.error.Error error = new Error();
        error.setMessage("First schedule failed");

        com.sandata.lab.data.model.error.Error error1 = new Error();
        error1.setMessage("Second schedule failed");

        com.sandata.lab.data.model.error.Error error2 = new Error();
        error2.setMessage("Third schedule failed");

        ArrayList<Error> errors = new ArrayList<>();
        errors.add(error);
        errors.add(error1);
        errors.add(error2);


        final Response response = new Response();
        response.setStatus(ServiceStatus.FAILED);
        response.setData(errors);


        new Expectations()
        {{ // an "expectation block"
                // Record an expectation, with a given value to be returned:
                intakeService.insertSchedules(scheduleList); result = response;

            }};

        exchange.getIn().setBody(scheduleList);

        scheduleImportService.sendSchedulestoIntakeService(exchange);

        List<String> errExpectedMessages = new ArrayList<>();
        errExpectedMessages.add(error.getMessage());
        errExpectedMessages.add(error1.getMessage());
        errExpectedMessages.add(error2.getMessage());


        String status = (String) exchange.getIn().getHeader("requestStatus");
        String errActualMessages = (String) exchange.getIn().getBody();

        String expected = StringUtils.join(errExpectedMessages, "\n");

        assertEquals("Error", status);
        assertEquals(expected, errActualMessages);
    }

    @Test
    public void when_schedule_import_service_is_called_and_all_schedule_imports_fail() throws Exception
    {
        Schedule schedule = new Schedule();
        Schedule schedule1 = new Schedule();
        Schedule schedule2 = new Schedule();
        Schedule schedule3 = new Schedule();

        final List<Schedule> scheduleList = new ArrayList<>();
        scheduleList.add(schedule);
        scheduleList.add(schedule1);
        scheduleList.add(schedule2);
        scheduleList.add(schedule3);

        com.sandata.lab.data.model.error.Error error = new Error();
        error.setMessage("First schedule failed");

        com.sandata.lab.data.model.error.Error error1 = new Error();
        error1.setMessage("Second schedule failed");

        com.sandata.lab.data.model.error.Error error2 = new Error();
        error2.setMessage("Third schedule failed");

        com.sandata.lab.data.model.error.Error error3 = new Error();
        error3.setMessage("Fourth schedule failed");

        ArrayList<Error> errors = new ArrayList<>();
        errors.add(error);
        errors.add(error1);
        errors.add(error2);
        errors.add(error3);

        final Response response = new Response();
        response.setStatus(ServiceStatus.FAILED);
        response.setData(errors);


        new Expectations()
        {{ // an "expectation block"
                // Record an expectation, with a given value to be returned:
                intakeService.insertSchedules(scheduleList); result = response;

            }};

        exchange.getIn().setBody(scheduleList);

        scheduleImportService.sendSchedulestoIntakeService(exchange);

        List<String> errExpectedMessages = new ArrayList<>();
        errExpectedMessages.add(error.getMessage());
        errExpectedMessages.add(error1.getMessage());
        errExpectedMessages.add(error2.getMessage());
        errExpectedMessages.add(error3.getMessage());

        String status = (String) exchange.getIn().getHeader("requestStatus");
        String errActualMessages = (String) exchange.getIn().getBody();

        String expected = StringUtils.join(errExpectedMessages, "\n");

        assertEquals("Error", status);
        assertEquals(expected, errActualMessages);
    }

    @Test
    public void when_schedule_import_service_is_called_and_all_schedule_imports_fail_and_one_error_isNull() throws Exception
    {
        Schedule schedule = new Schedule();
        Schedule schedule1 = new Schedule();
        Schedule schedule2 = new Schedule();
        Schedule schedule3 = new Schedule();

        final List<Schedule> scheduleList = new ArrayList<>();
        scheduleList.add(schedule);
        scheduleList.add(schedule1);
        scheduleList.add(schedule2);
        scheduleList.add(schedule3);

        com.sandata.lab.data.model.error.Error error = new Error();
        error.setMessage("First schedule failed");

        com.sandata.lab.data.model.error.Error error1 = new Error();
        error1.setMessage("Second schedule failed");

        com.sandata.lab.data.model.error.Error error2 = null;

        com.sandata.lab.data.model.error.Error error3 = new Error();
        error3.setMessage("Fourth schedule failed");

        ArrayList<Error> errors = new ArrayList<>();
        errors.add(error);
        errors.add(error1);
        errors.add(error2);
        errors.add(error3);

        final Response response = new Response();
        response.setStatus(ServiceStatus.FAILED);
        response.setData(errors);


        new Expectations()
        {{ // an "expectation block"
                // Record an expectation, with a given value to be returned:
                intakeService.insertSchedules(scheduleList); result = response;

            }};

        exchange.getIn().setBody(scheduleList);

        scheduleImportService.sendSchedulestoIntakeService(exchange);

        List<String> errExpectedMessages = new ArrayList<>();
        errExpectedMessages.add(error.getMessage());
        errExpectedMessages.add(error1.getMessage());
        errExpectedMessages.add(error3.getMessage());

        String status = (String) exchange.getIn().getHeader("requestStatus");
        String actual = (String) exchange.getIn().getBody();

        String expected = StringUtils.join(errExpectedMessages, "\n");


        assertEquals("Error", status);
        assertEquals(expected, actual);
    }


}
