package com.sandata.lab.rest.sched.validation;

import com.sandata.lab.common.utils.date.DateUtil;
import com.sandata.lab.data.model.dl.model.Authorization;
import com.sandata.lab.data.model.dl.model.AuthorizationLimitTypeName;
import com.sandata.lab.data.model.dl.model.Contract;
import com.sandata.lab.data.model.dl.model.ServiceUnitName;
import com.sandata.lab.data.model.dl.model.extended.schedule.ScheduleExt;
import com.sandata.lab.data.model.dl.model.extended.schedule.SelectedDaysOfWeek;
import com.sandata.lab.rest.sched.impl.OracleDataService;
import com.sandata.lab.rest.sched.impl.RestDataService;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by khangle on 10/18/2016.
 */
public class MonthlyAuthorizationLimitScheduleValidation extends ScheduleValidationBaseTest {

    private Authorization auth_with_10_visits_per_month;

    private Authorization auth_with_30_units_per_month;

    private Authorization auth_with_4_live_in_per_month;

    private Contract contract_with_60_minutes_per_unit;
    
    @Override
    protected void onSetup() throws Exception {
        auth_with_10_visits_per_month = new Authorization();
        auth_with_10_visits_per_month.setAuthorizationSK(new BigInteger("1"));
        auth_with_10_visits_per_month.setAuthorizationID("1111");
        auth_with_10_visits_per_month.setBusinessEntityID("1");
        auth_with_10_visits_per_month.setPatientID("1");
        auth_with_10_visits_per_month.setAuthorizationStartTimestamp(DATE_FORMAT.parse("2016-01-01T00:00:00Z"));
        auth_with_10_visits_per_month.setAuthorizationEndTimestamp(DATE_FORMAT.parse("2016-12-31T00:00:00Z"));
        auth_with_10_visits_per_month.setAuthorizationLimitTypeName(AuthorizationLimitTypeName.MONTH);
        auth_with_10_visits_per_month.setAuthorizationServiceUnitName(ServiceUnitName.VISIT.value());
        auth_with_10_visits_per_month.setAuthorizationLimit(new BigDecimal(10));

        auth_with_30_units_per_month = new Authorization();
        auth_with_30_units_per_month.setAuthorizationSK(new BigInteger("1"));
        auth_with_30_units_per_month.setAuthorizationID("1111");
        auth_with_30_units_per_month.setBusinessEntityID("1");
        auth_with_30_units_per_month.setPatientID("1");
        auth_with_30_units_per_month.setAuthorizationStartTimestamp(DATE_FORMAT.parse("2016-01-01T00:00:00Z"));
        auth_with_30_units_per_month.setAuthorizationEndTimestamp(DATE_FORMAT.parse("2016-12-31T00:00:00Z"));
        auth_with_30_units_per_month.setAuthorizationLimitTypeName(AuthorizationLimitTypeName.MONTH);
        auth_with_30_units_per_month.setAuthorizationServiceUnitName(ServiceUnitName.UNIT.value());
        auth_with_30_units_per_month.setAuthorizationLimit(new BigDecimal(30));

        auth_with_4_live_in_per_month = new Authorization();
        auth_with_4_live_in_per_month.setAuthorizationSK(new BigInteger("1"));
        auth_with_4_live_in_per_month.setAuthorizationID("1111");
        auth_with_4_live_in_per_month.setBusinessEntityID("1");
        auth_with_4_live_in_per_month.setPatientID("1");
        auth_with_4_live_in_per_month.setAuthorizationStartTimestamp(DATE_FORMAT.parse("2016-01-01T00:00:00Z"));
        auth_with_4_live_in_per_month.setAuthorizationEndTimestamp(DATE_FORMAT.parse("2016-12-31T00:00:00Z"));
        auth_with_4_live_in_per_month.setAuthorizationLimitTypeName(AuthorizationLimitTypeName.MONTH);
        auth_with_4_live_in_per_month.setAuthorizationServiceUnitName(ServiceUnitName.LIVE_IN.value());
        auth_with_4_live_in_per_month.setAuthorizationLimit(new BigDecimal(4));

        contract_with_60_minutes_per_unit = new Contract();
        contract_with_60_minutes_per_unit.setContractServiceUnitName(ServiceUnitName.UNIT);
        contract_with_60_minutes_per_unit.setContractServiceUnitEquivalence(new BigInteger("60"));
    }

