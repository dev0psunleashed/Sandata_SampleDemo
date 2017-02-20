package com.sandata.lab.data.model.dl.model.find;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.find.enums.ARPostStatus;

import java.math.BigDecimal;
import java.util.Date;


public class AccountsReceivableAutoPostFindResult extends BaseObject {

    private static final long serialVersionUID = 1L;



    @SerializedName("AccountsReceivableSK")
    BigDecimal arSk;

    @SerializedName("ProviderName")
    String providerName;

    @SerializedName("TaxId")
    String taxId;

    @SerializedName("NPI")
    String npi;

    @SerializedName("Payer")
    String payer;

    @SerializedName("CheckNumber")
    String checkNumber;

    @SerializedName("CheckDate")
    Date checkDate;

    @SerializedName("CheckReceivedDate")
    Date checkReceivedDate;

    @SerializedName("CheckAmount")
    BigDecimal checkAmount;

    @SerializedName("IsPLBPresent")
    boolean isPLBPresent;

    @SerializedName("ERAReceivedDate")
    Date eraReceivedDate;

    @SerializedName("Status")
    ARPostStatus arPostStatus;

    public BigDecimal getArSk() {
        return arSk;
    }

    public void setArSk(BigDecimal arSk) {
        this.arSk = arSk;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getNpi() {
        return npi;
    }

    public void setNpi(String npi) {
        this.npi = npi;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Date getCheckReceivedDate() {
        return checkReceivedDate;
    }

    public void setCheckReceivedDate(Date checkReceivedDate) {
        this.checkReceivedDate = checkReceivedDate;
    }

    public BigDecimal getCheckAmount() {
        return checkAmount;
    }

    public void setCheckAmount(BigDecimal checkAmount) {
        this.checkAmount = checkAmount;
    }

    public boolean isPLBPresent() {
        return isPLBPresent;
    }

    public void setPLBPresent(boolean PLBPresent) {
        isPLBPresent = PLBPresent;
    }

    public Date getEraReceivedDate() {
        return eraReceivedDate;
    }

    public void setEraReceivedDate(Date eraReceivedDate) {
        this.eraReceivedDate = eraReceivedDate;
    }

    public ARPostStatus getArPostStatus() {
        return arPostStatus;
    }

    public void setArPostStatus(ARPostStatus arPostStatus) {
        this.arPostStatus = arPostStatus;
    }


}
