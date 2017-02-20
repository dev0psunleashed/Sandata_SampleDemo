package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class OrdT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.ORD_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,2,91,91,91,91,12,12,12,2,2,12,12,12,91,91,91,12,12,12,2,2,2,91,91,2,91,91,2,91,91,2,91,91,2,91,91,2,91,91,2,91,91,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[44];
  protected static final OrdT _OrdTFactory = new OrdT();

  public static ORADataFactory getORADataFactory()
  { return _OrdTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[44], _sqlType, _factory); }
  public OrdT()
  { _init_struct(true); }
  public OrdT(java.math.BigDecimal ordSk, java.math.BigDecimal ordParSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String ptId, String payerId, java.sql.Timestamp ordIssuedDate, java.sql.Timestamp ordStartTmstp, java.sql.Timestamp ordEndTmstp, String ordComment, String ordLmtTypName, String ordSvcUnitName, java.math.BigDecimal ordLmt, java.math.BigDecimal ordLmtTotal, java.math.BigDecimal ordLmtDay1, java.sql.Timestamp ordLmtStartTimeDay1, java.sql.Timestamp ordLmtEndTimeDay1, java.math.BigDecimal ordLmtDay2, java.sql.Timestamp ordLmtStartTimeDay2, java.sql.Timestamp ordLmtEndTimeDay2, java.math.BigDecimal ordLmtDay3, java.sql.Timestamp ordLmtStartTimeDay3, java.sql.Timestamp ordLmtEndTimeDay3, java.math.BigDecimal ordLmtDay4, java.sql.Timestamp ordLmtStartTimeDay4, java.sql.Timestamp ordLmtEndTimeDay4, java.math.BigDecimal ordLmtDay5, java.sql.Timestamp ordLmtStartTimeDay5, java.sql.Timestamp ordLmtEndTimeDay5, java.math.BigDecimal ordLmtDay6, java.sql.Timestamp ordLmtStartTimeDay6, java.sql.Timestamp ordLmtEndTimeDay6, java.math.BigDecimal ordLmtDay7, java.sql.Timestamp ordLmtStartTimeDay7, java.sql.Timestamp ordLmtEndTimeDay7, String contrId) throws SQLException
  { _init_struct(true);
    setOrdSk(ordSk);
    setOrdParSk(ordParSk);
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
    setPtId(ptId);
    setPayerId(payerId);
    setOrdIssuedDate(ordIssuedDate);
    setOrdStartTmstp(ordStartTmstp);
    setOrdEndTmstp(ordEndTmstp);
    setOrdComment(ordComment);
    setOrdLmtTypName(ordLmtTypName);
    setOrdSvcUnitName(ordSvcUnitName);
    setOrdLmt(ordLmt);
    setOrdLmtTotal(ordLmtTotal);
    setOrdLmtDay1(ordLmtDay1);
    setOrdLmtStartTimeDay1(ordLmtStartTimeDay1);
    setOrdLmtEndTimeDay1(ordLmtEndTimeDay1);
    setOrdLmtDay2(ordLmtDay2);
    setOrdLmtStartTimeDay2(ordLmtStartTimeDay2);
    setOrdLmtEndTimeDay2(ordLmtEndTimeDay2);
    setOrdLmtDay3(ordLmtDay3);
    setOrdLmtStartTimeDay3(ordLmtStartTimeDay3);
    setOrdLmtEndTimeDay3(ordLmtEndTimeDay3);
    setOrdLmtDay4(ordLmtDay4);
    setOrdLmtStartTimeDay4(ordLmtStartTimeDay4);
    setOrdLmtEndTimeDay4(ordLmtEndTimeDay4);
    setOrdLmtDay5(ordLmtDay5);
    setOrdLmtStartTimeDay5(ordLmtStartTimeDay5);
    setOrdLmtEndTimeDay5(ordLmtEndTimeDay5);
    setOrdLmtDay6(ordLmtDay6);
    setOrdLmtStartTimeDay6(ordLmtStartTimeDay6);
    setOrdLmtEndTimeDay6(ordLmtEndTimeDay6);
    setOrdLmtDay7(ordLmtDay7);
    setOrdLmtStartTimeDay7(ordLmtStartTimeDay7);
    setOrdLmtEndTimeDay7(ordLmtEndTimeDay7);
    setContrId(contrId);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(OrdT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new OrdT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getOrdSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setOrdSk(java.math.BigDecimal ordSk) throws SQLException
  { _struct.setAttribute(0, ordSk); }


  public java.math.BigDecimal getOrdParSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(1); }

  public void setOrdParSk(java.math.BigDecimal ordParSk) throws SQLException
  { _struct.setAttribute(1, ordParSk); }


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


  public String getPtId() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setPtId(String ptId) throws SQLException
  { _struct.setAttribute(12, ptId); }


  public String getPayerId() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setPayerId(String payerId) throws SQLException
  { _struct.setAttribute(13, payerId); }


  public java.sql.Timestamp getOrdIssuedDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(14); }

  public void setOrdIssuedDate(java.sql.Timestamp ordIssuedDate) throws SQLException
  { _struct.setAttribute(14, ordIssuedDate); }


  public java.sql.Timestamp getOrdStartTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(15); }

  public void setOrdStartTmstp(java.sql.Timestamp ordStartTmstp) throws SQLException
  { _struct.setAttribute(15, ordStartTmstp); }


  public java.sql.Timestamp getOrdEndTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(16); }

  public void setOrdEndTmstp(java.sql.Timestamp ordEndTmstp) throws SQLException
  { _struct.setAttribute(16, ordEndTmstp); }


  public String getOrdComment() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setOrdComment(String ordComment) throws SQLException
  { _struct.setAttribute(17, ordComment); }


  public String getOrdLmtTypName() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setOrdLmtTypName(String ordLmtTypName) throws SQLException
  { _struct.setAttribute(18, ordLmtTypName); }


  public String getOrdSvcUnitName() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setOrdSvcUnitName(String ordSvcUnitName) throws SQLException
  { _struct.setAttribute(19, ordSvcUnitName); }


  public java.math.BigDecimal getOrdLmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(20); }

  public void setOrdLmt(java.math.BigDecimal ordLmt) throws SQLException
  { _struct.setAttribute(20, ordLmt); }


  public java.math.BigDecimal getOrdLmtTotal() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(21); }

  public void setOrdLmtTotal(java.math.BigDecimal ordLmtTotal) throws SQLException
  { _struct.setAttribute(21, ordLmtTotal); }


  public java.math.BigDecimal getOrdLmtDay1() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(22); }

  public void setOrdLmtDay1(java.math.BigDecimal ordLmtDay1) throws SQLException
  { _struct.setAttribute(22, ordLmtDay1); }


  public java.sql.Timestamp getOrdLmtStartTimeDay1() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(23); }

  public void setOrdLmtStartTimeDay1(java.sql.Timestamp ordLmtStartTimeDay1) throws SQLException
  { _struct.setAttribute(23, ordLmtStartTimeDay1); }


  public java.sql.Timestamp getOrdLmtEndTimeDay1() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(24); }

  public void setOrdLmtEndTimeDay1(java.sql.Timestamp ordLmtEndTimeDay1) throws SQLException
  { _struct.setAttribute(24, ordLmtEndTimeDay1); }


  public java.math.BigDecimal getOrdLmtDay2() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(25); }

  public void setOrdLmtDay2(java.math.BigDecimal ordLmtDay2) throws SQLException
  { _struct.setAttribute(25, ordLmtDay2); }


  public java.sql.Timestamp getOrdLmtStartTimeDay2() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(26); }

  public void setOrdLmtStartTimeDay2(java.sql.Timestamp ordLmtStartTimeDay2) throws SQLException
  { _struct.setAttribute(26, ordLmtStartTimeDay2); }


  public java.sql.Timestamp getOrdLmtEndTimeDay2() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(27); }

  public void setOrdLmtEndTimeDay2(java.sql.Timestamp ordLmtEndTimeDay2) throws SQLException
  { _struct.setAttribute(27, ordLmtEndTimeDay2); }


  public java.math.BigDecimal getOrdLmtDay3() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(28); }

  public void setOrdLmtDay3(java.math.BigDecimal ordLmtDay3) throws SQLException
  { _struct.setAttribute(28, ordLmtDay3); }


  public java.sql.Timestamp getOrdLmtStartTimeDay3() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(29); }

  public void setOrdLmtStartTimeDay3(java.sql.Timestamp ordLmtStartTimeDay3) throws SQLException
  { _struct.setAttribute(29, ordLmtStartTimeDay3); }


  public java.sql.Timestamp getOrdLmtEndTimeDay3() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(30); }

  public void setOrdLmtEndTimeDay3(java.sql.Timestamp ordLmtEndTimeDay3) throws SQLException
  { _struct.setAttribute(30, ordLmtEndTimeDay3); }


  public java.math.BigDecimal getOrdLmtDay4() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(31); }

  public void setOrdLmtDay4(java.math.BigDecimal ordLmtDay4) throws SQLException
  { _struct.setAttribute(31, ordLmtDay4); }


  public java.sql.Timestamp getOrdLmtStartTimeDay4() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(32); }

  public void setOrdLmtStartTimeDay4(java.sql.Timestamp ordLmtStartTimeDay4) throws SQLException
  { _struct.setAttribute(32, ordLmtStartTimeDay4); }


  public java.sql.Timestamp getOrdLmtEndTimeDay4() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(33); }

  public void setOrdLmtEndTimeDay4(java.sql.Timestamp ordLmtEndTimeDay4) throws SQLException
  { _struct.setAttribute(33, ordLmtEndTimeDay4); }


  public java.math.BigDecimal getOrdLmtDay5() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(34); }

  public void setOrdLmtDay5(java.math.BigDecimal ordLmtDay5) throws SQLException
  { _struct.setAttribute(34, ordLmtDay5); }


  public java.sql.Timestamp getOrdLmtStartTimeDay5() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(35); }

  public void setOrdLmtStartTimeDay5(java.sql.Timestamp ordLmtStartTimeDay5) throws SQLException
  { _struct.setAttribute(35, ordLmtStartTimeDay5); }


  public java.sql.Timestamp getOrdLmtEndTimeDay5() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(36); }

  public void setOrdLmtEndTimeDay5(java.sql.Timestamp ordLmtEndTimeDay5) throws SQLException
  { _struct.setAttribute(36, ordLmtEndTimeDay5); }


  public java.math.BigDecimal getOrdLmtDay6() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(37); }

  public void setOrdLmtDay6(java.math.BigDecimal ordLmtDay6) throws SQLException
  { _struct.setAttribute(37, ordLmtDay6); }


  public java.sql.Timestamp getOrdLmtStartTimeDay6() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(38); }

  public void setOrdLmtStartTimeDay6(java.sql.Timestamp ordLmtStartTimeDay6) throws SQLException
  { _struct.setAttribute(38, ordLmtStartTimeDay6); }


  public java.sql.Timestamp getOrdLmtEndTimeDay6() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(39); }

  public void setOrdLmtEndTimeDay6(java.sql.Timestamp ordLmtEndTimeDay6) throws SQLException
  { _struct.setAttribute(39, ordLmtEndTimeDay6); }


  public java.math.BigDecimal getOrdLmtDay7() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(40); }

  public void setOrdLmtDay7(java.math.BigDecimal ordLmtDay7) throws SQLException
  { _struct.setAttribute(40, ordLmtDay7); }


  public java.sql.Timestamp getOrdLmtStartTimeDay7() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(41); }

  public void setOrdLmtStartTimeDay7(java.sql.Timestamp ordLmtStartTimeDay7) throws SQLException
  { _struct.setAttribute(41, ordLmtStartTimeDay7); }


  public java.sql.Timestamp getOrdLmtEndTimeDay7() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(42); }

  public void setOrdLmtEndTimeDay7(java.sql.Timestamp ordLmtEndTimeDay7) throws SQLException
  { _struct.setAttribute(42, ordLmtEndTimeDay7); }


  public String getContrId() throws SQLException
  { return (String) _struct.getAttribute(43); }

  public void setContrId(String contrId) throws SQLException
  { _struct.setAttribute(43, contrId); }

}
