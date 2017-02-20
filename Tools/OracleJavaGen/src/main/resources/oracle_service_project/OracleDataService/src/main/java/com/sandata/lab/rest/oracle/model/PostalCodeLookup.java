//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.11.27 at 10:53:27 PM EST 
//


package com.sandata.lab.rest.oracle.model;

import com.sandata.lab.data.model.*;
import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Lookup table that stores postal code information
 * 
 * Source:
 * 
 * https://zipcodedownload.com
 * 
 * 
 * <p>Java class for Postal_Code_Lookup complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Postal_Code_Lookup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Postal_Code_Lookup_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Create_Timestamp" use="required" type="{}Record_Create_Timestamp" />
 *       &lt;attribute name="Record_Update_Timestamp" use="required" type="{}Record_Update_Timestamp" />
 *       &lt;attribute name="Record_Effective_Timestamp" use="required" type="{}Record_Effective_Timestamp" />
 *       &lt;attribute name="Record_Termination_Timestamp" use="required" type="{}Record_Termination_Timestamp" />
 *       &lt;attribute name="Record_Created_By" type="{}Record_Created_By" />
 *       &lt;attribute name="Record_Updated_By" type="{}Record_Updated_By" />
 *       &lt;attribute name="Change_Reason_Memo" type="{}Change_Reason_Memo" />
 *       &lt;attribute name="Current_Record_Indicator" use="required" type="{}Current_Record_Indicator" />
 *       &lt;attribute name="Change_Version_ID" use="required" type="{}Change_Version_ID" />
 *       &lt;attribute name="Postal_Code" use="required" type="{}Postal_Code" />
 *       &lt;attribute name="Postal_Code_Source_Qualifier" use="required" type="{}Postal_Code_Source_Qualifier" />
 *       &lt;attribute name="Postal_Code_United_States_Postal_Service_Type_Code" type="{}Postal_Code_United_States_Postal_Service_Type_Code" />
 *       &lt;attribute name="Postal_Code_City_Name">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="100"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Postal_Code_United_States_Postal_Service_City_Type_Code" type="{}Postal_Code_United_States_Postal_Service_City_Type_Code" />
 *       &lt;attribute name="Postal_Code_County_Name">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="100"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="County_Federal_Information_Processing_Standard_Code" type="{}Federal_Information_Processing_Standard_Code" />
 *       &lt;attribute name="Postal_Code_State_Name">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="100"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Postal_Code_State_Code" type="{}State_Code" />
 *       &lt;attribute name="State_Federal_Information_Processing_Standard_Code" type="{}Federal_Information_Processing_Standard_Code" />
 *       &lt;attribute name="Postal_Code_Metropolitan_Statistical_Area_Code">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="4"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Postal_Code_Area_Code">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="16"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Timezone_Name" type="{}Timezone_Name" />
 *       &lt;attribute name="Universal_Time_Coordinated_Offset">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *             &lt;totalDigits value="3"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Postal_Code_Daylight_Savings_Time_Indicator" type="{}Indicator" />
 *       &lt;attribute name="Postal_Code_Coordinate_Latitude" type="{}Coordinate_Latitude" />
 *       &lt;attribute name="Postal_Code_Coordinate_Longitide" type="{}Coordinate_Longitide" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Postal_Code_Lookup")
@OracleMetadata(
        packageName = "PKG_LOOKUP",
        insertMethod = "insertPstlCodeLkup",
        updateMethod = "updatePstlCodeLkup",
        deleteMethod = "deletePstlCodeLkup",
        getMethod = "getPstlCodeLkup",
        findMethod = "NotDefined_FindMethod",
        jpubClass = "com.sandata.lab.data.model.jpub.model.PstlCodeLkupT",
        primaryKeys = {})
public class PostalCodeLookup extends BaseObject {

