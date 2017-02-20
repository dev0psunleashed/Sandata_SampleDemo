package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class BillingT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.BILLING_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,12,12,12,12,2,12,12,12,12,12,12,12,12,12,12,91,91,91,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,2,2,12,2,12,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[57];
  protected static final BillingT _BillingTFactory = new BillingT();

  public static ORADataFactory getORADataFactory()
  { return _BillingTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[57], _sqlType, _factory); }
  public BillingT()
  { _init_struct(true); }
  public BillingT(java.math.BigDecimal billingSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonCode, String changeReasonMemo, java.math.BigDecimal changeVersionId, String beId, String beLobId, String beLocId, String payerId, String contrId, String authId, String ptId, String ptInsIdNum, String billingStatusName, String billingSubmTypName, java.sql.Timestamp billingStartDate, java.sql.Timestamp billingEndDate, java.sql.Timestamp billingDate, String billingTypQlfr, String billingFmtTypName, String icdDxCodePrmy, String icdDxCodeRevisionQlfrPrmy, String icdDxCode2, String icdDxCodeRevisionQlfr2, String icdDxCode3, String icdDxCodeRevisionQlfr3, String icdDxCode4, String icdDxCodeRevisionQlfr4, String icdDxCode5, String icdDxCodeRevisionQlfr5, String icdDxCode6, String icdDxCodeRevisionQlfr6, String icdDxCode7, String icdDxCodeRevisionQlfr7, String icdDxCode8, String icdDxCodeRevisionQlfr8, String icdDxCode9, String icdDxCodeRevisionQlfr9, String icdDxCode10, String icdDxCodeRevisionQlfr10, String icdDxCode11, String icdDxCodeRevisionQlfr11, String icdDxCode12, String icdDxCodeRevisionQlfr12, String renderHcpNpi, String refingHcpNpi, java.math.BigDecimal billingTotalAmt, java.math.BigDecimal billingTotalUnit, String billingBillTypCode, java.math.BigDecimal billingManualOverrideInd, String userName, String userGuid, String memo, String claimFilingIndCode) throws SQLException
  { _init_struct(true);
    setBillingSk(billingSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonCode(changeReasonCode);
    setChangeReasonMemo(changeReasonMemo);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setBeLobId(beLobId);
    setBeLocId(beLocId);
    setPayerId(payerId);
    setContrId(contrId);
    setAuthId(authId);
    setPtId(ptId);
    setPtInsIdNum(ptInsIdNum);
    setBillingStatusName(billingStatusName);
    setBillingSubmTypName(billingSubmTypName);
    setBillingStartDate(billingStartDate);
    setBillingEndDate(billingEndDate);
    setBillingDate(billingDate);
    setBillingTypQlfr(billingTypQlfr);
    setBillingFmtTypName(billingFmtTypName);
    setIcdDxCodePrmy(icdDxCodePrmy);
    setIcdDxCodeRevisionQlfrPrmy(icdDxCodeRevisionQlfrPrmy);
    setIcdDxCode2(icdDxCode2);
    setIcdDxCodeRevisionQlfr2(icdDxCodeRevisionQlfr2);
    setIcdDxCode3(icdDxCode3);
    setIcdDxCodeRevisionQlfr3(icdDxCodeRevisionQlfr3);
    setIcdDxCode4(icdDxCode4);
    setIcdDxCodeRevisionQlfr4(icdDxCodeRevisionQlfr4);
    setIcdDxCode5(icdDxCode5);
    setIcdDxCodeRevisionQlfr5(icdDxCodeRevisionQlfr5);
    setIcdDxCode6(icdDxCode6);
    setIcdDxCodeRevisionQlfr6(icdDxCodeRevisionQlfr6);
    setIcdDxCode7(icdDxCode7);
    setIcdDxCodeRevisionQlfr7(icdDxCodeRevisionQlfr7);
    setIcdDxCode8(icdDxCode8);
    setIcdDxCodeRevisionQlfr8(icdDxCodeRevisionQlfr8);
    setIcdDxCode9(icdDxCode9);
    setIcdDxCodeRevisionQlfr9(icdDxCodeRevisionQlfr9);
    setIcdDxCode10(icdDxCode10);
    setIcdDxCodeRevisionQlfr10(icdDxCodeRevisionQlfr10);
    setIcdDxCode11(icdDxCode11);
    setIcdDxCodeRevisionQlfr11(icdDxCodeRevisionQlfr11);
    setIcdDxCode12(icdDxCode12);
    setIcdDxCodeRevisionQlfr12(icdDxCodeRevisionQlfr12);
    setRenderHcpNpi(renderHcpNpi);
    setRefingHcpNpi(refingHcpNpi);
    setBillingTotalAmt(billingTotalAmt);
    setBillingTotalUnit(billingTotalUnit);
    setBillingBillTypCode(billingBillTypCode);
    setBillingManualOverrideInd(billingManualOverrideInd);
    setUserName(userName);
    setUserGuid(userGuid);
    setMemo(memo);
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
  protected ORAData create(BillingT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new BillingT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getBillingSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setBillingSk(java.math.BigDecimal billingSk) throws SQLException
  { _struct.setAttribute(0, billingSk); }


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


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(8, beId); }


  public String getBeLobId() throws SQLException
  { return (String) _struct.getAttribute(9); }

  public void setBeLobId(String beLobId) throws SQLException
  { _struct.setAttribute(9, beLobId); }


  public String getBeLocId() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setBeLocId(String beLocId) throws SQLException
  { _struct.setAttribute(10, beLocId); }


  public String getPayerId() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setPayerId(String payerId) throws SQLException
  { _struct.setAttribute(11, payerId); }


  public String getContrId() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setContrId(String contrId) throws SQLException
  { _struct.setAttribute(12, contrId); }


  public String getAuthId() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setAuthId(String authId) throws SQLException
  { _struct.setAttribute(13, authId); }


  public String getPtId() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setPtId(String ptId) throws SQLException
  { _struct.setAttribute(14, ptId); }


  public String getPtInsIdNum() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setPtInsIdNum(String ptInsIdNum) throws SQLException
  { _struct.setAttribute(15, ptInsIdNum); }


  public String getBillingStatusName() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setBillingStatusName(String billingStatusName) throws SQLException
  { _struct.setAttribute(16, billingStatusName); }


  public String getBillingSubmTypName() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setBillingSubmTypName(String billingSubmTypName) throws SQLException
  { _struct.setAttribute(17, billingSubmTypName); }


  public java.sql.Timestamp getBillingStartDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(18); }

  public void setBillingStartDate(java.sql.Timestamp billingStartDate) throws SQLException
  { _struct.setAttribute(18, billingStartDate); }


  public java.sql.Timestamp getBillingEndDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(19); }

  public void setBillingEndDate(java.sql.Timestamp billingEndDate) throws SQLException
  { _struct.setAttribute(19, billingEndDate); }


  public java.sql.Timestamp getBillingDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(20); }

  public void setBillingDate(java.sql.Timestamp billingDate) throws SQLException
  { _struct.setAttribute(20, billingDate); }


  public String getBillingTypQlfr() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setBillingTypQlfr(String billingTypQlfr) throws SQLException
  { _struct.setAttribute(21, billingTypQlfr); }


  public String getBillingFmtTypName() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setBillingFmtTypName(String billingFmtTypName) throws SQLException
  { _struct.setAttribute(22, billingFmtTypName); }


  public String getIcdDxCodePrmy() throws SQLException
  { return (String) _struct.getAttribute(23); }

  public void setIcdDxCodePrmy(String icdDxCodePrmy) throws SQLException
  { _struct.setAttribute(23, icdDxCodePrmy); }


  public String getIcdDxCodeRevisionQlfrPrmy() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setIcdDxCodeRevisionQlfrPrmy(String icdDxCodeRevisionQlfrPrmy) throws SQLException
  { _struct.setAttribute(24, icdDxCodeRevisionQlfrPrmy); }


  public String getIcdDxCode2() throws SQLException
  { return (String) _struct.getAttribute(25); }

  public void setIcdDxCode2(String icdDxCode2) throws SQLException
  { _struct.setAttribute(25, icdDxCode2); }


  public String getIcdDxCodeRevisionQlfr2() throws SQLException
  { return (String) _struct.getAttribute(26); }

  public void setIcdDxCodeRevisionQlfr2(String icdDxCodeRevisionQlfr2) throws SQLException
  { _struct.setAttribute(26, icdDxCodeRevisionQlfr2); }


  public String getIcdDxCode3() throws SQLException
  { return (String) _struct.getAttribute(27); }

  public void setIcdDxCode3(String icdDxCode3) throws SQLException
  { _struct.setAttribute(27, icdDxCode3); }


  public String getIcdDxCodeRevisionQlfr3() throws SQLException
  { return (String) _struct.getAttribute(28); }

  public void setIcdDxCodeRevisionQlfr3(String icdDxCodeRevisionQlfr3) throws SQLException
  { _struct.setAttribute(28, icdDxCodeRevisionQlfr3); }


  public String getIcdDxCode4() throws SQLException
  { return (String) _struct.getAttribute(29); }

  public void setIcdDxCode4(String icdDxCode4) throws SQLException
  { _struct.setAttribute(29, icdDxCode4); }


  public String getIcdDxCodeRevisionQlfr4() throws SQLException
  { return (String) _struct.getAttribute(30); }

  public void setIcdDxCodeRevisionQlfr4(String icdDxCodeRevisionQlfr4) throws SQLException
  { _struct.setAttribute(30, icdDxCodeRevisionQlfr4); }


  public String getIcdDxCode5() throws SQLException
  { return (String) _struct.getAttribute(31); }

  public void setIcdDxCode5(String icdDxCode5) throws SQLException
  { _struct.setAttribute(31, icdDxCode5); }


  public String getIcdDxCodeRevisionQlfr5() throws SQLException
  { return (String) _struct.getAttribute(32); }

  public void setIcdDxCodeRevisionQlfr5(String icdDxCodeRevisionQlfr5) throws SQLException
  { _struct.setAttribute(32, icdDxCodeRevisionQlfr5); }


  public String getIcdDxCode6() throws SQLException
  { return (String) _struct.getAttribute(33); }

  public void setIcdDxCode6(String icdDxCode6) throws SQLException
  { _struct.setAttribute(33, icdDxCode6); }


  public String getIcdDxCodeRevisionQlfr6() throws SQLException
  { return (String) _struct.getAttribute(34); }

  public void setIcdDxCodeRevisionQlfr6(String icdDxCodeRevisionQlfr6) throws SQLException
  { _struct.setAttribute(34, icdDxCodeRevisionQlfr6); }


  public String getIcdDxCode7() throws SQLException
  { return (String) _struct.getAttribute(35); }

  public void setIcdDxCode7(String icdDxCode7) throws SQLException
  { _struct.setAttribute(35, icdDxCode7); }


  public String getIcdDxCodeRevisionQlfr7() throws SQLException
  { return (String) _struct.getAttribute(36); }

  public void setIcdDxCodeRevisionQlfr7(String icdDxCodeRevisionQlfr7) throws SQLException
  { _struct.setAttribute(36, icdDxCodeRevisionQlfr7); }


  public String getIcdDxCode8() throws SQLException
  { return (String) _struct.getAttribute(37); }

  public void setIcdDxCode8(String icdDxCode8) throws SQLException
  { _struct.setAttribute(37, icdDxCode8); }


  public String getIcdDxCodeRevisionQlfr8() throws SQLException
  { return (String) _struct.getAttribute(38); }

  public void setIcdDxCodeRevisionQlfr8(String icdDxCodeRevisionQlfr8) throws SQLException
  { _struct.setAttribute(38, icdDxCodeRevisionQlfr8); }


  public String getIcdDxCode9() throws SQLException
  { return (String) _struct.getAttribute(39); }

  public void setIcdDxCode9(String icdDxCode9) throws SQLException
  { _struct.setAttribute(39, icdDxCode9); }


  public String getIcdDxCodeRevisionQlfr9() throws SQLException
  { return (String) _struct.getAttribute(40); }

  public void setIcdDxCodeRevisionQlfr9(String icdDxCodeRevisionQlfr9) throws SQLException
  { _struct.setAttribute(40, icdDxCodeRevisionQlfr9); }


  public String getIcdDxCode10() throws SQLException
  { return (String) _struct.getAttribute(41); }

  public void setIcdDxCode10(String icdDxCode10) throws SQLException
  { _struct.setAttribute(41, icdDxCode10); }


  public String getIcdDxCodeRevisionQlfr10() throws SQLException
  { return (String) _struct.getAttribute(42); }

  public void setIcdDxCodeRevisionQlfr10(String icdDxCodeRevisionQlfr10) throws SQLException
  { _struct.setAttribute(42, icdDxCodeRevisionQlfr10); }


  public String getIcdDxCode11() throws SQLException
  { return (String) _struct.getAttribute(43); }

  public void setIcdDxCode11(String icdDxCode11) throws SQLException
  { _struct.setAttribute(43, icdDxCode11); }


  public String getIcdDxCodeRevisionQlfr11() throws SQLException
  { return (String) _struct.getAttribute(44); }

  public void setIcdDxCodeRevisionQlfr11(String icdDxCodeRevisionQlfr11) throws SQLException
  { _struct.setAttribute(44, icdDxCodeRevisionQlfr11); }


  public String getIcdDxCode12() throws SQLException
  { return (String) _struct.getAttribute(45); }

  public void setIcdDxCode12(String icdDxCode12) throws SQLException
  { _struct.setAttribute(45, icdDxCode12); }


  public String getIcdDxCodeRevisionQlfr12() throws SQLException
  { return (String) _struct.getAttribute(46); }

  public void setIcdDxCodeRevisionQlfr12(String icdDxCodeRevisionQlfr12) throws SQLException
  { _struct.setAttribute(46, icdDxCodeRevisionQlfr12); }


  public String getRenderHcpNpi() throws SQLException
  { return (String) _struct.getAttribute(47); }

  public void setRenderHcpNpi(String renderHcpNpi) throws SQLException
  { _struct.setAttribute(47, renderHcpNpi); }


  public String getRefingHcpNpi() throws SQLException
  { return (String) _struct.getAttribute(48); }

  public void setRefingHcpNpi(String refingHcpNpi) throws SQLException
  { _struct.setAttribute(48, refingHcpNpi); }


  public java.math.BigDecimal getBillingTotalAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(49); }

  public void setBillingTotalAmt(java.math.BigDecimal billingTotalAmt) throws SQLException
  { _struct.setAttribute(49, billingTotalAmt); }


  public java.math.BigDecimal getBillingTotalUnit() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(50); }

  public void setBillingTotalUnit(java.math.BigDecimal billingTotalUnit) throws SQLException
  { _struct.setAttribute(50, billingTotalUnit); }


  public String getBillingBillTypCode() throws SQLException
  { return (String) _struct.getAttribute(51); }

  public void setBillingBillTypCode(String billingBillTypCode) throws SQLException
  { _struct.setAttribute(51, billingBillTypCode); }


  public java.math.BigDecimal getBillingManualOverrideInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(52); }

  public void setBillingManualOverrideInd(java.math.BigDecimal billingManualOverrideInd) throws SQLException
  { _struct.setAttribute(52, billingManualOverrideInd); }


  public String getUserName() throws SQLException
  { return (String) _struct.getAttribute(53); }

  public void setUserName(String userName) throws SQLException
  { _struct.setAttribute(53, userName); }


  public String getUserGuid() throws SQLException
  { return (String) _struct.getAttribute(54); }

  public void setUserGuid(String userGuid) throws SQLException
  { _struct.setAttribute(54, userGuid); }


  public String getMemo() throws SQLException
  { return (String) _struct.getAttribute(55); }

  public void setMemo(String memo) throws SQLException
  { _struct.setAttribute(55, memo); }


  public String getClaimFilingIndCode() throws SQLException
  { return (String) _struct.getAttribute(56); }

  public void setClaimFilingIndCode(String claimFilingIndCode) throws SQLException
  { _struct.setAttribute(56, claimFilingIndCode); }

}
