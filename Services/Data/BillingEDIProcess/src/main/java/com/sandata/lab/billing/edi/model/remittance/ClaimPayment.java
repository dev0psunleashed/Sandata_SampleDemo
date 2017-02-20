package com.sandata.lab.billing.edi.model.remittance;

import java.util.*;

import com.sandata.lab.billing.edi.model.enums.*;

public class ClaimPayment {
    private long id;
    private long claimPaymentGroupId;

    private String patientControlNumber;
    private ClaimStatusType status;
    private java.math.BigDecimal totalClaimCharge = new java.math.BigDecimal(0);
    private java.math.BigDecimal paymentAmount = new java.math.BigDecimal(0);
    private java.math.BigDecimal patientResponsibilityAmount;
    private ClaimFilingIndicatorType claimFilingIndicator;
    private String payerClaimControlNumber;
    private String facilityTypeCode;
    private ClaimFrequencyType claimFrequency;
    private String diagnosisRelatedGroupCode;
    private java.math.BigDecimal diagnosisRelatedGroupWeight;
    private java.math.BigDecimal dischargeFraction;

    private java.math.BigDecimal inpatientCoverageDaysOrVisitsCount;
    private java.math.BigDecimal inpatientPPSOperatingOutlierAmount;
    private java.math.BigDecimal inpatientLifetimePsychiatricDaysCount;
    private java.math.BigDecimal inpatientClaimDRGAmount;
    private java.math.BigDecimal inpatientClaimPaymentRemarkCode;
    private java.math.BigDecimal inpatientClaimDisproportionateShareAmount;
    private java.math.BigDecimal inpatientClaimMSPPassthroughAmount;
    private java.math.BigDecimal inpatientClaimPPSCapitalAmount;
    private java.math.BigDecimal inpatientPPSCapitalFSPDRGAmount;
    private java.math.BigDecimal inpatientPPSCapitalHSPDRGAmount;
    private java.math.BigDecimal inpatientPPSCapitalDSHDRGAmount;
    private java.math.BigDecimal inpatientOldCapitalAmount;
    private java.math.BigDecimal inpatientPPSCapitalIMEAmount;
    private java.math.BigDecimal inpatientPPSOperatingHospitalSpecificDRGAmount;
    private java.math.BigDecimal inpatientCostReportDayCount;
    private java.math.BigDecimal inpatientPPSOperatingFederalSpecificDRGAmount;
    private java.math.BigDecimal inpatientClaimPPSCapitalOutlierAmount;
    private java.math.BigDecimal inpatientClaimIndirectTeachingAmount;
    private java.math.BigDecimal inpatientNonpayableProfessionalComponentAmount;
    private String inpatientClaimPaymentRemarkCode1;
    private String inpatientClaimPaymentRemarkCode2;
    private String inpatientClaimPaymentRemarkCode3;
    private String inpatientClaimPaymentRemarkCode4;
    private java.math.BigDecimal inpatientPPSCapitalExceptionAmount;

    private java.math.BigDecimal outpatientReimbursementRate;
    private java.math.BigDecimal outpatientClaimHCPCSPayableAmount;
    private String outpatientClaimPaymentRemarkCode1;
    private String outpatientClaimPaymentRemarkCode2;
    private String outpatientClaimPaymentRemarkCode3;
    private String outpatientClaimPaymentRemarkCode4;
    private String outpatientClaimPaymentRemarkCode5;
    private java.math.BigDecimal outpatientClaimESRDPaymentAmount;
    private java.math.BigDecimal outpatientNonpayableProfessionalComponentAmount;

    private Collection<ClaimPaymentAdjustment> adjustments = new ArrayList<ClaimPaymentAdjustment>();

    public final Collection<ClaimPaymentAdjustment> getAdjustments() {
        return adjustments;
    }

    public final void setAdjustments(Collection<ClaimPaymentAdjustment> value) {
        adjustments = value;
    }

    private Collection<ClaimPaymentName> names = new ArrayList<ClaimPaymentName>();

    public final Collection<ClaimPaymentName> getNames() {
        return names;
    }

    public final void setNames(Collection<ClaimPaymentName> value) {
        names = value;
    }

    private Collection<ClaimPaymentAdditionalId> additionalIds = new ArrayList<ClaimPaymentAdditionalId>();

    public final Collection<ClaimPaymentAdditionalId> getAdditionalIds() {
        return additionalIds;
    }

