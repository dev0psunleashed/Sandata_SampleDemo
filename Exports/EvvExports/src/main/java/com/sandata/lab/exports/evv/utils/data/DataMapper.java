package com.sandata.lab.exports.evv.utils.data;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.exports.evv.model.*;
import com.sandata.lab.exports.evv.utils.Log;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import sun.util.calendar.Gregorian;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 10/9/2016
 * Time: 7:51 AM
 */
public class DataMapper {
    private Log log = new Log();
    private static final String DATETIME_FULL = "yyyy-MM-dd'T'HH:mm:ssZZ";
    private static final String DATETIME_NO_ZONE = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String DATE_FULL = "yyyy-MM-dd'T'00:00:00ZZ";
    private static final String DATE_NO_ZONE = "yyyy-MM-dd'T'00:00:00";
    private static final String DOB_FORMAT = "MMddyyyy";



    public Employee mapEmployeeFromStaff(ResultSet resultSet, String accountId) throws SandataRuntimeException {
        Employee employee = new Employee();
        String fieldName = "EMP-ACCOUNT";
        try {
            //REQUIRED FIELDS BY REAL TIME
            employee.setEMPACCOUNT(accountId);
            fieldName = "EMP_SSN";
            String ssn = setStringFromResultSetField(resultSet, fieldName, 9);
            employee.setEMPSSN(ssn);
            fieldName = "LastName";
            employee.setLastName(setStringFromResultSetField(resultSet, fieldName, 30));
            fieldName = "FirstName";
            employee.setFirstName(setStringFromResultSetField(resultSet, fieldName, 30));
        }
        catch(Exception e) {
            throw new SandataRuntimeException("Field required by realTimeInterface::Exception=" + e.getMessage() + "::FieldName=" + fieldName);
        }
        try {
            //REQUIRED BY GEORGE
            fieldName = "EmployeeID";
            employee.setEmployeeID(setStringFromResultSetField(resultSet, fieldName, 10));
        }
        catch(Exception e) {
            throw new SandataRuntimeException("Field required by Sandata.one::Exception=" + e.getMessage() + "::FieldName=" + fieldName);
        }
        int fieldCount = 0;
        while (fieldCount < 20) {
            try {
                //FIELDS NOT REQUIRED
                if(fieldCount == 0) {
                    //employee.setVendorCode(setStringFromResultSetField(resultSet, "VendorCode"));
                    fieldCount++;
                }
                if(fieldCount == 1) {

                    //employee.setStatus(setStringFromResultSetField(resultSet, "Status"));
                    fieldCount++;
                }
                if(fieldCount == 2) {
                    employee.setMiddleName(setStringFromResultSetField(resultSet, "MiddleName"));
                    fieldCount++;
                }
                if(fieldCount == 3) {
                    employee.setDepartment(setStringFromResultSetField(resultSet, "Department"));
                    fieldCount++;
                }
                if(fieldCount == 4) {
                    employee.setEmpType(setStringFromResultSetField(resultSet, "EmpType"));
                    fieldCount++;
                }
                if(fieldCount == 5) {
                    employee.setAddress1(setStringFromResultSetField(resultSet, "Address1", 30));
                    fieldCount++;
                }
                if(fieldCount == 6) {
                    employee.setAddress2(setStringFromResultSetField(resultSet, "Address2", 30));
                    fieldCount++;
                }
                if(fieldCount == 7) {
                    employee.setCity(setStringFromResultSetField(resultSet, "City"));
                    fieldCount++;
                }
                if(fieldCount == 8) {
                    employee.setState(setStringFromResultSetField(resultSet, "State"));
                    fieldCount++;
                }
                if(fieldCount == 9) {
                    employee.setZipCode(setStringFromResultSetField(resultSet, "ZipCode"));
                    fieldCount++;
                }
                if(fieldCount == 10) {
                employee.setEmpPhone(setStringFromResultSetField(resultSet, "EmpPhone"));
                    fieldCount++;
                }
                if(fieldCount == 11) {
                 //   employee.set_0020EmpPhoneAlt1(setStringFromResultSetField(resultSet, "EmpPhoneAlt1"));
                    fieldCount++;
                }
                if(fieldCount == 12) {
                //employee.set_0020EmpPhoneAlt2(setStringFromResultSetField(resultSet, "EmpPhoneAlt2"));
                    fieldCount++;
                }

                if(fieldCount == 13) {
                    //employee.setSex(setSexFromResultSetField(resultSet, "Sex"));
                    fieldCount++;
                }

                if(fieldCount == 14) {
                    //employee.setDOB(setDOBStringFromResultSetField(resultSet, "DOB"));
                    fieldCount++;
                }
                if(fieldCount == 15) {
                    employee.setDiscipline(setStringFromResultSetField(resultSet, "Discipline"));
                    fieldCount++;
                }

                if(fieldCount == 16) {
                    //employee.setPayRate(setStringFromResultSetField(resultSet, "PayRate"));
                    fieldCount++;
                }

                if(fieldCount == 17) {
                    //employee.setGPSPhone(setStringFromResultSetField(resultSet, "GPSPhone"));
                    fieldCount++;
                }
                if(fieldCount == 18) {
                    employee.setEmployeeIDCustom1(setStringFromResultSetField(resultSet, "EmployeeIDCustom1"));
                    fieldCount++;
                }
                if(fieldCount == 19) {
                    employee.setEmployeeIDCustom2(setStringFromResultSetField(resultSet, "EmployeeIDCustom2"));
                    fieldCount++;
                }
                fieldCount++;
            }
            catch(Exception e) {
                //This is for dev and troubleshooting but not neccessary in productio since these fields are not required.
                //log.getExportLog().info("Field missing but not not required by realTimeInterface or Sandata.one will continue to process::Exception=" + e.getMessage() + String.format("::Field index = %d", fieldCount));
                fieldCount++;
            }
        }
        return employee;
    }


