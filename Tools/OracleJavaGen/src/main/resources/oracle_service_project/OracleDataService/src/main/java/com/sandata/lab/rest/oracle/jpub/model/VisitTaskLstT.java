package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class VisitTaskLstT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.VISIT_TASK_LST_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,91,91,2,12,2,12,2,2,2,2,2,12,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[17];
  protected static final VisitTaskLstT _VisitTaskLstTFactory = new VisitTaskLstT();

  public static ORADataFactory getORADataFactory()
  { return _VisitTaskLstTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[17], _sqlType, _factory); }
  public VisitTaskLstT()
  { _init_struct(true); }
  public VisitTaskLstT(java.math.BigDecimal visitTaskLstSk, String visitTaskLstId, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal changeVersionId, String beId, java.math.BigDecimal visitSk, String beTaskId, java.math.BigDecimal criticalTaskInd, java.math.BigDecimal visitTaskScheduledInd, java.math.BigDecimal visitTaskPerfInd, java.math.BigDecimal visitTaskDeniedInd, java.math.BigDecimal visitTaskBypassedInd, String visitTaskNotPerfReason, String visitTaskComment, String taskResultsRdngTyp, String taskResultsRdngVal) throws SQLException
  { _init_struct(true);
    setVisitTaskLstSk(visitTaskLstSk);
    setVisitTaskLstId(visitTaskLstId);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setVisitSk(visitSk);
    setBeTaskId(beTaskId);
    setCriticalTaskInd(criticalTaskInd);
    setVisitTaskScheduledInd(visitTaskScheduledInd);
    setVisitTaskPerfInd(visitTaskPerfInd);
    setVisitTaskDeniedInd(visitTaskDeniedInd);
    setVisitTaskBypassedInd(visitTaskBypassedInd);
    setVisitTaskNotPerfReason(visitTaskNotPerfReason);
    setVisitTaskComment(visitTaskComment);
    setTaskResultsRdngTyp(taskResultsRdngTyp);
    setTaskResultsRdngVal(taskResultsRdngVal);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(VisitTaskLstT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new VisitTaskLstT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getVisitTaskLstSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setVisitTaskLstSk(java.math.BigDecimal visitTaskLstSk) throws SQLException
  { _struct.setAttribute(0, visitTaskLstSk); }


  public String getVisitTaskLstId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setVisitTaskLstId(String visitTaskLstId) throws SQLException
  { _struct.setAttribute(1, visitTaskLstId); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(2, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(3); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(3, recUpdateTmstp); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(4); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(4, changeVersionId); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(5, beId); }


  public java.math.BigDecimal getVisitSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(6); }

  public void setVisitSk(java.math.BigDecimal visitSk) throws SQLException
  { _struct.setAttribute(6, visitSk); }


  public String getBeTaskId() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setBeTaskId(String beTaskId) throws SQLException
  { _struct.setAttribute(7, beTaskId); }


  public java.math.BigDecimal getCriticalTaskInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(8); }

  public void setCriticalTaskInd(java.math.BigDecimal criticalTaskInd) throws SQLException
  { _struct.setAttribute(8, criticalTaskInd); }


  public java.math.BigDecimal getVisitTaskScheduledInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(9); }

  public void setVisitTaskScheduledInd(java.math.BigDecimal visitTaskScheduledInd) throws SQLException
  { _struct.setAttribute(9, visitTaskScheduledInd); }


  public java.math.BigDecimal getVisitTaskPerfInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(10); }

  public void setVisitTaskPerfInd(java.math.BigDecimal visitTaskPerfInd) throws SQLException
  { _struct.setAttribute(10, visitTaskPerfInd); }


  public java.math.BigDecimal getVisitTaskDeniedInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(11); }

  public void setVisitTaskDeniedInd(java.math.BigDecimal visitTaskDeniedInd) throws SQLException
  { _struct.setAttribute(11, visitTaskDeniedInd); }


  public java.math.BigDecimal getVisitTaskBypassedInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(12); }

  public void setVisitTaskBypassedInd(java.math.BigDecimal visitTaskBypassedInd) throws SQLException
  { _struct.setAttribute(12, visitTaskBypassedInd); }


  public String getVisitTaskNotPerfReason() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setVisitTaskNotPerfReason(String visitTaskNotPerfReason) throws SQLException
  { _struct.setAttribute(13, visitTaskNotPerfReason); }


  public String getVisitTaskComment() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setVisitTaskComment(String visitTaskComment) throws SQLException
  { _struct.setAttribute(14, visitTaskComment); }


  public String getTaskResultsRdngTyp() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setTaskResultsRdngTyp(String taskResultsRdngTyp) throws SQLException
  { _struct.setAttribute(15, taskResultsRdngTyp); }


  public String getTaskResultsRdngVal() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setTaskResultsRdngVal(String taskResultsRdngVal) throws SQLException
  { _struct.setAttribute(16, taskResultsRdngVal); }

}
