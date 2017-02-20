package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class PayerT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PAYER_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,2,2,12,12,12,12,12,12,91,91,12,12,12,12,12,12,12,12,12,12,1,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,1,12,12,12,12,12,12,12,12,12,1,12,12,12,12,12,12,2,12,12,12,12,12,12,12,12,12,2,2,2,2,2,2,2,2,2,2,2,12,12,12,12,12,12,12,12,12,12,12,12,12,2,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[103];
  protected static final PayerT _PayerTFactory = new PayerT();

  public static ORADataFactory getORADataFactory()
  { return _PayerTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[103], _sqlType, _factory); }
  public PayerT()
  { _init_struct(true); }
  public PayerT(java.math.BigDecimal payerSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String tzName, String payerId, String payerName, String payerTypQlfr, String payerSubTypCode, java.sql.Timestamp payerEffDate, java.sql.Timestamp payerTermDate, String payerContFirstName, String payerContLastName, String payerContTitle, String payerContPhone, String payerContEmail, String payerContEmailQlfr, String payerAddr1, String payerAddr2, String payerCity, String payerCounty, String payerState, String payerPstlCode, String payerZip4, String payerPhone, String payerPhoneExt, String payerPhoneQlfr, String payerPhone1, String payerPhone1Ext, String payerPhone1Qlfr, String payerFax, String payerFaxQlfr, String payerFax1, String payerFax1Qlfr, String payerEmail, String payerEmailQlfr, String payerBillToContName, String payerBillToContTitle, String payerBillToAddr1, String payerBillToAddr2, String payerBillToCity, String payerBillToState, String payerBillToPstlCode, String payerBillToZip4, String payerBillToPhone, String payerBillToPhoneExt, String payerRemitToContName, String payerRemitToContTitle, String payerRemitToAddr1, String payerRemitToAddr2, String payerRemitToCity, String payerRemitToState, String payerRemitToPstlCode, String payerRemitToZip4, String payerRemitToPhone, String payerRemitToPhoneExt, String payerEin, String payerWeekEndDay, java.math.BigDecimal payerActiveInd, String payerBillCode, String payerInvFmtTypName, String payerRsbmtInvFmtTypName, String payerClaimSubmFreqTypName, String payerWeekendStartDay, String payerWeekendStartTime, String payerWeekendEndDay, String payerWeekendEndTime, String payerEdiRoutingId, java.math.BigDecimal payerSvcUnitEquiv, java.math.BigDecimal payerLiEquiv, java.math.BigDecimal payerEdiSubmittableInd, java.math.BigDecimal payerHoldBillingInd, java.math.BigDecimal payerSplitBillingAlwdInd, java.math.BigDecimal payerNoHolidayPayInd, java.math.BigDecimal payerEligCheckRqdInd, java.math.BigDecimal payerCdsInd, java.math.BigDecimal payerBillingUnitRoundInd, java.math.BigDecimal payerSigRqdInd, java.math.BigDecimal payerPtInsIdRqdInd, String payerApprovalTyp, String payerEdiSubmitterId, String payerEdiSubmitterName, String payerEdiReceiverId, String payerEdiReceiverName, String payerEdiReceiverIdQlfr, String payerEdiGrpSubmitterId, String payerEdiProviderCommlId, String payerBillTypCode, String ptDischargeStatusCode, String ptAdmSrcCode, String ptAdmTypCode, String pcpCode, java.math.BigDecimal payerNurseNoteRqdInd, String payerEdiDateFmtQlfr, String payerSvcingLocQlfr, String claimFilingIndCode) throws SQLException
  { _init_struct(true);
    setPayerSk(payerSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonMemo(changeReasonMemo);
    setCurrRecInd(currRecInd);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setTzName(tzName);
    setPayerId(payerId);
    setPayerName(payerName);
    setPayerTypQlfr(payerTypQlfr);
    setPayerSubTypCode(payerSubTypCode);
    setPayerEffDate(payerEffDate);
    setPayerTermDate(payerTermDate);
    setPayerContFirstName(payerContFirstName);
    setPayerContLastName(payerContLastName);
    setPayerContTitle(payerContTitle);
    setPayerContPhone(payerContPhone);
    setPayerContEmail(payerContEmail);
    setPayerContEmailQlfr(payerContEmailQlfr);
    setPayerAddr1(payerAddr1);
    setPayerAddr2(payerAddr2);
    setPayerCity(payerCity);
    setPayerCounty(payerCounty);
    setPayerState(payerState);
    setPayerPstlCode(payerPstlCode);
    setPayerZip4(payerZip4);
    setPayerPhone(payerPhone);
    setPayerPhoneExt(payerPhoneExt);
    setPayerPhoneQlfr(payerPhoneQlfr);
    setPayerPhone1(payerPhone1);
    setPayerPhone1Ext(payerPhone1Ext);
    setPayerPhone1Qlfr(payerPhone1Qlfr);
    setPayerFax(payerFax);
    setPayerFaxQlfr(payerFaxQlfr);
    setPayerFax1(payerFax1);
    setPayerFax1Qlfr(payerFax1Qlfr);
    setPayerEmail(payerEmail);
    setPayerEmailQlfr(payerEmailQlfr);
    setPayerBillToContName(payerBillToContName);
    setPayerBillToContTitle(payerBillToContTitle);
    setPayerBillToAddr1(payerBillToAddr1);
    setPayerBillToAddr2(payerBillToAddr2);
    setPayerBillToCity(payerBillToCity);
    setPayerBillToState(payerBillToState);
    setPayerBillToPstlCode(payerBillToPstlCode);
    setPayerBillToZip4(payerBillToZip4);
    setPayerBillToPhone(payerBillToPhone);
    setPayerBillToPhoneExt(payerBillToPhoneExt);
    setPayerRemitToContName(payerRemitToContName);
    setPayerRemitToContTitle(payerRemitToContTitle);
    setPayerRemitToAddr1(payerRemitToAddr1);
    setPayerRemitToAddr2(payerRemitToAddr2);
    setPayerRemitToCity(payerRemitToCity);
    setPayerRemitToState(payerRemitToState);
    setPayerRemitToPstlCode(payerRemitToPstlCode);
    setPayerRemitToZip4(payerRemitToZip4);
    setPayerRemitToPhone(payerRemitToPhone);
    setPayerRemitToPhoneExt(payerRemitToPhoneExt);
    setPayerEin(payerEin);
    setPayerWeekEndDay(payerWeekEndDay);
    setPayerActiveInd(payerActiveInd);
    setPayerBillCode(payerBillCode);
    setPayerInvFmtTypName(payerInvFmtTypName);
    setPayerRsbmtInvFmtTypName(payerRsbmtInvFmtTypName);
    setPayerClaimSubmFreqTypName(payerClaimSubmFreqTypName);
    setPayerWeekendStartDay(payerWeekendStartDay);
    setPayerWeekendStartTime(payerWeekendStartTime);
    setPayerWeekendEndDay(payerWeekendEndDay);
    setPayerWeekendEndTime(payerWeekendEndTime);
    setPayerEdiRoutingId(payerEdiRoutingId);
    setPayerSvcUnitEquiv(payerSvcUnitEquiv);
    setPayerLiEquiv(payerLiEquiv);
    setPayerEdiSubmittableInd(payerEdiSubmittableInd);
    setPayerHoldBillingInd(payerHoldBillingInd);
    setPayerSplitBillingAlwdInd(payerSplitBillingAlwdInd);
    setPayerNoHolidayPayInd(payerNoHolidayPayInd);
    setPayerEligCheckRqdInd(payerEligCheckRqdInd);
    setPayerCdsInd(payerCdsInd);
    setPayerBillingUnitRoundInd(payerBillingUnitRoundInd);
    setPayerSigRqdInd(payerSigRqdInd);
    setPayerPtInsIdRqdInd(payerPtInsIdRqdInd);
    setPayerApprovalTyp(payerApprovalTyp);
    setPayerEdiSubmitterId(payerEdiSubmitterId);
    setPayerEdiSubmitterName(payerEdiSubmitterName);
    setPayerEdiReceiverId(payerEdiReceiverId);
    setPayerEdiReceiverName(payerEdiReceiverName);
    setPayerEdiReceiverIdQlfr(payerEdiReceiverIdQlfr);
    setPayerEdiGrpSubmitterId(payerEdiGrpSubmitterId);
    setPayerEdiProviderCommlId(payerEdiProviderCommlId);
    setPayerBillTypCode(payerBillTypCode);
    setPtDischargeStatusCode(ptDischargeStatusCode);
    setPtAdmSrcCode(ptAdmSrcCode);
    setPtAdmTypCode(ptAdmTypCode);
    setPcpCode(pcpCode);
    setPayerNurseNoteRqdInd(payerNurseNoteRqdInd);
    setPayerEdiDateFmtQlfr(payerEdiDateFmtQlfr);
    setPayerSvcingLocQlfr(payerSvcingLocQlfr);
    setClaimFilingIndCode(claimFilingIndCode);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PayerT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PayerT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPayerSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPayerSk(java.math.BigDecimal payerSk) throws SQLException
  { _struct.setAttribute(0, payerSk); }


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


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(10, beId); }


  public String getTzName() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setTzName(String tzName) throws SQLException
  { _struct.setAttribute(11, tzName); }


  public String getPayerId() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setPayerId(String payerId) throws SQLException
  { _struct.setAttribute(12, payerId); }


  public String getPayerName() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setPayerName(String payerName) throws SQLException
  { _struct.setAttribute(13, payerName); }


  public String getPayerTypQlfr() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setPayerTypQlfr(String payerTypQlfr) throws SQLException
  { _struct.setAttribute(14, payerTypQlfr); }


  public String getPayerSubTypCode() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setPayerSubTypCode(String payerSubTypCode) throws SQLException
  { _struct.setAttribute(15, payerSubTypCode); }


  public java.sql.Timestamp getPayerEffDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(16); }

  public void setPayerEffDate(java.sql.Timestamp payerEffDate) throws SQLException
  { _struct.setAttribute(16, payerEffDate); }


  public java.sql.Timestamp getPayerTermDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(17); }

  public void setPayerTermDate(java.sql.Timestamp payerTermDate) throws SQLException
  { _struct.setAttribute(17, payerTermDate); }


  public String getPayerContFirstName() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setPayerContFirstName(String payerContFirstName) throws SQLException
  { _struct.setAttribute(18, payerContFirstName); }


  public String getPayerContLastName() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setPayerContLastName(String payerContLastName) throws SQLException
  { _struct.setAttribute(19, payerContLastName); }


  public String getPayerContTitle() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setPayerContTitle(String payerContTitle) throws SQLException
  { _struct.setAttribute(20, payerContTitle); }


  public String getPayerContPhone() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setPayerContPhone(String payerContPhone) throws SQLException
  { _struct.setAttribute(21, payerContPhone); }


  public String getPayerContEmail() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setPayerContEmail(String payerContEmail) throws SQLException
  { _struct.setAttribute(22, payerContEmail); }


  public String getPayerContEmailQlfr() throws SQLException
  { return (String) _struct.getAttribute(23); }

  public void setPayerContEmailQlfr(String payerContEmailQlfr) throws SQLException
  { _struct.setAttribute(23, payerContEmailQlfr); }


  public String getPayerAddr1() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setPayerAddr1(String payerAddr1) throws SQLException
  { _struct.setAttribute(24, payerAddr1); }


  public String getPayerAddr2() throws SQLException
  { return (String) _struct.getAttribute(25); }

  public void setPayerAddr2(String payerAddr2) throws SQLException
  { _struct.setAttribute(25, payerAddr2); }


  public String getPayerCity() throws SQLException
  { return (String) _struct.getAttribute(26); }

  public void setPayerCity(String payerCity) throws SQLException
  { _struct.setAttribute(26, payerCity); }


  public String getPayerCounty() throws SQLException
  { return (String) _struct.getAttribute(27); }

  public void setPayerCounty(String payerCounty) throws SQLException
  { _struct.setAttribute(27, payerCounty); }


  public String getPayerState() throws SQLException
  { return (String) _struct.getAttribute(28); }

  public void setPayerState(String payerState) throws SQLException
  { _struct.setAttribute(28, payerState); }


  public String getPayerPstlCode() throws SQLException
  { return (String) _struct.getAttribute(29); }

  public void setPayerPstlCode(String payerPstlCode) throws SQLException
  { _struct.setAttribute(29, payerPstlCode); }


  public String getPayerZip4() throws SQLException
  { return (String) _struct.getAttribute(30); }

  public void setPayerZip4(String payerZip4) throws SQLException
  { _struct.setAttribute(30, payerZip4); }


  public String getPayerPhone() throws SQLException
  { return (String) _struct.getAttribute(31); }

  public void setPayerPhone(String payerPhone) throws SQLException
  { _struct.setAttribute(31, payerPhone); }


  public String getPayerPhoneExt() throws SQLException
  { return (String) _struct.getAttribute(32); }

  public void setPayerPhoneExt(String payerPhoneExt) throws SQLException
  { _struct.setAttribute(32, payerPhoneExt); }


  public String getPayerPhoneQlfr() throws SQLException
  { return (String) _struct.getAttribute(33); }

  public void setPayerPhoneQlfr(String payerPhoneQlfr) throws SQLException
  { _struct.setAttribute(33, payerPhoneQlfr); }


  public String getPayerPhone1() throws SQLException
  { return (String) _struct.getAttribute(34); }

  public void setPayerPhone1(String payerPhone1) throws SQLException
  { _struct.setAttribute(34, payerPhone1); }


  public String getPayerPhone1Ext() throws SQLException
  { return (String) _struct.getAttribute(35); }

  public void setPayerPhone1Ext(String payerPhone1Ext) throws SQLException
  { _struct.setAttribute(35, payerPhone1Ext); }


  public String getPayerPhone1Qlfr() throws SQLException
  { return (String) _struct.getAttribute(36); }

  public void setPayerPhone1Qlfr(String payerPhone1Qlfr) throws SQLException
  { _struct.setAttribute(36, payerPhone1Qlfr); }


  public String getPayerFax() throws SQLException
  { return (String) _struct.getAttribute(37); }

  public void setPayerFax(String payerFax) throws SQLException
  { _struct.setAttribute(37, payerFax); }


  public String getPayerFaxQlfr() throws SQLException
  { return (String) _struct.getAttribute(38); }

  public void setPayerFaxQlfr(String payerFaxQlfr) throws SQLException
  { _struct.setAttribute(38, payerFaxQlfr); }


  public String getPayerFax1() throws SQLException
  { return (String) _struct.getAttribute(39); }

  public void setPayerFax1(String payerFax1) throws SQLException
  { _struct.setAttribute(39, payerFax1); }


  public String getPayerFax1Qlfr() throws SQLException
  { return (String) _struct.getAttribute(40); }

  public void setPayerFax1Qlfr(String payerFax1Qlfr) throws SQLException
  { _struct.setAttribute(40, payerFax1Qlfr); }


  public String getPayerEmail() throws SQLException
  { return (String) _struct.getAttribute(41); }

  public void setPayerEmail(String payerEmail) throws SQLException
  { _struct.setAttribute(41, payerEmail); }


  public String getPayerEmailQlfr() throws SQLException
  { return (String) _struct.getAttribute(42); }

  public void setPayerEmailQlfr(String payerEmailQlfr) throws SQLException
  { _struct.setAttribute(42, payerEmailQlfr); }


  public String getPayerBillToContName() throws SQLException
  { return (String) _struct.getAttribute(43); }

  public void setPayerBillToContName(String payerBillToContName) throws SQLException
  { _struct.setAttribute(43, payerBillToContName); }


  public String getPayerBillToContTitle() throws SQLException
  { return (String) _struct.getAttribute(44); }

  public void setPayerBillToContTitle(String payerBillToContTitle) throws SQLException
  { _struct.setAttribute(44, payerBillToContTitle); }


  public String getPayerBillToAddr1() throws SQLException
  { return (String) _struct.getAttribute(45); }

  public void setPayerBillToAddr1(String payerBillToAddr1) throws SQLException
  { _struct.setAttribute(45, payerBillToAddr1); }


  public String getPayerBillToAddr2() throws SQLException
  { return (String) _struct.getAttribute(46); }

  public void setPayerBillToAddr2(String payerBillToAddr2) throws SQLException
  { _struct.setAttribute(46, payerBillToAddr2); }


  public String getPayerBillToCity() throws SQLException
  { return (String) _struct.getAttribute(47); }

  public void setPayerBillToCity(String payerBillToCity) throws SQLException
  { _struct.setAttribute(47, payerBillToCity); }


  public String getPayerBillToState() throws SQLException
  { return (String) _struct.getAttribute(48); }

  public void setPayerBillToState(String payerBillToState) throws SQLException
  { _struct.setAttribute(48, payerBillToState); }


  public String getPayerBillToPstlCode() throws SQLException
  { return (String) _struct.getAttribute(49); }

  public void setPayerBillToPstlCode(String payerBillToPstlCode) throws SQLException
  { _struct.setAttribute(49, payerBillToPstlCode); }


  public String getPayerBillToZip4() throws SQLException
  { return (String) _struct.getAttribute(50); }

  public void setPayerBillToZip4(String payerBillToZip4) throws SQLException
  { _struct.setAttribute(50, payerBillToZip4); }


  public String getPayerBillToPhone() throws SQLException
  { return (String) _struct.getAttribute(51); }

  public void setPayerBillToPhone(String payerBillToPhone) throws SQLException
  { _struct.setAttribute(51, payerBillToPhone); }


  public String getPayerBillToPhoneExt() throws SQLException
  { return (String) _struct.getAttribute(52); }

  public void setPayerBillToPhoneExt(String payerBillToPhoneExt) throws SQLException
  { _struct.setAttribute(52, payerBillToPhoneExt); }


  public String getPayerRemitToContName() throws SQLException
  { return (String) _struct.getAttribute(53); }

  public void setPayerRemitToContName(String payerRemitToContName) throws SQLException
  { _struct.setAttribute(53, payerRemitToContName); }


  public String getPayerRemitToContTitle() throws SQLException
  { return (String) _struct.getAttribute(54); }

  public void setPayerRemitToContTitle(String payerRemitToContTitle) throws SQLException
  { _struct.setAttribute(54, payerRemitToContTitle); }


  public String getPayerRemitToAddr1() throws SQLException
  { return (String) _struct.getAttribute(55); }

  public void setPayerRemitToAddr1(String payerRemitToAddr1) throws SQLException
  { _struct.setAttribute(55, payerRemitToAddr1); }


  public String getPayerRemitToAddr2() throws SQLException
  { return (String) _struct.getAttribute(56); }

  public void setPayerRemitToAddr2(String payerRemitToAddr2) throws SQLException
  { _struct.setAttribute(56, payerRemitToAddr2); }


  public String getPayerRemitToCity() throws SQLException
  { return (String) _struct.getAttribute(57); }

  public void setPayerRemitToCity(String payerRemitToCity) throws SQLException
  { _struct.setAttribute(57, payerRemitToCity); }


  public String getPayerRemitToState() throws SQLException
  { return (String) _struct.getAttribute(58); }

  public void setPayerRemitToState(String payerRemitToState) throws SQLException
  { _struct.setAttribute(58, payerRemitToState); }


  public String getPayerRemitToPstlCode() throws SQLException
  { return (String) _struct.getAttribute(59); }

  public void setPayerRemitToPstlCode(String payerRemitToPstlCode) throws SQLException
  { _struct.setAttribute(59, payerRemitToPstlCode); }


  public String getPayerRemitToZip4() throws SQLException
  { return (String) _struct.getAttribute(60); }

  public void setPayerRemitToZip4(String payerRemitToZip4) throws SQLException
  { _struct.setAttribute(60, payerRemitToZip4); }


  public String getPayerRemitToPhone() throws SQLException
  { return (String) _struct.getAttribute(61); }

  public void setPayerRemitToPhone(String payerRemitToPhone) throws SQLException
  { _struct.setAttribute(61, payerRemitToPhone); }


  public String getPayerRemitToPhoneExt() throws SQLException
  { return (String) _struct.getAttribute(62); }

  public void setPayerRemitToPhoneExt(String payerRemitToPhoneExt) throws SQLException
  { _struct.setAttribute(62, payerRemitToPhoneExt); }


  public String getPayerEin() throws SQLException
  { return (String) _struct.getAttribute(63); }

  public void setPayerEin(String payerEin) throws SQLException
  { _struct.setAttribute(63, payerEin); }


  public String getPayerWeekEndDay() throws SQLException
  { return (String) _struct.getAttribute(64); }

  public void setPayerWeekEndDay(String payerWeekEndDay) throws SQLException
  { _struct.setAttribute(64, payerWeekEndDay); }


  public java.math.BigDecimal getPayerActiveInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(65); }

  public void setPayerActiveInd(java.math.BigDecimal payerActiveInd) throws SQLException
  { _struct.setAttribute(65, payerActiveInd); }


  public String getPayerBillCode() throws SQLException
  { return (String) _struct.getAttribute(66); }

  public void setPayerBillCode(String payerBillCode) throws SQLException
  { _struct.setAttribute(66, payerBillCode); }


  public String getPayerInvFmtTypName() throws SQLException
  { return (String) _struct.getAttribute(67); }

  public void setPayerInvFmtTypName(String payerInvFmtTypName) throws SQLException
  { _struct.setAttribute(67, payerInvFmtTypName); }


  public String getPayerRsbmtInvFmtTypName() throws SQLException
  { return (String) _struct.getAttribute(68); }

  public void setPayerRsbmtInvFmtTypName(String payerRsbmtInvFmtTypName) throws SQLException
  { _struct.setAttribute(68, payerRsbmtInvFmtTypName); }


  public String getPayerClaimSubmFreqTypName() throws SQLException
  { return (String) _struct.getAttribute(69); }

  public void setPayerClaimSubmFreqTypName(String payerClaimSubmFreqTypName) throws SQLException
  { _struct.setAttribute(69, payerClaimSubmFreqTypName); }


  public String getPayerWeekendStartDay() throws SQLException
  { return (String) _struct.getAttribute(70); }

  public void setPayerWeekendStartDay(String payerWeekendStartDay) throws SQLException
  { _struct.setAttribute(70, payerWeekendStartDay); }


  public String getPayerWeekendStartTime() throws SQLException
  { return (String) _struct.getAttribute(71); }

  public void setPayerWeekendStartTime(String payerWeekendStartTime) throws SQLException
  { _struct.setAttribute(71, payerWeekendStartTime); }


  public String getPayerWeekendEndDay() throws SQLException
  { return (String) _struct.getAttribute(72); }

  public void setPayerWeekendEndDay(String payerWeekendEndDay) throws SQLException
  { _struct.setAttribute(72, payerWeekendEndDay); }


  public String getPayerWeekendEndTime() throws SQLException
  { return (String) _struct.getAttribute(73); }

  public void setPayerWeekendEndTime(String payerWeekendEndTime) throws SQLException
  { _struct.setAttribute(73, payerWeekendEndTime); }


  public String getPayerEdiRoutingId() throws SQLException
  { return (String) _struct.getAttribute(74); }

  public void setPayerEdiRoutingId(String payerEdiRoutingId) throws SQLException
  { _struct.setAttribute(74, payerEdiRoutingId); }


  public java.math.BigDecimal getPayerSvcUnitEquiv() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(75); }

  public void setPayerSvcUnitEquiv(java.math.BigDecimal payerSvcUnitEquiv) throws SQLException
  { _struct.setAttribute(75, payerSvcUnitEquiv); }


  public java.math.BigDecimal getPayerLiEquiv() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(76); }

  public void setPayerLiEquiv(java.math.BigDecimal payerLiEquiv) throws SQLException
  { _struct.setAttribute(76, payerLiEquiv); }


  public java.math.BigDecimal getPayerEdiSubmittableInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(77); }

  public void setPayerEdiSubmittableInd(java.math.BigDecimal payerEdiSubmittableInd) throws SQLException
  { _struct.setAttribute(77, payerEdiSubmittableInd); }


  public java.math.BigDecimal getPayerHoldBillingInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(78); }

  public void setPayerHoldBillingInd(java.math.BigDecimal payerHoldBillingInd) throws SQLException
  { _struct.setAttribute(78, payerHoldBillingInd); }


  public java.math.BigDecimal getPayerSplitBillingAlwdInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(79); }

  public void setPayerSplitBillingAlwdInd(java.math.BigDecimal payerSplitBillingAlwdInd) throws SQLException
  { _struct.setAttribute(79, payerSplitBillingAlwdInd); }


  public java.math.BigDecimal getPayerNoHolidayPayInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(80); }

  public void setPayerNoHolidayPayInd(java.math.BigDecimal payerNoHolidayPayInd) throws SQLException
  { _struct.setAttribute(80, payerNoHolidayPayInd); }


  public java.math.BigDecimal getPayerEligCheckRqdInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(81); }

  public void setPayerEligCheckRqdInd(java.math.BigDecimal payerEligCheckRqdInd) throws SQLException
  { _struct.setAttribute(81, payerEligCheckRqdInd); }


  public java.math.BigDecimal getPayerCdsInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(82); }

  public void setPayerCdsInd(java.math.BigDecimal payerCdsInd) throws SQLException
  { _struct.setAttribute(82, payerCdsInd); }


  public java.math.BigDecimal getPayerBillingUnitRoundInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(83); }

  public void setPayerBillingUnitRoundInd(java.math.BigDecimal payerBillingUnitRoundInd) throws SQLException
  { _struct.setAttribute(83, payerBillingUnitRoundInd); }


  public java.math.BigDecimal getPayerSigRqdInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(84); }

  public void setPayerSigRqdInd(java.math.BigDecimal payerSigRqdInd) throws SQLException
  { _struct.setAttribute(84, payerSigRqdInd); }


  public java.math.BigDecimal getPayerPtInsIdRqdInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(85); }

  public void setPayerPtInsIdRqdInd(java.math.BigDecimal payerPtInsIdRqdInd) throws SQLException
  { _struct.setAttribute(85, payerPtInsIdRqdInd); }


  public String getPayerApprovalTyp() throws SQLException
  { return (String) _struct.getAttribute(86); }

  public void setPayerApprovalTyp(String payerApprovalTyp) throws SQLException
  { _struct.setAttribute(86, payerApprovalTyp); }


  public String getPayerEdiSubmitterId() throws SQLException
  { return (String) _struct.getAttribute(87); }

  public void setPayerEdiSubmitterId(String payerEdiSubmitterId) throws SQLException
  { _struct.setAttribute(87, payerEdiSubmitterId); }


  public String getPayerEdiSubmitterName() throws SQLException
  { return (String) _struct.getAttribute(88); }

  public void setPayerEdiSubmitterName(String payerEdiSubmitterName) throws SQLException
  { _struct.setAttribute(88, payerEdiSubmitterName); }


  public String getPayerEdiReceiverId() throws SQLException
  { return (String) _struct.getAttribute(89); }

  public void setPayerEdiReceiverId(String payerEdiReceiverId) throws SQLException
  { _struct.setAttribute(89, payerEdiReceiverId); }


  public String getPayerEdiReceiverName() throws SQLException
  { return (String) _struct.getAttribute(90); }

  public void setPayerEdiReceiverName(String payerEdiReceiverName) throws SQLException
  { _struct.setAttribute(90, payerEdiReceiverName); }


  public String getPayerEdiReceiverIdQlfr() throws SQLException
  { return (String) _struct.getAttribute(91); }

  public void setPayerEdiReceiverIdQlfr(String payerEdiReceiverIdQlfr) throws SQLException
  { _struct.setAttribute(91, payerEdiReceiverIdQlfr); }


  public String getPayerEdiGrpSubmitterId() throws SQLException
  { return (String) _struct.getAttribute(92); }

  public void setPayerEdiGrpSubmitterId(String payerEdiGrpSubmitterId) throws SQLException
  { _struct.setAttribute(92, payerEdiGrpSubmitterId); }


  public String getPayerEdiProviderCommlId() throws SQLException
  { return (String) _struct.getAttribute(93); }

  public void setPayerEdiProviderCommlId(String payerEdiProviderCommlId) throws SQLException
  { _struct.setAttribute(93, payerEdiProviderCommlId); }


  public String getPayerBillTypCode() throws SQLException
  { return (String) _struct.getAttribute(94); }

  public void setPayerBillTypCode(String payerBillTypCode) throws SQLException
  { _struct.setAttribute(94, payerBillTypCode); }


  public String getPtDischargeStatusCode() throws SQLException
  { return (String) _struct.getAttribute(95); }

  public void setPtDischargeStatusCode(String ptDischargeStatusCode) throws SQLException
  { _struct.setAttribute(95, ptDischargeStatusCode); }


  public String getPtAdmSrcCode() throws SQLException
  { return (String) _struct.getAttribute(96); }

  public void setPtAdmSrcCode(String ptAdmSrcCode) throws SQLException
  { _struct.setAttribute(96, ptAdmSrcCode); }


  public String getPtAdmTypCode() throws SQLException
  { return (String) _struct.getAttribute(97); }

  public void setPtAdmTypCode(String ptAdmTypCode) throws SQLException
  { _struct.setAttribute(97, ptAdmTypCode); }


  public String getPcpCode() throws SQLException
  { return (String) _struct.getAttribute(98); }

  public void setPcpCode(String pcpCode) throws SQLException
  { _struct.setAttribute(98, pcpCode); }


  public java.math.BigDecimal getPayerNurseNoteRqdInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(99); }

  public void setPayerNurseNoteRqdInd(java.math.BigDecimal payerNurseNoteRqdInd) throws SQLException
  { _struct.setAttribute(99, payerNurseNoteRqdInd); }


  public String getPayerEdiDateFmtQlfr() throws SQLException
  { return (String) _struct.getAttribute(100); }

  public void setPayerEdiDateFmtQlfr(String payerEdiDateFmtQlfr) throws SQLException
  { _struct.setAttribute(100, payerEdiDateFmtQlfr); }


  public String getPayerSvcingLocQlfr() throws SQLException
  { return (String) _struct.getAttribute(101); }

  public void setPayerSvcingLocQlfr(String payerSvcingLocQlfr) throws SQLException
  { _struct.setAttribute(101, payerSvcingLocQlfr); }


  public String getClaimFilingIndCode() throws SQLException
  { return (String) _struct.getAttribute(102); }

  public void setClaimFilingIndCode(String claimFilingIndCode) throws SQLException
  { _struct.setAttribute(102, claimFilingIndCode); }

}
