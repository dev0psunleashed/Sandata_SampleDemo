package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class AppUserKeyConfT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.APP_USER_KEY_CONF_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[7];
  protected static final AppUserKeyConfT _AppUserKeyConfTFactory = new AppUserKeyConfT();

  public static ORADataFactory getORADataFactory()
  { return _AppUserKeyConfTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[7], _sqlType, _factory); }
  public AppUserKeyConfT()
  { _init_struct(true); }
  public AppUserKeyConfT(java.math.BigDecimal appUserKeyConfSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal appUserKeyConfParSk, String userGuid, String keyName, String userKeyConfVal) throws SQLException
  { _init_struct(true);
    setAppUserKeyConfSk(appUserKeyConfSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setAppUserKeyConfParSk(appUserKeyConfParSk);
    setUserGuid(userGuid);
    setKeyName(keyName);
    setUserKeyConfVal(userKeyConfVal);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AppUserKeyConfT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AppUserKeyConfT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAppUserKeyConfSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAppUserKeyConfSk(java.math.BigDecimal appUserKeyConfSk) throws SQLException
  { _struct.setAttribute(0, appUserKeyConfSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public java.math.BigDecimal getAppUserKeyConfParSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setAppUserKeyConfParSk(java.math.BigDecimal appUserKeyConfParSk) throws SQLException
  { _struct.setAttribute(3, appUserKeyConfParSk); }


  public String getUserGuid() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setUserGuid(String userGuid) throws SQLException
  { _struct.setAttribute(4, userGuid); }


  public String getKeyName() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setKeyName(String keyName) throws SQLException
  { _struct.setAttribute(5, keyName); }


  public String getUserKeyConfVal() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setUserKeyConfVal(String userKeyConfVal) throws SQLException
  { _struct.setAttribute(6, userKeyConfVal); }

}
