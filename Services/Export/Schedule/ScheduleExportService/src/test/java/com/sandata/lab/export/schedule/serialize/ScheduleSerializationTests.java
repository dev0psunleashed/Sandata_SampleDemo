package com.sandata.lab.export.schedule.serialize;

import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.data.model.schedule.Schedule;
import com.sandata.lab.export.schedule.BaseTestSupport;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.util.Assert;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public void should_serialize_a_simple_staff_object_to_json() throws Exception {

        Schedule schedule = new Schedule();

        schedule.setDayOfMonth(5);
        schedule.setEndDate(dateFormat.parse("08-04-2015"));
        schedule.setEndTime("16");
        schedule.setFrequencyId(2);
        schedule.setFromTime("12");
        schedule.setNumberOfOccurrences(10);
        schedule.setPatientId(108);
        schedule.setRestrictions("ABCDEFG");
        schedule.setScheduleId(3);
        schedule.setScheduleWeekDays(new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
        }});
        schedule.setStaffId(95);
        schedule.setStartDate(dateFormat.parse("08-03-2015"));

        String json = new GSONProvider("MM-dd-yyyy").toJson(schedule);

        Assert.notNull(json);
        Assert.isTrue(json.contains("\"dayOfMonth\": 5"));
        Assert.isTrue(json.contains("\"endDate\": \"08-04-2015\""));
        Assert.isTrue(json.contains("\"endTime\": \"16\""));
        Assert.isTrue(json.contains("\"frequencyId\": 2"));
        Assert.isTrue(json.contains("\"fromTime\": \"12\""));
        Assert.isTrue(json.contains("\"numberOfOccurrences\": 10"));
        Assert.isTrue(json.contains("\"patientId\": 108"));
        Assert.isTrue(json.contains("\"restrictions\": \"ABCDEFG\""));
        Assert.isTrue(json.contains("\"scheduleId\": 3"));
        Assert.isTrue(json.contains("\"staffId\": 95"));
        Assert.isTrue(json.contains("\"startDate\": \"08-03-2015\""));

        System.out.println(json);
    }

    @Override
    protected void onSetup() throws IOException {
        dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
    }
}
