package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class RprtReqAttrT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.RPRT_REQ_ATTR_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,12,2,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[7];
  protected static final RprtReqAttrT _RprtReqAttrTFactory = new RprtReqAttrT();

  public static ORADataFactory getORADataFactory()
  { return _RprtReqAttrTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[7], _sqlType, _factory); }
  public RprtReqAttrT()
  { _init_struct(true); }
  public RprtReqAttrT(java.math.BigDecimal rprtReqAttrSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String rprtId, java.math.BigDecimal rprtReqSk, String rprtReqAttrKeyName, String rprtReqAttrKeyVal) throws SQLException
  { _init_struct(true);
    setRprtReqAttrSk(rprtReqAttrSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRprtId(rprtId);
    setRprtReqSk(rprtReqSk);
    setRprtReqAttrKeyName(rprtReqAttrKeyName);
    setRprtReqAttrKeyVal(rprtReqAttrKeyVal);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(RprtReqAttrT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new RprtReqAttrT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getRprtReqAttrSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setRprtReqAttrSk(java.math.BigDecimal rprtReqAttrSk) throws SQLException
  { _struct.setAttribute(0, rprtReqAttrSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public String getRprtId() throws SQLException
  { return (String) _struct.getAttribute(3); }

  public void setRprtId(String rprtId) throws SQLException
  { _struct.setAttribute(3, rprtId); }


  public java.math.BigDecimal getRprtReqSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(4); }

  public void setRprtReqSk(java.math.BigDecimal rprtReqSk) throws SQLException
  { _struct.setAttribute(4, rprtReqSk); }


  public String getRprtReqAttrKeyName() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setRprtReqAttrKeyName(String rprtReqAttrKeyName) throws SQLException
  { _struct.setAttribute(5, rprtReqAttrKeyName); }


  public String getRprtReqAttrKeyVal() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setRprtReqAttrKeyVal(String rprtReqAttrKeyVal) throws SQLException
  { _struct.setAttribute(6, rprtReqAttrKeyVal); }

}
