package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class PtPayerT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PT_PAYER_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,2,2,12,12,12,12,12,12,91,91,2,2,12,12,12,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[24];
  protected static final PtPayerT _PtPayerTFactory = new PtPayerT();

  public static ORADataFactory getORADataFactory()
  { return _PtPayerTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[24], _sqlType, _factory); }
  public PtPayerT()
  { _init_struct(true); }
  public PtPayerT(java.math.BigDecimal ptPayerSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String ptId, String payerId, String contrId, String payerRankName, String svcName, java.sql.Timestamp ptPayerEffDate, java.sql.Timestamp ptPayerTermDate, java.math.BigDecimal ptSelfPayInd, java.math.BigDecimal ptGaurantorInd, String ptPayerRelTypName, String ptPayerFreqTypName, String ptPayerPmtRespValQlfr, java.math.BigDecimal ptPayerPmtRespVal) throws SQLException
  { _init_struct(true);
    setPtPayerSk(ptPayerSk);
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
    setPayerId(payerId);
    setContrId(contrId);
    setPayerRankName(payerRankName);
    setSvcName(svcName);
    setPtPayerEffDate(ptPayerEffDate);
    setPtPayerTermDate(ptPayerTermDate);
    setPtSelfPayInd(ptSelfPayInd);
    setPtGaurantorInd(ptGaurantorInd);
    setPtPayerRelTypName(ptPayerRelTypName);
    setPtPayerFreqTypName(ptPayerFreqTypName);
    setPtPayerPmtRespValQlfr(ptPayerPmtRespValQlfr);
    setPtPayerPmtRespVal(ptPayerPmtRespVal);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PtPayerT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PtPayerT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPtPayerSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPtPayerSk(java.math.BigDecimal ptPayerSk) throws SQLException
  { _struct.setAttribute(0, ptPayerSk); }


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


  public String getPayerId() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setPayerId(String payerId) throws SQLException
  { _struct.setAttribute(12, payerId); }


  public String getContrId() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setContrId(String contrId) throws SQLException
  { _struct.setAttribute(13, contrId); }


  public String getPayerRankName() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setPayerRankName(String payerRankName) throws SQLException
  { _struct.setAttribute(14, payerRankName); }


  public String getSvcName() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setSvcName(String svcName) throws SQLException
  { _struct.setAttribute(15, svcName); }


  public java.sql.Timestamp getPtPayerEffDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(16); }

  public void setPtPayerEffDate(java.sql.Timestamp ptPayerEffDate) throws SQLException
  { _struct.setAttribute(16, ptPayerEffDate); }


  public java.sql.Timestamp getPtPayerTermDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(17); }

  public void setPtPayerTermDate(java.sql.Timestamp ptPayerTermDate) throws SQLException
  { _struct.setAttribute(17, ptPayerTermDate); }


  public java.math.BigDecimal getPtSelfPayInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(18); }

  public void setPtSelfPayInd(java.math.BigDecimal ptSelfPayInd) throws SQLException
  { _struct.setAttribute(18, ptSelfPayInd); }


  public java.math.BigDecimal getPtGaurantorInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(19); }

  public void setPtGaurantorInd(java.math.BigDecimal ptGaurantorInd) throws SQLException
  { _struct.setAttribute(19, ptGaurantorInd); }


  public String getPtPayerRelTypName() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setPtPayerRelTypName(String ptPayerRelTypName) throws SQLException
  { _struct.setAttribute(20, ptPayerRelTypName); }


  public String getPtPayerFreqTypName() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setPtPayerFreqTypName(String ptPayerFreqTypName) throws SQLException
  { _struct.setAttribute(21, ptPayerFreqTypName); }


  public String getPtPayerPmtRespValQlfr() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setPtPayerPmtRespValQlfr(String ptPayerPmtRespValQlfr) throws SQLException
  { _struct.setAttribute(22, ptPayerPmtRespValQlfr); }


  public java.math.BigDecimal getPtPayerPmtRespVal() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(23); }

  public void setPtPayerPmtRespVal(java.math.BigDecimal ptPayerPmtRespVal) throws SQLException
  { _struct.setAttribute(23, ptPayerPmtRespVal); }

}
