package com.sandata.lab.rest.sched.validation;

import com.sandata.lab.common.utils.date.DateUtil;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.extended.schedule.ScheduleExt;
import com.sandata.lab.data.model.dl.model.extended.schedule.SelectedDaysOfWeek;
import com.sandata.lab.rest.sched.impl.OracleDataService;
import com.sandata.lab.rest.sched.impl.RestDataService;
import com.sandata.lab.rest.sched.model.ScheduleEventCountAndDuration;
import com.sandata.lab.rest.sched.utils.ScheduleUtils;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by khangle on 10/18/2016.
 */
public class DailyAuthorizationLimitScheduleValidation extends ScheduleValidationBaseTest {

    private Authorization auth_with_6_units_per_day;

    private Authorization auth_with_1_live_in_per_day_5_live_in_total;

    private Contract contract_with_15_minutes_per_unit;

    @Override
    protected void onSetup() throws Exception {
        auth_with_6_units_per_day = new Authorization();
        auth_with_6_units_per_day.setAuthorizationSK(new BigInteger("1"));
        auth_with_6_units_per_day.setAuthorizationID("1111");
        auth_with_6_units_per_day.setBusinessEntityID("1");
        auth_with_6_units_per_day.setPatientID("1");
        auth_with_6_units_per_day.setAuthorizationStartTimestamp(DATE_FORMAT.parse("2016-01-01T00:00:00Z"));
        auth_with_6_units_per_day.setAuthorizationEndTimestamp(DATE_FORMAT.parse("2016-12-31T00:00:00Z"));
        auth_with_6_units_per_day.setAuthorizationLimitTypeName(AuthorizationLimitTypeName.DAY);
        auth_with_6_units_per_day.setAuthorizationServiceUnitName(AuthorizationServiceUnitName.UNIT);
        auth_with_6_units_per_day.setAuthorizationLimit(new BigDecimal(6));

        auth_with_1_live_in_per_day_5_live_in_total = new Authorization();
        auth_with_1_live_in_per_day_5_live_in_total.setAuthorizationSK(new BigInteger("1"));
        auth_with_1_live_in_per_day_5_live_in_total.setAuthorizationID("1111");
        auth_with_1_live_in_per_day_5_live_in_total.setBusinessEntityID("1");
        auth_with_1_live_in_per_day_5_live_in_total.setPatientID("1");
        auth_with_1_live_in_per_day_5_live_in_total.setAuthorizationStartTimestamp(DATE_FORMAT.parse("2016-01-01T00:00:00Z"));
        auth_with_1_live_in_per_day_5_live_in_total.setAuthorizationEndTimestamp(DATE_FORMAT.parse("2016-12-31T00:00:00Z"));
        auth_with_1_live_in_per_day_5_live_in_total.setAuthorizationLimitTypeName(AuthorizationLimitTypeName.DAY);
        auth_with_1_live_in_per_day_5_live_in_total.setAuthorizationServiceUnitName(AuthorizationServiceUnitName.LIVE_IN);
        auth_with_1_live_in_per_day_5_live_in_total.setAuthorizationLimit(new BigDecimal(1));
        auth_with_1_live_in_per_day_5_live_in_total.setAuthorizationLimitTotal(new BigDecimal("5"));

        contract_with_15_minutes_per_unit = new Contract();
        contract_with_15_minutes_per_unit.setContractServiceUnitName(ServiceUnitName.UNIT);
        contract_with_15_minutes_per_unit.setContractServiceUnitEquivalence(new BigInteger("15"));
    }

