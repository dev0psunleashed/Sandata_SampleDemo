package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class StaffHiringHistT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.STAFF_HIRING_HIST_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,91,91,91,91,12,12,12,12,2,2,12,12,2,12,12,91,91,91,91,91,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[23];
  protected static final StaffHiringHistT _StaffHiringHistTFactory = new StaffHiringHistT();

  public static ORADataFactory getORADataFactory()
  { return _StaffHiringHistTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[23], _sqlType, _factory); }
  public StaffHiringHistT()
  { _init_struct(true); }
  public StaffHiringHistT(java.math.BigDecimal staffHiringHistSk, String staffHiringHistId, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonCode, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String staffId, java.math.BigDecimal appCmpldInd, String positionAppliedFor, String staffHiringCurrStatus, java.sql.Timestamp staffStartingDate, java.sql.Timestamp staffHiringDate, java.sql.Timestamp staffTermDate, java.sql.Timestamp staffRehireDate, java.sql.Timestamp staffHistOtherDate, String staffHistOtherDesc) throws SQLException
  { _init_struct(true);
    setStaffHiringHistSk(staffHiringHistSk);
    setStaffHiringHistId(staffHiringHistId);
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
    setStaffId(staffId);
    setAppCmpldInd(appCmpldInd);
    setPositionAppliedFor(positionAppliedFor);
    setStaffHiringCurrStatus(staffHiringCurrStatus);
    setStaffStartingDate(staffStartingDate);
    setStaffHiringDate(staffHiringDate);
    setStaffTermDate(staffTermDate);
    setStaffRehireDate(staffRehireDate);
    setStaffHistOtherDate(staffHistOtherDate);
    setStaffHistOtherDesc(staffHistOtherDesc);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(StaffHiringHistT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new StaffHiringHistT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getStaffHiringHistSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setStaffHiringHistSk(java.math.BigDecimal staffHiringHistSk) throws SQLException
  { _struct.setAttribute(0, staffHiringHistSk); }


  public String getStaffHiringHistId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setStaffHiringHistId(String staffHiringHistId) throws SQLException
  { _struct.setAttribute(1, staffHiringHistId); }


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


  public String getStaffId() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setStaffId(String staffId) throws SQLException
  { _struct.setAttribute(13, staffId); }


  public java.math.BigDecimal getAppCmpldInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(14); }

  public void setAppCmpldInd(java.math.BigDecimal appCmpldInd) throws SQLException
  { _struct.setAttribute(14, appCmpldInd); }


  public String getPositionAppliedFor() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setPositionAppliedFor(String positionAppliedFor) throws SQLException
  { _struct.setAttribute(15, positionAppliedFor); }


  public String getStaffHiringCurrStatus() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setStaffHiringCurrStatus(String staffHiringCurrStatus) throws SQLException
  { _struct.setAttribute(16, staffHiringCurrStatus); }


  public java.sql.Timestamp getStaffStartingDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(17); }

  public void setStaffStartingDate(java.sql.Timestamp staffStartingDate) throws SQLException
  { _struct.setAttribute(17, staffStartingDate); }


  public java.sql.Timestamp getStaffHiringDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(18); }

  public void setStaffHiringDate(java.sql.Timestamp staffHiringDate) throws SQLException
  { _struct.setAttribute(18, staffHiringDate); }


  public java.sql.Timestamp getStaffTermDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(19); }

  public void setStaffTermDate(java.sql.Timestamp staffTermDate) throws SQLException
  { _struct.setAttribute(19, staffTermDate); }


  public java.sql.Timestamp getStaffRehireDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(20); }

  public void setStaffRehireDate(java.sql.Timestamp staffRehireDate) throws SQLException
  { _struct.setAttribute(20, staffRehireDate); }


  public java.sql.Timestamp getStaffHistOtherDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(21); }

  public void setStaffHistOtherDate(java.sql.Timestamp staffHistOtherDate) throws SQLException
  { _struct.setAttribute(21, staffHistOtherDate); }


  public String getStaffHistOtherDesc() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setStaffHistOtherDesc(String staffHistOtherDesc) throws SQLException
  { _struct.setAttribute(22, staffHistOtherDesc); }

}
