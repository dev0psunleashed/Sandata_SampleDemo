package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class PrRateMatrixT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PR_RATE_MATRIX_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,2,2,12,12,12,12,91,91,12,12,12,12,12,2,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[24];
  protected static final PrRateMatrixT _PrRateMatrixTFactory = new PrRateMatrixT();

  public static ORADataFactory getORADataFactory()
  { return _PrRateMatrixTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[24], _sqlType, _factory); }
  public PrRateMatrixT()
  { _init_struct(true); }
  public PrRateMatrixT(java.math.BigDecimal prRateMatrixSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beLocId, String beId, String payerId, String contrId, java.sql.Timestamp prRateMatrixEffDate, java.sql.Timestamp prRateMatrixTermDate, String svcName, String rateTypName, String rateQlfrCode, String rateSubTypName, String prCode, java.math.BigDecimal rateAmt, String svcUnitName, String prRateMatrixNote) throws SQLException
  { _init_struct(true);
    setPrRateMatrixSk(prRateMatrixSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonMemo(changeReasonMemo);
    setCurrRecInd(currRecInd);
    setChangeVersionId(changeVersionId);
    setBeLocId(beLocId);
    setBeId(beId);
    setPayerId(payerId);
    setContrId(contrId);
    setPrRateMatrixEffDate(prRateMatrixEffDate);
    setPrRateMatrixTermDate(prRateMatrixTermDate);
    setSvcName(svcName);
    setRateTypName(rateTypName);
    setRateQlfrCode(rateQlfrCode);
    setRateSubTypName(rateSubTypName);
    setPrCode(prCode);
    setRateAmt(rateAmt);
    setSvcUnitName(svcUnitName);
    setPrRateMatrixNote(prRateMatrixNote);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PrRateMatrixT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PrRateMatrixT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPrRateMatrixSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPrRateMatrixSk(java.math.BigDecimal prRateMatrixSk) throws SQLException
  { _struct.setAttribute(0, prRateMatrixSk); }


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


  public String getBeLocId() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setBeLocId(String beLocId) throws SQLException
  { _struct.setAttribute(10, beLocId); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(11, beId); }


  public String getPayerId() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setPayerId(String payerId) throws SQLException
  { _struct.setAttribute(12, payerId); }


  public String getContrId() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setContrId(String contrId) throws SQLException
  { _struct.setAttribute(13, contrId); }


  public java.sql.Timestamp getPrRateMatrixEffDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(14); }

  public void setPrRateMatrixEffDate(java.sql.Timestamp prRateMatrixEffDate) throws SQLException
  { _struct.setAttribute(14, prRateMatrixEffDate); }


  public java.sql.Timestamp getPrRateMatrixTermDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(15); }

  public void setPrRateMatrixTermDate(java.sql.Timestamp prRateMatrixTermDate) throws SQLException
  { _struct.setAttribute(15, prRateMatrixTermDate); }


  public String getSvcName() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setSvcName(String svcName) throws SQLException
  { _struct.setAttribute(16, svcName); }


  public String getRateTypName() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setRateTypName(String rateTypName) throws SQLException
  { _struct.setAttribute(17, rateTypName); }


  public String getRateQlfrCode() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setRateQlfrCode(String rateQlfrCode) throws SQLException
  { _struct.setAttribute(18, rateQlfrCode); }


  public String getRateSubTypName() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setRateSubTypName(String rateSubTypName) throws SQLException
  { _struct.setAttribute(19, rateSubTypName); }


  public String getPrCode() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setPrCode(String prCode) throws SQLException
  { _struct.setAttribute(20, prCode); }


  public java.math.BigDecimal getRateAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(21); }

  public void setRateAmt(java.math.BigDecimal rateAmt) throws SQLException
  { _struct.setAttribute(21, rateAmt); }


  public String getSvcUnitName() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setSvcUnitName(String svcUnitName) throws SQLException
  { _struct.setAttribute(22, svcUnitName); }


  public String getPrRateMatrixNote() throws SQLException
  { return (String) _struct.getAttribute(23); }

  public void setPrRateMatrixNote(String prRateMatrixNote) throws SQLException
  { _struct.setAttribute(23, prRateMatrixNote); }

}
