package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class ArTxnBatchDetT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.AR_TXN_BATCH_DET_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,12,12,12,12,2,12,12,12,12,91,91,91,12,12,2,12,12,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[21];
  protected static final ArTxnBatchDetT _ArTxnBatchDetTFactory = new ArTxnBatchDetT();

  public static ORADataFactory getORADataFactory()
  { return _ArTxnBatchDetTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[21], _sqlType, _factory); }
  public ArTxnBatchDetT()
  { _init_struct(true); }
  public ArTxnBatchDetT(java.math.BigDecimal arTxnBatchDetSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonCode, String changeReasonMemo, java.math.BigDecimal changeVersionId, String beId, String arTxnBatchId, String payerId, String invNum, java.sql.Timestamp checkDate, java.sql.Timestamp checkDepositDate, java.sql.Timestamp checkRcvdDate, String pmtTypQlfr, String pmtTypNum, java.math.BigDecimal pmtAmt, String arNoteTypCode, String arTxnNote, java.math.BigDecimal arTxnBatchPostInd) throws SQLException
  { _init_struct(true);
    setArTxnBatchDetSk(arTxnBatchDetSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonCode(changeReasonCode);
    setChangeReasonMemo(changeReasonMemo);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setArTxnBatchId(arTxnBatchId);
    setPayerId(payerId);
    setInvNum(invNum);
    setCheckDate(checkDate);
    setCheckDepositDate(checkDepositDate);
    setCheckRcvdDate(checkRcvdDate);
    setPmtTypQlfr(pmtTypQlfr);
    setPmtTypNum(pmtTypNum);
    setPmtAmt(pmtAmt);
    setArNoteTypCode(arNoteTypCode);
    setArTxnNote(arTxnNote);
    setArTxnBatchPostInd(arTxnBatchPostInd);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(ArTxnBatchDetT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new ArTxnBatchDetT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getArTxnBatchDetSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setArTxnBatchDetSk(java.math.BigDecimal arTxnBatchDetSk) throws SQLException
  { _struct.setAttribute(0, arTxnBatchDetSk); }


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


  public String getArTxnBatchId() throws SQLException
  { return (String) _struct.getAttribute(9); }

  public void setArTxnBatchId(String arTxnBatchId) throws SQLException
  { _struct.setAttribute(9, arTxnBatchId); }


  public String getPayerId() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setPayerId(String payerId) throws SQLException
  { _struct.setAttribute(10, payerId); }


  public String getInvNum() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setInvNum(String invNum) throws SQLException
  { _struct.setAttribute(11, invNum); }


  public java.sql.Timestamp getCheckDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(12); }

  public void setCheckDate(java.sql.Timestamp checkDate) throws SQLException
  { _struct.setAttribute(12, checkDate); }


  public java.sql.Timestamp getCheckDepositDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(13); }

  public void setCheckDepositDate(java.sql.Timestamp checkDepositDate) throws SQLException
  { _struct.setAttribute(13, checkDepositDate); }


  public java.sql.Timestamp getCheckRcvdDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(14); }

  public void setCheckRcvdDate(java.sql.Timestamp checkRcvdDate) throws SQLException
  { _struct.setAttribute(14, checkRcvdDate); }


  public String getPmtTypQlfr() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setPmtTypQlfr(String pmtTypQlfr) throws SQLException
  { _struct.setAttribute(15, pmtTypQlfr); }


  public String getPmtTypNum() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setPmtTypNum(String pmtTypNum) throws SQLException
  { _struct.setAttribute(16, pmtTypNum); }


  public java.math.BigDecimal getPmtAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(17); }

  public void setPmtAmt(java.math.BigDecimal pmtAmt) throws SQLException
  { _struct.setAttribute(17, pmtAmt); }


  public String getArNoteTypCode() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setArNoteTypCode(String arNoteTypCode) throws SQLException
  { _struct.setAttribute(18, arNoteTypCode); }


  public String getArTxnNote() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setArTxnNote(String arTxnNote) throws SQLException
  { _struct.setAttribute(19, arTxnNote); }


  public java.math.BigDecimal getArTxnBatchPostInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(20); }

  public void setArTxnBatchPostInd(java.math.BigDecimal arTxnBatchPostInd) throws SQLException
  { _struct.setAttribute(20, arTxnBatchPostInd); }

}
