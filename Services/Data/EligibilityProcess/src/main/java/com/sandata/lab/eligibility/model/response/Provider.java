package com.sandata.lab.eligibility.model.response;

import java.util.Collection;

import com.google.gson.annotations.SerializedName;

public class Provider {
	@SerializedName("Id")
    private long id;

	@SerializedName("PayerId")
    private long payerId;
	@SerializedName("Payer")
    private Payer payer;

	@SerializedName("RequestErrors")
    private Collection<ProviderRequestError> requestErrors;
	@SerializedName("People")
    private Collection<Person> people;

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

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public Collection<ProviderRequestError> getRequestErrors() {
        return requestErrors;
    }

    public void setRequestErrors(Collection<ProviderRequestError> requestErrors) {
        this.requestErrors = requestErrors;
    }

    public Collection<Person> getPeople() {
        return people;
    }

    public void setPeople(Collection<Person> people) {
        this.people = people;
    }
}