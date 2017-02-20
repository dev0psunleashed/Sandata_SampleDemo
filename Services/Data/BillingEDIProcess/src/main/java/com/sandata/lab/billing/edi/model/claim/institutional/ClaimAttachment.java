package com.sandata.lab.billing.edi.model.claim.institutional;

import com.sandata.lab.billing.edi.model.enums.*;

public class ClaimAttachment {
    private long id;
    private long claimId;

    private AttachmentReportType type;
    private AttachmentTransmissionType transmission;
    private AttachmentCodeType idType;
    private String controlNumber;

    private Claim claim;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClaimId() {
        return claimId;
    }

    public void setClaimId(long claimId) {
        this.claimId = claimId;
    }

    public AttachmentReportType getType() {
        return type;
    }

    public void setType(AttachmentReportType type) {
        this.type = type;
    }

    public AttachmentTransmissionType getTransmission() {
        return transmission;
    }

    public void setTransmission(AttachmentTransmissionType transmission) {
        this.transmission = transmission;
    }

    public AttachmentCodeType getIdType() {
        return idType;
    }

    public void setIdType(AttachmentCodeType idType) {
        this.idType = idType;
    }

    public String getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber;
    }

    public Claim getClaim() {
        return claim;
    }

    public void setClaim(Claim claim) {
        this.claim = claim;
    }
}