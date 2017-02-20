package com.sandata.lab.data.model.dl.model.extended;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.StaffPayrollDetail;

import java.util.Date;

/**
 * Date: 11/5/15
 * Time: 12:19 AM
 */

public class StaffPayrollDetailExt extends StaffPayrollDetail {

    private static final long serialVersionUID = 1L;

    public StaffPayrollDetailExt(final StaffPayrollDetail staffPayrollDetail, final Date lastDayWorked) {

        setLastDayWorked(lastDayWorked);

        getStaffDeduction().addAll(staffPayrollDetail.getStaffDeduction());

        setStaffPayrollDetailSK(staffPayrollDetail.getStaffPayrollDetailSK());
        setStaffPayrollDetailID(staffPayrollDetail.getStaffPayrollDetailID());
        setStaffID(staffPayrollDetail.getStaffID());
        setChangeReasonMemo(staffPayrollDetail.getChangeReasonMemo());
        setRecordCreateTimestamp(staffPayrollDetail.getRecordCreateTimestamp());
        setRecordUpdateTimestamp(staffPayrollDetail.getRecordUpdateTimestamp());
        setRecordEffectiveTimestamp(staffPayrollDetail.getRecordEffectiveTimestamp());
        setRecordTerminationTimestamp(staffPayrollDetail.getRecordTerminationTimestamp());
        setRecordCreatedBy(staffPayrollDetail.getRecordCreatedBy());
        setRecordUpdatedBy(staffPayrollDetail.getRecordUpdatedBy());
        setCurrentRecordIndicator(staffPayrollDetail.isCurrentRecordIndicator());
        setChangeVersionID(staffPayrollDetail.getChangeVersionID());
        setStaffEmployeePayrollTypeName(staffPayrollDetail.getStaffEmployeePayrollTypeName());
        setStaffPayFrequency(staffPayrollDetail.getStaffPayFrequency());
        setStaffPayrollFederalExemptions(staffPayrollDetail.getStaffPayrollFederalExemptions());
        setStaffFederalMaritalStatusName(staffPayrollDetail.getStaffFederalMaritalStatusName());
        setStaffPayrollStateExemptions(staffPayrollDetail.getStaffPayrollStateExemptions());
        setStaffStateMaritalStatusName(staffPayrollDetail.getStaffStateMaritalStatusName());
        setStaffStateOfResidence(staffPayrollDetail.getStaffStateOfResidence());
        setStaffStateTaxNumber(staffPayrollDetail.getStaffStateTaxNumber());
        setCityTax1(staffPayrollDetail.getCityTax1());
        setCityTax2(staffPayrollDetail.getCityTax2());
        setStateTax(staffPayrollDetail.getStateTax());
        setStaffHourlyRateIndicator(staffPayrollDetail.isStaffHourlyRateIndicator());
        setStaffDailyRateIndicator(staffPayrollDetail.isStaffDailyRateIndicator());
        setStaffOverrideRate(staffPayrollDetail.getStaffOverrideRate());
        setHolidayRateBypassIndicator(staffPayrollDetail.isHolidayRateBypassIndicator());
        setStaffPayrollBankRouting(staffPayrollDetail.getStaffPayrollBankRouting());
        setStaffPayrollNotes(staffPayrollDetail.getStaffPayrollNotes());
    }

    @SerializedName("LastDayWorked")
    private Date lastDayWorked;

    public Date getLastDayWorked() {
        return lastDayWorked;
    }

    public void setLastDayWorked(Date lastDayWorked) {
        this.lastDayWorked = lastDayWorked;
    }
}
