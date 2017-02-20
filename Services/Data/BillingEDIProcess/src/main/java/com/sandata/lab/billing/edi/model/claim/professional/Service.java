package com.sandata.lab.billing.edi.model.claim.professional;

import java.util.ArrayList;
import java.util.Collection;

import com.sandata.lab.billing.edi.model.enums.AdditionalIdTypeCodeType;
import com.sandata.lab.billing.edi.model.enums.AmbulanceDistanceUnitType;
import com.sandata.lab.billing.edi.model.enums.AmbulanceTransportReasonType;
import com.sandata.lab.billing.edi.model.enums.ClaimPricingMethodologyType;
import com.sandata.lab.billing.edi.model.enums.ContractType;
import com.sandata.lab.billing.edi.model.enums.DrugCodeType;
import com.sandata.lab.billing.edi.model.enums.DrugQuantityUnitType;
import com.sandata.lab.billing.edi.model.enums.DurableMedicalEquipmentCertificationTimeFrameType;
import com.sandata.lab.billing.edi.model.enums.DurableMedicalEquipmentCertificationType;
import com.sandata.lab.billing.edi.model.enums.ProcedureServiceCodeType;
import com.sandata.lab.billing.edi.model.enums.RentalUnitPriceType;
import com.sandata.lab.billing.edi.model.enums.RepricingExceptionType;
import com.sandata.lab.billing.edi.model.enums.RepricingPolicyComplianceType;
import com.sandata.lab.billing.edi.model.enums.RepricingRejectReasonType;
import com.sandata.lab.billing.edi.model.enums.RepricingUnitBasisType;
import com.sandata.lab.billing.edi.model.enums.ResponseCodeType;
import com.sandata.lab.billing.edi.model.enums.ServiceCopayStatusType;
import com.sandata.lab.billing.edi.model.enums.ServiceQuantityType;

public class Service {
    private long id;
    private long claimId;

    private long lineNumber;

    private ProcedureServiceCodeType serviceCodeType;
    private String serviceCode;
    private String serviceCodeModifier1;
    private String serviceCodeModifier2;
    private String serviceCodeModifier3;
    private String serviceCodeModifier4;
    private String serviceCodeDescription;
    private java.math.BigDecimal charge = new java.math.BigDecimal(0);
    private ServiceQuantityType quantityType;
    private java.math.BigDecimal quantity = new java.math.BigDecimal(0);
    private long diagnosisPointer1;
    private Long diagnosisPointer2;
    private Long diagnosisPointer3;
    private Long diagnosisPointer4;
    private ResponseCodeType emergencyIndicator;
    private ResponseCodeType ePSDTIndicator;
    private ResponseCodeType familyPlanningIndicator;
    private ServiceCopayStatusType copayStatus;

    private ProcedureServiceCodeType dMEServiceCodeType;
    private String dMEServiceCode;
    private ServiceQuantityType dMEMedicalNecessityLengthType;
    private java.math.BigDecimal dMEMedicalNecessityLength;
    private java.math.BigDecimal dMERentalPrice;
    private java.math.BigDecimal dMEPurchasePrice;
    private RentalUnitPriceType dMERentalUnitPriceFrequency;

    private java.math.BigDecimal patientWeight;
    private AmbulanceTransportReasonType ambulanceTransportReason;
    private AmbulanceDistanceUnitType ambulanceDistanceUnit;
    private java.math.BigDecimal ambulanceTransportDistance;
    private String ambulanceRoundTripPurposeDescription;
    private String ambulanceStretcherPurposeDescription;

    private DurableMedicalEquipmentCertificationType dMECertification;
    private DurableMedicalEquipmentCertificationTimeFrameType dMETimeFrame;
    private java.math.BigDecimal dMEDuration;

    private ContractType contractType;
    private java.math.BigDecimal contractAmount;
    private java.math.BigDecimal contractPercentage;
    private String contractCode;
    private java.math.BigDecimal contractTermsDiscountPercentage;
    private String contractVersionId;

    private String purchasedServiceProviderId;
    private java.math.BigDecimal purchasedServiceChargeAmount;

