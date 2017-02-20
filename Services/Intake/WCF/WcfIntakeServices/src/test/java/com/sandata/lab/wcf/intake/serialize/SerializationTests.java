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

package com.sandata.lab.wcf.intake.serialize;

import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.data.model.schedule.Schedule;
import com.sandata.lab.data.model.wcf.error.WcfError;
import com.sandata.lab.data.model.wcf.response.WcfResponse;
import com.sandata.lab.data.model.wcf.schedule.WcfSchedule;
import com.sandata.lab.wcf.intake.BaseTestSupport;
import com.sandata.lab.wcf.intake.data.map.ScheduleMapper;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.util.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Tests the serialization/deserialization of objects.
 * <p/>
 *
 * @author David Rutgos
 */
@RunWith(JMockit.class)
public class SerializationTests extends BaseTestSupport {

    private List<Schedule> schedules;

    @Test
    public void should_serialize_schedules_collection_to_json() throws Exception {

        Assert.notNull(schedules);

        List<WcfSchedule> wcfSchedules = new ScheduleMapper().map(schedules);

        Assert.notNull(wcfSchedules);

        Assert.isTrue(wcfSchedules.size() == schedules.size());

        String json = new GSONProvider("MM-dd-yyyy").toJson(wcfSchedules);

        Assert.notNull(json);

        System.out.println(json);
    }
    
    @Test
    public void should_serialize_a_successful_json_wcf_response_into_a_wcf_response_entity() throws Exception {

        String json = "{\"Errors\":[],\"FailedCount\":0,\"Status\":\"Succeeded\",\"SucceededCount\":1}";

        WcfResponse response = (WcfResponse) GSONProvider.FromJson(json, WcfResponse.class);

        Assert.notNull(response);
        Assert.notNull(response.getErrors());
        Assert.isTrue(response.getErrors().size() == 0);
        Assert.isTrue(response.getFailedCount() == 0);
        Assert.isTrue(response.getStatus().equals("Succeeded"));
        Assert.isTrue(response.getSucceededCount() == 1);
    }

    @Test
    public void should_serialize_a_failed_json_wcf_response_into_a_wcf_response_entity_for_schedule() throws Exception {

        String json = "{\"Errors\":[{\"Message\":\"Schedule 8\\/6\\/2015 12:00:00 AM 8\\/7\\/2015 12:00:00 AM 95 108 could not be added\",\"UniqueID\":\"befa429e-1788-470f-9f1c-aeeecc93adcc\"}],\"FailedCount\":1,\"Status\":\"Failed\",\"SucceededCount\":0}";

        WcfResponse response = (WcfResponse) GSONProvider.FromJson(json, WcfResponse.class);

        Assert.notNull(response);
        Assert.notNull(response.getErrors());
        Assert.isTrue(response.getErrors().size() == 1);

        WcfError error = response.getErrors().get(0);

        Assert.isTrue(error.getMessage().equals("Schedule 8/6/2015 12:00:00 AM 8/7/2015 12:00:00 AM 95 108 could not be added"));
        Assert.isTrue(error.getUniqueID().equals("befa429e-1788-470f-9f1c-aeeecc93adcc"));

        Assert.isTrue(response.getFailedCount() == 1);
        Assert.isTrue(response.getStatus().equals("Failed"));
        Assert.isTrue(response.getSucceededCount() == 0);
    }

    @Test
    public void should_serialize_a_failed_json_wcf_response_into_a_wcf_response_entity_for_patient() throws Exception {

        String json = "{\"Errors\":[{\"Message\":\"Patient David Rutgos2 drutgos@sandata.com could not be added\",\"UniqueID\":\"079d6110-bd9e-456a-b53d-cffcb3025567\"}],\"FailedCount\":1,\"Status\":\"Failed\",\"SucceededCount\":0}";

        WcfResponse response = (WcfResponse) GSONProvider.FromJson(json, WcfResponse.class);

        Assert.notNull(response);
        Assert.notNull(response.getErrors());
        Assert.isTrue(response.getErrors().size() == 1);

        WcfError error = response.getErrors().get(0);

        Assert.isTrue(error.getMessage().equals("Patient David Rutgos2 drutgos@sandata.com could not be added"));
        Assert.isTrue(error.getUniqueID().equals("079d6110-bd9e-456a-b53d-cffcb3025567"));

        Assert.isTrue(response.getFailedCount() == 1);
        Assert.isTrue(response.getStatus().equals("Failed"));
        Assert.isTrue(response.getSucceededCount() == 0);
    }

    @Override
    protected void onSetup() throws Exception {

        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
        schedules = new ArrayList<>();

        Schedule schedule = new Schedule();

        schedule.setStaffId(96);
        schedule.setPatientId(106);

        Date startDate = dateFormat.parse("08-03-2015");
        schedule.setStartDate(startDate);

        Date endDate = dateFormat.parse("08-04-2015");
        schedule.setEndDate(endDate);

        schedule.setFromTime("12:00");
        schedule.setEndTime("16:00");

        schedule.setRestrictions("None");

        schedule.setFrequencyId(2);
        schedule.setNumberOfOccurrences(10);
        schedule.setDayOfMonth(5);
        schedule.setScheduleWeekDays(new ArrayList<Integer>(){{add(1);add(2);add(3);}});

        schedules.add(schedule);
    }
}
