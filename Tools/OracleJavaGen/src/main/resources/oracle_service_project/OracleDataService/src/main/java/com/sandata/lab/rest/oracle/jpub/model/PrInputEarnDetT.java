package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class PrInputEarnDetT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PR_INPUT_EARN_DET_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,2,12,12,2,91,12,2,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[12];
  protected static final PrInputEarnDetT _PrInputEarnDetTFactory = new PrInputEarnDetT();

  public static ORADataFactory getORADataFactory()
  { return _PrInputEarnDetTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[12], _sqlType, _factory); }
  public PrInputEarnDetT()
  { _init_struct(true); }
  public PrInputEarnDetT(java.math.BigDecimal prInputEarnDetSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal changeVersionId, java.math.BigDecimal prInputSk, String beId, String earnCode, java.math.BigDecimal earnAmt, java.sql.Timestamp dateOfSvc, String rateTypName, java.math.BigDecimal earnRateAmt, java.math.BigDecimal earnHrs) throws SQLException
  { _init_struct(true);
    setPrInputEarnDetSk(prInputEarnDetSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setChangeVersionId(changeVersionId);
    setPrInputSk(prInputSk);
    setBeId(beId);
    setEarnCode(earnCode);
    setEarnAmt(earnAmt);
    setDateOfSvc(dateOfSvc);
    setRateTypName(rateTypName);
    setEarnRateAmt(earnRateAmt);
    setEarnHrs(earnHrs);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PrInputEarnDetT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PrInputEarnDetT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPrInputEarnDetSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPrInputEarnDetSk(java.math.BigDecimal prInputEarnDetSk) throws SQLException
  { _struct.setAttribute(0, prInputEarnDetSk); }


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


  public java.math.BigDecimal getPrInputSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(4); }

  public void setPrInputSk(java.math.BigDecimal prInputSk) throws SQLException
  { _struct.setAttribute(4, prInputSk); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(5, beId); }


  public String getEarnCode() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setEarnCode(String earnCode) throws SQLException
  { _struct.setAttribute(6, earnCode); }


  public java.math.BigDecimal getEarnAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(7); }

  public void setEarnAmt(java.math.BigDecimal earnAmt) throws SQLException
  { _struct.setAttribute(7, earnAmt); }


  public java.sql.Timestamp getDateOfSvc() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(8); }

  public void setDateOfSvc(java.sql.Timestamp dateOfSvc) throws SQLException
  { _struct.setAttribute(8, dateOfSvc); }


  public String getRateTypName() throws SQLException
  { return (String) _struct.getAttribute(9); }

  public void setRateTypName(String rateTypName) throws SQLException
  { _struct.setAttribute(9, rateTypName); }


  public java.math.BigDecimal getEarnRateAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(10); }

  public void setEarnRateAmt(java.math.BigDecimal earnRateAmt) throws SQLException
  { _struct.setAttribute(10, earnRateAmt); }


  public java.math.BigDecimal getEarnHrs() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(11); }

  public void setEarnHrs(java.math.BigDecimal earnHrs) throws SQLException
  { _struct.setAttribute(11, earnHrs); }

}
