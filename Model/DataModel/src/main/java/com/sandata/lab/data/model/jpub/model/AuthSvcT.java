package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class AuthSvcT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.AUTH_SVC_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,2,12,12,12,12,12,2,91,91,12,12,2,2,91,91,2,91,91,2,91,91,2,91,91,2,91,91,2,91,91,2,91,91,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[38];
  protected static final AuthSvcT _AuthSvcTFactory = new AuthSvcT();

  public static ORADataFactory getORADataFactory()
  { return _AuthSvcTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[38], _sqlType, _factory); }
  public AuthSvcT()
  { _init_struct(true); }
  public AuthSvcT(java.math.BigDecimal authSvcSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal changeVersionId, java.math.BigDecimal authSk, String beId, String svcName, String billingCode, String rateTypName, String rateQlfrCode, java.math.BigDecimal authSvcRateAmt, java.sql.Timestamp authSvcStartDate, java.sql.Timestamp authSvcEndDate, String authLmtTypName, String authSvcUnitName, java.math.BigDecimal authSvcLmt, java.math.BigDecimal authSvcLmtDay1, java.sql.Timestamp authSvcLmtStartTimeDay1, java.sql.Timestamp authSvcLmtEndTimeDay1, java.math.BigDecimal authSvcLmtDay2, java.sql.Timestamp authSvcLmtStartTimeDay2, java.sql.Timestamp authSvcLmtEndTimeDay2, java.math.BigDecimal authSvcLmtDay3, java.sql.Timestamp authSvcLmtStartTimeDay3, java.sql.Timestamp authSvcLmtEndTimeDay3, java.math.BigDecimal authSvcLmtDay4, java.sql.Timestamp authSvcLmtStartTimeDay4, java.sql.Timestamp authSvcLmtEndTimeDay4, java.math.BigDecimal authSvcLmtDay5, java.sql.Timestamp authSvcLmtStartTimeDay5, java.sql.Timestamp authSvcLmtEndTimeDay5, java.math.BigDecimal authSvcLmtDay6, java.sql.Timestamp authSvcLmtStartTimeDay6, java.sql.Timestamp authSvcLmtEndTimeDay6, java.math.BigDecimal authSvcLmtDay7, java.sql.Timestamp authSvcLmtStartTimeDay7, java.sql.Timestamp authSvcLmtEndTimeDay7, String payerId) throws SQLException
  { _init_struct(true);
    setAuthSvcSk(authSvcSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setChangeVersionId(changeVersionId);
    setAuthSk(authSk);
    setBeId(beId);
    setSvcName(svcName);
    setBillingCode(billingCode);
    setRateTypName(rateTypName);
    setRateQlfrCode(rateQlfrCode);
    setAuthSvcRateAmt(authSvcRateAmt);
    setAuthSvcStartDate(authSvcStartDate);
    setAuthSvcEndDate(authSvcEndDate);
    setAuthLmtTypName(authLmtTypName);
    setAuthSvcUnitName(authSvcUnitName);
    setAuthSvcLmt(authSvcLmt);
    setAuthSvcLmtDay1(authSvcLmtDay1);
    setAuthSvcLmtStartTimeDay1(authSvcLmtStartTimeDay1);
    setAuthSvcLmtEndTimeDay1(authSvcLmtEndTimeDay1);
    setAuthSvcLmtDay2(authSvcLmtDay2);
    setAuthSvcLmtStartTimeDay2(authSvcLmtStartTimeDay2);
    setAuthSvcLmtEndTimeDay2(authSvcLmtEndTimeDay2);
    setAuthSvcLmtDay3(authSvcLmtDay3);
    setAuthSvcLmtStartTimeDay3(authSvcLmtStartTimeDay3);
    setAuthSvcLmtEndTimeDay3(authSvcLmtEndTimeDay3);
    setAuthSvcLmtDay4(authSvcLmtDay4);
    setAuthSvcLmtStartTimeDay4(authSvcLmtStartTimeDay4);
    setAuthSvcLmtEndTimeDay4(authSvcLmtEndTimeDay4);
    setAuthSvcLmtDay5(authSvcLmtDay5);
    setAuthSvcLmtStartTimeDay5(authSvcLmtStartTimeDay5);
    setAuthSvcLmtEndTimeDay5(authSvcLmtEndTimeDay5);
    setAuthSvcLmtDay6(authSvcLmtDay6);
    setAuthSvcLmtStartTimeDay6(authSvcLmtStartTimeDay6);
    setAuthSvcLmtEndTimeDay6(authSvcLmtEndTimeDay6);
    setAuthSvcLmtDay7(authSvcLmtDay7);
    setAuthSvcLmtStartTimeDay7(authSvcLmtStartTimeDay7);
    setAuthSvcLmtEndTimeDay7(authSvcLmtEndTimeDay7);
    setPayerId(payerId);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AuthSvcT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AuthSvcT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAuthSvcSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAuthSvcSk(java.math.BigDecimal authSvcSk) throws SQLException
  { _struct.setAttribute(0, authSvcSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(3, changeVersionId); }


  public java.math.BigDecimal getAuthSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(4); }

  public void setAuthSk(java.math.BigDecimal authSk) throws SQLException
  { _struct.setAttribute(4, authSk); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(5, beId); }


  public String getSvcName() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setSvcName(String svcName) throws SQLException
  { _struct.setAttribute(6, svcName); }


  public String getBillingCode() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setBillingCode(String billingCode) throws SQLException
  { _struct.setAttribute(7, billingCode); }


  public String getRateTypName() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setRateTypName(String rateTypName) throws SQLException
  { _struct.setAttribute(8, rateTypName); }


  public String getRateQlfrCode() throws SQLException
  { return (String) _struct.getAttribute(9); }

  public void setRateQlfrCode(String rateQlfrCode) throws SQLException
  { _struct.setAttribute(9, rateQlfrCode); }


  public java.math.BigDecimal getAuthSvcRateAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(10); }

  public void setAuthSvcRateAmt(java.math.BigDecimal authSvcRateAmt) throws SQLException
  { _struct.setAttribute(10, authSvcRateAmt); }


  public java.sql.Timestamp getAuthSvcStartDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(11); }

  public void setAuthSvcStartDate(java.sql.Timestamp authSvcStartDate) throws SQLException
  { _struct.setAttribute(11, authSvcStartDate); }


  public java.sql.Timestamp getAuthSvcEndDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(12); }

  public void setAuthSvcEndDate(java.sql.Timestamp authSvcEndDate) throws SQLException
  { _struct.setAttribute(12, authSvcEndDate); }


  public String getAuthLmtTypName() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setAuthLmtTypName(String authLmtTypName) throws SQLException
  { _struct.setAttribute(13, authLmtTypName); }


  public String getAuthSvcUnitName() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setAuthSvcUnitName(String authSvcUnitName) throws SQLException
  { _struct.setAttribute(14, authSvcUnitName); }


  public java.math.BigDecimal getAuthSvcLmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(15); }

  public void setAuthSvcLmt(java.math.BigDecimal authSvcLmt) throws SQLException
  { _struct.setAttribute(15, authSvcLmt); }


  public java.math.BigDecimal getAuthSvcLmtDay1() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(16); }

  public void setAuthSvcLmtDay1(java.math.BigDecimal authSvcLmtDay1) throws SQLException
  { _struct.setAttribute(16, authSvcLmtDay1); }


  public java.sql.Timestamp getAuthSvcLmtStartTimeDay1() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(17); }

  public void setAuthSvcLmtStartTimeDay1(java.sql.Timestamp authSvcLmtStartTimeDay1) throws SQLException
  { _struct.setAttribute(17, authSvcLmtStartTimeDay1); }


  public java.sql.Timestamp getAuthSvcLmtEndTimeDay1() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(18); }

  public void setAuthSvcLmtEndTimeDay1(java.sql.Timestamp authSvcLmtEndTimeDay1) throws SQLException
  { _struct.setAttribute(18, authSvcLmtEndTimeDay1); }


  public java.math.BigDecimal getAuthSvcLmtDay2() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(19); }

  public void setAuthSvcLmtDay2(java.math.BigDecimal authSvcLmtDay2) throws SQLException
  { _struct.setAttribute(19, authSvcLmtDay2); }


  public java.sql.Timestamp getAuthSvcLmtStartTimeDay2() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(20); }

  public void setAuthSvcLmtStartTimeDay2(java.sql.Timestamp authSvcLmtStartTimeDay2) throws SQLException
  { _struct.setAttribute(20, authSvcLmtStartTimeDay2); }


  public java.sql.Timestamp getAuthSvcLmtEndTimeDay2() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(21); }

  public void setAuthSvcLmtEndTimeDay2(java.sql.Timestamp authSvcLmtEndTimeDay2) throws SQLException
  { _struct.setAttribute(21, authSvcLmtEndTimeDay2); }


  public java.math.BigDecimal getAuthSvcLmtDay3() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(22); }

  public void setAuthSvcLmtDay3(java.math.BigDecimal authSvcLmtDay3) throws SQLException
  { _struct.setAttribute(22, authSvcLmtDay3); }


  public java.sql.Timestamp getAuthSvcLmtStartTimeDay3() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(23); }

  public void setAuthSvcLmtStartTimeDay3(java.sql.Timestamp authSvcLmtStartTimeDay3) throws SQLException
  { _struct.setAttribute(23, authSvcLmtStartTimeDay3); }


  public java.sql.Timestamp getAuthSvcLmtEndTimeDay3() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(24); }

  public void setAuthSvcLmtEndTimeDay3(java.sql.Timestamp authSvcLmtEndTimeDay3) throws SQLException
  { _struct.setAttribute(24, authSvcLmtEndTimeDay3); }


  public java.math.BigDecimal getAuthSvcLmtDay4() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(25); }

  public void setAuthSvcLmtDay4(java.math.BigDecimal authSvcLmtDay4) throws SQLException
  { _struct.setAttribute(25, authSvcLmtDay4); }


  public java.sql.Timestamp getAuthSvcLmtStartTimeDay4() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(26); }

  public void setAuthSvcLmtStartTimeDay4(java.sql.Timestamp authSvcLmtStartTimeDay4) throws SQLException
  { _struct.setAttribute(26, authSvcLmtStartTimeDay4); }


  public java.sql.Timestamp getAuthSvcLmtEndTimeDay4() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(27); }

  public void setAuthSvcLmtEndTimeDay4(java.sql.Timestamp authSvcLmtEndTimeDay4) throws SQLException
  { _struct.setAttribute(27, authSvcLmtEndTimeDay4); }


  public java.math.BigDecimal getAuthSvcLmtDay5() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(28); }

  public void setAuthSvcLmtDay5(java.math.BigDecimal authSvcLmtDay5) throws SQLException
  { _struct.setAttribute(28, authSvcLmtDay5); }


  public java.sql.Timestamp getAuthSvcLmtStartTimeDay5() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(29); }

  public void setAuthSvcLmtStartTimeDay5(java.sql.Timestamp authSvcLmtStartTimeDay5) throws SQLException
  { _struct.setAttribute(29, authSvcLmtStartTimeDay5); }


  public java.sql.Timestamp getAuthSvcLmtEndTimeDay5() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(30); }

  public void setAuthSvcLmtEndTimeDay5(java.sql.Timestamp authSvcLmtEndTimeDay5) throws SQLException
  { _struct.setAttribute(30, authSvcLmtEndTimeDay5); }


  public java.math.BigDecimal getAuthSvcLmtDay6() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(31); }

  public void setAuthSvcLmtDay6(java.math.BigDecimal authSvcLmtDay6) throws SQLException
  { _struct.setAttribute(31, authSvcLmtDay6); }


  public java.sql.Timestamp getAuthSvcLmtStartTimeDay6() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(32); }

  public void setAuthSvcLmtStartTimeDay6(java.sql.Timestamp authSvcLmtStartTimeDay6) throws SQLException
  { _struct.setAttribute(32, authSvcLmtStartTimeDay6); }


  public java.sql.Timestamp getAuthSvcLmtEndTimeDay6() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(33); }

  public void setAuthSvcLmtEndTimeDay6(java.sql.Timestamp authSvcLmtEndTimeDay6) throws SQLException
  { _struct.setAttribute(33, authSvcLmtEndTimeDay6); }


  public java.math.BigDecimal getAuthSvcLmtDay7() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(34); }

  public void setAuthSvcLmtDay7(java.math.BigDecimal authSvcLmtDay7) throws SQLException
  { _struct.setAttribute(34, authSvcLmtDay7); }


  public java.sql.Timestamp getAuthSvcLmtStartTimeDay7() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(35); }

  public void setAuthSvcLmtStartTimeDay7(java.sql.Timestamp authSvcLmtStartTimeDay7) throws SQLException
  { _struct.setAttribute(35, authSvcLmtStartTimeDay7); }


  public java.sql.Timestamp getAuthSvcLmtEndTimeDay7() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(36); }

  public void setAuthSvcLmtEndTimeDay7(java.sql.Timestamp authSvcLmtEndTimeDay7) throws SQLException
  { _struct.setAttribute(36, authSvcLmtEndTimeDay7); }


  public String getPayerId() throws SQLException
  { return (String) _struct.getAttribute(37); }

  public void setPayerId(String payerId) throws SQLException
  { _struct.setAttribute(37, payerId); }

}
