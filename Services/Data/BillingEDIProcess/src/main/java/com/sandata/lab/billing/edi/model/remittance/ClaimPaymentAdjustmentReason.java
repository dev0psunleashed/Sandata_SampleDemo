package com.sandata.lab.billing.edi.model.remittance;

import java.util.*;

import com.sandata.lab.billing.edi.model.enums.IndustryCodeType;

public class ClaimPaymentAdjustmentReason {
    private long id;
    private long claimPaymentAdjustmentId;

    private String reasonCode;
    private IndustryCodeType remarkCodeType;

    private Collection<ClaimPaymentAdjustmentReasonRemark> remarkCodes = new ArrayList<ClaimPaymentAdjustmentReasonRemark>();

    public final Collection<ClaimPaymentAdjustmentReasonRemark> getRemarkCodes() {
        return remarkCodes;
    }

    public final void setRemarkCodes(Collection<ClaimPaymentAdjustmentReasonRemark> value) {
        remarkCodes = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClaimPaymentAdjustmentId() {
        return claimPaymentAdjustmentId;
    }

    public void setClaimPaymentAdjustmentId(long claimPaymentAdjustmentId) {
        this.claimPaymentAdjustmentId = claimPaymentAdjustmentId;
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