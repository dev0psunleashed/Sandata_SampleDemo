package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class RfrlT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.RFRL_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,91,91,91,91,12,12,12,2,2,12,12,12,12,12,12,12,91,91,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[21];
  protected static final RfrlT _RfrlTFactory = new RfrlT();

  public static ORADataFactory getORADataFactory()
  { return _RfrlTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[21], _sqlType, _factory); }
  public RfrlT()
  { _init_struct(true); }
  public RfrlT(java.math.BigDecimal rfrlSk, String rfrlId, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String rfrlTypName, String hcpNpi, String rfrlName, String rfrlReceivalMthd, String rfrlPhone, String rfrlEmail, java.sql.Timestamp rfrlStartDate, java.sql.Timestamp rfrlEndDate, String rfrlSrcName) throws SQLException
  { _init_struct(true);
    setRfrlSk(rfrlSk);
    setRfrlId(rfrlId);
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
    setRfrlTypName(rfrlTypName);
    setHcpNpi(hcpNpi);
    setRfrlName(rfrlName);
    setRfrlReceivalMthd(rfrlReceivalMthd);
    setRfrlPhone(rfrlPhone);
    setRfrlEmail(rfrlEmail);
    setRfrlStartDate(rfrlStartDate);
    setRfrlEndDate(rfrlEndDate);
    setRfrlSrcName(rfrlSrcName);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(RfrlT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new RfrlT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getRfrlSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setRfrlSk(java.math.BigDecimal rfrlSk) throws SQLException
  { _struct.setAttribute(0, rfrlSk); }


  public String getRfrlId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setRfrlId(String rfrlId) throws SQLException
  { _struct.setAttribute(1, rfrlId); }


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

  public String getRfrlTypName() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setRfrlTypName(String rfrlTypName) throws SQLException
  { _struct.setAttribute(12, rfrlTypName); }


  public String getHcpNpi() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setHcpNpi(String hcpNpi) throws SQLException
  { _struct.setAttribute(13, hcpNpi); }


  public String getRfrlName() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setRfrlName(String rfrlName) throws SQLException
  { _struct.setAttribute(14, rfrlName); }


  public String getRfrlReceivalMthd() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setRfrlReceivalMthd(String rfrlReceivalMthd) throws SQLException
  { _struct.setAttribute(15, rfrlReceivalMthd); }


  public String getRfrlPhone() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setRfrlPhone(String rfrlPhone) throws SQLException
  { _struct.setAttribute(16, rfrlPhone); }


  public String getRfrlEmail() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setRfrlEmail(String rfrlEmail) throws SQLException
  { _struct.setAttribute(17, rfrlEmail); }


  public java.sql.Timestamp getRfrlStartDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(18); }

  public void setRfrlStartDate(java.sql.Timestamp rfrlStartDate) throws SQLException
  { _struct.setAttribute(18, rfrlStartDate); }


  public java.sql.Timestamp getRfrlEndDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(19); }

  public void setRfrlEndDate(java.sql.Timestamp rfrlEndDate) throws SQLException
  { _struct.setAttribute(19, rfrlEndDate); }


  public String getRfrlSrcName() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setRfrlSrcName(String rfrlSrcName) throws SQLException
  { _struct.setAttribute(20, rfrlSrcName); }

}
