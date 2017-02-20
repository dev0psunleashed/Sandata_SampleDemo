package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class AppUserHistT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.APP_USER_HIST_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,12,2,91,91,12,12,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[11];
  protected static final AppUserHistT _AppUserHistTFactory = new AppUserHistT();

  public static ORADataFactory getORADataFactory()
  { return _AppUserHistTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[11], _sqlType, _factory); }
  public AppUserHistT()
  { _init_struct(true); }
  public AppUserHistT(java.math.BigDecimal appUserHistSk, java.sql.Timestamp recChangeTmstp, String recChangeCode, java.math.BigDecimal appUserSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String userName, String userFirstName, String userMiddleName, String userLastName, String userGuid) throws SQLException
  { _init_struct(true);
    setAppUserHistSk(appUserHistSk);
    setRecChangeTmstp(recChangeTmstp);
    setRecChangeCode(recChangeCode);
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
  protected ORAData create(AppUserHistT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AppUserHistT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAppUserHistSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAppUserHistSk(java.math.BigDecimal appUserHistSk) throws SQLException
  { _struct.setAttribute(0, appUserHistSk); }


  public java.sql.Timestamp getRecChangeTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecChangeTmstp(java.sql.Timestamp recChangeTmstp) throws SQLException
  { _struct.setAttribute(1, recChangeTmstp); }


  public String getRecChangeCode() throws SQLException
  { return (String) _struct.getAttribute(2); }

  public void setRecChangeCode(String recChangeCode) throws SQLException
  { _struct.setAttribute(2, recChangeCode); }


  public java.math.BigDecimal getAppUserSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setAppUserSk(java.math.BigDecimal appUserSk) throws SQLException
  { _struct.setAttribute(3, appUserSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(4); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(4, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(5); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(5, recUpdateTmstp); }


  public String getUserName() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setUserName(String userName) throws SQLException
  { _struct.setAttribute(6, userName); }


  public String getUserFirstName() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setUserFirstName(String userFirstName) throws SQLException
  { _struct.setAttribute(7, userFirstName); }


  public String getUserMiddleName() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setUserMiddleName(String userMiddleName) throws SQLException
  { _struct.setAttribute(8, userMiddleName); }


  public String getUserLastName() throws SQLException
  { return (String) _struct.getAttribute(9); }

  public void setUserLastName(String userLastName) throws SQLException
  { _struct.setAttribute(9, userLastName); }


  public String getUserGuid() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setUserGuid(String userGuid) throws SQLException
  { _struct.setAttribute(10, userGuid); }

}
