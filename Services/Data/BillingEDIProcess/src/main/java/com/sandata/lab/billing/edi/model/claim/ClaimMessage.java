package com.sandata.lab.billing.edi.model.claim;

import java.util.Collection;

public class ClaimMessage {

    private String name;
    private String interchangeSubmitterIdType;
    private String interchangeSubmitterId;
    private String interchangeReceiverIdType;
    private String interchangeReceiverId;
    private String groupSubmitterId;
    private String groupReceiverId;
    private String controlNumber;
    private String segmentDelimiter;
    private String elementDelimiter;
    private String subelementDelimiter;
    private String repetitionDelimiter;
    private Collection<com.sandata.lab.billing.edi.model.remittance.Transaction> remittanceTransactions;
    private Collection<com.sandata.lab.billing.edi.model.claim.professional.Transaction> professionalClaimTransactions;
    private Collection<com.sandata.lab.billing.edi.model.claim.institutional.Transaction> institutionalClaimTransactions;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the interchangeSubmitterIdType
     */
    public String getInterchangeSubmitterIdType() {
        return interchangeSubmitterIdType;
    }

    /**
     * @param interchangeSubmitterIdType the interchangeSubmitterIdType to set
     */
    public void setInterchangeSubmitterIdType(String interchangeSubmitterIdType) {
        this.interchangeSubmitterIdType = interchangeSubmitterIdType;
    }

    /**
     * @return the interchangeSubmitterId
     */
    public String getInterchangeSubmitterId() {
        return interchangeSubmitterId;
    }

    /**
     * @param interchangeSubmitterId the interchangeSubmitterId to set
     */
    public void setInterchangeSubmitterId(String interchangeSubmitterId) {
        this.interchangeSubmitterId = interchangeSubmitterId;
    }

    /**
     * @return the interchangeReceiverIdType
     */
    public String getInterchangeReceiverIdType() {
        return interchangeReceiverIdType;
    }

    /**
     * @param interchangeReceiverIdType the interchangeReceiverIdType to set
     */
    public void setInterchangeReceiverIdType(String interchangeReceiverIdType) {
        this.interchangeReceiverIdType = interchangeReceiverIdType;
    }

    /**
     * @return the interchangeReceiverId
     */
    public String getInterchangeReceiverId() {
        return interchangeReceiverId;
    }

    /**
     * @param interchangeReceiverId the interchangeReceiverId to set
     */
    public void setInterchangeReceiverId(String interchangeReceiverId) {
        this.interchangeReceiverId = interchangeReceiverId;
    }

    /**
     * @return the groupSubmitterId
     */
    public String getGroupSubmitterId() {
        return groupSubmitterId;
    }

    /**
     * @param groupSubmitterId the groupSubmitterId to set
     */
    public void setGroupSubmitterId(String groupSubmitterId) {
        this.groupSubmitterId = groupSubmitterId;
    }

    /**
     * @return the groupReceiverId
     */
    public String getGroupReceiverId() {
        return groupReceiverId;
    }

    /**
     * @param groupReceiverId the groupReceiverId to set
     */
    public void setGroupReceiverId(String groupReceiverId) {
        this.groupReceiverId = groupReceiverId;
    }

    /**
     * @return the controlNumber
     */
    public String getControlNumber() {
        return controlNumber;
    }

    /**
     * @param controlNumber the controlNumber to set
     */
    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber;
    }

    /**
     * @return the segmentDelimiter
     */
    public String getSegmentDelimiter() {
        return segmentDelimiter;
    }

    /**
     * @param segmentDelimiter the segmentDelimiter to set
     */
    public void setSegmentDelimiter(String segmentDelimiter) {
        this.segmentDelimiter = segmentDelimiter;
    }

    /**
     * @return the elementDelimiter
     */
    public String getElementDelimiter() {
        return elementDelimiter;
    }

    /**
     * @param elementDelimiter the elementDelimiter to set
     */
    public void setElementDelimiter(String elementDelimiter) {
        this.elementDelimiter = elementDelimiter;
    }

    /**
     * @return the subelementDelimiter
     */
    public String getSubelementDelimiter() {
        return subelementDelimiter;
    }

    /**
     * @param subelementDelimiter the subelementDelimiter to set
     */
    public void setSubelementDelimiter(String subelementDelimiter) {
        this.subelementDelimiter = subelementDelimiter;
    }

    /**
     * @return the repetitionDelimiter
     */
    public String getRepetitionDelimiter() {
        return repetitionDelimiter;
    }

    /**
     * @param repetitionDelimiter the repetitionDelimiter to set
     */
    public void setRepetitionDelimiter(String repetitionDelimiter) {
        this.repetitionDelimiter = repetitionDelimiter;
    }

    /**
     * @return the remittanceTransactions
     */
    public Collection<com.sandata.lab.billing.edi.model.remittance.Transaction> getRemittanceTransactions() {
        return remittanceTransactions;
    }

    /**
     * @param remittanceTransactions the remittanceTransactions to set
     */
    public void setRemittanceTransactions(
            Collection<com.sandata.lab.billing.edi.model.remittance.Transaction> remittanceTransactions) {
        this.remittanceTransactions = remittanceTransactions;
    }

    /**
     * @return the professionalClaimTransactions
     */
    public Collection<com.sandata.lab.billing.edi.model.claim.professional.Transaction> getProfessionalClaimTransactions() {
        return professionalClaimTransactions;
    }

    /**
     * @param professionalClaimTransactions the professionalClaimTransactions to set
     */
    public void setProfessionalClaimTransactions(
            Collection<com.sandata.lab.billing.edi.model.claim.professional.Transaction> professionalClaimTransactions) {
        this.professionalClaimTransactions = professionalClaimTransactions;
    }

    /**
     * @return the institutionalClaimTransactions
     */
    public Collection<com.sandata.lab.billing.edi.model.claim.institutional.Transaction> getInstitutionalClaimTransactions() {
        return institutionalClaimTransactions;
    }

    /**
     * @param institutionalClaimTransactions the institutionalClaimTransactions to set
     */
    public void setInstitutionalClaimTransactions(
            Collection<com.sandata.lab.billing.edi.model.claim.institutional.Transaction> institutionalClaimTransactions) {
        this.institutionalClaimTransactions = institutionalClaimTransactions;
    }
}
