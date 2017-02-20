package com.sandata.lab.billing.edi.model.claim.professional;

import com.sandata.lab.billing.edi.model.enums.AdditionalIdTypeCodeType;

public class ServiceAdditionalId {
    private long id;
    private long serviceId;

    private AdditionalIdTypeCodeType idType;
    private String additionalId;

    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
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