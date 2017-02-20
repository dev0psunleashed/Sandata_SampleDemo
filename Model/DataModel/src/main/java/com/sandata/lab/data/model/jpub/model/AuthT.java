package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class AuthT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.AUTH_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,2,12,91,91,91,91,12,12,12,2,2,12,12,12,2,91,91,91,12,12,12,2,2,2,91,91,2,91,91,2,91,91,2,91,91,2,91,91,2,91,91,2,91,91,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[46];
  protected static final AuthT _AuthTFactory = new AuthT();

  public static ORADataFactory getORADataFactory()
  { return _AuthTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[46], _sqlType, _factory); }
  public AuthT()
  { _init_struct(true); }
  public AuthT(java.math.BigDecimal authSk, java.math.BigDecimal authParSk, String authId, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String ptId, String payerId, java.math.BigDecimal ordSk, java.sql.Timestamp authIssuedDate, java.sql.Timestamp authStartTmstp, java.sql.Timestamp authEndTmstp, String authComment, String authLmtTypName, String authSvcUnitName, java.math.BigDecimal authLmt, java.math.BigDecimal authLmtTotal, java.math.BigDecimal authLmtDay1, java.sql.Timestamp authLmtStartTimeDay1, java.sql.Timestamp authLmtEndTimeDay1, java.math.BigDecimal authLmtDay2, java.sql.Timestamp authLmtStartTimeDay2, java.sql.Timestamp authLmtEndTimeDay2, java.math.BigDecimal authLmtDay3, java.sql.Timestamp authLmtStartTimeDay3, java.sql.Timestamp authLmtEndTimeDay3, java.math.BigDecimal authLmtDay4, java.sql.Timestamp authLmtStartTimeDay4, java.sql.Timestamp authLmtEndTimeDay4, java.math.BigDecimal authLmtDay5, java.sql.Timestamp authLmtStartTimeDay5, java.sql.Timestamp authLmtEndTimeDay5, java.math.BigDecimal authLmtDay6, java.sql.Timestamp authLmtStartTimeDay6, java.sql.Timestamp authLmtEndTimeDay6, java.math.BigDecimal authLmtDay7, java.sql.Timestamp authLmtStartTimeDay7, java.sql.Timestamp authLmtEndTimeDay7, String contrId) throws SQLException
  { _init_struct(true);
    setAuthSk(authSk);
    setAuthParSk(authParSk);
    setAuthId(authId);
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
    setOrdSk(ordSk);
    setAuthIssuedDate(authIssuedDate);
    setAuthStartTmstp(authStartTmstp);
    setAuthEndTmstp(authEndTmstp);
    setAuthComment(authComment);
    setAuthLmtTypName(authLmtTypName);
    setAuthSvcUnitName(authSvcUnitName);
    setAuthLmt(authLmt);
    setAuthLmtTotal(authLmtTotal);
    setAuthLmtDay1(authLmtDay1);
    setAuthLmtStartTimeDay1(authLmtStartTimeDay1);
    setAuthLmtEndTimeDay1(authLmtEndTimeDay1);
    setAuthLmtDay2(authLmtDay2);
    setAuthLmtStartTimeDay2(authLmtStartTimeDay2);
    setAuthLmtEndTimeDay2(authLmtEndTimeDay2);
    setAuthLmtDay3(authLmtDay3);
    setAuthLmtStartTimeDay3(authLmtStartTimeDay3);
    setAuthLmtEndTimeDay3(authLmtEndTimeDay3);
    setAuthLmtDay4(authLmtDay4);
    setAuthLmtStartTimeDay4(authLmtStartTimeDay4);
    setAuthLmtEndTimeDay4(authLmtEndTimeDay4);
    setAuthLmtDay5(authLmtDay5);
    setAuthLmtStartTimeDay5(authLmtStartTimeDay5);
    setAuthLmtEndTimeDay5(authLmtEndTimeDay5);
    setAuthLmtDay6(authLmtDay6);
    setAuthLmtStartTimeDay6(authLmtStartTimeDay6);
    setAuthLmtEndTimeDay6(authLmtEndTimeDay6);
    setAuthLmtDay7(authLmtDay7);
    setAuthLmtStartTimeDay7(authLmtStartTimeDay7);
    setAuthLmtEndTimeDay7(authLmtEndTimeDay7);
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
  protected ORAData create(AuthT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AuthT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAuthSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAuthSk(java.math.BigDecimal authSk) throws SQLException
  { _struct.setAttribute(0, authSk); }


  public java.math.BigDecimal getAuthParSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(1); }

  public void setAuthParSk(java.math.BigDecimal authParSk) throws SQLException
  { _struct.setAttribute(1, authParSk); }


  public String getAuthId() throws SQLException
  { return (String) _struct.getAttribute(2); }

  public void setAuthId(String authId) throws SQLException
  { _struct.setAttribute(2, authId); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(3); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(3, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(4); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(4, recUpdateTmstp); }


  public java.sql.Timestamp getRecEffTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(5); }

  public void setRecEffTmstp(java.sql.Timestamp recEffTmstp) throws SQLException
  { _struct.setAttribute(5, recEffTmstp); }


  public java.sql.Timestamp getRecTermTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(6); }

  public void setRecTermTmstp(java.sql.Timestamp recTermTmstp) throws SQLException
  { _struct.setAttribute(6, recTermTmstp); }


  public String getRecCreatedBy() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setRecCreatedBy(String recCreatedBy) throws SQLException
  { _struct.setAttribute(7, recCreatedBy); }


  public String getRecUpdatedBy() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setRecUpdatedBy(String recUpdatedBy) throws SQLException
  { _struct.setAttribute(8, recUpdatedBy); }


  public String getChangeReasonMemo() throws SQLException
  { return (String) _struct.getAttribute(9); }

  public void setChangeReasonMemo(String changeReasonMemo) throws SQLException
  { _struct.setAttribute(9, changeReasonMemo); }


  public java.math.BigDecimal getCurrRecInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(10); }

  public void setCurrRecInd(java.math.BigDecimal currRecInd) throws SQLException
  { _struct.setAttribute(10, currRecInd); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(11); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(11, changeVersionId); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(12, beId); }


  public String getPtId() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setPtId(String ptId) throws SQLException
  { _struct.setAttribute(13, ptId); }


  public String getPayerId() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setPayerId(String payerId) throws SQLException
  { _struct.setAttribute(14, payerId); }


  public java.math.BigDecimal getOrdSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(15); }

  public void setOrdSk(java.math.BigDecimal ordSk) throws SQLException
  { _struct.setAttribute(15, ordSk); }


  public java.sql.Timestamp getAuthIssuedDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(16); }

  public void setAuthIssuedDate(java.sql.Timestamp authIssuedDate) throws SQLException
  { _struct.setAttribute(16, authIssuedDate); }


  public java.sql.Timestamp getAuthStartTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(17); }

  public void setAuthStartTmstp(java.sql.Timestamp authStartTmstp) throws SQLException
  { _struct.setAttribute(17, authStartTmstp); }


  public java.sql.Timestamp getAuthEndTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(18); }

  public void setAuthEndTmstp(java.sql.Timestamp authEndTmstp) throws SQLException
  { _struct.setAttribute(18, authEndTmstp); }


  public String getAuthComment() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setAuthComment(String authComment) throws SQLException
  { _struct.setAttribute(19, authComment); }


  public String getAuthLmtTypName() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setAuthLmtTypName(String authLmtTypName) throws SQLException
  { _struct.setAttribute(20, authLmtTypName); }


  public String getAuthSvcUnitName() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setAuthSvcUnitName(String authSvcUnitName) throws SQLException
  { _struct.setAttribute(21, authSvcUnitName); }


  public java.math.BigDecimal getAuthLmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(22); }

  public void setAuthLmt(java.math.BigDecimal authLmt) throws SQLException
  { _struct.setAttribute(22, authLmt); }


  public java.math.BigDecimal getAuthLmtTotal() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(23); }

  public void setAuthLmtTotal(java.math.BigDecimal authLmtTotal) throws SQLException
  { _struct.setAttribute(23, authLmtTotal); }


  public java.math.BigDecimal getAuthLmtDay1() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(24); }

  public void setAuthLmtDay1(java.math.BigDecimal authLmtDay1) throws SQLException
  { _struct.setAttribute(24, authLmtDay1); }


  public java.sql.Timestamp getAuthLmtStartTimeDay1() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(25); }

  public void setAuthLmtStartTimeDay1(java.sql.Timestamp authLmtStartTimeDay1) throws SQLException
  { _struct.setAttribute(25, authLmtStartTimeDay1); }


  public java.sql.Timestamp getAuthLmtEndTimeDay1() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(26); }

  public void setAuthLmtEndTimeDay1(java.sql.Timestamp authLmtEndTimeDay1) throws SQLException
  { _struct.setAttribute(26, authLmtEndTimeDay1); }


  public java.math.BigDecimal getAuthLmtDay2() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(27); }

  public void setAuthLmtDay2(java.math.BigDecimal authLmtDay2) throws SQLException
  { _struct.setAttribute(27, authLmtDay2); }


  public java.sql.Timestamp getAuthLmtStartTimeDay2() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(28); }

  public void setAuthLmtStartTimeDay2(java.sql.Timestamp authLmtStartTimeDay2) throws SQLException
  { _struct.setAttribute(28, authLmtStartTimeDay2); }


  public java.sql.Timestamp getAuthLmtEndTimeDay2() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(29); }

  public void setAuthLmtEndTimeDay2(java.sql.Timestamp authLmtEndTimeDay2) throws SQLException
  { _struct.setAttribute(29, authLmtEndTimeDay2); }


  public java.math.BigDecimal getAuthLmtDay3() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(30); }

  public void setAuthLmtDay3(java.math.BigDecimal authLmtDay3) throws SQLException
  { _struct.setAttribute(30, authLmtDay3); }


  public java.sql.Timestamp getAuthLmtStartTimeDay3() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(31); }

  public void setAuthLmtStartTimeDay3(java.sql.Timestamp authLmtStartTimeDay3) throws SQLException
  { _struct.setAttribute(31, authLmtStartTimeDay3); }


  public java.sql.Timestamp getAuthLmtEndTimeDay3() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(32); }

  public void setAuthLmtEndTimeDay3(java.sql.Timestamp authLmtEndTimeDay3) throws SQLException
  { _struct.setAttribute(32, authLmtEndTimeDay3); }


  public java.math.BigDecimal getAuthLmtDay4() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(33); }

  public void setAuthLmtDay4(java.math.BigDecimal authLmtDay4) throws SQLException
  { _struct.setAttribute(33, authLmtDay4); }


  public java.sql.Timestamp getAuthLmtStartTimeDay4() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(34); }

  public void setAuthLmtStartTimeDay4(java.sql.Timestamp authLmtStartTimeDay4) throws SQLException
  { _struct.setAttribute(34, authLmtStartTimeDay4); }


  public java.sql.Timestamp getAuthLmtEndTimeDay4() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(35); }

  public void setAuthLmtEndTimeDay4(java.sql.Timestamp authLmtEndTimeDay4) throws SQLException
  { _struct.setAttribute(35, authLmtEndTimeDay4); }


  public java.math.BigDecimal getAuthLmtDay5() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(36); }

  public void setAuthLmtDay5(java.math.BigDecimal authLmtDay5) throws SQLException
  { _struct.setAttribute(36, authLmtDay5); }


  public java.sql.Timestamp getAuthLmtStartTimeDay5() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(37); }

  public void setAuthLmtStartTimeDay5(java.sql.Timestamp authLmtStartTimeDay5) throws SQLException
  { _struct.setAttribute(37, authLmtStartTimeDay5); }


  public java.sql.Timestamp getAuthLmtEndTimeDay5() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(38); }

  public void setAuthLmtEndTimeDay5(java.sql.Timestamp authLmtEndTimeDay5) throws SQLException
  { _struct.setAttribute(38, authLmtEndTimeDay5); }


  public java.math.BigDecimal getAuthLmtDay6() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(39); }

  public void setAuthLmtDay6(java.math.BigDecimal authLmtDay6) throws SQLException
  { _struct.setAttribute(39, authLmtDay6); }


  public java.sql.Timestamp getAuthLmtStartTimeDay6() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(40); }

  public void setAuthLmtStartTimeDay6(java.sql.Timestamp authLmtStartTimeDay6) throws SQLException
  { _struct.setAttribute(40, authLmtStartTimeDay6); }


  public java.sql.Timestamp getAuthLmtEndTimeDay6() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(41); }

  public void setAuthLmtEndTimeDay6(java.sql.Timestamp authLmtEndTimeDay6) throws SQLException
  { _struct.setAttribute(41, authLmtEndTimeDay6); }


  public java.math.BigDecimal getAuthLmtDay7() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(42); }

  public void setAuthLmtDay7(java.math.BigDecimal authLmtDay7) throws SQLException
  { _struct.setAttribute(42, authLmtDay7); }


  public java.sql.Timestamp getAuthLmtStartTimeDay7() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(43); }

  public void setAuthLmtStartTimeDay7(java.sql.Timestamp authLmtStartTimeDay7) throws SQLException
  { _struct.setAttribute(43, authLmtStartTimeDay7); }


  public java.sql.Timestamp getAuthLmtEndTimeDay7() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(44); }

  public void setAuthLmtEndTimeDay7(java.sql.Timestamp authLmtEndTimeDay7) throws SQLException
  { _struct.setAttribute(44, authLmtEndTimeDay7); }


  public String getContrId() throws SQLException
  { return (String) _struct.getAttribute(45); }

  public void setContrId(String contrId) throws SQLException
  { _struct.setAttribute(45, contrId); }

}
