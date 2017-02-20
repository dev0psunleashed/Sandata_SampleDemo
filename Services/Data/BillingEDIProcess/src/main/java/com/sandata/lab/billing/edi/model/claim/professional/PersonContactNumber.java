package com.sandata.lab.billing.edi.model.claim.professional;

import com.sandata.lab.billing.edi.model.enums.ContactNumberTypeCodeType;

public class PersonContactNumber {
    private long id;
    private long personContactId;

    private ContactNumberTypeCodeType contactType;

    private String number;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPersonContactId() {
        return personContactId;
    }

    public void setPersonContactId(long personContactId) {
        this.personContactId = personContactId;
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
}