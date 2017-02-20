package com.sandata.lab.billing.edi.model.claim.professional;

import com.sandata.lab.billing.edi.model.enums.ContactNumberTypeCodeType;

public class ClaimEntityContactNumber {
    private long id;
    private long claimEntityContactId;

    private ContactNumberTypeCodeType contactType;
    private String number;

    public long getClaimEntityContactId() {
        return claimEntityContactId;
    }

    public void setClaimEntityContactId(long claimEntityContactId) {
        this.claimEntityContactId = claimEntityContactId;
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