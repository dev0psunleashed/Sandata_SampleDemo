package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class StaffBnftT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.STAFF_BNFT_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,91,91,91,91,12,12,12,2,2,12,12,12,12,91,91 };
  protected static ORADataFactory[] _factory = new ORADataFactory[17];
  protected static final StaffBnftT _StaffBnftTFactory = new StaffBnftT();

  public static ORADataFactory getORADataFactory()
  { return _StaffBnftTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[17], _sqlType, _factory); }
  public StaffBnftT()
  { _init_struct(true); }
  public StaffBnftT(java.math.BigDecimal staffBnftSk, String staffBnftId, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String staffId, String staffBnftName, String staffBnftDesc, java.sql.Timestamp staffBnftStartDate, java.sql.Timestamp staffBnftEndDate) throws SQLException
  { _init_struct(true);
    setStaffBnftSk(staffBnftSk);
    setStaffBnftId(staffBnftId);
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
    setStaffBnftName(staffBnftName);
    setStaffBnftDesc(staffBnftDesc);
    setStaffBnftStartDate(staffBnftStartDate);
    setStaffBnftEndDate(staffBnftEndDate);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(StaffBnftT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new StaffBnftT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getStaffBnftSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setStaffBnftSk(java.math.BigDecimal staffBnftSk) throws SQLException
  { _struct.setAttribute(0, staffBnftSk); }


  public String getStaffBnftId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setStaffBnftId(String staffBnftId) throws SQLException
  { _struct.setAttribute(1, staffBnftId); }


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


  public String getStaffBnftName() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setStaffBnftName(String staffBnftName) throws SQLException
  { _struct.setAttribute(13, staffBnftName); }


  public String getStaffBnftDesc() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setStaffBnftDesc(String staffBnftDesc) throws SQLException
  { _struct.setAttribute(14, staffBnftDesc); }


  public java.sql.Timestamp getStaffBnftStartDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(15); }

  public void setStaffBnftStartDate(java.sql.Timestamp staffBnftStartDate) throws SQLException
  { _struct.setAttribute(15, staffBnftStartDate); }


  public java.sql.Timestamp getStaffBnftEndDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(16); }

  public void setStaffBnftEndDate(java.sql.Timestamp staffBnftEndDate) throws SQLException
  { _struct.setAttribute(16, staffBnftEndDate); }

}
