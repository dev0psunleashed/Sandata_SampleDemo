package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class PtIntakeT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PT_INTAKE_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,91,91,91,91,12,12,12,2,2,91,12,12,2,2,12,2,91,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[20];
  protected static final PtIntakeT _PtIntakeTFactory = new PtIntakeT();

  public static ORADataFactory getORADataFactory()
  { return _PtIntakeTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[20], _sqlType, _factory); }
  public PtIntakeT()
  { _init_struct(true); }
  public PtIntakeT(java.math.BigDecimal ptIntakeSk, String admId, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, java.sql.Timestamp ptIntakeCreationDate, String beId, String ptId, java.math.BigDecimal eligSk, java.math.BigDecimal authSk, String authQlfr, java.math.BigDecimal providerSk, java.sql.Timestamp ptIntakeUpdateDate, String payerTyp) throws SQLException
  { _init_struct(true);
    setPtIntakeSk(ptIntakeSk);
    setAdmId(admId);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonMemo(changeReasonMemo);
    setCurrRecInd(currRecInd);
    setChangeVersionId(changeVersionId);
    setPtIntakeCreationDate(ptIntakeCreationDate);
    setBeId(beId);
    setPtId(ptId);
    setEligSk(eligSk);
    setAuthSk(authSk);
    setAuthQlfr(authQlfr);
    setProviderSk(providerSk);
    setPtIntakeUpdateDate(ptIntakeUpdateDate);
    setPayerTyp(payerTyp);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PtIntakeT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PtIntakeT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPtIntakeSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPtIntakeSk(java.math.BigDecimal ptIntakeSk) throws SQLException
  { _struct.setAttribute(0, ptIntakeSk); }


  public String getAdmId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setAdmId(String admId) throws SQLException
  { _struct.setAttribute(1, admId); }


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


  public String getRecCreatedBy() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setRecCreatedBy(String recCreatedBy) throws SQLException
  { _struct.setAttribute(6, recCreatedBy); }


  public String getRecUpdatedBy() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setRecUpdatedBy(String recUpdatedBy) throws SQLException
  { _struct.setAttribute(7, recUpdatedBy); }


  public String getChangeReasonMemo() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setChangeReasonMemo(String changeReasonMemo) throws SQLException
  { _struct.setAttribute(8, changeReasonMemo); }


  public java.math.BigDecimal getCurrRecInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(9); }

  public void setCurrRecInd(java.math.BigDecimal currRecInd) throws SQLException
  { _struct.setAttribute(9, currRecInd); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(10); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(10, changeVersionId); }


  public java.sql.Timestamp getPtIntakeCreationDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(11); }

  public void setPtIntakeCreationDate(java.sql.Timestamp ptIntakeCreationDate) throws SQLException
  { _struct.setAttribute(11, ptIntakeCreationDate); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(12, beId); }


  public String getPtId() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setPtId(String ptId) throws SQLException
  { _struct.setAttribute(13, ptId); }


  public java.math.BigDecimal getEligSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(14); }

  public void setEligSk(java.math.BigDecimal eligSk) throws SQLException
  { _struct.setAttribute(14, eligSk); }


  public java.math.BigDecimal getAuthSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(15); }

  public void setAuthSk(java.math.BigDecimal authSk) throws SQLException
  { _struct.setAttribute(15, authSk); }


  public String getAuthQlfr() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setAuthQlfr(String authQlfr) throws SQLException
  { _struct.setAttribute(16, authQlfr); }


  public java.math.BigDecimal getProviderSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(17); }

  public void setProviderSk(java.math.BigDecimal providerSk) throws SQLException
  { _struct.setAttribute(17, providerSk); }


  public java.sql.Timestamp getPtIntakeUpdateDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(18); }

  public void setPtIntakeUpdateDate(java.sql.Timestamp ptIntakeUpdateDate) throws SQLException
  { _struct.setAttribute(18, ptIntakeUpdateDate); }


  public String getPayerTyp() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setPayerTyp(String payerTyp) throws SQLException
  { _struct.setAttribute(19, payerTyp); }

}