    private String setSexFromResultSetField(ResultSet resultSet, String sexField) throws Exception {
        String sex = resultSet.getString(sexField);
        if(sex == null) {
            throw new Exception(String.format("Field %s was null", sexField));
        }
        else if(sex.toUpperCase().startsWith("M")) {
            sex = "M";
        }
        else if (sex.toUpperCase().startsWith("F")) {
            sex = "F";
        }
        else {
            sex = "O";
        }
        return sex;
    }
    private String setStringFromResultSetField(ResultSet resultSet, String fieldName) throws Exception {
        return setStringFromResultSetField(resultSet, fieldName, -1, false);
    }
    private String setStringFromResultSetField(ResultSet resultSet, String fieldName, int maxLength) throws Exception {
        return setStringFromResultSetField(resultSet, fieldName, maxLength, false);
    }
    private String setStringFromResultSetField(ResultSet resultSet, String fieldName, int maxLength, boolean throwExceptionOnLength) throws Exception {
        String field = resultSet.getString(fieldName);
        if(field == null) {
            throw new Exception(String.format("Field %s was null", fieldName));
        }
        if(maxLength > 0) {
            if (field.length() > maxLength) {
                if(throwExceptionOnLength) {
                    throw new Exception(String.format("EVV Cant Handle field = %s with length greater than length = %d, value = %s ", fieldName, maxLength, field));
                }
                field = field.substring(0, maxLength);
            }
        }
        return field;
    }

