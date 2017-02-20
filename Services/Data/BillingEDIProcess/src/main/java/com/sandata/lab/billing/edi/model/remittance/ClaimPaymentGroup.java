package com.sandata.lab.billing.edi.model.remittance;

import java.util.*;

public class ClaimPaymentGroup {
    private long id;
    private long transactionId;

    private long headerNumber;

    private String supplementalProviderNumber;
    private String supplementalFacility;
    private Date supplementalFiscalPeriod;
    private java.math.BigDecimal supplementalTotalClaimCount;
    private java.math.BigDecimal supplementalTotalClaimCharge;
    private java.math.BigDecimal supplementalTotalMSPPayerAmount;
    private java.math.BigDecimal supplementalTotalNonLabChargeAmount;
    private java.math.BigDecimal supplementalTotalHCPCSPayableAmount;
    private java.math.BigDecimal supplementalTotalProfessionalComponentAmount;
    private java.math.BigDecimal supplementalTotalMSPPatientLiabilityMetAmount;
    private java.math.BigDecimal supplementalTotalPatientReimbursementAmount;
    private java.math.BigDecimal supplementalTotalPIPAdjustmentAmount;
    private java.math.BigDecimal supplementalTotalDRGAmount;
    private java.math.BigDecimal supplementalTotalFederalSpecificAmount;
    private java.math.BigDecimal supplementalTotalHospitalSpecificAmount;
    private java.math.BigDecimal supplementalTotalDisproportionateShareAmount;
    private java.math.BigDecimal supplementalTotalCapitalAmount;
    private java.math.BigDecimal supplementalTotalIndirectMedicalEducationAmount;
    private java.math.BigDecimal supplementalTotalOutlierDayCount;
    private java.math.BigDecimal supplementalTotalDayOutlierAmount;
    private java.math.BigDecimal supplementalTotalCostOutlierAmount;
    private java.math.BigDecimal supplementalAverageDRGLengthOfStay;
    private java.math.BigDecimal supplementalTotalDischargeCount;
    private java.math.BigDecimal supplementalTotalCostReportDayCount;
    private java.math.BigDecimal supplementalTotalCoveredDayCount;
    private java.math.BigDecimal supplementalTotalNoncoveredDayCount;
    private java.math.BigDecimal supplementalMSPPassThroughAmount;
    private java.math.BigDecimal supplementalAverageDRGWeight;
    private java.math.BigDecimal supplementalTotalPPSCapitalFSPDRGAmount;
    private java.math.BigDecimal supplementalTotalPPSCapitalHSPDRGAmount;
    private java.math.BigDecimal supplementalTotalPPSDSHDRGAmount;

    private Collection<ClaimPayment> claimPayments = new ArrayList<ClaimPayment>();

    public final Collection<ClaimPayment> getClaimPayments() {
        return claimPayments;
    }

