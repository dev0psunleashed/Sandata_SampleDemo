
package org.tempuri;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="realTimeSantrax_schedule_ws">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                   &lt;element name="Activity">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="ASGN-ACCOUNT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="VendorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ASGN-RecipientID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="ASGN-EMP-SSN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="ActivityTblID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="ActivityDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                             &lt;element name="StartTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="EndTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="ElapsedTime" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="ARNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PayRate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BillRate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ScheduleFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DutyFree" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Weekend" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Discipline" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Service" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Contract" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Branch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="VisitType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="LiveInCase" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OTABHours" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OTABCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OTABApprover" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ProcedureCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CaseNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CaseSequence" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TimeZone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BillCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ProcCodeQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Modifier1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Modifier2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Modifier3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Modifier4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Employee">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="EMP-ACCOUNT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="VendorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="EMP-SSN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="EmployeeID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="LastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="FirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="MiddleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Department" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="EmpType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Address1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Address2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="State" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ZipCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="EmpPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name=" EmpPhoneAlt1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name=" EmpPhoneAlt2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Sex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DOB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Discipline" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PayRate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="GPSPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="EmployeeIDCustom1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="EmployeeIDCustom2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Compliance" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="ComplianceElement " type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="ComplianceValue " type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Recipient">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="CLIENT-ACCOUNT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="VendorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="RecipientID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="LastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="FirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Supervisor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DischargeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="Address1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Address2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="State" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ZipCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="RecipientSSN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Contract" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BillRate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Service" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CaseNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CaseSequence" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ProgramCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="MedicalRecordNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ARNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Team" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Branch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Borough" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Priority" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Area" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Weekend" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                             &lt;element name="CompanyNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Sex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DOB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TimeZone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="RecipientIDCustom1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="RecipientIDCustom2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="PhoneNbr">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="RecipientID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="PhoneNbr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="PHONE-ACCT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="VendorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "username",
    "password",
    "realTimeSantraxScheduleWs"
})
@XmlRootElement(name = "uploadSchedules")
public class UploadSchedules {

    protected String username;
    protected String password;
    @XmlElement(name = "realTimeSantrax_schedule_ws", required = true)
    protected UploadSchedules.RealTimeSantraxScheduleWs realTimeSantraxScheduleWs;

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the realTimeSantraxScheduleWs property.
     * 
     * @return
     *     possible object is
     *     {@link UploadSchedules.RealTimeSantraxScheduleWs }
     *     
     */
    public UploadSchedules.RealTimeSantraxScheduleWs getRealTimeSantraxScheduleWs() {
        return realTimeSantraxScheduleWs;
    }

