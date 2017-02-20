package com.sandata.lab.rest.lookup.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigInteger;

/**
 * Models the response for getting all billers for a given business entity.
 * <p/>
 *
 * @author David Rutgos
 */
public class BusinessEntityBillerLookup extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("PayerSK")
    protected BigInteger payerSK;

    @SerializedName("BillerUserGloballyUniqueIdentifier")
    protected String billerUserGloballyUniqueIdentifier;

    @SerializedName("PayerID")
    protected String payerID;

    @SerializedName("PayerName")
    protected String payerName;

    public BigInteger getPayerSK() {
        return payerSK;
    }

    public void setPayerSK(BigInteger payerSK) {
        this.payerSK = payerSK;
    }

    public String getBillerUserGloballyUniqueIdentifier() {
        return billerUserGloballyUniqueIdentifier;
    }

    public void setBillerUserGloballyUniqueIdentifier(String billerUserGloballyUniqueIdentifier) {
        this.billerUserGloballyUniqueIdentifier = billerUserGloballyUniqueIdentifier;
    }

    public String getPayerID() {
        return payerID;
    }

    public void setPayerID(String payerID) {
        this.payerID = payerID;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }
}
