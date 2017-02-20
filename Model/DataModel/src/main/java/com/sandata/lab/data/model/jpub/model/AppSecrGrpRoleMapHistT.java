package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class AppSecrGrpRoleMapHistT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.APP_SECR_GRP_ROLE_MAP_HIST_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,12,2,91,91,2,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[8];
  protected static final AppSecrGrpRoleMapHistT _AppSecrGrpRoleMapHistTFactory = new AppSecrGrpRoleMapHistT();

  public static ORADataFactory getORADataFactory()
  { return _AppSecrGrpRoleMapHistTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[8], _sqlType, _factory); }
  public AppSecrGrpRoleMapHistT()
  { _init_struct(true); }
  public AppSecrGrpRoleMapHistT(java.math.BigDecimal appSecrGrpRoleMapHistSk, java.sql.Timestamp recChangeTmstp, String recChangeCode, java.math.BigDecimal appSecrGrpRoleMapSk, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recCreateTmstp, java.math.BigDecimal appSecrGrpSk, java.math.BigDecimal appRoleSk) throws SQLException
  { _init_struct(true);
    setAppSecrGrpRoleMapHistSk(appSecrGrpRoleMapHistSk);
    setRecChangeTmstp(recChangeTmstp);
    setRecChangeCode(recChangeCode);
    setAppSecrGrpRoleMapSk(appSecrGrpRoleMapSk);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecCreateTmstp(recCreateTmstp);
    setAppSecrGrpSk(appSecrGrpSk);
    setAppRoleSk(appRoleSk);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AppSecrGrpRoleMapHistT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AppSecrGrpRoleMapHistT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAppSecrGrpRoleMapHistSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAppSecrGrpRoleMapHistSk(java.math.BigDecimal appSecrGrpRoleMapHistSk) throws SQLException
  { _struct.setAttribute(0, appSecrGrpRoleMapHistSk); }


  public java.sql.Timestamp getRecChangeTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecChangeTmstp(java.sql.Timestamp recChangeTmstp) throws SQLException
  { _struct.setAttribute(1, recChangeTmstp); }


  public String getRecChangeCode() throws SQLException
  { return (String) _struct.getAttribute(2); }

  public void setRecChangeCode(String recChangeCode) throws SQLException
  { _struct.setAttribute(2, recChangeCode); }


  public java.math.BigDecimal getAppSecrGrpRoleMapSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setAppSecrGrpRoleMapSk(java.math.BigDecimal appSecrGrpRoleMapSk) throws SQLException
  { _struct.setAttribute(3, appSecrGrpRoleMapSk); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(4); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(4, recUpdateTmstp); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(5); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(5, recCreateTmstp); }


  public java.math.BigDecimal getAppSecrGrpSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(6); }

  public void setAppSecrGrpSk(java.math.BigDecimal appSecrGrpSk) throws SQLException
  { _struct.setAttribute(6, appSecrGrpSk); }


  public java.math.BigDecimal getAppRoleSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(7); }

  public void setAppRoleSk(java.math.BigDecimal appRoleSk) throws SQLException
  { _struct.setAttribute(7, appRoleSk); }

}
