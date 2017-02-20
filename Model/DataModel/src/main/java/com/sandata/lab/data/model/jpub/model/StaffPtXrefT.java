package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class StaffPtXrefT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.STAFF_PT_XREF_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,12,12,12,91,91,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[10];
  protected static final StaffPtXrefT _StaffPtXrefTFactory = new StaffPtXrefT();

  public static ORADataFactory getORADataFactory()
  { return _StaffPtXrefTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[10], _sqlType, _factory); }
  public StaffPtXrefT()
  { _init_struct(true); }
  public StaffPtXrefT(java.math.BigDecimal staffPtXrefSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recTermTmstp, java.math.BigDecimal changeVersionId, String beId, String ptId, String staffId, java.sql.Timestamp staffPtXrefEffDate, java.sql.Timestamp staffPtXrefTermDate, String svcName) throws SQLException
  { _init_struct(true);
    setStaffPtXrefSk(staffPtXrefSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecTermTmstp(recTermTmstp);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setPtId(ptId);
    setStaffId(staffId);
    setStaffPtXrefEffDate(staffPtXrefEffDate);
    setStaffPtXrefTermDate(staffPtXrefTermDate);
    setSvcName(svcName);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(StaffPtXrefT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new StaffPtXrefT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getStaffPtXrefSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setStaffPtXrefSk(java.math.BigDecimal staffPtXrefSk) throws SQLException
  { _struct.setAttribute(0, staffPtXrefSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecTermTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecTermTmstp(java.sql.Timestamp recTermTmstp) throws SQLException
  { _struct.setAttribute(2, recTermTmstp); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(3, changeVersionId); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(4, beId); }


  public String getPtId() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setPtId(String ptId) throws SQLException
  { _struct.setAttribute(5, ptId); }


  public String getStaffId() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setStaffId(String staffId) throws SQLException
  { _struct.setAttribute(6, staffId); }


  public java.sql.Timestamp getStaffPtXrefEffDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(7); }

  public void setStaffPtXrefEffDate(java.sql.Timestamp staffPtXrefEffDate) throws SQLException
  { _struct.setAttribute(7, staffPtXrefEffDate); }


  public java.sql.Timestamp getStaffPtXrefTermDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(8); }

  public void setStaffPtXrefTermDate(java.sql.Timestamp staffPtXrefTermDate) throws SQLException
  { _struct.setAttribute(8, staffPtXrefTermDate); }


  public String getSvcName() throws SQLException
  { return (String) _struct.getAttribute(9); }

  public void setSvcName(String svcName) throws SQLException
  { _struct.setAttribute(9, svcName); }

}
