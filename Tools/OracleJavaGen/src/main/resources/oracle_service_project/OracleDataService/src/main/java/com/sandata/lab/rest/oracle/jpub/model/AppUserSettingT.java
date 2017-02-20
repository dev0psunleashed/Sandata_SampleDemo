package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class AppUserSettingT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.APP_USER_SETTING_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[3];
  protected static final AppUserSettingT _AppUserSettingTFactory = new AppUserSettingT();

  public static ORADataFactory getORADataFactory()
  { return _AppUserSettingTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[3], _sqlType, _factory); }
  public AppUserSettingT()
  { _init_struct(true); }
  public AppUserSettingT(String userGuid, String key, String val) throws SQLException
  { _init_struct(true);
    setUserGuid(userGuid);
    setKey(key);
    setVal(val);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AppUserSettingT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AppUserSettingT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public String getUserGuid() throws SQLException
  { return (String) _struct.getAttribute(0); }

  public void setUserGuid(String userGuid) throws SQLException
  { _struct.setAttribute(0, userGuid); }


  public String getKey() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setKey(String key) throws SQLException
  { _struct.setAttribute(1, key); }


  public String getVal() throws SQLException
  { return (String) _struct.getAttribute(2); }

  public void setVal(String val) throws SQLException
  { _struct.setAttribute(2, val); }

}
