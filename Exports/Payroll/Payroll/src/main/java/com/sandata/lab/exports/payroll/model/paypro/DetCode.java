package com.sandata.lab.exports.payroll.model.paypro;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;


@XmlAccessorType(XmlAccessType.FIELD)
public class DetCode {

    @XmlElement(name = "Code")
    private String code;

    @XmlElement(name = "Hours")
    private BigDecimal hours;

    @XmlElement(name = "Amount")
    private BigDecimal amount;

    @XmlElement(name = "Count")
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void incrementCount(){ this.count++;}

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void addAmount(BigDecimal amount) {

        if (this.amount == null) {
            this.amount = amount;
        } else if( amount != null){
            this.amount.add(amount);
        }
    }

    public BigDecimal getHours() {
        return hours;
    }

    public void setHours(BigDecimal hours) {
        this.hours = hours;
    }

    public void addHours(BigDecimal hours) {

        if (this.hours == null) {
            this.hours = hours;
        } else if(hours != null) {
            this.hours.add(hours);
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
