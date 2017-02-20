package com.sandata.lab.rest.lookup.utils.data;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.DefaultLogger;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.dl.model.EmploymentStatusTypeName;
import com.sandata.lab.data.model.dl.model.PatientStatusName;
import com.sandata.lab.data.model.dl.model.SchedulePermissionQualifier;
import com.sandata.lab.data.model.dl.model.VisitStatusName;
import com.sandata.lab.rest.lookup.model.DashboardLookup;
import com.sandata.lab.rest.lookup.model.EnumLookup;
import com.sandata.lab.rest.lookup.model.UnappliedBalanceRangeLookup;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements in enumeration for given services. Some of these data enumerations may move to the database in the future.
 * <p/>
 *
 * @author David Rutgos
 */
public class EnumLookupUtil {

    SandataLogger logger = DefaultLogger.CreateLogger();

    public static Object GetEnumList(final String methodName)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        EnumLookupUtil util = new EnumLookupUtil();

        Method method = util.getClass().getDeclaredMethod(methodName);

        return method.invoke(util);
    }

    //dmr GEOR-4243
    public List<EnumLookup> getReferralReceivedByMethod() {
        List<EnumLookup> result = new ArrayList<>();
        result.add(new EnumLookup(1, "Email"));
        result.add(new EnumLookup(2, "Fax"));
        result.add(new EnumLookup(3, "Mail"));
        result.add(new EnumLookup(4, "Other"));
        result.add(new EnumLookup(5, "Phone"));

        return result;
    }

    //dmr GEOR-4361
    public List<EnumLookup> getUnionStatus() {
        List<EnumLookup> result = new ArrayList<>();
        result.add(new EnumLookup(1, "YES"));
        result.add(new EnumLookup(2, "NO"));
        return result;
    }

    //dmr GEOR-4360
    public List<EnumLookup> getPatientAssessmentFreq() {
        List<EnumLookup> result = new ArrayList<>();
        result.add(new EnumLookup(1, "30 Days"));
        result.add(new EnumLookup(2, "60 Days"));
        result.add(new EnumLookup(3, "90 Days"));
        result.add(new EnumLookup(4, "120 Days"));
        return result;
    }

    //dmr GEOR-3141
    public List<EnumLookup> getTal() {
        List<EnumLookup> result = new ArrayList<>();
        result.add(new EnumLookup(1, "Non-Ambulatory"));
        result.add(new EnumLookup(2, "Wheelchair"));
        result.add(new EnumLookup(3, "Ambulatory"));
        return result;
    }

    //dmr GEOR-2629
    public List<EnumLookup> getDnrDa() {
        List<EnumLookup> result = new ArrayList<>();
        result.add(new EnumLookup(1, "Yes"));
        result.add(new EnumLookup(2, "No"));
        result.add(new EnumLookup(3, "Unknown/Not Applicable"));
        result.add(new EnumLookup(4, "Advanced Directive"));
        return result;
    }

    //dmr GEOR-3019
    public List<EnumLookup> getPayHours() {
        List<EnumLookup> result = new ArrayList<>();
        result.add(new EnumLookup(1, "0 to 5 Hours"));
        result.add(new EnumLookup(2, "6 to 40 Hours"));
        result.add(new EnumLookup(3, "40+ Hours"));
        return result;
    }

    /**
     * GEOR-6209, the Patient's statues: ACTIVE, HOLD, DISCHARGED, REFERRAL, NOT TAKEN UNDER CARE
     * @link https://mnt-ers-ts01.sandata.com/object/view.spg?key=203430
     */
    public List<EnumLookup> getPatientStatus() {
        return getEnumSerializedNameList(PatientStatusName.values());
    }


    /**
     * SAN-3490: Scheduled,In Process,Incomplete,Verified,Processed,Omitted
     * Embarcadero: https://mnt-ers-ts01.sandata.com/object/view.spg?tab=properties&key=685139&diagramKey=66819
     * @return
     */
    public List<EnumLookup> getVisitStatus() {
        return getEnumSerializedNameList(VisitStatusName.values());
    }

    public List<EnumLookup> getPhoneQualifier() {
        List<EnumLookup> result = new ArrayList<>();
        result.add(new EnumLookup(1, "Home"));
        result.add(new EnumLookup(2, "Mobile"));
        result.add(new EnumLookup(3, "Work"));
        result.add(new EnumLookup(4, "Fax"));
        result.add(new EnumLookup(5, "Other"));
        return result;
    }

    public List<EnumLookup> getAddressType() {
        List<EnumLookup> result = new ArrayList<>();
        result.add(new EnumLookup(1, "Primary"));
        result.add(new EnumLookup(2, "Secondary"));
        result.add(new EnumLookup(3, "Tertiary"));
        result.add(new EnumLookup(4, "Other"));
        return result;
    }

    public List<EnumLookup> getPayerRankType() {
        List<EnumLookup> result = new ArrayList<>();
        result.add(new EnumLookup(1, "Primary"));
        result.add(new EnumLookup(2, "Secondary"));
        result.add(new EnumLookup(3, "Tertiary"));
        result.add(new EnumLookup(4, "Private"));
        return result;
    }

    public List<EnumLookup> getStaffEmploymentType() {
        List<EnumLookup> result = new ArrayList<>();
        result.add(new EnumLookup(1, "Full-Time"));
        result.add(new EnumLookup(2, "Part-Time"));
        result.add(new EnumLookup(3, "Temporary"));
        return result;
    }

    public List<EnumLookup> getAdditionalPayType() {
        List<EnumLookup> result = new ArrayList<>();
        result.add(new EnumLookup(1, "Additional Hourly"));
        result.add(new EnumLookup(2, "Bonus"));
        result.add(new EnumLookup(3, "Mileage"));
        result.add(new EnumLookup(4, "Overtime"));
        result.add(new EnumLookup(5, "Regular"));
        return result;
    }

    public List<EnumLookup> getRateType() {

        List<EnumLookup> result = new ArrayList<>();
        result.add(new EnumLookup(1, "MU", "Mutual"));
        result.add(new EnumLookup(2, "LI", "Live-In"));
        result.add(new EnumLookup(3, "SS", "Split Shift"));
        result.add(new EnumLookup(4, "ST", "Standard"));
        result.add(new EnumLookup(5, "<3", "Less Than Three"));
        result.add(new EnumLookup(6, "CL", "Cluster"));
        result.add(new EnumLookup(7, "NA", "Nurse Assessment"));
        result.add(new EnumLookup(8, "SV", "Supervisory Visit"));


        return result;
    }

    public List<EnumLookup> getReferralReceivalMethod() {

        List<EnumLookup> result = new ArrayList<>();
        result.add(new EnumLookup(1, "Hospital"));
        result.add(new EnumLookup(2, "Insurance"));
        result.add(new EnumLookup(3, "Physician"));
        return result;
    }

    public List<EnumLookup> getStaffRelationshipTypeLookup() {

        List<EnumLookup> result = new ArrayList<>();
        result.add(new EnumLookup(1, "Coordinator"));
        result.add(new EnumLookup(2, "Manager"));

        return result;
    }

    public List<EnumLookup> getStaffPreferredContactLookup() {

        List<EnumLookup> result = new ArrayList<>();
        result.add(new EnumLookup(1, "Cell Phone"));
        result.add(new EnumLookup(2, "Home Phone"));
        result.add(new EnumLookup(3, "E-Mail"));
        result.add(new EnumLookup(4, "Mail"));

        return result;
    }

    public List<EnumLookup> getStaffPositionLookup() {

        List<EnumLookup> result = new ArrayList<>();
        result.add(new EnumLookup(1, "LPN"));
        result.add(new EnumLookup(2, "Home Health Attendant"));
        result.add(new EnumLookup(3, "Office Staff"));
        result.add(new EnumLookup(4, "Personal Care Aid"));
        result.add(new EnumLookup(5, "RN"));

        return result;
    }

    public List<EnumLookup> getNoteTypeLookup() {

        List<EnumLookup> result = new ArrayList<>();
        result.add(new EnumLookup(1, "Note Type1"));
        result.add(new EnumLookup(2, "Note Type2"));
        result.add(new EnumLookup(3, "Note Type3"));
        result.add(new EnumLookup(4, "Note Type4"));

        return result;
    }

    /**
     * GEOR-6209, the Staff's statues: RECRUIT, ACTIVE, HOLD, TERMINATED, NOT OFFERED EMPLOYMENT
     * TODO: This method still is updated along with @link getPatientStatus although the values are actually EMPLT_STATUS_TYP_LKUP table
     */
    public List<EnumLookup> getEmploymentStatusLookup() {
        return getEnumSerializedNameList(EmploymentStatusTypeName.values());
    }

    public List<EnumLookup> getMilitaryStatusLookup() {

        List<EnumLookup> result = new ArrayList<>();
        result.add(new EnumLookup(2, "Active"));
        result.add(new EnumLookup(3, "Reserve"));
        result.add(new EnumLookup(1, "Unknown"));
        result.add(new EnumLookup(4, "Veteran"));

        return result;
    }

    public List<EnumLookup> getLimitByLookup() {

        List<EnumLookup> result = new ArrayList<>();
        result.add(new EnumLookup(1, "None"));
        result.add(new EnumLookup(2, "Day"));
        result.add(new EnumLookup(3, "Week"));
        result.add(new EnumLookup(4, "Month"));
        result.add(new EnumLookup(5, "Year"));

        return result;
    }

    public List<EnumLookup> getTransportationTypeLookup() {

        List<EnumLookup> result = new ArrayList<>();
        result.add(new EnumLookup(1, "Unknown"));
        result.add(new EnumLookup(2, "Car"));
        result.add(new EnumLookup(3, "Bus"));
        result.add(new EnumLookup(4, "Taxi"));

        return result;
    }

    public List<EnumLookup> getReferenceFormatLookup() {

        List<EnumLookup> result = new ArrayList<>();
        result.add(new EnumLookup(1, "Hours"));
        result.add(new EnumLookup(2, "Per-Diem"));
        result.add(new EnumLookup(3, "Units"));
        result.add(new EnumLookup(4, "Visits"));

        return result;
    }

    public List<EnumLookup> getEligibilityLookup() {

        List<EnumLookup> result = new ArrayList<>();
        result.add(new EnumLookup(1, "Active"));
        result.add(new EnumLookup(2, "Non-Active"));

        return result;
    }

    public List<EnumLookup> getAdmissionTypeLookup() {

        List<EnumLookup> result = new ArrayList<>();
        result.add(new EnumLookup(1, "Ameri-care"));
        result.add(new EnumLookup(2, "Cigna"));
        result.add(new EnumLookup(3, "Molina"));
        result.add(new EnumLookup(4, "UHC"));

        return result;
    }

    public List<EnumLookup> getNotificationTypeLookup() {

        List<EnumLookup> result = new ArrayList<>();
        result.add(new EnumLookup(1, "Phone"));
        result.add(new EnumLookup(2, "Email"));

        return result;
    }

    public List<UnappliedBalanceRangeLookup> getUnappliedBalanceRangeLookup() {
        List<UnappliedBalanceRangeLookup> result = new ArrayList<>();
        result.add(new UnappliedBalanceRangeLookup(1, "UnappliedBalanceRangeLookup", new BigDecimal(0), new BigDecimal(10)));
        result.add(new UnappliedBalanceRangeLookup(2, "UnappliedBalanceRangeLookup", new BigDecimal(11), new BigDecimal(50)));
        result.add(new UnappliedBalanceRangeLookup(3, "UnappliedBalanceRangeLookup", new BigDecimal(51), new BigDecimal(100)));
        result.add(new UnappliedBalanceRangeLookup(4, "UnappliedBalanceRangeLookup", new BigDecimal(101), new BigDecimal(300)));
        result.add(new UnappliedBalanceRangeLookup(5, "UnappliedBalanceRangeLookup", new BigDecimal(301), null));
        return result;
    }

    public List<DashboardLookup> getPayRollOvertimeHours() {
        List<DashboardLookup> result = new ArrayList<>();
        result.add(new DashboardLookup(1, "PayRollOvertimeHours", 0, -7, true, "Past 7 days"));
        result.add(new DashboardLookup(2, "PayRollOvertimeHours", -8, -14, false, "Past 8-14 days"));
        result.add(new DashboardLookup(3, "PayRollOvertimeHours", -15, -21, false, "Past 15-21 days"));
        result.add(new DashboardLookup(4, "PayRollOvertimeHours", -22, -28, false,"Past 22-28 days"));
        result.add(new DashboardLookup(5, "PayRollOvertimeHours", -29, -35, false,"Past 29-35 days"));
        result.add(new DashboardLookup(6, "PayRollOvertimeHours", -36, -42, false,"Past 36-42 days"));
        result.add(new DashboardLookup(7, "PayRollOvertimeHours", 0, 7, false,"0-7 days"));
        result.add(new DashboardLookup(8, "PayRollOvertimeHours", 8, 14, false,"8-14 days"));
        result.add(new DashboardLookup(9, "PayRollOvertimeHours", 15, 21, false,"15-21 days"));
        result.add(new DashboardLookup(10, "PayRollOvertimeHours", 22, 28, false,"22-28 days"));
        result.add(new DashboardLookup(11, "PayRollOvertimeHours", 29, 35, false,"29-35 days"));
        result.add(new DashboardLookup(12, "PayRollOvertimeHours", 36, 42, false,"36-42 days"));
        return result;
    }

    public List<DashboardLookup> getPayRollTotalHoursPaid() {
        List<DashboardLookup> result = new ArrayList<>();
        result.add(new DashboardLookup(1, "PayRollTotalHoursPaid", 0, -30, true, "Past 30 days"));
        result.add(new DashboardLookup(2, "PayRollTotalHoursPaid", -31, -60, false, "Past 31-60 days"));
        result.add(new DashboardLookup(3, "PayRollTotalHoursPaid", -61, -90, false, "Past 61-90 days"));
        result.add(new DashboardLookup(4, "PayRollTotalHoursPaid", -91, -120, false, "Past 91-120 days"));
        result.add(new DashboardLookup(5, "PayRollTotalHoursPaid", -121, -150, false, "Past 121-150 days"));
        result.add(new DashboardLookup(6, "PayRollTotalHoursPaid", -151, -180, false, "Past 151-180 days"));
        return result;
    }

    public List<DashboardLookup> getPayRollTotalHoursPending() {
        List<DashboardLookup> result = new ArrayList<>();
        result.add(new DashboardLookup(1, "PayRollTotalHoursPending", 0, -30, true, "Past 30 days"));
        result.add(new DashboardLookup(2, "PayRollTotalHoursPending", -31, -60, false, "Past 31-60 days"));
        result.add(new DashboardLookup(3, "PayRollTotalHoursPending", -61, -90, false, "Past 61-90 days"));
        result.add(new DashboardLookup(4, "PayRollTotalHoursPending", -91, -120, false, "Past 91-120 days"));
        result.add(new DashboardLookup(5, "PayRollTotalHoursPending", -121, -150, false, "Past 121-150 days"));
        result.add(new DashboardLookup(6, "PayRollTotalHoursPending", -151, -180, false, "Past 151-180 days"));
        return result;
    }

    public List<DashboardLookup> getPayRollTotalDollarsPaid() {
        List<DashboardLookup> result = new ArrayList<>();
        result.add(new DashboardLookup(1, "PayRollTotalDollarsPaid", 0, -30, true, "Past 30 days"));
        result.add(new DashboardLookup(2, "PayRollTotalDollarsPaid", -31, -60, false, "Past 31-60 days"));
        result.add(new DashboardLookup(3, "PayRollTotalDollarsPaid", -61, -90, false, "Past 61-90 days"));
        result.add(new DashboardLookup(4, "PayRollTotalDollarsPaid", -91, -120, false, "Past 91-120 days"));
        result.add(new DashboardLookup(5, "PayRollTotalDollarsPaid", -121, -150, false, "Past 121-150 days"));
        result.add(new DashboardLookup(6, "PayRollTotalDollarsPaid", -151, -180, false, "Past 151-180 days"));
        return result;
    }

    public List<DashboardLookup> getPayRollTotalDollarsPending() {
        List<DashboardLookup> result = new ArrayList<>();
        result.add(new DashboardLookup(1, "PayRollTotalDollarsPending", 0, -30, true, "Past 30 days"));
        result.add(new DashboardLookup(2, "PayRollTotalDollarsPending", -31, -60, false, "Past 31-60 days"));
        result.add(new DashboardLookup(3, "PayRollTotalDollarsPending", -61, -90, false, "Past 61-90 days"));
        result.add(new DashboardLookup(4, "PayRollTotalDollarsPending", -91, -120, false, "Past 91-120 days"));
        result.add(new DashboardLookup(5, "PayRollTotalDollarsPending", -121, -150, false, "Past 121-150 days"));
        result.add(new DashboardLookup(6, "PayRollTotalDollarsPending", -151, -180, false, "Past 151-180 days"));
        return result;
    }

    public List<DashboardLookup> getExpiredAuths() {
        List<DashboardLookup> result = new ArrayList<>();
        result.add(new DashboardLookup(1, "ExpiredAuths", 0, 30, true, "0-30 days"));
        result.add(new DashboardLookup(2, "ExpiredAuths", 31, 60, false, "31-60 days"));
        result.add(new DashboardLookup(3, "ExpiredAuths", 61, 90, false, "61-90 days"));
        result.add(new DashboardLookup(4, "ExpiredAuths", 0, -30, false, "Past 30 days"));
        result.add(new DashboardLookup(5, "ExpiredAuths", -31, -60, false, "Past 31-60 days"));
        result.add(new DashboardLookup(6, "ExpiredAuths", -61, -90, false, "Past 61-90 days"));

        return result;
    }

    public List<DashboardLookup> getScheduledHours() {
        List<DashboardLookup> result = new ArrayList<>();
        result.add(new DashboardLookup(1, "ScheduledHours", 0, -7, true, "Past 7 days"));
        result.add(new DashboardLookup(2, "ScheduledHours", -8, -14, false, "Past 8-14 days"));
        result.add(new DashboardLookup(3, "ScheduledHours", -15, -21, false, "Past 15-21 days"));
        result.add(new DashboardLookup(4, "ScheduledHours", -22, -28, false, "Past 22-28 days"));
        result.add(new DashboardLookup(5, "ScheduledHours", -29, -35, false, "Past 29-35 days"));
        result.add(new DashboardLookup(6, "ScheduledHours", -36, -42, false, "Past 36-42 days"));
        result.add(new DashboardLookup(7, "ScheduledHours", 0, 7, false, "0-7 days"));
        result.add(new DashboardLookup(8, "ScheduledHours", 8, 14, false, "8-14 days"));
        result.add(new DashboardLookup(9, "ScheduledHours", 15, 21, false, "15-21 days"));
        result.add(new DashboardLookup(10, "ScheduledHours", 22, 28, false, "22-28 days"));
        result.add(new DashboardLookup(11, "ScheduledHours", 29, 35, false, "29-35 days"));
        result.add(new DashboardLookup(12, "ScheduledHours", 36, 42, false, "36-42 days"));

        return result;
    }

    public List<DashboardLookup> getVisitExceptions() {
        List<DashboardLookup> result = new ArrayList<>();
        result.add(new DashboardLookup(1, "VisitExceptions", 0, -30, true, "Past 30 days"));
        result.add(new DashboardLookup(2, "VisitExceptions", -31, -60, false, "Past 31-60 days"));
        result.add(new DashboardLookup(3, "VisitExceptions", -61, -90, false, "Past 61-90 days"));

        return result;
    }

    public List<DashboardLookup> getVisitVerifiedHours() {
        List<DashboardLookup> result = new ArrayList<>();
        result.add(new DashboardLookup(1, "VisitVerifiedHours", 0, -30, true, "Past 30 days"));
        result.add(new DashboardLookup(2, "VisitVerifiedHours", -31, -60, false, "Past 31-60 days"));
        result.add(new DashboardLookup(3, "VisitVerifiedHours", -61, -90, false, "Past 61-90 days"));
        result.add(new DashboardLookup(4, "VisitVerifiedHours", -91, -120, false, "Past 91-120 days"));
        result.add(new DashboardLookup(5, "VisitVerifiedHours", -121, -150, false, "Past 121-150 days"));
        result.add(new DashboardLookup(6, "VisitVerifiedHours", -151, -180, false, "Past 151-180 days"));

        return result;
    }
    
    public List<DashboardLookup> getOpenOrders() {
        List<DashboardLookup> result = new ArrayList<>();
        result.add(new DashboardLookup(7, "OpenOrders", 0, 7, true, "0-7 days"));
        result.add(new DashboardLookup(8, "OpenOrders", 0, 14, false, "0-14 days"));
        result.add(new DashboardLookup(8, "OpenOrders", 0, 30, false, "0-30 days"));
        result.add(new DashboardLookup(9, "OpenOrders", 0, 60, false, "0-60 days"));
        result.add(new DashboardLookup(10, "OpenOrders", 0, 90, false, "0-90 days"));
        result.add(new DashboardLookup(11, "OpenOrders", 0, 120, false, "0-120 days"));
        result.add(new DashboardLookup(12, "OpenOrders", 0, 180, false, "0-180 days"));

        return result;
    }

    public List<DashboardLookup> getEligibility() {
        List<DashboardLookup> result = new ArrayList<>();
        result.add(new DashboardLookup(1, "Eligibility", 0, -30, true, "Past 0-30 days"));
        result.add(new DashboardLookup(2, "Eligibility", -31, -60, false, "Past 31-60 days"));
        result.add(new DashboardLookup(3, "Eligibility", -61, -90, false, "Past 61-90 days"));
        result.add(new DashboardLookup(4, "Eligibility", 0, 30, false, "0-30 days"));
        result.add(new DashboardLookup(5, "Eligibility", 31, 60, false, "31-60 days"));
        result.add(new DashboardLookup(6, "Eligibility", 61, 90, false, "61-90 days"));

        return result;
    }

    public List<DashboardLookup> getDateRange() {
        List<DashboardLookup> result = new ArrayList<>();
        result.add(new DashboardLookup(1, "DateRange", 0, 7, true, "0-7 days"));
        result.add(new DashboardLookup(2, "DateRange", 0, 14, false, "0-14 days"));
        result.add(new DashboardLookup(3, "DateRange", 0, 30, false, "0-30 days"));
        result.add(new DashboardLookup(4, "DateRange", 0, 45, false, "0-45 days"));
        result.add(new DashboardLookup(5, "DateRange", 0, 60, true, "0-60 days"));
        result.add(new DashboardLookup(6, "DateRange", 0, 75, false, "0-75 days"));
        result.add(new DashboardLookup(7, "DateRange", 0, 90, false, "0-90 days"));

        return result;
    }

    /**
     * @return SchedulePermissionQualifier: Prevent, Warn, Allow
     */
    public List<EnumLookup> getSchedulePermissionQualifier() {
        List<EnumLookup> result = new ArrayList<>();
        try {
            int index = 1;
            for (Enum item : SchedulePermissionQualifier.values()) {
                SerializedName serializedName = item.getDeclaringClass().getField(item.name()).getAnnotation(SerializedName.class);
                result.add(new EnumLookup(index++, serializedName.value()));
            }
        }catch (Exception ex){
            throw new SandataRuntimeException( String.format("Exception while getting SerializedName of the enum %",
                    SchedulePermissionQualifier.class.getCanonicalName()), ex);
        }
        return result;
    }

    private List<EnumLookup> getEnumSerializedNameList(Enum[] enumValues){

        List<EnumLookup> result = new ArrayList<>();
        if(enumValues == null || enumValues.length ==0){
            return result;
        }

        try {
            int index = 1;
            for (Enum item : enumValues) {
                SerializedName serializedName = item.getDeclaringClass().getField(item.name()).getAnnotation(SerializedName.class);
                result.add(new EnumLookup(index++, serializedName.value()));
            }
        }catch (Exception ex){
            throw new SandataRuntimeException( String.format("Exception while getting SerializedName of the enum %",
                    enumValues.getClass().getCanonicalName()), ex);
        }
        return result;
    }
}
