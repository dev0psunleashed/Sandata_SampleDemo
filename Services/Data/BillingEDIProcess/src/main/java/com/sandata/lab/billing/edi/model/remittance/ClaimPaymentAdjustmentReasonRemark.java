package com.sandata.lab.billing.edi.model.remittance;

public class ClaimPaymentAdjustmentReasonRemark {
    private long id;
    private long claimPaymentAdjustmentReasonId;
    private String remarkCode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClaimPaymentAdjustmentReasonId() {
        return claimPaymentAdjustmentReasonId;
    }

    public void setClaimPaymentAdjustmentReasonId(long claimPaymentAdjustmentReasonId) {
        this.claimPaymentAdjustmentReasonId = claimPaymentAdjustmentReasonId;
    }

    public String getRemarkCode() {
        return remarkCode;
    }

    public void setRemarkCode(String remarkCode) {
        this.remarkCode = remarkCode;
    }
}