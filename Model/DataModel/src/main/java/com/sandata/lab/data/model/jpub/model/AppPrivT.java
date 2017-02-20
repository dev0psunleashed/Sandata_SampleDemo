package com.sandata.lab.data.model.jpub.model;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class AppPrivT implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "METADATA.APP_PRIV_T";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,91,91,2,2,2,2,2,2,2,2,2,2,2,2,2,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[17];
  protected static final AppPrivT _AppPrivTFactory = new AppPrivT();

  public static ORADataFactory getORADataFactory()
  { return _AppPrivTFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[17], _sqlType, _factory); }
  public AppPrivT()
  { _init_struct(true); }
  public AppPrivT(java.math.BigDecimal appPrivSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal appSecrEltSk, java.math.BigDecimal appTenantSk, java.math.BigDecimal appUserSk, java.math.BigDecimal appSecrGrpSk, java.math.BigDecimal inclInhcInd, java.math.BigDecimal privAccessInd, java.math.BigDecimal privNoAccessInd, java.math.BigDecimal privCreateInd, java.math.BigDecimal privNoCreateInd, java.math.BigDecimal privUpdateInd, java.math.BigDecimal privNoUpdateInd, java.math.BigDecimal privDeleteInd, java.math.BigDecimal privNoDeleteInd, java.math.BigDecimal exclInd) throws SQLException
  { _init_struct(true);
    setAppPrivSk(appPrivSk);
    setRecCreateTmstp(recCreateTmstp);
    setRecUpdateTmstp(recUpdateTmstp);
    setAppSecrEltSk(appSecrEltSk);
    setAppTenantSk(appTenantSk);
    setAppUserSk(appUserSk);
    setAppSecrGrpSk(appSecrGrpSk);
    setInclInhcInd(inclInhcInd);
    setPrivAccessInd(privAccessInd);
    setPrivNoAccessInd(privNoAccessInd);
    setPrivCreateInd(privCreateInd);
    setPrivNoCreateInd(privNoCreateInd);
    setPrivUpdateInd(privUpdateInd);
    setPrivNoUpdateInd(privNoUpdateInd);
    setPrivDeleteInd(privDeleteInd);
    setPrivNoDeleteInd(privNoDeleteInd);
    setExclInd(exclInd);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(AppPrivT o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new AppPrivT();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getAppPrivSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setAppPrivSk(java.math.BigDecimal appPrivSk) throws SQLException
  { _struct.setAttribute(0, appPrivSk); }


  public java.sql.Timestamp getRecCreateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(1); }

  public void setRecCreateTmstp(java.sql.Timestamp recCreateTmstp) throws SQLException
  { _struct.setAttribute(1, recCreateTmstp); }


  public java.sql.Timestamp getRecUpdateTmstp() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(2); }

  public void setRecUpdateTmstp(java.sql.Timestamp recUpdateTmstp) throws SQLException
  { _struct.setAttribute(2, recUpdateTmstp); }


  public java.math.BigDecimal getAppSecrEltSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setAppSecrEltSk(java.math.BigDecimal appSecrEltSk) throws SQLException
  { _struct.setAttribute(3, appSecrEltSk); }


  public java.math.BigDecimal getAppTenantSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(4); }

  public void setAppTenantSk(java.math.BigDecimal appTenantSk) throws SQLException
  { _struct.setAttribute(4, appTenantSk); }


  public java.math.BigDecimal getAppUserSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(5); }

  public void setAppUserSk(java.math.BigDecimal appUserSk) throws SQLException
  { _struct.setAttribute(5, appUserSk); }


  public java.math.BigDecimal getAppSecrGrpSk() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(6); }

  public void setAppSecrGrpSk(java.math.BigDecimal appSecrGrpSk) throws SQLException
  { _struct.setAttribute(6, appSecrGrpSk); }


  public java.math.BigDecimal getInclInhcInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(7); }

  public void setInclInhcInd(java.math.BigDecimal inclInhcInd) throws SQLException
  { _struct.setAttribute(7, inclInhcInd); }


  public java.math.BigDecimal getPrivAccessInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(8); }

  public void setPrivAccessInd(java.math.BigDecimal privAccessInd) throws SQLException
  { _struct.setAttribute(8, privAccessInd); }


  public java.math.BigDecimal getPrivNoAccessInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(9); }

  public void setPrivNoAccessInd(java.math.BigDecimal privNoAccessInd) throws SQLException
  { _struct.setAttribute(9, privNoAccessInd); }


  public java.math.BigDecimal getPrivCreateInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(10); }

  public void setPrivCreateInd(java.math.BigDecimal privCreateInd) throws SQLException
  { _struct.setAttribute(10, privCreateInd); }


  public java.math.BigDecimal getPrivNoCreateInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(11); }

  public void setPrivNoCreateInd(java.math.BigDecimal privNoCreateInd) throws SQLException
  { _struct.setAttribute(11, privNoCreateInd); }


  public java.math.BigDecimal getPrivUpdateInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(12); }

  public void setPrivUpdateInd(java.math.BigDecimal privUpdateInd) throws SQLException
  { _struct.setAttribute(12, privUpdateInd); }


  public java.math.BigDecimal getPrivNoUpdateInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(13); }

  public void setPrivNoUpdateInd(java.math.BigDecimal privNoUpdateInd) throws SQLException
  { _struct.setAttribute(13, privNoUpdateInd); }


  public java.math.BigDecimal getPrivDeleteInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(14); }

  public void setPrivDeleteInd(java.math.BigDecimal privDeleteInd) throws SQLException
  { _struct.setAttribute(14, privDeleteInd); }


  public java.math.BigDecimal getPrivNoDeleteInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(15); }

  public void setPrivNoDeleteInd(java.math.BigDecimal privNoDeleteInd) throws SQLException
  { _struct.setAttribute(15, privNoDeleteInd); }


  public java.math.BigDecimal getExclInd() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(16); }

  public void setExclInd(java.math.BigDecimal exclInd) throws SQLException
  { _struct.setAttribute(16, exclInd); }

}
