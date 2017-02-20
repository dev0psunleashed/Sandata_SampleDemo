package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class InvDetT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.INV_DET_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,12,2,2,12,12,12,2,2,12,91,12,12,12,12,12,12,12,12,12,12,12,12,12,2,2,2,2,12,12,12,12,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[40];
  protected static final InvDetT _InvDetTFactory = new InvDetT();

  public static ORADataFactory getORADataFactory()
  { return _InvDetTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[40], _sqlType, _factory); }
  public InvDetT()
  { _init_struct(true); }
  public InvDetT(java.math.BigDecimal invDetSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonCode, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String invNum, String invDetId, java.math.BigDecimal timesheetDetSk, java.math.BigDecimal timesheetSummarySk, String vendorName, java.sql.Timestamp invDetSvcDate, String invDetStatusCode, String invDetSubmStatusName, String payerId, String revCode, String billingCode, String mdfr4Code, String mdfr3Code, String mdfr2Code, String mdfr1Code, String renderProfnlNpi, String svcName, String rateTypName, String rateQlfrCode, java.math.BigDecimal invDetRateAmt, java.math.BigDecimal invDetTotalAmt, java.math.BigDecimal invDetTotalUnit, java.math.BigDecimal splitBillingAlwdInd, String userName, String userGuid, String memo, String crn, java.math.BigDecimal invDetSvcLineItemNum) throws SQLException
  { _init_struct(true);
    setInvDetSk(invDetSk);
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
    setInvNum(invNum);
    setInvDetId(invDetId);
    setTimesheetDetSk(timesheetDetSk);
    setTimesheetSummarySk(timesheetSummarySk);
    setVendorName(vendorName);
    setInvDetSvcDate(invDetSvcDate);
    setInvDetStatusCode(invDetStatusCode);
    setInvDetSubmStatusName(invDetSubmStatusName);
    setPayerId(payerId);
    setRevCode(revCode);
    setBillingCode(billingCode);
    setMdfr4Code(mdfr4Code);
    setMdfr3Code(mdfr3Code);
    setMdfr2Code(mdfr2Code);
    setMdfr1Code(mdfr1Code);
    setRenderProfnlNpi(renderProfnlNpi);
    setSvcName(svcName);
    setRateTypName(rateTypName);
    setRateQlfrCode(rateQlfrCode);
    setInvDetRateAmt(invDetRateAmt);
    setInvDetTotalAmt(invDetTotalAmt);
    setInvDetTotalUnit(invDetTotalUnit);
    setSplitBillingAlwdInd(splitBillingAlwdInd);
    setUserName(userName);
    setUserGuid(userGuid);
    setMemo(memo);
    setCrn(crn);
    setInvDetSvcLineItemNum(invDetSvcLineItemNum);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(InvDetT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new InvDetT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getInvDetSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setInvDetSk(java.math.BigDecimal invDetSk) throws SQLException
  { _struct.setAttribute(0, invDetSk); }


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


  public String getInvNum() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setInvNum(String invNum) throws SQLException
  { _struct.setAttribute(12, invNum); }


  public String getInvDetId() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setInvDetId(String invDetId) throws SQLException
  { _struct.setAttribute(13, invDetId); }


  public java.math.BigDecimal getTimesheetDetSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(14); }

  public void setTimesheetDetSk(java.math.BigDecimal timesheetDetSk) throws SQLException
  { _struct.setAttribute(14, timesheetDetSk); }


  public java.math.BigDecimal getTimesheetSummarySk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(15); }

  public void setTimesheetSummarySk(java.math.BigDecimal timesheetSummarySk) throws SQLException
  { _struct.setAttribute(15, timesheetSummarySk); }


  public String getVendorName() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setVendorName(String vendorName) throws SQLException
  { _struct.setAttribute(16, vendorName); }


  public java.sql.Timestamp getInvDetSvcDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(17); }

  public void setInvDetSvcDate(java.sql.Timestamp invDetSvcDate) throws SQLException
  { _struct.setAttribute(17, invDetSvcDate); }


  public String getInvDetStatusCode() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setInvDetStatusCode(String invDetStatusCode) throws SQLException
  { _struct.setAttribute(18, invDetStatusCode); }


  public String getInvDetSubmStatusName() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setInvDetSubmStatusName(String invDetSubmStatusName) throws SQLException
  { _struct.setAttribute(19, invDetSubmStatusName); }


  public String getPayerId() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setPayerId(String payerId) throws SQLException
  { _struct.setAttribute(20, payerId); }


  public String getRevCode() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setRevCode(String revCode) throws SQLException
  { _struct.setAttribute(21, revCode); }


  public String getBillingCode() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setBillingCode(String billingCode) throws SQLException
  { _struct.setAttribute(22, billingCode); }


  public String getMdfr4Code() throws SQLException
  { return (String) _struct.getAttribute(23); }

  public void setMdfr4Code(String mdfr4Code) throws SQLException
  { _struct.setAttribute(23, mdfr4Code); }


  public String getMdfr3Code() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setMdfr3Code(String mdfr3Code) throws SQLException
  { _struct.setAttribute(24, mdfr3Code); }


  public String getMdfr2Code() throws SQLException
  { return (String) _struct.getAttribute(25); }

  public void setMdfr2Code(String mdfr2Code) throws SQLException
  { _struct.setAttribute(25, mdfr2Code); }


  public String getMdfr1Code() throws SQLException
  { return (String) _struct.getAttribute(26); }

  public void setMdfr1Code(String mdfr1Code) throws SQLException
  { _struct.setAttribute(26, mdfr1Code); }


  public String getRenderProfnlNpi() throws SQLException
  { return (String) _struct.getAttribute(27); }

  public void setRenderProfnlNpi(String renderProfnlNpi) throws SQLException
  { _struct.setAttribute(27, renderProfnlNpi); }


  public String getSvcName() throws SQLException
  { return (String) _struct.getAttribute(28); }

  public void setSvcName(String svcName) throws SQLException
  { _struct.setAttribute(28, svcName); }


  public String getRateTypName() throws SQLException
  { return (String) _struct.getAttribute(29); }

  public void setRateTypName(String rateTypName) throws SQLException
  { _struct.setAttribute(29, rateTypName); }


  public String getRateQlfrCode() throws SQLException
  { return (String) _struct.getAttribute(30); }

  public void setRateQlfrCode(String rateQlfrCode) throws SQLException
  { _struct.setAttribute(30, rateQlfrCode); }


  public java.math.BigDecimal getInvDetRateAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(31); }

  public void setInvDetRateAmt(java.math.BigDecimal invDetRateAmt) throws SQLException
  { _struct.setAttribute(31, invDetRateAmt); }


  public java.math.BigDecimal getInvDetTotalAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(32); }

  public void setInvDetTotalAmt(java.math.BigDecimal invDetTotalAmt) throws SQLException
  { _struct.setAttribute(32, invDetTotalAmt); }


  public java.math.BigDecimal getInvDetTotalUnit() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(33); }

  public void setInvDetTotalUnit(java.math.BigDecimal invDetTotalUnit) throws SQLException
  { _struct.setAttribute(33, invDetTotalUnit); }


  public java.math.BigDecimal getSplitBillingAlwdInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(34); }

  public void setSplitBillingAlwdInd(java.math.BigDecimal splitBillingAlwdInd) throws SQLException
  { _struct.setAttribute(34, splitBillingAlwdInd); }


  public String getUserName() throws SQLException
  { return (String) _struct.getAttribute(35); }

  public void setUserName(String userName) throws SQLException
  { _struct.setAttribute(35, userName); }


  public String getUserGuid() throws SQLException
  { return (String) _struct.getAttribute(36); }

  public void setUserGuid(String userGuid) throws SQLException
  { _struct.setAttribute(36, userGuid); }


  public String getMemo() throws SQLException
  { return (String) _struct.getAttribute(37); }

  public void setMemo(String memo) throws SQLException
  { _struct.setAttribute(37, memo); }


  public String getCrn() throws SQLException
  { return (String) _struct.getAttribute(38); }

  public void setCrn(String crn) throws SQLException
  { _struct.setAttribute(38, crn); }


  public java.math.BigDecimal getInvDetSvcLineItemNum() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(39); }

  public void setInvDetSvcLineItemNum(java.math.BigDecimal invDetSvcLineItemNum) throws SQLException
  { _struct.setAttribute(39, invDetSvcLineItemNum); }

}
