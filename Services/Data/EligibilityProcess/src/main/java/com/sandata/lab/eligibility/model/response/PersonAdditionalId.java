package com.sandata.lab.eligibility.model.response;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.eligibility.model.response.enums.AdditionalIdTypeCodeType;

public class PersonAdditionalId {
	
	@SerializedName("Id")
    private long id;

	@SerializedName("PersonId")
    private long personId;
	@SerializedName("Person")
    private Person person;
	@SerializedName("IdType")
    private AdditionalIdTypeCodeType idType;

	@SerializedName("AdditionalId")
    private String additionalId;
	@SerializedName("Description")
    private String description;

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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public AdditionalIdTypeCodeType getIdType() {
        return idType;
    }

    public void setIdType(AdditionalIdTypeCodeType idType) {
        this.idType = idType;
    }

    public String getAdditionalId() {
        return additionalId;
    }

    public void setAdditionalId(String additionalId) {
        this.additionalId = additionalId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}