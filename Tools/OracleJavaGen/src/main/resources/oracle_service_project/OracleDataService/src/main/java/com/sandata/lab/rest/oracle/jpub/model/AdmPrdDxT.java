package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class AdmPrdDxT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.ADM_PRD_DX_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,2,2,91,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[9];
  protected static final AdmPrdDxT _AdmPrdDxTFactory = new AdmPrdDxT();

  public static ORADataFactory getORADataFactory()
  { return _AdmPrdDxTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[9], _sqlType, _factory); }
  public AdmPrdDxT()
  { _init_struct(true); }
  public AdmPrdDxT(java.math.BigDecimal admPrdDxSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal changeVersionId, java.math.BigDecimal admPrdSk, java.math.BigDecimal icdDxRank, java.sql.Timestamp icdDxFirstOnsetDate, String icdDxCode, String icdDxCodeRevisionQlfr) throws SQLException
  { _init_struct(true);
    setAdmPrdDxSk(admPrdDxSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setChangeVersionId(changeVersionId);
    setAdmPrdSk(admPrdSk);
    setIcdDxRank(icdDxRank);
    setIcdDxFirstOnsetDate(icdDxFirstOnsetDate);
    setIcdDxCode(icdDxCode);
    setIcdDxCodeRevisionQlfr(icdDxCodeRevisionQlfr);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AdmPrdDxT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AdmPrdDxT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAdmPrdDxSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAdmPrdDxSk(java.math.BigDecimal admPrdDxSk) throws SQLException
  { _struct.setAttribute(0, admPrdDxSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(3, changeVersionId); }


  public java.math.BigDecimal getAdmPrdSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(4); }

  public void setAdmPrdSk(java.math.BigDecimal admPrdSk) throws SQLException
  { _struct.setAttribute(4, admPrdSk); }


  public java.math.BigDecimal getIcdDxRank() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(5); }

  public void setIcdDxRank(java.math.BigDecimal icdDxRank) throws SQLException
  { _struct.setAttribute(5, icdDxRank); }


  public java.sql.Timestamp getIcdDxFirstOnsetDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(6); }

  public void setIcdDxFirstOnsetDate(java.sql.Timestamp icdDxFirstOnsetDate) throws SQLException
  { _struct.setAttribute(6, icdDxFirstOnsetDate); }


  public String getIcdDxCode() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setIcdDxCode(String icdDxCode) throws SQLException
  { _struct.setAttribute(7, icdDxCode); }


  public String getIcdDxCodeRevisionQlfr() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setIcdDxCodeRevisionQlfr(String icdDxCodeRevisionQlfr) throws SQLException
  { _struct.setAttribute(8, icdDxCodeRevisionQlfr); }

}
