package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class VendorLkupT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.VENDOR_LKUP_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,2,2,12,12,91,91,12,12,12,1,12,12,12,12,12,12,12,12,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[29];
  protected static final VendorLkupT _VendorLkupTFactory = new VendorLkupT();

  public static ORADataFactory getORADataFactory()
  { return _VendorLkupTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[29], _sqlType, _factory); }
  public VendorLkupT()
  { _init_struct(true); }
  public VendorLkupT(java.math.BigDecimal vendorLkupSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String vendorId, String vendorName, java.sql.Timestamp vendorEffDate, java.sql.Timestamp vendorTermDate, String vendorAddr1, String vendorAddr2, String vendorCity, String vendorState, String vendorPstlCode, String vendorZip4, String prmyContName, String prmyContPhone, String prmyContPhoneExt, String prmyContEmail, String scndryContName, String scndryContPhone, String scndryContPhoneExt, String scndryContEmail, String vendorFederalTaxId) throws SQLException
  { _init_struct(true);
    setVendorLkupSk(vendorLkupSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonMemo(changeReasonMemo);
    setCurrRecInd(currRecInd);
    setChangeVersionId(changeVersionId);
    setVendorId(vendorId);
    setVendorName(vendorName);
    setVendorEffDate(vendorEffDate);
    setVendorTermDate(vendorTermDate);
    setVendorAddr1(vendorAddr1);
    setVendorAddr2(vendorAddr2);
    setVendorCity(vendorCity);
    setVendorState(vendorState);
    setVendorPstlCode(vendorPstlCode);
    setVendorZip4(vendorZip4);
    setPrmyContName(prmyContName);
    setPrmyContPhone(prmyContPhone);
    setPrmyContPhoneExt(prmyContPhoneExt);
    setPrmyContEmail(prmyContEmail);
    setScndryContName(scndryContName);
    setScndryContPhone(scndryContPhone);
    setScndryContPhoneExt(scndryContPhoneExt);
    setScndryContEmail(scndryContEmail);
    setVendorFederalTaxId(vendorFederalTaxId);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(VendorLkupT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new VendorLkupT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getVendorLkupSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setVendorLkupSk(java.math.BigDecimal vendorLkupSk) throws SQLException
  { _struct.setAttribute(0, vendorLkupSk); }


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


  public String getVendorId() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setVendorId(String vendorId) throws SQLException
  { _struct.setAttribute(10, vendorId); }


  public String getVendorName() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setVendorName(String vendorName) throws SQLException
  { _struct.setAttribute(11, vendorName); }


  public java.sql.Timestamp getVendorEffDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(12); }

  public void setVendorEffDate(java.sql.Timestamp vendorEffDate) throws SQLException
  { _struct.setAttribute(12, vendorEffDate); }


  public java.sql.Timestamp getVendorTermDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(13); }

  public void setVendorTermDate(java.sql.Timestamp vendorTermDate) throws SQLException
  { _struct.setAttribute(13, vendorTermDate); }


  public String getVendorAddr1() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setVendorAddr1(String vendorAddr1) throws SQLException
  { _struct.setAttribute(14, vendorAddr1); }


  public String getVendorAddr2() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setVendorAddr2(String vendorAddr2) throws SQLException
  { _struct.setAttribute(15, vendorAddr2); }


  public String getVendorCity() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setVendorCity(String vendorCity) throws SQLException
  { _struct.setAttribute(16, vendorCity); }


  public String getVendorState() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setVendorState(String vendorState) throws SQLException
  { _struct.setAttribute(17, vendorState); }


  public String getVendorPstlCode() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setVendorPstlCode(String vendorPstlCode) throws SQLException
  { _struct.setAttribute(18, vendorPstlCode); }


  public String getVendorZip4() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setVendorZip4(String vendorZip4) throws SQLException
  { _struct.setAttribute(19, vendorZip4); }


  public String getPrmyContName() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setPrmyContName(String prmyContName) throws SQLException
  { _struct.setAttribute(20, prmyContName); }


  public String getPrmyContPhone() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setPrmyContPhone(String prmyContPhone) throws SQLException
  { _struct.setAttribute(21, prmyContPhone); }


  public String getPrmyContPhoneExt() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setPrmyContPhoneExt(String prmyContPhoneExt) throws SQLException
  { _struct.setAttribute(22, prmyContPhoneExt); }


  public String getPrmyContEmail() throws SQLException
  { return (String) _struct.getAttribute(23); }

  public void setPrmyContEmail(String prmyContEmail) throws SQLException
  { _struct.setAttribute(23, prmyContEmail); }


  public String getScndryContName() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setScndryContName(String scndryContName) throws SQLException
  { _struct.setAttribute(24, scndryContName); }


  public String getScndryContPhone() throws SQLException
  { return (String) _struct.getAttribute(25); }

  public void setScndryContPhone(String scndryContPhone) throws SQLException
  { _struct.setAttribute(25, scndryContPhone); }


  public String getScndryContPhoneExt() throws SQLException
  { return (String) _struct.getAttribute(26); }

  public void setScndryContPhoneExt(String scndryContPhoneExt) throws SQLException
  { _struct.setAttribute(26, scndryContPhoneExt); }


  public String getScndryContEmail() throws SQLException
  { return (String) _struct.getAttribute(27); }

  public void setScndryContEmail(String scndryContEmail) throws SQLException
  { _struct.setAttribute(27, scndryContEmail); }


  public String getVendorFederalTaxId() throws SQLException
  { return (String) _struct.getAttribute(28); }

  public void setVendorFederalTaxId(String vendorFederalTaxId) throws SQLException
  { _struct.setAttribute(28, vendorFederalTaxId); }

}
