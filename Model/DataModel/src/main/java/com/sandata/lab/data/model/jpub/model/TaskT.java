package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class TaskT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.TASK_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,2,2,12,12,12,12,12,2,12,2,91,91,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[21];
  protected static final TaskT _TaskTFactory = new TaskT();

  public static ORADataFactory getORADataFactory()
  { return _TaskTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[21], _sqlType, _factory); }
  public TaskT()
  { _init_struct(true); }
  public TaskT(java.math.BigDecimal taskSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String taskId, String beTaskId, String beTaskName, String taskDesc, java.math.BigDecimal taskRdngInd, String taskRdngValidRule, java.math.BigDecimal criticalTaskInd, java.sql.Timestamp taskEffDate, java.sql.Timestamp taskTermDate, String taskSourceQlfr) throws SQLException
  { _init_struct(true);
    setTaskSk(taskSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonMemo(changeReasonMemo);
    setCurrRecInd(currRecInd);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setTaskId(taskId);
    setBeTaskId(beTaskId);
    setBeTaskName(beTaskName);
    setTaskDesc(taskDesc);
    setTaskRdngInd(taskRdngInd);
    setTaskRdngValidRule(taskRdngValidRule);
    setCriticalTaskInd(criticalTaskInd);
    setTaskEffDate(taskEffDate);
    setTaskTermDate(taskTermDate);
    setTaskSourceQlfr(taskSourceQlfr);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(TaskT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new TaskT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getTaskSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setTaskSk(java.math.BigDecimal taskSk) throws SQLException
  { _struct.setAttribute(0, taskSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public java.sql.Timestamp getRecEffTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(3); }

  public void setRecEffTmstp(java.sql.Timestamp recEffTmstp) throws SQLException
  { _struct.setAttribute(3, recEffTmstp); }


  public java.sql.Timestamp getRecTermTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(4); }

  public void setRecTermTmstp(java.sql.Timestamp recTermTmstp) throws SQLException
  { _struct.setAttribute(4, recTermTmstp); }


  public String getRecCreatedBy() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setRecCreatedBy(String recCreatedBy) throws SQLException
  { _struct.setAttribute(5, recCreatedBy); }


  public String getRecUpdatedBy() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setRecUpdatedBy(String recUpdatedBy) throws SQLException
  { _struct.setAttribute(6, recUpdatedBy); }


  public String getChangeReasonMemo() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setChangeReasonMemo(String changeReasonMemo) throws SQLException
  { _struct.setAttribute(7, changeReasonMemo); }


  public java.math.BigDecimal getCurrRecInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(8); }

  public void setCurrRecInd(java.math.BigDecimal currRecInd) throws SQLException
  { _struct.setAttribute(8, currRecInd); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(9); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(9, changeVersionId); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(10, beId); }


  public String getTaskId() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setTaskId(String taskId) throws SQLException
  { _struct.setAttribute(11, taskId); }


  public String getBeTaskId() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setBeTaskId(String beTaskId) throws SQLException
  { _struct.setAttribute(12, beTaskId); }


  public String getBeTaskName() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setBeTaskName(String beTaskName) throws SQLException
  { _struct.setAttribute(13, beTaskName); }


  public String getTaskDesc() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setTaskDesc(String taskDesc) throws SQLException
  { _struct.setAttribute(14, taskDesc); }


  public java.math.BigDecimal getTaskRdngInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(15); }

  public void setTaskRdngInd(java.math.BigDecimal taskRdngInd) throws SQLException
  { _struct.setAttribute(15, taskRdngInd); }


  public String getTaskRdngValidRule() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setTaskRdngValidRule(String taskRdngValidRule) throws SQLException
  { _struct.setAttribute(16, taskRdngValidRule); }


  public java.math.BigDecimal getCriticalTaskInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(17); }

  public void setCriticalTaskInd(java.math.BigDecimal criticalTaskInd) throws SQLException
  { _struct.setAttribute(17, criticalTaskInd); }


  public java.sql.Timestamp getTaskEffDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(18); }

  public void setTaskEffDate(java.sql.Timestamp taskEffDate) throws SQLException
  { _struct.setAttribute(18, taskEffDate); }


  public java.sql.Timestamp getTaskTermDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(19); }

  public void setTaskTermDate(java.sql.Timestamp taskTermDate) throws SQLException
  { _struct.setAttribute(19, taskTermDate); }

  public String getTaskSourceQlfr() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setTaskSourceQlfr(String taskSourceQlfr) throws SQLException
  { _struct.setAttribute(20, taskSourceQlfr); }

}
