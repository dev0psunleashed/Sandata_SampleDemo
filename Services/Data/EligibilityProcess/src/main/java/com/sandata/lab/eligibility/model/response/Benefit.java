package com.sandata.lab.eligibility.model.response;

import java.util.*;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.eligibility.model.response.enums.*;

public class Benefit {
	
	@SerializedName("Id")
    private long id;
	@SerializedName("PersonId")
    private long personId;

	@SerializedName("InformationCode")
    private BenefitInformationCodeType informationCode;
	
	@SerializedName("CoverageLevel")
    private BenefitCoverageLevelCodeType coverageLevel;
	
	@SerializedName("InsuranceType")
    private BenefitInsuranceTypeCodeType insuranceType;
	
	@SerializedName("CoverageDescription")
    private String coverageDescription;
	
	
	@SerializedName("TimePeriod")
    private BenefitTimePeriodTypeCodeType timePeriod;
	
	@SerializedName("MonetaryAmount")
    private java.math.BigDecimal monetaryAmount;
	
	@SerializedName("PatientPercentage")
    private java.math.BigDecimal patientPercentage;
	
	@SerializedName("QuantityType")
    private BenefitQuantityTypeCodeType quantityType;
	
	@SerializedName("Quantity")
    private java.math.BigDecimal quantity;
	
	@SerializedName("AuthorizationRequired")
    private BenefitAuthorizationCertificationRequiredCodeType authorizationRequired;
	
	@SerializedName("InPlanNetwork")
    private BenefitInPlanNetworkCodeType inPlanNetwork;
	
	@SerializedName("ProductServiceIdType")
    private BenefitProductServiceIdTypeCodeType productServiceIdType;
	
	@SerializedName("ProductServiceId")
    private String productServiceId;

	@SerializedName("ProductServiceModifier1")
    private String productServiceModifier1;
	
	@SerializedName("ProductServiceModifier2")
    private String productServiceModifier2;
	
	@SerializedName("ProductServiceModifier3")
    private String productServiceModifier3;
	
	@SerializedName("ProductServiceModifier4")
    private String productServiceModifier4;
	
	@SerializedName("ProductServiceIdEnd")
    private String productServiceIdEnd;

	@SerializedName("DiagnosisPointer1")
    private Long diagnosisPointer1;
	@SerializedName("DiagnosisPointer2")
    private Long diagnosisPointer2;
	@SerializedName("DiagnosisPointer3")
    private Long diagnosisPointer3;
	@SerializedName("DiagnosisPointer4")
    private Long diagnosisPointer4;

	@SerializedName("ServiceTypes")
    private Collection<BenefitServiceTypeCode> serviceTypes;
	
	@SerializedName("HealthCareServiceDeliveryRestrictions")
    private Collection<BenefitHealthCareServicesDeliveryRestriction> healthCareServiceDeliveryRestrictions;
	
	@SerializedName("AdditionalIds")
    private Collection<BenefitAdditionalId> additionalIds;
	
	@SerializedName("Dates")
    private Collection<BenefitDate> dates;
	
	@SerializedName("RequestErrors")
    private Collection<BenefitRequestError> requestErrors;
	
	@SerializedName("Messages")
    private Collection<BenefitMessage> messages;
	
	@SerializedName("AdditionalInformation")
    private Collection<BenefitAdditionalInformation> additionalInformation;
	
	@SerializedName("RelatedEntities")
    private Collection<BenefitRelatedEntity> relatedEntities;
	
	@SerializedName("Person")
    private Person person;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public BenefitInformationCodeType getInformationCode() {
        return informationCode;
    }

    public void setInformationCode(BenefitInformationCodeType informationCode) {
        this.informationCode = informationCode;
    }

    public BenefitCoverageLevelCodeType getCoverageLevel() {
        return coverageLevel;
    }

    public void setCoverageLevel(BenefitCoverageLevelCodeType coverageLevel) {
        this.coverageLevel = coverageLevel;
    }

