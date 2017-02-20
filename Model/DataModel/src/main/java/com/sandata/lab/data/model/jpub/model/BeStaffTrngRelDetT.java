package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class BeStaffTrngRelDetT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.BE_STAFF_TRNG_REL_DET_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,12,12,12,12,2,12,2,12,2,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[13];
  protected static final BeStaffTrngRelDetT _BeStaffTrngRelDetTFactory = new BeStaffTrngRelDetT();

  public static ORADataFactory getORADataFactory()
  { return _BeStaffTrngRelDetTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[13], _sqlType, _factory); }
  public BeStaffTrngRelDetT()
  { _init_struct(true); }
  public BeStaffTrngRelDetT(java.math.BigDecimal beStaffTrngRelDetSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonCode, String changeReasonMemo, java.math.BigDecimal changeVersionId, String beId, java.math.BigDecimal beStaffTrngRelSk, String staffTrngResultVal, java.math.BigDecimal staffTrngCompliantInd, String staffTrngSchedPermQlfr) throws SQLException
  { _init_struct(true);
    setBeStaffTrngRelDetSk(beStaffTrngRelDetSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonCode(changeReasonCode);
    setChangeReasonMemo(changeReasonMemo);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setBeStaffTrngRelSk(beStaffTrngRelSk);
    setStaffTrngResultVal(staffTrngResultVal);
    setStaffTrngCompliantInd(staffTrngCompliantInd);
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
  protected ORAData create(BeStaffTrngRelDetT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new BeStaffTrngRelDetT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getBeStaffTrngRelDetSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setBeStaffTrngRelDetSk(java.math.BigDecimal beStaffTrngRelDetSk) throws SQLException
  { _struct.setAttribute(0, beStaffTrngRelDetSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public String getRecCreatedBy() throws SQLException
  { return (String) _struct.getAttribute(3); }

  public void setRecCreatedBy(String recCreatedBy) throws SQLException
  { _struct.setAttribute(3, recCreatedBy); }


  public String getRecUpdatedBy() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setRecUpdatedBy(String recUpdatedBy) throws SQLException
  { _struct.setAttribute(4, recUpdatedBy); }


  public String getChangeReasonCode() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setChangeReasonCode(String changeReasonCode) throws SQLException
  { _struct.setAttribute(5, changeReasonCode); }


  public String getChangeReasonMemo() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setChangeReasonMemo(String changeReasonMemo) throws SQLException
  { _struct.setAttribute(6, changeReasonMemo); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(7); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(7, changeVersionId); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(8, beId); }


  public java.math.BigDecimal getBeStaffTrngRelSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(9); }

  public void setBeStaffTrngRelSk(java.math.BigDecimal beStaffTrngRelSk) throws SQLException
  { _struct.setAttribute(9, beStaffTrngRelSk); }


  public String getStaffTrngResultVal() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setStaffTrngResultVal(String staffTrngResultVal) throws SQLException
  { _struct.setAttribute(10, staffTrngResultVal); }


  public java.math.BigDecimal getStaffTrngCompliantInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(11); }

  public void setStaffTrngCompliantInd(java.math.BigDecimal staffTrngCompliantInd) throws SQLException
  { _struct.setAttribute(11, staffTrngCompliantInd); }


  public String getStaffTrngSchedPermQlfr() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setStaffTrngSchedPermQlfr(String staffTrngSchedPermQlfr) throws SQLException
  { _struct.setAttribute(12, staffTrngSchedPermQlfr); }

}
