package com.sandata.lab.data.model.payroll;

import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * Defines the properties required to verify or create a TimeSheetDetail record.
 * <p/>
 *
 * @author David Rutgos
 */
public class TimeSheetDetailRequest extends BaseObject {

    private static final long serialVersionUID = 1L;

    private BigInteger calendarLkupSk;
    private BigInteger visitSk;
    private String patientId;
    private String payrollCode;
    private Date timesheetDate;
    private BigDecimal payrollRateAmount;
    private BigInteger payrollHours;
    private BigDecimal payrollAmount;

    private TimeSheetSummaryRequest timeSheetSummaryRequest;

    public BigInteger getCalendarLkupSk() {
        return calendarLkupSk;
    }

    public void setCalendarLkupSk(BigInteger calendarLkupSk) {
        this.calendarLkupSk = calendarLkupSk;
    }

    public BigInteger getVisitSk() {
        return visitSk;
    }

    public void setVisitSk(BigInteger visitSk) {
        this.visitSk = visitSk;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPayrollCode() {
        return payrollCode;
    }

    public void setPayrollCode(String payrollCode) {
        this.payrollCode = payrollCode;
    }

    public Date getTimesheetDate() {
        return timesheetDate;
    }

    public void setTimesheetDate(Date timesheetDate) {
        this.timesheetDate = timesheetDate;
    }

    public BigDecimal getPayrollRateAmount() {
        return payrollRateAmount;
    }

    public void setPayrollRateAmount(BigDecimal payrollRateAmount) {
        this.payrollRateAmount = payrollRateAmount;
    }

    public BigInteger getPayrollHours() {
        return payrollHours;
    }

    public void setPayrollHours(BigInteger payrollHours) {
        this.payrollHours = payrollHours;
    }

    public BigDecimal getPayrollAmount() {
        return payrollAmount;
    }

    public void setPayrollAmount(BigDecimal payrollAmount) {
        this.payrollAmount = payrollAmount;
    }

    public TimeSheetSummaryRequest getTimeSheetSummaryRequest() {
        return timeSheetSummaryRequest;
    }

    public void setTimeSheetSummaryRequest(TimeSheetSummaryRequest timeSheetSummaryRequest) {
        this.timeSheetSummaryRequest = timeSheetSummaryRequest;
    }
}
