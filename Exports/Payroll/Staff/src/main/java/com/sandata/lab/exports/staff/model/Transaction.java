package com.sandata.lab.exports.staff.model;

import org.apache.cxf.jaxrs.ext.xml.XMLName;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction {


    @XmlAttribute(name = "CO")
    private String company;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @XmlElement(name = "Staff")
    List<Staff> staffs;



    public List<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<Staff> staffs) {
        this.staffs = staffs;
    }

}
