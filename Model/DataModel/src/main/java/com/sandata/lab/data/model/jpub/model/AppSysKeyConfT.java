package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class AppSysKeyConfT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.APP_SYS_KEY_CONF_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[6];
  protected static final AppSysKeyConfT _AppSysKeyConfTFactory = new AppSysKeyConfT();

  public static ORADataFactory getORADataFactory()
  { return _AppSysKeyConfTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[6], _sqlType, _factory); }
  public AppSysKeyConfT()
  { _init_struct(true); }
  public AppSysKeyConfT(java.math.BigDecimal appSysKeyConfSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal appSysKeyConfParSk, String keyName, String sysKeyConfVal) throws SQLException
  { _init_struct(true);
    setAppSysKeyConfSk(appSysKeyConfSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setAppSysKeyConfParSk(appSysKeyConfParSk);
    setKeyName(keyName);
    setSysKeyConfVal(sysKeyConfVal);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AppSysKeyConfT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AppSysKeyConfT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAppSysKeyConfSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAppSysKeyConfSk(java.math.BigDecimal appSysKeyConfSk) throws SQLException
  { _struct.setAttribute(0, appSysKeyConfSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public java.math.BigDecimal getAppSysKeyConfParSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setAppSysKeyConfParSk(java.math.BigDecimal appSysKeyConfParSk) throws SQLException
  { _struct.setAttribute(3, appSysKeyConfParSk); }


  public String getKeyName() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setKeyName(String keyName) throws SQLException
  { _struct.setAttribute(4, keyName); }


  public String getSysKeyConfVal() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setSysKeyConfVal(String sysKeyConfVal) throws SQLException
  { _struct.setAttribute(5, sysKeyConfVal); }

}
