package com.sandata.lab.billing.edi.model.remittance;

import com.sandata.lab.billing.edi.model.enums.AmountTypeCodeType;

public class ClaimPaymentAmount {
    private long id;
    private long claimPaymentId;

    private AmountTypeCodeType type;
    private java.math.BigDecimal amount = new java.math.BigDecimal(0);

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClaimPaymentId() {
        return claimPaymentId;
    }

    public void setClaimPaymentId(long claimPaymentId) {
        this.claimPaymentId = claimPaymentId;
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