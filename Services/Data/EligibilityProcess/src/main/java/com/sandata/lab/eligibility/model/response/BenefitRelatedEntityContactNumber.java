package com.sandata.lab.eligibility.model.response;

import com.sandata.lab.eligibility.model.response.enums.ContactNumberTypeCodeType;

public class BenefitRelatedEntityContactNumber {
    private long id;
    private long contactId;

    private ContactNumberTypeCodeType contactType;
    private String number;

    private BenefitRelatedEntityContact contact;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
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

    public BenefitRelatedEntityContact getContact() {
        return contact;
    }

    public void setContact(BenefitRelatedEntityContact contact) {
        this.contact = contact;
    }
}