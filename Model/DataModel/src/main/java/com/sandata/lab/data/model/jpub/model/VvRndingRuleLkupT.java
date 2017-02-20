package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class VvRndingRuleLkupT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.VV_RNDING_RULE_LKUP_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,2,2,12,12,12,12,91,91 };
  protected static ORADataFactory[] _factory = new ORADataFactory[16];
  protected static final VvRndingRuleLkupT _VvRndingRuleLkupTFactory = new VvRndingRuleLkupT();

  public static ORADataFactory getORADataFactory()
  { return _VvRndingRuleLkupTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[16], _sqlType, _factory); }
  public VvRndingRuleLkupT()
  { _init_struct(true); }
  public VvRndingRuleLkupT(java.math.BigDecimal vvRndingRuleLkupSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String vvRndingRuleId, String vvRndingRuleName, String vvRndingRuleDesc, String vvRndingRuleQlfr, java.sql.Timestamp vvRndingRuleEffDate, java.sql.Timestamp vvRndingRuleTermDate) throws SQLException
  { _init_struct(true);
    setVvRndingRuleLkupSk(vvRndingRuleLkupSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonMemo(changeReasonMemo);
    setCurrRecInd(currRecInd);
    setChangeVersionId(changeVersionId);
    setVvRndingRuleId(vvRndingRuleId);
    setVvRndingRuleName(vvRndingRuleName);
    setVvRndingRuleDesc(vvRndingRuleDesc);
    setVvRndingRuleQlfr(vvRndingRuleQlfr);
    setVvRndingRuleEffDate(vvRndingRuleEffDate);
    setVvRndingRuleTermDate(vvRndingRuleTermDate);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(VvRndingRuleLkupT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new VvRndingRuleLkupT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getVvRndingRuleLkupSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setVvRndingRuleLkupSk(java.math.BigDecimal vvRndingRuleLkupSk) throws SQLException
  { _struct.setAttribute(0, vvRndingRuleLkupSk); }


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


  public String getVvRndingRuleId() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setVvRndingRuleId(String vvRndingRuleId) throws SQLException
  { _struct.setAttribute(10, vvRndingRuleId); }


  public String getVvRndingRuleName() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setVvRndingRuleName(String vvRndingRuleName) throws SQLException
  { _struct.setAttribute(11, vvRndingRuleName); }


  public String getVvRndingRuleDesc() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setVvRndingRuleDesc(String vvRndingRuleDesc) throws SQLException
  { _struct.setAttribute(12, vvRndingRuleDesc); }


  public String getVvRndingRuleQlfr() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setVvRndingRuleQlfr(String vvRndingRuleQlfr) throws SQLException
  { _struct.setAttribute(13, vvRndingRuleQlfr); }


  public java.sql.Timestamp getVvRndingRuleEffDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(14); }

  public void setVvRndingRuleEffDate(java.sql.Timestamp vvRndingRuleEffDate) throws SQLException
  { _struct.setAttribute(14, vvRndingRuleEffDate); }


  public java.sql.Timestamp getVvRndingRuleTermDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(15); }

  public void setVvRndingRuleTermDate(java.sql.Timestamp vvRndingRuleTermDate) throws SQLException
  { _struct.setAttribute(15, vvRndingRuleTermDate); }

}
