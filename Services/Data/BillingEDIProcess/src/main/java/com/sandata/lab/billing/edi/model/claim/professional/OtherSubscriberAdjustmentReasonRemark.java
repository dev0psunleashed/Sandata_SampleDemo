package com.sandata.lab.billing.edi.model.claim.professional;

public class OtherSubscriberAdjustmentReasonRemark {
    private long id;
    private long otherSubscriberAdjustmentReasonId;
    private String remarkCode;

    public long getId() {
        return id;
    }

    public long getOtherSubscriberAdjustmentReasonId() {
        return otherSubscriberAdjustmentReasonId;
    }

    public void setOtherSubscriberAdjustmentReasonId(long otherSubscriberAdjustmentReasonId) {
        this.otherSubscriberAdjustmentReasonId = otherSubscriberAdjustmentReasonId;
    }

    public String getRemarkCode() {
        return remarkCode;
    }

    public void setRemarkCode(String remarkCode) {
        this.remarkCode = remarkCode;
    }

    public void setId(long id) {
        this.id = id;
    }
}