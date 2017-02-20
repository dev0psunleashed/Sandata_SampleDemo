package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class Payment835BatchT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PAYMENT_835_BATCH_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,91,91,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[5];
  protected static final Payment835BatchT _Payment835BatchTFactory = new Payment835BatchT();

  public static ORADataFactory getORADataFactory()
  { return _Payment835BatchTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[5], _sqlType, _factory); }
  public Payment835BatchT()
  { _init_struct(true); }
  public Payment835BatchT(java.math.BigDecimal payment835BatchSk, String payment835BatchId, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal changeVersionId) throws SQLException
  { _init_struct(true);
    setPayment835BatchSk(payment835BatchSk);
    setPayment835BatchId(payment835BatchId);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setChangeVersionId(changeVersionId);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(Payment835BatchT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new Payment835BatchT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPayment835BatchSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPayment835BatchSk(java.math.BigDecimal payment835BatchSk) throws SQLException
  { _struct.setAttribute(0, payment835BatchSk); }


  public String getPayment835BatchId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setPayment835BatchId(String payment835BatchId) throws SQLException
  { _struct.setAttribute(1, payment835BatchId); }


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

}
