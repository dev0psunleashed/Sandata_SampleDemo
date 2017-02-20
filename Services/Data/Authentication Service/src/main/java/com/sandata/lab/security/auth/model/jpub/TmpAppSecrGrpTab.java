package com.sandata.lab.security.auth.model.jpub;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.jpub.runtime.MutableArray;

public class TmpAppSecrGrpTab implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.TMP_APP_SECR_GRP_TAB";
  public static final int _SQL_TYPECODE = OracleTypes.ARRAY;

  MutableArray _array;

private static final TmpAppSecrGrpTab _TmpAppSecrGrpTabFactory = new TmpAppSecrGrpTab();

  public static ORADataFactory getORADataFactory()
  { return _TmpAppSecrGrpTabFactory; }
  /* constructors */
  public TmpAppSecrGrpTab()
  {
    this((TmpAppSecrGrpTyp[])null);
  }

  public TmpAppSecrGrpTab(TmpAppSecrGrpTyp[] a)
  {
    _array = new MutableArray(2002, a, TmpAppSecrGrpTyp.getORADataFactory());
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _array.toDatum(c, _SQL_NAME);
  }

  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    TmpAppSecrGrpTab a = new TmpAppSecrGrpTab();
    a._array = new MutableArray(2002, (ARRAY) d, TmpAppSecrGrpTyp.getORADataFactory());
    return a;
  }

  public int length() throws SQLException
  {
    return _array.length();
  }

  public int getBaseType() throws SQLException
  {
    return _array.getBaseType();
  }

  public String getBaseTypeName() throws SQLException
  {
    return _array.getBaseTypeName();
  }

  public ArrayDescriptor getDescriptor() throws SQLException
  {
    return _array.getDescriptor();
  }

  /* array accessor methods */
  public TmpAppSecrGrpTyp[] getArray() throws SQLException
  {
    return (TmpAppSecrGrpTyp[]) _array.getObjectArray(
      new TmpAppSecrGrpTyp[_array.length()]);
  }

  public TmpAppSecrGrpTyp[] getArray(long index, int count) throws SQLException
  {
    return (TmpAppSecrGrpTyp[]) _array.getObjectArray(index,
      new TmpAppSecrGrpTyp[_array.sliceLength(index, count)]);
  }

  public void setArray(TmpAppSecrGrpTyp[] a) throws SQLException
  {
    _array.setObjectArray(a);
  }

  public void setArray(TmpAppSecrGrpTyp[] a, long index) throws SQLException
  {
    _array.setObjectArray(a, index);
  }

  public TmpAppSecrGrpTyp getElement(long index) throws SQLException
  {
    return (TmpAppSecrGrpTyp) _array.getObjectElement(index);
  }

  public void setElement(TmpAppSecrGrpTyp a, long index) throws SQLException
  {
    _array.setObjectElement(a, index);
  }

}
