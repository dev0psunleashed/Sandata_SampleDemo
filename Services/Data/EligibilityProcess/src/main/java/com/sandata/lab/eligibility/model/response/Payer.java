package com.sandata.lab.eligibility.model.response;

import java.util.Collection;

import com.google.gson.annotations.SerializedName;

public class Payer {
	@SerializedName("Id")
    private long id;
	@SerializedName("Name")
    private String name;
	@SerializedName("TransactionId")
    private long transactionId;
	@SerializedName("Transaction")
    private InquiryResponseTransaction transaction;

	@SerializedName("RequestErrors")
    private Collection<PayerRequestError> requestErrors;
	@SerializedName("Providers")
    private Collection<Provider> providers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public InquiryResponseTransaction getTransaction() {
        return transaction;
    }

    public void setTransaction(InquiryResponseTransaction transaction) {
        this.transaction = transaction;
    }

    public Collection<PayerRequestError> getRequestErrors() {
        return requestErrors;
    }

    public void setRequestErrors(Collection<PayerRequestError> requestErrors) {
        this.requestErrors = requestErrors;
    }

    public Collection<Provider> getProviders() {
        return providers;
    }

    public void setProviders(Collection<Provider> providers) {
        this.providers = providers;
    }
}