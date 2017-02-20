package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class AppTenantHistT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.APP_TENANT_HIST_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,12,2,91,91,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[8];
  protected static final AppTenantHistT _AppTenantHistTFactory = new AppTenantHistT();

  public static ORADataFactory getORADataFactory()
  { return _AppTenantHistTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[8], _sqlType, _factory); }
  public AppTenantHistT()
  { _init_struct(true); }
  public AppTenantHistT(java.math.BigDecimal appTenantHistSk, java.sql.Timestamp recChangeTmstp, String recChangeCode, java.math.BigDecimal appTenantSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String tenantName, String tenantTypName) throws SQLException
  { _init_struct(true);
    setAppTenantHistSk(appTenantHistSk);
    setRecChangeTmstp(recChangeTmstp);
    setRecChangeCode(recChangeCode);
    setAppTenantSk(appTenantSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setTenantName(tenantName);
    setTenantTypName(tenantTypName);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AppTenantHistT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AppTenantHistT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAppTenantHistSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAppTenantHistSk(java.math.BigDecimal appTenantHistSk) throws SQLException
  { _struct.setAttribute(0, appTenantHistSk); }


  public java.sql.Timestamp getRecChangeTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecChangeTmstp(java.sql.Timestamp recChangeTmstp) throws SQLException
  { _struct.setAttribute(1, recChangeTmstp); }


  public String getRecChangeCode() throws SQLException
  { return (String) _struct.getAttribute(2); }

  public void setRecChangeCode(String recChangeCode) throws SQLException
  { _struct.setAttribute(2, recChangeCode); }


  public java.math.BigDecimal getAppTenantSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setAppTenantSk(java.math.BigDecimal appTenantSk) throws SQLException
  { _struct.setAttribute(3, appTenantSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(4); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(4, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(5); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(5, recUpdateTmstp); }


  public String getTenantName() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setTenantName(String tenantName) throws SQLException
  { _struct.setAttribute(6, tenantName); }


  public String getTenantTypName() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setTenantTypName(String tenantTypName) throws SQLException
  { _struct.setAttribute(7, tenantTypName); }

}
