package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class AppSessT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.APP_SESS_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,12,93 };
  protected static ORADataFactory[] _factory = new ORADataFactory[5];
  protected static final AppSessT _AppSessTFactory = new AppSessT();

  public static ORADataFactory getORADataFactory()
  { return _AppSessTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[5], _sqlType, _factory); }
  public AppSessT()
  { _init_struct(true); }
  public AppSessT(java.math.BigDecimal appSessSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String userGuid, java.sql.Timestamp lastRenewedTmstp) throws SQLException
  { _init_struct(true);
    setAppSessSk(appSessSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setUserGuid(userGuid);
    setLastRenewedTmstp(lastRenewedTmstp);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AppSessT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AppSessT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAppSessSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAppSessSk(java.math.BigDecimal appSessSk) throws SQLException
  { _struct.setAttribute(0, appSessSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public String getUserGuid() throws SQLException
  { return (String) _struct.getAttribute(3); }

  public void setUserGuid(String userGuid) throws SQLException
  { _struct.setAttribute(3, userGuid); }


  public java.sql.Timestamp getLastRenewedTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(4); }

  public void setLastRenewedTmstp(java.sql.Timestamp lastRenewedTmstp) throws SQLException
  { _struct.setAttribute(4, lastRenewedTmstp); }

}
