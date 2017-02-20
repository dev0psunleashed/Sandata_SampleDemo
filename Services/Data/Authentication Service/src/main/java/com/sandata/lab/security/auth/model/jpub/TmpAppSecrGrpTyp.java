package com.sandata.lab.security.auth.model.jpub;/*@lineinfo:filename=TmpAppSecrGrpTyp*//*@lineinfo:user-code*//*@lineinfo:1^1*/import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;


public class TmpAppSecrGrpTyp implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.TMP_APP_SECR_GRP_TYP";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  /* connection management */
  protected Connection __onn = null;
  protected javax.sql.DataSource __dataSource = null;


  public java.util.Map getTypeMap() 
  {
    return m_typeMap;
  }
  private static java.util.Map m_typeMap = null;


  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[4];
  protected static final TmpAppSecrGrpTyp _TmpAppSecrGrpTypFactory = new TmpAppSecrGrpTyp();

  public static ORADataFactory getORADataFactory()
  { return _TmpAppSecrGrpTypFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[4], _sqlType, _factory); }
  public TmpAppSecrGrpTyp()
  { _init_struct(true); }

  public TmpAppSecrGrpTyp(Connection c) /*throws SQLException*/
  { _init_struct(true); __onn = c; }
  public TmpAppSecrGrpTyp(java.math.BigDecimal appSecrGrpSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String secrGrpName) throws SQLException
  {
    _init_struct(true);
    setAppSecrGrpSk(appSecrGrpSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setSecrGrpName(secrGrpName);
  }

  public TmpAppSecrGrpTyp(String secrGrpName) throws SQLException
  {
    _init_struct(true);
    setSecrGrpName(secrGrpName);
  }

  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }

  protected ORAData create(TmpAppSecrGrpTyp o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null;
    if (o == null) o = new TmpAppSecrGrpTyp();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  protected void setValueFrom(TmpAppSecrGrpTyp o) { _struct = o._struct; }

  /* accessor methods */
  public java.math.BigDecimal getAppSecrGrpSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAppSecrGrpSk(java.math.BigDecimal appSecrGrpSk) throws SQLException
  { _struct.setAttribute(0, appSecrGrpSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public String getSecrGrpName() throws SQLException
  { return (String) _struct.getAttribute(3); }

  public void setSecrGrpName(String secrGrpName) throws SQLException
  { _struct.setAttribute(3, secrGrpName); }


}/*@lineinfo:generated-code*/