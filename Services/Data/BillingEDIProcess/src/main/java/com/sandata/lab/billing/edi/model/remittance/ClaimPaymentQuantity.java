package com.sandata.lab.billing.edi.model.remittance;

import com.sandata.lab.billing.edi.model.enums.QuantityType;

public class ClaimPaymentQuantity {
    private long id;
    private long claimPaymentId;

    private QuantityType type;
    private java.math.BigDecimal quantity = new java.math.BigDecimal(0);

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

    public QuantityType getType() {
        return type;
    }

    public void setType(QuantityType type) {
        this.type = type;
    }

    public java.math.BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(java.math.BigDecimal quantity) {
        this.quantity = quantity;
    }
}