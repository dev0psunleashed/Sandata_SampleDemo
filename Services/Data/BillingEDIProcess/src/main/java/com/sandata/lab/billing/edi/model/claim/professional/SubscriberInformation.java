package com.sandata.lab.billing.edi.model.claim.professional;

import java.util.ArrayList;
import java.util.Collection;

import com.sandata.lab.billing.edi.model.enums.ClaimFilingIndicatorType;
import com.sandata.lab.billing.edi.model.enums.MedicareSecondaryType;
import com.sandata.lab.billing.edi.model.enums.NameIdentifierTypeCodeType;
import com.sandata.lab.billing.edi.model.enums.PayerPriorityType;

public class SubscriberInformation {
    private long id;
    private long billingProviderId;
    private long subscriberId;
    private PayerPriorityType payerSequence;
    private String groupNumber;
    private String groupName;
    private MedicareSecondaryType medicareSecondaryInsuranceType;
    private ClaimFilingIndicatorType payerType;

    private String payerName;

    private String payerId;
    private String payerStreet1;
    private String payerStreet2;
    private String payerCity;
    private String payerState;
    private String payerPostalCode;

    private Person subscriber;

    private NameIdentifierTypeCodeType payerIdType = NameIdentifierTypeCodeType.PayorIdentification;

    public final NameIdentifierTypeCodeType getPayerIdType() {
        return payerIdType;
    }

    public final void setPayerIdType(NameIdentifierTypeCodeType value) {
        payerIdType = value;
    }

    private Collection<PayerAdditionalId> payerAdditionalIds = new ArrayList<PayerAdditionalId>();

    public final Collection<PayerAdditionalId> getPayerAdditionalIds() {
        return payerAdditionalIds;
    }

    public final void setPayerAdditionalIds(Collection<PayerAdditionalId> value) {
        payerAdditionalIds = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBillingProviderId() {
        return billingProviderId;
    }

    public void setBillingProviderId(long billingProviderId) {
        this.billingProviderId = billingProviderId;
    }

    public long getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(long subscriberId) {
        this.subscriberId = subscriberId;
    }

    public PayerPriorityType getPayerSequence() {
        return payerSequence;
    }

    public void setPayerSequence(PayerPriorityType payerSequence) {
        this.payerSequence = payerSequence;
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

    public MedicareSecondaryType getMedicareSecondaryInsuranceType() {
        return medicareSecondaryInsuranceType;
    }

    public void setMedicareSecondaryInsuranceType(MedicareSecondaryType medicareSecondaryInsuranceType) {
        this.medicareSecondaryInsuranceType = medicareSecondaryInsuranceType;
    }

    public ClaimFilingIndicatorType getPayerType() {
        return payerType;
    }

    public void setPayerType(ClaimFilingIndicatorType payerType) {
        this.payerType = payerType;
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

    public Person getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Person subscriber) {
        this.subscriber = subscriber;
    }
}