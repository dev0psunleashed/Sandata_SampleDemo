package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class BillingDetT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.BILLING_DET_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,12,12,12,12,2,2,12,12,12,12,2,2,12,91,12,12,12,12,12,12,12,12,12,12,12,12,12,2,2,2,2,12,12,12,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[38];
  protected static final BillingDetT _BillingDetTFactory = new BillingDetT();

  public static ORADataFactory getORADataFactory()
  { return _BillingDetTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[38], _sqlType, _factory); }
  public BillingDetT()
  { _init_struct(true); }
  public BillingDetT(java.math.BigDecimal billingDetSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonCode, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String ptId, String contrId, String payerId, java.math.BigDecimal timesheetDetSk, java.math.BigDecimal timesheetSummarySk, String vendorName, java.sql.Timestamp billingDetDate, String billingDetStatusCode, String billingDetSubmStatusName, String svcName, String billingCode, String mdfr1Code, String mdfr2Code, String mdfr3Code, String mdfr4Code, String revCode, String renderHcpNpi, String rateTypName, String rateQlfrCode, String svcUnitName, java.math.BigDecimal billingDetRateAmt, java.math.BigDecimal billingDetTotalAmt, java.math.BigDecimal billingDetTotalUnit, java.math.BigDecimal splitBillingAlwdInd, String userName, String userGuid, String memo, java.math.BigDecimal visitSk) throws SQLException
  { _init_struct(true);
    setBillingDetSk(billingDetSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonCode(changeReasonCode);
    setChangeReasonMemo(changeReasonMemo);
    setCurrRecInd(currRecInd);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setPtId(ptId);
    setContrId(contrId);
    setPayerId(payerId);
    setTimesheetDetSk(timesheetDetSk);
    setTimesheetSummarySk(timesheetSummarySk);
    setVendorName(vendorName);
    setBillingDetDate(billingDetDate);
    setBillingDetStatusCode(billingDetStatusCode);
    setBillingDetSubmStatusName(billingDetSubmStatusName);
    setSvcName(svcName);
    setBillingCode(billingCode);
    setMdfr1Code(mdfr1Code);
    setMdfr2Code(mdfr2Code);
    setMdfr3Code(mdfr3Code);
    setMdfr4Code(mdfr4Code);
    setRevCode(revCode);
    setRenderHcpNpi(renderHcpNpi);
    setRateTypName(rateTypName);
    setRateQlfrCode(rateQlfrCode);
    setSvcUnitName(svcUnitName);
    setBillingDetRateAmt(billingDetRateAmt);
    setBillingDetTotalAmt(billingDetTotalAmt);
    setBillingDetTotalUnit(billingDetTotalUnit);
    setSplitBillingAlwdInd(splitBillingAlwdInd);
    setUserName(userName);
    setUserGuid(userGuid);
    setMemo(memo);
    setVisitSk(visitSk);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(BillingDetT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new BillingDetT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getBillingDetSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setBillingDetSk(java.math.BigDecimal billingDetSk) throws SQLException
  { _struct.setAttribute(0, billingDetSk); }


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


  public java.math.BigDecimal getCurrRecInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(7); }

  public void setCurrRecInd(java.math.BigDecimal currRecInd) throws SQLException
  { _struct.setAttribute(7, currRecInd); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(8); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(8, changeVersionId); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(9); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(9, beId); }


  public String getPtId() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setPtId(String ptId) throws SQLException
  { _struct.setAttribute(10, ptId); }


  public String getContrId() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setContrId(String contrId) throws SQLException
  { _struct.setAttribute(11, contrId); }


  public String getPayerId() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setPayerId(String payerId) throws SQLException
  { _struct.setAttribute(12, payerId); }


  public java.math.BigDecimal getTimesheetDetSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(13); }

  public void setTimesheetDetSk(java.math.BigDecimal timesheetDetSk) throws SQLException
  { _struct.setAttribute(13, timesheetDetSk); }


  public java.math.BigDecimal getTimesheetSummarySk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(14); }

  public void setTimesheetSummarySk(java.math.BigDecimal timesheetSummarySk) throws SQLException
  { _struct.setAttribute(14, timesheetSummarySk); }


  public String getVendorName() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setVendorName(String vendorName) throws SQLException
  { _struct.setAttribute(15, vendorName); }


  public java.sql.Timestamp getBillingDetDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(16); }

  public void setBillingDetDate(java.sql.Timestamp billingDetDate) throws SQLException
  { _struct.setAttribute(16, billingDetDate); }


  public String getBillingDetStatusCode() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setBillingDetStatusCode(String billingDetStatusCode) throws SQLException
  { _struct.setAttribute(17, billingDetStatusCode); }


  public String getBillingDetSubmStatusName() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setBillingDetSubmStatusName(String billingDetSubmStatusName) throws SQLException
  { _struct.setAttribute(18, billingDetSubmStatusName); }


  public String getSvcName() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setSvcName(String svcName) throws SQLException
  { _struct.setAttribute(19, svcName); }


  public String getBillingCode() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setBillingCode(String billingCode) throws SQLException
  { _struct.setAttribute(20, billingCode); }


  public String getMdfr1Code() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setMdfr1Code(String mdfr1Code) throws SQLException
  { _struct.setAttribute(21, mdfr1Code); }


  public String getMdfr2Code() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setMdfr2Code(String mdfr2Code) throws SQLException
  { _struct.setAttribute(22, mdfr2Code); }


  public String getMdfr3Code() throws SQLException
  { return (String) _struct.getAttribute(23); }

  public void setMdfr3Code(String mdfr3Code) throws SQLException
  { _struct.setAttribute(23, mdfr3Code); }


  public String getMdfr4Code() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setMdfr4Code(String mdfr4Code) throws SQLException
  { _struct.setAttribute(24, mdfr4Code); }


  public String getRevCode() throws SQLException
  { return (String) _struct.getAttribute(25); }

  public void setRevCode(String revCode) throws SQLException
  { _struct.setAttribute(25, revCode); }


  public String getRenderHcpNpi() throws SQLException
  { return (String) _struct.getAttribute(26); }

  public void setRenderHcpNpi(String renderHcpNpi) throws SQLException
  { _struct.setAttribute(26, renderHcpNpi); }


  public String getRateTypName() throws SQLException
  { return (String) _struct.getAttribute(27); }

  public void setRateTypName(String rateTypName) throws SQLException
  { _struct.setAttribute(27, rateTypName); }


  public String getRateQlfrCode() throws SQLException
  { return (String) _struct.getAttribute(28); }

  public void setRateQlfrCode(String rateQlfrCode) throws SQLException
  { _struct.setAttribute(28, rateQlfrCode); }


  public String getSvcUnitName() throws SQLException
  { return (String) _struct.getAttribute(29); }

  public void setSvcUnitName(String svcUnitName) throws SQLException
  { _struct.setAttribute(29, svcUnitName); }


  public java.math.BigDecimal getBillingDetRateAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(30); }

  public void setBillingDetRateAmt(java.math.BigDecimal billingDetRateAmt) throws SQLException
  { _struct.setAttribute(30, billingDetRateAmt); }


  public java.math.BigDecimal getBillingDetTotalAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(31); }

  public void setBillingDetTotalAmt(java.math.BigDecimal billingDetTotalAmt) throws SQLException
  { _struct.setAttribute(31, billingDetTotalAmt); }


  public java.math.BigDecimal getBillingDetTotalUnit() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(32); }

  public void setBillingDetTotalUnit(java.math.BigDecimal billingDetTotalUnit) throws SQLException
  { _struct.setAttribute(32, billingDetTotalUnit); }


  public java.math.BigDecimal getSplitBillingAlwdInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(33); }

  public void setSplitBillingAlwdInd(java.math.BigDecimal splitBillingAlwdInd) throws SQLException
  { _struct.setAttribute(33, splitBillingAlwdInd); }


  public String getUserName() throws SQLException
  { return (String) _struct.getAttribute(34); }

  public void setUserName(String userName) throws SQLException
  { _struct.setAttribute(34, userName); }


  public String getUserGuid() throws SQLException
  { return (String) _struct.getAttribute(35); }

  public void setUserGuid(String userGuid) throws SQLException
  { _struct.setAttribute(35, userGuid); }


  public String getMemo() throws SQLException
  { return (String) _struct.getAttribute(36); }

  public void setMemo(String memo) throws SQLException
  { _struct.setAttribute(36, memo); }


  public java.math.BigDecimal getVisitSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(37); }

  public void setVisitSk(java.math.BigDecimal visitSk) throws SQLException
  { _struct.setAttribute(37, visitSk); }

}
