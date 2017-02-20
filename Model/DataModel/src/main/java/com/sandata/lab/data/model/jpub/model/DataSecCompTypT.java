package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class DataSecCompTypT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.DATA_SEC_COMP_TYP_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[5];
  protected static final DataSecCompTypT _DataSecCompTypTFactory = new DataSecCompTypT();

  public static ORADataFactory getORADataFactory()
  { return _DataSecCompTypTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[5], _sqlType, _factory); }
  public DataSecCompTypT()
  { _init_struct(true); }
  public DataSecCompTypT(java.math.BigDecimal dataSecCompTypSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String dataSecCompTypName, String dataSecCompTypDesc) throws SQLException
  { _init_struct(true);
    setDataSecCompTypSk(dataSecCompTypSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setDataSecCompTypName(dataSecCompTypName);
    setDataSecCompTypDesc(dataSecCompTypDesc);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(DataSecCompTypT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new DataSecCompTypT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getDataSecCompTypSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setDataSecCompTypSk(java.math.BigDecimal dataSecCompTypSk) throws SQLException
  { _struct.setAttribute(0, dataSecCompTypSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public String getDataSecCompTypName() throws SQLException
  { return (String) _struct.getAttribute(3); }

  public void setDataSecCompTypName(String dataSecCompTypName) throws SQLException
  { _struct.setAttribute(3, dataSecCompTypName); }


  public String getDataSecCompTypDesc() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setDataSecCompTypDesc(String dataSecCompTypDesc) throws SQLException
  { _struct.setAttribute(4, dataSecCompTypDesc); }

}
