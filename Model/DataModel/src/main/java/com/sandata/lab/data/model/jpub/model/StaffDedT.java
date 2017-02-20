package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class StaffDedT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.STAFF_DED_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,2,91,91,2,12,2,2,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[10];
  protected static final StaffDedT _StaffDedTFactory = new StaffDedT();

  public static ORADataFactory getORADataFactory()
  { return _StaffDedTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[10], _sqlType, _factory); }
  public StaffDedT()
  { _init_struct(true); }
  public StaffDedT(java.math.BigDecimal staffDedSk, String staffDedId, java.math.BigDecimal staffPrDetSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal changeVersionId, String staffDedName, java.math.BigDecimal dedPercentage, java.math.BigDecimal dedAmt, java.math.BigDecimal dedPreTaxOrPostTaxInd) throws SQLException
  { _init_struct(true);
    setStaffDedSk(staffDedSk);
    setStaffDedId(staffDedId);
    setStaffPrDetSk(staffPrDetSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setChangeVersionId(changeVersionId);
    setStaffDedName(staffDedName);
    setDedPercentage(dedPercentage);
    setDedAmt(dedAmt);
    setDedPreTaxOrPostTaxInd(dedPreTaxOrPostTaxInd);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(StaffDedT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new StaffDedT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getStaffDedSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setStaffDedSk(java.math.BigDecimal staffDedSk) throws SQLException
  { _struct.setAttribute(0, staffDedSk); }


  public String getStaffDedId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setStaffDedId(String staffDedId) throws SQLException
  { _struct.setAttribute(1, staffDedId); }


  public java.math.BigDecimal getStaffPrDetSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(2); }

  public void setStaffPrDetSk(java.math.BigDecimal staffPrDetSk) throws SQLException
  { _struct.setAttribute(2, staffPrDetSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(3); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(3, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(4); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(4, recUpdateTmstp); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(5); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(5, changeVersionId); }


  public String getStaffDedName() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setStaffDedName(String staffDedName) throws SQLException
  { _struct.setAttribute(6, staffDedName); }


  public java.math.BigDecimal getDedPercentage() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(7); }

  public void setDedPercentage(java.math.BigDecimal dedPercentage) throws SQLException
  { _struct.setAttribute(7, dedPercentage); }


  public java.math.BigDecimal getDedAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(8); }

  public void setDedAmt(java.math.BigDecimal dedAmt) throws SQLException
  { _struct.setAttribute(8, dedAmt); }


  public java.math.BigDecimal getDedPreTaxOrPostTaxInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(9); }

  public void setDedPreTaxOrPostTaxInd(java.math.BigDecimal dedPreTaxOrPostTaxInd) throws SQLException
  { _struct.setAttribute(9, dedPreTaxOrPostTaxInd); }

}
