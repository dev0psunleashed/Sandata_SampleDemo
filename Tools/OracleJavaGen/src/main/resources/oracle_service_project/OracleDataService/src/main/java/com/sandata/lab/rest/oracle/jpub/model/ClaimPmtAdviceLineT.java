package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class ClaimPmtAdviceLineT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.CLAIM_PMT_ADVICE_LINE_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,12,12,2,2,12,12,12,12,12,12,12,2,2,2,12,91,12,12,12,2,12,2,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[26];
  protected static final ClaimPmtAdviceLineT _ClaimPmtAdviceLineTFactory = new ClaimPmtAdviceLineT();

  public static ORADataFactory getORADataFactory()
  { return _ClaimPmtAdviceLineTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[26], _sqlType, _factory); }
  public ClaimPmtAdviceLineT()
  { _init_struct(true); }
  public ClaimPmtAdviceLineT(java.math.BigDecimal claimPmtAdviceLineSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String recCreatedBy, String recUpdatedBy, java.math.BigDecimal changeVersionId, java.math.BigDecimal claimPmtAdviceSk, String beId, String billingCodeTypQlfr, String billingCode, String mdfr1Code, String mdfr2Code, String mdfr3Code, String mdfr4Code, java.math.BigDecimal invDetTotalAmt, java.math.BigDecimal invDetProviderPmtAmt, java.math.BigDecimal invDetProviderTotalUnit, String claimLinePmtDateQlfr, java.sql.Timestamp claimLinePmtDate, String claimLinePmtIdQlfr, String claimLinePmtId, String claimLineAmtQlfr, java.math.BigDecimal claimLineAmt, String claimLineCrn, java.math.BigDecimal claimLineSvcLineItemNum, String payerId) throws SQLException
  { _init_struct(true);
    setClaimPmtAdviceLineSk(claimPmtAdviceLineSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeVersionId(changeVersionId);
    setClaimPmtAdviceSk(claimPmtAdviceSk);
    setBeId(beId);
    setBillingCodeTypQlfr(billingCodeTypQlfr);
    setBillingCode(billingCode);
    setMdfr1Code(mdfr1Code);
    setMdfr2Code(mdfr2Code);
    setMdfr3Code(mdfr3Code);
    setMdfr4Code(mdfr4Code);
    setInvDetTotalAmt(invDetTotalAmt);
    setInvDetProviderPmtAmt(invDetProviderPmtAmt);
    setInvDetProviderTotalUnit(invDetProviderTotalUnit);
    setClaimLinePmtDateQlfr(claimLinePmtDateQlfr);
    setClaimLinePmtDate(claimLinePmtDate);
    setClaimLinePmtIdQlfr(claimLinePmtIdQlfr);
    setClaimLinePmtId(claimLinePmtId);
    setClaimLineAmtQlfr(claimLineAmtQlfr);
    setClaimLineAmt(claimLineAmt);
    setClaimLineCrn(claimLineCrn);
    setClaimLineSvcLineItemNum(claimLineSvcLineItemNum);
    setPayerId(payerId);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(ClaimPmtAdviceLineT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new ClaimPmtAdviceLineT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getClaimPmtAdviceLineSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setClaimPmtAdviceLineSk(java.math.BigDecimal claimPmtAdviceLineSk) throws SQLException
  { _struct.setAttribute(0, claimPmtAdviceLineSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public String getRecCreatedBy() throws SQLException
  { return (String) _struct.getAttribute(3); }

  public void setRecCreatedBy(String recCreatedBy) throws SQLException
  { _struct.setAttribute(3, recCreatedBy); }


  public String getRecUpdatedBy() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setRecUpdatedBy(String recUpdatedBy) throws SQLException
  { _struct.setAttribute(4, recUpdatedBy); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(5); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(5, changeVersionId); }


  public java.math.BigDecimal getClaimPmtAdviceSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(6); }

  public void setClaimPmtAdviceSk(java.math.BigDecimal claimPmtAdviceSk) throws SQLException
  { _struct.setAttribute(6, claimPmtAdviceSk); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(7, beId); }


  public String getBillingCodeTypQlfr() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setBillingCodeTypQlfr(String billingCodeTypQlfr) throws SQLException
  { _struct.setAttribute(8, billingCodeTypQlfr); }


  public String getBillingCode() throws SQLException
  { return (String) _struct.getAttribute(9); }

  public void setBillingCode(String billingCode) throws SQLException
  { _struct.setAttribute(9, billingCode); }


  public String getMdfr1Code() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setMdfr1Code(String mdfr1Code) throws SQLException
  { _struct.setAttribute(10, mdfr1Code); }


  public String getMdfr2Code() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setMdfr2Code(String mdfr2Code) throws SQLException
  { _struct.setAttribute(11, mdfr2Code); }


  public String getMdfr3Code() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setMdfr3Code(String mdfr3Code) throws SQLException
  { _struct.setAttribute(12, mdfr3Code); }


  public String getMdfr4Code() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setMdfr4Code(String mdfr4Code) throws SQLException
  { _struct.setAttribute(13, mdfr4Code); }


  public java.math.BigDecimal getInvDetTotalAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(14); }

  public void setInvDetTotalAmt(java.math.BigDecimal invDetTotalAmt) throws SQLException
  { _struct.setAttribute(14, invDetTotalAmt); }


  public java.math.BigDecimal getInvDetProviderPmtAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(15); }

  public void setInvDetProviderPmtAmt(java.math.BigDecimal invDetProviderPmtAmt) throws SQLException
  { _struct.setAttribute(15, invDetProviderPmtAmt); }


  public java.math.BigDecimal getInvDetProviderTotalUnit() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(16); }

  public void setInvDetProviderTotalUnit(java.math.BigDecimal invDetProviderTotalUnit) throws SQLException
  { _struct.setAttribute(16, invDetProviderTotalUnit); }


  public String getClaimLinePmtDateQlfr() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setClaimLinePmtDateQlfr(String claimLinePmtDateQlfr) throws SQLException
  { _struct.setAttribute(17, claimLinePmtDateQlfr); }


  public java.sql.Timestamp getClaimLinePmtDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(18); }

  public void setClaimLinePmtDate(java.sql.Timestamp claimLinePmtDate) throws SQLException
  { _struct.setAttribute(18, claimLinePmtDate); }


  public String getClaimLinePmtIdQlfr() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setClaimLinePmtIdQlfr(String claimLinePmtIdQlfr) throws SQLException
  { _struct.setAttribute(19, claimLinePmtIdQlfr); }


  public String getClaimLinePmtId() throws SQLException
  { return (String) _struct.getAttribute(20); }

  public void setClaimLinePmtId(String claimLinePmtId) throws SQLException
  { _struct.setAttribute(20, claimLinePmtId); }


  public String getClaimLineAmtQlfr() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setClaimLineAmtQlfr(String claimLineAmtQlfr) throws SQLException
  { _struct.setAttribute(21, claimLineAmtQlfr); }


  public java.math.BigDecimal getClaimLineAmt() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(22); }

  public void setClaimLineAmt(java.math.BigDecimal claimLineAmt) throws SQLException
  { _struct.setAttribute(22, claimLineAmt); }


  public String getClaimLineCrn() throws SQLException
  { return (String) _struct.getAttribute(23); }

  public void setClaimLineCrn(String claimLineCrn) throws SQLException
  { _struct.setAttribute(23, claimLineCrn); }


  public java.math.BigDecimal getClaimLineSvcLineItemNum() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(24); }

  public void setClaimLineSvcLineItemNum(java.math.BigDecimal claimLineSvcLineItemNum) throws SQLException
  { _struct.setAttribute(24, claimLineSvcLineItemNum); }


  public String getPayerId() throws SQLException
  { return (String) _struct.getAttribute(25); }

  public void setPayerId(String payerId) throws SQLException
  { _struct.setAttribute(25, payerId); }

}
