package com.sandata.lab.rest.sched.utils;

import com.sandata.lab.data.model.dl.model.extended.schedule.SelectedDaysOfWeek;
import org.joda.time.LocalDateTime;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by khangle on 10/21/2016.
 */
public class SelectedDaysOfWeekBuilder {
    private SelectedDaysOfWeek selectedDaysOfWeek;

    /**
     * Constructor
     *
     * @param selectedDaysOfWeek
     */
    public SelectedDaysOfWeekBuilder(SelectedDaysOfWeek selectedDaysOfWeek) {
        this.selectedDaysOfWeek = selectedDaysOfWeek;
    }

    public SelectedDaysOfWeek getSelectedDaysOfWeek() {
        return selectedDaysOfWeek;
    }

    public SelectedDaysOfWeekBuilder build(Date startDate, Date endDate, boolean isOvernight) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);

        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                this.selectedDaysOfWeek.setSunday(true);
                this.selectedDaysOfWeek.setSundayStart(new LocalDateTime(startDate).toLocalTime());
                this.selectedDaysOfWeek.setSundayEnd(new LocalDateTime(endDate).toLocalTime());
                this.selectedDaysOfWeek.setSundayOvernight(isOvernight);
                break;

            case Calendar.MONDAY:
                this.selectedDaysOfWeek.setMonday(true);
                this.selectedDaysOfWeek.setMondayStart(new LocalDateTime(startDate).toLocalTime());
                this.selectedDaysOfWeek.setMondayEnd(new LocalDateTime(endDate).toLocalTime());
                this.selectedDaysOfWeek.setMondayOvernight(isOvernight);
                break;

            case Calendar.TUESDAY:
                this.selectedDaysOfWeek.setTuesday(true);
                this.selectedDaysOfWeek.setTuesdayStart(new LocalDateTime(startDate).toLocalTime());
                this.selectedDaysOfWeek.setTuesdayEnd(new LocalDateTime(endDate).toLocalTime());
                this.selectedDaysOfWeek.setTuesdayOvernight(isOvernight);
                break;

            case Calendar.WEDNESDAY:
                this.selectedDaysOfWeek.setWednesday(true);
                this.selectedDaysOfWeek.setWednesdayStart(new LocalDateTime(startDate).toLocalTime());
                this.selectedDaysOfWeek.setWednesdayEnd(new LocalDateTime(endDate).toLocalTime());
                this.selectedDaysOfWeek.setWednesdayOvernight(isOvernight);
                break;

            case Calendar.THURSDAY:
                this.selectedDaysOfWeek.setThursday(true);
                this.selectedDaysOfWeek.setThursdayStart(new LocalDateTime(startDate).toLocalTime());
                this.selectedDaysOfWeek.setThursdayEnd(new LocalDateTime(endDate).toLocalTime());
                this.selectedDaysOfWeek.setThursdayOvernight(isOvernight);
                break;

            case Calendar.FRIDAY:
                this.selectedDaysOfWeek.setFriday(true);
                this.selectedDaysOfWeek.setFridayStart(new LocalDateTime(startDate).toLocalTime());
                this.selectedDaysOfWeek.setFridayEnd(new LocalDateTime(endDate).toLocalTime());
                this.selectedDaysOfWeek.setFridayOvernight(isOvernight);
                break;

            case Calendar.SATURDAY:
                this.selectedDaysOfWeek.setSaturday(true);
                this.selectedDaysOfWeek.setSaturdayStart(new LocalDateTime(startDate).toLocalTime());
                this.selectedDaysOfWeek.setSaturdayEnd(new LocalDateTime(endDate).toLocalTime());
                this.selectedDaysOfWeek.setSaturdayOvernight(isOvernight);
        }

        return this;
    }

}
