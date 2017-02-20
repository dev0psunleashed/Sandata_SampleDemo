package com.sandata.lab.exports.payroll.model.paypro;

import com.sandata.lab.data.model.base.BaseObject;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "PayPro" )
@XmlAccessorType(XmlAccessType.FIELD)
public class PayPro {

    @XmlElement(name = "Transaction")
    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