    @Test
    public void test_validate_auth_limit_type_name_month_service_unit_name_visit() throws Exception {
        LocalTime startTime = DateUtil.parseShortTime("09:00 AM");
        LocalTime endTime = DateUtil.parseShortTime("11:30 AM");

        SelectedDaysOfWeek selectedDaysOfWeek = new SelectedDaysOfWeek();
        selectedDaysOfWeek.setMonday(true);
        selectedDaysOfWeek.setMondayStart(startTime);
        selectedDaysOfWeek.setMondayEnd(endTime);

        selectedDaysOfWeek.setWednesday(true);
        selectedDaysOfWeek.setWednesdayStart(startTime);
        selectedDaysOfWeek.setWednesdayEnd(endTime);

        ScheduleExt schedExt = new ScheduleExt();
        schedExt.setAuthorizationSK(new BigInteger("1"));
        schedExt.setAuthorizationID("1111");
        schedExt.setSelectedDaysOfWeek(selectedDaysOfWeek);
        schedExt.setScheduleStartDate(DATE_FORMAT.parse("2016-10-01T09:00:00Z"));
        schedExt.setScheduleEndDate(DATE_FORMAT.parse("2016-10-31T09:00:00Z"));

        OracleDataService oracleDataService = mock(OracleDataService.class);
        when(oracleDataService.getAuthorization(any(Long.class))).thenReturn(auth_with_10_visits_per_month);
        when(oracleDataService.getContractForAuthService(any(String.class), any(Long.class), any(Boolean.class))).thenReturn(new Contract());
//        when(oracleDataService.getScheduleEventCountInRange(any(String.class), any(String.class), any(Date.class), any(Date.class)))
//                .thenReturn(1); // Already having 1 visit in the week

        RestDataService restDataService = new RestDataService();
        restDataService.setOracleDataService(oracleDataService);

        // There are 2 visits per week on Monday, Wednesday --> 9 visits from 10/1/2016 - 10/31/2016
        // Plus 1 already in DB, hence 10 total visits in a month --> still doesn't exceed auth limit
        exchange.getIn().setBody(schedExt);
        restDataService.validateSchedule(exchange);
        System.out.println("Validation message: " + exchange.getIn().getBody());
        Assert.assertTrue(exchange.getIn().getBody().toString().contains("status=true"));

        // Add 1 more visit on Saturday to every week --> 14 visits from 10/1/2016 - 10/31/2016
        // Plus 1 already in DB, hence 15 total visits in a month --> exceeds auth limit
        selectedDaysOfWeek.setSaturday(true);
        selectedDaysOfWeek.setSaturdayStart(startTime);
        selectedDaysOfWeek.setSaturdayEnd(endTime);

        exchange.getIn().setBody(schedExt);
        restDataService.validateSchedule(exchange);
        System.out.println("Validation message: " + exchange.getIn().getBody());
        Assert.assertTrue(exchange.getIn().getBody().toString().contains("status=false"));
    }

    @Test
    public void test_validate_auth_limit_type_name_month_service_unit_name_unit() throws Exception {
        LocalTime startTime = DateUtil.parseShortTime("09:00 AM");
        LocalTime endTime = DateUtil.parseShortTime("11:00 AM");

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
        when(oracleDataService.getAuthorization(any(Long.class))).thenReturn(auth_with_30_units_per_month);
        when(oracleDataService.getContractForAuthService(any(String.class), any(Long.class), any(Boolean.class))).thenReturn(contract_with_60_minutes_per_unit);

        RestDataService restDataService = new RestDataService();
        restDataService.setOracleDataService(oracleDataService);

        exchange.getIn().setBody(schedExt);
        restDataService.validateSchedule(exchange);
        System.out.println("Validation message: " + exchange.getIn().getBody());
        Assert.assertTrue(exchange.getIn().getBody().toString().contains("status=true"));

        selectedDaysOfWeek.setSaturday(true);
        selectedDaysOfWeek.setSaturdayStart(startTime);
        selectedDaysOfWeek.setSaturdayEnd(endTime);

        exchange.getIn().setBody(schedExt);
        restDataService.validateSchedule(exchange);
        System.out.println("Validation message: " + exchange.getIn().getBody());
        Assert.assertTrue(exchange.getIn().getBody().toString().contains("status=false"));
    }

    @Test
    public void test_validate_auth_limit_type_name_month_service_unit_name_live_in() throws Exception {
        LocalTime startTime = DateUtil.parseShortTime("09:00 AM");
        LocalTime endTime = DateUtil.parseShortTime("04:00 PM");

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
        when(oracleDataService.getAuthorization(any(Long.class))).thenReturn(auth_with_4_live_in_per_month);
        when(oracleDataService.getContractForAuthService(any(String.class), any(Long.class), any(Boolean.class))).thenReturn(new Contract());

        RestDataService restDataService = new RestDataService();
        restDataService.setOracleDataService(oracleDataService);

        exchange.getIn().setBody(schedExt);
        restDataService.validateSchedule(exchange);
        System.out.println("Validation message: " + exchange.getIn().getBody());
        Assert.assertTrue(exchange.getIn().getBody().toString().contains("status=true"));

        selectedDaysOfWeek.setSaturday(true);
        selectedDaysOfWeek.setSaturdayStart(startTime);
        selectedDaysOfWeek.setSaturdayEnd(endTime);

        exchange.getIn().setBody(schedExt);
        restDataService.validateSchedule(exchange);
        System.out.println("Validation message: " + exchange.getIn().getBody());
        Assert.assertTrue(exchange.getIn().getBody().toString().contains("status=false"));
    }
}
