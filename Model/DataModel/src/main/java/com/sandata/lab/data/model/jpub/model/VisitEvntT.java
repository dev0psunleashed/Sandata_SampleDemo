package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class VisitEvntT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.VISIT_EVNT_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,2,91,91,91,91,12,12,12,2,2,12,91,12,12,12,12,12,12,12,2,2,2,2,2,101,101,91,12,2,2,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[33];
  protected static final VisitEvntT _VisitEvntTFactory = new VisitEvntT();

  public static ORADataFactory getORADataFactory()
  { return _VisitEvntTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[33], _sqlType, _factory); }
  public VisitEvntT()
  { _init_struct(true); }
  public VisitEvntT(java.math.BigDecimal visitEvntSk, java.math.BigDecimal visitSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String visitEvntTypCode, java.sql.Timestamp visitEvntDtime, String tzName, String ani, String infoDigits, String dnis, String equipmentId, String staffId, String ptId, java.math.BigDecimal coordLatitude, java.math.BigDecimal coordLongitide, java.math.BigDecimal coordAccuracy, java.math.BigDecimal coordAltitude, java.math.BigDecimal coordAltitudeAccuracy, oracle.sql.BINARY_DOUBLE coordHeading, oracle.sql.BINARY_DOUBLE coordSpeed, java.sql.Timestamp coordTmstp, String imei, java.math.BigDecimal callInInd, java.math.BigDecimal callOutInd, String visitEvntPtCnfmQlfr, String changeReasonCode) throws SQLException
  { _init_struct(true);
    setVisitEvntSk(visitEvntSk);
    setVisitSk(visitSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonMemo(changeReasonMemo);
    setCurrRecInd(currRecInd);
    setChangeVersionId(changeVersionId);
    setVisitEvntTypCode(visitEvntTypCode);
    setVisitEvntDtime(visitEvntDtime);
    setTzName(tzName);
    setAni(ani);
    setInfoDigits(infoDigits);
    setDnis(dnis);
    setEquipmentId(equipmentId);
    setStaffId(staffId);
    setPtId(ptId);
    setCoordLatitude(coordLatitude);
    setCoordLongitide(coordLongitide);
    setCoordAccuracy(coordAccuracy);
    setCoordAltitude(coordAltitude);
    setCoordAltitudeAccuracy(coordAltitudeAccuracy);
    setCoordHeading(coordHeading);
    setCoordSpeed(coordSpeed);
    setCoordTmstp(coordTmstp);
    setImei(imei);
    setCallInInd(callInInd);
    setCallOutInd(callOutInd);
    setVisitEvntPtCnfmQlfr(visitEvntPtCnfmQlfr);
    setChangeReasonCode(changeReasonCode);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(VisitEvntT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new VisitEvntT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getVisitEvntSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setVisitEvntSk(java.math.BigDecimal visitEvntSk) throws SQLException
  { _struct.setAttribute(0, visitEvntSk); }


  public java.math.BigDecimal getVisitSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(1); }

  public void setVisitSk(java.math.BigDecimal visitSk) throws SQLException
  { _struct.setAttribute(1, visitSk); }


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


  public String getVisitEvntTypCode() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setVisitEvntTypCode(String visitEvntTypCode) throws SQLException
  { _struct.setAttribute(11, visitEvntTypCode); }


  public java.sql.Timestamp getVisitEvntDtime() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(12); }

  public void setVisitEvntDtime(java.sql.Timestamp visitEvntDtime) throws SQLException
  { _struct.setAttribute(12, visitEvntDtime); }


  public String getTzName() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setTzName(String tzName) throws SQLException
  { _struct.setAttribute(13, tzName); }


  public String getAni() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setAni(String ani) throws SQLException
  { _struct.setAttribute(14, ani); }


  public String getInfoDigits() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setInfoDigits(String infoDigits) throws SQLException
  { _struct.setAttribute(15, infoDigits); }


  public String getDnis() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setDnis(String dnis) throws SQLException
  { _struct.setAttribute(16, dnis); }


  public String getEquipmentId() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setEquipmentId(String equipmentId) throws SQLException
  { _struct.setAttribute(17, equipmentId); }


  public String getStaffId() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setStaffId(String staffId) throws SQLException
  { _struct.setAttribute(18, staffId); }


  public String getPtId() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setPtId(String ptId) throws SQLException
  { _struct.setAttribute(19, ptId); }


  public java.math.BigDecimal getCoordLatitude() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(20); }

  public void setCoordLatitude(java.math.BigDecimal coordLatitude) throws SQLException
  { _struct.setAttribute(20, coordLatitude); }


  public java.math.BigDecimal getCoordLongitide() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(21); }

  public void setCoordLongitide(java.math.BigDecimal coordLongitide) throws SQLException
  { _struct.setAttribute(21, coordLongitide); }


  public java.math.BigDecimal getCoordAccuracy() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(22); }

  public void setCoordAccuracy(java.math.BigDecimal coordAccuracy) throws SQLException
  { _struct.setAttribute(22, coordAccuracy); }


  public java.math.BigDecimal getCoordAltitude() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(23); }

  public void setCoordAltitude(java.math.BigDecimal coordAltitude) throws SQLException
  { _struct.setAttribute(23, coordAltitude); }


  public java.math.BigDecimal getCoordAltitudeAccuracy() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(24); }

  public void setCoordAltitudeAccuracy(java.math.BigDecimal coordAltitudeAccuracy) throws SQLException
  { _struct.setAttribute(24, coordAltitudeAccuracy); }


  public oracle.sql.BINARY_DOUBLE getCoordHeading() throws SQLException
  { return (oracle.sql.BINARY_DOUBLE) _struct.getOracleAttribute(25); }

  public void setCoordHeading(oracle.sql.BINARY_DOUBLE coordHeading) throws SQLException
  { _struct.setOracleAttribute(25, coordHeading); }


  public oracle.sql.BINARY_DOUBLE getCoordSpeed() throws SQLException
  { return (oracle.sql.BINARY_DOUBLE) _struct.getOracleAttribute(26); }

  public void setCoordSpeed(oracle.sql.BINARY_DOUBLE coordSpeed) throws SQLException
  { _struct.setOracleAttribute(26, coordSpeed); }


  public java.sql.Timestamp getCoordTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(27); }

  public void setCoordTmstp(java.sql.Timestamp coordTmstp) throws SQLException
  { _struct.setAttribute(27, coordTmstp); }


  public String getImei() throws SQLException
  { return (String) _struct.getAttribute(28); }

  public void setImei(String imei) throws SQLException
  { _struct.setAttribute(28, imei); }


  public java.math.BigDecimal getCallInInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(29); }

  public void setCallInInd(java.math.BigDecimal callInInd) throws SQLException
  { _struct.setAttribute(29, callInInd); }


  public java.math.BigDecimal getCallOutInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(30); }

  public void setCallOutInd(java.math.BigDecimal callOutInd) throws SQLException
  { _struct.setAttribute(30, callOutInd); }

  public String getVisitEvntPtCnfmQlfr() throws SQLException
  { return (String) _struct.getAttribute(31); }

  public void setVisitEvntPtCnfmQlfr(String visitEvntPtCnfmQlfr) throws SQLException
  { _struct.setAttribute(31, visitEvntPtCnfmQlfr); }

  public String getChangeReasonCode() throws SQLException
  { return (String) _struct.getAttribute(32); }

  public void setChangeReasonCode(String changeReasonCode) throws SQLException
  { _struct.setAttribute(32, changeReasonCode); }

}
