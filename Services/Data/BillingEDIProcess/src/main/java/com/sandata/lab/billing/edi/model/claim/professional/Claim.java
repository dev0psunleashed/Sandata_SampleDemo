package com.sandata.lab.billing.edi.model.claim.professional;

import java.util.ArrayList;
import java.util.Collection;

import com.sandata.lab.billing.edi.model.enums.AmbulanceDistanceUnitType;
import com.sandata.lab.billing.edi.model.enums.AmbulanceTransportReasonType;
import com.sandata.lab.billing.edi.model.enums.ClaimDelayReasonType;
import com.sandata.lab.billing.edi.model.enums.ClaimFrequencyType;
import com.sandata.lab.billing.edi.model.enums.ClaimPatientSignatureSourceType;
import com.sandata.lab.billing.edi.model.enums.ClaimPricingMethodologyType;
import com.sandata.lab.billing.edi.model.enums.ClaimRelatedCausesType;
import com.sandata.lab.billing.edi.model.enums.ClaimReleaseOfInformationCodeType;
import com.sandata.lab.billing.edi.model.enums.ClaimSpecialProgramType;
import com.sandata.lab.billing.edi.model.enums.ContractType;
import com.sandata.lab.billing.edi.model.enums.ProviderPlanParticipationCodeType;
import com.sandata.lab.billing.edi.model.enums.RepricingExceptionType;
import com.sandata.lab.billing.edi.model.enums.RepricingPolicyComplianceType;
import com.sandata.lab.billing.edi.model.enums.RepricingRejectReasonType;
import com.sandata.lab.billing.edi.model.enums.ResponseCodeType;
import com.sandata.lab.billing.edi.model.enums.SpinalManipulationConditionType;

public class Claim {
    private long id;
    private long personId;

    private String controlNumber;
    private java.math.BigDecimal totalCharge = new java.math.BigDecimal(0);
    private String facilityCode;
    private ClaimFrequencyType frequency;
    private ResponseCodeType providerOrSupplierSignatureIndicator;
    private ProviderPlanParticipationCodeType providerPlanParticipation;
    private ResponseCodeType benefitsAssigned;
    private ClaimReleaseOfInformationCodeType releaseOfInformation;
    private ClaimPatientSignatureSourceType patientSignatureSource;
    private ClaimRelatedCausesType relatedCause;
    private ClaimRelatedCausesType additionalRelatedCause;
    private String autoAccidentState;
    private String autoAccidentCountry;
    private ClaimSpecialProgramType specialProgram;
    private ClaimDelayReasonType delayReason;

    private ContractType contractType;
    private java.math.BigDecimal contractAmount;
    private java.math.BigDecimal contractPercentage;
    private String contractCode;
    private java.math.BigDecimal contractTermsDiscountPercentage;
    private String contractVersionId;

    private java.math.BigDecimal patientPaidAmount;

    private java.math.BigDecimal patientWeight;
    private AmbulanceTransportReasonType ambulanceTransportReason;
    private AmbulanceDistanceUnitType ambulanceDistanceUnit;
    private java.math.BigDecimal ambulanceTransportDistance;
    private String ambulanceRoundTripPurposeDescription;
    private String ambulanceStretcherPurposeDescription;

    private SpinalManipulationConditionType spinalManipulationPatientCondition;
    private String spinalManipulationConditionDescription;
    private String spinalManipulationAdditionalConditionDescription;

    private ClaimPricingMethodologyType repricingMethodology;
    private java.math.BigDecimal repricedAllowedAmount;
    private java.math.BigDecimal repricedSavingAmount;
    private String repricingOrganizationId;
    private java.math.BigDecimal repricingPerDiemOrFlatRate;
    private String repricingApprovedAmbulatoryPatientGroupCode;
    private java.math.BigDecimal repricedApprovedAmbulatoryPatientGroupAmount;
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

    private Collection<ClaimFileInformation> fileInformationSegements = new ArrayList<ClaimFileInformation>();

    public final Collection<ClaimFileInformation> getFileInformationSegements() {
        return fileInformationSegements;
    }

    public final void setFileInformationSegements(Collection<ClaimFileInformation> value) {
        fileInformationSegements = value;
    }

    private Collection<ClaimNote> notes = new ArrayList<ClaimNote>();

    public final Collection<ClaimNote> getNotes() {
        return notes;
    }

    public final void setNotes(Collection<ClaimNote> value) {
        notes = value;
    }

    private Collection<ClaimCertification> certifications = new ArrayList<ClaimCertification>();

    public final Collection<ClaimCertification> getCertifications() {
        return certifications;
    }

