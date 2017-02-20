package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class AppDataStrucSecMapT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.APP_DATA_STRUC_SEC_MAP_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,12,12,2,2,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[8];
  protected static final AppDataStrucSecMapT _AppDataStrucSecMapTFactory = new AppDataStrucSecMapT();

  public static ORADataFactory getORADataFactory()
  { return _AppDataStrucSecMapTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[8], _sqlType, _factory); }
  public AppDataStrucSecMapT()
  { _init_struct(true); }
  public AppDataStrucSecMapT(java.math.BigDecimal appDataStrucSecMapSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String dataSecCompTypName, String dataSecClasId, java.math.BigDecimal appDataStrucSk, java.math.BigDecimal dataStrucReadInd, java.math.BigDecimal dataStrucWriteInd) throws SQLException
  { _init_struct(true);
    setAppDataStrucSecMapSk(appDataStrucSecMapSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setDataSecCompTypName(dataSecCompTypName);
    setDataSecClasId(dataSecClasId);
    setAppDataStrucSk(appDataStrucSk);
    setDataStrucReadInd(dataStrucReadInd);
    setDataStrucWriteInd(dataStrucWriteInd);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AppDataStrucSecMapT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AppDataStrucSecMapT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAppDataStrucSecMapSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAppDataStrucSecMapSk(java.math.BigDecimal appDataStrucSecMapSk) throws SQLException
  { _struct.setAttribute(0, appDataStrucSecMapSk); }


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


  public String getDataSecClasId() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setDataSecClasId(String dataSecClasId) throws SQLException
  { _struct.setAttribute(4, dataSecClasId); }


  public java.math.BigDecimal getAppDataStrucSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(5); }

  public void setAppDataStrucSk(java.math.BigDecimal appDataStrucSk) throws SQLException
  { _struct.setAttribute(5, appDataStrucSk); }


  public java.math.BigDecimal getDataStrucReadInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(6); }

  public void setDataStrucReadInd(java.math.BigDecimal dataStrucReadInd) throws SQLException
  { _struct.setAttribute(6, dataStrucReadInd); }


  public java.math.BigDecimal getDataStrucWriteInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(7); }

  public void setDataStrucWriteInd(java.math.BigDecimal dataStrucWriteInd) throws SQLException
  { _struct.setAttribute(7, dataStrucWriteInd); }

}
