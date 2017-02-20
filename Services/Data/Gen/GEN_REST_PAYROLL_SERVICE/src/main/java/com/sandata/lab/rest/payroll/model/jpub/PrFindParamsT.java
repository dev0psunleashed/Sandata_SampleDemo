package com.sandata.lab.rest.payroll.model.jpub;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class PrFindParamsT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PR_FIND_PARAMS_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 12,12,12,12,12,12,12,91,91,12,2,12,2,12,12,91,2,2,2,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[22];
  protected static final PrFindParamsT _PrFindParamsTFactory = new PrFindParamsT();

  public static ORADataFactory getORADataFactory()
  { return _PrFindParamsTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[22], _sqlType, _factory); }
  public PrFindParamsT()
  { _init_struct(true); }
  public PrFindParamsT(String beId, String ptId, String ptFirstName, String ptLastName, String staffId, String staffFirstName, String staffLastName, java.sql.Timestamp prFromWeekEndDate, java.sql.Timestamp prToWeekEndDate, String svcName, java.math.BigDecimal payHrs, String payHrsFilter, java.math.BigDecimal rateAmt, String status, String checkNum, java.sql.Timestamp checkDate, java.math.BigDecimal checkAmount, java.math.BigDecimal fromRow, java.math.BigDecimal toRow, String orderByCol, String orderByDir, String rateTypName) throws SQLException
  { _init_struct(true);
    setBeId(beId);
    setPtId(ptId);
    setPtFirstName(ptFirstName);
    setPtLastName(ptLastName);
    setStaffId(staffId);
    setStaffFirstName(staffFirstName);
    setStaffLastName(staffLastName);
    setPrFromWeekEndDate(prFromWeekEndDate);
    setPrToWeekEndDate(prToWeekEndDate);
    setSvcName(svcName);
    setPayHrs(payHrs);
    setPayHrsFilter(payHrsFilter);
    setRateAmt(rateAmt);
    setStatus(status);
    setCheckNum(checkNum);
    setCheckDate(checkDate);
    setCheckAmount(checkAmount);
    setFromRow(fromRow);
    setToRow(toRow);
    setOrderByCol(orderByCol);
    setOrderByDir(orderByDir);
    setRateTypName(rateTypName);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PrFindParamsT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PrFindParamsT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(0); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(0, beId); }


  public String getPtId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setPtId(String ptId) throws SQLException
  { _struct.setAttribute(1, ptId); }


  public String getPtFirstName() throws SQLException
  { return (String) _struct.getAttribute(2); }

  public void setPtFirstName(String ptFirstName) throws SQLException
  { _struct.setAttribute(2, ptFirstName); }


  public String getPtLastName() throws SQLException
  { return (String) _struct.getAttribute(3); }

  public void setPtLastName(String ptLastName) throws SQLException
  { _struct.setAttribute(3, ptLastName); }


  public String getStaffId() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setStaffId(String staffId) throws SQLException
  { _struct.setAttribute(4, staffId); }


  public String getStaffFirstName() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setStaffFirstName(String staffFirstName) throws SQLException
  { _struct.setAttribute(5, staffFirstName); }


  public String getStaffLastName() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setStaffLastName(String staffLastName) throws SQLException
  { _struct.setAttribute(6, staffLastName); }


  public java.sql.Timestamp getPrFromWeekEndDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(7); }

  public void setPrFromWeekEndDate(java.sql.Timestamp prFromWeekEndDate) throws SQLException
  { _struct.setAttribute(7, prFromWeekEndDate); }


  public java.sql.Timestamp getPrToWeekEndDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(8); }

  public void setPrToWeekEndDate(java.sql.Timestamp prToWeekEndDate) throws SQLException
  { _struct.setAttribute(8, prToWeekEndDate); }


  public String getSvcName() throws SQLException
  { return (String) _struct.getAttribute(9); }

  public void setSvcName(String svcName) throws SQLException
  { _struct.setAttribute(9, svcName); }


  public java.math.BigDecimal getPayHrs() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(10); }

  public void setPayHrs(java.math.BigDecimal payHrs) throws SQLException
  { _struct.setAttribute(10, payHrs); }

  public String getPayHrsFilter() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setPayHrsFilter(String payHrsFilter) throws SQLException
  { _struct.setAttribute(11, payHrsFilter); }


  public java.math.BigDecimal getRateAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(12); }

  public void setRateAmt(java.math.BigDecimal rateAmt) throws SQLException
  { _struct.setAttribute(12, rateAmt); }


  public String getStatus() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setStatus(String status) throws SQLException
  { _struct.setAttribute(13, status); }


  public String getCheckNum() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setCheckNum(String checkNum) throws SQLException
  { _struct.setAttribute(14, checkNum); }


  public java.sql.Timestamp getCheckDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(15); }

  public void setCheckDate(java.sql.Timestamp checkDate) throws SQLException
  { _struct.setAttribute(15, checkDate); }


  public java.math.BigDecimal getCheckAmount() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(16); }

  public void setCheckAmount(java.math.BigDecimal checkAmount) throws SQLException
  { _struct.setAttribute(16, checkAmount); }


  public java.math.BigDecimal getFromRow() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(17); }

  public void setFromRow(java.math.BigDecimal fromRow) throws SQLException
  { _struct.setAttribute(17, fromRow); }


  public java.math.BigDecimal getToRow() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(18); }

  public void setToRow(java.math.BigDecimal toRow) throws SQLException
  { _struct.setAttribute(18, toRow); }


  public String getOrderByCol() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setOrderByCol(String orderByCol) throws SQLException
  { _struct.setAttribute(19, orderByCol); }


  public String getOrderByDir() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setOrderByDir(String orderByDir) throws SQLException
  { _struct.setAttribute(20, orderByDir); }

  public String getRateTypName() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setRateTypName(String rateTypName) throws SQLException
  { _struct.setAttribute(21, rateTypName); }

}
