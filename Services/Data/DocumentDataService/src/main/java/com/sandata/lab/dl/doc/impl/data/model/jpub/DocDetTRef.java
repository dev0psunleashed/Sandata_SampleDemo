package com.sandata.lab.dl.doc.impl.data.model.jpub;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;

public class DocDetTRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "LOBDATA.DOC_DET_T";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final DocDetTRef _DocDetTRefFactory = new DocDetTRef();

    public static ORADataFactory getORADataFactory() {
        return _DocDetTRefFactory;
    }

    /* constructor */
    public DocDetTRef() {
    }

    /* ORAData interface */
    public Datum toDatum(Connection c) throws SQLException {
        return _ref;
    }

    /* ORADataFactory interface */
    public ORAData create(Datum d, int sqlType) throws SQLException {
        if (d == null) return null;
        DocDetTRef r = new DocDetTRef();
        r._ref = (REF) d;
        return r;
    }

    public static DocDetTRef cast(ORAData o) throws SQLException {
        if (o == null) return null;
        try {
            return (DocDetTRef) getORADataFactory().create(o.toDatum(null), OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert " + o.getClass().getName() + " to DocDetTRef: " + exn.toString());
        }
    }

    public DocDetT getValue() throws SQLException {
        return (DocDetT) DocDetT.getORADataFactory().create(
                _ref.getSTRUCT(), OracleTypes.REF);
    }

    public void setValue(DocDetT c) throws SQLException {
        _ref.setValue((STRUCT) c.toDatum(_ref.getJavaSqlConnection()));
    }
}
