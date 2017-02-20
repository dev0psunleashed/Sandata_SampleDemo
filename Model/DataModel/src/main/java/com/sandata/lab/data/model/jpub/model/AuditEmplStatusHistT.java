package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class AuditEmplStatusHistT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.AUDIT_EMPL_STATUS_HIST_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 12,12,91,12,12,12,91 };
  protected static ORADataFactory[] _factory = new ORADataFactory[7];
  protected static final AuditEmplStatusHistT _AuditEmplStatusHistTFactory = new AuditEmplStatusHistT();

  public static ORADataFactory getORADataFactory()
  { return _AuditEmplStatusHistTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[7], _sqlType, _factory); }
  public AuditEmplStatusHistT()
  { _init_struct(true); }
  public AuditEmplStatusHistT(String auditHost, String userGuid, java.sql.Timestamp effectiveDate, String status, String reasonCode, String notes, java.sql.Timestamp modified) throws SQLException
  { _init_struct(true);
    setAuditHost(auditHost);
    setUserGuid(userGuid);
    setEffectiveDate(effectiveDate);
    setStatus(status);
    setReasonCode(reasonCode);
    setNotes(notes);
    setModified(modified);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AuditEmplStatusHistT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AuditEmplStatusHistT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public String getAuditHost() throws SQLException
  { return (String) _struct.getAttribute(0); }

  public void setAuditHost(String auditHost) throws SQLException
  { _struct.setAttribute(0, auditHost); }


  public String getUserGuid() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setUserGuid(String userGuid) throws SQLException
  { _struct.setAttribute(1, userGuid); }


  public java.sql.Timestamp getEffectiveDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setEffectiveDate(java.sql.Timestamp effectiveDate) throws SQLException
  { _struct.setAttribute(2, effectiveDate); }


  public String getStatus() throws SQLException
  { return (String) _struct.getAttribute(3); }

  public void setStatus(String status) throws SQLException
  { _struct.setAttribute(3, status); }


  public String getReasonCode() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setReasonCode(String reasonCode) throws SQLException
  { _struct.setAttribute(4, reasonCode); }


  public String getNotes() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setNotes(String notes) throws SQLException
  { _struct.setAttribute(5, notes); }


  public java.sql.Timestamp getModified() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(6); }

  public void setModified(java.sql.Timestamp modified) throws SQLException
  { _struct.setAttribute(6, modified); }

}