    public Recipient mapRecipientFromPatient(ResultSet resultSet, String accountId) {

        Recipient recipient = new Recipient();
        String fieldName = "CLIENT-ACCOUNT";
        String timeZoneId = null;
        try {
            //REQUIRED FIELDS BY REAL TIME
            recipient.setCLIENTACCOUNT(accountId);
            fieldName = "RecipientID";
            recipient.setRecipientID(setStringFromResultSetField(resultSet, fieldName, 10, true));
            fieldName = "LastName";
            recipient.setLastName(setStringFromResultSetField(resultSet, fieldName, 30));
            fieldName = "FirstName";
            recipient.setFirstName(setStringFromResultSetField(resultSet, fieldName, 30));
            timeZoneId = swapTimeZoneIfAmercanNewYork(setStringFromResultSetField(resultSet, "TimeZone"));
            recipient.setTimeZone(timeZoneId);

            //    fieldName = "Status";
        //    recipient.setStatus(setStringFromResultSetField(resultSet, fieldName));

        }
        catch(Exception e) {
            throw new SandataRuntimeException("Field required by realTimeInterface::Exception=" + e.getMessage() + "::FieldName=" + fieldName);
        }
        int fieldCount = 0;

        while (fieldCount < 14) {
            try {
                //FIELDS NOT REQUIRED
                if (fieldCount == 0) {
                    //recipient.setVendorCode(setStringFromResultSetField(resultSet, "VendorCode"));
                    fieldCount++;
                }
                if (fieldCount == 1) {
                    recipient.setDischargeDate(setDateTimeFromResultSetField(resultSet, "DischargeDate", timeZoneId, false));
                    fieldCount++;
                }
                if (fieldCount == 2) {
                    recipient.setAddress1(setStringFromResultSetField(resultSet, "Address1", 30));
                    fieldCount++;
                }
                if (fieldCount == 3) {
                    recipient.setAddress2(setStringFromResultSetField(resultSet, "Address2", 30));
                    fieldCount++;
                }
                if (fieldCount == 4) {
                    recipient.setCity(setStringFromResultSetField(resultSet, "City"));
                    fieldCount++;
                }
                if (fieldCount == 5) {
                    recipient.setState(setStringFromResultSetField(resultSet, "State"));
                    fieldCount++;
                }
                if (fieldCount == 6) {
                    recipient.setZipCode(setStringFromResultSetField(resultSet, "ZipCode"));
                    fieldCount++;
                }
                if (fieldCount == 7) {
                    recipient.setRecipientSSN(setStringFromResultSetField(resultSet, "RecipientSSN", 9));
                    fieldCount++;
                }
                if (fieldCount == 8) {
                    recipient.setMedicalRecordNumber(setStringFromResultSetField(resultSet, "MedicalRecordNumber"));
                    fieldCount++;
                }
                if (fieldCount == 9) {
                    recipient.setBorough(setStringFromResultSetField(resultSet, "Borough", 1));
                    fieldCount++;
                }
                if (fieldCount == 10) {
                    recipient.setSex(setSexFromResultSetField(resultSet, "Sex"));
                    fieldCount++;
                }
                if (fieldCount == 11) {
                    recipient.setDOB(setDOBStringFromResultSetField(resultSet, "DOB"));
                    fieldCount++;
                }
                if (fieldCount == 12) {
                    recipient.setRecipientIDCustom1(setStringFromResultSetField(resultSet, "RecipientIDCustom1"));
                    fieldCount++;
                }
                if (fieldCount == 13) {
                    recipient.setRecipientIDCustom2(setStringFromResultSetField(resultSet, "RecipientIDCustom2"));
                    fieldCount++;
                }
                fieldCount++;

            }
            catch(Exception e) {
                //This is for dev and troubleshooting but not neccessary in productio since these fields are not required.
                //log.getExportLog().info("Field missing but not not required by realTimeInterface or Sandata.one will continue to process::Exception=" + e.getMessage() + String.format("::Field index = %d", fieldCount));
                fieldCount++;
            }
        }

        return recipient;
    }



    public PhoneNbr mapPhoneNbrFromPatient(ResultSet resultSet, String accountId) {
        PhoneNbr phoneNbr = new PhoneNbr();
        String fieldName = "PHONE-ACCT";
        try {
            phoneNbr.setPHONEACCT(accountId);
            fieldName = "PhoneNbr";
            phoneNbr.setPhoneNbr(setStringFromResultSetField(resultSet, fieldName, 10));
            fieldName = "RecipientID";
            phoneNbr.setRecipientID(setStringFromResultSetField(resultSet, fieldName, 10));
        } catch (Exception e) {
            throw new SandataRuntimeException("Field required by realTimeInterface::Exception=" + e.getMessage() + "::FieldName=" + fieldName);
        }
        /*
        "RecipientID"
        "PhoneNbr"
        "PHONE-ACCT"

        "VendorCode"
        */
        int fieldCount = 0;
        while (fieldCount < 1) {
            try {
                //FIELDS NOT REQUIRED
                if (fieldCount == 0) {
                    //activity.setVendorCode(setStringFromResultSetField(resultSet, "VendorCode"));
                    fieldCount++;
                }
                fieldCount++;
            } catch (Exception e) {
                log.getExportLog().info("Field missing but not not required by realTimeInterface or Sandata.one will continue to process::Exception=" + e.getMessage() + "::FieldName=" + fieldName);
                fieldCount++;
            }
        }
        return phoneNbr;
    }


