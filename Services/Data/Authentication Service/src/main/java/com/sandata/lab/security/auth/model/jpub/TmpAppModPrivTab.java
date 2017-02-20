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

public class TmpAppModPrivTab implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.TMP_APP_MOD_PRIV_TAB";
  public static final int _SQL_TYPECODE = OracleTypes.ARRAY;

  MutableArray _array;

private static final TmpAppModPrivTab _TmpAppModPrivTabFactory = new TmpAppModPrivTab();

  public static ORADataFactory getORADataFactory()
  { return _TmpAppModPrivTabFactory; }
  /* constructors */
  public TmpAppModPrivTab()
  {
    this((TmpAppModPrivTyp[])null);
  }

  public TmpAppModPrivTab(TmpAppModPrivTyp[] a)
  {
    _array = new MutableArray(2002, a, TmpAppModPrivTyp.getORADataFactory());
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
    TmpAppModPrivTab a = new TmpAppModPrivTab();
    a._array = new MutableArray(2002, (ARRAY) d, TmpAppModPrivTyp.getORADataFactory());
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
  public TmpAppModPrivTyp[] getArray() throws SQLException
  {
    return (TmpAppModPrivTyp[]) _array.getObjectArray(
      new TmpAppModPrivTyp[_array.length()]);
  }

  public TmpAppModPrivTyp[] getArray(long index, int count) throws SQLException
  {
    return (TmpAppModPrivTyp[]) _array.getObjectArray(index,
      new TmpAppModPrivTyp[_array.sliceLength(index, count)]);
  }

  public void setArray(TmpAppModPrivTyp[] a) throws SQLException
  {
    _array.setObjectArray(a);
  }

  public void setArray(TmpAppModPrivTyp[] a, long index) throws SQLException
  {
    _array.setObjectArray(a, index);
  }

  public TmpAppModPrivTyp getElement(long index) throws SQLException
  {
    return (TmpAppModPrivTyp) _array.getObjectElement(index);
  }

  public void setElement(TmpAppModPrivTyp a, long index) throws SQLException
  {
    _array.setObjectElement(a, index);
  }

}
