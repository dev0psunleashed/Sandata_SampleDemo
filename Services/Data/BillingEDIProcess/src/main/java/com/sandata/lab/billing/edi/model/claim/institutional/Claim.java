package com.sandata.lab.billing.edi.model.claim.institutional;

import java.util.ArrayList;
import java.util.Collection;

import com.sandata.lab.billing.edi.model.enums.CertificationConditionIndicatorType;
import com.sandata.lab.billing.edi.model.enums.CertificationConditionType;
import com.sandata.lab.billing.edi.model.enums.ClaimDelayReasonType;
import com.sandata.lab.billing.edi.model.enums.ClaimFrequencyType;
import com.sandata.lab.billing.edi.model.enums.ClaimPricingMethodologyType;
import com.sandata.lab.billing.edi.model.enums.ClaimReleaseOfInformationCodeType;
import com.sandata.lab.billing.edi.model.enums.ContractType;
import com.sandata.lab.billing.edi.model.enums.ProviderPlanParticipationCodeType;
import com.sandata.lab.billing.edi.model.enums.RepricingExceptionType;
import com.sandata.lab.billing.edi.model.enums.RepricingPolicyComplianceType;
import com.sandata.lab.billing.edi.model.enums.RepricingRejectReasonType;
import com.sandata.lab.billing.edi.model.enums.RepricingUnitBasisType;
import com.sandata.lab.billing.edi.model.enums.ResponseCodeType;

public class Claim {
    private long id;
    private long personId;

    private String controlNumber;
    private java.math.BigDecimal totalCharge = new java.math.BigDecimal(0);
    private String facilityCode;
    private ClaimFrequencyType frequency;
    private ProviderPlanParticipationCodeType providerPlanParticipation;
    private ResponseCodeType benefitsAssigned;
    private ClaimReleaseOfInformationCodeType releaseOfInformation;
    private ClaimDelayReasonType delayReason;

    private String admissionTypeCode;
    private String admissionSourceCode;
    private String patientStatusCode;

    private ContractType contractType;
    private java.math.BigDecimal contractAmount;
    private java.math.BigDecimal contractPercentage;
    private String contractCode;
    private java.math.BigDecimal contractTermsDiscountPercentage;
    private String contractVersionId;

    private java.math.BigDecimal estimatedPatientResponsibilityAmount;

    private CertificationConditionType ePDSTReferralType;
    private ResponseCodeType ePDSTCertificationCodeApplies;
    private CertificationConditionIndicatorType ePDSTCondition1;
    private CertificationConditionIndicatorType ePDSTCondition2;
    private CertificationConditionIndicatorType ePDSTCondition3;

    private ClaimPricingMethodologyType repricingMethodology;
    private java.math.BigDecimal repricedAllowedAmount;
    private java.math.BigDecimal repricedSavingAmount;
    private String repricingOrganizationId;
    private java.math.BigDecimal repricingPerDiemOrFlatRate;
    private String repricingApprovedDRGCode;
    private java.math.BigDecimal repricedApprovedDRGAmount;
    private String repricedApprovedRevenueCode;
    private RepricingUnitBasisType repricingServiceUnitQuantityType;
    private java.math.BigDecimal repricingApprovedServiceUnitCount;
    private RepricingRejectReasonType repricingRejectReason;
    private RepricingPolicyComplianceType repricingPolicyCompliance;
    private RepricingExceptionType repricingException;

    private Collection<ClaimDate> dates = new ArrayList<ClaimDate>();

    public final Collection<ClaimDate> getDates() {
        return dates;
    }

    public final void setDates(Collection<ClaimDate> value) {
        dates = value;
    }

    private Collection<ClaimAttachment> attachments = new ArrayList<ClaimAttachment>();

    public final Collection<ClaimAttachment> getAttachments() {
        return attachments;
    }

    public final void setAttachments(Collection<ClaimAttachment> value) {
        attachments = value;
    }

    private Collection<ClaimAdditionalId> additionalIds = new ArrayList<ClaimAdditionalId>();

    public final Collection<ClaimAdditionalId> getAdditionalIds() {
        return additionalIds;
    }

    public final void setAdditionalIds(Collection<ClaimAdditionalId> value) {
        additionalIds = value;
    }

    private Collection<FileInformation> fileInformationSegements = new ArrayList<FileInformation>();

    public final Collection<FileInformation> getFileInformationSegements() {
        return fileInformationSegements;
    }

    public final void setFileInformationSegements(Collection<FileInformation> value) {
        fileInformationSegements = value;
    }

    private Collection<ClaimNote> notes = new ArrayList<ClaimNote>();

    public final Collection<ClaimNote> getNotes() {
        return notes;
    }

    public final void setNotes(Collection<ClaimNote> value) {
        notes = value;
    }