    public Activity mapActivityFromSchedule(ResultSet resultSet, String accountId) {
        Activity activity = new Activity();
        String fieldName = "ASGN-ACCOUNT";
        String timeZoneId = null;
        try {
            //REQUIRED FIELDS BY REAL TIME
            activity.setASGNACCOUNT(accountId);
            fieldName = "ASGN_RecipientID";
            activity.setASGNRecipientID(setStringFromResultSetField(resultSet, fieldName, 10));
            fieldName = "ASGN_EMP_SSN";
            activity.setASGNEMPSSN(setStringFromResultSetField(resultSet, fieldName, 9));
            fieldName = "ActivityTblID";
            String tableId = setStringFromResultSetField(resultSet, fieldName);
            log.getExportLog().info(String.format("*********************** ACTIVITY :: ActivityTblID = %s ***************************",
                            tableId));
            activity.setActivityTblID(tableId);
            fieldName = "TimeZone";
            timeZoneId = swapTimeZoneIfAmercanNewYork(setStringFromResultSetField(resultSet, "TimeZone"));
            activity.setTimeZone(timeZoneId);
            fieldName = "ActivityDate";
            activity.setActivityDate(setStringDateFromResultSetField(resultSet, fieldName, timeZoneId));//activity.setActivityDate(setXmlDateTimeFromResultSetField(resultSet, fieldName, true));
            fieldName = "deleteDate";
            String formattedDeletionDate = setDOBStringFromResultSetField(resultSet, fieldName);
            if(!formattedDeletionDate.equals("12319999")) {
                activity.setStatus("D");
            }
            else {
                fieldName = "Cancelled";
                String sandataOneStatus = setStringFromResultSetField(resultSet, fieldName);
                if (sandataOneStatus.equalsIgnoreCase("CANCELLED")) {
                    activity.setStatus("D");
                }

            }
        }
        catch(Exception e) {
            if(fieldName.equals("Cancelled")) {
                log.getExportLog().info("Field SCHED_EVNT_STATUS WAS NULL when checking for CANCELLED Sandata.one will continue to process record with unknown status");
            }
            else {
                throw new SandataRuntimeException("Field required by realTimeInterface::Exception=" + e.getMessage() + "::FieldName=" + fieldName);
            }
        }
        int fieldCount = 0;

        while (fieldCount < 6) {
            try {
                //FIELDS NOT REQUIRED
                if (fieldCount == 0) {
                    //activity.setVendorCode(setStringFromResultSetField(resultSet, "VendorCode"));
                    fieldCount++;
                }
                if(fieldCount == 1) {
                    activity.setStartTime(setStringDateTimeFromResultSetField(resultSet, "StartTime", timeZoneId));
                    fieldCount++;
                }
                if(fieldCount == 2) {
                    activity.setEndTime(setStringDateTimeFromResultSetField(resultSet, "EndTime", timeZoneId));
                    fieldCount++;
                }

                if(fieldCount == 3) {
                    activity.setElapsedTime(setBigDecimalFromResultSetField(resultSet, "ElapsedTime"));
                    fieldCount++;
                }
                if(fieldCount == 4) {
                    activity.setPayRate(setStringFromResultSetField(resultSet, "PayRate"));
                    fieldCount++;
                }
                if(fieldCount == 5) {
                    activity.setBillRate(setStringFromResultSetField(resultSet, "BillRate"));
                    fieldCount++;
                }

                fieldCount++;
            }
            catch(Exception e) {
                //This is for dev and troubleshooting but not neccessary in productio since these fields are not required.
                //log.getExportLog().info("Field missing but not not required by realTimeInterface or Sandata.one will continue to process::Exception=" + e.getMessage() + String.format("::Field index = %d", fieldCount));
                fieldCount++;
            }
        }
        return activity;

    }


