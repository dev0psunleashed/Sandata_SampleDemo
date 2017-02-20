package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class PstlCodeDetLkupT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PSTL_CODE_DET_LKUP_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,2,2,12,12,12,12,12,1,12,12,12,12,12,12,12,1,12,12,12,12,1,12,12,12,12,1,12,1,12,1,12,12,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[43];
  protected static final PstlCodeDetLkupT _PstlCodeDetLkupTFactory = new PstlCodeDetLkupT();

  public static ORADataFactory getORADataFactory()
  { return _PstlCodeDetLkupTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[43], _sqlType, _factory); }
  public PstlCodeDetLkupT()
  { _init_struct(true); }
  public PstlCodeDetLkupT(java.math.BigDecimal pstlCodeDetLkupSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String pstlCode, String pstlCodeSrcQlfr, String pstlCodeZip4, String pstlCodeZip4Range, String pstlCodeCityName, String pstlCodeRecTypCode, String pstlCodeCarrierRouteId, String pstlCodeStreetPredirCode, String pstlCodeStreetName, String pstlCodeStreetSuffix, String pstlCodeStreetPostdirCode, String pstlCodePrmyLowNum, String pstlCodePrmyHighNum, String pstlCodePrmyOddEvenCode, String pstlCodeEntName, String pstlCodeScndryAbbrv, String pstlCodeScndryLowNum, String pstlCodeScndryHighNum, String pstlCodeScndryOddEvenCode, String pstlCodeAddOnLowSector, String pstlCodeAddOnLowSegment, String pstlCodeAddOnHighSector, String pstlCodeAddOnHighSegment, String pstlCodeBaseAlternateCode, String lacsStatusInd, String govBldgCode, String pstlCodeFinNum, String pstlCodeStateCode, String countyFipsCode, String congrnlDistrNum, String muniCityStateKey, String urbanizationCityStateKey, String prefdLastLineCityStateKey) throws SQLException
  { _init_struct(true);
    setPstlCodeDetLkupSk(pstlCodeDetLkupSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonMemo(changeReasonMemo);
    setCurrRecInd(currRecInd);
    setChangeVersionId(changeVersionId);
    setPstlCode(pstlCode);
    setPstlCodeSrcQlfr(pstlCodeSrcQlfr);
    setPstlCodeZip4(pstlCodeZip4);
    setPstlCodeZip4Range(pstlCodeZip4Range);
    setPstlCodeCityName(pstlCodeCityName);
    setPstlCodeRecTypCode(pstlCodeRecTypCode);
    setPstlCodeCarrierRouteId(pstlCodeCarrierRouteId);
    setPstlCodeStreetPredirCode(pstlCodeStreetPredirCode);
    setPstlCodeStreetName(pstlCodeStreetName);
    setPstlCodeStreetSuffix(pstlCodeStreetSuffix);
    setPstlCodeStreetPostdirCode(pstlCodeStreetPostdirCode);
    setPstlCodePrmyLowNum(pstlCodePrmyLowNum);
    setPstlCodePrmyHighNum(pstlCodePrmyHighNum);
    setPstlCodePrmyOddEvenCode(pstlCodePrmyOddEvenCode);
    setPstlCodeEntName(pstlCodeEntName);
    setPstlCodeScndryAbbrv(pstlCodeScndryAbbrv);
    setPstlCodeScndryLowNum(pstlCodeScndryLowNum);
    setPstlCodeScndryHighNum(pstlCodeScndryHighNum);
    setPstlCodeScndryOddEvenCode(pstlCodeScndryOddEvenCode);
    setPstlCodeAddOnLowSector(pstlCodeAddOnLowSector);
    setPstlCodeAddOnLowSegment(pstlCodeAddOnLowSegment);
    setPstlCodeAddOnHighSector(pstlCodeAddOnHighSector);
    setPstlCodeAddOnHighSegment(pstlCodeAddOnHighSegment);
    setPstlCodeBaseAlternateCode(pstlCodeBaseAlternateCode);
    setLacsStatusInd(lacsStatusInd);
    setGovBldgCode(govBldgCode);
    setPstlCodeFinNum(pstlCodeFinNum);
    setPstlCodeStateCode(pstlCodeStateCode);
    setCountyFipsCode(countyFipsCode);
    setCongrnlDistrNum(congrnlDistrNum);
    setMuniCityStateKey(muniCityStateKey);
    setUrbanizationCityStateKey(urbanizationCityStateKey);
    setPrefdLastLineCityStateKey(prefdLastLineCityStateKey);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PstlCodeDetLkupT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PstlCodeDetLkupT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPstlCodeDetLkupSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPstlCodeDetLkupSk(java.math.BigDecimal pstlCodeDetLkupSk) throws SQLException
  { _struct.setAttribute(0, pstlCodeDetLkupSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public java.sql.Timestamp getRecEffTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(3); }

  public void setRecEffTmstp(java.sql.Timestamp recEffTmstp) throws SQLException
  { _struct.setAttribute(3, recEffTmstp); }


  public java.sql.Timestamp getRecTermTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(4); }

  public void setRecTermTmstp(java.sql.Timestamp recTermTmstp) throws SQLException
  { _struct.setAttribute(4, recTermTmstp); }


  public String getRecCreatedBy() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setRecCreatedBy(String recCreatedBy) throws SQLException
  { _struct.setAttribute(5, recCreatedBy); }


  public String getRecUpdatedBy() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setRecUpdatedBy(String recUpdatedBy) throws SQLException
  { _struct.setAttribute(6, recUpdatedBy); }


  public String getChangeReasonMemo() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setChangeReasonMemo(String changeReasonMemo) throws SQLException
  { _struct.setAttribute(7, changeReasonMemo); }


  public java.math.BigDecimal getCurrRecInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(8); }

  public void setCurrRecInd(java.math.BigDecimal currRecInd) throws SQLException
  { _struct.setAttribute(8, currRecInd); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(9); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(9, changeVersionId); }


  public String getPstlCode() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setPstlCode(String pstlCode) throws SQLException
  { _struct.setAttribute(10, pstlCode); }


  public String getPstlCodeSrcQlfr() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setPstlCodeSrcQlfr(String pstlCodeSrcQlfr) throws SQLException
  { _struct.setAttribute(11, pstlCodeSrcQlfr); }


  public String getPstlCodeZip4() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setPstlCodeZip4(String pstlCodeZip4) throws SQLException
  { _struct.setAttribute(12, pstlCodeZip4); }


  public String getPstlCodeZip4Range() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setPstlCodeZip4Range(String pstlCodeZip4Range) throws SQLException
  { _struct.setAttribute(13, pstlCodeZip4Range); }


  public String getPstlCodeCityName() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setPstlCodeCityName(String pstlCodeCityName) throws SQLException
  { _struct.setAttribute(14, pstlCodeCityName); }


  public String getPstlCodeRecTypCode() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setPstlCodeRecTypCode(String pstlCodeRecTypCode) throws SQLException
  { _struct.setAttribute(15, pstlCodeRecTypCode); }


  public String getPstlCodeCarrierRouteId() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setPstlCodeCarrierRouteId(String pstlCodeCarrierRouteId) throws SQLException
  { _struct.setAttribute(16, pstlCodeCarrierRouteId); }


  public String getPstlCodeStreetPredirCode() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setPstlCodeStreetPredirCode(String pstlCodeStreetPredirCode) throws SQLException
  { _struct.setAttribute(17, pstlCodeStreetPredirCode); }


  public String getPstlCodeStreetName() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setPstlCodeStreetName(String pstlCodeStreetName) throws SQLException
  { _struct.setAttribute(18, pstlCodeStreetName); }


  public String getPstlCodeStreetSuffix() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setPstlCodeStreetSuffix(String pstlCodeStreetSuffix) throws SQLException
  { _struct.setAttribute(19, pstlCodeStreetSuffix); }


  public String getPstlCodeStreetPostdirCode() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setPstlCodeStreetPostdirCode(String pstlCodeStreetPostdirCode) throws SQLException
  { _struct.setAttribute(20, pstlCodeStreetPostdirCode); }


  public String getPstlCodePrmyLowNum() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setPstlCodePrmyLowNum(String pstlCodePrmyLowNum) throws SQLException
  { _struct.setAttribute(21, pstlCodePrmyLowNum); }


  public String getPstlCodePrmyHighNum() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setPstlCodePrmyHighNum(String pstlCodePrmyHighNum) throws SQLException
  { _struct.setAttribute(22, pstlCodePrmyHighNum); }


  public String getPstlCodePrmyOddEvenCode() throws SQLException
  { return (String) _struct.getAttribute(23); }

  public void setPstlCodePrmyOddEvenCode(String pstlCodePrmyOddEvenCode) throws SQLException
  { _struct.setAttribute(23, pstlCodePrmyOddEvenCode); }


  public String getPstlCodeEntName() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setPstlCodeEntName(String pstlCodeEntName) throws SQLException
  { _struct.setAttribute(24, pstlCodeEntName); }


  public String getPstlCodeScndryAbbrv() throws SQLException
  { return (String) _struct.getAttribute(25); }

  public void setPstlCodeScndryAbbrv(String pstlCodeScndryAbbrv) throws SQLException
  { _struct.setAttribute(25, pstlCodeScndryAbbrv); }


  public String getPstlCodeScndryLowNum() throws SQLException
  { return (String) _struct.getAttribute(26); }

  public void setPstlCodeScndryLowNum(String pstlCodeScndryLowNum) throws SQLException
  { _struct.setAttribute(26, pstlCodeScndryLowNum); }


  public String getPstlCodeScndryHighNum() throws SQLException
  { return (String) _struct.getAttribute(27); }

  public void setPstlCodeScndryHighNum(String pstlCodeScndryHighNum) throws SQLException
  { _struct.setAttribute(27, pstlCodeScndryHighNum); }


  public String getPstlCodeScndryOddEvenCode() throws SQLException
  { return (String) _struct.getAttribute(28); }

  public void setPstlCodeScndryOddEvenCode(String pstlCodeScndryOddEvenCode) throws SQLException
  { _struct.setAttribute(28, pstlCodeScndryOddEvenCode); }


  public String getPstlCodeAddOnLowSector() throws SQLException
  { return (String) _struct.getAttribute(29); }

  public void setPstlCodeAddOnLowSector(String pstlCodeAddOnLowSector) throws SQLException
  { _struct.setAttribute(29, pstlCodeAddOnLowSector); }


  public String getPstlCodeAddOnLowSegment() throws SQLException
  { return (String) _struct.getAttribute(30); }

  public void setPstlCodeAddOnLowSegment(String pstlCodeAddOnLowSegment) throws SQLException
  { _struct.setAttribute(30, pstlCodeAddOnLowSegment); }


  public String getPstlCodeAddOnHighSector() throws SQLException
  { return (String) _struct.getAttribute(31); }

  public void setPstlCodeAddOnHighSector(String pstlCodeAddOnHighSector) throws SQLException
  { _struct.setAttribute(31, pstlCodeAddOnHighSector); }


  public String getPstlCodeAddOnHighSegment() throws SQLException
  { return (String) _struct.getAttribute(32); }

  public void setPstlCodeAddOnHighSegment(String pstlCodeAddOnHighSegment) throws SQLException
  { _struct.setAttribute(32, pstlCodeAddOnHighSegment); }


  public String getPstlCodeBaseAlternateCode() throws SQLException
  { return (String) _struct.getAttribute(33); }

  public void setPstlCodeBaseAlternateCode(String pstlCodeBaseAlternateCode) throws SQLException
  { _struct.setAttribute(33, pstlCodeBaseAlternateCode); }


  public String getLacsStatusInd() throws SQLException
  { return (String) _struct.getAttribute(34); }

  public void setLacsStatusInd(String lacsStatusInd) throws SQLException
  { _struct.setAttribute(34, lacsStatusInd); }


  public String getGovBldgCode() throws SQLException
  { return (String) _struct.getAttribute(35); }

  public void setGovBldgCode(String govBldgCode) throws SQLException
  { _struct.setAttribute(35, govBldgCode); }


  public String getPstlCodeFinNum() throws SQLException
  { return (String) _struct.getAttribute(36); }

  public void setPstlCodeFinNum(String pstlCodeFinNum) throws SQLException
  { _struct.setAttribute(36, pstlCodeFinNum); }


  public String getPstlCodeStateCode() throws SQLException
  { return (String) _struct.getAttribute(37); }

  public void setPstlCodeStateCode(String pstlCodeStateCode) throws SQLException
  { _struct.setAttribute(37, pstlCodeStateCode); }


  public String getCountyFipsCode() throws SQLException
  { return (String) _struct.getAttribute(38); }

  public void setCountyFipsCode(String countyFipsCode) throws SQLException
  { _struct.setAttribute(38, countyFipsCode); }


  public String getCongrnlDistrNum() throws SQLException
  { return (String) _struct.getAttribute(39); }

  public void setCongrnlDistrNum(String congrnlDistrNum) throws SQLException
  { _struct.setAttribute(39, congrnlDistrNum); }


  public String getMuniCityStateKey() throws SQLException
  { return (String) _struct.getAttribute(40); }

  public void setMuniCityStateKey(String muniCityStateKey) throws SQLException
  { _struct.setAttribute(40, muniCityStateKey); }


  public String getUrbanizationCityStateKey() throws SQLException
  { return (String) _struct.getAttribute(41); }

  public void setUrbanizationCityStateKey(String urbanizationCityStateKey) throws SQLException
  { _struct.setAttribute(41, urbanizationCityStateKey); }


  public String getPrefdLastLineCityStateKey() throws SQLException
  { return (String) _struct.getAttribute(42); }

  public void setPrefdLastLineCityStateKey(String prefdLastLineCityStateKey) throws SQLException
  { _struct.setAttribute(42, prefdLastLineCityStateKey); }

}
