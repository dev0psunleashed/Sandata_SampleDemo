package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class PtPayerInsT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PT_PAYER_INS_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,2,2,12,12,12,12,12,12,12,12,12,12,91,91 };
  protected static ORADataFactory[] _factory = new ORADataFactory[22];
  protected static final PtPayerInsT _PtPayerInsTFactory = new PtPayerInsT();

  public static ORADataFactory getORADataFactory()
  { return _PtPayerInsTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[22], _sqlType, _factory); }
  public PtPayerInsT()
  { _init_struct(true); }
  public PtPayerInsT(java.math.BigDecimal ptPayerInsSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String ptId, String payerId, String ptInsGrpNum, String ptInsGrpName, String payerPlanName, String ptPayerPlanTyp, String ptPayerCvgTyp, String ptInsProvider, String ptInsIdNum, java.sql.Timestamp ptInsStartDate, java.sql.Timestamp ptInsEndDate) throws SQLException
  { _init_struct(true);
    setPtPayerInsSk(ptPayerInsSk);
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
    setPtInsGrpNum(ptInsGrpNum);
    setPtInsGrpName(ptInsGrpName);
    setPayerPlanName(payerPlanName);
    setPtPayerPlanTyp(ptPayerPlanTyp);
    setPtPayerCvgTyp(ptPayerCvgTyp);
    setPtInsProvider(ptInsProvider);
    setPtInsIdNum(ptInsIdNum);
    setPtInsStartDate(ptInsStartDate);
    setPtInsEndDate(ptInsEndDate);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PtPayerInsT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PtPayerInsT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPtPayerInsSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPtPayerInsSk(java.math.BigDecimal ptPayerInsSk) throws SQLException
  { _struct.setAttribute(0, ptPayerInsSk); }


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


  public String getPtInsGrpNum() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setPtInsGrpNum(String ptInsGrpNum) throws SQLException
  { _struct.setAttribute(13, ptInsGrpNum); }


  public String getPtInsGrpName() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setPtInsGrpName(String ptInsGrpName) throws SQLException
  { _struct.setAttribute(14, ptInsGrpName); }


  public String getPayerPlanName() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setPayerPlanName(String payerPlanName) throws SQLException
  { _struct.setAttribute(15, payerPlanName); }


  public String getPtPayerPlanTyp() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setPtPayerPlanTyp(String ptPayerPlanTyp) throws SQLException
  { _struct.setAttribute(16, ptPayerPlanTyp); }


  public String getPtPayerCvgTyp() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setPtPayerCvgTyp(String ptPayerCvgTyp) throws SQLException
  { _struct.setAttribute(17, ptPayerCvgTyp); }


  public String getPtInsProvider() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setPtInsProvider(String ptInsProvider) throws SQLException
  { _struct.setAttribute(18, ptInsProvider); }


  public String getPtInsIdNum() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setPtInsIdNum(String ptInsIdNum) throws SQLException
  { _struct.setAttribute(19, ptInsIdNum); }


  public java.sql.Timestamp getPtInsStartDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(20); }

  public void setPtInsStartDate(java.sql.Timestamp ptInsStartDate) throws SQLException
  { _struct.setAttribute(20, ptInsStartDate); }


  public java.sql.Timestamp getPtInsEndDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(21); }

  public void setPtInsEndDate(java.sql.Timestamp ptInsEndDate) throws SQLException
  { _struct.setAttribute(21, ptInsEndDate); }

}
