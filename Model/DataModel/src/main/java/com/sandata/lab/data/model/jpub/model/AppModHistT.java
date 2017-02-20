package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class AppModHistT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.APP_MOD_HIST_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,12,2,91,91,2,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[9];
  protected static final AppModHistT _AppModHistTFactory = new AppModHistT();

  public static ORADataFactory getORADataFactory()
  { return _AppModHistTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[9], _sqlType, _factory); }
  public AppModHistT()
  { _init_struct(true); }
  public AppModHistT(java.math.BigDecimal appModHistSk, java.sql.Timestamp recChangeTmstp, String recChangeCode, java.math.BigDecimal appModSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal appModParSk, String modName, String modTypName) throws SQLException
  { _init_struct(true);
    setAppModHistSk(appModHistSk);
    setRecChangeTmstp(recChangeTmstp);
    setRecChangeCode(recChangeCode);
    setAppModSk(appModSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setAppModParSk(appModParSk);
    setModName(modName);
    setModTypName(modTypName);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AppModHistT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AppModHistT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAppModHistSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAppModHistSk(java.math.BigDecimal appModHistSk) throws SQLException
  { _struct.setAttribute(0, appModHistSk); }


  public java.sql.Timestamp getRecChangeTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecChangeTmstp(java.sql.Timestamp recChangeTmstp) throws SQLException
  { _struct.setAttribute(1, recChangeTmstp); }


  public String getRecChangeCode() throws SQLException
  { return (String) _struct.getAttribute(2); }

  public void setRecChangeCode(String recChangeCode) throws SQLException
  { _struct.setAttribute(2, recChangeCode); }


  public java.math.BigDecimal getAppModSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setAppModSk(java.math.BigDecimal appModSk) throws SQLException
  { _struct.setAttribute(3, appModSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(4); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(4, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(5); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(5, recUpdateTmstp); }


  public java.math.BigDecimal getAppModParSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(6); }

  public void setAppModParSk(java.math.BigDecimal appModParSk) throws SQLException
  { _struct.setAttribute(6, appModParSk); }


  public String getModName() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setModName(String modName) throws SQLException
  { _struct.setAttribute(7, modName); }


  public String getModTypName() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setModTypName(String modTypName) throws SQLException
  { _struct.setAttribute(8, modTypName); }

}
