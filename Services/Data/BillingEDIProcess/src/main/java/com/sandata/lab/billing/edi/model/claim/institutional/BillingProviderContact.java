package com.sandata.lab.billing.edi.model.claim.institutional;

import java.util.*;

import com.sandata.lab.billing.edi.model.enums.ContactCodeType;

public class BillingProviderContact {
    private long id;
    private long billingProviderId;
    private String name;

    private ContactCodeType type = ContactCodeType.InformationContact;

    public final ContactCodeType getType() {
        return type;
    }

    public final void setType(ContactCodeType value) {
        type = value;
    }

    private Collection<BillingProviderContactNumber> contactNumbers = new ArrayList<BillingProviderContactNumber>();

    public Collection<BillingProviderContactNumber> getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(Collection<BillingProviderContactNumber> value) {
        contactNumbers = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBillingProviderId() {
        return billingProviderId;
    }

    public void setBillingProviderId(long billingProviderId) {
        this.billingProviderId = billingProviderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}