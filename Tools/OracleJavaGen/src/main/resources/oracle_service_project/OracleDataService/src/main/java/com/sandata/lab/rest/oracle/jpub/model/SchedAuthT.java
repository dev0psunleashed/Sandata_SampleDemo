package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class SchedAuthT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.SCHED_AUTH_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,2,2,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[7];
  protected static final SchedAuthT _SchedAuthTFactory = new SchedAuthT();

  public static ORADataFactory getORADataFactory()
  { return _SchedAuthTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[7], _sqlType, _factory); }
  public SchedAuthT()
  { _init_struct(true); }
  public SchedAuthT(java.math.BigDecimal schedAuthSk, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recCreateTmstp, java.math.BigDecimal changeVersionId, java.math.BigDecimal schedSk, java.math.BigDecimal authSk, String authQlfr) throws SQLException
  { _init_struct(true);
    setSchedAuthSk(schedAuthSk);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecCreateTmstp(recCreateTmstp);
    setChangeVersionId(changeVersionId);
    setSchedSk(schedSk);
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
  protected ORAData create(SchedAuthT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new SchedAuthT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getSchedAuthSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setSchedAuthSk(java.math.BigDecimal schedAuthSk) throws SQLException
  { _struct.setAttribute(0, schedAuthSk); }


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


  public java.math.BigDecimal getSchedSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(4); }

  public void setSchedSk(java.math.BigDecimal schedSk) throws SQLException
  { _struct.setAttribute(4, schedSk); }


  public java.math.BigDecimal getAuthSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(5); }

  public void setAuthSk(java.math.BigDecimal authSk) throws SQLException
  { _struct.setAttribute(5, authSk); }


  public String getAuthQlfr() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setAuthQlfr(String authQlfr) throws SQLException
  { _struct.setAttribute(6, authQlfr); }

}
