package com.sandata.lab.rest.payer.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 5/15/2016
 * Time: 6:06 PM
 */
public class PayerHdr extends BaseObject {
    private static final long serialVersionUID = 1L;
    @SerializedName("PayerSK")
    private BigInteger payerSK;
    @SerializedName("PayerID")
    private String payerId;
    @SerializedName("PayerName")
    private String payerName;
    @SerializedName("BusinessEntityID")
    private String businessEntityID;
    @SerializedName("PayerActiveIndicator")
    private Boolean payerActiveIndicator;
    @SerializedName("PayerEffectiveDate")
    private Date payerEffectiveDate;
    @SerializedName("ContractID")
    private String contractID;
    @SerializedName("ContractDescription")
    private String contractDescription;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public BigInteger getPayerSK() {
        return payerSK;
    }

    public void setPayerSK(BigInteger payerSK) {
        this.payerSK = payerSK;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getBusinessEntityID() {
        return businessEntityID;
    }

    public void setBusinessEntityID(String businessEntityID) {
        this.businessEntityID = businessEntityID;
    }

    public Boolean getPayerActiveIndicator() {
        return payerActiveIndicator;
    }

    public void setPayerActiveIndicator(Boolean payerActiveIndicator) {
        this.payerActiveIndicator = payerActiveIndicator;
    }

    public Date getPayerEffectiveDate() {
        return payerEffectiveDate;
    }

    public void setPayerEffectiveDate(Date payerEffectiveDate) {
        this.payerEffectiveDate = payerEffectiveDate;
    }

    public String getContractID() {
        return contractID;
    }

    public void setContractID(String contractID) {
        this.contractID = contractID;
    }

    public String getContractDescription() {
        return contractDescription;
    }

    public void setContractDescription(String contractDescription) {
        this.contractDescription = contractDescription;
    }
}
