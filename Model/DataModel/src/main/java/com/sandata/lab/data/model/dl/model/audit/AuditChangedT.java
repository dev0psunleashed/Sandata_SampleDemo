package com.sandata.lab.data.model.dl.model.audit;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class AuditChangedT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.AUDIT_CHANGED_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 12,12,12,12,12,12,12,91 };
  protected static ORADataFactory[] _factory = new ORADataFactory[8];
  protected static final AuditChangedT _AuditChangedTFactory = new AuditChangedT();

  public static ORADataFactory getORADataFactory()
  { return _AuditChangedTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[8], _sqlType, _factory); }
  public AuditChangedT()
  { _init_struct(true); }
  public AuditChangedT(String userGuid, String userName, String userFirstName, String userLastName, String dataPoint, String changedFrom, String changedTo, java.sql.Timestamp changedOn) throws SQLException
  { _init_struct(true);
    setUserGuid(userGuid);
    setUserName(userName);
    setUserFirstName(userFirstName);
    setUserLastName(userLastName);
    setDataPoint(dataPoint);
    setChangedFrom(changedFrom);
    setChangedTo(changedTo);
    setChangedOn(changedOn);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AuditChangedT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AuditChangedT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public String getUserGuid() throws SQLException
  { return (String) _struct.getAttribute(0); }

  public void setUserGuid(String userGuid) throws SQLException
  { _struct.setAttribute(0, userGuid); }


  public String getUserName() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setUserName(String userName) throws SQLException
  { _struct.setAttribute(1, userName); }


  public String getUserFirstName() throws SQLException
  { return (String) _struct.getAttribute(2); }

  public void setUserFirstName(String userFirstName) throws SQLException
  { _struct.setAttribute(2, userFirstName); }


  public String getUserLastName() throws SQLException
  { return (String) _struct.getAttribute(3); }

  public void setUserLastName(String userLastName) throws SQLException
  { _struct.setAttribute(3, userLastName); }


  public String getDataPoint() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setDataPoint(String dataPoint) throws SQLException
  { _struct.setAttribute(4, dataPoint); }


  public String getChangedFrom() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setChangedFrom(String changedFrom) throws SQLException
  { _struct.setAttribute(5, changedFrom); }


  public String getChangedTo() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setChangedTo(String changedTo) throws SQLException
  { _struct.setAttribute(6, changedTo); }


  public java.sql.Timestamp getChangedOn() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(7); }

  public void setChangedOn(java.sql.Timestamp changedOn) throws SQLException
  { _struct.setAttribute(7, changedOn); }

}
