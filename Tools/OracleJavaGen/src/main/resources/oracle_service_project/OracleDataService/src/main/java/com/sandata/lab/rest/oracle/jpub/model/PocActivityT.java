package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class PocActivityT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.POC_ACTIVITY_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,2,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[6];
  protected static final PocActivityT _PocActivityTFactory = new PocActivityT();

  public static ORADataFactory getORADataFactory()
  { return _PocActivityTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[6], _sqlType, _factory); }
  public PocActivityT()
  { _init_struct(true); }
  public PocActivityT(java.math.BigDecimal pocActivitySk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal changeVersionId, java.math.BigDecimal activitySk, java.math.BigDecimal pocSk) throws SQLException
  { _init_struct(true);
    setPocActivitySk(pocActivitySk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setChangeVersionId(changeVersionId);
    setActivitySk(activitySk);
    setPocSk(pocSk);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PocActivityT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PocActivityT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPocActivitySk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPocActivitySk(java.math.BigDecimal pocActivitySk) throws SQLException
  { _struct.setAttribute(0, pocActivitySk); }


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


  public java.math.BigDecimal getActivitySk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(4); }

  public void setActivitySk(java.math.BigDecimal activitySk) throws SQLException
  { _struct.setAttribute(4, activitySk); }


  public java.math.BigDecimal getPocSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(5); }

  public void setPocSk(java.math.BigDecimal pocSk) throws SQLException
  { _struct.setAttribute(5, pocSk); }

}
