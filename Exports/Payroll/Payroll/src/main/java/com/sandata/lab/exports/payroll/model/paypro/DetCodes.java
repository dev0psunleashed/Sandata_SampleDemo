package com.sandata.lab.exports.payroll.model.paypro;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
public class DetCodes {

    @XmlAttribute(name = "Count")
    private int count;

    @XmlAttribute(name = "TotalHours")
    private BigDecimal hours;

    @XmlAttribute(name = "Amount")
    private BigDecimal amount;

    @XmlElement(name = "DetCode")
    private List<DetCode> detCodes;

    public List<DetCode> getDetCodes() {
        return detCodes;
    }

    public void setDetCodes(List<DetCode> detCodes) {
        this.detCodes = detCodes;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void addAmount(BigDecimal amount) {

        if (this.amount == null) {
            this.amount = amount;
        } else if(amount != null){
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
        } else if (hours != null){
            this.hours.add(hours);
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void incrementCount(){ this.count++;}
}
