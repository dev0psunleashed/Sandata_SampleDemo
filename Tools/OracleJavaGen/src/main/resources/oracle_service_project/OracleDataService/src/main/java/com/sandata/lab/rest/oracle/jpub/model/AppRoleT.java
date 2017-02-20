package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class AppRoleT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.APP_ROLE_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,12,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[6];
  protected static final AppRoleT _AppRoleTFactory = new AppRoleT();

  public static ORADataFactory getORADataFactory()
  { return _AppRoleTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[6], _sqlType, _factory); }
  public AppRoleT()
  { _init_struct(true); }
  public AppRoleT(java.math.BigDecimal appRoleSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal appTenantSk, String roleName, java.math.BigDecimal canImpersInd) throws SQLException
  { _init_struct(true);
    setAppRoleSk(appRoleSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setAppTenantSk(appTenantSk);
    setRoleName(roleName);
    setCanImpersInd(canImpersInd);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AppRoleT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AppRoleT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAppRoleSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAppRoleSk(java.math.BigDecimal appRoleSk) throws SQLException
  { _struct.setAttribute(0, appRoleSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public java.math.BigDecimal getAppTenantSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setAppTenantSk(java.math.BigDecimal appTenantSk) throws SQLException
  { _struct.setAttribute(3, appTenantSk); }


  public String getRoleName() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setRoleName(String roleName) throws SQLException
  { _struct.setAttribute(4, roleName); }


  public java.math.BigDecimal getCanImpersInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(5); }

  public void setCanImpersInd(java.math.BigDecimal canImpersInd) throws SQLException
  { _struct.setAttribute(5, canImpersInd); }

}
