package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class PtContAddrT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PT_CONT_ADDR_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,2,12,2,12,12,12,12,2,12,12,12,12,1,12,12,12,12,12,2,2,2,91 };
  protected static ORADataFactory[] _factory = new ORADataFactory[29];
  protected static final PtContAddrT _PtContAddrTFactory = new PtContAddrT();

  public static ORADataFactory getORADataFactory()
  { return _PtContAddrTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[29], _sqlType, _factory); }
  public PtContAddrT()
  { _init_struct(true); }
  public PtContAddrT(java.math.BigDecimal ptContAddrSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, java.math.BigDecimal currRecInd, String changeReasonMemo, java.math.BigDecimal changeVersionId, String ptId, String beId, String addrPrioName, String ptAddrTypName, java.math.BigDecimal ptAddrUseForMailInd, String ptAddr1, String ptAddr2, String ptAptNum, String ptCity, String ptState, String ptPstlCode, String ptZip4, String ptCounty, String ptRegion, String ptBorough, java.math.BigDecimal ptCoordLatitude, java.math.BigDecimal ptCoordLongitude, java.math.BigDecimal ptCoordAltitude, java.sql.Timestamp ptCoordTmstp) throws SQLException
  { _init_struct(true);
    setPtContAddrSk(ptContAddrSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setCurrRecInd(currRecInd);
    setChangeReasonMemo(changeReasonMemo);
    setChangeVersionId(changeVersionId);
    setPtId(ptId);
    setBeId(beId);
    setAddrPrioName(addrPrioName);
    setPtAddrTypName(ptAddrTypName);
    setPtAddrUseForMailInd(ptAddrUseForMailInd);
    setPtAddr1(ptAddr1);
    setPtAddr2(ptAddr2);
    setPtAptNum(ptAptNum);
    setPtCity(ptCity);
    setPtState(ptState);
    setPtPstlCode(ptPstlCode);
    setPtZip4(ptZip4);
    setPtCounty(ptCounty);
    setPtRegion(ptRegion);
    setPtBorough(ptBorough);
    setPtCoordLatitude(ptCoordLatitude);
    setPtCoordLongitude(ptCoordLongitude);
    setPtCoordAltitude(ptCoordAltitude);
    setPtCoordTmstp(ptCoordTmstp);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PtContAddrT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PtContAddrT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPtContAddrSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPtContAddrSk(java.math.BigDecimal ptContAddrSk) throws SQLException
  { _struct.setAttribute(0, ptContAddrSk); }


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


  public String getPtId() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setPtId(String ptId) throws SQLException
  { _struct.setAttribute(10, ptId); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(11, beId); }


  public String getAddrPrioName() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setAddrPrioName(String addrPrioName) throws SQLException
  { _struct.setAttribute(12, addrPrioName); }


  public String getPtAddrTypName() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setPtAddrTypName(String ptAddrTypName) throws SQLException
  { _struct.setAttribute(13, ptAddrTypName); }


  public java.math.BigDecimal getPtAddrUseForMailInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(14); }

  public void setPtAddrUseForMailInd(java.math.BigDecimal ptAddrUseForMailInd) throws SQLException
  { _struct.setAttribute(14, ptAddrUseForMailInd); }


  public String getPtAddr1() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setPtAddr1(String ptAddr1) throws SQLException
  { _struct.setAttribute(15, ptAddr1); }


  public String getPtAddr2() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setPtAddr2(String ptAddr2) throws SQLException
  { _struct.setAttribute(16, ptAddr2); }


  public String getPtAptNum() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setPtAptNum(String ptAptNum) throws SQLException
  { _struct.setAttribute(17, ptAptNum); }


  public String getPtCity() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setPtCity(String ptCity) throws SQLException
  { _struct.setAttribute(18, ptCity); }


  public String getPtState() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setPtState(String ptState) throws SQLException
  { _struct.setAttribute(19, ptState); }


  public String getPtPstlCode() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setPtPstlCode(String ptPstlCode) throws SQLException
  { _struct.setAttribute(20, ptPstlCode); }


  public String getPtZip4() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setPtZip4(String ptZip4) throws SQLException
  { _struct.setAttribute(21, ptZip4); }


  public String getPtCounty() throws SQLException
  { return (String) _struct.getAttribute(22); }

  public void setPtCounty(String ptCounty) throws SQLException
  { _struct.setAttribute(22, ptCounty); }


  public String getPtRegion() throws SQLException
  { return (String) _struct.getAttribute(23); }

  public void setPtRegion(String ptRegion) throws SQLException
  { _struct.setAttribute(23, ptRegion); }


  public String getPtBorough() throws SQLException
  { return (String) _struct.getAttribute(24); }

  public void setPtBorough(String ptBorough) throws SQLException
  { _struct.setAttribute(24, ptBorough); }


  public java.math.BigDecimal getPtCoordLatitude() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(25); }

  public void setPtCoordLatitude(java.math.BigDecimal ptCoordLatitude) throws SQLException
  { _struct.setAttribute(25, ptCoordLatitude); }


  public java.math.BigDecimal getPtCoordLongitude() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(26); }

  public void setPtCoordLongitude(java.math.BigDecimal ptCoordLongitude) throws SQLException
  { _struct.setAttribute(26, ptCoordLongitude); }


  public java.math.BigDecimal getPtCoordAltitude() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(27); }

  public void setPtCoordAltitude(java.math.BigDecimal ptCoordAltitude) throws SQLException
  { _struct.setAttribute(27, ptCoordAltitude); }


  public java.sql.Timestamp getPtCoordTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(28); }

  public void setPtCoordTmstp(java.sql.Timestamp ptCoordTmstp) throws SQLException
  { _struct.setAttribute(28, ptCoordTmstp); }

}
