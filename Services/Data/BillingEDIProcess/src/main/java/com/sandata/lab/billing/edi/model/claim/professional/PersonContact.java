package com.sandata.lab.billing.edi.model.claim.professional;

import java.util.*;

import com.sandata.lab.billing.edi.model.enums.ContactCodeType;

public class PersonContact {
    private long id;
    private long personId;

    private String name;

    private ContactCodeType type = ContactCodeType.InformationContact;

    public final ContactCodeType getType() {
        return type;
    }

    public final void setType(ContactCodeType value) {
        type = value;
    }

    private Collection<PersonContactNumber> contactNumbers = new ArrayList<PersonContactNumber>();

    public Collection<PersonContactNumber> getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(Collection<PersonContactNumber> value) {
        contactNumbers = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}