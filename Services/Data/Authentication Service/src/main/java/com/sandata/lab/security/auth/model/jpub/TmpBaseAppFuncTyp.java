package com.sandata.lab.security.auth.model.jpub;/*@lineinfo:filename=TmpBaseAppFuncTyp*//*@lineinfo:user-code*//*@lineinfo:1^1*/import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;


public class TmpBaseAppFuncTyp implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.TMP_BASE_APP_FUNC_TYP";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;



  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,2,2,2,2,91,91,2002 };
  protected static ORADataFactory[] _factory = new ORADataFactory[8];
  static
  {
    _factory[7] = TmpBaseAppPrivTyp.getORADataFactory();
  }
  protected static final TmpBaseAppFuncTyp _TmpBaseAppFuncTypFactory = new TmpBaseAppFuncTyp();

  public static ORADataFactory getORADataFactory()
  { return _TmpBaseAppFuncTypFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[8], _sqlType, _factory); }
  public TmpBaseAppFuncTyp()
  { _init_struct(true); }

  public TmpBaseAppFuncTyp(java.math.BigDecimal baseAppFuncSk, java.math.BigDecimal appSecrEltSk, java.math.BigDecimal appTenantSk, java.math.BigDecimal appRoleSk, java.math.BigDecimal baseAppPrivSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, TmpBaseAppPrivTyp baseAppPriv) throws SQLException
  {
    _init_struct(true);
    setBaseAppFuncSk(baseAppFuncSk);
    setAppSecrEltSk(appSecrEltSk);
    setAppTenantSk(appTenantSk);
    setAppRoleSk(appRoleSk);
    setBaseAppPrivSk(baseAppPrivSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setBaseAppPriv(baseAppPriv);
  }

  public TmpBaseAppFuncTyp(java.math.BigDecimal baseAppFuncSk, java.math.BigDecimal appSecrEltSk, java.math.BigDecimal appTenantSk, java.math.BigDecimal appRoleSk, java.math.BigDecimal baseAppPrivSk) throws SQLException
  {
    _init_struct(true);
    setBaseAppFuncSk(baseAppFuncSk);
    setAppSecrEltSk(appSecrEltSk);
    setAppTenantSk(appTenantSk);
    setAppRoleSk(appRoleSk);
    setBaseAppPrivSk(baseAppPrivSk);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }

  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }

  protected ORAData create(TmpBaseAppFuncTyp o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null;
    if (o == null) o = new TmpBaseAppFuncTyp();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }


  /* accessor methods */
  public java.math.BigDecimal getBaseAppFuncSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setBaseAppFuncSk(java.math.BigDecimal baseAppFuncSk) throws SQLException
  { _struct.setAttribute(0, baseAppFuncSk); }


  public java.math.BigDecimal getAppSecrEltSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(1); }

  public void setAppSecrEltSk(java.math.BigDecimal appSecrEltSk) throws SQLException
  { _struct.setAttribute(1, appSecrEltSk); }


  public java.math.BigDecimal getAppTenantSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(2); }

  public void setAppTenantSk(java.math.BigDecimal appTenantSk) throws SQLException
  { _struct.setAttribute(2, appTenantSk); }


  public java.math.BigDecimal getAppRoleSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setAppRoleSk(java.math.BigDecimal appRoleSk) throws SQLException
  { _struct.setAttribute(3, appRoleSk); }


  public java.math.BigDecimal getBaseAppPrivSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(4); }

  public void setBaseAppPrivSk(java.math.BigDecimal baseAppPrivSk) throws SQLException
  { _struct.setAttribute(4, baseAppPrivSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(5); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(5, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(6); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(6, recUpdateTmstp); }


  public TmpBaseAppPrivTyp getBaseAppPriv() throws SQLException
  { return (TmpBaseAppPrivTyp) _struct.getAttribute(7); }

  public void setBaseAppPriv(TmpBaseAppPrivTyp baseAppPriv) throws SQLException
  { _struct.setAttribute(7, baseAppPriv); }

}/*@lineinfo:generated-code*/