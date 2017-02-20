package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class SvcVisitT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.SVC_VISIT_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,2,2,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[9];
  protected static final SvcVisitT _SvcVisitTFactory = new SvcVisitT();

  public static ORADataFactory getORADataFactory()
  { return _SvcVisitTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[9], _sqlType, _factory); }
  public SvcVisitT()
  { _init_struct(true); }
  public SvcVisitT(java.math.BigDecimal svcVisitSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal changeVersionId, java.math.BigDecimal svcSk, java.math.BigDecimal visitSk, String beId, String rateTypName, String rateQlfrCode) throws SQLException
  { _init_struct(true);
    setSvcVisitSk(svcVisitSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setChangeVersionId(changeVersionId);
    setSvcSk(svcSk);
    setVisitSk(visitSk);
    setBeId(beId);
    setRateTypName(rateTypName);
    setRateQlfrCode(rateQlfrCode);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(SvcVisitT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new SvcVisitT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getSvcVisitSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setSvcVisitSk(java.math.BigDecimal svcVisitSk) throws SQLException
  { _struct.setAttribute(0, svcVisitSk); }


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


  public java.math.BigDecimal getSvcSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(4); }

  public void setSvcSk(java.math.BigDecimal svcSk) throws SQLException
  { _struct.setAttribute(4, svcSk); }


  public java.math.BigDecimal getVisitSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(5); }

  public void setVisitSk(java.math.BigDecimal visitSk) throws SQLException
  { _struct.setAttribute(5, visitSk); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(6, beId); }


  public String getRateTypName() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setRateTypName(String rateTypName) throws SQLException
  { _struct.setAttribute(7, rateTypName); }


  public String getRateQlfrCode() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setRateQlfrCode(String rateQlfrCode) throws SQLException
  { _struct.setAttribute(8, rateQlfrCode); }

}
