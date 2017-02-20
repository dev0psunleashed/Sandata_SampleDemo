package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class PtContPhoneT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PT_CONT_PHONE_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,2,12,2,12,12,12,12,12,12,2,2,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[19];
  protected static final PtContPhoneT _PtContPhoneTFactory = new PtContPhoneT();

  public static ORADataFactory getORADataFactory()
  { return _PtContPhoneTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[19], _sqlType, _factory); }
  public PtContPhoneT()
  { _init_struct(true); }
  public PtContPhoneT(java.math.BigDecimal ptContPhoneSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, java.math.BigDecimal currRecInd, String changeReasonMemo, java.math.BigDecimal changeVersionId, String beId, String ptId, String addrPrioName, String ptContPhoneQlfr, String ptPhone, String ptPhoneExt, java.math.BigDecimal ptPhoneAniEnabledInd, java.math.BigDecimal ptPhonePrmyInd, java.math.BigDecimal ptPhonetextMsgInd) throws SQLException
  { _init_struct(true);
    setPtContPhoneSk(ptContPhoneSk);
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
    setPtId(ptId);
    setAddrPrioName(addrPrioName);
    setPtContPhoneQlfr(ptContPhoneQlfr);
    setPtPhone(ptPhone);
    setPtPhoneExt(ptPhoneExt);
    setPtPhoneAniEnabledInd(ptPhoneAniEnabledInd);
    setPtPhonePrmyInd(ptPhonePrmyInd);
    setPtPhonetextMsgInd(ptPhonetextMsgInd);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PtContPhoneT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PtContPhoneT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPtContPhoneSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPtContPhoneSk(java.math.BigDecimal ptContPhoneSk) throws SQLException
  { _struct.setAttribute(0, ptContPhoneSk); }


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


  public String getPtId() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setPtId(String ptId) throws SQLException
  { _struct.setAttribute(11, ptId); }


  public String getAddrPrioName() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setAddrPrioName(String addrPrioName) throws SQLException
  { _struct.setAttribute(12, addrPrioName); }


  public String getPtContPhoneQlfr() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setPtContPhoneQlfr(String ptContPhoneQlfr) throws SQLException
  { _struct.setAttribute(13, ptContPhoneQlfr); }


  public String getPtPhone() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setPtPhone(String ptPhone) throws SQLException
  { _struct.setAttribute(14, ptPhone); }


  public String getPtPhoneExt() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setPtPhoneExt(String ptPhoneExt) throws SQLException
  { _struct.setAttribute(15, ptPhoneExt); }


  public java.math.BigDecimal getPtPhoneAniEnabledInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(16); }

  public void setPtPhoneAniEnabledInd(java.math.BigDecimal ptPhoneAniEnabledInd) throws SQLException
  { _struct.setAttribute(16, ptPhoneAniEnabledInd); }


  public java.math.BigDecimal getPtPhonePrmyInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(17); }

  public void setPtPhonePrmyInd(java.math.BigDecimal ptPhonePrmyInd) throws SQLException
  { _struct.setAttribute(17, ptPhonePrmyInd); }


  public java.math.BigDecimal getPtPhonetextMsgInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(18); }

  public void setPtPhonetextMsgInd(java.math.BigDecimal ptPhonetextMsgInd) throws SQLException
  { _struct.setAttribute(18, ptPhonetextMsgInd); }

}
