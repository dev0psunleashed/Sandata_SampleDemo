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


import java.math.BigInteger;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Establishes a many-to-many relationship between the Plan Of Care and Task entities
 * 
 * <p>Java class for Plan_Of_Care_Task_List complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Plan_Of_Care_Task_List">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Plan_Of_Care_Task_List_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Record_Create_Timestamp" use="required" type="{}Record_Create_Timestamp" />
 *       &lt;attribute name="Record_Update_Timestamp" use="required" type="{}Record_Update_Timestamp" />
 *       &lt;attribute name="Change_Version_ID" use="required" type="{}Change_Version_ID" />
 *       &lt;attribute name="Plan_Of_Care_SK" use="required" type="{}Surrogate_Key" />
 *       &lt;attribute name="Business_Entity_ID" use="required" type="{}ID" />
 *       &lt;attribute name="Business_Entity_Task_ID" use="required" type="{}Business_Entity_Task_ID" />
 *       &lt;attribute name="Plan_Of_Care_Start_Time_Day_1" type="{}Plan_Of_Care_Time" />
 *       &lt;attribute name="Plan_Of_Care_End_Time_Day_1" type="{}Plan_Of_Care_Time" />
 *       &lt;attribute name="Plan_Of_Care_Start_Time_Day_2" type="{}Plan_Of_Care_Time" />
 *       &lt;attribute name="Plan_Of_Care_End_Time_Day_2" type="{}Plan_Of_Care_Time" />
 *       &lt;attribute name="Plan_Of_Care_Start_Time_Day_3" type="{}Plan_Of_Care_Time" />
 *       &lt;attribute name="Plan_Of_Care_End_Time_Day_3" type="{}Plan_Of_Care_Time" />
 *       &lt;attribute name="Plan_Of_Care_Start_Time_Day_4" type="{}Plan_Of_Care_Time" />
 *       &lt;attribute name="Plan_Of_Care_End_Time_Day_4" type="{}Plan_Of_Care_Time" />
 *       &lt;attribute name="Plan_Of_Care_Start_Time_Day_5" type="{}Plan_Of_Care_Time" />
 *       &lt;attribute name="Plan_Of_Care_End_Time_Day_5" type="{}Plan_Of_Care_Time" />
 *       &lt;attribute name="Plan_Of_Care_Start_Time_Day_6" type="{}Plan_Of_Care_Time" />
 *       &lt;attribute name="Plan_Of_Care_End_Time_Day_6" type="{}Plan_Of_Care_Time" />
 *       &lt;attribute name="Plan_Of_Care_Start_Time_Day_7" type="{}Plan_Of_Care_Time" />
 *       &lt;attribute name="Plan_Of_Care_End_Time_Day_7" type="{}Plan_Of_Care_Time" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Plan_Of_Care_Task_List")
@OracleMetadata(
        packageName = "PKG_POC",
        insertMethod = "insertPocTaskLst",
        updateMethod = "updatePocTaskLst",
        deleteMethod = "deletePocTaskLst",
        getMethod = "getPocTaskLst",
        findMethod = "NotDefined_FindMethod",
        jpubClass = "com.sandata.lab.data.model.jpub.model.PocTaskLstT",
        primaryKeys = {})
public class PlanOfCareTaskList extends BaseObject {

