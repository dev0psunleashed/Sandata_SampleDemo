package com.sandata.lab.billing.edi.model.remittance;

import java.util.*;

import com.sandata.lab.billing.edi.model.enums.NameIdentifierTypeCodeType;

public class Payer {
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

    private Collection<PayerAdditionalId> additionalIds = new ArrayList<PayerAdditionalId>();

    public final Collection<PayerAdditionalId> getAdditionalIds() {
        return additionalIds;
    }

    public final void setAdditionalIds(Collection<PayerAdditionalId> value) {
        additionalIds = value;
    }

    private Collection<PayerContact> contacts = new ArrayList<PayerContact>();

    public final Collection<PayerContact> getContacts() {
        return contacts;
    }

    public final void setContacts(Collection<PayerContact> value) {
        contacts = value;
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
}