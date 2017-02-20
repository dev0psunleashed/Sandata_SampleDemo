package com.sandata.one.aggregator.documents.impl.data.model.jpub;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class DocDetT implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "LOBDATA.DOC_DET_T";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    protected MutableStruct _struct;

    protected static int[] _sqlType = {2, 91, 91, 2, 12, 12};
    protected static ORADataFactory[] _factory = new ORADataFactory[6];
    protected static final DocDetT _DocDetTFactory = new DocDetT();

    public static ORADataFactory getORADataFactory() {
        return _DocDetTFactory;
    }

    /* constructors */
    protected void _init_struct(boolean init) {
        if (init) _struct = new MutableStruct(new Object[6], _sqlType, _factory);
    }

    public DocDetT() {
        _init_struct(true);
    }

    public DocDetT(java.math.BigDecimal docDetSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, java.math.BigDecimal docSk, String docDetProptyKeyName, String docDetProptyKeyVal) throws SQLException {
        _init_struct(true);
        setDocDetSk(docDetSk);
        setRecCreateTmstp(recCreateTmstp);
        setRecUpdateTmstp(recUpdateTmstp);
        setDocSk(docSk);
        setDocDetProptyKeyName(docDetProptyKeyName);
        setDocDetProptyKeyVal(docDetProptyKeyVal);
    }

    /* ORAData interface */
    public Datum toDatum(Connection c) throws SQLException {
        return _struct.toDatum(c, _SQL_NAME);
    }


    /* ORADataFactory interface */
    public ORAData create(Datum d, int sqlType) throws SQLException {
        return create(null, d, sqlType);
    }

    protected ORAData create(DocDetT o, Datum d, int sqlType) throws SQLException {
        if (d == null) return null;
        if (o == null) o = new DocDetT();
        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        return o;
    }

    /* accessor methods */
    public java.math.BigDecimal getDocDetSk() throws SQLException {
        return (java.math.BigDecimal) _struct.getAttribute(0);
    }

    public void setDocDetSk(java.math.BigDecimal docDetSk) throws SQLException {
        _struct.setAttribute(0, docDetSk);
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


    public java.math.BigDecimal getDocSk() throws SQLException {
        return (java.math.BigDecimal) _struct.getAttribute(3);
    }

    public void setDocSk(java.math.BigDecimal docSk) throws SQLException {
        _struct.setAttribute(3, docSk);
    }


    public String getDocDetProptyKeyName() throws SQLException {
        return (String) _struct.getAttribute(4);
    }

    public void setDocDetProptyKeyName(String docDetProptyKeyName) throws SQLException {
        _struct.setAttribute(4, docDetProptyKeyName);
    }


    public String getDocDetProptyKeyVal() throws SQLException {
        return (String) _struct.getAttribute(5);
    }

    public void setDocDetProptyKeyVal(String docDetProptyKeyVal) throws SQLException {
        _struct.setAttribute(5, docDetProptyKeyVal);
    }

}
