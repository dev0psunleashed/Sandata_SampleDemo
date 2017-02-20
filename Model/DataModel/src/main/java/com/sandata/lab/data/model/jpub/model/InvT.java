package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class InvT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.INV_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,12,2,2,12,12,12,12,12,12,12,12,12,12,12,91,91,91,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,2,2,12,2,12,12,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[51];
  protected static final InvT _InvTFactory = new InvT();

  public static ORADataFactory getORADataFactory()
  { return _InvTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[51], _sqlType, _factory); }
  public InvT()
  { _init_struct(true); }
  public InvT(java.math.BigDecimal invSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonCode, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String beLocId, String beLobId, String payerId, String contrId, String authId, String ptId, String ptInsIdNum, String invNum, String invStatusCode, String invSubmTypCode, java.sql.Timestamp invStartDate, java.sql.Timestamp invEndDate, java.sql.Timestamp invDate, String invTypQlfr, String invFmtTypName, String icdDxCodeRevisionQlfr, String icdDxCodePrmy, String icdDxCode2, String icdDxCode3, String icdDxCode4, String icdDxCode5, String icdDxCode6, String icdDxCode7, String icdDxCode8, String icdDxCode9, String icdDxCode10, String icdDxCode11, String icdDxCode12, String renderHcpNpi, String refingProfnlNpi, java.math.BigDecimal invTotalAmt, java.math.BigDecimal invTotalUnit, String invBillTypCode, java.math.BigDecimal invManualOverrideInd, String userName, String userGuid, String memo, String crn, String claimFilingIndCode) throws SQLException
  { _init_struct(true);
    setInvSk(invSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonCode(changeReasonCode);
    setChangeReasonMemo(changeReasonMemo);
    setCurrRecInd(currRecInd);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setBeLocId(beLocId);
    setBeLobId(beLobId);
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
    setRenderHcpNpi(renderHcpNpi);
    setRefingProfnlNpi(refingProfnlNpi);
    setInvTotalAmt(invTotalAmt);
    setInvTotalUnit(invTotalUnit);
    setInvBillTypCode(invBillTypCode);
    setInvManualOverrideInd(invManualOverrideInd);
    setUserName(userName);
    setUserGuid(userGuid);
    setMemo(memo);
    setCrn(crn);
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
  protected ORAData create(InvT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new InvT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getInvSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setInvSk(java.math.BigDecimal invSk) throws SQLException
  { _struct.setAttribute(0, invSk); }


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


  public String getChangeReasonCode() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setChangeReasonCode(String changeReasonCode) throws SQLException
  { _struct.setAttribute(7, changeReasonCode); }


  public String getChangeReasonMemo() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setChangeReasonMemo(String changeReasonMemo) throws SQLException
  { _struct.setAttribute(8, changeReasonMemo); }


  public java.math.BigDecimal getCurrRecInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(9); }

  public void setCurrRecInd(java.math.BigDecimal currRecInd) throws SQLException
  { _struct.setAttribute(9, currRecInd); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(10); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(10, changeVersionId); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(11, beId); }


  public String getBeLocId() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setBeLocId(String beLocId) throws SQLException
  { _struct.setAttribute(12, beLocId); }


  public String getBeLobId() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setBeLobId(String beLobId) throws SQLException
  { _struct.setAttribute(13, beLobId); }


  public String getPayerId() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setPayerId(String payerId) throws SQLException
  { _struct.setAttribute(14, payerId); }


  public String getContrId() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setContrId(String contrId) throws SQLException
  { _struct.setAttribute(15, contrId); }


  public String getAuthId() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setAuthId(String authId) throws SQLException
  { _struct.setAttribute(16, authId); }


  public String getPtId() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setPtId(String ptId) throws SQLException
  { _struct.setAttribute(17, ptId); }


  public String getPtInsIdNum() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setPtInsIdNum(String ptInsIdNum) throws SQLException
  { _struct.setAttribute(18, ptInsIdNum); }


  public String getInvNum() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setInvNum(String invNum) throws SQLException
  { _struct.setAttribute(19, invNum); }


  public String getInvStatusCode() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setInvStatusCode(String invStatusCode) throws SQLException
  { _struct.setAttribute(20, invStatusCode); }


  public String getInvSubmTypCode() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setInvSubmTypCode(String invSubmTypCode) throws SQLException
  { _struct.setAttribute(21, invSubmTypCode); }


  public java.sql.Timestamp getInvStartDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(22); }

  public void setInvStartDate(java.sql.Timestamp invStartDate) throws SQLException
  { _struct.setAttribute(22, invStartDate); }


  public java.sql.Timestamp getInvEndDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(23); }

  public void setInvEndDate(java.sql.Timestamp invEndDate) throws SQLException
  { _struct.setAttribute(23, invEndDate); }


  public java.sql.Timestamp getInvDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(24); }

  public void setInvDate(java.sql.Timestamp invDate) throws SQLException
  { _struct.setAttribute(24, invDate); }


  public String getInvTypQlfr() throws SQLException
  { return (String) _struct.getAttribute(25); }

  public void setInvTypQlfr(String invTypQlfr) throws SQLException
  { _struct.setAttribute(25, invTypQlfr); }


  public String getInvFmtTypName() throws SQLException
  { return (String) _struct.getAttribute(26); }

  public void setInvFmtTypName(String invFmtTypName) throws SQLException
  { _struct.setAttribute(26, invFmtTypName); }


  public String getIcdDxCodeRevisionQlfr() throws SQLException
  { return (String) _struct.getAttribute(27); }

  public void setIcdDxCodeRevisionQlfr(String icdDxCodeRevisionQlfr) throws SQLException
  { _struct.setAttribute(27, icdDxCodeRevisionQlfr); }


  public String getIcdDxCodePrmy() throws SQLException
  { return (String) _struct.getAttribute(28); }

  public void setIcdDxCodePrmy(String icdDxCodePrmy) throws SQLException
  { _struct.setAttribute(28, icdDxCodePrmy); }


  public String getIcdDxCode2() throws SQLException
  { return (String) _struct.getAttribute(29); }

  public void setIcdDxCode2(String icdDxCode2) throws SQLException
  { _struct.setAttribute(29, icdDxCode2); }


  public String getIcdDxCode3() throws SQLException
  { return (String) _struct.getAttribute(30); }

  public void setIcdDxCode3(String icdDxCode3) throws SQLException
  { _struct.setAttribute(30, icdDxCode3); }


  public String getIcdDxCode4() throws SQLException
  { return (String) _struct.getAttribute(31); }

  public void setIcdDxCode4(String icdDxCode4) throws SQLException
  { _struct.setAttribute(31, icdDxCode4); }


  public String getIcdDxCode5() throws SQLException
  { return (String) _struct.getAttribute(32); }

  public void setIcdDxCode5(String icdDxCode5) throws SQLException
  { _struct.setAttribute(32, icdDxCode5); }


  public String getIcdDxCode6() throws SQLException
  { return (String) _struct.getAttribute(33); }

  public void setIcdDxCode6(String icdDxCode6) throws SQLException
  { _struct.setAttribute(33, icdDxCode6); }


  public String getIcdDxCode7() throws SQLException
  { return (String) _struct.getAttribute(34); }

  public void setIcdDxCode7(String icdDxCode7) throws SQLException
  { _struct.setAttribute(34, icdDxCode7); }


  public String getIcdDxCode8() throws SQLException
  { return (String) _struct.getAttribute(35); }

  public void setIcdDxCode8(String icdDxCode8) throws SQLException
  { _struct.setAttribute(35, icdDxCode8); }


  public String getIcdDxCode9() throws SQLException
  { return (String) _struct.getAttribute(36); }

  public void setIcdDxCode9(String icdDxCode9) throws SQLException
  { _struct.setAttribute(36, icdDxCode9); }


  public String getIcdDxCode10() throws SQLException
  { return (String) _struct.getAttribute(37); }

  public void setIcdDxCode10(String icdDxCode10) throws SQLException
  { _struct.setAttribute(37, icdDxCode10); }


  public String getIcdDxCode11() throws SQLException
  { return (String) _struct.getAttribute(38); }

  public void setIcdDxCode11(String icdDxCode11) throws SQLException
  { _struct.setAttribute(38, icdDxCode11); }


  public String getIcdDxCode12() throws SQLException
  { return (String) _struct.getAttribute(39); }

  public void setIcdDxCode12(String icdDxCode12) throws SQLException
  { _struct.setAttribute(39, icdDxCode12); }


  public String getRenderHcpNpi() throws SQLException
  { return (String) _struct.getAttribute(40); }

  public void setRenderHcpNpi(String renderHcpNpi) throws SQLException
  { _struct.setAttribute(40, renderHcpNpi); }


  public String getRefingProfnlNpi() throws SQLException
  { return (String) _struct.getAttribute(41); }

  public void setRefingProfnlNpi(String refingProfnlNpi) throws SQLException
  { _struct.setAttribute(41, refingProfnlNpi); }


  public java.math.BigDecimal getInvTotalAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(42); }

  public void setInvTotalAmt(java.math.BigDecimal invTotalAmt) throws SQLException
  { _struct.setAttribute(42, invTotalAmt); }


  public java.math.BigDecimal getInvTotalUnit() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(43); }

  public void setInvTotalUnit(java.math.BigDecimal invTotalUnit) throws SQLException
  { _struct.setAttribute(43, invTotalUnit); }


  public String getInvBillTypCode() throws SQLException
  { return (String) _struct.getAttribute(44); }

  public void setInvBillTypCode(String invBillTypCode) throws SQLException
  { _struct.setAttribute(44, invBillTypCode); }


  public java.math.BigDecimal getInvManualOverrideInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(45); }

  public void setInvManualOverrideInd(java.math.BigDecimal invManualOverrideInd) throws SQLException
  { _struct.setAttribute(45, invManualOverrideInd); }


  public String getUserName() throws SQLException
  { return (String) _struct.getAttribute(46); }

  public void setUserName(String userName) throws SQLException
  { _struct.setAttribute(46, userName); }


  public String getUserGuid() throws SQLException
  { return (String) _struct.getAttribute(47); }

  public void setUserGuid(String userGuid) throws SQLException
  { _struct.setAttribute(47, userGuid); }


  public String getMemo() throws SQLException
  { return (String) _struct.getAttribute(48); }

  public void setMemo(String memo) throws SQLException
  { _struct.setAttribute(48, memo); }


  public String getCrn() throws SQLException
  { return (String) _struct.getAttribute(49); }

  public void setCrn(String crn) throws SQLException
  { _struct.setAttribute(49, crn); }


  public String getClaimFilingIndCode() throws SQLException
  { return (String) _struct.getAttribute(50); }

  public void setClaimFilingIndCode(String claimFilingIndCode) throws SQLException
  { _struct.setAttribute(50, claimFilingIndCode); }

}
