package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class BeHcpLkupT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.BE_HCP_LKUP_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,2,2,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,91,91 };
  protected static ORADataFactory[] _factory = new ORADataFactory[31];
  protected static final BeHcpLkupT _BeHcpLkupTFactory = new BeHcpLkupT();

  public static ORADataFactory getORADataFactory()
  { return _BeHcpLkupTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[31], _sqlType, _factory); }
  public BeHcpLkupT()
  { _init_struct(true); }
  public BeHcpLkupT(java.math.BigDecimal beHcpLkupSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String hcpFirstName, String hcpLastName, String hcpSpecialtyQlfr, String hcpPhone, String hcpPhoneExt, String hcpFax, String hcpAddr1, String hcpAddr2, String hcpCity, String hcpState, String hcpPstlCode, String hcpZip4, String hcpEmailAddr, String hcpApi, String hcpNpi, String hcpTin, String hcpTinQlfr, String hcpLicenseNum, java.sql.Timestamp hcpEffDate, java.sql.Timestamp hcpTermDate) throws SQLException
  { _init_struct(true);
    setBeHcpLkupSk(beHcpLkupSk);
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
    setHcpFirstName(hcpFirstName);
    setHcpLastName(hcpLastName);
    setHcpSpecialtyQlfr(hcpSpecialtyQlfr);
    setHcpPhone(hcpPhone);
    setHcpPhoneExt(hcpPhoneExt);
    setHcpFax(hcpFax);
    setHcpAddr1(hcpAddr1);
    setHcpAddr2(hcpAddr2);
    setHcpCity(hcpCity);
    setHcpState(hcpState);
    setHcpPstlCode(hcpPstlCode);
    setHcpZip4(hcpZip4);
    setHcpEmailAddr(hcpEmailAddr);
    setHcpApi(hcpApi);
    setHcpNpi(hcpNpi);
    setHcpTin(hcpTin);
    setHcpTinQlfr(hcpTinQlfr);
    setHcpLicenseNum(hcpLicenseNum);
    setHcpEffDate(hcpEffDate);
    setHcpTermDate(hcpTermDate);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(BeHcpLkupT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new BeHcpLkupT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getBeHcpLkupSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setBeHcpLkupSk(java.math.BigDecimal beHcpLkupSk) throws SQLException
  { _struct.setAttribute(0, beHcpLkupSk); }


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


  public String getHcpFirstName() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setHcpFirstName(String hcpFirstName) throws SQLException
  { _struct.setAttribute(11, hcpFirstName); }


  public String getHcpLastName() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setHcpLastName(String hcpLastName) throws SQLException
  { _struct.setAttribute(12, hcpLastName); }


  public String getHcpSpecialtyQlfr() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setHcpSpecialtyQlfr(String hcpSpecialtyQlfr) throws SQLException
  { _struct.setAttribute(13, hcpSpecialtyQlfr); }


  public String getHcpPhone() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setHcpPhone(String hcpPhone) throws SQLException
  { _struct.setAttribute(14, hcpPhone); }


  public String getHcpPhoneExt() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setHcpPhoneExt(String hcpPhoneExt) throws SQLException
  { _struct.setAttribute(15, hcpPhoneExt); }


  public String getHcpFax() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setHcpFax(String hcpFax) throws SQLException
  { _struct.setAttribute(16, hcpFax); }


  public String getHcpAddr1() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setHcpAddr1(String hcpAddr1) throws SQLException
  { _struct.setAttribute(17, hcpAddr1); }


  public String getHcpAddr2() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setHcpAddr2(String hcpAddr2) throws SQLException
  { _struct.setAttribute(18, hcpAddr2); }


  public String getHcpCity() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setHcpCity(String hcpCity) throws SQLException
  { _struct.setAttribute(19, hcpCity); }


  public String getHcpState() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setHcpState(String hcpState) throws SQLException
  { _struct.setAttribute(20, hcpState); }


  public String getHcpPstlCode() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setHcpPstlCode(String hcpPstlCode) throws SQLException
  { _struct.setAttribute(21, hcpPstlCode); }


  public String getHcpZip4() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setHcpZip4(String hcpZip4) throws SQLException
  { _struct.setAttribute(22, hcpZip4); }


  public String getHcpEmailAddr() throws SQLException
  { return (String) _struct.getAttribute(23); }

  public void setHcpEmailAddr(String hcpEmailAddr) throws SQLException
  { _struct.setAttribute(23, hcpEmailAddr); }


  public String getHcpApi() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setHcpApi(String hcpApi) throws SQLException
  { _struct.setAttribute(24, hcpApi); }


  public String getHcpNpi() throws SQLException
  { return (String) _struct.getAttribute(25); }

  public void setHcpNpi(String hcpNpi) throws SQLException
  { _struct.setAttribute(25, hcpNpi); }


  public String getHcpTin() throws SQLException
  { return (String) _struct.getAttribute(26); }

  public void setHcpTin(String hcpTin) throws SQLException
  { _struct.setAttribute(26, hcpTin); }


  public String getHcpTinQlfr() throws SQLException
  { return (String) _struct.getAttribute(27); }

  public void setHcpTinQlfr(String hcpTinQlfr) throws SQLException
  { _struct.setAttribute(27, hcpTinQlfr); }


  public String getHcpLicenseNum() throws SQLException
  { return (String) _struct.getAttribute(28); }

  public void setHcpLicenseNum(String hcpLicenseNum) throws SQLException
  { _struct.setAttribute(28, hcpLicenseNum); }


  public java.sql.Timestamp getHcpEffDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(29); }

  public void setHcpEffDate(java.sql.Timestamp hcpEffDate) throws SQLException
  { _struct.setAttribute(29, hcpEffDate); }


  public java.sql.Timestamp getHcpTermDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(30); }

  public void setHcpTermDate(java.sql.Timestamp hcpTermDate) throws SQLException
  { _struct.setAttribute(30, hcpTermDate); }

}
