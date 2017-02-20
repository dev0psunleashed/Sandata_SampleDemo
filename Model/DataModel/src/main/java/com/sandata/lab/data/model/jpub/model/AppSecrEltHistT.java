package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class AppSecrEltHistT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.APP_SECR_ELT_HIST_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,12,2,91,91,2,2,2,2,12,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[12];
  protected static final AppSecrEltHistT _AppSecrEltHistTFactory = new AppSecrEltHistT();

  public static ORADataFactory getORADataFactory()
  { return _AppSecrEltHistTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[12], _sqlType, _factory); }
  public AppSecrEltHistT()
  { _init_struct(true); }
  public AppSecrEltHistT(java.math.BigDecimal appSecrEltHistSk, java.sql.Timestamp recChangeTmstp, String recChangeCode, java.math.BigDecimal appSecrEltSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal appTenantSk, java.math.BigDecimal appFuncEltSk, java.math.BigDecimal appFunSk, java.math.BigDecimal appModSk, String secrEltName, java.math.BigDecimal inclInhcInd) throws SQLException
  { _init_struct(true);
    setAppSecrEltHistSk(appSecrEltHistSk);
    setRecChangeTmstp(recChangeTmstp);
    setRecChangeCode(recChangeCode);
    setAppSecrEltSk(appSecrEltSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setAppTenantSk(appTenantSk);
    setAppFuncEltSk(appFuncEltSk);
    setAppFunSk(appFunSk);
    setAppModSk(appModSk);
    setSecrEltName(secrEltName);
    setInclInhcInd(inclInhcInd);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AppSecrEltHistT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AppSecrEltHistT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAppSecrEltHistSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAppSecrEltHistSk(java.math.BigDecimal appSecrEltHistSk) throws SQLException
  { _struct.setAttribute(0, appSecrEltHistSk); }


  public java.sql.Timestamp getRecChangeTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecChangeTmstp(java.sql.Timestamp recChangeTmstp) throws SQLException
  { _struct.setAttribute(1, recChangeTmstp); }


  public String getRecChangeCode() throws SQLException
  { return (String) _struct.getAttribute(2); }

  public void setRecChangeCode(String recChangeCode) throws SQLException
  { _struct.setAttribute(2, recChangeCode); }


  public java.math.BigDecimal getAppSecrEltSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setAppSecrEltSk(java.math.BigDecimal appSecrEltSk) throws SQLException
  { _struct.setAttribute(3, appSecrEltSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(4); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(4, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(5); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(5, recUpdateTmstp); }


  public java.math.BigDecimal getAppTenantSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(6); }

  public void setAppTenantSk(java.math.BigDecimal appTenantSk) throws SQLException
  { _struct.setAttribute(6, appTenantSk); }


  public java.math.BigDecimal getAppFuncEltSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(7); }

  public void setAppFuncEltSk(java.math.BigDecimal appFuncEltSk) throws SQLException
  { _struct.setAttribute(7, appFuncEltSk); }


  public java.math.BigDecimal getAppFunSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(8); }

  public void setAppFunSk(java.math.BigDecimal appFunSk) throws SQLException
  { _struct.setAttribute(8, appFunSk); }


  public java.math.BigDecimal getAppModSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(9); }

  public void setAppModSk(java.math.BigDecimal appModSk) throws SQLException
  { _struct.setAttribute(9, appModSk); }


  public String getSecrEltName() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setSecrEltName(String secrEltName) throws SQLException
  { _struct.setAttribute(10, secrEltName); }


  public java.math.BigDecimal getInclInhcInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(11); }

  public void setInclInhcInd(java.math.BigDecimal inclInhcInd) throws SQLException
  { _struct.setAttribute(11, inclInhcInd); }

}
