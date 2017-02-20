package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class StaffMedclHistT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.STAFF_MEDCL_HIST_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,91,91,91,91,12,12,12,2,2,12,12,12,91,91,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[19];
  protected static final StaffMedclHistT _StaffMedclHistTFactory = new StaffMedclHistT();

  public static ORADataFactory getORADataFactory()
  { return _StaffMedclHistTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[19], _sqlType, _factory); }
  public StaffMedclHistT()
  { _init_struct(true); }
  public StaffMedclHistT(java.math.BigDecimal staffMedclHistSk, String staffMedclHistId, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String staffId, String medclExamItemName, java.sql.Timestamp staffMedclItemDate, java.sql.Timestamp staffMedclItemExprDate, String staffMedclResult, String staffMedclResultRdng, String staffMedclHistNote) throws SQLException
  { _init_struct(true);
    setStaffMedclHistSk(staffMedclHistSk);
    setStaffMedclHistId(staffMedclHistId);
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
    setStaffId(staffId);
    setMedclExamItemName(medclExamItemName);
    setStaffMedclItemDate(staffMedclItemDate);
    setStaffMedclItemExprDate(staffMedclItemExprDate);
    setStaffMedclResult(staffMedclResult);
    setStaffMedclResultRdng(staffMedclResultRdng);
    setStaffMedclHistNote(staffMedclHistNote);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(StaffMedclHistT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new StaffMedclHistT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getStaffMedclHistSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setStaffMedclHistSk(java.math.BigDecimal staffMedclHistSk) throws SQLException
  { _struct.setAttribute(0, staffMedclHistSk); }


  public String getStaffMedclHistId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setStaffMedclHistId(String staffMedclHistId) throws SQLException
  { _struct.setAttribute(1, staffMedclHistId); }


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


  public String getStaffId() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setStaffId(String staffId) throws SQLException
  { _struct.setAttribute(12, staffId); }


  public String getMedclExamItemName() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setMedclExamItemName(String medclExamItemName) throws SQLException
  { _struct.setAttribute(13, medclExamItemName); }


  public java.sql.Timestamp getStaffMedclItemDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(14); }

  public void setStaffMedclItemDate(java.sql.Timestamp staffMedclItemDate) throws SQLException
  { _struct.setAttribute(14, staffMedclItemDate); }


  public java.sql.Timestamp getStaffMedclItemExprDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(15); }

  public void setStaffMedclItemExprDate(java.sql.Timestamp staffMedclItemExprDate) throws SQLException
  { _struct.setAttribute(15, staffMedclItemExprDate); }


  public String getStaffMedclResult() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setStaffMedclResult(String staffMedclResult) throws SQLException
  { _struct.setAttribute(16, staffMedclResult); }


  public String getStaffMedclResultRdng() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setStaffMedclResultRdng(String staffMedclResultRdng) throws SQLException
  { _struct.setAttribute(17, staffMedclResultRdng); }


  public String getStaffMedclHistNote() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setStaffMedclHistNote(String staffMedclHistNote) throws SQLException
  { _struct.setAttribute(18, staffMedclHistNote); }

}
