package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class PtContEmailT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PT_CONT_EMAIL_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,2,12,2,12,12,12,12,2,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[16];
  protected static final PtContEmailT _PtContEmailTFactory = new PtContEmailT();

  public static ORADataFactory getORADataFactory()
  { return _PtContEmailTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[16], _sqlType, _factory); }
  public PtContEmailT()
  { _init_struct(true); }
  public PtContEmailT(java.math.BigDecimal ptContEmailSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, java.math.BigDecimal currRecInd, String changeReasonMemo, java.math.BigDecimal changeVersionId, String ptId, String beId, String ptContEmailQlfr, String ptEmail, java.math.BigDecimal ptEmailPrmyInd, java.math.BigDecimal ptEmailHtmlInd) throws SQLException
  { _init_struct(true);
    setPtContEmailSk(ptContEmailSk);
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
    setPtContEmailQlfr(ptContEmailQlfr);
    setPtEmail(ptEmail);
    setPtEmailPrmyInd(ptEmailPrmyInd);
    setPtEmailHtmlInd(ptEmailHtmlInd);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PtContEmailT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PtContEmailT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPtContEmailSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPtContEmailSk(java.math.BigDecimal ptContEmailSk) throws SQLException
  { _struct.setAttribute(0, ptContEmailSk); }


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


  public String getPtContEmailQlfr() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setPtContEmailQlfr(String ptContEmailQlfr) throws SQLException
  { _struct.setAttribute(12, ptContEmailQlfr); }


  public String getPtEmail() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setPtEmail(String ptEmail) throws SQLException
  { _struct.setAttribute(13, ptEmail); }


  public java.math.BigDecimal getPtEmailPrmyInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(14); }

  public void setPtEmailPrmyInd(java.math.BigDecimal ptEmailPrmyInd) throws SQLException
  { _struct.setAttribute(14, ptEmailPrmyInd); }


  public java.math.BigDecimal getPtEmailHtmlInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(15); }

  public void setPtEmailHtmlInd(java.math.BigDecimal ptEmailHtmlInd) throws SQLException
  { _struct.setAttribute(15, ptEmailHtmlInd); }

}
