package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class BeStaffTrngCtgyLkupT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.BE_STAFF_TRNG_CTGY_LKUP_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,2,2,12,12,12,12,2,2,91,2,12,12,2,2,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[23];
  protected static final BeStaffTrngCtgyLkupT _BeStaffTrngCtgyLkupTFactory = new BeStaffTrngCtgyLkupT();

  public static ORADataFactory getORADataFactory()
  { return _BeStaffTrngCtgyLkupTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[23], _sqlType, _factory); }
  public BeStaffTrngCtgyLkupT()
  { _init_struct(true); }
  public BeStaffTrngCtgyLkupT(java.math.BigDecimal beStaffTrngCtgyLkupSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String staffTrngCtgyCode, String staffTrngCtgyName, String staffTrngCtgyDesc, java.math.BigDecimal staffTrngCtgyRqdHrs, java.math.BigDecimal staffTrngCtgyRecurInd, java.sql.Timestamp staffTrngCtgyRqdByDate, java.math.BigDecimal staffTrngCtgyRecurFreq, String staffTrngCtgyRecurFreqUom, String staffTrngCtgyRqdFromQlfr, java.math.BigDecimal nonCompAlertInd, java.math.BigDecimal nonCompAlertThreshold, String staffTrngSchedPermQlfr) throws SQLException
  { _init_struct(true);
    setBeStaffTrngCtgyLkupSk(beStaffTrngCtgyLkupSk);
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
    setStaffTrngCtgyCode(staffTrngCtgyCode);
    setStaffTrngCtgyName(staffTrngCtgyName);
    setStaffTrngCtgyDesc(staffTrngCtgyDesc);
    setStaffTrngCtgyRqdHrs(staffTrngCtgyRqdHrs);
    setStaffTrngCtgyRecurInd(staffTrngCtgyRecurInd);
    setStaffTrngCtgyRqdByDate(staffTrngCtgyRqdByDate);
    setStaffTrngCtgyRecurFreq(staffTrngCtgyRecurFreq);
    setStaffTrngCtgyRecurFreqUom(staffTrngCtgyRecurFreqUom);
    setStaffTrngCtgyRqdFromQlfr(staffTrngCtgyRqdFromQlfr);
    setNonCompAlertInd(nonCompAlertInd);
    setNonCompAlertThreshold(nonCompAlertThreshold);
    setStaffTrngSchedPermQlfr(staffTrngSchedPermQlfr);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(BeStaffTrngCtgyLkupT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new BeStaffTrngCtgyLkupT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getBeStaffTrngCtgyLkupSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setBeStaffTrngCtgyLkupSk(java.math.BigDecimal beStaffTrngCtgyLkupSk) throws SQLException
  { _struct.setAttribute(0, beStaffTrngCtgyLkupSk); }


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


  public String getStaffTrngCtgyCode() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setStaffTrngCtgyCode(String staffTrngCtgyCode) throws SQLException
  { _struct.setAttribute(11, staffTrngCtgyCode); }


  public String getStaffTrngCtgyName() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setStaffTrngCtgyName(String staffTrngCtgyName) throws SQLException
  { _struct.setAttribute(12, staffTrngCtgyName); }


  public String getStaffTrngCtgyDesc() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setStaffTrngCtgyDesc(String staffTrngCtgyDesc) throws SQLException
  { _struct.setAttribute(13, staffTrngCtgyDesc); }


  public java.math.BigDecimal getStaffTrngCtgyRqdHrs() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(14); }

  public void setStaffTrngCtgyRqdHrs(java.math.BigDecimal staffTrngCtgyRqdHrs) throws SQLException
  { _struct.setAttribute(14, staffTrngCtgyRqdHrs); }


  public java.math.BigDecimal getStaffTrngCtgyRecurInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(15); }

  public void setStaffTrngCtgyRecurInd(java.math.BigDecimal staffTrngCtgyRecurInd) throws SQLException
  { _struct.setAttribute(15, staffTrngCtgyRecurInd); }


  public java.sql.Timestamp getStaffTrngCtgyRqdByDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(16); }

  public void setStaffTrngCtgyRqdByDate(java.sql.Timestamp staffTrngCtgyRqdByDate) throws SQLException
  { _struct.setAttribute(16, staffTrngCtgyRqdByDate); }


  public java.math.BigDecimal getStaffTrngCtgyRecurFreq() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(17); }

  public void setStaffTrngCtgyRecurFreq(java.math.BigDecimal staffTrngCtgyRecurFreq) throws SQLException
  { _struct.setAttribute(17, staffTrngCtgyRecurFreq); }


  public String getStaffTrngCtgyRecurFreqUom() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setStaffTrngCtgyRecurFreqUom(String staffTrngCtgyRecurFreqUom) throws SQLException
  { _struct.setAttribute(18, staffTrngCtgyRecurFreqUom); }


  public String getStaffTrngCtgyRqdFromQlfr() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setStaffTrngCtgyRqdFromQlfr(String staffTrngCtgyRqdFromQlfr) throws SQLException
  { _struct.setAttribute(19, staffTrngCtgyRqdFromQlfr); }


  public java.math.BigDecimal getNonCompAlertInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(20); }

  public void setNonCompAlertInd(java.math.BigDecimal nonCompAlertInd) throws SQLException
  { _struct.setAttribute(20, nonCompAlertInd); }


  public java.math.BigDecimal getNonCompAlertThreshold() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(21); }

  public void setNonCompAlertThreshold(java.math.BigDecimal nonCompAlertThreshold) throws SQLException
  { _struct.setAttribute(21, nonCompAlertThreshold); }


  public String setStaffTrngSchedPermQlfr() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setStaffTrngSchedPermQlfr(String staffTrngSchedPermQlfr) throws SQLException
  { _struct.setAttribute(22, staffTrngSchedPermQlfr); }

}
