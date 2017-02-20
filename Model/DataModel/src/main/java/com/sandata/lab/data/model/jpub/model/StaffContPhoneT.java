package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class StaffContPhoneT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.STAFF_CONT_PHONE_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,2,12,2,12,12,12,12,12,12,2,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[18];
  protected static final StaffContPhoneT _StaffContPhoneTFactory = new StaffContPhoneT();

  public static ORADataFactory getORADataFactory()
  { return _StaffContPhoneTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[18], _sqlType, _factory); }
  public StaffContPhoneT()
  { _init_struct(true); }
  public StaffContPhoneT(java.math.BigDecimal staffContPhoneSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, java.math.BigDecimal currRecInd, String changeReasonMemo, java.math.BigDecimal changeVersionId, String beId, String staffId, String addrPrioName, String staffContPhoneQlfr, String staffPhone, String staffPhoneExt, java.math.BigDecimal staffPhonePrmyInd, java.math.BigDecimal staffPhonetextMsgInd) throws SQLException
  { _init_struct(true);
    setStaffContPhoneSk(staffContPhoneSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setCurrRecInd(currRecInd);
    setChangeReasonMemo(changeReasonMemo);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setStaffId(staffId);
    setAddrPrioName(addrPrioName);
    setStaffContPhoneQlfr(staffContPhoneQlfr);
    setStaffPhone(staffPhone);
    setStaffPhoneExt(staffPhoneExt);
    setStaffPhonePrmyInd(staffPhonePrmyInd);
    setStaffPhonetextMsgInd(staffPhonetextMsgInd);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(StaffContPhoneT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new StaffContPhoneT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getStaffContPhoneSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setStaffContPhoneSk(java.math.BigDecimal staffContPhoneSk) throws SQLException
  { _struct.setAttribute(0, staffContPhoneSk); }


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


  public java.math.BigDecimal getCurrRecInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(7); }

  public void setCurrRecInd(java.math.BigDecimal currRecInd) throws SQLException
  { _struct.setAttribute(7, currRecInd); }


  public String getChangeReasonMemo() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setChangeReasonMemo(String changeReasonMemo) throws SQLException
  { _struct.setAttribute(8, changeReasonMemo); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(9); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(9, changeVersionId); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(10, beId); }


  public String getStaffId() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setStaffId(String staffId) throws SQLException
  { _struct.setAttribute(11, staffId); }


  public String getAddrPrioName() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setAddrPrioName(String addrPrioName) throws SQLException
  { _struct.setAttribute(12, addrPrioName); }


  public String getStaffContPhoneQlfr() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setStaffContPhoneQlfr(String staffContPhoneQlfr) throws SQLException
  { _struct.setAttribute(13, staffContPhoneQlfr); }


  public String getStaffPhone() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setStaffPhone(String staffPhone) throws SQLException
  { _struct.setAttribute(14, staffPhone); }


  public String getStaffPhoneExt() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setStaffPhoneExt(String staffPhoneExt) throws SQLException
  { _struct.setAttribute(15, staffPhoneExt); }


  public java.math.BigDecimal getStaffPhonePrmyInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(16); }

  public void setStaffPhonePrmyInd(java.math.BigDecimal staffPhonePrmyInd) throws SQLException
  { _struct.setAttribute(16, staffPhonePrmyInd); }


  public java.math.BigDecimal getStaffPhonetextMsgInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(17); }

  public void setStaffPhonetextMsgInd(java.math.BigDecimal staffPhonetextMsgInd) throws SQLException
  { _struct.setAttribute(17, staffPhonetextMsgInd); }

}
