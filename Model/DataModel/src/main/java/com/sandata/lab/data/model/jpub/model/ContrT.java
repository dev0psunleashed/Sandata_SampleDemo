package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class ContrT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.CONTR_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,2,2,12,12,12,12,91,91,12,12,12,12,12,12,12,12,12,12,12,1,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,1,12,12,12,12,12,12,12,12,12,1,12,12,12,12,12,2,12,12,12,12,12,12,12,12,12,2,2,12,2,2,2,2,2,2,2,2,2,12,2,2,12,12,12,12,12,12,12,2,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[99];
  protected static final ContrT _ContrTFactory = new ContrT();

  public static ORADataFactory getORADataFactory()
  { return _ContrTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[99], _sqlType, _factory); }
  public ContrT()
  { _init_struct(true); }
  public ContrT(java.math.BigDecimal contrSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String tzName, String payerId, String contrId, java.sql.Timestamp contrEffDate, java.sql.Timestamp contrTermDate, String revCode, String contrDesc, String contrContName, String contrContTitle, String contrContPhone, String contrContEmail, String contrContEmailQlfr, String contrAddr1, String contrAddr2, String contrCity, String contrCounty, String contrState, String contrPstlCode, String contrZip4, String contrPhone, String contrPhoneExt, String contrPhoneQlfr, String contrPhone1, String contrPhone1Ext, String contrPhone1Qlfr, String contrFax, String contrFaxQlfr, String contrFax1, String contrFax1Qlfr, String contrEmail, String contrEmailQlfr, String contrBillToContName, String contrBillToContTitle, String contrBillToAddr1, String contrBillToAddr2, String contrBillToCity, String contrBillToState, String contrBillToPstlCode, String contrBillToZip4, String contrBillToPhone, String contrBillToPhoneExt, String contrRemitToContName, String contrRemitToContTitle, String contrRemitToAddr1, String contrRemitToAddr2, String contrRemitToCity, String contrRemitToState, String contrRemitToPstlCode, String contrRemitToZip4, String contrRemitToPhone, String contrRemitToPhoneExt, String contrWeekEndDay, java.math.BigDecimal contrActiveInd, String contrBillCode, String contrInvFmtTypName, String contrRsbmtInvFmtTypName, String contrClaimSubmFreqTypName, String contrWeekendStartDay, String contrWeekendStartTime, String contrWeekendEndDay, String contrWeekendEndTime, String contrSvcUnitName, java.math.BigDecimal contrSvcUnitEquiv, java.math.BigDecimal contrLiEquiv, String contrEdiRoutingId, java.math.BigDecimal contrEdiSubmittableInd, java.math.BigDecimal contrHoldBillingInd, java.math.BigDecimal contrSplitBillingAlwdInd, java.math.BigDecimal contrNoHolidayPayInd, java.math.BigDecimal contrEligCheckRqdInd, java.math.BigDecimal contrCdsInd, java.math.BigDecimal contrBillingUnitRoundInd, java.math.BigDecimal contrSigRqdInd, java.math.BigDecimal contrPtInsIdRqdInd, String contrApprovalTyp, java.math.BigDecimal contrCovertingCopayAmt, java.math.BigDecimal contrCopayAmt, String contrPtAsmtFreq, String contrPtSupvyVisitFreq, String contrEdiProviderLocCode, String contrEdiFmtQlfr, String contrBillTypCode, String pcpCode, String beLobId, java.math.BigDecimal contrNurseNoteRqdInd, String contrSvcingLocQlfr, String vvRndingRuleId, String contrNote) throws SQLException
  { _init_struct(true);
    setContrSk(contrSk);
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
    setContrId(contrId);
    setContrEffDate(contrEffDate);
    setContrTermDate(contrTermDate);
    setRevCode(revCode);
    setContrDesc(contrDesc);
    setContrContName(contrContName);
    setContrContTitle(contrContTitle);
    setContrContPhone(contrContPhone);
    setContrContEmail(contrContEmail);
    setContrContEmailQlfr(contrContEmailQlfr);
    setContrAddr1(contrAddr1);
    setContrAddr2(contrAddr2);
    setContrCity(contrCity);
    setContrCounty(contrCounty);
    setContrState(contrState);
    setContrPstlCode(contrPstlCode);
    setContrZip4(contrZip4);
    setContrPhone(contrPhone);
    setContrPhoneExt(contrPhoneExt);
    setContrPhoneQlfr(contrPhoneQlfr);
    setContrPhone1(contrPhone1);
    setContrPhone1Ext(contrPhone1Ext);
    setContrPhone1Qlfr(contrPhone1Qlfr);
    setContrFax(contrFax);
    setContrFaxQlfr(contrFaxQlfr);
    setContrFax1(contrFax1);
    setContrFax1Qlfr(contrFax1Qlfr);
    setContrEmail(contrEmail);
    setContrEmailQlfr(contrEmailQlfr);
    setContrBillToContName(contrBillToContName);
    setContrBillToContTitle(contrBillToContTitle);
    setContrBillToAddr1(contrBillToAddr1);
    setContrBillToAddr2(contrBillToAddr2);
    setContrBillToCity(contrBillToCity);
    setContrBillToState(contrBillToState);
    setContrBillToPstlCode(contrBillToPstlCode);
    setContrBillToZip4(contrBillToZip4);
    setContrBillToPhone(contrBillToPhone);
    setContrBillToPhoneExt(contrBillToPhoneExt);
    setContrRemitToContName(contrRemitToContName);
    setContrRemitToContTitle(contrRemitToContTitle);
    setContrRemitToAddr1(contrRemitToAddr1);
    setContrRemitToAddr2(contrRemitToAddr2);
    setContrRemitToCity(contrRemitToCity);
    setContrRemitToState(contrRemitToState);
    setContrRemitToPstlCode(contrRemitToPstlCode);
    setContrRemitToZip4(contrRemitToZip4);
    setContrRemitToPhone(contrRemitToPhone);
    setContrRemitToPhoneExt(contrRemitToPhoneExt);
    setContrWeekEndDay(contrWeekEndDay);
    setContrActiveInd(contrActiveInd);
    setContrBillCode(contrBillCode);
    setContrInvFmtTypName(contrInvFmtTypName);
    setContrRsbmtInvFmtTypName(contrRsbmtInvFmtTypName);
    setContrClaimSubmFreqTypName(contrClaimSubmFreqTypName);
    setContrWeekendStartDay(contrWeekendStartDay);
    setContrWeekendStartTime(contrWeekendStartTime);
    setContrWeekendEndDay(contrWeekendEndDay);
    setContrWeekendEndTime(contrWeekendEndTime);
    setContrSvcUnitName(contrSvcUnitName);
    setContrSvcUnitEquiv(contrSvcUnitEquiv);
    setContrLiEquiv(contrLiEquiv);
    setContrEdiRoutingId(contrEdiRoutingId);
    setContrEdiSubmittableInd(contrEdiSubmittableInd);
    setContrHoldBillingInd(contrHoldBillingInd);
    setContrSplitBillingAlwdInd(contrSplitBillingAlwdInd);
    setContrNoHolidayPayInd(contrNoHolidayPayInd);
    setContrEligCheckRqdInd(contrEligCheckRqdInd);
    setContrCdsInd(contrCdsInd);
    setContrBillingUnitRoundInd(contrBillingUnitRoundInd);
    setContrSigRqdInd(contrSigRqdInd);
    setContrPtInsIdRqdInd(contrPtInsIdRqdInd);
    setContrApprovalTyp(contrApprovalTyp);
    setContrCovertingCopayAmt(contrCovertingCopayAmt);
    setContrCopayAmt(contrCopayAmt);
    setContrPtAsmtFreq(contrPtAsmtFreq);
    setContrPtSupvyVisitFreq(contrPtSupvyVisitFreq);
    setContrEdiProviderLocCode(contrEdiProviderLocCode);
    setContrEdiFmtQlfr(contrEdiFmtQlfr);
    setContrBillTypCode(contrBillTypCode);
    setPcpCode(pcpCode);
    setBeLobId(beLobId);
    setContrNurseNoteRqdInd(contrNurseNoteRqdInd);
    setContrSvcingLocQlfr(contrSvcingLocQlfr);
    setVvRndingRuleId(vvRndingRuleId);
    setContrNote(contrNote);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(ContrT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new ContrT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getContrSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setContrSk(java.math.BigDecimal contrSk) throws SQLException
  { _struct.setAttribute(0, contrSk); }


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


  public String getContrId() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setContrId(String contrId) throws SQLException
  { _struct.setAttribute(13, contrId); }


  public java.sql.Timestamp getContrEffDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(14); }

  public void setContrEffDate(java.sql.Timestamp contrEffDate) throws SQLException
  { _struct.setAttribute(14, contrEffDate); }


  public java.sql.Timestamp getContrTermDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(15); }

  public void setContrTermDate(java.sql.Timestamp contrTermDate) throws SQLException
  { _struct.setAttribute(15, contrTermDate); }


  public String getRevCode() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setRevCode(String revCode) throws SQLException
  { _struct.setAttribute(16, revCode); }


  public String getContrDesc() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setContrDesc(String contrDesc) throws SQLException
  { _struct.setAttribute(17, contrDesc); }


  public String getContrContName() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setContrContName(String contrContName) throws SQLException
  { _struct.setAttribute(18, contrContName); }


  public String getContrContTitle() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setContrContTitle(String contrContTitle) throws SQLException
  { _struct.setAttribute(19, contrContTitle); }


  public String getContrContPhone() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setContrContPhone(String contrContPhone) throws SQLException
  { _struct.setAttribute(20, contrContPhone); }


  public String getContrContEmail() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setContrContEmail(String contrContEmail) throws SQLException
  { _struct.setAttribute(21, contrContEmail); }


  public String getContrContEmailQlfr() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setContrContEmailQlfr(String contrContEmailQlfr) throws SQLException
  { _struct.setAttribute(22, contrContEmailQlfr); }


  public String getContrAddr1() throws SQLException
  { return (String) _struct.getAttribute(23); }

  public void setContrAddr1(String contrAddr1) throws SQLException
  { _struct.setAttribute(23, contrAddr1); }


  public String getContrAddr2() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setContrAddr2(String contrAddr2) throws SQLException
  { _struct.setAttribute(24, contrAddr2); }


  public String getContrCity() throws SQLException
  { return (String) _struct.getAttribute(25); }

  public void setContrCity(String contrCity) throws SQLException
  { _struct.setAttribute(25, contrCity); }


  public String getContrCounty() throws SQLException
  { return (String) _struct.getAttribute(26); }

  public void setContrCounty(String contrCounty) throws SQLException
  { _struct.setAttribute(26, contrCounty); }


  public String getContrState() throws SQLException
  { return (String) _struct.getAttribute(27); }

  public void setContrState(String contrState) throws SQLException
  { _struct.setAttribute(27, contrState); }


  public String getContrPstlCode() throws SQLException
  { return (String) _struct.getAttribute(28); }

  public void setContrPstlCode(String contrPstlCode) throws SQLException
  { _struct.setAttribute(28, contrPstlCode); }


  public String getContrZip4() throws SQLException
  { return (String) _struct.getAttribute(29); }

  public void setContrZip4(String contrZip4) throws SQLException
  { _struct.setAttribute(29, contrZip4); }


  public String getContrPhone() throws SQLException
  { return (String) _struct.getAttribute(30); }

  public void setContrPhone(String contrPhone) throws SQLException
  { _struct.setAttribute(30, contrPhone); }


  public String getContrPhoneExt() throws SQLException
  { return (String) _struct.getAttribute(31); }

  public void setContrPhoneExt(String contrPhoneExt) throws SQLException
  { _struct.setAttribute(31, contrPhoneExt); }


  public String getContrPhoneQlfr() throws SQLException
  { return (String) _struct.getAttribute(32); }

  public void setContrPhoneQlfr(String contrPhoneQlfr) throws SQLException
  { _struct.setAttribute(32, contrPhoneQlfr); }


  public String getContrPhone1() throws SQLException
  { return (String) _struct.getAttribute(33); }

  public void setContrPhone1(String contrPhone1) throws SQLException
  { _struct.setAttribute(33, contrPhone1); }


  public String getContrPhone1Ext() throws SQLException
  { return (String) _struct.getAttribute(34); }

  public void setContrPhone1Ext(String contrPhone1Ext) throws SQLException
  { _struct.setAttribute(34, contrPhone1Ext); }


  public String getContrPhone1Qlfr() throws SQLException
  { return (String) _struct.getAttribute(35); }

  public void setContrPhone1Qlfr(String contrPhone1Qlfr) throws SQLException
  { _struct.setAttribute(35, contrPhone1Qlfr); }


  public String getContrFax() throws SQLException
  { return (String) _struct.getAttribute(36); }

  public void setContrFax(String contrFax) throws SQLException
  { _struct.setAttribute(36, contrFax); }


  public String getContrFaxQlfr() throws SQLException
  { return (String) _struct.getAttribute(37); }

  public void setContrFaxQlfr(String contrFaxQlfr) throws SQLException
  { _struct.setAttribute(37, contrFaxQlfr); }


  public String getContrFax1() throws SQLException
  { return (String) _struct.getAttribute(38); }

  public void setContrFax1(String contrFax1) throws SQLException
  { _struct.setAttribute(38, contrFax1); }


  public String getContrFax1Qlfr() throws SQLException
  { return (String) _struct.getAttribute(39); }

  public void setContrFax1Qlfr(String contrFax1Qlfr) throws SQLException
  { _struct.setAttribute(39, contrFax1Qlfr); }


  public String getContrEmail() throws SQLException
  { return (String) _struct.getAttribute(40); }

  public void setContrEmail(String contrEmail) throws SQLException
  { _struct.setAttribute(40, contrEmail); }


  public String getContrEmailQlfr() throws SQLException
  { return (String) _struct.getAttribute(41); }

  public void setContrEmailQlfr(String contrEmailQlfr) throws SQLException
  { _struct.setAttribute(41, contrEmailQlfr); }


  public String getContrBillToContName() throws SQLException
  { return (String) _struct.getAttribute(42); }

  public void setContrBillToContName(String contrBillToContName) throws SQLException
  { _struct.setAttribute(42, contrBillToContName); }


  public String getContrBillToContTitle() throws SQLException
  { return (String) _struct.getAttribute(43); }

  public void setContrBillToContTitle(String contrBillToContTitle) throws SQLException
  { _struct.setAttribute(43, contrBillToContTitle); }


  public String getContrBillToAddr1() throws SQLException
  { return (String) _struct.getAttribute(44); }

  public void setContrBillToAddr1(String contrBillToAddr1) throws SQLException
  { _struct.setAttribute(44, contrBillToAddr1); }


  public String getContrBillToAddr2() throws SQLException
  { return (String) _struct.getAttribute(45); }

  public void setContrBillToAddr2(String contrBillToAddr2) throws SQLException
  { _struct.setAttribute(45, contrBillToAddr2); }


  public String getContrBillToCity() throws SQLException
  { return (String) _struct.getAttribute(46); }

  public void setContrBillToCity(String contrBillToCity) throws SQLException
  { _struct.setAttribute(46, contrBillToCity); }


  public String getContrBillToState() throws SQLException
  { return (String) _struct.getAttribute(47); }

  public void setContrBillToState(String contrBillToState) throws SQLException
  { _struct.setAttribute(47, contrBillToState); }


  public String getContrBillToPstlCode() throws SQLException
  { return (String) _struct.getAttribute(48); }

  public void setContrBillToPstlCode(String contrBillToPstlCode) throws SQLException
  { _struct.setAttribute(48, contrBillToPstlCode); }


  public String getContrBillToZip4() throws SQLException
  { return (String) _struct.getAttribute(49); }

  public void setContrBillToZip4(String contrBillToZip4) throws SQLException
  { _struct.setAttribute(49, contrBillToZip4); }


  public String getContrBillToPhone() throws SQLException
  { return (String) _struct.getAttribute(50); }

  public void setContrBillToPhone(String contrBillToPhone) throws SQLException
  { _struct.setAttribute(50, contrBillToPhone); }


  public String getContrBillToPhoneExt() throws SQLException
  { return (String) _struct.getAttribute(51); }

  public void setContrBillToPhoneExt(String contrBillToPhoneExt) throws SQLException
  { _struct.setAttribute(51, contrBillToPhoneExt); }


  public String getContrRemitToContName() throws SQLException
  { return (String) _struct.getAttribute(52); }

  public void setContrRemitToContName(String contrRemitToContName) throws SQLException
  { _struct.setAttribute(52, contrRemitToContName); }


  public String getContrRemitToContTitle() throws SQLException
  { return (String) _struct.getAttribute(53); }

  public void setContrRemitToContTitle(String contrRemitToContTitle) throws SQLException
  { _struct.setAttribute(53, contrRemitToContTitle); }


  public String getContrRemitToAddr1() throws SQLException
  { return (String) _struct.getAttribute(54); }

  public void setContrRemitToAddr1(String contrRemitToAddr1) throws SQLException
  { _struct.setAttribute(54, contrRemitToAddr1); }


  public String getContrRemitToAddr2() throws SQLException
  { return (String) _struct.getAttribute(55); }

  public void setContrRemitToAddr2(String contrRemitToAddr2) throws SQLException
  { _struct.setAttribute(55, contrRemitToAddr2); }


  public String getContrRemitToCity() throws SQLException
  { return (String) _struct.getAttribute(56); }

  public void setContrRemitToCity(String contrRemitToCity) throws SQLException
  { _struct.setAttribute(56, contrRemitToCity); }


  public String getContrRemitToState() throws SQLException
  { return (String) _struct.getAttribute(57); }

  public void setContrRemitToState(String contrRemitToState) throws SQLException
  { _struct.setAttribute(57, contrRemitToState); }


  public String getContrRemitToPstlCode() throws SQLException
  { return (String) _struct.getAttribute(58); }

  public void setContrRemitToPstlCode(String contrRemitToPstlCode) throws SQLException
  { _struct.setAttribute(58, contrRemitToPstlCode); }


  public String getContrRemitToZip4() throws SQLException
  { return (String) _struct.getAttribute(59); }

  public void setContrRemitToZip4(String contrRemitToZip4) throws SQLException
  { _struct.setAttribute(59, contrRemitToZip4); }


  public String getContrRemitToPhone() throws SQLException
  { return (String) _struct.getAttribute(60); }

  public void setContrRemitToPhone(String contrRemitToPhone) throws SQLException
  { _struct.setAttribute(60, contrRemitToPhone); }


  public String getContrRemitToPhoneExt() throws SQLException
  { return (String) _struct.getAttribute(61); }

  public void setContrRemitToPhoneExt(String contrRemitToPhoneExt) throws SQLException
  { _struct.setAttribute(61, contrRemitToPhoneExt); }


  public String getContrWeekEndDay() throws SQLException
  { return (String) _struct.getAttribute(62); }

  public void setContrWeekEndDay(String contrWeekEndDay) throws SQLException
  { _struct.setAttribute(62, contrWeekEndDay); }


  public java.math.BigDecimal getContrActiveInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(63); }

  public void setContrActiveInd(java.math.BigDecimal contrActiveInd) throws SQLException
  { _struct.setAttribute(63, contrActiveInd); }


  public String getContrBillCode() throws SQLException
  { return (String) _struct.getAttribute(64); }

  public void setContrBillCode(String contrBillCode) throws SQLException
  { _struct.setAttribute(64, contrBillCode); }


  public String getContrInvFmtTypName() throws SQLException
  { return (String) _struct.getAttribute(65); }

  public void setContrInvFmtTypName(String contrInvFmtTypName) throws SQLException
  { _struct.setAttribute(65, contrInvFmtTypName); }


  public String getContrRsbmtInvFmtTypName() throws SQLException
  { return (String) _struct.getAttribute(66); }

  public void setContrRsbmtInvFmtTypName(String contrRsbmtInvFmtTypName) throws SQLException
  { _struct.setAttribute(66, contrRsbmtInvFmtTypName); }


  public String getContrClaimSubmFreqTypName() throws SQLException
  { return (String) _struct.getAttribute(67); }

  public void setContrClaimSubmFreqTypName(String contrClaimSubmFreqTypName) throws SQLException
  { _struct.setAttribute(67, contrClaimSubmFreqTypName); }


  public String getContrWeekendStartDay() throws SQLException
  { return (String) _struct.getAttribute(68); }

  public void setContrWeekendStartDay(String contrWeekendStartDay) throws SQLException
  { _struct.setAttribute(68, contrWeekendStartDay); }


  public String getContrWeekendStartTime() throws SQLException
  { return (String) _struct.getAttribute(69); }

  public void setContrWeekendStartTime(String contrWeekendStartTime) throws SQLException
  { _struct.setAttribute(69, contrWeekendStartTime); }


  public String getContrWeekendEndDay() throws SQLException
  { return (String) _struct.getAttribute(70); }

  public void setContrWeekendEndDay(String contrWeekendEndDay) throws SQLException
  { _struct.setAttribute(70, contrWeekendEndDay); }


  public String getContrWeekendEndTime() throws SQLException
  { return (String) _struct.getAttribute(71); }

  public void setContrWeekendEndTime(String contrWeekendEndTime) throws SQLException
  { _struct.setAttribute(71, contrWeekendEndTime); }


  public String getContrSvcUnitName() throws SQLException
  { return (String) _struct.getAttribute(72); }

  public void setContrSvcUnitName(String contrSvcUnitName) throws SQLException
  { _struct.setAttribute(72, contrSvcUnitName); }


  public java.math.BigDecimal getContrSvcUnitEquiv() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(73); }

  public void setContrSvcUnitEquiv(java.math.BigDecimal contrSvcUnitEquiv) throws SQLException
  { _struct.setAttribute(73, contrSvcUnitEquiv); }


  public java.math.BigDecimal getContrLiEquiv() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(74); }

  public void setContrLiEquiv(java.math.BigDecimal contrLiEquiv) throws SQLException
  { _struct.setAttribute(74, contrLiEquiv); }


  public String getContrEdiRoutingId() throws SQLException
  { return (String) _struct.getAttribute(75); }

  public void setContrEdiRoutingId(String contrEdiRoutingId) throws SQLException
  { _struct.setAttribute(75, contrEdiRoutingId); }


  public java.math.BigDecimal getContrEdiSubmittableInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(76); }

  public void setContrEdiSubmittableInd(java.math.BigDecimal contrEdiSubmittableInd) throws SQLException
  { _struct.setAttribute(76, contrEdiSubmittableInd); }


  public java.math.BigDecimal getContrHoldBillingInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(77); }

  public void setContrHoldBillingInd(java.math.BigDecimal contrHoldBillingInd) throws SQLException
  { _struct.setAttribute(77, contrHoldBillingInd); }


  public java.math.BigDecimal getContrSplitBillingAlwdInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(78); }

  public void setContrSplitBillingAlwdInd(java.math.BigDecimal contrSplitBillingAlwdInd) throws SQLException
  { _struct.setAttribute(78, contrSplitBillingAlwdInd); }


  public java.math.BigDecimal getContrNoHolidayPayInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(79); }

  public void setContrNoHolidayPayInd(java.math.BigDecimal contrNoHolidayPayInd) throws SQLException
  { _struct.setAttribute(79, contrNoHolidayPayInd); }


  public java.math.BigDecimal getContrEligCheckRqdInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(80); }

  public void setContrEligCheckRqdInd(java.math.BigDecimal contrEligCheckRqdInd) throws SQLException
  { _struct.setAttribute(80, contrEligCheckRqdInd); }


  public java.math.BigDecimal getContrCdsInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(81); }

  public void setContrCdsInd(java.math.BigDecimal contrCdsInd) throws SQLException
  { _struct.setAttribute(81, contrCdsInd); }


  public java.math.BigDecimal getContrBillingUnitRoundInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(82); }

  public void setContrBillingUnitRoundInd(java.math.BigDecimal contrBillingUnitRoundInd) throws SQLException
  { _struct.setAttribute(82, contrBillingUnitRoundInd); }


  public java.math.BigDecimal getContrSigRqdInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(83); }

  public void setContrSigRqdInd(java.math.BigDecimal contrSigRqdInd) throws SQLException
  { _struct.setAttribute(83, contrSigRqdInd); }


  public java.math.BigDecimal getContrPtInsIdRqdInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(84); }

  public void setContrPtInsIdRqdInd(java.math.BigDecimal contrPtInsIdRqdInd) throws SQLException
  { _struct.setAttribute(84, contrPtInsIdRqdInd); }


  public String getContrApprovalTyp() throws SQLException
  { return (String) _struct.getAttribute(85); }

  public void setContrApprovalTyp(String contrApprovalTyp) throws SQLException
  { _struct.setAttribute(85, contrApprovalTyp); }


  public java.math.BigDecimal getContrCovertingCopayAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(86); }

  public void setContrCovertingCopayAmt(java.math.BigDecimal contrCovertingCopayAmt) throws SQLException
  { _struct.setAttribute(86, contrCovertingCopayAmt); }


  public java.math.BigDecimal getContrCopayAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(87); }

  public void setContrCopayAmt(java.math.BigDecimal contrCopayAmt) throws SQLException
  { _struct.setAttribute(87, contrCopayAmt); }


  public String getContrPtAsmtFreq() throws SQLException
  { return (String) _struct.getAttribute(88); }

  public void setContrPtAsmtFreq(String contrPtAsmtFreq) throws SQLException
  { _struct.setAttribute(88, contrPtAsmtFreq); }


  public String getContrPtSupvyVisitFreq() throws SQLException
  { return (String) _struct.getAttribute(89); }

  public void setContrPtSupvyVisitFreq(String contrPtSupvyVisitFreq) throws SQLException
  { _struct.setAttribute(89, contrPtSupvyVisitFreq); }


  public String getContrEdiProviderLocCode() throws SQLException
  { return (String) _struct.getAttribute(90); }

  public void setContrEdiProviderLocCode(String contrEdiProviderLocCode) throws SQLException
  { _struct.setAttribute(90, contrEdiProviderLocCode); }


  public String getContrEdiFmtQlfr() throws SQLException
  { return (String) _struct.getAttribute(91); }

  public void setContrEdiFmtQlfr(String contrEdiFmtQlfr) throws SQLException
  { _struct.setAttribute(91, contrEdiFmtQlfr); }


  public String getContrBillTypCode() throws SQLException
  { return (String) _struct.getAttribute(92); }

  public void setContrBillTypCode(String contrBillTypCode) throws SQLException
  { _struct.setAttribute(92, contrBillTypCode); }


  public String getPcpCode() throws SQLException
  { return (String) _struct.getAttribute(93); }

  public void setPcpCode(String pcpCode) throws SQLException
  { _struct.setAttribute(93, pcpCode); }


  public String getBeLobId() throws SQLException
  { return (String) _struct.getAttribute(94); }

  public void setBeLobId(String beLobId) throws SQLException
  { _struct.setAttribute(94, beLobId); }


  public java.math.BigDecimal getContrNurseNoteRqdInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(95); }

  public void setContrNurseNoteRqdInd(java.math.BigDecimal contrNurseNoteRqdInd) throws SQLException
  { _struct.setAttribute(95, contrNurseNoteRqdInd); }


  public String getContrSvcingLocQlfr() throws SQLException
  { return (String) _struct.getAttribute(96); }

  public void setContrSvcingLocQlfr(String contrSvcingLocQlfr) throws SQLException
  { _struct.setAttribute(96, contrSvcingLocQlfr); }


  public String getVvRndingRuleId() throws SQLException
  { return (String) _struct.getAttribute(97); }

  public void setVvRndingRuleId(String vvRndingRuleId) throws SQLException
  { _struct.setAttribute(97, vvRndingRuleId); }

  public String getContrNote() throws SQLException
  { return (String) _struct.getAttribute(98); }

  public void setContrNote(String contrNote) throws SQLException
  { _struct.setAttribute(98, contrNote); }

}
