package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class BeDocClasT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.BE_DOC_CLAS_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,12,2,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[8];
  protected static final BeDocClasT _BeDocClasTFactory = new BeDocClasT();

  public static ORADataFactory getORADataFactory()
  { return _BeDocClasTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[8], _sqlType, _factory); }
  public BeDocClasT()
  { _init_struct(true); }
  public BeDocClasT(java.math.BigDecimal beDocClasSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal changeVersionId, String beId, java.math.BigDecimal docClasParSk, String docClasName, String docClasDesc) throws SQLException
  { _init_struct(true);
    setBeDocClasSk(beDocClasSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setDocClasParSk(docClasParSk);
    setDocClasName(docClasName);
    setDocClasDesc(docClasDesc);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(BeDocClasT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new BeDocClasT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getBeDocClasSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setBeDocClasSk(java.math.BigDecimal beDocClasSk) throws SQLException
  { _struct.setAttribute(0, beDocClasSk); }


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


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(4, beId); }


  public java.math.BigDecimal getDocClasParSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(5); }

  public void setDocClasParSk(java.math.BigDecimal docClasParSk) throws SQLException
  { _struct.setAttribute(5, docClasParSk); }


  public String getDocClasName() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setDocClasName(String docClasName) throws SQLException
  { _struct.setAttribute(6, docClasName); }


  public String getDocClasDesc() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setDocClasDesc(String docClasDesc) throws SQLException
  { _struct.setAttribute(7, docClasDesc); }

}
