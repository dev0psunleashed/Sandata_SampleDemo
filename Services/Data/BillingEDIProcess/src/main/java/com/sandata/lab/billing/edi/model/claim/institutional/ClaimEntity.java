package com.sandata.lab.billing.edi.model.claim.institutional;

import java.util.Collection;

import com.sandata.lab.billing.edi.model.enums.NameIdentifierTypeCodeType;
import com.sandata.lab.billing.edi.model.enums.NameTypeCodeType;
import com.sandata.lab.billing.edi.model.enums.ProviderTypeCodeType;

public class ClaimEntity {
    private long id;
    private long claimId;

    private NameTypeCodeType entityType;
    private String lastOrOrganizationName;
    private String firstName;
    private String middleName;
    private String suffixName;
    private NameIdentifierTypeCodeType entityIdType;
    private String entitiyId;

    private ProviderTypeCodeType providerType;
    private String taxonomyCode;

    private String street1;
    private String street2;
    private String city;
    private String state;
    private String postalCode;

    private Collection<ClaimEntityAdditionalId> additionalIds;

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

    public NameTypeCodeType getEntityType() {
        return entityType;
    }

    public void setEntityType(NameTypeCodeType entityType) {
        this.entityType = entityType;
    }

    public String getLastOrOrganizationName() {
        return lastOrOrganizationName;
    }

    public void setLastOrOrganizationName(String lastOrOrganizationName) {
        this.lastOrOrganizationName = lastOrOrganizationName;
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

    public String getEntitiyId() {
        return entitiyId;
    }

    public void setEntitiyId(String entitiyId) {
        this.entitiyId = entitiyId;
    }

    public ProviderTypeCodeType getProviderType() {
        return providerType;
    }

    public void setProviderType(ProviderTypeCodeType providerType) {
        this.providerType = providerType;
    }

    public String getTaxonomyCode() {
        return taxonomyCode;
    }

    public void setTaxonomyCode(String taxonomyCode) {
        this.taxonomyCode = taxonomyCode;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Collection<ClaimEntityAdditionalId> getAdditionalIds() {
        return additionalIds;
    }

    public void setAdditionalIds(Collection<ClaimEntityAdditionalId> additionalIds) {
        this.additionalIds = additionalIds;
    }
}