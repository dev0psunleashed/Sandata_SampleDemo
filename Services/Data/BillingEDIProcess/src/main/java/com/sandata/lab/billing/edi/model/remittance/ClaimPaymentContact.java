package com.sandata.lab.billing.edi.model.remittance;

import java.util.*;

import com.sandata.lab.billing.edi.model.enums.ContactCodeType;

public class ClaimPaymentContact {
    private long id;
    private long claimPaymentId;

    private String name;

    private ContactCodeType type = ContactCodeType.InformationContact;

    public final ContactCodeType getType() {
        return type;
    }

    public final void setType(ContactCodeType value) {
        type = value;
    }

    private Collection<ClaimPaymentContactNumber> contactNumbers = new ArrayList<ClaimPaymentContactNumber>();

    public Collection<ClaimPaymentContactNumber> getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(Collection<ClaimPaymentContactNumber> value) {
        contactNumbers = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClaimPaymentId() {
        return claimPaymentId;
    }

    public void setClaimPaymentId(long claimPaymentId) {
        this.claimPaymentId = claimPaymentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}