    public final void setAdditionalIds(Collection<ClaimPaymentAdditionalId> value) {
        additionalIds = value;
    }

    private Collection<ClaimPaymentDate> dates = new ArrayList<ClaimPaymentDate>();

    public final Collection<ClaimPaymentDate> getDates() {
        return dates;
    }

    public final void setDates(Collection<ClaimPaymentDate> value) {
        dates = value;
    }

    private Collection<ClaimPaymentContact> contacts = new ArrayList<ClaimPaymentContact>();

    public final Collection<ClaimPaymentContact> getContacts() {
        return contacts;
    }

    public final void setContacts(Collection<ClaimPaymentContact> value) {
        contacts = value;
    }

    private Collection<ClaimPaymentAmount> amounts = new ArrayList<ClaimPaymentAmount>();

    public final Collection<ClaimPaymentAmount> getAmounts() {
        return amounts;
    }

    public final void setAmounts(Collection<ClaimPaymentAmount> value) {
        amounts = value;
    }

    private Collection<ClaimPaymentQuantity> quantities = new ArrayList<ClaimPaymentQuantity>();

    public final Collection<ClaimPaymentQuantity> getQuantities() {
        return quantities;
    }

    public final void setQuantities(Collection<ClaimPaymentQuantity> value) {
        quantities = value;
    }

    private Collection<ServicePayment> servicePayments = new ArrayList<ServicePayment>();

    public final Collection<ServicePayment> getServicePayments() {
        return servicePayments;
    }

