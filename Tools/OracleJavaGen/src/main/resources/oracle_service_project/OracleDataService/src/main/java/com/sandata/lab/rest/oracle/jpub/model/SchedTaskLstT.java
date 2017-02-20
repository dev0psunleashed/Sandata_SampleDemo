package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class SchedTaskLstT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.SCHED_TASK_LST_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,2,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[7];
  protected static final SchedTaskLstT _SchedTaskLstTFactory = new SchedTaskLstT();

  public static ORADataFactory getORADataFactory()
  { return _SchedTaskLstTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[7], _sqlType, _factory); }
  public SchedTaskLstT()
  { _init_struct(true); }
  public SchedTaskLstT(java.math.BigDecimal schedTaskLstSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal changeVersionId, java.math.BigDecimal schedSk, String beId, String beTaskId) throws SQLException
  { _init_struct(true);
    setSchedTaskLstSk(schedTaskLstSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setChangeVersionId(changeVersionId);
    setSchedSk(schedSk);
    setBeId(beId);
    setBeTaskId(beTaskId);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(SchedTaskLstT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new SchedTaskLstT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getSchedTaskLstSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setSchedTaskLstSk(java.math.BigDecimal schedTaskLstSk) throws SQLException
  { _struct.setAttribute(0, schedTaskLstSk); }


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


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(5, beId); }


  public String getBeTaskId() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setBeTaskId(String beTaskId) throws SQLException
  { _struct.setAttribute(6, beTaskId); }

}
