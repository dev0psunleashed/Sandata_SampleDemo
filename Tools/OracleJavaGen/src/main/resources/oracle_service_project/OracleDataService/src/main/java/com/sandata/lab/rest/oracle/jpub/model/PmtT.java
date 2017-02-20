package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class PmtT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PMT_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,2,2,2,2,2,2,2,12,12,12,12,12,2,2,2,12,2,12,12,12,91,2,12,12,12,12,12,12,12,12,12,1,12,91,12,12,91,91,12,12,2,91,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[51];
  protected static final PmtT _PmtTFactory = new PmtT();

  public static ORADataFactory getORADataFactory()
  { return _PmtTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[51], _sqlType, _factory); }
  public PmtT()
  { _init_struct(true); }
  public PmtT(java.math.BigDecimal pmtSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, java.math.BigDecimal payerSk, java.math.BigDecimal claimPmtAdviceSk, java.math.BigDecimal pmt835HistSk, java.math.BigDecimal billingDetSk, java.math.BigDecimal claimSk, String beId, String ptId, String billingNum, String sandataClaimId, String pmtSrc, java.math.BigDecimal pmtAmt, java.math.BigDecimal fullPmtInd, java.math.BigDecimal partialPmtInd, String mthdOfPmt, java.math.BigDecimal checkNum, String bankRoutingNum, String bankAccountNum, String bankAccountTyp, java.sql.Timestamp checkIssueDate, java.math.BigDecimal bouncedCheckInd, String checkStatus, String checkReasonForHold, String creditCardTyp, String creditCardNum, String creditCardHolderName, String creditCardHolderAddr1, String creditCardHolderAddr2, String creditCardHolderAptNum, String creditCardHolderCity, String creditCardHolderState, String creditCardHolderPstlCode, java.sql.Timestamp creditCardExprDate, String creditCardCvcCode, String creditCardAuthCode, java.sql.Timestamp creditCardAuthDate, java.sql.Timestamp postedDate, String pmtProcessingMthdCode, String batchId, java.math.BigDecimal pmtPostedInd, java.sql.Timestamp pmtPostedDate, String pmtPostStatusCode) throws SQLException
  { _init_struct(true);
    setPmtSk(pmtSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonMemo(changeReasonMemo);
    setCurrRecInd(currRecInd);
    setChangeVersionId(changeVersionId);
    setPayerSk(payerSk);
    setClaimPmtAdviceSk(claimPmtAdviceSk);
    setPmt835HistSk(pmt835HistSk);
    setBillingDetSk(billingDetSk);
    setClaimSk(claimSk);
    setBeId(beId);
    setPtId(ptId);
    setBillingNum(billingNum);
    setSandataClaimId(sandataClaimId);
    setPmtSrc(pmtSrc);
    setPmtAmt(pmtAmt);
    setFullPmtInd(fullPmtInd);
    setPartialPmtInd(partialPmtInd);
    setMthdOfPmt(mthdOfPmt);
    setCheckNum(checkNum);
    setBankRoutingNum(bankRoutingNum);
    setBankAccountNum(bankAccountNum);
    setBankAccountTyp(bankAccountTyp);
    setCheckIssueDate(checkIssueDate);
    setBouncedCheckInd(bouncedCheckInd);
    setCheckStatus(checkStatus);
    setCheckReasonForHold(checkReasonForHold);
    setCreditCardTyp(creditCardTyp);
    setCreditCardNum(creditCardNum);
    setCreditCardHolderName(creditCardHolderName);
    setCreditCardHolderAddr1(creditCardHolderAddr1);
    setCreditCardHolderAddr2(creditCardHolderAddr2);
    setCreditCardHolderAptNum(creditCardHolderAptNum);
    setCreditCardHolderCity(creditCardHolderCity);
    setCreditCardHolderState(creditCardHolderState);
    setCreditCardHolderPstlCode(creditCardHolderPstlCode);
    setCreditCardExprDate(creditCardExprDate);
    setCreditCardCvcCode(creditCardCvcCode);
    setCreditCardAuthCode(creditCardAuthCode);
    setCreditCardAuthDate(creditCardAuthDate);
    setPostedDate(postedDate);
    setPmtProcessingMthdCode(pmtProcessingMthdCode);
    setBatchId(batchId);
    setPmtPostedInd(pmtPostedInd);
    setPmtPostedDate(pmtPostedDate);
    setPmtPostStatusCode(pmtPostStatusCode);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PmtT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PmtT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPmtSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPmtSk(java.math.BigDecimal pmtSk) throws SQLException
  { _struct.setAttribute(0, pmtSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public java.sql.Timestamp getRecEffTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(3); }

  public void setRecEffTmstp(java.sql.Timestamp recEffTmstp) throws SQLException
  { _struct.setAttribute(3, recEffTmstp); }


  public java.sql.Timestamp getRecTermTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(4); }

  public void setRecTermTmstp(java.sql.Timestamp recTermTmstp) throws SQLException
  { _struct.setAttribute(4, recTermTmstp); }


  public String getRecCreatedBy() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setRecCreatedBy(String recCreatedBy) throws SQLException
  { _struct.setAttribute(5, recCreatedBy); }


  public String getRecUpdatedBy() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setRecUpdatedBy(String recUpdatedBy) throws SQLException
  { _struct.setAttribute(6, recUpdatedBy); }


  public String getChangeReasonMemo() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setChangeReasonMemo(String changeReasonMemo) throws SQLException
  { _struct.setAttribute(7, changeReasonMemo); }


  public java.math.BigDecimal getCurrRecInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(8); }

  public void setCurrRecInd(java.math.BigDecimal currRecInd) throws SQLException
  { _struct.setAttribute(8, currRecInd); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(9); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(9, changeVersionId); }


  public java.math.BigDecimal getPayerSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(10); }

  public void setPayerSk(java.math.BigDecimal payerSk) throws SQLException
  { _struct.setAttribute(10, payerSk); }


  public java.math.BigDecimal getClaimPmtAdviceSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(11); }

  public void setClaimPmtAdviceSk(java.math.BigDecimal claimPmtAdviceSk) throws SQLException
  { _struct.setAttribute(11, claimPmtAdviceSk); }


  public java.math.BigDecimal getPmt835HistSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(12); }

  public void setPmt835HistSk(java.math.BigDecimal pmt835HistSk) throws SQLException
  { _struct.setAttribute(12, pmt835HistSk); }


  public java.math.BigDecimal getBillingDetSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(13); }

  public void setBillingDetSk(java.math.BigDecimal billingDetSk) throws SQLException
  { _struct.setAttribute(13, billingDetSk); }


  public java.math.BigDecimal getClaimSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(14); }

  public void setClaimSk(java.math.BigDecimal claimSk) throws SQLException
  { _struct.setAttribute(14, claimSk); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(15, beId); }


  public String getPtId() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setPtId(String ptId) throws SQLException
  { _struct.setAttribute(16, ptId); }


  public String getBillingNum() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setBillingNum(String billingNum) throws SQLException
  { _struct.setAttribute(17, billingNum); }


  public String getSandataClaimId() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setSandataClaimId(String sandataClaimId) throws SQLException
  { _struct.setAttribute(18, sandataClaimId); }


  public String getPmtSrc() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setPmtSrc(String pmtSrc) throws SQLException
  { _struct.setAttribute(19, pmtSrc); }


  public java.math.BigDecimal getPmtAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(20); }

  public void setPmtAmt(java.math.BigDecimal pmtAmt) throws SQLException
  { _struct.setAttribute(20, pmtAmt); }


  public java.math.BigDecimal getFullPmtInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(21); }

  public void setFullPmtInd(java.math.BigDecimal fullPmtInd) throws SQLException
  { _struct.setAttribute(21, fullPmtInd); }


  public java.math.BigDecimal getPartialPmtInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(22); }

  public void setPartialPmtInd(java.math.BigDecimal partialPmtInd) throws SQLException
  { _struct.setAttribute(22, partialPmtInd); }


  public String getMthdOfPmt() throws SQLException
  { return (String) _struct.getAttribute(23); }

  public void setMthdOfPmt(String mthdOfPmt) throws SQLException
  { _struct.setAttribute(23, mthdOfPmt); }


  public java.math.BigDecimal getCheckNum() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(24); }

  public void setCheckNum(java.math.BigDecimal checkNum) throws SQLException
  { _struct.setAttribute(24, checkNum); }


  public String getBankRoutingNum() throws SQLException
  { return (String) _struct.getAttribute(25); }

  public void setBankRoutingNum(String bankRoutingNum) throws SQLException
  { _struct.setAttribute(25, bankRoutingNum); }


  public String getBankAccountNum() throws SQLException
  { return (String) _struct.getAttribute(26); }

  public void setBankAccountNum(String bankAccountNum) throws SQLException
  { _struct.setAttribute(26, bankAccountNum); }


  public String getBankAccountTyp() throws SQLException
  { return (String) _struct.getAttribute(27); }

  public void setBankAccountTyp(String bankAccountTyp) throws SQLException
  { _struct.setAttribute(27, bankAccountTyp); }


  public java.sql.Timestamp getCheckIssueDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(28); }

  public void setCheckIssueDate(java.sql.Timestamp checkIssueDate) throws SQLException
  { _struct.setAttribute(28, checkIssueDate); }


  public java.math.BigDecimal getBouncedCheckInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(29); }

  public void setBouncedCheckInd(java.math.BigDecimal bouncedCheckInd) throws SQLException
  { _struct.setAttribute(29, bouncedCheckInd); }


  public String getCheckStatus() throws SQLException
  { return (String) _struct.getAttribute(30); }

  public void setCheckStatus(String checkStatus) throws SQLException
  { _struct.setAttribute(30, checkStatus); }


  public String getCheckReasonForHold() throws SQLException
  { return (String) _struct.getAttribute(31); }

  public void setCheckReasonForHold(String checkReasonForHold) throws SQLException
  { _struct.setAttribute(31, checkReasonForHold); }


  public String getCreditCardTyp() throws SQLException
  { return (String) _struct.getAttribute(32); }

  public void setCreditCardTyp(String creditCardTyp) throws SQLException
  { _struct.setAttribute(32, creditCardTyp); }


  public String getCreditCardNum() throws SQLException
  { return (String) _struct.getAttribute(33); }

  public void setCreditCardNum(String creditCardNum) throws SQLException
  { _struct.setAttribute(33, creditCardNum); }


  public String getCreditCardHolderName() throws SQLException
  { return (String) _struct.getAttribute(34); }

  public void setCreditCardHolderName(String creditCardHolderName) throws SQLException
  { _struct.setAttribute(34, creditCardHolderName); }


  public String getCreditCardHolderAddr1() throws SQLException
  { return (String) _struct.getAttribute(35); }

  public void setCreditCardHolderAddr1(String creditCardHolderAddr1) throws SQLException
  { _struct.setAttribute(35, creditCardHolderAddr1); }


  public String getCreditCardHolderAddr2() throws SQLException
  { return (String) _struct.getAttribute(36); }

  public void setCreditCardHolderAddr2(String creditCardHolderAddr2) throws SQLException
  { _struct.setAttribute(36, creditCardHolderAddr2); }


  public String getCreditCardHolderAptNum() throws SQLException
  { return (String) _struct.getAttribute(37); }

  public void setCreditCardHolderAptNum(String creditCardHolderAptNum) throws SQLException
  { _struct.setAttribute(37, creditCardHolderAptNum); }


  public String getCreditCardHolderCity() throws SQLException
  { return (String) _struct.getAttribute(38); }

  public void setCreditCardHolderCity(String creditCardHolderCity) throws SQLException
  { _struct.setAttribute(38, creditCardHolderCity); }


  public String getCreditCardHolderState() throws SQLException
  { return (String) _struct.getAttribute(39); }

  public void setCreditCardHolderState(String creditCardHolderState) throws SQLException
  { _struct.setAttribute(39, creditCardHolderState); }


  public String getCreditCardHolderPstlCode() throws SQLException
  { return (String) _struct.getAttribute(40); }

  public void setCreditCardHolderPstlCode(String creditCardHolderPstlCode) throws SQLException
  { _struct.setAttribute(40, creditCardHolderPstlCode); }


  public java.sql.Timestamp getCreditCardExprDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(41); }

  public void setCreditCardExprDate(java.sql.Timestamp creditCardExprDate) throws SQLException
  { _struct.setAttribute(41, creditCardExprDate); }


  public String getCreditCardCvcCode() throws SQLException
  { return (String) _struct.getAttribute(42); }

  public void setCreditCardCvcCode(String creditCardCvcCode) throws SQLException
  { _struct.setAttribute(42, creditCardCvcCode); }


  public String getCreditCardAuthCode() throws SQLException
  { return (String) _struct.getAttribute(43); }

  public void setCreditCardAuthCode(String creditCardAuthCode) throws SQLException
  { _struct.setAttribute(43, creditCardAuthCode); }


  public java.sql.Timestamp getCreditCardAuthDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(44); }

  public void setCreditCardAuthDate(java.sql.Timestamp creditCardAuthDate) throws SQLException
  { _struct.setAttribute(44, creditCardAuthDate); }


  public java.sql.Timestamp getPostedDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(45); }

  public void setPostedDate(java.sql.Timestamp postedDate) throws SQLException
  { _struct.setAttribute(45, postedDate); }


  public String getPmtProcessingMthdCode() throws SQLException
  { return (String) _struct.getAttribute(46); }

  public void setPmtProcessingMthdCode(String pmtProcessingMthdCode) throws SQLException
  { _struct.setAttribute(46, pmtProcessingMthdCode); }


  public String getBatchId() throws SQLException
  { return (String) _struct.getAttribute(47); }

  public void setBatchId(String batchId) throws SQLException
  { _struct.setAttribute(47, batchId); }


  public java.math.BigDecimal getPmtPostedInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(48); }

  public void setPmtPostedInd(java.math.BigDecimal pmtPostedInd) throws SQLException
  { _struct.setAttribute(48, pmtPostedInd); }


  public java.sql.Timestamp getPmtPostedDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(49); }

  public void setPmtPostedDate(java.sql.Timestamp pmtPostedDate) throws SQLException
  { _struct.setAttribute(49, pmtPostedDate); }


  public String getPmtPostStatusCode() throws SQLException
  { return (String) _struct.getAttribute(50); }

  public void setPmtPostStatusCode(String pmtPostStatusCode) throws SQLException
  { _struct.setAttribute(50, pmtPostStatusCode); }

}
