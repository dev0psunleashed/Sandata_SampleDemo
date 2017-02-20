package com.sandata.lab.eligibility.model.response;

import java.util.Collection;

import com.sandata.lab.eligibility.model.response.enums.NameIdentifierTypeCodeType;
import com.sandata.lab.eligibility.model.response.enums.NameTypeCodeType;
import com.sandata.lab.eligibility.model.response.enums.ProviderTypeCodeType;

public class BenefitRelatedEntity {
    private long id;
    private long benefitId;

    private NameTypeCodeType entityType;
    private String firstName;
    private String middleName;
    private String lastName;
    private String suffixName;

    private NameIdentifierTypeCodeType idType;

    private String street1;
    private String street2;
    private String city;
    private String state;
    private String postalCode;

    private ProviderTypeCodeType providerType;
    private String providerTaxonomy;

    private Collection<BenefitRelatedEntityContact> contacts;
    private Benefit benefit;

    private String entityId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBenefitId() {
        return benefitId;
    }

    public void setBenefitId(long benefitId) {
        this.benefitId = benefitId;
    }

    public NameTypeCodeType getEntityType() {
        return entityType;
    }

    public void setEntityType(NameTypeCodeType entityType) {
        this.entityType = entityType;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSuffixName() {
        return suffixName;
    }

    public void setSuffixName(String suffixName) {
        this.suffixName = suffixName;
    }

    public NameIdentifierTypeCodeType getIdType() {
        return idType;
    }

    public void setIdType(NameIdentifierTypeCodeType idType) {
        this.idType = idType;
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

    public ProviderTypeCodeType getProviderType() {
        return providerType;
    }

    public void setProviderType(ProviderTypeCodeType providerType) {
        this.providerType = providerType;
    }

    public String getProviderTaxonomy() {
        return providerTaxonomy;
    }

    public void setProviderTaxonomy(String providerTaxonomy) {
        this.providerTaxonomy = providerTaxonomy;
    }

    public Collection<BenefitRelatedEntityContact> getContacts() {
        return contacts;
    }

    public void setContacts(Collection<BenefitRelatedEntityContact> contacts) {
        this.contacts = contacts;
    }

    public Benefit getBenefit() {
        return benefit;
    }

    public void setBenefit(Benefit benefit) {
        this.benefit = benefit;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }
}