package com.sandata.lab.billing.edi.model.claim.professional;

import com.sandata.lab.billing.edi.model.enums.AdditionalIdTypeCodeType;

public class PayToPlanAdditionalId {
    private long id;
    private long billingProviderId;

    private AdditionalIdTypeCodeType idType;
    private String additionalId;

    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBillingProviderId() {
        return billingProviderId;
    }

    public void setBillingProviderId(long billingProviderId) {
        this.billingProviderId = billingProviderId;
    }

    public AdditionalIdTypeCodeType getIdType() {
        return idType;
    }

    public void setIdType(AdditionalIdTypeCodeType idType) {
        this.idType = idType;
    }

    public String getAdditionalId() {
        return additionalId;
    }

    public void setAdditionalId(String additionalId) {
        this.additionalId = additionalId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}