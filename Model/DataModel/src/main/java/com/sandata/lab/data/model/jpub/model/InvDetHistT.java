package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class InvDetHistT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.INV_DET_HIST_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,12,2,91,91,12,12,12,12,2,12,12,2,2,2,12,91,12,12,12,12,12,12,12,12,12,12,12,12,12,12,2,2,2,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[38];
  protected static final InvDetHistT _InvDetHistTFactory = new InvDetHistT();

  public static ORADataFactory getORADataFactory()
  { return _InvDetHistTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[38], _sqlType, _factory); }
  public InvDetHistT()
  { _init_struct(true); }
  public InvDetHistT(java.math.BigDecimal invDetHistSk, java.sql.Timestamp recCreateTmstpHist, String actionCode, java.math.BigDecimal invDetSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonCode, String changeReasonMemo, java.math.BigDecimal changeVersionId, String beId, String invNum, java.math.BigDecimal timesheetDetSk, java.math.BigDecimal timesheetSummarySk, java.math.BigDecimal claimSk, String vendorName, java.sql.Timestamp invDetDate, String invDetStatus, String invSubmStatus, String svcName, String billingCode, String mdfr1Code, String mdfr2Code, String mdfr3Code, String mdfr4Code, String payerId, String revCode, String renderHcpNpi, String rateTypName, String rateQlfrCode, String svcUnitName, java.math.BigDecimal rateAmt, java.math.BigDecimal invDetTotalAmt, java.math.BigDecimal invDetTotalUnit, String userName, String userGuid, String memo) throws SQLException
  { _init_struct(true);
    setInvDetHistSk(invDetHistSk);
    setRecCreateTmstpHist(recCreateTmstpHist);
    setActionCode(actionCode);
    setInvDetSk(invDetSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonCode(changeReasonCode);
    setChangeReasonMemo(changeReasonMemo);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setInvNum(invNum);
    setTimesheetDetSk(timesheetDetSk);
    setTimesheetSummarySk(timesheetSummarySk);
    setClaimSk(claimSk);
    setVendorName(vendorName);
    setInvDetDate(invDetDate);
    setInvDetStatus(invDetStatus);
    setInvSubmStatus(invSubmStatus);
    setSvcName(svcName);
    setBillingCode(billingCode);
    setMdfr1Code(mdfr1Code);
    setMdfr2Code(mdfr2Code);
    setMdfr3Code(mdfr3Code);
    setMdfr4Code(mdfr4Code);
    setPayerId(payerId);
    setRevCode(revCode);
    setRenderHcpNpi(renderHcpNpi);
    setRateTypName(rateTypName);
    setRateQlfrCode(rateQlfrCode);
    setSvcUnitName(svcUnitName);
    setRateAmt(rateAmt);
    setInvDetTotalAmt(invDetTotalAmt);
    setInvDetTotalUnit(invDetTotalUnit);
    setUserName(userName);
    setUserGuid(userGuid);
    setMemo(memo);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(InvDetHistT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new InvDetHistT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getInvDetHistSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setInvDetHistSk(java.math.BigDecimal invDetHistSk) throws SQLException
  { _struct.setAttribute(0, invDetHistSk); }


  public java.sql.Timestamp getRecCreateTmstpHist() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstpHist(java.sql.Timestamp recCreateTmstpHist) throws SQLException
  { _struct.setAttribute(1, recCreateTmstpHist); }


  public String getActionCode() throws SQLException
  { return (String) _struct.getAttribute(2); }

  public void setActionCode(String actionCode) throws SQLException
  { _struct.setAttribute(2, actionCode); }


  public java.math.BigDecimal getInvDetSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setInvDetSk(java.math.BigDecimal invDetSk) throws SQLException
  { _struct.setAttribute(3, invDetSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(4); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(4, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(5); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(5, recUpdateTmstp); }


  public String getRecCreatedBy() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setRecCreatedBy(String recCreatedBy) throws SQLException
  { _struct.setAttribute(6, recCreatedBy); }


  public String getRecUpdatedBy() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setRecUpdatedBy(String recUpdatedBy) throws SQLException
  { _struct.setAttribute(7, recUpdatedBy); }


  public String getChangeReasonCode() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setChangeReasonCode(String changeReasonCode) throws SQLException
  { _struct.setAttribute(8, changeReasonCode); }


  public String getChangeReasonMemo() throws SQLException
  { return (String) _struct.getAttribute(9); }

  public void setChangeReasonMemo(String changeReasonMemo) throws SQLException
  { _struct.setAttribute(9, changeReasonMemo); }


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


  public java.math.BigDecimal getTimesheetDetSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(13); }

  public void setTimesheetDetSk(java.math.BigDecimal timesheetDetSk) throws SQLException
  { _struct.setAttribute(13, timesheetDetSk); }


  public java.math.BigDecimal getTimesheetSummarySk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(14); }

  public void setTimesheetSummarySk(java.math.BigDecimal timesheetSummarySk) throws SQLException
  { _struct.setAttribute(14, timesheetSummarySk); }


  public java.math.BigDecimal getClaimSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(15); }

  public void setClaimSk(java.math.BigDecimal claimSk) throws SQLException
  { _struct.setAttribute(15, claimSk); }


  public String getVendorName() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setVendorName(String vendorName) throws SQLException
  { _struct.setAttribute(16, vendorName); }


  public java.sql.Timestamp getInvDetDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(17); }

  public void setInvDetDate(java.sql.Timestamp invDetDate) throws SQLException
  { _struct.setAttribute(17, invDetDate); }


  public String getInvDetStatus() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setInvDetStatus(String invDetStatus) throws SQLException
  { _struct.setAttribute(18, invDetStatus); }


  public String getInvSubmStatus() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setInvSubmStatus(String invSubmStatus) throws SQLException
  { _struct.setAttribute(19, invSubmStatus); }


  public String getSvcName() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setSvcName(String svcName) throws SQLException
  { _struct.setAttribute(20, svcName); }


  public String getBillingCode() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setBillingCode(String billingCode) throws SQLException
  { _struct.setAttribute(21, billingCode); }


  public String getMdfr1Code() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setMdfr1Code(String mdfr1Code) throws SQLException
  { _struct.setAttribute(22, mdfr1Code); }


  public String getMdfr2Code() throws SQLException
  { return (String) _struct.getAttribute(23); }

  public void setMdfr2Code(String mdfr2Code) throws SQLException
  { _struct.setAttribute(23, mdfr2Code); }


  public String getMdfr3Code() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setMdfr3Code(String mdfr3Code) throws SQLException
  { _struct.setAttribute(24, mdfr3Code); }


  public String getMdfr4Code() throws SQLException
  { return (String) _struct.getAttribute(25); }

  public void setMdfr4Code(String mdfr4Code) throws SQLException
  { _struct.setAttribute(25, mdfr4Code); }


  public String getPayerId() throws SQLException
  { return (String) _struct.getAttribute(26); }

  public void setPayerId(String payerId) throws SQLException
  { _struct.setAttribute(26, payerId); }


  public String getRevCode() throws SQLException
  { return (String) _struct.getAttribute(27); }

  public void setRevCode(String revCode) throws SQLException
  { _struct.setAttribute(27, revCode); }


  public String getRenderHcpNpi() throws SQLException
  { return (String) _struct.getAttribute(28); }

  public void setRenderHcpNpi(String renderHcpNpi) throws SQLException
  { _struct.setAttribute(28, renderHcpNpi); }


  public String getRateTypName() throws SQLException
  { return (String) _struct.getAttribute(29); }

  public void setRateTypName(String rateTypName) throws SQLException
  { _struct.setAttribute(29, rateTypName); }


  public String getRateQlfrCode() throws SQLException
  { return (String) _struct.getAttribute(30); }

  public void setRateQlfrCode(String rateQlfrCode) throws SQLException
  { _struct.setAttribute(30, rateQlfrCode); }


  public String getSvcUnitName() throws SQLException
  { return (String) _struct.getAttribute(31); }

  public void setSvcUnitName(String svcUnitName) throws SQLException
  { _struct.setAttribute(31, svcUnitName); }


  public java.math.BigDecimal getRateAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(32); }

  public void setRateAmt(java.math.BigDecimal rateAmt) throws SQLException
  { _struct.setAttribute(32, rateAmt); }


  public java.math.BigDecimal getInvDetTotalAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(33); }

  public void setInvDetTotalAmt(java.math.BigDecimal invDetTotalAmt) throws SQLException
  { _struct.setAttribute(33, invDetTotalAmt); }


  public java.math.BigDecimal getInvDetTotalUnit() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(34); }

  public void setInvDetTotalUnit(java.math.BigDecimal invDetTotalUnit) throws SQLException
  { _struct.setAttribute(34, invDetTotalUnit); }


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

}
