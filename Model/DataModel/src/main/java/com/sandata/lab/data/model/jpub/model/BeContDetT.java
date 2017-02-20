package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class BeContDetT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.BE_CONT_DET_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,91,91,91,91,12,12,12,2,2,12,12,12,12,2,12,12,12,1,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[38];
  protected static final BeContDetT _BeContDetTFactory = new BeContDetT();

  public static ORADataFactory getORADataFactory()
  { return _BeContDetTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[38], _sqlType, _factory); }
  public BeContDetT()
  { _init_struct(true); }
  public BeContDetT(java.math.BigDecimal beContDetSk, String beContDetId, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String beContDetTyp, String beOtherContName, String beOtherContTitle, java.math.BigDecimal beOtherAddrUseForMailInd, String beOtherAddr1, String beOtherAddr2, String beOtherCity, String beOtherState, String beOtherCounty, String beOtherPstlCode, String beOtherZip4, String beOtherPhone, String beOtherPhoneExt, String beOtherPhoneQlfr, String beOtherPhone1, String beOtherPhone1Ext, String beOtherPhone1Qlfr, String beOtherPhone2, String beOtherPhone2Ext, String beOtherPhone2Qlfr, String beOtherEmail, String beOtherEmailQlfr, String beOtherEmail1, String beOtherEmail1Qlfr, String beOtherEmail2, String beOtherEmail2Qlfr) throws SQLException
  { _init_struct(true);
    setBeContDetSk(beContDetSk);
    setBeContDetId(beContDetId);
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
    setBeContDetTyp(beContDetTyp);
    setBeOtherContName(beOtherContName);
    setBeOtherContTitle(beOtherContTitle);
    setBeOtherAddrUseForMailInd(beOtherAddrUseForMailInd);
    setBeOtherAddr1(beOtherAddr1);
    setBeOtherAddr2(beOtherAddr2);
    setBeOtherCity(beOtherCity);
    setBeOtherState(beOtherState);
    setBeOtherCounty(beOtherCounty);
    setBeOtherPstlCode(beOtherPstlCode);
    setBeOtherZip4(beOtherZip4);
    setBeOtherPhone(beOtherPhone);
    setBeOtherPhoneExt(beOtherPhoneExt);
    setBeOtherPhoneQlfr(beOtherPhoneQlfr);
    setBeOtherPhone1(beOtherPhone1);
    setBeOtherPhone1Ext(beOtherPhone1Ext);
    setBeOtherPhone1Qlfr(beOtherPhone1Qlfr);
    setBeOtherPhone2(beOtherPhone2);
    setBeOtherPhone2Ext(beOtherPhone2Ext);
    setBeOtherPhone2Qlfr(beOtherPhone2Qlfr);
    setBeOtherEmail(beOtherEmail);
    setBeOtherEmailQlfr(beOtherEmailQlfr);
    setBeOtherEmail1(beOtherEmail1);
    setBeOtherEmail1Qlfr(beOtherEmail1Qlfr);
    setBeOtherEmail2(beOtherEmail2);
    setBeOtherEmail2Qlfr(beOtherEmail2Qlfr);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(BeContDetT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new BeContDetT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getBeContDetSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setBeContDetSk(java.math.BigDecimal beContDetSk) throws SQLException
  { _struct.setAttribute(0, beContDetSk); }


  public String getBeContDetId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setBeContDetId(String beContDetId) throws SQLException
  { _struct.setAttribute(1, beContDetId); }


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


  public String getBeContDetTyp() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setBeContDetTyp(String beContDetTyp) throws SQLException
  { _struct.setAttribute(12, beContDetTyp); }


  public String getBeOtherContName() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setBeOtherContName(String beOtherContName) throws SQLException
  { _struct.setAttribute(13, beOtherContName); }


  public String getBeOtherContTitle() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setBeOtherContTitle(String beOtherContTitle) throws SQLException
  { _struct.setAttribute(14, beOtherContTitle); }


  public java.math.BigDecimal getBeOtherAddrUseForMailInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(15); }

  public void setBeOtherAddrUseForMailInd(java.math.BigDecimal beOtherAddrUseForMailInd) throws SQLException
  { _struct.setAttribute(15, beOtherAddrUseForMailInd); }


  public String getBeOtherAddr1() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setBeOtherAddr1(String beOtherAddr1) throws SQLException
  { _struct.setAttribute(16, beOtherAddr1); }


  public String getBeOtherAddr2() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setBeOtherAddr2(String beOtherAddr2) throws SQLException
  { _struct.setAttribute(17, beOtherAddr2); }


  public String getBeOtherCity() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setBeOtherCity(String beOtherCity) throws SQLException
  { _struct.setAttribute(18, beOtherCity); }


  public String getBeOtherState() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setBeOtherState(String beOtherState) throws SQLException
  { _struct.setAttribute(19, beOtherState); }


  public String getBeOtherCounty() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setBeOtherCounty(String beOtherCounty) throws SQLException
  { _struct.setAttribute(20, beOtherCounty); }


  public String getBeOtherPstlCode() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setBeOtherPstlCode(String beOtherPstlCode) throws SQLException
  { _struct.setAttribute(21, beOtherPstlCode); }


  public String getBeOtherZip4() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setBeOtherZip4(String beOtherZip4) throws SQLException
  { _struct.setAttribute(22, beOtherZip4); }


  public String getBeOtherPhone() throws SQLException
  { return (String) _struct.getAttribute(23); }

  public void setBeOtherPhone(String beOtherPhone) throws SQLException
  { _struct.setAttribute(23, beOtherPhone); }


  public String getBeOtherPhoneExt() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setBeOtherPhoneExt(String beOtherPhoneExt) throws SQLException
  { _struct.setAttribute(24, beOtherPhoneExt); }


  public String getBeOtherPhoneQlfr() throws SQLException
  { return (String) _struct.getAttribute(25); }

  public void setBeOtherPhoneQlfr(String beOtherPhoneQlfr) throws SQLException
  { _struct.setAttribute(25, beOtherPhoneQlfr); }


  public String getBeOtherPhone1() throws SQLException
  { return (String) _struct.getAttribute(26); }

  public void setBeOtherPhone1(String beOtherPhone1) throws SQLException
  { _struct.setAttribute(26, beOtherPhone1); }


  public String getBeOtherPhone1Ext() throws SQLException
  { return (String) _struct.getAttribute(27); }

  public void setBeOtherPhone1Ext(String beOtherPhone1Ext) throws SQLException
  { _struct.setAttribute(27, beOtherPhone1Ext); }


  public String getBeOtherPhone1Qlfr() throws SQLException
  { return (String) _struct.getAttribute(28); }

  public void setBeOtherPhone1Qlfr(String beOtherPhone1Qlfr) throws SQLException
  { _struct.setAttribute(28, beOtherPhone1Qlfr); }


  public String getBeOtherPhone2() throws SQLException
  { return (String) _struct.getAttribute(29); }

  public void setBeOtherPhone2(String beOtherPhone2) throws SQLException
  { _struct.setAttribute(29, beOtherPhone2); }


  public String getBeOtherPhone2Ext() throws SQLException
  { return (String) _struct.getAttribute(30); }

  public void setBeOtherPhone2Ext(String beOtherPhone2Ext) throws SQLException
  { _struct.setAttribute(30, beOtherPhone2Ext); }


  public String getBeOtherPhone2Qlfr() throws SQLException
  { return (String) _struct.getAttribute(31); }

  public void setBeOtherPhone2Qlfr(String beOtherPhone2Qlfr) throws SQLException
  { _struct.setAttribute(31, beOtherPhone2Qlfr); }


  public String getBeOtherEmail() throws SQLException
  { return (String) _struct.getAttribute(32); }

  public void setBeOtherEmail(String beOtherEmail) throws SQLException
  { _struct.setAttribute(32, beOtherEmail); }


  public String getBeOtherEmailQlfr() throws SQLException
  { return (String) _struct.getAttribute(33); }

  public void setBeOtherEmailQlfr(String beOtherEmailQlfr) throws SQLException
  { _struct.setAttribute(33, beOtherEmailQlfr); }


  public String getBeOtherEmail1() throws SQLException
  { return (String) _struct.getAttribute(34); }

  public void setBeOtherEmail1(String beOtherEmail1) throws SQLException
  { _struct.setAttribute(34, beOtherEmail1); }


  public String getBeOtherEmail1Qlfr() throws SQLException
  { return (String) _struct.getAttribute(35); }

  public void setBeOtherEmail1Qlfr(String beOtherEmail1Qlfr) throws SQLException
  { _struct.setAttribute(35, beOtherEmail1Qlfr); }


  public String getBeOtherEmail2() throws SQLException
  { return (String) _struct.getAttribute(36); }

  public void setBeOtherEmail2(String beOtherEmail2) throws SQLException
  { _struct.setAttribute(36, beOtherEmail2); }


  public String getBeOtherEmail2Qlfr() throws SQLException
  { return (String) _struct.getAttribute(37); }

  public void setBeOtherEmail2Qlfr(String beOtherEmail2Qlfr) throws SQLException
  { _struct.setAttribute(37, beOtherEmail2Qlfr); }

}
