package com.sandata.lab.imports.payroll.model;

import com.sandata.lab.data.model.base.BaseObject;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class Tax extends BaseObject{

    @XmlElement(name = "Type")
    private String type;

    @XmlElement(name = "Code")
    private String code;

    @XmlElement(name = "Amount")
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
