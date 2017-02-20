package com.sandata.lab.billing.edi.model.claim.professional;

import com.sandata.lab.billing.edi.model.enums.ContactNumberTypeCodeType;

public class SubmitterContactNumber {
    private long id;
    private long submitterContactId;

    private ContactNumberTypeCodeType contactType;
    private String number;

    private SubmitterContact submitterContact;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSubmitterContactId() {
        return submitterContactId;
    }

    public void setSubmitterContactId(long submitterContactId) {
        this.submitterContactId = submitterContactId;
    }

    public ContactNumberTypeCodeType getContactType() {
        return contactType;
    }

    public void setContactType(ContactNumberTypeCodeType contactType) {
        this.contactType = contactType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public SubmitterContact getSubmitterContact() {
        return submitterContact;
    }

    public void setSubmitterContact(SubmitterContact submitterContact) {
        this.submitterContact = submitterContact;
    }
}