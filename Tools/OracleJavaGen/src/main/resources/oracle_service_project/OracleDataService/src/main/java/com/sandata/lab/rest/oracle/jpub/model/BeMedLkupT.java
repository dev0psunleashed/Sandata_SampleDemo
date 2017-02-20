package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class BeMedLkupT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.BE_MED_LKUP_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,2,2,12,12,12,12,12,12,12,12,12,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[22];
  protected static final BeMedLkupT _BeMedLkupTFactory = new BeMedLkupT();

  public static ORADataFactory getORADataFactory()
  { return _BeMedLkupTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[22], _sqlType, _factory); }
  public BeMedLkupT()
  { _init_struct(true); }
  public BeMedLkupT(java.math.BigDecimal beMedLkupSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String medNdcCode, String medGenericName, String medPtyName, String medInnName, String medChemicalName, String medClasName, String medMfgCode, String medMfgName, String medPackageDesc, String medNcpdpUomCode, String medDesc) throws SQLException
  { _init_struct(true);
    setBeMedLkupSk(beMedLkupSk);
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
    setMedNdcCode(medNdcCode);
    setMedGenericName(medGenericName);
    setMedPtyName(medPtyName);
    setMedInnName(medInnName);
    setMedChemicalName(medChemicalName);
    setMedClasName(medClasName);
    setMedMfgCode(medMfgCode);
    setMedMfgName(medMfgName);
    setMedPackageDesc(medPackageDesc);
    setMedNcpdpUomCode(medNcpdpUomCode);
    setMedDesc(medDesc);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(BeMedLkupT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new BeMedLkupT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getBeMedLkupSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setBeMedLkupSk(java.math.BigDecimal beMedLkupSk) throws SQLException
  { _struct.setAttribute(0, beMedLkupSk); }


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


  public String getMedNdcCode() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setMedNdcCode(String medNdcCode) throws SQLException
  { _struct.setAttribute(11, medNdcCode); }


  public String getMedGenericName() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setMedGenericName(String medGenericName) throws SQLException
  { _struct.setAttribute(12, medGenericName); }


  public String getMedPtyName() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setMedPtyName(String medPtyName) throws SQLException
  { _struct.setAttribute(13, medPtyName); }


  public String getMedInnName() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setMedInnName(String medInnName) throws SQLException
  { _struct.setAttribute(14, medInnName); }


  public String getMedChemicalName() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setMedChemicalName(String medChemicalName) throws SQLException
  { _struct.setAttribute(15, medChemicalName); }


  public String getMedClasName() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setMedClasName(String medClasName) throws SQLException
  { _struct.setAttribute(16, medClasName); }


  public String getMedMfgCode() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setMedMfgCode(String medMfgCode) throws SQLException
  { _struct.setAttribute(17, medMfgCode); }


  public String getMedMfgName() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setMedMfgName(String medMfgName) throws SQLException
  { _struct.setAttribute(18, medMfgName); }


  public String getMedPackageDesc() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setMedPackageDesc(String medPackageDesc) throws SQLException
  { _struct.setAttribute(19, medPackageDesc); }


  public String getMedNcpdpUomCode() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setMedNcpdpUomCode(String medNcpdpUomCode) throws SQLException
  { _struct.setAttribute(20, medNcpdpUomCode); }


  public String getMedDesc() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setMedDesc(String medDesc) throws SQLException
  { _struct.setAttribute(21, medDesc); }

}
