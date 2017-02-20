//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.28 at 11:05:46 PM EDT 
//


package com.sandata.lab.data.model.dl.model;

import com.sandata.lab.data.model.*;
import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.Adapter1;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for County_Subdivision_Lookup complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="County_Subdivision_Lookup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}Patient_Contact_Address" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Staff_Contact_Address" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="County_Subdivision_Lookup_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Create_Timestamp" use="required" type="{}Record_Create_Timestamp" />
 *       &lt;attribute name="Record_Update_Timestamp" use="required" type="{}Record_Update_Timestamp" />
 *       &lt;attribute name="Record_Effective_Timestamp" use="required" type="{}Record_Effective_Timestamp" />
 *       &lt;attribute name="Record_Termination_Timestamp" use="required" type="{}Record_Termination_Timestamp" />
 *       &lt;attribute name="Record_Created_By" type="{}Record_Created_By" />
 *       &lt;attribute name="Record_Updated_By" type="{}Record_Updated_By" />
 *       &lt;attribute name="Change_Reason_Memo" type="{}Change_Reason_Memo" />
 *       &lt;attribute name="Current_Record_Indicator" use="required" type="{}Current_Record_Indicator" />
 *       &lt;attribute name="Change_Version_ID" use="required" type="{}Change_Version_ID" />
 *       &lt;attribute name="County_Subdivision_FIPS_Code">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="10"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="County_Subdivision_Qualifier" use="required" type="{}County_Subdivision_Qualifier" />
 *       &lt;attribute name="County_Subdivision_Name" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="50"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="County_FIPS_Code">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="10"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="State_FIPS_Code">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="10"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="State_Code" use="required" type="{}State_Code" />
 *       &lt;attribute name="County_Subdivision_Functional_Status_Code" type="{}Code" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "County_Subdivision_Lookup", propOrder = {
    "patientContactAddress",
    "staffContactAddress"
})
@OracleMetadata(
        packageName = "PKG_LOOKUP",
        insertMethod = "insertCountySubdivLkup",
        updateMethod = "updateCountySubdivLkup",
        deleteMethod = "deleteCountySubdivLkup",
        getMethod = "getCountySubdivLkup",
        findMethod = "NotDefined_FindMethod",
        jpubClass = "com.sandata.lab.data.model.jpub.model.CountySubdivLkupT",
        primaryKeys = {})
public class CountySubdivisionLookup extends BaseObject {

	private static final long serialVersionUID = 1L;

