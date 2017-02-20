package com.sandata.lab.imports.payroll.model;

import com.sandata.lab.data.model.base.BaseObject;

import javax.xml.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class Earning extends BaseObject {

    @XmlElement(name = "TimesheetSummaryID")
    private String timesheetSummaryID;

    @XmlElement(name = "DateOfService")
    private Date dateOfService;

    @XmlElement(name = "RateType")
    private String rateType;

    @XmlElement(name = "RateAmount")
    private BigDecimal rateAmount;

    @XmlElement(name = "Rate")
    private String rate;

    @XmlElement(name = "Hours")
    private BigDecimal hours;

    public BigDecimal getHours() {
        return hours;
    }

    public void setHours(BigDecimal hours) {
        this.hours = hours;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public BigDecimal getRateAmount() {
        return rateAmount;
    }

    public void setRateAmount(BigDecimal rateAmount) {
        this.rateAmount = rateAmount;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public Date getDateOfService() {
        return dateOfService;
    }

    public void setDateOfService(Date dateOfService) {
        this.dateOfService = dateOfService;
    }

    public String getTimesheetSummaryID() {
        return timesheetSummaryID;
    }

    public void setTimesheetSummaryID(String timesheetSummaryID) {
        this.timesheetSummaryID = timesheetSummaryID;
    }

}
