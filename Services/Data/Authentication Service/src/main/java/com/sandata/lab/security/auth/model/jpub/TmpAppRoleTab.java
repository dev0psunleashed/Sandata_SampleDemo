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

public class TmpAppRoleTab implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.TMP_APP_ROLE_TAB";
  public static final int _SQL_TYPECODE = OracleTypes.ARRAY;

  MutableArray _array;

private static final TmpAppRoleTab _TmpAppRoleTabFactory = new TmpAppRoleTab();

  public static ORADataFactory getORADataFactory()
  { return _TmpAppRoleTabFactory; }
  /* constructors */
  public TmpAppRoleTab()
  {
    this((TmpAppRoleTyp[])null);
  }

  public TmpAppRoleTab(TmpAppRoleTyp[] a)
  {
    _array = new MutableArray(2002, a, TmpAppRoleTyp.getORADataFactory());
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
    TmpAppRoleTab a = new TmpAppRoleTab();
    a._array = new MutableArray(2002, (ARRAY) d, TmpAppRoleTyp.getORADataFactory());
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
  public TmpAppRoleTyp[] getArray() throws SQLException
  {
    return (TmpAppRoleTyp[]) _array.getObjectArray(
      new TmpAppRoleTyp[_array.length()]);
  }

  public TmpAppRoleTyp[] getArray(long index, int count) throws SQLException
  {
    return (TmpAppRoleTyp[]) _array.getObjectArray(index,
      new TmpAppRoleTyp[_array.sliceLength(index, count)]);
  }

  public void setArray(TmpAppRoleTyp[] a) throws SQLException
  {
    _array.setObjectArray(a);
  }

  public void setArray(TmpAppRoleTyp[] a, long index) throws SQLException
  {
    _array.setObjectArray(a, index);
  }

  public TmpAppRoleTyp getElement(long index) throws SQLException
  {
    return (TmpAppRoleTyp) _array.getObjectElement(index);
  }

  public void setElement(TmpAppRoleTyp a, long index) throws SQLException
  {
    _array.setObjectElement(a, index);
  }

}
