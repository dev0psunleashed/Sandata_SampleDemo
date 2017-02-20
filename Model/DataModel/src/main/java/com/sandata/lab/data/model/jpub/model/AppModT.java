package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class AppModT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.APP_MOD_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[7];
  protected static final AppModT _AppModTFactory = new AppModT();

  public static ORADataFactory getORADataFactory()
  { return _AppModTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[7], _sqlType, _factory); }
  public AppModT()
  { _init_struct(true); }
  public AppModT(java.math.BigDecimal appModSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal appModParSk, String modName, String modTypName, String appName) throws SQLException
  { _init_struct(true);
    setAppModSk(appModSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setAppModParSk(appModParSk);
    setModName(modName);
    setModTypName(modTypName);
    setAppName(appName);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AppModT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AppModT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAppModSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAppModSk(java.math.BigDecimal appModSk) throws SQLException
  { _struct.setAttribute(0, appModSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public java.math.BigDecimal getAppModParSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setAppModParSk(java.math.BigDecimal appModParSk) throws SQLException
  { _struct.setAttribute(3, appModParSk); }


  public String getModName() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setModName(String modName) throws SQLException
  { _struct.setAttribute(4, modName); }


  public String getModTypName() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setModTypName(String modTypName) throws SQLException
  { _struct.setAttribute(5, modTypName); }


  public String getAppName() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setAppName(String appName) throws SQLException
  { _struct.setAttribute(6, appName); }

}
