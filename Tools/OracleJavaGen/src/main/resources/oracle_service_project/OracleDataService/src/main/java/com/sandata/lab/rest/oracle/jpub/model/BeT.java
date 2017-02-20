package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class BeT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.BE_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,91,91,91,91,12,12,12,2,2,12,12,12,12,12,12,12,12,12,12,12,12,12,12,2,12,12,12,1,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,2,2,2,12,12,12,12,12,12,12,12,2,91,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[63];
  protected static final BeT _BeTFactory = new BeT();

  public static ORADataFactory getORADataFactory()
  { return _BeTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[63], _sqlType, _factory); }
  public BeT()
  { _init_struct(true); }
  public BeT(java.math.BigDecimal beSk, String beId, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String tzName, String beLogoPointer, String beName, String beLegalName, String beFederaltaxId, String beMedicareId, String beMedicaidId, String oasisId, String beNpi, String beApi, String beTaxonomyCode, String beTyp, String bePrmyContName, String bePrmyContTitle, java.math.BigDecimal bePrmyAddrUseForMailInd, String bePrmyAddr1, String bePrmyAddr2, String bePrmyCity, String bePrmyState, String bePrmyCounty, String bePrmyPstlCode, String bePrmyZip4, String bePrmyPhone, String bePrmyPhoneExt, String bePrmyPhoneQlfr, String bePrmyPhone1, String bePrmyPhone1Ext, String bePrmyPhone1Qlfr, String bePrmyPhone2, String bePrmyPhone2Ext, String bePrmyPhone2Qlfr, String beFax, String beFaxQlfr, String beFax1, String beFax1Qlfr, String bePrmyEmail, String bePrWeekEndDay, java.math.BigDecimal beHoldBillingInd, java.math.BigDecimal beSplitBillingAlwdInd, java.math.BigDecimal beInclRemitAddrInd, String beWeekendStartDay, String beWeekendStartTime, String beWeekendEndDay, String beWeekendEndTime, String bePtAsmtFreq, String bePtSupvyVisitFreq, String bePrFreqTypName, String beBillingFreqTypName, java.math.BigDecimal beLiEquiv, java.sql.Timestamp beFirstPrDate, String bePrStartDay, String vvRndingRuleId) throws SQLException
  { _init_struct(true);
    setBeSk(beSk);
    setBeId(beId);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonMemo(changeReasonMemo);
    setCurrRecInd(currRecInd);
    setChangeVersionId(changeVersionId);
    setTzName(tzName);
    setBeLogoPointer(beLogoPointer);
    setBeName(beName);
    setBeLegalName(beLegalName);
    setBeFederaltaxId(beFederaltaxId);
    setBeMedicareId(beMedicareId);
    setBeMedicaidId(beMedicaidId);
    setOasisId(oasisId);
    setBeNpi(beNpi);
    setBeApi(beApi);
    setBeTaxonomyCode(beTaxonomyCode);
    setBeTyp(beTyp);
    setBePrmyContName(bePrmyContName);
    setBePrmyContTitle(bePrmyContTitle);
    setBePrmyAddrUseForMailInd(bePrmyAddrUseForMailInd);
    setBePrmyAddr1(bePrmyAddr1);
    setBePrmyAddr2(bePrmyAddr2);
    setBePrmyCity(bePrmyCity);
    setBePrmyState(bePrmyState);
    setBePrmyCounty(bePrmyCounty);
    setBePrmyPstlCode(bePrmyPstlCode);
    setBePrmyZip4(bePrmyZip4);
    setBePrmyPhone(bePrmyPhone);
    setBePrmyPhoneExt(bePrmyPhoneExt);
    setBePrmyPhoneQlfr(bePrmyPhoneQlfr);
    setBePrmyPhone1(bePrmyPhone1);
    setBePrmyPhone1Ext(bePrmyPhone1Ext);
    setBePrmyPhone1Qlfr(bePrmyPhone1Qlfr);
    setBePrmyPhone2(bePrmyPhone2);
    setBePrmyPhone2Ext(bePrmyPhone2Ext);
    setBePrmyPhone2Qlfr(bePrmyPhone2Qlfr);
    setBeFax(beFax);
    setBeFaxQlfr(beFaxQlfr);
    setBeFax1(beFax1);
    setBeFax1Qlfr(beFax1Qlfr);
    setBePrmyEmail(bePrmyEmail);
    setBePrWeekEndDay(bePrWeekEndDay);
    setBeHoldBillingInd(beHoldBillingInd);
    setBeSplitBillingAlwdInd(beSplitBillingAlwdInd);
    setBeInclRemitAddrInd(beInclRemitAddrInd);
    setBeWeekendStartDay(beWeekendStartDay);
    setBeWeekendStartTime(beWeekendStartTime);
    setBeWeekendEndDay(beWeekendEndDay);
    setBeWeekendEndTime(beWeekendEndTime);
    setBePtAsmtFreq(bePtAsmtFreq);
    setBePtSupvyVisitFreq(bePtSupvyVisitFreq);
    setBePrFreqTypName(bePrFreqTypName);
    setBeBillingFreqTypName(beBillingFreqTypName);
    setBeLiEquiv(beLiEquiv);
    setBeFirstPrDate(beFirstPrDate);
    setBePrStartDay(bePrStartDay);
    setVvRndingRuleId(vvRndingRuleId);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(BeT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new BeT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getBeSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setBeSk(java.math.BigDecimal beSk) throws SQLException
  { _struct.setAttribute(0, beSk); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(1, beId); }


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


  public String getTzName() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setTzName(String tzName) throws SQLException
  { _struct.setAttribute(11, tzName); }


  public String getBeLogoPointer() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setBeLogoPointer(String beLogoPointer) throws SQLException
  { _struct.setAttribute(12, beLogoPointer); }


  public String getBeName() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setBeName(String beName) throws SQLException
  { _struct.setAttribute(13, beName); }


  public String getBeLegalName() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setBeLegalName(String beLegalName) throws SQLException
  { _struct.setAttribute(14, beLegalName); }


  public String getBeFederaltaxId() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setBeFederaltaxId(String beFederaltaxId) throws SQLException
  { _struct.setAttribute(15, beFederaltaxId); }


  public String getBeMedicareId() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setBeMedicareId(String beMedicareId) throws SQLException
  { _struct.setAttribute(16, beMedicareId); }


  public String getBeMedicaidId() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setBeMedicaidId(String beMedicaidId) throws SQLException
  { _struct.setAttribute(17, beMedicaidId); }


  public String getOasisId() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setOasisId(String oasisId) throws SQLException
  { _struct.setAttribute(18, oasisId); }


  public String getBeNpi() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setBeNpi(String beNpi) throws SQLException
  { _struct.setAttribute(19, beNpi); }


  public String getBeApi() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setBeApi(String beApi) throws SQLException
  { _struct.setAttribute(20, beApi); }


  public String getBeTaxonomyCode() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setBeTaxonomyCode(String beTaxonomyCode) throws SQLException
  { _struct.setAttribute(21, beTaxonomyCode); }


  public String getBeTyp() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setBeTyp(String beTyp) throws SQLException
  { _struct.setAttribute(22, beTyp); }


  public String getBePrmyContName() throws SQLException
  { return (String) _struct.getAttribute(23); }

  public void setBePrmyContName(String bePrmyContName) throws SQLException
  { _struct.setAttribute(23, bePrmyContName); }


  public String getBePrmyContTitle() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setBePrmyContTitle(String bePrmyContTitle) throws SQLException
  { _struct.setAttribute(24, bePrmyContTitle); }


  public java.math.BigDecimal getBePrmyAddrUseForMailInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(25); }

  public void setBePrmyAddrUseForMailInd(java.math.BigDecimal bePrmyAddrUseForMailInd) throws SQLException
  { _struct.setAttribute(25, bePrmyAddrUseForMailInd); }


  public String getBePrmyAddr1() throws SQLException
  { return (String) _struct.getAttribute(26); }

  public void setBePrmyAddr1(String bePrmyAddr1) throws SQLException
  { _struct.setAttribute(26, bePrmyAddr1); }


  public String getBePrmyAddr2() throws SQLException
  { return (String) _struct.getAttribute(27); }

  public void setBePrmyAddr2(String bePrmyAddr2) throws SQLException
  { _struct.setAttribute(27, bePrmyAddr2); }


  public String getBePrmyCity() throws SQLException
  { return (String) _struct.getAttribute(28); }

  public void setBePrmyCity(String bePrmyCity) throws SQLException
  { _struct.setAttribute(28, bePrmyCity); }


  public String getBePrmyState() throws SQLException
  { return (String) _struct.getAttribute(29); }

  public void setBePrmyState(String bePrmyState) throws SQLException
  { _struct.setAttribute(29, bePrmyState); }


  public String getBePrmyCounty() throws SQLException
  { return (String) _struct.getAttribute(30); }

  public void setBePrmyCounty(String bePrmyCounty) throws SQLException
  { _struct.setAttribute(30, bePrmyCounty); }


  public String getBePrmyPstlCode() throws SQLException
  { return (String) _struct.getAttribute(31); }

  public void setBePrmyPstlCode(String bePrmyPstlCode) throws SQLException
  { _struct.setAttribute(31, bePrmyPstlCode); }


  public String getBePrmyZip4() throws SQLException
  { return (String) _struct.getAttribute(32); }

  public void setBePrmyZip4(String bePrmyZip4) throws SQLException
  { _struct.setAttribute(32, bePrmyZip4); }


  public String getBePrmyPhone() throws SQLException
  { return (String) _struct.getAttribute(33); }

  public void setBePrmyPhone(String bePrmyPhone) throws SQLException
  { _struct.setAttribute(33, bePrmyPhone); }


  public String getBePrmyPhoneExt() throws SQLException
  { return (String) _struct.getAttribute(34); }

  public void setBePrmyPhoneExt(String bePrmyPhoneExt) throws SQLException
  { _struct.setAttribute(34, bePrmyPhoneExt); }


  public String getBePrmyPhoneQlfr() throws SQLException
  { return (String) _struct.getAttribute(35); }

  public void setBePrmyPhoneQlfr(String bePrmyPhoneQlfr) throws SQLException
  { _struct.setAttribute(35, bePrmyPhoneQlfr); }


  public String getBePrmyPhone1() throws SQLException
  { return (String) _struct.getAttribute(36); }

  public void setBePrmyPhone1(String bePrmyPhone1) throws SQLException
  { _struct.setAttribute(36, bePrmyPhone1); }


  public String getBePrmyPhone1Ext() throws SQLException
  { return (String) _struct.getAttribute(37); }

  public void setBePrmyPhone1Ext(String bePrmyPhone1Ext) throws SQLException
  { _struct.setAttribute(37, bePrmyPhone1Ext); }


  public String getBePrmyPhone1Qlfr() throws SQLException
  { return (String) _struct.getAttribute(38); }

  public void setBePrmyPhone1Qlfr(String bePrmyPhone1Qlfr) throws SQLException
  { _struct.setAttribute(38, bePrmyPhone1Qlfr); }


  public String getBePrmyPhone2() throws SQLException
  { return (String) _struct.getAttribute(39); }

  public void setBePrmyPhone2(String bePrmyPhone2) throws SQLException
  { _struct.setAttribute(39, bePrmyPhone2); }


  public String getBePrmyPhone2Ext() throws SQLException
  { return (String) _struct.getAttribute(40); }

  public void setBePrmyPhone2Ext(String bePrmyPhone2Ext) throws SQLException
  { _struct.setAttribute(40, bePrmyPhone2Ext); }


  public String getBePrmyPhone2Qlfr() throws SQLException
  { return (String) _struct.getAttribute(41); }

  public void setBePrmyPhone2Qlfr(String bePrmyPhone2Qlfr) throws SQLException
  { _struct.setAttribute(41, bePrmyPhone2Qlfr); }


  public String getBeFax() throws SQLException
  { return (String) _struct.getAttribute(42); }

  public void setBeFax(String beFax) throws SQLException
  { _struct.setAttribute(42, beFax); }


  public String getBeFaxQlfr() throws SQLException
  { return (String) _struct.getAttribute(43); }

  public void setBeFaxQlfr(String beFaxQlfr) throws SQLException
  { _struct.setAttribute(43, beFaxQlfr); }


  public String getBeFax1() throws SQLException
  { return (String) _struct.getAttribute(44); }

  public void setBeFax1(String beFax1) throws SQLException
  { _struct.setAttribute(44, beFax1); }


  public String getBeFax1Qlfr() throws SQLException
  { return (String) _struct.getAttribute(45); }

  public void setBeFax1Qlfr(String beFax1Qlfr) throws SQLException
  { _struct.setAttribute(45, beFax1Qlfr); }


  public String getBePrmyEmail() throws SQLException
  { return (String) _struct.getAttribute(46); }

  public void setBePrmyEmail(String bePrmyEmail) throws SQLException
  { _struct.setAttribute(46, bePrmyEmail); }


  public String getBePrWeekEndDay() throws SQLException
  { return (String) _struct.getAttribute(47); }

  public void setBePrWeekEndDay(String bePrWeekEndDay) throws SQLException
  { _struct.setAttribute(47, bePrWeekEndDay); }


  public java.math.BigDecimal getBeHoldBillingInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(48); }

  public void setBeHoldBillingInd(java.math.BigDecimal beHoldBillingInd) throws SQLException
  { _struct.setAttribute(48, beHoldBillingInd); }


  public java.math.BigDecimal getBeSplitBillingAlwdInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(49); }

  public void setBeSplitBillingAlwdInd(java.math.BigDecimal beSplitBillingAlwdInd) throws SQLException
  { _struct.setAttribute(49, beSplitBillingAlwdInd); }


  public java.math.BigDecimal getBeInclRemitAddrInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(50); }

  public void setBeInclRemitAddrInd(java.math.BigDecimal beInclRemitAddrInd) throws SQLException
  { _struct.setAttribute(50, beInclRemitAddrInd); }


  public String getBeWeekendStartDay() throws SQLException
  { return (String) _struct.getAttribute(51); }

  public void setBeWeekendStartDay(String beWeekendStartDay) throws SQLException
  { _struct.setAttribute(51, beWeekendStartDay); }


  public String getBeWeekendStartTime() throws SQLException
  { return (String) _struct.getAttribute(52); }

  public void setBeWeekendStartTime(String beWeekendStartTime) throws SQLException
  { _struct.setAttribute(52, beWeekendStartTime); }


  public String getBeWeekendEndDay() throws SQLException
  { return (String) _struct.getAttribute(53); }

  public void setBeWeekendEndDay(String beWeekendEndDay) throws SQLException
  { _struct.setAttribute(53, beWeekendEndDay); }


  public String getBeWeekendEndTime() throws SQLException
  { return (String) _struct.getAttribute(54); }

  public void setBeWeekendEndTime(String beWeekendEndTime) throws SQLException
  { _struct.setAttribute(54, beWeekendEndTime); }


  public String getBePtAsmtFreq() throws SQLException
  { return (String) _struct.getAttribute(55); }

  public void setBePtAsmtFreq(String bePtAsmtFreq) throws SQLException
  { _struct.setAttribute(55, bePtAsmtFreq); }


  public String getBePtSupvyVisitFreq() throws SQLException
  { return (String) _struct.getAttribute(56); }

  public void setBePtSupvyVisitFreq(String bePtSupvyVisitFreq) throws SQLException
  { _struct.setAttribute(56, bePtSupvyVisitFreq); }


  public String getBePrFreqTypName() throws SQLException
  { return (String) _struct.getAttribute(57); }

  public void setBePrFreqTypName(String bePrFreqTypName) throws SQLException
  { _struct.setAttribute(57, bePrFreqTypName); }


  public String getBeBillingFreqTypName() throws SQLException
  { return (String) _struct.getAttribute(58); }

  public void setBeBillingFreqTypName(String beBillingFreqTypName) throws SQLException
  { _struct.setAttribute(58, beBillingFreqTypName); }


  public java.math.BigDecimal getBeLiEquiv() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(59); }

  public void setBeLiEquiv(java.math.BigDecimal beLiEquiv) throws SQLException
  { _struct.setAttribute(59, beLiEquiv); }


  public java.sql.Timestamp getBeFirstPrDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(60); }

  public void setBeFirstPrDate(java.sql.Timestamp beFirstPrDate) throws SQLException
  { _struct.setAttribute(60, beFirstPrDate); }


  public String getBePrStartDay() throws SQLException
  { return (String) _struct.getAttribute(61); }

  public void setBePrStartDay(String bePrStartDay) throws SQLException
  { _struct.setAttribute(61, bePrStartDay); }


  public String getVvRndingRuleId() throws SQLException
  { return (String) _struct.getAttribute(62); }

  public void setVvRndingRuleId(String vvRndingRuleId) throws SQLException
  { _struct.setAttribute(62, vvRndingRuleId); }

}
