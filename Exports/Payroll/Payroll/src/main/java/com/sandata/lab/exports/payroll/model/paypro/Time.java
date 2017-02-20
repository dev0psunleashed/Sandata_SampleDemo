package com.sandata.lab.exports.payroll.model.paypro;

import com.sandata.lab.exports.payroll.utils.DateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Time")
public class Time {

    @XmlElement(name = "SSN")
    String ssn;

    @XmlElement(name = "Det")
    String earning;

    @XmlElement(name = "DetCode")
    String earningCode;

    @XmlElement(name = "Hours")
    BigDecimal hours;

    @XmlElement(name = "Amount")
    BigDecimal amount;

    @XmlElement(name = "HourlyRate")
    BigDecimal hourlyRate;

    //This is not used and will remain blank
    @XmlElement(name = "RateCode")
    String rateCode;

    @XmlElement(name = "BaseRate")
    BigDecimal baseRate;

    //Not used and will remain blank
    @XmlElement(name = "JobCode")
    String jobcode;

    @XmlElement(name = "BeginDate")
    @XmlJavaTypeAdapter(DateAdapter.class)
    Date beginDate;

    @XmlElement(name = "EndDate")
    @XmlJavaTypeAdapter(DateAdapter.class)
    Date endDate;

    @XmlElement(name = "Comment")
    String comment;

    @XmlElement(name = "Shift")
    String shift;

    @XmlElement(name = "WCC")
    String wcc;

    @XmlElement(name = "CC1")
    String cc1;

    @XmlElement(name = "CC2")
    String cc2;

    @XmlElement(name = "CC3")
    String cc3;

    @XmlElement(name = "CC4")
    String cc4;

    @XmlElement(name = "Tcode1")
    String tCode1;

    @XmlElement(name = "Tcode2")
    String tCode2;

    @XmlElement(name = "Tcode3")
    String tCode3;

    @XmlElement(name = "Tcode4")
    String tCode4;

    public String gettCode4() {
        return tCode4;
    }

    public void settCode4(String tCode4) {
        this.tCode4 = tCode4;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getEarning() {
        return earning;
    }

    public void setEarning(String earning) {
        this.earning = earning;
    }

    public String getEarningCode() {
        return earningCode;
    }

    public void setEarningCode(String earningCode) {
        this.earningCode = earningCode;
    }

    public BigDecimal getHours() {
        return hours;
    }

    public void setHours(BigDecimal hours) {
        this.hours = hours;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getRateCode() {
        return rateCode;
    }

    public void setRateCode(String rateCode) {
        this.rateCode = rateCode;
    }

    public String getJobcode() {
        return jobcode;
    }

    public void setJobcode(String jobcode) {
        this.jobcode = jobcode;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getWcc() {
        return wcc;
    }

    public void setWcc(String wcc) {
        this.wcc = wcc;
    }

    public String getCc1() {
        return cc1;
    }

    public void setCc1(String cc1) {
        this.cc1 = cc1;
    }

    public String getCc2() {
        return cc2;
    }

    public void setCc2(String cc2) {
        this.cc2 = cc2;
    }

    public String getCc3() {
        return cc3;
    }

    public void setCc3(String cc3) {
        this.cc3 = cc3;
    }

    public String getCc4() {
        return cc4;
    }

    public void setCc4(String cc4) {
        this.cc4 = cc4;
    }

    public String gettCode1() {
        return tCode1;
    }

    public void settCode1(String tCode1) {
        this.tCode1 = tCode1;
    }

    public String gettCode2() {
        return tCode2;
    }

    public void settCode2(String tCode2) {
        this.tCode2 = tCode2;
    }

    public String gettCode3() {
        return tCode3;
    }

    public void settCode3(String tCode3) {
        this.tCode3 = tCode3;
    }

    public BigDecimal getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(BigDecimal baseRate) {
        this.baseRate = baseRate;
    }
}
