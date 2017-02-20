package com.sandata.lab.billing.edi.model.remittance;

import com.sandata.lab.billing.edi.model.enums.IndustryCodeType;

public class ServicePaymentRemark {
    private long id;
    private long servicePaymentId;

    private IndustryCodeType type;
    private String remarkCode;

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

    public IndustryCodeType getType() {
        return type;
    }

    public void setType(IndustryCodeType type) {
        this.type = type;
    }

    public String getRemarkCode() {
        return remarkCode;
    }

    public void setRemarkCode(String remarkCode) {
        this.remarkCode = remarkCode;
    }
}