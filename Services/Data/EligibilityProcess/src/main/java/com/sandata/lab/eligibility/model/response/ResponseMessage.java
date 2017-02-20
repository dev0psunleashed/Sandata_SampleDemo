package com.sandata.lab.eligibility.model.response;

import java.util.Collection;

import com.google.gson.annotations.SerializedName;

public class ResponseMessage {
	
	@SerializedName("Id")
    private long id;
	
	@SerializedName("Name")
    private String name;
	
	@SerializedName("Contents")
    private String  contents;

    @SerializedName("InquiryResponseTransactions")
    private Collection<InquiryResponseTransaction> inquiryResponseTransactions;
    
    @SerializedName("Errors")
    private Collection<ResponseError> errors;

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


    public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Collection<InquiryResponseTransaction> getInquiryResponseTransactions() {
        return inquiryResponseTransactions;
    }

    public void setInquiryResponseTransactions(Collection<InquiryResponseTransaction> inquiryResponseTransactions) {
        this.inquiryResponseTransactions = inquiryResponseTransactions;
    }

    public Collection<ResponseError> getErrors() {
        return errors;
    }

    public void setErrors(Collection<ResponseError> errors) {
        this.errors = errors;
    }
}