    private BigDecimal setBigDecimalFromResultSetField(ResultSet resultSet, String fieldName) throws SQLException{
        BigDecimal bigDecimal = null;
        bigDecimal = resultSet.getBigDecimal(fieldName);
        return bigDecimal;
    }

    private String setStringDateFromResultSetField(ResultSet resultSet, String fieldName, String timeZoneId)  throws Exception {

        return setDateTimeFromResultSetField(resultSet, fieldName, timeZoneId, true);
    }

    private String setStringDateTimeFromResultSetField(ResultSet resultSet, String fieldName, String timeZoneId) throws Exception {

        return setDateTimeFromResultSetField(resultSet, fieldName, timeZoneId, false);

    }
    private String setDateTimeFromResultSetField(ResultSet resultSet, String fieldName, String timeZoneId, boolean dateOnly) throws SQLException, DatatypeConfigurationException {
        Date date = null;
        date = resultSet.getTimestamp(fieldName);
        if(date == null) {
            return null;
        }
        String beforeConversion;
        String afterConversion;
        String msg = "Process UTC Date only :: Date before was; date = %s, Date after was date = %s";
        if(dateOnly) {
            DateTime dt = new DateTime(date);
            beforeConversion = getFullDateStringUTCFromDate(dt);
            afterConversion = beforeConversion;//getFullDateStringFromDateAndZone(dt, timeZoneId);
        }
        else {
            msg = "Process UTC DateTime :: DateTime before was; datetime = %s, Date after was datetime = %s";
            DateTime dt = new DateTime(date);
            beforeConversion = getFullDateTimeStringUTCFromDate(dt);
            afterConversion = getFullDateTimeStringFromDateAndZone(dt, timeZoneId);
        }
        String info = String.format(msg, beforeConversion, afterConversion);
        log.getExportLog().info(info);
        return afterConversion;
    }

    private String setDOBStringFromResultSetField(ResultSet resultSet, String fieldName) throws SQLException {
        Date date = null;
        date = resultSet.getTimestamp(fieldName);
        if(date == null) {
            return null;
        }
        String noConversion;
        String msg = "Process DOB in Santrax format MMDDYYYY :: Date = %s";

        DateTime dt = new DateTime(date);
        DateTimeFormatter dtf = DateTimeFormat.forPattern(DOB_FORMAT);
        noConversion = dtf.print(dt);
        String info = String.format(msg, noConversion);
        log.getExportLog().info(info);
        return noConversion;
    }

    String swapTimeZoneIfAmercanNewYork(String zone) {
        if(zone != null && !zone.isEmpty() && zone.equals("America/New_York")) {
            return "US/Eastern";
        }
        return zone;
    }
    String getFullDateTimeStringUTCFromDate(DateTime date) {
        return getFullDateTimeStringFromDateAndZone(date, DateTimeZone.UTC);
    }
    String getFullDateStringUTCFromDate(DateTime date) {
        return getFullDateStringFromDateAndZone(date, DateTimeZone.UTC);
    }
    String getFullDateTimeStringFromDateAndZone(DateTime date, String zone) {
        return getFullDateTimeStringFromDateAndZone(date, DateTimeZone.forID(zone));
    }
    String getFullDateStringFromDateAndZone(DateTime date, String zone) {
        return getFullDateTimeStringFromDateAndZone(date, DateTimeZone.forID(zone));
    }
    String getFullDateTimeStringFromDateAndZone(DateTime date, DateTimeZone zone) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern(DATETIME_FULL).withZone(zone);
        return dtf.print(date);
    }
    String getFullDateStringFromDateAndZone(DateTime date, DateTimeZone zone) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern(DATE_FULL).withZone(zone);
        return dtf.print(date);
    }
}