    private ClaimPricingMethodologyType repricingMethodology;
    private java.math.BigDecimal repricedAllowedAmount;
    private java.math.BigDecimal repricedSavingAmount;
    private String repricingOrganizationId;
    private java.math.BigDecimal repricingPerDiemOrFlatRate;
    private String repricingApprovedAmbulatoryPatientGroupCode;
    private java.math.BigDecimal repricedApprovedAmbulatoryPatientGroupAmount;
    private ProcedureServiceCodeType repricedApprovedProcedureCodeType;
    private String repricedApprovedProcedureCode;
    private RepricingUnitBasisType repricingServiceUnitQuantityType;
    private java.math.BigDecimal repricingApprovedServiceUnitCount;
    private RepricingRejectReasonType repricingRejectReason;
    private RepricingPolicyComplianceType repricingPolicyCompliance;
    private RepricingExceptionType repricingException;

    private DrugCodeType drugCodeType;
    private String drugCode;
    private DrugQuantityUnitType drugUnitType;
    private java.math.BigDecimal drugUnitCount;
    private AdditionalIdTypeCodeType drugAdditionalIdType;
    private String drugAdditionalId;

    private Collection<ServiceAttachment> attachments = new ArrayList<ServiceAttachment>();

    public final Collection<ServiceAttachment> getAttachments() {
        return attachments;
    }

    public final void setAttachments(Collection<ServiceAttachment> value) {
        attachments = value;
    }

    private Collection<ServiceCertification> certifications = new ArrayList<ServiceCertification>();

    public final Collection<ServiceCertification> getCertifications() {
        return certifications;
    }

    public final void setCertifications(Collection<ServiceCertification> value) {
        certifications = value;
    }

    private Collection<ServiceDate> dates = new ArrayList<ServiceDate>();

    public final Collection<ServiceDate> getDates() {
        return dates;
    }

    public final void setDates(Collection<ServiceDate> value) {
        dates = value;
    }

    private Collection<ServiceQuantity> quantities = new ArrayList<ServiceQuantity>();

    public final Collection<ServiceQuantity> getQuantities() {
        return quantities;
    }

    public final void setQuantities(Collection<ServiceQuantity> value) {
        quantities = value;
    }

    private Collection<Measurement> measurements = new ArrayList<Measurement>();

    public final Collection<Measurement> getMeasurements() {
        return measurements;
    }

    public final void setMeasurements(Collection<Measurement> value) {
        measurements = value;
    }

    private Collection<ServiceAdditionalId> additionalIds = new ArrayList<ServiceAdditionalId>();

    public final Collection<ServiceAdditionalId> getAdditionalIds() {
        return additionalIds;
    }

    public final void setAdditionalIds(Collection<ServiceAdditionalId> value) {
        additionalIds = value;
    }

    private Collection<ServiceAmount> amounts = new ArrayList<ServiceAmount>();

    public final Collection<ServiceAmount> getAmounts() {
        return amounts;
    }

    public final void setAmounts(Collection<ServiceAmount> value) {
        amounts = value;
    }

    private Collection<ServiceFileInformation> fileInformationSegements = new ArrayList<ServiceFileInformation>();

    public final Collection<ServiceFileInformation> getFileInformationSegements() {
        return fileInformationSegements;
    }

    public final void setFileInformationSegements(Collection<ServiceFileInformation> value) {
        fileInformationSegements = value;
    }

    private Collection<ServiceNote> notes = new ArrayList<ServiceNote>();

    public final Collection<ServiceNote> getNotes() {
        return notes;
    }

    public final void setNotes(Collection<ServiceNote> value) {
        notes = value;
    }

    private Collection<ServiceEntity> entities = new ArrayList<ServiceEntity>();

    public final Collection<ServiceEntity> getEntities() {
        return entities;
    }

    public final void setEntities(Collection<ServiceEntity> value) {
        entities = value;
    }

    private Collection<ServiceAdjudicationInformation> adjudications = new ArrayList<ServiceAdjudicationInformation>();

    public final Collection<ServiceAdjudicationInformation> getAdjudications() {
        return adjudications;
    }

    public final void setAdjudications(Collection<ServiceAdjudicationInformation> value) {
        adjudications = value;
    }

    private Collection<Form> forms = new ArrayList<Form>();

