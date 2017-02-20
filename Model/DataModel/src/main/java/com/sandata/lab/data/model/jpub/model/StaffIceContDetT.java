package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class StaffIceContDetT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.STAFF_ICE_CONT_DET_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,2,12,2,12,12,12,12,12,12,12,12,12,12,1,12,12,12,12,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[28];
  protected static final StaffIceContDetT _StaffIceContDetTFactory = new StaffIceContDetT();

  public static ORADataFactory getORADataFactory()
  { return _StaffIceContDetTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[28], _sqlType, _factory); }
  public StaffIceContDetT()
  { _init_struct(true); }
  public StaffIceContDetT(java.math.BigDecimal staffIceContDetSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, java.math.BigDecimal currRecInd, String changeReasonMemo, java.math.BigDecimal changeVersionId, String beId, String staffId, String contRel, String staffIceContFirstName, String staffIceContMiddleName, String staffIceContLastName, String staffIceContSuffixName, String staffIceContAddr1, String staffIceContAddr2, String staffIceContCity, String staffIceContState, String staffIceContPstlCode, String staffIceContZip4, String staffIceContHomePhone, String staffIceContWorkPhone, String staffIceContMobilePhone, String staffIceContEmail, String staffIceContEmailOther) throws SQLException
  { _init_struct(true);
    setStaffIceContDetSk(staffIceContDetSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setCurrRecInd(currRecInd);
    setChangeReasonMemo(changeReasonMemo);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setStaffId(staffId);
    setContRel(contRel);
    setStaffIceContFirstName(staffIceContFirstName);
    setStaffIceContMiddleName(staffIceContMiddleName);
    setStaffIceContLastName(staffIceContLastName);
    setStaffIceContSuffixName(staffIceContSuffixName);
    setStaffIceContAddr1(staffIceContAddr1);
    setStaffIceContAddr2(staffIceContAddr2);
    setStaffIceContCity(staffIceContCity);
    setStaffIceContState(staffIceContState);
    setStaffIceContPstlCode(staffIceContPstlCode);
    setStaffIceContZip4(staffIceContZip4);
    setStaffIceContHomePhone(staffIceContHomePhone);
    setStaffIceContWorkPhone(staffIceContWorkPhone);
    setStaffIceContMobilePhone(staffIceContMobilePhone);
    setStaffIceContEmail(staffIceContEmail);
    setStaffIceContEmailOther(staffIceContEmailOther);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(StaffIceContDetT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new StaffIceContDetT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getStaffIceContDetSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setStaffIceContDetSk(java.math.BigDecimal staffIceContDetSk) throws SQLException
  { _struct.setAttribute(0, staffIceContDetSk); }


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


  public java.math.BigDecimal getCurrRecInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(7); }

  public void setCurrRecInd(java.math.BigDecimal currRecInd) throws SQLException
  { _struct.setAttribute(7, currRecInd); }


  public String getChangeReasonMemo() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setChangeReasonMemo(String changeReasonMemo) throws SQLException
  { _struct.setAttribute(8, changeReasonMemo); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(9); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(9, changeVersionId); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(10, beId); }


  public String getStaffId() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setStaffId(String staffId) throws SQLException
  { _struct.setAttribute(11, staffId); }


  public String getContRel() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setContRel(String contRel) throws SQLException
  { _struct.setAttribute(12, contRel); }


  public String getStaffIceContFirstName() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setStaffIceContFirstName(String staffIceContFirstName) throws SQLException
  { _struct.setAttribute(13, staffIceContFirstName); }


  public String getStaffIceContMiddleName() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setStaffIceContMiddleName(String staffIceContMiddleName) throws SQLException
  { _struct.setAttribute(14, staffIceContMiddleName); }


  public String getStaffIceContLastName() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setStaffIceContLastName(String staffIceContLastName) throws SQLException
  { _struct.setAttribute(15, staffIceContLastName); }


  public String getStaffIceContSuffixName() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setStaffIceContSuffixName(String staffIceContSuffixName) throws SQLException
  { _struct.setAttribute(16, staffIceContSuffixName); }


  public String getStaffIceContAddr1() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setStaffIceContAddr1(String staffIceContAddr1) throws SQLException
  { _struct.setAttribute(17, staffIceContAddr1); }


  public String getStaffIceContAddr2() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setStaffIceContAddr2(String staffIceContAddr2) throws SQLException
  { _struct.setAttribute(18, staffIceContAddr2); }


  public String getStaffIceContCity() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setStaffIceContCity(String staffIceContCity) throws SQLException
  { _struct.setAttribute(19, staffIceContCity); }


  public String getStaffIceContState() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setStaffIceContState(String staffIceContState) throws SQLException
  { _struct.setAttribute(20, staffIceContState); }


  public String getStaffIceContPstlCode() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setStaffIceContPstlCode(String staffIceContPstlCode) throws SQLException
  { _struct.setAttribute(21, staffIceContPstlCode); }


  public String getStaffIceContZip4() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setStaffIceContZip4(String staffIceContZip4) throws SQLException
  { _struct.setAttribute(22, staffIceContZip4); }


  public String getStaffIceContHomePhone() throws SQLException
  { return (String) _struct.getAttribute(23); }

  public void setStaffIceContHomePhone(String staffIceContHomePhone) throws SQLException
  { _struct.setAttribute(23, staffIceContHomePhone); }


  public String getStaffIceContWorkPhone() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setStaffIceContWorkPhone(String staffIceContWorkPhone) throws SQLException
  { _struct.setAttribute(24, staffIceContWorkPhone); }


  public String getStaffIceContMobilePhone() throws SQLException
  { return (String) _struct.getAttribute(25); }

  public void setStaffIceContMobilePhone(String staffIceContMobilePhone) throws SQLException
  { _struct.setAttribute(25, staffIceContMobilePhone); }


  public String getStaffIceContEmail() throws SQLException
  { return (String) _struct.getAttribute(26); }

  public void setStaffIceContEmail(String staffIceContEmail) throws SQLException
  { _struct.setAttribute(26, staffIceContEmail); }


  public String getStaffIceContEmailOther() throws SQLException
  { return (String) _struct.getAttribute(27); }

  public void setStaffIceContEmailOther(String staffIceContEmailOther) throws SQLException
  { _struct.setAttribute(27, staffIceContEmailOther); }

}