    public final void setClaimPayments(Collection<ClaimPayment> value) {
        claimPayments = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public long getHeaderNumber() {
        return headerNumber;
    }

    public void setHeaderNumber(long headerNumber) {
        this.headerNumber = headerNumber;
    }

    public String getSupplementalProviderNumber() {
        return supplementalProviderNumber;
    }

    public void setSupplementalProviderNumber(String supplementalProviderNumber) {
        this.supplementalProviderNumber = supplementalProviderNumber;
    }

    public String getSupplementalFacility() {
        return supplementalFacility;
    }

    public void setSupplementalFacility(String supplementalFacility) {
        this.supplementalFacility = supplementalFacility;
    }

    public Date getSupplementalFiscalPeriod() {
        return supplementalFiscalPeriod;
    }

    public void setSupplementalFiscalPeriod(Date supplementalFiscalPeriod) {
        this.supplementalFiscalPeriod = supplementalFiscalPeriod;
    }

    public java.math.BigDecimal getSupplementalTotalClaimCount() {
        return supplementalTotalClaimCount;
    }

    public void setSupplementalTotalClaimCount(java.math.BigDecimal supplementalTotalClaimCount) {
        this.supplementalTotalClaimCount = supplementalTotalClaimCount;
    }

    public java.math.BigDecimal getSupplementalTotalClaimCharge() {
        return supplementalTotalClaimCharge;
    }

    public void setSupplementalTotalClaimCharge(java.math.BigDecimal supplementalTotalClaimCharge) {
        this.supplementalTotalClaimCharge = supplementalTotalClaimCharge;
    }

    public java.math.BigDecimal getSupplementalTotalMSPPayerAmount() {
        return supplementalTotalMSPPayerAmount;
    }

    public void setSupplementalTotalMSPPayerAmount(java.math.BigDecimal supplementalTotalMSPPayerAmount) {
        this.supplementalTotalMSPPayerAmount = supplementalTotalMSPPayerAmount;
    }

    public java.math.BigDecimal getSupplementalTotalNonLabChargeAmount() {
        return supplementalTotalNonLabChargeAmount;
    }

    public void setSupplementalTotalNonLabChargeAmount(java.math.BigDecimal supplementalTotalNonLabChargeAmount) {
        this.supplementalTotalNonLabChargeAmount = supplementalTotalNonLabChargeAmount;
    }

    public java.math.BigDecimal getSupplementalTotalHCPCSPayableAmount() {
        return supplementalTotalHCPCSPayableAmount;
    }

    public void setSupplementalTotalHCPCSPayableAmount(java.math.BigDecimal supplementalTotalHCPCSPayableAmount) {
        this.supplementalTotalHCPCSPayableAmount = supplementalTotalHCPCSPayableAmount;
    }

    public java.math.BigDecimal getSupplementalTotalProfessionalComponentAmount() {
        return supplementalTotalProfessionalComponentAmount;
    }

    public void setSupplementalTotalProfessionalComponentAmount(
            java.math.BigDecimal supplementalTotalProfessionalComponentAmount) {
        this.supplementalTotalProfessionalComponentAmount = supplementalTotalProfessionalComponentAmount;
    }

    public java.math.BigDecimal getSupplementalTotalMSPPatientLiabilityMetAmount() {
        return supplementalTotalMSPPatientLiabilityMetAmount;
    }

    public void setSupplementalTotalMSPPatientLiabilityMetAmount(
            java.math.BigDecimal supplementalTotalMSPPatientLiabilityMetAmount) {
        this.supplementalTotalMSPPatientLiabilityMetAmount = supplementalTotalMSPPatientLiabilityMetAmount;
    }

    public java.math.BigDecimal getSupplementalTotalPatientReimbursementAmount() {
        return supplementalTotalPatientReimbursementAmount;
    }

    public void setSupplementalTotalPatientReimbursementAmount(
            java.math.BigDecimal supplementalTotalPatientReimbursementAmount) {
        this.supplementalTotalPatientReimbursementAmount = supplementalTotalPatientReimbursementAmount;
    }

    public java.math.BigDecimal getSupplementalTotalPIPAdjustmentAmount() {
        return supplementalTotalPIPAdjustmentAmount;
    }

    public void setSupplementalTotalPIPAdjustmentAmount(java.math.BigDecimal supplementalTotalPIPAdjustmentAmount) {
        this.supplementalTotalPIPAdjustmentAmount = supplementalTotalPIPAdjustmentAmount;
    }

    public java.math.BigDecimal getSupplementalTotalDRGAmount() {
        return supplementalTotalDRGAmount;
    }

    public void setSupplementalTotalDRGAmount(java.math.BigDecimal supplementalTotalDRGAmount) {
        this.supplementalTotalDRGAmount = supplementalTotalDRGAmount;
    }

    public java.math.BigDecimal getSupplementalTotalFederalSpecificAmount() {
        return supplementalTotalFederalSpecificAmount;
    }

    public void setSupplementalTotalFederalSpecificAmount(java.math.BigDecimal supplementalTotalFederalSpecificAmount) {
        this.supplementalTotalFederalSpecificAmount = supplementalTotalFederalSpecificAmount;
    }

    public java.math.BigDecimal getSupplementalTotalHospitalSpecificAmount() {
        return supplementalTotalHospitalSpecificAmount;
    }

    public void setSupplementalTotalHospitalSpecificAmount(java.math.BigDecimal supplementalTotalHospitalSpecificAmount) {
        this.supplementalTotalHospitalSpecificAmount = supplementalTotalHospitalSpecificAmount;
    }

    public java.math.BigDecimal getSupplementalTotalDisproportionateShareAmount() {
        return supplementalTotalDisproportionateShareAmount;
    }

    public void setSupplementalTotalDisproportionateShareAmount(
            java.math.BigDecimal supplementalTotalDisproportionateShareAmount) {
        this.supplementalTotalDisproportionateShareAmount = supplementalTotalDisproportionateShareAmount;
    }

    public java.math.BigDecimal getSupplementalTotalCapitalAmount() {
        return supplementalTotalCapitalAmount;
    }

    public void setSupplementalTotalCapitalAmount(java.math.BigDecimal supplementalTotalCapitalAmount) {
        this.supplementalTotalCapitalAmount = supplementalTotalCapitalAmount;
    }

    public java.math.BigDecimal getSupplementalTotalIndirectMedicalEducationAmount() {
        return supplementalTotalIndirectMedicalEducationAmount;
    }

    public void setSupplementalTotalIndirectMedicalEducationAmount(
            java.math.BigDecimal supplementalTotalIndirectMedicalEducationAmount) {
        this.supplementalTotalIndirectMedicalEducationAmount = supplementalTotalIndirectMedicalEducationAmount;
    }

    public java.math.BigDecimal getSupplementalTotalOutlierDayCount() {
        return supplementalTotalOutlierDayCount;
    }

    public void setSupplementalTotalOutlierDayCount(java.math.BigDecimal supplementalTotalOutlierDayCount) {
        this.supplementalTotalOutlierDayCount = supplementalTotalOutlierDayCount;
    }

    public java.math.BigDecimal getSupplementalTotalDayOutlierAmount() {
        return supplementalTotalDayOutlierAmount;
    }

    public void setSupplementalTotalDayOutlierAmount(java.math.BigDecimal supplementalTotalDayOutlierAmount) {
        this.supplementalTotalDayOutlierAmount = supplementalTotalDayOutlierAmount;
    }

    public java.math.BigDecimal getSupplementalTotalCostOutlierAmount() {
        return supplementalTotalCostOutlierAmount;
    }

    public void setSupplementalTotalCostOutlierAmount(java.math.BigDecimal supplementalTotalCostOutlierAmount) {
        this.supplementalTotalCostOutlierAmount = supplementalTotalCostOutlierAmount;
    }

    public java.math.BigDecimal getSupplementalAverageDRGLengthOfStay() {
        return supplementalAverageDRGLengthOfStay;
    }

    public void setSupplementalAverageDRGLengthOfStay(java.math.BigDecimal supplementalAverageDRGLengthOfStay) {
        this.supplementalAverageDRGLengthOfStay = supplementalAverageDRGLengthOfStay;
    }

    public java.math.BigDecimal getSupplementalTotalDischargeCount() {
        return supplementalTotalDischargeCount;
    }

    public void setSupplementalTotalDischargeCount(java.math.BigDecimal supplementalTotalDischargeCount) {
        this.supplementalTotalDischargeCount = supplementalTotalDischargeCount;
    }

    public java.math.BigDecimal getSupplementalTotalCostReportDayCount() {
        return supplementalTotalCostReportDayCount;
    }

    public void setSupplementalTotalCostReportDayCount(java.math.BigDecimal supplementalTotalCostReportDayCount) {
        this.supplementalTotalCostReportDayCount = supplementalTotalCostReportDayCount;
    }

    public java.math.BigDecimal getSupplementalTotalCoveredDayCount() {
        return supplementalTotalCoveredDayCount;
    }

    public void setSupplementalTotalCoveredDayCount(java.math.BigDecimal supplementalTotalCoveredDayCount) {
        this.supplementalTotalCoveredDayCount = supplementalTotalCoveredDayCount;
    }

    public java.math.BigDecimal getSupplementalTotalNoncoveredDayCount() {
        return supplementalTotalNoncoveredDayCount;
    }

    public void setSupplementalTotalNoncoveredDayCount(java.math.BigDecimal supplementalTotalNoncoveredDayCount) {
        this.supplementalTotalNoncoveredDayCount = supplementalTotalNoncoveredDayCount;
    }

    public java.math.BigDecimal getSupplementalMSPPassThroughAmount() {
        return supplementalMSPPassThroughAmount;
    }

    public void setSupplementalMSPPassThroughAmount(java.math.BigDecimal supplementalMSPPassThroughAmount) {
        this.supplementalMSPPassThroughAmount = supplementalMSPPassThroughAmount;
    }

    public java.math.BigDecimal getSupplementalAverageDRGWeight() {
        return supplementalAverageDRGWeight;
    }

    public void setSupplementalAverageDRGWeight(java.math.BigDecimal supplementalAverageDRGWeight) {
        this.supplementalAverageDRGWeight = supplementalAverageDRGWeight;
    }

    public java.math.BigDecimal getSupplementalTotalPPSCapitalFSPDRGAmount() {
        return supplementalTotalPPSCapitalFSPDRGAmount;
    }

    public void setSupplementalTotalPPSCapitalFSPDRGAmount(java.math.BigDecimal supplementalTotalPPSCapitalFSPDRGAmount) {
        this.supplementalTotalPPSCapitalFSPDRGAmount = supplementalTotalPPSCapitalFSPDRGAmount;
    }

    public java.math.BigDecimal getSupplementalTotalPPSCapitalHSPDRGAmount() {
        return supplementalTotalPPSCapitalHSPDRGAmount;
    }

    public void setSupplementalTotalPPSCapitalHSPDRGAmount(java.math.BigDecimal supplementalTotalPPSCapitalHSPDRGAmount) {
        this.supplementalTotalPPSCapitalHSPDRGAmount = supplementalTotalPPSCapitalHSPDRGAmount;
    }

    public java.math.BigDecimal getSupplementalTotalPPSDSHDRGAmount() {
        return supplementalTotalPPSDSHDRGAmount;
    }

    public void setSupplementalTotalPPSDSHDRGAmount(java.math.BigDecimal supplementalTotalPPSDSHDRGAmount) {
        this.supplementalTotalPPSDSHDRGAmount = supplementalTotalPPSDSHDRGAmount;
    }
}