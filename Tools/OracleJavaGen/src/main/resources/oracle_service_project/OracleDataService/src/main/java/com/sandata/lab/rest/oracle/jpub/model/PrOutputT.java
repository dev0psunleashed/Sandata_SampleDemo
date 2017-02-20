package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class PrOutputT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PR_OUTPUT_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,91,91,91,91,12,12,12,2,2,12,12,12,12,2,91,91,12,2,2,2,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[23];
  protected static final PrOutputT _PrOutputTFactory = new PrOutputT();

  public static ORADataFactory getORADataFactory()
  { return _PrOutputTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[23], _sqlType, _factory); }
  public PrOutputT()
  { _init_struct(true); }
  public PrOutputT(java.math.BigDecimal prOutputSk, String prOutputId, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recTermTmstp, java.sql.Timestamp recEffTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String beLobId, String staffId, String intfId, java.math.BigDecimal timesheetSummarySk, java.sql.Timestamp weekEndDate, java.sql.Timestamp prExportDate, String prCode, java.math.BigDecimal prHrs, java.math.BigDecimal prRate, java.math.BigDecimal prAmt, String checkMemo) throws SQLException
  { _init_struct(true);
    setPrOutputSk(prOutputSk);
    setPrOutputId(prOutputId);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonMemo(changeReasonMemo);
    setCurrRecInd(currRecInd);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setBeLobId(beLobId);
    setStaffId(staffId);
    setIntfId(intfId);
    setTimesheetSummarySk(timesheetSummarySk);
    setWeekEndDate(weekEndDate);
    setPrExportDate(prExportDate);
    setPrCode(prCode);
    setPrHrs(prHrs);
    setPrRate(prRate);
    setPrAmt(prAmt);
    setCheckMemo(checkMemo);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PrOutputT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PrOutputT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPrOutputSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPrOutputSk(java.math.BigDecimal prOutputSk) throws SQLException
  { _struct.setAttribute(0, prOutputSk); }


  public String getPrOutputId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setPrOutputId(String prOutputId) throws SQLException
  { _struct.setAttribute(1, prOutputId); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(2, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(3); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(3, recUpdateTmstp); }


  public java.sql.Timestamp getRecTermTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(4); }

  public void setRecTermTmstp(java.sql.Timestamp recTermTmstp) throws SQLException
  { _struct.setAttribute(4, recTermTmstp); }


  public java.sql.Timestamp getRecEffTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(5); }

  public void setRecEffTmstp(java.sql.Timestamp recEffTmstp) throws SQLException
  { _struct.setAttribute(5, recEffTmstp); }


  public String getRecCreatedBy() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setRecCreatedBy(String recCreatedBy) throws SQLException
  { _struct.setAttribute(6, recCreatedBy); }


  public String getRecUpdatedBy() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setRecUpdatedBy(String recUpdatedBy) throws SQLException
  { _struct.setAttribute(7, recUpdatedBy); }


  public String getChangeReasonMemo() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setChangeReasonMemo(String changeReasonMemo) throws SQLException
  { _struct.setAttribute(8, changeReasonMemo); }


  public java.math.BigDecimal getCurrRecInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(9); }

  public void setCurrRecInd(java.math.BigDecimal currRecInd) throws SQLException
  { _struct.setAttribute(9, currRecInd); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(10); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(10, changeVersionId); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(11, beId); }


  public String getBeLobId() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setBeLobId(String beLobId) throws SQLException
  { _struct.setAttribute(12, beLobId); }


  public String getStaffId() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setStaffId(String staffId) throws SQLException
  { _struct.setAttribute(13, staffId); }


  public String getIntfId() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setIntfId(String intfId) throws SQLException
  { _struct.setAttribute(14, intfId); }


  public java.math.BigDecimal getTimesheetSummarySk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(15); }

  public void setTimesheetSummarySk(java.math.BigDecimal timesheetSummarySk) throws SQLException
  { _struct.setAttribute(15, timesheetSummarySk); }


  public java.sql.Timestamp getWeekEndDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(16); }

  public void setWeekEndDate(java.sql.Timestamp weekEndDate) throws SQLException
  { _struct.setAttribute(16, weekEndDate); }


  public java.sql.Timestamp getPrExportDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(17); }

  public void setPrExportDate(java.sql.Timestamp prExportDate) throws SQLException
  { _struct.setAttribute(17, prExportDate); }


  public String getPrCode() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setPrCode(String prCode) throws SQLException
  { _struct.setAttribute(18, prCode); }


  public java.math.BigDecimal getPrHrs() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(19); }

  public void setPrHrs(java.math.BigDecimal prHrs) throws SQLException
  { _struct.setAttribute(19, prHrs); }


  public java.math.BigDecimal getPrRate() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(20); }

  public void setPrRate(java.math.BigDecimal prRate) throws SQLException
  { _struct.setAttribute(20, prRate); }


  public java.math.BigDecimal getPrAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(21); }

  public void setPrAmt(java.math.BigDecimal prAmt) throws SQLException
  { _struct.setAttribute(21, prAmt); }


  public String getCheckMemo() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setCheckMemo(String checkMemo) throws SQLException
  { _struct.setAttribute(22, checkMemo); }

}
