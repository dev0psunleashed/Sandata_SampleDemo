package com.sandata.lab.billing.edi.model.claim.professional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.sandata.lab.billing.edi.model.enums.ClaimFilingIndicatorType;
import com.sandata.lab.billing.edi.model.enums.ClaimPatientSignatureSourceType;
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
    private ClaimPatientSignatureSourceType patientSignatureSource;
    private ClaimReleaseOfInformationCodeType releaseOfInformation;

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

    private NameIdentifierTypeCodeType payerIdType = NameIdentifierTypeCodeType.PayorIdentification;

    public final NameIdentifierTypeCodeType getPayerIdType() {
        return payerIdType;
    }

    public final void setPayerIdType(NameIdentifierTypeCodeType value) {
        payerIdType = value;
    }

    private Collection<OtherSubscriberAdjustment> adjustments = new ArrayList<OtherSubscriberAdjustment>();

    public final Collection<OtherSubscriberAdjustment> getAdjustments() {
        return adjustments;
    }

    public final void setAdjustments(Collection<OtherSubscriberAdjustment> value) {
        adjustments = value;
    }

    private Collection<OtherSubscriberAmount> amounts = new ArrayList<OtherSubscriberAmount>();

    public final Collection<OtherSubscriberAmount> getAmounts() {
        return amounts;
    }

    public final void setAmounts(Collection<OtherSubscriberAmount> value) {
        amounts = value;
    }

    private Collection<OtherSubscriberAdditionalId> subscriberAdditionalIds = new ArrayList<OtherSubscriberAdditionalId>();

    public final Collection<OtherSubscriberAdditionalId> getSubscriberAdditionalIds() {
        return subscriberAdditionalIds;
    }

    public final void setSubscriberAdditionalIds(Collection<OtherSubscriberAdditionalId> value) {
        subscriberAdditionalIds = value;
    }

    private Collection<OtherSubscriberPayerAdditionalId> payerAdditionalIds = new ArrayList<OtherSubscriberPayerAdditionalId>();

    public final Collection<OtherSubscriberPayerAdditionalId> getPayerAdditionalIds() {
        return payerAdditionalIds;
    }

    public final void setPayerAdditionalIds(Collection<OtherSubscriberPayerAdditionalId> value) {
        payerAdditionalIds = value;
    }

    private Collection<OtherSubscriberEntity> entities = new ArrayList<OtherSubscriberEntity>();

    public final Collection<OtherSubscriberEntity> getEntities() {
        return entities;
    }

    public final void setEntities(Collection<OtherSubscriberEntity> value) {
        entities = value;
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

    public ClaimPatientSignatureSourceType getPatientSignatureSource() {
        return patientSignatureSource;
    }

    public void setPatientSignatureSource(ClaimPatientSignatureSourceType patientSignatureSource) {
        this.patientSignatureSource = patientSignatureSource;
    }

    public ClaimReleaseOfInformationCodeType getReleaseOfInformation() {
        return releaseOfInformation;
    }

    public void setReleaseOfInformation(ClaimReleaseOfInformationCodeType releaseOfInformation) {
        this.releaseOfInformation = releaseOfInformation;
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