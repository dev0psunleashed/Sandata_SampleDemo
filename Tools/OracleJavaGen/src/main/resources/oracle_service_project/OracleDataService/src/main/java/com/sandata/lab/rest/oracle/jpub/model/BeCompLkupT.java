package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class BeCompLkupT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.BE_COMP_LKUP_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,2,2,12,12,91,91,12,12,12,2,91,2,12,12,2,2,12,12,2,12,2,12,91 };
  protected static ORADataFactory[] _factory = new ORADataFactory[31];
  protected static final BeCompLkupT _BeCompLkupTFactory = new BeCompLkupT();

  public static ORADataFactory getORADataFactory()
  { return _BeCompLkupTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[31], _sqlType, _factory); }
  public BeCompLkupT()
  { _init_struct(true); }
  public BeCompLkupT(java.math.BigDecimal beCompLkupSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String compCtgyCode, java.sql.Timestamp compEffDate, java.sql.Timestamp compTermDate, String compCode, String compName, String compDesc, java.math.BigDecimal compRecurInd, java.sql.Timestamp compRqdByDate, java.math.BigDecimal compRecurFreq, String compRecurFreqUom, String compRqdFromDateQlfr, java.math.BigDecimal nonCompAlertInd, java.math.BigDecimal nonCompAlertThreshold, String compNote, String compAddlInfoQlfr, java.math.BigDecimal compAddlInfoRqdInd, String compAddlInfoName, java.math.BigDecimal compRequisiteItemInd, String compSchedPermQlfr, java.sql.Timestamp compRqdFromDate) throws SQLException
  { _init_struct(true);
    setBeCompLkupSk(beCompLkupSk);
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
    setCompCtgyCode(compCtgyCode);
    setCompEffDate(compEffDate);
    setCompTermDate(compTermDate);
    setCompCode(compCode);
    setCompName(compName);
    setCompDesc(compDesc);
    setCompRecurInd(compRecurInd);
    setCompRqdByDate(compRqdByDate);
    setCompRecurFreq(compRecurFreq);
    setCompRecurFreqUom(compRecurFreqUom);
    setCompRqdFromDateQlfr(compRqdFromDateQlfr);
    setNonCompAlertInd(nonCompAlertInd);
    setNonCompAlertThreshold(nonCompAlertThreshold);
    setCompNote(compNote);
    setCompAddlInfoQlfr(compAddlInfoQlfr);
    setCompAddlInfoRqdInd(compAddlInfoRqdInd);
    setCompAddlInfoName(compAddlInfoName);
    setCompRequisiteItemInd(compRequisiteItemInd);
    setCompSchedPermQlfr(compSchedPermQlfr);
    setCompRqdFromDate(compRqdFromDate);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(BeCompLkupT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new BeCompLkupT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getBeCompLkupSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setBeCompLkupSk(java.math.BigDecimal beCompLkupSk) throws SQLException
  { _struct.setAttribute(0, beCompLkupSk); }


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


  public String getCompCtgyCode() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setCompCtgyCode(String compCtgyCode) throws SQLException
  { _struct.setAttribute(11, compCtgyCode); }


  public java.sql.Timestamp getCompEffDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(12); }

  public void setCompEffDate(java.sql.Timestamp compEffDate) throws SQLException
  { _struct.setAttribute(12, compEffDate); }


  public java.sql.Timestamp getCompTermDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(13); }

  public void setCompTermDate(java.sql.Timestamp compTermDate) throws SQLException
  { _struct.setAttribute(13, compTermDate); }


  public String getCompCode() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setCompCode(String compCode) throws SQLException
  { _struct.setAttribute(14, compCode); }


  public String getCompName() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setCompName(String compName) throws SQLException
  { _struct.setAttribute(15, compName); }


  public String getCompDesc() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setCompDesc(String compDesc) throws SQLException
  { _struct.setAttribute(16, compDesc); }


  public java.math.BigDecimal getCompRecurInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(17); }

  public void setCompRecurInd(java.math.BigDecimal compRecurInd) throws SQLException
  { _struct.setAttribute(17, compRecurInd); }


  public java.sql.Timestamp getCompRqdByDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(18); }

  public void setCompRqdByDate(java.sql.Timestamp compRqdByDate) throws SQLException
  { _struct.setAttribute(18, compRqdByDate); }


  public java.math.BigDecimal getCompRecurFreq() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(19); }

  public void setCompRecurFreq(java.math.BigDecimal compRecurFreq) throws SQLException
  { _struct.setAttribute(19, compRecurFreq); }


  public String getCompRecurFreqUom() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setCompRecurFreqUom(String compRecurFreqUom) throws SQLException
  { _struct.setAttribute(20, compRecurFreqUom); }


  public String getCompRqdFromDateQlfr() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setCompRqdFromDateQlfr(String compRqdFromDateQlfr) throws SQLException
  { _struct.setAttribute(21, compRqdFromDateQlfr); }


  public java.math.BigDecimal getNonCompAlertInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(22); }

  public void setNonCompAlertInd(java.math.BigDecimal nonCompAlertInd) throws SQLException
  { _struct.setAttribute(22, nonCompAlertInd); }


  public java.math.BigDecimal getNonCompAlertThreshold() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(23); }

  public void setNonCompAlertThreshold(java.math.BigDecimal nonCompAlertThreshold) throws SQLException
  { _struct.setAttribute(23, nonCompAlertThreshold); }


  public String getCompNote() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setCompNote(String compNote) throws SQLException
  { _struct.setAttribute(24, compNote); }


  public String getCompAddlInfoQlfr() throws SQLException
  { return (String) _struct.getAttribute(25); }

  public void setCompAddlInfoQlfr(String compAddlInfoQlfr) throws SQLException
  { _struct.setAttribute(25, compAddlInfoQlfr); }


  public java.math.BigDecimal getCompAddlInfoRqdInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(26); }

  public void setCompAddlInfoRqdInd(java.math.BigDecimal compAddlInfoRqdInd) throws SQLException
  { _struct.setAttribute(26, compAddlInfoRqdInd); }


  public String getCompAddlInfoName() throws SQLException
  { return (String) _struct.getAttribute(27); }

  public void setCompAddlInfoName(String compAddlInfoName) throws SQLException
  { _struct.setAttribute(27, compAddlInfoName); }


  public java.math.BigDecimal getCompRequisiteItemInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(28); }

  public void setCompRequisiteItemInd(java.math.BigDecimal compRequisiteItemInd) throws SQLException
  { _struct.setAttribute(28, compRequisiteItemInd); }


  public String getCompSchedPermQlfr() throws SQLException
  { return (String) _struct.getAttribute(29); }

  public void setCompSchedPermQlfr(String compSchedPermQlfr) throws SQLException
  { _struct.setAttribute(29, compSchedPermQlfr); }


  public java.sql.Timestamp getCompRqdFromDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(30); }

  public void setCompRqdFromDate(java.sql.Timestamp compRqdFromDate) throws SQLException
  { _struct.setAttribute(30, compRqdFromDate); }

}