    public final void setCertifications(Collection<ClaimCertification> value) {
        certifications = value;
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

    public ResponseCodeType getProviderOrSupplierSignatureIndicator() {
        return providerOrSupplierSignatureIndicator;
    }

    public void setProviderOrSupplierSignatureIndicator(ResponseCodeType providerOrSupplierSignatureIndicator) {
        this.providerOrSupplierSignatureIndicator = providerOrSupplierSignatureIndicator;
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

    public ClaimPatientSignatureSourceType getPatientSignatureSource() {
        return patientSignatureSource;
    }

    public void setPatientSignatureSource(ClaimPatientSignatureSourceType patientSignatureSource) {
        this.patientSignatureSource = patientSignatureSource;
    }

    public ClaimRelatedCausesType getRelatedCause() {
        return relatedCause;
    }

    public void setRelatedCause(ClaimRelatedCausesType relatedCause) {
        this.relatedCause = relatedCause;
    }

    public ClaimRelatedCausesType getAdditionalRelatedCause() {
        return additionalRelatedCause;
    }

    public void setAdditionalRelatedCause(ClaimRelatedCausesType additionalRelatedCause) {
        this.additionalRelatedCause = additionalRelatedCause;
    }

    public String getAutoAccidentState() {
        return autoAccidentState;
    }

    public void setAutoAccidentState(String autoAccidentState) {
        this.autoAccidentState = autoAccidentState;
    }

    public String getAutoAccidentCountry() {
        return autoAccidentCountry;
    }

    public void setAutoAccidentCountry(String autoAccidentCountry) {
        this.autoAccidentCountry = autoAccidentCountry;
    }

    public ClaimSpecialProgramType getSpecialProgram() {
        return specialProgram;
    }

    public void setSpecialProgram(ClaimSpecialProgramType specialProgram) {
        this.specialProgram = specialProgram;
    }

    public ClaimDelayReasonType getDelayReason() {
        return delayReason;
    }

    public void setDelayReason(ClaimDelayReasonType delayReason) {
        this.delayReason = delayReason;
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

    public java.math.BigDecimal getPatientPaidAmount() {
        return patientPaidAmount;
    }

    public void setPatientPaidAmount(java.math.BigDecimal patientPaidAmount) {
        this.patientPaidAmount = patientPaidAmount;
    }

    public java.math.BigDecimal getPatientWeight() {
        return patientWeight;
    }

    public void setPatientWeight(java.math.BigDecimal patientWeight) {
        this.patientWeight = patientWeight;
    }

    public AmbulanceTransportReasonType getAmbulanceTransportReason() {
        return ambulanceTransportReason;
    }

    public void setAmbulanceTransportReason(AmbulanceTransportReasonType ambulanceTransportReason) {
        this.ambulanceTransportReason = ambulanceTransportReason;
    }

    public AmbulanceDistanceUnitType getAmbulanceDistanceUnit() {
        return ambulanceDistanceUnit;
    }

    public void setAmbulanceDistanceUnit(AmbulanceDistanceUnitType ambulanceDistanceUnit) {
        this.ambulanceDistanceUnit = ambulanceDistanceUnit;
    }

    public java.math.BigDecimal getAmbulanceTransportDistance() {
        return ambulanceTransportDistance;
    }

    public void setAmbulanceTransportDistance(java.math.BigDecimal ambulanceTransportDistance) {
        this.ambulanceTransportDistance = ambulanceTransportDistance;
    }

    public String getAmbulanceRoundTripPurposeDescription() {
        return ambulanceRoundTripPurposeDescription;
    }

    public void setAmbulanceRoundTripPurposeDescription(String ambulanceRoundTripPurposeDescription) {
        this.ambulanceRoundTripPurposeDescription = ambulanceRoundTripPurposeDescription;
    }

    public String getAmbulanceStretcherPurposeDescription() {
        return ambulanceStretcherPurposeDescription;
    }

    public void setAmbulanceStretcherPurposeDescription(String ambulanceStretcherPurposeDescription) {
        this.ambulanceStretcherPurposeDescription = ambulanceStretcherPurposeDescription;
    }

    public SpinalManipulationConditionType getSpinalManipulationPatientCondition() {
        return spinalManipulationPatientCondition;
    }

    public void setSpinalManipulationPatientCondition(SpinalManipulationConditionType spinalManipulationPatientCondition) {
        this.spinalManipulationPatientCondition = spinalManipulationPatientCondition;
    }

    public String getSpinalManipulationConditionDescription() {
        return spinalManipulationConditionDescription;
    }

    public void setSpinalManipulationConditionDescription(String spinalManipulationConditionDescription) {
        this.spinalManipulationConditionDescription = spinalManipulationConditionDescription;
    }

    public String getSpinalManipulationAdditionalConditionDescription() {
        return spinalManipulationAdditionalConditionDescription;
    }

    public void setSpinalManipulationAdditionalConditionDescription(
            String spinalManipulationAdditionalConditionDescription) {
        this.spinalManipulationAdditionalConditionDescription = spinalManipulationAdditionalConditionDescription;
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

    public String getRepricingApprovedAmbulatoryPatientGroupCode() {
        return repricingApprovedAmbulatoryPatientGroupCode;
    }

    public void setRepricingApprovedAmbulatoryPatientGroupCode(String repricingApprovedAmbulatoryPatientGroupCode) {
        this.repricingApprovedAmbulatoryPatientGroupCode = repricingApprovedAmbulatoryPatientGroupCode;
    }

    public java.math.BigDecimal getRepricedApprovedAmbulatoryPatientGroupAmount() {
        return repricedApprovedAmbulatoryPatientGroupAmount;
    }

    public void setRepricedApprovedAmbulatoryPatientGroupAmount(
            java.math.BigDecimal repricedApprovedAmbulatoryPatientGroupAmount) {
        this.repricedApprovedAmbulatoryPatientGroupAmount = repricedApprovedAmbulatoryPatientGroupAmount;
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