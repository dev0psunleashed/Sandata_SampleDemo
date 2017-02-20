package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class SchedRptWeekT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.SCHED_RPT_WEEK_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,2,2,91,91 };
  protected static ORADataFactory[] _factory = new ORADataFactory[8];
  protected static final SchedRptWeekT _SchedRptWeekTFactory = new SchedRptWeekT();

  public static ORADataFactory getORADataFactory()
  { return _SchedRptWeekTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[8], _sqlType, _factory); }
  public SchedRptWeekT()
  { _init_struct(true); }
  public SchedRptWeekT(java.math.BigDecimal schedRptWeekSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal changeVersionId, java.math.BigDecimal weekOfMonthNum, java.math.BigDecimal schedSk, java.sql.Timestamp schedStartTime, java.sql.Timestamp schedEndTime) throws SQLException
  { _init_struct(true);
    setSchedRptWeekSk(schedRptWeekSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setChangeVersionId(changeVersionId);
    setWeekOfMonthNum(weekOfMonthNum);
    setSchedSk(schedSk);
    setSchedStartTime(schedStartTime);
    setSchedEndTime(schedEndTime);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(SchedRptWeekT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new SchedRptWeekT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getSchedRptWeekSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setSchedRptWeekSk(java.math.BigDecimal schedRptWeekSk) throws SQLException
  { _struct.setAttribute(0, schedRptWeekSk); }


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


  public java.math.BigDecimal getWeekOfMonthNum() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(4); }

  public void setWeekOfMonthNum(java.math.BigDecimal weekOfMonthNum) throws SQLException
  { _struct.setAttribute(4, weekOfMonthNum); }


  public java.math.BigDecimal getSchedSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(5); }

  public void setSchedSk(java.math.BigDecimal schedSk) throws SQLException
  { _struct.setAttribute(5, schedSk); }


  public java.sql.Timestamp getSchedStartTime() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(6); }

  public void setSchedStartTime(java.sql.Timestamp schedStartTime) throws SQLException
  { _struct.setAttribute(6, schedStartTime); }


  public java.sql.Timestamp getSchedEndTime() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(7); }

  public void setSchedEndTime(java.sql.Timestamp schedEndTime) throws SQLException
  { _struct.setAttribute(7, schedEndTime); }

}
