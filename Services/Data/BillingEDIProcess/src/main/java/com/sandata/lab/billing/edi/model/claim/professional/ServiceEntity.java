package com.sandata.lab.billing.edi.model.claim.professional;

import java.util.ArrayList;
import java.util.Collection;

import com.sandata.lab.billing.edi.model.enums.NameIdentifierTypeCodeType;
import com.sandata.lab.billing.edi.model.enums.NameTypeCodeType;
import com.sandata.lab.billing.edi.model.enums.ProviderTypeCodeType;

public class ServiceEntity {
    private long id;
    private long serviceId;

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

    private Collection<ServiceEntityAdditionalId> additionalIds = new ArrayList<ServiceEntityAdditionalId>();

    public final Collection<ServiceEntityAdditionalId> getAdditionalIds() {
        return additionalIds;
    }

    public final void setAdditionalIds(Collection<ServiceEntityAdditionalId> value) {
        additionalIds = value;
    }

    private Collection<ServiceEntityContact> contacts = new ArrayList<ServiceEntityContact>();

    public final Collection<ServiceEntityContact> getContacts() {
        return contacts;
    }

    public final void setContacts(Collection<ServiceEntityContact> value) {
        contacts = value;
    }

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
}