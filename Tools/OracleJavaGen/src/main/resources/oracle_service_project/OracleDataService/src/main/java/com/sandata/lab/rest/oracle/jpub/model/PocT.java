package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class PocT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.POC_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,91,91,91,91,2,2,12,12,12,2,91,91,2,2,2,2,2,2,2,2,2,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[24];
  protected static final PocT _PocTFactory = new PocT();

  public static ORADataFactory getORADataFactory()
  { return _PocTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[24], _sqlType, _factory); }
  public PocT()
  { _init_struct(true); }
  public PocT(java.math.BigDecimal pocSk, String pocId, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String ptId, String authId, java.math.BigDecimal freqTypLkupSk, java.sql.Timestamp pocStartDate, java.sql.Timestamp pocEndDate, java.math.BigDecimal planOfTreatInd, java.math.BigDecimal pocDaysPerWeek, java.math.BigDecimal pocDay1Ind, java.math.BigDecimal pocDay2Ind, java.math.BigDecimal pocDay3Ind, java.math.BigDecimal pocDay4Ind, java.math.BigDecimal pocDay5Ind, java.math.BigDecimal pocDay6Ind, java.math.BigDecimal pocDay7Ind, java.math.BigDecimal pocHrsPerDay) throws SQLException
  { _init_struct(true);
    setPocSk(pocSk);
    setPocId(pocId);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setCurrRecInd(currRecInd);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setPtId(ptId);
    setAuthId(authId);
    setFreqTypLkupSk(freqTypLkupSk);
    setPocStartDate(pocStartDate);
    setPocEndDate(pocEndDate);
    setPlanOfTreatInd(planOfTreatInd);
    setPocDaysPerWeek(pocDaysPerWeek);
    setPocDay1Ind(pocDay1Ind);
    setPocDay2Ind(pocDay2Ind);
    setPocDay3Ind(pocDay3Ind);
    setPocDay4Ind(pocDay4Ind);
    setPocDay5Ind(pocDay5Ind);
    setPocDay6Ind(pocDay6Ind);
    setPocDay7Ind(pocDay7Ind);
    setPocHrsPerDay(pocHrsPerDay);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PocT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PocT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPocSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPocSk(java.math.BigDecimal pocSk) throws SQLException
  { _struct.setAttribute(0, pocSk); }


  public String getPocId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setPocId(String pocId) throws SQLException
  { _struct.setAttribute(1, pocId); }


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


  public java.math.BigDecimal getCurrRecInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(6); }

  public void setCurrRecInd(java.math.BigDecimal currRecInd) throws SQLException
  { _struct.setAttribute(6, currRecInd); }


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


  public String getAuthId() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setAuthId(String authId) throws SQLException
  { _struct.setAttribute(10, authId); }


  public java.math.BigDecimal getFreqTypLkupSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(11); }

  public void setFreqTypLkupSk(java.math.BigDecimal freqTypLkupSk) throws SQLException
  { _struct.setAttribute(11, freqTypLkupSk); }


  public java.sql.Timestamp getPocStartDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(12); }

  public void setPocStartDate(java.sql.Timestamp pocStartDate) throws SQLException
  { _struct.setAttribute(12, pocStartDate); }


  public java.sql.Timestamp getPocEndDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(13); }

  public void setPocEndDate(java.sql.Timestamp pocEndDate) throws SQLException
  { _struct.setAttribute(13, pocEndDate); }


  public java.math.BigDecimal getPlanOfTreatInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(14); }

  public void setPlanOfTreatInd(java.math.BigDecimal planOfTreatInd) throws SQLException
  { _struct.setAttribute(14, planOfTreatInd); }


  public java.math.BigDecimal getPocDaysPerWeek() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(15); }

  public void setPocDaysPerWeek(java.math.BigDecimal pocDaysPerWeek) throws SQLException
  { _struct.setAttribute(15, pocDaysPerWeek); }


  public java.math.BigDecimal getPocDay1Ind() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(16); }

  public void setPocDay1Ind(java.math.BigDecimal pocDay1Ind) throws SQLException
  { _struct.setAttribute(16, pocDay1Ind); }


  public java.math.BigDecimal getPocDay2Ind() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(17); }

  public void setPocDay2Ind(java.math.BigDecimal pocDay2Ind) throws SQLException
  { _struct.setAttribute(17, pocDay2Ind); }


  public java.math.BigDecimal getPocDay3Ind() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(18); }

  public void setPocDay3Ind(java.math.BigDecimal pocDay3Ind) throws SQLException
  { _struct.setAttribute(18, pocDay3Ind); }


  public java.math.BigDecimal getPocDay4Ind() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(19); }

  public void setPocDay4Ind(java.math.BigDecimal pocDay4Ind) throws SQLException
  { _struct.setAttribute(19, pocDay4Ind); }


  public java.math.BigDecimal getPocDay5Ind() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(20); }

  public void setPocDay5Ind(java.math.BigDecimal pocDay5Ind) throws SQLException
  { _struct.setAttribute(20, pocDay5Ind); }


  public java.math.BigDecimal getPocDay6Ind() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(21); }

  public void setPocDay6Ind(java.math.BigDecimal pocDay6Ind) throws SQLException
  { _struct.setAttribute(21, pocDay6Ind); }


  public java.math.BigDecimal getPocDay7Ind() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(22); }

  public void setPocDay7Ind(java.math.BigDecimal pocDay7Ind) throws SQLException
  { _struct.setAttribute(22, pocDay7Ind); }


  public java.math.BigDecimal getPocHrsPerDay() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(23); }

  public void setPocHrsPerDay(java.math.BigDecimal pocHrsPerDay) throws SQLException
  { _struct.setAttribute(23, pocHrsPerDay); }

}
