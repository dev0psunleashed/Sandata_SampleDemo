package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class SchedT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.SCHED_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,91,91,2,2,2,12,12,12,12,91,91,2,2,2,12,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[20];
  protected static final SchedT _SchedTFactory = new SchedT();

  public static ORADataFactory getORADataFactory()
  { return _SchedTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[20], _sqlType, _factory); }
  public SchedT()
  { _init_struct(true); }
  public SchedT(java.math.BigDecimal schedSk, String schedId, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal changeVersionId, java.math.BigDecimal payerSk, java.math.BigDecimal rfrlSk, String beId, String ptId, String staffId, String tzName, java.sql.Timestamp schedStartDate, java.sql.Timestamp schedEndDate, java.math.BigDecimal numOfOccurences, java.math.BigDecimal schedPayRate, java.math.BigDecimal schedBillRate, String schedUnit, String schedFreqId, String schedRestrictions, String schedCmnt) throws SQLException
  { _init_struct(true);
    setSchedSk(schedSk);
    setSchedId(schedId);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setChangeVersionId(changeVersionId);
    setPayerSk(payerSk);
    setRfrlSk(rfrlSk);
    setBeId(beId);
    setPtId(ptId);
    setStaffId(staffId);
    setTzName(tzName);
    setSchedStartDate(schedStartDate);
    setSchedEndDate(schedEndDate);
    setNumOfOccurences(numOfOccurences);
    setSchedPayRate(schedPayRate);
    setSchedBillRate(schedBillRate);
    setSchedUnit(schedUnit);
    setSchedFreqId(schedFreqId);
    setSchedRestrictions(schedRestrictions);
    setSchedCmnt(schedCmnt);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(SchedT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new SchedT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getSchedSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setSchedSk(java.math.BigDecimal schedSk) throws SQLException
  { _struct.setAttribute(0, schedSk); }


  public String getSchedId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setSchedId(String schedId) throws SQLException
  { _struct.setAttribute(1, schedId); }


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


  public java.math.BigDecimal getPayerSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(5); }

  public void setPayerSk(java.math.BigDecimal payerSk) throws SQLException
  { _struct.setAttribute(5, payerSk); }


  public java.math.BigDecimal getRfrlSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(6); }

  public void setRfrlSk(java.math.BigDecimal rfrlSk) throws SQLException
  { _struct.setAttribute(6, rfrlSk); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(7, beId); }


  public String getPtId() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setPtId(String ptId) throws SQLException
  { _struct.setAttribute(8, ptId); }


  public String getStaffId() throws SQLException
  { return (String) _struct.getAttribute(9); }

  public void setStaffId(String staffId) throws SQLException
  { _struct.setAttribute(9, staffId); }


  public String getTzName() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setTzName(String tzName) throws SQLException
  { _struct.setAttribute(10, tzName); }


  public java.sql.Timestamp getSchedStartDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(11); }

  public void setSchedStartDate(java.sql.Timestamp schedStartDate) throws SQLException
  { _struct.setAttribute(11, schedStartDate); }


  public java.sql.Timestamp getSchedEndDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(12); }

  public void setSchedEndDate(java.sql.Timestamp schedEndDate) throws SQLException
  { _struct.setAttribute(12, schedEndDate); }


  public java.math.BigDecimal getNumOfOccurences() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(13); }

  public void setNumOfOccurences(java.math.BigDecimal numOfOccurences) throws SQLException
  { _struct.setAttribute(13, numOfOccurences); }


  public java.math.BigDecimal getSchedPayRate() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(14); }

  public void setSchedPayRate(java.math.BigDecimal schedPayRate) throws SQLException
  { _struct.setAttribute(14, schedPayRate); }


  public java.math.BigDecimal getSchedBillRate() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(15); }

  public void setSchedBillRate(java.math.BigDecimal schedBillRate) throws SQLException
  { _struct.setAttribute(15, schedBillRate); }


  public String getSchedUnit() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setSchedUnit(String schedUnit) throws SQLException
  { _struct.setAttribute(16, schedUnit); }


  public String getSchedFreqId() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setSchedFreqId(String schedFreqId) throws SQLException
  { _struct.setAttribute(17, schedFreqId); }


  public String getSchedRestrictions() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setSchedRestrictions(String schedRestrictions) throws SQLException
  { _struct.setAttribute(18, schedRestrictions); }


  public String getSchedCmnt() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setSchedCmnt(String schedCmnt) throws SQLException
  { _struct.setAttribute(19, schedCmnt); }

}
