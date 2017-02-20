package com.sandata.lab.imports.payroll.model;

import com.sandata.lab.data.model.base.BaseObject;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement( name = "PayPro" )
@XmlAccessorType(XmlAccessType.FIELD)
public class PayPro extends BaseObject {

    @XmlElement(name = "Transaction")
    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
