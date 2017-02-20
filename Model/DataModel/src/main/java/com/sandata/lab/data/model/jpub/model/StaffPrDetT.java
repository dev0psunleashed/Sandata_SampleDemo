package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class StaffPrDetT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.STAFF_PR_DET_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,91,91,91,91,12,12,12,2,2,12,12,12,12,2,12,2,12,1,12,12,12,12,12,2,2,2,2,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[31];
  protected static final StaffPrDetT _StaffPrDetTFactory = new StaffPrDetT();

  public static ORADataFactory getORADataFactory()
  { return _StaffPrDetTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[31], _sqlType, _factory); }
  public StaffPrDetT()
  { _init_struct(true); }
  public StaffPrDetT(java.math.BigDecimal staffPrDetSk, String staffPrDetId, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String staffId, String staffEmployeePrTypName, String staffPayFreq, java.math.BigDecimal staffPrFederalExemptions, String staffFederalMrtlStatusName, java.math.BigDecimal staffPrStateExemptions, String staffStateMrtlStatusName, String staffStateOfResidence, String staffEin, String staffStateTaxNum, String cityTax1, String cityTax2, String stateTax, java.math.BigDecimal staffHourlyRateInd, java.math.BigDecimal staffDailyRateInd, java.math.BigDecimal staffOverrideRate, java.math.BigDecimal holidayRateBypassInd, String staffPrBankRouting, String staffPrNotes) throws SQLException
  { _init_struct(true);
    setStaffPrDetSk(staffPrDetSk);
    setStaffPrDetId(staffPrDetId);
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
    setStaffId(staffId);
    setStaffEmployeePrTypName(staffEmployeePrTypName);
    setStaffPayFreq(staffPayFreq);
    setStaffPrFederalExemptions(staffPrFederalExemptions);
    setStaffFederalMrtlStatusName(staffFederalMrtlStatusName);
    setStaffPrStateExemptions(staffPrStateExemptions);
    setStaffStateMrtlStatusName(staffStateMrtlStatusName);
    setStaffStateOfResidence(staffStateOfResidence);
    setStaffEin(staffEin);
    setStaffStateTaxNum(staffStateTaxNum);
    setCityTax1(cityTax1);
    setCityTax2(cityTax2);
    setStateTax(stateTax);
    setStaffHourlyRateInd(staffHourlyRateInd);
    setStaffDailyRateInd(staffDailyRateInd);
    setStaffOverrideRate(staffOverrideRate);
    setHolidayRateBypassInd(holidayRateBypassInd);
    setStaffPrBankRouting(staffPrBankRouting);
    setStaffPrNotes(staffPrNotes);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(StaffPrDetT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new StaffPrDetT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getStaffPrDetSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setStaffPrDetSk(java.math.BigDecimal staffPrDetSk) throws SQLException
  { _struct.setAttribute(0, staffPrDetSk); }


  public String getStaffPrDetId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setStaffPrDetId(String staffPrDetId) throws SQLException
  { _struct.setAttribute(1, staffPrDetId); }


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


  public String getStaffId() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setStaffId(String staffId) throws SQLException
  { _struct.setAttribute(12, staffId); }


  public String getStaffEmployeePrTypName() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setStaffEmployeePrTypName(String staffEmployeePrTypName) throws SQLException
  { _struct.setAttribute(13, staffEmployeePrTypName); }


  public String getStaffPayFreq() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setStaffPayFreq(String staffPayFreq) throws SQLException
  { _struct.setAttribute(14, staffPayFreq); }


  public java.math.BigDecimal getStaffPrFederalExemptions() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(15); }

  public void setStaffPrFederalExemptions(java.math.BigDecimal staffPrFederalExemptions) throws SQLException
  { _struct.setAttribute(15, staffPrFederalExemptions); }


  public String getStaffFederalMrtlStatusName() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setStaffFederalMrtlStatusName(String staffFederalMrtlStatusName) throws SQLException
  { _struct.setAttribute(16, staffFederalMrtlStatusName); }


  public java.math.BigDecimal getStaffPrStateExemptions() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(17); }

  public void setStaffPrStateExemptions(java.math.BigDecimal staffPrStateExemptions) throws SQLException
  { _struct.setAttribute(17, staffPrStateExemptions); }


  public String getStaffStateMrtlStatusName() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setStaffStateMrtlStatusName(String staffStateMrtlStatusName) throws SQLException
  { _struct.setAttribute(18, staffStateMrtlStatusName); }


  public String getStaffStateOfResidence() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setStaffStateOfResidence(String staffStateOfResidence) throws SQLException
  { _struct.setAttribute(19, staffStateOfResidence); }


  public String getStaffEin() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setStaffEin(String staffEin) throws SQLException
  { _struct.setAttribute(20, staffEin); }


  public String getStaffStateTaxNum() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setStaffStateTaxNum(String staffStateTaxNum) throws SQLException
  { _struct.setAttribute(21, staffStateTaxNum); }


  public String getCityTax1() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setCityTax1(String cityTax1) throws SQLException
  { _struct.setAttribute(22, cityTax1); }


  public String getCityTax2() throws SQLException
  { return (String) _struct.getAttribute(23); }

  public void setCityTax2(String cityTax2) throws SQLException
  { _struct.setAttribute(23, cityTax2); }


  public String getStateTax() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setStateTax(String stateTax) throws SQLException
  { _struct.setAttribute(24, stateTax); }


  public java.math.BigDecimal getStaffHourlyRateInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(25); }

  public void setStaffHourlyRateInd(java.math.BigDecimal staffHourlyRateInd) throws SQLException
  { _struct.setAttribute(25, staffHourlyRateInd); }


  public java.math.BigDecimal getStaffDailyRateInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(26); }

  public void setStaffDailyRateInd(java.math.BigDecimal staffDailyRateInd) throws SQLException
  { _struct.setAttribute(26, staffDailyRateInd); }


  public java.math.BigDecimal getStaffOverrideRate() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(27); }

  public void setStaffOverrideRate(java.math.BigDecimal staffOverrideRate) throws SQLException
  { _struct.setAttribute(27, staffOverrideRate); }


  public java.math.BigDecimal getHolidayRateBypassInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(28); }

  public void setHolidayRateBypassInd(java.math.BigDecimal holidayRateBypassInd) throws SQLException
  { _struct.setAttribute(28, holidayRateBypassInd); }


  public String getStaffPrBankRouting() throws SQLException
  { return (String) _struct.getAttribute(29); }

  public void setStaffPrBankRouting(String staffPrBankRouting) throws SQLException
  { _struct.setAttribute(29, staffPrBankRouting); }


  public String getStaffPrNotes() throws SQLException
  { return (String) _struct.getAttribute(30); }

  public void setStaffPrNotes(String staffPrNotes) throws SQLException
  { _struct.setAttribute(30, staffPrNotes); }

}
