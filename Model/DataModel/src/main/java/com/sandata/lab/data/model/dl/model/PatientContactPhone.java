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
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * ERROR: String not found in file
 * 
 * <p>Java class for Patient_Contact_Phone complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Patient_Contact_Phone">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Patient_Contact_Phone_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Create_Timestamp" use="required" type="{}Record_Create_Timestamp" />
 *       &lt;attribute name="Record_Update_Timestamp" use="required" type="{}Record_Update_Timestamp" />
 *       &lt;attribute name="Record_Effective_Timestamp" use="required" type="{}Record_Effective_Timestamp" />
 *       &lt;attribute name="Record_Termination_Timestamp" use="required" type="{}Record_Termination_Timestamp" />
 *       &lt;attribute name="Record_Created_By" type="{}Record_Created_By" />
 *       &lt;attribute name="Record_Updated_By" type="{}Record_Updated_By" />
 *       &lt;attribute name="Current_Record_Indicator" use="required" type="{}Current_Record_Indicator" />
 *       &lt;attribute name="Change_Reason_Memo" type="{}Change_Reason_Memo" />
 *       &lt;attribute name="Change_Version_ID" use="required" type="{}Change_Version_ID" />
 *       &lt;attribute name="Business_Entity_ID" use="required" type="{}ID" />
 *       &lt;attribute name="Patient_ID" use="required" type="{}ID" />
 *       &lt;attribute name="Address_Priority_Name" type="{}Address_Priority_Name" />
 *       &lt;attribute name="Patient_Contact_Phone_Qualifier">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="100"/>
 *             &lt;enumeration value="HOME"/>
 *             &lt;enumeration value="WORK"/>
 *             &lt;enumeration value="MOBILE"/>
 *             &lt;enumeration value="FAX"/>
 *             &lt;enumeration value="OTHER"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Patient_Phone" type="{}Phone" />
 *       &lt;attribute name="Patient_Phone_Extension" type="{}Phone_Extension" />
 *       &lt;attribute name="Patient_Phone_Automatic_Number_Identification_Enabled_Indicator" type="{}Indicator" />
 *       &lt;attribute name="Patient_Phone_Primary_Indicator" type="{}Indicator" />
 *       &lt;attribute name="Patient_PhoneText_Message_Indicator" type="{}Indicator" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Patient_Contact_Phone")
@OracleMetadata(
        packageName = "PKG_PATIENT",
        insertMethod = "insertPtContPhone",
        updateMethod = "updatePtContPhone",
        deleteMethod = "deletePtContPhone",
        getMethod = "getPtContPhone",
        findMethod = "NotDefined_FindMethod",
        jpubClass = "com.sandata.lab.data.model.jpub.model.PtContPhoneT",
        primaryKeys = {})
public class PatientContactPhone extends BaseObject {

