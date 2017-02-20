package com.sandata.lab.billing.edi.model.claim.professional;

public class ServiceAdjustmentReasonRemark {
    private long id;
    private long serviceAdjustmentReasonId;

    private String remarkCode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getServiceAdjustmentReasonId() {
        return serviceAdjustmentReasonId;
    }

    public void setServiceAdjustmentReasonId(long serviceAdjustmentReasonId) {
        this.serviceAdjustmentReasonId = serviceAdjustmentReasonId;
    }

    public String getRemarkCode() {
        return remarkCode;
    }

    public void setRemarkCode(String remarkCode) {
        this.remarkCode = remarkCode;
    }
}