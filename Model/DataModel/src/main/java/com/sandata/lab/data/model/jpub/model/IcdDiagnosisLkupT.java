package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class IcdDiagnosisLkupT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.ICD_DX_LKUP_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,2,2,12,12,12,12,91,91 };
  protected static ORADataFactory[] _factory = new ORADataFactory[16];
  protected static final IcdDiagnosisLkupT _IcdDiagnosisLkupTFactory = new IcdDiagnosisLkupT();

  public static ORADataFactory getORADataFactory()
  { return _IcdDiagnosisLkupTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[16], _sqlType, _factory); }
  public IcdDiagnosisLkupT()
  { _init_struct(true); }
  public IcdDiagnosisLkupT(java.math.BigDecimal icdDiagnosisLkupSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String icdDiagnosisCode, String icdDiagnosisCodeRevision, String icdDiagnosisCodeShortDesc, String icdDiagnosisCodeLongDesc, java.sql.Timestamp icdDiagnosisCodeEffDate, java.sql.Timestamp icdDiagnosisCodeTermDate) throws SQLException
  { _init_struct(true);
    setIcdDiagnosisLkupSk(icdDiagnosisLkupSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonMemo(changeReasonMemo);
    setCurrRecInd(currRecInd);
    setChangeVersionId(changeVersionId);
    setIcdDiagnosisCode(icdDiagnosisCode);
    setIcdDiagnosisCodeRevision(icdDiagnosisCodeRevision);
    setIcdDiagnosisCodeShortDesc(icdDiagnosisCodeShortDesc);
    setIcdDiagnosisCodeLongDesc(icdDiagnosisCodeLongDesc);
    setIcdDiagnosisCodeEffDate(icdDiagnosisCodeEffDate);
    setIcdDiagnosisCodeTermDate(icdDiagnosisCodeTermDate);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(IcdDiagnosisLkupT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new IcdDiagnosisLkupT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getIcdDiagnosisLkupSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setIcdDiagnosisLkupSk(java.math.BigDecimal icdDiagnosisLkupSk) throws SQLException
  { _struct.setAttribute(0, icdDiagnosisLkupSk); }


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


  public String getIcdDiagnosisCode() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setIcdDiagnosisCode(String icdDiagnosisCode) throws SQLException
  { _struct.setAttribute(10, icdDiagnosisCode); }


  public String getIcdDiagnosisCodeRevision() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setIcdDiagnosisCodeRevision(String icdDiagnosisCodeRevision) throws SQLException
  { _struct.setAttribute(11, icdDiagnosisCodeRevision); }


  public String getIcdDiagnosisCodeShortDesc() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setIcdDiagnosisCodeShortDesc(String icdDiagnosisCodeShortDesc) throws SQLException
  { _struct.setAttribute(12, icdDiagnosisCodeShortDesc); }


  public String getIcdDiagnosisCodeLongDesc() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setIcdDiagnosisCodeLongDesc(String icdDiagnosisCodeLongDesc) throws SQLException
  { _struct.setAttribute(13, icdDiagnosisCodeLongDesc); }


  public java.sql.Timestamp getIcdDiagnosisCodeEffDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(14); }

  public void setIcdDiagnosisCodeEffDate(java.sql.Timestamp icdDiagnosisCodeEffDate) throws SQLException
  { _struct.setAttribute(14, icdDiagnosisCodeEffDate); }


  public java.sql.Timestamp getIcdDiagnosisCodeTermDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(15); }

  public void setIcdDiagnosisCodeTermDate(java.sql.Timestamp icdDiagnosisCodeTermDate) throws SQLException
  { _struct.setAttribute(15, icdDiagnosisCodeTermDate); }

}
