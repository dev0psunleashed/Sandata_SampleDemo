package com.sandata.lab.billing.edi.model.claim.institutional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.sandata.lab.billing.edi.model.enums.AdditionalIdTypeCodeType;
import com.sandata.lab.billing.edi.model.enums.ClaimPricingMethodologyType;
import com.sandata.lab.billing.edi.model.enums.DrugQuantityUnitType;
import com.sandata.lab.billing.edi.model.enums.ProcedureServiceCodeType;
import com.sandata.lab.billing.edi.model.enums.RepricingExceptionType;
import com.sandata.lab.billing.edi.model.enums.RepricingPolicyComplianceType;
import com.sandata.lab.billing.edi.model.enums.RepricingRejectReasonType;
import com.sandata.lab.billing.edi.model.enums.RepricingUnitBasisType;
import com.sandata.lab.billing.edi.model.enums.ServiceQuantityType;

public class Service {
    private long id;
    private long claimId;

    private long lineNumber;

    private String revenueCode;
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
    private java.math.BigDecimal deniedOrNonCoveredCharge;

    private Date date = new Date(0);

    private String tPONote;

    private ClaimPricingMethodologyType repricingMethodology;
    private java.math.BigDecimal repricedAllowedAmount;
    private java.math.BigDecimal repricedSavingAmount;
    private String repricingOrganizationId;
    private java.math.BigDecimal repricingPerDiemOrFlatRate;
    private String repricingApprovedDRGCode;
    private java.math.BigDecimal repricedApprovedDRGAmount;
    private String repricedApprovedRevenueCode;
    private ProcedureServiceCodeType repricedApprovedProcedureCodeType;
    private String repricedApprovedProcedureCode;
    private RepricingUnitBasisType repricingServiceUnitQuantityType;
    private java.math.BigDecimal repricingApprovedServiceUnitCount;
    private RepricingRejectReasonType repricingRejectReason;
    private RepricingPolicyComplianceType repricingPolicyCompliance;
    private RepricingExceptionType repricingException;

    private String nationalDrugCode;
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

    public String getRevenueCode() {
        return revenueCode;
    }

    public void setRevenueCode(String revenueCode) {
        this.revenueCode = revenueCode;
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

    public java.math.BigDecimal getDeniedOrNonCoveredCharge() {
        return deniedOrNonCoveredCharge;
    }

    public void setDeniedOrNonCoveredCharge(java.math.BigDecimal deniedOrNonCoveredCharge) {
        this.deniedOrNonCoveredCharge = deniedOrNonCoveredCharge;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String gettPONote() {
        return tPONote;
    }

    public void settPONote(String tPONote) {
        this.tPONote = tPONote;
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

    public String getNationalDrugCode() {
        return nationalDrugCode;
    }

    public void setNationalDrugCode(String nationalDrugCode) {
        this.nationalDrugCode = nationalDrugCode;
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