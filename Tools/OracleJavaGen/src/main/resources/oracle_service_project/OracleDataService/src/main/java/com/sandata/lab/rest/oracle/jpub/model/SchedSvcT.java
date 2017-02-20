package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class SchedSvcT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.SCHED_SVC_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,2,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[6];
  protected static final SchedSvcT _SchedSvcTFactory = new SchedSvcT();

  public static ORADataFactory getORADataFactory()
  { return _SchedSvcTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[6], _sqlType, _factory); }
  public SchedSvcT()
  { _init_struct(true); }
  public SchedSvcT(java.math.BigDecimal schedSvcSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal changeVersionId, java.math.BigDecimal schedSk, java.math.BigDecimal svcSk) throws SQLException
  { _init_struct(true);
    setSchedSvcSk(schedSvcSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setChangeVersionId(changeVersionId);
    setSchedSk(schedSk);
    setSvcSk(svcSk);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(SchedSvcT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new SchedSvcT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getSchedSvcSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setSchedSvcSk(java.math.BigDecimal schedSvcSk) throws SQLException
  { _struct.setAttribute(0, schedSvcSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(3, changeVersionId); }


  public java.math.BigDecimal getSchedSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(4); }

  public void setSchedSk(java.math.BigDecimal schedSk) throws SQLException
  { _struct.setAttribute(4, schedSk); }


  public java.math.BigDecimal getSvcSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(5); }

  public void setSvcSk(java.math.BigDecimal svcSk) throws SQLException
  { _struct.setAttribute(5, svcSk); }

}
