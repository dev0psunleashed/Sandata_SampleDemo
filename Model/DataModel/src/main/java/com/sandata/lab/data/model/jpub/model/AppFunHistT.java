package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class AppFunHistT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.APP_FUN_HIST_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,12,2,91,91,2,12,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[11];
  protected static final AppFunHistT _AppFunHistTFactory = new AppFunHistT();

  public static ORADataFactory getORADataFactory()
  { return _AppFunHistTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[11], _sqlType, _factory); }
  public AppFunHistT()
  { _init_struct(true); }
  public AppFunHistT(java.math.BigDecimal appFunHistSk, java.sql.Timestamp recChangeTmstp, String recChangeCode, java.math.BigDecimal appFunSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal appModSk, String funName, String funTypName, String funArea, String funScope) throws SQLException
  { _init_struct(true);
    setAppFunHistSk(appFunHistSk);
    setRecChangeTmstp(recChangeTmstp);
    setRecChangeCode(recChangeCode);
    setAppFunSk(appFunSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setAppModSk(appModSk);
    setFunName(funName);
    setFunTypName(funTypName);
    setFunArea(funArea);
    setFunScope(funScope);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AppFunHistT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AppFunHistT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAppFunHistSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAppFunHistSk(java.math.BigDecimal appFunHistSk) throws SQLException
  { _struct.setAttribute(0, appFunHistSk); }


  public java.sql.Timestamp getRecChangeTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecChangeTmstp(java.sql.Timestamp recChangeTmstp) throws SQLException
  { _struct.setAttribute(1, recChangeTmstp); }


  public String getRecChangeCode() throws SQLException
  { return (String) _struct.getAttribute(2); }

  public void setRecChangeCode(String recChangeCode) throws SQLException
  { _struct.setAttribute(2, recChangeCode); }


  public java.math.BigDecimal getAppFunSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setAppFunSk(java.math.BigDecimal appFunSk) throws SQLException
  { _struct.setAttribute(3, appFunSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(4); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(4, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(5); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(5, recUpdateTmstp); }


  public java.math.BigDecimal getAppModSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(6); }

  public void setAppModSk(java.math.BigDecimal appModSk) throws SQLException
  { _struct.setAttribute(6, appModSk); }


  public String getFunName() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setFunName(String funName) throws SQLException
  { _struct.setAttribute(7, funName); }


  public String getFunTypName() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setFunTypName(String funTypName) throws SQLException
  { _struct.setAttribute(8, funTypName); }


  public String getFunArea() throws SQLException
  { return (String) _struct.getAttribute(9); }

  public void setFunArea(String funArea) throws SQLException
  { _struct.setAttribute(9, funArea); }


  public String getFunScope() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setFunScope(String funScope) throws SQLException
  { _struct.setAttribute(10, funScope); }

}