	private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "Plan_Of_Care_Task_List_SK", required = true)
    @SerializedName("PlanOfCareTaskListSK")
	@Mapping(getter = "getPocTaskLstSk", setter = "setPocTaskLstSk", type = "java.math.BigDecimal", index = 0)
    protected BigInteger planOfCareTaskListSK;
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
    @XmlAttribute(name = "Change_Version_ID", required = true)
    @SerializedName("ChangeVersionID")
	@Mapping(getter = "getChangeVersionId", setter = "setChangeVersionId", type = "java.math.BigDecimal", index = 3)
    protected BigInteger changeVersionID;
    @XmlAttribute(name = "Plan_Of_Care_SK", required = true)
    @SerializedName("PlanOfCareSK")
	@Mapping(getter = "getPocSk", setter = "setPocSk", type = "java.math.BigDecimal", index = 4)
    protected BigInteger planOfCareSK;
    @XmlAttribute(name = "Business_Entity_ID", required = true)
    @SerializedName("BusinessEntityID")
	@Mapping(getter = "getBeId", setter = "setBeId", type = "String", index = 5)
    protected String businessEntityID;
    @XmlAttribute(name = "Business_Entity_Task_ID", required = true)
    @SerializedName("BusinessEntityTaskID")
	@Mapping(getter = "getBeTaskId", setter = "setBeTaskId", type = "String", index = 6)
    protected String businessEntityTaskID;
    @XmlAttribute(name = "Plan_Of_Care_Start_Time_Day_1")
    @SerializedName("PlanOfCareStartTimeDay1")
    @XmlJavaTypeAdapter(Adapter3 .class)
	@Mapping(getter = "getPocStartTimeDay1", setter = "setPocStartTimeDay1", type = "java.sql.Timestamp", index = 7)
    protected Date planOfCareStartTimeDay1;
    @XmlAttribute(name = "Plan_Of_Care_End_Time_Day_1")
    @SerializedName("PlanOfCareEndTimeDay1")
    @XmlJavaTypeAdapter(Adapter3 .class)
	@Mapping(getter = "getPocEndTimeDay1", setter = "setPocEndTimeDay1", type = "java.sql.Timestamp", index = 8)
    protected Date planOfCareEndTimeDay1;
    @XmlAttribute(name = "Plan_Of_Care_Start_Time_Day_2")
    @SerializedName("PlanOfCareStartTimeDay2")
    @XmlJavaTypeAdapter(Adapter3 .class)
	@Mapping(getter = "getPocStartTimeDay2", setter = "setPocStartTimeDay2", type = "java.sql.Timestamp", index = 9)
    protected Date planOfCareStartTimeDay2;
    @XmlAttribute(name = "Plan_Of_Care_End_Time_Day_2")
    @SerializedName("PlanOfCareEndTimeDay2")
    @XmlJavaTypeAdapter(Adapter3 .class)
	@Mapping(getter = "getPocEndTimeDay2", setter = "setPocEndTimeDay2", type = "java.sql.Timestamp", index = 10)
    protected Date planOfCareEndTimeDay2;
    @XmlAttribute(name = "Plan_Of_Care_Start_Time_Day_3")
    @SerializedName("PlanOfCareStartTimeDay3")
    @XmlJavaTypeAdapter(Adapter3 .class)
	@Mapping(getter = "getPocStartTimeDay3", setter = "setPocStartTimeDay3", type = "java.sql.Timestamp", index = 11)
    protected Date planOfCareStartTimeDay3;
    @XmlAttribute(name = "Plan_Of_Care_End_Time_Day_3")
    @SerializedName("PlanOfCareEndTimeDay3")
    @XmlJavaTypeAdapter(Adapter3 .class)
	@Mapping(getter = "getPocEndTimeDay3", setter = "setPocEndTimeDay3", type = "java.sql.Timestamp", index = 12)
    protected Date planOfCareEndTimeDay3;
    @XmlAttribute(name = "Plan_Of_Care_Start_Time_Day_4")
    @SerializedName("PlanOfCareStartTimeDay4")
    @XmlJavaTypeAdapter(Adapter3 .class)
	@Mapping(getter = "getPocStartTimeDay4", setter = "setPocStartTimeDay4", type = "java.sql.Timestamp", index = 13)
    protected Date planOfCareStartTimeDay4;
    @XmlAttribute(name = "Plan_Of_Care_End_Time_Day_4")
    @SerializedName("PlanOfCareEndTimeDay4")
    @XmlJavaTypeAdapter(Adapter3 .class)
	@Mapping(getter = "getPocEndTimeDay4", setter = "setPocEndTimeDay4", type = "java.sql.Timestamp", index = 14)
    protected Date planOfCareEndTimeDay4;
    @XmlAttribute(name = "Plan_Of_Care_Start_Time_Day_5")
    @SerializedName("PlanOfCareStartTimeDay5")
    @XmlJavaTypeAdapter(Adapter3 .class)
	@Mapping(getter = "getPocStartTimeDay5", setter = "setPocStartTimeDay5", type = "java.sql.Timestamp", index = 15)
    protected Date planOfCareStartTimeDay5;
    @XmlAttribute(name = "Plan_Of_Care_End_Time_Day_5")
    @SerializedName("PlanOfCareEndTimeDay5")
    @XmlJavaTypeAdapter(Adapter3 .class)
	@Mapping(getter = "getPocEndTimeDay5", setter = "setPocEndTimeDay5", type = "java.sql.Timestamp", index = 16)
    protected Date planOfCareEndTimeDay5;
    @XmlAttribute(name = "Plan_Of_Care_Start_Time_Day_6")
    @SerializedName("PlanOfCareStartTimeDay6")
    @XmlJavaTypeAdapter(Adapter3 .class)
	@Mapping(getter = "getPocStartTimeDay6", setter = "setPocStartTimeDay6", type = "java.sql.Timestamp", index = 17)
    protected Date planOfCareStartTimeDay6;
    @XmlAttribute(name = "Plan_Of_Care_End_Time_Day_6")
    @SerializedName("PlanOfCareEndTimeDay6")
    @XmlJavaTypeAdapter(Adapter3 .class)
	@Mapping(getter = "getPocEndTimeDay6", setter = "setPocEndTimeDay6", type = "java.sql.Timestamp", index = 18)
    protected Date planOfCareEndTimeDay6;
    @XmlAttribute(name = "Plan_Of_Care_Start_Time_Day_7")
    @SerializedName("PlanOfCareStartTimeDay7")
    @XmlJavaTypeAdapter(Adapter3 .class)
	@Mapping(getter = "getPocStartTimeDay7", setter = "setPocStartTimeDay7", type = "java.sql.Timestamp", index = 19)
    protected Date planOfCareStartTimeDay7;
    @XmlAttribute(name = "Plan_Of_Care_End_Time_Day_7")
    @SerializedName("PlanOfCareEndTimeDay7")
    @XmlJavaTypeAdapter(Adapter3 .class)
	@Mapping(getter = "getPocEndTimeDay7", setter = "setPocEndTimeDay7", type = "java.sql.Timestamp", index = 20)
    protected Date planOfCareEndTimeDay7;

    /**
     * Gets the value of the planOfCareTaskListSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPlanOfCareTaskListSK() {
        return planOfCareTaskListSK;
    }

    /**
     * Sets the value of the planOfCareTaskListSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPlanOfCareTaskListSK(BigInteger value) {
        this.planOfCareTaskListSK = value;
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
     * Gets the value of the planOfCareSK property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPlanOfCareSK() {
        return planOfCareSK;
    }

    /**
     * Sets the value of the planOfCareSK property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPlanOfCareSK(BigInteger value) {
        this.planOfCareSK = value;
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
     * Gets the value of the businessEntityTaskID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessEntityTaskID() {
        return businessEntityTaskID;
    }

    /**
     * Sets the value of the businessEntityTaskID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessEntityTaskID(String value) {
        this.businessEntityTaskID = value;
    }

    /**
     * Gets the value of the planOfCareStartTimeDay1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPlanOfCareStartTimeDay1() {
        return planOfCareStartTimeDay1;
    }

    /**
     * Sets the value of the planOfCareStartTimeDay1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanOfCareStartTimeDay1(Date value) {
        this.planOfCareStartTimeDay1 = value;
    }

    /**
     * Gets the value of the planOfCareEndTimeDay1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPlanOfCareEndTimeDay1() {
        return planOfCareEndTimeDay1;
    }

    /**
     * Sets the value of the planOfCareEndTimeDay1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanOfCareEndTimeDay1(Date value) {
        this.planOfCareEndTimeDay1 = value;
    }

    /**
     * Gets the value of the planOfCareStartTimeDay2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPlanOfCareStartTimeDay2() {
        return planOfCareStartTimeDay2;
    }

    /**
     * Sets the value of the planOfCareStartTimeDay2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanOfCareStartTimeDay2(Date value) {
        this.planOfCareStartTimeDay2 = value;
    }

    /**
     * Gets the value of the planOfCareEndTimeDay2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPlanOfCareEndTimeDay2() {
        return planOfCareEndTimeDay2;
    }

    /**
     * Sets the value of the planOfCareEndTimeDay2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanOfCareEndTimeDay2(Date value) {
        this.planOfCareEndTimeDay2 = value;
    }

    /**
     * Gets the value of the planOfCareStartTimeDay3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPlanOfCareStartTimeDay3() {
        return planOfCareStartTimeDay3;
    }

    /**
     * Sets the value of the planOfCareStartTimeDay3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanOfCareStartTimeDay3(Date value) {
        this.planOfCareStartTimeDay3 = value;
    }

    /**
     * Gets the value of the planOfCareEndTimeDay3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPlanOfCareEndTimeDay3() {
        return planOfCareEndTimeDay3;
    }

    /**
     * Sets the value of the planOfCareEndTimeDay3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanOfCareEndTimeDay3(Date value) {
        this.planOfCareEndTimeDay3 = value;
    }

    /**
     * Gets the value of the planOfCareStartTimeDay4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPlanOfCareStartTimeDay4() {
        return planOfCareStartTimeDay4;
    }

    /**
     * Sets the value of the planOfCareStartTimeDay4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanOfCareStartTimeDay4(Date value) {
        this.planOfCareStartTimeDay4 = value;
    }

    /**
     * Gets the value of the planOfCareEndTimeDay4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPlanOfCareEndTimeDay4() {
        return planOfCareEndTimeDay4;
    }

    /**
     * Sets the value of the planOfCareEndTimeDay4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanOfCareEndTimeDay4(Date value) {
        this.planOfCareEndTimeDay4 = value;
    }

    /**
     * Gets the value of the planOfCareStartTimeDay5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPlanOfCareStartTimeDay5() {
        return planOfCareStartTimeDay5;
    }

    /**
     * Sets the value of the planOfCareStartTimeDay5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanOfCareStartTimeDay5(Date value) {
        this.planOfCareStartTimeDay5 = value;
    }

    /**
     * Gets the value of the planOfCareEndTimeDay5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPlanOfCareEndTimeDay5() {
        return planOfCareEndTimeDay5;
    }

    /**
     * Sets the value of the planOfCareEndTimeDay5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanOfCareEndTimeDay5(Date value) {
        this.planOfCareEndTimeDay5 = value;
    }

    /**
     * Gets the value of the planOfCareStartTimeDay6 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPlanOfCareStartTimeDay6() {
        return planOfCareStartTimeDay6;
    }

    /**
     * Sets the value of the planOfCareStartTimeDay6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanOfCareStartTimeDay6(Date value) {
        this.planOfCareStartTimeDay6 = value;
    }

    /**
     * Gets the value of the planOfCareEndTimeDay6 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPlanOfCareEndTimeDay6() {
        return planOfCareEndTimeDay6;
    }

    /**
     * Sets the value of the planOfCareEndTimeDay6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanOfCareEndTimeDay6(Date value) {
        this.planOfCareEndTimeDay6 = value;
    }

    /**
     * Gets the value of the planOfCareStartTimeDay7 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPlanOfCareStartTimeDay7() {
        return planOfCareStartTimeDay7;
    }

    /**
     * Sets the value of the planOfCareStartTimeDay7 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanOfCareStartTimeDay7(Date value) {
        this.planOfCareStartTimeDay7 = value;
    }

    /**
     * Gets the value of the planOfCareEndTimeDay7 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPlanOfCareEndTimeDay7() {
        return planOfCareEndTimeDay7;
    }

    /**
     * Sets the value of the planOfCareEndTimeDay7 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanOfCareEndTimeDay7(Date value) {
        this.planOfCareEndTimeDay7 = value;
    }

}
