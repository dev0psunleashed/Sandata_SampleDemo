package com.sandata.lab.billing.edi.model.remittance;

import com.sandata.lab.billing.edi.model.enums.*;

public class ClaimPaymentName {
    private long id;
    private long claimPaymentId;

    private NameTypeCodeType type;
    private String lastName;
    private String firstName;
    private String middleName;
    private String suffixName;
    private NameIdentifierTypeCodeType entityIdType;
    private String entityId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClaimPaymentId() {
        return claimPaymentId;
    }

    public void setClaimPaymentId(long claimPaymentId) {
        this.claimPaymentId = claimPaymentId;
    }

    public NameTypeCodeType getType() {
        return type;
    }

    public void setType(NameTypeCodeType type) {
        this.type = type;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSuffixName() {
        return suffixName;
    }

    public void setSuffixName(String suffixName) {
        this.suffixName = suffixName;
    }

    public NameIdentifierTypeCodeType getEntityIdType() {
        return entityIdType;
    }

    public void setEntityIdType(NameIdentifierTypeCodeType entityIdType) {
        this.entityIdType = entityIdType;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }
}