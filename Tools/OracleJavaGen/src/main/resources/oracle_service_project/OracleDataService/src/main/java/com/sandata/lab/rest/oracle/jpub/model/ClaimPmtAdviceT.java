package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class ClaimPmtAdviceT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.CLAIM_PMT_ADVICE_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,12,12,2,12,2,12,12,12,12,12,12,2,91,12,2,12,12,12,12,12,12,12,12,12,12,12,12,2,2,2,12,12,12,12,12,12,12,12,91,12,91,12,12,2,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[48];
  protected static final ClaimPmtAdviceT _ClaimPmtAdviceTFactory = new ClaimPmtAdviceT();

  public static ORADataFactory getORADataFactory()
  { return _ClaimPmtAdviceTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[48], _sqlType, _factory); }
  public ClaimPmtAdviceT()
  { _init_struct(true); }
  public ClaimPmtAdviceT(java.math.BigDecimal claimPmtAdviceSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String recCreatedBy, String recUpdatedBy, java.math.BigDecimal changeVersionId, String beId, java.math.BigDecimal billingDetSk, String ediSubmitterId, String ediReceiverId, String claimPmtAdviceControlNum, String payerId, String payerName, String checkNum, java.math.BigDecimal checkInd, java.sql.Timestamp checkDate, String eftTxnId, java.math.BigDecimal pmtAmt, String providerId, String providerIdQlfr, String providerName, String providerAddr1, String providerAddr2, String providerCity, String providerState, String providerPstlCode, String providerIdOther, String providerIdOtherQlfr, String invNum, String payerClaimStatusCode, java.math.BigDecimal claimChargeTotalAmt, java.math.BigDecimal claimPmtTotalAmt, java.math.BigDecimal claimPtRespAmt, String crn, String claimFacilityTypCode, String invSubmTypCode, String claimEntNameQlfr, String claimEntFirstName, String claimEntLastName, String claimEntIdTypQlfr, String claimEntIdNum, java.sql.Timestamp claimStartDate, String claimStartDateQlfr, java.sql.Timestamp claimEndDate, String claimEndDateQlfr, String claimAmtQlfr, java.math.BigDecimal claimAmt, String pcpCode) throws SQLException
  { _init_struct(true);
    setClaimPmtAdviceSk(claimPmtAdviceSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setBillingDetSk(billingDetSk);
    setEdiSubmitterId(ediSubmitterId);
    setEdiReceiverId(ediReceiverId);
    setClaimPmtAdviceControlNum(claimPmtAdviceControlNum);
    setPayerId(payerId);
    setPayerName(payerName);
    setCheckNum(checkNum);
    setCheckInd(checkInd);
    setCheckDate(checkDate);
    setEftTxnId(eftTxnId);
    setPmtAmt(pmtAmt);
    setProviderId(providerId);
    setProviderIdQlfr(providerIdQlfr);
    setProviderName(providerName);
    setProviderAddr1(providerAddr1);
    setProviderAddr2(providerAddr2);
    setProviderCity(providerCity);
    setProviderState(providerState);
    setProviderPstlCode(providerPstlCode);
    setProviderIdOther(providerIdOther);
    setProviderIdOtherQlfr(providerIdOtherQlfr);
    setInvNum(invNum);
    setPayerClaimStatusCode(payerClaimStatusCode);
    setClaimChargeTotalAmt(claimChargeTotalAmt);
    setClaimPmtTotalAmt(claimPmtTotalAmt);
    setClaimPtRespAmt(claimPtRespAmt);
    setCrn(crn);
    setClaimFacilityTypCode(claimFacilityTypCode);
    setInvSubmTypCode(invSubmTypCode);
    setClaimEntNameQlfr(claimEntNameQlfr);
    setClaimEntFirstName(claimEntFirstName);
    setClaimEntLastName(claimEntLastName);
    setClaimEntIdTypQlfr(claimEntIdTypQlfr);
    setClaimEntIdNum(claimEntIdNum);
    setClaimStartDate(claimStartDate);
    setClaimStartDateQlfr(claimStartDateQlfr);
    setClaimEndDate(claimEndDate);
    setClaimEndDateQlfr(claimEndDateQlfr);
    setClaimAmtQlfr(claimAmtQlfr);
    setClaimAmt(claimAmt);
    setPcpCode(pcpCode);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(ClaimPmtAdviceT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new ClaimPmtAdviceT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getClaimPmtAdviceSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setClaimPmtAdviceSk(java.math.BigDecimal claimPmtAdviceSk) throws SQLException
  { _struct.setAttribute(0, claimPmtAdviceSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public String getRecCreatedBy() throws SQLException
  { return (String) _struct.getAttribute(3); }

  public void setRecCreatedBy(String recCreatedBy) throws SQLException
  { _struct.setAttribute(3, recCreatedBy); }


  public String getRecUpdatedBy() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setRecUpdatedBy(String recUpdatedBy) throws SQLException
  { _struct.setAttribute(4, recUpdatedBy); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(5); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(5, changeVersionId); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(6, beId); }


  public java.math.BigDecimal getBillingDetSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(7); }

  public void setBillingDetSk(java.math.BigDecimal billingDetSk) throws SQLException
  { _struct.setAttribute(7, billingDetSk); }


  public String getEdiSubmitterId() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setEdiSubmitterId(String ediSubmitterId) throws SQLException
  { _struct.setAttribute(8, ediSubmitterId); }


  public String getEdiReceiverId() throws SQLException
  { return (String) _struct.getAttribute(9); }

  public void setEdiReceiverId(String ediReceiverId) throws SQLException
  { _struct.setAttribute(9, ediReceiverId); }


  public String getClaimPmtAdviceControlNum() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setClaimPmtAdviceControlNum(String claimPmtAdviceControlNum) throws SQLException
  { _struct.setAttribute(10, claimPmtAdviceControlNum); }


  public String getPayerId() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setPayerId(String payerId) throws SQLException
  { _struct.setAttribute(11, payerId); }


  public String getPayerName() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setPayerName(String payerName) throws SQLException
  { _struct.setAttribute(12, payerName); }


  public String getCheckNum() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setCheckNum(String checkNum) throws SQLException
  { _struct.setAttribute(13, checkNum); }


  public java.math.BigDecimal getCheckInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(14); }

  public void setCheckInd(java.math.BigDecimal checkInd) throws SQLException
  { _struct.setAttribute(14, checkInd); }


  public java.sql.Timestamp getCheckDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(15); }

  public void setCheckDate(java.sql.Timestamp checkDate) throws SQLException
  { _struct.setAttribute(15, checkDate); }


  public String getEftTxnId() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setEftTxnId(String eftTxnId) throws SQLException
  { _struct.setAttribute(16, eftTxnId); }


  public java.math.BigDecimal getPmtAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(17); }

  public void setPmtAmt(java.math.BigDecimal pmtAmt) throws SQLException
  { _struct.setAttribute(17, pmtAmt); }


  public String getProviderId() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setProviderId(String providerId) throws SQLException
  { _struct.setAttribute(18, providerId); }


  public String getProviderIdQlfr() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setProviderIdQlfr(String providerIdQlfr) throws SQLException
  { _struct.setAttribute(19, providerIdQlfr); }


  public String getProviderName() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setProviderName(String providerName) throws SQLException
  { _struct.setAttribute(20, providerName); }


  public String getProviderAddr1() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setProviderAddr1(String providerAddr1) throws SQLException
  { _struct.setAttribute(21, providerAddr1); }


  public String getProviderAddr2() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setProviderAddr2(String providerAddr2) throws SQLException
  { _struct.setAttribute(22, providerAddr2); }


  public String getProviderCity() throws SQLException
  { return (String) _struct.getAttribute(23); }

  public void setProviderCity(String providerCity) throws SQLException
  { _struct.setAttribute(23, providerCity); }


  public String getProviderState() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setProviderState(String providerState) throws SQLException
  { _struct.setAttribute(24, providerState); }


  public String getProviderPstlCode() throws SQLException
  { return (String) _struct.getAttribute(25); }

  public void setProviderPstlCode(String providerPstlCode) throws SQLException
  { _struct.setAttribute(25, providerPstlCode); }


  public String getProviderIdOther() throws SQLException
  { return (String) _struct.getAttribute(26); }

  public void setProviderIdOther(String providerIdOther) throws SQLException
  { _struct.setAttribute(26, providerIdOther); }


  public String getProviderIdOtherQlfr() throws SQLException
  { return (String) _struct.getAttribute(27); }

  public void setProviderIdOtherQlfr(String providerIdOtherQlfr) throws SQLException
  { _struct.setAttribute(27, providerIdOtherQlfr); }


  public String getInvNum() throws SQLException
  { return (String) _struct.getAttribute(28); }

  public void setInvNum(String invNum) throws SQLException
  { _struct.setAttribute(28, invNum); }


  public String getPayerClaimStatusCode() throws SQLException
  { return (String) _struct.getAttribute(29); }

  public void setPayerClaimStatusCode(String payerClaimStatusCode) throws SQLException
  { _struct.setAttribute(29, payerClaimStatusCode); }


  public java.math.BigDecimal getClaimChargeTotalAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(30); }

  public void setClaimChargeTotalAmt(java.math.BigDecimal claimChargeTotalAmt) throws SQLException
  { _struct.setAttribute(30, claimChargeTotalAmt); }


  public java.math.BigDecimal getClaimPmtTotalAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(31); }

  public void setClaimPmtTotalAmt(java.math.BigDecimal claimPmtTotalAmt) throws SQLException
  { _struct.setAttribute(31, claimPmtTotalAmt); }


  public java.math.BigDecimal getClaimPtRespAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(32); }

  public void setClaimPtRespAmt(java.math.BigDecimal claimPtRespAmt) throws SQLException
  { _struct.setAttribute(32, claimPtRespAmt); }


  public String getCrn() throws SQLException
  { return (String) _struct.getAttribute(33); }

  public void setCrn(String crn) throws SQLException
  { _struct.setAttribute(33, crn); }


  public String getClaimFacilityTypCode() throws SQLException
  { return (String) _struct.getAttribute(34); }

  public void setClaimFacilityTypCode(String claimFacilityTypCode) throws SQLException
  { _struct.setAttribute(34, claimFacilityTypCode); }


  public String getInvSubmTypCode() throws SQLException
  { return (String) _struct.getAttribute(35); }

  public void setInvSubmTypCode(String invSubmTypCode) throws SQLException
  { _struct.setAttribute(35, invSubmTypCode); }


  public String getClaimEntNameQlfr() throws SQLException
  { return (String) _struct.getAttribute(36); }

  public void setClaimEntNameQlfr(String claimEntNameQlfr) throws SQLException
  { _struct.setAttribute(36, claimEntNameQlfr); }


  public String getClaimEntFirstName() throws SQLException
  { return (String) _struct.getAttribute(37); }

  public void setClaimEntFirstName(String claimEntFirstName) throws SQLException
  { _struct.setAttribute(37, claimEntFirstName); }


  public String getClaimEntLastName() throws SQLException
  { return (String) _struct.getAttribute(38); }

  public void setClaimEntLastName(String claimEntLastName) throws SQLException
  { _struct.setAttribute(38, claimEntLastName); }


  public String getClaimEntIdTypQlfr() throws SQLException
  { return (String) _struct.getAttribute(39); }

  public void setClaimEntIdTypQlfr(String claimEntIdTypQlfr) throws SQLException
  { _struct.setAttribute(39, claimEntIdTypQlfr); }


  public String getClaimEntIdNum() throws SQLException
  { return (String) _struct.getAttribute(40); }

  public void setClaimEntIdNum(String claimEntIdNum) throws SQLException
  { _struct.setAttribute(40, claimEntIdNum); }


  public java.sql.Timestamp getClaimStartDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(41); }

  public void setClaimStartDate(java.sql.Timestamp claimStartDate) throws SQLException
  { _struct.setAttribute(41, claimStartDate); }


  public String getClaimStartDateQlfr() throws SQLException
  { return (String) _struct.getAttribute(42); }

  public void setClaimStartDateQlfr(String claimStartDateQlfr) throws SQLException
  { _struct.setAttribute(42, claimStartDateQlfr); }


  public java.sql.Timestamp getClaimEndDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(43); }

  public void setClaimEndDate(java.sql.Timestamp claimEndDate) throws SQLException
  { _struct.setAttribute(43, claimEndDate); }


  public String getClaimEndDateQlfr() throws SQLException
  { return (String) _struct.getAttribute(44); }

  public void setClaimEndDateQlfr(String claimEndDateQlfr) throws SQLException
  { _struct.setAttribute(44, claimEndDateQlfr); }


  public String getClaimAmtQlfr() throws SQLException
  { return (String) _struct.getAttribute(45); }

  public void setClaimAmtQlfr(String claimAmtQlfr) throws SQLException
  { _struct.setAttribute(45, claimAmtQlfr); }


  public java.math.BigDecimal getClaimAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(46); }

  public void setClaimAmt(java.math.BigDecimal claimAmt) throws SQLException
  { _struct.setAttribute(46, claimAmt); }


  public String getPcpCode() throws SQLException
  { return (String) _struct.getAttribute(47); }

  public void setPcpCode(String pcpCode) throws SQLException
  { _struct.setAttribute(47, pcpCode); }

}
