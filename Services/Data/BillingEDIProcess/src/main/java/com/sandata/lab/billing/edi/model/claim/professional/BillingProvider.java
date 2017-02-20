package com.sandata.lab.billing.edi.model.claim.professional;

import java.util.*;

import com.sandata.lab.billing.edi.model.enums.NameIdentifierTypeCodeType;

public class BillingProvider {
    private long id;
    private long transactionId;

    private String taxonomyCode;
    private String currencyCode;

    private String lastOrOrganizationName;
    private String firstName;
    private String middleName;
    private String suffixName;
    private String npi;

    private String street1;
    private String street2;
    private String city;
    private String state;
    private String postalCode;


    private String payToStreet1;
    private String payToStreet2;
    private String payToCity;
    private String payToState;
    private String payToPostalCode;

    private String payToPlanLastOrOrganizationName;
    private String payToPlanFirstName;
    private String payToPlanMiddleName;
    private String payToPlanSuffixName;
    private NameIdentifierTypeCodeType payToPlanIdType;
    private String payToPlanId;

    private String payToPlanStreet1;
    private String payToPlanStreet2;
    private String payToPlanCity;
    private String payToPlanState;
    private String payToPlanPostalCode;

    private Collection<BillingProviderAdditionalId> additionalIds = new ArrayList<BillingProviderAdditionalId>();

    public final Collection<BillingProviderAdditionalId> getAdditionalIds() {
        return additionalIds;
    }

    public final void setAdditionalIds(Collection<BillingProviderAdditionalId> value) {
        additionalIds = value;
    }

    private Collection<BillingProviderContact> contacts = new ArrayList<BillingProviderContact>();

    public final Collection<BillingProviderContact> getContacts() {
        return contacts;
    }

    public final void setContacts(Collection<BillingProviderContact> value) {
        contacts = value;
    }

    private Collection<PayToPlanAdditionalId> payToPlanAdditionalIds = new ArrayList<PayToPlanAdditionalId>();

    public final Collection<PayToPlanAdditionalId> getPayToPlanAdditionalIds() {
        return payToPlanAdditionalIds;
    }

    public final void setPayToPlanAdditionalIds(Collection<PayToPlanAdditionalId> value) {
        payToPlanAdditionalIds = value;
    }

    private Collection<SubscriberInformation> subscribers = new ArrayList<SubscriberInformation>();

    public final Collection<SubscriberInformation> getSubscribers() {
        return subscribers;
    }

    public final void setSubscribers(Collection<SubscriberInformation> value) {
        subscribers = value;
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

    public String getTaxonomyCode() {
        return taxonomyCode;
    }

    public void setTaxonomyCode(String taxonomyCode) {
        this.taxonomyCode = taxonomyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getLastOrOrganizationName() {
        return lastOrOrganizationName;
    }

    public void setLastOrOrganizationName(String lastOrOrganizationName) {
        this.lastOrOrganizationName = lastOrOrganizationName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSuffixName() {
        return suffixName;
    }

    public void setSuffixName(String suffixName) {
        this.suffixName = suffixName;
    }

    public String getNpi() {
        return npi;
    }

    public void setNpi(String npi) {
        this.npi = npi;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPayToStreet1() {
        return payToStreet1;
    }

    public void setPayToStreet1(String payToStreet1) {
        this.payToStreet1 = payToStreet1;
    }

    public String getPayToStreet2() {
        return payToStreet2;
    }

    public void setPayToStreet2(String payToStreet2) {
        this.payToStreet2 = payToStreet2;
    }

    public String getPayToCity() {
        return payToCity;
    }

    public void setPayToCity(String payToCity) {
        this.payToCity = payToCity;
    }

    public String getPayToState() {
        return payToState;
    }

    public void setPayToState(String payToState) {
        this.payToState = payToState;
    }

    public String getPayToPostalCode() {
        return payToPostalCode;
    }

    public void setPayToPostalCode(String payToPostalCode) {
        this.payToPostalCode = payToPostalCode;
    }

    public String getPayToPlanLastOrOrganizationName() {
        return payToPlanLastOrOrganizationName;
    }

    public void setPayToPlanLastOrOrganizationName(String payToPlanLastOrOrganizationName) {
        this.payToPlanLastOrOrganizationName = payToPlanLastOrOrganizationName;
    }

    public String getPayToPlanFirstName() {
        return payToPlanFirstName;
    }

    public void setPayToPlanFirstName(String payToPlanFirstName) {
        this.payToPlanFirstName = payToPlanFirstName;
    }

    public String getPayToPlanMiddleName() {
        return payToPlanMiddleName;
    }

    public void setPayToPlanMiddleName(String payToPlanMiddleName) {
        this.payToPlanMiddleName = payToPlanMiddleName;
    }

    public String getPayToPlanSuffixName() {
        return payToPlanSuffixName;
    }

    public void setPayToPlanSuffixName(String payToPlanSuffixName) {
        this.payToPlanSuffixName = payToPlanSuffixName;
    }

    public NameIdentifierTypeCodeType getPayToPlanIdType() {
        return payToPlanIdType;
    }

    public void setPayToPlanIdType(NameIdentifierTypeCodeType payToPlanIdType) {
        this.payToPlanIdType = payToPlanIdType;
    }

    public String getPayToPlanId() {
        return payToPlanId;
    }

    public void setPayToPlanId(String payToPlanId) {
        this.payToPlanId = payToPlanId;
    }

    public String getPayToPlanStreet1() {
        return payToPlanStreet1;
    }

    public void setPayToPlanStreet1(String payToPlanStreet1) {
        this.payToPlanStreet1 = payToPlanStreet1;
    }

    public String getPayToPlanStreet2() {
        return payToPlanStreet2;
    }

    public void setPayToPlanStreet2(String payToPlanStreet2) {
        this.payToPlanStreet2 = payToPlanStreet2;
    }

    public String getPayToPlanCity() {
        return payToPlanCity;
    }

    public void setPayToPlanCity(String payToPlanCity) {
        this.payToPlanCity = payToPlanCity;
    }

    public String getPayToPlanState() {
        return payToPlanState;
    }

    public void setPayToPlanState(String payToPlanState) {
        this.payToPlanState = payToPlanState;
    }

    public String getPayToPlanPostalCode() {
        return payToPlanPostalCode;
    }

    public void setPayToPlanPostalCode(String payToPlanPostalCode) {
        this.payToPlanPostalCode = payToPlanPostalCode;
    }
}