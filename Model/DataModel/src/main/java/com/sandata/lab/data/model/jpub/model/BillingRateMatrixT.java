package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class BillingRateMatrixT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.BILLING_RATE_MATRIX_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,2,2,12,12,12,12,91,91,12,12,12,2,12,12,12,12,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[27];
  protected static final BillingRateMatrixT _BillingRateMatrixTFactory = new BillingRateMatrixT();

  public static ORADataFactory getORADataFactory()
  { return _BillingRateMatrixTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[27], _sqlType, _factory); }
  public BillingRateMatrixT()
  { _init_struct(true); }
  public BillingRateMatrixT(java.math.BigDecimal billingRateMatrixSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String beLocId, String payerId, String contrId, java.sql.Timestamp billingRateMatrixEffDate, java.sql.Timestamp billingRateMatrixTermDate, String svcName, String rateTypName, String rateQlfrCode, java.math.BigDecimal rateAmt, String svcUnitName, String billingCode, String mdfr1Code, String mdfr2Code, String mdfr3Code, String mdfr4Code, String revCode) throws SQLException
  { _init_struct(true);
    setBillingRateMatrixSk(billingRateMatrixSk);
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
    setBeLocId(beLocId);
    setPayerId(payerId);
    setContrId(contrId);
    setBillingRateMatrixEffDate(billingRateMatrixEffDate);
    setBillingRateMatrixTermDate(billingRateMatrixTermDate);
    setSvcName(svcName);
    setRateTypName(rateTypName);
    setRateQlfrCode(rateQlfrCode);
    setRateAmt(rateAmt);
    setSvcUnitName(svcUnitName);
    setBillingCode(billingCode);
    setMdfr1Code(mdfr1Code);
    setMdfr2Code(mdfr2Code);
    setMdfr3Code(mdfr3Code);
    setMdfr4Code(mdfr4Code);
    setRevCode(revCode);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(BillingRateMatrixT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new BillingRateMatrixT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getBillingRateMatrixSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setBillingRateMatrixSk(java.math.BigDecimal billingRateMatrixSk) throws SQLException
  { _struct.setAttribute(0, billingRateMatrixSk); }


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

  public String getBeLocId() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setBeLocId(String beLocId) throws SQLException
  { _struct.setAttribute(11, beLocId); }


  public String getPayerId() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setPayerId(String payerId) throws SQLException
  { _struct.setAttribute(12, payerId); }


  public String getContrId() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setContrId(String contrId) throws SQLException
  { _struct.setAttribute(13, contrId); }


  public java.sql.Timestamp getBillingRateMatrixEffDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(14); }

  public void setBillingRateMatrixEffDate(java.sql.Timestamp billingRateMatrixEffDate) throws SQLException
  { _struct.setAttribute(14, billingRateMatrixEffDate); }


  public java.sql.Timestamp getBillingRateMatrixTermDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(15); }

  public void setBillingRateMatrixTermDate(java.sql.Timestamp billingRateMatrixTermDate) throws SQLException
  { _struct.setAttribute(15, billingRateMatrixTermDate); }


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


  public java.math.BigDecimal getRateAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(19); }

  public void setRateAmt(java.math.BigDecimal rateAmt) throws SQLException
  { _struct.setAttribute(19, rateAmt); }


  public String getSvcUnitName() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setSvcUnitName(String svcUnitName) throws SQLException
  { _struct.setAttribute(20, svcUnitName); }


  public String getBillingCode() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setBillingCode(String billingCode) throws SQLException
  { _struct.setAttribute(21, billingCode); }


  public String getMdfr1Code() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setMdfr1Code(String mdfr1Code) throws SQLException
  { _struct.setAttribute(22, mdfr1Code); }


  public String getMdfr2Code() throws SQLException
  { return (String) _struct.getAttribute(23); }

  public void setMdfr2Code(String mdfr2Code) throws SQLException
  { _struct.setAttribute(23, mdfr2Code); }


  public String getMdfr3Code() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setMdfr3Code(String mdfr3Code) throws SQLException
  { _struct.setAttribute(24, mdfr3Code); }


  public String getMdfr4Code() throws SQLException
  { return (String) _struct.getAttribute(25); }

  public void setMdfr4Code(String mdfr4Code) throws SQLException
  { _struct.setAttribute(25, mdfr4Code); }


  public String getRevCode() throws SQLException
  { return (String) _struct.getAttribute(26); }

  public void setRevCode(String revCode) throws SQLException
  { _struct.setAttribute(26, revCode); }

}
