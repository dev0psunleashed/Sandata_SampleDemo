package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class AdminStaffPtT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.ADMIN_STAFF_PT_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,2,2,12,12,12,91,91,12,91,91 };
  protected static ORADataFactory[] _factory = new ORADataFactory[18];
  protected static final AdminStaffPtT _AdminStaffPtTFactory = new AdminStaffPtT();

  public static ORADataFactory getORADataFactory()
  { return _AdminStaffPtTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[18], _sqlType, _factory); }
  public AdminStaffPtT()
  { _init_struct(true); }
  public AdminStaffPtT(java.math.BigDecimal adminStaffPtSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String adminStaffId, String ptId, java.sql.Timestamp adminStaffPtEffDate, java.sql.Timestamp adminStaffPtTermDate, String adminStaffRoleName, java.sql.Timestamp adminStaffRoleEffDate, java.sql.Timestamp adminStaffRoleTermDate) throws SQLException
  { _init_struct(true);
    setAdminStaffPtSk(adminStaffPtSk);
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
    setPtId(ptId);
    setAdminStaffPtEffDate(adminStaffPtEffDate);
    setAdminStaffPtTermDate(adminStaffPtTermDate);
    setAdminStaffRoleName(adminStaffRoleName);
    setAdminStaffRoleEffDate(adminStaffRoleEffDate);
    setAdminStaffRoleTermDate(adminStaffRoleTermDate);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AdminStaffPtT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AdminStaffPtT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAdminStaffPtSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAdminStaffPtSk(java.math.BigDecimal adminStaffPtSk) throws SQLException
  { _struct.setAttribute(0, adminStaffPtSk); }


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


  public String getPtId() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setPtId(String ptId) throws SQLException
  { _struct.setAttribute(12, ptId); }


  public java.sql.Timestamp getAdminStaffPtEffDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(13); }

  public void setAdminStaffPtEffDate(java.sql.Timestamp adminStaffPtEffDate) throws SQLException
  { _struct.setAttribute(13, adminStaffPtEffDate); }


  public java.sql.Timestamp getAdminStaffPtTermDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(14); }

  public void setAdminStaffPtTermDate(java.sql.Timestamp adminStaffPtTermDate) throws SQLException
  { _struct.setAttribute(14, adminStaffPtTermDate); }


  public String getAdminStaffRoleName() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setAdminStaffRoleName(String adminStaffRoleName) throws SQLException
  { _struct.setAttribute(15, adminStaffRoleName); }


  public java.sql.Timestamp getAdminStaffRoleEffDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(16); }

  public void setAdminStaffRoleEffDate(java.sql.Timestamp adminStaffRoleEffDate) throws SQLException
  { _struct.setAttribute(16, adminStaffRoleEffDate); }


  public java.sql.Timestamp getAdminStaffRoleTermDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(17); }

  public void setAdminStaffRoleTermDate(java.sql.Timestamp adminStaffRoleTermDate) throws SQLException
  { _struct.setAttribute(17, adminStaffRoleTermDate); }

}