package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class AdminStaffT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.ADMIN_STAFF_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,2,2,12,12,12,12,12,91,12,12,12,12,12,12,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[25];
  protected static final AdminStaffT _AdminStaffTFactory = new AdminStaffT();

  public static ORADataFactory getORADataFactory()
  { return _AdminStaffTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[25], _sqlType, _factory); }
  public AdminStaffT()
  { _init_struct(true); }
  public AdminStaffT(java.math.BigDecimal adminStaffSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String adminStaffId, String adminStaffFirstName, String adminStaffMiddleName, String adminStaffLastName, java.sql.Timestamp adminStaffDob, String adminStaffHomePhone, String adminStaffMobilePhone, String adminStaffAddr1, String adminStaffAddr2, String adminStaffCity, String adminStaffState, String adminStaffPstlCode, String adminStaffZip4, String adminStaffEmailAddr) throws SQLException
  { _init_struct(true);
    setAdminStaffSk(adminStaffSk);
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
    setAdminStaffId(adminStaffId);
    setAdminStaffFirstName(adminStaffFirstName);
    setAdminStaffMiddleName(adminStaffMiddleName);
    setAdminStaffLastName(adminStaffLastName);
    setAdminStaffDob(adminStaffDob);
    setAdminStaffHomePhone(adminStaffHomePhone);
    setAdminStaffMobilePhone(adminStaffMobilePhone);
    setAdminStaffAddr1(adminStaffAddr1);
    setAdminStaffAddr2(adminStaffAddr2);
    setAdminStaffCity(adminStaffCity);
    setAdminStaffState(adminStaffState);
    setAdminStaffPstlCode(adminStaffPstlCode);
    setAdminStaffZip4(adminStaffZip4);
    setAdminStaffEmailAddr(adminStaffEmailAddr);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AdminStaffT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AdminStaffT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAdminStaffSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAdminStaffSk(java.math.BigDecimal adminStaffSk) throws SQLException
  { _struct.setAttribute(0, adminStaffSk); }


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


  public String getAdminStaffId() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setAdminStaffId(String adminStaffId) throws SQLException
  { _struct.setAttribute(11, adminStaffId); }


  public String getAdminStaffFirstName() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setAdminStaffFirstName(String adminStaffFirstName) throws SQLException
  { _struct.setAttribute(12, adminStaffFirstName); }


  public String getAdminStaffMiddleName() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setAdminStaffMiddleName(String adminStaffMiddleName) throws SQLException
  { _struct.setAttribute(13, adminStaffMiddleName); }


  public String getAdminStaffLastName() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setAdminStaffLastName(String adminStaffLastName) throws SQLException
  { _struct.setAttribute(14, adminStaffLastName); }


  public java.sql.Timestamp getAdminStaffDob() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(15); }

  public void setAdminStaffDob(java.sql.Timestamp adminStaffDob) throws SQLException
  { _struct.setAttribute(15, adminStaffDob); }


  public String getAdminStaffHomePhone() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setAdminStaffHomePhone(String adminStaffHomePhone) throws SQLException
  { _struct.setAttribute(16, adminStaffHomePhone); }


  public String getAdminStaffMobilePhone() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setAdminStaffMobilePhone(String adminStaffMobilePhone) throws SQLException
  { _struct.setAttribute(17, adminStaffMobilePhone); }


  public String getAdminStaffAddr1() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setAdminStaffAddr1(String adminStaffAddr1) throws SQLException
  { _struct.setAttribute(18, adminStaffAddr1); }


  public String getAdminStaffAddr2() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setAdminStaffAddr2(String adminStaffAddr2) throws SQLException
  { _struct.setAttribute(19, adminStaffAddr2); }


  public String getAdminStaffCity() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setAdminStaffCity(String adminStaffCity) throws SQLException
  { _struct.setAttribute(20, adminStaffCity); }


  public String getAdminStaffState() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setAdminStaffState(String adminStaffState) throws SQLException
  { _struct.setAttribute(21, adminStaffState); }


  public String getAdminStaffPstlCode() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setAdminStaffPstlCode(String adminStaffPstlCode) throws SQLException
  { _struct.setAttribute(22, adminStaffPstlCode); }


  public String getAdminStaffZip4() throws SQLException
  { return (String) _struct.getAttribute(23); }

  public void setAdminStaffZip4(String adminStaffZip4) throws SQLException
  { _struct.setAttribute(23, adminStaffZip4); }


  public String getAdminStaffEmailAddr() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setAdminStaffEmailAddr(String adminStaffEmailAddr) throws SQLException
  { _struct.setAttribute(24, adminStaffEmailAddr); }

}