    public BenefitInsuranceTypeCodeType getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(BenefitInsuranceTypeCodeType insuranceType) {
        this.insuranceType = insuranceType;
    }

    public String getCoverageDescription() {
        return coverageDescription;
    }

    public void setCoverageDescription(String coverageDescription) {
        this.coverageDescription = coverageDescription;
    }

    public BenefitTimePeriodTypeCodeType getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(BenefitTimePeriodTypeCodeType timePeriod) {
        this.timePeriod = timePeriod;
    }

    public java.math.BigDecimal getMonetaryAmount() {
        return monetaryAmount;
    }

    public void setMonetaryAmount(java.math.BigDecimal monetaryAmount) {
        this.monetaryAmount = monetaryAmount;
    }

    public java.math.BigDecimal getPatientPercentage() {
        return patientPercentage;
    }

    public void setPatientPercentage(java.math.BigDecimal patientPercentage) {
        this.patientPercentage = patientPercentage;
    }

    public BenefitQuantityTypeCodeType getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(BenefitQuantityTypeCodeType quantityType) {
        this.quantityType = quantityType;
    }

    public java.math.BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(java.math.BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BenefitAuthorizationCertificationRequiredCodeType getAuthorizationRequired() {
        return authorizationRequired;
    }

    public void setAuthorizationRequired(BenefitAuthorizationCertificationRequiredCodeType authorizationRequired) {
        this.authorizationRequired = authorizationRequired;
    }

    public BenefitInPlanNetworkCodeType getInPlanNetwork() {
        return inPlanNetwork;
    }

    public void setInPlanNetwork(BenefitInPlanNetworkCodeType inPlanNetwork) {
        this.inPlanNetwork = inPlanNetwork;
    }

    public BenefitProductServiceIdTypeCodeType getProductServiceIdType() {
        return productServiceIdType;
    }

    public void setProductServiceIdType(BenefitProductServiceIdTypeCodeType productServiceIdType) {
        this.productServiceIdType = productServiceIdType;
    }

    public String getProductServiceId() {
        return productServiceId;
    }

    public void setProductServiceId(String productServiceId) {
        this.productServiceId = productServiceId;
    }

    public String getProductServiceModifier1() {
        return productServiceModifier1;
    }

    public void setProductServiceModifier1(String productServiceModifier1) {
        this.productServiceModifier1 = productServiceModifier1;
    }

    public String getProductServiceModifier2() {
        return productServiceModifier2;
    }

    public void setProductServiceModifier2(String productServiceModifier2) {
        this.productServiceModifier2 = productServiceModifier2;
    }

    public String getProductServiceModifier3() {
        return productServiceModifier3;
    }

    public void setProductServiceModifier3(String productServiceModifier3) {
        this.productServiceModifier3 = productServiceModifier3;
    }

    public String getProductServiceModifier4() {
        return productServiceModifier4;
    }

    public void setProductServiceModifier4(String productServiceModifier4) {
        this.productServiceModifier4 = productServiceModifier4;
    }

    public String getProductServiceIdEnd() {
        return productServiceIdEnd;
    }

    public void setProductServiceIdEnd(String productServiceIdEnd) {
        this.productServiceIdEnd = productServiceIdEnd;
    }

    public Long getDiagnosisPointer1() {
        return diagnosisPointer1;
    }

    public void setDiagnosisPointer1(Long diagnosisPointer1) {
        this.diagnosisPointer1 = diagnosisPointer1;
    }

    public Long getDiagnosisPointer2() {
        return diagnosisPointer2;
    }

    public void setDiagnosisPointer2(Long diagnosisPointer2) {
        this.diagnosisPointer2 = diagnosisPointer2;
    }

    public Long getDiagnosisPointer3() {
        return diagnosisPointer3;
    }

    public void setDiagnosisPointer3(Long diagnosisPointer3) {
        this.diagnosisPointer3 = diagnosisPointer3;
    }

    public Long getDiagnosisPointer4() {
        return diagnosisPointer4;
    }

    public void setDiagnosisPointer4(Long diagnosisPointer4) {
        this.diagnosisPointer4 = diagnosisPointer4;
    }

    public Collection<BenefitServiceTypeCode> getServiceTypes() {
        return serviceTypes;
    }

    public void setServiceTypes(Collection<BenefitServiceTypeCode> serviceTypes) {
        this.serviceTypes = serviceTypes;
    }

    public Collection<BenefitHealthCareServicesDeliveryRestriction> getHealthCareServiceDeliveryRestrictions() {
        return healthCareServiceDeliveryRestrictions;
    }

    public void setHealthCareServiceDeliveryRestrictions(
            Collection<BenefitHealthCareServicesDeliveryRestriction> healthCareServiceDeliveryRestrictions) {
        this.healthCareServiceDeliveryRestrictions = healthCareServiceDeliveryRestrictions;
    }

    public Collection<BenefitAdditionalId> getAdditionalIds() {
        return additionalIds;
    }

    public void setAdditionalIds(Collection<BenefitAdditionalId> additionalIds) {
        this.additionalIds = additionalIds;
    }

    public Collection<BenefitDate> getDates() {
        return dates;
    }

    public void setDates(Collection<BenefitDate> dates) {
        this.dates = dates;
    }

    public Collection<BenefitRequestError> getRequestErrors() {
        return requestErrors;
    }

    public void setRequestErrors(Collection<BenefitRequestError> requestErrors) {
        this.requestErrors = requestErrors;
    }

    public Collection<BenefitMessage> getMessages() {
        return messages;
    }

    public void setMessages(Collection<BenefitMessage> messages) {
        this.messages = messages;
    }

    public Collection<BenefitAdditionalInformation> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(Collection<BenefitAdditionalInformation> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public Collection<BenefitRelatedEntity> getRelatedEntities() {
        return relatedEntities;
    }

    public void setRelatedEntities(Collection<BenefitRelatedEntity> relatedEntities) {
        this.relatedEntities = relatedEntities;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setProductServiceModifiers(List<String> productServiceModifiers) {
        this.productServiceModifiers = productServiceModifiers;
    }

    public void setDiagnosisPointers(List<Long> diagnosisPointers) {
        this.diagnosisPointers = diagnosisPointers;
    }

    @SerializedName("ProductServiceModifiers")
    private List<String> productServiceModifiers;
    @SerializedName("DiagnosisPointers")
    private List<Long> diagnosisPointers;

    public final List<String> getProductServiceModifiers() {
        if (productServiceModifiers == null) {
            productServiceModifiers = new ArrayList<String>();
            if (productServiceModifier1 != null) {
                productServiceModifiers.add(productServiceModifier1);
            }
            if (productServiceModifier2 != null) {
                productServiceModifiers.add(productServiceModifier2);
            }
            if (productServiceModifier3 != null) {
                productServiceModifiers.add(productServiceModifier3);
            }
            if (productServiceModifier4 != null) {
                productServiceModifiers.add(productServiceModifier4);
            }
        }
        return productServiceModifiers;
    }

    public final List<Long> getDiagnosisPointers() {
        if (diagnosisPointers == null) {
            diagnosisPointers = new ArrayList<Long>();
            if (diagnosisPointer1 != null) {
                diagnosisPointers.add(diagnosisPointer1.longValue());
            }
            if (diagnosisPointer2 != null) {
                diagnosisPointers.add(diagnosisPointer2.longValue());
            }
            if (diagnosisPointer3 != null) {
                diagnosisPointers.add(diagnosisPointer3.longValue());
            }
            if (diagnosisPointer4 != null) {
                diagnosisPointers.add(diagnosisPointer4.longValue());
            }
        }
        return diagnosisPointers;
    }
}