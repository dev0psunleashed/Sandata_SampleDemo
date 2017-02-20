package com.sandata.lab.billing.edi.model.remittance;

import com.sandata.lab.billing.edi.model.enums.ProviderAdjustmentReasonType;

public class ProviderAdjustment {
    private long id;
    private long providerGroupId;
    private ProviderAdjustmentReasonType reason;

    private java.math.BigDecimal amount = new java.math.BigDecimal(0);

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProviderGroupId() {
        return providerGroupId;
    }

    public void setProviderGroupId(long providerGroupId) {
        this.providerGroupId = providerGroupId;
    }

    public ProviderAdjustmentReasonType getReason() {
        return reason;
    }

    public void setReason(ProviderAdjustmentReasonType reason) {
        this.reason = reason;
    }

    public java.math.BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(java.math.BigDecimal amount) {
        this.amount = amount;
    }
}