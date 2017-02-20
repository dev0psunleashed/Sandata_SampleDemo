package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class EligT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.ELIG_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,2,2,12,12,12,12,91,12,12,12,2,12,12,12,12,91,91 };
  protected static ORADataFactory[] _factory = new ORADataFactory[25];
  protected static final EligT _EligTFactory = new EligT();

  public static ORADataFactory getORADataFactory()
  { return _EligTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[25], _sqlType, _factory); }
  public EligT()
  { _init_struct(true); }
  public EligT(java.math.BigDecimal eligSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String payerId, String ptId, String eligSrcVendorName, java.sql.Timestamp lastEligCheckDate, String eligStatusName, String eligCvgLvl, String planCvgDesc, java.math.BigDecimal bnftAmt, String svcTyp, String insTyp, String timePrdQual, String eligCmnt, java.sql.Timestamp eligEffDate, java.sql.Timestamp eligTermDate) throws SQLException
  { _init_struct(true);
    setEligSk(eligSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonMemo(changeReasonMemo);
    setCurrRecInd(currRecInd);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setPayerId(payerId);
    setPtId(ptId);
    setEligSrcVendorName(eligSrcVendorName);
    setLastEligCheckDate(lastEligCheckDate);
    setEligStatusName(eligStatusName);
    setEligCvgLvl(eligCvgLvl);
    setPlanCvgDesc(planCvgDesc);
    setBnftAmt(bnftAmt);
    setSvcTyp(svcTyp);
    setInsTyp(insTyp);
    setTimePrdQual(timePrdQual);
    setEligCmnt(eligCmnt);
    setEligEffDate(eligEffDate);
    setEligTermDate(eligTermDate);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(EligT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new EligT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getEligSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setEligSk(java.math.BigDecimal eligSk) throws SQLException
  { _struct.setAttribute(0, eligSk); }


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


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(10, beId); }


  public String getPayerId() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setPayerId(String payerId) throws SQLException
  { _struct.setAttribute(11, payerId); }


  public String getPtId() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setPtId(String ptId) throws SQLException
  { _struct.setAttribute(12, ptId); }


  public String getEligSrcVendorName() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setEligSrcVendorName(String eligSrcVendorName) throws SQLException
  { _struct.setAttribute(13, eligSrcVendorName); }


  public java.sql.Timestamp getLastEligCheckDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(14); }

  public void setLastEligCheckDate(java.sql.Timestamp lastEligCheckDate) throws SQLException
  { _struct.setAttribute(14, lastEligCheckDate); }


  public String getEligStatusName() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setEligStatusName(String eligStatusName) throws SQLException
  { _struct.setAttribute(15, eligStatusName); }


  public String getEligCvgLvl() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setEligCvgLvl(String eligCvgLvl) throws SQLException
  { _struct.setAttribute(16, eligCvgLvl); }


  public String getPlanCvgDesc() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setPlanCvgDesc(String planCvgDesc) throws SQLException
  { _struct.setAttribute(17, planCvgDesc); }


  public java.math.BigDecimal getBnftAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(18); }

  public void setBnftAmt(java.math.BigDecimal bnftAmt) throws SQLException
  { _struct.setAttribute(18, bnftAmt); }


  public String getSvcTyp() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setSvcTyp(String svcTyp) throws SQLException
  { _struct.setAttribute(19, svcTyp); }


  public String getInsTyp() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setInsTyp(String insTyp) throws SQLException
  { _struct.setAttribute(20, insTyp); }


  public String getTimePrdQual() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setTimePrdQual(String timePrdQual) throws SQLException
  { _struct.setAttribute(21, timePrdQual); }


  public String getEligCmnt() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setEligCmnt(String eligCmnt) throws SQLException
  { _struct.setAttribute(22, eligCmnt); }


  public java.sql.Timestamp getEligEffDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(23); }

  public void setEligEffDate(java.sql.Timestamp eligEffDate) throws SQLException
  { _struct.setAttribute(23, eligEffDate); }


  public java.sql.Timestamp getEligTermDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(24); }

  public void setEligTermDate(java.sql.Timestamp eligTermDate) throws SQLException
  { _struct.setAttribute(24, eligTermDate); }

}