    @Test
    public void test_validate_auth_limit_type_name_day_service_unit_name_visit() throws Exception {
        LocalTime startTime = DateUtil.parseShortTime("09:00 AM");
        LocalTime endTime = DateUtil.parseShortTime("12:00 PM");

        // Authorization: 2 Visits/day
        Authorization auth = create_authorization_limit_type_name_is_day_and_service_unit_name_is_visit();
        Contract contr = create_contract_with_hour_service_unit_name();

        SelectedDaysOfWeek selectedDaysOfWeek = new SelectedDaysOfWeek();
        selectedDaysOfWeek.setMonday(true);
        selectedDaysOfWeek.setMondayStart(startTime);
        selectedDaysOfWeek.setMondayEnd(endTime);

        selectedDaysOfWeek.setWednesday(true);
        selectedDaysOfWeek.setWednesdayStart(startTime);
        selectedDaysOfWeek.setWednesdayEnd(endTime);

        selectedDaysOfWeek.setFriday(true);
        selectedDaysOfWeek.setFridayStart(startTime);
        selectedDaysOfWeek.setFridayEnd(endTime);

        ScheduleExt schedExt = new ScheduleExt();
        schedExt.setAuthorizationSK(new BigInteger("1"));
        schedExt.setAuthorizationID("1111");
        schedExt.setSelectedDaysOfWeek(selectedDaysOfWeek);
        schedExt.setScheduleStartDate(DATE_FORMAT.parse("2016-10-01T09:00:00Z"));
        schedExt.setScheduleEndDate(DATE_FORMAT.parse("2016-10-31T09:00:00Z"));

        ScheduleEventCountAndDuration scheduleEventCountAndDuration1 = new ScheduleEventCountAndDuration();
        scheduleEventCountAndDuration1.setScheduleEventStartDate(DATE_ONLY_FORMAT.parse("2016-10-03"));
        scheduleEventCountAndDuration1.setScheduleEventEndDate(DATE_ONLY_FORMAT.parse("2016-10-03"));
        scheduleEventCountAndDuration1.setScheduleEventCount(1);
        scheduleEventCountAndDuration1.setTotalDuration(0);

        ScheduleEventCountAndDuration scheduleEventCountAndDuration2 = new ScheduleEventCountAndDuration();
        scheduleEventCountAndDuration2.setScheduleEventStartDate(DATE_ONLY_FORMAT.parse("2016-10-05"));
        scheduleEventCountAndDuration2.setScheduleEventEndDate(DATE_ONLY_FORMAT.parse("2016-10-05"));
        scheduleEventCountAndDuration2.setScheduleEventCount(1);
        scheduleEventCountAndDuration2.setTotalDuration(0);

        ScheduleEventCountAndDuration scheduleEventCountAndDuration3 = new ScheduleEventCountAndDuration();
        scheduleEventCountAndDuration1.setScheduleEventStartDate(DATE_ONLY_FORMAT.parse("2016-10-07"));
        scheduleEventCountAndDuration1.setScheduleEventEndDate(DATE_ONLY_FORMAT.parse("2016-10-07"));
        scheduleEventCountAndDuration1.setScheduleEventCount(1);
        scheduleEventCountAndDuration1.setTotalDuration(0);

        Map<String, ScheduleEventCountAndDuration> schedEventCountMap = new HashMap<>();
        schedEventCountMap.put("2016-10-03", scheduleEventCountAndDuration1);
        schedEventCountMap.put("2016-10-05", scheduleEventCountAndDuration2);
        schedEventCountMap.put("2016-10-07", scheduleEventCountAndDuration3);

        OracleDataService oracleDataService = mock(OracleDataService.class);
        when(oracleDataService.getAuthorization(schedExt.getAuthorizationSK().longValue())).thenReturn(auth);
        when(oracleDataService.getContractForAuthService(any(String.class), any(Long.class), any(Boolean.class))).thenReturn(new Contract());
//        when(oracleDataService.getDailyScheduleEventCountInRange(any(String.class), any(String.class), any(List.class))).thenReturn(schedEventCountMap);

        RestDataService restDataService = new RestDataService();
        restDataService.setOracleDataService(oracleDataService);

        exchange.getIn().setBody(schedExt);
        restDataService.validateSchedule(exchange);
        System.out.println("Validation message: " + exchange.getIn().getBody());
        Assert.assertTrue(exchange.getIn().getBody().toString().contains("status=true"));

        ScheduleEventCountAndDuration scheduleEventCountAndDuration4 = new ScheduleEventCountAndDuration();
        scheduleEventCountAndDuration4.setScheduleEventStartDate(DATE_ONLY_FORMAT.parse("2016-10-08"));
        scheduleEventCountAndDuration4.setScheduleEventEndDate(DATE_ONLY_FORMAT.parse("2016-10-08"));
        scheduleEventCountAndDuration4.setScheduleEventCount(2);
        scheduleEventCountAndDuration4.setTotalDuration(0);

        ScheduleEventCountAndDuration scheduleEventCountAndDuration5 = new ScheduleEventCountAndDuration();
        scheduleEventCountAndDuration5.setScheduleEventStartDate(DATE_ONLY_FORMAT.parse("2016-10-09"));
        scheduleEventCountAndDuration5.setScheduleEventEndDate(DATE_ONLY_FORMAT.parse("2016-10-09"));
        scheduleEventCountAndDuration5.setScheduleEventCount(5);
        scheduleEventCountAndDuration5.setTotalDuration(0);

        schedEventCountMap.put("2016-10-08", scheduleEventCountAndDuration4);
        schedEventCountMap.put("2016-10-09", scheduleEventCountAndDuration5);
        exchange.getIn().setBody(schedExt);
        restDataService.validateSchedule(exchange);
        System.out.println("Validation message: " + exchange.getIn().getBody());
        Assert.assertTrue(exchange.getIn().getBody().toString().contains("status=false"));
    }

