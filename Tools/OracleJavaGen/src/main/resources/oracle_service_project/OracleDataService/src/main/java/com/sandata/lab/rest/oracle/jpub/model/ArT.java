package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class ArT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.AR_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,12,12,12,12,2,12,12,2,12,12,2,2,2,12,12,12,12,12,12,12,12,12,91,91,91,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,2,2,12,2,12,12,12,12,2,12,2,2,2,12,12,12,12,12,12,12,12,91,12,12,12,12,12,12,12,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[75];
  protected static final ArT _ArTFactory = new ArT();

  public static ORADataFactory getORADataFactory()
  { return _ArTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[75], _sqlType, _factory); }
  public ArT()
  { _init_struct(true); }
  public ArT(java.math.BigDecimal arSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonCode, String changeReasonMemo, java.math.BigDecimal changeVersionId, String beLobId, String beLocId, java.math.BigDecimal arTxnBatchDetSk, String arTxnCode, String arRemitCode, java.math.BigDecimal arTxnAmt, java.math.BigDecimal arTxnTotalUnit, java.math.BigDecimal arTxnUnitRate, String beId, String payerId, String contrId, String authId, String ptId, String ptInsIdNum, String invNum, String invStatusCode, String invSubmTypCode, java.sql.Timestamp invStartDate, java.sql.Timestamp invEndDate, java.sql.Timestamp invDate, String invTypQlfr, String invFmtTypName, String icdDxCodeRevisionQlfr, String icdDxCodePrmy, String icdDxCode2, String icdDxCode3, String icdDxCode4, String icdDxCode5, String icdDxCode6, String icdDxCode7, String icdDxCode8, String icdDxCode9, String icdDxCode10, String icdDxCode11, String icdDxCode12, String refingHcpNpi, String renderProfnlNpi, java.math.BigDecimal invTotalAmt, java.math.BigDecimal invTotalUnit, String invBillTypCode, java.math.BigDecimal invManualOverrideInd, String userName, String userGuid, String memo, String svcUnitName, java.math.BigDecimal rateAmt, String invDetId, java.math.BigDecimal invDetTotalUnit, java.math.BigDecimal invDetTotalAmt, java.math.BigDecimal invDetSplitBillingAlwdInd, String vendorName, String svcName, String billingCode, String mdfr1Code, String mdfr2Code, String mdfr3Code, String mdfr4Code, String revCode, java.sql.Timestamp invDetDate, String invSubmStatus, String invDetStatusCode, String rateTypName, String rateQlfrCode, String arNoteTypCode, String arNote, String crn, java.math.BigDecimal arUnappliedBalanceInd) throws SQLException
  { _init_struct(true);
    setArSk(arSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonCode(changeReasonCode);
    setChangeReasonMemo(changeReasonMemo);
    setChangeVersionId(changeVersionId);
    setBeLobId(beLobId);
    setBeLocId(beLocId);
    setArTxnBatchDetSk(arTxnBatchDetSk);
    setArTxnCode(arTxnCode);
    setArRemitCode(arRemitCode);
    setArTxnAmt(arTxnAmt);
    setArTxnTotalUnit(arTxnTotalUnit);
    setArTxnUnitRate(arTxnUnitRate);
    setBeId(beId);
    setPayerId(payerId);
    setContrId(contrId);
    setAuthId(authId);
    setPtId(ptId);
    setPtInsIdNum(ptInsIdNum);
    setInvNum(invNum);
    setInvStatusCode(invStatusCode);
    setInvSubmTypCode(invSubmTypCode);
    setInvStartDate(invStartDate);
    setInvEndDate(invEndDate);
    setInvDate(invDate);
    setInvTypQlfr(invTypQlfr);
    setInvFmtTypName(invFmtTypName);
    setIcdDxCodeRevisionQlfr(icdDxCodeRevisionQlfr);
    setIcdDxCodePrmy(icdDxCodePrmy);
    setIcdDxCode2(icdDxCode2);
    setIcdDxCode3(icdDxCode3);
    setIcdDxCode4(icdDxCode4);
    setIcdDxCode5(icdDxCode5);
    setIcdDxCode6(icdDxCode6);
    setIcdDxCode7(icdDxCode7);
    setIcdDxCode8(icdDxCode8);
    setIcdDxCode9(icdDxCode9);
    setIcdDxCode10(icdDxCode10);
    setIcdDxCode11(icdDxCode11);
    setIcdDxCode12(icdDxCode12);
    setRefingHcpNpi(refingHcpNpi);
    setRenderProfnlNpi(renderProfnlNpi);
    setInvTotalAmt(invTotalAmt);
    setInvTotalUnit(invTotalUnit);
    setInvBillTypCode(invBillTypCode);
    setInvManualOverrideInd(invManualOverrideInd);
    setUserName(userName);
    setUserGuid(userGuid);
    setMemo(memo);
    setSvcUnitName(svcUnitName);
    setRateAmt(rateAmt);
    setInvDetId(invDetId);
    setInvDetTotalUnit(invDetTotalUnit);
    setInvDetTotalAmt(invDetTotalAmt);
    setInvDetSplitBillingAlwdInd(invDetSplitBillingAlwdInd);
    setVendorName(vendorName);
    setSvcName(svcName);
    setBillingCode(billingCode);
    setMdfr1Code(mdfr1Code);
    setMdfr2Code(mdfr2Code);
    setMdfr3Code(mdfr3Code);
    setMdfr4Code(mdfr4Code);
    setRevCode(revCode);
    setInvDetDate(invDetDate);
    setInvSubmStatus(invSubmStatus);
    setInvDetStatusCode(invDetStatusCode);
    setRateTypName(rateTypName);
    setRateQlfrCode(rateQlfrCode);
    setArNoteTypCode(arNoteTypCode);
    setArNote(arNote);
    setCrn(crn);
    setArUnappliedBalanceInd(arUnappliedBalanceInd);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(ArT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new ArT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getArSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setArSk(java.math.BigDecimal arSk) throws SQLException
  { _struct.setAttribute(0, arSk); }


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


  public String getChangeReasonCode() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setChangeReasonCode(String changeReasonCode) throws SQLException
  { _struct.setAttribute(5, changeReasonCode); }


  public String getChangeReasonMemo() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setChangeReasonMemo(String changeReasonMemo) throws SQLException
  { _struct.setAttribute(6, changeReasonMemo); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(7); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(7, changeVersionId); }


  public String getBeLobId() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setBeLobId(String beLobId) throws SQLException
  { _struct.setAttribute(8, beLobId); }


  public String getBeLocId() throws SQLException
  { return (String) _struct.getAttribute(9); }

  public void setBeLocId(String beLocId) throws SQLException
  { _struct.setAttribute(9, beLocId); }


  public java.math.BigDecimal getArTxnBatchDetSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(10); }

  public void setArTxnBatchDetSk(java.math.BigDecimal arTxnBatchDetSk) throws SQLException
  { _struct.setAttribute(10, arTxnBatchDetSk); }


  public String getArTxnCode() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setArTxnCode(String arTxnCode) throws SQLException
  { _struct.setAttribute(11, arTxnCode); }


  public String getArRemitCode() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setArRemitCode(String arRemitCode) throws SQLException
  { _struct.setAttribute(12, arRemitCode); }


  public java.math.BigDecimal getArTxnAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(13); }

  public void setArTxnAmt(java.math.BigDecimal arTxnAmt) throws SQLException
  { _struct.setAttribute(13, arTxnAmt); }


  public java.math.BigDecimal getArTxnTotalUnit() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(14); }

  public void setArTxnTotalUnit(java.math.BigDecimal arTxnTotalUnit) throws SQLException
  { _struct.setAttribute(14, arTxnTotalUnit); }


  public java.math.BigDecimal getArTxnUnitRate() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(15); }

  public void setArTxnUnitRate(java.math.BigDecimal arTxnUnitRate) throws SQLException
  { _struct.setAttribute(15, arTxnUnitRate); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(16, beId); }


  public String getPayerId() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setPayerId(String payerId) throws SQLException
  { _struct.setAttribute(17, payerId); }


  public String getContrId() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setContrId(String contrId) throws SQLException
  { _struct.setAttribute(18, contrId); }


  public String getAuthId() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setAuthId(String authId) throws SQLException
  { _struct.setAttribute(19, authId); }


  public String getPtId() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setPtId(String ptId) throws SQLException
  { _struct.setAttribute(20, ptId); }


  public String getPtInsIdNum() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setPtInsIdNum(String ptInsIdNum) throws SQLException
  { _struct.setAttribute(21, ptInsIdNum); }


  public String getInvNum() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setInvNum(String invNum) throws SQLException
  { _struct.setAttribute(22, invNum); }


  public String getInvStatusCode() throws SQLException
  { return (String) _struct.getAttribute(23); }

  public void setInvStatusCode(String invStatusCode) throws SQLException
  { _struct.setAttribute(23, invStatusCode); }


  public String getInvSubmTypCode() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setInvSubmTypCode(String invSubmTypCode) throws SQLException
  { _struct.setAttribute(24, invSubmTypCode); }


  public java.sql.Timestamp getInvStartDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(25); }

  public void setInvStartDate(java.sql.Timestamp invStartDate) throws SQLException
  { _struct.setAttribute(25, invStartDate); }


  public java.sql.Timestamp getInvEndDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(26); }

  public void setInvEndDate(java.sql.Timestamp invEndDate) throws SQLException
  { _struct.setAttribute(26, invEndDate); }


  public java.sql.Timestamp getInvDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(27); }

  public void setInvDate(java.sql.Timestamp invDate) throws SQLException
  { _struct.setAttribute(27, invDate); }


  public String getInvTypQlfr() throws SQLException
  { return (String) _struct.getAttribute(28); }

  public void setInvTypQlfr(String invTypQlfr) throws SQLException
  { _struct.setAttribute(28, invTypQlfr); }


  public String getInvFmtTypName() throws SQLException
  { return (String) _struct.getAttribute(29); }

  public void setInvFmtTypName(String invFmtTypName) throws SQLException
  { _struct.setAttribute(29, invFmtTypName); }


  public String getIcdDxCodeRevisionQlfr() throws SQLException
  { return (String) _struct.getAttribute(30); }

  public void setIcdDxCodeRevisionQlfr(String icdDxCodeRevisionQlfr) throws SQLException
  { _struct.setAttribute(30, icdDxCodeRevisionQlfr); }


  public String getIcdDxCodePrmy() throws SQLException
  { return (String) _struct.getAttribute(31); }

  public void setIcdDxCodePrmy(String icdDxCodePrmy) throws SQLException
  { _struct.setAttribute(31, icdDxCodePrmy); }


  public String getIcdDxCode2() throws SQLException
  { return (String) _struct.getAttribute(32); }

  public void setIcdDxCode2(String icdDxCode2) throws SQLException
  { _struct.setAttribute(32, icdDxCode2); }


  public String getIcdDxCode3() throws SQLException
  { return (String) _struct.getAttribute(33); }

  public void setIcdDxCode3(String icdDxCode3) throws SQLException
  { _struct.setAttribute(33, icdDxCode3); }


  public String getIcdDxCode4() throws SQLException
  { return (String) _struct.getAttribute(34); }

  public void setIcdDxCode4(String icdDxCode4) throws SQLException
  { _struct.setAttribute(34, icdDxCode4); }


  public String getIcdDxCode5() throws SQLException
  { return (String) _struct.getAttribute(35); }

  public void setIcdDxCode5(String icdDxCode5) throws SQLException
  { _struct.setAttribute(35, icdDxCode5); }


  public String getIcdDxCode6() throws SQLException
  { return (String) _struct.getAttribute(36); }

  public void setIcdDxCode6(String icdDxCode6) throws SQLException
  { _struct.setAttribute(36, icdDxCode6); }


  public String getIcdDxCode7() throws SQLException
  { return (String) _struct.getAttribute(37); }

  public void setIcdDxCode7(String icdDxCode7) throws SQLException
  { _struct.setAttribute(37, icdDxCode7); }


  public String getIcdDxCode8() throws SQLException
  { return (String) _struct.getAttribute(38); }

  public void setIcdDxCode8(String icdDxCode8) throws SQLException
  { _struct.setAttribute(38, icdDxCode8); }


  public String getIcdDxCode9() throws SQLException
  { return (String) _struct.getAttribute(39); }

  public void setIcdDxCode9(String icdDxCode9) throws SQLException
  { _struct.setAttribute(39, icdDxCode9); }


  public String getIcdDxCode10() throws SQLException
  { return (String) _struct.getAttribute(40); }

  public void setIcdDxCode10(String icdDxCode10) throws SQLException
  { _struct.setAttribute(40, icdDxCode10); }


  public String getIcdDxCode11() throws SQLException
  { return (String) _struct.getAttribute(41); }

  public void setIcdDxCode11(String icdDxCode11) throws SQLException
  { _struct.setAttribute(41, icdDxCode11); }


  public String getIcdDxCode12() throws SQLException
  { return (String) _struct.getAttribute(42); }

  public void setIcdDxCode12(String icdDxCode12) throws SQLException
  { _struct.setAttribute(42, icdDxCode12); }


  public String getRefingHcpNpi() throws SQLException
  { return (String) _struct.getAttribute(43); }

  public void setRefingHcpNpi(String refingHcpNpi) throws SQLException
  { _struct.setAttribute(43, refingHcpNpi); }


  public String getRenderProfnlNpi() throws SQLException
  { return (String) _struct.getAttribute(44); }

  public void setRenderProfnlNpi(String renderProfnlNpi) throws SQLException
  { _struct.setAttribute(44, renderProfnlNpi); }


  public java.math.BigDecimal getInvTotalAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(45); }

  public void setInvTotalAmt(java.math.BigDecimal invTotalAmt) throws SQLException
  { _struct.setAttribute(45, invTotalAmt); }


  public java.math.BigDecimal getInvTotalUnit() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(46); }

  public void setInvTotalUnit(java.math.BigDecimal invTotalUnit) throws SQLException
  { _struct.setAttribute(46, invTotalUnit); }


  public String getInvBillTypCode() throws SQLException
  { return (String) _struct.getAttribute(47); }

  public void setInvBillTypCode(String invBillTypCode) throws SQLException
  { _struct.setAttribute(47, invBillTypCode); }


  public java.math.BigDecimal getInvManualOverrideInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(48); }

  public void setInvManualOverrideInd(java.math.BigDecimal invManualOverrideInd) throws SQLException
  { _struct.setAttribute(48, invManualOverrideInd); }


  public String getUserName() throws SQLException
  { return (String) _struct.getAttribute(49); }

  public void setUserName(String userName) throws SQLException
  { _struct.setAttribute(49, userName); }


  public String getUserGuid() throws SQLException
  { return (String) _struct.getAttribute(50); }

  public void setUserGuid(String userGuid) throws SQLException
  { _struct.setAttribute(50, userGuid); }


  public String getMemo() throws SQLException
  { return (String) _struct.getAttribute(51); }

  public void setMemo(String memo) throws SQLException
  { _struct.setAttribute(51, memo); }


  public String getSvcUnitName() throws SQLException
  { return (String) _struct.getAttribute(52); }

  public void setSvcUnitName(String svcUnitName) throws SQLException
  { _struct.setAttribute(52, svcUnitName); }


  public java.math.BigDecimal getRateAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(53); }

  public void setRateAmt(java.math.BigDecimal rateAmt) throws SQLException
  { _struct.setAttribute(53, rateAmt); }


  public String getInvDetId() throws SQLException
  { return (String) _struct.getAttribute(54); }

  public void setInvDetId(String invDetId) throws SQLException
  { _struct.setAttribute(54, invDetId); }


  public java.math.BigDecimal getInvDetTotalUnit() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(55); }

  public void setInvDetTotalUnit(java.math.BigDecimal invDetTotalUnit) throws SQLException
  { _struct.setAttribute(55, invDetTotalUnit); }


  public java.math.BigDecimal getInvDetTotalAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(56); }

  public void setInvDetTotalAmt(java.math.BigDecimal invDetTotalAmt) throws SQLException
  { _struct.setAttribute(56, invDetTotalAmt); }


  public java.math.BigDecimal getInvDetSplitBillingAlwdInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(57); }

  public void setInvDetSplitBillingAlwdInd(java.math.BigDecimal invDetSplitBillingAlwdInd) throws SQLException
  { _struct.setAttribute(57, invDetSplitBillingAlwdInd); }


  public String getVendorName() throws SQLException
  { return (String) _struct.getAttribute(58); }

  public void setVendorName(String vendorName) throws SQLException
  { _struct.setAttribute(58, vendorName); }


  public String getSvcName() throws SQLException
  { return (String) _struct.getAttribute(59); }

  public void setSvcName(String svcName) throws SQLException
  { _struct.setAttribute(59, svcName); }


  public String getBillingCode() throws SQLException
  { return (String) _struct.getAttribute(60); }

  public void setBillingCode(String billingCode) throws SQLException
  { _struct.setAttribute(60, billingCode); }


  public String getMdfr1Code() throws SQLException
  { return (String) _struct.getAttribute(61); }

  public void setMdfr1Code(String mdfr1Code) throws SQLException
  { _struct.setAttribute(61, mdfr1Code); }


  public String getMdfr2Code() throws SQLException
  { return (String) _struct.getAttribute(62); }

  public void setMdfr2Code(String mdfr2Code) throws SQLException
  { _struct.setAttribute(62, mdfr2Code); }


  public String getMdfr3Code() throws SQLException
  { return (String) _struct.getAttribute(63); }

  public void setMdfr3Code(String mdfr3Code) throws SQLException
  { _struct.setAttribute(63, mdfr3Code); }


  public String getMdfr4Code() throws SQLException
  { return (String) _struct.getAttribute(64); }

  public void setMdfr4Code(String mdfr4Code) throws SQLException
  { _struct.setAttribute(64, mdfr4Code); }


  public String getRevCode() throws SQLException
  { return (String) _struct.getAttribute(65); }

  public void setRevCode(String revCode) throws SQLException
  { _struct.setAttribute(65, revCode); }


  public java.sql.Timestamp getInvDetDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(66); }

  public void setInvDetDate(java.sql.Timestamp invDetDate) throws SQLException
  { _struct.setAttribute(66, invDetDate); }


  public String getInvSubmStatus() throws SQLException
  { return (String) _struct.getAttribute(67); }

  public void setInvSubmStatus(String invSubmStatus) throws SQLException
  { _struct.setAttribute(67, invSubmStatus); }


  public String getInvDetStatusCode() throws SQLException
  { return (String) _struct.getAttribute(68); }

  public void setInvDetStatusCode(String invDetStatusCode) throws SQLException
  { _struct.setAttribute(68, invDetStatusCode); }


  public String getRateTypName() throws SQLException
  { return (String) _struct.getAttribute(69); }

  public void setRateTypName(String rateTypName) throws SQLException
  { _struct.setAttribute(69, rateTypName); }


  public String getRateQlfrCode() throws SQLException
  { return (String) _struct.getAttribute(70); }

  public void setRateQlfrCode(String rateQlfrCode) throws SQLException
  { _struct.setAttribute(70, rateQlfrCode); }


  public String getArNoteTypCode() throws SQLException
  { return (String) _struct.getAttribute(71); }

  public void setArNoteTypCode(String arNoteTypCode) throws SQLException
  { _struct.setAttribute(71, arNoteTypCode); }


  public String getArNote() throws SQLException
  { return (String) _struct.getAttribute(72); }

  public void setArNote(String arNote) throws SQLException
  { _struct.setAttribute(72, arNote); }


  public String getCrn() throws SQLException
  { return (String) _struct.getAttribute(73); }

  public void setCrn(String crn) throws SQLException
  { _struct.setAttribute(73, crn); }


  public java.math.BigDecimal getArUnappliedBalanceInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(74); }

  public void setArUnappliedBalanceInd(java.math.BigDecimal arUnappliedBalanceInd) throws SQLException
  { _struct.setAttribute(74, arUnappliedBalanceInd); }

}
