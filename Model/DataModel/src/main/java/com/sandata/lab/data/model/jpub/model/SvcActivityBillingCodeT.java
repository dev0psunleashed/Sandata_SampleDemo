package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class SvcActivityBillingCodeT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.SVC_ACTIVITY_BILLING_CODE_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,12,12,2,12,2,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[10];
  protected static final SvcActivityBillingCodeT _SvcActivityBillingCodeTFactory = new SvcActivityBillingCodeT();

  public static ORADataFactory getORADataFactory()
  { return _SvcActivityBillingCodeTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[10], _sqlType, _factory); }
  public SvcActivityBillingCodeT()
  { _init_struct(true); }
  public SvcActivityBillingCodeT(java.math.BigDecimal svcActivityBillingCodeSk, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recCreateTmstp, java.math.BigDecimal changeVersionId, String beId, String svcName, java.math.BigDecimal activitySk, String billingCode, java.math.BigDecimal billingRate, String billingRateUnit) throws SQLException
  { _init_struct(true);
    setSvcActivityBillingCodeSk(svcActivityBillingCodeSk);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecCreateTmstp(recCreateTmstp);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setSvcName(svcName);
    setActivitySk(activitySk);
    setBillingCode(billingCode);
    setBillingRate(billingRate);
    setBillingRateUnit(billingRateUnit);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(SvcActivityBillingCodeT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new SvcActivityBillingCodeT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getSvcActivityBillingCodeSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setSvcActivityBillingCodeSk(java.math.BigDecimal svcActivityBillingCodeSk) throws SQLException
  { _struct.setAttribute(0, svcActivityBillingCodeSk); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(1, recUpdateTmstp); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(2, recCreateTmstp); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(3, changeVersionId); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(4, beId); }


  public String getSvcName() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setSvcName(String svcName) throws SQLException
  { _struct.setAttribute(5, svcName); }


  public java.math.BigDecimal getActivitySk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(6); }

  public void setActivitySk(java.math.BigDecimal activitySk) throws SQLException
  { _struct.setAttribute(6, activitySk); }


  public String getBillingCode() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setBillingCode(String billingCode) throws SQLException
  { _struct.setAttribute(7, billingCode); }


  public java.math.BigDecimal getBillingRate() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(8); }

  public void setBillingRate(java.math.BigDecimal billingRate) throws SQLException
  { _struct.setAttribute(8, billingRate); }


  public String getBillingRateUnit() throws SQLException
  { return (String) _struct.getAttribute(9); }

  public void setBillingRateUnit(String billingRateUnit) throws SQLException
  { _struct.setAttribute(9, billingRateUnit); }

}
