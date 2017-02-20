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

package com.sandata.lab.wcf.intake.data.map;

import com.sandata.lab.data.model.schedule.Schedule;
import com.sandata.lab.data.model.wcf.schedule.WcfSchedule;
import com.sandata.lab.wcf.intake.BaseTestSupport;
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
 * Tests the mapping of WCF Schedule entity.
 * <p/>
 *
 * @author David Rutgos
 */
@RunWith(JMockit.class)
public class ScheduleMapperTests extends BaseTestSupport {

    private DateFormat dateFormat;

    private Schedule schedule;

    private ScheduleMapper mapper;

    @Test
    public void should_map_normalized_schedule_collection_to_wcf_schedule_collection() throws Exception {

        Assert.notNull(mapper);

        List<Schedule> schedules = new ArrayList<>();
        schedules.add(schedule);

        List<WcfSchedule> wcfSchedules = mapper.map(schedules);

        Assert.notNull(wcfSchedules);
        Assert.isTrue(wcfSchedules.size() == 1);

        validateSchedule(wcfSchedules.get(0));
    }

    @Test
    public void should_map_normalized_schedule_to_wcf_schedule() throws Exception {

        Assert.notNull(mapper);

        WcfSchedule wcfSchedule = mapper.map(schedule);

        validateSchedule(wcfSchedule);
    }

    private void validateSchedule(final WcfSchedule wcfSchedule) {

        Assert.notNull(wcfSchedule);

        Assert.isTrue(wcfSchedule.getScheduleId() == schedule.getScheduleId());
        Assert.isTrue(wcfSchedule.getStaffId() == schedule.getScheduleId());
        Assert.isTrue(wcfSchedule.getPatientId() == schedule.getPatientId());
        Assert.isTrue(wcfSchedule.getStartDate().equals(schedule.getStartDate()));
        Assert.isTrue(wcfSchedule.getEndDate().equals(schedule.getEndDate()));
        Assert.isTrue(wcfSchedule.getFromTime().equals(schedule.getFromTime()));
        Assert.isTrue(wcfSchedule.getEndTime().equals(schedule.getEndTime()));
        Assert.isTrue(wcfSchedule.getRestrictions().equals(schedule.getRestrictions()));
        Assert.isTrue(wcfSchedule.getFrequencyId().equals(schedule.getFrequencyId()));
        Assert.isTrue(wcfSchedule.getNumberOfOccurrences().equals(schedule.getNumberOfOccurrences()));
        Assert.isTrue(wcfSchedule.getDayOfMonth().equals(schedule.getDayOfMonth()));

        Assert.notNull(wcfSchedule.getScheduleWeekDays());
        Assert.isTrue(schedule.getScheduleWeekDays().size() == 3);

        List<Integer> scheduleWeekDays = schedule.getScheduleWeekDays();

        Assert.isTrue(scheduleWeekDays.get(2) == 3);
    }

    @Override
    protected void onSetup() throws Exception {
        dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
        mapper = new ScheduleMapper();

        schedule = new Schedule();
        schedule.setScheduleId(1);
        schedule.setStaffId(1);
        schedule.setPatientId(1);

        Date startDate = dateFormat.parse("08-03-2015");
        schedule.setStartDate(startDate);

        Date endDate = dateFormat.parse("08-04-2015");
        schedule.setEndDate(endDate);

        schedule.setFromTime("12");
        schedule.setEndTime("16");

        schedule.setRestrictions("None");

        schedule.setFrequencyId(999);
        schedule.setNumberOfOccurrences(5);
        schedule.setDayOfMonth(29);
        schedule.setScheduleWeekDays(new ArrayList<Integer>(){{add(1);add(2);add(3);}});
    }
}
