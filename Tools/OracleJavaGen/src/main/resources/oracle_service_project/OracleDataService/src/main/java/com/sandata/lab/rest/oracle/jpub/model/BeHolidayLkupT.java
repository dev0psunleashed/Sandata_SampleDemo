package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class BeHolidayLkupT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.BE_HOLIDAY_LKUP_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,2,2,12,12,91,91,2,2,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[16];
  protected static final BeHolidayLkupT _BeHolidayLkupTFactory = new BeHolidayLkupT();

  public static ORADataFactory getORADataFactory()
  { return _BeHolidayLkupTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[16], _sqlType, _factory); }
  public BeHolidayLkupT()
  { _init_struct(true); }
  public BeHolidayLkupT(java.math.BigDecimal beHolidayLkupSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String holidayName, java.sql.Timestamp holidayStartDtime, java.sql.Timestamp holidayEndDtime, java.math.BigDecimal holidayFullDayInd, java.math.BigDecimal holidayBillInd, java.math.BigDecimal holidayPayInd) throws SQLException
  { _init_struct(true);
    setBeHolidayLkupSk(beHolidayLkupSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setCurrRecInd(currRecInd);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setHolidayName(holidayName);
    setHolidayStartDtime(holidayStartDtime);
    setHolidayEndDtime(holidayEndDtime);
    setHolidayFullDayInd(holidayFullDayInd);
    setHolidayBillInd(holidayBillInd);
    setHolidayPayInd(holidayPayInd);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(BeHolidayLkupT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new BeHolidayLkupT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getBeHolidayLkupSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setBeHolidayLkupSk(java.math.BigDecimal beHolidayLkupSk) throws SQLException
  { _struct.setAttribute(0, beHolidayLkupSk); }


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


  public java.math.BigDecimal getCurrRecInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(7); }

  public void setCurrRecInd(java.math.BigDecimal currRecInd) throws SQLException
  { _struct.setAttribute(7, currRecInd); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(8); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(8, changeVersionId); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(9); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(9, beId); }


  public String getHolidayName() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setHolidayName(String holidayName) throws SQLException
  { _struct.setAttribute(10, holidayName); }


  public java.sql.Timestamp getHolidayStartDtime() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(11); }

  public void setHolidayStartDtime(java.sql.Timestamp holidayStartDtime) throws SQLException
  { _struct.setAttribute(11, holidayStartDtime); }


  public java.sql.Timestamp getHolidayEndDtime() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(12); }

  public void setHolidayEndDtime(java.sql.Timestamp holidayEndDtime) throws SQLException
  { _struct.setAttribute(12, holidayEndDtime); }


  public java.math.BigDecimal getHolidayFullDayInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(13); }

  public void setHolidayFullDayInd(java.math.BigDecimal holidayFullDayInd) throws SQLException
  { _struct.setAttribute(13, holidayFullDayInd); }


  public java.math.BigDecimal getHolidayBillInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(14); }

  public void setHolidayBillInd(java.math.BigDecimal holidayBillInd) throws SQLException
  { _struct.setAttribute(14, holidayBillInd); }


  public java.math.BigDecimal getHolidayPayInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(15); }

  public void setHolidayPayInd(java.math.BigDecimal holidayPayInd) throws SQLException
  { _struct.setAttribute(15, holidayPayInd); }

}
