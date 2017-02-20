package com.sandata.lab.payroll;

import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.data.model.agency.AgencyGeneralSettings;
import org.junit.Assert;
import org.junit.Test;

/**
 * Date: 5/30/16
 * Time: 11:43 AM
 */

public class AgencyGeneralSettingsTests extends BaseTestSupport {

    private AgencyGeneralSettings agencyGeneralSettings;

    @Test
    public void should_gen_json_string_from_settings_entity() throws Exception {

        agencyGeneralSettings.setMaxSchedulingRecurring(52);
        agencyGeneralSettings.setPayrollFrequency("WEEKLY");
        agencyGeneralSettings.setPayrollEndDay("FRIDAY");
        agencyGeneralSettings.setPayrollEndTime("17:00");
        agencyGeneralSettings.setWeekendStartDay("SATURDAY");
        agencyGeneralSettings.setWeekendStartTime("00:00");
        agencyGeneralSettings.setRequiresNotesOnAllReasonCodess(false);
        agencyGeneralSettings.setRequiresDischargeNote(false);
        agencyGeneralSettings.setEnforceAllReasonCodes(false);
        agencyGeneralSettings.setDefaultScheduleViewToCurrentWeek(true);
        agencyGeneralSettings.setShowVerifiedTimesOnSchedule(true);
        agencyGeneralSettings.setAllowMoreThan24ScheduledHours(false);
        agencyGeneralSettings.setMatchStaffSkillsToPatientRequirements(true);
        agencyGeneralSettings.setMaxAllowedWorkHours(40);
        agencyGeneralSettings.setOvertimeRestriction("WARN");
        agencyGeneralSettings.setRequiresReasonCodeForOvertime(true);

        String json = GSONProvider.ToJson(agencyGeneralSettings);

        Assert.assertNotNull(json);

        System.out.println(json);
    }

    @Override
    protected void onSetup() throws Exception {

        agencyGeneralSettings = new AgencyGeneralSettings();
    }
}