	private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "Postal_Code_Lookup_SK", required = true)
    @SerializedName("PostalCodeLookupSK")
	@Mapping(getter = "getPstlCodeLkupSk", setter = "setPstlCodeLkupSk", type = "java.math.BigDecimal", index = 0)
    protected BigInteger postalCodeLookupSK;
    @XmlAttribute(name = "Record_Create_Timestamp", required = true)
    @SerializedName("RecordCreateTimestamp")
    @XmlJavaTypeAdapter(Adapter1 .class)
	@Mapping(getter = "getRecCreateTmstp", setter = "setRecCreateTmstp", type = "java.sql.Timestamp", index = 1)
    protected Date recordCreateTimestamp;
    @XmlAttribute(name = "Record_Update_Timestamp", required = true)
    @SerializedName("RecordUpdateTimestamp")
    @XmlJavaTypeAdapter(Adapter1 .class)
	@Mapping(getter = "getRecUpdateTmstp", setter = "setRecUpdateTmstp", type = "java.sql.Timestamp", index = 2)
    protected Date recordUpdateTimestamp;
    @XmlAttribute(name = "Record_Effective_Timestamp", required = true)
    @SerializedName("RecordEffectiveTimestamp")
    @XmlJavaTypeAdapter(Adapter1 .class)
	@Mapping(getter = "getRecEffTmstp", setter = "setRecEffTmstp", type = "java.sql.Timestamp", index = 3)
    protected Date recordEffectiveTimestamp;
    @XmlAttribute(name = "Record_Termination_Timestamp", required = true)
    @SerializedName("RecordTerminationTimestamp")
    @XmlJavaTypeAdapter(Adapter1 .class)
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
    @XmlAttribute(name = "Postal_Code", required = true)
    @SerializedName("PostalCode")
	@Mapping(getter = "getPstlCode", setter = "setPstlCode", type = "String", index = 10)
    protected String postalCode;
    @XmlAttribute(name = "Postal_Code_Source_Qualifier", required = true)
    @SerializedName("PostalCodeSourceQualifier")
	@Mapping(getter = "getPstlCodeSrcQlfr", setter = "setPstlCodeSrcQlfr", type = "String", index = 11)
    protected PostalCodeSourceQualifier postalCodeSourceQualifier;
    @XmlAttribute(name = "Postal_Code_United_States_Postal_Service_Type_Code")
    @SerializedName("PostalCodeUnitedStatesPostalServiceTypeCode")
	@Mapping(getter = "getPstlCodeUspsTypCode", setter = "setPstlCodeUspsTypCode", type = "String", index = 12)
    protected PostalCodeUnitedStatesPostalServiceTypeCode postalCodeUnitedStatesPostalServiceTypeCode;
    @XmlAttribute(name = "Postal_Code_City_Name")
    @SerializedName("PostalCodeCityName")
	@Mapping(getter = "getPstlCodeCityName", setter = "setPstlCodeCityName", type = "String", index = 13)
    protected String postalCodeCityName;
    @XmlAttribute(name = "Postal_Code_United_States_Postal_Service_City_Type_Code")
    @SerializedName("PostalCodeUnitedStatesPostalServiceCityTypeCode")
	@Mapping(getter = "getPstlCodeUspsCityTypCode", setter = "setPstlCodeUspsCityTypCode", type = "String", index = 14)
    protected PostalCodeUnitedStatesPostalServiceCityTypeCode postalCodeUnitedStatesPostalServiceCityTypeCode;
    @XmlAttribute(name = "Postal_Code_County_Name")
    @SerializedName("PostalCodeCountyName")
	@Mapping(getter = "getPstlCodeCountyName", setter = "setPstlCodeCountyName", type = "String", index = 15)
    protected String postalCodeCountyName;
    @XmlAttribute(name = "County_Federal_Information_Processing_Standard_Code")
    @SerializedName("CountyFederalInformationProcessingStandardCode")
	@Mapping(getter = "getCountyFipsCode", setter = "setCountyFipsCode", type = "String", index = 16)
    protected String countyFederalInformationProcessingStandardCode;
    @XmlAttribute(name = "Postal_Code_State_Name")
    @SerializedName("PostalCodeStateName")
	@Mapping(getter = "getPstlCodeStateName", setter = "setPstlCodeStateName", type = "String", index = 17)
    protected String postalCodeStateName;
    @XmlAttribute(name = "Postal_Code_State_Code")
    @SerializedName("PostalCodeStateCode")
	@Mapping(getter = "getPstlCodeStateCode", setter = "setPstlCodeStateCode", type = "String", index = 18)
    protected StateCode postalCodeStateCode;
    @XmlAttribute(name = "State_Federal_Information_Processing_Standard_Code")
    @SerializedName("StateFederalInformationProcessingStandardCode")
	@Mapping(getter = "getStateFipsCode", setter = "setStateFipsCode", type = "String", index = 19)
    protected String stateFederalInformationProcessingStandardCode;
    @XmlAttribute(name = "Postal_Code_Metropolitan_Statistical_Area_Code")
    @SerializedName("PostalCodeMetropolitanStatisticalAreaCode")
	@Mapping(getter = "getPstlCodeMsaCode", setter = "setPstlCodeMsaCode", type = "String", index = 20)
    protected String postalCodeMetropolitanStatisticalAreaCode;
    @XmlAttribute(name = "Postal_Code_Area_Code")
    @SerializedName("PostalCodeAreaCode")
	@Mapping(getter = "getPstlCodeAreaCode", setter = "setPstlCodeAreaCode", type = "String", index = 21)
    protected String postalCodeAreaCode;
    @XmlAttribute(name = "Timezone_Name")
    @SerializedName("TimezoneName")
	@Mapping(getter = "getTzName", setter = "setTzName", type = "String", index = 22)
    protected String timezoneName;
    @XmlAttribute(name = "Universal_Time_Coordinated_Offset")
    @SerializedName("UniversalTimeCoordinatedOffset")
	@Mapping(getter = "getUtcOffset", setter = "setUtcOffset", type = "java.math.BigDecimal", index = 23)
    protected BigDecimal universalTimeCoordinatedOffset;
    @XmlAttribute(name = "Postal_Code_Daylight_Savings_Time_Indicator")
    @SerializedName("PostalCodeDaylightSavingsTimeIndicator")
	@Mapping(getter = "getPstlCodeDstInd", setter = "setPstlCodeDstInd", type = "java.math.BigDecimal", index = 24)
    protected Boolean postalCodeDaylightSavingsTimeIndicator;
    @XmlAttribute(name = "Postal_Code_Coordinate_Latitude")
    @SerializedName("PostalCodeCoordinateLatitude")
	@Mapping(getter = "getPstlCodeCoordLatitude", setter = "setPstlCodeCoordLatitude", type = "java.math.BigDecimal", index = 25)
    protected BigDecimal postalCodeCoordinateLatitude;
    @XmlAttribute(name = "Postal_Code_Coordinate_Longitide")
    @SerializedName("PostalCodeCoordinateLongitide")
	@Mapping(getter = "getPstlCodeCoordLongitide", setter = "setPstlCodeCoordLongitide", type = "java.math.BigDecimal", index = 26)
    protected BigDecimal postalCodeCoordinateLongitide;

    /**
     * Gets the value of the postalCodeLookupSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPostalCodeLookupSK() {
        return postalCodeLookupSK;
    }

    /**
     * Sets the value of the postalCodeLookupSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPostalCodeLookupSK(BigInteger value) {
        this.postalCodeLookupSK = value;
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
     * Gets the value of the postalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the value of the postalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalCode(String value) {
        this.postalCode = value;
    }

    /**
     * Gets the value of the postalCodeSourceQualifier property.
     * 
     * @return
     *     possible object is
     *     {@link PostalCodeSourceQualifier }
     *     
     */
    public PostalCodeSourceQualifier getPostalCodeSourceQualifier() {
        return postalCodeSourceQualifier;
    }

    /**
     * Sets the value of the postalCodeSourceQualifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link PostalCodeSourceQualifier }
     *     
     */
    public void setPostalCodeSourceQualifier(PostalCodeSourceQualifier value) {
        this.postalCodeSourceQualifier = value;
    }

    /**
     * Gets the value of the postalCodeUnitedStatesPostalServiceTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link PostalCodeUnitedStatesPostalServiceTypeCode }
     *     
     */
    public PostalCodeUnitedStatesPostalServiceTypeCode getPostalCodeUnitedStatesPostalServiceTypeCode() {
        return postalCodeUnitedStatesPostalServiceTypeCode;
    }

    /**
     * Sets the value of the postalCodeUnitedStatesPostalServiceTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link PostalCodeUnitedStatesPostalServiceTypeCode }
     *     
     */
    public void setPostalCodeUnitedStatesPostalServiceTypeCode(PostalCodeUnitedStatesPostalServiceTypeCode value) {
        this.postalCodeUnitedStatesPostalServiceTypeCode = value;
    }

    /**
     * Gets the value of the postalCodeCityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalCodeCityName() {
        return postalCodeCityName;
    }

    /**
     * Sets the value of the postalCodeCityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalCodeCityName(String value) {
        this.postalCodeCityName = value;
    }

    /**
     * Gets the value of the postalCodeUnitedStatesPostalServiceCityTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link PostalCodeUnitedStatesPostalServiceCityTypeCode }
     *     
     */
    public PostalCodeUnitedStatesPostalServiceCityTypeCode getPostalCodeUnitedStatesPostalServiceCityTypeCode() {
        return postalCodeUnitedStatesPostalServiceCityTypeCode;
    }

    /**
     * Sets the value of the postalCodeUnitedStatesPostalServiceCityTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link PostalCodeUnitedStatesPostalServiceCityTypeCode }
     *     
     */
    public void setPostalCodeUnitedStatesPostalServiceCityTypeCode(PostalCodeUnitedStatesPostalServiceCityTypeCode value) {
        this.postalCodeUnitedStatesPostalServiceCityTypeCode = value;
    }

    /**
     * Gets the value of the postalCodeCountyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalCodeCountyName() {
        return postalCodeCountyName;
    }

    /**
     * Sets the value of the postalCodeCountyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalCodeCountyName(String value) {
        this.postalCodeCountyName = value;
    }

    /**
     * Gets the value of the countyFederalInformationProcessingStandardCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountyFederalInformationProcessingStandardCode() {
        return countyFederalInformationProcessingStandardCode;
    }

    /**
     * Sets the value of the countyFederalInformationProcessingStandardCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountyFederalInformationProcessingStandardCode(String value) {
        this.countyFederalInformationProcessingStandardCode = value;
    }

    /**
     * Gets the value of the postalCodeStateName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalCodeStateName() {
        return postalCodeStateName;
    }

    /**
     * Sets the value of the postalCodeStateName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalCodeStateName(String value) {
        this.postalCodeStateName = value;
    }

    /**
     * Gets the value of the postalCodeStateCode property.
     * 
     * @return
     *     possible object is
     *     {@link StateCode }
     *     
     */
    public StateCode getPostalCodeStateCode() {
        return postalCodeStateCode;
    }

    /**
     * Sets the value of the postalCodeStateCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link StateCode }
     *     
     */
    public void setPostalCodeStateCode(StateCode value) {
        this.postalCodeStateCode = value;
    }

    /**
     * Gets the value of the stateFederalInformationProcessingStandardCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStateFederalInformationProcessingStandardCode() {
        return stateFederalInformationProcessingStandardCode;
    }

    /**
     * Sets the value of the stateFederalInformationProcessingStandardCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStateFederalInformationProcessingStandardCode(String value) {
        this.stateFederalInformationProcessingStandardCode = value;
    }

    /**
     * Gets the value of the postalCodeMetropolitanStatisticalAreaCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalCodeMetropolitanStatisticalAreaCode() {
        return postalCodeMetropolitanStatisticalAreaCode;
    }

    /**
     * Sets the value of the postalCodeMetropolitanStatisticalAreaCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalCodeMetropolitanStatisticalAreaCode(String value) {
        this.postalCodeMetropolitanStatisticalAreaCode = value;
    }

    /**
     * Gets the value of the postalCodeAreaCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalCodeAreaCode() {
        return postalCodeAreaCode;
    }

    /**
     * Sets the value of the postalCodeAreaCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalCodeAreaCode(String value) {
        this.postalCodeAreaCode = value;
    }

    /**
     * Gets the value of the timezoneName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimezoneName() {
        return timezoneName;
    }

    /**
     * Sets the value of the timezoneName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimezoneName(String value) {
        this.timezoneName = value;
    }

    /**
     * Gets the value of the universalTimeCoordinatedOffset property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUniversalTimeCoordinatedOffset() {
        return universalTimeCoordinatedOffset;
    }

    /**
     * Sets the value of the universalTimeCoordinatedOffset property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUniversalTimeCoordinatedOffset(BigDecimal value) {
        this.universalTimeCoordinatedOffset = value;
    }

    /**
     * Gets the value of the postalCodeDaylightSavingsTimeIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPostalCodeDaylightSavingsTimeIndicator() {
        return postalCodeDaylightSavingsTimeIndicator;
    }

    /**
     * Sets the value of the postalCodeDaylightSavingsTimeIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPostalCodeDaylightSavingsTimeIndicator(Boolean value) {
        this.postalCodeDaylightSavingsTimeIndicator = value;
    }

    /**
     * Gets the value of the postalCodeCoordinateLatitude property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPostalCodeCoordinateLatitude() {
        return postalCodeCoordinateLatitude;
    }

    /**
     * Sets the value of the postalCodeCoordinateLatitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPostalCodeCoordinateLatitude(BigDecimal value) {
        this.postalCodeCoordinateLatitude = value;
    }

    /**
     * Gets the value of the postalCodeCoordinateLongitide property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPostalCodeCoordinateLongitide() {
        return postalCodeCoordinateLongitide;
    }

    /**
     * Sets the value of the postalCodeCoordinateLongitide property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPostalCodeCoordinateLongitide(BigDecimal value) {
        this.postalCodeCoordinateLongitide = value;
    }

}