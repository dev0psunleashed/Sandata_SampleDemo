package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class AppAuditT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.APP_AUDIT_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,12,2,12,12,12,2,12,2,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[13];
  protected static final AppAuditT _AppAuditTFactory = new AppAuditT();

  public static ORADataFactory getORADataFactory()
  { return _AppAuditTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[13], _sqlType, _factory); }
  public AppAuditT()
  { _init_struct(true); }
  public AppAuditT(java.math.BigDecimal appAuditSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal appSessSk, String userGuid, java.math.BigDecimal appPrivSk, String auditHost, String dataSecCompTypName, String dataSecClasId, java.math.BigDecimal appDataStrucSk, String appDataStrucEltVal, java.math.BigDecimal auditDataStrucReadInd, java.math.BigDecimal auditDataStrucWriteInd) throws SQLException
  { _init_struct(true);
    setAppAuditSk(appAuditSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setAppSessSk(appSessSk);
    setUserGuid(userGuid);
    setAppPrivSk(appPrivSk);
    setAuditHost(auditHost);
    setDataSecCompTypName(dataSecCompTypName);
    setDataSecClasId(dataSecClasId);
    setAppDataStrucSk(appDataStrucSk);
    setAppDataStrucEltVal(appDataStrucEltVal);
    setAuditDataStrucReadInd(auditDataStrucReadInd);
    setAuditDataStrucWriteInd(auditDataStrucWriteInd);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AppAuditT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AppAuditT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAppAuditSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAppAuditSk(java.math.BigDecimal appAuditSk) throws SQLException
  { _struct.setAttribute(0, appAuditSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public java.math.BigDecimal getAppSessSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setAppSessSk(java.math.BigDecimal appSessSk) throws SQLException
  { _struct.setAttribute(3, appSessSk); }


  public String getUserGuid() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setUserGuid(String userGuid) throws SQLException
  { _struct.setAttribute(4, userGuid); }


  public java.math.BigDecimal getAppPrivSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(5); }

  public void setAppPrivSk(java.math.BigDecimal appPrivSk) throws SQLException
  { _struct.setAttribute(5, appPrivSk); }


  public String getAuditHost() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setAuditHost(String auditHost) throws SQLException
  { _struct.setAttribute(6, auditHost); }


  public String getDataSecCompTypName() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setDataSecCompTypName(String dataSecCompTypName) throws SQLException
  { _struct.setAttribute(7, dataSecCompTypName); }


  public String getDataSecClasId() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setDataSecClasId(String dataSecClasId) throws SQLException
  { _struct.setAttribute(8, dataSecClasId); }


  public java.math.BigDecimal getAppDataStrucSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(9); }

  public void setAppDataStrucSk(java.math.BigDecimal appDataStrucSk) throws SQLException
  { _struct.setAttribute(9, appDataStrucSk); }


  public String getAppDataStrucEltVal() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setAppDataStrucEltVal(String appDataStrucEltVal) throws SQLException
  { _struct.setAttribute(10, appDataStrucEltVal); }


  public java.math.BigDecimal getAuditDataStrucReadInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(11); }

  public void setAuditDataStrucReadInd(java.math.BigDecimal auditDataStrucReadInd) throws SQLException
  { _struct.setAttribute(11, auditDataStrucReadInd); }


  public java.math.BigDecimal getAuditDataStrucWriteInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(12); }

  public void setAuditDataStrucWriteInd(java.math.BigDecimal auditDataStrucWriteInd) throws SQLException
  { _struct.setAttribute(12, auditDataStrucWriteInd); }

}
