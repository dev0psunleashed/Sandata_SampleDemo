package com.sandata.lab.wcf.export.data.map;

import com.sandata.lab.data.model.schedule.Schedule;
import com.sandata.lab.data.model.wcf.schedule.WcfSchedule;
import com.sandata.lab.wcf.export.BaseTestSupport;
import org.junit.Test;
import org.springframework.util.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests mapping Schedule entity from WcfSchedule.
 * <p/>
 *
 * @author David Rutgos
 */
public class WcfScheduleMapperTests extends BaseTestSupport {

    private WcfScheduleMapper mapper;

    private WcfSchedule wcfSchedule;

    private DateFormat dateFormat;

    @Test
    public void should_map_wcf_schedule_collection_to_normalized_schedule_collection() throws Exception {

        Assert.notNull(mapper);

        List<WcfSchedule> wcfSchedules = new ArrayList<>();
        wcfSchedules.add(wcfSchedule);

        List<Schedule> schedules = mapper.map(wcfSchedules);

        Assert.notNull(schedules);
        Assert.isTrue(schedules.size() == 1);

        validateSchedule(schedules.get(0));
    }

    @Test
    public void should_map_wcf_schedule_to_normalized_schedule_entity() throws Exception {

        Assert.notNull(mapper);

        Schedule schedule = mapper.map(wcfSchedule);

        validateSchedule(schedule);
    }

    private void validateSchedule(final Schedule schedule) {
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
        Assert.isTrue(schedule.getRestrictions().equals("ABCDEFG"));
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
    protected void onSetup() throws Exception {

        mapper = new WcfScheduleMapper();

        dateFormat = new SimpleDateFormat("MM-dd-yyyy");

        wcfSchedule = new WcfSchedule();

        wcfSchedule.setDayOfMonth(5);
        wcfSchedule.setEndDate(dateFormat.parse("08-04-2015"));
        wcfSchedule.setEndTime("16");
        wcfSchedule.setFrequencyId(2);
        wcfSchedule.setFromTime("12");
        wcfSchedule.setNumberOfOccurrences(10);
        wcfSchedule.setPatientId(108);
        wcfSchedule.setRestrictions("ABCDEFG");
        wcfSchedule.setScheduleId(3);
        wcfSchedule.setScheduleWeekDays(new ArrayList<Integer>(){{add(1);add(2);add(3);}});
        wcfSchedule.setStaffId(95);
        wcfSchedule.setStartDate(dateFormat.parse("08-03-2015"));
    }
}
