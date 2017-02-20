package com.sandata.lab.billing.edi.model.claim.professional;

import com.sandata.lab.billing.edi.model.enums.ContactNumberTypeCodeType;

public class ServiceEntityContactNumber {
    private long id;
    private long serviceEntityContactId;

    private ContactNumberTypeCodeType contactType;
    
    private String number;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getServiceEntityContactId() {
        return serviceEntityContactId;
    }

    public void setServiceEntityContactId(long serviceEntityContactId) {
        this.serviceEntityContactId = serviceEntityContactId;
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