package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class SchedEvntT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.SCHED_EVNT_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,91,91,91,91,12,12,12,12,2,2,12,12,2,12,12,12,2,2,2,2,12,2,12,12,91,91,2,12,2,12,12,12,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[35];
  protected static final SchedEvntT _SchedEvntTFactory = new SchedEvntT();

  public static ORADataFactory getORADataFactory()
  { return _SchedEvntTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[35], _sqlType, _factory); }
  public SchedEvntT()
  { _init_struct(true); }
  public SchedEvntT(java.math.BigDecimal schedEvntSk, String schedEvntId, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonCode, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String ptId, java.math.BigDecimal authSk, String authQlfr, String staffId, String payerId, java.math.BigDecimal rfrlSk, java.math.BigDecimal beCalendarLkupSk, java.math.BigDecimal schedSk, java.math.BigDecimal pocSk, String tzName, java.math.BigDecimal schedEvntBillRate, String masterRateId, String dayOfMonth, java.sql.Timestamp schedEvntStartDtime, java.sql.Timestamp schedEvntEndDtime, java.math.BigDecimal schedEvntTotalHrs, String schedEvntStatus, java.math.BigDecimal schedEvntPayRate, String schedEvntUnit, String schedEvntRestriction, String schedEvntComment, java.math.BigDecimal schedEvntManualOverrideInd) throws SQLException
  { _init_struct(true);
    setSchedEvntSk(schedEvntSk);
    setSchedEvntId(schedEvntId);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonCode(changeReasonCode);
    setChangeReasonMemo(changeReasonMemo);
    setCurrRecInd(currRecInd);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setPtId(ptId);
    setAuthSk(authSk);
    setAuthQlfr(authQlfr);
    setStaffId(staffId);
    setPayerId(payerId);
    setRfrlSk(rfrlSk);
    setBeCalendarLkupSk(beCalendarLkupSk);
    setSchedSk(schedSk);
    setPocSk(pocSk);
    setTzName(tzName);
    setSchedEvntBillRate(schedEvntBillRate);
    setMasterRateId(masterRateId);
    setDayOfMonth(dayOfMonth);
    setSchedEvntStartDtime(schedEvntStartDtime);
    setSchedEvntEndDtime(schedEvntEndDtime);
    setSchedEvntTotalHrs(schedEvntTotalHrs);
    setSchedEvntStatus(schedEvntStatus);
    setSchedEvntPayRate(schedEvntPayRate);
    setSchedEvntUnit(schedEvntUnit);
    setSchedEvntRestriction(schedEvntRestriction);
    setSchedEvntComment(schedEvntComment);
    setSchedEvntManualOverrideInd(schedEvntManualOverrideInd);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(SchedEvntT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new SchedEvntT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getSchedEvntSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setSchedEvntSk(java.math.BigDecimal schedEvntSk) throws SQLException
  { _struct.setAttribute(0, schedEvntSk); }


  public String getSchedEvntId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setSchedEvntId(String schedEvntId) throws SQLException
  { _struct.setAttribute(1, schedEvntId); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(2, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(3); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(3, recUpdateTmstp); }


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


  public String getChangeReasonCode() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setChangeReasonCode(String changeReasonCode) throws SQLException
  { _struct.setAttribute(8, changeReasonCode); }


  public String getChangeReasonMemo() throws SQLException
  { return (String) _struct.getAttribute(9); }

  public void setChangeReasonMemo(String changeReasonMemo) throws SQLException
  { _struct.setAttribute(9, changeReasonMemo); }


  public java.math.BigDecimal getCurrRecInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(10); }

  public void setCurrRecInd(java.math.BigDecimal currRecInd) throws SQLException
  { _struct.setAttribute(10, currRecInd); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(11); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(11, changeVersionId); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(12, beId); }


  public String getPtId() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setPtId(String ptId) throws SQLException
  { _struct.setAttribute(13, ptId); }


  public java.math.BigDecimal getAuthSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(14); }

  public void setAuthSk(java.math.BigDecimal authSk) throws SQLException
  { _struct.setAttribute(14, authSk); }


  public String getAuthQlfr() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setAuthQlfr(String authQlfr) throws SQLException
  { _struct.setAttribute(15, authQlfr); }


  public String getStaffId() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setStaffId(String staffId) throws SQLException
  { _struct.setAttribute(16, staffId); }


  public String getPayerId() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setPayerId(String payerId) throws SQLException
  { _struct.setAttribute(17, payerId); }


  public java.math.BigDecimal getRfrlSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(18); }

  public void setRfrlSk(java.math.BigDecimal rfrlSk) throws SQLException
  { _struct.setAttribute(18, rfrlSk); }


  public java.math.BigDecimal getBeCalendarLkupSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(19); }

  public void setBeCalendarLkupSk(java.math.BigDecimal beCalendarLkupSk) throws SQLException
  { _struct.setAttribute(19, beCalendarLkupSk); }


  public java.math.BigDecimal getSchedSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(20); }

  public void setSchedSk(java.math.BigDecimal schedSk) throws SQLException
  { _struct.setAttribute(20, schedSk); }


  public java.math.BigDecimal getPocSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(21); }

  public void setPocSk(java.math.BigDecimal pocSk) throws SQLException
  { _struct.setAttribute(21, pocSk); }


  public String getTzName() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setTzName(String tzName) throws SQLException
  { _struct.setAttribute(22, tzName); }


  public java.math.BigDecimal getSchedEvntBillRate() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(23); }

  public void setSchedEvntBillRate(java.math.BigDecimal schedEvntBillRate) throws SQLException
  { _struct.setAttribute(23, schedEvntBillRate); }


  public String getMasterRateId() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setMasterRateId(String masterRateId) throws SQLException
  { _struct.setAttribute(24, masterRateId); }


  public String getDayOfMonth() throws SQLException
  { return (String) _struct.getAttribute(25); }

  public void setDayOfMonth(String dayOfMonth) throws SQLException
  { _struct.setAttribute(25, dayOfMonth); }


  public java.sql.Timestamp getSchedEvntStartDtime() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(26); }

  public void setSchedEvntStartDtime(java.sql.Timestamp schedEvntStartDtime) throws SQLException
  { _struct.setAttribute(26, schedEvntStartDtime); }


  public java.sql.Timestamp getSchedEvntEndDtime() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(27); }

  public void setSchedEvntEndDtime(java.sql.Timestamp schedEvntEndDtime) throws SQLException
  { _struct.setAttribute(27, schedEvntEndDtime); }


  public java.math.BigDecimal getSchedEvntTotalHrs() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(28); }

  public void setSchedEvntTotalHrs(java.math.BigDecimal schedEvntTotalHrs) throws SQLException
  { _struct.setAttribute(28, schedEvntTotalHrs); }


  public String getSchedEvntStatus() throws SQLException
  { return (String) _struct.getAttribute(29); }

  public void setSchedEvntStatus(String schedEvntStatus) throws SQLException
  { _struct.setAttribute(29, schedEvntStatus); }


  public java.math.BigDecimal getSchedEvntPayRate() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(30); }

  public void setSchedEvntPayRate(java.math.BigDecimal schedEvntPayRate) throws SQLException
  { _struct.setAttribute(30, schedEvntPayRate); }


  public String getSchedEvntUnit() throws SQLException
  { return (String) _struct.getAttribute(31); }

  public void setSchedEvntUnit(String schedEvntUnit) throws SQLException
  { _struct.setAttribute(31, schedEvntUnit); }


  public String getSchedEvntRestriction() throws SQLException
  { return (String) _struct.getAttribute(32); }

  public void setSchedEvntRestriction(String schedEvntRestriction) throws SQLException
  { _struct.setAttribute(32, schedEvntRestriction); }


  public String getSchedEvntComment() throws SQLException
  { return (String) _struct.getAttribute(33); }

  public void setSchedEvntComment(String schedEvntComment) throws SQLException
  { _struct.setAttribute(33, schedEvntComment); }


  public java.math.BigDecimal getSchedEvntManualOverrideInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(34); }

  public void setSchedEvntManualOverrideInd(java.math.BigDecimal schedEvntManualOverrideInd) throws SQLException
  { _struct.setAttribute(34, schedEvntManualOverrideInd); }

}
