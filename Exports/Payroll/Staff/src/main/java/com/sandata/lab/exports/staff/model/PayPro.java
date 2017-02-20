package com.sandata.lab.exports.staff.model;

import com.sandata.lab.data.model.base.BaseObject;

import javax.xml.bind.annotation.*;

@XmlRootElement( name = "PayPro" )
@XmlAccessorType(XmlAccessType.FIELD)
public class PayPro  {

    @XmlElement(name = "Transaction")
    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
