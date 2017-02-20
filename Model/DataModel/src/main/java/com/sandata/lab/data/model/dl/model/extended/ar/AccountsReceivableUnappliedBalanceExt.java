package com.sandata.lab.data.model.dl.model.extended.ar;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.AccountsReceivable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 7/30/2016
 * Time: 10:23 AM
 */
public class AccountsReceivableUnappliedBalanceExt extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("AccountsReceivableSK")
    private BigInteger accountsReceivableSk;

    @SerializedName("AccountsReceivableBatchDetailSK")
    private BigInteger accountsReceivableBatchDetailSk;

    @SerializedName("CheckNumber")
    private String checkNumber;

    @SerializedName("CheckDate")
    private Date checkDate;

    @SerializedName("UnappliedBalance")
    private BigDecimal unappliedBalance;

    @SerializedName("BatchNumber")
    private String batchNumber;

    @SerializedName("PayerName")
    private String payerName;

    @SerializedName("PayerID")
    private String payerID;

    @SerializedName("TransactionDate")
    private Date transactionDate;

    @SerializedName("TransactionDescription")
    private String transactionDescription;

    @SerializedName("Notes")
    private String notes;

    @SerializedName("UserName")
    private String userName;

    @SerializedName("TransactionCode")
    private String transactionCode;

    @SerializedName("TransactionType")
    private String transactionType;


    public BigInteger getAccountsReceivableSk() {
        return accountsReceivableSk;
    }

    public void setAccountsReceivableSk(BigInteger accountsReceivableSk) {
        this.accountsReceivableSk = accountsReceivableSk;
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

    public BigDecimal getUnappliedBalance() {
        return unappliedBalance;
    }

    public void setUnappliedBalance(BigDecimal unappliedBalance) {
        this.unappliedBalance = unappliedBalance;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getPayerID() {
        return payerID;
    }

    public void setPayerID(String payerID) {
        this.payerID = payerID;
    }

    public BigInteger getAccountsReceivableBatchDetailSk() {
        return accountsReceivableBatchDetailSk;
    }

    public void setAccountsReceivableBatchDetailSk(BigInteger accountsReceivableBatchDetailSk) {
        this.accountsReceivableBatchDetailSk = accountsReceivableBatchDetailSk;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
