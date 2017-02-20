//package com.sandata.lab.rest.sched.validation;
//
//import com.sandata.lab.common.utils.date.DateUtil;
//import com.sandata.lab.data.model.dl.model.Authorization;
//import com.sandata.lab.data.model.dl.model.AuthorizationLimitTypeName;
//import com.sandata.lab.data.model.dl.model.Contract;
//import com.sandata.lab.data.model.dl.model.extended.schedule.ScheduleExt;
//import com.sandata.lab.data.model.dl.model.extended.schedule.SelectedDaysOfWeek;
//import com.sandata.lab.rest.sched.utils.ScheduleUtils;
//import org.joda.time.LocalTime;
//import org.junit.Assert;
//import org.junit.Test;
//
///**
// * Created by khangle on 10/12/2016.
// */
//public class ScheduleValidationTest extends ScheduleValidationBaseTest {
//
//    @Override
//    protected void onSetup() throws Exception {
//
//    }
//
//    @Test
//    public void test_validate_auth_limit_day_hourly() throws Exception {
//        Authorization auth = create_authorization_2_hours_per_day();
//        Contract contr = create_contract_with_hour_service_unit_name();
//
//        LocalTime startTime = DateUtil.parseShortTime("08:00 AM");
//        LocalTime endTime = DateUtil.parseShortTime("10:00 AM");
//
//        // Schedule 2hrs for Monday
//        SelectedDaysOfWeek selectedDaysOfWeek = new SelectedDaysOfWeek();
//        selectedDaysOfWeek.setMonday(true);
//        selectedDaysOfWeek.setMondayStart(startTime);
//        selectedDaysOfWeek.setMondayEnd(endTime);
//
//        ScheduleExt schedExt = new ScheduleExt();
//        schedExt.setAuthorizationID("1111");
//        schedExt.setSelectedDaysOfWeek(selectedDaysOfWeek);
//        schedExt.setScheduleStartDate(DATE_FORMAT.parse("2016-10-03T08:00:00Z"));
//        schedExt.setScheduleEndDate(DATE_FORMAT.parse("2016-10-10T10:00:00Z"));
//
//        String message = ScheduleUtils.validateDailyDuration(auth, contr, schedExt);
//        System.out.println("Message: " + message);
//        Assert.assertEquals("", message);
//
//        message = ScheduleUtils.validateDailyTimeRanges(auth, schedExt);
//        System.out.println("Message: " + message);
//        Assert.assertEquals("", message);
//
//        // Schedule 2hrs for Tuesday
//        selectedDaysOfWeek.setTuesday(true);
//        selectedDaysOfWeek.setTuesdayStart(startTime);
//        selectedDaysOfWeek.setTuesdayEnd(endTime);
//
//        message = ScheduleUtils.validateDailyDuration(auth, contr, schedExt);
//        Assert.assertEquals("", message);
//        message = ScheduleUtils.validateDailyTimeRanges(auth, schedExt);
//        Assert.assertEquals("", message);
//
//        // Schedule 3hrs for Friday
//        selectedDaysOfWeek.setFriday(true);
//        selectedDaysOfWeek.setFridayStart(startTime);
//        selectedDaysOfWeek.setFridayEnd(DateUtil.parseShortTime("11:00 AM"));
//
//        message = ScheduleUtils.validateDailyDuration(auth, contr, schedExt);
//        System.out.println("Message: " + message);
//        Assert.assertEquals("Invalid time for Friday", message);
//        message = ScheduleUtils.validateDailyTimeRanges(auth, schedExt);
//        System.out.println("Message: " + message);
//        Assert.assertEquals("", message);
//
//
//        // Schedule 2hrs for Wednesday, but outside the range of AuthorizationLimitStartTimeDay4 and AuthorizationLimitEndTimeDay4
//        selectedDaysOfWeek.setWednesday(true);
//        selectedDaysOfWeek.setWednesdayStart(DateUtil.parseShortTime("10:00 AM"));
//        selectedDaysOfWeek.setWednesdayEnd(DateUtil.parseShortTime("12:00 PM"));
//
//        message = ScheduleUtils.validateDailyDuration(auth, contr, schedExt);
//        System.out.println("Message: " + message);
//        Assert.assertEquals("Invalid time for Friday", message);
//        message = ScheduleUtils.validateDailyTimeRanges(auth, schedExt);
//        System.out.println("Message: " + message);
//        Assert.assertEquals("Invalid time for Wednesday", message);
//}
//
//    @Test
//    public void test_validate_auth_limit_week() throws Exception {
//        Authorization auth = creat_authorization_40_hours_per_week();
//        Contract contr = create_contract_with_hour_service_unit_name();
//
//        LocalTime startTime = DateUtil.parseShortTime("09:00 AM");
//        LocalTime endTime = DateUtil.parseShortTime("05:00 PM");
//
//        SelectedDaysOfWeek selectedDaysOfWeek = new SelectedDaysOfWeek();
//        selectedDaysOfWeek.setMonday(true);
//        selectedDaysOfWeek.setMondayStart(startTime);
//        selectedDaysOfWeek.setMondayEnd(endTime);
//
//        selectedDaysOfWeek.setTuesday(true);
//        selectedDaysOfWeek.setTuesdayStart(startTime);
//        selectedDaysOfWeek.setTuesdayEnd(endTime);
//
//        selectedDaysOfWeek.setWednesday(true);
//        selectedDaysOfWeek.setWednesdayStart(startTime);
//        selectedDaysOfWeek.setWednesdayEnd(endTime);
//
//        selectedDaysOfWeek.setThursday(true);
//        selectedDaysOfWeek.setThursdayStart(startTime);
//        selectedDaysOfWeek.setThursdayEnd(endTime);
//
//        selectedDaysOfWeek.setFriday(true);
//        selectedDaysOfWeek.setFridayStart(startTime);
//        selectedDaysOfWeek.setFridayEnd(endTime);
//
//        ScheduleExt schedExt = new ScheduleExt();
//        schedExt.setAuthorizationID("1111");
//        schedExt.setSelectedDaysOfWeek(selectedDaysOfWeek);
//        schedExt.setScheduleStartDate(DATE_FORMAT.parse("2016-10-10T09:00:00Z"));
//        schedExt.setScheduleEndDate(DATE_FORMAT.parse("2016-12-14T19:00:00Z"));
//
//        String message = ScheduleUtils.validateDurationByAuthLimitType(auth, contr, schedExt, AuthorizationLimitTypeName.WEEK);
//        Assert.assertEquals("", message);
//
//        selectedDaysOfWeek.setSaturday(true);
//        selectedDaysOfWeek.setSaturdayStart(startTime);
//        selectedDaysOfWeek.setSaturdayEnd(endTime);
//
//        message = ScheduleUtils.validateDurationByAuthLimitType(auth, contr, schedExt, AuthorizationLimitTypeName.WEEK);
//        Assert.assertEquals("Total duration of 48.00 hours exceeds week Authorization Limit of 40.00", message);
//    }
//
//    @Test
//    public void test_validate_auth_limit_month() throws Exception {
//        Authorization auth = creat_authorization_40_hours_per_month_60_hours_total_limit();
//        Contract contr = create_contract_with_hour_service_unit_name();
//
//        LocalTime startTime = DateUtil.parseShortTime("09:00 AM");
//        LocalTime endTime = DateUtil.parseShortTime("02:00 PM");
//
//        // Scheduled on Monday and Friday, 5hrs/day
//        SelectedDaysOfWeek selectedDaysOfWeek = new SelectedDaysOfWeek();
//        selectedDaysOfWeek.setMonday(true);
//        selectedDaysOfWeek.setMondayStart(startTime);
//        selectedDaysOfWeek.setMondayEnd(endTime);
//
//        ScheduleExt schedExt = new ScheduleExt();
//        schedExt.setAuthorizationID("1111");
//        schedExt.setSelectedDaysOfWeek(selectedDaysOfWeek);
//        schedExt.setScheduleStartDate(DATE_FORMAT.parse("2016-10-10T09:00:00Z"));
//        schedExt.setScheduleEndDate(DATE_FORMAT.parse("2016-12-31T09:00:00Z"));
//
//        String message = ScheduleUtils.validateDurationByAuthLimitType(auth, contr, schedExt, AuthorizationLimitTypeName.MONTH);
//        System.out.println("Message: " + message);
//        Assert.assertEquals("", message);
//
//        message = ScheduleUtils.validateAuthorizationLimitTotal(auth, contr, schedExt);
//        Assert.assertEquals("", message);
//
//        selectedDaysOfWeek.setFriday(true);
//        selectedDaysOfWeek.setFridayStart(startTime);
//        selectedDaysOfWeek.setFridayEnd(endTime);
//
//        message = ScheduleUtils.validateDurationByAuthLimitType(auth, contr, schedExt, AuthorizationLimitTypeName.MONTH);
//        Assert.assertEquals("Total duration of 45.00 hours exceeds month Authorization Limit of 40.00", message);
//        System.out.println("Message: " + message);
//
//        selectedDaysOfWeek.setWednesday(true);
//        selectedDaysOfWeek.setWednesdayStart(startTime);
//        selectedDaysOfWeek.setWednesdayEnd(endTime);
//
//        message = ScheduleUtils.validateAuthorizationLimitTotal(auth, contr, schedExt);
//        Assert.assertTrue(message.contains("exceeds Authorization Limit Total"));
//        System.out.println("Message: " + message);
//    }
//
//    @Test
//    public void test_validate_auth_limit_year() throws Exception {
//        Authorization auth = creat_authorization_200_hours_per_year();
//        Contract contr = create_contract_with_hour_service_unit_name();
//
//        LocalTime startTime = DateUtil.parseShortTime("09:00 AM");
//        LocalTime endTime = DateUtil.parseShortTime("12:00 PM");
//
//        SelectedDaysOfWeek selectedDaysOfWeek = new SelectedDaysOfWeek();
//        selectedDaysOfWeek.setMonday(true);
//        selectedDaysOfWeek.setMondayStart(startTime);
//        selectedDaysOfWeek.setMondayEnd(endTime);
//
//        ScheduleExt schedExt = new ScheduleExt();
//        schedExt.setAuthorizationID("1111");
//        schedExt.setSelectedDaysOfWeek(selectedDaysOfWeek);
//        schedExt.setScheduleStartDate(DATE_FORMAT.parse("2016-10-10T09:00:00Z"));
//        schedExt.setScheduleEndDate(DATE_FORMAT.parse("2017-10-09T09:00:00Z"));
//
//        String message = ScheduleUtils.validateDurationByAuthLimitType(auth, contr, schedExt, AuthorizationLimitTypeName.YEAR);
//        System.out.println("Message: " + message);
//        Assert.assertEquals("", message);
//
//        selectedDaysOfWeek.setWednesday(true);
//        selectedDaysOfWeek.setWednesdayStart(startTime);
//        selectedDaysOfWeek.setWednesdayEnd(endTime);
//
//        message = ScheduleUtils.validateDurationByAuthLimitType(auth, contr, schedExt, AuthorizationLimitTypeName.YEAR);
//        System.out.println("Message: " + message);
//        Assert.assertTrue(message.contains("exceeds year Authorization Limit"));
//    }
//
//}
