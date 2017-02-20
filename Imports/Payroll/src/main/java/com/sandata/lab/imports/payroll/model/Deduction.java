package com.sandata.lab.imports.payroll.model;

import com.sandata.lab.data.model.base.BaseObject;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class Deduction extends BaseObject {

    @XmlElement(name = "RateType")
    private String rateType;

    @XmlElement(name = "RateAmount")
    private BigDecimal rateAmount;


    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public BigDecimal getRateAmount() {
        return rateAmount;
    }

    public void setRateAmount(BigDecimal rateAmount) {
        this.rateAmount = rateAmount;
    }
}
