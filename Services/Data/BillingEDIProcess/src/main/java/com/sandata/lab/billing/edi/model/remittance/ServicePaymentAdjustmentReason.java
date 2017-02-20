package com.sandata.lab.billing.edi.model.remittance;

import java.util.*;

import com.sandata.lab.billing.edi.model.enums.IndustryCodeType;

public class ServicePaymentAdjustmentReason {
    private long id;
    private long servicePaymentAdjustmentId;

    private String reasonCode;
    private IndustryCodeType remarkCodeType;

    private Collection<ServicePaymentAdjustmentReasonRemark> remarkCodes = new ArrayList<ServicePaymentAdjustmentReasonRemark>();

    public final Collection<ServicePaymentAdjustmentReasonRemark> getRemarkCodes() {
        return remarkCodes;
    }

    public final void setRemarkCodes(Collection<ServicePaymentAdjustmentReasonRemark> value) {
        remarkCodes = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getServicePaymentAdjustmentId() {
        return servicePaymentAdjustmentId;
    }

    public void setServicePaymentAdjustmentId(long servicePaymentAdjustmentId) {
        this.servicePaymentAdjustmentId = servicePaymentAdjustmentId;
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