    public final void setServicePayments(Collection<ServicePayment> value) {
        servicePayments = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClaimPaymentGroupId() {
        return claimPaymentGroupId;
    }

    public void setClaimPaymentGroupId(long claimPaymentGroupId) {
        this.claimPaymentGroupId = claimPaymentGroupId;
    }

    public String getPatientControlNumber() {
        return patientControlNumber;
    }

    public void setPatientControlNumber(String patientControlNumber) {
        this.patientControlNumber = patientControlNumber;
    }

    public ClaimStatusType getStatus() {
        return status;
    }

    public void setStatus(ClaimStatusType status) {
        this.status = status;
    }

    public java.math.BigDecimal getTotalClaimCharge() {
        return totalClaimCharge;
    }

    public void setTotalClaimCharge(java.math.BigDecimal totalClaimCharge) {
        this.totalClaimCharge = totalClaimCharge;
    }

    public java.math.BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(java.math.BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public java.math.BigDecimal getPatientResponsibilityAmount() {
        return patientResponsibilityAmount;
    }

    public void setPatientResponsibilityAmount(java.math.BigDecimal patientResponsibilityAmount) {
        this.patientResponsibilityAmount = patientResponsibilityAmount;
    }

    public ClaimFilingIndicatorType getClaimFilingIndicator() {
        return claimFilingIndicator;
    }

    public void setClaimFilingIndicator(ClaimFilingIndicatorType claimFilingIndicator) {
        this.claimFilingIndicator = claimFilingIndicator;
    }

    public String getPayerClaimControlNumber() {
        return payerClaimControlNumber;
    }

    public void setPayerClaimControlNumber(String payerClaimControlNumber) {
        this.payerClaimControlNumber = payerClaimControlNumber;
    }

    public String getFacilityTypeCode() {
        return facilityTypeCode;
    }

    public void setFacilityTypeCode(String facilityTypeCode) {
        this.facilityTypeCode = facilityTypeCode;
    }

    public ClaimFrequencyType getClaimFrequency() {
        return claimFrequency;
    }

    public void setClaimFrequency(ClaimFrequencyType claimFrequency) {
        this.claimFrequency = claimFrequency;
    }

    public String getDiagnosisRelatedGroupCode() {
        return diagnosisRelatedGroupCode;
    }

    public void setDiagnosisRelatedGroupCode(String diagnosisRelatedGroupCode) {
        this.diagnosisRelatedGroupCode = diagnosisRelatedGroupCode;
    }

    public java.math.BigDecimal getDiagnosisRelatedGroupWeight() {
        return diagnosisRelatedGroupWeight;
    }

    public void setDiagnosisRelatedGroupWeight(java.math.BigDecimal diagnosisRelatedGroupWeight) {
        this.diagnosisRelatedGroupWeight = diagnosisRelatedGroupWeight;
    }

    public java.math.BigDecimal getDischargeFraction() {
        return dischargeFraction;
    }

    public void setDischargeFraction(java.math.BigDecimal dischargeFraction) {
        this.dischargeFraction = dischargeFraction;
    }

    public java.math.BigDecimal getInpatientCoverageDaysOrVisitsCount() {
        return inpatientCoverageDaysOrVisitsCount;
    }

    public void setInpatientCoverageDaysOrVisitsCount(java.math.BigDecimal inpatientCoverageDaysOrVisitsCount) {
        this.inpatientCoverageDaysOrVisitsCount = inpatientCoverageDaysOrVisitsCount;
    }

    public java.math.BigDecimal getInpatientPPSOperatingOutlierAmount() {
        return inpatientPPSOperatingOutlierAmount;
    }

    public void setInpatientPPSOperatingOutlierAmount(java.math.BigDecimal inpatientPPSOperatingOutlierAmount) {
        this.inpatientPPSOperatingOutlierAmount = inpatientPPSOperatingOutlierAmount;
    }

    public java.math.BigDecimal getInpatientLifetimePsychiatricDaysCount() {
        return inpatientLifetimePsychiatricDaysCount;
    }

    public void setInpatientLifetimePsychiatricDaysCount(java.math.BigDecimal inpatientLifetimePsychiatricDaysCount) {
        this.inpatientLifetimePsychiatricDaysCount = inpatientLifetimePsychiatricDaysCount;
    }

    public java.math.BigDecimal getInpatientClaimDRGAmount() {
        return inpatientClaimDRGAmount;
    }

    public void setInpatientClaimDRGAmount(java.math.BigDecimal inpatientClaimDRGAmount) {
        this.inpatientClaimDRGAmount = inpatientClaimDRGAmount;
    }

    public java.math.BigDecimal getInpatientClaimPaymentRemarkCode() {
        return inpatientClaimPaymentRemarkCode;
    }

    public void setInpatientClaimPaymentRemarkCode(java.math.BigDecimal inpatientClaimPaymentRemarkCode) {
        this.inpatientClaimPaymentRemarkCode = inpatientClaimPaymentRemarkCode;
    }

    public java.math.BigDecimal getInpatientClaimDisproportionateShareAmount() {
        return inpatientClaimDisproportionateShareAmount;
    }

    public void setInpatientClaimDisproportionateShareAmount(
            java.math.BigDecimal inpatientClaimDisproportionateShareAmount) {
        this.inpatientClaimDisproportionateShareAmount = inpatientClaimDisproportionateShareAmount;
    }

    public java.math.BigDecimal getInpatientClaimMSPPassthroughAmount() {
        return inpatientClaimMSPPassthroughAmount;
    }

    public void setInpatientClaimMSPPassthroughAmount(java.math.BigDecimal inpatientClaimMSPPassthroughAmount) {
        this.inpatientClaimMSPPassthroughAmount = inpatientClaimMSPPassthroughAmount;
    }

    public java.math.BigDecimal getInpatientClaimPPSCapitalAmount() {
        return inpatientClaimPPSCapitalAmount;
    }

    public void setInpatientClaimPPSCapitalAmount(java.math.BigDecimal inpatientClaimPPSCapitalAmount) {
        this.inpatientClaimPPSCapitalAmount = inpatientClaimPPSCapitalAmount;
    }

    public java.math.BigDecimal getInpatientPPSCapitalFSPDRGAmount() {
        return inpatientPPSCapitalFSPDRGAmount;
    }

    public void setInpatientPPSCapitalFSPDRGAmount(java.math.BigDecimal inpatientPPSCapitalFSPDRGAmount) {
        this.inpatientPPSCapitalFSPDRGAmount = inpatientPPSCapitalFSPDRGAmount;
    }

    public java.math.BigDecimal getInpatientPPSCapitalHSPDRGAmount() {
        return inpatientPPSCapitalHSPDRGAmount;
    }

    public void setInpatientPPSCapitalHSPDRGAmount(java.math.BigDecimal inpatientPPSCapitalHSPDRGAmount) {
        this.inpatientPPSCapitalHSPDRGAmount = inpatientPPSCapitalHSPDRGAmount;
    }

    public java.math.BigDecimal getInpatientPPSCapitalDSHDRGAmount() {
        return inpatientPPSCapitalDSHDRGAmount;
    }

    public void setInpatientPPSCapitalDSHDRGAmount(java.math.BigDecimal inpatientPPSCapitalDSHDRGAmount) {
        this.inpatientPPSCapitalDSHDRGAmount = inpatientPPSCapitalDSHDRGAmount;
    }

    public java.math.BigDecimal getInpatientOldCapitalAmount() {
        return inpatientOldCapitalAmount;
    }

    public void setInpatientOldCapitalAmount(java.math.BigDecimal inpatientOldCapitalAmount) {
        this.inpatientOldCapitalAmount = inpatientOldCapitalAmount;
    }

    public java.math.BigDecimal getInpatientPPSCapitalIMEAmount() {
        return inpatientPPSCapitalIMEAmount;
    }

    public void setInpatientPPSCapitalIMEAmount(java.math.BigDecimal inpatientPPSCapitalIMEAmount) {
        this.inpatientPPSCapitalIMEAmount = inpatientPPSCapitalIMEAmount;
    }

    public java.math.BigDecimal getInpatientPPSOperatingHospitalSpecificDRGAmount() {
        return inpatientPPSOperatingHospitalSpecificDRGAmount;
    }

    public void setInpatientPPSOperatingHospitalSpecificDRGAmount(
            java.math.BigDecimal inpatientPPSOperatingHospitalSpecificDRGAmount) {
        this.inpatientPPSOperatingHospitalSpecificDRGAmount = inpatientPPSOperatingHospitalSpecificDRGAmount;
    }

    public java.math.BigDecimal getInpatientCostReportDayCount() {
        return inpatientCostReportDayCount;
    }

    public void setInpatientCostReportDayCount(java.math.BigDecimal inpatientCostReportDayCount) {
        this.inpatientCostReportDayCount = inpatientCostReportDayCount;
    }

    public java.math.BigDecimal getInpatientPPSOperatingFederalSpecificDRGAmount() {
        return inpatientPPSOperatingFederalSpecificDRGAmount;
    }

    public void setInpatientPPSOperatingFederalSpecificDRGAmount(
            java.math.BigDecimal inpatientPPSOperatingFederalSpecificDRGAmount) {
        this.inpatientPPSOperatingFederalSpecificDRGAmount = inpatientPPSOperatingFederalSpecificDRGAmount;
    }

    public java.math.BigDecimal getInpatientClaimPPSCapitalOutlierAmount() {
        return inpatientClaimPPSCapitalOutlierAmount;
    }

    public void setInpatientClaimPPSCapitalOutlierAmount(java.math.BigDecimal inpatientClaimPPSCapitalOutlierAmount) {
        this.inpatientClaimPPSCapitalOutlierAmount = inpatientClaimPPSCapitalOutlierAmount;
    }

    public java.math.BigDecimal getInpatientClaimIndirectTeachingAmount() {
        return inpatientClaimIndirectTeachingAmount;
    }

    public void setInpatientClaimIndirectTeachingAmount(java.math.BigDecimal inpatientClaimIndirectTeachingAmount) {
        this.inpatientClaimIndirectTeachingAmount = inpatientClaimIndirectTeachingAmount;
    }

    public java.math.BigDecimal getInpatientNonpayableProfessionalComponentAmount() {
        return inpatientNonpayableProfessionalComponentAmount;
    }

    public void setInpatientNonpayableProfessionalComponentAmount(
            java.math.BigDecimal inpatientNonpayableProfessionalComponentAmount) {
        this.inpatientNonpayableProfessionalComponentAmount = inpatientNonpayableProfessionalComponentAmount;
    }

    public String getInpatientClaimPaymentRemarkCode1() {
        return inpatientClaimPaymentRemarkCode1;
    }

    public void setInpatientClaimPaymentRemarkCode1(String inpatientClaimPaymentRemarkCode1) {
        this.inpatientClaimPaymentRemarkCode1 = inpatientClaimPaymentRemarkCode1;
    }

    public String getInpatientClaimPaymentRemarkCode2() {
        return inpatientClaimPaymentRemarkCode2;
    }

    public void setInpatientClaimPaymentRemarkCode2(String inpatientClaimPaymentRemarkCode2) {
        this.inpatientClaimPaymentRemarkCode2 = inpatientClaimPaymentRemarkCode2;
    }

    public String getInpatientClaimPaymentRemarkCode3() {
        return inpatientClaimPaymentRemarkCode3;
    }

    public void setInpatientClaimPaymentRemarkCode3(String inpatientClaimPaymentRemarkCode3) {
        this.inpatientClaimPaymentRemarkCode3 = inpatientClaimPaymentRemarkCode3;
    }

    public String getInpatientClaimPaymentRemarkCode4() {
        return inpatientClaimPaymentRemarkCode4;
    }

    public void setInpatientClaimPaymentRemarkCode4(String inpatientClaimPaymentRemarkCode4) {
        this.inpatientClaimPaymentRemarkCode4 = inpatientClaimPaymentRemarkCode4;
    }

    public java.math.BigDecimal getInpatientPPSCapitalExceptionAmount() {
        return inpatientPPSCapitalExceptionAmount;
    }

    public void setInpatientPPSCapitalExceptionAmount(java.math.BigDecimal inpatientPPSCapitalExceptionAmount) {
        this.inpatientPPSCapitalExceptionAmount = inpatientPPSCapitalExceptionAmount;
    }

    public java.math.BigDecimal getOutpatientReimbursementRate() {
        return outpatientReimbursementRate;
    }

    public void setOutpatientReimbursementRate(java.math.BigDecimal outpatientReimbursementRate) {
        this.outpatientReimbursementRate = outpatientReimbursementRate;
    }

    public java.math.BigDecimal getOutpatientClaimHCPCSPayableAmount() {
        return outpatientClaimHCPCSPayableAmount;
    }

    public void setOutpatientClaimHCPCSPayableAmount(java.math.BigDecimal outpatientClaimHCPCSPayableAmount) {
        this.outpatientClaimHCPCSPayableAmount = outpatientClaimHCPCSPayableAmount;
    }

    public String getOutpatientClaimPaymentRemarkCode1() {
        return outpatientClaimPaymentRemarkCode1;
    }

    public void setOutpatientClaimPaymentRemarkCode1(String outpatientClaimPaymentRemarkCode1) {
        this.outpatientClaimPaymentRemarkCode1 = outpatientClaimPaymentRemarkCode1;
    }

    public String getOutpatientClaimPaymentRemarkCode2() {
        return outpatientClaimPaymentRemarkCode2;
    }

    public void setOutpatientClaimPaymentRemarkCode2(String outpatientClaimPaymentRemarkCode2) {
        this.outpatientClaimPaymentRemarkCode2 = outpatientClaimPaymentRemarkCode2;
    }

    public String getOutpatientClaimPaymentRemarkCode3() {
        return outpatientClaimPaymentRemarkCode3;
    }

    public void setOutpatientClaimPaymentRemarkCode3(String outpatientClaimPaymentRemarkCode3) {
        this.outpatientClaimPaymentRemarkCode3 = outpatientClaimPaymentRemarkCode3;
    }

    public String getOutpatientClaimPaymentRemarkCode4() {
        return outpatientClaimPaymentRemarkCode4;
    }

    public void setOutpatientClaimPaymentRemarkCode4(String outpatientClaimPaymentRemarkCode4) {
        this.outpatientClaimPaymentRemarkCode4 = outpatientClaimPaymentRemarkCode4;
    }

    public String getOutpatientClaimPaymentRemarkCode5() {
        return outpatientClaimPaymentRemarkCode5;
    }

    public void setOutpatientClaimPaymentRemarkCode5(String outpatientClaimPaymentRemarkCode5) {
        this.outpatientClaimPaymentRemarkCode5 = outpatientClaimPaymentRemarkCode5;
    }

    public java.math.BigDecimal getOutpatientClaimESRDPaymentAmount() {
        return outpatientClaimESRDPaymentAmount;
    }

    public void setOutpatientClaimESRDPaymentAmount(java.math.BigDecimal outpatientClaimESRDPaymentAmount) {
        this.outpatientClaimESRDPaymentAmount = outpatientClaimESRDPaymentAmount;
    }

    public java.math.BigDecimal getOutpatientNonpayableProfessionalComponentAmount() {
        return outpatientNonpayableProfessionalComponentAmount;
    }

    public void setOutpatientNonpayableProfessionalComponentAmount(
            java.math.BigDecimal outpatientNonpayableProfessionalComponentAmount) {
        this.outpatientNonpayableProfessionalComponentAmount = outpatientNonpayableProfessionalComponentAmount;
    }
}