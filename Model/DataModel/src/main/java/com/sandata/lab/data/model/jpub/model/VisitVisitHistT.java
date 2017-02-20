package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class VisitVisitHistT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.VISIT_VISIT_HIST_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[2];
  protected static final VisitVisitHistT _VisitVisitHistTFactory = new VisitVisitHistT();

  public static ORADataFactory getORADataFactory()
  { return _VisitVisitHistTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[2], _sqlType, _factory); }
  public VisitVisitHistT()
  { _init_struct(true); }
  public VisitVisitHistT(java.math.BigDecimal visitSk, java.math.BigDecimal visitHistSk) throws SQLException
  { _init_struct(true);
    setVisitSk(visitSk);
    setVisitHistSk(visitHistSk);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(VisitVisitHistT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new VisitVisitHistT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getVisitSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setVisitSk(java.math.BigDecimal visitSk) throws SQLException
  { _struct.setAttribute(0, visitSk); }


  public java.math.BigDecimal getVisitHistSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(1); }

  public void setVisitHistSk(java.math.BigDecimal visitHistSk) throws SQLException
  { _struct.setAttribute(1, visitHistSk); }

}
