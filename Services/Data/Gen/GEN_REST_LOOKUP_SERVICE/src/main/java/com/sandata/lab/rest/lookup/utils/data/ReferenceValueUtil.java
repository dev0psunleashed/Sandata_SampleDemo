package com.sandata.lab.rest.lookup.utils.data;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Helper class to return string collection of reference values.
 * <p/>
 *
 * @author David Rutgos
 */
@SuppressWarnings("unchecked")
public class ReferenceValueUtil {

    private static String SANDATA_MODEL_DL_PACKAGE = "com.sandata.lab.data.model.dl.model";
    private static String SANDATA_MODEL_PAYROLL_PACKAGE = "com.sandata.lab.data.model.payroll";
    private static String SANDATA_MODEL_REFERENCE_PACKAGE = "com.sandata.lab.data.model.dl.model.extended.reference";
    private static String SANDATA_MODEL_COMPLIANCE_PACKAGE = "com.sandata.lab.data.model.dl.model.extended.compliance";
    private static String SANDATA_MODEL_VISIT_PACKAGE = "com.sandata.lab.data.model.dl.model.extended.visit";
    //private static String SANDATA_MODEL_STAFF_PACKAGE = "com.sandata.lab.data.model.dl.model.extended.staff";
    //private static String SANDATA_MODEL_PATIENT_PACKAGE = "com.sandata.lab.data.model.dl.model.extended.patient";
    private static String SANDATA_MODEL_AR_PACKAGE = "com.sandata.lab.data.model.dl.model.extended.ar";

    public static List GetReferenceValueList(String referenceEntity) throws SandataRuntimeException {

        //TODO: Refactor
        try {
            String entityClass = String.format("%s.%s", SANDATA_MODEL_DL_PACKAGE, referenceEntity);

            if (isPayrollEntity(referenceEntity)) {
                entityClass = String.format("%s.%s", SANDATA_MODEL_PAYROLL_PACKAGE, referenceEntity);
            } else if (isReferenceEntity(referenceEntity)) {
                entityClass = String.format("%s.%s", SANDATA_MODEL_REFERENCE_PACKAGE, referenceEntity);
            } else if (isComplianceEntity(referenceEntity)) {
                entityClass = String.format("%s.%s", SANDATA_MODEL_COMPLIANCE_PACKAGE, referenceEntity);
            } else if (isVisitEntity(referenceEntity)) {
                entityClass = String.format("%s.%s", SANDATA_MODEL_VISIT_PACKAGE, referenceEntity);
            } else if (isArEntity(referenceEntity)) {
                entityClass = String.format("%s.%s", SANDATA_MODEL_AR_PACKAGE, referenceEntity);
            }

            return new ArrayList<>(EnumSet.allOf((Class<? extends Enum>)Class.forName(entityClass)));
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("%s: EXCEPTION: GetReferenceValueList: %s",
                    ReferenceValueUtil.class.getName(), e.getMessage()), e);
        }
    }

    private static boolean isArEntity(String referenceEntity) {

        if (
                referenceEntity.equals("BatchStatus")
                || referenceEntity.equals("OpenBalance")

                ) {
            return true;
        }

        return false;
    }

    private static boolean isPayrollEntity(String referenceEntity) {

        if (
                referenceEntity.equals("StaffAssociatedRateType")
                || referenceEntity.equals("PayrollWeekendStartDays")
                || referenceEntity.equals("PayrollWeekendEndDays")
        ) {
            return true;
        }

        return false;
    }

    private static boolean isReferenceEntity(String referenceEntity) {

        if (
                referenceEntity.equals("Frequency")
                ) {
            return true;
        }

        return false;
    }

    private static boolean isComplianceEntity(String referenceEntity) {

        if (
                referenceEntity.equals("ComplianceNotificationPeriod")
                || referenceEntity.equals("ComplianceRequiredFrom")
                ) {
            return true;
        }

        return false;
    }

    private static boolean isVisitEntity(String referenceEntity) {

        if (
                referenceEntity.equals("SupervisoryVisitFrequency")
                ) {
            return true;
        }

        return false;
    }
}
