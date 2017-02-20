package com.sandata.lab.billing.edi.model.claim.institutional;

import java.util.*;

import com.sandata.lab.billing.edi.model.enums.AdjustmentGroupCodeType;

public class ServiceAdjustment {
    private long id;
    private long serviceAdjudicationInformationId;
    private AdjustmentGroupCodeType group;
    private java.math.BigDecimal amount = new java.math.BigDecimal(0);
    private java.math.BigDecimal quantity;

    private Collection<ServiceAdjustmentReason> reasons = new ArrayList<ServiceAdjustmentReason>();

    public final Collection<ServiceAdjustmentReason> getReasons() {
        return reasons;
    }

    public final void setReasons(Collection<ServiceAdjustmentReason> value) {
        reasons = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getServiceAdjudicationInformationId() {
        return serviceAdjudicationInformationId;
    }

    public void setServiceAdjudicationInformationId(long serviceAdjudicationInformationId) {
        this.serviceAdjudicationInformationId = serviceAdjudicationInformationId;
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