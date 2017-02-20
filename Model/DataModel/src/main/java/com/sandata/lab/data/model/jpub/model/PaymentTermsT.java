package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class PaymentTermsT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PAYMENT_TERMS_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,12,2,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[7];
  protected static final PaymentTermsT _PaymentTermsTFactory = new PaymentTermsT();

  public static ORADataFactory getORADataFactory()
  { return _PaymentTermsTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[7], _sqlType, _factory); }
  public PaymentTermsT()
  { _init_struct(true); }
  public PaymentTermsT(java.math.BigDecimal paymentTermsSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal changeVersionId, String paymentTermsId, java.math.BigDecimal paymentDue, java.math.BigDecimal discountApplied) throws SQLException
  { _init_struct(true);
    setPaymentTermsSk(paymentTermsSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setChangeVersionId(changeVersionId);
    setPaymentTermsId(paymentTermsId);
    setPaymentDue(paymentDue);
    setDiscountApplied(discountApplied);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PaymentTermsT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PaymentTermsT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPaymentTermsSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPaymentTermsSk(java.math.BigDecimal paymentTermsSk) throws SQLException
  { _struct.setAttribute(0, paymentTermsSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(3, changeVersionId); }


  public String getPaymentTermsId() throws SQLException
  { return (String) _struct.getAttribute(4); }

  public void setPaymentTermsId(String paymentTermsId) throws SQLException
  { _struct.setAttribute(4, paymentTermsId); }


  public java.math.BigDecimal getPaymentDue() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(5); }

  public void setPaymentDue(java.math.BigDecimal paymentDue) throws SQLException
  { _struct.setAttribute(5, paymentDue); }


  public java.math.BigDecimal getDiscountApplied() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(6); }

  public void setDiscountApplied(java.math.BigDecimal discountApplied) throws SQLException
  { _struct.setAttribute(6, discountApplied); }

}
