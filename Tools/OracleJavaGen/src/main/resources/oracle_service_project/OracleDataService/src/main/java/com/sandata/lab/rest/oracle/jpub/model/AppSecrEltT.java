package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class AppSecrEltT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.APP_SECR_ELT_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,2,2,2,12,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[9];
  protected static final AppSecrEltT _AppSecrEltTFactory = new AppSecrEltT();

  public static ORADataFactory getORADataFactory()
  { return _AppSecrEltTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[9], _sqlType, _factory); }
  public AppSecrEltT()
  { _init_struct(true); }
  public AppSecrEltT(java.math.BigDecimal appSecrEltSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal appTenantSk, java.math.BigDecimal appFunEltSk, java.math.BigDecimal appFunSk, java.math.BigDecimal appModSk, String secrEltName, java.math.BigDecimal inclInhcInd) throws SQLException
  { _init_struct(true);
    setAppSecrEltSk(appSecrEltSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setAppTenantSk(appTenantSk);
    setAppFunEltSk(appFunEltSk);
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
  protected ORAData create(AppSecrEltT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AppSecrEltT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAppSecrEltSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAppSecrEltSk(java.math.BigDecimal appSecrEltSk) throws SQLException
  { _struct.setAttribute(0, appSecrEltSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public java.math.BigDecimal getAppTenantSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setAppTenantSk(java.math.BigDecimal appTenantSk) throws SQLException
  { _struct.setAttribute(3, appTenantSk); }


  public java.math.BigDecimal getAppFunEltSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(4); }

  public void setAppFunEltSk(java.math.BigDecimal appFunEltSk) throws SQLException
  { _struct.setAttribute(4, appFunEltSk); }


  public java.math.BigDecimal getAppFunSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(5); }

  public void setAppFunSk(java.math.BigDecimal appFunSk) throws SQLException
  { _struct.setAttribute(5, appFunSk); }


  public java.math.BigDecimal getAppModSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(6); }

  public void setAppModSk(java.math.BigDecimal appModSk) throws SQLException
  { _struct.setAttribute(6, appModSk); }


  public String getSecrEltName() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setSecrEltName(String secrEltName) throws SQLException
  { _struct.setAttribute(7, secrEltName); }


  public java.math.BigDecimal getInclInhcInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(8); }

  public void setInclInhcInd(java.math.BigDecimal inclInhcInd) throws SQLException
  { _struct.setAttribute(8, inclInhcInd); }

}
