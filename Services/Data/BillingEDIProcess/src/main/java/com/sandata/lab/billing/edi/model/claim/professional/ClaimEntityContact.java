package com.sandata.lab.billing.edi.model.claim.professional;

import java.util.*;

import com.sandata.lab.billing.edi.model.enums.ContactCodeType;

public class ClaimEntityContact {
    private long id;
    private long claimEntityId;
    private String name;

    private ContactCodeType type = ContactCodeType.InformationContact;

    public final ContactCodeType getType() {
        return type;
    }

    public final void setType(ContactCodeType value) {
        type = value;
    }

    private Collection<ClaimEntityContactNumber> contactNumbers = new ArrayList<ClaimEntityContactNumber>();

    public Collection<ClaimEntityContactNumber> getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(Collection<ClaimEntityContactNumber> value) {
        contactNumbers = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClaimEntityId() {
        return claimEntityId;
    }

    public void setClaimEntityId(long claimEntityId) {
        this.claimEntityId = claimEntityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}