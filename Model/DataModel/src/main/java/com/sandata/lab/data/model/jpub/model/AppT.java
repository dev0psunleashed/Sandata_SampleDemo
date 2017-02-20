package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class AppT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.APP_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[5];
  protected static final AppT _AppTFactory = new AppT();

  public static ORADataFactory getORADataFactory()
  { return _AppTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[5], _sqlType, _factory); }
  public AppT()
  { _init_struct(true); }
  public AppT(java.math.BigDecimal appSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String appName, String appDesc) throws SQLException
  { _init_struct(true);
    setAppSk(appSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setAppName(appName);
    setAppDesc(appDesc);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AppT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AppT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAppSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAppSk(java.math.BigDecimal appSk) throws SQLException
  { _struct.setAttribute(0, appSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public String getAppName() throws SQLException
  { return (String) _struct.getAttribute(3); }

  public void setAppName(String appName) throws SQLException
  { _struct.setAttribute(3, appName); }


  public String getAppDesc() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setAppDesc(String appDesc) throws SQLException
  { _struct.setAttribute(4, appDesc); }

}
