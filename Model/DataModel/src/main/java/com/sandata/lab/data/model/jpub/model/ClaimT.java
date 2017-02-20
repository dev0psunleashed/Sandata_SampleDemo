package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class ClaimT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.CLAIM_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,91,91,91,91,12,12,12,2,2,12,12,12,12,2,2,2,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,91,2004,12,12,12,12,12,12,12,12,12,1,12,91,12,12,12,12,12,12,91,12,12,12,12,12,2,12,12,12,12,12,12,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,91,12,12,12,12,12,91,12,12,12,12,12,12,2,12,12,12,12,2,2,2,12,2,2,12,2,12,2,12,12,12,2,2,2,12,2,12,2,2,2,2,2,2,12,12,12,2,12,12,12,12,2,12,12,12,12,12,12,12,12,12,12,12,2,2,12,12,12,12,12,12,12,2,2,12,12,2,2,12,12,2,2,12,12,2,2,12,12,2,2,12,12,2,2,2,2,2,2,2,2,2,2,2,12,12,12,12,12,2,2,91,2,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[210];
  protected static final ClaimT _ClaimTFactory = new ClaimT();

  public static ORADataFactory getORADataFactory()
  { return _ClaimTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[210], _sqlType, _factory); }
  public ClaimT()
  { _init_struct(true); }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(ClaimT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new ClaimT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getClaimSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setClaimSk(java.math.BigDecimal claimSk) throws SQLException
  { _struct.setAttribute(0, claimSk); }


  public String getClaimId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setClaimId(String claimId) throws SQLException
  { _struct.setAttribute(1, claimId); }


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


  public String getOtherPayerId() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setOtherPayerId(String otherPayerId) throws SQLException
  { _struct.setAttribute(14, otherPayerId); }


  public java.math.BigDecimal getRfrlSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(15); }

  public void setRfrlSk(java.math.BigDecimal rfrlSk) throws SQLException
  { _struct.setAttribute(15, rfrlSk); }


  public java.math.BigDecimal getAuthSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(16); }

  public void setAuthSk(java.math.BigDecimal authSk) throws SQLException
  { _struct.setAttribute(16, authSk); }


  public java.math.BigDecimal getOtherPayerAuthSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(17); }

  public void setOtherPayerAuthSk(java.math.BigDecimal otherPayerAuthSk) throws SQLException
  { _struct.setAttribute(17, otherPayerAuthSk); }


  public String getIcdDxCodeRevisionQlfr() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setIcdDxCodeRevisionQlfr(String icdDxCodeRevisionQlfr) throws SQLException
  { _struct.setAttribute(18, icdDxCodeRevisionQlfr); }


  public String getIcdDxCodePrmy() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setIcdDxCodePrmy(String icdDxCodePrmy) throws SQLException
  { _struct.setAttribute(19, icdDxCodePrmy); }


  public String getIcdDxCode2() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setIcdDxCode2(String icdDxCode2) throws SQLException
  { _struct.setAttribute(20, icdDxCode2); }


  public String getIcdDxCode3() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setIcdDxCode3(String icdDxCode3) throws SQLException
  { _struct.setAttribute(21, icdDxCode3); }


  public String getIcdDxCode4() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setIcdDxCode4(String icdDxCode4) throws SQLException
  { _struct.setAttribute(22, icdDxCode4); }


  public String getIcdDxCode5() throws SQLException
  { return (String) _struct.getAttribute(23); }

  public void setIcdDxCode5(String icdDxCode5) throws SQLException
  { _struct.setAttribute(23, icdDxCode5); }


  public String getIcdDxCode6() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setIcdDxCode6(String icdDxCode6) throws SQLException
  { _struct.setAttribute(24, icdDxCode6); }


  public String getIcdDxCode7() throws SQLException
  { return (String) _struct.getAttribute(25); }

  public void setIcdDxCode7(String icdDxCode7) throws SQLException
  { _struct.setAttribute(25, icdDxCode7); }


  public String getIcdDxCode8() throws SQLException
  { return (String) _struct.getAttribute(26); }

  public void setIcdDxCode8(String icdDxCode8) throws SQLException
  { _struct.setAttribute(26, icdDxCode8); }


  public String getIcdDxCode9() throws SQLException
  { return (String) _struct.getAttribute(27); }

  public void setIcdDxCode9(String icdDxCode9) throws SQLException
  { _struct.setAttribute(27, icdDxCode9); }


  public String getIcdDxCode10() throws SQLException
  { return (String) _struct.getAttribute(28); }

  public void setIcdDxCode10(String icdDxCode10) throws SQLException
  { _struct.setAttribute(28, icdDxCode10); }


  public String getIcdDxCode11() throws SQLException
  { return (String) _struct.getAttribute(29); }

  public void setIcdDxCode11(String icdDxCode11) throws SQLException
  { _struct.setAttribute(29, icdDxCode11); }


  public String getIcdDxCode12() throws SQLException
  { return (String) _struct.getAttribute(30); }

  public void setIcdDxCode12(String icdDxCode12) throws SQLException
  { _struct.setAttribute(30, icdDxCode12); }


  public String getClaimPayer() throws SQLException
  { return (String) _struct.getAttribute(31); }

  public void setClaimPayer(String claimPayer) throws SQLException
  { _struct.setAttribute(31, claimPayer); }


  public String getClaimTyp() throws SQLException
  { return (String) _struct.getAttribute(32); }

  public void setClaimTyp(String claimTyp) throws SQLException
  { _struct.setAttribute(32, claimTyp); }


  public String getInvSubmTypCode() throws SQLException
  { return (String) _struct.getAttribute(33); }

  public void setInvSubmTypCode(String invSubmTypCode) throws SQLException
  { _struct.setAttribute(33, invSubmTypCode); }


  public java.sql.Timestamp getClaimSubmDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(34); }

  public void setClaimSubmDate(java.sql.Timestamp claimSubmDate) throws SQLException
  { _struct.setAttribute(34, claimSubmDate); }


  public oracle.sql.BLOB getClaimRec() throws SQLException
  { return (oracle.sql.BLOB) _struct.getOracleAttribute(35); }

  public void setClaimRec(oracle.sql.BLOB claimRec) throws SQLException
  { _struct.setOracleAttribute(35, claimRec); }


  public String getInvFmtTypName() throws SQLException
  { return (String) _struct.getAttribute(36); }

  public void setInvFmtTypName(String invFmtTypName) throws SQLException
  { _struct.setAttribute(36, invFmtTypName); }


  public String getSandataClaimStatus() throws SQLException
  { return (String) _struct.getAttribute(37); }

  public void setSandataClaimStatus(String sandataClaimStatus) throws SQLException
  { _struct.setAttribute(37, sandataClaimStatus); }


  public String getSubsLastName() throws SQLException
  { return (String) _struct.getAttribute(38); }

  public void setSubsLastName(String subsLastName) throws SQLException
  { _struct.setAttribute(38, subsLastName); }


  public String getSubsFirstName() throws SQLException
  { return (String) _struct.getAttribute(39); }

  public void setSubsFirstName(String subsFirstName) throws SQLException
  { _struct.setAttribute(39, subsFirstName); }


  public String getSubsMiddleName() throws SQLException
  { return (String) _struct.getAttribute(40); }

  public void setSubsMiddleName(String subsMiddleName) throws SQLException
  { _struct.setAttribute(40, subsMiddleName); }


  public String getSubsNameSuffix() throws SQLException
  { return (String) _struct.getAttribute(41); }

  public void setSubsNameSuffix(String subsNameSuffix) throws SQLException
  { _struct.setAttribute(41, subsNameSuffix); }


  public String getSubsAddr1() throws SQLException
  { return (String) _struct.getAttribute(42); }

  public void setSubsAddr1(String subsAddr1) throws SQLException
  { _struct.setAttribute(42, subsAddr1); }


  public String getSubsAddr2() throws SQLException
  { return (String) _struct.getAttribute(43); }

  public void setSubsAddr2(String subsAddr2) throws SQLException
  { _struct.setAttribute(43, subsAddr2); }


  public String getSubsCityName() throws SQLException
  { return (String) _struct.getAttribute(44); }

  public void setSubsCityName(String subsCityName) throws SQLException
  { _struct.setAttribute(44, subsCityName); }


  public String getSubsStateCode() throws SQLException
  { return (String) _struct.getAttribute(45); }

  public void setSubsStateCode(String subsStateCode) throws SQLException
  { _struct.setAttribute(45, subsStateCode); }


  public String getSubsPstlCode() throws SQLException
  { return (String) _struct.getAttribute(46); }

  public void setSubsPstlCode(String subsPstlCode) throws SQLException
  { _struct.setAttribute(46, subsPstlCode); }


  public java.sql.Timestamp getSubsDob() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(47); }

  public void setSubsDob(java.sql.Timestamp subsDob) throws SQLException
  { _struct.setAttribute(47, subsDob); }


  public String getSubsGenderTypName() throws SQLException
  { return (String) _struct.getAttribute(48); }

  public void setSubsGenderTypName(String subsGenderTypName) throws SQLException
  { _struct.setAttribute(48, subsGenderTypName); }


  public String getSubsPrmyId() throws SQLException
  { return (String) _struct.getAttribute(49); }

  public void setSubsPrmyId(String subsPrmyId) throws SQLException
  { _struct.setAttribute(49, subsPrmyId); }


  public String getInsuredGrpNum() throws SQLException
  { return (String) _struct.getAttribute(50); }

  public void setInsuredGrpNum(String insuredGrpNum) throws SQLException
  { _struct.setAttribute(50, insuredGrpNum); }


  public String getInsuredGrpName() throws SQLException
  { return (String) _struct.getAttribute(51); }

  public void setInsuredGrpName(String insuredGrpName) throws SQLException
  { _struct.setAttribute(51, insuredGrpName); }


  public String getIndivRelCode() throws SQLException
  { return (String) _struct.getAttribute(52); }

  public void setIndivRelCode(String indivRelCode) throws SQLException
  { _struct.setAttribute(52, indivRelCode); }


  public String getInsTypCode() throws SQLException
  { return (String) _struct.getAttribute(53); }

  public void setInsTypCode(String insTypCode) throws SQLException
  { _struct.setAttribute(53, insTypCode); }


  public java.sql.Timestamp getInsuredIndivDod() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(54); }

  public void setInsuredIndivDod(java.sql.Timestamp insuredIndivDod) throws SQLException
  { _struct.setAttribute(54, insuredIndivDod); }


  public String getSubsSupplId() throws SQLException
  { return (String) _struct.getAttribute(55); }

  public void setSubsSupplId(String subsSupplId) throws SQLException
  { _struct.setAttribute(55, subsSupplId); }


  public String getClaimFilingIndCode() throws SQLException
  { return (String) _struct.getAttribute(56); }

  public void setClaimFilingIndCode(String claimFilingIndCode) throws SQLException
  { _struct.setAttribute(56, claimFilingIndCode); }


  public String getProptyCasClaimNum() throws SQLException
  { return (String) _struct.getAttribute(57); }

  public void setProptyCasClaimNum(String proptyCasClaimNum) throws SQLException
  { _struct.setAttribute(57, proptyCasClaimNum); }


  public String getPtPrmyId() throws SQLException
  { return (String) _struct.getAttribute(58); }

  public void setPtPrmyId(String ptPrmyId) throws SQLException
  { _struct.setAttribute(58, ptPrmyId); }


  public String getPtScndryId() throws SQLException
  { return (String) _struct.getAttribute(59); }

  public void setPtScndryId(String ptScndryId) throws SQLException
  { _struct.setAttribute(59, ptScndryId); }


  public java.math.BigDecimal getPregnancyInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(60); }

  public void setPregnancyInd(java.math.BigDecimal pregnancyInd) throws SQLException
  { _struct.setAttribute(60, pregnancyInd); }


  public String getPtAccountNum() throws SQLException
  { return (String) _struct.getAttribute(61); }

  public void setPtAccountNum(String ptAccountNum) throws SQLException
  { _struct.setAttribute(61, ptAccountNum); }


  public String getReleaseOfInfoCode() throws SQLException
  { return (String) _struct.getAttribute(62); }

  public void setReleaseOfInfoCode(String releaseOfInfoCode) throws SQLException
  { _struct.setAttribute(62, releaseOfInfoCode); }


  public String getPtSigSrcCode() throws SQLException
  { return (String) _struct.getAttribute(63); }

  public void setPtSigSrcCode(String ptSigSrcCode) throws SQLException
  { _struct.setAttribute(63, ptSigSrcCode); }


  public String getRelatedCausesCode() throws SQLException
  { return (String) _struct.getAttribute(64); }

  public void setRelatedCausesCode(String relatedCausesCode) throws SQLException
  { _struct.setAttribute(64, relatedCausesCode); }


  public String getAutoAccidentStateCode() throws SQLException
  { return (String) _struct.getAttribute(65); }

  public void setAutoAccidentStateCode(String autoAccidentStateCode) throws SQLException
  { _struct.setAttribute(65, autoAccidentStateCode); }


  public String getAutoAccidentCountryCode() throws SQLException
  { return (String) _struct.getAttribute(66); }

  public void setAutoAccidentCountryCode(String autoAccidentCountryCode) throws SQLException
  { _struct.setAttribute(66, autoAccidentCountryCode); }


  public java.sql.Timestamp getInitialTreatDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(67); }

  public void setInitialTreatDate(java.sql.Timestamp initialTreatDate) throws SQLException
  { _struct.setAttribute(67, initialTreatDate); }


  public java.sql.Timestamp getPtLastSeenDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(68); }

  public void setPtLastSeenDate(java.sql.Timestamp ptLastSeenDate) throws SQLException
  { _struct.setAttribute(68, ptLastSeenDate); }


  public java.sql.Timestamp getCurrCondOnsetDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(69); }

  public void setCurrCondOnsetDate(java.sql.Timestamp currCondOnsetDate) throws SQLException
  { _struct.setAttribute(69, currCondOnsetDate); }


  public java.sql.Timestamp getAcuteManifestationDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(70); }

  public void setAcuteManifestationDate(java.sql.Timestamp acuteManifestationDate) throws SQLException
  { _struct.setAttribute(70, acuteManifestationDate); }


  public java.sql.Timestamp getSimiliarCondOnsetDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(71); }

  public void setSimiliarCondOnsetDate(java.sql.Timestamp similiarCondOnsetDate) throws SQLException
  { _struct.setAttribute(71, similiarCondOnsetDate); }


  public java.sql.Timestamp getAccidentDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(72); }

  public void setAccidentDate(java.sql.Timestamp accidentDate) throws SQLException
  { _struct.setAttribute(72, accidentDate); }


  public java.sql.Timestamp getLastMenstrualPrdDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(73); }

  public void setLastMenstrualPrdDate(java.sql.Timestamp lastMenstrualPrdDate) throws SQLException
  { _struct.setAttribute(73, lastMenstrualPrdDate); }


  public java.sql.Timestamp getLastXrayDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(74); }

  public void setLastXrayDate(java.sql.Timestamp lastXrayDate) throws SQLException
  { _struct.setAttribute(74, lastXrayDate); }


  public java.sql.Timestamp getRxDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(75); }

  public void setRxDate(java.sql.Timestamp rxDate) throws SQLException
  { _struct.setAttribute(75, rxDate); }


  public java.sql.Timestamp getDisFromDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(76); }

  public void setDisFromDate(java.sql.Timestamp disFromDate) throws SQLException
  { _struct.setAttribute(76, disFromDate); }


  public java.sql.Timestamp getDisToDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(77); }

  public void setDisToDate(java.sql.Timestamp disToDate) throws SQLException
  { _struct.setAttribute(77, disToDate); }


  public java.sql.Timestamp getLastWorkedDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(78); }

  public void setLastWorkedDate(java.sql.Timestamp lastWorkedDate) throws SQLException
  { _struct.setAttribute(78, lastWorkedDate); }


  public java.sql.Timestamp getWorkReturnDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(79); }

  public void setWorkReturnDate(java.sql.Timestamp workReturnDate) throws SQLException
  { _struct.setAttribute(79, workReturnDate); }


  public java.sql.Timestamp getRelatedHospDischargeDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(80); }

  public void setRelatedHospDischargeDate(java.sql.Timestamp relatedHospDischargeDate) throws SQLException
  { _struct.setAttribute(80, relatedHospDischargeDate); }


  public java.sql.Timestamp getRelatedHospAdmDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(81); }

  public void setRelatedHospAdmDate(java.sql.Timestamp relatedHospAdmDate) throws SQLException
  { _struct.setAttribute(81, relatedHospAdmDate); }


  public java.sql.Timestamp getRelinqushedCareDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(82); }

  public void setRelinqushedCareDate(java.sql.Timestamp relinqushedCareDate) throws SQLException
  { _struct.setAttribute(82, relinqushedCareDate); }


  public String getCliaNum() throws SQLException
  { return (String) _struct.getAttribute(83); }

  public void setCliaNum(String cliaNum) throws SQLException
  { _struct.setAttribute(83, cliaNum); }


  public String getReprCrn() throws SQLException
  { return (String) _struct.getAttribute(84); }

  public void setReprCrn(String reprCrn) throws SQLException
  { _struct.setAttribute(84, reprCrn); }


  public String getAdjReprCrn() throws SQLException
  { return (String) _struct.getAttribute(85); }

  public void setAdjReprCrn(String adjReprCrn) throws SQLException
  { _struct.setAttribute(85, adjReprCrn); }


  public String getIdeId() throws SQLException
  { return (String) _struct.getAttribute(86); }

  public void setIdeId(String ideId) throws SQLException
  { _struct.setAttribute(86, ideId); }


  public String getChTraceNum() throws SQLException
  { return (String) _struct.getAttribute(87); }

  public void setChTraceNum(String chTraceNum) throws SQLException
  { _struct.setAttribute(87, chTraceNum); }


  public java.sql.Timestamp getTxnSetCreationDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(88); }

  public void setTxnSetCreationDate(java.sql.Timestamp txnSetCreationDate) throws SQLException
  { _struct.setAttribute(88, txnSetCreationDate); }


  public String getSpecialProgramCode() throws SQLException
  { return (String) _struct.getAttribute(89); }

  public void setSpecialProgramCode(String specialProgramCode) throws SQLException
  { _struct.setAttribute(89, specialProgramCode); }


  public String getPartAgrmtCode() throws SQLException
  { return (String) _struct.getAttribute(90); }

  public void setPartAgrmtCode(String partAgrmtCode) throws SQLException
  { _struct.setAttribute(90, partAgrmtCode); }


  public String getAttRprtTypCode() throws SQLException
  { return (String) _struct.getAttribute(91); }

  public void setAttRprtTypCode(String attRprtTypCode) throws SQLException
  { _struct.setAttribute(91, attRprtTypCode); }


  public String getDelayReasonCode() throws SQLException
  { return (String) _struct.getAttribute(92); }

  public void setDelayReasonCode(String delayReasonCode) throws SQLException
  { _struct.setAttribute(92, delayReasonCode); }


  public String getAttTrnsmsnCode() throws SQLException
  { return (String) _struct.getAttribute(93); }

  public void setAttTrnsmsnCode(String attTrnsmsnCode) throws SQLException
  { _struct.setAttribute(93, attTrnsmsnCode); }


  public String getSvcAuthExcpCode() throws SQLException
  { return (String) _struct.getAttribute(94); }

  public void setSvcAuthExcpCode(String svcAuthExcpCode) throws SQLException
  { _struct.setAttribute(94, svcAuthExcpCode); }


  public java.math.BigDecimal getMedicareSection4081Ind() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(95); }

  public void setMedicareSection4081Ind(java.math.BigDecimal medicareSection4081Ind) throws SQLException
  { _struct.setAttribute(95, medicareSection4081Ind); }


  public String getMmgCertNum() throws SQLException
  { return (String) _struct.getAttribute(96); }

  public void setMmgCertNum(String mmgCertNum) throws SQLException
  { _struct.setAttribute(96, mmgCertNum); }


  public String getApgNum() throws SQLException
  { return (String) _struct.getAttribute(97); }

  public void setApgNum(String apgNum) throws SQLException
  { _struct.setAttribute(97, apgNum); }


  public String getMrn() throws SQLException
  { return (String) _struct.getAttribute(98); }

  public void setMrn(String mrn) throws SQLException
  { _struct.setAttribute(98, mrn); }


  public String getDemoProjectId() throws SQLException
  { return (String) _struct.getAttribute(99); }

  public void setDemoProjectId(String demoProjectId) throws SQLException
  { _struct.setAttribute(99, demoProjectId); }


  public java.math.BigDecimal getNumOfHomeHlthVisits() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(100); }

  public void setNumOfHomeHlthVisits(java.math.BigDecimal numOfHomeHlthVisits) throws SQLException
  { _struct.setAttribute(100, numOfHomeHlthVisits); }


  public java.math.BigDecimal getHomeHlthVisitFreqCount() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(101); }

  public void setHomeHlthVisitFreqCount(java.math.BigDecimal homeHlthVisitFreqCount) throws SQLException
  { _struct.setAttribute(101, homeHlthVisitFreqCount); }


  public java.math.BigDecimal getDurOfHomeHlthVisitsCount() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(102); }

  public void setDurOfHomeHlthVisitsCount(java.math.BigDecimal durOfHomeHlthVisitsCount) throws SQLException
  { _struct.setAttribute(102, durOfHomeHlthVisitsCount); }


  public String getClaimFreqTypCode() throws SQLException
  { return (String) _struct.getAttribute(103); }

  public void setClaimFreqTypCode(String claimFreqTypCode) throws SQLException
  { _struct.setAttribute(103, claimFreqTypCode); }


  public java.math.BigDecimal getReprAlwdAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(104); }

  public void setReprAlwdAmt(java.math.BigDecimal reprAlwdAmt) throws SQLException
  { _struct.setAttribute(104, reprAlwdAmt); }


  public java.math.BigDecimal getReprSavingAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(105); }

  public void setReprSavingAmt(java.math.BigDecimal reprSavingAmt) throws SQLException
  { _struct.setAttribute(105, reprSavingAmt); }


  public String getRepricingOrgId() throws SQLException
  { return (String) _struct.getAttribute(106); }

  public void setRepricingOrgId(String repricingOrgId) throws SQLException
  { _struct.setAttribute(106, repricingOrgId); }


  public java.math.BigDecimal getRepricingPerDiemAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(107); }

  public void setRepricingPerDiemAmt(java.math.BigDecimal repricingPerDiemAmt) throws SQLException
  { _struct.setAttribute(107, repricingPerDiemAmt); }


  public String getReprAprvdApgCode() throws SQLException
  { return (String) _struct.getAttribute(108); }

  public void setReprAprvdApgCode(String reprAprvdApgCode) throws SQLException
  { _struct.setAttribute(108, reprAprvdApgCode); }


  public java.math.BigDecimal getReprAprvdApgAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(109); }

  public void setReprAprvdApgAmt(java.math.BigDecimal reprAprvdApgAmt) throws SQLException
  { _struct.setAttribute(109, reprAprvdApgAmt); }


  public String getRejectReasonCode() throws SQLException
  { return (String) _struct.getAttribute(110); }

  public void setRejectReasonCode(String rejectReasonCode) throws SQLException
  { _struct.setAttribute(110, rejectReasonCode); }


  public String getPolicyCompCode() throws SQLException
  { return (String) _struct.getAttribute(111); }

  public void setPolicyCompCode(String policyCompCode) throws SQLException
  { _struct.setAttribute(111, policyCompCode); }


  public String getExcpCode() throws SQLException
  { return (String) _struct.getAttribute(112); }

  public void setExcpCode(String excpCode) throws SQLException
  { _struct.setAttribute(112, excpCode); }


  public java.math.BigDecimal getTotalClaimChargeAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(113); }

  public void setTotalClaimChargeAmt(java.math.BigDecimal totalClaimChargeAmt) throws SQLException
  { _struct.setAttribute(113, totalClaimChargeAmt); }


  public java.math.BigDecimal getContrAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(114); }

  public void setContrAmt(java.math.BigDecimal contrAmt) throws SQLException
  { _struct.setAttribute(114, contrAmt); }


  public java.math.BigDecimal getContrPercentage() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(115); }

  public void setContrPercentage(java.math.BigDecimal contrPercentage) throws SQLException
  { _struct.setAttribute(115, contrPercentage); }


  public String getContrCode() throws SQLException
  { return (String) _struct.getAttribute(116); }

  public void setContrCode(String contrCode) throws SQLException
  { _struct.setAttribute(116, contrCode); }


  public java.math.BigDecimal getTermsDiscountPercentage() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(117); }

  public void setTermsDiscountPercentage(java.math.BigDecimal termsDiscountPercentage) throws SQLException
  { _struct.setAttribute(117, termsDiscountPercentage); }


  public String getContrVersionId() throws SQLException
  { return (String) _struct.getAttribute(118); }

  public void setContrVersionId(String contrVersionId) throws SQLException
  { _struct.setAttribute(118, contrVersionId); }


  public java.math.BigDecimal getCreditCardMaximumAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(119); }

  public void setCreditCardMaximumAmt(java.math.BigDecimal creditCardMaximumAmt) throws SQLException
  { _struct.setAttribute(119, creditCardMaximumAmt); }


  public java.math.BigDecimal getPtPaidAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(120); }

  public void setPtPaidAmt(java.math.BigDecimal ptPaidAmt) throws SQLException
  { _struct.setAttribute(120, ptPaidAmt); }


  public java.math.BigDecimal getTotalPurchSvcAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(121); }

  public void setTotalPurchSvcAmt(java.math.BigDecimal totalPurchSvcAmt) throws SQLException
  { _struct.setAttribute(121, totalPurchSvcAmt); }


  public java.math.BigDecimal getPayerPaidAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(122); }

  public void setPayerPaidAmt(java.math.BigDecimal payerPaidAmt) throws SQLException
  { _struct.setAttribute(122, payerPaidAmt); }


  public java.math.BigDecimal getAprvdAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(123); }

  public void setAprvdAmt(java.math.BigDecimal aprvdAmt) throws SQLException
  { _struct.setAttribute(123, aprvdAmt); }


  public java.math.BigDecimal getAlwdAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(124); }

  public void setAlwdAmt(java.math.BigDecimal alwdAmt) throws SQLException
  { _struct.setAttribute(124, alwdAmt); }


  public String getCcyCode() throws SQLException
  { return (String) _struct.getAttribute(125); }

  public void setCcyCode(String ccyCode) throws SQLException
  { _struct.setAttribute(125, ccyCode); }


  public String getAmblTransportCode() throws SQLException
  { return (String) _struct.getAttribute(126); }

  public void setAmblTransportCode(String amblTransportCode) throws SQLException
  { _struct.setAttribute(126, amblTransportCode); }


  public String getAmblTransportReasonCode() throws SQLException
  { return (String) _struct.getAttribute(127); }

  public void setAmblTransportReasonCode(String amblTransportReasonCode) throws SQLException
  { _struct.setAttribute(127, amblTransportReasonCode); }


  public java.math.BigDecimal getTransportDistance() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(128); }

  public void setTransportDistance(java.math.BigDecimal transportDistance) throws SQLException
  { _struct.setAttribute(128, transportDistance); }


  public String getRoundTripPurposeDesc() throws SQLException
  { return (String) _struct.getAttribute(129); }

  public void setRoundTripPurposeDesc(String roundTripPurposeDesc) throws SQLException
  { _struct.setAttribute(129, roundTripPurposeDesc); }


  public String getStretcherPurposeDesc() throws SQLException
  { return (String) _struct.getAttribute(130); }

  public void setStretcherPurposeDesc(String stretcherPurposeDesc) throws SQLException
  { _struct.setAttribute(130, stretcherPurposeDesc); }


  public String getPtCondCode() throws SQLException
  { return (String) _struct.getAttribute(131); }

  public void setPtCondCode(String ptCondCode) throws SQLException
  { _struct.setAttribute(131, ptCondCode); }


  public String getPtCondDesc() throws SQLException
  { return (String) _struct.getAttribute(132); }

  public void setPtCondDesc(String ptCondDesc) throws SQLException
  { _struct.setAttribute(132, ptCondDesc); }


  public java.math.BigDecimal getXrayAvailInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(133); }

  public void setXrayAvailInd(java.math.BigDecimal xrayAvailInd) throws SQLException
  { _struct.setAttribute(133, xrayAvailInd); }


  public String getAmblCertCondCode1() throws SQLException
  { return (String) _struct.getAttribute(134); }

  public void setAmblCertCondCode1(String amblCertCondCode1) throws SQLException
  { _struct.setAttribute(134, amblCertCondCode1); }


  public String getAmblCertCondCode2() throws SQLException
  { return (String) _struct.getAttribute(135); }

  public void setAmblCertCondCode2(String amblCertCondCode2) throws SQLException
  { _struct.setAttribute(135, amblCertCondCode2); }


  public String getAmblCertCondCode3() throws SQLException
  { return (String) _struct.getAttribute(136); }

  public void setAmblCertCondCode3(String amblCertCondCode3) throws SQLException
  { _struct.setAttribute(136, amblCertCondCode3); }


  public String getAmblCertCondCode4() throws SQLException
  { return (String) _struct.getAttribute(137); }

  public void setAmblCertCondCode4(String amblCertCondCode4) throws SQLException
  { _struct.setAttribute(137, amblCertCondCode4); }


  public String getAmblCertCondCode5() throws SQLException
  { return (String) _struct.getAttribute(138); }

  public void setAmblCertCondCode5(String amblCertCondCode5) throws SQLException
  { _struct.setAttribute(138, amblCertCondCode5); }


  public String getPtVisionCondCode1() throws SQLException
  { return (String) _struct.getAttribute(139); }

  public void setPtVisionCondCode1(String ptVisionCondCode1) throws SQLException
  { _struct.setAttribute(139, ptVisionCondCode1); }


  public String getPtVisionCondCode2() throws SQLException
  { return (String) _struct.getAttribute(140); }

  public void setPtVisionCondCode2(String ptVisionCondCode2) throws SQLException
  { _struct.setAttribute(140, ptVisionCondCode2); }


  public String getPtVisionCondCode3() throws SQLException
  { return (String) _struct.getAttribute(141); }

  public void setPtVisionCondCode3(String ptVisionCondCode3) throws SQLException
  { _struct.setAttribute(141, ptVisionCondCode3); }


  public String getEpsdtRfrlCondCode1() throws SQLException
  { return (String) _struct.getAttribute(142); }

  public void setEpsdtRfrlCondCode1(String epsdtRfrlCondCode1) throws SQLException
  { _struct.setAttribute(142, epsdtRfrlCondCode1); }


  public String getEpsdtRfrlCondCode2() throws SQLException
  { return (String) _struct.getAttribute(143); }

  public void setEpsdtRfrlCondCode2(String epsdtRfrlCondCode2) throws SQLException
  { _struct.setAttribute(143, epsdtRfrlCondCode2); }


  public String getEpsdtRfrlCondCode3() throws SQLException
  { return (String) _struct.getAttribute(144); }

  public void setEpsdtRfrlCondCode3(String epsdtRfrlCondCode3) throws SQLException
  { _struct.setAttribute(144, epsdtRfrlCondCode3); }


  public java.math.BigDecimal getCertPrdProjVisitCount() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(145); }

  public void setCertPrdProjVisitCount(java.math.BigDecimal certPrdProjVisitCount) throws SQLException
  { _struct.setAttribute(145, certPrdProjVisitCount); }


  public java.math.BigDecimal getTotalVisitsRenderedCount() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(146); }

  public void setTotalVisitsRenderedCount(java.math.BigDecimal totalVisitsRenderedCount) throws SQLException
  { _struct.setAttribute(146, totalVisitsRenderedCount); }


  public String getPayToHcpNpi() throws SQLException
  { return (String) _struct.getAttribute(147); }

  public void setPayToHcpNpi(String payToHcpNpi) throws SQLException
  { _struct.setAttribute(147, payToHcpNpi); }


  public String getBillingHcpNpi() throws SQLException
  { return (String) _struct.getAttribute(148); }

  public void setBillingHcpNpi(String billingHcpNpi) throws SQLException
  { _struct.setAttribute(148, billingHcpNpi); }


  public String getAttendingHcpNpi() throws SQLException
  { return (String) _struct.getAttribute(149); }

  public void setAttendingHcpNpi(String attendingHcpNpi) throws SQLException
  { _struct.setAttribute(149, attendingHcpNpi); }


  public String getRefingHcpNpi() throws SQLException
  { return (String) _struct.getAttribute(150); }

  public void setRefingHcpNpi(String refingHcpNpi) throws SQLException
  { _struct.setAttribute(150, refingHcpNpi); }


  public String getRenderHcpNpi() throws SQLException
  { return (String) _struct.getAttribute(151); }

  public void setRenderHcpNpi(String renderHcpNpi) throws SQLException
  { _struct.setAttribute(151, renderHcpNpi); }


  public String getAdjmtReasonCode1() throws SQLException
  { return (String) _struct.getAttribute(152); }

  public void setAdjmtReasonCode1(String adjmtReasonCode1) throws SQLException
  { _struct.setAttribute(152, adjmtReasonCode1); }


  public String getAdjmtTypCode1() throws SQLException
  { return (String) _struct.getAttribute(153); }

  public void setAdjmtTypCode1(String adjmtTypCode1) throws SQLException
  { _struct.setAttribute(153, adjmtTypCode1); }


  public java.math.BigDecimal getAdjmtAmt1() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(154); }

  public void setAdjmtAmt1(java.math.BigDecimal adjmtAmt1) throws SQLException
  { _struct.setAttribute(154, adjmtAmt1); }


  public java.math.BigDecimal getAdjmtQty1() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(155); }

  public void setAdjmtQty1(java.math.BigDecimal adjmtQty1) throws SQLException
  { _struct.setAttribute(155, adjmtQty1); }


  public String getAdjmtReasonCode2() throws SQLException
  { return (String) _struct.getAttribute(156); }

  public void setAdjmtReasonCode2(String adjmtReasonCode2) throws SQLException
  { _struct.setAttribute(156, adjmtReasonCode2); }


  public String getAdjmtTypCode2() throws SQLException
  { return (String) _struct.getAttribute(157); }

  public void setAdjmtTypCode2(String adjmtTypCode2) throws SQLException
  { _struct.setAttribute(157, adjmtTypCode2); }


  public java.math.BigDecimal getAdjmtAmt2() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(158); }

  public void setAdjmtAmt2(java.math.BigDecimal adjmtAmt2) throws SQLException
  { _struct.setAttribute(158, adjmtAmt2); }


  public java.math.BigDecimal getAdjmtQty2() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(159); }

  public void setAdjmtQty2(java.math.BigDecimal adjmtQty2) throws SQLException
  { _struct.setAttribute(159, adjmtQty2); }


  public String getAdjmtReasonCode3() throws SQLException
  { return (String) _struct.getAttribute(160); }

  public void setAdjmtReasonCode3(String adjmtReasonCode3) throws SQLException
  { _struct.setAttribute(160, adjmtReasonCode3); }


  public String getAdjmtTypCode3() throws SQLException
  { return (String) _struct.getAttribute(161); }

  public void setAdjmtTypCode3(String adjmtTypCode3) throws SQLException
  { _struct.setAttribute(161, adjmtTypCode3); }


  public java.math.BigDecimal getAdjmtAmt3() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(162); }

  public void setAdjmtAmt3(java.math.BigDecimal adjmtAmt3) throws SQLException
  { _struct.setAttribute(162, adjmtAmt3); }


  public java.math.BigDecimal getAdjmtQty3() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(163); }

  public void setAdjmtQty3(java.math.BigDecimal adjmtQty3) throws SQLException
  { _struct.setAttribute(163, adjmtQty3); }


  public String getAdjmtReasonCode4() throws SQLException
  { return (String) _struct.getAttribute(164); }

  public void setAdjmtReasonCode4(String adjmtReasonCode4) throws SQLException
  { _struct.setAttribute(164, adjmtReasonCode4); }


  public String getAdjmtTypCode4() throws SQLException
  { return (String) _struct.getAttribute(165); }

  public void setAdjmtTypCode4(String adjmtTypCode4) throws SQLException
  { _struct.setAttribute(165, adjmtTypCode4); }


  public java.math.BigDecimal getAdjmtAmt4() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(166); }

  public void setAdjmtAmt4(java.math.BigDecimal adjmtAmt4) throws SQLException
  { _struct.setAttribute(166, adjmtAmt4); }


  public java.math.BigDecimal getAdjmtQty4() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(167); }

  public void setAdjmtQty4(java.math.BigDecimal adjmtQty4) throws SQLException
  { _struct.setAttribute(167, adjmtQty4); }


  public String getAdjmtReasonCode5() throws SQLException
  { return (String) _struct.getAttribute(168); }

  public void setAdjmtReasonCode5(String adjmtReasonCode5) throws SQLException
  { _struct.setAttribute(168, adjmtReasonCode5); }


  public String getAdjmtTypCode5() throws SQLException
  { return (String) _struct.getAttribute(169); }

  public void setAdjmtTypCode5(String adjmtTypCode5) throws SQLException
  { _struct.setAttribute(169, adjmtTypCode5); }


  public java.math.BigDecimal getAdjmtAmt5() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(170); }

  public void setAdjmtAmt5(java.math.BigDecimal adjmtAmt5) throws SQLException
  { _struct.setAttribute(170, adjmtAmt5); }


  public java.math.BigDecimal getAdjmtQty5() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(171); }

  public void setAdjmtQty5(java.math.BigDecimal adjmtQty5) throws SQLException
  { _struct.setAttribute(171, adjmtQty5); }


  public String getAdjmtReasonCode6() throws SQLException
  { return (String) _struct.getAttribute(172); }

  public void setAdjmtReasonCode6(String adjmtReasonCode6) throws SQLException
  { _struct.setAttribute(172, adjmtReasonCode6); }


  public String getAdjmtTypCode6() throws SQLException
  { return (String) _struct.getAttribute(173); }

  public void setAdjmtTypCode6(String adjmtTypCode6) throws SQLException
  { _struct.setAttribute(173, adjmtTypCode6); }


  public java.math.BigDecimal getAdjmtAmt6() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(174); }

  public void setAdjmtAmt6(java.math.BigDecimal adjmtAmt6) throws SQLException
  { _struct.setAttribute(174, adjmtAmt6); }


  public java.math.BigDecimal getAdjmtQty6() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(175); }

  public void setAdjmtQty6(java.math.BigDecimal adjmtQty6) throws SQLException
  { _struct.setAttribute(175, adjmtQty6); }


  public java.math.BigDecimal getOtherPayerPtRespAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(176); }

  public void setOtherPayerPtRespAmt(java.math.BigDecimal otherPayerPtRespAmt) throws SQLException
  { _struct.setAttribute(176, otherPayerPtRespAmt); }


  public java.math.BigDecimal getOtherPayerCoveredAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(177); }

  public void setOtherPayerCoveredAmt(java.math.BigDecimal otherPayerCoveredAmt) throws SQLException
  { _struct.setAttribute(177, otherPayerCoveredAmt); }


  public java.math.BigDecimal getOtherPayerDiscountAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(178); }

  public void setOtherPayerDiscountAmt(java.math.BigDecimal otherPayerDiscountAmt) throws SQLException
  { _struct.setAttribute(178, otherPayerDiscountAmt); }


  public java.math.BigDecimal getOtherPayerPerDayLmtAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(179); }

  public void setOtherPayerPerDayLmtAmt(java.math.BigDecimal otherPayerPerDayLmtAmt) throws SQLException
  { _struct.setAttribute(179, otherPayerPerDayLmtAmt); }


  public java.math.BigDecimal getOtherPayerPtPaidAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(180); }

  public void setOtherPayerPtPaidAmt(java.math.BigDecimal otherPayerPtPaidAmt) throws SQLException
  { _struct.setAttribute(180, otherPayerPtPaidAmt); }


  public java.math.BigDecimal getOtherPayerTaxAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(181); }

  public void setOtherPayerTaxAmt(java.math.BigDecimal otherPayerTaxAmt) throws SQLException
  { _struct.setAttribute(181, otherPayerTaxAmt); }


  public java.math.BigDecimal getOtherPayerPretaxClaimTotal() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(182); }

  public void setOtherPayerPretaxClaimTotal(java.math.BigDecimal otherPayerPretaxClaimTotal) throws SQLException
  { _struct.setAttribute(182, otherPayerPretaxClaimTotal); }


  public java.math.BigDecimal getReimbursementRate() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(183); }

  public void setReimbursementRate(java.math.BigDecimal reimbursementRate) throws SQLException
  { _struct.setAttribute(183, reimbursementRate); }


  public java.math.BigDecimal getHcpcsPayableAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(184); }

  public void setHcpcsPayableAmt(java.math.BigDecimal hcpcsPayableAmt) throws SQLException
  { _struct.setAttribute(184, hcpcsPayableAmt); }


  public String getRemarkCode1() throws SQLException
  { return (String) _struct.getAttribute(185); }

  public void setRemarkCode1(String remarkCode1) throws SQLException
  { _struct.setAttribute(185, remarkCode1); }


  public String getRemarkCode2() throws SQLException
  { return (String) _struct.getAttribute(186); }

  public void setRemarkCode2(String remarkCode2) throws SQLException
  { _struct.setAttribute(186, remarkCode2); }


  public String getRemarkCode3() throws SQLException
  { return (String) _struct.getAttribute(187); }

  public void setRemarkCode3(String remarkCode3) throws SQLException
  { _struct.setAttribute(187, remarkCode3); }


  public String getRemarkCode4() throws SQLException
  { return (String) _struct.getAttribute(188); }

  public void setRemarkCode4(String remarkCode4) throws SQLException
  { _struct.setAttribute(188, remarkCode4); }


  public String getRemarkCode5() throws SQLException
  { return (String) _struct.getAttribute(189); }

  public void setRemarkCode5(String remarkCode5) throws SQLException
  { _struct.setAttribute(189, remarkCode5); }


  public java.math.BigDecimal getEsrdPmtAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(190); }

  public void setEsrdPmtAmt(java.math.BigDecimal esrdPmtAmt) throws SQLException
  { _struct.setAttribute(190, esrdPmtAmt); }


  public java.math.BigDecimal getNonpayProfnlComponentBilled() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(191); }

  public void setNonpayProfnlComponentBilled(java.math.BigDecimal nonpayProfnlComponentBilled) throws SQLException
  { _struct.setAttribute(191, nonpayProfnlComponentBilled); }


  public java.sql.Timestamp getAdjdDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(192); }

  public void setAdjdDate(java.sql.Timestamp adjdDate) throws SQLException
  { _struct.setAttribute(192, adjdDate); }


  public java.math.BigDecimal getOtherPayerClaimAdjmtInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(193); }

  public void setOtherPayerClaimAdjmtInd(java.math.BigDecimal otherPayerClaimAdjmtInd) throws SQLException
  { _struct.setAttribute(193, otherPayerClaimAdjmtInd); }


  public String getClaimEdiSubmitterId() throws SQLException
  { return (String) _struct.getAttribute(194); }

  public void setClaimEdiSubmitterId(String claimEdiSubmitterId) throws SQLException
  { _struct.setAttribute(194, claimEdiSubmitterId); }


  public String getClaimEdiReceiverId() throws SQLException
  { return (String) _struct.getAttribute(195); }

  public void setClaimEdiReceiverId(String claimEdiReceiverId) throws SQLException
  { _struct.setAttribute(195, claimEdiReceiverId); }


  public String getClaimEdiSubmitterName() throws SQLException
  { return (String) _struct.getAttribute(196); }

  public void setClaimEdiSubmitterName(String claimEdiSubmitterName) throws SQLException
  { _struct.setAttribute(196, claimEdiSubmitterName); }


  public String getClaimEdiReceiverName() throws SQLException
  { return (String) _struct.getAttribute(197); }

  public void setClaimEdiReceiverName(String claimEdiReceiverName) throws SQLException
  { _struct.setAttribute(197, claimEdiReceiverName); }


  public String getClaimEdiReceiverIdQlfr() throws SQLException
  { return (String) _struct.getAttribute(198); }

  public void setClaimEdiReceiverIdQlfr(String claimEdiReceiverIdQlfr) throws SQLException
  { _struct.setAttribute(198, claimEdiReceiverIdQlfr); }


  public String getClaimEdiGrpSubmitterId() throws SQLException
  { return (String) _struct.getAttribute(199); }

  public void setClaimEdiGrpSubmitterId(String claimEdiGrpSubmitterId) throws SQLException
  { _struct.setAttribute(199, claimEdiGrpSubmitterId); }


  public String getClaimEdiProviderCommlId() throws SQLException
  { return (String) _struct.getAttribute(200); }

  public void setClaimEdiProviderCommlId(String claimEdiProviderCommlId) throws SQLException
  { _struct.setAttribute(200, claimEdiProviderCommlId); }


  public String getClaimEdiFmtQlfr() throws SQLException
  { return (String) _struct.getAttribute(201); }

  public void setClaimEdiFmtQlfr(String claimEdiFmtQlfr) throws SQLException
  { _struct.setAttribute(201, claimEdiFmtQlfr); }


  public String getClaimEdiProviderLocCode() throws SQLException
  { return (String) _struct.getAttribute(202); }

  public void setClaimEdiProviderLocCode(String claimEdiProviderLocCode) throws SQLException
  { _struct.setAttribute(202, claimEdiProviderLocCode); }


  public String getClaimBillTypCode() throws SQLException
  { return (String) _struct.getAttribute(203); }

  public void setClaimBillTypCode(String claimBillTypCode) throws SQLException
  { _struct.setAttribute(203, claimBillTypCode); }


  public String getInvNum() throws SQLException
  { return (String) _struct.getAttribute(204); }

  public void setInvNum(String invNum) throws SQLException
  { _struct.setAttribute(204, invNum); }


  public String getCrn() throws SQLException
  { return (String) _struct.getAttribute(205); }

  public void setCrn(String crn) throws SQLException
  { _struct.setAttribute(205, crn); }


  public String getPtDischargeStatusCode() throws SQLException
  { return (String) _struct.getAttribute(206); }

  public void setPtDischargeStatusCode(String ptDischargeStatusCode) throws SQLException
  { _struct.setAttribute(206, ptDischargeStatusCode); }


  public String getPtAdmSrcCode() throws SQLException
  { return (String) _struct.getAttribute(207); }

  public void setPtAdmSrcCode(String ptAdmSrcCode) throws SQLException
  { _struct.setAttribute(207, ptAdmSrcCode); }


  public String getPtAdmTypCode() throws SQLException
  { return (String) _struct.getAttribute(208); }

  public void setPtAdmTypCode(String ptAdmTypCode) throws SQLException
  { _struct.setAttribute(208, ptAdmTypCode); }


  public String getPcpCode() throws SQLException
  { return (String) _struct.getAttribute(209); }

  public void setPcpCode(String pcpCode) throws SQLException
  { _struct.setAttribute(209, pcpCode); }

}
