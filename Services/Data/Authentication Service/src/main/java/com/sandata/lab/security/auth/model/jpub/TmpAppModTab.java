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

public class TmpAppModTab implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.TMP_APP_MOD_TAB";
  public static final int _SQL_TYPECODE = OracleTypes.ARRAY;

  MutableArray _array;

private static final TmpAppModTab _TmpAppModTabFactory = new TmpAppModTab();

  public static ORADataFactory getORADataFactory()
  { return _TmpAppModTabFactory; }
  /* constructors */
  public TmpAppModTab()
  {
    this((TmpAppModTyp[])null);
  }

  public TmpAppModTab(TmpAppModTyp[] a)
  {
    _array = new MutableArray(2002, a, TmpAppModTyp.getORADataFactory());
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
    TmpAppModTab a = new TmpAppModTab();
    a._array = new MutableArray(2002, (ARRAY) d, TmpAppModTyp.getORADataFactory());
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
  public TmpAppModTyp[] getArray() throws SQLException
  {
    return (TmpAppModTyp[]) _array.getObjectArray(
      new TmpAppModTyp[_array.length()]);
  }

  public TmpAppModTyp[] getArray(long index, int count) throws SQLException
  {
    return (TmpAppModTyp[]) _array.getObjectArray(index,
      new TmpAppModTyp[_array.sliceLength(index, count)]);
  }

  public void setArray(TmpAppModTyp[] a) throws SQLException
  {
    _array.setObjectArray(a);
  }

  public void setArray(TmpAppModTyp[] a, long index) throws SQLException
  {
    _array.setObjectArray(a, index);
  }

  public TmpAppModTyp getElement(long index) throws SQLException
  {
    return (TmpAppModTyp) _array.getObjectElement(index);
  }

  public void setElement(TmpAppModTyp a, long index) throws SQLException
  {
    _array.setObjectElement(a, index);
  }

}
