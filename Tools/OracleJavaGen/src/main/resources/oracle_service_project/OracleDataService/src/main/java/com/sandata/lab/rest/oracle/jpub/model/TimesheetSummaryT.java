package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class TimesheetSummaryT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.TIMESHEET_SUMMARY_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,12,2,12,12,12,2,91,91,2,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[13];
  protected static final TimesheetSummaryT _TimesheetSummaryTFactory = new TimesheetSummaryT();

  public static ORADataFactory getORADataFactory()
  { return _TimesheetSummaryTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[13], _sqlType, _factory); }
  public TimesheetSummaryT()
  { _init_struct(true); }
  public TimesheetSummaryT(java.math.BigDecimal timesheetSummarySk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String timesheetSummaryId, java.math.BigDecimal changeVersionId, String beId, String beLobId, String staffId, java.math.BigDecimal beCalendarLkupSk, java.sql.Timestamp timesheetWeekStartDate, java.sql.Timestamp timesheetWeekEndDate, java.math.BigDecimal timesheetOtHrs, java.math.BigDecimal timesheetWeekTotalHrs) throws SQLException
  { _init_struct(true);
    setTimesheetSummarySk(timesheetSummarySk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setTimesheetSummaryId(timesheetSummaryId);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setBeLobId(beLobId);
    setStaffId(staffId);
    setBeCalendarLkupSk(beCalendarLkupSk);
    setTimesheetWeekStartDate(timesheetWeekStartDate);
    setTimesheetWeekEndDate(timesheetWeekEndDate);
    setTimesheetOtHrs(timesheetOtHrs);
    setTimesheetWeekTotalHrs(timesheetWeekTotalHrs);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(TimesheetSummaryT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new TimesheetSummaryT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getTimesheetSummarySk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setTimesheetSummarySk(java.math.BigDecimal timesheetSummarySk) throws SQLException
  { _struct.setAttribute(0, timesheetSummarySk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public String getTimesheetSummaryId() throws SQLException
  { return (String) _struct.getAttribute(3); }

  public void setTimesheetSummaryId(String timesheetSummaryId) throws SQLException
  { _struct.setAttribute(3, timesheetSummaryId); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(4); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(4, changeVersionId); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(5, beId); }


  public String getBeLobId() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setBeLobId(String beLobId) throws SQLException
  { _struct.setAttribute(6, beLobId); }


  public String getStaffId() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setStaffId(String staffId) throws SQLException
  { _struct.setAttribute(7, staffId); }


  public java.math.BigDecimal getBeCalendarLkupSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(8); }

  public void setBeCalendarLkupSk(java.math.BigDecimal beCalendarLkupSk) throws SQLException
  { _struct.setAttribute(8, beCalendarLkupSk); }


  public java.sql.Timestamp getTimesheetWeekStartDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(9); }

  public void setTimesheetWeekStartDate(java.sql.Timestamp timesheetWeekStartDate) throws SQLException
  { _struct.setAttribute(9, timesheetWeekStartDate); }


  public java.sql.Timestamp getTimesheetWeekEndDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(10); }

  public void setTimesheetWeekEndDate(java.sql.Timestamp timesheetWeekEndDate) throws SQLException
  { _struct.setAttribute(10, timesheetWeekEndDate); }


  public java.math.BigDecimal getTimesheetOtHrs() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(11); }

  public void setTimesheetOtHrs(java.math.BigDecimal timesheetOtHrs) throws SQLException
  { _struct.setAttribute(11, timesheetOtHrs); }


  public java.math.BigDecimal getTimesheetWeekTotalHrs() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(12); }

  public void setTimesheetWeekTotalHrs(java.math.BigDecimal timesheetWeekTotalHrs) throws SQLException
  { _struct.setAttribute(12, timesheetWeekTotalHrs); }

}
