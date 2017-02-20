package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class VisitT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.VISIT_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,12,12,12,12,2,12,12,12,2,91,91,91,91,2,12,2,2,2,2,2,2,2,2,12,12,12,12,12,12,2,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[34];
  protected static final VisitT _VisitTFactory = new VisitT();

  public static ORADataFactory getORADataFactory()
  { return _VisitTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[34], _sqlType, _factory); }
  public VisitT()
  { _init_struct(true); }
  public VisitT(java.math.BigDecimal visitSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonCode, String changeReasonMemo, java.math.BigDecimal changeVersionId, String beId, String ptId, String staffId, java.math.BigDecimal schedEvntSk, java.sql.Timestamp visitActStartTmstp, java.sql.Timestamp visitActEndTmstp, java.sql.Timestamp visitAdjStartTmstp, java.sql.Timestamp visitAdjEndTmstp, java.math.BigDecimal visitCancelledInd, String visitCancellationReason, java.math.BigDecimal visitAprvdInd, java.math.BigDecimal visitVfydBySchedInd, java.math.BigDecimal visitPayBySchedInd, java.math.BigDecimal visitDoNotBillInd, java.math.BigDecimal visitDoNotPayInd, java.math.BigDecimal visitPayHrs, java.math.BigDecimal visitBillHrs, java.math.BigDecimal visitOtAbsHrs, String userName, String userGuid, String resolutionCode, String memo, String visitStatusName, String visitOtherId, java.math.BigDecimal visitPtSigInd, String visitCnfrmQlfr) throws SQLException
  { _init_struct(true);
    setVisitSk(visitSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonCode(changeReasonCode);
    setChangeReasonMemo(changeReasonMemo);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setPtId(ptId);
    setStaffId(staffId);
    setSchedEvntSk(schedEvntSk);
    setVisitActStartTmstp(visitActStartTmstp);
    setVisitActEndTmstp(visitActEndTmstp);
    setVisitAdjStartTmstp(visitAdjStartTmstp);
    setVisitAdjEndTmstp(visitAdjEndTmstp);
    setVisitCancelledInd(visitCancelledInd);
    setVisitCancellationReason(visitCancellationReason);
    setVisitAprvdInd(visitAprvdInd);
    setVisitVfydBySchedInd(visitVfydBySchedInd);
    setVisitPayBySchedInd(visitPayBySchedInd);
    setVisitDoNotBillInd(visitDoNotBillInd);
    setVisitDoNotPayInd(visitDoNotPayInd);
    setVisitPayHrs(visitPayHrs);
    setVisitBillHrs(visitBillHrs);
    setVisitOtAbsHrs(visitOtAbsHrs);
    setUserName(userName);
    setUserGuid(userGuid);
    setResolutionCode(resolutionCode);
    setMemo(memo);
    setVisitStatusName(visitStatusName);
    setVisitOtherId(visitOtherId);
    setVisitPtSigInd(visitPtSigInd);
    setVisitCnfrmQlfr(visitCnfrmQlfr);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(VisitT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new VisitT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getVisitSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setVisitSk(java.math.BigDecimal visitSk) throws SQLException
  { _struct.setAttribute(0, visitSk); }


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


  public String getPtId() throws SQLException
  { return (String) _struct.getAttribute(9); }

  public void setPtId(String ptId) throws SQLException
  { _struct.setAttribute(9, ptId); }


  public String getStaffId() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setStaffId(String staffId) throws SQLException
  { _struct.setAttribute(10, staffId); }


  public java.math.BigDecimal getSchedEvntSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(11); }

  public void setSchedEvntSk(java.math.BigDecimal schedEvntSk) throws SQLException
  { _struct.setAttribute(11, schedEvntSk); }


  public java.sql.Timestamp getVisitActStartTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(12); }

  public void setVisitActStartTmstp(java.sql.Timestamp visitActStartTmstp) throws SQLException
  { _struct.setAttribute(12, visitActStartTmstp); }


  public java.sql.Timestamp getVisitActEndTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(13); }

  public void setVisitActEndTmstp(java.sql.Timestamp visitActEndTmstp) throws SQLException
  { _struct.setAttribute(13, visitActEndTmstp); }


  public java.sql.Timestamp getVisitAdjStartTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(14); }

  public void setVisitAdjStartTmstp(java.sql.Timestamp visitAdjStartTmstp) throws SQLException
  { _struct.setAttribute(14, visitAdjStartTmstp); }


  public java.sql.Timestamp getVisitAdjEndTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(15); }

  public void setVisitAdjEndTmstp(java.sql.Timestamp visitAdjEndTmstp) throws SQLException
  { _struct.setAttribute(15, visitAdjEndTmstp); }


  public java.math.BigDecimal getVisitCancelledInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(16); }

  public void setVisitCancelledInd(java.math.BigDecimal visitCancelledInd) throws SQLException
  { _struct.setAttribute(16, visitCancelledInd); }


  public String getVisitCancellationReason() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setVisitCancellationReason(String visitCancellationReason) throws SQLException
  { _struct.setAttribute(17, visitCancellationReason); }


  public java.math.BigDecimal getVisitAprvdInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(18); }

  public void setVisitAprvdInd(java.math.BigDecimal visitAprvdInd) throws SQLException
  { _struct.setAttribute(18, visitAprvdInd); }


  public java.math.BigDecimal getVisitVfydBySchedInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(19); }

  public void setVisitVfydBySchedInd(java.math.BigDecimal visitVfydBySchedInd) throws SQLException
  { _struct.setAttribute(19, visitVfydBySchedInd); }


  public java.math.BigDecimal getVisitPayBySchedInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(20); }

  public void setVisitPayBySchedInd(java.math.BigDecimal visitPayBySchedInd) throws SQLException
  { _struct.setAttribute(20, visitPayBySchedInd); }


  public java.math.BigDecimal getVisitDoNotBillInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(21); }

  public void setVisitDoNotBillInd(java.math.BigDecimal visitDoNotBillInd) throws SQLException
  { _struct.setAttribute(21, visitDoNotBillInd); }


  public java.math.BigDecimal getVisitDoNotPayInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(22); }

  public void setVisitDoNotPayInd(java.math.BigDecimal visitDoNotPayInd) throws SQLException
  { _struct.setAttribute(22, visitDoNotPayInd); }


  public java.math.BigDecimal getVisitPayHrs() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(23); }

  public void setVisitPayHrs(java.math.BigDecimal visitPayHrs) throws SQLException
  { _struct.setAttribute(23, visitPayHrs); }


  public java.math.BigDecimal getVisitBillHrs() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(24); }

  public void setVisitBillHrs(java.math.BigDecimal visitBillHrs) throws SQLException
  { _struct.setAttribute(24, visitBillHrs); }


  public java.math.BigDecimal getVisitOtAbsHrs() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(25); }

  public void setVisitOtAbsHrs(java.math.BigDecimal visitOtAbsHrs) throws SQLException
  { _struct.setAttribute(25, visitOtAbsHrs); }


  public String getUserName() throws SQLException
  { return (String) _struct.getAttribute(26); }

  public void setUserName(String userName) throws SQLException
  { _struct.setAttribute(26, userName); }


  public String getUserGuid() throws SQLException
  { return (String) _struct.getAttribute(27); }

  public void setUserGuid(String userGuid) throws SQLException
  { _struct.setAttribute(27, userGuid); }


  public String getResolutionCode() throws SQLException
  { return (String) _struct.getAttribute(28); }

  public void setResolutionCode(String resolutionCode) throws SQLException
  { _struct.setAttribute(28, resolutionCode); }


  public String getMemo() throws SQLException
  { return (String) _struct.getAttribute(29); }

  public void setMemo(String memo) throws SQLException
  { _struct.setAttribute(29, memo); }

  public String getVisitStatusName() throws SQLException
  { return (String) _struct.getAttribute(30); }

  public void setVisitStatusName(String visitStatusName) throws SQLException
  { _struct.setAttribute(30, visitStatusName); }

  public String getVisitOtherId() throws SQLException
  { return (String) _struct.getAttribute(31); }

  public void setVisitOtherId(String visitOtherId) throws SQLException
  { _struct.setAttribute(31, visitOtherId); }


  public java.math.BigDecimal getVisitPtSigInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(32); }

  public void setVisitPtSigInd(java.math.BigDecimal visitPtSigInd) throws SQLException
  { _struct.setAttribute(32, visitPtSigInd); }


  public String getVisitCnfrmQlfr() throws SQLException
  { return (String) _struct.getAttribute(33); }

  public void setVisitCnfrmQlfr(String visitCnfrmQlfr) throws SQLException
  { _struct.setAttribute(33, visitCnfrmQlfr); }
}
