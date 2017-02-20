package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class BeLvlT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.BE_LVL_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,91,91,91,91,2,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[9];
  protected static final BeLvlT _BeLvlTFactory = new BeLvlT();

  public static ORADataFactory getORADataFactory()
  { return _BeLvlTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[9], _sqlType, _factory); }
  public BeLvlT()
  { _init_struct(true); }
  public BeLvlT(java.math.BigDecimal beLvlSk, String beLvlId, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, java.math.BigDecimal changeVersionId, String beId, String beLvlName) throws SQLException
  { _init_struct(true);
    setBeLvlSk(beLvlSk);
    setBeLvlId(beLvlId);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setBeLvlName(beLvlName);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(BeLvlT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new BeLvlT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getBeLvlSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setBeLvlSk(java.math.BigDecimal beLvlSk) throws SQLException
  { _struct.setAttribute(0, beLvlSk); }


  public String getBeLvlId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setBeLvlId(String beLvlId) throws SQLException
  { _struct.setAttribute(1, beLvlId); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(2, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(3); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(3, recUpdateTmstp); }


  public java.sql.Timestamp getRecEffTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(4); }

  public void setRecEffTmstp(java.sql.Timestamp recEffTmstp) throws SQLException
  { _struct.setAttribute(4, recEffTmstp); }


  public java.sql.Timestamp getRecTermTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(5); }

  public void setRecTermTmstp(java.sql.Timestamp recTermTmstp) throws SQLException
  { _struct.setAttribute(5, recTermTmstp); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(6); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(6, changeVersionId); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(7, beId); }


  public String getBeLvlName() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setBeLvlName(String beLvlName) throws SQLException
  { _struct.setAttribute(8, beLvlName); }

}
