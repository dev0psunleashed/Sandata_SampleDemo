package com.sandata.lab.billing.edi.model.remittance;

import java.util.*;

import com.sandata.lab.billing.edi.model.enums.ContactCodeType;

public class PayerContact {
    private long id;
    private long payerId;

    private String name;

    private ContactCodeType type = ContactCodeType.InformationContact;

    public final ContactCodeType getType() {
        return type;
    }

    public final void setType(ContactCodeType value) {
        type = value;
    }

    private Collection<PayerContactNumber> contactNumbers = new ArrayList<PayerContactNumber>();

    public Collection<PayerContactNumber> getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(Collection<PayerContactNumber> value) {
        contactNumbers = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPayerId() {
        return payerId;
    }

    public void setPayerId(long payerId) {
        this.payerId = payerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}