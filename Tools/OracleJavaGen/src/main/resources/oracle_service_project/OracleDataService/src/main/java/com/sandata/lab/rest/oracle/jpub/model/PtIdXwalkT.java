package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class PtIdXwalkT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PT_ID_XWALK_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,91,91,2,12,12,12,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[11];
  protected static final PtIdXwalkT _PtIdXwalkTFactory = new PtIdXwalkT();

  public static ORADataFactory getORADataFactory()
  { return _PtIdXwalkTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[11], _sqlType, _factory); }
  public PtIdXwalkT()
  { _init_struct(true); }
  public PtIdXwalkT(java.math.BigDecimal ptIdXwalkSk, String ptIdXwalkId, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal changeVersionId, String beId, String ptId, String ptIdTyp, String ptIdQlfr, String ptIdNum, String ptIdCreatingOrg) throws SQLException
  { _init_struct(true);
    setPtIdXwalkSk(ptIdXwalkSk);
    setPtIdXwalkId(ptIdXwalkId);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setPtId(ptId);
    setPtIdTyp(ptIdTyp);
    setPtIdQlfr(ptIdQlfr);
    setPtIdNum(ptIdNum);
    setPtIdCreatingOrg(ptIdCreatingOrg);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PtIdXwalkT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PtIdXwalkT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPtIdXwalkSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPtIdXwalkSk(java.math.BigDecimal ptIdXwalkSk) throws SQLException
  { _struct.setAttribute(0, ptIdXwalkSk); }


  public String getPtIdXwalkId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setPtIdXwalkId(String ptIdXwalkId) throws SQLException
  { _struct.setAttribute(1, ptIdXwalkId); }


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


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(5, beId); }


  public String getPtId() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setPtId(String ptId) throws SQLException
  { _struct.setAttribute(6, ptId); }


  public String getPtIdTyp() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setPtIdTyp(String ptIdTyp) throws SQLException
  { _struct.setAttribute(7, ptIdTyp); }


  public String getPtIdQlfr() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setPtIdQlfr(String ptIdQlfr) throws SQLException
  { _struct.setAttribute(8, ptIdQlfr); }


  public String getPtIdNum() throws SQLException
  { return (String) _struct.getAttribute(9); }

  public void setPtIdNum(String ptIdNum) throws SQLException
  { _struct.setAttribute(9, ptIdNum); }


  public String getPtIdCreatingOrg() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setPtIdCreatingOrg(String ptIdCreatingOrg) throws SQLException
  { _struct.setAttribute(10, ptIdCreatingOrg); }

}
