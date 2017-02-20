package com.sandata.lab.dl.doc.services.oracle;

public enum CoreTables {
    PATIENTX_TABLE_NAME("PT_DOC_XWALK"),
    STAFF_TABLE_NAME("STAFF_DOC_XWALK"),
    BSNENTX_TABLE_NAME("BE_DOC_XWALK");

    private String value;

    private CoreTables(String value)
    {
        this.value = value;
    }
}