    /**
     * Sets the value of the realTimeSantraxScheduleWs property.
     * 
     * @param value
     *     allowed object is
     *     {@link UploadSchedules.RealTimeSantraxScheduleWs }
     *     
     */
    public void setRealTimeSantraxScheduleWs(UploadSchedules.RealTimeSantraxScheduleWs value) {
        this.realTimeSantraxScheduleWs = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;choice maxOccurs="unbounded" minOccurs="0">
     *         &lt;element name="Activity">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="ASGN-ACCOUNT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="VendorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ASGN-RecipientID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="ASGN-EMP-SSN" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="ActivityTblID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="ActivityDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *                   &lt;element name="StartTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="EndTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="ElapsedTime" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="ARNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PayRate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BillRate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ScheduleFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DutyFree" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Weekend" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Discipline" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Service" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Contract" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Branch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="VisitType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="LiveInCase" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OTABHours" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OTABCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OTABApprover" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ProcedureCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CaseNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CaseSequence" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="TimeZone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BillCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ProcCodeQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Modifier1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Modifier2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Modifier3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Modifier4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="Employee">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="EMP-ACCOUNT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="VendorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="EMP-SSN" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="EmployeeID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="LastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="FirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="MiddleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Department" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="EmpType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Address1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Address2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="State" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ZipCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="EmpPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name=" EmpPhoneAlt1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name=" EmpPhoneAlt2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Sex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DOB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Discipline" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PayRate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="GPSPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="EmployeeIDCustom1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="EmployeeIDCustom2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Compliance" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="ComplianceElement " type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="ComplianceValue " type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="Recipient">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="CLIENT-ACCOUNT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="VendorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="RecipientID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="LastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="FirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Supervisor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DischargeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="Address1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Address2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="State" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ZipCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="RecipientSSN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Contract" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BillRate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Service" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CaseNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CaseSequence" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ProgramCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="MedicalRecordNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ARNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Team" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Branch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Borough" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Priority" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Area" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Weekend" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                   &lt;element name="CompanyNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Sex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DOB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="TimeZone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="RecipientIDCustom1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="RecipientIDCustom2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="PhoneNbr">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="RecipientID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="PhoneNbr" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="PHONE-ACCT" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="VendorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "activityOrEmployeeOrRecipient"
    })
    public static class RealTimeSantraxScheduleWs {

        @XmlElements({
            @XmlElement(name = "Activity", type = UploadSchedules.RealTimeSantraxScheduleWs.Activity.class),
            @XmlElement(name = "Employee", type = UploadSchedules.RealTimeSantraxScheduleWs.Employee.class),
            @XmlElement(name = "Recipient", type = UploadSchedules.RealTimeSantraxScheduleWs.Recipient.class),
            @XmlElement(name = "PhoneNbr", type = UploadSchedules.RealTimeSantraxScheduleWs.PhoneNbr.class)
        })
        protected List<Object> activityOrEmployeeOrRecipient;

        /**
         * Gets the value of the activityOrEmployeeOrRecipient property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the activityOrEmployeeOrRecipient property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getActivityOrEmployeeOrRecipient().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link UploadSchedules.RealTimeSantraxScheduleWs.Activity }
         * {@link UploadSchedules.RealTimeSantraxScheduleWs.Employee }
         * {@link UploadSchedules.RealTimeSantraxScheduleWs.Recipient }
         * {@link UploadSchedules.RealTimeSantraxScheduleWs.PhoneNbr }
         * 
         * 
         */
        public List<Object> getActivityOrEmployeeOrRecipient() {
            if (activityOrEmployeeOrRecipient == null) {
                activityOrEmployeeOrRecipient = new ArrayList<Object>();
            }
            return this.activityOrEmployeeOrRecipient;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="ASGN-ACCOUNT" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="VendorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ASGN-RecipientID" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="ASGN-EMP-SSN" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="ActivityTblID" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="ActivityDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
         *         &lt;element name="StartTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="EndTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="ElapsedTime" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="ARNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PayRate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BillRate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ScheduleFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DutyFree" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Weekend" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Discipline" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Service" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Contract" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Branch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="VisitType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="LiveInCase" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OTABHours" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OTABCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OTABApprover" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ProcedureCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CaseNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CaseSequence" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="TimeZone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BillCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ProcCodeQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Modifier1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Modifier2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Modifier3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Modifier4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "asgnaccount",
            "vendorCode",
            "asgnRecipientID",
            "asgnempssn",
            "activityTblID",
            "activityDate",
            "startTime",
            "endTime",
            "elapsedTime",
            "arNumber",
            "payRate",
            "billRate",
            "scheduleFlag",
            "dutyFree",
            "status",
            "weekend",
            "discipline",
            "service",
            "contract",
            "branch",
            "visitType",
            "liveInCase",
            "otabHours",
            "otabCode",
            "otabApprover",
            "procedureCode",
            "caseNumber",
            "caseSequence",
            "timeZone",
            "billCode",
            "procCodeQualifier",
            "modifier1",
            "modifier2",
            "modifier3",
            "modifier4"
        })
        public static class Activity {

            @XmlElement(name = "ASGN-ACCOUNT", required = true)
            protected String asgnaccount;
            @XmlElement(name = "VendorCode")
            protected String vendorCode;
            @XmlElement(name = "ASGN-RecipientID", required = true)
            protected String asgnRecipientID;
            @XmlElement(name = "ASGN-EMP-SSN", required = true)
            protected String asgnempssn;
            @XmlElement(name = "ActivityTblID", required = true)
            protected String activityTblID;
            @XmlElement(name = "ActivityDate", required = true)
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar activityDate;
            @XmlElement(name = "StartTime")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar startTime;
            @XmlElement(name = "EndTime")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar endTime;
            @XmlElement(name = "ElapsedTime")
            protected BigDecimal elapsedTime;
            @XmlElement(name = "ARNumber")
            protected String arNumber;
            @XmlElement(name = "PayRate")
            protected String payRate;
            @XmlElement(name = "BillRate")
            protected String billRate;
            @XmlElement(name = "ScheduleFlag")
            protected String scheduleFlag;
            @XmlElement(name = "DutyFree")
            protected String dutyFree;
            @XmlElement(name = "Status")
            protected String status;
            @XmlElement(name = "Weekend")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar weekend;
            @XmlElement(name = "Discipline")
            protected String discipline;
            @XmlElement(name = "Service")
            protected String service;
            @XmlElement(name = "Contract")
            protected String contract;
            @XmlElement(name = "Branch")
            protected String branch;
            @XmlElement(name = "VisitType")
            protected String visitType;
            @XmlElement(name = "LiveInCase")
            protected String liveInCase;
            @XmlElement(name = "OTABHours")
            protected String otabHours;
            @XmlElement(name = "OTABCode")
            protected String otabCode;
            @XmlElement(name = "OTABApprover")
            protected String otabApprover;
            @XmlElement(name = "ProcedureCode")
            protected String procedureCode;
            @XmlElement(name = "CaseNumber")
            protected String caseNumber;
            @XmlElement(name = "CaseSequence")
            protected String caseSequence;
            @XmlElement(name = "TimeZone")
            protected String timeZone;
            @XmlElement(name = "BillCode")
            protected String billCode;
            @XmlElement(name = "ProcCodeQualifier")
            protected String procCodeQualifier;
            @XmlElement(name = "Modifier1")
            protected String modifier1;
            @XmlElement(name = "Modifier2")
            protected String modifier2;
            @XmlElement(name = "Modifier3")
            protected String modifier3;
            @XmlElement(name = "Modifier4")
            protected String modifier4;

            /**
             * Gets the value of the asgnaccount property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getASGNACCOUNT() {
                return asgnaccount;
            }

            /**
             * Sets the value of the asgnaccount property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setASGNACCOUNT(String value) {
                this.asgnaccount = value;
            }

            /**
             * Gets the value of the vendorCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVendorCode() {
                return vendorCode;
            }

            /**
             * Sets the value of the vendorCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVendorCode(String value) {
                this.vendorCode = value;
            }

            /**
             * Gets the value of the asgnRecipientID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getASGNRecipientID() {
                return asgnRecipientID;
            }

            /**
             * Sets the value of the asgnRecipientID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setASGNRecipientID(String value) {
                this.asgnRecipientID = value;
            }

            /**
             * Gets the value of the asgnempssn property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getASGNEMPSSN() {
                return asgnempssn;
            }

            /**
             * Sets the value of the asgnempssn property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setASGNEMPSSN(String value) {
                this.asgnempssn = value;
            }

            /**
             * Gets the value of the activityTblID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getActivityTblID() {
                return activityTblID;
            }

            /**
             * Sets the value of the activityTblID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setActivityTblID(String value) {
                this.activityTblID = value;
            }

            /**
             * Gets the value of the activityDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getActivityDate() {
                return activityDate;
            }

            /**
             * Sets the value of the activityDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setActivityDate(XMLGregorianCalendar value) {
                this.activityDate = value;
            }

            /**
             * Gets the value of the startTime property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getStartTime() {
                return startTime;
            }

            /**
             * Sets the value of the startTime property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setStartTime(XMLGregorianCalendar value) {
                this.startTime = value;
            }

            /**
             * Gets the value of the endTime property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getEndTime() {
                return endTime;
            }

            /**
             * Sets the value of the endTime property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setEndTime(XMLGregorianCalendar value) {
                this.endTime = value;
            }

            /**
             * Gets the value of the elapsedTime property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getElapsedTime() {
                return elapsedTime;
            }

            /**
             * Sets the value of the elapsedTime property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setElapsedTime(BigDecimal value) {
                this.elapsedTime = value;
            }

            /**
             * Gets the value of the arNumber property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getARNumber() {
                return arNumber;
            }

            /**
             * Sets the value of the arNumber property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setARNumber(String value) {
                this.arNumber = value;
            }

            /**
             * Gets the value of the payRate property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPayRate() {
                return payRate;
            }

            /**
             * Sets the value of the payRate property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPayRate(String value) {
                this.payRate = value;
            }

            /**
             * Gets the value of the billRate property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBillRate() {
                return billRate;
            }

            /**
             * Sets the value of the billRate property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBillRate(String value) {
                this.billRate = value;
            }

            /**
             * Gets the value of the scheduleFlag property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getScheduleFlag() {
                return scheduleFlag;
            }

            /**
             * Sets the value of the scheduleFlag property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setScheduleFlag(String value) {
                this.scheduleFlag = value;
            }

            /**
             * Gets the value of the dutyFree property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDutyFree() {
                return dutyFree;
            }

            /**
             * Sets the value of the dutyFree property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDutyFree(String value) {
                this.dutyFree = value;
            }

            /**
             * Gets the value of the status property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStatus() {
                return status;
            }

            /**
             * Sets the value of the status property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStatus(String value) {
                this.status = value;
            }

            /**
             * Gets the value of the weekend property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getWeekend() {
                return weekend;
            }

            /**
             * Sets the value of the weekend property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setWeekend(XMLGregorianCalendar value) {
                this.weekend = value;
            }

            /**
             * Gets the value of the discipline property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDiscipline() {
                return discipline;
            }

            /**
             * Sets the value of the discipline property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDiscipline(String value) {
                this.discipline = value;
            }

            /**
             * Gets the value of the service property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getService() {
                return service;
            }

            /**
             * Sets the value of the service property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setService(String value) {
                this.service = value;
            }

            /**
             * Gets the value of the contract property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getContract() {
                return contract;
            }

            /**
             * Sets the value of the contract property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setContract(String value) {
                this.contract = value;
            }

            /**
             * Gets the value of the branch property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBranch() {
                return branch;
            }

            /**
             * Sets the value of the branch property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBranch(String value) {
                this.branch = value;
            }

            /**
             * Gets the value of the visitType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVisitType() {
                return visitType;
            }

            /**
             * Sets the value of the visitType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVisitType(String value) {
                this.visitType = value;
            }

            /**
             * Gets the value of the liveInCase property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLiveInCase() {
                return liveInCase;
            }

            /**
             * Sets the value of the liveInCase property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLiveInCase(String value) {
                this.liveInCase = value;
            }

            /**
             * Gets the value of the otabHours property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOTABHours() {
                return otabHours;
            }

            /**
             * Sets the value of the otabHours property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOTABHours(String value) {
                this.otabHours = value;
            }

            /**
             * Gets the value of the otabCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOTABCode() {
                return otabCode;
            }

            /**
             * Sets the value of the otabCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOTABCode(String value) {
                this.otabCode = value;
            }

            /**
             * Gets the value of the otabApprover property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOTABApprover() {
                return otabApprover;
            }

            /**
             * Sets the value of the otabApprover property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOTABApprover(String value) {
                this.otabApprover = value;
            }

            /**
             * Gets the value of the procedureCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getProcedureCode() {
                return procedureCode;
            }

            /**
             * Sets the value of the procedureCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setProcedureCode(String value) {
                this.procedureCode = value;
            }

            /**
             * Gets the value of the caseNumber property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCaseNumber() {
                return caseNumber;
            }

            /**
             * Sets the value of the caseNumber property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCaseNumber(String value) {
                this.caseNumber = value;
            }

            /**
             * Gets the value of the caseSequence property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCaseSequence() {
                return caseSequence;
            }

            /**
             * Sets the value of the caseSequence property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCaseSequence(String value) {
                this.caseSequence = value;
            }

            /**
             * Gets the value of the timeZone property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTimeZone() {
                return timeZone;
            }

            /**
             * Sets the value of the timeZone property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTimeZone(String value) {
                this.timeZone = value;
            }

            /**
             * Gets the value of the billCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBillCode() {
                return billCode;
            }

            /**
             * Sets the value of the billCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBillCode(String value) {
                this.billCode = value;
            }

            /**
             * Gets the value of the procCodeQualifier property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getProcCodeQualifier() {
                return procCodeQualifier;
            }

            /**
             * Sets the value of the procCodeQualifier property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setProcCodeQualifier(String value) {
                this.procCodeQualifier = value;
            }

            /**
             * Gets the value of the modifier1 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getModifier1() {
                return modifier1;
            }

            /**
             * Sets the value of the modifier1 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setModifier1(String value) {
                this.modifier1 = value;
            }

            /**
             * Gets the value of the modifier2 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getModifier2() {
                return modifier2;
            }

            /**
             * Sets the value of the modifier2 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setModifier2(String value) {
                this.modifier2 = value;
            }

            /**
             * Gets the value of the modifier3 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getModifier3() {
                return modifier3;
            }

            /**
             * Sets the value of the modifier3 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setModifier3(String value) {
                this.modifier3 = value;
            }

            /**
             * Gets the value of the modifier4 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getModifier4() {
                return modifier4;
            }

            /**
             * Sets the value of the modifier4 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setModifier4(String value) {
                this.modifier4 = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="EMP-ACCOUNT" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="VendorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="EMP-SSN" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="EmployeeID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="LastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="FirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="MiddleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Department" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="EmpType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Address1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Address2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="State" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ZipCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="EmpPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name=" EmpPhoneAlt1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name=" EmpPhoneAlt2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Sex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DOB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Discipline" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PayRate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="GPSPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="EmployeeIDCustom1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="EmployeeIDCustom2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Compliance" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="ComplianceElement " type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="ComplianceValue " type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "empaccount",
            "vendorCode",
            "empssn",
            "employeeID",
            "lastName",
            "firstName",
            "status",
            "middleName",
            "department",
            "empType",
            "address1",
            "address2",
            "city",
            "state",
            "zipCode",
            "empPhone",
            "_0020EmpPhoneAlt1",
            "_0020EmpPhoneAlt2",
            "sex",
            "dob",
            "discipline",
            "payRate",
            "gpsPhone",
            "employeeIDCustom1",
            "employeeIDCustom2",
            "compliance"
        })
        public static class Employee {

            @XmlElement(name = "EMP-ACCOUNT", required = true)
            protected String empaccount;
            @XmlElement(name = "VendorCode")
            protected String vendorCode;
            @XmlElement(name = "EMP-SSN", required = true)
            protected String empssn;
            @XmlElement(name = "EmployeeID")
            protected String employeeID;
            @XmlElement(name = "LastName", required = true)
            protected String lastName;
            @XmlElement(name = "FirstName", required = true)
            protected String firstName;
            @XmlElement(name = "Status")
            protected String status;
            @XmlElement(name = "MiddleName")
            protected String middleName;
            @XmlElement(name = "Department")
            protected String department;
            @XmlElement(name = "EmpType")
            protected String empType;
            @XmlElement(name = "Address1")
            protected String address1;
            @XmlElement(name = "Address2")
            protected String address2;
            @XmlElement(name = "City")
            protected String city;
            @XmlElement(name = "State")
            protected String state;
            @XmlElement(name = "ZipCode")
            protected String zipCode;
            @XmlElement(name = "EmpPhone")
            protected String empPhone;
            @XmlElement(name = " EmpPhoneAlt1")
            protected String _0020EmpPhoneAlt1;
            @XmlElement(name = " EmpPhoneAlt2")
            protected String _0020EmpPhoneAlt2;
            @XmlElement(name = "Sex")
            protected String sex;
            @XmlElement(name = "DOB")
            protected String dob;
            @XmlElement(name = "Discipline")
            protected String discipline;
            @XmlElement(name = "PayRate")
            protected String payRate;
            @XmlElement(name = "GPSPhone")
            protected String gpsPhone;
            @XmlElement(name = "EmployeeIDCustom1")
            protected String employeeIDCustom1;
            @XmlElement(name = "EmployeeIDCustom2")
            protected String employeeIDCustom2;
            @XmlElement(name = "Compliance")
            protected UploadSchedules.RealTimeSantraxScheduleWs.Employee.Compliance compliance;

            /**
             * Gets the value of the empaccount property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEMPACCOUNT() {
                return empaccount;
            }

            /**
             * Sets the value of the empaccount property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEMPACCOUNT(String value) {
                this.empaccount = value;
            }

            /**
             * Gets the value of the vendorCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVendorCode() {
                return vendorCode;
            }

            /**
             * Sets the value of the vendorCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVendorCode(String value) {
                this.vendorCode = value;
            }

            /**
             * Gets the value of the empssn property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEMPSSN() {
                return empssn;
            }

            /**
             * Sets the value of the empssn property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEMPSSN(String value) {
                this.empssn = value;
            }

            /**
             * Gets the value of the employeeID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEmployeeID() {
                return employeeID;
            }

            /**
             * Sets the value of the employeeID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEmployeeID(String value) {
                this.employeeID = value;
            }

            /**
             * Gets the value of the lastName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLastName() {
                return lastName;
            }

            /**
             * Sets the value of the lastName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLastName(String value) {
                this.lastName = value;
            }

            /**
             * Gets the value of the firstName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFirstName() {
                return firstName;
            }

            /**
             * Sets the value of the firstName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFirstName(String value) {
                this.firstName = value;
            }

            /**
             * Gets the value of the status property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStatus() {
                return status;
            }

            /**
             * Sets the value of the status property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStatus(String value) {
                this.status = value;
            }

            /**
             * Gets the value of the middleName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMiddleName() {
                return middleName;
            }

            /**
             * Sets the value of the middleName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMiddleName(String value) {
                this.middleName = value;
            }

            /**
             * Gets the value of the department property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDepartment() {
                return department;
            }

            /**
             * Sets the value of the department property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDepartment(String value) {
                this.department = value;
            }

            /**
             * Gets the value of the empType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEmpType() {
                return empType;
            }

            /**
             * Sets the value of the empType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEmpType(String value) {
                this.empType = value;
            }

            /**
             * Gets the value of the address1 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAddress1() {
                return address1;
            }

            /**
             * Sets the value of the address1 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAddress1(String value) {
                this.address1 = value;
            }

            /**
             * Gets the value of the address2 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAddress2() {
                return address2;
            }

            /**
             * Sets the value of the address2 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAddress2(String value) {
                this.address2 = value;
            }

            /**
             * Gets the value of the city property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCity() {
                return city;
            }

            /**
             * Sets the value of the city property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCity(String value) {
                this.city = value;
            }

            /**
             * Gets the value of the state property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getState() {
                return state;
            }

            /**
             * Sets the value of the state property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setState(String value) {
                this.state = value;
            }

            /**
             * Gets the value of the zipCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getZipCode() {
                return zipCode;
            }

            /**
             * Sets the value of the zipCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setZipCode(String value) {
                this.zipCode = value;
            }

            /**
             * Gets the value of the empPhone property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEmpPhone() {
                return empPhone;
            }

            /**
             * Sets the value of the empPhone property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEmpPhone(String value) {
                this.empPhone = value;
            }

            /**
             * Gets the value of the 0020EmpPhoneAlt1 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String get_0020EmpPhoneAlt1() {
                return _0020EmpPhoneAlt1;
            }

            /**
             * Sets the value of the 0020EmpPhoneAlt1 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void set_0020EmpPhoneAlt1(String value) {
                this._0020EmpPhoneAlt1 = value;
            }

            /**
             * Gets the value of the 0020EmpPhoneAlt2 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String get_0020EmpPhoneAlt2() {
                return _0020EmpPhoneAlt2;
            }

            /**
             * Sets the value of the 0020EmpPhoneAlt2 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void set_0020EmpPhoneAlt2(String value) {
                this._0020EmpPhoneAlt2 = value;
            }

            /**
             * Gets the value of the sex property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSex() {
                return sex;
            }

            /**
             * Sets the value of the sex property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSex(String value) {
                this.sex = value;
            }

            /**
             * Gets the value of the dob property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDOB() {
                return dob;
            }

            /**
             * Sets the value of the dob property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDOB(String value) {
                this.dob = value;
            }

            /**
             * Gets the value of the discipline property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDiscipline() {
                return discipline;
            }

            /**
             * Sets the value of the discipline property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDiscipline(String value) {
                this.discipline = value;
            }

            /**
             * Gets the value of the payRate property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPayRate() {
                return payRate;
            }

            /**
             * Sets the value of the payRate property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPayRate(String value) {
                this.payRate = value;
            }

            /**
             * Gets the value of the gpsPhone property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getGPSPhone() {
                return gpsPhone;
            }

            /**
             * Sets the value of the gpsPhone property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setGPSPhone(String value) {
                this.gpsPhone = value;
            }

            /**
             * Gets the value of the employeeIDCustom1 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEmployeeIDCustom1() {
                return employeeIDCustom1;
            }

            /**
             * Sets the value of the employeeIDCustom1 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEmployeeIDCustom1(String value) {
                this.employeeIDCustom1 = value;
            }

            /**
             * Gets the value of the employeeIDCustom2 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEmployeeIDCustom2() {
                return employeeIDCustom2;
            }

            /**
             * Sets the value of the employeeIDCustom2 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEmployeeIDCustom2(String value) {
                this.employeeIDCustom2 = value;
            }

            /**
             * Gets the value of the compliance property.
             * 
             * @return
             *     possible object is
             *     {@link UploadSchedules.RealTimeSantraxScheduleWs.Employee.Compliance }
             *     
             */
            public UploadSchedules.RealTimeSantraxScheduleWs.Employee.Compliance getCompliance() {
                return compliance;
            }

            /**
             * Sets the value of the compliance property.
             * 
             * @param value
             *     allowed object is
             *     {@link UploadSchedules.RealTimeSantraxScheduleWs.Employee.Compliance }
             *     
             */
            public void setCompliance(UploadSchedules.RealTimeSantraxScheduleWs.Employee.Compliance value) {
                this.compliance = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="ComplianceElement " type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="ComplianceValue " type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "complianceElement0020",
                "complianceValue0020",
                "status"
            })
            public static class Compliance {

                @XmlElement(name = "ComplianceElement ")
                protected String complianceElement0020;
                @XmlElement(name = "ComplianceValue ", required = true)
                protected String complianceValue0020;
                @XmlElement(name = "Status")
                protected String status;

                /**
                 * Gets the value of the complianceElement0020 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getComplianceElement_0020() {
                    return complianceElement0020;
                }

                /**
                 * Sets the value of the complianceElement0020 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setComplianceElement_0020(String value) {
                    this.complianceElement0020 = value;
                }

                /**
                 * Gets the value of the complianceValue0020 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getComplianceValue_0020() {
                    return complianceValue0020;
                }

                /**
                 * Sets the value of the complianceValue0020 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setComplianceValue_0020(String value) {
                    this.complianceValue0020 = value;
                }

                /**
                 * Gets the value of the status property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getStatus() {
                    return status;
                }

                /**
                 * Sets the value of the status property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setStatus(String value) {
                    this.status = value;
                }

            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="RecipientID" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="PhoneNbr" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="PHONE-ACCT" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="VendorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "recipientID",
            "phoneNbr",
            "phoneacct",
            "vendorCode"
        })
        public static class PhoneNbr {

            @XmlElement(name = "RecipientID", required = true)
            protected String recipientID;
            @XmlElement(name = "PhoneNbr", required = true)
            protected String phoneNbr;
            @XmlElement(name = "PHONE-ACCT", required = true)
            protected String phoneacct;
            @XmlElement(name = "VendorCode")
            protected String vendorCode;

            /**
             * Gets the value of the recipientID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRecipientID() {
                return recipientID;
            }

            /**
             * Sets the value of the recipientID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRecipientID(String value) {
                this.recipientID = value;
            }

            /**
             * Gets the value of the phoneNbr property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPhoneNbr() {
                return phoneNbr;
            }

            /**
             * Sets the value of the phoneNbr property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPhoneNbr(String value) {
                this.phoneNbr = value;
            }

            /**
             * Gets the value of the phoneacct property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPHONEACCT() {
                return phoneacct;
            }

            /**
             * Sets the value of the phoneacct property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPHONEACCT(String value) {
                this.phoneacct = value;
            }

            /**
             * Gets the value of the vendorCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVendorCode() {
                return vendorCode;
            }

            /**
             * Sets the value of the vendorCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVendorCode(String value) {
                this.vendorCode = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="CLIENT-ACCOUNT" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="VendorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="RecipientID" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="LastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="FirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Supervisor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DischargeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="Address1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Address2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="State" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ZipCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="RecipientSSN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Contract" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BillRate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Service" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CaseNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CaseSequence" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ProgramCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="MedicalRecordNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ARNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Team" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Branch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Borough" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Priority" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Area" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Weekend" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *         &lt;element name="CompanyNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Sex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DOB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="TimeZone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="RecipientIDCustom1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="RecipientIDCustom2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "clientaccount",
            "vendorCode",
            "recipientID",
            "lastName",
            "firstName",
            "status",
            "supervisor",
            "dischargeDate",
            "address1",
            "address2",
            "city",
            "state",
            "zipCode",
            "recipientSSN",
            "contract",
            "billRate",
            "service",
            "caseNumber",
            "caseSequence",
            "programCode",
            "medicalRecordNumber",
            "arNumber",
            "team",
            "branch",
            "borough",
            "priority",
            "area",
            "weekend",
            "companyNumber",
            "sex",
            "dob",
            "timeZone",
            "recipientIDCustom1",
            "recipientIDCustom2"
        })
        public static class Recipient {

            @XmlElement(name = "CLIENT-ACCOUNT", required = true)
            protected String clientaccount;
            @XmlElement(name = "VendorCode")
            protected String vendorCode;
            @XmlElement(name = "RecipientID", required = true)
            protected String recipientID;
            @XmlElement(name = "LastName", required = true)
            protected String lastName;
            @XmlElement(name = "FirstName", required = true)
            protected String firstName;
            @XmlElement(name = "Status")
            protected String status;
            @XmlElement(name = "Supervisor")
            protected String supervisor;
            @XmlElement(name = "DischargeDate")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar dischargeDate;
            @XmlElement(name = "Address1")
            protected String address1;
            @XmlElement(name = "Address2")
            protected String address2;
            @XmlElement(name = "City")
            protected String city;
            @XmlElement(name = "State")
            protected String state;
            @XmlElement(name = "ZipCode")
            protected String zipCode;
            @XmlElement(name = "RecipientSSN")
            protected String recipientSSN;
            @XmlElement(name = "Contract")
            protected String contract;
            @XmlElement(name = "BillRate")
            protected String billRate;
            @XmlElement(name = "Service")
            protected String service;
            @XmlElement(name = "CaseNumber")
            protected String caseNumber;
            @XmlElement(name = "CaseSequence")
            protected String caseSequence;
            @XmlElement(name = "ProgramCode")
            protected String programCode;
            @XmlElement(name = "MedicalRecordNumber")
            protected String medicalRecordNumber;
            @XmlElement(name = "ARNumber")
            protected String arNumber;
            @XmlElement(name = "Team")
            protected String team;
            @XmlElement(name = "Branch")
            protected String branch;
            @XmlElement(name = "Borough")
            protected String borough;
            @XmlElement(name = "Priority")
            protected String priority;
            @XmlElement(name = "Area")
            protected String area;
            @XmlElement(name = "Weekend")
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar weekend;
            @XmlElement(name = "CompanyNumber")
            protected String companyNumber;
            @XmlElement(name = "Sex")
            protected String sex;
            @XmlElement(name = "DOB")
            protected String dob;
            @XmlElement(name = "TimeZone")
            protected String timeZone;
            @XmlElement(name = "RecipientIDCustom1")
            protected String recipientIDCustom1;
            @XmlElement(name = "RecipientIDCustom2")
            protected String recipientIDCustom2;

            /**
             * Gets the value of the clientaccount property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCLIENTACCOUNT() {
                return clientaccount;
            }

            /**
             * Sets the value of the clientaccount property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCLIENTACCOUNT(String value) {
                this.clientaccount = value;
            }

            /**
             * Gets the value of the vendorCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVendorCode() {
                return vendorCode;
            }

            /**
             * Sets the value of the vendorCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVendorCode(String value) {
                this.vendorCode = value;
            }

            /**
             * Gets the value of the recipientID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRecipientID() {
                return recipientID;
            }

            /**
             * Sets the value of the recipientID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRecipientID(String value) {
                this.recipientID = value;
            }

            /**
             * Gets the value of the lastName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLastName() {
                return lastName;
            }

            /**
             * Sets the value of the lastName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLastName(String value) {
                this.lastName = value;
            }

            /**
             * Gets the value of the firstName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFirstName() {
                return firstName;
            }

            /**
             * Sets the value of the firstName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFirstName(String value) {
                this.firstName = value;
            }

            /**
             * Gets the value of the status property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStatus() {
                return status;
            }

            /**
             * Sets the value of the status property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStatus(String value) {
                this.status = value;
            }

            /**
             * Gets the value of the supervisor property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSupervisor() {
                return supervisor;
            }

            /**
             * Sets the value of the supervisor property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSupervisor(String value) {
                this.supervisor = value;
            }

            /**
             * Gets the value of the dischargeDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDischargeDate() {
                return dischargeDate;
            }

            /**
             * Sets the value of the dischargeDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDischargeDate(XMLGregorianCalendar value) {
                this.dischargeDate = value;
            }

            /**
             * Gets the value of the address1 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAddress1() {
                return address1;
            }

            /**
             * Sets the value of the address1 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAddress1(String value) {
                this.address1 = value;
            }

            /**
             * Gets the value of the address2 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAddress2() {
                return address2;
            }

            /**
             * Sets the value of the address2 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAddress2(String value) {
                this.address2 = value;
            }

            /**
             * Gets the value of the city property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCity() {
                return city;
            }

            /**
             * Sets the value of the city property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCity(String value) {
                this.city = value;
            }

            /**
             * Gets the value of the state property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getState() {
                return state;
            }

            /**
             * Sets the value of the state property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setState(String value) {
                this.state = value;
            }

            /**
             * Gets the value of the zipCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getZipCode() {
                return zipCode;
            }

            /**
             * Sets the value of the zipCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setZipCode(String value) {
                this.zipCode = value;
            }

            /**
             * Gets the value of the recipientSSN property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRecipientSSN() {
                return recipientSSN;
            }

            /**
             * Sets the value of the recipientSSN property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRecipientSSN(String value) {
                this.recipientSSN = value;
            }

            /**
             * Gets the value of the contract property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getContract() {
                return contract;
            }

            /**
             * Sets the value of the contract property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setContract(String value) {
                this.contract = value;
            }

            /**
             * Gets the value of the billRate property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBillRate() {
                return billRate;
            }

            /**
             * Sets the value of the billRate property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBillRate(String value) {
                this.billRate = value;
            }

            /**
             * Gets the value of the service property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getService() {
                return service;
            }

            /**
             * Sets the value of the service property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setService(String value) {
                this.service = value;
            }

            /**
             * Gets the value of the caseNumber property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCaseNumber() {
                return caseNumber;
            }

            /**
             * Sets the value of the caseNumber property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCaseNumber(String value) {
                this.caseNumber = value;
            }

            /**
             * Gets the value of the caseSequence property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCaseSequence() {
                return caseSequence;
            }

            /**
             * Sets the value of the caseSequence property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCaseSequence(String value) {
                this.caseSequence = value;
            }

            /**
             * Gets the value of the programCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getProgramCode() {
                return programCode;
            }

            /**
             * Sets the value of the programCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setProgramCode(String value) {
                this.programCode = value;
            }

            /**
             * Gets the value of the medicalRecordNumber property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMedicalRecordNumber() {
                return medicalRecordNumber;
            }

            /**
             * Sets the value of the medicalRecordNumber property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMedicalRecordNumber(String value) {
                this.medicalRecordNumber = value;
            }

            /**
             * Gets the value of the arNumber property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getARNumber() {
                return arNumber;
            }

            /**
             * Sets the value of the arNumber property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setARNumber(String value) {
                this.arNumber = value;
            }

            /**
             * Gets the value of the team property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTeam() {
                return team;
            }

            /**
             * Sets the value of the team property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTeam(String value) {
                this.team = value;
            }

            /**
             * Gets the value of the branch property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBranch() {
                return branch;
            }

            /**
             * Sets the value of the branch property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBranch(String value) {
                this.branch = value;
            }

            /**
             * Gets the value of the borough property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBorough() {
                return borough;
            }

            /**
             * Sets the value of the borough property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBorough(String value) {
                this.borough = value;
            }

            /**
             * Gets the value of the priority property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPriority() {
                return priority;
            }

            /**
             * Sets the value of the priority property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPriority(String value) {
                this.priority = value;
            }

            /**
             * Gets the value of the area property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getArea() {
                return area;
            }

            /**
             * Sets the value of the area property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setArea(String value) {
                this.area = value;
            }

            /**
             * Gets the value of the weekend property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getWeekend() {
                return weekend;
            }

            /**
             * Sets the value of the weekend property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setWeekend(XMLGregorianCalendar value) {
                this.weekend = value;
            }

            /**
             * Gets the value of the companyNumber property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCompanyNumber() {
                return companyNumber;
            }

            /**
             * Sets the value of the companyNumber property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCompanyNumber(String value) {
                this.companyNumber = value;
            }

            /**
             * Gets the value of the sex property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSex() {
                return sex;
            }

            /**
             * Sets the value of the sex property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSex(String value) {
                this.sex = value;
            }

            /**
             * Gets the value of the dob property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDOB() {
                return dob;
            }

            /**
             * Sets the value of the dob property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDOB(String value) {
                this.dob = value;
            }

            /**
             * Gets the value of the timeZone property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTimeZone() {
                return timeZone;
            }

            /**
             * Sets the value of the timeZone property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTimeZone(String value) {
                this.timeZone = value;
            }

            /**
             * Gets the value of the recipientIDCustom1 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRecipientIDCustom1() {
                return recipientIDCustom1;
            }

            /**
             * Sets the value of the recipientIDCustom1 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRecipientIDCustom1(String value) {
                this.recipientIDCustom1 = value;
            }

            /**
             * Gets the value of the recipientIDCustom2 property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRecipientIDCustom2() {
                return recipientIDCustom2;
            }

            /**
             * Sets the value of the recipientIDCustom2 property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRecipientIDCustom2(String value) {
                this.recipientIDCustom2 = value;
            }

        }

    }

}
