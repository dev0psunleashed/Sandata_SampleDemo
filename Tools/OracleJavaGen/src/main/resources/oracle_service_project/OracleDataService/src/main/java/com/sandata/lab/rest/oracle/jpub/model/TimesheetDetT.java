package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class TimesheetDetT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.TIMESHEET_DET_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,2,91,91,12,12,12,12,2,12,12,2,2,2,12,91,2,2,2,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[20];
  protected static final TimesheetDetT _TimesheetDetTFactory = new TimesheetDetT();

  public static ORADataFactory getORADataFactory()
  { return _TimesheetDetTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[20], _sqlType, _factory); }
  public TimesheetDetT()
  { _init_struct(true); }
  public TimesheetDetT(java.math.BigDecimal timesheetDetSk, java.math.BigDecimal timesheetSummarySk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonCode, String changeReasonMemo, java.math.BigDecimal changeVersionId, String beId, String ptId, java.math.BigDecimal visitSk, java.math.BigDecimal beCalendarLkupSk, java.math.BigDecimal svcActivityBillingCodeSk, String prCode, java.sql.Timestamp timesheetDate, java.math.BigDecimal prRateAmt, java.math.BigDecimal prHrs, java.math.BigDecimal prAmt, java.math.BigDecimal releaseToBillInd) throws SQLException
  { _init_struct(true);
    setTimesheetDetSk(timesheetDetSk);
    setTimesheetSummarySk(timesheetSummarySk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonCode(changeReasonCode);
    setChangeReasonMemo(changeReasonMemo);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setPtId(ptId);
    setVisitSk(visitSk);
    setBeCalendarLkupSk(beCalendarLkupSk);
    setSvcActivityBillingCodeSk(svcActivityBillingCodeSk);
    setPrCode(prCode);
    setTimesheetDate(timesheetDate);
    setPrRateAmt(prRateAmt);
    setPrHrs(prHrs);
    setPrAmt(prAmt);
    setReleaseToBillInd(releaseToBillInd);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(TimesheetDetT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new TimesheetDetT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getTimesheetDetSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setTimesheetDetSk(java.math.BigDecimal timesheetDetSk) throws SQLException
  { _struct.setAttribute(0, timesheetDetSk); }


  public java.math.BigDecimal getTimesheetSummarySk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(1); }

  public void setTimesheetSummarySk(java.math.BigDecimal timesheetSummarySk) throws SQLException
  { _struct.setAttribute(1, timesheetSummarySk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(2, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(3); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(3, recUpdateTmstp); }


  public String getRecCreatedBy() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setRecCreatedBy(String recCreatedBy) throws SQLException
  { _struct.setAttribute(4, recCreatedBy); }


  public String getRecUpdatedBy() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setRecUpdatedBy(String recUpdatedBy) throws SQLException
  { _struct.setAttribute(5, recUpdatedBy); }


  public String getChangeReasonCode() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setChangeReasonCode(String changeReasonCode) throws SQLException
  { _struct.setAttribute(6, changeReasonCode); }


  public String getChangeReasonMemo() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setChangeReasonMemo(String changeReasonMemo) throws SQLException
  { _struct.setAttribute(7, changeReasonMemo); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(8); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(8, changeVersionId); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(9); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(9, beId); }


  public String getPtId() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setPtId(String ptId) throws SQLException
  { _struct.setAttribute(10, ptId); }


  public java.math.BigDecimal getVisitSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(11); }

  public void setVisitSk(java.math.BigDecimal visitSk) throws SQLException
  { _struct.setAttribute(11, visitSk); }


  public java.math.BigDecimal getBeCalendarLkupSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(12); }

  public void setBeCalendarLkupSk(java.math.BigDecimal beCalendarLkupSk) throws SQLException
  { _struct.setAttribute(12, beCalendarLkupSk); }


  public java.math.BigDecimal getSvcActivityBillingCodeSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(13); }

  public void setSvcActivityBillingCodeSk(java.math.BigDecimal svcActivityBillingCodeSk) throws SQLException
  { _struct.setAttribute(13, svcActivityBillingCodeSk); }


  public String getPrCode() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setPrCode(String prCode) throws SQLException
  { _struct.setAttribute(14, prCode); }


  public java.sql.Timestamp getTimesheetDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(15); }

  public void setTimesheetDate(java.sql.Timestamp timesheetDate) throws SQLException
  { _struct.setAttribute(15, timesheetDate); }


  public java.math.BigDecimal getPrRateAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(16); }

  public void setPrRateAmt(java.math.BigDecimal prRateAmt) throws SQLException
  { _struct.setAttribute(16, prRateAmt); }


  public java.math.BigDecimal getPrHrs() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(17); }

  public void setPrHrs(java.math.BigDecimal prHrs) throws SQLException
  { _struct.setAttribute(17, prHrs); }


  public java.math.BigDecimal getPrAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(18); }

  public void setPrAmt(java.math.BigDecimal prAmt) throws SQLException
  { _struct.setAttribute(18, prAmt); }


  public java.math.BigDecimal getReleaseToBillInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(19); }

  public void setReleaseToBillInd(java.math.BigDecimal releaseToBillInd) throws SQLException
  { _struct.setAttribute(19, releaseToBillInd); }

}
