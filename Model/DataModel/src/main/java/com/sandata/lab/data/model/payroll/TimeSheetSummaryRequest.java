package com.sandata.lab.data.model.payroll;

import com.sandata.lab.data.model.base.BaseObject;

import java.util.Date;

/**
 * Defines the properties required to verify or create a TimeSheetSummary record.
 * <p/>
 *
 * @author David Rutgos
 */
public class TimeSheetSummaryRequest extends BaseObject {

    private static final long serialVersionUID = 1L;

    private String businessEntityId;
    private String staffId;
    private Date payrollWeekEndDate;

    public String getBusinessEntityId() {
        return businessEntityId;
    }

    public void setBusinessEntityId(String businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public Date getPayrollWeekEndDate() {
        return payrollWeekEndDate;
    }

    public void setPayrollWeekEndDate(Date payrollWeekEndDate) {
        this.payrollWeekEndDate = payrollWeekEndDate;
    }
}
