package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class AppLogT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.APP_LOG_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,12,12,2,2,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[10];
  protected static final AppLogT _AppLogTFactory = new AppLogT();

  public static ORADataFactory getORADataFactory()
  { return _AppLogTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[10], _sqlType, _factory); }
  public AppLogT()
  { _init_struct(true); }
  public AppLogT(java.math.BigDecimal appLogSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal appSessSk, String logHost, String logProcess, java.math.BigDecimal logPid, java.math.BigDecimal logThread, String logLvl, String logMsg) throws SQLException
  { _init_struct(true);
    setAppLogSk(appLogSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setAppSessSk(appSessSk);
    setLogHost(logHost);
    setLogProcess(logProcess);
    setLogPid(logPid);
    setLogThread(logThread);
    setLogLvl(logLvl);
    setLogMsg(logMsg);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AppLogT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AppLogT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAppLogSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAppLogSk(java.math.BigDecimal appLogSk) throws SQLException
  { _struct.setAttribute(0, appLogSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public java.math.BigDecimal getAppSessSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setAppSessSk(java.math.BigDecimal appSessSk) throws SQLException
  { _struct.setAttribute(3, appSessSk); }


  public String getLogHost() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setLogHost(String logHost) throws SQLException
  { _struct.setAttribute(4, logHost); }


  public String getLogProcess() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setLogProcess(String logProcess) throws SQLException
  { _struct.setAttribute(5, logProcess); }


  public java.math.BigDecimal getLogPid() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(6); }

  public void setLogPid(java.math.BigDecimal logPid) throws SQLException
  { _struct.setAttribute(6, logPid); }


  public java.math.BigDecimal getLogThread() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(7); }

  public void setLogThread(java.math.BigDecimal logThread) throws SQLException
  { _struct.setAttribute(7, logThread); }


  public String getLogLvl() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setLogLvl(String logLvl) throws SQLException
  { _struct.setAttribute(8, logLvl); }


  public String getLogMsg() throws SQLException
  { return (String) _struct.getAttribute(9); }

  public void setLogMsg(String logMsg) throws SQLException
  { _struct.setAttribute(9, logMsg); }

}
