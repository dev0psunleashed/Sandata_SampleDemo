package com.sandata.lab.dl.doc.impl.data.model.jpub;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;

public class DocTRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "LOBDATA.DOC_T";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final DocTRef _DocTRefFactory = new DocTRef();

    public static ORADataFactory getORADataFactory() {
        return _DocTRefFactory;
    }

    /* constructor */
    public DocTRef() {
    }

    /* ORAData interface */
    public Datum toDatum(Connection c) throws SQLException {
        return _ref;
    }

    /* ORADataFactory interface */
    public ORAData create(Datum d, int sqlType) throws SQLException {
        if (d == null) return null;
        DocTRef r = new DocTRef();
        r._ref = (REF) d;
        return r;
    }

    public static DocTRef cast(ORAData o) throws SQLException {
        if (o == null) return null;
        try {
            return (DocTRef) getORADataFactory().create(o.toDatum(null), OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert " + o.getClass().getName() + " to DocTRef: " + exn.toString());
        }
    }

    public DocT getValue() throws SQLException {
        return (DocT) DocT.getORADataFactory().create(
                _ref.getSTRUCT(), OracleTypes.REF);
    }

    public void setValue(DocT c) throws SQLException {
        _ref.setValue((STRUCT) c.toDatum(_ref.getJavaSqlConnection()));
    }
}
