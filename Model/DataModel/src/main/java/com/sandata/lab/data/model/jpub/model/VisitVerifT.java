package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class VisitVerifT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.VISIT_VERIF_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,12,91,91,91,91,12,12,12,2,2,2,12,12,12,2,2,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[18];
  protected static final VisitVerifT _VisitVerifTFactory = new VisitVerifT();

  public static ORADataFactory getORADataFactory()
  { return _VisitVerifTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[18], _sqlType, _factory); }
  public VisitVerifT()
  { _init_struct(true); }
  public VisitVerifT(java.math.BigDecimal visitVerifSk, String visitVerifId, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.sql.Timestamp recEffTmstp, java.sql.Timestamp recTermTmstp, String recCreatedBy, String recUpdatedBy, String changeReasonMemo, java.math.BigDecimal currRecInd, java.math.BigDecimal changeVersionId, java.math.BigDecimal visitSk, String verifMthd, String verifNamePhone, String visitVerifStatus, java.math.BigDecimal visitVfydInd, java.math.BigDecimal visitVerifOnholdInd, String verifFailedReason) throws SQLException
  { _init_struct(true);
    setVisitVerifSk(visitVerifSk);
    setVisitVerifId(visitVerifId);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setRecEffTmstp(recEffTmstp);
    setRecTermTmstp(recTermTmstp);
    setRecCreatedBy(recCreatedBy);
    setRecUpdatedBy(recUpdatedBy);
    setChangeReasonMemo(changeReasonMemo);
    setCurrRecInd(currRecInd);
    setChangeVersionId(changeVersionId);
    setVisitSk(visitSk);
    setVerifMthd(verifMthd);
    setVerifNamePhone(verifNamePhone);
    setVisitVerifStatus(visitVerifStatus);
    setVisitVfydInd(visitVfydInd);
    setVisitVerifOnholdInd(visitVerifOnholdInd);
    setVerifFailedReason(verifFailedReason);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(VisitVerifT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new VisitVerifT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getVisitVerifSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setVisitVerifSk(java.math.BigDecimal visitVerifSk) throws SQLException
  { _struct.setAttribute(0, visitVerifSk); }


  public String getVisitVerifId() throws SQLException
  { return (String) _struct.getAttribute(1); }

  public void setVisitVerifId(String visitVerifId) throws SQLException
  { _struct.setAttribute(1, visitVerifId); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(2, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(3); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(3, recUpdateTmstp); }


  public java.sql.Timestamp getRecEffTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(4); }

  public void setRecEffTmstp(java.sql.Timestamp recEffTmstp) throws SQLException
  { _struct.setAttribute(4, recEffTmstp); }


  public java.sql.Timestamp getRecTermTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(5); }

  public void setRecTermTmstp(java.sql.Timestamp recTermTmstp) throws SQLException
  { _struct.setAttribute(5, recTermTmstp); }


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


  public java.math.BigDecimal getCurrRecInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(9); }

  public void setCurrRecInd(java.math.BigDecimal currRecInd) throws SQLException
  { _struct.setAttribute(9, currRecInd); }


  public java.math.BigDecimal getChangeVersionId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(10); }

  public void setChangeVersionId(java.math.BigDecimal changeVersionId) throws SQLException
  { _struct.setAttribute(10, changeVersionId); }


  public java.math.BigDecimal getVisitSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(11); }

  public void setVisitSk(java.math.BigDecimal visitSk) throws SQLException
  { _struct.setAttribute(11, visitSk); }


  public String getVerifMthd() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setVerifMthd(String verifMthd) throws SQLException
  { _struct.setAttribute(12, verifMthd); }


  public String getVerifNamePhone() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setVerifNamePhone(String verifNamePhone) throws SQLException
  { _struct.setAttribute(13, verifNamePhone); }


  public String getVisitVerifStatus() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setVisitVerifStatus(String visitVerifStatus) throws SQLException
  { _struct.setAttribute(14, visitVerifStatus); }


  public java.math.BigDecimal getVisitVfydInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(15); }

  public void setVisitVfydInd(java.math.BigDecimal visitVfydInd) throws SQLException
  { _struct.setAttribute(15, visitVfydInd); }


  public java.math.BigDecimal getVisitVerifOnholdInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(16); }

  public void setVisitVerifOnholdInd(java.math.BigDecimal visitVerifOnholdInd) throws SQLException
  { _struct.setAttribute(16, visitVerifOnholdInd); }


  public String getVerifFailedReason() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setVerifFailedReason(String verifFailedReason) throws SQLException
  { _struct.setAttribute(17, verifFailedReason); }

}
