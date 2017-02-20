package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class StaffT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.STAFF_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,91,91,91,91,12,12,12,12,2,2,12,12,12,91,12,12,12,12,12,12,12,2,12,12,12,12,12,12,12,12,12,91,91,91,12,91,12,12,12,12,12,2,2,2,12,12,12,12,12,12,12,12,91 };
  protected static ORADataFactory[] _factory = new ORADataFactory[55];
  protected static final StaffT _StaffTFactory = new StaffT();

  public static ORADataFactory getORADataFactory()
  { return _StaffTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[55], _sqlType, _factory); }
  public StaffT()
  { _init_struct(true); }
  public StaffT(java.math.BigDecimal staffSk, String staffId, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonCode, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String tzName, String staffEmpltStatusTypName, java.sql.Timestamp staffEmpltStatusChangeDate, String staffTinQlfr, String staffTin, String staffVvId, String staffVvIdQlfr, String staffNpi, String staffApi, String staffEmpltClsName, java.math.BigDecimal staffUnionMemberInd, String staffFirstName, String staffMiddleName, String staffMaidenName, String staffLastName, String staffNickname, String staffEmail, String staffEmailOther, String staffLoc, String staffPrefdContMthd, java.sql.Timestamp staffTermDate, java.sql.Timestamp staffHireDate, java.sql.Timestamp staffLastRaiseDate, String staffMrtlStatusName, java.sql.Timestamp staffDob, String staffGenderTypName, String staffRaceEthnicityCode, String staffEducation, String staffReligionCode, String staffTransportMthd, java.math.BigDecimal staffTravelRadius, java.math.BigDecimal staffOnOkInd, java.math.BigDecimal staffLiOkInd, String staffTeam, String staffMilitaryRec, String staffRfrlTyp, String staffRfrlName, String staffRfrlInstitution, String staffRfrlCmnt, String staffPositionName, String staffRfrlTypName, java.sql.Timestamp staffLastHireDate) throws SQLException
  { _init_struct(true);
    setStaffSk(staffSk);
    setStaffId(staffId);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonCode(changeReasonCode);
    setChangeReasonMemo(changeReasonMemo);
    setCurrRecInd(currRecInd);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setTzName(tzName);
    setStaffEmpltStatusTypName(staffEmpltStatusTypName);
    setStaffEmpltStatusChangeDate(staffEmpltStatusChangeDate);
    setStaffTinQlfr(staffTinQlfr);
    setStaffTin(staffTin);
    setStaffVvId(staffVvId);
    setStaffVvIdQlfr(staffVvIdQlfr);
    setStaffNpi(staffNpi);
    setStaffApi(staffApi);
    setStaffEmpltClsName(staffEmpltClsName);
    setStaffUnionMemberInd(staffUnionMemberInd);
    setStaffFirstName(staffFirstName);
    setStaffMiddleName(staffMiddleName);
    setStaffMaidenName(staffMaidenName);
    setStaffLastName(staffLastName);
    setStaffNickname(staffNickname);
    setStaffEmail(staffEmail);
    setStaffEmailOther(staffEmailOther);
    setStaffLoc(staffLoc);
    setStaffPrefdContMthd(staffPrefdContMthd);
    setStaffTermDate(staffTermDate);
    setStaffHireDate(staffHireDate);
    setStaffLastRaiseDate(staffLastRaiseDate);
    setStaffMrtlStatusName(staffMrtlStatusName);
    setStaffDob(staffDob);
    setStaffGenderTypName(staffGenderTypName);
    setStaffRaceEthnicityCode(staffRaceEthnicityCode);
    setStaffEducation(staffEducation);
    setStaffReligionCode(staffReligionCode);
    setStaffTransportMthd(staffTransportMthd);
    setStaffTravelRadius(staffTravelRadius);
    setStaffOnOkInd(staffOnOkInd);
    setStaffLiOkInd(staffLiOkInd);
    setStaffTeam(staffTeam);
    setStaffMilitaryRec(staffMilitaryRec);
    setStaffRfrlTyp(staffRfrlTyp);
    setStaffRfrlName(staffRfrlName);
    setStaffRfrlInstitution(staffRfrlInstitution);
    setStaffRfrlCmnt(staffRfrlCmnt);
    setStaffPositionName(staffPositionName);
    setStaffRfrlTypName(staffRfrlTypName);
    setStaffLastHireDate(staffLastHireDate);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(StaffT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new StaffT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getStaffSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setStaffSk(java.math.BigDecimal staffSk) throws SQLException
  { _struct.setAttribute(0, staffSk); }


  public String getStaffId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setStaffId(String staffId) throws SQLException
  { _struct.setAttribute(1, staffId); }


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


  public String getChangeReasonCode() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setChangeReasonCode(String changeReasonCode) throws SQLException
  { _struct.setAttribute(8, changeReasonCode); }


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


  public String getTzName() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setTzName(String tzName) throws SQLException
  { _struct.setAttribute(13, tzName); }


  public String getStaffEmpltStatusTypName() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setStaffEmpltStatusTypName(String staffEmpltStatusTypName) throws SQLException
  { _struct.setAttribute(14, staffEmpltStatusTypName); }


  public java.sql.Timestamp getStaffEmpltStatusChangeDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(15); }

  public void setStaffEmpltStatusChangeDate(java.sql.Timestamp staffEmpltStatusChangeDate) throws SQLException
  { _struct.setAttribute(15, staffEmpltStatusChangeDate); }


  public String getStaffTinQlfr() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setStaffTinQlfr(String staffTinQlfr) throws SQLException
  { _struct.setAttribute(16, staffTinQlfr); }


  public String getStaffTin() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setStaffTin(String staffTin) throws SQLException
  { _struct.setAttribute(17, staffTin); }


  public String getStaffVvId() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setStaffVvId(String staffVvId) throws SQLException
  { _struct.setAttribute(18, staffVvId); }


  public String getStaffVvIdQlfr() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setStaffVvIdQlfr(String staffVvIdQlfr) throws SQLException
  { _struct.setAttribute(19, staffVvIdQlfr); }


  public String getStaffNpi() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setStaffNpi(String staffNpi) throws SQLException
  { _struct.setAttribute(20, staffNpi); }


  public String getStaffApi() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setStaffApi(String staffApi) throws SQLException
  { _struct.setAttribute(21, staffApi); }


  public String getStaffEmpltClsName() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setStaffEmpltClsName(String staffEmpltClsName) throws SQLException
  { _struct.setAttribute(22, staffEmpltClsName); }


  public java.math.BigDecimal getStaffUnionMemberInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(23); }

  public void setStaffUnionMemberInd(java.math.BigDecimal staffUnionMemberInd) throws SQLException
  { _struct.setAttribute(23, staffUnionMemberInd); }


  public String getStaffFirstName() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setStaffFirstName(String staffFirstName) throws SQLException
  { _struct.setAttribute(24, staffFirstName); }


  public String getStaffMiddleName() throws SQLException
  { return (String) _struct.getAttribute(25); }

  public void setStaffMiddleName(String staffMiddleName) throws SQLException
  { _struct.setAttribute(25, staffMiddleName); }


  public String getStaffMaidenName() throws SQLException
  { return (String) _struct.getAttribute(26); }

  public void setStaffMaidenName(String staffMaidenName) throws SQLException
  { _struct.setAttribute(26, staffMaidenName); }


  public String getStaffLastName() throws SQLException
  { return (String) _struct.getAttribute(27); }

  public void setStaffLastName(String staffLastName) throws SQLException
  { _struct.setAttribute(27, staffLastName); }


  public String getStaffNickname() throws SQLException
  { return (String) _struct.getAttribute(28); }

  public void setStaffNickname(String staffNickname) throws SQLException
  { _struct.setAttribute(28, staffNickname); }


  public String getStaffEmail() throws SQLException
  { return (String) _struct.getAttribute(29); }

  public void setStaffEmail(String staffEmail) throws SQLException
  { _struct.setAttribute(29, staffEmail); }


  public String getStaffEmailOther() throws SQLException
  { return (String) _struct.getAttribute(30); }

  public void setStaffEmailOther(String staffEmailOther) throws SQLException
  { _struct.setAttribute(30, staffEmailOther); }


  public String getStaffLoc() throws SQLException
  { return (String) _struct.getAttribute(31); }

  public void setStaffLoc(String staffLoc) throws SQLException
  { _struct.setAttribute(31, staffLoc); }


  public String getStaffPrefdContMthd() throws SQLException
  { return (String) _struct.getAttribute(32); }

  public void setStaffPrefdContMthd(String staffPrefdContMthd) throws SQLException
  { _struct.setAttribute(32, staffPrefdContMthd); }


  public java.sql.Timestamp getStaffTermDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(33); }

  public void setStaffTermDate(java.sql.Timestamp staffTermDate) throws SQLException
  { _struct.setAttribute(33, staffTermDate); }


  public java.sql.Timestamp getStaffHireDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(34); }

  public void setStaffHireDate(java.sql.Timestamp staffHireDate) throws SQLException
  { _struct.setAttribute(34, staffHireDate); }


  public java.sql.Timestamp getStaffLastRaiseDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(35); }

  public void setStaffLastRaiseDate(java.sql.Timestamp staffLastRaiseDate) throws SQLException
  { _struct.setAttribute(35, staffLastRaiseDate); }


  public String getStaffMrtlStatusName() throws SQLException
  { return (String) _struct.getAttribute(36); }

  public void setStaffMrtlStatusName(String staffMrtlStatusName) throws SQLException
  { _struct.setAttribute(36, staffMrtlStatusName); }


  public java.sql.Timestamp getStaffDob() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(37); }

  public void setStaffDob(java.sql.Timestamp staffDob) throws SQLException
  { _struct.setAttribute(37, staffDob); }


  public String getStaffGenderTypName() throws SQLException
  { return (String) _struct.getAttribute(38); }

  public void setStaffGenderTypName(String staffGenderTypName) throws SQLException
  { _struct.setAttribute(38, staffGenderTypName); }


  public String getStaffRaceEthnicityCode() throws SQLException
  { return (String) _struct.getAttribute(39); }

  public void setStaffRaceEthnicityCode(String staffRaceEthnicityCode) throws SQLException
  { _struct.setAttribute(39, staffRaceEthnicityCode); }


  public String getStaffEducation() throws SQLException
  { return (String) _struct.getAttribute(40); }

  public void setStaffEducation(String staffEducation) throws SQLException
  { _struct.setAttribute(40, staffEducation); }


  public String getStaffReligionCode() throws SQLException
  { return (String) _struct.getAttribute(41); }

  public void setStaffReligionCode(String staffReligionCode) throws SQLException
  { _struct.setAttribute(41, staffReligionCode); }


  public String getStaffTransportMthd() throws SQLException
  { return (String) _struct.getAttribute(42); }

  public void setStaffTransportMthd(String staffTransportMthd) throws SQLException
  { _struct.setAttribute(42, staffTransportMthd); }


  public java.math.BigDecimal getStaffTravelRadius() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(43); }

  public void setStaffTravelRadius(java.math.BigDecimal staffTravelRadius) throws SQLException
  { _struct.setAttribute(43, staffTravelRadius); }


  public java.math.BigDecimal getStaffOnOkInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(44); }

  public void setStaffOnOkInd(java.math.BigDecimal staffOnOkInd) throws SQLException
  { _struct.setAttribute(44, staffOnOkInd); }


  public java.math.BigDecimal getStaffLiOkInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(45); }

  public void setStaffLiOkInd(java.math.BigDecimal staffLiOkInd) throws SQLException
  { _struct.setAttribute(45, staffLiOkInd); }


  public String getStaffTeam() throws SQLException
  { return (String) _struct.getAttribute(46); }

  public void setStaffTeam(String staffTeam) throws SQLException
  { _struct.setAttribute(46, staffTeam); }


  public String getStaffMilitaryRec() throws SQLException
  { return (String) _struct.getAttribute(47); }

  public void setStaffMilitaryRec(String staffMilitaryRec) throws SQLException
  { _struct.setAttribute(47, staffMilitaryRec); }


  public String getStaffRfrlTyp() throws SQLException
  { return (String) _struct.getAttribute(48); }

  public void setStaffRfrlTyp(String staffRfrlTyp) throws SQLException
  { _struct.setAttribute(48, staffRfrlTyp); }


  public String getStaffRfrlName() throws SQLException
  { return (String) _struct.getAttribute(49); }

  public void setStaffRfrlName(String staffRfrlName) throws SQLException
  { _struct.setAttribute(49, staffRfrlName); }


  public String getStaffRfrlInstitution() throws SQLException
  { return (String) _struct.getAttribute(50); }

  public void setStaffRfrlInstitution(String staffRfrlInstitution) throws SQLException
  { _struct.setAttribute(50, staffRfrlInstitution); }


  public String getStaffRfrlCmnt() throws SQLException
  { return (String) _struct.getAttribute(51); }

  public void setStaffRfrlCmnt(String staffRfrlCmnt) throws SQLException
  { _struct.setAttribute(51, staffRfrlCmnt); }


  public String getStaffPositionName() throws SQLException
  { return (String) _struct.getAttribute(52); }

  public void setStaffPositionName(String staffPositionName) throws SQLException
  { _struct.setAttribute(52, staffPositionName); }


  public String getStaffRfrlTypName() throws SQLException
  { return (String) _struct.getAttribute(53); }

  public void setStaffRfrlTypName(String staffRfrlTypName) throws SQLException
  { _struct.setAttribute(53, staffRfrlTypName); }


  public java.sql.Timestamp getStaffLastHireDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(54); }

  public void setStaffLastHireDate(java.sql.Timestamp staffLastHireDate) throws SQLException
  { _struct.setAttribute(54, staffLastHireDate); }
}
