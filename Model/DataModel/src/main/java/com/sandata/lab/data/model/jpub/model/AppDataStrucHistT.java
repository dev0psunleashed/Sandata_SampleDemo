package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class AppDataStrucHistT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.APP_DATA_STRUC_HIST_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,12,2,91,91,2,2,12,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[12];
  protected static final AppDataStrucHistT _AppDataStrucHistTFactory = new AppDataStrucHistT();

  public static ORADataFactory getORADataFactory()
  { return _AppDataStrucHistTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[12], _sqlType, _factory); }
  public AppDataStrucHistT()
  { _init_struct(true); }
  public AppDataStrucHistT(java.math.BigDecimal appDataStrucHistSk, java.sql.Timestamp recChangeTmstp, String recChangeCode, java.math.BigDecimal appDataStrucSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal appDataStrucParSk, java.math.BigDecimal appSecrGrpSk, String lgclEltTypName, String lgclEltName, String physEltTypName, String physEltName) throws SQLException
  { _init_struct(true);
    setAppDataStrucHistSk(appDataStrucHistSk);
    setRecChangeTmstp(recChangeTmstp);
    setRecChangeCode(recChangeCode);
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
  protected ORAData create(AppDataStrucHistT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AppDataStrucHistT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAppDataStrucHistSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAppDataStrucHistSk(java.math.BigDecimal appDataStrucHistSk) throws SQLException
  { _struct.setAttribute(0, appDataStrucHistSk); }


  public java.sql.Timestamp getRecChangeTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecChangeTmstp(java.sql.Timestamp recChangeTmstp) throws SQLException
  { _struct.setAttribute(1, recChangeTmstp); }


  public String getRecChangeCode() throws SQLException
  { return (String) _struct.getAttribute(2); }

  public void setRecChangeCode(String recChangeCode) throws SQLException
  { _struct.setAttribute(2, recChangeCode); }


  public java.math.BigDecimal getAppDataStrucSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setAppDataStrucSk(java.math.BigDecimal appDataStrucSk) throws SQLException
  { _struct.setAttribute(3, appDataStrucSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(4); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(4, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(5); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(5, recUpdateTmstp); }


  public java.math.BigDecimal getAppDataStrucParSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(6); }

  public void setAppDataStrucParSk(java.math.BigDecimal appDataStrucParSk) throws SQLException
  { _struct.setAttribute(6, appDataStrucParSk); }


  public java.math.BigDecimal getAppSecrGrpSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(7); }

  public void setAppSecrGrpSk(java.math.BigDecimal appSecrGrpSk) throws SQLException
  { _struct.setAttribute(7, appSecrGrpSk); }


  public String getLgclEltTypName() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setLgclEltTypName(String lgclEltTypName) throws SQLException
  { _struct.setAttribute(8, lgclEltTypName); }


  public String getLgclEltName() throws SQLException
  { return (String) _struct.getAttribute(9); }

  public void setLgclEltName(String lgclEltName) throws SQLException
  { _struct.setAttribute(9, lgclEltName); }


  public String getPhysEltTypName() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setPhysEltTypName(String physEltTypName) throws SQLException
  { _struct.setAttribute(10, physEltTypName); }


  public String getPhysEltName() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setPhysEltName(String physEltName) throws SQLException
  { _struct.setAttribute(11, physEltName); }

}
