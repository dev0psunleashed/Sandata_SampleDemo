package com.sandata.lab.billing.edi.model.claim.professional;

import com.sandata.lab.billing.edi.model.enums.AdditionalIdTypeCodeType;

public class ServiceEntityAdditionalId {
    private long id;
    private long serviceEntityId;

    private AdditionalIdTypeCodeType idType;
    private String additionalId;
    private String description;

    private String otherPayerId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getServiceEntityId() {
        return serviceEntityId;
    }

    public void setServiceEntityId(long serviceEntityId) {
        this.serviceEntityId = serviceEntityId;
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

    public String getOtherPayerId() {
        return otherPayerId;
    }

    public void setOtherPayerId(String otherPayerId) {
        this.otherPayerId = otherPayerId;
    }
}