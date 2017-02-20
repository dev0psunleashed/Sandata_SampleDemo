package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class RprtAttrT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.RPRT_ATTR_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[6];
  protected static final RprtAttrT _RprtAttrTFactory = new RprtAttrT();

  public static ORADataFactory getORADataFactory()
  { return _RprtAttrTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[6], _sqlType, _factory); }
  public RprtAttrT()
  { _init_struct(true); }
  public RprtAttrT(java.math.BigDecimal rprtAttrSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String rprtId, String rprtAttrKeyName, String rprtAttrKeyVal) throws SQLException
  { _init_struct(true);
    setRprtAttrSk(rprtAttrSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRprtId(rprtId);
    setRprtAttrKeyName(rprtAttrKeyName);
    setRprtAttrKeyVal(rprtAttrKeyVal);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(RprtAttrT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new RprtAttrT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getRprtAttrSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setRprtAttrSk(java.math.BigDecimal rprtAttrSk) throws SQLException
  { _struct.setAttribute(0, rprtAttrSk); }


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


  public String getRprtAttrKeyName() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setRprtAttrKeyName(String rprtAttrKeyName) throws SQLException
  { _struct.setAttribute(4, rprtAttrKeyName); }


  public String getRprtAttrKeyVal() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setRprtAttrKeyVal(String rprtAttrKeyVal) throws SQLException
  { _struct.setAttribute(5, rprtAttrKeyVal); }

}
