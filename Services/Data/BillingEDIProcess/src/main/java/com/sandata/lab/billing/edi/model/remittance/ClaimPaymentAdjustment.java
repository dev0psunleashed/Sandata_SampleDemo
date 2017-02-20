package com.sandata.lab.billing.edi.model.remittance;

import java.util.*;

import com.sandata.lab.billing.edi.model.enums.AdjustmentGroupCodeType;

public class ClaimPaymentAdjustment {
    private long id;
    private long claimPaymentId;
    private AdjustmentGroupCodeType group;
    private java.math.BigDecimal amount = new java.math.BigDecimal(0);
    private java.math.BigDecimal quantity;

    private Collection<ClaimPaymentAdjustmentReason> reasons = new ArrayList<ClaimPaymentAdjustmentReason>();

    public final Collection<ClaimPaymentAdjustmentReason> getReasons() {
        return reasons;
    }

    public final void setReasons(Collection<ClaimPaymentAdjustmentReason> value) {
        reasons = value;
    }

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

    public AdjustmentGroupCodeType getGroup() {
        return group;
    }

    public void setGroup(AdjustmentGroupCodeType group) {
        this.group = group;
    }

    public java.math.BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(java.math.BigDecimal amount) {
        this.amount = amount;
    }

    public java.math.BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(java.math.BigDecimal quantity) {
        this.quantity = quantity;
    }
}