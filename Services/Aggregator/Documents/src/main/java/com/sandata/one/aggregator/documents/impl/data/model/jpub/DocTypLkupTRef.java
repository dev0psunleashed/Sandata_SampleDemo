package com.sandata.one.aggregator.documents.impl.data.model.jpub;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;

public class DocTypLkupTRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "LOBDATA.DOC_TYP_LKUP_T";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final DocTypLkupTRef _DocTypLkupTRefFactory = new DocTypLkupTRef();

    public static ORADataFactory getORADataFactory() {
        return _DocTypLkupTRefFactory;
    }

    /* constructor */
    public DocTypLkupTRef() {
    }

    /* ORAData interface */
    public Datum toDatum(Connection c) throws SQLException {
        return _ref;
    }

    /* ORADataFactory interface */
    public ORAData create(Datum d, int sqlType) throws SQLException {
        if (d == null) return null;
        DocTypLkupTRef r = new DocTypLkupTRef();
        r._ref = (REF) d;
        return r;
    }

    public static DocTypLkupTRef cast(ORAData o) throws SQLException {
        if (o == null) return null;
        try {
            return (DocTypLkupTRef) getORADataFactory().create(o.toDatum(null), OracleTypes.REF);
        } catch (Exception exn) {
            throw new SQLException("Unable to convert " + o.getClass().getName() + " to DocTypLkupTRef: " + exn.toString());
        }
    }

    public DocTypLkupT getValue() throws SQLException {
        return (DocTypLkupT) DocTypLkupT.getORADataFactory().create(
                _ref.getSTRUCT(), OracleTypes.REF);
    }

    public void setValue(DocTypLkupT c) throws SQLException {
        _ref.setValue((STRUCT) c.toDatum(_ref.getJavaSqlConnection()));
    }
}
