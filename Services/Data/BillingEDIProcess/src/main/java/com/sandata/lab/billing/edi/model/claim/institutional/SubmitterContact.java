package com.sandata.lab.billing.edi.model.claim.institutional;

import java.util.*;

import com.sandata.lab.billing.edi.model.enums.ContactCodeType;

public class SubmitterContact {
    private long id;
    private long transactionId;
    private String name;

    private ContactCodeType type = ContactCodeType.InformationContact;

    public final ContactCodeType getType() {
        return type;
    }

    public final void setType(ContactCodeType value) {
        type = value;
    }

    private Collection<SubmitterContactNumber> contactNumbers = new ArrayList<SubmitterContactNumber>();

    public Collection<SubmitterContactNumber> getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(Collection<SubmitterContactNumber> value) {
        contactNumbers = value;
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
}