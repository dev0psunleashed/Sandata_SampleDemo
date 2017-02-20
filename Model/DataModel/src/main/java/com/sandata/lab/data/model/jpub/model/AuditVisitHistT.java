package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class AuditVisitHistT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.AUDIT_VISIT_HIST_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[52];
  protected static final AuditVisitHistT _AuditVisitHistTFactory = new AuditVisitHistT();

  public static ORADataFactory getORADataFactory()
  { return _AuditVisitHistTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[52], _sqlType, _factory); }
  public AuditVisitHistT()
  { _init_struct(true); }
  public AuditVisitHistT(String auditHost, String userGuid, String reasonCode, String notes, String ptIdOld, String ptIdNew, String ptNameOld, String ptNameNew, String staffIdOld, String staffIdNew, String staffNameOld, String staffNameNew, String serviceOld, String serviceNew, String schdInOld, String schdInNew, String schdOutOld, String schdOutNew, String schdHrsOld, String schdHrsNew, String callInOld, String callInNew, String callOutOld, String callOutNew, String callHrsOld, String callHrsNew, String adjInOld, String adjInNew, String adjOutOld, String adjOutNew, String adjHrsOld, String adjHrsNew, String payHrsOld, String payHrsNew, String billHrsOld, String billHrsNew, String statusOld, String statusNew, String verifyBySchdOld, String verifyBySchdNew, String payBySchdOld, String payBySchdNew, String otAbsHoursOld, String otAbsHoursNew, String apprvOld, String apprvNew, String visitExceptionsOld, String visitExceptionsNew, String visitNotesOld, String visitNotesNew, String visitTasksOld, String visitTasksNew) throws SQLException
  { _init_struct(true);
    setAuditHost(auditHost);
    setUserGuid(userGuid);
    setReasonCode(reasonCode);
    setNotes(notes);
    setPtIdOld(ptIdOld);
    setPtIdNew(ptIdNew);
    setPtNameOld(ptNameOld);
    setPtNameNew(ptNameNew);
    setStaffIdOld(staffIdOld);
    setStaffIdNew(staffIdNew);
    setStaffNameOld(staffNameOld);
    setStaffNameNew(staffNameNew);
    setServiceOld(serviceOld);
    setServiceNew(serviceNew);
    setSchdInOld(schdInOld);
    setSchdInNew(schdInNew);
    setSchdOutOld(schdOutOld);
    setSchdOutNew(schdOutNew);
    setSchdHrsOld(schdHrsOld);
    setSchdHrsNew(schdHrsNew);
    setCallInOld(callInOld);
    setCallInNew(callInNew);
    setCallOutOld(callOutOld);
    setCallOutNew(callOutNew);
    setCallHrsOld(callHrsOld);
    setCallHrsNew(callHrsNew);
    setAdjInOld(adjInOld);
    setAdjInNew(adjInNew);
    setAdjOutOld(adjOutOld);
    setAdjOutNew(adjOutNew);
    setAdjHrsOld(adjHrsOld);
    setAdjHrsNew(adjHrsNew);
    setPayHrsOld(payHrsOld);
    setPayHrsNew(payHrsNew);
    setBillHrsOld(billHrsOld);
    setBillHrsNew(billHrsNew);
    setStatusOld(statusOld);
    setStatusNew(statusNew);
    setVerifyBySchdOld(verifyBySchdOld);
    setVerifyBySchdNew(verifyBySchdNew);
    setPayBySchdOld(payBySchdOld);
    setPayBySchdNew(payBySchdNew);
    setOtAbsHoursOld(otAbsHoursOld);
    setOtAbsHoursNew(otAbsHoursNew);
    setApprvOld(apprvOld);
    setApprvNew(apprvNew);
    setVisitExceptionsOld(visitExceptionsOld);
    setVisitExceptionsNew(visitExceptionsNew);
    setVisitNotesOld(visitNotesOld);
    setVisitNotesNew(visitNotesNew);
    setVisitTasksOld(visitTasksOld);
    setVisitTasksNew(visitTasksNew);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AuditVisitHistT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AuditVisitHistT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public String getAuditHost() throws SQLException
  { return (String) _struct.getAttribute(0); }

  public void setAuditHost(String auditHost) throws SQLException
  { _struct.setAttribute(0, auditHost); }


  public String getUserGuid() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setUserGuid(String userGuid) throws SQLException
  { _struct.setAttribute(1, userGuid); }


  public String getReasonCode() throws SQLException
  { return (String) _struct.getAttribute(2); }

  public void setReasonCode(String reasonCode) throws SQLException
  { _struct.setAttribute(2, reasonCode); }


  public String getNotes() throws SQLException
  { return (String) _struct.getAttribute(3); }

  public void setNotes(String notes) throws SQLException
  { _struct.setAttribute(3, notes); }


  public String getPtIdOld() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setPtIdOld(String ptIdOld) throws SQLException
  { _struct.setAttribute(4, ptIdOld); }


  public String getPtIdNew() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setPtIdNew(String ptIdNew) throws SQLException
  { _struct.setAttribute(5, ptIdNew); }


  public String getPtNameOld() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setPtNameOld(String ptNameOld) throws SQLException
  { _struct.setAttribute(6, ptNameOld); }


  public String getPtNameNew() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setPtNameNew(String ptNameNew) throws SQLException
  { _struct.setAttribute(7, ptNameNew); }


  public String getStaffIdOld() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setStaffIdOld(String staffIdOld) throws SQLException
  { _struct.setAttribute(8, staffIdOld); }


  public String getStaffIdNew() throws SQLException
  { return (String) _struct.getAttribute(9); }

  public void setStaffIdNew(String staffIdNew) throws SQLException
  { _struct.setAttribute(9, staffIdNew); }


  public String getStaffNameOld() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setStaffNameOld(String staffNameOld) throws SQLException
  { _struct.setAttribute(10, staffNameOld); }


  public String getStaffNameNew() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setStaffNameNew(String staffNameNew) throws SQLException
  { _struct.setAttribute(11, staffNameNew); }


  public String getServiceOld() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setServiceOld(String serviceOld) throws SQLException
  { _struct.setAttribute(12, serviceOld); }


  public String getServiceNew() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setServiceNew(String serviceNew) throws SQLException
  { _struct.setAttribute(13, serviceNew); }


  public String getSchdInOld() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setSchdInOld(String schdInOld) throws SQLException
  { _struct.setAttribute(14, schdInOld); }


  public String getSchdInNew() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setSchdInNew(String schdInNew) throws SQLException
  { _struct.setAttribute(15, schdInNew); }


  public String getSchdOutOld() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setSchdOutOld(String schdOutOld) throws SQLException
  { _struct.setAttribute(16, schdOutOld); }


  public String getSchdOutNew() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setSchdOutNew(String schdOutNew) throws SQLException
  { _struct.setAttribute(17, schdOutNew); }


  public String getSchdHrsOld() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setSchdHrsOld(String schdHrsOld) throws SQLException
  { _struct.setAttribute(18, schdHrsOld); }


  public String getSchdHrsNew() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setSchdHrsNew(String schdHrsNew) throws SQLException
  { _struct.setAttribute(19, schdHrsNew); }


  public String getCallInOld() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setCallInOld(String callInOld) throws SQLException
  { _struct.setAttribute(20, callInOld); }


  public String getCallInNew() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setCallInNew(String callInNew) throws SQLException
  { _struct.setAttribute(21, callInNew); }


  public String getCallOutOld() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setCallOutOld(String callOutOld) throws SQLException
  { _struct.setAttribute(22, callOutOld); }


  public String getCallOutNew() throws SQLException
  { return (String) _struct.getAttribute(23); }

  public void setCallOutNew(String callOutNew) throws SQLException
  { _struct.setAttribute(23, callOutNew); }


  public String getCallHrsOld() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setCallHrsOld(String callHrsOld) throws SQLException
  { _struct.setAttribute(24, callHrsOld); }


  public String getCallHrsNew() throws SQLException
  { return (String) _struct.getAttribute(25); }

  public void setCallHrsNew(String callHrsNew) throws SQLException
  { _struct.setAttribute(25, callHrsNew); }


  public String getAdjInOld() throws SQLException
  { return (String) _struct.getAttribute(26); }

  public void setAdjInOld(String adjInOld) throws SQLException
  { _struct.setAttribute(26, adjInOld); }


  public String getAdjInNew() throws SQLException
  { return (String) _struct.getAttribute(27); }

  public void setAdjInNew(String adjInNew) throws SQLException
  { _struct.setAttribute(27, adjInNew); }


  public String getAdjOutOld() throws SQLException
  { return (String) _struct.getAttribute(28); }

  public void setAdjOutOld(String adjOutOld) throws SQLException
  { _struct.setAttribute(28, adjOutOld); }


  public String getAdjOutNew() throws SQLException
  { return (String) _struct.getAttribute(29); }

  public void setAdjOutNew(String adjOutNew) throws SQLException
  { _struct.setAttribute(29, adjOutNew); }


  public String getAdjHrsOld() throws SQLException
  { return (String) _struct.getAttribute(30); }

  public void setAdjHrsOld(String adjHrsOld) throws SQLException
  { _struct.setAttribute(30, adjHrsOld); }


  public String getAdjHrsNew() throws SQLException
  { return (String) _struct.getAttribute(31); }

  public void setAdjHrsNew(String adjHrsNew) throws SQLException
  { _struct.setAttribute(31, adjHrsNew); }


  public String getPayHrsOld() throws SQLException
  { return (String) _struct.getAttribute(32); }

  public void setPayHrsOld(String payHrsOld) throws SQLException
  { _struct.setAttribute(32, payHrsOld); }


  public String getPayHrsNew() throws SQLException
  { return (String) _struct.getAttribute(33); }

  public void setPayHrsNew(String payHrsNew) throws SQLException
  { _struct.setAttribute(33, payHrsNew); }


  public String getBillHrsOld() throws SQLException
  { return (String) _struct.getAttribute(34); }

  public void setBillHrsOld(String billHrsOld) throws SQLException
  { _struct.setAttribute(34, billHrsOld); }


  public String getBillHrsNew() throws SQLException
  { return (String) _struct.getAttribute(35); }

  public void setBillHrsNew(String billHrsNew) throws SQLException
  { _struct.setAttribute(35, billHrsNew); }


  public String getStatusOld() throws SQLException
  { return (String) _struct.getAttribute(36); }

  public void setStatusOld(String statusOld) throws SQLException
  { _struct.setAttribute(36, statusOld); }


  public String getStatusNew() throws SQLException
  { return (String) _struct.getAttribute(37); }

  public void setStatusNew(String statusNew) throws SQLException
  { _struct.setAttribute(37, statusNew); }


  public String getVerifyBySchdOld() throws SQLException
  { return (String) _struct.getAttribute(38); }

  public void setVerifyBySchdOld(String verifyBySchdOld) throws SQLException
  { _struct.setAttribute(38, verifyBySchdOld); }


  public String getVerifyBySchdNew() throws SQLException
  { return (String) _struct.getAttribute(39); }

  public void setVerifyBySchdNew(String verifyBySchdNew) throws SQLException
  { _struct.setAttribute(39, verifyBySchdNew); }


  public String getPayBySchdOld() throws SQLException
  { return (String) _struct.getAttribute(40); }

  public void setPayBySchdOld(String payBySchdOld) throws SQLException
  { _struct.setAttribute(40, payBySchdOld); }


  public String getPayBySchdNew() throws SQLException
  { return (String) _struct.getAttribute(41); }

  public void setPayBySchdNew(String payBySchdNew) throws SQLException
  { _struct.setAttribute(41, payBySchdNew); }


  public String getOtAbsHoursOld() throws SQLException
  { return (String) _struct.getAttribute(42); }

  public void setOtAbsHoursOld(String otAbsHoursOld) throws SQLException
  { _struct.setAttribute(42, otAbsHoursOld); }


  public String getOtAbsHoursNew() throws SQLException
  { return (String) _struct.getAttribute(43); }

  public void setOtAbsHoursNew(String otAbsHoursNew) throws SQLException
  { _struct.setAttribute(43, otAbsHoursNew); }


  public String getApprvOld() throws SQLException
  { return (String) _struct.getAttribute(44); }

  public void setApprvOld(String apprvOld) throws SQLException
  { _struct.setAttribute(44, apprvOld); }


  public String getApprvNew() throws SQLException
  { return (String) _struct.getAttribute(45); }

  public void setApprvNew(String apprvNew) throws SQLException
  { _struct.setAttribute(45, apprvNew); }


  public String getVisitExceptionsOld() throws SQLException
  { return (String) _struct.getAttribute(46); }

  public void setVisitExceptionsOld(String visitExceptionsOld) throws SQLException
  { _struct.setAttribute(46, visitExceptionsOld); }


  public String getVisitExceptionsNew() throws SQLException
  { return (String) _struct.getAttribute(47); }

  public void setVisitExceptionsNew(String visitExceptionsNew) throws SQLException
  { _struct.setAttribute(47, visitExceptionsNew); }


  public String getVisitNotesOld() throws SQLException
  { return (String) _struct.getAttribute(48); }

  public void setVisitNotesOld(String visitNotesOld) throws SQLException
  { _struct.setAttribute(48, visitNotesOld); }


  public String getVisitNotesNew() throws SQLException
  { return (String) _struct.getAttribute(49); }

  public void setVisitNotesNew(String visitNotesNew) throws SQLException
  { _struct.setAttribute(49, visitNotesNew); }


  public String getVisitTasksOld() throws SQLException
  { return (String) _struct.getAttribute(50); }

  public void setVisitTasksOld(String visitTasksOld) throws SQLException
  { _struct.setAttribute(50, visitTasksOld); }


  public String getVisitTasksNew() throws SQLException
  { return (String) _struct.getAttribute(51); }

  public void setVisitTasksNew(String visitTasksNew) throws SQLException
  { _struct.setAttribute(51, visitTasksNew); }

}
