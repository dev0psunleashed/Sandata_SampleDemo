package com.sandata.lab.billing.edi.model.claim.institutional;

import java.util.*;

import com.sandata.lab.billing.edi.model.enums.IndustryCodeType;

public class ServiceAdjustmentReason {
    private long id;
    private long serviceAdjustmentId;

    private String reasonCode;
    private IndustryCodeType remarkCodeType;

    private Collection<ServiceAdjustmentReasonRemark> remarkCodes = new ArrayList<ServiceAdjustmentReasonRemark>();

    public final Collection<ServiceAdjustmentReasonRemark> getRemarkCodes() {
        return remarkCodes;
    }

    public final void setRemarkCodes(Collection<ServiceAdjustmentReasonRemark> value) {
        remarkCodes = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getServiceAdjustmentId() {
        return serviceAdjustmentId;
    }

    public void setServiceAdjustmentId(long serviceAdjustmentId) {
        this.serviceAdjustmentId = serviceAdjustmentId;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public IndustryCodeType getRemarkCodeType() {
        return remarkCodeType;
    }

    public void setRemarkCodeType(IndustryCodeType remarkCodeType) {
        this.remarkCodeType = remarkCodeType;
    }
}