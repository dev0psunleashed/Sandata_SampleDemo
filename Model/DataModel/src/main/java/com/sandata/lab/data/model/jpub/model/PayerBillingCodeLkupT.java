package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class PayerBillingCodeLkupT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PAYER_BILLING_CODE_LKUP_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,2,2,12,12,12,12,12,91,91 };
  protected static ORADataFactory[] _factory = new ORADataFactory[17];
  protected static final PayerBillingCodeLkupT _PayerBillingCodeLkupTFactory = new PayerBillingCodeLkupT();

  public static ORADataFactory getORADataFactory()
  { return _PayerBillingCodeLkupTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[17], _sqlType, _factory); }
  public PayerBillingCodeLkupT()
  { _init_struct(true); }
  public PayerBillingCodeLkupT(java.math.BigDecimal payerBillingCodeLkupSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String payerId, String billingCode, String billingCodeShortDesc, String billingCodeLongDesc, java.sql.Timestamp billingCodeEffDate, java.sql.Timestamp billingCodeTermDate) throws SQLException
  { _init_struct(true);
    setPayerBillingCodeLkupSk(payerBillingCodeLkupSk);
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
    setPayerId(payerId);
    setBillingCode(billingCode);
    setBillingCodeShortDesc(billingCodeShortDesc);
    setBillingCodeLongDesc(billingCodeLongDesc);
    setBillingCodeEffDate(billingCodeEffDate);
    setBillingCodeTermDate(billingCodeTermDate);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PayerBillingCodeLkupT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PayerBillingCodeLkupT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPayerBillingCodeLkupSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPayerBillingCodeLkupSk(java.math.BigDecimal payerBillingCodeLkupSk) throws SQLException
  { _struct.setAttribute(0, payerBillingCodeLkupSk); }


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


  public String getPayerId() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setPayerId(String payerId) throws SQLException
  { _struct.setAttribute(11, payerId); }


  public String getBillingCode() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setBillingCode(String billingCode) throws SQLException
  { _struct.setAttribute(12, billingCode); }


  public String getBillingCodeShortDesc() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setBillingCodeShortDesc(String billingCodeShortDesc) throws SQLException
  { _struct.setAttribute(13, billingCodeShortDesc); }


  public String getBillingCodeLongDesc() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setBillingCodeLongDesc(String billingCodeLongDesc) throws SQLException
  { _struct.setAttribute(14, billingCodeLongDesc); }


  public java.sql.Timestamp getBillingCodeEffDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(15); }

  public void setBillingCodeEffDate(java.sql.Timestamp billingCodeEffDate) throws SQLException
  { _struct.setAttribute(15, billingCodeEffDate); }


  public java.sql.Timestamp getBillingCodeTermDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(16); }

  public void setBillingCodeTermDate(java.sql.Timestamp billingCodeTermDate) throws SQLException
  { _struct.setAttribute(16, billingCodeTermDate); }

}
