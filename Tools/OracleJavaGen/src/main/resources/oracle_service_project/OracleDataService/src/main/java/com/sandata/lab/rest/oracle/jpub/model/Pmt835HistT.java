package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class Pmt835HistT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PMT_835_HIST_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,91,91,2,2004 };
  protected static ORADataFactory[] _factory = new ORADataFactory[6];
  protected static final Pmt835HistT _Pmt835HistTFactory = new Pmt835HistT();

  public static ORADataFactory getORADataFactory()
  { return _Pmt835HistTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[6], _sqlType, _factory); }
  public Pmt835HistT()
  { _init_struct(true); }
  public Pmt835HistT(java.math.BigDecimal pmt835HistSk, String pmt835HistId, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal changeVersionId, oracle.sql.BLOB pmt835Rec) throws SQLException
  { _init_struct(true);
    setPmt835HistSk(pmt835HistSk);
    setPmt835HistId(pmt835HistId);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setChangeVersionId(changeVersionId);
    setPmt835Rec(pmt835Rec);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(Pmt835HistT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new Pmt835HistT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPmt835HistSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPmt835HistSk(java.math.BigDecimal pmt835HistSk) throws SQLException
  { _struct.setAttribute(0, pmt835HistSk); }


  public String getPmt835HistId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setPmt835HistId(String pmt835HistId) throws SQLException
  { _struct.setAttribute(1, pmt835HistId); }


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


  public oracle.sql.BLOB getPmt835Rec() throws SQLException
  { return (oracle.sql.BLOB) _struct.getOracleAttribute(5); }

  public void setPmt835Rec(oracle.sql.BLOB pmt835Rec) throws SQLException
  { _struct.setOracleAttribute(5, pmt835Rec); }

}
