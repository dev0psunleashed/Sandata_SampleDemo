/*
 * Copyright (c) 2015. Sandata Technologies, LLC
 * 26 Harbor Park Drive, Port Washington, NY 11050, 800-544-7263
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Sandata Technologies, LLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with
 * Sandata.
 */

package com.sandata.lab.wcf.export.serialize;

import com.google.gson.reflect.TypeToken;
import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.data.model.wcf.schedule.WcfSchedule;
import com.sandata.lab.wcf.export.BaseTestSupport;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.util.Assert;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Tests the serialization/deserialization of objects.
 * <p/>
 *
 * @author David Rutgos
 */
@SuppressWarnings("unchecked")
@RunWith(JMockit.class)
public class SerializationTests extends BaseTestSupport {

    private DateFormat dateFormat;

    @Test
    public void should_serialize_a_successful_json_wcf_response_into_a_list_of_wcf_schedules() throws Exception {

        String json = "[\n" +
                "   {\n" +
                "      \"DayOfMonth\":5,\n" +
                "      \"EndDate\":\"08-04-2015\",\n" +
                "      \"EndTime\":\"16\",\n" +
                "      \"FrequencyId\":2,\n" +
                "      \"FromTime\":\"12\",\n" +
                "      \"NumberOfOccurrences\":10,\n" +
                "      \"PatientId\":108,\n" +
                "      \"Restrictions\":\"\",\n" +
                "      \"ScheduleId\":3,\n" +
                "      \"ScheduleWeekDays\":[\n" +
                "         1,\n" +
                "         2,\n" +
                "         3\n" +
                "      ],\n" +
                "      \"StaffId\":95,\n" +
                "      \"StartDate\":\"08-03-2015\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"DayOfMonth\":5,\n" +
                "      \"EndDate\":\"08-04-2015\",\n" +
                "      \"EndTime\":\"16\",\n" +
                "      \"FrequencyId\":2,\n" +
                "      \"FromTime\":\"12\",\n" +
                "      \"NumberOfOccurrences\":10,\n" +
                "      \"PatientId\":108,\n" +
                "      \"Restrictions\":\"\",\n" +
                "      \"ScheduleId\":4,\n" +
                "      \"ScheduleWeekDays\":[\n" +
                "         1,\n" +
                "         2,\n" +
                "         3\n" +
                "      ],\n" +
                "      \"StaffId\":95,\n" +
                "      \"StartDate\":\"08-03-2015\"\n" +
                "   }\n" +
                "]";

        GSONProvider.SetDateTimeFormat("MM-dd-yyyy");

        Type listType = new TypeToken<List<WcfSchedule>>() {}.getType();

        List<WcfSchedule> schedules = (List<WcfSchedule>) GSONProvider.FromJson(json, listType);

        Assert.notNull(schedules);
        Assert.isTrue(schedules.size() == 2);

        WcfSchedule schedule = schedules.get(0);

        Assert.notNull(schedule);

        Assert.isTrue(schedule.getDayOfMonth() == 5);

        Assert.notNull(schedule.getEndDate());

        String endDate = dateFormat.format(schedule.getEndDate());

        Assert.isTrue(endDate.equals("08-04-2015"));

        Assert.isTrue(schedule.getEndTime().equals("16"));
        Assert.isTrue(schedule.getFrequencyId() == 2);
        Assert.isTrue(schedule.getFromTime().equals("12"));
        Assert.isTrue(schedule.getNumberOfOccurrences() == 10);
        Assert.isTrue(schedule.getPatientId() == 108);
        Assert.isTrue(schedule.getRestrictions().equals(""));
        Assert.isTrue(schedule.getScheduleId() == 3);

        Assert.notNull(schedule.getScheduleWeekDays());
        Assert.isTrue(schedule.getScheduleWeekDays().size() == 3);

        List<Integer> scheduleWeekDays = schedule.getScheduleWeekDays();

        Assert.isTrue(scheduleWeekDays.get(2) == 3);

        Assert.isTrue(schedule.getStaffId() == 95);

        Assert.notNull(schedule.getStartDate());

        String startDate = dateFormat.format(schedule.getStartDate());

        Assert.isTrue(startDate.equals("08-03-2015"));
    }

    @Override
    protected void onSetup() throws IOException {
        dateFormat = new SimpleDateFormat("MM-dd-yyyy");
    }
}
