package com.sandata.one.aggregator.documents.impl.data.model.jpub;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class DocT implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "LOBDATA.DOC_T";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    protected MutableStruct _struct;

    protected static int[] _sqlType = {2, 91, 91, 12, 12, 2004, 2004};
    protected static ORADataFactory[] _factory = new ORADataFactory[7];
    protected static final DocT _DocTFactory = new DocT();

    public static ORADataFactory getORADataFactory() {
        return _DocTFactory;
    }

    /* constructors */
    protected void _init_struct(boolean init) {
        if (init) _struct = new MutableStruct(new Object[7], _sqlType, _factory);
    }

    public DocT() {
        _init_struct(true);
    }

    public DocT(java.math.BigDecimal docSk, java.sql.Timestamp recCreateTmstp, java.sql.Timestamp recUpdateTmstp, String docId, String docTypName, oracle.sql.BLOB doc, oracle.sql.BLOB docOther) throws SQLException {
        _init_struct(true);
        setDocSk(docSk);
        setRecCreateTmstp(recCreateTmstp);
        setRecUpdateTmstp(recUpdateTmstp);
        setDocId(docId);
        setDocTypName(docTypName);
        setDoc(doc);
        setDocOther(docOther);
    }

    /* ORAData interface */
    public Datum toDatum(Connection c) throws SQLException {
        return _struct.toDatum(c, _SQL_NAME);
    }


    /* ORADataFactory interface */
    public ORAData create(Datum d, int sqlType) throws SQLException {
        return create(null, d, sqlType);
    }

    protected ORAData create(DocT o, Datum d, int sqlType) throws SQLException {
        if (d == null) return null;
        if (o == null) o = new DocT();
        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        return o;
    }

    /* accessor methods */
    public java.math.BigDecimal getDocSk() throws SQLException {
        return (java.math.BigDecimal) _struct.getAttribute(0);
    }

    public void setDocSk(java.math.BigDecimal docSk) throws SQLException {
        _struct.setAttribute(0, docSk);
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


    public String getDocId() throws SQLException {
        return (String) _struct.getAttribute(3);
    }

    public void setDocId(String docId) throws SQLException {
        _struct.setAttribute(3, docId);
    }


    public String getDocTypName() throws SQLException {
        return (String) _struct.getAttribute(4);
    }

    public void setDocTypName(String docTypName) throws SQLException {
        _struct.setAttribute(4, docTypName);
    }


    public oracle.sql.BLOB getDoc() throws SQLException {
        return (oracle.sql.BLOB) _struct.getOracleAttribute(5);
    }

    public void setDoc(oracle.sql.BLOB doc) throws SQLException {
        _struct.setOracleAttribute(5, doc);
    }


    public oracle.sql.BLOB getDocOther() throws SQLException {
        return (oracle.sql.BLOB) _struct.getOracleAttribute(6);
    }

    public void setDocOther(oracle.sql.BLOB docOther) throws SQLException {
        _struct.setOracleAttribute(6, docOther);
    }

}
