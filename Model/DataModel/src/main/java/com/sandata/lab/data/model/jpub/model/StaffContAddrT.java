package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class StaffContAddrT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.STAFF_CONT_ADDR_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,2,12,2,12,12,12,12,2,12,12,12,12,1,12,12,12,12,12,2,2,2,91 };
  protected static ORADataFactory[] _factory = new ORADataFactory[29];
  protected static final StaffContAddrT _StaffContAddrTFactory = new StaffContAddrT();

  public static ORADataFactory getORADataFactory()
  { return _StaffContAddrTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[29], _sqlType, _factory); }
  public StaffContAddrT()
  { _init_struct(true); }
  public StaffContAddrT(java.math.BigDecimal staffContAddrSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, java.math.BigDecimal currRecInd, String changeReasonMemo, java.math.BigDecimal changeVersionId, String beId, String staffId, String addrPrioName, String staffAddrTypName, java.math.BigDecimal staffAddrUseForMailInd, String staffAddr1, String staffAddr2, String staffAptNum, String staffCity, String staffState, String staffPstlCode, String staffZip4, String staffCounty, String staffRegion, String staffBorough, java.math.BigDecimal staffCoordLatitude, java.math.BigDecimal staffCoordLongitude, java.math.BigDecimal staffCoordAltitude, java.sql.Timestamp staffCoordTmstp) throws SQLException
  { _init_struct(true);
    setStaffContAddrSk(staffContAddrSk);
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
    setStaffAddrTypName(staffAddrTypName);
    setStaffAddrUseForMailInd(staffAddrUseForMailInd);
    setStaffAddr1(staffAddr1);
    setStaffAddr2(staffAddr2);
    setStaffAptNum(staffAptNum);
    setStaffCity(staffCity);
    setStaffState(staffState);
    setStaffPstlCode(staffPstlCode);
    setStaffZip4(staffZip4);
    setStaffCounty(staffCounty);
    setStaffRegion(staffRegion);
    setStaffBorough(staffBorough);
    setStaffCoordLatitude(staffCoordLatitude);
    setStaffCoordLongitude(staffCoordLongitude);
    setStaffCoordAltitude(staffCoordAltitude);
    setStaffCoordTmstp(staffCoordTmstp);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(StaffContAddrT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new StaffContAddrT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getStaffContAddrSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setStaffContAddrSk(java.math.BigDecimal staffContAddrSk) throws SQLException
  { _struct.setAttribute(0, staffContAddrSk); }


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


  public String getStaffAddrTypName() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setStaffAddrTypName(String staffAddrTypName) throws SQLException
  { _struct.setAttribute(13, staffAddrTypName); }


  public java.math.BigDecimal getStaffAddrUseForMailInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(14); }

  public void setStaffAddrUseForMailInd(java.math.BigDecimal staffAddrUseForMailInd) throws SQLException
  { _struct.setAttribute(14, staffAddrUseForMailInd); }


  public String getStaffAddr1() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setStaffAddr1(String staffAddr1) throws SQLException
  { _struct.setAttribute(15, staffAddr1); }


  public String getStaffAddr2() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setStaffAddr2(String staffAddr2) throws SQLException
  { _struct.setAttribute(16, staffAddr2); }


  public String getStaffAptNum() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setStaffAptNum(String staffAptNum) throws SQLException
  { _struct.setAttribute(17, staffAptNum); }


  public String getStaffCity() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setStaffCity(String staffCity) throws SQLException
  { _struct.setAttribute(18, staffCity); }


  public String getStaffState() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setStaffState(String staffState) throws SQLException
  { _struct.setAttribute(19, staffState); }


  public String getStaffPstlCode() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setStaffPstlCode(String staffPstlCode) throws SQLException
  { _struct.setAttribute(20, staffPstlCode); }


  public String getStaffZip4() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setStaffZip4(String staffZip4) throws SQLException
  { _struct.setAttribute(21, staffZip4); }


  public String getStaffCounty() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setStaffCounty(String staffCounty) throws SQLException
  { _struct.setAttribute(22, staffCounty); }


  public String getStaffRegion() throws SQLException
  { return (String) _struct.getAttribute(23); }

  public void setStaffRegion(String staffRegion) throws SQLException
  { _struct.setAttribute(23, staffRegion); }


  public String getStaffBorough() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setStaffBorough(String staffBorough) throws SQLException
  { _struct.setAttribute(24, staffBorough); }


  public java.math.BigDecimal getStaffCoordLatitude() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(25); }

  public void setStaffCoordLatitude(java.math.BigDecimal staffCoordLatitude) throws SQLException
  { _struct.setAttribute(25, staffCoordLatitude); }


  public java.math.BigDecimal getStaffCoordLongitude() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(26); }

  public void setStaffCoordLongitude(java.math.BigDecimal staffCoordLongitude) throws SQLException
  { _struct.setAttribute(26, staffCoordLongitude); }


  public java.math.BigDecimal getStaffCoordAltitude() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(27); }

  public void setStaffCoordAltitude(java.math.BigDecimal staffCoordAltitude) throws SQLException
  { _struct.setAttribute(27, staffCoordAltitude); }


  public java.sql.Timestamp getStaffCoordTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(28); }

  public void setStaffCoordTmstp(java.sql.Timestamp staffCoordTmstp) throws SQLException
  { _struct.setAttribute(28, staffCoordTmstp); }

}
