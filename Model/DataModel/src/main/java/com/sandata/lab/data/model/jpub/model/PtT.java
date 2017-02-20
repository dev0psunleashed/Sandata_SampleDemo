package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class PtT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PT_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,91,91,91,91,12,12,12,12,2,2,12,12,12,91,12,12,12,12,12,12,12,12,12,12,12,12,91,12,12,12,12,2,12,12,12,12,12,2,12,12,12,91,12,2,2,91,91,12,2,12,2,91,12,12,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[59];
  protected static final PtT _PtTFactory = new PtT();

  public static ORADataFactory getORADataFactory()
  { return _PtTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[59], _sqlType, _factory); }
  public PtT()
  { _init_struct(true); }
  public PtT(java.math.BigDecimal ptSk, String ptId, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonCode, String changeReasonMemo, java.math.BigDecimal changeVersionId, java.math.BigDecimal currRecInd, String beId, String tzName, String ptStatusName, java.sql.Timestamp ptStatusChangeDate, String ptTinQlfr, String ptTin, String ptMrn, String ptBeAdmId, String ptMedicareId, String ptMedicaidId, String ptOtherId, String ptOtherIdQlfr, String ptFirstName, String ptLastName, String ptMiddleName, String ptMrtlStatusName, java.sql.Timestamp ptDob, String ptGenderTypName, String ptPrefdContMthd, String ptPrefdLangCode, String ptPrefdNickname, java.math.BigDecimal nonEnglishSpeakingInd, String ptWrittenLang, String ptRaceEthnicityCode, String ptReligionCode, String ptCurrResidenceTyp, String personsSharingLivingSpace, java.math.BigDecimal ptSupportNetworkInd, String ptEvacZone, String ptSupportAsst, String ptTal, java.sql.Timestamp ptTalDate, String ptPrioLvlCode, java.math.BigDecimal ptDnrInd, java.math.BigDecimal ptAdInd, java.sql.Timestamp ptDnrDate, java.sql.Timestamp ptDod, String ptDeathCtfNum, java.math.BigDecimal ptWeight, String ptWeightUom, java.math.BigDecimal homeboundInd, java.sql.Timestamp ptDischargeDate, String ptDischargeStatusCode, String ptAdmSrcCode, String ptAdmTypCode, String ptVvId, String ptVvIdQlfr) throws SQLException
  { _init_struct(true);
    setPtSk(ptSk);
    setPtId(ptId);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonCode(changeReasonCode);
    setChangeReasonMemo(changeReasonMemo);
    setChangeVersionId(changeVersionId);
    setCurrRecInd(currRecInd);
    setBeId(beId);
    setTzName(tzName);
    setPtStatusName(ptStatusName);
    setPtStatusChangeDate(ptStatusChangeDate);
    setPtTinQlfr(ptTinQlfr);
    setPtTin(ptTin);
    setPtMrn(ptMrn);
    setPtBeAdmId(ptBeAdmId);
    setPtMedicareId(ptMedicareId);
    setPtMedicaidId(ptMedicaidId);
    setPtOtherId(ptOtherId);
    setPtOtherIdQlfr(ptOtherIdQlfr);
    setPtFirstName(ptFirstName);
    setPtLastName(ptLastName);
    setPtMiddleName(ptMiddleName);
    setPtMrtlStatusName(ptMrtlStatusName);
    setPtDob(ptDob);
    setPtGenderTypName(ptGenderTypName);
    setPtPrefdContMthd(ptPrefdContMthd);
    setPtPrefdLangCode(ptPrefdLangCode);
    setPtPrefdNickname(ptPrefdNickname);
    setNonEnglishSpeakingInd(nonEnglishSpeakingInd);
    setPtWrittenLang(ptWrittenLang);
    setPtRaceEthnicityCode(ptRaceEthnicityCode);
    setPtReligionCode(ptReligionCode);
    setPtCurrResidenceTyp(ptCurrResidenceTyp);
    setPersonsSharingLivingSpace(personsSharingLivingSpace);
    setPtSupportNetworkInd(ptSupportNetworkInd);
    setPtEvacZone(ptEvacZone);
    setPtSupportAsst(ptSupportAsst);
    setPtTal(ptTal);
    setPtTalDate(ptTalDate);
    setPtPrioLvlCode(ptPrioLvlCode);
    setPtDnrInd(ptDnrInd);
    setPtAdInd(ptAdInd);
    setPtDnrDate(ptDnrDate);
    setPtDod(ptDod);
    setPtDeathCtfNum(ptDeathCtfNum);
    setPtWeight(ptWeight);
    setPtWeightUom(ptWeightUom);
    setHomeboundInd(homeboundInd);
    setPtDischargeDate(ptDischargeDate);
    setPtDischargeStatusCode(ptDischargeStatusCode);
    setPtAdmSrcCode(ptAdmSrcCode);
    setPtAdmTypCode(ptAdmTypCode);
    setPtVvId(ptVvId);
    setPtVvIdQlfr(ptVvIdQlfr);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PtT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PtT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPtSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPtSk(java.math.BigDecimal ptSk) throws SQLException
  { _struct.setAttribute(0, ptSk); }


  public String getPtId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setPtId(String ptId) throws SQLException
  { _struct.setAttribute(1, ptId); }


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


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(10); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(10, changeVersionId); }


  public java.math.BigDecimal getCurrRecInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(11); }

  public void setCurrRecInd(java.math.BigDecimal currRecInd) throws SQLException
  { _struct.setAttribute(11, currRecInd); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(12, beId); }


  public String getTzName() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setTzName(String tzName) throws SQLException
  { _struct.setAttribute(13, tzName); }


  public String getPtStatusName() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setPtStatusName(String ptStatusName) throws SQLException
  { _struct.setAttribute(14, ptStatusName); }


  public java.sql.Timestamp getPtStatusChangeDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(15); }

  public void setPtStatusChangeDate(java.sql.Timestamp ptStatusChangeDate) throws SQLException
  { _struct.setAttribute(15, ptStatusChangeDate); }


  public String getPtTinQlfr() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setPtTinQlfr(String ptTinQlfr) throws SQLException
  { _struct.setAttribute(16, ptTinQlfr); }


  public String getPtTin() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setPtTin(String ptTin) throws SQLException
  { _struct.setAttribute(17, ptTin); }


  public String getPtMrn() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setPtMrn(String ptMrn) throws SQLException
  { _struct.setAttribute(18, ptMrn); }


  public String getPtBeAdmId() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setPtBeAdmId(String ptBeAdmId) throws SQLException
  { _struct.setAttribute(19, ptBeAdmId); }


  public String getPtMedicareId() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setPtMedicareId(String ptMedicareId) throws SQLException
  { _struct.setAttribute(20, ptMedicareId); }


  public String getPtMedicaidId() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setPtMedicaidId(String ptMedicaidId) throws SQLException
  { _struct.setAttribute(21, ptMedicaidId); }


  public String getPtOtherId() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setPtOtherId(String ptOtherId) throws SQLException
  { _struct.setAttribute(22, ptOtherId); }


  public String getPtOtherIdQlfr() throws SQLException
  { return (String) _struct.getAttribute(23); }

  public void setPtOtherIdQlfr(String ptOtherIdQlfr) throws SQLException
  { _struct.setAttribute(23, ptOtherIdQlfr); }


  public String getPtFirstName() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setPtFirstName(String ptFirstName) throws SQLException
  { _struct.setAttribute(24, ptFirstName); }


  public String getPtLastName() throws SQLException
  { return (String) _struct.getAttribute(25); }

  public void setPtLastName(String ptLastName) throws SQLException
  { _struct.setAttribute(25, ptLastName); }


  public String getPtMiddleName() throws SQLException
  { return (String) _struct.getAttribute(26); }

  public void setPtMiddleName(String ptMiddleName) throws SQLException
  { _struct.setAttribute(26, ptMiddleName); }


  public String getPtMrtlStatusName() throws SQLException
  { return (String) _struct.getAttribute(27); }

  public void setPtMrtlStatusName(String ptMrtlStatusName) throws SQLException
  { _struct.setAttribute(27, ptMrtlStatusName); }


  public java.sql.Timestamp getPtDob() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(28); }

  public void setPtDob(java.sql.Timestamp ptDob) throws SQLException
  { _struct.setAttribute(28, ptDob); }


  public String getPtGenderTypName() throws SQLException
  { return (String) _struct.getAttribute(29); }

  public void setPtGenderTypName(String ptGenderTypName) throws SQLException
  { _struct.setAttribute(29, ptGenderTypName); }


  public String getPtPrefdContMthd() throws SQLException
  { return (String) _struct.getAttribute(30); }

  public void setPtPrefdContMthd(String ptPrefdContMthd) throws SQLException
  { _struct.setAttribute(30, ptPrefdContMthd); }


  public String getPtPrefdLangCode() throws SQLException
  { return (String) _struct.getAttribute(31); }

  public void setPtPrefdLangCode(String ptPrefdLangCode) throws SQLException
  { _struct.setAttribute(31, ptPrefdLangCode); }


  public String getPtPrefdNickname() throws SQLException
  { return (String) _struct.getAttribute(32); }

  public void setPtPrefdNickname(String ptPrefdNickname) throws SQLException
  { _struct.setAttribute(32, ptPrefdNickname); }


  public java.math.BigDecimal getNonEnglishSpeakingInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(33); }

  public void setNonEnglishSpeakingInd(java.math.BigDecimal nonEnglishSpeakingInd) throws SQLException
  { _struct.setAttribute(33, nonEnglishSpeakingInd); }


  public String getPtWrittenLang() throws SQLException
  { return (String) _struct.getAttribute(34); }

  public void setPtWrittenLang(String ptWrittenLang) throws SQLException
  { _struct.setAttribute(34, ptWrittenLang); }


  public String getPtRaceEthnicityCode() throws SQLException
  { return (String) _struct.getAttribute(35); }

  public void setPtRaceEthnicityCode(String ptRaceEthnicityCode) throws SQLException
  { _struct.setAttribute(35, ptRaceEthnicityCode); }


  public String getPtReligionCode() throws SQLException
  { return (String) _struct.getAttribute(36); }

  public void setPtReligionCode(String ptReligionCode) throws SQLException
  { _struct.setAttribute(36, ptReligionCode); }


  public String getPtCurrResidenceTyp() throws SQLException
  { return (String) _struct.getAttribute(37); }

  public void setPtCurrResidenceTyp(String ptCurrResidenceTyp) throws SQLException
  { _struct.setAttribute(37, ptCurrResidenceTyp); }


  public String getPersonsSharingLivingSpace() throws SQLException
  { return (String) _struct.getAttribute(38); }

  public void setPersonsSharingLivingSpace(String personsSharingLivingSpace) throws SQLException
  { _struct.setAttribute(38, personsSharingLivingSpace); }


  public java.math.BigDecimal getPtSupportNetworkInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(39); }

  public void setPtSupportNetworkInd(java.math.BigDecimal ptSupportNetworkInd) throws SQLException
  { _struct.setAttribute(39, ptSupportNetworkInd); }


  public String getPtEvacZone() throws SQLException
  { return (String) _struct.getAttribute(40); }

  public void setPtEvacZone(String ptEvacZone) throws SQLException
  { _struct.setAttribute(40, ptEvacZone); }


  public String getPtSupportAsst() throws SQLException
  { return (String) _struct.getAttribute(41); }

  public void setPtSupportAsst(String ptSupportAsst) throws SQLException
  { _struct.setAttribute(41, ptSupportAsst); }


  public String getPtTal() throws SQLException
  { return (String) _struct.getAttribute(42); }

  public void setPtTal(String ptTal) throws SQLException
  { _struct.setAttribute(42, ptTal); }


  public java.sql.Timestamp getPtTalDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(43); }

  public void setPtTalDate(java.sql.Timestamp ptTalDate) throws SQLException
  { _struct.setAttribute(43, ptTalDate); }


  public String getPtPrioLvlCode() throws SQLException
  { return (String) _struct.getAttribute(44); }

  public void setPtPrioLvlCode(String ptPrioLvlCode) throws SQLException
  { _struct.setAttribute(44, ptPrioLvlCode); }


  public java.math.BigDecimal getPtDnrInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(45); }

  public void setPtDnrInd(java.math.BigDecimal ptDnrInd) throws SQLException
  { _struct.setAttribute(45, ptDnrInd); }


  public java.math.BigDecimal getPtAdInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(46); }

  public void setPtAdInd(java.math.BigDecimal ptAdInd) throws SQLException
  { _struct.setAttribute(46, ptAdInd); }


  public java.sql.Timestamp getPtDnrDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(47); }

  public void setPtDnrDate(java.sql.Timestamp ptDnrDate) throws SQLException
  { _struct.setAttribute(47, ptDnrDate); }


  public java.sql.Timestamp getPtDod() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(48); }

  public void setPtDod(java.sql.Timestamp ptDod) throws SQLException
  { _struct.setAttribute(48, ptDod); }


  public String getPtDeathCtfNum() throws SQLException
  { return (String) _struct.getAttribute(49); }

  public void setPtDeathCtfNum(String ptDeathCtfNum) throws SQLException
  { _struct.setAttribute(49, ptDeathCtfNum); }


  public java.math.BigDecimal getPtWeight() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(50); }

  public void setPtWeight(java.math.BigDecimal ptWeight) throws SQLException
  { _struct.setAttribute(50, ptWeight); }


  public String getPtWeightUom() throws SQLException
  { return (String) _struct.getAttribute(51); }

  public void setPtWeightUom(String ptWeightUom) throws SQLException
  { _struct.setAttribute(51, ptWeightUom); }


  public java.math.BigDecimal getHomeboundInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(52); }

  public void setHomeboundInd(java.math.BigDecimal homeboundInd) throws SQLException
  { _struct.setAttribute(52, homeboundInd); }


  public java.sql.Timestamp getPtDischargeDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(53); }

  public void setPtDischargeDate(java.sql.Timestamp ptDischargeDate) throws SQLException
  { _struct.setAttribute(53, ptDischargeDate); }


  public String getPtDischargeStatusCode() throws SQLException
  { return (String) _struct.getAttribute(54); }

  public void setPtDischargeStatusCode(String ptDischargeStatusCode) throws SQLException
  { _struct.setAttribute(54, ptDischargeStatusCode); }


  public String getPtAdmSrcCode() throws SQLException
  { return (String) _struct.getAttribute(55); }

  public void setPtAdmSrcCode(String ptAdmSrcCode) throws SQLException
  { _struct.setAttribute(55, ptAdmSrcCode); }


  public String getPtAdmTypCode() throws SQLException
  { return (String) _struct.getAttribute(56); }

  public void setPtAdmTypCode(String ptAdmTypCode) throws SQLException
  { _struct.setAttribute(56, ptAdmTypCode); }


  public String getPtVvId() throws SQLException
  { return (String) _struct.getAttribute(57); }

  public void setPtVvId(String ptVvId) throws SQLException
  { _struct.setAttribute(57, ptVvId); }


  public String getPtVvIdQlfr() throws SQLException
  { return (String) _struct.getAttribute(58); }

  public void setPtVvIdQlfr(String ptVvIdQlfr) throws SQLException
  { _struct.setAttribute(58, ptVvIdQlfr); }

}
