package com.sandata.lab.security.auth.model.jpub;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;

public class TmpAppModPrivTypRef implements ORAData, ORADataFactory
{
  public static final String _SQL_BASETYPE = "METADATA.TMP_APP_MOD_PRIV_TYP";
  public static final int _SQL_TYPECODE = OracleTypes.REF;

  REF _ref;

private static final TmpAppModPrivTypRef _TmpAppModPrivTypRefFactory = new TmpAppModPrivTypRef();

  public static ORADataFactory getORADataFactory()
  { return _TmpAppModPrivTypRefFactory; }
  /* constructor */
  public TmpAppModPrivTypRef()
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
    TmpAppModPrivTypRef r = new TmpAppModPrivTypRef();
    r._ref = (REF) d;
    return r;
  }

  public static TmpAppModPrivTypRef cast(ORAData o) throws SQLException
  {
     if (o == null) return null;
     try { return (TmpAppModPrivTypRef) getORADataFactory().create(o.toDatum(null), OracleTypes.REF); }
     catch (Exception exn)
     { throw new SQLException("Unable to convert "+o.getClass().getName()+" to TmpAppModPrivTypRef: "+exn.toString()); }
  }

  public TmpAppModPrivTyp getValue() throws SQLException
  {
     return (TmpAppModPrivTyp) TmpAppModPrivTyp.getORADataFactory().create(
       _ref.getSTRUCT(), OracleTypes.REF);
  }

  public void setValue(TmpAppModPrivTyp c) throws SQLException
  {
    _ref.setValue((STRUCT) c.toDatum(_ref.getJavaSqlConnection()));
  }
}