    @XmlElement(name = "Patient_Contact_Address")
    @SerializedName("PatientContactAddress")
    protected List<PatientContactAddress> patientContactAddress;
    @XmlElement(name = "Staff_Contact_Address")
    @SerializedName("StaffContactAddress")
    protected List<StaffContactAddress> staffContactAddress;
    @XmlAttribute(name = "County_Subdivision_Lookup_SK", required = true)
    @SerializedName("CountySubdivisionLookupSK")
	@Mapping(getter = "getCountySubdivLkupSk", setter = "setCountySubdivLkupSk", type = "java.math.BigDecimal", index = 0)
    protected BigInteger countySubdivisionLookupSK;
    @XmlAttribute(name = "Record_Create_Timestamp", required = true)
    @SerializedName("RecordCreateTimestamp")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter1.class)
	@Mapping(getter = "getRecCreateTmstp", setter = "setRecCreateTmstp", type = "java.sql.Timestamp", index = 1)
    protected Date recordCreateTimestamp;
    @XmlAttribute(name = "Record_Update_Timestamp", required = true)
    @SerializedName("RecordUpdateTimestamp")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter1.class)
	@Mapping(getter = "getRecUpdateTmstp", setter = "setRecUpdateTmstp", type = "java.sql.Timestamp", index = 2)
    protected Date recordUpdateTimestamp;
    @XmlAttribute(name = "Record_Effective_Timestamp", required = true)
    @SerializedName("RecordEffectiveTimestamp")
    @XmlJavaTypeAdapter(com.sandata.lab.data.model.Adapter1.class)
	@Mapping(getter = "getRecEffTmstp", setter = "setRecEffTmstp", type = "java.sql.Timestamp", index = 3)
    protected Date recordEffectiveTimestamp;
    @XmlAttribute(name = "Record_Termination_Timestamp", required = true)
    @SerializedName("RecordTerminationTimestamp")
    @XmlJavaTypeAdapter(Adapter1.class)
	@Mapping(getter = "getRecTermTmstp", setter = "setRecTermTmstp", type = "java.sql.Timestamp", index = 4)
    protected Date recordTerminationTimestamp;
    @XmlAttribute(name = "Record_Created_By")
    @SerializedName("RecordCreatedBy")
	@Mapping(getter = "getRecCreatedBy", setter = "setRecCreatedBy", type = "String", index = 5)
    protected String recordCreatedBy;
    @XmlAttribute(name = "Record_Updated_By")
    @SerializedName("RecordUpdatedBy")
	@Mapping(getter = "getRecUpdatedBy", setter = "setRecUpdatedBy", type = "String", index = 6)
    protected String recordUpdatedBy;
    @XmlAttribute(name = "Change_Reason_Memo")
    @SerializedName("ChangeReasonMemo")
	@Mapping(getter = "getChangeReasonMemo", setter = "setChangeReasonMemo", type = "String", index = 7)
    protected String changeReasonMemo;
    @XmlAttribute(name = "Current_Record_Indicator", required = true)
    @SerializedName("CurrentRecordIndicator")
	@Mapping(getter = "getCurrRecInd", setter = "setCurrRecInd", type = "java.math.BigDecimal", index = 8)
    protected boolean currentRecordIndicator;
    @XmlAttribute(name = "Change_Version_ID", required = true)
    @SerializedName("ChangeVersionID")
	@Mapping(getter = "getChangeVersionId", setter = "setChangeVersionId", type = "java.math.BigDecimal", index = 9)
    protected BigInteger changeVersionID;
    @XmlAttribute(name = "County_Subdivision_FIPS_Code")
    @SerializedName("CountySubdivisionFIPSCode")
	@Mapping(getter = "getCountySubdivFipsCode", setter = "setCountySubdivFipsCode", type = "String", index = 10)
    protected String countySubdivisionFIPSCode;
    @XmlAttribute(name = "County_Subdivision_Qualifier", required = true)
    @SerializedName("CountySubdivisionQualifier")
	@Mapping(getter = "getCountySubdivQlfr", setter = "setCountySubdivQlfr", type = "String", index = 11)
    protected CountySubdivisionQualifier countySubdivisionQualifier;
    @XmlAttribute(name = "County_Subdivision_Name", required = true)
    @SerializedName("CountySubdivisionName")
	@Mapping(getter = "getCountySubdivName", setter = "setCountySubdivName", type = "String", index = 12)
    protected String countySubdivisionName;
    @XmlAttribute(name = "County_FIPS_Code")
    @SerializedName("CountyFIPSCode")
	@Mapping(getter = "getCountyFipsCode", setter = "setCountyFipsCode", type = "String", index = 13)
    protected String countyFIPSCode;
    @XmlAttribute(name = "State_FIPS_Code")
    @SerializedName("StateFIPSCode")
	@Mapping(getter = "getStateFipsCode", setter = "setStateFipsCode", type = "String", index = 14)
    protected String stateFIPSCode;
    @XmlAttribute(name = "State_Code", required = true)
    @SerializedName("StateCode")
	@Mapping(getter = "getStateCode", setter = "setStateCode", type = "String", index = 15)
    protected StateCode stateCode;
    @XmlAttribute(name = "County_Subdivision_Functional_Status_Code")
    @SerializedName("CountySubdivisionFunctionalStatusCode")
	@Mapping(getter = "getCountySubdivFuncStatusCode", setter = "setCountySubdivFuncStatusCode", type = "String", index = 16)
    protected String countySubdivisionFunctionalStatusCode;

    /**
     * Gets the value of the patientContactAddress property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patientContactAddress property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatientContactAddress().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PatientContactAddress }
     * 
     * 
     */
    public List<PatientContactAddress> getPatientContactAddress() {
        if (patientContactAddress == null) {
            patientContactAddress = new ArrayList<PatientContactAddress>();
        }
        return this.patientContactAddress;
    }

    /**
     * Gets the value of the staffContactAddress property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the staffContactAddress property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStaffContactAddress().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StaffContactAddress }
     * 
     * 
     */
    public List<StaffContactAddress> getStaffContactAddress() {
        if (staffContactAddress == null) {
            staffContactAddress = new ArrayList<StaffContactAddress>();
        }
        return this.staffContactAddress;
    }

    /**
     * Gets the value of the countySubdivisionLookupSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCountySubdivisionLookupSK() {
        return countySubdivisionLookupSK;
    }

    /**
     * Sets the value of the countySubdivisionLookupSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCountySubdivisionLookupSK(BigInteger value) {
        this.countySubdivisionLookupSK = value;
    }

    /**
     * Gets the value of the recordCreateTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getRecordCreateTimestamp() {
        return recordCreateTimestamp;
    }

    /**
     * Sets the value of the recordCreateTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordCreateTimestamp(Date value) {
        this.recordCreateTimestamp = value;
    }

    /**
     * Gets the value of the recordUpdateTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getRecordUpdateTimestamp() {
        return recordUpdateTimestamp;
    }

    /**
     * Sets the value of the recordUpdateTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordUpdateTimestamp(Date value) {
        this.recordUpdateTimestamp = value;
    }

    /**
     * Gets the value of the recordEffectiveTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getRecordEffectiveTimestamp() {
        return recordEffectiveTimestamp;
    }

    /**
     * Sets the value of the recordEffectiveTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordEffectiveTimestamp(Date value) {
        this.recordEffectiveTimestamp = value;
    }

    /**
     * Gets the value of the recordTerminationTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getRecordTerminationTimestamp() {
        return recordTerminationTimestamp;
    }

    /**
     * Sets the value of the recordTerminationTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordTerminationTimestamp(Date value) {
        this.recordTerminationTimestamp = value;
    }

    /**
     * Gets the value of the recordCreatedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecordCreatedBy() {
        return recordCreatedBy;
    }

    /**
     * Sets the value of the recordCreatedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordCreatedBy(String value) {
        this.recordCreatedBy = value;
    }

    /**
     * Gets the value of the recordUpdatedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecordUpdatedBy() {
        return recordUpdatedBy;
    }

    /**
     * Sets the value of the recordUpdatedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordUpdatedBy(String value) {
        this.recordUpdatedBy = value;
    }

    /**
     * Gets the value of the changeReasonMemo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChangeReasonMemo() {
        return changeReasonMemo;
    }

    /**
     * Sets the value of the changeReasonMemo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChangeReasonMemo(String value) {
        this.changeReasonMemo = value;
    }

    /**
     * Gets the value of the currentRecordIndicator property.
     * 
     */
    public boolean isCurrentRecordIndicator() {
        return currentRecordIndicator;
    }

    /**
     * Sets the value of the currentRecordIndicator property.
     * 
     */
    public void setCurrentRecordIndicator(boolean value) {
        this.currentRecordIndicator = value;
    }

    /**
     * Gets the value of the changeVersionID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getChangeVersionID() {
        return changeVersionID;
    }

    /**
     * Sets the value of the changeVersionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setChangeVersionID(BigInteger value) {
        this.changeVersionID = value;
    }

    /**
     * Gets the value of the countySubdivisionFIPSCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountySubdivisionFIPSCode() {
        return countySubdivisionFIPSCode;
    }

    /**
     * Sets the value of the countySubdivisionFIPSCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountySubdivisionFIPSCode(String value) {
        this.countySubdivisionFIPSCode = value;
    }

    /**
     * Gets the value of the countySubdivisionQualifier property.
     * 
     * @return
     *     possible object is
     *     {@link CountySubdivisionQualifier }
     *     
     */
    public CountySubdivisionQualifier getCountySubdivisionQualifier() {
        return countySubdivisionQualifier;
    }

    /**
     * Sets the value of the countySubdivisionQualifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link CountySubdivisionQualifier }
     *     
     */
    public void setCountySubdivisionQualifier(CountySubdivisionQualifier value) {
        this.countySubdivisionQualifier = value;
    }

    /**
     * Gets the value of the countySubdivisionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountySubdivisionName() {
        return countySubdivisionName;
    }

    /**
     * Sets the value of the countySubdivisionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountySubdivisionName(String value) {
        this.countySubdivisionName = value;
    }

    /**
     * Gets the value of the countyFIPSCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountyFIPSCode() {
        return countyFIPSCode;
    }

    /**
     * Sets the value of the countyFIPSCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountyFIPSCode(String value) {
        this.countyFIPSCode = value;
    }

    /**
     * Gets the value of the stateFIPSCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStateFIPSCode() {
        return stateFIPSCode;
    }

    /**
     * Sets the value of the stateFIPSCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStateFIPSCode(String value) {
        this.stateFIPSCode = value;
    }

    /**
     * Gets the value of the stateCode property.
     * 
     * @return
     *     possible object is
     *     {@link StateCode }
     *     
     */
    public StateCode getStateCode() {
        return stateCode;
    }

    /**
     * Sets the value of the stateCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link StateCode }
     *     
     */
    public void setStateCode(StateCode value) {
        this.stateCode = value;
    }

    /**
     * Gets the value of the countySubdivisionFunctionalStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountySubdivisionFunctionalStatusCode() {
        return countySubdivisionFunctionalStatusCode;
    }

    /**
     * Sets the value of the countySubdivisionFunctionalStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountySubdivisionFunctionalStatusCode(String value) {
        this.countySubdivisionFunctionalStatusCode = value;
    }

}