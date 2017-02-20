package com.sandata.lab.billing.edi.model.claim.institutional;

import com.sandata.lab.billing.edi.model.enums.AmountTypeCodeType;

public class ServiceAmount {
    private long id;
    private long serviceId;

    private AmountTypeCodeType type;
    private java.math.BigDecimal amount = new java.math.BigDecimal(0);

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public AmountTypeCodeType getType() {
        return type;
    }

    public void setType(AmountTypeCodeType type) {
        this.type = type;
    }

    public java.math.BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(java.math.BigDecimal amount) {
        this.amount = amount;
    }
}