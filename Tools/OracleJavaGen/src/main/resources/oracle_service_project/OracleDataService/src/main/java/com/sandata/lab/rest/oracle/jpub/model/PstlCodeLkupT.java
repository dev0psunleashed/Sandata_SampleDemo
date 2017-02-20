package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class PstlCodeLkupT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PSTL_CODE_LKUP_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,2,2,12,12,1,12,1,12,12,12,1,12,1,12,12,2,2,2,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[27];
  protected static final PstlCodeLkupT _PstlCodeLkupTFactory = new PstlCodeLkupT();

  public static ORADataFactory getORADataFactory()
  { return _PstlCodeLkupTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[27], _sqlType, _factory); }
  public PstlCodeLkupT()
  { _init_struct(true); }
  public PstlCodeLkupT(java.math.BigDecimal pstlCodeLkupSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String pstlCode, String pstlCodeSrcQlfr, String pstlCodeUspsTypCode, String pstlCodeCityName, String pstlCodeUspsCityTypCode, String pstlCodeCountyName, String countyFipsCode, String pstlCodeStateName, String pstlCodeStateCode, String stateFipsCode, String pstlCodeMsaCode, String pstlCodeAreaCode, String tzName, java.math.BigDecimal utcOffset, java.math.BigDecimal pstlCodeDstInd, java.math.BigDecimal pstlCodeCoordLatitude, java.math.BigDecimal pstlCodeCoordLongitide) throws SQLException
  { _init_struct(true);
    setPstlCodeLkupSk(pstlCodeLkupSk);
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
    setPstlCodeUspsTypCode(pstlCodeUspsTypCode);
    setPstlCodeCityName(pstlCodeCityName);
    setPstlCodeUspsCityTypCode(pstlCodeUspsCityTypCode);
    setPstlCodeCountyName(pstlCodeCountyName);
    setCountyFipsCode(countyFipsCode);
    setPstlCodeStateName(pstlCodeStateName);
    setPstlCodeStateCode(pstlCodeStateCode);
    setStateFipsCode(stateFipsCode);
    setPstlCodeMsaCode(pstlCodeMsaCode);
    setPstlCodeAreaCode(pstlCodeAreaCode);
    setTzName(tzName);
    setUtcOffset(utcOffset);
    setPstlCodeDstInd(pstlCodeDstInd);
    setPstlCodeCoordLatitude(pstlCodeCoordLatitude);
    setPstlCodeCoordLongitide(pstlCodeCoordLongitide);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PstlCodeLkupT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PstlCodeLkupT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPstlCodeLkupSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPstlCodeLkupSk(java.math.BigDecimal pstlCodeLkupSk) throws SQLException
  { _struct.setAttribute(0, pstlCodeLkupSk); }


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


  public String getPstlCodeUspsTypCode() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setPstlCodeUspsTypCode(String pstlCodeUspsTypCode) throws SQLException
  { _struct.setAttribute(12, pstlCodeUspsTypCode); }


  public String getPstlCodeCityName() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setPstlCodeCityName(String pstlCodeCityName) throws SQLException
  { _struct.setAttribute(13, pstlCodeCityName); }


  public String getPstlCodeUspsCityTypCode() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setPstlCodeUspsCityTypCode(String pstlCodeUspsCityTypCode) throws SQLException
  { _struct.setAttribute(14, pstlCodeUspsCityTypCode); }


  public String getPstlCodeCountyName() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setPstlCodeCountyName(String pstlCodeCountyName) throws SQLException
  { _struct.setAttribute(15, pstlCodeCountyName); }


  public String getCountyFipsCode() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setCountyFipsCode(String countyFipsCode) throws SQLException
  { _struct.setAttribute(16, countyFipsCode); }


  public String getPstlCodeStateName() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setPstlCodeStateName(String pstlCodeStateName) throws SQLException
  { _struct.setAttribute(17, pstlCodeStateName); }


  public String getPstlCodeStateCode() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setPstlCodeStateCode(String pstlCodeStateCode) throws SQLException
  { _struct.setAttribute(18, pstlCodeStateCode); }


  public String getStateFipsCode() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setStateFipsCode(String stateFipsCode) throws SQLException
  { _struct.setAttribute(19, stateFipsCode); }


  public String getPstlCodeMsaCode() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setPstlCodeMsaCode(String pstlCodeMsaCode) throws SQLException
  { _struct.setAttribute(20, pstlCodeMsaCode); }


  public String getPstlCodeAreaCode() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setPstlCodeAreaCode(String pstlCodeAreaCode) throws SQLException
  { _struct.setAttribute(21, pstlCodeAreaCode); }


  public String getTzName() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setTzName(String tzName) throws SQLException
  { _struct.setAttribute(22, tzName); }


  public java.math.BigDecimal getUtcOffset() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(23); }

  public void setUtcOffset(java.math.BigDecimal utcOffset) throws SQLException
  { _struct.setAttribute(23, utcOffset); }


  public java.math.BigDecimal getPstlCodeDstInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(24); }

  public void setPstlCodeDstInd(java.math.BigDecimal pstlCodeDstInd) throws SQLException
  { _struct.setAttribute(24, pstlCodeDstInd); }


  public java.math.BigDecimal getPstlCodeCoordLatitude() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(25); }

  public void setPstlCodeCoordLatitude(java.math.BigDecimal pstlCodeCoordLatitude) throws SQLException
  { _struct.setAttribute(25, pstlCodeCoordLatitude); }


  public java.math.BigDecimal getPstlCodeCoordLongitide() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(26); }

  public void setPstlCodeCoordLongitide(java.math.BigDecimal pstlCodeCoordLongitide) throws SQLException
  { _struct.setAttribute(26, pstlCodeCoordLongitide); }

}
