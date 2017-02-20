package com.sandata.lab.billing.edi.model.claim.professional;

import com.sandata.lab.billing.edi.model.enums.AdditionalIdTypeCodeType;

public class ClaimEntityAdditionalId {
    private long id;
    private long claimEntityId;

    private AdditionalIdTypeCodeType idType;
    private String additionalId;
    private String description;

    public String getAdditionalId() {
        return additionalId;
    }

    public void setAdditionalId(String additionalId) {
        this.additionalId = additionalId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClaimEntityId() {
        return claimEntityId;
    }

    public void setClaimEntityId(long claimEntityId) {
        this.claimEntityId = claimEntityId;
    }

    public AdditionalIdTypeCodeType getIdType() {
        return idType;
    }

    public void setIdType(AdditionalIdTypeCodeType idType) {
        this.idType = idType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}