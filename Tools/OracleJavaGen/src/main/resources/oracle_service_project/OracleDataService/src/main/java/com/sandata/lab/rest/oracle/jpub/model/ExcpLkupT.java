package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class ExcpLkupT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.EXCP_LKUP_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,2,2,12,2,12,12,2,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[16];
  protected static final ExcpLkupT _ExcpLkupTFactory = new ExcpLkupT();

  public static ORADataFactory getORADataFactory()
  { return _ExcpLkupTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[16], _sqlType, _factory); }
  public ExcpLkupT()
  { _init_struct(true); }
  public ExcpLkupT(java.math.BigDecimal excpLkupSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String appModName, java.math.BigDecimal excpId, String excpName, String excpDesc, java.math.BigDecimal excpNonFixableInd, java.math.BigDecimal excpAckInd) throws SQLException
  { _init_struct(true);
    setExcpLkupSk(excpLkupSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonMemo(changeReasonMemo);
    setCurrRecInd(currRecInd);
    setChangeVersionId(changeVersionId);
    setAppModName(appModName);
    setExcpId(excpId);
    setExcpName(excpName);
    setExcpDesc(excpDesc);
    setExcpNonFixableInd(excpNonFixableInd);
    setExcpAckInd(excpAckInd);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(ExcpLkupT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new ExcpLkupT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getExcpLkupSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setExcpLkupSk(java.math.BigDecimal excpLkupSk) throws SQLException
  { _struct.setAttribute(0, excpLkupSk); }


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


  public String getAppModName() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setAppModName(String appModName) throws SQLException
  { _struct.setAttribute(10, appModName); }


  public java.math.BigDecimal getExcpId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(11); }

  public void setExcpId(java.math.BigDecimal excpId) throws SQLException
  { _struct.setAttribute(11, excpId); }


  public String getExcpName() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setExcpName(String excpName) throws SQLException
  { _struct.setAttribute(12, excpName); }


  public String getExcpDesc() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setExcpDesc(String excpDesc) throws SQLException
  { _struct.setAttribute(13, excpDesc); }


  public java.math.BigDecimal getExcpNonFixableInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(14); }

  public void setExcpNonFixableInd(java.math.BigDecimal excpNonFixableInd) throws SQLException
  { _struct.setAttribute(14, excpNonFixableInd); }


  public java.math.BigDecimal getExcpAckInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(15); }

  public void setExcpAckInd(java.math.BigDecimal excpAckInd) throws SQLException
  { _struct.setAttribute(15, excpAckInd); }

}
