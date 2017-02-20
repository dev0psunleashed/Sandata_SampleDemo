package com.sandata.lab.rest.sched.utils;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.model.Authorization;
import com.sandata.lab.data.model.dl.model.ScheduleEvent;
import com.sandata.lab.data.model.dl.model.WeekendStartDay;
import com.sandata.lab.data.model.dl.model.extended.schedule.ScheduleEvntExt;
import com.sandata.lab.data.model.dl.model.extended.schedule.ScheduleExt;
import com.sandata.lab.data.model.dl.model.extended.schedule.SelectedDaysOfWeek;
import com.sandata.lab.rest.sched.model.DayInWeek;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by khangle on 09/12/2016.
 */
public class ScheduleUtils {

    public static final SimpleDateFormat DATE_ONLY_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    // Constructor
    private ScheduleUtils() {

    }

    /**
     * Return total duration in milliseconds for a specific day of  <code>SelectedDaysOfWeek</code>
     *
     * @param selectedDaysOfWeek
     * @param dayOfWeek
     * @return
     */
    public static long getTotalDurationForDay(SelectedDaysOfWeek selectedDaysOfWeek, int dayOfWeek) {
        LocalTime dayStart = null;
        LocalTime dayEnd = null;
        boolean isOvernight = false;

        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                if (selectedDaysOfWeek.isSunday()) {
                    dayStart = selectedDaysOfWeek.getSundayStart();
                    dayEnd = selectedDaysOfWeek.getSundayEnd();
                    isOvernight = selectedDaysOfWeek.isSundayOvernight();
                }
                break;

            case Calendar.MONDAY:
                if (selectedDaysOfWeek.isMonday()) {
                    dayStart = selectedDaysOfWeek.getMondayStart();
                    dayEnd = selectedDaysOfWeek.getMondayEnd();
                    isOvernight = selectedDaysOfWeek.isMondayOvernight();
                }
                break;

            case Calendar.TUESDAY:
                if (selectedDaysOfWeek.isTuesday()) {
                    dayStart = selectedDaysOfWeek.getTuesdayStart();
                    dayEnd = selectedDaysOfWeek.getTuesdayEnd();
                    isOvernight = selectedDaysOfWeek.isTuesdayOvernight();
                }
                break;

            case Calendar.WEDNESDAY:
                if (selectedDaysOfWeek.isWednesday()) {
                    dayStart = selectedDaysOfWeek.getWednesdayStart();
                    dayEnd = selectedDaysOfWeek.getWednesdayEnd();
                    isOvernight = selectedDaysOfWeek.isWednesdayOvernight();
                }
                break;

            case Calendar.THURSDAY:
                if (selectedDaysOfWeek.isThursday()) {
                    dayStart = selectedDaysOfWeek.getThursdayStart();
                    dayEnd = selectedDaysOfWeek.getThursdayEnd();
                    isOvernight = selectedDaysOfWeek.isThursdayOvernight();
                }
                break;

            case Calendar.FRIDAY:
                if (selectedDaysOfWeek.isFriday()) {
                    dayStart = selectedDaysOfWeek.getFridayStart();
                    dayEnd = selectedDaysOfWeek.getFridayEnd();
                    isOvernight = selectedDaysOfWeek.isFridayOvernight();
                }
                break;

            case Calendar.SATURDAY:
                if (selectedDaysOfWeek.isSaturday()) {
                    dayStart = selectedDaysOfWeek.getSaturdayStart();
                    dayEnd = selectedDaysOfWeek.getSaturdayEnd();
                    isOvernight = selectedDaysOfWeek.isSaturdayOvernight();
                }
                break;

        }

        if (dayStart != null && dayEnd != null) {
            if (isOvernight) {
                LocalDateTime startDt = new LocalDateTime(1970, 1, 1, dayStart.getHourOfDay(), dayStart.getMinuteOfHour());
                LocalDateTime endDt = new LocalDateTime(1970, 1, 2, dayEnd.getHourOfDay(), dayEnd.getMinuteOfHour());
                return endDt.toDate().getTime() - startDt.toDate().getTime();
            } else {
                return dayEnd.getMillisOfDay() - dayStart.getMillisOfDay();
            }
        }

