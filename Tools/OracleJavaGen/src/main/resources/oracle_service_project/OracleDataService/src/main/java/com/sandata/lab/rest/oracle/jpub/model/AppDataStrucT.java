package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class AppDataStrucT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.APP_DATA_STRUC_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,2,12,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[9];
  protected static final AppDataStrucT _AppDataStrucTFactory = new AppDataStrucT();

  public static ORADataFactory getORADataFactory()
  { return _AppDataStrucTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[9], _sqlType, _factory); }
  public AppDataStrucT()
  { _init_struct(true); }
  public AppDataStrucT(java.math.BigDecimal appDataStrucSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal appDataStrucParSk, java.math.BigDecimal appSecrGrpSk, String lgclEltTypName, String lgclEltName, String physEltTypName, String physEltName) throws SQLException
  { _init_struct(true);
    setAppDataStrucSk(appDataStrucSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setAppDataStrucParSk(appDataStrucParSk);
    setAppSecrGrpSk(appSecrGrpSk);
    setLgclEltTypName(lgclEltTypName);
    setLgclEltName(lgclEltName);
    setPhysEltTypName(physEltTypName);
    setPhysEltName(physEltName);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AppDataStrucT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AppDataStrucT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAppDataStrucSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAppDataStrucSk(java.math.BigDecimal appDataStrucSk) throws SQLException
  { _struct.setAttribute(0, appDataStrucSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public java.math.BigDecimal getAppDataStrucParSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setAppDataStrucParSk(java.math.BigDecimal appDataStrucParSk) throws SQLException
  { _struct.setAttribute(3, appDataStrucParSk); }


  public java.math.BigDecimal getAppSecrGrpSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(4); }

  public void setAppSecrGrpSk(java.math.BigDecimal appSecrGrpSk) throws SQLException
  { _struct.setAttribute(4, appSecrGrpSk); }


  public String getLgclEltTypName() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setLgclEltTypName(String lgclEltTypName) throws SQLException
  { _struct.setAttribute(5, lgclEltTypName); }


  public String getLgclEltName() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setLgclEltName(String lgclEltName) throws SQLException
  { _struct.setAttribute(6, lgclEltName); }


  public String getPhysEltTypName() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setPhysEltTypName(String physEltTypName) throws SQLException
  { _struct.setAttribute(7, physEltTypName); }


  public String getPhysEltName() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setPhysEltName(String physEltName) throws SQLException
  { _struct.setAttribute(8, physEltName); }

}
