package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class PrInputTaxDetT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PR_INPUT_TAX_DET_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,2,12,12,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[8];
  protected static final PrInputTaxDetT _PrInputTaxDetTFactory = new PrInputTaxDetT();

  public static ORADataFactory getORADataFactory()
  { return _PrInputTaxDetTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[8], _sqlType, _factory); }
  public PrInputTaxDetT()
  { _init_struct(true); }
  public PrInputTaxDetT(java.math.BigDecimal prInputTaxDetSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal changeVersionId, java.math.BigDecimal prInputSk, String beId, String taxCode, java.math.BigDecimal taxAmt) throws SQLException
  { _init_struct(true);
    setPrInputTaxDetSk(prInputTaxDetSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setChangeVersionId(changeVersionId);
    setPrInputSk(prInputSk);
    setBeId(beId);
    setTaxCode(taxCode);
    setTaxAmt(taxAmt);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PrInputTaxDetT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PrInputTaxDetT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPrInputTaxDetSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPrInputTaxDetSk(java.math.BigDecimal prInputTaxDetSk) throws SQLException
  { _struct.setAttribute(0, prInputTaxDetSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(3, changeVersionId); }


  public java.math.BigDecimal getPrInputSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(4); }

  public void setPrInputSk(java.math.BigDecimal prInputSk) throws SQLException
  { _struct.setAttribute(4, prInputSk); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(5, beId); }


  public String getTaxCode() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setTaxCode(String taxCode) throws SQLException
  { _struct.setAttribute(6, taxCode); }


  public java.math.BigDecimal getTaxAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(7); }

  public void setTaxAmt(java.math.BigDecimal taxAmt) throws SQLException
  { _struct.setAttribute(7, taxAmt); }

}
