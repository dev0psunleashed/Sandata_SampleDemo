package com.sandata.lab.billing.edi.model.remittance;

import java.util.*;

import com.sandata.lab.billing.edi.model.enums.AdjustmentGroupCodeType;

public class ServicePaymentAdjustment {
    private long id;
    private long servicePaymentId;
    private AdjustmentGroupCodeType group;
    private java.math.BigDecimal amount = new java.math.BigDecimal(0);
    private java.math.BigDecimal quantity;

    private Collection<ServicePaymentAdjustmentReason> reasons = new ArrayList<ServicePaymentAdjustmentReason>();

    public final Collection<ServicePaymentAdjustmentReason> getReasons() {
        return reasons;
    }

    public final void setReasons(Collection<ServicePaymentAdjustmentReason> value) {
        reasons = value;
    }

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