package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class AppSecrGrpHistT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.APP_SECR_GRP_HIST_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,12,2,91,91,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[7];
  protected static final AppSecrGrpHistT _AppSecrGrpHistTFactory = new AppSecrGrpHistT();

  public static ORADataFactory getORADataFactory()
  { return _AppSecrGrpHistTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[7], _sqlType, _factory); }
  public AppSecrGrpHistT()
  { _init_struct(true); }
  public AppSecrGrpHistT(java.math.BigDecimal appSecrGrpHistSk, java.sql.Timestamp recChangeTmstp, String recChangeCode, java.math.BigDecimal appSecrGrpSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String secrGrpName) throws SQLException
  { _init_struct(true);
    setAppSecrGrpHistSk(appSecrGrpHistSk);
    setRecChangeTmstp(recChangeTmstp);
    setRecChangeCode(recChangeCode);
    setAppSecrGrpSk(appSecrGrpSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setSecrGrpName(secrGrpName);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AppSecrGrpHistT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AppSecrGrpHistT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAppSecrGrpHistSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAppSecrGrpHistSk(java.math.BigDecimal appSecrGrpHistSk) throws SQLException
  { _struct.setAttribute(0, appSecrGrpHistSk); }


  public java.sql.Timestamp getRecChangeTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecChangeTmstp(java.sql.Timestamp recChangeTmstp) throws SQLException
  { _struct.setAttribute(1, recChangeTmstp); }


  public String getRecChangeCode() throws SQLException
  { return (String) _struct.getAttribute(2); }

  public void setRecChangeCode(String recChangeCode) throws SQLException
  { _struct.setAttribute(2, recChangeCode); }


  public java.math.BigDecimal getAppSecrGrpSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setAppSecrGrpSk(java.math.BigDecimal appSecrGrpSk) throws SQLException
  { _struct.setAttribute(3, appSecrGrpSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(4); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(4, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(5); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(5, recUpdateTmstp); }


  public String getSecrGrpName() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setSecrGrpName(String secrGrpName) throws SQLException
  { _struct.setAttribute(6, secrGrpName); }

}
