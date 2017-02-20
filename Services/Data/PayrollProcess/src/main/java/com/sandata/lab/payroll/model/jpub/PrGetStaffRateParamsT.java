package com.sandata.lab.payroll.model.jpub;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class PrGetStaffRateParamsT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PR_GET_STAFF_RATE_PARAMS_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 12,12,12,12,12,12,12,12,2,91 };
  protected static ORADataFactory[] _factory = new ORADataFactory[10];
  protected static final PrGetStaffRateParamsT _PrGetStaffRateParamsTFactory = new PrGetStaffRateParamsT();

  public static ORADataFactory getORADataFactory()
  { return _PrGetStaffRateParamsTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[10], _sqlType, _factory); }
  public PrGetStaffRateParamsT()
  { _init_struct(true); }
  public PrGetStaffRateParamsT(String beId, String beLocId, String beLobId, String ptId, String staffId, String svcName, String rateTypName, String rateSubTypName, java.math.BigDecimal visitSk, java.sql.Timestamp visitDate) throws SQLException
  { _init_struct(true);
    setBeId(beId);
    setBeLocId(beLocId);
    setBeLobId(beLobId);
    setPtId(ptId);
    setStaffId(staffId);
    setSvcName(svcName);
    setRateTypName(rateTypName);
    setRateSubTypName(rateSubTypName);
    setVisitSk(visitSk);
    setVisitDate(visitDate);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PrGetStaffRateParamsT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PrGetStaffRateParamsT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(0); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(0, beId); }


  public String getBeLocId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setBeLocId(String beLocId) throws SQLException
  { _struct.setAttribute(1, beLocId); }


  public String getBeLobId() throws SQLException
  { return (String) _struct.getAttribute(2); }

  public void setBeLobId(String beLobId) throws SQLException
  { _struct.setAttribute(2, beLobId); }


  public String getPtId() throws SQLException
  { return (String) _struct.getAttribute(3); }

  public void setPtId(String ptId) throws SQLException
  { _struct.setAttribute(3, ptId); }


  public String getStaffId() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setStaffId(String staffId) throws SQLException
  { _struct.setAttribute(4, staffId); }


  public String getSvcName() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setSvcName(String svcName) throws SQLException
  { _struct.setAttribute(5, svcName); }


  public String getRateTypName() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setRateTypName(String rateTypName) throws SQLException
  { _struct.setAttribute(6, rateTypName); }


  public String getRateSubTypName() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setRateSubTypName(String rateSubTypName) throws SQLException
  { _struct.setAttribute(7, rateSubTypName); }


  public java.math.BigDecimal getVisitSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(8); }

  public void setVisitSk(java.math.BigDecimal visitSk) throws SQLException
  { _struct.setAttribute(8, visitSk); }


  public java.sql.Timestamp getVisitDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(9); }

  public void setVisitDate(java.sql.Timestamp visitDate) throws SQLException
  { _struct.setAttribute(9, visitDate); }

}
