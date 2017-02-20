package com.sandata.lab.data.model.dl.model.find;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * <p>ArTxnBatchManualPostFindResult</p>
 * <p>Date: 7/29/2016</p>
 * <p>Time: 3:00 PM</p>
 *
 * @author jasonscott
 */
public class ArTxnBatchManualPostFindResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("BatchDetailSK")
    protected BigInteger batchDetailSK;

    @SerializedName("BatchNumber")
    private String batchNumber;

    @SerializedName("BatchDepositDate")
    private Date batchDepositDate;

    @SerializedName("BatchPayerName")
    private String batchPayerName;

    @SerializedName("BatchCheckNumber")
    private String batchCheckNumber;

    @SerializedName("BatchTotalAmount")
    private BigDecimal batchTotalAmount;

    @SerializedName("BatchUnappliedPosted")
    private BigDecimal batchUnappliedPaymentPosted;

    @SerializedName("BatchTotalDenied")
    private BigDecimal batchTotalDenied;

    @SerializedName("BatchTotalPaid")
    private BigDecimal batchTotalPaid;

    @SerializedName("BatchTotalOpen")
    private BigDecimal batchTotalOpen;

    @SerializedName("BatchPosted")
    private boolean batchPosted;

    @SerializedName("BatchPayerID")
    protected String batchPayerID;

    @SerializedName("BatchCheckAmount")
    protected BigDecimal batchCheckAmount;

    @SerializedName("BatchPaymentBalance")
    private BigDecimal batchPaymentBalance;

    @SerializedName("BatchCheckDate")
    private Date checkDate;

    @SerializedName("BatchCheckReceivedDate")
    private Date checkReceived;

    @SerializedName("BatchPaymentType")
    private String paymentType;

    @SerializedName("BatchAdjustmentDebitsTotal")
    private BigDecimal adjustmentDebitTotal;

    @SerializedName("BatchAdjustmentCreditsTotal")
    private BigDecimal adjustmentCreditTotal;

    @SerializedName("BatchWriteOffDebitTotal")
    private BigDecimal writeOffDebitTotal;

    public Date getCheckReceived() {
        return checkReceived;
    }

    public void setCheckReceived(Date checkReceived) {
        this.checkReceived = checkReceived;
    }

    public BigDecimal getAdjustmentDebitTotal() {
        return adjustmentDebitTotal;
    }

    public void setAdjustmentDebitTotal(BigDecimal adjustmentDebitTotal) {
        this.adjustmentDebitTotal = adjustmentDebitTotal;
    }

    public BigDecimal getAdjustmentCreditTotal() {
        return adjustmentCreditTotal;
    }

    public void setAdjustmentCreditTotal(BigDecimal adjustmentCreditTotal) {
        this.adjustmentCreditTotal = adjustmentCreditTotal;
    }

    public BigDecimal getWriteOffDebitTotal() {
        return writeOffDebitTotal;
    }

    public void setWriteOffDebitTotal(BigDecimal writeOffDebitTotal) {
        this.writeOffDebitTotal = writeOffDebitTotal;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getBatchPaymentBalance() {
        return batchPaymentBalance;
    }

    public void setBatchPaymentBalance(BigDecimal batchPaymentBalance) {
        this.batchPaymentBalance = batchPaymentBalance;
    }

    public BigInteger getBatchDetailSK() {
        return batchDetailSK;
    }

    public void setBatchDetailSK(BigInteger batchDetailSK) {
        this.batchDetailSK = batchDetailSK;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Date getBatchDepositDate() {
        return batchDepositDate;
    }

    public void setBatchDepositDate(Date batchDepositDate) {
        this.batchDepositDate = batchDepositDate;
    }

    public String getBatchPayerName() {
        return batchPayerName;
    }

    public void setBatchPayerName(String batchPayerName) {
        this.batchPayerName = batchPayerName;
    }

    public String getBatchCheckNumber() {
        return batchCheckNumber;
    }

    public void setBatchCheckNumber(String batchCheckNumber) {
        this.batchCheckNumber = batchCheckNumber;
    }

    public BigDecimal getBatchTotalAmount() {
        return batchTotalAmount;
    }

    public void setBatchTotalAmount(BigDecimal batchTotalAmount) {
        this.batchTotalAmount = batchTotalAmount;
    }

    public BigDecimal getBatchUnappliedPaymentPosted() {
        return batchUnappliedPaymentPosted;
    }

    public void setBatchUnappliedPaymentPosted(BigDecimal batchUnappliedPaymentPosted) {
        this.batchUnappliedPaymentPosted = batchUnappliedPaymentPosted;
    }

    public BigDecimal getBatchTotalDenied() {
        return batchTotalDenied;
    }

    public void setBatchTotalDenied(BigDecimal batchTotalDenied) {
        this.batchTotalDenied = batchTotalDenied;
    }

    public BigDecimal getBatchTotalPaid() {
        return batchTotalPaid;
    }

    public void setBatchTotalPaid(BigDecimal batchTotalPaid) {
        this.batchTotalPaid = batchTotalPaid;
    }

    public BigDecimal getBatchTotalOpen() {
        return batchTotalOpen;
    }

    public void setBatchTotalOpen(BigDecimal batchTotalOpen) {
        this.batchTotalOpen = batchTotalOpen;
    }

    public boolean isBatchPosted() {
        return batchPosted;
    }

    public void setBatchPosted(boolean batchPosted) {
        this.batchPosted = batchPosted;
    }

    public String getBatchPayerID() {
        return batchPayerID;
    }

    public void setBatchPayerID(String batchPayerID) {
        this.batchPayerID = batchPayerID;
    }

    public BigDecimal getBatchCheckAmount() {
        return batchCheckAmount;
    }

    public void setBatchCheckAmount(BigDecimal batchCheckAmount) {
        this.batchCheckAmount = batchCheckAmount;
    }
}
