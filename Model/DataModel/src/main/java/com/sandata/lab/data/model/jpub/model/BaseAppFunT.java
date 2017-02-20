package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class BaseAppFunT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.BASE_APP_FUN_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,2,2,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[7];
  protected static final BaseAppFunT _BaseAppFunTFactory = new BaseAppFunT();

  public static ORADataFactory getORADataFactory()
  { return _BaseAppFunTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[7], _sqlType, _factory); }
  public BaseAppFunT()
  { _init_struct(true); }
  public BaseAppFunT(java.math.BigDecimal baseAppFunSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal appSecrEltSk, java.math.BigDecimal appTenantSk, java.math.BigDecimal appRoleSk, java.math.BigDecimal baseAppPrivSk) throws SQLException
  { _init_struct(true);
    setBaseAppFunSk(baseAppFunSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setAppSecrEltSk(appSecrEltSk);
    setAppTenantSk(appTenantSk);
    setAppRoleSk(appRoleSk);
    setBaseAppPrivSk(baseAppPrivSk);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(BaseAppFunT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new BaseAppFunT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getBaseAppFunSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setBaseAppFunSk(java.math.BigDecimal baseAppFunSk) throws SQLException
  { _struct.setAttribute(0, baseAppFunSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public java.math.BigDecimal getAppSecrEltSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setAppSecrEltSk(java.math.BigDecimal appSecrEltSk) throws SQLException
  { _struct.setAttribute(3, appSecrEltSk); }


  public java.math.BigDecimal getAppTenantSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(4); }

  public void setAppTenantSk(java.math.BigDecimal appTenantSk) throws SQLException
  { _struct.setAttribute(4, appTenantSk); }


  public java.math.BigDecimal getAppRoleSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(5); }

  public void setAppRoleSk(java.math.BigDecimal appRoleSk) throws SQLException
  { _struct.setAttribute(5, appRoleSk); }


  public java.math.BigDecimal getBaseAppPrivSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(6); }

  public void setBaseAppPrivSk(java.math.BigDecimal baseAppPrivSk) throws SQLException
  { _struct.setAttribute(6, baseAppPrivSk); }

}
