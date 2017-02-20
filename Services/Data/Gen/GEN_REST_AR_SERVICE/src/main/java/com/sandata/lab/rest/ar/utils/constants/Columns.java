package com.sandata.lab.rest.ar.utils.constants;

/**
 * For mapping column of table AR
 * @author thanhxle
 *
 */
public enum Columns {

    BE_ID("BE_ID"),
    PAYER_ID("PAYER_ID"),
    CONTR_ID("CONTR_ID"),
    INV_START_DATE("INV_START_DATE"),
    INV_END_DATE("INV_END_DATE"),
    INV_NUM("INV_NUM"),
    PT_FIRST_NAME("PT_FIRST_NAME"),
    PT_LAST_NAME("PT_LAST_NAME"),
    BE_LOC_ID("BE_LOC_ID"),
    BE_LOB_ID("BE_LOB_ID"),
    INV_STATUS_CODE("INV_STATUS_CODE"),
    TXN_CODE("AR_TXN_CODE"),
    TOTAL_AMT("TOTAL_AMT"),
    INV_DATE("INV_DATE"),
    INV_TOTAL_AMT("INV_TOTAL_AMT"),
    PAID_AMT("AR_TXN_BATCH.PMT_AMT"),
    AR_TXN_CODE("AR_TXN_CODE")
    ;


    private final String columnName;

    /**
     * Constructs message names.
     *
     * @param names Specified names
     */
    private Columns(final String columnName) {
        this.columnName = columnName;
    }



    public String getValue() {
        return columnName;
    }
}