package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class ClaimLineT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.CLAIM_LINE_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,91,91,12,12,12,2,12,2,2,2,2,2,2,2,12,12,2,2,12,12,2,12,12,12,12,12,12,12,12,2,2,2,12,12,12,12,12,12,2,12,12,2,12,12,12,12,2,2,12,12,12,12,12,12,12,12,2,2,12,2,91,91,91,91,91,91,91,91,91,91,91,91,91,2,2,2,12,12,12,12,12,12,12,12,12,12,12,12,2,12,2,12,2,2,2,2,12,2,2,12,12,2,2,2,2,12,2,2,12,2,12,2,2,12,12,12,2,12,2,2,91,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[146];
  protected static final ClaimLineT _ClaimLineTFactory = new ClaimLineT();

  public static ORADataFactory getORADataFactory()
  { return _ClaimLineTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[146], _sqlType, _factory); }
  public ClaimLineT()
  { _init_struct(true); }
  public ClaimLineT(java.math.BigDecimal claimLineSk, String claimLineId, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal changeVersionId, String beId, java.math.BigDecimal claimSk, java.math.BigDecimal renderProviderSk, java.math.BigDecimal purchSvcProviderSk, java.math.BigDecimal refingProviderSk, java.math.BigDecimal ordngProviderSk, java.math.BigDecimal supvProviderSk, java.math.BigDecimal labSvcProviderSk, String adjmtTypCode, String ndc, java.math.BigDecimal drugUnitPrice, java.math.BigDecimal nationalDrugUnitCount, String nationalDrugUom, String rxNum, java.math.BigDecimal placeOfSvcCodeSk, String procMdfrCode1, String procMdfrCode2, String procMdfrCode3, String procMdfrCode4, String dxCodePointer1, String dxCodePointer2, String dxCodePointer3, String dxCodePointer4, java.math.BigDecimal emergInd, java.math.BigDecimal familyPlanningInd, java.math.BigDecimal epsdtInd, String copayStatusCode, String procCode, String billingCode, String attRprtTypCode, String attTrnsmsnCode, String amblTransportReasonCode, java.math.BigDecimal transportDistance, String roundTripPurposeDesc, String stretcherPurposeDesc, java.math.BigDecimal xrayAvailInd, String certTypCode, String dmeDur, String reprLineItemRefNum, String adjReprLineItemRefNum, java.math.BigDecimal authSk, java.math.BigDecimal rfrlSk, String mmgCertNum, String cliaNum, String refingCliaNum, String immunBatchNum, String apgNum, String oxygenFlowRate, String universalProductNum, String lineNoteText, java.math.BigDecimal numOfHomeHlthVisits, java.math.BigDecimal homeHlthVisitsFreqCount, String durOfHomeHlthVisitsUom, java.math.BigDecimal durOfHomeHlthVisits, java.sql.Timestamp svcDate, java.sql.Timestamp certRevisionDate, java.sql.Timestamp begTherapyDate, java.sql.Timestamp lastCertDate, java.sql.Timestamp lastSeenDate, java.sql.Timestamp shippedDate, java.sql.Timestamp onsetDate, java.sql.Timestamp lastXrayDate, java.sql.Timestamp acuteManifestationDate, java.sql.Timestamp initialTreatDate, java.sql.Timestamp similarIllnessDate, java.sql.Timestamp testPerfDate, java.sql.Timestamp sao2TestDate, java.math.BigDecimal treatPrdCount, java.math.BigDecimal arterialBloodGasQty, java.math.BigDecimal sao2Qty, String oxygenTestCondCode, String oxygenTestFindingsCode, String amblCertCondCode1, String amblCertCondCode2, String amblCertCondCode3, String amblCertCondCode4, String amblCertCondCode5, String dmercCondCode1, String dmercCondCode2, String dmercCondCode3, String dmercCondCode4, String dmercCondCode5, java.math.BigDecimal lmn, String lmnUom, java.math.BigDecimal svcUnitCount, String svcUnitCountUom, java.math.BigDecimal dmeRentalPrice, java.math.BigDecimal dmePurchasePrice, java.math.BigDecimal termsDiscountPercentage, java.math.BigDecimal lineItemChargeAmt, String contrTypCode, java.math.BigDecimal contrAmt, java.math.BigDecimal contrPercentage, String contrCode, String contrVersionId, java.math.BigDecimal salesTaxAmt, java.math.BigDecimal aprvdAmt, java.math.BigDecimal postageClaimedAmt, java.math.BigDecimal purchSvcChargeAmt, String pricingMethodologyCode, java.math.BigDecimal reprAlwdAmt, java.math.BigDecimal reprSavingAmt, String repricingOrgId, java.math.BigDecimal repricingPerDiemAmt, String reprAprvdApgCode, java.math.BigDecimal reprAprvdApgAmt, java.math.BigDecimal reprAprvdSvcUnitCount, String rejectReasonCode, String policyCompCode, String excpCode, java.math.BigDecimal svcLinePaidAmt, String adjmtReasonCode, java.math.BigDecimal adjmtAmt, java.math.BigDecimal adjmtQty, java.sql.Timestamp adjdDate, String payerId, String revCode, String icdDxCodeRevisionQlfr, String icdDxCodePrmy, String icdDxCode2, String icdDxCode3, String icdDxCode4, String icdDxCode5, String icdDxCode6, String icdDxCode7, String icdDxCode8, String icdDxCode9, String icdDxCode10, String icdDxCode11, String icdDxCode12, String svcName, String invNum, String invDetId, String crn, String ptDischargeStatusCode, String ptAdmSrcCode, String ptAdmTypCode, java.math.BigDecimal claimLineSvcLineItemNum) throws SQLException
  { _init_struct(true);
    setClaimLineSk(claimLineSk);
    setClaimLineId(claimLineId);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonMemo(changeReasonMemo);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setClaimSk(claimSk);
    setRenderProviderSk(renderProviderSk);
    setPurchSvcProviderSk(purchSvcProviderSk);
    setRefingProviderSk(refingProviderSk);
    setOrdngProviderSk(ordngProviderSk);
    setSupvProviderSk(supvProviderSk);
    setLabSvcProviderSk(labSvcProviderSk);
    setAdjmtTypCode(adjmtTypCode);
    setNdc(ndc);
    setDrugUnitPrice(drugUnitPrice);
    setNationalDrugUnitCount(nationalDrugUnitCount);
    setNationalDrugUom(nationalDrugUom);
    setRxNum(rxNum);
    setPlaceOfSvcCodeSk(placeOfSvcCodeSk);
    setProcMdfrCode1(procMdfrCode1);
    setProcMdfrCode2(procMdfrCode2);
    setProcMdfrCode3(procMdfrCode3);
    setProcMdfrCode4(procMdfrCode4);
    setDxCodePointer1(dxCodePointer1);
    setDxCodePointer2(dxCodePointer2);
    setDxCodePointer3(dxCodePointer3);
    setDxCodePointer4(dxCodePointer4);
    setEmergInd(emergInd);
    setFamilyPlanningInd(familyPlanningInd);
    setEpsdtInd(epsdtInd);
    setCopayStatusCode(copayStatusCode);
    setProcCode(procCode);
    setBillingCode(billingCode);
    setAttRprtTypCode(attRprtTypCode);
    setAttTrnsmsnCode(attTrnsmsnCode);
    setAmblTransportReasonCode(amblTransportReasonCode);
    setTransportDistance(transportDistance);
    setRoundTripPurposeDesc(roundTripPurposeDesc);
    setStretcherPurposeDesc(stretcherPurposeDesc);
    setXrayAvailInd(xrayAvailInd);
    setCertTypCode(certTypCode);
    setDmeDur(dmeDur);
    setReprLineItemRefNum(reprLineItemRefNum);
    setAdjReprLineItemRefNum(adjReprLineItemRefNum);
    setAuthSk(authSk);
    setRfrlSk(rfrlSk);
    setMmgCertNum(mmgCertNum);
    setCliaNum(cliaNum);
    setRefingCliaNum(refingCliaNum);
    setImmunBatchNum(immunBatchNum);
    setApgNum(apgNum);
    setOxygenFlowRate(oxygenFlowRate);
    setUniversalProductNum(universalProductNum);
    setLineNoteText(lineNoteText);
    setNumOfHomeHlthVisits(numOfHomeHlthVisits);
    setHomeHlthVisitsFreqCount(homeHlthVisitsFreqCount);
    setDurOfHomeHlthVisitsUom(durOfHomeHlthVisitsUom);
    setDurOfHomeHlthVisits(durOfHomeHlthVisits);
    setSvcDate(svcDate);
    setCertRevisionDate(certRevisionDate);
    setBegTherapyDate(begTherapyDate);
    setLastCertDate(lastCertDate);
    setLastSeenDate(lastSeenDate);
    setShippedDate(shippedDate);
    setOnsetDate(onsetDate);
    setLastXrayDate(lastXrayDate);
    setAcuteManifestationDate(acuteManifestationDate);
    setInitialTreatDate(initialTreatDate);
    setSimilarIllnessDate(similarIllnessDate);
    setTestPerfDate(testPerfDate);
    setSao2TestDate(sao2TestDate);
    setTreatPrdCount(treatPrdCount);
    setArterialBloodGasQty(arterialBloodGasQty);
    setSao2Qty(sao2Qty);
    setOxygenTestCondCode(oxygenTestCondCode);
    setOxygenTestFindingsCode(oxygenTestFindingsCode);
    setAmblCertCondCode1(amblCertCondCode1);
    setAmblCertCondCode2(amblCertCondCode2);
    setAmblCertCondCode3(amblCertCondCode3);
    setAmblCertCondCode4(amblCertCondCode4);
    setAmblCertCondCode5(amblCertCondCode5);
    setDmercCondCode1(dmercCondCode1);
    setDmercCondCode2(dmercCondCode2);
    setDmercCondCode3(dmercCondCode3);
    setDmercCondCode4(dmercCondCode4);
    setDmercCondCode5(dmercCondCode5);
    setLmn(lmn);
    setLmnUom(lmnUom);
    setSvcUnitCount(svcUnitCount);
    setSvcUnitCountUom(svcUnitCountUom);
    setDmeRentalPrice(dmeRentalPrice);
    setDmePurchasePrice(dmePurchasePrice);
    setTermsDiscountPercentage(termsDiscountPercentage);
    setLineItemChargeAmt(lineItemChargeAmt);
    setContrTypCode(contrTypCode);
    setContrAmt(contrAmt);
    setContrPercentage(contrPercentage);
    setContrCode(contrCode);
    setContrVersionId(contrVersionId);
    setSalesTaxAmt(salesTaxAmt);
    setAprvdAmt(aprvdAmt);
    setPostageClaimedAmt(postageClaimedAmt);
    setPurchSvcChargeAmt(purchSvcChargeAmt);
    setPricingMethodologyCode(pricingMethodologyCode);
    setReprAlwdAmt(reprAlwdAmt);
    setReprSavingAmt(reprSavingAmt);
    setRepricingOrgId(repricingOrgId);
    setRepricingPerDiemAmt(repricingPerDiemAmt);
    setReprAprvdApgCode(reprAprvdApgCode);
    setReprAprvdApgAmt(reprAprvdApgAmt);
    setReprAprvdSvcUnitCount(reprAprvdSvcUnitCount);
    setRejectReasonCode(rejectReasonCode);
    setPolicyCompCode(policyCompCode);
    setExcpCode(excpCode);
    setSvcLinePaidAmt(svcLinePaidAmt);
    setAdjmtReasonCode(adjmtReasonCode);
    setAdjmtAmt(adjmtAmt);
    setAdjmtQty(adjmtQty);
    setAdjdDate(adjdDate);
    setPayerId(payerId);
    setRevCode(revCode);
    setIcdDxCodeRevisionQlfr(icdDxCodeRevisionQlfr);
    setIcdDxCodePrmy(icdDxCodePrmy);
    setIcdDxCode2(icdDxCode2);
    setIcdDxCode3(icdDxCode3);
    setIcdDxCode4(icdDxCode4);
    setIcdDxCode5(icdDxCode5);
    setIcdDxCode6(icdDxCode6);
    setIcdDxCode7(icdDxCode7);
    setIcdDxCode8(icdDxCode8);
    setIcdDxCode9(icdDxCode9);
    setIcdDxCode10(icdDxCode10);
    setIcdDxCode11(icdDxCode11);
    setIcdDxCode12(icdDxCode12);
    setSvcName(svcName);
    setInvNum(invNum);
    setInvDetId(invDetId);
    setCrn(crn);
    setPtDischargeStatusCode(ptDischargeStatusCode);
    setPtAdmSrcCode(ptAdmSrcCode);
    setPtAdmTypCode(ptAdmTypCode);
    setClaimLineSvcLineItemNum(claimLineSvcLineItemNum);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(ClaimLineT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new ClaimLineT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getClaimLineSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setClaimLineSk(java.math.BigDecimal claimLineSk) throws SQLException
  { _struct.setAttribute(0, claimLineSk); }


  public String getClaimLineId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setClaimLineId(String claimLineId) throws SQLException
  { _struct.setAttribute(1, claimLineId); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(2, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(3); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(3, recUpdateTmstp); }


  public String getRecCreatedBy() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setRecCreatedBy(String recCreatedBy) throws SQLException
  { _struct.setAttribute(4, recCreatedBy); }


  public String getRecUpdatedBy() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setRecUpdatedBy(String recUpdatedBy) throws SQLException
  { _struct.setAttribute(5, recUpdatedBy); }


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


  public java.math.BigDecimal getClaimSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(9); }

  public void setClaimSk(java.math.BigDecimal claimSk) throws SQLException
  { _struct.setAttribute(9, claimSk); }


  public java.math.BigDecimal getRenderProviderSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(10); }

  public void setRenderProviderSk(java.math.BigDecimal renderProviderSk) throws SQLException
  { _struct.setAttribute(10, renderProviderSk); }


  public java.math.BigDecimal getPurchSvcProviderSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(11); }

  public void setPurchSvcProviderSk(java.math.BigDecimal purchSvcProviderSk) throws SQLException
  { _struct.setAttribute(11, purchSvcProviderSk); }


  public java.math.BigDecimal getRefingProviderSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(12); }

  public void setRefingProviderSk(java.math.BigDecimal refingProviderSk) throws SQLException
  { _struct.setAttribute(12, refingProviderSk); }


  public java.math.BigDecimal getOrdngProviderSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(13); }

  public void setOrdngProviderSk(java.math.BigDecimal ordngProviderSk) throws SQLException
  { _struct.setAttribute(13, ordngProviderSk); }


  public java.math.BigDecimal getSupvProviderSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(14); }

  public void setSupvProviderSk(java.math.BigDecimal supvProviderSk) throws SQLException
  { _struct.setAttribute(14, supvProviderSk); }


  public java.math.BigDecimal getLabSvcProviderSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(15); }

  public void setLabSvcProviderSk(java.math.BigDecimal labSvcProviderSk) throws SQLException
  { _struct.setAttribute(15, labSvcProviderSk); }


  public String getAdjmtTypCode() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setAdjmtTypCode(String adjmtTypCode) throws SQLException
  { _struct.setAttribute(16, adjmtTypCode); }


  public String getNdc() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setNdc(String ndc) throws SQLException
  { _struct.setAttribute(17, ndc); }


  public java.math.BigDecimal getDrugUnitPrice() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(18); }

  public void setDrugUnitPrice(java.math.BigDecimal drugUnitPrice) throws SQLException
  { _struct.setAttribute(18, drugUnitPrice); }


  public java.math.BigDecimal getNationalDrugUnitCount() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(19); }

  public void setNationalDrugUnitCount(java.math.BigDecimal nationalDrugUnitCount) throws SQLException
  { _struct.setAttribute(19, nationalDrugUnitCount); }


  public String getNationalDrugUom() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setNationalDrugUom(String nationalDrugUom) throws SQLException
  { _struct.setAttribute(20, nationalDrugUom); }


  public String getRxNum() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setRxNum(String rxNum) throws SQLException
  { _struct.setAttribute(21, rxNum); }


  public java.math.BigDecimal getPlaceOfSvcCodeSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(22); }

  public void setPlaceOfSvcCodeSk(java.math.BigDecimal placeOfSvcCodeSk) throws SQLException
  { _struct.setAttribute(22, placeOfSvcCodeSk); }


  public String getProcMdfrCode1() throws SQLException
  { return (String) _struct.getAttribute(23); }

  public void setProcMdfrCode1(String procMdfrCode1) throws SQLException
  { _struct.setAttribute(23, procMdfrCode1); }


  public String getProcMdfrCode2() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setProcMdfrCode2(String procMdfrCode2) throws SQLException
  { _struct.setAttribute(24, procMdfrCode2); }


  public String getProcMdfrCode3() throws SQLException
  { return (String) _struct.getAttribute(25); }

  public void setProcMdfrCode3(String procMdfrCode3) throws SQLException
  { _struct.setAttribute(25, procMdfrCode3); }


  public String getProcMdfrCode4() throws SQLException
  { return (String) _struct.getAttribute(26); }

  public void setProcMdfrCode4(String procMdfrCode4) throws SQLException
  { _struct.setAttribute(26, procMdfrCode4); }


  public String getDxCodePointer1() throws SQLException
  { return (String) _struct.getAttribute(27); }

  public void setDxCodePointer1(String dxCodePointer1) throws SQLException
  { _struct.setAttribute(27, dxCodePointer1); }


  public String getDxCodePointer2() throws SQLException
  { return (String) _struct.getAttribute(28); }

  public void setDxCodePointer2(String dxCodePointer2) throws SQLException
  { _struct.setAttribute(28, dxCodePointer2); }


  public String getDxCodePointer3() throws SQLException
  { return (String) _struct.getAttribute(29); }

  public void setDxCodePointer3(String dxCodePointer3) throws SQLException
  { _struct.setAttribute(29, dxCodePointer3); }


  public String getDxCodePointer4() throws SQLException
  { return (String) _struct.getAttribute(30); }

  public void setDxCodePointer4(String dxCodePointer4) throws SQLException
  { _struct.setAttribute(30, dxCodePointer4); }


  public java.math.BigDecimal getEmergInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(31); }

  public void setEmergInd(java.math.BigDecimal emergInd) throws SQLException
  { _struct.setAttribute(31, emergInd); }


  public java.math.BigDecimal getFamilyPlanningInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(32); }

  public void setFamilyPlanningInd(java.math.BigDecimal familyPlanningInd) throws SQLException
  { _struct.setAttribute(32, familyPlanningInd); }


  public java.math.BigDecimal getEpsdtInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(33); }

  public void setEpsdtInd(java.math.BigDecimal epsdtInd) throws SQLException
  { _struct.setAttribute(33, epsdtInd); }


  public String getCopayStatusCode() throws SQLException
  { return (String) _struct.getAttribute(34); }

  public void setCopayStatusCode(String copayStatusCode) throws SQLException
  { _struct.setAttribute(34, copayStatusCode); }


  public String getProcCode() throws SQLException
  { return (String) _struct.getAttribute(35); }

  public void setProcCode(String procCode) throws SQLException
  { _struct.setAttribute(35, procCode); }


  public String getBillingCode() throws SQLException
  { return (String) _struct.getAttribute(36); }

  public void setBillingCode(String billingCode) throws SQLException
  { _struct.setAttribute(36, billingCode); }


  public String getAttRprtTypCode() throws SQLException
  { return (String) _struct.getAttribute(37); }

  public void setAttRprtTypCode(String attRprtTypCode) throws SQLException
  { _struct.setAttribute(37, attRprtTypCode); }


  public String getAttTrnsmsnCode() throws SQLException
  { return (String) _struct.getAttribute(38); }

  public void setAttTrnsmsnCode(String attTrnsmsnCode) throws SQLException
  { _struct.setAttribute(38, attTrnsmsnCode); }


  public String getAmblTransportReasonCode() throws SQLException
  { return (String) _struct.getAttribute(39); }

  public void setAmblTransportReasonCode(String amblTransportReasonCode) throws SQLException
  { _struct.setAttribute(39, amblTransportReasonCode); }


  public java.math.BigDecimal getTransportDistance() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(40); }

  public void setTransportDistance(java.math.BigDecimal transportDistance) throws SQLException
  { _struct.setAttribute(40, transportDistance); }


  public String getRoundTripPurposeDesc() throws SQLException
  { return (String) _struct.getAttribute(41); }

  public void setRoundTripPurposeDesc(String roundTripPurposeDesc) throws SQLException
  { _struct.setAttribute(41, roundTripPurposeDesc); }


  public String getStretcherPurposeDesc() throws SQLException
  { return (String) _struct.getAttribute(42); }

  public void setStretcherPurposeDesc(String stretcherPurposeDesc) throws SQLException
  { _struct.setAttribute(42, stretcherPurposeDesc); }


  public java.math.BigDecimal getXrayAvailInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(43); }

  public void setXrayAvailInd(java.math.BigDecimal xrayAvailInd) throws SQLException
  { _struct.setAttribute(43, xrayAvailInd); }


  public String getCertTypCode() throws SQLException
  { return (String) _struct.getAttribute(44); }

  public void setCertTypCode(String certTypCode) throws SQLException
  { _struct.setAttribute(44, certTypCode); }


  public String getDmeDur() throws SQLException
  { return (String) _struct.getAttribute(45); }

  public void setDmeDur(String dmeDur) throws SQLException
  { _struct.setAttribute(45, dmeDur); }


  public String getReprLineItemRefNum() throws SQLException
  { return (String) _struct.getAttribute(46); }

  public void setReprLineItemRefNum(String reprLineItemRefNum) throws SQLException
  { _struct.setAttribute(46, reprLineItemRefNum); }


  public String getAdjReprLineItemRefNum() throws SQLException
  { return (String) _struct.getAttribute(47); }

  public void setAdjReprLineItemRefNum(String adjReprLineItemRefNum) throws SQLException
  { _struct.setAttribute(47, adjReprLineItemRefNum); }


  public java.math.BigDecimal getAuthSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(48); }

  public void setAuthSk(java.math.BigDecimal authSk) throws SQLException
  { _struct.setAttribute(48, authSk); }


  public java.math.BigDecimal getRfrlSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(49); }

  public void setRfrlSk(java.math.BigDecimal rfrlSk) throws SQLException
  { _struct.setAttribute(49, rfrlSk); }


  public String getMmgCertNum() throws SQLException
  { return (String) _struct.getAttribute(50); }

  public void setMmgCertNum(String mmgCertNum) throws SQLException
  { _struct.setAttribute(50, mmgCertNum); }


  public String getCliaNum() throws SQLException
  { return (String) _struct.getAttribute(51); }

  public void setCliaNum(String cliaNum) throws SQLException
  { _struct.setAttribute(51, cliaNum); }


  public String getRefingCliaNum() throws SQLException
  { return (String) _struct.getAttribute(52); }

  public void setRefingCliaNum(String refingCliaNum) throws SQLException
  { _struct.setAttribute(52, refingCliaNum); }


  public String getImmunBatchNum() throws SQLException
  { return (String) _struct.getAttribute(53); }

  public void setImmunBatchNum(String immunBatchNum) throws SQLException
  { _struct.setAttribute(53, immunBatchNum); }


  public String getApgNum() throws SQLException
  { return (String) _struct.getAttribute(54); }

  public void setApgNum(String apgNum) throws SQLException
  { _struct.setAttribute(54, apgNum); }


  public String getOxygenFlowRate() throws SQLException
  { return (String) _struct.getAttribute(55); }

  public void setOxygenFlowRate(String oxygenFlowRate) throws SQLException
  { _struct.setAttribute(55, oxygenFlowRate); }


  public String getUniversalProductNum() throws SQLException
  { return (String) _struct.getAttribute(56); }

  public void setUniversalProductNum(String universalProductNum) throws SQLException
  { _struct.setAttribute(56, universalProductNum); }


  public String getLineNoteText() throws SQLException
  { return (String) _struct.getAttribute(57); }

  public void setLineNoteText(String lineNoteText) throws SQLException
  { _struct.setAttribute(57, lineNoteText); }


  public java.math.BigDecimal getNumOfHomeHlthVisits() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(58); }

  public void setNumOfHomeHlthVisits(java.math.BigDecimal numOfHomeHlthVisits) throws SQLException
  { _struct.setAttribute(58, numOfHomeHlthVisits); }


  public java.math.BigDecimal getHomeHlthVisitsFreqCount() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(59); }

  public void setHomeHlthVisitsFreqCount(java.math.BigDecimal homeHlthVisitsFreqCount) throws SQLException
  { _struct.setAttribute(59, homeHlthVisitsFreqCount); }


  public String getDurOfHomeHlthVisitsUom() throws SQLException
  { return (String) _struct.getAttribute(60); }

  public void setDurOfHomeHlthVisitsUom(String durOfHomeHlthVisitsUom) throws SQLException
  { _struct.setAttribute(60, durOfHomeHlthVisitsUom); }


  public java.math.BigDecimal getDurOfHomeHlthVisits() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(61); }

  public void setDurOfHomeHlthVisits(java.math.BigDecimal durOfHomeHlthVisits) throws SQLException
  { _struct.setAttribute(61, durOfHomeHlthVisits); }


  public java.sql.Timestamp getSvcDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(62); }

  public void setSvcDate(java.sql.Timestamp svcDate) throws SQLException
  { _struct.setAttribute(62, svcDate); }


  public java.sql.Timestamp getCertRevisionDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(63); }

  public void setCertRevisionDate(java.sql.Timestamp certRevisionDate) throws SQLException
  { _struct.setAttribute(63, certRevisionDate); }


  public java.sql.Timestamp getBegTherapyDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(64); }

  public void setBegTherapyDate(java.sql.Timestamp begTherapyDate) throws SQLException
  { _struct.setAttribute(64, begTherapyDate); }


  public java.sql.Timestamp getLastCertDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(65); }

  public void setLastCertDate(java.sql.Timestamp lastCertDate) throws SQLException
  { _struct.setAttribute(65, lastCertDate); }


  public java.sql.Timestamp getLastSeenDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(66); }

  public void setLastSeenDate(java.sql.Timestamp lastSeenDate) throws SQLException
  { _struct.setAttribute(66, lastSeenDate); }


  public java.sql.Timestamp getShippedDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(67); }

  public void setShippedDate(java.sql.Timestamp shippedDate) throws SQLException
  { _struct.setAttribute(67, shippedDate); }


  public java.sql.Timestamp getOnsetDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(68); }

  public void setOnsetDate(java.sql.Timestamp onsetDate) throws SQLException
  { _struct.setAttribute(68, onsetDate); }


  public java.sql.Timestamp getLastXrayDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(69); }

  public void setLastXrayDate(java.sql.Timestamp lastXrayDate) throws SQLException
  { _struct.setAttribute(69, lastXrayDate); }


  public java.sql.Timestamp getAcuteManifestationDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(70); }

  public void setAcuteManifestationDate(java.sql.Timestamp acuteManifestationDate) throws SQLException
  { _struct.setAttribute(70, acuteManifestationDate); }


  public java.sql.Timestamp getInitialTreatDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(71); }

  public void setInitialTreatDate(java.sql.Timestamp initialTreatDate) throws SQLException
  { _struct.setAttribute(71, initialTreatDate); }


  public java.sql.Timestamp getSimilarIllnessDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(72); }

  public void setSimilarIllnessDate(java.sql.Timestamp similarIllnessDate) throws SQLException
  { _struct.setAttribute(72, similarIllnessDate); }


  public java.sql.Timestamp getTestPerfDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(73); }

  public void setTestPerfDate(java.sql.Timestamp testPerfDate) throws SQLException
  { _struct.setAttribute(73, testPerfDate); }


  public java.sql.Timestamp getSao2TestDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(74); }

  public void setSao2TestDate(java.sql.Timestamp sao2TestDate) throws SQLException
  { _struct.setAttribute(74, sao2TestDate); }


  public java.math.BigDecimal getTreatPrdCount() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(75); }

  public void setTreatPrdCount(java.math.BigDecimal treatPrdCount) throws SQLException
  { _struct.setAttribute(75, treatPrdCount); }


  public java.math.BigDecimal getArterialBloodGasQty() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(76); }

  public void setArterialBloodGasQty(java.math.BigDecimal arterialBloodGasQty) throws SQLException
  { _struct.setAttribute(76, arterialBloodGasQty); }


  public java.math.BigDecimal getSao2Qty() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(77); }

  public void setSao2Qty(java.math.BigDecimal sao2Qty) throws SQLException
  { _struct.setAttribute(77, sao2Qty); }


  public String getOxygenTestCondCode() throws SQLException
  { return (String) _struct.getAttribute(78); }

  public void setOxygenTestCondCode(String oxygenTestCondCode) throws SQLException
  { _struct.setAttribute(78, oxygenTestCondCode); }


  public String getOxygenTestFindingsCode() throws SQLException
  { return (String) _struct.getAttribute(79); }

  public void setOxygenTestFindingsCode(String oxygenTestFindingsCode) throws SQLException
  { _struct.setAttribute(79, oxygenTestFindingsCode); }


  public String getAmblCertCondCode1() throws SQLException
  { return (String) _struct.getAttribute(80); }

  public void setAmblCertCondCode1(String amblCertCondCode1) throws SQLException
  { _struct.setAttribute(80, amblCertCondCode1); }


  public String getAmblCertCondCode2() throws SQLException
  { return (String) _struct.getAttribute(81); }

  public void setAmblCertCondCode2(String amblCertCondCode2) throws SQLException
  { _struct.setAttribute(81, amblCertCondCode2); }


  public String getAmblCertCondCode3() throws SQLException
  { return (String) _struct.getAttribute(82); }

  public void setAmblCertCondCode3(String amblCertCondCode3) throws SQLException
  { _struct.setAttribute(82, amblCertCondCode3); }


  public String getAmblCertCondCode4() throws SQLException
  { return (String) _struct.getAttribute(83); }

  public void setAmblCertCondCode4(String amblCertCondCode4) throws SQLException
  { _struct.setAttribute(83, amblCertCondCode4); }


  public String getAmblCertCondCode5() throws SQLException
  { return (String) _struct.getAttribute(84); }

  public void setAmblCertCondCode5(String amblCertCondCode5) throws SQLException
  { _struct.setAttribute(84, amblCertCondCode5); }


  public String getDmercCondCode1() throws SQLException
  { return (String) _struct.getAttribute(85); }

  public void setDmercCondCode1(String dmercCondCode1) throws SQLException
  { _struct.setAttribute(85, dmercCondCode1); }


  public String getDmercCondCode2() throws SQLException
  { return (String) _struct.getAttribute(86); }

  public void setDmercCondCode2(String dmercCondCode2) throws SQLException
  { _struct.setAttribute(86, dmercCondCode2); }


  public String getDmercCondCode3() throws SQLException
  { return (String) _struct.getAttribute(87); }

  public void setDmercCondCode3(String dmercCondCode3) throws SQLException
  { _struct.setAttribute(87, dmercCondCode3); }


  public String getDmercCondCode4() throws SQLException
  { return (String) _struct.getAttribute(88); }

  public void setDmercCondCode4(String dmercCondCode4) throws SQLException
  { _struct.setAttribute(88, dmercCondCode4); }


  public String getDmercCondCode5() throws SQLException
  { return (String) _struct.getAttribute(89); }

  public void setDmercCondCode5(String dmercCondCode5) throws SQLException
  { _struct.setAttribute(89, dmercCondCode5); }


  public java.math.BigDecimal getLmn() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(90); }

  public void setLmn(java.math.BigDecimal lmn) throws SQLException
  { _struct.setAttribute(90, lmn); }


  public String getLmnUom() throws SQLException
  { return (String) _struct.getAttribute(91); }

  public void setLmnUom(String lmnUom) throws SQLException
  { _struct.setAttribute(91, lmnUom); }


  public java.math.BigDecimal getSvcUnitCount() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(92); }

  public void setSvcUnitCount(java.math.BigDecimal svcUnitCount) throws SQLException
  { _struct.setAttribute(92, svcUnitCount); }


  public String getSvcUnitCountUom() throws SQLException
  { return (String) _struct.getAttribute(93); }

  public void setSvcUnitCountUom(String svcUnitCountUom) throws SQLException
  { _struct.setAttribute(93, svcUnitCountUom); }


  public java.math.BigDecimal getDmeRentalPrice() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(94); }

  public void setDmeRentalPrice(java.math.BigDecimal dmeRentalPrice) throws SQLException
  { _struct.setAttribute(94, dmeRentalPrice); }


  public java.math.BigDecimal getDmePurchasePrice() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(95); }

  public void setDmePurchasePrice(java.math.BigDecimal dmePurchasePrice) throws SQLException
  { _struct.setAttribute(95, dmePurchasePrice); }


  public java.math.BigDecimal getTermsDiscountPercentage() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(96); }

  public void setTermsDiscountPercentage(java.math.BigDecimal termsDiscountPercentage) throws SQLException
  { _struct.setAttribute(96, termsDiscountPercentage); }


  public java.math.BigDecimal getLineItemChargeAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(97); }

  public void setLineItemChargeAmt(java.math.BigDecimal lineItemChargeAmt) throws SQLException
  { _struct.setAttribute(97, lineItemChargeAmt); }


  public String getContrTypCode() throws SQLException
  { return (String) _struct.getAttribute(98); }

  public void setContrTypCode(String contrTypCode) throws SQLException
  { _struct.setAttribute(98, contrTypCode); }


  public java.math.BigDecimal getContrAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(99); }

  public void setContrAmt(java.math.BigDecimal contrAmt) throws SQLException
  { _struct.setAttribute(99, contrAmt); }


  public java.math.BigDecimal getContrPercentage() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(100); }

  public void setContrPercentage(java.math.BigDecimal contrPercentage) throws SQLException
  { _struct.setAttribute(100, contrPercentage); }


  public String getContrCode() throws SQLException
  { return (String) _struct.getAttribute(101); }

  public void setContrCode(String contrCode) throws SQLException
  { _struct.setAttribute(101, contrCode); }


  public String getContrVersionId() throws SQLException
  { return (String) _struct.getAttribute(102); }

  public void setContrVersionId(String contrVersionId) throws SQLException
  { _struct.setAttribute(102, contrVersionId); }


  public java.math.BigDecimal getSalesTaxAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(103); }

  public void setSalesTaxAmt(java.math.BigDecimal salesTaxAmt) throws SQLException
  { _struct.setAttribute(103, salesTaxAmt); }


  public java.math.BigDecimal getAprvdAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(104); }

  public void setAprvdAmt(java.math.BigDecimal aprvdAmt) throws SQLException
  { _struct.setAttribute(104, aprvdAmt); }


  public java.math.BigDecimal getPostageClaimedAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(105); }

  public void setPostageClaimedAmt(java.math.BigDecimal postageClaimedAmt) throws SQLException
  { _struct.setAttribute(105, postageClaimedAmt); }


  public java.math.BigDecimal getPurchSvcChargeAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(106); }

  public void setPurchSvcChargeAmt(java.math.BigDecimal purchSvcChargeAmt) throws SQLException
  { _struct.setAttribute(106, purchSvcChargeAmt); }


  public String getPricingMethodologyCode() throws SQLException
  { return (String) _struct.getAttribute(107); }

  public void setPricingMethodologyCode(String pricingMethodologyCode) throws SQLException
  { _struct.setAttribute(107, pricingMethodologyCode); }


  public java.math.BigDecimal getReprAlwdAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(108); }

  public void setReprAlwdAmt(java.math.BigDecimal reprAlwdAmt) throws SQLException
  { _struct.setAttribute(108, reprAlwdAmt); }


  public java.math.BigDecimal getReprSavingAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(109); }

  public void setReprSavingAmt(java.math.BigDecimal reprSavingAmt) throws SQLException
  { _struct.setAttribute(109, reprSavingAmt); }


  public String getRepricingOrgId() throws SQLException
  { return (String) _struct.getAttribute(110); }

  public void setRepricingOrgId(String repricingOrgId) throws SQLException
  { _struct.setAttribute(110, repricingOrgId); }


  public java.math.BigDecimal getRepricingPerDiemAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(111); }

  public void setRepricingPerDiemAmt(java.math.BigDecimal repricingPerDiemAmt) throws SQLException
  { _struct.setAttribute(111, repricingPerDiemAmt); }


  public String getReprAprvdApgCode() throws SQLException
  { return (String) _struct.getAttribute(112); }

  public void setReprAprvdApgCode(String reprAprvdApgCode) throws SQLException
  { _struct.setAttribute(112, reprAprvdApgCode); }


  public java.math.BigDecimal getReprAprvdApgAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(113); }

  public void setReprAprvdApgAmt(java.math.BigDecimal reprAprvdApgAmt) throws SQLException
  { _struct.setAttribute(113, reprAprvdApgAmt); }


  public java.math.BigDecimal getReprAprvdSvcUnitCount() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(114); }

  public void setReprAprvdSvcUnitCount(java.math.BigDecimal reprAprvdSvcUnitCount) throws SQLException
  { _struct.setAttribute(114, reprAprvdSvcUnitCount); }


  public String getRejectReasonCode() throws SQLException
  { return (String) _struct.getAttribute(115); }

  public void setRejectReasonCode(String rejectReasonCode) throws SQLException
  { _struct.setAttribute(115, rejectReasonCode); }


  public String getPolicyCompCode() throws SQLException
  { return (String) _struct.getAttribute(116); }

  public void setPolicyCompCode(String policyCompCode) throws SQLException
  { _struct.setAttribute(116, policyCompCode); }


  public String getExcpCode() throws SQLException
  { return (String) _struct.getAttribute(117); }

  public void setExcpCode(String excpCode) throws SQLException
  { _struct.setAttribute(117, excpCode); }


  public java.math.BigDecimal getSvcLinePaidAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(118); }

  public void setSvcLinePaidAmt(java.math.BigDecimal svcLinePaidAmt) throws SQLException
  { _struct.setAttribute(118, svcLinePaidAmt); }


  public String getAdjmtReasonCode() throws SQLException
  { return (String) _struct.getAttribute(119); }

  public void setAdjmtReasonCode(String adjmtReasonCode) throws SQLException
  { _struct.setAttribute(119, adjmtReasonCode); }


  public java.math.BigDecimal getAdjmtAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(120); }

  public void setAdjmtAmt(java.math.BigDecimal adjmtAmt) throws SQLException
  { _struct.setAttribute(120, adjmtAmt); }


  public java.math.BigDecimal getAdjmtQty() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(121); }

  public void setAdjmtQty(java.math.BigDecimal adjmtQty) throws SQLException
  { _struct.setAttribute(121, adjmtQty); }


  public java.sql.Timestamp getAdjdDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(122); }

  public void setAdjdDate(java.sql.Timestamp adjdDate) throws SQLException
  { _struct.setAttribute(122, adjdDate); }


  public String getPayerId() throws SQLException
  { return (String) _struct.getAttribute(123); }

  public void setPayerId(String payerId) throws SQLException
  { _struct.setAttribute(123, payerId); }


  public String getRevCode() throws SQLException
  { return (String) _struct.getAttribute(124); }

  public void setRevCode(String revCode) throws SQLException
  { _struct.setAttribute(124, revCode); }


  public String getIcdDxCodeRevisionQlfr() throws SQLException
  { return (String) _struct.getAttribute(125); }

  public void setIcdDxCodeRevisionQlfr(String icdDxCodeRevisionQlfr) throws SQLException
  { _struct.setAttribute(125, icdDxCodeRevisionQlfr); }


  public String getIcdDxCodePrmy() throws SQLException
  { return (String) _struct.getAttribute(126); }

  public void setIcdDxCodePrmy(String icdDxCodePrmy) throws SQLException
  { _struct.setAttribute(126, icdDxCodePrmy); }


  public String getIcdDxCode2() throws SQLException
  { return (String) _struct.getAttribute(127); }

  public void setIcdDxCode2(String icdDxCode2) throws SQLException
  { _struct.setAttribute(127, icdDxCode2); }


  public String getIcdDxCode3() throws SQLException
  { return (String) _struct.getAttribute(128); }

  public void setIcdDxCode3(String icdDxCode3) throws SQLException
  { _struct.setAttribute(128, icdDxCode3); }


  public String getIcdDxCode4() throws SQLException
  { return (String) _struct.getAttribute(129); }

  public void setIcdDxCode4(String icdDxCode4) throws SQLException
  { _struct.setAttribute(129, icdDxCode4); }


  public String getIcdDxCode5() throws SQLException
  { return (String) _struct.getAttribute(130); }

  public void setIcdDxCode5(String icdDxCode5) throws SQLException
  { _struct.setAttribute(130, icdDxCode5); }


  public String getIcdDxCode6() throws SQLException
  { return (String) _struct.getAttribute(131); }

  public void setIcdDxCode6(String icdDxCode6) throws SQLException
  { _struct.setAttribute(131, icdDxCode6); }


  public String getIcdDxCode7() throws SQLException
  { return (String) _struct.getAttribute(132); }

  public void setIcdDxCode7(String icdDxCode7) throws SQLException
  { _struct.setAttribute(132, icdDxCode7); }


  public String getIcdDxCode8() throws SQLException
  { return (String) _struct.getAttribute(133); }

  public void setIcdDxCode8(String icdDxCode8) throws SQLException
  { _struct.setAttribute(133, icdDxCode8); }


  public String getIcdDxCode9() throws SQLException
  { return (String) _struct.getAttribute(134); }

  public void setIcdDxCode9(String icdDxCode9) throws SQLException
  { _struct.setAttribute(134, icdDxCode9); }


  public String getIcdDxCode10() throws SQLException
  { return (String) _struct.getAttribute(135); }

  public void setIcdDxCode10(String icdDxCode10) throws SQLException
  { _struct.setAttribute(135, icdDxCode10); }


  public String getIcdDxCode11() throws SQLException
  { return (String) _struct.getAttribute(136); }

  public void setIcdDxCode11(String icdDxCode11) throws SQLException
  { _struct.setAttribute(136, icdDxCode11); }


  public String getIcdDxCode12() throws SQLException
  { return (String) _struct.getAttribute(137); }

  public void setIcdDxCode12(String icdDxCode12) throws SQLException
  { _struct.setAttribute(137, icdDxCode12); }


  public String getSvcName() throws SQLException
  { return (String) _struct.getAttribute(138); }

  public void setSvcName(String svcName) throws SQLException
  { _struct.setAttribute(138, svcName); }


  public String getInvNum() throws SQLException
  { return (String) _struct.getAttribute(139); }

  public void setInvNum(String invNum) throws SQLException
  { _struct.setAttribute(139, invNum); }


  public String getInvDetId() throws SQLException
  { return (String) _struct.getAttribute(140); }

  public void setInvDetId(String invDetId) throws SQLException
  { _struct.setAttribute(140, invDetId); }


  public String getCrn() throws SQLException
  { return (String) _struct.getAttribute(141); }

  public void setCrn(String crn) throws SQLException
  { _struct.setAttribute(141, crn); }


  public String getPtDischargeStatusCode() throws SQLException
  { return (String) _struct.getAttribute(142); }

  public void setPtDischargeStatusCode(String ptDischargeStatusCode) throws SQLException
  { _struct.setAttribute(142, ptDischargeStatusCode); }


  public String getPtAdmSrcCode() throws SQLException
  { return (String) _struct.getAttribute(143); }

  public void setPtAdmSrcCode(String ptAdmSrcCode) throws SQLException
  { _struct.setAttribute(143, ptAdmSrcCode); }


  public String getPtAdmTypCode() throws SQLException
  { return (String) _struct.getAttribute(144); }

  public void setPtAdmTypCode(String ptAdmTypCode) throws SQLException
  { _struct.setAttribute(144, ptAdmTypCode); }


  public java.math.BigDecimal getClaimLineSvcLineItemNum() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(145); }

  public void setClaimLineSvcLineItemNum(java.math.BigDecimal claimLineSvcLineItemNum) throws SQLException
  { _struct.setAttribute(145, claimLineSvcLineItemNum); }

}
