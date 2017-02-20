package com.sandata.lab.data.model.jpub.model;

import oracle.jdbc.OracleTypes;
import oracle.jpub.runtime.MutableStruct;
import oracle.sql.Datum;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;

import java.sql.Connection;
import java.sql.SQLException;

public class BeCompRelDetHistT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.BE_COMP_REL_DET_HIST_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,12,2,91,91,12,12,12,2,12,2,12,2,12,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[17];
  protected static final BeCompRelDetHistT _BeCompRelDetHistTFactory = new BeCompRelDetHistT();

  public static ORADataFactory getORADataFactory()
  { return _BeCompRelDetHistTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[17], _sqlType, _factory); }
  public BeCompRelDetHistT()
  { _init_struct(true); }
  public BeCompRelDetHistT(java.math.BigDecimal beCompRelDetHistSk, java.sql.Timestamp recCreateTmstpHist, String actionCode, java.math.BigDecimal beCompRelDetSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal changeVersionId, String beId, java.math.BigDecimal beCompRelSk, String compResultRdngVal, java.math.BigDecimal compliantInd, java.math.BigDecimal compMandComplThreshold, java.math.BigDecimal compStopRecurInd, String compMandComplThreasholdUom) throws SQLException
  { _init_struct(true);
    setBeCompRelDetHistSk(beCompRelDetHistSk);
    setRecCreateTmstpHist(recCreateTmstpHist);
    setActionCode(actionCode);
    setBeCompRelDetSk(beCompRelDetSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonMemo(changeReasonMemo);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setBeCompRelSk(beCompRelSk);
    setCompResultRdngVal(compResultRdngVal);
    setCompliantInd(compliantInd);
    setCompMandComplThreshold(compMandComplThreshold);
    setCompStopRecurInd(compStopRecurInd);
    setCompMandComplThreasholdUom(compMandComplThreasholdUom);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(BeCompRelDetHistT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new BeCompRelDetHistT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getBeCompRelDetHistSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setBeCompRelDetHistSk(java.math.BigDecimal beCompRelDetHistSk) throws SQLException
  { _struct.setAttribute(0, beCompRelDetHistSk); }


  public java.sql.Timestamp getRecCreateTmstpHist() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstpHist(java.sql.Timestamp recCreateTmstpHist) throws SQLException
  { _struct.setAttribute(1, recCreateTmstpHist); }


  public String getActionCode() throws SQLException
  { return (String) _struct.getAttribute(2); }

  public void setActionCode(String actionCode) throws SQLException
  { _struct.setAttribute(2, actionCode); }


  public java.math.BigDecimal getBeCompRelDetSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setBeCompRelDetSk(java.math.BigDecimal beCompRelDetSk) throws SQLException
  { _struct.setAttribute(3, beCompRelDetSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(4); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(4, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(5); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(5, recUpdateTmstp); }


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


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(9); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(9, changeVersionId); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(10, beId); }


  public java.math.BigDecimal getBeCompRelSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(11); }

  public void setBeCompRelSk(java.math.BigDecimal beCompRelSk) throws SQLException
  { _struct.setAttribute(11, beCompRelSk); }


  public String getCompResultRdngVal() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setCompResultRdngVal(String compResultRdngVal) throws SQLException
  { _struct.setAttribute(12, compResultRdngVal); }


  public java.math.BigDecimal getCompliantInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(13); }

  public void setCompliantInd(java.math.BigDecimal compliantInd) throws SQLException
  { _struct.setAttribute(13, compliantInd); }


  public java.math.BigDecimal getCompMandComplThreshold() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(14); }

  public void setCompMandComplThreshold(java.math.BigDecimal compMandComplThreshold) throws SQLException
  { _struct.setAttribute(14, compMandComplThreshold); }

  public java.math.BigDecimal getCompStopRecurInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(15); }

  public void setCompStopRecurInd(java.math.BigDecimal compStopRecurInd) throws SQLException
  { _struct.setAttribute(15, compStopRecurInd); }

  public String getCompMandComplThreasholdUom() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setCompMandComplThreasholdUom(String compMandComplThreasholdUom) throws SQLException
  { _struct.setAttribute(16, compMandComplThreasholdUom); }

}
