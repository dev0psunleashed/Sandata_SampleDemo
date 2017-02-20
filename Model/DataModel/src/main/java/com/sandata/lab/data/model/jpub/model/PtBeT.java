package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class PtBeT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.PT_BE_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,91,91,12,12,12,2,2,12,12,12,12,12,12,12,12,12,12,91,91 };
  protected static ORADataFactory[] _factory = new ORADataFactory[22];
  protected static final PtBeT _PtBeTFactory = new PtBeT();

  public static ORADataFactory getORADataFactory()
  { return _PtBeTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[22], _sqlType, _factory); }
  public PtBeT()
  { _init_struct(true); }
  public PtBeT(java.math.BigDecimal ptBeSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, String beId, String ptId, String ptBeAdmId, String ptBeClinicalMgr, String ptBeBranch, String ptBeCoordinator, String ptBeMktr, String ptBeStatus, String ptBeSvcs, String ptBeEvntCode, java.sql.Timestamp ptBeStartDate, java.sql.Timestamp ptBeEndDate) throws SQLException
  { _init_struct(true);
    setPtBeSk(ptBeSk);
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
    setPtId(ptId);
    setPtBeAdmId(ptBeAdmId);
    setPtBeClinicalMgr(ptBeClinicalMgr);
    setPtBeBranch(ptBeBranch);
    setPtBeCoordinator(ptBeCoordinator);
    setPtBeMktr(ptBeMktr);
    setPtBeStatus(ptBeStatus);
    setPtBeSvcs(ptBeSvcs);
    setPtBeEvntCode(ptBeEvntCode);
    setPtBeStartDate(ptBeStartDate);
    setPtBeEndDate(ptBeEndDate);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PtBeT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PtBeT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPtBeSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPtBeSk(java.math.BigDecimal ptBeSk) throws SQLException
  { _struct.setAttribute(0, ptBeSk); }


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


  public String getPtId() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setPtId(String ptId) throws SQLException
  { _struct.setAttribute(11, ptId); }


  public String getPtBeAdmId() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setPtBeAdmId(String ptBeAdmId) throws SQLException
  { _struct.setAttribute(12, ptBeAdmId); }


  public String getPtBeClinicalMgr() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setPtBeClinicalMgr(String ptBeClinicalMgr) throws SQLException
  { _struct.setAttribute(13, ptBeClinicalMgr); }


  public String getPtBeBranch() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setPtBeBranch(String ptBeBranch) throws SQLException
  { _struct.setAttribute(14, ptBeBranch); }


  public String getPtBeCoordinator() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setPtBeCoordinator(String ptBeCoordinator) throws SQLException
  { _struct.setAttribute(15, ptBeCoordinator); }


  public String getPtBeMktr() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setPtBeMktr(String ptBeMktr) throws SQLException
  { _struct.setAttribute(16, ptBeMktr); }


  public String getPtBeStatus() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setPtBeStatus(String ptBeStatus) throws SQLException
  { _struct.setAttribute(17, ptBeStatus); }


  public String getPtBeSvcs() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setPtBeSvcs(String ptBeSvcs) throws SQLException
  { _struct.setAttribute(18, ptBeSvcs); }


  public String getPtBeEvntCode() throws SQLException
  { return (String) _struct.getAttribute(19); }

  public void setPtBeEvntCode(String ptBeEvntCode) throws SQLException
  { _struct.setAttribute(19, ptBeEvntCode); }


  public java.sql.Timestamp getPtBeStartDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(20); }

  public void setPtBeStartDate(java.sql.Timestamp ptBeStartDate) throws SQLException
  { _struct.setAttribute(20, ptBeStartDate); }


  public java.sql.Timestamp getPtBeEndDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(21); }

  public void setPtBeEndDate(java.sql.Timestamp ptBeEndDate) throws SQLException
  { _struct.setAttribute(21, ptBeEndDate); }

}
