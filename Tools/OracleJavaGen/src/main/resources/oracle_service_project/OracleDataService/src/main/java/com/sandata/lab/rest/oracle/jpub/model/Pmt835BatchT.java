package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class Pmt835BatchT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PMT_835_BATCH_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,91,91,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[5];
  protected static final Pmt835BatchT _Pmt835BatchTFactory = new Pmt835BatchT();

  public static ORADataFactory getORADataFactory()
  { return _Pmt835BatchTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[5], _sqlType, _factory); }
  public Pmt835BatchT()
  { _init_struct(true); }
  public Pmt835BatchT(java.math.BigDecimal pmt835BatchSk, String pmt835BatchId, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal changeVersionId) throws SQLException
  { _init_struct(true);
    setPmt835BatchSk(pmt835BatchSk);
    setPmt835BatchId(pmt835BatchId);
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
  protected ORAData create(Pmt835BatchT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new Pmt835BatchT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPmt835BatchSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPmt835BatchSk(java.math.BigDecimal pmt835BatchSk) throws SQLException
  { _struct.setAttribute(0, pmt835BatchSk); }


  public String getPmt835BatchId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setPmt835BatchId(String pmt835BatchId) throws SQLException
  { _struct.setAttribute(1, pmt835BatchId); }


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
