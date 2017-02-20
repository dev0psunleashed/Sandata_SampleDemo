package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class StaffRelT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.STAFF_REL_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,12,12,12,12,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[11];
  protected static final StaffRelT _StaffRelTFactory = new StaffRelT();

  public static ORADataFactory getORADataFactory()
  { return _StaffRelTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[11], _sqlType, _factory); }
  public StaffRelT()
  { _init_struct(true); }
  public StaffRelT(java.math.BigDecimal staffRelSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal changeVersionId, String beId, String staffId, String staffParentId, String staffRelTyp, String staffRelStatus, String staffRelEndDate, String staffRelStartDate) throws SQLException
  { _init_struct(true);
    setStaffRelSk(staffRelSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setStaffId(staffId);
    setStaffParentId(staffParentId);
    setStaffRelTyp(staffRelTyp);
    setStaffRelStatus(staffRelStatus);
    setStaffRelEndDate(staffRelEndDate);
    setStaffRelStartDate(staffRelStartDate);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(StaffRelT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new StaffRelT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getStaffRelSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setStaffRelSk(java.math.BigDecimal staffRelSk) throws SQLException
  { _struct.setAttribute(0, staffRelSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(3, changeVersionId); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(4, beId); }


  public String getStaffId() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setStaffId(String staffId) throws SQLException
  { _struct.setAttribute(5, staffId); }


  public String getStaffParentId() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setStaffParentId(String staffParentId) throws SQLException
  { _struct.setAttribute(6, staffParentId); }


  public String getStaffRelTyp() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setStaffRelTyp(String staffRelTyp) throws SQLException
  { _struct.setAttribute(7, staffRelTyp); }


  public String getStaffRelStatus() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setStaffRelStatus(String staffRelStatus) throws SQLException
  { _struct.setAttribute(8, staffRelStatus); }


  public String getStaffRelEndDate() throws SQLException
  { return (String) _struct.getAttribute(9); }

  public void setStaffRelEndDate(String staffRelEndDate) throws SQLException
  { _struct.setAttribute(9, staffRelEndDate); }


  public String getStaffRelStartDate() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setStaffRelStartDate(String staffRelStartDate) throws SQLException
  { _struct.setAttribute(10, staffRelStartDate); }

}
