package com.sandata.lab.intake.schedule.serialize;

import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.data.model.schedule.Schedule;
import com.sandata.lab.intake.schedule.BaseTestSupport;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.util.Assert;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Tests the GSON Serialization/Deserialization of model objects.
 * <p/>
 *
 * @author David Rutgos
 */
@RunWith(JMockit.class)
public class ScheduleSerializationTests extends BaseTestSupport {

    private DateFormat dateFormat;

    @Test
    public void should_serialize_a_simple_schedule_object_to_json() throws Exception {

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

        String json = new GSONProvider("yyyy-MM-dd HH:mm:ss").toJson(schedule);

        Assert.notNull(json);

        System.out.println(json);
    }

    @Override
    protected void onSetup() throws IOException {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    }
}
