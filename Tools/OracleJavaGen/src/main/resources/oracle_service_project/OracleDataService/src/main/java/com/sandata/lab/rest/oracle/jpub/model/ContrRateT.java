package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class ContrRateT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.CONTR_RATE_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,2,2,12,12,12,91,91,12,12,12,12,12,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[21];
  protected static final ContrRateT _ContrRateTFactory = new ContrRateT();

  public static ORADataFactory getORADataFactory()
  { return _ContrRateTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[21], _sqlType, _factory); }
  public ContrRateT()
  { _init_struct(true); }
  public ContrRateT(java.math.BigDecimal contrRateSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String payerId, String contrId, java.sql.Timestamp contrRateEffDate, java.sql.Timestamp contrRateTermDate, String svcName, String rateTypName, String rateSubTypName, String rateQlfrCode, String svcUnitName, java.math.BigDecimal contrRateAmt) throws SQLException
  { _init_struct(true);
    setContrRateSk(contrRateSk);
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
    setContrId(contrId);
    setContrRateEffDate(contrRateEffDate);
    setContrRateTermDate(contrRateTermDate);
    setSvcName(svcName);
    setRateTypName(rateTypName);
    setRateSubTypName(rateSubTypName);
    setRateQlfrCode(rateQlfrCode);
    setSvcUnitName(svcUnitName);
    setContrRateAmt(contrRateAmt);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(ContrRateT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new ContrRateT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getContrRateSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setContrRateSk(java.math.BigDecimal contrRateSk) throws SQLException
  { _struct.setAttribute(0, contrRateSk); }


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


  public String getContrId() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setContrId(String contrId) throws SQLException
  { _struct.setAttribute(12, contrId); }


  public java.sql.Timestamp getContrRateEffDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(13); }

  public void setContrRateEffDate(java.sql.Timestamp contrRateEffDate) throws SQLException
  { _struct.setAttribute(13, contrRateEffDate); }


  public java.sql.Timestamp getContrRateTermDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(14); }

  public void setContrRateTermDate(java.sql.Timestamp contrRateTermDate) throws SQLException
  { _struct.setAttribute(14, contrRateTermDate); }


  public String getSvcName() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setSvcName(String svcName) throws SQLException
  { _struct.setAttribute(15, svcName); }


  public String getRateTypName() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setRateTypName(String rateTypName) throws SQLException
  { _struct.setAttribute(16, rateTypName); }


  public String getRateSubTypName() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setRateSubTypName(String rateSubTypName) throws SQLException
  { _struct.setAttribute(17, rateSubTypName); }


  public String getRateQlfrCode() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setRateQlfrCode(String rateQlfrCode) throws SQLException
  { _struct.setAttribute(18, rateQlfrCode); }


  public String getSvcUnitName() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setSvcUnitName(String svcUnitName) throws SQLException
  { _struct.setAttribute(19, svcUnitName); }


  public java.math.BigDecimal getContrRateAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(20); }

  public void setContrRateAmt(java.math.BigDecimal contrRateAmt) throws SQLException
  { _struct.setAttribute(20, contrRateAmt); }

}