        return 0;
    }

    /**
     * Return total duration in milliseconds for a list of dates
     *
     * @param selectedDates
     * @param selectedDaysOfWeek
     * @return
     */
    public static long getTotalDurationForDateList(List<Date> selectedDates, SelectedDaysOfWeek selectedDaysOfWeek) {
        long totalDuration = 0;
        Calendar cal = Calendar.getInstance();
        for (Date d : selectedDates) {
            cal.setTime(d);
            totalDuration += getTotalDurationForDay(selectedDaysOfWeek, cal.get(Calendar.DAY_OF_WEEK));
        }

        return totalDuration;
    }

    /**
     * Return duration in milliseconds for a date
     *
     * @param date
     * @param selectedDaysOfWeek
     * @return
     */
    public static long getDurationForDate(Date date, SelectedDaysOfWeek selectedDaysOfWeek) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        return getTotalDurationForDay(selectedDaysOfWeek, cal.get(Calendar.DAY_OF_WEEK));
    }

    /**
     * Return duration in milliseconds of a ScheduleEvntExt instance
     *
     * @param scheduleEvntExt
     * @return
     */
    public static long getDurationForScheduleEvntExt(ScheduleEvntExt scheduleEvntExt) {

        return scheduleEvntExt.getScheduleEventEndDatetime().getTime() - scheduleEvntExt.getScheduleEventStartDatetime().getTime();
    }

    /**
     *
     * @param date
     * @param authLimitDay1
     * @param authLimitDay2
     * @param authLimitDay3
     * @param authLimitDay4
     * @param authLimitDay5
     * @param authLimitDay6
     * @param authLimitDay7
     * @return
     */
    public static BigDecimal getAuthLimitForDate(Date date,
                                                 BigDecimal authLimitDay1,
                                                 BigDecimal authLimitDay2,
                                                 BigDecimal authLimitDay3,
                                                 BigDecimal authLimitDay4,
                                                 BigDecimal authLimitDay5,
                                                 BigDecimal authLimitDay6,
                                                 BigDecimal authLimitDay7) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:;
                return authLimitDay1;

            case Calendar.MONDAY:
                return authLimitDay2;

            case Calendar.TUESDAY:
                return authLimitDay3;

            case Calendar.WEDNESDAY:
                return authLimitDay4;

            case Calendar.THURSDAY:
                return authLimitDay5;

            case Calendar.FRIDAY:
                return authLimitDay6;

            case Calendar.SATURDAY:
                return authLimitDay7;
        }

        return new BigDecimal("0");
    }

    /**
     * Validate time ranges of each day of a Schedule against Authorization
     *
     * @param auth
     * @param scheduleExt
     * @return
     */
    public static String validateDailyTimeRanges(Authorization auth, ScheduleExt scheduleExt) {
        String errorMessage = "";
        int errorCount = 0;
        SelectedDaysOfWeek selectedDaysOfWeek = scheduleExt.getSelectedDaysOfWeek();

        LocalTime authLimitStartTime;
        LocalTime authLimitEndTime;

        if (selectedDaysOfWeek.isSunday()
                && auth.getAuthorizationLimitStartTimeDay1() != null
                && auth.getAuthorizationLimitEndTimeDay1() != null) {

            authLimitStartTime = new LocalDateTime(auth.getAuthorizationLimitStartTimeDay1()).toLocalTime();
            authLimitEndTime = new LocalDateTime(auth.getAuthorizationLimitEndTimeDay1()).toLocalTime();
            if (selectedDaysOfWeek.getSundayStart().isBefore(authLimitStartTime)
                    || selectedDaysOfWeek.getSundayStart().isAfter(authLimitEndTime)
                    || selectedDaysOfWeek.getSundayEnd().isAfter(authLimitEndTime)) {
                errorMessage = "Invalid time for Sunday";
                errorCount++;
            }
        }

        if (selectedDaysOfWeek.isMonday()
                && auth.getAuthorizationLimitStartTimeDay2() != null
                && auth.getAuthorizationLimitEndTimeDay2() != null) {

            authLimitStartTime = new LocalDateTime(auth.getAuthorizationLimitStartTimeDay2()).toLocalTime();
            authLimitEndTime = new LocalDateTime(auth.getAuthorizationLimitEndTimeDay2()).toLocalTime();
            if (selectedDaysOfWeek.getMondayStart().isBefore(authLimitStartTime)
                    || selectedDaysOfWeek.getMondayStart().isAfter(authLimitEndTime)
                    || selectedDaysOfWeek.getMondayEnd().isAfter(authLimitEndTime)) {
                errorMessage = "Invalid time for Monday";
                errorCount++;
            }
        }

        if (selectedDaysOfWeek.isTuesday()
                && auth.getAuthorizationLimitStartTimeDay3() != null
                && auth.getAuthorizationLimitEndTimeDay3() != null) {

            authLimitStartTime = new LocalDateTime(auth.getAuthorizationLimitStartTimeDay3()).toLocalTime();
            authLimitEndTime = new LocalDateTime(auth.getAuthorizationLimitEndTimeDay3()).toLocalTime();
            if (selectedDaysOfWeek.getTuesdayStart().isBefore(authLimitStartTime)
                    || selectedDaysOfWeek.getTuesdayStart().isAfter(authLimitEndTime)
                    || selectedDaysOfWeek.getTuesdayEnd().isAfter(authLimitEndTime)) {
                errorMessage = "Invalid time for Tuesday";
                errorCount++;
            }
        }

        if (selectedDaysOfWeek.isWednesday()
                && auth.getAuthorizationLimitStartTimeDay4() != null
                && auth.getAuthorizationLimitEndTimeDay4() != null) {

            authLimitStartTime = new LocalDateTime(auth.getAuthorizationLimitStartTimeDay4()).toLocalTime();
            authLimitEndTime = new LocalDateTime(auth.getAuthorizationLimitEndTimeDay4()).toLocalTime();
            if (selectedDaysOfWeek.getWednesdayStart().isBefore(authLimitStartTime)
                    || selectedDaysOfWeek.getWednesdayStart().isAfter(authLimitEndTime)
                    || selectedDaysOfWeek.getWednesdayEnd().isAfter(authLimitEndTime)) {
                errorMessage = "Invalid time for Wednesday";
                errorCount++;
            }
        }

        if (selectedDaysOfWeek.isThursday()
                && auth.getAuthorizationLimitStartTimeDay5() != null
                && auth.getAuthorizationLimitEndTimeDay5() != null) {

            authLimitStartTime = new LocalDateTime(auth.getAuthorizationLimitStartTimeDay5()).toLocalTime();
            authLimitEndTime = new LocalDateTime(auth.getAuthorizationLimitEndTimeDay5()).toLocalTime();
            if (selectedDaysOfWeek.getThursdayStart().isBefore(authLimitStartTime)
                    || selectedDaysOfWeek.getThursdayStart().isAfter(authLimitEndTime)
                    || selectedDaysOfWeek.getThursdayEnd().isAfter(authLimitEndTime)) {
                errorMessage = "Invalid time for Thursday";
                errorCount++;
            }
        }

        if (selectedDaysOfWeek.isFriday()
                && auth.getAuthorizationLimitStartTimeDay6() != null
                && auth.getAuthorizationLimitEndTimeDay6() != null) {

            authLimitStartTime = new LocalDateTime(auth.getAuthorizationLimitStartTimeDay6()).toLocalTime();
            authLimitEndTime = new LocalDateTime(auth.getAuthorizationLimitEndTimeDay6()).toLocalTime();
            if (selectedDaysOfWeek.getFridayStart().isBefore(authLimitStartTime)
                    || selectedDaysOfWeek.getFridayStart().isAfter(authLimitEndTime)
                    || selectedDaysOfWeek.getFridayEnd().isAfter(authLimitEndTime)) {
                errorMessage = "Invalid time for Friday";
                errorCount++;
            }
        }

        if (selectedDaysOfWeek.isSaturday()
                && auth.getAuthorizationLimitStartTimeDay7() != null
                && auth.getAuthorizationLimitEndTimeDay7() != null) {

            authLimitStartTime = new LocalDateTime(auth.getAuthorizationLimitStartTimeDay7()).toLocalTime();
            authLimitEndTime = new LocalDateTime(auth.getAuthorizationLimitEndTimeDay7()).toLocalTime();
            if (selectedDaysOfWeek.getSaturdayStart().isBefore(authLimitStartTime)
                    || selectedDaysOfWeek.getSaturdayStart().isAfter(authLimitEndTime)
                    || selectedDaysOfWeek.getSaturdayEnd().isAfter(authLimitEndTime)) {
                errorMessage = "Invalid time for Saturday";
                errorCount++;
            }
        }

        if (errorCount == 0) {
            return "";
        } else if (errorCount == 1) {
            return errorMessage;
        } else {
            return "Scheduled hours do not match with authorized daily time";
        }
    }

    /**
     * Get all selected days between Schedule Start Date and Schedule End Date
     *
     * @param scheduleStartDate
     * @param scheduleEndDate
     * @param selectedDaysInWeek
     * @return
     */
    public static List<Date> getAllScheduleDatesInRange(Date scheduleStartDate, Date scheduleEndDate,
                                                        List<Integer> selectedDaysInWeek) {

        List<Date> dates = new ArrayList<>();

        Calendar curDate = Calendar.getInstance();
        curDate.setTime(scheduleStartDate);
        curDate.set(Calendar.HOUR_OF_DAY, 0);
        curDate.set(Calendar.MINUTE, 0);
        curDate.set(Calendar.SECOND, 0);
        curDate.set(Calendar.MILLISECOND, 0);

        while (!curDate.getTime().after(scheduleEndDate)) {
            if (selectedDaysInWeek.contains(curDate.get(Calendar.DAY_OF_WEEK))) {
                dates.add(curDate.getTime());
            }
            curDate.add(Calendar.DATE, 1);
        }

        return dates;
    }

    /**
     * Check if a date is inclusively between start and end dates
     *
     * @param date
     * @param start
     * @param end
     * @return
     */
    public static boolean isDateInRange(Date date, Date start, Date end) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(start);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date newStart = cal.getTime();

        cal.setTime(end);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        Date newEnd = cal.getTime();


        return ((date.equals(newStart) || date.after(newStart)) && (date.equals(newEnd) || date.before(newEnd)));
    }

    /**
     * Get all selected days between Schedule Start Date and Schedule End Date
     *
     * @param scheduleExt
     * @param scheduleStartDate
     * @param scheduleEndDate
     * @param selectedDaysInWeek
     * @return
     */
    public static List<Date> getScheduleDatesInRange(ScheduleExt scheduleExt,
                                                     Date scheduleStartDate,
                                                     Date scheduleEndDate,
                                                     List<Integer> selectedDaysInWeek) {

        List<Date> dates = new ArrayList<>();

        Calendar curDate = Calendar.getInstance();
        curDate.setTime(scheduleStartDate);

        while (!curDate.getTime().after(scheduleEndDate)) {
            if (selectedDaysInWeek.contains(curDate.get(Calendar.DAY_OF_WEEK))
                    && isDateInRange(curDate.getTime(), scheduleExt.getScheduleStartDate(), scheduleExt.getScheduleEndDate())) {
                dates.add(curDate.getTime());
            }
            curDate.add(Calendar.DATE, 1);
        }

        return dates;
    }

    /**
     * Get all selected days in week of Schedule
     *
     * @param selectedDaysOfWeek
     * @return
     */
    public static List<Integer> getScheduleSelectedDayOfWeek(SelectedDaysOfWeek selectedDaysOfWeek) {
        List<Integer> selectedDays = new ArrayList<>();

        if (selectedDaysOfWeek.isSunday()) {
            selectedDays.add(Calendar.SUNDAY);
        }

        if (selectedDaysOfWeek.isMonday()) {
            selectedDays.add(Calendar.MONDAY);
        }

        if (selectedDaysOfWeek.isTuesday()) {
            selectedDays.add(Calendar.TUESDAY);
        }

        if (selectedDaysOfWeek.isWednesday()) {
            selectedDays.add(Calendar.WEDNESDAY);
        }

        if (selectedDaysOfWeek.isThursday()) {
            selectedDays.add(Calendar.THURSDAY);
        }

        if (selectedDaysOfWeek.isFriday()) {
            selectedDays.add(Calendar.FRIDAY);
        }

        if (selectedDaysOfWeek.isSaturday()) {
            selectedDays.add(Calendar.SATURDAY);
        }

        return selectedDays;
    }
    
    /**
     * Get all selected days in week of Schedule
     *
     * @param scheduleExt
     * @return Map of daynumber and time
     */
    public static Map<Integer, DayInWeek> getScheduleSelectedDayOfWeekInMap(ScheduleExt scheduleExt) {
        Map<Integer, DayInWeek> selectedDays = new HashMap<>();

        SelectedDaysOfWeek selectedDaysOfWeek = scheduleExt.getSelectedDaysOfWeek();

        if (selectedDaysOfWeek.isSunday()) {
            selectedDays.put(Calendar.SUNDAY,
                    new DayInWeek(selectedDaysOfWeek.getSundayStart(), selectedDaysOfWeek.getSundayEnd(), selectedDaysOfWeek.isSundayOvernight()));
        }

        if (selectedDaysOfWeek.isMonday()) {
            selectedDays.put(Calendar.MONDAY,
                    new DayInWeek(selectedDaysOfWeek.getMondayStart(), selectedDaysOfWeek.getMondayEnd(), selectedDaysOfWeek.isMondayOvernight()));
        }

        if (selectedDaysOfWeek.isTuesday()) {
            selectedDays.put(Calendar.TUESDAY,
                    new DayInWeek(selectedDaysOfWeek.getTuesdayStart(), selectedDaysOfWeek.getTuesdayEnd(), selectedDaysOfWeek.isTuesdayOvernight()));
        }

        if (selectedDaysOfWeek.isWednesday()) {
            selectedDays.put(Calendar.WEDNESDAY,
                    new DayInWeek(selectedDaysOfWeek.getWednesdayStart(), selectedDaysOfWeek.getWednesdayEnd(), selectedDaysOfWeek.isWednesdayOvernight()));
        }

        if (selectedDaysOfWeek.isThursday()) {
            selectedDays.put(Calendar.THURSDAY,
                    new DayInWeek(selectedDaysOfWeek.getThursdayStart(), selectedDaysOfWeek.getThursdayEnd(), selectedDaysOfWeek.isThursdayOvernight()));
        }

        if (selectedDaysOfWeek.isFriday()) {
            selectedDays.put(Calendar.FRIDAY,
                    new DayInWeek(selectedDaysOfWeek.getFridayStart(), selectedDaysOfWeek.getFridayEnd(), selectedDaysOfWeek.isFridayOvernight()));
        }

        if (selectedDaysOfWeek.isSaturday()) {
            selectedDays.put(Calendar.SATURDAY,
                    new DayInWeek(selectedDaysOfWeek.getSaturdayStart(), selectedDaysOfWeek.getSaturdayEnd(), selectedDaysOfWeek.isSaturdayOvernight()));
        }

        return selectedDays;
    }

    /**
     * Get a list of selected java.util.Date instances between schedule start date and untilDate
     *
     * @param scheduleExt
     * @param untilDate
     * @return
     */
    public static List<Date> getselectedDateForRange(final ScheduleExt scheduleExt, Date untilDate) {

        List<Date> allSelectedScheduleDatesInRange;
        List<Integer> selectedDaysInWeek = getScheduleSelectedDayOfWeek(scheduleExt.getSelectedDaysOfWeek());

        // Get all selected dates from scheduleStartDate until next number of count calendarField or until scheduleEndDate
        allSelectedScheduleDatesInRange = getAllScheduleDatesInRange(scheduleExt.getScheduleStartDate(),
                untilDate,
                selectedDaysInWeek);

        return allSelectedScheduleDatesInRange;
    }

    /**
     * set time of <code>date</code> to 23:59:59.999 (HH:mm:ss.SSS)
     * @param date
     * @return
     */
    public static Date toTheEndOfTheDate(Date date) {
        return new LocalDateTime(date.getTime())
            .withHourOfDay(23)
            .withMinuteOfHour(59)
            .withSecondOfMinute(59)
            .withMillisOfSecond(999)
            .toDate();
    }
    
    /**
     * set time of <code>date</code> to 00:00:00.000 (HH:mm:ss.SSS)
     * @param date
     * @return
     */
    public static Date toTheBeginOfTheDate(Date date) {
        return new LocalDateTime(date.getTime())
            .withHourOfDay(0)
            .withMinuteOfHour(0)
            .withSecondOfMinute(0)
            .withMillisOfSecond(0)
            .toDate();
    }
    
    /**
     * combines date and time(hour + minute) then converts to UTC timezone
     * @param date              date of current <code>timezoneName</code>
     * @param hourOfDay         hour of current <code>timezoneName</code>
     * @param minuteOfHour      minute of current <code>timezoneName</code>
     * @param timezoneName      timezone to convert to UTC format
     * @return
     */
    public static Date convertToUTC(Date date, int hourOfDay, int minuteOfHour, String timezoneName) {
        Date localDateTime = null;
        try {
            localDateTime = new LocalDateTime(date.getTime())
                    .withHourOfDay(hourOfDay)
                    .withMinuteOfHour(minuteOfHour)
                    .withSecondOfMinute(0)
                    .withMillisOfSecond(0)
                    .toDateTime(DateTimeZone.forID(timezoneName))
                    .toDateTime(DateTimeZone.UTC)
                    .toLocalDateTime()
                    .toDate();
        } catch (org.joda.time.IllegalInstantException ex){
            //Check the message based on Joda error message
            if(ex.getMessage().contains("daylight savings")) {
                //Daylight Saving Time Starts, needs to increase 1 more hour
                hourOfDay = hourOfDay + 1;
                localDateTime = new LocalDateTime(date.getTime())
                        .withHourOfDay(hourOfDay)
                        .withMinuteOfHour(minuteOfHour)
                        .withSecondOfMinute(0)
                        .withMillisOfSecond(0)
                        .toDateTime(DateTimeZone.forID(timezoneName))
                        .toDateTime(DateTimeZone.UTC)
                        .toLocalDateTime()
                        .toDate();
            }
            else {
                throw new SandataRuntimeException(String.format("ScheduleUtils#ConvertToUTC - Invalid date time: - date.getTime(): %s [in long value: %s]; hourOfDay: %s; timezoneName: %s", date, date.getTime(), hourOfDay, timezoneName));
            }
        }
        return localDateTime;
    }

    public static Date getDateOnlyInTargetTimeZone(Date date, String timezoneName) {
        return new DateTime(date.getTime())
                .toDateTime(DateTimeZone.forID(timezoneName))
                .toLocalDate()
                .toDate();
    }

    public static Date convertToTargetTimeZone(Date date, String timezoneName) {
        return new DateTime(date.getTime())
                .toDateTime(DateTimeZone.forID(timezoneName))
                .toLocalDateTime()
                .toDate();
    }

    /**
     * Calculate schedule end date from schedule start date and number of repeated weeks
     *
     * @param scheduleStartDate
     * @param weekOfMonthNumber
     * @return
     */
    public static Date getScheduleEndDateByRepeatWeek(Date scheduleStartDate, int weekOfMonthNumber) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(scheduleStartDate);
        cal.add(Calendar.WEEK_OF_MONTH, weekOfMonthNumber);
        cal.add(Calendar.DATE, -1);

        return cal.getTime();
    }


    /**
     * Get start date and end date of a week in which the specified date belongs to
     *
     * @param d
     * @param weekendStartDay
     * @return
     */
    public static Date[] getWeekForDate(Date d, WeekendStartDay weekendStartDay) {
        Date[] weekStartEnd = new Date[2];
        Calendar origCal = Calendar.getInstance();
        origCal.setTime(d);

        Calendar cal = Calendar.getInstance();
        cal.setTime(d);

        int offset = 0;
        switch (weekendStartDay) {
            case FRIDAY:
                offset = 0;
                break;
            case SATURDAY:
                offset = 1;
        }

        cal.set(Calendar.DAY_OF_WEEK, cal. getActualMinimum(Calendar.DAY_OF_WEEK));
        cal.add(Calendar.DAY_OF_WEEK, offset);
        if (cal.after(origCal)) {
            cal.add(Calendar.WEEK_OF_MONTH, -1);
        }
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        weekStartEnd[0] = cal.getTime();

        cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
        cal.add(Calendar.DAY_OF_WEEK, offset);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        weekStartEnd[1] = cal.getTime();

        return weekStartEnd;
    }

    /**
     * Get a list of weeks between start and end dates
     *
     * @param start
     * @param end
     * @param weekendStartDay
     * @return List of Date[] in which Date[0] is week start date, Date[1] is week end date
     */
    public static List<Date[]> getWeeksInDateRange(Date start, Date end, WeekendStartDay weekendStartDay) {
        List<Date[]> result = new ArrayList<>();

        Calendar curDate = Calendar.getInstance();
        curDate.setTime(start);

        Date[] weekStartEnd;
        while (!curDate.getTime().after(end)) {
            weekStartEnd = getWeekForDate(curDate.getTime(), weekendStartDay);
            result.add(weekStartEnd);

            curDate.setTime(weekStartEnd[1]);

            // Add 1ms to advance to next day
            curDate.add(Calendar.MILLISECOND, 1);
        }

        return result;
    }

    public static boolean isTheSameDate(Date utcDate1, Date utcDate2, String timezone) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(ScheduleUtils.convertToTargetTimeZone(utcDate1, timezone));
        calendar2.setTime(ScheduleUtils.convertToTargetTimeZone(utcDate2, timezone));

        boolean sameDay = calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
                calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);

        return sameDay;
    }


    /**
     * Get start date and end date of a month in which the specified date belongs to
     *
     * @param d
     * @return
     */
    public static Date[] getMonthForDate(Date d) {
        Date[] monthStartEnd = new Date[2];
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);

        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        monthStartEnd[0] = cal.getTime();

        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        monthStartEnd[1] = cal.getTime();

        return monthStartEnd;
    }

    /**
     * Get all months between start and end dates
     *
     * @param start
     * @param end
     * @return
     */
    public static List<Date[]> getMonthsInDateRange(Date start, Date end) {
        List<Date[]> result = new ArrayList<>();

        Calendar curDate = Calendar.getInstance();
        curDate.setTime(start);

        Date[] monthStartEnd;
        while (!curDate.getTime().after(end)) {
            monthStartEnd = getMonthForDate(curDate.getTime());
            result.add(monthStartEnd);

            curDate.setTime(monthStartEnd[1]);

            // Add 1ms to advance to next month
            curDate.add(Calendar.MILLISECOND, 1);
        }

        return result;
    }

    /**
     * Get start date and end date of a year in which the specified date belongs to
     *
     * @param d
     * @return
     */
    public static Date[] getYearForDate(Date d) {
        Date[] yearStartEnd = new Date[2];
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);

        cal.set(Calendar.DAY_OF_YEAR, cal.getActualMinimum(Calendar.DAY_OF_YEAR));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        yearStartEnd[0] = cal.getTime();

        cal.set(Calendar.DAY_OF_YEAR, cal.getActualMaximum(Calendar.DAY_OF_YEAR));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        yearStartEnd[1] = cal.getTime();

        return yearStartEnd;
    }

    /**
     * Get all years between start and end dates
     *
     * @param start
     * @param end
     * @return
     */
    public static List<Date[]> getYearsInDateRange(Date start, Date end) {
        List<Date[]> result = new ArrayList<>();

        Calendar curDate = Calendar.getInstance();
        curDate.setTime(start);

        Date[] yearStartEnd;
        while (!curDate.getTime().after(end)) {
            yearStartEnd = getYearForDate(curDate.getTime());
            result.add(yearStartEnd);

            curDate.setTime(yearStartEnd[1]);

            // Add 1ms to advance to next month
            curDate.add(Calendar.MILLISECOND, 1);
        }

        return result;
    }

    /**
     * Converts number of hours to milliseconds
     *
     * @param hours
     * @return
     */
    public static long toMilliseconds(BigDecimal hours) {
        return (long)(hours.floatValue() * 60 * 60 * 1000);
    }

    /**
     * Converts milliseconds to number of hours
     *
     * @param milliseconds
     * @return
     */
    private static float toHours(long milliseconds) {
        long secs = milliseconds / 1000;
        float hours = (int) secs / 3600;
        float minutes = (int) secs % 3600f / 60f;
        float seconds = (int) secs % 60f;

        return (float) Math.round((hours + (minutes / 60f) + (seconds / 3600f)) * 100f) / 100f;
    }

    /**
     * Adds number of days to date
     * 
     * @param date
     * @param numberOfDays
     * @return
     */
    public static Date addDays(Date date, int numberOfDays) {
        Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(date); 
        calendar.add(Calendar.DATE, numberOfDays);
        return calendar.getTime();
    }

    /**
     * This will populate the ScheduleEventTotalHours in SECOND unit
     * @param event
     */
    public static void populateScheduleEventTotalHours(ScheduleEvent event){

        if(event == null || event.getScheduleEventEndDatetime() == null || event.getScheduleEventStartDatetime() == null){
            //Cannot populate data for ScheduleEventTotalHours
            return;
        }

        if (event.getScheduleEventEndDatetime().before(event.getScheduleEventStartDatetime())) {
            throw new SandataRuntimeException(
                    String.format("This is an invalid Schedule event: Starttime: %s - Endtime: %s",
                            event.getScheduleEventStartDatetime(), event.getScheduleEventEndDatetime()));
        }

        long diff = event.getScheduleEventEndDatetime().getTime() - event.getScheduleEventStartDatetime().getTime();
        long diffSeconds = (diff / 1000);

        event.setScheduleEventTotalHours(BigDecimal.valueOf(diffSeconds));
    }

    public static void populateScheduleEventOvernightIndicator(ScheduleEvntExt scheduleEvent) {
        if(scheduleEvent.getScheduleEventStartDatetime() != null
                && scheduleEvent.getScheduleEventEndDatetime() != null
                && !StringUtil.IsNullOrEmpty(scheduleEvent.getTimezoneName())){

            boolean isOvernight = !ScheduleUtils.isTheSameDate(scheduleEvent.getScheduleEventStartDatetime(),
                    scheduleEvent.getScheduleEventEndDatetime(),
                    scheduleEvent.getTimezoneName());

            scheduleEvent.setScheduleEventOvernight(isOvernight);
        }
    }
}