    public final Collection<Form> getForms() {
        return forms;
    }

    public final void setForms(Collection<Form> value) {
        forms = value;
    }

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

    public long getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(long lineNumber) {
        this.lineNumber = lineNumber;
    }

    public ProcedureServiceCodeType getServiceCodeType() {
        return serviceCodeType;
    }

    public void setServiceCodeType(ProcedureServiceCodeType serviceCodeType) {
        this.serviceCodeType = serviceCodeType;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceCodeModifier1() {
        return serviceCodeModifier1;
    }

    public void setServiceCodeModifier1(String serviceCodeModifier1) {
        this.serviceCodeModifier1 = serviceCodeModifier1;
    }

    public String getServiceCodeModifier2() {
        return serviceCodeModifier2;
    }

    public void setServiceCodeModifier2(String serviceCodeModifier2) {
        this.serviceCodeModifier2 = serviceCodeModifier2;
    }

    public String getServiceCodeModifier3() {
        return serviceCodeModifier3;
    }

    public void setServiceCodeModifier3(String serviceCodeModifier3) {
        this.serviceCodeModifier3 = serviceCodeModifier3;
    }

    public String getServiceCodeModifier4() {
        return serviceCodeModifier4;
    }

    public void setServiceCodeModifier4(String serviceCodeModifier4) {
        this.serviceCodeModifier4 = serviceCodeModifier4;
    }

    public String getServiceCodeDescription() {
        return serviceCodeDescription;
    }

    public void setServiceCodeDescription(String serviceCodeDescription) {
        this.serviceCodeDescription = serviceCodeDescription;
    }

    public java.math.BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(java.math.BigDecimal charge) {
        this.charge = charge;
    }

    public ServiceQuantityType getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(ServiceQuantityType quantityType) {
        this.quantityType = quantityType;
    }

    public java.math.BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(java.math.BigDecimal quantity) {
        this.quantity = quantity;
    }

    public long getDiagnosisPointer1() {
        return diagnosisPointer1;
    }

    public void setDiagnosisPointer1(long diagnosisPointer1) {
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

    public ResponseCodeType getEmergencyIndicator() {
        return emergencyIndicator;
    }

    public void setEmergencyIndicator(ResponseCodeType emergencyIndicator) {
        this.emergencyIndicator = emergencyIndicator;
    }

    public ResponseCodeType getePSDTIndicator() {
        return ePSDTIndicator;
    }

    public void setePSDTIndicator(ResponseCodeType ePSDTIndicator) {
        this.ePSDTIndicator = ePSDTIndicator;
    }

    public ResponseCodeType getFamilyPlanningIndicator() {
        return familyPlanningIndicator;
    }

    public void setFamilyPlanningIndicator(ResponseCodeType familyPlanningIndicator) {
        this.familyPlanningIndicator = familyPlanningIndicator;
    }

    public ServiceCopayStatusType getCopayStatus() {
        return copayStatus;
    }

    public void setCopayStatus(ServiceCopayStatusType copayStatus) {
        this.copayStatus = copayStatus;
    }

    public ProcedureServiceCodeType getdMEServiceCodeType() {
        return dMEServiceCodeType;
    }

    public void setdMEServiceCodeType(ProcedureServiceCodeType dMEServiceCodeType) {
        this.dMEServiceCodeType = dMEServiceCodeType;
    }

    public String getdMEServiceCode() {
        return dMEServiceCode;
    }

    public void setdMEServiceCode(String dMEServiceCode) {
        this.dMEServiceCode = dMEServiceCode;
    }

    public ServiceQuantityType getdMEMedicalNecessityLengthType() {
        return dMEMedicalNecessityLengthType;
    }

    public void setdMEMedicalNecessityLengthType(ServiceQuantityType dMEMedicalNecessityLengthType) {
        this.dMEMedicalNecessityLengthType = dMEMedicalNecessityLengthType;
    }

    public java.math.BigDecimal getdMEMedicalNecessityLength() {
        return dMEMedicalNecessityLength;
    }

    public void setdMEMedicalNecessityLength(java.math.BigDecimal dMEMedicalNecessityLength) {
        this.dMEMedicalNecessityLength = dMEMedicalNecessityLength;
    }

    public java.math.BigDecimal getdMERentalPrice() {
        return dMERentalPrice;
    }

    public void setdMERentalPrice(java.math.BigDecimal dMERentalPrice) {
        this.dMERentalPrice = dMERentalPrice;
    }

    public java.math.BigDecimal getdMEPurchasePrice() {
        return dMEPurchasePrice;
    }

    public void setdMEPurchasePrice(java.math.BigDecimal dMEPurchasePrice) {
        this.dMEPurchasePrice = dMEPurchasePrice;
    }

    public RentalUnitPriceType getdMERentalUnitPriceFrequency() {
        return dMERentalUnitPriceFrequency;
    }

    public void setdMERentalUnitPriceFrequency(RentalUnitPriceType dMERentalUnitPriceFrequency) {
        this.dMERentalUnitPriceFrequency = dMERentalUnitPriceFrequency;
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

    public DurableMedicalEquipmentCertificationType getdMECertification() {
        return dMECertification;
    }

    public void setdMECertification(DurableMedicalEquipmentCertificationType dMECertification) {
        this.dMECertification = dMECertification;
    }

    public DurableMedicalEquipmentCertificationTimeFrameType getdMETimeFrame() {
        return dMETimeFrame;
    }

    public void setdMETimeFrame(DurableMedicalEquipmentCertificationTimeFrameType dMETimeFrame) {
        this.dMETimeFrame = dMETimeFrame;
    }

    public java.math.BigDecimal getdMEDuration() {
        return dMEDuration;
    }

    public void setdMEDuration(java.math.BigDecimal dMEDuration) {
        this.dMEDuration = dMEDuration;
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

    public String getPurchasedServiceProviderId() {
        return purchasedServiceProviderId;
    }

    public void setPurchasedServiceProviderId(String purchasedServiceProviderId) {
        this.purchasedServiceProviderId = purchasedServiceProviderId;
    }

    public java.math.BigDecimal getPurchasedServiceChargeAmount() {
        return purchasedServiceChargeAmount;
    }

    public void setPurchasedServiceChargeAmount(java.math.BigDecimal purchasedServiceChargeAmount) {
        this.purchasedServiceChargeAmount = purchasedServiceChargeAmount;
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

    public ProcedureServiceCodeType getRepricedApprovedProcedureCodeType() {
        return repricedApprovedProcedureCodeType;
    }

    public void setRepricedApprovedProcedureCodeType(ProcedureServiceCodeType repricedApprovedProcedureCodeType) {
        this.repricedApprovedProcedureCodeType = repricedApprovedProcedureCodeType;
    }

    public String getRepricedApprovedProcedureCode() {
        return repricedApprovedProcedureCode;
    }

    public void setRepricedApprovedProcedureCode(String repricedApprovedProcedureCode) {
        this.repricedApprovedProcedureCode = repricedApprovedProcedureCode;
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

    public DrugCodeType getDrugCodeType() {
        return drugCodeType;
    }

    public void setDrugCodeType(DrugCodeType drugCodeType) {
        this.drugCodeType = drugCodeType;
    }

    public String getDrugCode() {
        return drugCode;
    }

    public void setDrugCode(String drugCode) {
        this.drugCode = drugCode;
    }

    public DrugQuantityUnitType getDrugUnitType() {
        return drugUnitType;
    }

    public void setDrugUnitType(DrugQuantityUnitType drugUnitType) {
        this.drugUnitType = drugUnitType;
    }

    public java.math.BigDecimal getDrugUnitCount() {
        return drugUnitCount;
    }

    public void setDrugUnitCount(java.math.BigDecimal drugUnitCount) {
        this.drugUnitCount = drugUnitCount;
    }

    public AdditionalIdTypeCodeType getDrugAdditionalIdType() {
        return drugAdditionalIdType;
    }

    public void setDrugAdditionalIdType(AdditionalIdTypeCodeType drugAdditionalIdType) {
        this.drugAdditionalIdType = drugAdditionalIdType;
    }

    public String getDrugAdditionalId() {
        return drugAdditionalId;
    }

    public void setDrugAdditionalId(String drugAdditionalId) {
        this.drugAdditionalId = drugAdditionalId;
    }
}