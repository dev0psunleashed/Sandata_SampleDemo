package com.sandata.lab.exports.evv.model;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 10/9/2016
 * Time: 7:28 AM
 */

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

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
@XmlType(name = "Employee", propOrder = {
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
public class Employee {
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
    protected Compliance compliance;

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
     *     {@link Compliance }
     *
     */
    public Compliance getCompliance() {
        return compliance;
    }

    /**
     * Sets the value of the compliance property.
     *
     * @param value
     *     allowed object is
     *     {@link Compliance }
     *
     */
    public void setCompliance(Compliance value) {
        this.compliance = value;
    }



}

