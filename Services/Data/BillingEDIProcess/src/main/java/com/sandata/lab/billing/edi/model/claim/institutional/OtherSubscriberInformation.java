package com.sandata.lab.billing.edi.model.claim.institutional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.sandata.lab.billing.edi.model.enums.ClaimFilingIndicatorType;
import com.sandata.lab.billing.edi.model.enums.ClaimReleaseOfInformationCodeType;
import com.sandata.lab.billing.edi.model.enums.NameIdentifierTypeCodeType;
import com.sandata.lab.billing.edi.model.enums.PayerPriorityType;
import com.sandata.lab.billing.edi.model.enums.RelationshipCodeType;
import com.sandata.lab.billing.edi.model.enums.ResponseCodeType;

public class OtherSubscriberInformation {
    private long id;
    private long claimId;

    private PayerPriorityType payerSequence;
    private RelationshipCodeType relationshipToPatient;
    private String groupNumber;
    private String groupName;
    private ClaimFilingIndicatorType payerType;

    private ResponseCodeType benefitsAssigned;
    private ClaimReleaseOfInformationCodeType releaseOfInformation;

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

    private String subscriberLastOrOrganizationName;
    private String subscriberFirstName;
    private String subscriberMiddleName;
    private String subscriberSuffixName;
    private NameIdentifierTypeCodeType subscriberIdType;
    private String subscriberId;
    private String subscriberStreet1;
    private String subscriberStreet2;
    private String subscriberCity;
    private String subscriberState;
    private String subscriberPostalCode;

    private String payerName;

    private String payerId;
    private String payerStreet1;
    private String payerStreet2;
    private String payerCity;
    private String payerState;
    private String payerPostalCode;
    private Date remittanceDate;

    private NameIdentifierTypeCodeType PayerIdType = NameIdentifierTypeCodeType.PayorIdentification;

    public final NameIdentifierTypeCodeType getPayerIdType() {
        return PayerIdType;
    }

    public final void setPayerIdType(NameIdentifierTypeCodeType value) {
        PayerIdType = value;
    }

    private Collection<OtherSubscriberAdjustment> Adjustments = new ArrayList<OtherSubscriberAdjustment>();

    public final Collection<OtherSubscriberAdjustment> getAdjustments() {
        return Adjustments;
    }

    public final void setAdjustments(Collection<OtherSubscriberAdjustment> value) {
        Adjustments = value;
    }

    private Collection<OtherSubscriberAmount> Amounts = new ArrayList<OtherSubscriberAmount>();

    public final Collection<OtherSubscriberAmount> getAmounts() {
        return Amounts;
    }

    public final void setAmounts(Collection<OtherSubscriberAmount> value) {
        Amounts = value;
    }

    private Collection<OtherSubscriberAdditionalId> SubscriberAdditionalIds = new ArrayList<OtherSubscriberAdditionalId>();

    public final Collection<OtherSubscriberAdditionalId> getSubscriberAdditionalIds() {
        return SubscriberAdditionalIds;
    }

    public final void setSubscriberAdditionalIds(Collection<OtherSubscriberAdditionalId> value) {
        SubscriberAdditionalIds = value;
    }

    private Collection<OtherSubscriberPayerAdditionalId> PayerAdditionalIds = new ArrayList<OtherSubscriberPayerAdditionalId>();

    public final Collection<OtherSubscriberPayerAdditionalId> getPayerAdditionalIds() {
        return PayerAdditionalIds;
    }

    public final void setPayerAdditionalIds(Collection<OtherSubscriberPayerAdditionalId> value) {
        PayerAdditionalIds = value;
    }

    private Collection<OtherSubscriberEntity> Entities = new ArrayList<OtherSubscriberEntity>();

    public final Collection<OtherSubscriberEntity> getEntities() {
        return Entities;
    }

    public final void setEntities(Collection<OtherSubscriberEntity> value) {
        Entities = value;
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

    public PayerPriorityType getPayerSequence() {
        return payerSequence;
    }

    public void setPayerSequence(PayerPriorityType payerSequence) {
        this.payerSequence = payerSequence;
    }

    public RelationshipCodeType getRelationshipToPatient() {
        return relationshipToPatient;
    }

    public void setRelationshipToPatient(RelationshipCodeType relationshipToPatient) {
        this.relationshipToPatient = relationshipToPatient;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ClaimFilingIndicatorType getPayerType() {
        return payerType;
    }

    public void setPayerType(ClaimFilingIndicatorType payerType) {
        this.payerType = payerType;
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

    public String getSubscriberLastOrOrganizationName() {
        return subscriberLastOrOrganizationName;
    }

    public void setSubscriberLastOrOrganizationName(String subscriberLastOrOrganizationName) {
        this.subscriberLastOrOrganizationName = subscriberLastOrOrganizationName;
    }

    public String getSubscriberFirstName() {
        return subscriberFirstName;
    }

    public void setSubscriberFirstName(String subscriberFirstName) {
        this.subscriberFirstName = subscriberFirstName;
    }

    public String getSubscriberMiddleName() {
        return subscriberMiddleName;
    }

    public void setSubscriberMiddleName(String subscriberMiddleName) {
        this.subscriberMiddleName = subscriberMiddleName;
    }

    public String getSubscriberSuffixName() {
        return subscriberSuffixName;
    }

    public void setSubscriberSuffixName(String subscriberSuffixName) {
        this.subscriberSuffixName = subscriberSuffixName;
    }

    public NameIdentifierTypeCodeType getSubscriberIdType() {
        return subscriberIdType;
    }

    public void setSubscriberIdType(NameIdentifierTypeCodeType subscriberIdType) {
        this.subscriberIdType = subscriberIdType;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getSubscriberStreet1() {
        return subscriberStreet1;
    }

    public void setSubscriberStreet1(String subscriberStreet1) {
        this.subscriberStreet1 = subscriberStreet1;
    }

    public String getSubscriberStreet2() {
        return subscriberStreet2;
    }

    public void setSubscriberStreet2(String subscriberStreet2) {
        this.subscriberStreet2 = subscriberStreet2;
    }

    public String getSubscriberCity() {
        return subscriberCity;
    }

    public void setSubscriberCity(String subscriberCity) {
        this.subscriberCity = subscriberCity;
    }

    public String getSubscriberState() {
        return subscriberState;
    }

    public void setSubscriberState(String subscriberState) {
        this.subscriberState = subscriberState;
    }

    public String getSubscriberPostalCode() {
        return subscriberPostalCode;
    }

    public void setSubscriberPostalCode(String subscriberPostalCode) {
        this.subscriberPostalCode = subscriberPostalCode;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getPayerStreet1() {
        return payerStreet1;
    }

    public void setPayerStreet1(String payerStreet1) {
        this.payerStreet1 = payerStreet1;
    }

    public String getPayerStreet2() {
        return payerStreet2;
    }

    public void setPayerStreet2(String payerStreet2) {
        this.payerStreet2 = payerStreet2;
    }

    public String getPayerCity() {
        return payerCity;
    }

    public void setPayerCity(String payerCity) {
        this.payerCity = payerCity;
    }

    public String getPayerState() {
        return payerState;
    }

    public void setPayerState(String payerState) {
        this.payerState = payerState;
    }

    public String getPayerPostalCode() {
        return payerPostalCode;
    }

    public void setPayerPostalCode(String payerPostalCode) {
        this.payerPostalCode = payerPostalCode;
    }

    public Date getRemittanceDate() {
        return remittanceDate;
    }

    public void setRemittanceDate(Date remittanceDate) {
        this.remittanceDate = remittanceDate;
    }
}