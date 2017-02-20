package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class OrdSvcT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.ORD_SVC_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,2,12,12,12,12,12,2,91,91,12,12,2,2,91,91,2,91,91,2,91,91,2,91,91,2,91,91,2,91,91,2,91,91,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[38];
  protected static final OrdSvcT _OrdSvcTFactory = new OrdSvcT();

  public static ORADataFactory getORADataFactory()
  { return _OrdSvcTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[38], _sqlType, _factory); }
  public OrdSvcT()
  { _init_struct(true); }
  public OrdSvcT(java.math.BigDecimal ordSvcSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal changeVersionId, java.math.BigDecimal ordSk, String beId, String svcName, String billingCode, String rateTypName, String rateQlfrCode, java.math.BigDecimal ordSvcRateAmt, java.sql.Timestamp ordSvcStartDate, java.sql.Timestamp ordSvcEndDate, String ordLmtTypName, String ordSvcUnitName, java.math.BigDecimal ordSvcLmt, java.math.BigDecimal ordSvcLmtDay1, java.sql.Timestamp ordSvcLmtStartTimeDay1, java.sql.Timestamp ordSvcLmtEndTimeDay1, java.math.BigDecimal ordSvcLmtDay2, java.sql.Timestamp ordSvcLmtStartTimeDay2, java.sql.Timestamp ordSvcLmtEndTimeDay2, java.math.BigDecimal ordSvcLmtDay3, java.sql.Timestamp ordSvcLmtStartTimeDay3, java.sql.Timestamp ordSvcLmtEndTimeDay3, java.math.BigDecimal ordSvcLmtDay4, java.sql.Timestamp ordSvcLmtStartTimeDay4, java.sql.Timestamp ordSvcLmtEndTimeDay4, java.math.BigDecimal ordSvcLmtDay5, java.sql.Timestamp ordSvcLmtStartTimeDay5, java.sql.Timestamp ordSvcLmtEndTimeDay5, java.math.BigDecimal ordSvcLmtDay6, java.sql.Timestamp ordSvcLmtStartTimeDay6, java.sql.Timestamp ordSvcLmtEndTimeDay6, java.math.BigDecimal ordSvcLmtDay7, java.sql.Timestamp ordSvcLmtStartTimeDay7, java.sql.Timestamp ordSvcLmtEndTimeDay7, String payerId) throws SQLException
  { _init_struct(true);
    setOrdSvcSk(ordSvcSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setChangeVersionId(changeVersionId);
    setOrdSk(ordSk);
    setBeId(beId);
    setSvcName(svcName);
    setBillingCode(billingCode);
    setRateTypName(rateTypName);
    setRateQlfrCode(rateQlfrCode);
    setOrdSvcRateAmt(ordSvcRateAmt);
    setOrdSvcStartDate(ordSvcStartDate);
    setOrdSvcEndDate(ordSvcEndDate);
    setOrdLmtTypName(ordLmtTypName);
    setOrdSvcUnitName(ordSvcUnitName);
    setOrdSvcLmt(ordSvcLmt);
    setOrdSvcLmtDay1(ordSvcLmtDay1);
    setOrdSvcLmtStartTimeDay1(ordSvcLmtStartTimeDay1);
    setOrdSvcLmtEndTimeDay1(ordSvcLmtEndTimeDay1);
    setOrdSvcLmtDay2(ordSvcLmtDay2);
    setOrdSvcLmtStartTimeDay2(ordSvcLmtStartTimeDay2);
    setOrdSvcLmtEndTimeDay2(ordSvcLmtEndTimeDay2);
    setOrdSvcLmtDay3(ordSvcLmtDay3);
    setOrdSvcLmtStartTimeDay3(ordSvcLmtStartTimeDay3);
    setOrdSvcLmtEndTimeDay3(ordSvcLmtEndTimeDay3);
    setOrdSvcLmtDay4(ordSvcLmtDay4);
    setOrdSvcLmtStartTimeDay4(ordSvcLmtStartTimeDay4);
    setOrdSvcLmtEndTimeDay4(ordSvcLmtEndTimeDay4);
    setOrdSvcLmtDay5(ordSvcLmtDay5);
    setOrdSvcLmtStartTimeDay5(ordSvcLmtStartTimeDay5);
    setOrdSvcLmtEndTimeDay5(ordSvcLmtEndTimeDay5);
    setOrdSvcLmtDay6(ordSvcLmtDay6);
    setOrdSvcLmtStartTimeDay6(ordSvcLmtStartTimeDay6);
    setOrdSvcLmtEndTimeDay6(ordSvcLmtEndTimeDay6);
    setOrdSvcLmtDay7(ordSvcLmtDay7);
    setOrdSvcLmtStartTimeDay7(ordSvcLmtStartTimeDay7);
    setOrdSvcLmtEndTimeDay7(ordSvcLmtEndTimeDay7);
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
  protected ORAData create(OrdSvcT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new OrdSvcT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getOrdSvcSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setOrdSvcSk(java.math.BigDecimal ordSvcSk) throws SQLException
  { _struct.setAttribute(0, ordSvcSk); }


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


  public java.math.BigDecimal getOrdSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(4); }

  public void setOrdSk(java.math.BigDecimal ordSk) throws SQLException
  { _struct.setAttribute(4, ordSk); }


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


  public java.math.BigDecimal getOrdSvcRateAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(10); }

  public void setOrdSvcRateAmt(java.math.BigDecimal ordSvcRateAmt) throws SQLException
  { _struct.setAttribute(10, ordSvcRateAmt); }


  public java.sql.Timestamp getOrdSvcStartDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(11); }

  public void setOrdSvcStartDate(java.sql.Timestamp ordSvcStartDate) throws SQLException
  { _struct.setAttribute(11, ordSvcStartDate); }


  public java.sql.Timestamp getOrdSvcEndDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(12); }

  public void setOrdSvcEndDate(java.sql.Timestamp ordSvcEndDate) throws SQLException
  { _struct.setAttribute(12, ordSvcEndDate); }


  public String getOrdLmtTypName() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setOrdLmtTypName(String ordLmtTypName) throws SQLException
  { _struct.setAttribute(13, ordLmtTypName); }


  public String getOrdSvcUnitName() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setOrdSvcUnitName(String ordSvcUnitName) throws SQLException
  { _struct.setAttribute(14, ordSvcUnitName); }


  public java.math.BigDecimal getOrdSvcLmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(15); }

  public void setOrdSvcLmt(java.math.BigDecimal ordSvcLmt) throws SQLException
  { _struct.setAttribute(15, ordSvcLmt); }


  public java.math.BigDecimal getOrdSvcLmtDay1() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(16); }

  public void setOrdSvcLmtDay1(java.math.BigDecimal ordSvcLmtDay1) throws SQLException
  { _struct.setAttribute(16, ordSvcLmtDay1); }


  public java.sql.Timestamp getOrdSvcLmtStartTimeDay1() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(17); }

  public void setOrdSvcLmtStartTimeDay1(java.sql.Timestamp ordSvcLmtStartTimeDay1) throws SQLException
  { _struct.setAttribute(17, ordSvcLmtStartTimeDay1); }


  public java.sql.Timestamp getOrdSvcLmtEndTimeDay1() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(18); }

  public void setOrdSvcLmtEndTimeDay1(java.sql.Timestamp ordSvcLmtEndTimeDay1) throws SQLException
  { _struct.setAttribute(18, ordSvcLmtEndTimeDay1); }


  public java.math.BigDecimal getOrdSvcLmtDay2() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(19); }

  public void setOrdSvcLmtDay2(java.math.BigDecimal ordSvcLmtDay2) throws SQLException
  { _struct.setAttribute(19, ordSvcLmtDay2); }


  public java.sql.Timestamp getOrdSvcLmtStartTimeDay2() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(20); }

  public void setOrdSvcLmtStartTimeDay2(java.sql.Timestamp ordSvcLmtStartTimeDay2) throws SQLException
  { _struct.setAttribute(20, ordSvcLmtStartTimeDay2); }


  public java.sql.Timestamp getOrdSvcLmtEndTimeDay2() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(21); }

  public void setOrdSvcLmtEndTimeDay2(java.sql.Timestamp ordSvcLmtEndTimeDay2) throws SQLException
  { _struct.setAttribute(21, ordSvcLmtEndTimeDay2); }


  public java.math.BigDecimal getOrdSvcLmtDay3() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(22); }

  public void setOrdSvcLmtDay3(java.math.BigDecimal ordSvcLmtDay3) throws SQLException
  { _struct.setAttribute(22, ordSvcLmtDay3); }


  public java.sql.Timestamp getOrdSvcLmtStartTimeDay3() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(23); }

  public void setOrdSvcLmtStartTimeDay3(java.sql.Timestamp ordSvcLmtStartTimeDay3) throws SQLException
  { _struct.setAttribute(23, ordSvcLmtStartTimeDay3); }


  public java.sql.Timestamp getOrdSvcLmtEndTimeDay3() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(24); }

  public void setOrdSvcLmtEndTimeDay3(java.sql.Timestamp ordSvcLmtEndTimeDay3) throws SQLException
  { _struct.setAttribute(24, ordSvcLmtEndTimeDay3); }


  public java.math.BigDecimal getOrdSvcLmtDay4() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(25); }

  public void setOrdSvcLmtDay4(java.math.BigDecimal ordSvcLmtDay4) throws SQLException
  { _struct.setAttribute(25, ordSvcLmtDay4); }


  public java.sql.Timestamp getOrdSvcLmtStartTimeDay4() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(26); }

  public void setOrdSvcLmtStartTimeDay4(java.sql.Timestamp ordSvcLmtStartTimeDay4) throws SQLException
  { _struct.setAttribute(26, ordSvcLmtStartTimeDay4); }


  public java.sql.Timestamp getOrdSvcLmtEndTimeDay4() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(27); }

  public void setOrdSvcLmtEndTimeDay4(java.sql.Timestamp ordSvcLmtEndTimeDay4) throws SQLException
  { _struct.setAttribute(27, ordSvcLmtEndTimeDay4); }


  public java.math.BigDecimal getOrdSvcLmtDay5() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(28); }

  public void setOrdSvcLmtDay5(java.math.BigDecimal ordSvcLmtDay5) throws SQLException
  { _struct.setAttribute(28, ordSvcLmtDay5); }


  public java.sql.Timestamp getOrdSvcLmtStartTimeDay5() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(29); }

  public void setOrdSvcLmtStartTimeDay5(java.sql.Timestamp ordSvcLmtStartTimeDay5) throws SQLException
  { _struct.setAttribute(29, ordSvcLmtStartTimeDay5); }


  public java.sql.Timestamp getOrdSvcLmtEndTimeDay5() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(30); }

  public void setOrdSvcLmtEndTimeDay5(java.sql.Timestamp ordSvcLmtEndTimeDay5) throws SQLException
  { _struct.setAttribute(30, ordSvcLmtEndTimeDay5); }


  public java.math.BigDecimal getOrdSvcLmtDay6() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(31); }

  public void setOrdSvcLmtDay6(java.math.BigDecimal ordSvcLmtDay6) throws SQLException
  { _struct.setAttribute(31, ordSvcLmtDay6); }


  public java.sql.Timestamp getOrdSvcLmtStartTimeDay6() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(32); }

  public void setOrdSvcLmtStartTimeDay6(java.sql.Timestamp ordSvcLmtStartTimeDay6) throws SQLException
  { _struct.setAttribute(32, ordSvcLmtStartTimeDay6); }


  public java.sql.Timestamp getOrdSvcLmtEndTimeDay6() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(33); }

  public void setOrdSvcLmtEndTimeDay6(java.sql.Timestamp ordSvcLmtEndTimeDay6) throws SQLException
  { _struct.setAttribute(33, ordSvcLmtEndTimeDay6); }


  public java.math.BigDecimal getOrdSvcLmtDay7() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(34); }

  public void setOrdSvcLmtDay7(java.math.BigDecimal ordSvcLmtDay7) throws SQLException
  { _struct.setAttribute(34, ordSvcLmtDay7); }


  public java.sql.Timestamp getOrdSvcLmtStartTimeDay7() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(35); }

  public void setOrdSvcLmtStartTimeDay7(java.sql.Timestamp ordSvcLmtStartTimeDay7) throws SQLException
  { _struct.setAttribute(35, ordSvcLmtStartTimeDay7); }


  public java.sql.Timestamp getOrdSvcLmtEndTimeDay7() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(36); }

  public void setOrdSvcLmtEndTimeDay7(java.sql.Timestamp ordSvcLmtEndTimeDay7) throws SQLException
  { _struct.setAttribute(36, ordSvcLmtEndTimeDay7); }


  public String getPayerId() throws SQLException
  { return (String) _struct.getAttribute(37); }

  public void setPayerId(String payerId) throws SQLException
  { _struct.setAttribute(37, payerId); }

}
