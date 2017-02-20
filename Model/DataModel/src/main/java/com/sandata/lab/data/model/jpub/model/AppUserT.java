package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class AppUserT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.APP_USER_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,12,12,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[8];
  protected static final AppUserT _AppUserTFactory = new AppUserT();

  public static ORADataFactory getORADataFactory()
  { return _AppUserTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[8], _sqlType, _factory); }
  public AppUserT()
  { _init_struct(true); }
  public AppUserT(java.math.BigDecimal appUserSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String userName, String userFirstName, String userMiddleName, String userLastName, String userGuid) throws SQLException
  { _init_struct(true);
    setAppUserSk(appUserSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setUserName(userName);
    setUserFirstName(userFirstName);
    setUserMiddleName(userMiddleName);
    setUserLastName(userLastName);
    setUserGuid(userGuid);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AppUserT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AppUserT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAppUserSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAppUserSk(java.math.BigDecimal appUserSk) throws SQLException
  { _struct.setAttribute(0, appUserSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public String getUserName() throws SQLException
  { return (String) _struct.getAttribute(3); }

  public void setUserName(String userName) throws SQLException
  { _struct.setAttribute(3, userName); }


  public String getUserFirstName() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setUserFirstName(String userFirstName) throws SQLException
  { _struct.setAttribute(4, userFirstName); }


  public String getUserMiddleName() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setUserMiddleName(String userMiddleName) throws SQLException
  { _struct.setAttribute(5, userMiddleName); }


  public String getUserLastName() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setUserLastName(String userLastName) throws SQLException
  { _struct.setAttribute(6, userLastName); }


  public String getUserGuid() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setUserGuid(String userGuid) throws SQLException
  { _struct.setAttribute(7, userGuid); }

}
