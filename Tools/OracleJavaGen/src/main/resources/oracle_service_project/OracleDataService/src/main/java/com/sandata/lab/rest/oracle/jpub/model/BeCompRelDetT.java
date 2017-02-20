package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class BeCompRelDetT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.BE_COMP_REL_DET_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,12,12,12,12,2,12,2,12,2,2,2,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[15];
  protected static final BeCompRelDetT _BeCompRelDetTFactory = new BeCompRelDetT();

  public static ORADataFactory getORADataFactory()
  { return _BeCompRelDetTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[15], _sqlType, _factory); }
  public BeCompRelDetT()
  { _init_struct(true); }
  public BeCompRelDetT(java.math.BigDecimal beCompRelDetSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonCode, String changeReasonMemo, java.math.BigDecimal changeVersionId, String beId, java.math.BigDecimal beCompRelSk, String compResultRdngVal, java.math.BigDecimal compliantInd, java.math.BigDecimal compMandComplThreshold, java.math.BigDecimal compStopRecurInd, String compMandComplThresholdUom) throws SQLException
  { _init_struct(true);
    setBeCompRelDetSk(beCompRelDetSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonCode(changeReasonCode);
    setChangeReasonMemo(changeReasonMemo);
    setChangeVersionId(changeVersionId);
    setBeId(beId);
    setBeCompRelSk(beCompRelSk);
    setCompResultRdngVal(compResultRdngVal);
    setCompliantInd(compliantInd);
    setCompMandComplThreshold(compMandComplThreshold);
    setCompStopRecurInd(compStopRecurInd);
    setCompMandComplThresholdUom(compMandComplThresholdUom);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(BeCompRelDetT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new BeCompRelDetT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getBeCompRelDetSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setBeCompRelDetSk(java.math.BigDecimal beCompRelDetSk) throws SQLException
  { _struct.setAttribute(0, beCompRelDetSk); }


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


  public String getChangeReasonCode() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setChangeReasonCode(String changeReasonCode) throws SQLException
  { _struct.setAttribute(5, changeReasonCode); }


  public String getChangeReasonMemo() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setChangeReasonMemo(String changeReasonMemo) throws SQLException
  { _struct.setAttribute(6, changeReasonMemo); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(7); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(7, changeVersionId); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(8, beId); }


  public java.math.BigDecimal getBeCompRelSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(9); }

  public void setBeCompRelSk(java.math.BigDecimal beCompRelSk) throws SQLException
  { _struct.setAttribute(9, beCompRelSk); }


  public String getCompResultRdngVal() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setCompResultRdngVal(String compResultRdngVal) throws SQLException
  { _struct.setAttribute(10, compResultRdngVal); }


  public java.math.BigDecimal getCompliantInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(11); }

  public void setCompliantInd(java.math.BigDecimal compliantInd) throws SQLException
  { _struct.setAttribute(11, compliantInd); }


  public java.math.BigDecimal getCompMandComplThreshold() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(12); }

  public void setCompMandComplThreshold(java.math.BigDecimal compMandComplThreshold) throws SQLException
  { _struct.setAttribute(12, compMandComplThreshold); }


  public java.math.BigDecimal getCompStopRecurInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(13); }

  public void setCompStopRecurInd(java.math.BigDecimal compStopRecurInd) throws SQLException
  { _struct.setAttribute(13, compStopRecurInd); }


  public String getCompMandComplThresholdUom() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setCompMandComplThresholdUom(String compMandComplThresholdUom) throws SQLException
  { _struct.setAttribute(14, compMandComplThresholdUom); }

}
