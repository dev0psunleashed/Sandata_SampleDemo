package com.sandata.lab.billing.edi.model.remittance;

import com.sandata.lab.billing.edi.model.enums.AmountTypeCodeType;

public class ServicePaymentAmount {
    private long id;
    private long servicePaymentId;

    private AmountTypeCodeType type;
    private java.math.BigDecimal amount = new java.math.BigDecimal(0);

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getServicePaymentId() {
        return servicePaymentId;
    }

    public void setServicePaymentId(long servicePaymentId) {
        this.servicePaymentId = servicePaymentId;
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