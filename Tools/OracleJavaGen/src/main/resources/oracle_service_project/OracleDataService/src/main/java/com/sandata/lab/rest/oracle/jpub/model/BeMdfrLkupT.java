package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class BeMdfrLkupT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.BE_MDFR_LKUP_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,2,2,12,12,12,12,91,91 };
  protected static ORADataFactory[] _factory = new ORADataFactory[16];
  protected static final BeMdfrLkupT _BeMdfrLkupTFactory = new BeMdfrLkupT();

  public static ORADataFactory getORADataFactory()
  { return _BeMdfrLkupTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[16], _sqlType, _factory); }
  public BeMdfrLkupT()
  { _init_struct(true); }
  public BeMdfrLkupT(java.math.BigDecimal beMdfrLkupSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String mdfrCode, String mdfrShortDesc, String mdfrLongDesc, java.sql.Timestamp mdfrEffDate, java.sql.Timestamp mdfrTermDate) throws SQLException
  { _init_struct(true);
    setBeMdfrLkupSk(beMdfrLkupSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonMemo(changeReasonMemo);
    setCurrRecInd(currRecInd);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setMdfrCode(mdfrCode);
    setMdfrShortDesc(mdfrShortDesc);
    setMdfrLongDesc(mdfrLongDesc);
    setMdfrEffDate(mdfrEffDate);
    setMdfrTermDate(mdfrTermDate);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(BeMdfrLkupT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new BeMdfrLkupT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getBeMdfrLkupSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setBeMdfrLkupSk(java.math.BigDecimal beMdfrLkupSk) throws SQLException
  { _struct.setAttribute(0, beMdfrLkupSk); }


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


  public String getChangeReasonMemo() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setChangeReasonMemo(String changeReasonMemo) throws SQLException
  { _struct.setAttribute(7, changeReasonMemo); }


  public java.math.BigDecimal getCurrRecInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(8); }

  public void setCurrRecInd(java.math.BigDecimal currRecInd) throws SQLException
  { _struct.setAttribute(8, currRecInd); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(9); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(9, changeVersionId); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(10, beId); }


  public String getMdfrCode() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setMdfrCode(String mdfrCode) throws SQLException
  { _struct.setAttribute(11, mdfrCode); }


  public String getMdfrShortDesc() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setMdfrShortDesc(String mdfrShortDesc) throws SQLException
  { _struct.setAttribute(12, mdfrShortDesc); }


  public String getMdfrLongDesc() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setMdfrLongDesc(String mdfrLongDesc) throws SQLException
  { _struct.setAttribute(13, mdfrLongDesc); }


  public java.sql.Timestamp getMdfrEffDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(14); }

  public void setMdfrEffDate(java.sql.Timestamp mdfrEffDate) throws SQLException
  { _struct.setAttribute(14, mdfrEffDate); }


  public java.sql.Timestamp getMdfrTermDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(15); }

  public void setMdfrTermDate(java.sql.Timestamp mdfrTermDate) throws SQLException
  { _struct.setAttribute(15, mdfrTermDate); }

}
