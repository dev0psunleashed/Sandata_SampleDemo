package com.sandata.lab.billing.edi.model.claim.professional;

import com.sandata.lab.billing.edi.model.enums.ContactNumberTypeCodeType;

public class BillingProviderContactNumber {
    private long id;
    private long billingProviderContactId;

    private ContactNumberTypeCodeType contactType;
    private String number;

    public long getBillingProviderContactId() {
        return billingProviderContactId;
    }

    public void setBillingProviderContactId(long billingProviderContactId) {
        this.billingProviderContactId = billingProviderContactId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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