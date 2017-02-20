package com.sandata.lab.security.auth.model.jpub;/*@lineinfo:filename=TmpBaseAppPrivTyp*//*@lineinfo:user-code*//*@lineinfo:1^1*/import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.*;
import oracle.jpub.runtime.MutableStruct;

public class TmpBaseAppPrivTyp implements ORAData, ORADataFactory {
  public static final String _SQL_NAME = "METADATA.TMP_BASE_APP_PRIV_TYP";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;


  protected MutableStruct _struct;

  protected static int[] _sqlType = {2, 91, 91, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
  protected static ORADataFactory[] _factory = new ORADataFactory[13];
  protected static final TmpBaseAppPrivTyp _TmpBaseAppPrivTypFactory = new TmpBaseAppPrivTyp();

  public static ORADataFactory getORADataFactory() {
    return _TmpBaseAppPrivTypFactory;
  }

  /* constructors */
  protected void _init_struct(boolean init) {
    if (init) _struct = new MutableStruct(new Object[13], _sqlType, _factory);
  }

  public TmpBaseAppPrivTyp() {
    _init_struct(true);
  }

  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }

  protected ORAData create(TmpBaseAppPrivTyp o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null;
    if (o == null) o = new TmpBaseAppPrivTyp();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }

  public TmpBaseAppPrivTyp(java.math.BigDecimal baseAppPrivSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal includeInheritanceInd, java.math.BigDecimal privAccessInd, java.math.BigDecimal privNoAccessInd, java.math.BigDecimal privCreateInd, java.math.BigDecimal privNoCreateInd, java.math.BigDecimal privUpdateInd, java.math.BigDecimal privNoUpdateInd, java.math.BigDecimal privDeleteInd, java.math.BigDecimal privNoDeleteInd, java.math.BigDecimal exclusionInd) throws SQLException {
    _init_struct(true);
    setBaseAppPrivSk(baseAppPrivSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setIncludeInheritanceInd(includeInheritanceInd);
    setPrivAccessInd(privAccessInd);
    setPrivNoAccessInd(privNoAccessInd);
    setPrivCreateInd(privCreateInd);
    setPrivNoCreateInd(privNoCreateInd);
    setPrivUpdateInd(privUpdateInd);
    setPrivNoUpdateInd(privNoUpdateInd);
    setPrivDeleteInd(privDeleteInd);
    setPrivNoDeleteInd(privNoDeleteInd);
    setExclusionInd(exclusionInd);
  }

  public TmpBaseAppPrivTyp(java.math.BigDecimal baseAppPrivSk, java.math.BigDecimal includeInheritanceInd, java.math.BigDecimal privAccessInd, java.math.BigDecimal privNoAccessInd, java.math.BigDecimal privCreateInd, java.math.BigDecimal privNoCreateInd, java.math.BigDecimal privUpdateInd, java.math.BigDecimal privNoUpdateInd, java.math.BigDecimal privDeleteInd, java.math.BigDecimal privNoDeleteInd, java.math.BigDecimal exclusionInd) throws SQLException {
    _init_struct(true);
    setBaseAppPrivSk(baseAppPrivSk);
    setIncludeInheritanceInd(includeInheritanceInd);
    setPrivAccessInd(privAccessInd);
    setPrivNoAccessInd(privNoAccessInd);
    setPrivCreateInd(privCreateInd);
    setPrivNoCreateInd(privNoCreateInd);
    setPrivUpdateInd(privUpdateInd);
    setPrivNoUpdateInd(privNoUpdateInd);
    setPrivDeleteInd(privDeleteInd);
    setPrivNoDeleteInd(privNoDeleteInd);
    setExclusionInd(exclusionInd);
  }


  /* accessor methods */
  public java.math.BigDecimal getBaseAppPrivSk() throws SQLException {
    return (java.math.BigDecimal) _struct.getAttribute(0);
  }

  public void setBaseAppPrivSk(java.math.BigDecimal baseAppPrivSk) throws SQLException {
    _struct.setAttribute(0, baseAppPrivSk);
  }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException {
    return (java.sql.Timestamp) _struct.getAttribute(1);
  }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException {
    _struct.setAttribute(1, recCreateTmstp);
  }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException {
    return (java.sql.Timestamp) _struct.getAttribute(2);
  }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException {
    _struct.setAttribute(2, recUpdateTmstp);
  }


  public java.math.BigDecimal getIncludeInheritanceInd() throws SQLException {
    return (java.math.BigDecimal) _struct.getAttribute(3);
  }

  public void setIncludeInheritanceInd(java.math.BigDecimal includeInheritanceInd) throws SQLException {
    _struct.setAttribute(3, includeInheritanceInd);
  }


  public java.math.BigDecimal getPrivAccessInd() throws SQLException {
    return (java.math.BigDecimal) _struct.getAttribute(4);
  }

  public void setPrivAccessInd(java.math.BigDecimal privAccessInd) throws SQLException {
    _struct.setAttribute(4, privAccessInd);
  }


  public java.math.BigDecimal getPrivNoAccessInd() throws SQLException {
    return (java.math.BigDecimal) _struct.getAttribute(5);
  }

  public void setPrivNoAccessInd(java.math.BigDecimal privNoAccessInd) throws SQLException {
    _struct.setAttribute(5, privNoAccessInd);
  }


  public java.math.BigDecimal getPrivCreateInd() throws SQLException {
    return (java.math.BigDecimal) _struct.getAttribute(6);
  }

  public void setPrivCreateInd(java.math.BigDecimal privCreateInd) throws SQLException {
    _struct.setAttribute(6, privCreateInd);
  }


  public java.math.BigDecimal getPrivNoCreateInd() throws SQLException {
    return (java.math.BigDecimal) _struct.getAttribute(7);
  }

  public void setPrivNoCreateInd(java.math.BigDecimal privNoCreateInd) throws SQLException {
    _struct.setAttribute(7, privNoCreateInd);
  }


  public java.math.BigDecimal getPrivUpdateInd() throws SQLException {
    return (java.math.BigDecimal) _struct.getAttribute(8);
  }

  public void setPrivUpdateInd(java.math.BigDecimal privUpdateInd) throws SQLException {
    _struct.setAttribute(8, privUpdateInd);
  }


  public java.math.BigDecimal getPrivNoUpdateInd() throws SQLException {
    return (java.math.BigDecimal) _struct.getAttribute(9);
  }

  public void setPrivNoUpdateInd(java.math.BigDecimal privNoUpdateInd) throws SQLException {
    _struct.setAttribute(9, privNoUpdateInd);
  }


  public java.math.BigDecimal getPrivDeleteInd() throws SQLException {
    return (java.math.BigDecimal) _struct.getAttribute(10);
  }

  public void setPrivDeleteInd(java.math.BigDecimal privDeleteInd) throws SQLException {
    _struct.setAttribute(10, privDeleteInd);
  }


  public java.math.BigDecimal getPrivNoDeleteInd() throws SQLException {
    return (java.math.BigDecimal) _struct.getAttribute(11);
  }

  public void setPrivNoDeleteInd(java.math.BigDecimal privNoDeleteInd) throws SQLException {
    _struct.setAttribute(11, privNoDeleteInd);
  }


  public java.math.BigDecimal getExclusionInd() throws SQLException {
    return (java.math.BigDecimal) _struct.getAttribute(12);
  }

  public void setExclusionInd(java.math.BigDecimal exclusionInd) throws SQLException {
    _struct.setAttribute(12, exclusionInd);
  }



}/*@lineinfo:generated-code*/