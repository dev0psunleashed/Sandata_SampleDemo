package com.sandata.lab.security.auth.model.jpub;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;

public class TmpAppRoleTypRef implements ORAData, ORADataFactory
{
  public static final String _SQL_BASETYPE = "METADATA.TMP_APP_ROLE_TYP";
  public static final int _SQL_TYPECODE = OracleTypes.REF;

  REF _ref;

private static final TmpAppRoleTypRef _TmpAppRoleTypRefFactory = new TmpAppRoleTypRef();

  public static ORADataFactory getORADataFactory()
  { return _TmpAppRoleTypRefFactory; }
  /* constructor */
  public TmpAppRoleTypRef()
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
    TmpAppRoleTypRef r = new TmpAppRoleTypRef();
    r._ref = (REF) d;
    return r;
  }

  public static TmpAppRoleTypRef cast(ORAData o) throws SQLException
  {
     if (o == null) return null;
     try { return (TmpAppRoleTypRef) getORADataFactory().create(o.toDatum(null), OracleTypes.REF); }
     catch (Exception exn)
     { throw new SQLException("Unable to convert "+o.getClass().getName()+" to TmpAppRoleTypRef: "+exn.toString()); }
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
