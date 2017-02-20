package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class PocTaskLstT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "COREDATA.POC_TASK_LST_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,2,12,12,91,91,91,91,91,91,91,91,91,91,91,91,91,91 };
  protected static ORADataFactory[] _factory = new ORADataFactory[21];
  protected static final PocTaskLstT _PocTaskLstTFactory = new PocTaskLstT();

  public static ORADataFactory getORADataFactory()
  { return _PocTaskLstTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[21], _sqlType, _factory); }
  public PocTaskLstT()
  { _init_struct(true); }
  public PocTaskLstT(java.math.BigDecimal pocTaskLstSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal changeVersionId, java.math.BigDecimal pocSk, String beId, String beTaskId, java.sql.Timestamp pocStartTimeDay1, java.sql.Timestamp pocEndTimeDay1, java.sql.Timestamp pocStartTimeDay2, java.sql.Timestamp pocEndTimeDay2, java.sql.Timestamp pocStartTimeDay3, java.sql.Timestamp pocEndTimeDay3, java.sql.Timestamp pocStartTimeDay4, java.sql.Timestamp pocEndTimeDay4, java.sql.Timestamp pocStartTimeDay5, java.sql.Timestamp pocEndTimeDay5, java.sql.Timestamp pocStartTimeDay6, java.sql.Timestamp pocEndTimeDay6, java.sql.Timestamp pocStartTimeDay7, java.sql.Timestamp pocEndTimeDay7) throws SQLException
  { _init_struct(true);
    setPocTaskLstSk(pocTaskLstSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setChangeVersionId(changeVersionId);
    setPocSk(pocSk);
    setBeId(beId);
    setBeTaskId(beTaskId);
    setPocStartTimeDay1(pocStartTimeDay1);
    setPocEndTimeDay1(pocEndTimeDay1);
    setPocStartTimeDay2(pocStartTimeDay2);
    setPocEndTimeDay2(pocEndTimeDay2);
    setPocStartTimeDay3(pocStartTimeDay3);
    setPocEndTimeDay3(pocEndTimeDay3);
    setPocStartTimeDay4(pocStartTimeDay4);
    setPocEndTimeDay4(pocEndTimeDay4);
    setPocStartTimeDay5(pocStartTimeDay5);
    setPocEndTimeDay5(pocEndTimeDay5);
    setPocStartTimeDay6(pocStartTimeDay6);
    setPocEndTimeDay6(pocEndTimeDay6);
    setPocStartTimeDay7(pocStartTimeDay7);
    setPocEndTimeDay7(pocEndTimeDay7);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(PocTaskLstT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new PocTaskLstT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getPocTaskLstSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setPocTaskLstSk(java.math.BigDecimal pocTaskLstSk) throws SQLException
  { _struct.setAttribute(0, pocTaskLstSk); }


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


  public java.math.BigDecimal getPocSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(4); }

  public void setPocSk(java.math.BigDecimal pocSk) throws SQLException
  { _struct.setAttribute(4, pocSk); }


  public String getBeId() throws SQLException
  { return (String) _struct.getAttribute(5); }

  public void setBeId(String beId) throws SQLException
  { _struct.setAttribute(5, beId); }


  public String getBeTaskId() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setBeTaskId(String beTaskId) throws SQLException
  { _struct.setAttribute(6, beTaskId); }


  public java.sql.Timestamp getPocStartTimeDay1() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(7); }

  public void setPocStartTimeDay1(java.sql.Timestamp pocStartTimeDay1) throws SQLException
  { _struct.setAttribute(7, pocStartTimeDay1); }


  public java.sql.Timestamp getPocEndTimeDay1() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(8); }

  public void setPocEndTimeDay1(java.sql.Timestamp pocEndTimeDay1) throws SQLException
  { _struct.setAttribute(8, pocEndTimeDay1); }


  public java.sql.Timestamp getPocStartTimeDay2() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(9); }

  public void setPocStartTimeDay2(java.sql.Timestamp pocStartTimeDay2) throws SQLException
  { _struct.setAttribute(9, pocStartTimeDay2); }


  public java.sql.Timestamp getPocEndTimeDay2() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(10); }

  public void setPocEndTimeDay2(java.sql.Timestamp pocEndTimeDay2) throws SQLException
  { _struct.setAttribute(10, pocEndTimeDay2); }


  public java.sql.Timestamp getPocStartTimeDay3() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(11); }

  public void setPocStartTimeDay3(java.sql.Timestamp pocStartTimeDay3) throws SQLException
  { _struct.setAttribute(11, pocStartTimeDay3); }


  public java.sql.Timestamp getPocEndTimeDay3() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(12); }

  public void setPocEndTimeDay3(java.sql.Timestamp pocEndTimeDay3) throws SQLException
  { _struct.setAttribute(12, pocEndTimeDay3); }


  public java.sql.Timestamp getPocStartTimeDay4() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(13); }

  public void setPocStartTimeDay4(java.sql.Timestamp pocStartTimeDay4) throws SQLException
  { _struct.setAttribute(13, pocStartTimeDay4); }


  public java.sql.Timestamp getPocEndTimeDay4() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(14); }

  public void setPocEndTimeDay4(java.sql.Timestamp pocEndTimeDay4) throws SQLException
  { _struct.setAttribute(14, pocEndTimeDay4); }


  public java.sql.Timestamp getPocStartTimeDay5() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(15); }

  public void setPocStartTimeDay5(java.sql.Timestamp pocStartTimeDay5) throws SQLException
  { _struct.setAttribute(15, pocStartTimeDay5); }


  public java.sql.Timestamp getPocEndTimeDay5() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(16); }

  public void setPocEndTimeDay5(java.sql.Timestamp pocEndTimeDay5) throws SQLException
  { _struct.setAttribute(16, pocEndTimeDay5); }


  public java.sql.Timestamp getPocStartTimeDay6() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(17); }

  public void setPocStartTimeDay6(java.sql.Timestamp pocStartTimeDay6) throws SQLException
  { _struct.setAttribute(17, pocStartTimeDay6); }


  public java.sql.Timestamp getPocEndTimeDay6() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(18); }

  public void setPocEndTimeDay6(java.sql.Timestamp pocEndTimeDay6) throws SQLException
  { _struct.setAttribute(18, pocEndTimeDay6); }


  public java.sql.Timestamp getPocStartTimeDay7() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(19); }

  public void setPocStartTimeDay7(java.sql.Timestamp pocStartTimeDay7) throws SQLException
  { _struct.setAttribute(19, pocStartTimeDay7); }


  public java.sql.Timestamp getPocEndTimeDay7() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(20); }

  public void setPocEndTimeDay7(java.sql.Timestamp pocEndTimeDay7) throws SQLException
  { _struct.setAttribute(20, pocEndTimeDay7); }

}
