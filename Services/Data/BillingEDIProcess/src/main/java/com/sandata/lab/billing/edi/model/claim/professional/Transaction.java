package com.sandata.lab.billing.edi.model.claim.professional;

import java.util.Collection;
import java.util.Date;

import com.sandata.lab.billing.edi.model.enums.TransactionPurposeCodeType;
import com.sandata.lab.billing.edi.model.enums.TransactionTypeCodeType;

public class Transaction {
    private long id;
    private long responseMessageId;
    private TransactionPurposeCodeType purpose;
    private String transactionId;
    private Date creationDateTime = new Date(0);
    private TransactionTypeCodeType transactionType;

    private String submitterLastOrOrganizationName;
    private String submitterFirstName;
    private String submitterMiddleName;
    private String submitterSuffixName;
    private String submitterId;

    private String receiverLastOrOrganizationName;
    private String receiverFirstName;
    private String receiverMiddleName;
    private String receiverSuffixName;
    private String receiverId;

    private Collection<SubmitterContact> submitterContacts;

    private Collection<BillingProvider> billingProviders;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getResponseMessageId() {
        return responseMessageId;
    }

    public void setResponseMessageId(long responseMessageId) {
        this.responseMessageId = responseMessageId;
    }

    public TransactionPurposeCodeType getPurpose() {
        return purpose;
    }

    public void setPurpose(TransactionPurposeCodeType purpose) {
        this.purpose = purpose;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public TransactionTypeCodeType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionTypeCodeType transactionType) {
        this.transactionType = transactionType;
    }

    public String getSubmitterLastOrOrganizationName() {
        return submitterLastOrOrganizationName;
    }

    public void setSubmitterLastOrOrganizationName(String submitterLastOrOrganizationName) {
        this.submitterLastOrOrganizationName = submitterLastOrOrganizationName;
    }

    public String getSubmitterFirstName() {
        return submitterFirstName;
    }

    public void setSubmitterFirstName(String submitterFirstName) {
        this.submitterFirstName = submitterFirstName;
    }

    public String getSubmitterMiddleName() {
        return submitterMiddleName;
    }

    public void setSubmitterMiddleName(String submitterMiddleName) {
        this.submitterMiddleName = submitterMiddleName;
    }

    public String getSubmitterSuffixName() {
        return submitterSuffixName;
    }

    public void setSubmitterSuffixName(String submitterSuffixName) {
        this.submitterSuffixName = submitterSuffixName;
    }

    public String getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(String submitterId) {
        this.submitterId = submitterId;
    }

    public String getReceiverLastOrOrganizationName() {
        return receiverLastOrOrganizationName;
    }

    public void setReceiverLastOrOrganizationName(String receiverLastOrOrganizationName) {
        this.receiverLastOrOrganizationName = receiverLastOrOrganizationName;
    }

    public String getReceiverFirstName() {
        return receiverFirstName;
    }

    public void setReceiverFirstName(String receiverFirstName) {
        this.receiverFirstName = receiverFirstName;
    }

    public String getReceiverMiddleName() {
        return receiverMiddleName;
    }

    public void setReceiverMiddleName(String receiverMiddleName) {
        this.receiverMiddleName = receiverMiddleName;
    }

    public String getReceiverSuffixName() {
        return receiverSuffixName;
    }

    public void setReceiverSuffixName(String receiverSuffixName) {
        this.receiverSuffixName = receiverSuffixName;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public Collection<SubmitterContact> getSubmitterContacts() {
        return submitterContacts;
    }

    public void setSubmitterContacts(Collection<SubmitterContact> submitterContacts) {
        this.submitterContacts = submitterContacts;
    }

    public Collection<BillingProvider> getBillingProviders() {
        return billingProviders;
    }

    public void setBillingProviders(Collection<BillingProvider> billingProviders) {
        this.billingProviders = billingProviders;
    }
}