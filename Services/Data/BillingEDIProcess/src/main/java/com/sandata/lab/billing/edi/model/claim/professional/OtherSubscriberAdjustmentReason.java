package com.sandata.lab.billing.edi.model.claim.professional;

import java.util.*;

import com.sandata.lab.billing.edi.model.enums.IndustryCodeType;

public class OtherSubscriberAdjustmentReason {
    private long id;
    private long otherSubscriberAdjustmentId;

    private String reasonCode;
    private IndustryCodeType remarkCodeType;

    private Collection<OtherSubscriberAdjustmentReasonRemark> remarkCodes = new ArrayList<OtherSubscriberAdjustmentReasonRemark>();

    public final Collection<OtherSubscriberAdjustmentReasonRemark> getRemarkCodes() {
        return remarkCodes;
    }

    public final void setRemarkCodes(Collection<OtherSubscriberAdjustmentReasonRemark> value) {
        remarkCodes = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOtherSubscriberAdjustmentId() {
        return otherSubscriberAdjustmentId;
    }

    public void setOtherSubscriberAdjustmentId(long otherSubscriberAdjustmentId) {
        this.otherSubscriberAdjustmentId = otherSubscriberAdjustmentId;
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