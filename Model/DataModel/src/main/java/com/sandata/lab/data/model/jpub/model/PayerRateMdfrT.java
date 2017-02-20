package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class PayerRateMdfrT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PAYER_RATE_MDFR_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,12,12,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[9];
  protected static final PayerRateMdfrT _PayerRateMdfrTFactory = new PayerRateMdfrT();

  public static ORADataFactory getORADataFactory()
  { return _PayerRateMdfrTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[9], _sqlType, _factory); }
  public PayerRateMdfrT()
  { _init_struct(true); }
  public PayerRateMdfrT(java.math.BigDecimal payerRateMdfrSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal changeVersionId, String beId, String payerId, String contrId, String payerRateMdfrCode, String payerRateMdfrDesc) throws SQLException
  { _init_struct(true);
    setPayerRateMdfrSk(payerRateMdfrSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setPayerId(payerId);
    setContrId(contrId);
    setPayerRateMdfrCode(payerRateMdfrCode);
    setPayerRateMdfrDesc(payerRateMdfrDesc);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PayerRateMdfrT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PayerRateMdfrT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPayerRateMdfrSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPayerRateMdfrSk(java.math.BigDecimal payerRateMdfrSk) throws SQLException
  { _struct.setAttribute(0, payerRateMdfrSk); }


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


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(4, beId); }


  public String getPayerId() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setPayerId(String payerId) throws SQLException
  { _struct.setAttribute(5, payerId); }


  public String getContrId() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setContrId(String contrId) throws SQLException
  { _struct.setAttribute(6, contrId); }


  public String getPayerRateMdfrCode() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setPayerRateMdfrCode(String payerRateMdfrCode) throws SQLException
  { _struct.setAttribute(7, payerRateMdfrCode); }


  public String getPayerRateMdfrDesc() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setPayerRateMdfrDesc(String payerRateMdfrDesc) throws SQLException
  { _struct.setAttribute(8, payerRateMdfrDesc); }

}
