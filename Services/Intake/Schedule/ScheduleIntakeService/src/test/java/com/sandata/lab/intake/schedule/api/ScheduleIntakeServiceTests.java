package com.sandata.lab.intake.schedule.api;

import com.sandata.lab.cache.api.CacheService;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.data.model.schedule.Schedule;
import com.sandata.lab.intake.schedule.BaseTestSupport;
import com.sandata.lab.intake.schedule.impl.RestScheduleIntakeService;
import com.sandata.lab.intake.schedule.utils.constants.App;
import com.sandata.lab.wcf.intake.api.IntakeService;
import mockit.Mocked;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.util.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Test the StaffIntakeServiceApi.
 * <p/>
 *
 * @author David Rutgos
 */
@SuppressWarnings("unchecked")
@RunWith(JMockit.class)
public class ScheduleIntakeServiceTests extends BaseTestSupport {

    @Mocked
    private IntakeService intakeService;

    @Mocked
    private CacheService cacheService;

    private RestScheduleIntakeService service;

    private DateFormat dateFormat;

    private List<Schedule> scheduleList;

    @Test
    public void should_verify_that_osgi_intake_service_insert_schedule_is_called() throws Exception {

        final String uuid = UUID.randomUUID().toString();

        exchange.getIn().setHeader(App.ID.SCHEDULE_GUID.toString(), uuid);

        exchange.getIn().setBody(scheduleList);

        service.insertSchedules(exchange);

        Assert.notNull(exchange.getIn().getBody());

        new Verifications() {{

            intakeService.insertSchedules(scheduleList);
            times = 1;
            cacheService.put(withAny(Response.class), uuid);
        }};
    }

    @Test
    public void should_verify_that_osgi_cache_service_put_is_called() throws Exception {

        final String uuid = UUID.randomUUID().toString();

        exchange.getIn().setHeader(App.ID.SCHEDULE_GUID.toString(), uuid);

        exchange.getIn().setBody(scheduleList);

        service.insertSchedules(exchange);

        Assert.notNull(exchange.getIn().getBody());

        new Verifications() {{

            cacheService.put(withAny(Response.class), uuid);
            times = 1;
        }};
    }

    @Test
    public void should_verify_that_osgi_cache_services_processing_is_called() throws Exception {

        final String uuid = UUID.randomUUID().toString();

        exchange.getIn().setHeader(App.ID.SCHEDULE_GUID.toString(), uuid);

        exchange.getIn().setBody(scheduleList);

        service.insertSchedules(exchange);

        Assert.notNull(exchange.getIn().getBody());

        new Verifications() {{

            cacheService.processing(scheduleList, uuid);
            times = 1;
        }};
    }

    @Override
    protected void onSetup() throws Exception {

        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

        service = new RestScheduleIntakeService();

        service.setIntakeService(intakeService);

        service.setCacheService(cacheService);

        scheduleList = new ArrayList<>();

        Schedule schedule = new Schedule();
        schedule.setScheduleId(1);
        schedule.setStaffId(1);
        schedule.setPatientId(1);

        Date startDate = dateFormat.parse("2015-07-29 00:00:00");
        schedule.setStartDate(startDate);

        Date endDate = dateFormat.parse("2015-07-29 02:00:00");
        schedule.setEndDate(endDate);

        schedule.setFromTime("00:00");
        schedule.setEndTime("01:00");

        schedule.setRestrictions("None");

        schedule.setFrequencyId(999);
        schedule.setNumberOfOccurrences(5);
        schedule.setDayOfMonth(29);

        scheduleList.add(schedule);
    }
}
