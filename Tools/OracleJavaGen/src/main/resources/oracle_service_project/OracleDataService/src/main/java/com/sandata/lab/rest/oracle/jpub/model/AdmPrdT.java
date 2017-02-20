package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class AdmPrdT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.ADM_PRD_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,91,91,2,2,91,91 };
  protected static ORADataFactory[] _factory = new ORADataFactory[8];
  protected static final AdmPrdT _AdmPrdTFactory = new AdmPrdT();

  public static ORADataFactory getORADataFactory()
  { return _AdmPrdTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[8], _sqlType, _factory); }
  public AdmPrdT()
  { _init_struct(true); }
  public AdmPrdT(java.math.BigDecimal admPrdSk, String admPrdId, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal changeVersionId, java.math.BigDecimal ptIntakeSk, java.sql.Timestamp admPrdEffDate, java.sql.Timestamp admPrdTermDate) throws SQLException
  { _init_struct(true);
    setAdmPrdSk(admPrdSk);
    setAdmPrdId(admPrdId);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setChangeVersionId(changeVersionId);
    setPtIntakeSk(ptIntakeSk);
    setAdmPrdEffDate(admPrdEffDate);
    setAdmPrdTermDate(admPrdTermDate);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AdmPrdT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AdmPrdT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAdmPrdSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAdmPrdSk(java.math.BigDecimal admPrdSk) throws SQLException
  { _struct.setAttribute(0, admPrdSk); }


  public String getAdmPrdId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setAdmPrdId(String admPrdId) throws SQLException
  { _struct.setAttribute(1, admPrdId); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(2, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(3); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(3, recUpdateTmstp); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(4); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(4, changeVersionId); }


  public java.math.BigDecimal getPtIntakeSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(5); }

  public void setPtIntakeSk(java.math.BigDecimal ptIntakeSk) throws SQLException
  { _struct.setAttribute(5, ptIntakeSk); }


  public java.sql.Timestamp getAdmPrdEffDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(6); }

  public void setAdmPrdEffDate(java.sql.Timestamp admPrdEffDate) throws SQLException
  { _struct.setAttribute(6, admPrdEffDate); }


  public java.sql.Timestamp getAdmPrdTermDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(7); }

  public void setAdmPrdTermDate(java.sql.Timestamp admPrdTermDate) throws SQLException
  { _struct.setAttribute(7, admPrdTermDate); }

}
