package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class PmtAppliedT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PMT_APPLIED_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,91,91,91,91,12,12,12,2,2,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[12];
  protected static final PmtAppliedT _PmtAppliedTFactory = new PmtAppliedT();

  public static ORADataFactory getORADataFactory()
  { return _PmtAppliedTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[12], _sqlType, _factory); }
  public PmtAppliedT()
  { _init_struct(true); }
  public PmtAppliedT(java.math.BigDecimal pmtAppliedSk, String pmtAppliedId, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, java.sql.Timestamp recUpdateTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal changeVersionId, java.math.BigDecimal currRecInd, java.math.BigDecimal pmtSk) throws SQLException
  { _init_struct(true);
    setPmtAppliedSk(pmtAppliedSk);
    setPmtAppliedId(pmtAppliedId);
    setRecCreateTmstp(recCreateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonMemo(changeReasonMemo);
    setChangeVersionId(changeVersionId);
    setCurrRecInd(currRecInd);
    setPmtSk(pmtSk);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PmtAppliedT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PmtAppliedT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPmtAppliedSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPmtAppliedSk(java.math.BigDecimal pmtAppliedSk) throws SQLException
  { _struct.setAttribute(0, pmtAppliedSk); }


  public String getPmtAppliedId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setPmtAppliedId(String pmtAppliedId) throws SQLException
  { _struct.setAttribute(1, pmtAppliedId); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(2, recCreateTmstp); }


  public java.sql.Timestamp getRecEffTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(3); }

  public void setRecEffTmstp(java.sql.Timestamp recEffTmstp) throws SQLException
  { _struct.setAttribute(3, recEffTmstp); }


  public java.sql.Timestamp getRecTermTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(4); }

  public void setRecTermTmstp(java.sql.Timestamp recTermTmstp) throws SQLException
  { _struct.setAttribute(4, recTermTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(5); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(5, recUpdateTmstp); }


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


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(9); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(9, changeVersionId); }


  public java.math.BigDecimal getCurrRecInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(10); }

  public void setCurrRecInd(java.math.BigDecimal currRecInd) throws SQLException
  { _struct.setAttribute(10, currRecInd); }


  public java.math.BigDecimal getPmtSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(11); }

  public void setPmtSk(java.math.BigDecimal pmtSk) throws SQLException
  { _struct.setAttribute(11, pmtSk); }

}
