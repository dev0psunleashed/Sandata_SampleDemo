package com.sandata.lab.data.model.agency;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

/**
 * Date: 5/28/16
 * Time: 10:40 PM
 */

public class AgencyGeneralSettings extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("MaxSchedulingRecurring")
    protected int maxSchedulingRecurring;

    @SerializedName("PayrollFrequency")
    protected String payrollFrequency;

    @SerializedName("PayrollEndDay")
    protected String payrollEndDay;

    @SerializedName("PayrollEndTime")
    protected String payrollEndTime;

    @SerializedName("WeekendStartDay")
    protected String weekendStartDay;

    @SerializedName("WeekendStartTime")
    protected String weekendStartTime;

    @SerializedName("RequiresNotesOnAllReasonCodes")
    protected boolean requiresNotesOnAllReasonCodess;

    @SerializedName("RequiresDischargeNote")
    protected boolean requiresDischargeNote;

    @SerializedName("EnforceAllReasonCodes")
    protected boolean enforceAllReasonCodes;

    @SerializedName("DefaultScheduleViewToCurrentWeek")
    protected boolean defaultScheduleViewToCurrentWeek;

    @SerializedName("ShowVerifiedTimesOnSchedule")
    protected boolean showVerifiedTimesOnSchedule;

    @SerializedName("AllowMoreThan24ScheduledHours")
    protected boolean allowMoreThan24ScheduledHours;

    @SerializedName("MatchStaffSkillsToPatientRequirements")
    protected boolean matchStaffSkillsToPatientRequirements;

    @SerializedName("MaxAllowedWorkHours")
    protected int maxAllowedWorkHours;

    @SerializedName("OvertimeRestriction")
    protected String overtimeRestriction;

    @SerializedName("RequiresReasonCodeForOvertime")
    protected boolean requiresReasonCodeForOvertime;

    public int getMaxSchedulingRecurring() {
        return maxSchedulingRecurring;
    }

    public void setMaxSchedulingRecurring(int maxSchedulingRecurring) {
        this.maxSchedulingRecurring = maxSchedulingRecurring;
    }

    public String getPayrollFrequency() {
        return payrollFrequency;
    }

    public void setPayrollFrequency(String payrollFrequency) {
        this.payrollFrequency = payrollFrequency;
    }

    public String getPayrollEndDay() {
        return payrollEndDay;
    }

    public void setPayrollEndDay(String payrollEndDay) {
        this.payrollEndDay = payrollEndDay;
    }

    public String getPayrollEndTime() {
        return payrollEndTime;
    }

    public void setPayrollEndTime(String payrollEndTime) {
        this.payrollEndTime = payrollEndTime;
    }

    public String getWeekendStartDay() {
        return weekendStartDay;
    }

    public void setWeekendStartDay(String weekendStartDay) {
        this.weekendStartDay = weekendStartDay;
    }

    public String getWeekendStartTime() {
        return weekendStartTime;
    }

    public void setWeekendStartTime(String weekendStartTime) {
        this.weekendStartTime = weekendStartTime;
    }

    public boolean getRequiresNotesOnAllReasonCodess() {
        return requiresNotesOnAllReasonCodess;
    }

    public void setRequiresNotesOnAllReasonCodess(boolean requiresNotesOnAllReasonCodess) {
        this.requiresNotesOnAllReasonCodess = requiresNotesOnAllReasonCodess;
    }

    public boolean getRequiresDischargeNote() {
        return requiresDischargeNote;
    }

    public void setRequiresDischargeNote(boolean requiresDischargeNote) {
        this.requiresDischargeNote = requiresDischargeNote;
    }

    public boolean getEnforceAllReasonCodes() {
        return enforceAllReasonCodes;
    }

    public void setEnforceAllReasonCodes(boolean enforceAllReasonCodes) {
        this.enforceAllReasonCodes = enforceAllReasonCodes;
    }

    public boolean getDefaultScheduleViewToCurrentWeek() {
        return defaultScheduleViewToCurrentWeek;
    }

    public void setDefaultScheduleViewToCurrentWeek(boolean defaultScheduleViewToCurrentWeek) {
        this.defaultScheduleViewToCurrentWeek = defaultScheduleViewToCurrentWeek;
    }

    public boolean getShowVerifiedTimesOnSchedule() {
        return showVerifiedTimesOnSchedule;
    }

    public void setShowVerifiedTimesOnSchedule(boolean showVerifiedTimesOnSchedule) {
        this.showVerifiedTimesOnSchedule = showVerifiedTimesOnSchedule;
    }

    public boolean getAllowMoreThan24ScheduledHours() {
        return allowMoreThan24ScheduledHours;
    }

    public void setAllowMoreThan24ScheduledHours(boolean allowMoreThan24ScheduledHours) {
        this.allowMoreThan24ScheduledHours = allowMoreThan24ScheduledHours;
    }

    public boolean getMatchStaffSkillsToPatientRequirements() {
        return matchStaffSkillsToPatientRequirements;
    }

    public void setMatchStaffSkillsToPatientRequirements(boolean matchStaffSkillsToPatientRequirements) {
        this.matchStaffSkillsToPatientRequirements = matchStaffSkillsToPatientRequirements;
    }

    public int getMaxAllowedWorkHours() {
        return maxAllowedWorkHours;
    }

    public void setMaxAllowedWorkHours(int maxAllowedWorkHours) {
        this.maxAllowedWorkHours = maxAllowedWorkHours;
    }

    public String getOvertimeRestriction() {
        return overtimeRestriction;
    }

    public void setOvertimeRestriction(String overtimeRestriction) {
        this.overtimeRestriction = overtimeRestriction;
    }

    public boolean getRequiresReasonCodeForOvertime() {
        return requiresReasonCodeForOvertime;
    }

    public void setRequiresReasonCodeForOvertime(boolean requiresReasonCodeForOvertime) {
        this.requiresReasonCodeForOvertime = requiresReasonCodeForOvertime;
    }
}
