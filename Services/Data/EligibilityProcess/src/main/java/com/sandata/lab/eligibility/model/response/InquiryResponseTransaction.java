package com.sandata.lab.eligibility.model.response;

import java.util.*;

import com.google.gson.annotations.SerializedName;

public class InquiryResponseTransaction {
	
	
	@SerializedName("Id")
    private long id;
	@SerializedName("TransactionId")
    private String transactionId;
	@SerializedName("Payers")
    private List<Payer> payers;
	@SerializedName("DateTime")
    private Date dateTime;
	@SerializedName("ResponseMessageId")
    private long responseMessageId;
	@SerializedName("ResponseMessage")
    private ResponseMessage responseMessage;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public List<Payer> getPayers() {
        return payers;
    }

    public void setPayers(List<Payer> payers) {
        this.payers = payers;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public long getResponseMessageId() {
        return responseMessageId;
    }

    public void setResponseMessageId(long responseMessageId) {
        this.responseMessageId = responseMessageId;
    }

    public ResponseMessage getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(ResponseMessage responseMessage) {
        this.responseMessage = responseMessage;
    }
}