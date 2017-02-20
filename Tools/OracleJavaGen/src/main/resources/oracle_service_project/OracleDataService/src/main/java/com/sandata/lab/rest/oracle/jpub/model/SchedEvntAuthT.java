package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class SchedEvntAuthT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.SCHED_EVNT_AUTH_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,2,2,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[7];
  protected static final SchedEvntAuthT _SchedEvntAuthTFactory = new SchedEvntAuthT();

  public static ORADataFactory getORADataFactory()
  { return _SchedEvntAuthTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[7], _sqlType, _factory); }
  public SchedEvntAuthT()
  { _init_struct(true); }
  public SchedEvntAuthT(java.math.BigDecimal schedEvntAuthSk, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recCreateTmstp, java.math.BigDecimal changeVersionId, java.math.BigDecimal schedEvntSk, java.math.BigDecimal authSk, String authQlfr) throws SQLException
  { _init_struct(true);
    setSchedEvntAuthSk(schedEvntAuthSk);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecCreateTmstp(recCreateTmstp);
    setChangeVersionId(changeVersionId);
    setSchedEvntSk(schedEvntSk);
    setAuthSk(authSk);
    setAuthQlfr(authQlfr);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(SchedEvntAuthT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new SchedEvntAuthT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getSchedEvntAuthSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setSchedEvntAuthSk(java.math.BigDecimal schedEvntAuthSk) throws SQLException
  { _struct.setAttribute(0, schedEvntAuthSk); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(1, recUpdateTmstp); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(2, recCreateTmstp); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(3, changeVersionId); }


  public java.math.BigDecimal getSchedEvntSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(4); }

  public void setSchedEvntSk(java.math.BigDecimal schedEvntSk) throws SQLException
  { _struct.setAttribute(4, schedEvntSk); }


  public java.math.BigDecimal getAuthSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(5); }

  public void setAuthSk(java.math.BigDecimal authSk) throws SQLException
  { _struct.setAttribute(5, authSk); }


  public String getAuthQlfr() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setAuthQlfr(String authQlfr) throws SQLException
  { _struct.setAttribute(6, authQlfr); }

}
