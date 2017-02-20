package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class BeIdXwalkT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.BE_ID_XWALK_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,2,12,12,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[13];
  protected static final BeIdXwalkT _BeIdXwalkTFactory = new BeIdXwalkT();

  public static ORADataFactory getORADataFactory()
  { return _BeIdXwalkTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[13], _sqlType, _factory); }
  public BeIdXwalkT()
  { _init_struct(true); }
  public BeIdXwalkT(java.math.BigDecimal beIdXwalkSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, java.math.BigDecimal changeVersionId, String beId, String beIdTyp, String beIdQlfr, String beIdNum, String beIdCreatingOrg) throws SQLException
  { _init_struct(true);
    setBeIdXwalkSk(beIdXwalkSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setBeIdTyp(beIdTyp);
    setBeIdQlfr(beIdQlfr);
    setBeIdNum(beIdNum);
    setBeIdCreatingOrg(beIdCreatingOrg);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(BeIdXwalkT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new BeIdXwalkT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getBeIdXwalkSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setBeIdXwalkSk(java.math.BigDecimal beIdXwalkSk) throws SQLException
  { _struct.setAttribute(0, beIdXwalkSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public java.sql.Timestamp getRecEffTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(3); }

  public void setRecEffTmstp(java.sql.Timestamp recEffTmstp) throws SQLException
  { _struct.setAttribute(3, recEffTmstp); }


  public java.sql.Timestamp getRecTermTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(4); }

  public void setRecTermTmstp(java.sql.Timestamp recTermTmstp) throws SQLException
  { _struct.setAttribute(4, recTermTmstp); }


  public String getRecCreatedBy() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setRecCreatedBy(String recCreatedBy) throws SQLException
  { _struct.setAttribute(5, recCreatedBy); }


  public String getRecUpdatedBy() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setRecUpdatedBy(String recUpdatedBy) throws SQLException
  { _struct.setAttribute(6, recUpdatedBy); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(7); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(7, changeVersionId); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(8, beId); }


  public String getBeIdTyp() throws SQLException
  { return (String) _struct.getAttribute(9); }

  public void setBeIdTyp(String beIdTyp) throws SQLException
  { _struct.setAttribute(9, beIdTyp); }


  public String getBeIdQlfr() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setBeIdQlfr(String beIdQlfr) throws SQLException
  { _struct.setAttribute(10, beIdQlfr); }


  public String getBeIdNum() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setBeIdNum(String beIdNum) throws SQLException
  { _struct.setAttribute(11, beIdNum); }


  public String getBeIdCreatingOrg() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setBeIdCreatingOrg(String beIdCreatingOrg) throws SQLException
  { _struct.setAttribute(12, beIdCreatingOrg); }

}
