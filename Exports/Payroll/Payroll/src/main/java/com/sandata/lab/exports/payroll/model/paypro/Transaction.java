package com.sandata.lab.exports.payroll.model.paypro;

import javax.xml.bind.annotation.*;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"summary", "times"})
public class Transaction {

    @XmlElement(name = "Summary")
    private Summary summary;

    @XmlElementWrapper(name = "Times")
    @XmlElement(name = "Time")
    private List<Time> times;

    @XmlAttribute(name = "CO")
    private String company;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<Time> getTimes() {
        return times;
    }

    public void setTimes(List<Time> times) {
        this.times = times;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }
}
