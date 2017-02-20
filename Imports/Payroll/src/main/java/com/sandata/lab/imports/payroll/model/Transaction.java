package com.sandata.lab.imports.payroll.model;

import org.apache.cxf.jaxrs.ext.xml.XMLName;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement( name = "Transaction")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction {



    @XmlElementWrapper(name = "Checks")
    @XmlElement(name = "Check")
    private List<Check> checks;

    @XmlAttribute(name = "CO")
    private String company;

    public List<Check> getChecks() {
        return checks;
    }

    public void setChecks(List<Check> checks) {
        this.checks = checks;
    }



    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
