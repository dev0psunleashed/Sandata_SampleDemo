package com.sandata.lab.eligibility.model.response;

import java.util.*;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.eligibility.model.response.enums.GenderCodeType;
import com.sandata.lab.eligibility.model.response.enums.MilitaryEmploymentStatusCodeType;
import com.sandata.lab.eligibility.model.response.enums.MilitaryInformationStatusCodeType;
import com.sandata.lab.eligibility.model.response.enums.MilitaryServiceAffiliationCodeType;
import com.sandata.lab.eligibility.model.response.enums.MilitaryServiceRankCodeType;
import com.sandata.lab.eligibility.model.response.enums.ProviderTypeCodeType;
import com.sandata.lab.eligibility.model.response.enums.RelationshipCodeType;

public class Person {
	@SerializedName("Id")
    private long id;

	@SerializedName("ProviderId")
    private long providerId;
	@SerializedName("SubscriberId")
    private Long subscriberId;

	@SerializedName("TraceNumber")
    private Long traceNumber;
	
	@SerializedName("OriginatingTraceNumber")
	private String originatingTraceNumber;
	
	@SerializedName("DatabaseId")
    private String databaseId;

	@SerializedName("FirstName")
    private String firstName;
	@SerializedName("MiddleName")
    private String middleName;
	@SerializedName("LastName")
    private String lastName;
	@SerializedName("SuffixName")
    private String suffixName;

	@SerializedName("Street1")
    private String street1;
	@SerializedName("Street2")
    private String street2;
	@SerializedName("City")
    private String city;
	@SerializedName("State")
    private String state;
	@SerializedName("PostalCode")
    private String postalCode;

	@SerializedName("Gender")
    private GenderCodeType gender;

	@SerializedName("ProviderType")
    private ProviderTypeCodeType providerType;
	@SerializedName("ProviderTaxonomy")
    private String providerTaxonomy;

	@SerializedName("RelationshipToSubscriber")
    private RelationshipCodeType relationshipToSubscriber;
	@SerializedName("HasChangesFromInquiry")
    private Boolean hasChangesFromInquiry;

	@SerializedName("MilitaryInformationStatus")
    private MilitaryInformationStatusCodeType militaryInformationStatus;
	
	@SerializedName("MilitaryEmploymentStatus")
    private MilitaryEmploymentStatusCodeType militaryEmploymentStatus;
	
	@SerializedName("MilitaryServiceAffiliation")
    private MilitaryServiceAffiliationCodeType militaryServiceAffiliation;
	
	@SerializedName("MilitaryServiceRank")
    private MilitaryServiceRankCodeType militaryServiceRank;
	
	@SerializedName("MilitaryDescription")
    private String militaryDescription;
	
	@SerializedName("MilitaryServiceStartDate")
    private Date militaryServiceStartDate;
	
	@SerializedName("MilitaryServiceEndDate")
    private Date militaryServiceEndDate;

	@SerializedName("AdditionalIds")
    private Collection<PersonAdditionalId> additionalIds;
	
	@SerializedName("RequestErrors")
    private Collection<PersonRequestError> requestErrors;
	
	@SerializedName("Benefits")
    private Collection<Benefit> benefits;
	
	@SerializedName("DiagnosisCodes")
    private Collection<DiagnosisCode> diagnosisCodes;
	
	@SerializedName("Dates")
    private Collection<PersonDate> dates;
	
	@SerializedName("Patients")
    private Collection<Person> patients;

	@SerializedName("Subscriber")
    private Person subscriber;
	@SerializedName("Provider")
    private Provider provider;
	@SerializedName("DateOfBirth")
    private Date dateOfBirth;
	@SerializedName("MemberId")
    private String memberId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProviderId() {
        return providerId;
    }

    public void setProviderId(long providerId) {
        this.providerId = providerId;
    }

    public Long getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(Long subscriberId) {
        this.subscriberId = subscriberId;
    }

    public Long getTraceNumber() {
        return traceNumber;
    }

    public void setTraceNumber(Long traceNumber) {
        this.traceNumber = traceNumber;
    }
    

    public String getOriginatingTraceNumber() {
		return originatingTraceNumber;
	}

