package com.sandata.lab.billing.edi.model.claim.professional;

import com.sandata.lab.billing.edi.model.enums.AmountTypeCodeType;

public class OtherSubscriberAmount {
    private long id;
    private long otherSubscriberId;

    private AmountTypeCodeType type;
    private java.math.BigDecimal amount = new java.math.BigDecimal(0);

    public java.math.BigDecimal getAmount() {
        return amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOtherSubscriberId() {
        return otherSubscriberId;
    }

    public void setOtherSubscriberId(long otherSubscriberId) {
        this.otherSubscriberId = otherSubscriberId;
    }

    public AmountTypeCodeType getType() {
        return type;
    }

    public void setType(AmountTypeCodeType type) {
        this.type = type;
    }

    public void setAmount(java.math.BigDecimal amount) {
        this.amount = amount;
    }
}