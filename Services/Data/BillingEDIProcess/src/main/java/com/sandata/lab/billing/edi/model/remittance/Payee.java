package com.sandata.lab.billing.edi.model.remittance;

import java.util.*;

import com.sandata.lab.billing.edi.model.enums.*;

public class Payee {
    private long id;
    private long transactionId;

    private String name;
    private NameIdentifierTypeCodeType idType;
    private String entityId;

    private String street1;
    private String street2;
    private String city;
    private String state;
    private String postalCode;

    private RemittanceDeliveryType remittanceDeliveryType; // Only filled when
                                                          // the remittance is
                                                          // separate from the
                                                          // payment.
    private String remittanceDeliveryRecipient; // This contains the RDM02 or
                                               // RDM03 depending on the
                                               // delivery type. It is a person
                                               // for Mail and a url or email
                                               // for the electronic tranmission
                                               // types.

    private Collection<PayeeAdditionalId> additionalIds = new ArrayList<PayeeAdditionalId>();

    public final Collection<PayeeAdditionalId> getAdditionalIds() {
        return additionalIds;
    }

    public final void setAdditionalIds(Collection<PayeeAdditionalId> value) {
        additionalIds = value;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NameIdentifierTypeCodeType getIdType() {
        return idType;
    }

    public void setIdType(NameIdentifierTypeCodeType idType) {
        this.idType = idType;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
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

    public RemittanceDeliveryType getRemittanceDeliveryType() {
        return remittanceDeliveryType;
    }

    public void setRemittanceDeliveryType(RemittanceDeliveryType remittanceDeliveryType) {
        this.remittanceDeliveryType = remittanceDeliveryType;
    }

    public String getRemittanceDeliveryRecipient() {
        return remittanceDeliveryRecipient;
    }

    public void setRemittanceDeliveryRecipient(String remittanceDeliveryRecipient) {
        this.remittanceDeliveryRecipient = remittanceDeliveryRecipient;
    }
}