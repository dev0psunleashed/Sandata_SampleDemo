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

public class TmpBaseAppFuncTab implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.TMP_BASE_APP_FUNC_TAB";
  public static final int _SQL_TYPECODE = OracleTypes.ARRAY;

  MutableArray _array;

private static final TmpBaseAppFuncTab _TmpBaseAppFuncTabFactory = new TmpBaseAppFuncTab();

  public static ORADataFactory getORADataFactory()
  { return _TmpBaseAppFuncTabFactory; }
  /* constructors */
  public TmpBaseAppFuncTab()
  {
    this((TmpBaseAppFuncTyp[])null);
  }

  public TmpBaseAppFuncTab(TmpBaseAppFuncTyp[] a)
  {
    _array = new MutableArray(2002, a, TmpBaseAppFuncTyp.getORADataFactory());
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
    TmpBaseAppFuncTab a = new TmpBaseAppFuncTab();
    a._array = new MutableArray(2002, (ARRAY) d, TmpBaseAppFuncTyp.getORADataFactory());
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
  public TmpBaseAppFuncTyp[] getArray() throws SQLException
  {
    return (TmpBaseAppFuncTyp[]) _array.getObjectArray(
      new TmpBaseAppFuncTyp[_array.length()]);
  }

  public TmpBaseAppFuncTyp[] getArray(long index, int count) throws SQLException
  {
    return (TmpBaseAppFuncTyp[]) _array.getObjectArray(index,
      new TmpBaseAppFuncTyp[_array.sliceLength(index, count)]);
  }

  public void setArray(TmpBaseAppFuncTyp[] a) throws SQLException
  {
    _array.setObjectArray(a);
  }

  public void setArray(TmpBaseAppFuncTyp[] a, long index) throws SQLException
  {
    _array.setObjectArray(a, index);
  }

  public TmpBaseAppFuncTyp getElement(long index) throws SQLException
  {
    return (TmpBaseAppFuncTyp) _array.getObjectElement(index);
  }

  public void setElement(TmpBaseAppFuncTyp a, long index) throws SQLException
  {
    _array.setObjectElement(a, index);
  }

}