	private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "Patient_Contact_Phone_SK", required = true)
    @SerializedName("PatientContactPhoneSK")
	@Mapping(getter = "getPtContPhoneSk", setter = "setPtContPhoneSk", type = "java.math.BigDecimal", index = 0)
    protected BigInteger patientContactPhoneSK;
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
    @XmlAttribute(name = "Current_Record_Indicator", required = true)
    @SerializedName("CurrentRecordIndicator")
	@Mapping(getter = "getCurrRecInd", setter = "setCurrRecInd", type = "java.math.BigDecimal", index = 7)
    protected boolean currentRecordIndicator;
    @XmlAttribute(name = "Change_Reason_Memo")
    @SerializedName("ChangeReasonMemo")
	@Mapping(getter = "getChangeReasonMemo", setter = "setChangeReasonMemo", type = "String", index = 8)
    protected String changeReasonMemo;
    @XmlAttribute(name = "Change_Version_ID", required = true)
    @SerializedName("ChangeVersionID")
	@Mapping(getter = "getChangeVersionId", setter = "setChangeVersionId", type = "java.math.BigDecimal", index = 9)
    protected BigInteger changeVersionID;
    @XmlAttribute(name = "Business_Entity_ID", required = true)
    @SerializedName("BusinessEntityID")
	@Mapping(getter = "getBeId", setter = "setBeId", type = "String", index = 10)
    protected String businessEntityID;
    @XmlAttribute(name = "Patient_ID", required = true)
    @SerializedName("PatientID")
	@Mapping(getter = "getPtId", setter = "setPtId", type = "String", index = 11)
    protected String patientID;
    @XmlAttribute(name = "Address_Priority_Name")
    @SerializedName("AddressPriorityName")
	@Mapping(getter = "getAddrPrioName", setter = "setAddrPrioName", type = "String", index = 12)
    protected AddressPriorityName addressPriorityName;
    @XmlAttribute(name = "Patient_Contact_Phone_Qualifier")
    @SerializedName("PatientContactPhoneQualifier")
	@Mapping(getter = "getPtContPhoneQlfr", setter = "setPtContPhoneQlfr", type = "String", index = 13)
    protected String patientContactPhoneQualifier;
    @XmlAttribute(name = "Patient_Phone")
    @SerializedName("PatientPhone")
	@Mapping(getter = "getPtPhone", setter = "setPtPhone", type = "String", index = 14)
    protected String patientPhone;
    @XmlAttribute(name = "Patient_Phone_Extension")
    @SerializedName("PatientPhoneExtension")
	@Mapping(getter = "getPtPhoneExt", setter = "setPtPhoneExt", type = "String", index = 15)
    protected String patientPhoneExtension;
    @XmlAttribute(name = "Patient_Phone_Automatic_Number_Identification_Enabled_Indicator")
    @SerializedName("PatientPhoneAutomaticNumberIdentificationEnabledIndicator")
	@Mapping(getter = "getPtPhoneAniEnabledInd", setter = "setPtPhoneAniEnabledInd", type = "java.math.BigDecimal", index = 16)
    protected Boolean patientPhoneAutomaticNumberIdentificationEnabledIndicator;
    @XmlAttribute(name = "Patient_Phone_Primary_Indicator")
    @SerializedName("PatientPhonePrimaryIndicator")
	@Mapping(getter = "getPtPhonePrmyInd", setter = "setPtPhonePrmyInd", type = "java.math.BigDecimal", index = 17)
    protected Boolean patientPhonePrimaryIndicator;
    @XmlAttribute(name = "Patient_PhoneText_Message_Indicator")
    @SerializedName("PatientPhoneTextMessageIndicator")
	@Mapping(getter = "getPtPhonetextMsgInd", setter = "setPtPhonetextMsgInd", type = "java.math.BigDecimal", index = 18)
    protected Boolean patientPhoneTextMessageIndicator;

    /**
     * Gets the value of the patientContactPhoneSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPatientContactPhoneSK() {
        return patientContactPhoneSK;
    }

    /**
     * Sets the value of the patientContactPhoneSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPatientContactPhoneSK(BigInteger value) {
        this.patientContactPhoneSK = value;
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
     * Gets the value of the businessEntityID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessEntityID() {
        return businessEntityID;
    }

    /**
     * Sets the value of the businessEntityID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessEntityID(String value) {
        this.businessEntityID = value;
    }

    /**
     * Gets the value of the patientID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientID() {
        return patientID;
    }

    /**
     * Sets the value of the patientID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientID(String value) {
        this.patientID = value;
    }

    /**
     * Gets the value of the addressPriorityName property.
     * 
     * @return
     *     possible object is
     *     {@link AddressPriorityName }
     *     
     */
    public AddressPriorityName getAddressPriorityName() {
        return addressPriorityName;
    }

    /**
     * Sets the value of the addressPriorityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressPriorityName }
     *     
     */
    public void setAddressPriorityName(AddressPriorityName value) {
        this.addressPriorityName = value;
    }

    /**
     * Gets the value of the patientContactPhoneQualifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientContactPhoneQualifier() {
        return patientContactPhoneQualifier;
    }

    /**
     * Sets the value of the patientContactPhoneQualifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientContactPhoneQualifier(String value) {
        this.patientContactPhoneQualifier = value;
    }

    /**
     * Gets the value of the patientPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientPhone() {
        return patientPhone;
    }

    /**
     * Sets the value of the patientPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientPhone(String value) {
        this.patientPhone = value;
    }

    /**
     * Gets the value of the patientPhoneExtension property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientPhoneExtension() {
        return patientPhoneExtension;
    }

    /**
     * Sets the value of the patientPhoneExtension property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientPhoneExtension(String value) {
        this.patientPhoneExtension = value;
    }

    /**
     * Gets the value of the patientPhoneAutomaticNumberIdentificationEnabledIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPatientPhoneAutomaticNumberIdentificationEnabledIndicator() {
        return patientPhoneAutomaticNumberIdentificationEnabledIndicator;
    }

    /**
     * Sets the value of the patientPhoneAutomaticNumberIdentificationEnabledIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPatientPhoneAutomaticNumberIdentificationEnabledIndicator(Boolean value) {
        this.patientPhoneAutomaticNumberIdentificationEnabledIndicator = value;
    }

    /**
     * Gets the value of the patientPhonePrimaryIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPatientPhonePrimaryIndicator() {
        return patientPhonePrimaryIndicator;
    }

    /**
     * Sets the value of the patientPhonePrimaryIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPatientPhonePrimaryIndicator(Boolean value) {
        this.patientPhonePrimaryIndicator = value;
    }

    /**
     * Gets the value of the patientPhoneTextMessageIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPatientPhoneTextMessageIndicator() {
        return patientPhoneTextMessageIndicator;
    }

    /**
     * Sets the value of the patientPhoneTextMessageIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPatientPhoneTextMessageIndicator(Boolean value) {
        this.patientPhoneTextMessageIndicator = value;
    }

}