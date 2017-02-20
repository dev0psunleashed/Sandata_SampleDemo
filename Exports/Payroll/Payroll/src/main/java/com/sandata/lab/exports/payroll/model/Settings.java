package com.sandata.lab.exports.payroll.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

public class Settings extends BaseObject {

    @SerializedName("PayrollFrequency")
    String payrollFrequency;

    @SerializedName("PayrollEndDay")
    String payrollEndDay;

    @SerializedName("PayrollEndTime")
    String payrollEndTime;

    public String getPayrollEndTime() {
        return payrollEndTime;
    }

    public void setPayrollEndTime(String payrollEndTime) {
        this.payrollEndTime = payrollEndTime;
    }

    public String getPayrollEndDay() {
        return payrollEndDay;
    }

    public void setPayrollEndDay(String payrollEndDay) {
        this.payrollEndDay = payrollEndDay;
    }

    public String getPayrollFrequency() {
        return payrollFrequency;
    }

    public void setPayrollFrequency(String payrollFrequency) {
        this.payrollFrequency = payrollFrequency;
    }


}
