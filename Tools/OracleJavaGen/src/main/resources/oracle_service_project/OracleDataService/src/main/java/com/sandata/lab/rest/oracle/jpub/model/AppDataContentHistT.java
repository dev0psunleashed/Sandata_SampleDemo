package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class AppDataContentHistT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.APP_DATA_CONTENT_HIST_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,12,2,91,91,2,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[9];
  protected static final AppDataContentHistT _AppDataContentHistTFactory = new AppDataContentHistT();

  public static ORADataFactory getORADataFactory()
  { return _AppDataContentHistTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[9], _sqlType, _factory); }
  public AppDataContentHistT()
  { _init_struct(true); }
  public AppDataContentHistT(java.math.BigDecimal appDataContentHistSk, java.sql.Timestamp recChangeTmstp, String recChangeCode, java.math.BigDecimal appDataContentSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal appTenantSk, String masterName, String masterId) throws SQLException
  { _init_struct(true);
    setAppDataContentHistSk(appDataContentHistSk);
    setRecChangeTmstp(recChangeTmstp);
    setRecChangeCode(recChangeCode);
    setAppDataContentSk(appDataContentSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setAppTenantSk(appTenantSk);
    setMasterName(masterName);
    setMasterId(masterId);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AppDataContentHistT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AppDataContentHistT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAppDataContentHistSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAppDataContentHistSk(java.math.BigDecimal appDataContentHistSk) throws SQLException
  { _struct.setAttribute(0, appDataContentHistSk); }


  public java.sql.Timestamp getRecChangeTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecChangeTmstp(java.sql.Timestamp recChangeTmstp) throws SQLException
  { _struct.setAttribute(1, recChangeTmstp); }


  public String getRecChangeCode() throws SQLException
  { return (String) _struct.getAttribute(2); }

  public void setRecChangeCode(String recChangeCode) throws SQLException
  { _struct.setAttribute(2, recChangeCode); }


  public java.math.BigDecimal getAppDataContentSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setAppDataContentSk(java.math.BigDecimal appDataContentSk) throws SQLException
  { _struct.setAttribute(3, appDataContentSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(4); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(4, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(5); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(5, recUpdateTmstp); }


  public java.math.BigDecimal getAppTenantSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(6); }

  public void setAppTenantSk(java.math.BigDecimal appTenantSk) throws SQLException
  { _struct.setAttribute(6, appTenantSk); }


  public String getMasterName() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setMasterName(String masterName) throws SQLException
  { _struct.setAttribute(7, masterName); }


  public String getMasterId() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setMasterId(String masterId) throws SQLException
  { _struct.setAttribute(8, masterId); }

}
