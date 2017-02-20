package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class SchedPtcExclT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.SCHED_PTC_EXCL_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,12,91,91,12,12,2,2,12,12,12,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[16];
  protected static final SchedPtcExclT _SchedPtcExclTFactory = new SchedPtcExclT();

  public static ORADataFactory getORADataFactory()
  { return _SchedPtcExclTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[16], _sqlType, _factory); }
  public SchedPtcExclT()
  { _init_struct(true); }
  public SchedPtcExclT(java.math.BigDecimal schedPtcExclSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String changeReasonMemo, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String ptId, String staffId, String schedPtcExclQlfr, String schedPermQlfr, String schedPtcExclNote) throws SQLException
  { _init_struct(true);
    setSchedPtcExclSk(schedPtcExclSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setChangeReasonMemo(changeReasonMemo);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setCurrRecInd(currRecInd);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setPtId(ptId);
    setStaffId(staffId);
    setSchedPtcExclQlfr(schedPtcExclQlfr);
    setSchedPermQlfr(schedPermQlfr);
    setSchedPtcExclNote(schedPtcExclNote);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(SchedPtcExclT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new SchedPtcExclT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getSchedPtcExclSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setSchedPtcExclSk(java.math.BigDecimal schedPtcExclSk) throws SQLException
  { _struct.setAttribute(0, schedPtcExclSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public String getChangeReasonMemo() throws SQLException
  { return (String) _struct.getAttribute(3); }

  public void setChangeReasonMemo(String changeReasonMemo) throws SQLException
  { _struct.setAttribute(3, changeReasonMemo); }


  public java.sql.Timestamp getRecEffTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(4); }

  public void setRecEffTmstp(java.sql.Timestamp recEffTmstp) throws SQLException
  { _struct.setAttribute(4, recEffTmstp); }


  public java.sql.Timestamp getRecTermTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(5); }

  public void setRecTermTmstp(java.sql.Timestamp recTermTmstp) throws SQLException
  { _struct.setAttribute(5, recTermTmstp); }


  public String getRecCreatedBy() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setRecCreatedBy(String recCreatedBy) throws SQLException
  { _struct.setAttribute(6, recCreatedBy); }


  public String getRecUpdatedBy() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setRecUpdatedBy(String recUpdatedBy) throws SQLException
  { _struct.setAttribute(7, recUpdatedBy); }


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


  public String getPtId() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setPtId(String ptId) throws SQLException
  { _struct.setAttribute(11, ptId); }


  public String getStaffId() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setStaffId(String staffId) throws SQLException
  { _struct.setAttribute(12, staffId); }


  public String getSchedPtcExclQlfr() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setSchedPtcExclQlfr(String schedPtcExclQlfr) throws SQLException
  { _struct.setAttribute(13, schedPtcExclQlfr); }


  public String getSchedPermQlfr() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setSchedPermQlfr(String schedPermQlfr) throws SQLException
  { _struct.setAttribute(14, schedPermQlfr); }


  public String getSchedPtcExclNote() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setSchedPtcExclNote(String schedPtcExclNote) throws SQLException
  { _struct.setAttribute(15, schedPtcExclNote); }

}
