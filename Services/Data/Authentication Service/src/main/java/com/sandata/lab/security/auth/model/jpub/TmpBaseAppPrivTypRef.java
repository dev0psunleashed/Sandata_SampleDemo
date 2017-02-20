package com.sandata.lab.security.auth.model.jpub;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;

public class TmpBaseAppPrivTypRef implements ORAData, ORADataFactory
{
  public static final String _SQL_BASETYPE = "METADATA.TMP_BASE_APP_PRIV_TYP";
  public static final int _SQL_TYPECODE = OracleTypes.REF;

  REF _ref;

private static final TmpBaseAppPrivTypRef _TmpBaseAppPrivTypRefFactory = new TmpBaseAppPrivTypRef();

  public static ORADataFactory getORADataFactory()
  { return _TmpBaseAppPrivTypRefFactory; }
  /* constructor */
  public TmpBaseAppPrivTypRef()
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
    TmpBaseAppPrivTypRef r = new TmpBaseAppPrivTypRef();
    r._ref = (REF) d;
    return r;
  }

  public static TmpBaseAppPrivTypRef cast(ORAData o) throws SQLException
  {
     if (o == null) return null;
     try { return (TmpBaseAppPrivTypRef) getORADataFactory().create(o.toDatum(null), OracleTypes.REF); }
     catch (Exception exn)
     { throw new SQLException("Unable to convert "+o.getClass().getName()+" to TmpBaseAppPrivTypRef: "+exn.toString()); }
  }

  public TmpBaseAppPrivTyp getValue() throws SQLException
  {
     return (TmpBaseAppPrivTyp) TmpBaseAppPrivTyp.getORADataFactory().create(
       _ref.getSTRUCT(), OracleTypes.REF);
  }

  public void setValue(TmpBaseAppPrivTyp c) throws SQLException
  {
    _ref.setValue((STRUCT) c.toDatum(_ref.getJavaSqlConnection()));
  }
}