	public void setOriginatingTraceNumber(String originatingTraceNumber) {
		this.originatingTraceNumber = originatingTraceNumber;
	}

	public String getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(String databaseId) {
        this.databaseId = databaseId;
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

    public GenderCodeType getGender() {
        return gender;
    }

    public void setGender(GenderCodeType gender) {
        this.gender = gender;
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

    public RelationshipCodeType getRelationshipToSubscriber() {
        return relationshipToSubscriber;
    }

    public void setRelationshipToSubscriber(RelationshipCodeType relationshipToSubscriber) {
        this.relationshipToSubscriber = relationshipToSubscriber;
    }

    public Boolean getHasChangesFromInquiry() {
        return hasChangesFromInquiry;
    }

    public void setHasChangesFromInquiry(Boolean hasChangesFromInquiry) {
        this.hasChangesFromInquiry = hasChangesFromInquiry;
    }

    public MilitaryInformationStatusCodeType getMilitaryInformationStatus() {
        return militaryInformationStatus;
    }

    public void setMilitaryInformationStatus(MilitaryInformationStatusCodeType militaryInformationStatus) {
        this.militaryInformationStatus = militaryInformationStatus;
    }

    public MilitaryEmploymentStatusCodeType getMilitaryEmploymentStatus() {
        return militaryEmploymentStatus;
    }

    public void setMilitaryEmploymentStatus(MilitaryEmploymentStatusCodeType militaryEmploymentStatus) {
        this.militaryEmploymentStatus = militaryEmploymentStatus;
    }

    public MilitaryServiceAffiliationCodeType getMilitaryServiceAffiliation() {
        return militaryServiceAffiliation;
    }

    public void setMilitaryServiceAffiliation(MilitaryServiceAffiliationCodeType militaryServiceAffiliation) {
        this.militaryServiceAffiliation = militaryServiceAffiliation;
    }

    public MilitaryServiceRankCodeType getMilitaryServiceRank() {
        return militaryServiceRank;
    }

    public void setMilitaryServiceRank(MilitaryServiceRankCodeType militaryServiceRank) {
        this.militaryServiceRank = militaryServiceRank;
    }

    public String getMilitaryDescription() {
        return militaryDescription;
    }

    public void setMilitaryDescription(String militaryDescription) {
        this.militaryDescription = militaryDescription;
    }

    public Date getMilitaryServiceStartDate() {
        return militaryServiceStartDate;
    }

    public void setMilitaryServiceStartDate(Date militaryServiceStartDate) {
        this.militaryServiceStartDate = militaryServiceStartDate;
    }

    public Date getMilitaryServiceEndDate() {
        return militaryServiceEndDate;
    }

    public void setMilitaryServiceEndDate(Date militaryServiceEndDate) {
        this.militaryServiceEndDate = militaryServiceEndDate;
    }

    public Collection<PersonAdditionalId> getAdditionalIds() {
        return additionalIds;
    }

    public void setAdditionalIds(Collection<PersonAdditionalId> additionalIds) {
        this.additionalIds = additionalIds;
    }

    public Collection<PersonRequestError> getRequestErrors() {
        return requestErrors;
    }

    public void setRequestErrors(Collection<PersonRequestError> requestErrors) {
        this.requestErrors = requestErrors;
    }

    public Collection<Benefit> getBenefits() {
        return benefits;
    }

    public void setBenefits(Collection<Benefit> benefits) {
        this.benefits = benefits;
    }

    public Collection<DiagnosisCode> getDiagnosisCodes() {
        return diagnosisCodes;
    }

    public void setDiagnosisCodes(Collection<DiagnosisCode> diagnosisCodes) {
        this.diagnosisCodes = diagnosisCodes;
    }

    public Collection<PersonDate> getDates() {
        return dates;
    }

    public void setDates(Collection<PersonDate> dates) {
        this.dates = dates;
    }

    public Collection<Person> getPatients() {
        return patients;
    }

    public void setPatients(Collection<Person> patients) {
        this.patients = patients;
    }

    public Person getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Person subscriber) {
        this.subscriber = subscriber;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}