package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class ProviderT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PROVIDER_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,91,91,91,91,12,12,12,2,2,12,12,12,12,12,12,12,12,1,91,12,12,12,1,12,12,12,12,12,2,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[33];
  protected static final ProviderT _ProviderTFactory = new ProviderT();

  public static ORADataFactory getORADataFactory()
  { return _ProviderTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[33], _sqlType, _factory); }
  public ProviderT()
  { _init_struct(true); }
  public ProviderT(java.math.BigDecimal providerSk, String providerId, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String providerFirstName, String providerMiddleName, String providerLastName, String providerOrgName, String providerUpin, String providerNpi, String providerLicenseNum, String providerLicenseState, java.sql.Timestamp providerLicenseExpr, String providerAddr1, String providerAddr2, String providerCity, String providerState, String providerPstlCode, String providerZip4, String providerPhone, String providerFax, String providerContName, java.math.BigDecimal providerSigInd, String providerTaxonomyCode, String medicareAssignmentCode) throws SQLException
  { _init_struct(true);
    setProviderSk(providerSk);
    setProviderId(providerId);
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
    setProviderFirstName(providerFirstName);
    setProviderMiddleName(providerMiddleName);
    setProviderLastName(providerLastName);
    setProviderOrgName(providerOrgName);
    setProviderUpin(providerUpin);
    setProviderNpi(providerNpi);
    setProviderLicenseNum(providerLicenseNum);
    setProviderLicenseState(providerLicenseState);
    setProviderLicenseExpr(providerLicenseExpr);
    setProviderAddr1(providerAddr1);
    setProviderAddr2(providerAddr2);
    setProviderCity(providerCity);
    setProviderState(providerState);
    setProviderPstlCode(providerPstlCode);
    setProviderZip4(providerZip4);
    setProviderPhone(providerPhone);
    setProviderFax(providerFax);
    setProviderContName(providerContName);
    setProviderSigInd(providerSigInd);
    setProviderTaxonomyCode(providerTaxonomyCode);
    setMedicareAssignmentCode(medicareAssignmentCode);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(ProviderT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new ProviderT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getProviderSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setProviderSk(java.math.BigDecimal providerSk) throws SQLException
  { _struct.setAttribute(0, providerSk); }


  public String getProviderId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setProviderId(String providerId) throws SQLException
  { _struct.setAttribute(1, providerId); }


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


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(11, beId); }


  public String getProviderFirstName() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setProviderFirstName(String providerFirstName) throws SQLException
  { _struct.setAttribute(12, providerFirstName); }


  public String getProviderMiddleName() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setProviderMiddleName(String providerMiddleName) throws SQLException
  { _struct.setAttribute(13, providerMiddleName); }


  public String getProviderLastName() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setProviderLastName(String providerLastName) throws SQLException
  { _struct.setAttribute(14, providerLastName); }


  public String getProviderOrgName() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setProviderOrgName(String providerOrgName) throws SQLException
  { _struct.setAttribute(15, providerOrgName); }


  public String getProviderUpin() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setProviderUpin(String providerUpin) throws SQLException
  { _struct.setAttribute(16, providerUpin); }


  public String getProviderNpi() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setProviderNpi(String providerNpi) throws SQLException
  { _struct.setAttribute(17, providerNpi); }


  public String getProviderLicenseNum() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setProviderLicenseNum(String providerLicenseNum) throws SQLException
  { _struct.setAttribute(18, providerLicenseNum); }


  public String getProviderLicenseState() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setProviderLicenseState(String providerLicenseState) throws SQLException
  { _struct.setAttribute(19, providerLicenseState); }


  public java.sql.Timestamp getProviderLicenseExpr() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(20); }

  public void setProviderLicenseExpr(java.sql.Timestamp providerLicenseExpr) throws SQLException
  { _struct.setAttribute(20, providerLicenseExpr); }


  public String getProviderAddr1() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setProviderAddr1(String providerAddr1) throws SQLException
  { _struct.setAttribute(21, providerAddr1); }


  public String getProviderAddr2() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setProviderAddr2(String providerAddr2) throws SQLException
  { _struct.setAttribute(22, providerAddr2); }


  public String getProviderCity() throws SQLException
  { return (String) _struct.getAttribute(23); }

  public void setProviderCity(String providerCity) throws SQLException
  { _struct.setAttribute(23, providerCity); }


  public String getProviderState() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setProviderState(String providerState) throws SQLException
  { _struct.setAttribute(24, providerState); }


  public String getProviderPstlCode() throws SQLException
  { return (String) _struct.getAttribute(25); }

  public void setProviderPstlCode(String providerPstlCode) throws SQLException
  { _struct.setAttribute(25, providerPstlCode); }


  public String getProviderZip4() throws SQLException
  { return (String) _struct.getAttribute(26); }

  public void setProviderZip4(String providerZip4) throws SQLException
  { _struct.setAttribute(26, providerZip4); }


  public String getProviderPhone() throws SQLException
  { return (String) _struct.getAttribute(27); }

  public void setProviderPhone(String providerPhone) throws SQLException
  { _struct.setAttribute(27, providerPhone); }


  public String getProviderFax() throws SQLException
  { return (String) _struct.getAttribute(28); }

  public void setProviderFax(String providerFax) throws SQLException
  { _struct.setAttribute(28, providerFax); }


  public String getProviderContName() throws SQLException
  { return (String) _struct.getAttribute(29); }

  public void setProviderContName(String providerContName) throws SQLException
  { _struct.setAttribute(29, providerContName); }


  public java.math.BigDecimal getProviderSigInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(30); }

  public void setProviderSigInd(java.math.BigDecimal providerSigInd) throws SQLException
  { _struct.setAttribute(30, providerSigInd); }


  public String getProviderTaxonomyCode() throws SQLException
  { return (String) _struct.getAttribute(31); }

  public void setProviderTaxonomyCode(String providerTaxonomyCode) throws SQLException
  { _struct.setAttribute(31, providerTaxonomyCode); }


  public String getMedicareAssignmentCode() throws SQLException
  { return (String) _struct.getAttribute(32); }

  public void setMedicareAssignmentCode(String medicareAssignmentCode) throws SQLException
  { _struct.setAttribute(32, medicareAssignmentCode); }

}
