package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class PtHcpT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PT_HCP_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,2,2,12,12,12,91,91,12,12,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[18];
  protected static final PtHcpT _PtHcpTFactory = new PtHcpT();

  public static ORADataFactory getORADataFactory()
  { return _PtHcpTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[18], _sqlType, _factory); }
  public PtHcpT()
  { _init_struct(true); }
  public PtHcpT(java.math.BigDecimal ptHcpSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String ptId, String hcpNpi, java.sql.Timestamp ptHcpStartDate, java.sql.Timestamp ptHcpEndDate, String ptHcpProviderTypName, String ptHcpProviderTypNameOther, java.math.BigDecimal ptHcpSigInd) throws SQLException
  { _init_struct(true);
    setPtHcpSk(ptHcpSk);
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
    setPtId(ptId);
    setHcpNpi(hcpNpi);
    setPtHcpStartDate(ptHcpStartDate);
    setPtHcpEndDate(ptHcpEndDate);
    setPtHcpProviderTypName(ptHcpProviderTypName);
    setPtHcpProviderTypNameOther(ptHcpProviderTypNameOther);
    setPtHcpSigInd(ptHcpSigInd);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PtHcpT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PtHcpT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPtHcpSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPtHcpSk(java.math.BigDecimal ptHcpSk) throws SQLException
  { _struct.setAttribute(0, ptHcpSk); }


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


  public String getPtId() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setPtId(String ptId) throws SQLException
  { _struct.setAttribute(11, ptId); }


  public String getHcpNpi() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setHcpNpi(String hcpNpi) throws SQLException
  { _struct.setAttribute(12, hcpNpi); }


  public java.sql.Timestamp getPtHcpStartDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(13); }

  public void setPtHcpStartDate(java.sql.Timestamp ptHcpStartDate) throws SQLException
  { _struct.setAttribute(13, ptHcpStartDate); }


  public java.sql.Timestamp getPtHcpEndDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(14); }

  public void setPtHcpEndDate(java.sql.Timestamp ptHcpEndDate) throws SQLException
  { _struct.setAttribute(14, ptHcpEndDate); }


  public String getPtHcpProviderTypName() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setPtHcpProviderTypName(String ptHcpProviderTypName) throws SQLException
  { _struct.setAttribute(15, ptHcpProviderTypName); }


  public String getPtHcpProviderTypNameOther() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setPtHcpProviderTypNameOther(String ptHcpProviderTypNameOther) throws SQLException
  { _struct.setAttribute(16, ptHcpProviderTypNameOther); }


  public java.math.BigDecimal getPtHcpSigInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(17); }

  public void setPtHcpSigInd(java.math.BigDecimal ptHcpSigInd) throws SQLException
  { _struct.setAttribute(17, ptHcpSigInd); }

}