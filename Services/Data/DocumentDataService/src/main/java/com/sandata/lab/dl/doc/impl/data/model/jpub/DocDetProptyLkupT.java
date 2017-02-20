package com.sandata.lab.dl.doc.impl.data.model.jpub;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class DocDetProptyLkupT implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "LOBDATA.DOC_DET_PROPTY_LKUP_T";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    protected MutableStruct _struct;

    protected static int[] _sqlType = {2, 91, 91, 12, 12};
    protected static ORADataFactory[] _factory = new ORADataFactory[5];
    protected static final DocDetProptyLkupT _DocDetProptyLkupTFactory = new DocDetProptyLkupT();

    public static ORADataFactory getORADataFactory() {
        return _DocDetProptyLkupTFactory;
    }

    /* constructors */
    protected void _init_struct(boolean init) {
        if (init) _struct = new MutableStruct(new Object[5], _sqlType, _factory);
    }

    public DocDetProptyLkupT() {
        _init_struct(true);
    }

    public DocDetProptyLkupT(java.math.BigDecimal docDetProptyLkupSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String docDetProptyKeyName, String docDetProptyKeyDesc) throws SQLException {
        _init_struct(true);
        setDocDetProptyLkupSk(docDetProptyLkupSk);
        setRecCreateTmstp(recCreateTmstp);
        setRecUpdateTmstp(recUpdateTmstp);
        setDocDetProptyKeyName(docDetProptyKeyName);
        setDocDetProptyKeyDesc(docDetProptyKeyDesc);
    }

    /* ORAData interface */
    public Datum toDatum(Connection c) throws SQLException {
        return _struct.toDatum(c, _SQL_NAME);
    }


    /* ORADataFactory interface */
    public ORAData create(Datum d, int sqlType) throws SQLException {
        return create(null, d, sqlType);
    }

    protected ORAData create(DocDetProptyLkupT o, Datum d, int sqlType) throws SQLException {
        if (d == null) return null;
        if (o == null) o = new DocDetProptyLkupT();
        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        return o;
    }

    /* accessor methods */
    public java.math.BigDecimal getDocDetProptyLkupSk() throws SQLException {
        return (java.math.BigDecimal) _struct.getAttribute(0);
    }

    public void setDocDetProptyLkupSk(java.math.BigDecimal docDetProptyLkupSk) throws SQLException {
        _struct.setAttribute(0, docDetProptyLkupSk);
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


    public String getDocDetProptyKeyName() throws SQLException {
        return (String) _struct.getAttribute(3);
    }

    public void setDocDetProptyKeyName(String docDetProptyKeyName) throws SQLException {
        _struct.setAttribute(3, docDetProptyKeyName);
    }


    public String getDocDetProptyKeyDesc() throws SQLException {
        return (String) _struct.getAttribute(4);
    }

    public void setDocDetProptyKeyDesc(String docDetProptyKeyDesc) throws SQLException {
        _struct.setAttribute(4, docDetProptyKeyDesc);
    }

}
