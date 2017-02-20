package com.sandata.lab.security.auth.model.jpub;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;

public class TmpBaseAppFuncTypRef implements ORAData, ORADataFactory
{
  public static final String _SQL_BASETYPE = "METADATA.TMP_BASE_APP_FUNC_TYP";
  public static final int _SQL_TYPECODE = OracleTypes.REF;

  REF _ref;

private static final TmpBaseAppFuncTypRef _TmpBaseAppFuncTypRefFactory = new TmpBaseAppFuncTypRef();

  public static ORADataFactory getORADataFactory()
  { return _TmpBaseAppFuncTypRefFactory; }
  /* constructor */
  public TmpBaseAppFuncTypRef()
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
    TmpBaseAppFuncTypRef r = new TmpBaseAppFuncTypRef();
    r._ref = (REF) d;
    return r;
  }

  public static TmpBaseAppFuncTypRef cast(ORAData o) throws SQLException
  {
     if (o == null) return null;
     try { return (TmpBaseAppFuncTypRef) getORADataFactory().create(o.toDatum(null), OracleTypes.REF); }
     catch (Exception exn)
     { throw new SQLException("Unable to convert "+o.getClass().getName()+" to TmpBaseAppFuncTypRef: "+exn.toString()); }
  }

  public TmpBaseAppFuncTyp getValue() throws SQLException
  {
     return (TmpBaseAppFuncTyp) TmpBaseAppFuncTyp.getORADataFactory().create(
       _ref.getSTRUCT(), OracleTypes.REF);
  }

  public void setValue(TmpBaseAppFuncTyp c) throws SQLException
  {
    _ref.setValue((STRUCT) c.toDatum(_ref.getJavaSqlConnection()));
  }
}
