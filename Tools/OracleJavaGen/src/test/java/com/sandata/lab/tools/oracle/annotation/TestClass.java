package com.sandata.lab.tools.oracle.annotation;

/**
 * Created by dmrutgos on 8/31/2015.
 */
@OracleMetadata(
        packageName = "PKG_PATIENT",
        insertMethod = "insertPatient",
        updateMethod = "updatePatient",
        deleteMethod = "deletePatient",
        getMethod = "getPatient",
        jpubClass = "com.sandata.lab.jpub.model.PatientTyp"
)
public class TestClass {
}