    @Test
    public void test_validate_auth_limit_type_name_day_service_unit_name_unit() throws Exception {
        LocalTime startTime = DateUtil.parseShortTime("09:00 AM");
        LocalTime endTime = DateUtil.parseShortTime("10:00 AM");

        SelectedDaysOfWeek selectedDaysOfWeek = new SelectedDaysOfWeek();
        selectedDaysOfWeek.setMonday(true);
        selectedDaysOfWeek.setMondayStart(DateUtil.parseShortTime("09:00 AM"));
        selectedDaysOfWeek.setMondayEnd(DateUtil.parseShortTime("10:30 AM"));

        selectedDaysOfWeek.setWednesday(true);
        selectedDaysOfWeek.setWednesdayStart(startTime);
        selectedDaysOfWeek.setWednesdayEnd(endTime);

        selectedDaysOfWeek.setFriday(true);
        selectedDaysOfWeek.setFridayStart(startTime);
        selectedDaysOfWeek.setFridayEnd(endTime);

        ScheduleExt schedExt = new ScheduleExt();
        schedExt.setAuthorizationSK(new BigInteger("1"));
        schedExt.setAuthorizationID("1111");
        schedExt.setSelectedDaysOfWeek(selectedDaysOfWeek);
        schedExt.setScheduleStartDate(DATE_FORMAT.parse("2016-10-01T09:00:00Z"));
        schedExt.setScheduleEndDate(DATE_FORMAT.parse("2016-10-31T09:00:00Z"));

        OracleDataService oracleDataService = mock(OracleDataService.class);
        when(oracleDataService.getAuthorization(any(Long.class))).thenReturn(auth_with_6_units_per_day);
        when(oracleDataService.getContractForAuthService(any(String.class), any(Long.class), any(Boolean.class))).thenReturn(contract_with_15_minutes_per_unit);

        RestDataService restDataService = new RestDataService();
        restDataService.setOracleDataService(oracleDataService);

        exchange.getIn().setBody(schedExt);
        restDataService.validateSchedule(exchange);
        System.out.println("Validation message: " + exchange.getIn().getBody());
        Assert.assertTrue(exchange.getIn().getBody().toString().contains("status=true"));

        // Set Limit Total to 60
        auth_with_6_units_per_day.setAuthorizationLimitTotal(new BigDecimal(60));
        exchange.getIn().setBody(schedExt);
        restDataService.validateSchedule(exchange);
        System.out.println("Validation message: " + exchange.getIn().getBody());
        Assert.assertTrue(exchange.getIn().getBody().toString().contains("status=false"));
    }

    @Test
    public void test_validate_auth_limit_type_name_day_service_unit_name_live_in() throws Exception {
        LocalTime startTime = DateUtil.parseShortTime("08:00 AM");
        LocalTime endTime = DateUtil.parseShortTime("08:00 PM");

        SelectedDaysOfWeek selectedDaysOfWeek = new SelectedDaysOfWeek();
        selectedDaysOfWeek.setMonday(true);
        selectedDaysOfWeek.setMondayStart(startTime);
        selectedDaysOfWeek.setMondayEnd(endTime);

        selectedDaysOfWeek.setWednesday(true);
        selectedDaysOfWeek.setWednesdayStart(startTime);
        selectedDaysOfWeek.setWednesdayEnd(endTime);

        selectedDaysOfWeek.setFriday(true);
        selectedDaysOfWeek.setFridayStart(startTime);
        selectedDaysOfWeek.setFridayEnd(endTime);

        ScheduleExt schedExt = new ScheduleExt();
        schedExt.setAuthorizationSK(new BigInteger("1"));
        schedExt.setAuthorizationID("1111");
        schedExt.setSelectedDaysOfWeek(selectedDaysOfWeek);
        schedExt.setScheduleStartDate(DATE_FORMAT.parse("2016-10-01T09:00:00Z"));
        schedExt.setScheduleEndDate(DATE_FORMAT.parse("2016-10-31T09:00:00Z"));

        OracleDataService oracleDataService = mock(OracleDataService.class);
        when(oracleDataService.getAuthorization(any(Long.class))).thenReturn(auth_with_1_live_in_per_day_5_live_in_total);
        when(oracleDataService.getContractForAuthService(any(String.class), any(Long.class), any(Boolean.class))).thenReturn(new Contract());

        RestDataService restDataService = new RestDataService();
        restDataService.setOracleDataService(oracleDataService);

        exchange.getIn().setBody(schedExt);
        restDataService.validateSchedule(exchange);
        System.out.println("Validation message: " + exchange.getIn().getBody());
        Assert.assertTrue(exchange.getIn().getBody().toString()
                .contains("Total duration is 156.00 hours from 2016-10-01 to 2016-10-31 exceeds Authorization Limit Total of 120.00"));
    }
}
