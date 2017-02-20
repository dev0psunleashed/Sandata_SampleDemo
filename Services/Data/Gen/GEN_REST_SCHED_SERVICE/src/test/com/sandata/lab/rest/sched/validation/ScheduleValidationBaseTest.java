package com.sandata.lab.rest.sched.validation;

import com.sandata.lab.common.utils.date.DateUtil;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.rest.sched.BaseTestSupport;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;

/**
 * Created by khangle on 10/12/2016.
 */
public abstract class ScheduleValidationBaseTest extends BaseTestSupport {

    protected static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DateUtil.SANDATA_UTC_DATE_TIME_FORMAT);
    protected static final SimpleDateFormat DATE_ONLY_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    protected Contract create_contract_with_hour_service_unit_name() {
        Contract contr = new Contract();
        contr.setContractServiceUnitName(ServiceUnitName.HOUR);
        contr.setContractServiceUnitEquivalence(new BigInteger("1"));

        return contr;
    }

    protected Authorization creat_authorization_40_hours_per_week() throws Exception {
        Authorization auth = new Authorization();
        auth.setAuthorizationSK(new BigInteger("1"));
        auth.setAuthorizationID("1111");
        auth.setAuthorizationStartTimestamp(DATE_FORMAT.parse("2016-10-10T00:00:00Z"));
        auth.setAuthorizationEndTimestamp(DATE_FORMAT.parse("2016-12-30T23:00:00Z"));
        auth.setAuthorizationLimitTypeName(AuthorizationLimitTypeName.WEEK);
        auth.setAuthorizationServiceUnitName(AuthorizationServiceUnitName.HOUR);
        auth.setAuthorizationLimit(new BigDecimal(40));

        return auth;
    }

    protected Authorization creat_authorization_40_hours_per_month_60_hours_total_limit() throws Exception {
        Authorization auth = new Authorization();
        auth.setAuthorizationSK(new BigInteger("1"));
        auth.setAuthorizationID("1111");
        auth.setAuthorizationStartTimestamp(DATE_FORMAT.parse("2016-10-10T00:00:00Z"));
        auth.setAuthorizationEndTimestamp(DATE_FORMAT.parse("2016-12-31T00:00:00Z"));
        auth.setAuthorizationLimitTypeName(AuthorizationLimitTypeName.MONTH);
        auth.setAuthorizationServiceUnitName(AuthorizationServiceUnitName.HOUR);
        auth.setAuthorizationLimit(new BigDecimal(40));
        auth.setAuthorizationLimitTotal(new BigDecimal(60));

        return auth;
    }

    protected Authorization creat_authorization_200_hours_per_year() throws Exception {
        Authorization auth = new Authorization();
        auth.setAuthorizationSK(new BigInteger("1"));
        auth.setAuthorizationID("1111");
        auth.setAuthorizationStartTimestamp(DATE_FORMAT.parse("2016-10-10T00:00:00Z"));
        auth.setAuthorizationEndTimestamp(DATE_FORMAT.parse("2017-10-09T00:00:00Z"));
        auth.setAuthorizationLimitTypeName(AuthorizationLimitTypeName.YEAR);
        auth.setAuthorizationServiceUnitName(AuthorizationServiceUnitName.HOUR);
        auth.setAuthorizationLimit(new BigDecimal(200));

        return auth;
    }

    /**
     * SAN-31: verifying "Incorrect prevent while user is going within allowed hourly"
     *
     * @return
     * @throws Exception
     */
    protected Authorization create_authorization_2_hours_per_day() throws Exception {
        Authorization auth = new Authorization();
        auth.setAuthorizationSK(new BigInteger("1"));
        auth.setAuthorizationID("1111");
        auth.setAuthorizationStartTimestamp(DATE_FORMAT.parse("2016-01-01T00:00:00Z"));
        auth.setAuthorizationEndTimestamp(DATE_FORMAT.parse("2016-12-31T00:00:00Z"));
        auth.setAuthorizationLimitTypeName(AuthorizationLimitTypeName.DAY);
        auth.setAuthorizationServiceUnitName(AuthorizationServiceUnitName.HOUR);
        auth.setAuthorizationLimit(new BigDecimal(2));

        auth.setAuthorizationLimitStartTimeDay4(DATE_FORMAT.parse("2016-10-05T09:00:00Z"));
        auth.setAuthorizationLimitEndTimeDay4(DATE_FORMAT.parse("2016-10-05T11:00:00Z"));

        return auth;
    }

    /**
     * Create Authorization: 2 Visits/day
     *
     * @return
     * @throws Exception
     */
    protected Authorization create_authorization_limit_type_name_is_day_and_service_unit_name_is_visit() throws Exception {
        Authorization auth = new Authorization();
        auth.setAuthorizationSK(new BigInteger("1"));
        auth.setAuthorizationID("1111");
        auth.setBusinessEntityID("1");
        auth.setPatientID("1");
        auth.setAuthorizationStartTimestamp(DATE_FORMAT.parse("2016-01-01T00:00:00Z"));
        auth.setAuthorizationEndTimestamp(DATE_FORMAT.parse("2016-12-31T00:00:00Z"));
        auth.setAuthorizationLimitTypeName(AuthorizationLimitTypeName.DAY);
        auth.setAuthorizationServiceUnitName(AuthorizationServiceUnitName.VISIT);
        auth.setAuthorizationLimit(new BigDecimal(2));

        return auth;
    }

    /**
     * Create Authorization: 5 Visits/week
     *
     * @return
     * @throws Exception
     */
    protected Authorization create_authorization_limit_type_name_is_week_and_service_unit_name_is_visit() throws Exception {
        Authorization auth = new Authorization();
        auth.setAuthorizationSK(new BigInteger("1"));
        auth.setAuthorizationID("1111");
        auth.setBusinessEntityID("1");
        auth.setPatientID("1");
        auth.setAuthorizationStartTimestamp(DATE_FORMAT.parse("2016-01-01T00:00:00Z"));
        auth.setAuthorizationEndTimestamp(DATE_FORMAT.parse("2016-12-31T00:00:00Z"));
        auth.setAuthorizationLimitTypeName(AuthorizationLimitTypeName.WEEK);
        auth.setAuthorizationServiceUnitName(AuthorizationServiceUnitName.VISIT);
        auth.setAuthorizationLimit(new BigDecimal(5));

        return auth;
    }

    /**
     * Create Authorization: 20 Visits/month
     *
     * @return
     * @throws Exception
     */
    protected Authorization create_authorization_limit_type_name_is_month_and_service_unit_name_is_visit() throws Exception {
        Authorization auth = new Authorization();
        auth.setAuthorizationSK(new BigInteger("1"));
        auth.setAuthorizationID("1111");
        auth.setBusinessEntityID("1");
        auth.setPatientID("1");
        auth.setAuthorizationStartTimestamp(DATE_FORMAT.parse("2016-01-01T00:00:00Z"));
        auth.setAuthorizationEndTimestamp(DATE_FORMAT.parse("2016-12-31T00:00:00Z"));
        auth.setAuthorizationLimitTypeName(AuthorizationLimitTypeName.MONTH);
        auth.setAuthorizationServiceUnitName(AuthorizationServiceUnitName.VISIT);
        auth.setAuthorizationLimit(new BigDecimal(20));

        return auth;
    }

    /**
     * Create Authorization: 200 Visits/year
     *
     * @return
     * @throws Exception
     */
    protected Authorization create_authorization_limit_type_name_is_year_and_service_unit_name_is_visit() throws Exception {
        Authorization auth = new Authorization();
        auth.setAuthorizationSK(new BigInteger("1"));
        auth.setAuthorizationID("1111");
        auth.setBusinessEntityID("1");
        auth.setPatientID("1");
        auth.setAuthorizationStartTimestamp(DATE_FORMAT.parse("2016-01-01T00:00:00Z"));
        auth.setAuthorizationEndTimestamp(DATE_FORMAT.parse("2016-12-31T00:00:00Z"));
        auth.setAuthorizationLimitTypeName(AuthorizationLimitTypeName.YEAR);
        auth.setAuthorizationServiceUnitName(AuthorizationServiceUnitName.VISIT);
        auth.setAuthorizationLimit(new BigDecimal(200));

        return auth;
    }
}
