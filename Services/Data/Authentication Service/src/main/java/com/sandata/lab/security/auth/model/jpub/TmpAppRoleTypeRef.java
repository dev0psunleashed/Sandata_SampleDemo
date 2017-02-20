package com.sandata.lab.security.auth.model.jpub;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;

public class TmpAppRoleTypeRef implements ORAData, ORADataFactory
{
  public static final String _SQL_BASETYPE = "METADATA.TMP_APP_ROLE_TYP";
  public static final int _SQL_TYPECODE = OracleTypes.REF;

  REF _ref;

private static final TmpAppRoleTypeRef _TmpAppRoleTypeRefFactory = new TmpAppRoleTypeRef();

  public static ORADataFactory getORADataFactory()
  { return _TmpAppRoleTypeRefFactory; }
  /* constructor */
  public TmpAppRoleTypeRef()
  {
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _ref;
  }

  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    TmpAppRoleTypeRef r = new TmpAppRoleTypeRef();
    r._ref = (REF) d;
    return r;
  }

  public static TmpAppRoleTypeRef cast(ORAData o) throws SQLException
  {
     if (o == null) return null;
     try { return (TmpAppRoleTypeRef) getORADataFactory().create(o.toDatum(null), OracleTypes.REF); }
     catch (Exception exn)
     { throw new SQLException("Unable to convert "+o.getClass().getName()+" to TmpAppRoleTypeRef: "+exn.toString()); }
  }

  public TmpAppRoleTyp getValue() throws SQLException
  {
     return (TmpAppRoleTyp) TmpAppRoleTyp.getORADataFactory().create(
       _ref.getSTRUCT(), OracleTypes.REF);
  }

  public void setValue(TmpAppRoleTyp c) throws SQLException
  {
    _ref.setValue((STRUCT) c.toDatum(_ref.getJavaSqlConnection()));
  }
}
