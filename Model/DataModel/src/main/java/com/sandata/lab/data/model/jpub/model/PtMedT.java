package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class PtMedT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PT_MED_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,2,2,12,12,12,12,12,12,2,12,12,91,91,12,12,2,12,12,12,12,2004 };
  protected static ORADataFactory[] _factory = new ORADataFactory[29];
  protected static final PtMedT _PtMedTFactory = new PtMedT();

  public static ORADataFactory getORADataFactory()
  { return _PtMedTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[29], _sqlType, _factory); }
  public PtMedT()
  { _init_struct(true); }
  public PtMedT(java.math.BigDecimal ptMedSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String ptId, String beId, String medGenericName, String medDosageStrgName, String medDosageFormName, String medDosageFreqName, java.math.BigDecimal freqTypLkupSk, String medRouteName, String medClasName, java.sql.Timestamp medStartDate, java.sql.Timestamp medEndDate, String medRank, String authreimburse, java.math.BigDecimal medPrnInd, String medPrnReason, String medRxcui, String medContraindication, String medComment, oracle.sql.BLOB medInfoSheet) throws SQLException
  { _init_struct(true);
    setPtMedSk(ptMedSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonMemo(changeReasonMemo);
    setCurrRecInd(currRecInd);
    setChangeVersionId(changeVersionId);
    setPtId(ptId);
    setBeId(beId);
    setMedGenericName(medGenericName);
    setMedDosageStrgName(medDosageStrgName);
    setMedDosageFormName(medDosageFormName);
    setMedDosageFreqName(medDosageFreqName);
    setFreqTypLkupSk(freqTypLkupSk);
    setMedRouteName(medRouteName);
    setMedClasName(medClasName);
    setMedStartDate(medStartDate);
    setMedEndDate(medEndDate);
    setMedRank(medRank);
    setAuthreimburse(authreimburse);
    setMedPrnInd(medPrnInd);
    setMedPrnReason(medPrnReason);
    setMedRxcui(medRxcui);
    setMedContraindication(medContraindication);
    setMedComment(medComment);
    setMedInfoSheet(medInfoSheet);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PtMedT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PtMedT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPtMedSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPtMedSk(java.math.BigDecimal ptMedSk) throws SQLException
  { _struct.setAttribute(0, ptMedSk); }


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


  public String getPtId() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setPtId(String ptId) throws SQLException
  { _struct.setAttribute(10, ptId); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(11, beId); }


  public String getMedGenericName() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setMedGenericName(String medGenericName) throws SQLException
  { _struct.setAttribute(12, medGenericName); }


  public String getMedDosageStrgName() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setMedDosageStrgName(String medDosageStrgName) throws SQLException
  { _struct.setAttribute(13, medDosageStrgName); }


  public String getMedDosageFormName() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setMedDosageFormName(String medDosageFormName) throws SQLException
  { _struct.setAttribute(14, medDosageFormName); }


  public String getMedDosageFreqName() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setMedDosageFreqName(String medDosageFreqName) throws SQLException
  { _struct.setAttribute(15, medDosageFreqName); }


  public java.math.BigDecimal getFreqTypLkupSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(16); }

  public void setFreqTypLkupSk(java.math.BigDecimal freqTypLkupSk) throws SQLException
  { _struct.setAttribute(16, freqTypLkupSk); }


  public String getMedRouteName() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setMedRouteName(String medRouteName) throws SQLException
  { _struct.setAttribute(17, medRouteName); }


  public String getMedClasName() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setMedClasName(String medClasName) throws SQLException
  { _struct.setAttribute(18, medClasName); }


  public java.sql.Timestamp getMedStartDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(19); }

  public void setMedStartDate(java.sql.Timestamp medStartDate) throws SQLException
  { _struct.setAttribute(19, medStartDate); }


  public java.sql.Timestamp getMedEndDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(20); }

  public void setMedEndDate(java.sql.Timestamp medEndDate) throws SQLException
  { _struct.setAttribute(20, medEndDate); }


  public String getMedRank() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setMedRank(String medRank) throws SQLException
  { _struct.setAttribute(21, medRank); }


  public String getAuthreimburse() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setAuthreimburse(String authreimburse) throws SQLException
  { _struct.setAttribute(22, authreimburse); }


  public java.math.BigDecimal getMedPrnInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(23); }

  public void setMedPrnInd(java.math.BigDecimal medPrnInd) throws SQLException
  { _struct.setAttribute(23, medPrnInd); }


  public String getMedPrnReason() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setMedPrnReason(String medPrnReason) throws SQLException
  { _struct.setAttribute(24, medPrnReason); }


  public String getMedRxcui() throws SQLException
  { return (String) _struct.getAttribute(25); }

  public void setMedRxcui(String medRxcui) throws SQLException
  { _struct.setAttribute(25, medRxcui); }


  public String getMedContraindication() throws SQLException
  { return (String) _struct.getAttribute(26); }

  public void setMedContraindication(String medContraindication) throws SQLException
  { _struct.setAttribute(26, medContraindication); }


  public String getMedComment() throws SQLException
  { return (String) _struct.getAttribute(27); }

  public void setMedComment(String medComment) throws SQLException
  { _struct.setAttribute(27, medComment); }


  public oracle.sql.BLOB getMedInfoSheet() throws SQLException
  { return (oracle.sql.BLOB) _struct.getOracleAttribute(28); }

  public void setMedInfoSheet(oracle.sql.BLOB medInfoSheet) throws SQLException
  { _struct.setOracleAttribute(28, medInfoSheet); }

}
