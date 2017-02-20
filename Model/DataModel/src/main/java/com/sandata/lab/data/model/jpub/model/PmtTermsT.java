package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class PmtTermsT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PMT_TERMS_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,12,2,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[7];
  protected static final PmtTermsT _PmtTermsTFactory = new PmtTermsT();

  public static ORADataFactory getORADataFactory()
  { return _PmtTermsTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[7], _sqlType, _factory); }
  public PmtTermsT()
  { _init_struct(true); }
  public PmtTermsT(java.math.BigDecimal pmtTermsSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal changeVersionId, String pmtTermsId, java.math.BigDecimal pmtDue, java.math.BigDecimal discountApplied) throws SQLException
  { _init_struct(true);
    setPmtTermsSk(pmtTermsSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setChangeVersionId(changeVersionId);
    setPmtTermsId(pmtTermsId);
    setPmtDue(pmtDue);
    setDiscountApplied(discountApplied);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PmtTermsT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PmtTermsT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPmtTermsSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPmtTermsSk(java.math.BigDecimal pmtTermsSk) throws SQLException
  { _struct.setAttribute(0, pmtTermsSk); }


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


  public String getPmtTermsId() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setPmtTermsId(String pmtTermsId) throws SQLException
  { _struct.setAttribute(4, pmtTermsId); }


  public java.math.BigDecimal getPmtDue() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(5); }

  public void setPmtDue(java.math.BigDecimal pmtDue) throws SQLException
  { _struct.setAttribute(5, pmtDue); }


  public java.math.BigDecimal getDiscountApplied() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(6); }

  public void setDiscountApplied(java.math.BigDecimal discountApplied) throws SQLException
  { _struct.setAttribute(6, discountApplied); }

}
