package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class AppTenantKeyConfT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.APP_TENANT_KEY_CONF_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,2,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[7];
  protected static final AppTenantKeyConfT _AppTenantKeyConfTFactory = new AppTenantKeyConfT();

  public static ORADataFactory getORADataFactory()
  { return _AppTenantKeyConfTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[7], _sqlType, _factory); }
  public AppTenantKeyConfT()
  { _init_struct(true); }
  public AppTenantKeyConfT(java.math.BigDecimal appTenantKeyConfSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal appTenantKeyConfParSk, java.math.BigDecimal appTenantSk, String keyName, String tenantKeyConfVal) throws SQLException
  { _init_struct(true);
    setAppTenantKeyConfSk(appTenantKeyConfSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setAppTenantKeyConfParSk(appTenantKeyConfParSk);
    setAppTenantSk(appTenantSk);
    setKeyName(keyName);
    setTenantKeyConfVal(tenantKeyConfVal);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AppTenantKeyConfT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AppTenantKeyConfT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAppTenantKeyConfSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAppTenantKeyConfSk(java.math.BigDecimal appTenantKeyConfSk) throws SQLException
  { _struct.setAttribute(0, appTenantKeyConfSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public java.math.BigDecimal getAppTenantKeyConfParSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setAppTenantKeyConfParSk(java.math.BigDecimal appTenantKeyConfParSk) throws SQLException
  { _struct.setAttribute(3, appTenantKeyConfParSk); }


  public java.math.BigDecimal getAppTenantSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(4); }

  public void setAppTenantSk(java.math.BigDecimal appTenantSk) throws SQLException
  { _struct.setAttribute(4, appTenantSk); }


  public String getKeyName() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setKeyName(String keyName) throws SQLException
  { _struct.setAttribute(5, keyName); }


  public String getTenantKeyConfVal() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setTenantKeyConfVal(String tenantKeyConfVal) throws SQLException
  { _struct.setAttribute(6, tenantKeyConfVal); }

}
