package com.sandata.lab.security.auth.model.jpub;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;

public class TmpAppSecrGrpTypRef implements ORAData, ORADataFactory
{
  public static final String _SQL_BASETYPE = "METADATA.TMP_APP_SECR_GRP_TYP";
  public static final int _SQL_TYPECODE = OracleTypes.REF;

  REF _ref;

private static final TmpAppSecrGrpTypRef _TmpAppSecrGrpTypRefFactory = new TmpAppSecrGrpTypRef();

  public static ORADataFactory getORADataFactory()
  { return _TmpAppSecrGrpTypRefFactory; }
  /* constructor */
  public TmpAppSecrGrpTypRef()
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
    TmpAppSecrGrpTypRef r = new TmpAppSecrGrpTypRef();
    r._ref = (REF) d;
    return r;
  }

  public static TmpAppSecrGrpTypRef cast(ORAData o) throws SQLException
  {
     if (o == null) return null;
     try { return (TmpAppSecrGrpTypRef) getORADataFactory().create(o.toDatum(null), OracleTypes.REF); }
     catch (Exception exn)
     { throw new SQLException("Unable to convert "+o.getClass().getName()+" to TmpAppSecrGrpTypRef: "+exn.toString()); }
  }

  public TmpAppSecrGrpTyp getValue() throws SQLException
  {
     return (TmpAppSecrGrpTyp) TmpAppSecrGrpTyp.getORADataFactory().create(
       _ref.getSTRUCT(), OracleTypes.REF);
  }

  public void setValue(TmpAppSecrGrpTyp c) throws SQLException
  {
    _ref.setValue((STRUCT) c.toDatum(_ref.getJavaSqlConnection()));
  }
}
