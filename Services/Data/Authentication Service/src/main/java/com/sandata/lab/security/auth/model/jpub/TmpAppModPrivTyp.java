package com.sandata.lab.security.auth.model.jpub;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class TmpAppModPrivTyp implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.TMP_APP_MOD_PRIV_TYP";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,2,12,2,2,2003 };
  protected static ORADataFactory[] _factory = new ORADataFactory[6];
  static
  {
    _factory[5] = TmpAppModTab.getORADataFactory();
  }

  protected static final TmpAppModPrivTyp _TmpAppModPrivTypFactory = new TmpAppModPrivTyp();

  public static ORADataFactory getORADataFactory()
  { return _TmpAppModPrivTypFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[6], _sqlType, _factory); }
  public TmpAppModPrivTyp()
  { _init_struct(true); }
  public TmpAppModPrivTyp(java.math.BigDecimal appTenantSk, java.math.BigDecimal appModSk, String modName, java.math.BigDecimal viewInd, java.math.BigDecimal editInd, TmpAppModTab subAppModList) throws SQLException
  { _init_struct(true);
    setAppTenantSk(appTenantSk);
    setAppModSk(appModSk);
    setModName(modName);
    setViewInd(viewInd);
    setEditInd(editInd);
    setSubAppModList(subAppModList);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(TmpAppModPrivTyp o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new TmpAppModPrivTyp();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAppTenantSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAppTenantSk(java.math.BigDecimal appTenantSk) throws SQLException
  { _struct.setAttribute(0, appTenantSk); }


  public java.math.BigDecimal getAppModSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(1); }

  public void setAppModSk(java.math.BigDecimal appModSk) throws SQLException
  { _struct.setAttribute(1, appModSk); }


  public String getModName() throws SQLException
  { return (String) _struct.getAttribute(2); }

  public void setModName(String modName) throws SQLException
  { _struct.setAttribute(2, modName); }


  public java.math.BigDecimal getViewInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setViewInd(java.math.BigDecimal viewInd) throws SQLException
  { _struct.setAttribute(3, viewInd); }


  public java.math.BigDecimal getEditInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(4); }

  public void setEditInd(java.math.BigDecimal editInd) throws SQLException
  { _struct.setAttribute(4, editInd); }


  public TmpAppModTab getSubAppModList() throws SQLException
  { return (TmpAppModTab) _struct.getAttribute(5); }

  public void setSubAppModList(TmpAppModTab subAppModList) throws SQLException
  { _struct.setAttribute(5, subAppModList); }

}
