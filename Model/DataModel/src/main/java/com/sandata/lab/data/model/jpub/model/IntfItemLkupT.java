package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class IntfItemLkupT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.INTF_ITEM_LKUP_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,2,2,12,12,12,12,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[16];
  protected static final IntfItemLkupT _IntfItemLkupTFactory = new IntfItemLkupT();

  public static ORADataFactory getORADataFactory()
  { return _IntfItemLkupTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[16], _sqlType, _factory); }
  public IntfItemLkupT()
  { _init_struct(true); }
  public IntfItemLkupT(java.math.BigDecimal intfItemLkupSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String intfId, String intfItemName, String intfItemTypQlfr, String intfItemDesc, String intfItemDef, String intfItemValidRule) throws SQLException
  { _init_struct(true);
    setIntfItemLkupSk(intfItemLkupSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonMemo(changeReasonMemo);
    setCurrRecInd(currRecInd);
    setChangeVersionId(changeVersionId);
    setIntfId(intfId);
    setIntfItemName(intfItemName);
    setIntfItemTypQlfr(intfItemTypQlfr);
    setIntfItemDesc(intfItemDesc);
    setIntfItemDef(intfItemDef);
    setIntfItemValidRule(intfItemValidRule);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(IntfItemLkupT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new IntfItemLkupT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getIntfItemLkupSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setIntfItemLkupSk(java.math.BigDecimal intfItemLkupSk) throws SQLException
  { _struct.setAttribute(0, intfItemLkupSk); }


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


  public String getIntfId() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setIntfId(String intfId) throws SQLException
  { _struct.setAttribute(10, intfId); }


  public String getIntfItemName() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setIntfItemName(String intfItemName) throws SQLException
  { _struct.setAttribute(11, intfItemName); }


  public String getIntfItemTypQlfr() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setIntfItemTypQlfr(String intfItemTypQlfr) throws SQLException
  { _struct.setAttribute(12, intfItemTypQlfr); }


  public String getIntfItemDesc() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setIntfItemDesc(String intfItemDesc) throws SQLException
  { _struct.setAttribute(13, intfItemDesc); }


  public String getIntfItemDef() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setIntfItemDef(String intfItemDef) throws SQLException
  { _struct.setAttribute(14, intfItemDef); }


  public String getIntfItemValidRule() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setIntfItemValidRule(String intfItemValidRule) throws SQLException
  { _struct.setAttribute(15, intfItemValidRule); }

}
