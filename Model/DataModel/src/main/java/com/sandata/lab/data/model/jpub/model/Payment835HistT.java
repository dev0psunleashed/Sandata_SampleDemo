package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class Payment835HistT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PAYMENT_835_HIST_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,91,91,2,2004 };
  protected static ORADataFactory[] _factory = new ORADataFactory[6];
  protected static final Payment835HistT _Payment835HistTFactory = new Payment835HistT();

  public static ORADataFactory getORADataFactory()
  { return _Payment835HistTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[6], _sqlType, _factory); }
  public Payment835HistT()
  { _init_struct(true); }
  public Payment835HistT(java.math.BigDecimal payment835HistSk, String payment835HistId, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal changeVersionId, oracle.sql.BLOB payment835Rec) throws SQLException
  { _init_struct(true);
    setPayment835HistSk(payment835HistSk);
    setPayment835HistId(payment835HistId);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setChangeVersionId(changeVersionId);
    setPayment835Rec(payment835Rec);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(Payment835HistT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new Payment835HistT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPayment835HistSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPayment835HistSk(java.math.BigDecimal payment835HistSk) throws SQLException
  { _struct.setAttribute(0, payment835HistSk); }


  public String getPayment835HistId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setPayment835HistId(String payment835HistId) throws SQLException
  { _struct.setAttribute(1, payment835HistId); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(2, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(3); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(3, recUpdateTmstp); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(4); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(4, changeVersionId); }


  public oracle.sql.BLOB getPayment835Rec() throws SQLException
  { return (oracle.sql.BLOB) _struct.getOracleAttribute(5); }

  public void setPayment835Rec(oracle.sql.BLOB payment835Rec) throws SQLException
  { _struct.setOracleAttribute(5, payment835Rec); }

}
