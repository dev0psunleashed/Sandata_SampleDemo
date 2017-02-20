package com.sandata.lab.billing.edi.model.remittance;

public class ServicePaymentAdjustmentReasonRemark {
    private long id;
    private long servicePaymentAdjustmentReasonId;
    private String remarkCode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getServicePaymentAdjustmentReasonId() {
        return servicePaymentAdjustmentReasonId;
    }

    public void setServicePaymentAdjustmentReasonId(long servicePaymentAdjustmentReasonId) {
        this.servicePaymentAdjustmentReasonId = servicePaymentAdjustmentReasonId;
    }

    public String getRemarkCode() {
        return remarkCode;
    }

    public void setRemarkCode(String remarkCode) {
        this.remarkCode = remarkCode;
    }
}