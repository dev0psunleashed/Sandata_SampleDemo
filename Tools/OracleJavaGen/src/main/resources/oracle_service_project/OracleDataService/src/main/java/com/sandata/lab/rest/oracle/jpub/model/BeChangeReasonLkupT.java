package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class BeChangeReasonLkupT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.BE_CHANGE_REASON_LKUP_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,2,91,91,91,91,12,12,12,2,2,12,12,12,12,12,91,91,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[19];
  protected static final BeChangeReasonLkupT _BeChangeReasonLkupTFactory = new BeChangeReasonLkupT();

  public static ORADataFactory getORADataFactory()
  { return _BeChangeReasonLkupTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[19], _sqlType, _factory); }
  public BeChangeReasonLkupT()
  { _init_struct(true); }
  public BeChangeReasonLkupT(java.math.BigDecimal beChangeReasonLkupSk, java.math.BigDecimal beChangeReasonLkupParSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String appModName, String changeReasonCode, String changeReasonName, String changeReasonDesc, java.sql.Timestamp changeReasonEffDate, java.sql.Timestamp changeReasonTermDate, java.math.BigDecimal changeReasonMemoRqdInd) throws SQLException
  { _init_struct(true);
    setBeChangeReasonLkupSk(beChangeReasonLkupSk);
    setBeChangeReasonLkupParSk(beChangeReasonLkupParSk);
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
    setAppModName(appModName);
    setChangeReasonCode(changeReasonCode);
    setChangeReasonName(changeReasonName);
    setChangeReasonDesc(changeReasonDesc);
    setChangeReasonEffDate(changeReasonEffDate);
    setChangeReasonTermDate(changeReasonTermDate);
    setChangeReasonMemoRqdInd(changeReasonMemoRqdInd);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(BeChangeReasonLkupT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new BeChangeReasonLkupT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getBeChangeReasonLkupSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setBeChangeReasonLkupSk(java.math.BigDecimal beChangeReasonLkupSk) throws SQLException
  { _struct.setAttribute(0, beChangeReasonLkupSk); }


  public java.math.BigDecimal getBeChangeReasonLkupParSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(1); }

  public void setBeChangeReasonLkupParSk(java.math.BigDecimal beChangeReasonLkupParSk) throws SQLException
  { _struct.setAttribute(1, beChangeReasonLkupParSk); }


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


  public String getAppModName() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setAppModName(String appModName) throws SQLException
  { _struct.setAttribute(12, appModName); }


  public String getChangeReasonCode() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setChangeReasonCode(String changeReasonCode) throws SQLException
  { _struct.setAttribute(13, changeReasonCode); }


  public String getChangeReasonName() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setChangeReasonName(String changeReasonName) throws SQLException
  { _struct.setAttribute(14, changeReasonName); }


  public String getChangeReasonDesc() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setChangeReasonDesc(String changeReasonDesc) throws SQLException
  { _struct.setAttribute(15, changeReasonDesc); }


  public java.sql.Timestamp getChangeReasonEffDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(16); }

  public void setChangeReasonEffDate(java.sql.Timestamp changeReasonEffDate) throws SQLException
  { _struct.setAttribute(16, changeReasonEffDate); }


  public java.sql.Timestamp getChangeReasonTermDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(17); }

  public void setChangeReasonTermDate(java.sql.Timestamp changeReasonTermDate) throws SQLException
  { _struct.setAttribute(17, changeReasonTermDate); }


  public java.math.BigDecimal getChangeReasonMemoRqdInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(18); }

  public void setChangeReasonMemoRqdInd(java.math.BigDecimal changeReasonMemoRqdInd) throws SQLException
  { _struct.setAttribute(18, changeReasonMemoRqdInd); }

}
