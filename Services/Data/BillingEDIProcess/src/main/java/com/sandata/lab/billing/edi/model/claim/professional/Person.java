package com.sandata.lab.billing.edi.model.claim.professional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.sandata.lab.billing.edi.model.enums.GenderCodeType;
import com.sandata.lab.billing.edi.model.enums.NameIdentifierTypeCodeType;
import com.sandata.lab.billing.edi.model.enums.RelationshipCodeType;
import com.sandata.lab.billing.edi.model.enums.ResponseCodeType;

public class Person {
    private long id;
    private long subsciberInformationId;
    private long subscriberId;

    private RelationshipCodeType relationshipToInsured;
    private Date dateOfDeath;
    private Long patientWeight;
    private ResponseCodeType pregnancyIndicator;

    private String lastName;
    private String firstName;
    private String middleName;
    private String suffixName;

    private NameIdentifierTypeCodeType idType;
    private String entityId;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String postalCode;

    private Date dateOfBirth;
    private GenderCodeType gender;

    private Collection<PersonAdditionalId> additionalIds = new ArrayList<PersonAdditionalId>();

    public final Collection<PersonAdditionalId> getAdditionalIds() {
        return additionalIds;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSubsciberInformationId() {
        return subsciberInformationId;
    }

    public void setSubsciberInformationId(long subsciberInformationId) {
        this.subsciberInformationId = subsciberInformationId;
    }

    public long getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(long subscriberId) {
        this.subscriberId = subscriberId;
    }

    public RelationshipCodeType getRelationshipToInsured() {
        return relationshipToInsured;
    }

    public void setRelationshipToInsured(RelationshipCodeType relationshipToInsured) {
        this.relationshipToInsured = relationshipToInsured;
    }

    public Date getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(Date dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public Long getPatientWeight() {
        return patientWeight;
    }

    public void setPatientWeight(Long patientWeight) {
        this.patientWeight = patientWeight;
    }

    public ResponseCodeType getPregnancyIndicator() {
        return pregnancyIndicator;
    }

    public void setPregnancyIndicator(ResponseCodeType pregnancyIndicator) {
        this.pregnancyIndicator = pregnancyIndicator;
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

    public NameIdentifierTypeCodeType getIdType() {
        return idType;
    }

    public void setIdType(NameIdentifierTypeCodeType idType) {
        this.idType = idType;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public GenderCodeType getGender() {
        return gender;
    }

    public void setGender(GenderCodeType gender) {
        this.gender = gender;
    }

    public final void setAdditionalIds(Collection<PersonAdditionalId> value) {
        additionalIds = value;
    }

    private Collection<PersonContact> contacts = new ArrayList<PersonContact>();

    public final Collection<PersonContact> getContacts() {
        return contacts;
    }

    public final void setContacts(Collection<PersonContact> value) {
        contacts = value;
    }

    private Collection<Person> patients = new ArrayList<Person>();

    public final Collection<Person> getPatients() {
        return patients;
    }

    public final void setPatients(Collection<Person> value) {
        patients = value;
    }

    private Collection<Claim> claims = new ArrayList<Claim>();

    public final Collection<Claim> getClaims() {
        return claims;
    }

    public final void setClaims(Collection<Claim> value) {
        claims = value;
    }
}