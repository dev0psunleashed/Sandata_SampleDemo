package com.sandata.lab.exports.payroll.model.paypro;

import com.sandata.lab.exports.payroll.utils.DateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
public class Summary {

    @XmlElement(name = "BeginDate")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date beginDate;

    @XmlElement(name = "EndDate")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date endDate;

    @XmlElement(name = "EmployeeCount")
    private int employeeCount;

    @XmlElement(name = "TimeCount")
    private int timeCount;

    @XmlElement(name = "DetCodes")
    private DetCodes detCodes;

    public int getTimeCount() {
        return timeCount;
    }

    public void setTimeCount(int timeCount) {
        this.timeCount = timeCount;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public void incrementTimeCount() {
        this.timeCount++;
    }

    public DetCodes getDetCodes() {
        return detCodes;
    }

    public void setDetCodes(DetCodes detCodes) {
        this.detCodes = detCodes;
    }


}
