package com.sandata.lab.billing.edi.model.claim.professional;

import java.util.*;

import com.sandata.lab.billing.edi.model.enums.ContactCodeType;

public class ServiceEntityContact {
    private long id;
    private long serviceEntityId;

    private String name;

    private ContactCodeType type = ContactCodeType.InformationContact;

    public final ContactCodeType getType() {
        return type;
    }

    public final void setType(ContactCodeType value) {
        type = value;
    }

    private Collection<ServiceEntityContactNumber> contactNumbers = new ArrayList<ServiceEntityContactNumber>();

    public Collection<ServiceEntityContactNumber> getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(Collection<ServiceEntityContactNumber> value) {
        contactNumbers = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getServiceEntityId() {
        return serviceEntityId;
    }

    public void setServiceEntityId(long serviceEntityId) {
        this.serviceEntityId = serviceEntityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}