    private Collection<HealthCareInformationCode> healthCareInformationCodes = new ArrayList<HealthCareInformationCode>();

    public final Collection<HealthCareInformationCode> getHealthCareInformationCodes() {
        return healthCareInformationCodes;
    }

    public final void setHealthCareInformationCodes(Collection<HealthCareInformationCode> value) {
        healthCareInformationCodes = value;
    }

    private Collection<ClaimEntity> entities = new ArrayList<ClaimEntity>();

    public final Collection<ClaimEntity> getEntities() {
        return entities;
    }

    public final void setEntities(Collection<ClaimEntity> value) {
        entities = value;
    }

    private Collection<OtherSubscriberInformation> otherSubscribers = new ArrayList<OtherSubscriberInformation>();

    public final Collection<OtherSubscriberInformation> getOtherSubscribers() {
        return otherSubscribers;
    }

    public final void setOtherSubscribers(Collection<OtherSubscriberInformation> value) {
        otherSubscribers = value;
    }

    private Collection<Service> services = new ArrayList<Service>();

    public final Collection<Service> getServices() {
        return services;
    }

    public final void setServices(Collection<Service> value) {
        services = value;
    }

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

    public String getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber;
    }

    public java.math.BigDecimal getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(java.math.BigDecimal totalCharge) {
        this.totalCharge = totalCharge;
    }

    public String getFacilityCode() {
        return facilityCode;
    }

    public void setFacilityCode(String facilityCode) {
        this.facilityCode = facilityCode;
    }

    public ClaimFrequencyType getFrequency() {
        return frequency;
    }

    public void setFrequency(ClaimFrequencyType frequency) {
        this.frequency = frequency;
    }

    public ProviderPlanParticipationCodeType getProviderPlanParticipation() {
        return providerPlanParticipation;
    }

    public void setProviderPlanParticipation(ProviderPlanParticipationCodeType providerPlanParticipation) {
        this.providerPlanParticipation = providerPlanParticipation;
    }

    public ResponseCodeType getBenefitsAssigned() {
        return benefitsAssigned;
    }

    public void setBenefitsAssigned(ResponseCodeType benefitsAssigned) {
        this.benefitsAssigned = benefitsAssigned;
    }

    public ClaimReleaseOfInformationCodeType getReleaseOfInformation() {
        return releaseOfInformation;
    }

    public void setReleaseOfInformation(ClaimReleaseOfInformationCodeType releaseOfInformation) {
        this.releaseOfInformation = releaseOfInformation;
    }

    public ClaimDelayReasonType getDelayReason() {
        return delayReason;
    }

    public void setDelayReason(ClaimDelayReasonType delayReason) {
        this.delayReason = delayReason;
    }

    public String getAdmissionTypeCode() {
        return admissionTypeCode;
    }

    public void setAdmissionTypeCode(String admissionTypeCode) {
        this.admissionTypeCode = admissionTypeCode;
    }

    public String getAdmissionSourceCode() {
        return admissionSourceCode;
    }

    public void setAdmissionSourceCode(String admissionSourceCode) {
        this.admissionSourceCode = admissionSourceCode;
    }

    public String getPatientStatusCode() {
        return patientStatusCode;
    }

    public void setPatientStatusCode(String patientStatusCode) {
        this.patientStatusCode = patientStatusCode;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public java.math.BigDecimal getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(java.math.BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    public java.math.BigDecimal getContractPercentage() {
        return contractPercentage;
    }

    public void setContractPercentage(java.math.BigDecimal contractPercentage) {
        this.contractPercentage = contractPercentage;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public java.math.BigDecimal getContractTermsDiscountPercentage() {
        return contractTermsDiscountPercentage;
    }

    public void setContractTermsDiscountPercentage(java.math.BigDecimal contractTermsDiscountPercentage) {
        this.contractTermsDiscountPercentage = contractTermsDiscountPercentage;
    }

    public String getContractVersionId() {
        return contractVersionId;
    }

    public void setContractVersionId(String contractVersionId) {
        this.contractVersionId = contractVersionId;
    }

    public java.math.BigDecimal getEstimatedPatientResponsibilityAmount() {
        return estimatedPatientResponsibilityAmount;
    }

    public void setEstimatedPatientResponsibilityAmount(java.math.BigDecimal estimatedPatientResponsibilityAmount) {
        this.estimatedPatientResponsibilityAmount = estimatedPatientResponsibilityAmount;
    }

    public CertificationConditionType getePDSTReferralType() {
        return ePDSTReferralType;
    }

    public void setePDSTReferralType(CertificationConditionType ePDSTReferralType) {
        this.ePDSTReferralType = ePDSTReferralType;
    }

    public ResponseCodeType getePDSTCertificationCodeApplies() {
        return ePDSTCertificationCodeApplies;
    }

    public void setePDSTCertificationCodeApplies(ResponseCodeType ePDSTCertificationCodeApplies) {
        this.ePDSTCertificationCodeApplies = ePDSTCertificationCodeApplies;
    }

    public CertificationConditionIndicatorType getePDSTCondition1() {
        return ePDSTCondition1;
    }

    public void setePDSTCondition1(CertificationConditionIndicatorType ePDSTCondition1) {
        this.ePDSTCondition1 = ePDSTCondition1;
    }

    public CertificationConditionIndicatorType getePDSTCondition2() {
        return ePDSTCondition2;
    }

    public void setePDSTCondition2(CertificationConditionIndicatorType ePDSTCondition2) {
        this.ePDSTCondition2 = ePDSTCondition2;
    }

    public CertificationConditionIndicatorType getePDSTCondition3() {
        return ePDSTCondition3;
    }

    public void setePDSTCondition3(CertificationConditionIndicatorType ePDSTCondition3) {
        this.ePDSTCondition3 = ePDSTCondition3;
    }

    public ClaimPricingMethodologyType getRepricingMethodology() {
        return repricingMethodology;
    }

    public void setRepricingMethodology(ClaimPricingMethodologyType repricingMethodology) {
        this.repricingMethodology = repricingMethodology;
    }

    public java.math.BigDecimal getRepricedAllowedAmount() {
        return repricedAllowedAmount;
    }

    public void setRepricedAllowedAmount(java.math.BigDecimal repricedAllowedAmount) {
        this.repricedAllowedAmount = repricedAllowedAmount;
    }

    public java.math.BigDecimal getRepricedSavingAmount() {
        return repricedSavingAmount;
    }

    public void setRepricedSavingAmount(java.math.BigDecimal repricedSavingAmount) {
        this.repricedSavingAmount = repricedSavingAmount;
    }

    public String getRepricingOrganizationId() {
        return repricingOrganizationId;
    }

    public void setRepricingOrganizationId(String repricingOrganizationId) {
        this.repricingOrganizationId = repricingOrganizationId;
    }

    public java.math.BigDecimal getRepricingPerDiemOrFlatRate() {
        return repricingPerDiemOrFlatRate;
    }

    public void setRepricingPerDiemOrFlatRate(java.math.BigDecimal repricingPerDiemOrFlatRate) {
        this.repricingPerDiemOrFlatRate = repricingPerDiemOrFlatRate;
    }

    public String getRepricingApprovedDRGCode() {
        return repricingApprovedDRGCode;
    }

    public void setRepricingApprovedDRGCode(String repricingApprovedDRGCode) {
        this.repricingApprovedDRGCode = repricingApprovedDRGCode;
    }

    public java.math.BigDecimal getRepricedApprovedDRGAmount() {
        return repricedApprovedDRGAmount;
    }

    public void setRepricedApprovedDRGAmount(java.math.BigDecimal repricedApprovedDRGAmount) {
        this.repricedApprovedDRGAmount = repricedApprovedDRGAmount;
    }

    public String getRepricedApprovedRevenueCode() {
        return repricedApprovedRevenueCode;
    }

    public void setRepricedApprovedRevenueCode(String repricedApprovedRevenueCode) {
        this.repricedApprovedRevenueCode = repricedApprovedRevenueCode;
    }

    public RepricingUnitBasisType getRepricingServiceUnitQuantityType() {
        return repricingServiceUnitQuantityType;
    }

    public void setRepricingServiceUnitQuantityType(RepricingUnitBasisType repricingServiceUnitQuantityType) {
        this.repricingServiceUnitQuantityType = repricingServiceUnitQuantityType;
    }

    public java.math.BigDecimal getRepricingApprovedServiceUnitCount() {
        return repricingApprovedServiceUnitCount;
    }

    public void setRepricingApprovedServiceUnitCount(java.math.BigDecimal repricingApprovedServiceUnitCount) {
        this.repricingApprovedServiceUnitCount = repricingApprovedServiceUnitCount;
    }

    public RepricingRejectReasonType getRepricingRejectReason() {
        return repricingRejectReason;
    }

    public void setRepricingRejectReason(RepricingRejectReasonType repricingRejectReason) {
        this.repricingRejectReason = repricingRejectReason;
    }

    public RepricingPolicyComplianceType getRepricingPolicyCompliance() {
        return repricingPolicyCompliance;
    }

    public void setRepricingPolicyCompliance(RepricingPolicyComplianceType repricingPolicyCompliance) {
        this.repricingPolicyCompliance = repricingPolicyCompliance;
    }

    public RepricingExceptionType getRepricingException() {
        return repricingException;
    }

    public void setRepricingException(RepricingExceptionType repricingException) {
        this.repricingException = repricingException;
    }
}