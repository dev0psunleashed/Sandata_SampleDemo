package com.sandata.lab.eligibility.model.response;

import java.util.Collection;

public class BenefitRelatedEntityContact {
    private long id;
    private long relatedEntityId;

    private String name;

    private Collection<BenefitRelatedEntityContactNumber> contactNumbers;
    private BenefitRelatedEntity relatedEntity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRelatedEntityId() {
        return relatedEntityId;
    }

    public void setRelatedEntityId(long relatedEntityId) {
        this.relatedEntityId = relatedEntityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<BenefitRelatedEntityContactNumber> getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(Collection<BenefitRelatedEntityContactNumber> contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    public BenefitRelatedEntity getRelatedEntity() {
        return relatedEntity;
    }

    public void setRelatedEntity(BenefitRelatedEntity relatedEntity) {
        this.relatedEntity = relatedEntity;
    }
}